package com.demo.retryer.service.bookledger;

import com.demo.retryer.dto.bookledger.BookLedgerResponse;
import com.demo.retryer.exception.CustomException;
import com.demo.retryer.service.bookledgerext.BookLedgerExtService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookLedgerImpl implements BookLedger {

    private final BookLedgerExtService bookLedgerExtService;

    @Override
    public BookLedgerResponse postNewBookLedger(String bookTitle) {
        try {
            log.info(LocalDateTime.now() + " [Execute] Original execution of postNewBookLedger retryable function");
            return bookLedgerExtService.postNewTransaction(bookTitle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookLedgerResponse getBackendResponseFallback(HttpClientErrorException e, String bookName) {
        log.error(LocalDateTime.now() + " [Fallback] Fallback called after multiple retry errors: " + e.getMessage());
        return new BookLedgerResponse(false, "500", bookName);
    }
}
