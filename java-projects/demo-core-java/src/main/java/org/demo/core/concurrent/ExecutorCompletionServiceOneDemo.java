package org.demo.core.concurrent;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExecutorCompletionServiceOneDemo {

    public static void main(String[] args) {
        // Use a fixed-size thread pool to execute our tasks
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // The ExecutorCompletionService wraps the executor
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);

        // A list of tasks to be executed
        List<Callable<String>> tasks = List.of(
            createTask("Task 1", 5),
            createTask("Task 2", 2),
            createTask("Task 3", 8),
            createTask("Task 4", 1),
            createTask("Task 5", 3)
        );

        log.info("Submitting {} tasks...", tasks.size());
        // Submit all tasks for execution
        for (Callable<String> task : tasks) {
            completionService.submit(task);
        }

        log.info("Waiting for tasks to complete and processing results as they arrive...");
        // Retrieve results as they complete using poll()
        int completedCount = 0;
        while (completedCount < tasks.size()) {
            try {
                // poll() is non-blocking and returns null if no task is complete yet
                Future<String> completedFuture = completionService.poll(); // Using poll() here
                if (completedFuture != null) {
                    completedCount++;
                    // .get() is still a blocking call get results. Where as .resultNow() is non blocking to get results.
                    // after poll() has confirmed the task is complete.
                    log.info("Result received: {}", completedFuture.resultNow());
                } else {
                    // Optional: Do other work here or just sleep briefly to avoid a busy-wait loop
                    log.trace("No completed tasks yet, will check again...");
                    Thread.sleep(200); // Avoid spinning the CPU
                }
            } catch (InterruptedException e) {
                completedCount++; // Count the task even if it failed
                log.error("Error retrieving task result", e);
            }
        }

        // It's a good practice to shut down the executor service
        executorService.shutdown();
        log.info("All tasks processed. Executor service shut down.");
    }

    /**
     * Creates a sample task that sleeps for a random duration and returns a message.
     * @param name The name of the task.
     * @param maxSleepSeconds The maximum sleep duration in seconds.
     * @return A Callable that returns a String.
     */
    private static Callable<String> createTask(String name, int maxSleepSeconds) {
        return () -> {
            int sleepTime = ThreadLocalRandom.current().nextInt(1, maxSleepSeconds + 1);
            log.info("{} will sleep for {} seconds.", name, sleepTime);
            Thread.sleep(sleepTime * 1000L);
            return name + " completed after " + sleepTime + " seconds.";
        };
    }
}
