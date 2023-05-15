package com.zepz.assessment.backend.persistence.service;

import com.zepz.assessment.backend.dto.ActivityResponse;
import com.zepz.assessment.backend.dto.SearchDO;
import com.zepz.assessment.backend.persistence.model.Activity;
import com.zepz.assessment.backend.persistence.repository.ActivityRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

import static com.zepz.assessment.backend.mapper.ActivityMapper.documentsToResponses;
import static com.zepz.assessment.backend.mapper.ActivityMapper.responsesToDocuments;
import static java.util.stream.Collectors.toList;

@Service
public class ActivityService {
    private final ActivityRepository repository;

    public ActivityService(final ActivityRepository repository) {
        this.repository = repository;
    }

    public Flux<ActivityResponse> saveActivities(final List<ActivityResponse> responses) {
        final List<Activity> activities = responsesToDocuments(responses);
        final List<Activity> saved = this.repository.insert(activities).toStream().collect(toList());
        final List<ActivityResponse> mappedResponses = documentsToResponses(saved);
        return Flux.fromIterable(mappedResponses);
    }

    public long countActivities() {
        return this.repository.count().block();
    }

    public Flux<ActivityResponse> findAllActivities() {
        final List<Activity> activities = this.repository
                .findAll()
                .toStream()
                .collect(toList());
        final List<ActivityResponse> responses = documentsToResponses(activities);
        return Flux.fromIterable(responses);
    }

    public Flux<ActivityResponse> search(final SearchDO searchDO) {
        final List<Activity> activities = this.repository
                .findByType(searchDO.type())
                .toStream()
                .collect(toList());
        final List<ActivityResponse> responses = documentsToResponses(activities);
        return Flux.fromIterable(responses);
    }
}
