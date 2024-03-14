package com.pvkstkv.url_availability_notifier.layers.domain;

import com.pvkstkv.url_availability_notifier.layers.domain.exception.NotValidPeriodCheckException;
import com.pvkstkv.url_availability_notifier.layers.domain.exception.NotValidStatusCodeException;
import com.pvkstkv.url_availability_notifier.layers.domain.exception.NotValidUrlException;
import lombok.Getter;

import java.util.Set;

@Getter
public class Rule {
    private String url;
    private long periodInSeconds;
    private int expectedStatusCode;
    private boolean isActivated;

    public Rule(String url, long periodInSeconds, int expectedStatusCode, Set<Integer> allAllowedStatusCode, boolean isActivated) throws NotValidUrlException, NotValidPeriodCheckException, NotValidStatusCodeException {
        throwIfUrlUnacceptable(url);
        throwIfPeriodInSecondsNonPositive(periodInSeconds);
        throwIfExpectedStatusCodeInvalid(expectedStatusCode, allAllowedStatusCode);
        this.url = url;
        this.periodInSeconds = periodInSeconds;
        this.expectedStatusCode = expectedStatusCode;
        this.isActivated = isActivated;
    }

    private void throwIfUrlUnacceptable(String url) throws NotValidUrlException {
        if (!url.startsWith("http:\\\\")) {
            throw new NotValidUrlException(String.format("URL %s не соответствует правилу URL", url));
        }
    }

    private void throwIfPeriodInSecondsNonPositive(long periodInSeconds) throws NotValidPeriodCheckException {
        if (periodInSeconds < 1) {
            throw new NotValidPeriodCheckException(String.format("Период опроса %d должен быть положительным числом", periodInSeconds));
        }
    }

    private void throwIfExpectedStatusCodeInvalid(int expectedStatusCode, Set<Integer> allAllowedStatusCode) throws NotValidStatusCodeException {
        if (!allAllowedStatusCode.contains(expectedStatusCode)) {
            throw new NotValidStatusCodeException(String.format("Ожидаемый статус код %d не является валидным", expectedStatusCode));
        }
    }
}
