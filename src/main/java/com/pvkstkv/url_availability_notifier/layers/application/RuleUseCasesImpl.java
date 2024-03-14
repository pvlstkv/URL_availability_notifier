package com.pvkstkv.url_availability_notifier.layers.application;

import com.pvkstkv.url_availability_notifier.layers.domain.Rule;
import com.pvkstkv.url_availability_notifier.layers.domain.exception.NotValidPeriodCheckException;
import com.pvkstkv.url_availability_notifier.layers.domain.exception.NotValidStatusCodeException;
import com.pvkstkv.url_availability_notifier.layers.domain.exception.NotValidUrlException;
import com.pvkstkv.url_availability_notifier.layers.infrastructure.persitence.entity.RuleEntity;
import com.pvkstkv.url_availability_notifier.layers.infrastructure.persitence.repository.RuleRepository;
import com.pvkstkv.url_availability_notifier.layers.infrastructure.persitence.repository.exception.RuleNotFoundException;
import com.pvkstkv.url_availability_notifier.layers.application.dto.RuleDTO;
import com.pvkstkv.url_availability_notifier.layers.application.dto.mapper.RuleMapper;
import com.pvkstkv.url_availability_notifier.layers.application.exception.RuleYetExistsException;
import com.pvkstkv.url_availability_notifier.layers.application.inerface.RuleUseCases;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleUseCasesImpl implements RuleUseCases {
    private RuleRepository repository;
    private RuleMapper mapper;

    @Transactional
    @Override
    public RuleDTO createRule(RuleDTO ruleDto) throws RuleYetExistsException {
        try {
            Rule ruleDomain = getDomain(ruleDto);
            RuleEntity rule = getRuleEntity(ruleDomain);
            RuleEntity savedRuleEntity = repository.save(rule);
            return getRuleDTO(savedRuleEntity);
        } catch (NotValidUrlException | NotValidStatusCodeException | NotValidPeriodCheckException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public RuleDTO updateRule(Long ruleId, RuleDTO ruleDTO) throws RuleYetExistsException, RuleNotFoundException {
        try {
            var ruleDomain = getDomain(ruleDTO);
            var newRuleEntity = getRuleEntity(ruleDomain);
            var oldRuleEntity = repository.readOrThrowById(ruleId);
            newRuleEntity.setId(oldRuleEntity.getId());
            var savedRuleEntity = repository.save(newRuleEntity);
            return getRuleDTO(savedRuleEntity);
        } catch (NotValidUrlException | NotValidStatusCodeException | NotValidPeriodCheckException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public RuleDTO readRule(Long ruleId) throws RuleNotFoundException {
        try {
            RuleEntity foundRule = repository.readOrThrowById(ruleId);
            return getRuleDTO(foundRule);
        } catch (NotValidUrlException | NotValidStatusCodeException | NotValidPeriodCheckException e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional
    @Override
    public void deleteRule(Long ruleId) {
        repository.deleteById(ruleId);
    }

    @Override
    public RuleDTO changeRuleActivating(Long ruleId, Boolean isActivated) throws RuleNotFoundException {
        try {
            RuleEntity existRule = repository.readOrThrowById(ruleId);
            existRule.setActivated(isActivated);
            RuleEntity updatedRule = repository.save(existRule);
            return getRuleDTO(updatedRule);
        } catch (NotValidUrlException | NotValidStatusCodeException | NotValidPeriodCheckException e) {
            throw new RuntimeException(e);
        }
    }


    private RuleDTO getRuleDTO(RuleEntity entity) throws NotValidUrlException, NotValidStatusCodeException, NotValidPeriodCheckException {
        Set<Integer> allAllowedStatusCodes = getAllAllowedHttpStatusCodes();
        Rule ruleDomain = mapper.toDomainFromEntity(entity, allAllowedStatusCodes);
        return mapper.toDTO(ruleDomain);
    }

    private RuleEntity getRuleEntity(Rule ruleDomain) throws RuleYetExistsException {
        RuleEntity rule = mapper.toEntityFromDomain(ruleDomain);
        Optional<RuleEntity> existRule = repository.findByUrl(rule.getUrl());
        existRule.orElseThrow(() -> new RuleYetExistsException(String.format("Правило с url = %s уже существует", rule.getUrl())));
        return rule;
    }

    private Rule getDomain(RuleDTO ruleDto) throws NotValidUrlException, NotValidStatusCodeException, NotValidPeriodCheckException {
        Set<Integer> allAllowedStatusCodes = getAllAllowedHttpStatusCodes();
        return mapper.toDomainFromDTO(ruleDto, allAllowedStatusCodes);
    }

    private Set<Integer> getAllAllowedHttpStatusCodes() {
        HttpStatus[] httpStatuses = HttpStatus.values();
        Set<Integer> allAllowedHttpStatusCodes = new HashSet<>();
        for (HttpStatus status : httpStatuses) {
            allAllowedHttpStatusCodes.add(status.value());
        }
        return allAllowedHttpStatusCodes;
    }
}
