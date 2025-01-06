package com.newsdatapipeline.backend.Services;

import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeService {
    public String parseDate(String date, String pattern){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, dateFormatter);
        String formattedDate = zonedDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        return formattedDate;
    }
}
