package com.distancetracker.api

import com.distancetracker.model.{Coordinates, GpsDataEntity}
import com.distancetracker.persistence.DataSource
import com.distancetracker.swagger.GpsApiDescription
import org.scalatra.Created


class GPSApi(implicit var deviceDS: DataSource[GpsDataEntity]) extends BaseController with GpsApiDescription {

  post("/:deviceId",operation(insertDeviceCoordinates)) {
    val deviceCoord = deviceDS.create(new GpsDataEntity(getDeviceIdFromUrl, getCordFromBody))
    Created(deviceCoord)
  }

  def getCordFromBody = parsedBody.extractOrElse[Coordinates](halt(400))

  def getDeviceIdFromUrl = params.getOrElse("deviceId", halt(400))
}
