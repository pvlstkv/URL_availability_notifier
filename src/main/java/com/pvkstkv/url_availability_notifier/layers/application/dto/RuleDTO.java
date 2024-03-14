package com.pvkstkv.url_availability_notifier.layers.application.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class RuleDTO {

    private String url;

    private Long periodInSeconds;

    private Integer expectedStatusCode;

    private Boolean isActivated;

}
