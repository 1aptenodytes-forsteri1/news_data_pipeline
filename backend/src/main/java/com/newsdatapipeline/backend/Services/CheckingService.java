package com.newsdatapipeline.backend.Services;

import com.fasterxml.jackson.databind.JsonNode;
import org.newspipeline.Article;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CheckingService {
    private final WebClient webClient;

    CheckingService(WebClient webClient) {
        this.webClient = webClient;
    }

    public boolean isNotEmptyJsonNode(JsonNode jsonNode) {
        return jsonNode != null && !jsonNode.isNull() && jsonNode.size() > 0;
    }

    public String removeQuotationMarks(String text) {
        return text.replace("\"", "");
    }

    public Mono<Article> checkArticles(Article news) {
        Article article = news;
        return webClient.get()
                .uri(news.getUrl())
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> article)
                .onErrorResume(e -> Mono.empty());
    }
}
