package com.pvkstkv.url_availability_notifier.layers.infrastructure.persitence.repository;

import com.pvkstkv.url_availability_notifier.layers.infrastructure.persitence.entity.RuleEntity;
import com.pvkstkv.url_availability_notifier.layers.infrastructure.persitence.repository.exception.RuleNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuleRepository extends JpaRepository<RuleEntity, Long> {
    Optional<RuleEntity> findByUrl(String url);

    default RuleEntity readOrThrowById(Long id) throws RuleNotFoundException {
        Optional<RuleEntity> rule = findById(id);
        return rule.orElseThrow(() -> new RuleNotFoundException(String.format("Правило с идентификатором %d не найдено", id)));
    }
}
