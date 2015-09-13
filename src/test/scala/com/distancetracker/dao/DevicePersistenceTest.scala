package com.distancetracker.dao

import org.scalatest.FlatSpec
import com.distancetracker.model.Device
import org.scalatest.BeforeAndAfter
import com.distancetracker.persistence.MockDataSource
import org.scalatest.Matchers

/**
 * @author claudio
 */
class DevicePersistenceTest extends FlatSpec with BeforeAndAfter with Matchers{
  
  val deviceDao: DeviceDao = new DeviceDao(new MockDataSource())
  
  it should "persist a device in a storage" in{
     
    val device = new Device("testName", "testMail")    
    val deviceId = deviceDao.create(device)    
    
    val resultDevice = deviceDao.read(deviceId)
    
    assert(device == resultDevice)
    
  }
  
}