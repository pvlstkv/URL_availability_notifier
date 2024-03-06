package com.pvkstkv.url_availability_notifier.pinger.service.inerface;

import com.pvkstkv.url_availability_notifier.pinger.model.Rule;
import com.pvkstkv.url_availability_notifier.pinger.service.excpetion.RuleNotFoundException;

public interface RuleUseCases {
    Rule createRule(Rule rule);

    Rule updateRule(Rule rule);

    Rule readRule(long ruleId) throws RuleNotFoundException;

    void deleteRule(long ruleId);

    void enableRule(long ruleId) throws RuleNotFoundException;

    void disableRule(long ruleId) throws RuleNotFoundException;
}
