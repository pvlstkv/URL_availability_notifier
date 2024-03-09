package com.pvkstkv.pinger;

import com.pvkstkv.url_availability_notifier.UrlAvailabilityNotifierApplication;
import com.pvkstkv.url_availability_notifier.rule_api.dto.RuleDTO;
import com.pvkstkv.url_availability_notifier.rule_api.mapper.RuleMapper;
import com.pvkstkv.url_availability_notifier.rule_api.model.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = UrlAvailabilityNotifierApplication.class)
public class RuleMapperTests {

    @Autowired
    private RuleMapper mapper;

    @Test
    public void checkMapperToDto() {
        Rule rule = new Rule(1L, "https://ya.ru", 100L, 200, true);
        RuleDTO ruleDTO = mapper.ruleToRuleDto(rule);
        assertNotNull(ruleDTO);
        assertEquals(rule.getId(), ruleDTO.getId());
        assertEquals(rule.getUrl(), ruleDTO.getUrl());
        assertEquals(rule.getExpectedStatusCode(), ruleDTO.getExpectedStatusCode());
        assertEquals(rule.getPeriodInSeconds(), ruleDTO.getPeriodInSeconds());
        assertEquals(rule.getIsActivated(), ruleDTO.getIsActivated());

    }

    @Test
    public void checkMapperToEntity() {
        RuleDTO ruleDTO = new RuleDTO(1L, "https://ya.ru", 100L, 200, true);
        Rule rule = mapper.ruleDtoToRule(ruleDTO);
        assertNotNull(rule);
        assertEquals(rule.getId(), ruleDTO.getId());
        assertEquals(rule.getUrl(), ruleDTO.getUrl());
        assertEquals(rule.getExpectedStatusCode(), ruleDTO.getExpectedStatusCode());
        assertEquals(rule.getPeriodInSeconds(), ruleDTO.getPeriodInSeconds());
        assertEquals(rule.getIsActivated(), ruleDTO.getIsActivated());
    }

    @Test
    public void checkMapperToRuleDTOList(){
        Rule rule = new Rule(1L, "https://ya.ru", 100L, 200, true);
        List<Rule> rules = List.of(rule);
        List<RuleDTO> ruleDTOS = mapper.ruleListToRuleDtoList(rules);
        assertNotNull(ruleDTOS);
        assertEquals(rules.size(), ruleDTOS.size());

        RuleDTO ruleDTO = ruleDTOS.get(0);
        assertNotNull(ruleDTO);
        assertEquals(rule.getId(), ruleDTO.getId());
        assertEquals(rule.getUrl(), ruleDTO.getUrl());
        assertEquals(rule.getExpectedStatusCode(), ruleDTO.getExpectedStatusCode());
        assertEquals(rule.getPeriodInSeconds(), ruleDTO.getPeriodInSeconds());
        assertEquals(rule.getIsActivated(), ruleDTO.getIsActivated());

    }
}
