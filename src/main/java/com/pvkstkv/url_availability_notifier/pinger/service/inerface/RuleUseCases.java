package com.pvkstkv.url_availability_notifier.pinger.service.inerface;

import com.pvkstkv.url_availability_notifier.pinger.dto.RuleDTO;
import com.pvkstkv.url_availability_notifier.pinger.model.Rule;
import com.pvkstkv.url_availability_notifier.pinger.service.excpetion.RuleNotFoundException;
import com.pvkstkv.url_availability_notifier.pinger.service.excpetion.RuleYetExistsException;

import java.util.List;

public interface RuleUseCases {
    RuleDTO createRule(RuleDTO ruleDto) throws RuleYetExistsException;

    RuleDTO updateRule(Long ruleId, RuleDTO ruleDTO) throws RuleNotFoundException;

    RuleDTO readRule(Long ruleId) throws RuleNotFoundException;

    void deleteRule(Long ruleId);

    RuleDTO changeActivation(Long ruleId, Boolean isActivated);

    List<RuleDTO> getAllRules();
}
