package com.distancetracker.api

import com.distancetracker.dao.{GenericDao, InMemoryGenericDao}
import com.distancetracker.model.Coordinates
import com.distancetracker.persistence.GpsDataEntity
import org.bson.types.ObjectId
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization.write
import org.mockito.Matchers._
import org.mockito.Mockito._


class GPSApiTest extends BaseServletTest {

  implicit var mockGpsDS: GenericDao[GpsDataEntity, String] = mock[InMemoryGenericDao[GpsDataEntity]]

  addServlet(new GPSApi, "/coordinates/*")

  test("POST /coordinates/:deviceId - insert device coordinates") {
    val coordinatesParam = Coordinates(123, 123)
    val expectedDevice = new GpsDataEntity(ObjectId.get.toString, coordinatesParam)
    when(mockGpsDS.create(any[GpsDataEntity])).thenReturn(Some(expectedDevice))

    post("/coordinates/" + expectedDevice.id, body = write(coordinatesParam).getBytes, headers = jsonContentTypeHeader) {
      status should equal(201)
      header.getOrElse("Content-Type", fail) should include("application/json")

      val actualDevice: GpsDataEntity = parse(body).extract[GpsDataEntity]
      actualDevice should be(expectedDevice)
      verify(mockGpsDS).create(any[GpsDataEntity])
    }
    reset(mockGpsDS)
  }

  test("GET /coordinates/:deviceId - retrieve a device") {
    val expectedDevice = new GpsDataEntity(ObjectId.get.toString, 123, 123)
    when(mockGpsDS.getById(expectedDevice.id)).thenReturn(Some(expectedDevice))

    get("/coordinates/" + expectedDevice.id, headers = jsonContentTypeHeader) {
      status should equal(200)
      header.getOrElse("Content-Type", fail) should include("application/json")

      val actualDevice: GpsDataEntity = parse(body).extract[GpsDataEntity]
      actualDevice should be(expectedDevice)
      verify(mockGpsDS).getById(expectedDevice.id)
    }
    reset(mockGpsDS)
  }

  test("GET coordonates for non existing device should return Not found") {
    val testId = "123Random"
    when(mockGpsDS.getById(testId)).thenReturn(None)
    get("/coordinates/" + testId, headers = jsonContentTypeHeader) {
      status should equal(404)
      verify(mockGpsDS).getById(testId)
    }
    reset(mockGpsDS)
  }


}
