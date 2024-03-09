package com.pvkstkv.url_availability_notifier.rule_api.repository;

import com.pvkstkv.url_availability_notifier.rule_api.model.Rule;
import com.pvkstkv.url_availability_notifier.rule_api.service.excpetion.RuleNotFoundException;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    Optional<Rule> findByUrl(String url);

    List<Rule> findAllByIsActivated(Boolean isActivated);

    @SneakyThrows
    default @NonNull Rule getById(@NonNull Long id) {
        Optional<Rule> rule = findById(id);
        return rule.orElseThrow(() -> new RuleNotFoundException(
                String.format("Правило с идентификатором %d не существует ", id)));
    }
}
