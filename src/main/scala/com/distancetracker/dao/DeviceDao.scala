package com.distancetracker.dao

import com.distancetracker.model.Device
import com.distancetracker.persistence.DataSource

/**
 * @author claudio
 */
class DeviceDao(dataSource: DataSource) extends GenericDao[Device, Long] {


  def create(newInstance: Device): Long = {
    dataSource.createNewDevice(newInstance)
  }

  def read(deviceId: Long): Device = {
    dataSource.getDevice(deviceId)
  }

  def update(deviceId: Long, device: Device): Unit = {
    dataSource.update(deviceId, device)
  }

  def delete(deviceId: Long): Unit = {
    dataSource.delete(deviceId)
  }

}