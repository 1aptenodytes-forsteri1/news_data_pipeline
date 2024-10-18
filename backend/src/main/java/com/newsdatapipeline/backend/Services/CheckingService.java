package com.newsdatapipeline.backend.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.newsdatapipeline.backend.Models.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CheckingService {
    private final WebClient webClient;
    private final ArticlesService articlesService;
    CheckingService(WebClient webClient, ArticlesService articlesService){
        this.webClient = webClient;
        this.articlesService = articlesService;
    }
    public boolean isNotEmptyJsonNode(JsonNode jsonNode) {
        return jsonNode != null && !jsonNode.isNull() && jsonNode.size() > 0;
    }
    public String removeQuotationMarks(String text){
        return text.replace("\"","");
    }
    public Mono<News> checkArticles(News news)  {
        News article = news;
        return webClient.get()
                .uri(news.getUrl())
                .retrieve()
                .bodyToMono(String.class)
                .map(response-> article)
                .onErrorResume(e->Mono.empty());
//        URL url;
//        HttpURLConnection httpURLConnection;
//        int responseCode;
//        try{
//            url = new URL(news.getUrl());
//            httpURLConnection = (HttpURLConnection) url.openConnection();
//            responseCode = httpURLConnection.getResponseCode();
//        }catch (IOException e){
//            return false;
//        }
//        return responseCode == HttpURLConnection.HTTP_OK;
    }
}
