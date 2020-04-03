package com.likefood.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class RestTemplateUtils {
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateUtils.class);

    public static <T> T getForObject(String url, Class<T> responseType) {
        logger.debug("get: " + url);
        RestTemplate restTemplate = new RestTemplate();
        T result = null;
        try {
            result = restTemplate.getForObject(url, responseType);
        }  catch (HttpClientErrorException ex)   {
            if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw ex;
            }
        }
        return result;
    }




}
