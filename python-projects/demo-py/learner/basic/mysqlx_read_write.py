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

if schema.get_collection("country").exists_in_database():
    countryinfo = schema.get_collection("country")
    print("Found collection: {}".format(countryinfo.get_name()))
else:
    countryinfo = schema.create_collection("country")
    print("Created new collection: {}".format(countryinfo.get_name()))

db.start_transaction()
# Add a document to 'my_collection'
result = countryinfo.add({'_id': '1', 'name': 'Sakovia', 'area': 15}).execute()

# You can also add multiple documents at once
result = countryinfo.add({'_id': '2', 'name': 'Krakazis', 'area': 15},
            {'_id': '3', 'name': 'Roshuland', 'area': 15},
            {'_id': '4', 'name': 'Mayura', 'area': 37}).execute()

print("Total record(s): {}".format(countryinfo.count()))
db.commit()

# Read the records
result = countryinfo.find().execute()
doc = result.fetch_one()
while doc:
    print("Record: {}".format(doc))
    doc = result.fetch_one()

db.close()