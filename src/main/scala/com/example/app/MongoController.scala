package com.example.app

/**
 * Created by claudio.david on 15/07/2015.
 */
import org.scalatra._

// MongoDb-specific imports
import com.mongodb.casbah.Imports._

class MongoController(mongoCollection: MongoCollection) extends MyScalatraWebAppStack {

  /**
   * Insert a new object into the database. You can use the following from your console to try it out:
   * curl -i -H "Accept: application/json" -X POST -d "key=super&value=duper" http://localhost:8080/insert
   */
  post("/") {
    val key = params("key")
    val value = params("value")
    val newObj = MongoDBObject(key -> value)
    mongoCollection += newObj
  }

  /**
   * Retrieve everything in the MongoDb collection we're currently using.
   */
  get("/") {
    mongoCollection.find()
    for { x <- mongoCollection} yield x
  }

  /**
   * Query for the first object which matches the values given. If you copy/pasted the insert example above,
   * try http://localhost:8080/query/super/duper in your browser.
   */
  get("/query/:key/:value") {
    val q = MongoDBObject(params("key") -> params("value"))
    for ( x <- mongoCollection.findOne(q) ) yield x
  }

}