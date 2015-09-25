package com.distancetracker.api

import com.distancetracker.dao.DeviceDao
import com.distancetracker.model.{Device, DeviceEntity}
import com.distancetracker.persistence.InMemoryDataSource
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization.write
import org.scalatest.FunSuiteLike
import org.scalatra.test.scalatest.ScalatraSuite

import scala.collection.DefaultMap

/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApiTest extends ScalatraSuite with FunSuiteLike {

  protected implicit val deviceDao: DeviceDao = new DeviceDao(InMemoryDataSource)
  protected implicit val jsonFormats: Formats = DefaultFormats

  addServlet(new DeviceApi, "/devices/*")

  test("POST /devices/device") {
    val actualDevice = Device("n1", "m1");
    val requestBody = write(actualDevice).getBytes
    val requestHeaders = Map("Content-Type" -> "application/json")

    post("/devices/device", requestBody, requestHeaders) {
      status should equal(201)
      getContentTypeFromHeader(header) should include("application/json")
      val returnedDevice: DeviceEntity = parse(body).extract[DeviceEntity]
      returnedDevice.name should be(actualDevice.name)
      returnedDevice.email should be(actualDevice.email)
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
