package com.demo.retryer.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
@Slf4j
public class HttpHelperImpl implements HttpHelper {
    private HttpHelperImpl() {
    }

    @Override
    public <T> T execute(String absoluteUrl, HttpMethod method, String token, Map<String, String> headers,
                         Map<String, String> queryParams, Object requestBody, Class<T> responseType) throws RestClientException {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Object> requestEntity = getObjectHttpEntity(token, headers, requestBody);

        // Create URI builder
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(absoluteUrl);
        if (queryParams != null && !queryParams.isEmpty()) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        String finalUrl = builder.toUriString();

        try {
            // log execution start
            long startTime = System.currentTimeMillis();

            // Execute request
            ResponseEntity<T> response = restTemplate.exchange(finalUrl, method, requestEntity, responseType);
            T body = response.getBody();

            // Calculate Response Time
            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime;

            return body;
        }
        catch (HttpClientErrorException e) {
            // Handle 4xx errors

            throw e;
        }
        catch (HttpServerErrorException ex) {
            // Handle 5xx errors

            throw ex;
        }
        catch (RestClientException ex) {

            throw ex;
        }
    }

    private static HttpEntity<Object> getObjectHttpEntity(String token, Map<String, String> headers,
                                                          Object requestBody) {
        HttpHeaders httpHeaders = getDefaultJsonHeader();
        if (token != null && !token.isEmpty()) {
            httpHeaders.setBearerAuth(token);
        }
        if (headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpHeaders.set(header.getKey(), header.getValue());
            }
        }

        // Create request entity
        return new HttpEntity<>(requestBody, httpHeaders);
    }

    private static HttpHeaders getDefaultJsonHeader() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
