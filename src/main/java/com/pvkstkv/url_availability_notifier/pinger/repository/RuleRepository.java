package com.pvkstkv.url_availability_notifier.pinger.repository;

import com.pvkstkv.url_availability_notifier.pinger.model.Rule;
import com.pvkstkv.url_availability_notifier.pinger.service.excpetion.RuleNotFoundException;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    Optional<Rule> findByUrl(String url);

    @SneakyThrows
    default Rule getById(Long id) {
        Optional<Rule> rule = findById(id);
        return rule.orElseThrow(() -> new RuleNotFoundException(String.format("Правило с идентификатором %d ", id)));
    }
}
