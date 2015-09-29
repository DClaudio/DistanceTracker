package com.distancetracker.swagger

import org.scalatra.swagger.{Swagger, SwaggerSupport}


trait GpsApiDescription extends SwaggerSupport {

  val swagger: Swagger = DistanceTrackerSwagger
  val applicationDescription: String = "GpsApi"


}
