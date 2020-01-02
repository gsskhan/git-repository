import sys, os 
import pandas as pd
from pandas.io.json import json_normalize

print( "started program ...")
main_script = sys.argv[0]
pathname = os.path.dirname(main_script)
exec_dir = os.path.abspath(pathname)
src_dir = os.path.abspath(os.path.dirname(exec_dir))
print( "located python script [%s] at [%s]" %( main_script, exec_dir))

students_json_file = src_dir + "/resources/students.json"
students_df = pd.read_json(students_json_file) 
print ( "Students DataFrame: \n %s" %(students_df) )

students_marks_df = json_normalize (students_df['marks'])
print ( "Students marks DataFrame: \n %s" %(students_marks_df) )
