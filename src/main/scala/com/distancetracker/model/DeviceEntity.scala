package com.distancetracker.model

import com.novus.salat.annotations.raw.Key
import org.bson.types.ObjectId

case class DeviceEntity(@Key("_id") id: String = ObjectId.get.toString, name: String, email: String) extends Entity {

  def this(name: String, email: String) {
    this(ObjectId.get.toString, name, email)
  }

  def this(device: Device) {
    this(ObjectId.get.toString, device.name, device.email)
  }

  def this(id: String, device: Device) {
    this(id, device.name, device.email)
  }

}