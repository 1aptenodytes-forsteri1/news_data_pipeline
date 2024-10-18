package com.newsdatapipeline.backend.Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.newsdatapipeline.backend.Models.GeonamesResponse;
import com.newsdatapipeline.backend.Models.GetSettlementsRequest;
import com.newsdatapipeline.backend.Models.Settlement;
import com.newsdatapipeline.backend.Services.LocationService;
import com.newsdatapipeline.backend.Services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/settlements")
public class SettlementsController {
    private final LocationService locationService;
    SettlementsController(LocationService locationService){
        this.locationService = locationService;
    }
    @Autowired
    RequestService requestService;
    @GetMapping
    public List<Settlement> getSettlements(@ModelAttribute GetSettlementsRequest request){
        return locationService.getNearbySettlements(request);
    }
}
