package com.newsdatapipeline.backend.Controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.newsdatapipeline.backend.Config.ConfigFileTemplate;
import com.newsdatapipeline.backend.Models.DataAPI;
import com.newsdatapipeline.backend.Models.GeonamesResponse;
import com.newsdatapipeline.backend.Models.News;
import com.newsdatapipeline.backend.Models.Settlement;
import com.newsdatapipeline.backend.Services.LocationService;
import com.newsdatapipeline.backend.Services.NewsService;
import com.newsdatapipeline.backend.Services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/settlement")
public class SettlementController {
    @Autowired
    RequestService requestService;
    private final LocationService locationService;
    private final NewsService newsService;
    SettlementController(LocationService locationService, NewsService newsService){
        this.locationService = locationService;
        this.newsService = newsService;
    }
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<News> getSettlement(@RequestParam String name){
        return newsService.getNews(locationService.getSettlementData(name));
    }

}
