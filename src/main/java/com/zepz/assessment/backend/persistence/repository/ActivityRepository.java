package com.zepz.assessment.backend.persistence.repository;

import com.zepz.assessment.backend.persistence.model.Activity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface ActivityRepository extends ReactiveMongoRepository<Activity, UUID> {
    @Query("{ 'type' : ?0 }")
    Flux<Activity> findByType(final String type);
}
