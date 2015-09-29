package com.distancetracker.persistence

import com.distancetracker.model.DeviceEntity

/**
 * @author claudio
 */
trait DataSource {

  def createNewDevice(device: DeviceEntity): Option[DeviceEntity]

  def getDevice(deviceId: String): Option[DeviceEntity]

  def updateDevice(device: DeviceEntity): Option[DeviceEntity]

  def deleteDevice(deviceId: String): Option[DeviceEntity]

  def getAllDevices(): Set[DeviceEntity]

}