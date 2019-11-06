'''
Created on 03-Jul-2016

@author: gsskhan
'''

import sys, logging, sqlite3, json
from logging.handlers import RotatingFileHandler
from sihir_root.common_conf import mongo_db_uri
from pymongo import MongoClient
from bson import json_util


def _enable_logging(log_file_location):
    log = logging.getLogger()
    log.setLevel(logging.DEBUG)
    frmt = logging.Formatter("[%(asctime)s] %(levelname)s {%(module)s,%(lineno)s} - %(message)s")    
    # console logging
    ch = logging.StreamHandler(sys.stdout)
    ch.setFormatter(frmt)
    log.addHandler(ch)    
    # file logger
    fh = RotatingFileHandler(log_file_location + "/sihir-log.log", maxBytes=1000000 , backupCount=3)
    fh.setFormatter(frmt)
    log.addHandler(fh)

    
def _get_sqlite_db_connection(db_filename):
    try:
        logging.info('sqlite3 database adaptor, or built-in pysqlite3 version: ' + str(sqlite3.version_info))        
        logging.info('sqlite3 database version: ' + str(sqlite3.sqlite_version_info))        
        conn = sqlite3.connect(db_filename)       
    except Exception as e:
        logging.error("problem connecting sqlite db ...")
        logging.exception(e)
        raise e
    else:
        logging.info("connection to sqlite db :" + str(db_filename) + " successful ...")
    return conn

def _close_sqlite_db_connection(connection):
    try:
        connection.close()
    except Exception as e:
        logging.error("problem closing connection ...")
        logging.exception(e)
    else:
        logging.info("connection closed successfully ...")

def _get_mongodb_connection():
    try:
        client = MongoClient(mongo_db_uri)
        db = client.get_default_database()
        primaryStatus = client.is_primary
    except Exception as e:
        logging.error("problem creating mongodb connection ...")
        logging.exception(e)
        raise e
    else:
        logging.info("mongodb connection [ primary : " + str(primaryStatus) +" ], [ info : " + str(db) +" ]..." )
        logging.info("connection to mongodb successful ...")
    return client

def _close_mongodb_client(client):
    try:
        client.close()
    except Exception as e:
        logging.error("problem closing connection ...")
        logging.exception(e)
    else:
        logging.info("connection closed successfully ...")
