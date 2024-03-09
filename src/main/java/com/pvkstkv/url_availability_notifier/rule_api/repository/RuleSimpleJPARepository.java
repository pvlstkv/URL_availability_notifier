//package com.pvkstkv.url_availability_notifier.pinger.repository;
//
//import com.pvkstkv.url_availability_notifier.pinger.model.Rule;
//import com.pvkstkv.url_availability_notifier.pinger.service.excpetion.RuleNotFoundException;
//import jakarta.persistence.EntityManager;
//import lombok.NonNull;
//import lombok.SneakyThrows;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.support.JpaEntityInformation;
//import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//
//@Repository
//public class RuleSimpleJPARepository implements RuleRepository {
//    @SneakyThrows
//    @NonNull
//    public Rule getById(@NonNull Long aLong) {
//        Optional<Rule> rule = findById(aLong);
//
//        return rule.orElseThrow(RuleNotFoundException::new);
//    }
//
//}
