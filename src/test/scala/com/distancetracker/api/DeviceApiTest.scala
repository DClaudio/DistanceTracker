package com.distancetracker.api

import org.scalatra.test.specs2._

/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApiTest extends MutableScalatraSpec {

  addServlet(classOf[DeviceApi], "/devices/*")

  "POST /devices/device" should {
    "return status 201(created)" in{
      post("/devices/device", "{name:'n1', email:'m1'}"){
        status must_== 201
      }
    }
  }

  "GET /devices/device/123 on DeviceApi" should {
    "return status 200" in {
      get("/devices/device/123") {
        status must_== 200
      }
    }
  }


}
