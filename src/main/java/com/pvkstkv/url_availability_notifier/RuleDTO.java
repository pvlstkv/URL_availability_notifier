package com.pvkstkv.url_availability_notifier;

import lombok.Data;
import lombok.NonNull;

@Data

public class RuleDTO {
    @NonNull
    private String url;
    @NonNull
    private long periodInSeconds;
    @NonNull
    private short expectedStatusCode;
}
