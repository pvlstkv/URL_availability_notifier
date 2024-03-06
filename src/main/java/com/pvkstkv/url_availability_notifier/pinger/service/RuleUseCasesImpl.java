package com.pvkstkv.url_availability_notifier.pinger.service;

import com.pvkstkv.url_availability_notifier.pinger.model.Rule;
import com.pvkstkv.url_availability_notifier.pinger.repository.RuleRepository;
import com.pvkstkv.url_availability_notifier.pinger.service.excpetion.RuleNotFoundException;
import com.pvkstkv.url_availability_notifier.pinger.service.inerface.RuleUseCases;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RuleUseCasesImpl implements RuleUseCases {
    private RuleRepository repository;

    @Override
    public Rule createRule(Rule rule) {
        return repository.save(rule);
    }

    @Override
    public Rule updateRule(Rule rule) {
        return repository.save(rule);
    }

    @Override
    public Rule readRule(long ruleId) throws RuleNotFoundException {
        return repository.findById(ruleId).orElseThrow(
                () -> new RuleNotFoundException(String.format("Правило с идентификатором %d ", ruleId))
        );
    }

    @Override
    public void deleteRule(long ruleId) {
        repository.deleteById(ruleId);
    }

    @Override
    public void enableRule(long ruleId) throws RuleNotFoundException {
        Rule rule = readRule(ruleId);
        rule.setEnabled(true);
    }

    @Override
    public void disableRule(long ruleId) throws RuleNotFoundException {
        Rule rule = readRule(ruleId);
        rule.setEnabled(false);
    }
}
