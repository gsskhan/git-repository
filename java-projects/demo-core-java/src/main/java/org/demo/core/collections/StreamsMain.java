package org.demo.core.collections;

import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

/**
 * This class demo's all methods of the Stream.
 */
@Slf4j
public class StreamsMain {

    public static void main(String[] args) {

        // Create a Stream of Integers
        Stream<Integer> streamOfIntegers = Stream.of(1, 2, 3, 4, 5);
        log.info("Stream of integers: {}", streamOfIntegers.toList());

        // Create a Stream of Alphabets
        Stream<Character> streamOfAlphabets = Stream.of('a', 'b', 'c', 'd', 'e');
        log.info("Stream of alphabets: {}", streamOfAlphabets.toList());

        // Create a Stream of Strings
        Stream<String> streamOfStrings = Stream.of("One", "Two", "Three", "Four", "Five");
        log.info("Stream of strings: {}", streamOfStrings.toList());

    }

}
