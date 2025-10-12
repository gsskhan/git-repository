package org.demo.core.concurrent;

import java.util.concurrent.CompletableFuture;

import lombok.extern.slf4j.Slf4j;


/**
 * Demonstrates the use of {@link CompletableFuture} for asynchronous operations,
 * specifically focusing on how to run multiple independent asynchronous tasks
 * and wait for all of them to complete using {@link CompletableFuture#allOf(CompletableFuture[])}.
 * <p>
 * This class illustrates a scenario where two independent greeting tasks are
 * executed concurrently, and the main thread can perform other work while
 * these tasks are running. It then waits for both tasks to finish before
 * concluding the demonstration.
 * </p>
 */
@Slf4j
public class CompletableFutureThreeDemo {

    private static final String HELLO = "Hello";

    /**
     * The main entry point for the CompletableFutureThreeDemo.
     * It initiates two asynchronous greeting tasks and waits for their completion.
     *
     * @param args Command line arguments (not used in this demo).
     */
    public static void main(String[] args) {
        log.info("--- Demonstrating CompletableFuture ---");
        
        CompletableFuture<String> completableFutureWorldGreeting = CompletableFuture.supplyAsync(() -> {
            log.info("Entered Greetings for world.");
            long sleepDuration = 2000;
            sleep(sleepDuration);
            log.info("Leaving Greetings for world after {} seconds.", sleepDuration / 1000);
            return HELLO.concat(" World!");
        });

        CompletableFuture<String> completableFutureJavaGreeting = CompletableFuture.supplyAsync(() -> {
            log.info("Entered Greetings for Java.");
            long sleepDuration = 5000;
            sleep(sleepDuration);
            log.info("Leaving Greetings for Java after {} seconds.", sleepDuration / 1000);
            return HELLO.concat(" Java!");
        });

        log.info("Main thread is not blocked and can do other work");
        
        // Wait for results of completable futures.
        CompletableFuture.allOf(completableFutureWorldGreeting, completableFutureJavaGreeting).join();


        log.info("--- Demonstration Finished ---");
    }

    /**
     * Pauses the current thread for a specified duration.
     * <p>
     * This is a utility method that wraps {@link Thread#sleep(long)} and handles
     * the {@link InterruptedException} by re-interrupting the current thread and
     * throwing an unchecked {@link RuntimeException}.
     *
     * @param millis the duration to sleep in milliseconds.
     * @throws RuntimeException if the current thread is interrupted while sleeping.
     */
    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}
