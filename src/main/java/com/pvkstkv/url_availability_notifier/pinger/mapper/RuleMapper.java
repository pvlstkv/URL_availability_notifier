package com.pvkstkv.url_availability_notifier.pinger.mapper;

import com.pvkstkv.url_availability_notifier.pinger.dto.RuleDTO;
import com.pvkstkv.url_availability_notifier.pinger.model.Rule;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RuleMapper {

    Rule ruleDtoToRule(RuleDTO ruleDto);

    RuleDTO ruleToRuleDto(Rule rule);

    List<RuleDTO> ruleListToRuleDtoList(List<Rule> rules);
}
