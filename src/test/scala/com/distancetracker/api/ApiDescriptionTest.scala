package com.distancetracker.api

import com.distancetracker.swagger.SwaggerIntegrationServlet
import org.scalatest.FunSuiteLike
import org.scalatra.test.scalatest.ScalatraSuite


class ApiDescriptionTest extends ScalatraSuite with FunSuiteLike {

  addServlet(classOf[SwaggerIntegrationServlet], "/api-docs")

  test("GET /api-docs on root") {
    get("/api-docs") {
      status should equal(200)
    }
  }

}
