package com.distancetracker.dao

import com.distancetracker.model.DeviceEntity
import com.distancetracker.persistence.DataSource
import org.bson.types.ObjectId

/**
 * @author claudio
 */
class DeviceDao(dataSource: DataSource) extends GenericDao[DeviceEntity, ObjectId] {


  def create(newInstance: DeviceEntity): Option[DeviceEntity] = {
    dataSource.createNewDevice(newInstance)
  }

  def read(deviceId: ObjectId): Option[DeviceEntity] = {
    dataSource.getDevice(deviceId)
  }

  def update(device: DeviceEntity): Option[DeviceEntity] = {
    dataSource.updateDevice(device)
  }

  def delete(deviceId: ObjectId): Option[DeviceEntity] = {
    dataSource.deleteDevice(deviceId)
  }

}