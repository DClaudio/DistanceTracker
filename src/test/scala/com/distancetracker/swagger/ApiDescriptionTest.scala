package com.distancetracker.swagger

import org.scalatest.FunSuiteLike
import org.scalatra.test.scalatest.ScalatraSuite

/**
 * Created by claudio.david on 14/09/2015.
 */
class ApiDescriptionTest extends ScalatraSuite with FunSuiteLike{

  addServlet(classOf[SwaggerIntegrationServlet], "/api-docs")

  test("GET /api-docs on root") {
      get("/api-docs") {
        status should equal(200)
    }
  }

}
