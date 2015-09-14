package com.distancetracker.swagger

import org.scalatra.ScalatraServlet
import org.scalatra.swagger.{ApiInfo, NativeSwaggerBase, Swagger}

/**
 * Created by claudio.david on 22/07/2015.
 */
object DistanceTrackerApiInfo extends ApiInfo(
  "DistanceTracker-API",
  "REST API for distance tracker mobile app",
  "http://swagger.io",
  "test@test.com",
  "Apache 2.0",
  "http://www.apache.org/licenses/LICENSE-2.0.html")

object DistanceTrackerSwagger extends Swagger(Swagger.SpecVersion, "1.0.0", DistanceTrackerApiInfo)

class ResourcesApp(val swagger: Swagger = DistanceTrackerSwagger) extends ScalatraServlet with NativeSwaggerBase