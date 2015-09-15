package com.distancetracker.api

import com.distancetracker.model.Device
import com.distancetracker.persistence.DeviceEntity
import org.scalatra.test.specs2._

import scala.collection.DefaultMap
import org.json4s._
import org.json4s.jackson.JsonMethods._

/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApiTest extends MutableScalatraSpec {

  addServlet(classOf[DeviceApi], "/devices/*")

  "POST /devices/device" should {
    "return status 201(created)" in {
      post("/devices/device", "{name:'n1', email:'m1'}") {
        var newDevice = new Device("n1", "m1")
        status must_== 201
        getContentTypeFromHeader(header) must contain("application/json")
        val jsonBody = parse(body)
        jsonBody.extract[DeviceEntity] must_== new DeviceEntity(_, "n1", "m1")
      }
    }
  }

  "GET /devices/device/123 on DeviceApi" should {
    "return status 200" in {
      get("/devices/device/123") {
        status must_== 200
        getContentTypeFromHeader(header) must contain("application/json")
      }
    }
  }



  def getContentTypeFromHeader(header: DefaultMap[String,String]):String = {
    header.get("Content-Type") match {
      case Some(contentType: String) => contentType
      case None => ""
    }
  }
}
