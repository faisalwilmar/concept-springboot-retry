package com.demo.retryer.utils;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

import java.util.Map;

public interface HttpHelper {

    /**
     * Execute HTTP Request using {@code RestTemplate}
     * @param absoluteUrl Absolute URL including base url and path, without query params.
     * @param method HTTP Request method.
     * @param token Optional. Bearer Token, if any.
     * @param headers Optional. Customized Headers, if any.
     * @param queryParams Optional. Customized Query Parameters, if any.
     * @param requestBody Optional. Json Request Body, if any.
     * @param responseType Request Response type.
     * @return request Response
     * @param <T> request Response template.
     * @throws HttpClientErrorException when response is Bad Request
     * @throws HttpServerErrorException when response is Internal Server Error
     * @throws RestClientException when response error is unexpected
     */
    <T> T execute(String absoluteUrl, HttpMethod method, String token, Map<String, String> headers,
                  Map<String, String> queryParams, Object requestBody, Class<T> responseType) throws RestClientException;
}
