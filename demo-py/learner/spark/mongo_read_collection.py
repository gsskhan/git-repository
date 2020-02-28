# -*- coding:utf-8 -*-

#import required modules
import logging
from pyspark import SparkConf, SparkContext
from pyspark.sql import SparkSession

#configure logging
log = logging.getLogger(__name__)

#Create spark configuration object
conf = SparkConf()
conf.setMaster("local").setAppName("Mongo Read Collections")

#Create spark context and sparksession
sc = SparkContext.getOrCreate(conf=conf)
spark = SparkSession(sparkContext = sc)

#mongodb connection details
mongo_hostname = "127.0.0.1"
mongo_port = "27017"
mongo_db_name = "learner"
mongo_collection = "contacts"
mongo_uri = f"mongodb://{mongo_hostname}:{mongo_port}/{mongo_db_name}.{mongo_collection}"

# create a dataframe of people
people = spark.createDataFrame(data = [("Bilbo Baggins",  50),
                                ("Gandalf", 1000),
                                ("Thorin", 195),
                                ("Balin", 178),
                                ("Kili", 77),
                                ("Dwalin", 169),
                                ("Oin", 167),
                                ("Gloin", 158),
                                ("Fili", 82),
                                ("Bombur", None)],
                               schema= ["name", "age"])
log.warning("dataframe object created ...")

# create a dataframe of people
people.write.format("mongo").mode("append") \
    .option("uri", mongo_uri ) \
    .option("database", mongo_db_name) \
    .option("collection", mongo_collection) \
    .save()
log.warning("dataframe object saved to db ...")

#read mongodb collection data into a spark dataframe
peopleDF = spark.read.format("mongo").option("uri", mongo_uri).load()

#show the data loaded into dataframe
log.warning("dataframe object read from db ...")

peopleDF.show()