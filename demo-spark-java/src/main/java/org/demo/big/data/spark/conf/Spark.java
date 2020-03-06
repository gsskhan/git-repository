package org.demo.big.data.spark.conf;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class Spark {
	
	private SparkConf sparkConfiguration;
	private JavaSparkContext sparkContext;
	
	/**
	 * Initializes a Spark connection. Use it afterwards for execution of Spark
	 * SQL queries.
	 * 
	 * @param appName
	 *            the name of the app that will be used with this Spark
	 *            connection
	 */
	public Spark(String appName, String masterName) {
		this.sparkConfiguration = new SparkConf()
				.setAppName(appName)
				.setMaster(masterName);
		this.sparkContext = new JavaSparkContext(sparkConfiguration);
	}
	
	public JavaSparkContext getSparkContext() {
		return this.sparkContext;
	}

}
