package com.newsdatapipeline.backend.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.newsdatapipeline.backend.Config.ConfigFileTemplate;
import com.newsdatapipeline.backend.Models.*;
import org.newspipeline.Article;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class NewsService {
    private final WebClient webClient;
    private final ConfigFileTemplate configFileTemplate;
    private final CheckingService checkingService;
    private final RequestService requestService;
    private final PluginService pluginService;
    private final TimeService timeService;

    NewsService(WebClient webClient, ConfigFileTemplate configFileTemplate, RequestService requestService, CheckingService checkingService, PluginService pluginService, TimeService timeService) {
        this.webClient = webClient;
        this.configFileTemplate = configFileTemplate;
        this.requestService = requestService;
        this.checkingService = checkingService;
        this.pluginService = pluginService;
        this.timeService = timeService;
    }

    public Flux<Article> getNews(Settlement settlement) throws InterruptedException {
        List<NewsRequest> requestsList = new ArrayList<>();
        for (DataAPI dataAPI : configFileTemplate.getNewsAPIs()) {
            requestsList.add(new NewsRequest(settlement, dataAPI));
        }
        AtomicInteger counter = new AtomicInteger(0);
        return Flux.fromIterable(requestsList)
                .flatMap(request -> webClient.get()
                        .uri(requestService.getNewsRequest(request))
                        .retrieve()
                        .bodyToFlux(JsonNode.class)
                        .filter(checkingService::isNotEmptyJsonNode)
                        .flatMap(response -> parseResponse(response, request.getDataAPI(), request.getSettlement()))
                        .flatMap(checkingService::checkArticles)

                )
                .mergeWith(pluginService.getWebsitesArticles(settlement))
                .distinct()
                .delayElements(Duration.ofMillis(50))
                .onErrorResume(e -> Mono.empty());

    }

    private Flux<Article> parseResponse(JsonNode jsonNode, DataAPI dataAPI, Settlement settlement) {
        return Flux.create(element -> {
            for (JsonNode node : jsonNode.get(dataAPI.getResponseListName())) {
                element.next(new News(checkingService.removeQuotationMarks(node.get(dataAPI.getResponseLink()).toString())
                        ,checkingService.removeQuotationMarks(node.get(dataAPI.getResponseTitle()).toString())
                        ,settlement.getToponymName()
                        ,timeService.parseDate(checkingService.removeQuotationMarks(node.get(dataAPI.getResponseDate()).toString()),dataAPI.getDateFormat())
                        ,checkingService.removeQuotationMarks(node.get(dataAPI.getResponseImageUrl()).toString()))

                );
            }
            element.complete();
        });
    }


}
