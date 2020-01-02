import sys, os 
import json
import pandas as pd
from pandas.io.json import json_normalize

print( "started program ...")
main_script = sys.argv[0]
pathname = os.path.dirname(main_script)
exec_dir = os.path.abspath(pathname)
src_dir = os.path.abspath(os.path.dirname(exec_dir))
print( "located python script [%s] at [%s]" %( main_script, exec_dir))

students_json_file = src_dir + "/resources/students.json"

with open(students_json_file) as data_file:
    students_json = json.load(data_file)
    print ( "Student json: \n", json.dumps(students_json, indent= 1))
students_df1 = json_normalize (students_json)
print ( "Students DataFrame using pandas.io.json_normalize(): \n %s" %(students_df1) )

students_df2 = pd.read_json(students_json_file) 
print ( "Students DataFrame using pandas.read_json(): \n %s" %(students_df2) )



