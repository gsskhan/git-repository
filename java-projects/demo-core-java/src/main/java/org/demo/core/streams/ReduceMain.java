package org.demo.core.streams;

import java.util.List;

public class ReduceMain {

    public static void main(String[] args) {
        // List of strings
        var listOne = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");

        // Concatenate strings
        String resultConcatedString = listOne.stream()
            .reduce("", (partialResult, element) -> partialResult + element);
        System.out.println(resultConcatedString);

        // Concatenated strings with length
        int resultConcatenatingLength = listOne.stream()
            .reduce(0, (partialResult, element) -> partialResult + element.length(), Integer::sum);
        System.out.println(resultConcatenatingLength);


    }
}
