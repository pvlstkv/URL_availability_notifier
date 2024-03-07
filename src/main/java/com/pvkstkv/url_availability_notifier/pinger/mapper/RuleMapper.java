package com.pvkstkv.url_availability_notifier.pinger.mapper;

import com.pvkstkv.url_availability_notifier.pinger.dto.RuleDTO;
import com.pvkstkv.url_availability_notifier.pinger.model.Rule;
import org.springframework.stereotype.Component;

@Component
public class RuleMapper {
    public RuleDTO toDTO(Rule rule){
        return new RuleDTO(rule.getUrl(), rule.getPeriodInSeconds(), rule.getExpectedStatusCode(), rule.isEnabled());
    }
    public Rule toEntity(RuleDTO ruleDTO){
        return new Rule(ruleDTO.getUrl(), ruleDTO.getPeriodInSeconds(), ruleDTO.getExpectedStatusCode(), ruleDTO.getIsEnabled());
    }
}
