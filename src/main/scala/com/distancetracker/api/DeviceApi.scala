package com.distancetracker.api

import org.scalatra.ScalatraServlet
import org.scalatra.swagger._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._
import com.mongodb.casbah.Imports._
import com.distancetracker.model.Device

class DeviceApi (var swagger: Swagger, deviceMongoCollection: MongoCollection) extends ScalatraServlet
  with NativeJsonSupport with  SwaggerSupport{
  protected implicit val jsonFormats: Formats = DefaultFormats

  protected val applicationDescription: String = "DeviceApi"
  override protected val applicationName: Option[String] = Some("devices")

  before() {
    contentType = formats("json")
    response.headers += ("Access-Control-Allow-Origin" -> "*")
  }

  val devicesGetOperation = (apiOperation[List[Device]]("devicesGet")
      summary "Returns All available devices"
      parameters(
      )
    )
  get("/findAll",operation(devicesGetOperation)) {
  }


  val registerDeviceOperation = (apiOperation[Device]("registerDevice")
      summary "Register a device to the database."
      parameters(
        pathParam[String]("name").description("the name of the device to register")
      )
    )
  post("/device/",operation(registerDeviceOperation)) {
    val name = parsedBody.extract[String]
    println("name: " + name)
  }

  val devicesDeviceidGetOperation = (apiOperation[Device]("devicesDeviceidGet")
    summary "Get a device"
    parameters(
      pathParam[Long]("deviceid").description("device id")
      )
    )
  get("/device/:deviceid",operation(devicesDeviceidGetOperation)) {

    val deviceid = params.getOrElse("deviceid", halt(400))

    println("deviceid: " + deviceid)
  }

  val devicesDeviceidPutOperation = (apiOperation[Device]("devicesDeviceidPut")
      summary "Update an existing device"
      parameters(
        pathParam[Long]("deviceid").description("device id")
      )
    )
  put("device/:deviceid",operation(devicesDeviceidPutOperation)) {
    val deviceid = params.getOrElse("deviceid", halt(400))
    println("deviceid: " + deviceid)
  }

}