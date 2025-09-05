package org.demo.core.collections;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * This class demo's all methods of the Map interface or its implementations
 */
@Slf4j
public class MapMain {

    public static void main(String[] args) {

        var mapOfIntegers = new HashMap<>(Map.of(1, "One", 2, "Two", 3, "Three", 4, "Four", 5, "Five"));
        log.info("Original map of integers: {}", mapOfIntegers);

        var mapOfStrings = new HashMap<>(Map.of("One", 1, "Two", 2, "Three", 3, "Four", 4, "Five", 5));
        log.info("Original map of strings: {}", mapOfStrings);

        // showcase all methods of hashmap.
        mapOfIntegers.putIfAbsent(6, "Six");
        mapOfIntegers.computeIfPresent(7, (k, v) -> "Seven");
        mapOfIntegers.computeIfPresent(7, (k, v) -> null);
        mapOfIntegers.putIfAbsent(8, "Eight");
        mapOfIntegers.computeIfAbsent(9, k -> null); // This will not add 9 -> null, as computeIfAbsent does not add null values.
        log.info("Modified map of integers (showcase 1): {}", mapOfIntegers);

    }

}
