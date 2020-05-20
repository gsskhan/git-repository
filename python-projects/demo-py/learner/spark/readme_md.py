# -*- coding:utf-8 -*-

#import required modules
import logging
from pyspark import SparkConf, SparkContext
from pyspark.sql import SparkSession

#configure logging
log = logging.getLogger(__name__)

#Create spark configuration object
conf = SparkConf()
conf.setMaster("local").setAppName("Read Readme file")

#Create spark context and sparksession
sc = SparkContext.getOrCreate(conf=conf)
spark = SparkSession(sparkContext = sc)

##################### business logic ###########################
file = "README.md"
lines = sc.textFile(file)
log.warning("%s file has total lines = %s", file, lines.count())

pythonLines = lines.filter(lambda line: "python" in line)
log.warning("%s file has lines having word 'python' = %s", file, pythonLines.count())


##################### shutdown spark ###########################
sc.stop()