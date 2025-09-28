package com.ecom.testproducts.clients;

import com.ecom.testproducts.dto.TokenTechnicDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "security-service", url = "http://localhost:8091/api")
public interface SecurityRestClient {

    @PostMapping(value="/oauth2/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @CircuitBreaker(name="tokenTechnic", fallbackMethod = "getDefaultToken")
    TokenTechnicDto getTokenTechnic(@RequestHeader("Authorization") String authorization, @RequestBody String formBody);

    default TokenTechnicDto getDefaultToken(String authorization, String formBody, Exception e) {
        return new TokenTechnicDto(null, null, 0L, null);
    }

}
