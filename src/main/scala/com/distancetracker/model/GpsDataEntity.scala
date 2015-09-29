package com.distancetracker.model

import com.novus.salat.annotations.raw.Key
import org.bson.types.ObjectId


case class GpsDataEntity(@Key("_id") id: String = ObjectId.get.toString, deviceId: String, latitude: Double, longitude: Double) extends Entity {

  def this(deviceId: String, coord: Coordinates) {
    this(ObjectId.get.toString, deviceId, coord.latitude, coord.longitude)
  }
}
