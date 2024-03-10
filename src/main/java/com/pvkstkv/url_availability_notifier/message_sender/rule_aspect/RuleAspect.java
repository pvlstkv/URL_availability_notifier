package com.pvkstkv.url_availability_notifier.message_sender.rule_aspect;

import com.pvkstkv.url_availability_notifier.rule_api.model.Rule;
import com.pvkstkv.url_availability_notifier.url_checker.URLChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Configuration
@EnableAspectJAutoProxy
@Component
@RequiredArgsConstructor
@Slf4j
public class RuleAspect {

    private final URLChecker urlChecker;

    @After("execution(* com.pvkstkv.url_availability_notifier.rule_api.repository.RuleRepository.save(..))")
    public void changeRuleState(JoinPoint joinPoint) {
        var rule = joinPoint.getArgs()[0];
        log.info("change rule state: " + rule);
        if (rule instanceof Rule) {
            urlChecker.add((Rule) rule);
        }
    }

    @After("execution(* com.pvkstkv.url_availability_notifier.rule_api.repository.RuleRepository.deleteById(..))")
    public void deleteRule(JoinPoint joinPoint) {
        var ruleId = joinPoint.getArgs()[0];
        log.info("delete ruleId: " + ruleId);
        if (ruleId instanceof Long) {
            urlChecker.remove((Long) ruleId);
        }
    }
}
