package com.distancetracker.dao

import com.distancetracker.model.DeviceEntity
import com.distancetracker.persistence.DataSource

/**
 * @author claudio
 */
class DeviceDao(dataSource: DataSource) extends GenericDao[DeviceEntity, String] {

  def create(newInstance: DeviceEntity): Option[DeviceEntity] = {
    dataSource.createNewDevice(newInstance)
  }

  def read(deviceId: String): Option[DeviceEntity] = {
    dataSource.getDevice(deviceId)
  }

  def update(device: DeviceEntity): Option[DeviceEntity] = {
    dataSource.updateDevice(device)
  }

  def delete(deviceId: String): Option[DeviceEntity] = {
    dataSource.deleteDevice(deviceId)
  }

  def getAll(): Set[DeviceEntity] = {
    dataSource.getAllDevices
  }

}