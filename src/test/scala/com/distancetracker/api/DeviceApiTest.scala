package com.distancetracker.api

import com.distancetracker.model.Device
import com.distancetracker.persistence.DeviceEntity
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.scalatest.FunSuiteLike
import org.scalatra.test.scalatest.ScalatraSuite
import org.json4s.native.Serialization.write

import scala.collection.DefaultMap

/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApiTest extends ScalatraSuite with FunSuiteLike {

  protected implicit val jsonFormats: Formats = DefaultFormats

  addServlet(classOf[DeviceApi], "/devices/*")

  test("POST /devices/device") {
    val requestBody = write(Device("n1", "m1")).getBytes
    val requestHeaders = Map("Content-Type" -> "application/json")

    post("/devices/device", requestBody, requestHeaders ) {
      status should equal(201)
      var device = parse(body).extract[DeviceEntity]
      getContentTypeFromHeader(header) should include("application/json")
      parse(body).extract[DeviceEntity] should be(DeviceEntity(1L,"n1", "m1"))
    }
  }

  test("retrieve a device") {
    get("/devices/device/123") {
      status should equal(200)
      getContentTypeFromHeader(header) should include("application/json")
    }
  }


  def getContentTypeFromHeader(header: DefaultMap[String, String]): String = {
    header.get("Content-Type") match {
      case Some(contentType: String) => contentType
      case None => ""
    }
  }
}
