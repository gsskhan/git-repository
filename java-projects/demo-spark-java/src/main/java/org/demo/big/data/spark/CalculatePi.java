package org.demo.big.data.spark;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculatePi {
	
	private static Logger log = LoggerFactory.getLogger(CalculatePi.class);
	
	private static int NUM_SAMPLES = 50;
	
	public static void main(String[] args) {

		try (JavaSparkContext sc = new JavaSparkContext("local", "Calculate-Pi")) {
			List<Integer> l = new ArrayList<Integer>(NUM_SAMPLES);
			for (int i = 0; i < NUM_SAMPLES; i++) {
				l.add(i);
			}
	
			long count = sc.parallelize(l).filter(i -> {
				double x = Math.random();
				double y = Math.random();
				return x * x + y * y < 1;
			}).count();
			log.info("Pi is roughly " + 4.0 * count / NUM_SAMPLES);			
		} catch (Exception e) {
			log.error("error in program.", e);
		}
	}

}
