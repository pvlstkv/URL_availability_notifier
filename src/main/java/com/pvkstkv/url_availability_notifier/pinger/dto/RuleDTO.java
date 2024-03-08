package com.pvkstkv.url_availability_notifier.pinger.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class RuleDTO {
    private Long id;

    private String url;

    private Long periodInSeconds;

    private Integer expectedStatusCode;

    private Boolean isActivated;

}
