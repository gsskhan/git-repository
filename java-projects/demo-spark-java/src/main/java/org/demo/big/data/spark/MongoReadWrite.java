package org.demo.big.data.spark;

import java.util.HashMap;
import java.util.Map;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MongoReadWrite
 */
public class MongoReadWrite {

    private static Logger log = LoggerFactory.getLogger(MongoReadWrite.class);

    public static void main(String[] args) {

        try (SparkSession spark = SparkSession.builder()
                        .appName("mongodb example")
                        .master("local")
                        .getOrCreate();
            JavaSparkContext jsc = new JavaSparkContext(spark.sparkContext()); 
        ) {           
            Map<String, String> options = new HashMap<>();
            options.put("uri", "mongodb://127.0.0.1/learner");
            options.put("collection", "contacts");
            Dataset<Row> implicitDS = spark.read().format("mongo").options(options).load();
            implicitDS.show();

            implicitDS.createOrReplaceTempView("t_contacts");
            Dataset<Row> finalDS = spark.sql("select distinct name , age from t_contacts");
            finalDS.show();
            
            log.info("program finished ...");
        } catch (Exception e) {
            log.error("error in program.", e);
        }
    }

}