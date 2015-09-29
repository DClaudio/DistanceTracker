package com.distancetracker.swagger

import com.distancetracker.model.{Coordinates, GpsDataEntity}
import org.scalatra.swagger.{Swagger, SwaggerSupport}


trait GpsApiDescription extends SwaggerSupport {

  val swagger: Swagger = DistanceTrackerSwagger
  val applicationDescription: String = "GpsApi"

  val insertDeviceCoordinates = (apiOperation[GpsDataEntity]("insertCoordinates")
    summary "Insert new coordinates in the database."
    parameters (pathParam[String]("deviceid").description("device id")).required
    parameter bodyParam[Coordinates]("gps coordinates").description("the coordinates to insert").required
    )

}
