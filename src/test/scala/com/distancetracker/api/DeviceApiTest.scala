package com.distancetracker.api

import com.distancetracker.dao.DeviceDao
import com.distancetracker.model.{Device, DeviceEntity}
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization.write
import org.mockito.Matchers._
import org.mockito.Mockito._

/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApiTest extends BaseServletTest {

  val jsonContentTypeHeader = Map("Content-Type" -> "application/json")
  protected implicit val jsonFormats: Formats = DefaultFormats
  implicit var mockDeviceDao: DeviceDao = mock[DeviceDao]

  addServlet(new DeviceApi, "/devices/*")

  test("POST /devices/device - create a device") {
    val deviceParameter = Device("n1", "m1")
    val expectedDevice = new DeviceEntity(deviceParameter)
    when(mockDeviceDao.create(any[DeviceEntity])).thenReturn(Some(expectedDevice))

    post("/devices/device", body = write(deviceParameter).getBytes, headers = jsonContentTypeHeader) {
      status should equal(201)
      header.getOrElse("Content-Type", fail) should include("application/json")

      val actualDevice: DeviceEntity = parse(body).extract[DeviceEntity]
      actualDevice should be(expectedDevice)
      verify(mockDeviceDao).create(any[DeviceEntity])
    }
    reset(mockDeviceDao)
  }

  test("GET non existing device should return Not found") {
    val testId = "123Random"
    when(mockDeviceDao.read(testId)).thenReturn(None)
    get("/devices/device/" + testId, headers = jsonContentTypeHeader) {
      status should equal(404)
      verify(mockDeviceDao).read(testId)
    }
    reset(mockDeviceDao)
  }

  test("GET /devices/device/:id - retrieve a device") {
    val expectedDevice = new DeviceEntity("n1", "m1")
    when(mockDeviceDao.read(expectedDevice.id)).thenReturn(Some(expectedDevice))

    get("/devices/device/" + expectedDevice.id, headers = jsonContentTypeHeader) {
      status should equal(200)
      header.getOrElse("Content-Type", fail) should include("application/json")

      val actualDevice: DeviceEntity = parse(body).extract[DeviceEntity]
      actualDevice should be(expectedDevice)
      verify(mockDeviceDao).read(expectedDevice.id)
    }
    reset(mockDeviceDao)
  }

  test("DELETE /devices/device/:id - removes device") {
    val deviceToDelete = new DeviceEntity("n1", "m1")
    when(mockDeviceDao.delete(deviceToDelete.id)).thenReturn(Some(deviceToDelete))

    delete("/devices/device/" + deviceToDelete.id, headers = jsonContentTypeHeader) {
      status should equal(200)
      header.getOrElse("Content-Type", fail) should include("application/json")

      val actualDeviceDeleted: DeviceEntity = parse(body).extract[DeviceEntity]
      actualDeviceDeleted should be(deviceToDelete)
      verify(mockDeviceDao).delete(deviceToDelete.id)
    }
    reset(mockDeviceDao)
  }

  test("PUT /devices/device/:id - updates device") {
    val deviceParameter = Device("updatedValue", "updatedValue")
    val updatedDevice = new DeviceEntity(deviceParameter)
    val deviceToUpdate = new DeviceEntity(updatedDevice.id, "n1", "m1")
    when(mockDeviceDao.update(updatedDevice)).thenReturn(Some(updatedDevice))

    put("/devices/device/" + deviceToUpdate.id, body = write(deviceParameter).getBytes(),
      headers = jsonContentTypeHeader) {
      status should equal(200)
      header.getOrElse("Content-Type", fail) should include("application/json")

      val actualDeviceDeleted: DeviceEntity = parse(body).extract[DeviceEntity]
      actualDeviceDeleted should be(updatedDevice)
      verify(mockDeviceDao).update(updatedDevice)
    }
    reset(mockDeviceDao)
  }


}
