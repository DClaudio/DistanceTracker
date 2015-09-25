package com.distancetracker.model


import com.novus.salat.annotations.raw.Key
import org.bson.types.ObjectId

/**
 * Created by claudio.david on 15/09/2015.
 */
case class DeviceEntity(@Key("_id") id: String = ObjectId.get.toString, name: String, email: String) {
  
  def this(name: String, email: String) {
    this(ObjectId.get.toString, name, email)
  }

  def this(device: Device) {
    this(ObjectId.get.toString, device.name, device.email)
  }

}