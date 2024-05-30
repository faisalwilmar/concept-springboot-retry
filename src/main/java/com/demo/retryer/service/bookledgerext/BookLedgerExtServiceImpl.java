package com.demo.retryer.service.bookledgerext;

import com.demo.retryer.dto.bookledger.BookLedgerResponse;
import com.demo.retryer.exception.CustomException;
import com.demo.retryer.utils.HttpHelper;
import com.demo.retryer.utils.JsonHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookLedgerExtServiceImpl implements BookLedgerExtService {

    private final HttpHelper httpHelper;

    private final JsonHelper jsonHelper;

    private final String endpoint = "http://localhost:8080/lending-origination-service";

    @Override
    public BookLedgerResponse postNewTransaction(String bookTitle) throws JsonProcessingException {
        try {
            String urlPath = endpoint + "/welcome/basic-response";

            return httpHelper.execute(urlPath, HttpMethod.GET, null, null, Map.of("someString", bookTitle), null,
                    BookLedgerResponse.class);
        }
        catch (HttpClientErrorException e) {
            // HttpClientErrorException is thrown for HTTP status 4xx
//            String responseBody = e.getResponseBodyAsString();
//            return jsonHelper.toClass(responseBody, BookLedgerResponse.class);
            throw e;
        }
        catch (RestClientException ex) {
            log.error("postNewTransaction Response Error: " + ex.getMessage());
            throw ex;
        }

    }
}
