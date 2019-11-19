'''
Created on 02-Jul-2016

@author: gsskhan
'''
import os, logging, subprocess, platform


def _perform_prechecks():
    logging.info("performing prechecks...")
    _print_environment_attributes()
    _check_sqlite3_in_os_()
    logging.info("completed performing prechecks...")


def _check_java_version():
    logging.info("searching java ...")
    pr = subprocess.Popen('java -version', shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    for line in pr.stdout.readlines():
        logging.info("\t" + str(line.decode('ascii')).strip())
    retval = pr.wait()
    if retval == 0 :
        logging.info("java found ...")
    else:
        logging.info("java not found ...")

        
def _check_mysql_client_version():
    logging.info("searching mysql client ...")
    pr = subprocess.Popen('mysql -V', shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    for line in pr.stdout.readlines():
        logging.info("\t" + str(line.decode('ascii')).strip())
    retval = pr.wait()
    if retval == 0 :
        logging.info("mysql client found ...")
    else:
        logging.info("mysql client not found ...")

        
def _check_sqlite3_in_os_():
    logging.info("searching sqlite3 installation in OS ...")
    pr = subprocess.Popen('sqlite3 -version', shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
    for line in pr.stdout.readlines():
        logging.info("\t" + str(line.decode('ascii')).strip())
    retval = pr.wait()
    if retval == 0 :
        logging.info("sqlite3 found installed in OS ...")
    else:
        logging.info("sqlite3 not found installed in OS ...")

        
def _print_environment_attributes():    
    logging.info("\t" + "operating system (OS)        - " + str(platform.system()))
    logging.info("\t" + "operating system (OS) release- " + str(platform.release()))
    logging.info("\t" + "operating system (OS) version- " + str(platform.version()))
    logging.info("\t" + "login id                     - " + str(os.getenv("USER")))
    logging.info("\t" + "python version               - " + str(platform.python_version()))
    
