package com.pvkstkv.url_availability_notifier.pinger.service.inerface;

import com.pvkstkv.url_availability_notifier.pinger.dto.RuleDTO;
import com.pvkstkv.url_availability_notifier.pinger.model.Rule;
import com.pvkstkv.url_availability_notifier.pinger.service.excpetion.RuleNotFoundException;
import com.pvkstkv.url_availability_notifier.pinger.service.excpetion.RuleYetExistsException;

public interface RuleUseCases {
    Rule createRule(Rule rule) throws RuleYetExistsException;
    RuleDTO createRule(RuleDTO ruleDto) throws RuleYetExistsException;

    Rule updateRule(Long ruleId, Rule rule) throws RuleNotFoundException;
    Rule updateRule(Long ruleId, RuleDTO ruleDTO) throws RuleNotFoundException;
    Rule readRule(long ruleId) throws RuleNotFoundException;

    void deleteRule(long ruleId);

    void enableRule(long ruleId) throws RuleNotFoundException;

    void disableRule(long ruleId) throws RuleNotFoundException;
}
