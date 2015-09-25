package com.distancetracker.api

import com.distancetracker.dao.DeviceDao
import com.distancetracker.model.{Device, DeviceEntity}
import com.distancetracker.persistence.DataSource
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization.write
import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfter, FunSuiteLike}
import org.scalatra.test.scalatest.ScalatraSuite

/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApiTest extends ScalatraSuite with FunSuiteLike with MockFactory with BeforeAndAfter {

  val jsonContentTypeHeader = Map("Content-Type" -> "application/json")
  protected implicit val jsonFormats: Formats = DefaultFormats
  implicit var deviceDao: DeviceDao = _
  var dataSource = mock[DataSource]

  addServlet(new DeviceApi, "/devices/*")

  before {
    deviceDao = new DeviceDao(dataSource)
  }

  test("POST /devices/device") {
    val deviceParameter = Device("n1", "m1")
    val expectedDevice = new DeviceEntity(deviceParameter)
    val requestBody = write(deviceParameter).getBytes
    (dataSource.createNewDevice _).expects(*).returning(Some(expectedDevice))

    post("/devices/device", requestBody, jsonContentTypeHeader) {
      status should equal(201)
      header.getOrElse("Content-Type", fail) should include("application/json")
      val returnedDevice: DeviceEntity = parse(body).extract[DeviceEntity]
      returnedDevice.name should be(deviceParameter.name)
      returnedDevice.email should be(deviceParameter.email)
      returnedDevice.id should not be (None)
      returnedDevice should be(expectedDevice)
    }
  }

//  test("retrieve a device") {
//
//    val deviceParameter = Device("n1", "m1")
//    val expectedDevice = new DeviceEntity(deviceParameter)
//
//    (dataSource.getDevice _).expects(*).returning(Some(expectedDevice))
//    get("/devices/device/" + expectedDevice.id, jsonContentTypeHeader) {
//      status should equal(200)
//      header.getOrElse("Content-Type", fail) should include("application/json")
//    }
//  }


}
