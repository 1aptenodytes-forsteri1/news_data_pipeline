package com.newsdatapipeline.backend.Services;

import com.newsdatapipeline.backend.Models.GeonamesResponse;
import com.newsdatapipeline.backend.Models.Settlement;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class LocationService {
    private final RequestService requestService;
    private final WebClient webClient;

    LocationService(RequestService requestService, WebClient webClient) {
        this.requestService = requestService;
        this.webClient = webClient;
    }

    public Settlement getSettlementData(String name) {
        GeonamesResponse response = webClient.get()
                .uri(requestService.getLocationRequest(name))
                .retrieve()
                .bodyToMono(GeonamesResponse.class)
                .block();
        return response.getGeonames().get(0);
    }

}
