package io.javabrains.ratingsdataservice.services;

import io.javabrains.ratingsdataservice.models.Library;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class LibraryService {
private final RestTemplate restTemplate;

    public LibraryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Library> getLibraries() {
        ResponseEntity<List<Library>> rateResponse =
                restTemplate.exchange("https://bitpay.com/api/rates",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Library>>() {
                        });
        List<Library> libraries = rateResponse.getBody();
        return libraries;
    }
}
