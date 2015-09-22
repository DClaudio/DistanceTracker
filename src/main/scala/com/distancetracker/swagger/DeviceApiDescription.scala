package com.distancetracker.swagger

import com.distancetracker.model.Device
import com.distancetracker.persistence.DeviceEntity
import org.scalatra.swagger.{Swagger, SwaggerSupport}

/**
 * Created by claudio.david on 14/09/2015.
 */

trait DeviceApiDescription extends SwaggerSupport{

  val swagger: Swagger = DistanceTrackerSwagger
  protected val applicationDescription: String = "DeviceApi"


  val createNewDeviceOperation = (apiOperation[DeviceEntity]("createNewDevice")
    summary "Create a new device in the database."
    parameters (
    bodyParam[Device]("device").description("the name of the device to register")
    )
    )

  val devicesDeviceidGetOperation = (apiOperation[DeviceEntity]("devicesDeviceidGet")
    summary "Get a device"
    parameters (pathParam[Long]("deviceid").description("device id"))
    )
}
