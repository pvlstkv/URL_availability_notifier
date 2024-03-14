package com.pvkstkv.url_availability_notifier.layers.presentation.web.api;

import com.pvkstkv.url_availability_notifier.layers.application.dto.RuleDTO;
import com.pvkstkv.url_availability_notifier.layers.infrastructure.persitence.repository.exception.RuleNotFoundException;
import com.pvkstkv.url_availability_notifier.layers.application.exception.RuleYetExistsException;
import com.pvkstkv.url_availability_notifier.layers.application.inerface.RuleUseCases;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rules")
@AllArgsConstructor
public class RuleController {
    private RuleUseCases ruleUsecases;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RuleDTO create(@RequestBody RuleDTO ruleDTO) throws RuleYetExistsException {
        return ruleUsecases.createRule(ruleDTO);
    }

    @PutMapping("/{id}")
    public RuleDTO update(@PathVariable Long id, @RequestBody RuleDTO ruleDTO)
            throws RuleNotFoundException, RuleYetExistsException {
        return ruleUsecases.updateRule(id, ruleDTO);
    }

    @GetMapping("/{id}")
    public RuleDTO getRule(@PathVariable Long id) throws RuleNotFoundException {
        return ruleUsecases.readRule(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ruleUsecases.deleteRule(id);
    }

    @GetMapping("/health")
    public String hello() {
        return "Hello world!";
    }
}
