package org.demo.big.data.spark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.demo.big.data.spark.conf.Spark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Readme
 */
public class Readme {

    private static Logger log = LoggerFactory.getLogger(Readme.class);

    public static void main(String[] args) {
        JavaSparkContext sc = new Spark("Readme-Calculations", "local").getSparkContext();
        String file = sc.getSparkHome().get() + "/README.md";

        JavaRDD<String> readmeDF = sc.textFile(file);
        log.info("Total lines in {} file = {}", file, readmeDF.count());

        log.info("Contents of readmeDF:");
        readmeDF.foreach(l -> {
            log.info(l);
        });

        JavaRDD<String> linesHavingStringPythonDF = readmeDF.filter(l -> l.toLowerCase().contains("python"));
        log.info("Contents of readmeDF:");
        linesHavingStringPythonDF.foreach(l -> {
            log.info(l);
        });

    }

}