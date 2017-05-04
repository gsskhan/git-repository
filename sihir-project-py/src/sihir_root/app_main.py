'''
Created on 02-Jul-2016

@author: gsskhan
'''

import os, logging
from sihir_root.pre_checks import _perform_prechecks
from sihir_root.common_utility import _enable_logging, _get_db_connection, _close_db_connection
from sihir_root.db_access import DatabaseAccess
from sihir_root.common_conf import *



# main method
def _main():
    logging.info("sihir started with process id ("+ str(os.getpid())+") ...")
    _perform_prechecks()
    
    db_cnx = _get_db_connection(db_filename=sqlite_db_filename)
    dao = DatabaseAccess(db_cnx)
    
    # testing db operations
    dao._set_test_table()
    dao._get_test_records()
    _close_db_connection(db_cnx)
    
    
if __name__ == "__main__" :
    try:
        _enable_logging(logfile_location)
        _main()
    except Exception as e:
        logging.critical("sihir encountered error, program terminated... exception{"+str(e)+"}")   
        logging.exception(e)
    else:
        logging.info("sihir completed...")

