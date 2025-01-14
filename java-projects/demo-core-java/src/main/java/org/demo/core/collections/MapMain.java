package org.demo.core.collections;

import java.util.Map;

/**
 * This class demo's all methods of the Map interface or its implementations
 */
public class MapMain {

    public static void main(String[] args) {

        var mapOfIntegers = Map.of(1, "One", 2, "Two", 3, "Three", 4, "Four", 5, "Five");
        System.out.println("Original map of integers: " + mapOfIntegers);

        var mapOfStrings = Map.of("One", 1, "Two", 2, "Three", 3, "Four", 4, "Five", 5);
        System.out.println("Original map of strings: " + mapOfStrings);

        // Showcase all methods of Hashmap.
        
        
    }

}
