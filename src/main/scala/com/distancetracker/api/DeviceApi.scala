package com.distancetracker.api

import com.distancetracker.model.Device
import com.distancetracker.swagger.DistanceTrackerSwagger
import org.scalatra.{Ok, Created, ScalatraServlet}
import org.scalatra.swagger.{Swagger, SwaggerSupport}


/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApi extends ScalatraServlet with SwaggerSupport {

  val swagger: Swagger = DistanceTrackerSwagger
  protected val applicationDescription: String = "DeviceApi"

  before(){
    response.headers += ("Access-Control-Allow-Origin" -> "*")
  }

  val createNewDeviceOperation = (apiOperation[Device]("createNewDevice")
    summary "Create a new device in the database."
    parameters(
    bodyParam[Device]("device").description("the name of the device to register")
    )
    )
  post("/device", operation(createNewDeviceOperation)) {
    Created(1L)
  }

  val devicesDeviceidGetOperation = (apiOperation[Device]("devicesDeviceidGet")
    summary "Get a device"
    parameters (pathParam[Long]("deviceid").description("device id"))
    )
  get("/device/:deviceId", operation(devicesDeviceidGetOperation)) {
    Ok(new Device("n1", "m1"))
  }


}

object SwaggerSpecs{

}
