package com.distancetracker.model

import com.mongodb.casbah.Imports.ObjectId
import com.novus.salat.annotations.raw.Key

/**
 * Created by claudio.david on 15/09/2015.
 */
case class DeviceEntity(@Key("_id") id: ObjectId = new ObjectId, name: String, email: String){
  def this(device: Device){
    this(new ObjectId, device.name, device.email)
  }
}