package com.pvkstkv.url_availability_notifier.layers.application.inerface;

import com.pvkstkv.url_availability_notifier.layers.application.dto.RuleDTO;
import com.pvkstkv.url_availability_notifier.layers.infrastructure.persitence.repository.exception.RuleNotFoundException;
import com.pvkstkv.url_availability_notifier.layers.application.exception.RuleYetExistsException;

public interface RuleUseCases {
    RuleDTO createRule(RuleDTO ruleDto) throws RuleYetExistsException;

    RuleDTO updateRule(Long ruleId, RuleDTO ruleDTO) throws RuleNotFoundException, RuleYetExistsException;

    RuleDTO readRule(Long ruleId) throws RuleNotFoundException;

    void deleteRule(Long ruleId);

    RuleDTO changeRuleActivating(Long ruleId, Boolean isActivated) throws RuleNotFoundException;
}
