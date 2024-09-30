package com.example.demorecord;

import com.example.demorecord.dto.request.CreateRecordRequest;
import com.example.demorecord.dto.response.RecordFullResponse;
import com.example.demorecord.service.RecordApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit ;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecordServiceTest {
    @LocalServerPort
    private int port;
    @Autowired
    private RecordApiService recordService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final List<Long> times = Collections.synchronizedList(new ArrayList<>());

    @Test
    public void create100kRecords() {
        final var totalRecords = 100000;
        saveRandomRecordsByCount(totalRecords);

        final var allCreatedRecords = recordService.getAllRecords();
        assertEquals(allCreatedRecords.size(), totalRecords);
    }
    @Test
    public void testParallelSelects() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        var totalRecords = 1000000;
        final var createdRecordIds = saveRandomRecordsByCount(totalRecords);

        for (int i = 0; i < totalRecords; i++) {
            final var id = createdRecordIds.get((int) (Math.random() * totalRecords));
            executorService.submit(() -> {
                final var url = "http://localhost:" + port +"/api/records/" + id;
                StopWatch stopwatch = new StopWatch();
                stopwatch.start();
                try {
                    restTemplate.getForEntity(url, RecordFullResponse.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                stopwatch.stop();
                times.add(stopwatch.getTotalTimeMillis());

            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        analyzeTimes(times);
    }

    private void analyzeTimes(List<Long> times) {
        Collections.sort(times);

        long totalTime = times.stream().mapToLong(Long::longValue).sum();
        double avgTime = totalTime / (double) times.size();
        double medianTime = times.get(times.size() / 2);
        long p95Time = times.get((int) (times.size() * 0.95));
        long p99Time = times.get((int) (times.size() * 0.99));

        System.out.println("Total time: " + totalTime);
        System.out.println("Average time: " + avgTime);
        System.out.println("Median time: " + medianTime);
        System.out.println("95th percentile: " + p95Time);
        System.out.println("99th percentile: " + p99Time);
    }

    private List<UUID> saveRandomRecordsByCount(long count) {
        final var createdRecordIds = new ArrayList<UUID>();
        for (int i = 0; i < count; i++) {
            final var record = new CreateRecordRequest("Record " + i);
            createdRecordIds.add(recordService.createRecord(record).id());
        }
        return createdRecordIds;
    }
}
