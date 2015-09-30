package com.distancetracker.model

import com.novus.salat.annotations.raw.Key


case class GpsDataEntity(@Key("_id") id: String, latitude: Double, longitude: Double) extends Entity {

  def this(deviceId: String, coord: Coordinates) {
    this(deviceId, coord.latitude, coord.longitude)
  }
  
}
