package com.newsdatapipeline.backend.Controllers;

import com.newsdatapipeline.backend.Models.Settlement;
import com.newsdatapipeline.backend.Services.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/settlement")
public class SettlementController {
    private final LocationService locationService;

    SettlementController(LocationService locationService, NewsService newsService) {
        this.locationService = locationService;
    }

    @GetMapping
    public Settlement getSettlement(@RequestParam String name) {
        return locationService.getSettlementData(name);
    }

}
