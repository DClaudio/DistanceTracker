package com.distancetracker.dao

import com.distancetracker.model.Device
import com.distancetracker.persistence.DataSource

/**
 * @author claudio
 */
class DeviceDao(dataSource: DataSource) {
  
  
  def create(newInstance: Device): Long = {
    dataSource.createNewDevice(newInstance)
  }
  
   def read(deviceId: Long ): Device = {
    dataSource.getDevice(deviceId)
  }

}