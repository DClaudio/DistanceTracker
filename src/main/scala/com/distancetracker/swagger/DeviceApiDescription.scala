package com.distancetracker.swagger

import com.distancetracker.model.Device
import com.distancetracker.persistence.DeviceEntity
import org.scalatra.swagger.{StringResponseMessage, Swagger, SwaggerSupport}

/**
 * Created by claudio.david on 14/09/2015.
 */

trait DeviceApiDescription extends SwaggerSupport {

  val swagger: Swagger = DistanceTrackerSwagger
  val applicationDescription: String = "DeviceApi"

  val createNewDeviceOperation = (apiOperation[DeviceEntity]("createNewDevice")
    summary "Create a new device in the database."
    parameter bodyParam[Device]("device").description("the device to register").required

    responseMessage (StringResponseMessage(400,"invalid request"))
    responseMessage (StringResponseMessage(201,"device created"))
    )

  val getDeviceByIdOperation = (apiOperation[DeviceEntity]("getDevice")
    summary "Get a device"
    parameters (pathParam[String]("deviceid").description("device id")).required

    responseMessage (StringResponseMessage(400,"invalid request"))
    responseMessage (StringResponseMessage(404,"device not found"))
    responseMessage (StringResponseMessage(200,"device found"))
    )

  val deleteDeviceOperation = (apiOperation[DeviceEntity]("deleteDevice")
    summary "Delete a device"
    parameters (pathParam[String]("deviceid").description("device id")).required

    responseMessage (StringResponseMessage(400,"invalid request"))
    responseMessage (StringResponseMessage(404,"device not found"))
    responseMessage (StringResponseMessage(204,"device succesfully deleted"))
    )

  val updateDeviceOperation = (apiOperation[DeviceEntity]("updateDevice")
    summary "Update a device"
    parameters (pathParam[String]("deviceid").description("device id")).required
    parameter bodyParam[Device]("device").description("the updated values").required

    responseMessage (StringResponseMessage(400,"invalid request"))
    responseMessage (StringResponseMessage(404,"device not found"))
    responseMessage (StringResponseMessage(200,"device succesfully updated"))
    )

  val getDeviceListOperation = (apiOperation[Set[DeviceEntity]]("getAllDevices")
    summary "Get All devices"
    )

}
