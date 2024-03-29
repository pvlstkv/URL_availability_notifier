package com.pvkstkv.url_availability_notifier.rule_api.service;

import com.pvkstkv.url_availability_notifier.rule_api.dto.RuleDTO;
import com.pvkstkv.url_availability_notifier.rule_api.mapper.RuleMapper;
import com.pvkstkv.url_availability_notifier.rule_api.model.Rule;
import com.pvkstkv.url_availability_notifier.rule_api.repository.RuleRepository;
import com.pvkstkv.url_availability_notifier.rule_api.service.excpetion.RuleNotFoundException;
import com.pvkstkv.url_availability_notifier.rule_api.service.excpetion.RuleYetExistsException;
import com.pvkstkv.url_availability_notifier.rule_api.service.interfaces.RuleUseCases;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RuleUseCasesImpl implements RuleUseCases {

    private RuleRepository repository;
    private RuleMapper mapper;

    @Transactional
    @Override
    public RuleDTO createRule(RuleDTO ruleDto) throws RuleYetExistsException {
        Rule rule = mapper.ruleDtoToRule(ruleDto);
        if (rule.getIsActivated() == null) {
            rule.setIsActivated(true);
        }
        Optional<Rule> existRule = repository.findByUrl(rule.getUrl());
        if (existRule.isPresent()) {
            throw new RuleYetExistsException(String.format("Правило с url = %s уже существует", rule.getUrl()));
        }
        Rule SavedRule = repository.save(rule);
        return mapper.ruleToRuleDto(SavedRule);
    }

    @Transactional
    @Override
    public RuleDTO updateRule(Long ruleId, RuleDTO ruleDTO) {
        Rule existRule = repository.getById(ruleId);
        existRule.setIsActivated(ruleDTO.getIsActivated());
        existRule.setUrl(ruleDTO.getUrl());
        existRule.setPeriodInSeconds(ruleDTO.getPeriodInSeconds());
        existRule.setExpectedStatusCode(ruleDTO.getExpectedStatusCode());
        Rule savedRule = repository.save(existRule);
        return mapper.ruleToRuleDto(savedRule);
    }

    @Override
    public RuleDTO readRule(Long ruleId) {
        Rule foundRule = repository.getById(ruleId);
        return mapper.ruleToRuleDto(foundRule);
    }

    @Override
    public void deleteRule(Long ruleId) {
        repository.deleteById(ruleId);
    }

    @Override
    public RuleDTO changeActivation(Long ruleId, Boolean isActivated) {
        Rule existRule = repository.getById(ruleId);
        existRule.setIsActivated(isActivated);
        Rule savedRule = repository.save(existRule);
        return mapper.ruleToRuleDto(savedRule);
    }

    @Override
    public List<RuleDTO> getAllRules() {
        List<Rule> rules = repository.findAll();
        return mapper.ruleListToRuleDtoList(rules);
    }
}
