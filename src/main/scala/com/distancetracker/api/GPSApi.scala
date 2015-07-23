package com.distancetracker.api

import org.scalatra._
import org.scalatra.swagger._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.mongodb.casbah.Imports._
import com.distancetracker.model.GPSdata
import com.distancetracker.model.GPScoord


class GPSApi (var swagger: Swagger, var gpsMongoCollection: MongoCollection) extends ScalatraServlet
with NativeJsonSupport with SwaggerSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  protected val applicationDescription: String = "GPSApi"
  override protected val applicationName: Option[String] = Some("gps")

  before() {
    contentType = formats("json")
    response.headers += ("Access-Control-Allow-Origin" -> "*")
  }

  val gpsDeviceidGetOperation = (apiOperation[GPSdata]("gpsDeviceidGet")
    summary "Get gps data for a device"
      parameters(
      pathParam[Long]("deviceid").description("device id")
      )
    )
  get("/gps/:deviceid",operation(gpsDeviceidGetOperation)) {
    val deviceid:Long = params.getOrElse("deviceid", halt(400)).toLong
    val query = MongoDBObject("deviceid" -> deviceid)
    gpsMongoCollection.findOne(query) match{
      case Some(s) => s
      case None => ""
    }
  }

  val gpsDeviceidPutOperation = (apiOperation[GPSdata]("gpsDeviceidPut")
    summary "Update gps data for an existing device or add a gps data for a new device"
    parameters(
      pathParam[Long]("deviceid").description("device id"),
      bodyParam[GPScoord]("gps").description("device gps data")
      )
    )
  put("/gps/:deviceid",operation(gpsDeviceidPutOperation)) {
    val deviceid:Long = params.getOrElse("deviceid", halt(400)).toLong
    val coord: GPScoord = parsedBody.extract[GPScoord]


    gpsMongoCollection.findAndModify(
      query=MongoDBObject("deviceid" -> deviceid),
      update=MongoDBObject("$set" -> MongoDBObject("gps" -> coord))
    ) match {
      case Some(obj) => obj
      case None => gpsMongoCollection+= MongoDBObject("deviceid" -> deviceid, "gps" -> coord)
    }

  }

}

