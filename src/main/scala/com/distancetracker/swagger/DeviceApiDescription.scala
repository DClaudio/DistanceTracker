package com.distancetracker.swagger

import com.distancetracker.model.{Device, DeviceEntity}
import org.scalatra.swagger.{Swagger, SwaggerSupport}

/**
 * Created by claudio.david on 14/09/2015.
 */

trait DeviceApiDescription extends SwaggerSupport {

  val swagger: Swagger = DistanceTrackerSwagger
  val applicationDescription: String = "DeviceApi"

  val createNewDeviceOperation = (apiOperation[DeviceEntity]("createNewDevice")
    summary "Create a new device in the database."
    parameter bodyParam[Device]("device").description("the device to register").required

    )
  val getDeviceByIdOperation = (apiOperation[DeviceEntity]("getDevice")
    summary "Get a device"
    parameters (pathParam[String]("deviceid").description("device id")).required
    )
  val deleteDeviceOperation = (apiOperation[DeviceEntity]("deleteDevice")
    summary "Delete a device"
    parameters (pathParam[String]("deviceid").description("device id")).required
    )
  val updateDeviceOperation = (apiOperation[DeviceEntity]("updateDevice")
    summary "Update a device"
    parameters (pathParam[String]("deviceid").description("device id")).required
    parameter bodyParam[Device]("device").description("the updated values").required
    )
  val getDeviceListOperation = (apiOperation[Set[DeviceEntity]]("getAllDevices")
    summary "Get All devices"
    )


}
