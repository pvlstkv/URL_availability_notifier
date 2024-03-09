package com.pvkstkv.url_availability_notifier.rule_api.controller;

import com.pvkstkv.url_availability_notifier.rule_api.dto.RuleDTO;
import com.pvkstkv.url_availability_notifier.rule_api.service.excpetion.RuleNotFoundException;
import com.pvkstkv.url_availability_notifier.rule_api.service.excpetion.RuleYetExistsException;
import com.pvkstkv.url_availability_notifier.rule_api.service.interfaces.RuleUseCases;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ruleUsecases.readRule(id);
    }

    @GetMapping("/all")
    public List<RuleDTO> getAllRules(){
        return ruleUsecases.getAllRules();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ruleUsecases.deleteRule(id);
    }

//    @PutMapping({"/activating"})
//    public RuleDTO changeActivating(@RequestParam Boolean isActivated){
//        return ruleUsecases.changeActivation()
//    }



    @GetMapping
    public String hello() {
        return "Hello world!";
    }
}
