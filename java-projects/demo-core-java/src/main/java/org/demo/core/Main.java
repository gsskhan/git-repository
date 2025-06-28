package org.demo.core;

import org.demo.core.modules.PrintNumbers;

public class Main {
    /**
     * The main method of the application.
     * This method demonstrates the usage of the {@link PrintNumbers} class
     * to print squared numbers and a halved value.
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
    	PrintNumbers.printSquaredNumbers(1, 10);
        System.out.println("#############");
        PrintNumbers.printHalvedValue(2.0);
    }
}