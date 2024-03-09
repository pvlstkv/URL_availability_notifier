package com.pvkstkv.url_availability_notifier.url_checker;

import com.pvkstkv.url_availability_notifier.rule_api.model.Rule;
import com.pvkstkv.url_availability_notifier.rule_api.repository.RuleRepository;
import com.pvkstkv.url_availability_notifier.url_checker.messages.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class URLChecker {

    private final RuleRepository repository;

    public void start() {
        Boolean isActivated = true;
        List<Rule> rules = repository.findAllByIsActivated(isActivated);
        // as property value, but now is const
        int queueSize = 100;
        BlockingQueue<Message> messages = new LinkedBlockingQueue<>(queueSize);
        int threadPoolSize = 8;
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(threadPoolSize);
        ses.submit(new MessageTransponder(messages));
        rules.forEach(rule -> {
            ses.scheduleWithFixedDelay(new URLExecutor(rule, messages), 0, rule.getPeriodInSeconds(), TimeUnit.SECONDS);
        });
    }

}
