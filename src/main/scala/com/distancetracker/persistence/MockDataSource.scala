package com.distancetracker.persistence

import com.distancetracker.model.Device
import scala.collection.mutable.HashMap

/**
 * @author claudio
 */
class MockDataSource extends DataSource{
  
  val database = new HashMap[Long, Device]
  
  def createNewDevice(device: Device): Long = {
    val deviceId = 1L
    database.put(deviceId, device)
    deviceId
  }
  
  def getDevice(deviceId: Long): Device = {
    
    database.get(deviceId) match {
      case Some(device) => device
      case None => new Device("","")
    }
  }
  
}