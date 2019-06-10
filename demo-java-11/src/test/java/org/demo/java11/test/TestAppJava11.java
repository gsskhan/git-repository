package org.demo.java11.test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TestAppJava11 {
	
	@Test
	public void testApp() {
		int i = 0, x= 1;
		assertNotEquals(i, x, "yikes! both values found equal.");
	}

}
