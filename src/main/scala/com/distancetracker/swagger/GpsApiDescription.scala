package com.distancetracker.swagger

import com.distancetracker.model.Coordinates
import com.distancetracker.persistence.GpsDataEntity
import org.scalatra.swagger.{StringResponseMessage, Swagger, SwaggerSupport}


trait GpsApiDescription extends SwaggerSupport {

  val swagger: Swagger = DistanceTrackerSwagger
  val applicationDescription: String = "GpsApi"

  val updateDeviceCoordinates = (apiOperation[GpsDataEntity]("updateCoordinates")
    summary "Update device coordinates in the database."
    parameters (pathParam[String]("deviceid").description("device id")).required
    parameter bodyParam[Coordinates]("gps coordinates").description("the coordinates to update").required

    responseMessage (StringResponseMessage(400,"invalid request"))
    responseMessage (StringResponseMessage(201,"device created"))
    )

  val readDeviceCoordinates = (apiOperation[GpsDataEntity]("readDeviceCoordinates")
    summary "Read coordinates from the database."
    parameters (pathParam[String]("deviceid").description("device id")).required

    responseMessage (StringResponseMessage(400,"invalid request"))
    responseMessage (StringResponseMessage(404,"coordinates not found"))
    responseMessage (StringResponseMessage(200,"device found"))
    )

}
