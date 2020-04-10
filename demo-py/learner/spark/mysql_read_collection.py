# -*- coding:utf-8 -*-

import os
import sys

from pyspark import SparkContext
from pyspark.sql import SparkSession

#Create spark context, sparksession
sc=SparkContext(master="local", appName="mysql read collection").getOrCreate()
spark=SparkSession(sparkContext=sc)

#configure logging, Log Level
log4jLogger = sc._jvm.org.apache.log4j
log4jLogger.LogManager.getRootLogger().setLevel(log4jLogger.Level.INFO)
log4jLogger.LogManager.getLogger("org.apache.spark").setLevel(log4jLogger.Level.WARN)
log = log4jLogger.LogManager.getLogger(__name__)

#mysql connection details
hostname = "localhost"
port = "3406"
db_name = "dms"
db_user = "root"
db_password = "password"
driver_name = "com.mysql.cj.jdbc.Driver"
db_url = f"jdbc:mysql://{hostname}:{port}/{db_name}"

############# Application Logic #####################

# create a dataframe of people
peopleDF = spark.createDataFrame(data = [("Bilbo Baggins",  50),
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
log.info ("people - data frame created")
peopleDF.show()

# write a dataframe of people
peopleDF.write.format("jdbc").options(
    url = db_url,
    dbtable="people",
    driver=driver_name,
    user=db_user,
    password=db_password,
    truncate=True).mode("overwrite").save()
log.info ("people - data frame written")

jdbcDF = spark.read.format("jdbc").options(
    url = db_url,
    dbtable="people",
    driver=driver_name,
    user=db_user,
    password=db_password).load()
log.info ("people - data frame read. Row(s) = {}".format(jdbcDF.count()))
jdbcDF.show()

















