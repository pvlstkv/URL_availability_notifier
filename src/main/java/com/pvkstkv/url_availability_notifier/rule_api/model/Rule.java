package com.pvkstkv.url_availability_notifier.rule_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private Long periodInSeconds;
    private Integer expectedStatusCode;
    private Boolean isActivated;

    public Rule(String url, Long periodInSeconds, Integer expectedStatusCode, Boolean isActivated) {
        this.url = url;
        this.periodInSeconds = periodInSeconds;
        this.expectedStatusCode = expectedStatusCode;
        this.isActivated = isActivated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return Objects.equals(id, rule.id) && Objects.equals(url, rule.url) && Objects.equals(periodInSeconds, rule.periodInSeconds) && Objects.equals(expectedStatusCode, rule.expectedStatusCode) && Objects.equals(isActivated, rule.isActivated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, periodInSeconds, expectedStatusCode, isActivated);
    }

    @Override
    public String toString() {
        return "Правило{" +
                ", url='" + url + '\'' +
                ", период проверки в секундах=" + periodInSeconds +
                ", ожидаемый статус код=" + expectedStatusCode +
                ", активен=" + isActivated +
                '}';
    }
}
