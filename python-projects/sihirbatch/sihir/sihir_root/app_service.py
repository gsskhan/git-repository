'''
Created on 03-Jun-2017

@author: gsskhan
'''
import logging
from sihir_root.common_conf import sqlite_db_filename
from sihir_root.common_utility import _get_sqlite_db_connection, _close_sqlite_db_connection, _get_mongodb_connection, _close_mongodb_client
from sihir_root.db_access import DatabaseAccess


def _start_operations_():
    db_cnx = _get_sqlite_db_connection(db_filename=sqlite_db_filename)
    dao = DatabaseAccess(db_cnx)    
    _store_startup_time(data_access_obj=dao)   
    _process_sys_info(data_access_obj=dao) 
    _close_sqlite_db_connection(db_cnx);
    mongo_client = _get_mongodb_connection()
    _close_mongodb_client(mongo_client)

    
def _store_startup_time(data_access_obj):
    logging.info('processing startup time ...')
    # add startup time to db
    data_access_obj._add_startup_time()
    # data_access_obj._get_test_records()    


def _process_sys_info(data_access_obj):
    logging.info('processing system information ...')
