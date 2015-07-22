package com.distancetracker.api

import org.scalatra.json.NativeJsonSupport
import org.scalatra.swagger.SwaggerSupport
import org.scalatra._
import com.distancetracker.model.GPSdata

class GPSApi (implicit val swagger: Swagger) extends ScalatraServlet
with NativeJsonSupport with SwaggerSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  protected val applicationDescription: String = "GPSApi"
  override protected val applicationName: Option[String] = Some("GPS")

  before() {
    contentType = formats("json")
    response.headers += ("Access-Control-Allow-Origin" -> "*")
  }


  val gpsDeviceidGetOperation = (apiOperation[GPSdata]("gpsDeviceidGet")
    summary "Get gps data for a device"
    parameters(

    pathParam[String]("deviceid").description("")





    )
    )

  get("/gps/{deviceid}",operation(gpsDeviceidGetOperation)) {




    val deviceid = params.getOrElse("deviceid", halt(400))










    println("deviceid: " + deviceid)

  }



  val gpsDeviceidPutOperation = (apiOperation[GPSdata]("gpsDeviceidPut")
    summary "Update gps data for an existing device or add a gps data for a new device"
    parameters(

    pathParam[String]("deviceid").description("")





    )
    )

  put("/gps/{deviceid}",operation(gpsDeviceidPutOperation)) {




    val deviceid = params.getOrElse("deviceid", halt(400))










    println("deviceid: " + deviceid)

  }

}