package com.newsdatapipeline.backend.Controllers;

import com.newsdatapipeline.backend.Services.LocationService;
import com.newsdatapipeline.backend.Services.NewsService;
import org.newspipeline.Article;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@CrossOrigin
@RestController
@RequestMapping("/news")
public class NewsController {
    private final LocationService locationService;
    private final NewsService newsService;

    NewsController(LocationService locationService, NewsService newsService) {
        this.locationService = locationService;
        this.newsService = newsService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Article> getNews(@RequestParam String name) {
        return newsService.getNews(locationService.getSettlementData(name));
    }
}
