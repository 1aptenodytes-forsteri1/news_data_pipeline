package com.newsdatapipeline.backend.ServicesTests;

import com.newsdatapipeline.backend.Models.DataAPI;
import com.newsdatapipeline.backend.Models.GetSettlementsRequest;
import com.newsdatapipeline.backend.Models.NewsRequest;
import com.newsdatapipeline.backend.Models.Settlement;
import com.newsdatapipeline.backend.Services.RequestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RequestServiceTest {
    @Autowired
    private RequestService requestService;

    @Test
    void newsRequest(){
        NewsRequest newsRequest ;
        Settlement settlement;
        DataAPI dataAPI;
        settlement = new Settlement();
        dataAPI = new DataAPI();
        settlement.setAdminCode1("NE");
        settlement.setAdminName1("Nebraska");
        settlement.setName("Hemingford");
        dataAPI.setLink("https://api.gdeltproject.org/api/v2/doc/doc?query=sourcecountry:us AND sourcelang:english AND near20:\"%CITYNAME %STATECODE\"&format=json&sort=datedesc&maxrecords=250");
        dataAPI.setResponseLink("url");
        dataAPI.setResponseTitle("title");
        dataAPI.setResponseListName("articles");
        newsRequest = new NewsRequest(settlement, dataAPI);
        assertEquals("https://api.gdeltproject.org/api/v2/doc/doc?query=sourcecountry:us AND sourcelang:english AND near20:\"Hemingford NE\"&format=json&sort=datedesc&maxrecords=250",
                requestService.getNewsRequest(newsRequest));
    }

    @Test
    void nearbyRequest(){
        GetSettlementsRequest getSettlementsRequest = new GetSettlementsRequest();
        getSettlementsRequest.setLat("42.32163");
        getSettlementsRequest.setLng("-103.07298");
        getSettlementsRequest.setRadius(50);
        assertEquals("http://api.geonames.org/findNearbyPlaceNameJSON?lat=42.32163&lng=-103.07298&radius=50&localCountry=true&maxRows=500&username=newsdatapipeline",
                requestService.getNearbySettlementsRequest(getSettlementsRequest));
    }
}
