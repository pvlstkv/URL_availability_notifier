package com.pvkstkv.url_availability_notifier.pinger.controller;

import com.pvkstkv.url_availability_notifier.RuleDTO;
import com.pvkstkv.url_availability_notifier.pinger.model.Rule;
import com.pvkstkv.url_availability_notifier.pinger.service.inerface.RuleUseCases;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/rule")
public class RuleController {
    private RuleUseCases ruleUsecases;

    @PostMapping("/create")
    public void createRule(RuleDTO ruleDTO) {
        ruleUsecases.createRule(new Rule(
                ruleDTO.getUrl(), ruleDTO.getPeriodInSeconds(), ruleDTO.getExpectedStatusCode())
        );
    }

    @GetMapping
    public String hello(){
        return "Hello world!";
    }
}
