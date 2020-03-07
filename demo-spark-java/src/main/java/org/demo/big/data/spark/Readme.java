package org.demo.big.data.spark;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.demo.big.data.spark.conf.Spark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.Tuple2;

/**
 * Readme
 */
public class Readme {

    private static Logger log = LoggerFactory.getLogger(Readme.class);

    public static void main(String[] args) {
        JavaSparkContext sc = new Spark("Readme-Calculations", "local").getSparkContext();
        String file = sc.getSparkHome().get() + "/README.md";

        // Load the README.md file and display the contents
        JavaRDD<String> readmeDS = sc.textFile(file);
        log.info("Total lines in {} file = {}", file, readmeDS.count());
        log.info("Contents of readmeDS:");
        readmeDS.foreach(l -> {
            log.info(l);
        });

        // Find the lines in file containing keyword - python
        JavaRDD<String> linesHavingStringPythonDS = readmeDS.filter(l -> l.toLowerCase().contains("python"));
        log.info("Contents of linesHavingStringPythonDS:");
        linesHavingStringPythonDS.foreach(l -> {
            log.info(l);
        });

        // Split up into words.
        JavaRDD<String> wordsDS = readmeDS.flatMap(l -> {
            Iterator<String> list = Arrays.asList(l.split(" ")).iterator();
            return list;
        });

        // Transform into pairs and count.
        JavaPairRDD<String, Integer> countsDS = wordsDS
                                .mapToPair(s-> new Tuple2<>(s, 1))
                                .reduceByKey( (Function2<Integer, Integer, Integer>) (a, b) -> a + b);

        countsDS.saveAsTextFile("/tmp/word_count_of_readme_md");

        // Destroy spark context
        sc.stop();
    }

}