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
        
    def _set_test_table(self):
        cursor = self.connection.cursor()
        cursor.execute("DROP TABLE IF EXISTS TEST")
        cursor.execute("create table TEST (trans text, symbol text, qty real, price real)")
        cursor.execute("insert into TEST values('BUY','RHAT',100,35.14)") 
        self.connection.commit()     
        
    def _get_test_records(self):
        cursor = self.connection.cursor()
        cursor.execute("select * from test")
        for row in cursor:
            logging.info(str(row))
        cursor.close()
        