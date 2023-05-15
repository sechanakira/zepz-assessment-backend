package com.zepz.assessment.backend.persistence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public record Activity(@Id UUID id,
                       Long key,
                       String activity,
                       String type,
                       Integer participants,
                       Double price,
                       String link,
                       Double accessibility) {
    public Activity(Long key, String activity, String type, Integer participants, Double price, String link, Double accessibility) {
        this(UUID.randomUUID(), key, activity, type, participants, price, link, accessibility);
    }
}
