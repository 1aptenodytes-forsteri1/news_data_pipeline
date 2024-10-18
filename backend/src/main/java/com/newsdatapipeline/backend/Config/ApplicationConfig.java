package com.newsdatapipeline.backend.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.server.HttpServer;
import java.io.IOException;
import java.time.Duration;

@Configuration
@EnableConfigurationProperties(ConfigFileTemplate.class)
public class ApplicationConfig {
//    @Bean
//    public WebClient webClient(WebClient.Builder builder) {
//        HttpClient httpClient = HttpClient.create()
//                .responseTimeout(Duration.ofSeconds(20))
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);
//
//        return builder
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
//                .build();
//    }
    @Bean
    public WebClient createWebClient() {
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                        .defaultCodecs()
                        .maxInMemorySize(1024 * 1024))
                .build();
        HttpClient httpClient = HttpClient.create();
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(strategies)
                .build();
    }
}
