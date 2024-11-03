package com.newsdatapipeline.backend.Controllers;

import com.newsdatapipeline.backend.Services.*;
import org.newspipeline.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import java.io.IOException;


@CrossOrigin
@RestController
@RequestMapping("/settlement")
public class SettlementController {
    @Autowired
    RequestService requestService;
    @Autowired
    PluginService pluginService;
    private final LocationService locationService;
    private final NewsService newsService;


    SettlementController(LocationService locationService, NewsService newsService){
        this.locationService = locationService;
        this.newsService = newsService;
    }
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Article> getSettlement(@RequestParam String name) throws IOException {
        return newsService.getNews(locationService.getSettlementData(name));
    }

}
