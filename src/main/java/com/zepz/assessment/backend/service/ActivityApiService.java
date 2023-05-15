package com.zepz.assessment.backend.service;

import com.zepz.assessment.backend.client.ActivityApiClient;
import com.zepz.assessment.backend.dto.ActivityResponse;
import com.zepz.assessment.backend.persistence.service.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ActivityApiService {

    private final ActivityApiClient apiClient;
    private final ActivityService activityService;

    public ActivityApiService(final ActivityApiClient apiClient,
                              final ActivityService activityService) {
        this.apiClient = apiClient;
        this.activityService = activityService;
    }

    @Transactional
    public Flux<ActivityResponse> fetchAndPersistActivities() {
        long currentActivities = this.activityService.countActivities();
        if (currentActivities == 0) {
            final List<ActivityResponse> responses = this.apiClient.fetchActivities()
                    .toStream()
                    .collect(toList());
            return this.activityService.saveActivities(responses);
        }
        return this.activityService.findAllActivities();
    }
}
