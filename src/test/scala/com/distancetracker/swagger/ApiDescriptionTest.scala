package com.distancetracker.swagger

import org.scalatra.test.specs2.MutableScalatraSpec

/**
 * Created by claudio.david on 14/09/2015.
 */
class ApiDescriptionTest extends MutableScalatraSpec{

  addServlet(classOf[SwaggerIntegrationServlet], "/api-docs")

  "GET /api-docs on root" should {
    "return status 200" in {
      get("/api-docs") {
        status must_== 200
      }
    }
  }

}
