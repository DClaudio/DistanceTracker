package com.distancetracker.dao

import com.distancetracker.model.DeviceEntity
import com.distancetracker.persistence.DataSource

/**
 * @author claudio
 */
class DeviceDao(dataSource: DataSource[DeviceEntity]) extends GenericDao[DeviceEntity, String] {

  def create(newInstance: DeviceEntity): Option[DeviceEntity] = {
    dataSource.create(newInstance)
  }

  def read(deviceId: String): Option[DeviceEntity] = {
    dataSource.get(deviceId)
  }

  def update(device: DeviceEntity): Option[DeviceEntity] = {
    dataSource.update(device)
  }

  def delete(deviceId: String): Option[DeviceEntity] = {
    dataSource.delete(deviceId)
  }

  def getAll(): Set[DeviceEntity] = {
    dataSource.getAll
  }

}