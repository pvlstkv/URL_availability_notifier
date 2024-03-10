package com.pvkstkv.url_availability_notifier.url_checker;

import com.pvkstkv.url_availability_notifier.message_sender.Retransmittable;
import com.pvkstkv.url_availability_notifier.rule_api.model.Rule;
import com.pvkstkv.url_availability_notifier.rule_api.repository.RuleRepository;
import com.pvkstkv.url_availability_notifier.url_checker.messages.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class URLChecker implements ApplicationRunner {


    private final RuleRepository repository;
    private final Retransmittable tgBot;
    private ScheduledExecutorService ses;
    private BlockingQueue<Message> messages;

    @Value("${thredpoolsize}")
    private int threadPoolSize;

    public void start() {
        log.info("ULChecker started");
        log.debug(tgBot.toString());
        Boolean isActivated = true;
        List<Rule> rules = repository.findAllByIsActivated(isActivated);
        // as property value, but now is const
        int queueSize = 100;
        messages = new LinkedBlockingQueue<>(queueSize);
        ses = Executors.newScheduledThreadPool(threadPoolSize);
        ses.submit(new MessageTransponder(messages, tgBot));
        rules.forEach(rule -> {
            ses.scheduleWithFixedDelay(new URLExecutor(rule, messages), 0, rule.getPeriodInSeconds(), TimeUnit.SECONDS);
        });
        log.info("All checkers started");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        start();
    }

    public void add(Rule rule) {
        if (rule.getIsActivated()) {
            ses.scheduleWithFixedDelay(new URLExecutor(rule, messages), 0, rule.getPeriodInSeconds(), TimeUnit.SECONDS);
            log.warn("ACHTUNG start a new rule");
        }
    }
}
