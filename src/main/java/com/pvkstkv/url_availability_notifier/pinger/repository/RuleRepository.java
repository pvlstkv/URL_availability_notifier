package com.pvkstkv.url_availability_notifier.pinger.repository;

import com.pvkstkv.url_availability_notifier.pinger.model.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RuleRepository extends JpaRepository<Rule, Long> {
}
