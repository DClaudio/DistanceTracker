package com.distancetracker.api

import com.distancetracker.model.Device
import com.distancetracker.swagger.DistanceTrackerSwagger
import org.scalatra.swagger.{Swagger, SwaggerSupport}
import org.scalatra.{Created, Ok, ScalatraServlet}

/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApi(val swagger: Swagger = DistanceTrackerSwagger) extends ScalatraServlet with SwaggerSupport {

  protected val applicationDescription: String = "DeviceApi"

  val createNewDeviceOperation = (apiOperation[Device]("createNewDevice")
    summary "Create a new device in the database."
    parameters(
    bodyParam[Device]("device").description("the name of the device to register")
    )
    )
  post("/device", operation(createNewDeviceOperation)) {
    Created
  }

  val devicesDeviceidGetOperation = (apiOperation[Device]("devicesDeviceidGet")
    summary "Get a device"
    parameters (pathParam[Long]("deviceid").description("device id"))
    )
  get("/device/:deviceId", operation(devicesDeviceidGetOperation)) {
    Ok(new Device("n1", "m1"))
  }


}
