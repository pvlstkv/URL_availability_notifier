package com.pvkstkv.url_availability_notifier.pinger.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class RuleDTO {

    private String url;

    private Long periodInSeconds;

    private Short expectedStatusCode;

    private Boolean isEnabled;

}
