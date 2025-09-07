package org.demo.core.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompletableFutureTwoDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Future vs CompletableFuture Example.
        log.info("--- Demonstrating traditional Future ---");
        demonstrateFuture("Samuel");

        log.info("\n--- Demonstrating CompletableFuture ---");
        demonstrateCompletableFuture("Richard");
    }

    private static void demonstrateFuture(String name) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 1. Submit a task to get a user ID
        Future<Integer> userIdFuture = executor.submit(() -> {
            log.info("Future: Fetching User ID for {} ...", name);
            sleep(2);
            return 123;
        });

        // 2. Block and get the user ID. We have to wait here.
        Integer userId = userIdFuture.get();
        log.info("Future: Got User ID: {} for name {}", userId, name);

        // 3. Submit another task to get user details, using the ID from the previous step.
        Future<String> userDetailsFuture = executor.submit(() -> {
            log.info("Future: Fetching details for User ID: {}", userId);
            sleep(2);
            return "Details for User " + userId;
        });

        // 4. Block again to get the final result.
        String userDetails = userDetailsFuture.get();
        log.info("Future: Final Result: {}", userDetails);

        executor.shutdown();
    }

    private static void demonstrateCompletableFuture(String name) throws ExecutionException, InterruptedException {
        // With CompletableFuture, we can create a pipeline of operations without blocking.

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            // 1. Asynchronously get the user ID
            log.info("CompletableFuture: Fetching User ID for {}...", name);
            sleep(2);
            return 987;
        }).thenApplyAsync(userId -> {
            // 2. thenApplyAsync is automatically triggered when the user ID is available.
            log.info("CompletableFuture: Fetching details for User ID: {}", userId);
            sleep(2);
            return "Details for User " + userId;
        });

        // We can do other work here while the async operations are running...
        log.info("CompletableFuture: Main thread is not blocked and can do other work.");

        // 3. Block only at the very end to get the final result using method .get() or .join()
        String result = completableFuture.get();
        log.info("CompletableFuture: Final Result: {}", result);
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}
