package com.swagger

import org.scalatra.ScalatraServlet
import org.scalatra.swagger.{ApiInfo, NativeSwaggerBase, Swagger}

/**
 * Created by claudio.david on 22/07/2015.
 */
class ResourcesApp(implicit val swagger: Swagger) extends ScalatraServlet with NativeSwaggerBase

object DistanceTrackerApiInfo extends ApiInfo(
    "DistanceTracker-API",
    "REST API for distance tracker mobile app",
    "http://swagger.io",
    "test@test.com",
    "Apache 2.0",
    "http://www.apache.org/licenses/LICENSE-2.0.html")

class DistanceTrackerSwagger extends Swagger(Swagger.SpecVersion, "1.0.0", DistanceTrackerApiInfo)