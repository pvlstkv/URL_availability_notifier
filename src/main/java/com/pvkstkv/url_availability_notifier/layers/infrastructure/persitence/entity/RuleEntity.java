package com.pvkstkv.url_availability_notifier.layers.infrastructure.persitence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class RuleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private long periodInSeconds;
    private int expectedStatusCode;
    private boolean isActivated = true;

    public RuleEntity(String url, long periodInSeconds, int expectedStatusCode, boolean isActivated) {
        this.url = url;
        this.periodInSeconds = periodInSeconds;
        this.expectedStatusCode = expectedStatusCode;
        this.isActivated = isActivated;
    }
}
