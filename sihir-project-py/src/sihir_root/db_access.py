'''
Created on 03-Jul-2016

@author: gsskhan
'''
import logging

class DatabaseAccess():
    '''
    this class contains methods for accessing database objects
    '''

    def __init__(self, connection):
        self.connection = connection
        
    def _add_startup_time(self):
        cursor = self.connection.cursor()
        cursor.execute(''' CREATE TABLE IF NOT EXISTS RUN_STARTUP (
                            RUN_ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                            START_TIME TEXT NOT NULL,
                            END_TIME TEXT )
                    ''')
        cursor.execute(" insert into RUN_STARTUP values (null, datetime('now'), null) ") 
        self.connection.commit()     
        logging.info("added startup time ...")
        
    def _get_test_records(self):
        cursor = self.connection.cursor()
        cursor.execute("select * from RUN_STARTUP")
        for row in cursor:
            logging.info(str(row))
        cursor.close()
        