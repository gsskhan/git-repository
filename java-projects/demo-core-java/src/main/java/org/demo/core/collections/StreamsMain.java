package org.demo.core.collections;

import java.util.stream.Stream;

/**
 * This class demo's all methods of the Stream.
 */
public class StreamsMain {

    public static void main(String[] args){

        // Create a Stream of Integers
        Stream<Integer> streamOfIntegers = Stream.of(1, 2, 3, 4, 5);
        System.out.println("Stream of integers: " + streamOfIntegers.toList());

        // Create a Stream of Alphabets
        Stream<Character> streamOfAlphabets = Stream.of('a', 'b', 'c', 'd', 'e');
        System.out.println("Stream of alphabets: " + streamOfAlphabets.toList());

        // Create a Stream of Strings
        Stream<String> streamOfStrings = Stream.of("One", "Two", "Three", "Four", "Five");
        System.out.println("Stream of strings: " + streamOfStrings.toList());


    }
    
}
