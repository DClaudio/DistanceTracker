package com.distancetracker.api

import com.distancetracker.model.Device
import com.distancetracker.swagger.DistanceTrackerSwagger
import org.scalatra.ScalatraServlet
import org.scalatra.swagger.{Swagger, SwaggerSupport}

/**
 * Created by claudio.david on 14/09/2015.
 */
abstract class BaseController extends ScalatraServlet {
  before() {
    response.headers += ("Access-Control-Allow-Origin" -> "*")
  }

}
