'''
Example to Split a string into a dict.
'''

str = "key1=value1:key2=value2:key3=value3"

# smart way
d = dict( x.split("=") for x in str.split(":"))
print ("d  ==> %s" %(d) )

# long way
d1 = dict()
for x in str.split(":"):
    s = x.split("=")
    d1[ s[0] ] = s[1]
print ("d1 ==> %s" %(d1) )

# another long way
d3 = dict()
for x in str.split(":"):
    s = x.split("=")
    d3.update({ s[0] : s[1] })
print ("d3 ==> %s" %(d3) )