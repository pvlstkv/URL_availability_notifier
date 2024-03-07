package com.pvkstkv.url_availability_notifier.pinger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WholeRuleDto {

    private String url;
    private Long periodInSeconds;
    private Short expectedStatusCode;
    private Boolean isEnabled;
}
