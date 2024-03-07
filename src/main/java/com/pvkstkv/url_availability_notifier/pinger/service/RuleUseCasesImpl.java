package com.pvkstkv.url_availability_notifier.pinger.service;

import com.pvkstkv.url_availability_notifier.pinger.dto.RuleDTO;
import com.pvkstkv.url_availability_notifier.pinger.mapper.RuleMapper;
import com.pvkstkv.url_availability_notifier.pinger.model.Rule;
import com.pvkstkv.url_availability_notifier.pinger.repository.RuleRepository;
import com.pvkstkv.url_availability_notifier.pinger.service.excpetion.RuleNotFoundException;
import com.pvkstkv.url_availability_notifier.pinger.service.excpetion.RuleYetExistsException;
import com.pvkstkv.url_availability_notifier.pinger.service.inerface.RuleUseCases;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RuleUseCasesImpl implements RuleUseCases {
    private RuleRepository repository;
    private RuleMapper mapper;

    @Transactional
    @Override
    public Rule createRule(Rule rule) throws RuleYetExistsException {
        Optional<Rule> existRule = repository.findByUrl(rule.getUrl());
        if (existRule.isPresent()) {
            throw new RuleYetExistsException(String.format("Правило с url = %s уже существует", rule.getUrl()));
        }
        return repository.save(rule);
    }

    @Transactional
    @Override
    public RuleDTO createRule(RuleDTO ruleDto) throws RuleYetExistsException {
        Rule rule = mapper.toEntity(ruleDto);
        Optional<Rule> existRule = repository.findByUrl(rule.getUrl());
        existRule.orElseThrow(() -> new RuleYetExistsException(String.format("Правило с url = %s уже существует", rule.getUrl())));
        Rule SavedRule = repository.save(rule);
        return mapper.toDTO(SavedRule);
    }

    @Transactional
    @Override
    public Rule updateRule(Long ruleId, Rule rule) {
//        Rule oldRule = repository.findById(ruleId).orElseThrow(
//                () -> new RuleNotFoundException(String.format("Правило с идентификатором %d ", ruleId)));

        Rule oldRule = repository.getById(ruleId);
        rule.setId(oldRule.getId());
        return repository.save(rule);
    }

    @Transactional
    @Override
    public Rule updateRule(Long ruleId, RuleDTO ruleDTO) throws RuleNotFoundException {
        var entityWithoutId = mapper.toEntity(ruleDTO);
        entityWithoutId.setId(ruleId);
        return repository.save(entityWithoutId);
    }

    @Override
    public Rule readRule(long ruleId) throws RuleNotFoundException {
//        return repository.findById(ruleId).orElseThrow(
//                () -> new RuleNotFoundException(String.format("Правило с идентификатором %d ", ruleId))
//        );
        return repository.getById(ruleId);
    }

    @Override
    public void deleteRule(long ruleId) {
        repository.deleteById(ruleId);
    }

    @Transactional
    @Override
    public void enableRule(long ruleId) throws RuleNotFoundException {
        Rule rule = readRule(ruleId);
        rule.setEnabled(true);
        repository.save(rule);
    }

    @Transactional
    @Override
    public void disableRule(long ruleId) throws RuleNotFoundException {
        Rule rule = readRule(ruleId);
        rule.setEnabled(false);
        repository.save(rule);
    }
}
