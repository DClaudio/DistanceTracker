package com.distancetracker.api

import com.distancetracker.dao.GenericDao
import com.distancetracker.model.{Device, DeviceEntity}
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization.write
import org.mockito.Matchers._
import org.mockito.Mockito._


class DeviceApiTest extends BaseServletTest {


  implicit var mockDeviceDS: GenericDao[DeviceEntity, String] = mock[GenericDao[DeviceEntity, String]]

  addServlet(new DeviceApi, "/devices/*")

  test("POST /devices/device - create a device") {
    val deviceParameter = Device("n1", "m1")
    val expectedDevice = new DeviceEntity(name = deviceParameter.name, email = deviceParameter.email)
    when(mockDeviceDS.create(any[DeviceEntity])).thenReturn(Some(expectedDevice))

    post("/devices/device", body = write(deviceParameter).getBytes, headers = jsonContentTypeHeader) {
      status should equal(201)
      header.getOrElse("Content-Type", fail) should include("application/json")

      val actualDevice: DeviceEntity = parse(body).extract[DeviceEntity]
      actualDevice should be(expectedDevice)
      verify(mockDeviceDS).create(any[DeviceEntity])
    }
    reset(mockDeviceDS)
  }

  test("GET non existing device should return Not found") {
    val testId = "123Random"
    when(mockDeviceDS.getById(testId)).thenReturn(None)
    get("/devices/device/" + testId, headers = jsonContentTypeHeader) {
      status should equal(404)
      verify(mockDeviceDS).getById(testId)
    }
    reset(mockDeviceDS)
  }

  test("GET /devices/device/:id - retrieve a device") {
    val expectedDevice = new DeviceEntity(name = "n1", email = "m1")
    when(mockDeviceDS.getById(expectedDevice.id)).thenReturn(Some(expectedDevice))

    get("/devices/device/" + expectedDevice.id, headers = jsonContentTypeHeader) {
      status should equal(200)
      header.getOrElse("Content-Type", fail) should include("application/json")

      val actualDevice: DeviceEntity = parse(body).extract[DeviceEntity]
      actualDevice should be(expectedDevice)
      verify(mockDeviceDS).getById(expectedDevice.id)
    }
    reset(mockDeviceDS)
  }

  test("DELETE /devices/device/:id - removes device") {
    val deviceToDelete = new DeviceEntity(name = "n1", email = "m1")
    when(mockDeviceDS.delete(deviceToDelete.id)).thenReturn(true)

    delete("/devices/device/" + deviceToDelete.id, headers = jsonContentTypeHeader) {
      status should equal(204)
      header.getOrElse("Content-Type", fail) should include("application/json")
      verify(mockDeviceDS).delete(deviceToDelete.id)
    }
    reset(mockDeviceDS)
  }

  test("PUT /devices/device/:id - updates device") {
    val deviceParameter = Device("updatedValue", "updatedValue")
    val updatedDevice = new DeviceEntity(name = deviceParameter.name, email = deviceParameter.email)
    val expectedDevice = new DeviceEntity(updatedDevice.id, "n1", "m1")
    when(mockDeviceDS.update(updatedDevice)).thenReturn(Some(updatedDevice))

    put("/devices/device/" + expectedDevice.id, body = write(deviceParameter).getBytes(),
      headers = jsonContentTypeHeader) {
      status should equal(200)
      header.getOrElse("Content-Type", fail) should include("application/json")

      val actualDevice: DeviceEntity = parse(body).extract[DeviceEntity]
      actualDevice should be(updatedDevice)
      verify(mockDeviceDS).update(updatedDevice)
    }
    reset(mockDeviceDS)
  }

  test("GET /devices - get all devices") {
    val expectedDeviceList = Set(new DeviceEntity(name = "n1", email = "m1"), new DeviceEntity(name = "n2", email = "m2"))
    when(mockDeviceDS.getAll).thenReturn(expectedDeviceList)
    get("/devices", headers = jsonContentTypeHeader) {
      status should equal(200)
      header.getOrElse("Content-Type", fail) should include("application/json")

      val actualDeviceList = parse(body).extract[Set[DeviceEntity]]
      actualDeviceList should equal(expectedDeviceList)
      verify(mockDeviceDS).getAll
    }
    reset(mockDeviceDS)
  }


}
