'''
Created on 03-Jun-2017

@author: gsskhan
'''
import logging
from sihir_root.common_conf import sqlite_db_filename
from sihir_root.common_utility import _get_db_connection, _close_db_connection
from sihir_root.db_access import DatabaseAccess


def _start_operations_():
    db_cnx = _get_db_connection(db_filename=sqlite_db_filename)
    dao = DatabaseAccess(db_cnx)    
    _store_startup_time(data_access_obj=dao)   
    _process_sys_info(data_access_obj=dao) 
    _close_db_connection(db_cnx);

    
def _store_startup_time(data_access_obj):
    logging.info('processing startup time ...')
    # add startup time to db
    data_access_obj._add_startup_time()
    # data_access_obj._get_test_records()    


def _process_sys_info(data_access_obj):
    logging.info('processing system information ...')
