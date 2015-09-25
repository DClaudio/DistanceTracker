package com.distancetracker.persistence

import com.distancetracker.model.DeviceEntity
import org.bson.types.ObjectId

/**
 * @author claudio
 */
trait DataSource {
  
  def createNewDevice(device: DeviceEntity): Option[DeviceEntity]
  
  def getDevice(deviceId: ObjectId): Option[DeviceEntity]

  def updateDevice(device: DeviceEntity): Option[DeviceEntity]

  def deleteDevice(deviceId: ObjectId): Option[DeviceEntity]

  
}