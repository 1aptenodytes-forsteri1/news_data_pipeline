package com.newsdatapipeline.backend.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.newsdatapipeline.backend.Config.ConfigFileTemplate;
import com.newsdatapipeline.backend.Models.*;
import org.newspipeline.Article;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.*;

@Service
public class NewsService {
    private final WebClient webClient;
    private final ConfigFileTemplate configFileTemplate;
    private final CheckingService checkingService;
    private final RequestService requestService;
    private final PluginService pluginService;
    NewsService(WebClient webClient, ConfigFileTemplate configFileTemplate, RequestService requestService, CheckingService checkingService, PluginService pluginService){
        this.webClient = webClient;
        this.configFileTemplate = configFileTemplate;
        this.requestService = requestService;
        this.checkingService = checkingService;
        this.pluginService = pluginService;
    }
    public Flux<Article> getNews(Settlement settlement){
        List<NewsRequest> requestsList = new ArrayList<>();
        for (DataAPI dataAPI : configFileTemplate.getNewsAPIs()){
            requestsList.add(new NewsRequest(settlement, dataAPI));
        }
        return Flux.fromIterable(requestsList)
                .flatMap(request -> webClient.get()
                        .uri(requestService.getNewsRequest(request))
                        .retrieve()
                        .bodyToFlux(JsonNode.class)
                        //.onErrorResume(e->Flux.empty())
                        .filter(checkingService::isNotEmptyJsonNode)
                        .flatMap(response ->  parseResponse(response,request.getDataAPI(),request.getSettlement()))
                        .flatMap(checkingService::checkArticles)
                        .onErrorResume(e->Flux.empty())

                )
                .mergeWith(pluginService.getWebsitesArticles(settlement))
                .distinct()
                .onErrorResume(e->Mono.empty());
//                .subscribe(result -> {
//                    try {
//                        checkingService.checkArticles(newsSet, result);
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                });

        //return newsSet;
    }


    public List<JsonNode> t(List<Settlement> settlements){
        List<NewsRequest> requestsList = new ArrayList<>();
        List<JsonNode> jsonNodes = new ArrayList<>();
        for (Settlement settlement : settlements){
            for (DataAPI dataAPI : configFileTemplate.getNewsAPIs()){
                requestsList.add(new NewsRequest(settlement, dataAPI));
            }
        }
        for (NewsRequest newsRequest : requestsList){
            JsonNode node;
            node = webClient.get()
                    .uri(requestService.getNewsRequest(newsRequest))
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();
            jsonNodes.add(node);
        }
        return jsonNodes;
    }
    private Flux<Article> parseResponse(JsonNode jsonNode, DataAPI dataAPI, Settlement settlement){
        return Flux.create(element -> {
            for(JsonNode node : jsonNode.get(dataAPI.getResponseListName())){
                element.next(new News(checkingService.removeQuotationMarks(node.get(dataAPI.getResponseLink()).toString())
                        ,checkingService.removeQuotationMarks(node.get(dataAPI.getResponseTitle()).toString())
                        ,settlement.getToponymName())
                );
            }
            element.complete();
        });
    }


}
