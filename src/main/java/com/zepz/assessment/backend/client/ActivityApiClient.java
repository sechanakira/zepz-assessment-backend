package com.zepz.assessment.backend.client;

import com.zepz.assessment.backend.dto.ActivityResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class ActivityApiClient {

    private final String endpoint;
    private final int fetchLimit;
    private final WebClient webClient;

    public ActivityApiClient(@Value("${api.endpoint}") final String endpoint,
                             @Value("${fetch.limit}") int fetchLimit) {
        this.endpoint = endpoint;
        this.fetchLimit = fetchLimit;
        this.webClient = WebClient.create();
    }

    public Flux<ActivityResponse> fetchActivities() {
        final Stream<Integer> intStream = IntStream.rangeClosed(1, fetchLimit).boxed();

        return Flux.fromStream(intStream)
                .parallel()
                .flatMap(i -> this.fetchActivity())
                .ordered(Comparator.comparing(ActivityResponse::key));
    }

    private Mono<ActivityResponse> fetchActivity() {
        final WebClient.ResponseSpec responseSpec = webClient.get()
                .uri(endpoint)
                .retrieve();

        return responseSpec.bodyToMono(ActivityResponse.class);
    }
}
