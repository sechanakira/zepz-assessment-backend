package com.zepz.assessment.backend.dto;

public record ActivityResponse(String activity,
                               String type,
                               Integer participants,
                               Double price,
                               String link,
                               Long key,
                               Double accessibility) {
}
