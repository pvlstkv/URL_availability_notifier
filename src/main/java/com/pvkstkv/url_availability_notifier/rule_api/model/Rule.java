package com.pvkstkv.url_availability_notifier.rule_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
