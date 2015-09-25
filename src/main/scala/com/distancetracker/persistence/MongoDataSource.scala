package com.distancetracker.persistence

import com.distancetracker.model.DeviceEntity
import com.mongodb.casbah.Imports._
import com.novus.salat.global._
import com.novus.salat._

/**
 * Created by claudio.david on 22/09/2015.
 */
class MongoDataSource extends DataSource {

  val mongoClient = MongoClient("localhost", 27017)
  val deviceCollection = mongoClient("distance_tracker")("device")

  override def createNewDevice(device: DeviceEntity): Option[DeviceEntity] = {
    val result = deviceCollection.insert(grater[DeviceEntity].asDBObject(device))
    None
  }

  override def updateDevice(device: DeviceEntity): Option[DeviceEntity] = ???

  override def deleteDevice(deviceId: ObjectId): Option[DeviceEntity] = ???

  override def getDevice(deviceId: ObjectId): Option[DeviceEntity] = {
    deviceCollection.findOne(MongoDBObject("_id" -> deviceId)) match {
      case Some(obj) => Some(grater[DeviceEntity].asObject(obj))
      case None => None
    }
  }
}
