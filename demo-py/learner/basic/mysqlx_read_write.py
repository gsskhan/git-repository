import mysqlx
import mysql.connector
 
connect_args = {
    "host": "127.0.0.1",
    "port": 33406,
    "user": "dev",
    "password": "password"
}
 
print("Using inline expression.")
print("MySQL Connector/Python {0}".format(mysql.connector.__version__))
 
db = mysqlx.get_session(**connect_args)

if db.get_schema("dms_x").exists_in_database():
    schema = db.get_schema("dms_x")
    print("Found schema: {}".format(schema.get_name()))
else:
    schema = db.create_schema("dms_x")
    print("Created new schema: {}".format(schema.get_name()))


#try:
#    
#except Exception as ex:
#    print("failed to get collection")
if schema.get_collection("country").exists_in_database():
    countryinfo = schema.get_collection("country")
    print("Found collection: {}".format(countryinfo.get_name()))
else:
    countryinfo = schema.create_collection("country")
    print("Created new collection: {}".format(countryinfo.get_name()))

# Add a document to 'my_collection'
countryinfo.add({'_id': '1', 'name': 'Sakovia', 'area': 15}).execute()

# You can also add multiple documents at once
countryinfo.add({'_id': '2', 'name': 'Krakazis', 'area': 15},
            {'_id': '3', 'name': 'Roshuland', 'area': 15},
            {'_id': '4', 'name': 'Mayura', 'area': 37}).execute()

# Remove the document with '_id' = '1'
countryinfo.remove('_id = 1').execute()

print("Total record(s): {}".format(countryinfo.count()))
 
# commit and close the connections
db.commit()
db.close()