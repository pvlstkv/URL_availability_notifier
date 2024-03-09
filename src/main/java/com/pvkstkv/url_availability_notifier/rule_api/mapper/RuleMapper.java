package com.pvkstkv.url_availability_notifier.rule_api.mapper;

import com.pvkstkv.url_availability_notifier.rule_api.dto.RuleDTO;
import com.pvkstkv.url_availability_notifier.rule_api.model.Rule;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RuleMapper {

    Rule ruleDtoToRule(RuleDTO ruleDto);

    RuleDTO ruleToRuleDto(Rule rule);

    List<RuleDTO> ruleListToRuleDtoList(List<Rule> rules);
}
