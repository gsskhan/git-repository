package org.demo.core.concurrent;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureOneDemo {

    public static void main(String[] args) {

        // Declare a list of numbers.
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("List of Integer: " + numbers);


        // Stream over each item in list "numbers" and create a list of Strings by converting each Integer to String but using CompletableFuture.
        List<CompletableFuture<String>> completableFuturesOfStrings = numbers.stream().map(number -> CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            return String.valueOf(number);
        })).toList();

        // Wait for all tasks to finish
        CompletableFuture.allOf(completableFuturesOfStrings.toArray(new CompletableFuture[0])).join();

        // Gather the results
        List<String> strings = completableFuturesOfStrings.stream()
                .map(CompletableFuture::join)
                .toList();

        System.out.println("List of String: " + strings);
    }

}
