package com.pvkstkv.url_availability_notifier.layers.application.dto.mapper;

import com.pvkstkv.url_availability_notifier.layers.domain.Rule;
import com.pvkstkv.url_availability_notifier.layers.domain.exception.NotValidPeriodCheckException;
import com.pvkstkv.url_availability_notifier.layers.domain.exception.NotValidStatusCodeException;
import com.pvkstkv.url_availability_notifier.layers.domain.exception.NotValidUrlException;
import com.pvkstkv.url_availability_notifier.layers.application.dto.RuleDTO;
import com.pvkstkv.url_availability_notifier.layers.infrastructure.persitence.entity.RuleEntity;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RuleMapper {

    public RuleEntity toEntityFromDomain(Rule rule) {
        return new RuleEntity(rule.getUrl(), rule.getPeriodInSeconds(), rule.getExpectedStatusCode(), rule.isActivated());
    }

    public RuleDTO toDTO(Rule rule) {
        return new RuleDTO(rule.getUrl(), rule.getPeriodInSeconds(), rule.getExpectedStatusCode(), rule.isActivated());
    }

    public Rule toDomainFromDTO(RuleDTO ruleDTO, Set<Integer> allAllowedExpectedStatus) throws NotValidUrlException, NotValidStatusCodeException, NotValidPeriodCheckException {
        return new Rule(ruleDTO.getUrl(), ruleDTO.getPeriodInSeconds(), ruleDTO.getExpectedStatusCode(), allAllowedExpectedStatus, ruleDTO.getIsActivated());
    }

    public Rule toDomainFromEntity(RuleEntity ruleEntity, Set<Integer> allAllowedExpectedStatus) throws NotValidUrlException, NotValidStatusCodeException, NotValidPeriodCheckException {
        return new Rule(ruleEntity.getUrl(), ruleEntity.getPeriodInSeconds(), ruleEntity.getExpectedStatusCode(), allAllowedExpectedStatus, ruleEntity.isActivated());
    }
}
