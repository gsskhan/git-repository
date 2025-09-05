package org.demo.core.modules;

import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class PrintNumbers {

    public static void printSquaredNumbers(int initialValue, long maxValue) {
        Stream.iterate(initialValue, i -> i * 2)
              .limit(maxValue)
              .forEach(i -> log.info("{}", i));
    }

    public static void printHalvedValue(double initialValue) {
        Stream.iterate(initialValue, d -> d > 0.25, d -> d / 2)
              .forEach(d -> log.info("{}", d));
    }

}
