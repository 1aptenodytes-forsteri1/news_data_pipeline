package com.newsdatapipeline.backend.Services;

import com.newsdatapipeline.backend.Models.NewsRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    @Value("${searchLocation}")
    private String searchLocation;

    public String getLocationRequest(String name) {
        return String.format(searchLocation, name);
    }

    public String getNewsRequest(NewsRequest getNewsRequest) {
        String request = getNewsRequest.getDataAPI().getLink();
        return request.replace("%CITYNAME", getNewsRequest.getSettlement().getToponymName())
                .replace("%STATENAME", getNewsRequest.getSettlement().getAdminName1())
                .replace("%STATECODE", getNewsRequest.getSettlement().getAdminCode1());
    }
}
