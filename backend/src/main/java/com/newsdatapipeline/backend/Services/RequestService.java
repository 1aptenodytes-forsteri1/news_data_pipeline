package com.newsdatapipeline.backend.Services;

import com.newsdatapipeline.backend.Config.ConfigFileTemplate;
import com.newsdatapipeline.backend.Models.DataAPI;
import com.newsdatapipeline.backend.Models.GetSettlementsRequest;
import com.newsdatapipeline.backend.Models.NewsRequest;
import com.newsdatapipeline.backend.Models.Settlement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    @Value("${searchLocation}")
    private String searchLocation;
    @Value("${searchNearby}")
    private String searchNearby;
    public String getLocationRequest(String name){
        return String.format(searchLocation,name);
    }
    public String getNearbySettlementsRequest(GetSettlementsRequest getSettlementsRequest){
        return String.format(searchNearby,
                getSettlementsRequest.getLat(),
                getSettlementsRequest.getLng(),
                getSettlementsRequest.getRadius());
    }
    public String getNewsRequest(NewsRequest getNewsRequest){
        String request = getNewsRequest.getDataAPI().getLink();
        return request.replace("%CITYNAME", getNewsRequest.getSettlement().getToponymName())
                .replace("%STATENAME", getNewsRequest.getSettlement().getAdminName1())
                .replace("%STATECODE", getNewsRequest.getSettlement().getAdminCode1());
    }
}
