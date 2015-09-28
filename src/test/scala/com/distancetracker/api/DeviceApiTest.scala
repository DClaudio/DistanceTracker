package com.distancetracker.api

import com.distancetracker.dao.DeviceDao
import com.distancetracker.model.{Device, DeviceEntity}
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization.write
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.scalatest.mock.MockitoSugar
import org.scalatest.{BeforeAndAfter, FunSuiteLike}
import org.scalatra.test.scalatest.ScalatraSuite

/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApiTest extends ScalatraSuite with FunSuiteLike with MockitoSugar with BeforeAndAfter {

  val jsonContentTypeHeader = Map("Content-Type" -> "application/json")
  protected implicit val jsonFormats: Formats = DefaultFormats
  implicit var mockDeviceDao: DeviceDao = mock[DeviceDao]

  addServlet(new DeviceApi, "/devices/*")

  before {
    //mockDeviceDao = mock[DeviceDao]
  }

  test("POST /devices/device - create a device") {
    val deviceParameter = Device("n1", "m1")
    val expectedDevice = new DeviceEntity(deviceParameter)
    when(mockDeviceDao.create(any[DeviceEntity])).thenReturn(Some(expectedDevice))

    post("/devices/device", write(deviceParameter).getBytes, jsonContentTypeHeader) {
      status should equal(201)
      header.getOrElse("Content-Type", fail) should include("application/json")
      val bd = body
      val actualDevice: DeviceEntity = parse(body).extract[DeviceEntity]
      actualDevice should be(expectedDevice)
    }
    verify(mockDeviceDao).create(any[DeviceEntity])
    reset(mockDeviceDao)
  }

  test("GET non existing device should return Not found"){
    val testId = "123"
    when(mockDeviceDao.read(testId)).thenReturn(None)
    get("/devices/device/"+testId , jsonContentTypeHeader) {
      status should equal(404)
    }
    verify(mockDeviceDao).read(testId)
    reset(mockDeviceDao)
  }

  test("GET /devices/device/:id - retrieve a device") {

    val deviceParameter = Device("n1", "m1")
    val expectedDevice = new DeviceEntity(deviceParameter)
    when(mockDeviceDao.read(expectedDevice.id)).thenReturn(Some(expectedDevice))

    get("/devices/device/" + expectedDevice.id, jsonContentTypeHeader) {
      status should equal(200)
      header.getOrElse("Content-Type", fail) should include("application/json")
      val bd = body
      val prbd = parse(body)
      val actualDevice: DeviceEntity = parse(body).extract[DeviceEntity]

      actualDevice should be(expectedDevice)
    }
    verify(mockDeviceDao).read(expectedDevice.id)
    reset(mockDeviceDao)

  }


}
