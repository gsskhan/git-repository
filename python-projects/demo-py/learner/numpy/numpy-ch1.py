import sys, os
import numpy

print ( "learning numpy chapter-01 started...")
main_script=sys.argv[0]
exec_dir= os.path.abspath(os.path.dirname(main_script))
print( "located python script [%s] at [%s]" %( main_script, exec_dir))

a1 = numpy.array( [1,2] )
print ( "a1: %s" %(a1) )

a2 = numpy.array( [ [1,2,3] ,[7,9,8] ] )
print ( "a2: %s" %(a2) )

a3 = numpy.array([1, 2, 3, 4, 5], ndmin=2)
print ( "a3: %s" %(a3) )