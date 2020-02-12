#-*- coding: utf-8 -*-

'''
Created on 02-Jul-2016

@author: gsskhan
'''

import os
import sys
sys.path.append(os.path.abspath(os.path.join(os.path.dirname(__file__), '.')))

import logging

from sihir_root.pre_checks import _perform_prechecks
from sihir_root.common_utility import _enable_logging
from sihir_root.common_conf import logfile_location
from sihir_root.app_service import _start_operations_


# main method
def _main():
    logging.info("sihir started with process id (" + str(os.getpid()) + ") ...")
    _perform_prechecks()
    logging.info('pre-checks successful, starting operations ...')
    _start_operations_()   
    
    
if __name__ == "__main__" :
    try:
        _enable_logging(logfile_location)
        _main()
    except Exception as e:
        logging.critical("sihir encountered error, program terminated... exception{" + str(e) + "}")   
        logging.exception(e)
    else:
        logging.info("sihir completed...")
