package com.distancetracker.model


case class GpsDataEntity(deviceId: String, latitude: Double, longitude: Double) extends Entity {
  def this(deviceId: String, coord: Coordinates){
      this(deviceId, coord.latitude, coord.longitude)
  }
}
