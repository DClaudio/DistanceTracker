package com.distancetracker.persistence

import com.distancetracker.model.DeviceEntity
import org.bson.types.ObjectId

import scala.collection.concurrent.TrieMap

/**
 * @author claudio
 *         This simulates a persistent storage
 */
object InMemoryDataSource extends DataSource {

  val database = new TrieMap[ObjectId, DeviceEntity]

  override def createNewDevice(device: DeviceEntity): Option[DeviceEntity] = {
    database.putIfAbsent(device.id, device)
    Some(device)
  }

  override def getDevice(deviceId: ObjectId): Option[DeviceEntity] = {
    database.get(deviceId)
  }

  override def updateDevice(device: DeviceEntity): Option[DeviceEntity] = {
    database.replace(device.id, device) match {
      case Some(_) => Some(device)
      case None => None
    }
  }

  override def deleteDevice(deviceId: ObjectId): Option[DeviceEntity] = {
    database.remove(deviceId)
  }

}