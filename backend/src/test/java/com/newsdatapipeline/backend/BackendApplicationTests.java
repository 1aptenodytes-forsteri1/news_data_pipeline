package com.newsdatapipeline.backend;

import com.fasterxml.jackson.databind.JsonNode;
import com.newsdatapipeline.backend.Config.ConfigFileTemplate;
import com.newsdatapipeline.backend.Models.DataAPI;
import com.newsdatapipeline.backend.Models.News;
import com.newsdatapipeline.backend.Models.NewsRequest;
import com.newsdatapipeline.backend.Models.Settlement;
import com.newsdatapipeline.backend.Services.CheckingService;
import com.newsdatapipeline.backend.Services.PluginService;
import com.newsdatapipeline.backend.Services.RequestService;
import com.newsdatapipeline.backend.Services.TimeService;
import org.junit.jupiter.api.Test;
import org.newspipeline.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private WebClient webClient;
	@Autowired
	private PluginService pluginService;
	@Autowired
	private CheckingService checkingService;
	@Autowired
	private TimeService timeService;
	@Autowired
	private ConfigFileTemplate configFileTemplate;
	@Autowired
	private RequestService requestService;

	@Test
	void contextLoads() throws InterruptedException {
		Settlement settlement = new Settlement();
		settlement.setToponymName("New York City");
		settlement.setAdminName1("Virginia");
		settlement.setAdminCode1("NY");

//		webClient.get()
//				.uri("https://api.gdeltproject.org/api/v2/doc/doc?query=sourcecountry:us AND sourcelang:english AND near20:\"Ashburn VA\" &format=json&sort=datedesc&maxrecords=250")
//				.exchangeToMono(clientResponse -> {
//					if(clientResponse.statusCode().isError()){
//						return Mono.empty();
//					}else return Mono.just(clientResponse.bodyToMono(String.class));
//				}).subscribe(System.out::println);
//		pluginService.getWebsitesArticles(settlement).hasElements().subscribe(t -> System.out.println(t));
//		Thread.sleep(20000);
		List<NewsRequest> requestsList = new ArrayList<>();
		for (DataAPI dataAPI : configFileTemplate.getNewsAPIs()) {
			requestsList.add(new NewsRequest(settlement, dataAPI));
		}
		AtomicInteger counter = new AtomicInteger(0);
		Flux.fromIterable(requestsList)
				.flatMap(request ->
						webClient.get()
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
//				.doOnNext(t->{counter.incrementAndGet(); System.out.println(counter.get());})
//				.doOnComplete(()->System.out.println(counter.get()))
//				.onErrorResume(e -> Mono.empty()).subscribe();
		.subscribe(System.out::println);
		Thread.sleep(50000);
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
