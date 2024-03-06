package com.pvkstkv.url_availability_notifier.pinger.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private long periodInSeconds;
    private short expectedStatusCode;
    private boolean isEnabled = true;

    public Rule(String url, long periodInSeconds, short expectedStatusCode) {
        this.url = url;
        this.periodInSeconds = periodInSeconds;
        this.expectedStatusCode = expectedStatusCode;
    }
}
