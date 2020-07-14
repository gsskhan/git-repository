const MongoClient = require('mongodb').MongoClient;
const mongoDbUri = "mongodb://localhost:27017/dms";

MongoClient.connect(mongoDbUri, function(err, db) {
    if (err) throw err;
    var dbo = db.db("admin");
    dbo.collection("system.version").findOne({}, function(err, result) {
      if (err) {
          console.log("Problem ...")
        throw err;
        }          
      console.log("Mongo db version: "+ JSON.stringify(result));
      db.close();
    });
  });