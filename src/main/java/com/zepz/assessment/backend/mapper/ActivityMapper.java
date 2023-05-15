package com.zepz.assessment.backend.mapper;

import com.zepz.assessment.backend.dto.ActivityResponse;
import com.zepz.assessment.backend.persistence.model.Activity;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ActivityMapper {

    public static List<Activity> responsesToDocuments(final List<ActivityResponse> responses) {
        return responses.stream().map(response ->
                new Activity(
                        response.key(),
                        response.activity(),
                        response.type(),
                        response.participants(),
                        response.price(),
                        response.link(),
                        response.accessibility())
        ).collect(toList());
    }

    public static List<ActivityResponse> documentsToResponses(final List<Activity> documents) {
        return documents.stream().map(document -> new ActivityResponse(
                document.activity(),
                document.type(),
                document.participants(),
                document.price(),
                document.link(),
                document.key(),
                document.accessibility()
        )).collect(toList());
    }
}
