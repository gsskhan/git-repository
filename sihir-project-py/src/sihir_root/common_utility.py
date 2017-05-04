'''
Created on 03-Jul-2016

@author: gsskhan
'''


import sys, logging 
from logging.handlers import RotatingFileHandler
import sqlite3


def _enable_logging(log_file_location):
    log = logging.getLogger()
    log.setLevel(logging.DEBUG)
    frmt = logging.Formatter("[%(asctime)s] %(levelname)s {%(module)s,%(lineno)s} - %(message)s")    
    # console logging
    ch = logging.StreamHandler(sys.stdout)
    ch.setFormatter(frmt)
    log.addHandler(ch)    
    # file logger
    fh = RotatingFileHandler(log_file_location+"/sihir-log.log", maxBytes=1000000 , backupCount=3)
    fh.setFormatter(frmt)
    log.addHandler(fh)
    
def _get_db_connection(db_filename):
    try:
        logging.info('sqlite3 database adaptor, or built-in pysqlite3 version: ' + str(sqlite3.version_info))        
        logging.info('sqlite3 database version: ' + str(sqlite3.sqlite_version_info))        
        conn = sqlite3.connect(db_filename)       
    except Exception as e:
        logging.error("problem connecting sqlite db ...")
        logging.exception(e)
        raise e
    else:
        logging.info("connection to sqlite db :"+ str(db_filename)+ " successful ...")
    return conn

def _close_db_connection(connection):
    try:
        connection.close()
    except Exception as e:
        logging.error("problem closing connection ...")
        logging.exception(e)
    else:
        logging.info("connection closed successfully ...")    