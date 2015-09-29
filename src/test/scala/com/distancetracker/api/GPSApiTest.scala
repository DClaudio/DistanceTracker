package com.distancetracker.api

import com.distancetracker.model.{Coordinates, GpsDataEntity}
import com.distancetracker.persistence.{DataSource, InMemoryDataSource}
import org.bson.types.ObjectId
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.native.Serialization.write
import org.mockito.Matchers._
import org.mockito.Mockito._


class GPSApiTest extends BaseServletTest {

  implicit var mockGpsDS: DataSource[GpsDataEntity] = mock[InMemoryDataSource[GpsDataEntity]]

  addServlet(new GPSApi, "/coordinates/*")

  test("POST gps/:deviceId - insert device coordinates") {
    val coordinatesParam = Coordinates(123, 123)
    val deviceId = ObjectId.get.toString
    val expectedDevice = new GpsDataEntity(deviceId, coordinatesParam)
    when(mockGpsDS.create(any[GpsDataEntity])).thenReturn(Some(expectedDevice))

    post("/coordinates/" + deviceId, body = write(coordinatesParam).getBytes, headers = jsonContentTypeHeader) {
      status should equal(201)
      header.getOrElse("Content-Type", fail) should include("application/json")

      val actualDevice: GpsDataEntity = parse(body).extract[GpsDataEntity]
      actualDevice should be(expectedDevice)
      verify(mockGpsDS).create(any[GpsDataEntity])
    }
    reset(mockGpsDS)
  }


}
