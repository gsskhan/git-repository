package org.demo.jdk8;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class BuiltInFunctionalInterface {
	
	public static void main(String[] args) {
		
		Predicate<String> predicate = (s) -> ( s.length() >0);
		boolean status = predicate.test("foo");
		System.out.println(status);		
		
		String test = null;
		Predicate<String> nonNull = Objects::nonNull;
		status = nonNull.test(test);
		System.out.println(status);	
		
		Function<String, Integer> sizeFn = (x) -> x.length();
		int result = sizeFn.apply("gulam");
		System.out.println(result);
		
		Function<String, Long> longValueFromString = Long::valueOf;
		long output = longValueFromString.apply("1000");
		System.out.println(output);
	}

}
