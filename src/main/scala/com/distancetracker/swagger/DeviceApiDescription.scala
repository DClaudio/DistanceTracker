package com.distancetracker.swagger

import com.distancetracker.model.{Device, DeviceEntity}
import org.scalatra.swagger.{Swagger, SwaggerSupport}

/**
 * Created by claudio.david on 14/09/2015.
 */

trait DeviceApiDescription extends SwaggerSupport{

  val swagger: Swagger = DistanceTrackerSwagger
  protected val applicationDescription: String = "DeviceApi"


  val createNewDeviceOperation = (apiOperation[DeviceEntity]("createNewDevice")
    summary "Create a new device in the database."
    parameter bodyParam[Device]("device").description("the device to register").required

    )

  val devicesDeviceidGetOperation = (apiOperation[DeviceEntity]("getDevice")
    summary "Get a device"
    parameters (pathParam[String]("deviceid").description("device id")).required
    )
}
