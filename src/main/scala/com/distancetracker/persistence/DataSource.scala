package com.distancetracker.persistence

import com.distancetracker.model.Device

/**
 * @author claudio
 */
trait DataSource {
  
  def createNewDevice(device: Device): Long
  
  def getDevice(deviceId: Long): Option[Device]

  def updateDevice(deviceId: Long, device: Device): Option[Device]

  def deleteDevice(deviceId: Long): Option[Device]

  
}