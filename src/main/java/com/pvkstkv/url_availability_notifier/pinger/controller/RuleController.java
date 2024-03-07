package com.pvkstkv.url_availability_notifier.pinger.controller;

import com.pvkstkv.url_availability_notifier.pinger.dto.RuleDTO;
import com.pvkstkv.url_availability_notifier.pinger.mapper.RuleMapper;
import com.pvkstkv.url_availability_notifier.pinger.model.Rule;
import com.pvkstkv.url_availability_notifier.pinger.service.excpetion.RuleNotFoundException;
import com.pvkstkv.url_availability_notifier.pinger.service.excpetion.RuleYetExistsException;
import com.pvkstkv.url_availability_notifier.pinger.service.inerface.RuleUseCases;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rule")
@AllArgsConstructor
public class RuleController {
    private RuleUseCases ruleUsecases;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RuleDTO create(@RequestBody RuleDTO ruleDTO) throws RuleYetExistsException {
        return ruleUsecases.createRule(ruleDTO);
    }


    @PutMapping("/{id}")
    public RuleDTO update(@PathVariable Long id, @RequestBody RuleDTO ruleDTO) throws RuleNotFoundException {
//        ruleUsecases.updateRule(id, mapper.toEntity(ruleDTO));
        return ruleDTO;
    }

    @GetMapping("/{id}")
    public RuleDTO getRule(@PathVariable Long id) throws RuleNotFoundException {
        var r = ruleUsecases.readRule(id);
        return new RuleDTO(r.getUrl(), r.getPeriodInSeconds(), r.getExpectedStatusCode(), r.isEnabled());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ruleUsecases.deleteRule(id);
    }

    @GetMapping
    public String hello() {
        return "Hello world!";
    }
}
