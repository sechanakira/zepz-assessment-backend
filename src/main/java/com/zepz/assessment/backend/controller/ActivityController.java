package com.zepz.assessment.backend.controller;

import com.zepz.assessment.backend.dto.ActivityResponse;
import com.zepz.assessment.backend.dto.SearchDO;
import com.zepz.assessment.backend.persistence.service.ActivityService;
import com.zepz.assessment.backend.service.ActivityApiService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityApiService activityApiService;
    private final ActivityService activityService;

    public ActivityController(final ActivityApiService activityApiService,
                              final ActivityService activityService) {
        this.activityApiService = activityApiService;
        this.activityService = activityService;
    }

    @GetMapping
    public Flux<ActivityResponse> getAllActivities() {
        return this.activityService.findAllActivities();
    }

    @GetMapping("/search")
    public Flux<ActivityResponse> search(@RequestParam final String type) {
        final SearchDO searchDO = new SearchDO(type);
        return this.activityService.search(searchDO);
    }

    @PostMapping("/sync")
    public Flux<ActivityResponse> sync() {
        return this.activityApiService.fetchAndPersistActivities();
    }
}
