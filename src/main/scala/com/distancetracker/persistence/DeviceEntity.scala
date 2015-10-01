package com.distancetracker.persistence

import com.novus.salat.annotations._
import org.bson.types.ObjectId

case class DeviceEntity(@Key("_id") id: String = ObjectId.get.toString, name: String, email: String) extends Entity