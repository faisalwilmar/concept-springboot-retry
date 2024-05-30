package com.demo.retryer.service.bookledger;

import com.demo.retryer.dto.bookledger.BookLedgerResponse;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

public interface BookLedger {

    @Retryable(retryFor = {HttpClientErrorException.class, HttpServerErrorException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 3000, multiplier = 2, maxDelay = 15000)
    )
    public BookLedgerResponse postNewBookLedger(String bookName);

    @Recover
    public BookLedgerResponse getBackendResponseFallback(HttpClientErrorException e,
                                             String bookName);
}
