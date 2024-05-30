package com.demo.retryer.service.bookledgerext;

import com.demo.retryer.dto.bookledger.BookLedgerResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BookLedgerExtService {
    BookLedgerResponse postNewTransaction(String bookTitle) throws JsonProcessingException;
}
