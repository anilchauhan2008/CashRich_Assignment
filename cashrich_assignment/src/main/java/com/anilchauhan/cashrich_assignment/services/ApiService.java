package com.anilchauhan.cashrich_assignment.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.anilchauhan.cashrich_assignment.entities.ApiResponse;
import com.anilchauhan.cashrich_assignment.repositories.ApiResponseRepository;

@Service
public class ApiService {
	  @Autowired
	    private ApiResponseRepository apiResponseRepository;
	  
	  private final String THIRD_PARTY_API_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=BTC,ETHLTC";
	  private final String API_KEY = "27ab17d1-215f-49e5-9ca4-afd48810c149";
	  	
	  public String callThirdPartyApi(Long userId) {

	  RestTemplate restTemplate = new RestTemplate();

      // Set up headers with API key
      HttpHeaders headers = new HttpHeaders();
      headers.set("api-key", API_KEY);

      // Create the HTTP entity with headers
      HttpEntity<String> entity = new HttpEntity<>(headers);

      // Make the GET request with headers
      ResponseEntity<String> response = restTemplate.exchange(THIRD_PARTY_API_URL, HttpMethod.GET, entity, String.class);

	        ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setUserId(userId);
	        apiResponse.setResponse(response.getBody());
	        apiResponse.setTimestamp(LocalDateTime.now());
	        apiResponseRepository.save(apiResponse);

	        return response.getBody();
	    }
}
