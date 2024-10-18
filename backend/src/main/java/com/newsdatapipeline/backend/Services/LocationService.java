package com.newsdatapipeline.backend.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.newsdatapipeline.backend.Config.ConfigFileTemplate;
import com.newsdatapipeline.backend.Models.GeonamesResponse;
import com.newsdatapipeline.backend.Models.GetSettlementsRequest;
import com.newsdatapipeline.backend.Models.Settlement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {
    private final RequestService requestService;
    private final WebClient webClient;
    @Autowired
    NewsService newsService;
    LocationService(RequestService requestService, WebClient webClient){
        this.requestService = requestService;
        this.webClient = webClient;
    }
    public Settlement getSettlementData(String name){
        GeonamesResponse response = webClient.get()
                .uri(requestService.getLocationRequest(name))
                .retrieve()
                .bodyToMono(GeonamesResponse.class)
                .block();
        return response.getGeonames().get(0);
    }

    public List<Settlement> getNearbySettlements(GetSettlementsRequest request){
        WebClient webClient = WebClient.create();
        List<Settlement> result = new ArrayList<>();
//        String size = "cities15000";
//        if(request.getShowSmall()&!request.getShowMedium()&!request.getShowBig()){
//            size = "";
//        }
        GeonamesResponse response = webClient.get()
                .uri(requestService.getNearbySettlementsRequest(request))
                .retrieve()
                .bodyToMono(GeonamesResponse.class)
                .block();
        for (Settlement settlement : response.getGeonames()){
            if (settlement.getPopulation()>0){
                result.add(settlement);
            }
        }


//        for(int i = 0; response.getGeonames().size() < 0; i++){
//            if(i==0){
//                result.add(response.getGeonames().get(i));
//                continue;
//            }
//            if(request.getShowBig() & response.getGeonames().get(i).getPopulation() > 250000){
//                result.add(response.getGeonames().get(i));
//                continue;
//            }
//            if (request.getShowMedium() & response.getGeonames().get(i).getPopulation() > 25000 & response.getGeonames().get(i).getPopulation() < 250000){
//                result.add(response.getGeonames().get(i));
//                continue;
//            }
//            if (request.getShowSmall() & response.getGeonames().get(i).getPopulation() > 0 & response.getGeonames().get(i).getPopulation() < 25000){
//                result.add(response.getGeonames().get(i));
//            }
//        }

        return result;
    }


}
