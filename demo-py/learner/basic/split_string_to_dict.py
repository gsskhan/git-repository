'''
Example to Split a string into a dict.
'''

str = "key1=value1:key2=value2:key3=value3"

# smart way
d1 = dict( x.split("=") for x in str.split(":"))
print ("d1  ==> %s" %(d1) )

# long way
d2 = dict()
for x in str.split(":"):
    s = x.split("=")
    d2[ s[0] ] = s[1]
print ("d2 ==> %s" %(d2) )

# another long way
d3 = dict()
for x in str.split(":"):
    s = x.split("=")
    d3.update({ s[0] : s[1] })
print ("d3 ==> %s" %(d3) )

# copy, iterate and print dictionary key/values 
d4 = d3.copy();
print ("d4 ==>")
for k, v in d4.items():
    print (k, v)

'''
Example to convert two list into a dict.
'''


str1 = "keyX | keyY | keyZ"
str2 = "valueX | valueY | valueZ"

keys = str1.split("|")
values = str2.split("|")

d5 = dict(zip(keys, values))
print ("d5 ==> %s" %(d5) )