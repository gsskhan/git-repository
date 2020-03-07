package org.demo.big.data.spark.conf;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;

public class Spark {
	
	private SparkSession sparkSession;
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
		this.sparkSession = SparkSession
							.builder()
							.appName(appName)
							.master(masterName)
							.getOrCreate();
		this.sparkContext = new JavaSparkContext(this.sparkSession.sparkContext());
	}
	
	public JavaSparkContext getSparkContext() {
		return this.sparkContext;
	}

}
