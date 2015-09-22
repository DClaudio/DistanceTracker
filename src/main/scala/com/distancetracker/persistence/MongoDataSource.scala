package com.distancetracker.persistence

import com.distancetracker.model.Device
import com.mongodb.casbah.Imports._
import com.novus.salat.global._
import com.novus.salat._

/**
 * Created by claudio.david on 22/09/2015.
 */
class MongoDataSource extends DataSource {

  val mongoClient = MongoClient("localhost", 27017)
  val deviceCollection = mongoClient("distance_tracker")("device")

  override def createNewDevice(device: Device): Long = {
    val id = new ObjectId
    val result = deviceCollection.insert(grater[Device].asDBObject(device))
    1L
  }

  override def updateDevice(deviceId: Long, device: Device): Option[Device] = ???

  override def deleteDevice(deviceId: Long): Option[Device] = ???

  override def getDevice(deviceId: Long): Option[Device] = {
    deviceCollection.findOne(MongoDBObject("_id" -> deviceId)) match {
      case Some(obj) => Some(grater[Device].asObject(obj))
      case None => None
    }
  }
}
