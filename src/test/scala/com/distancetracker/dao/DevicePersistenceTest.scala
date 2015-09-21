package com.distancetracker.dao


import com.distancetracker.model.Device
import com.distancetracker.persistence.InMemoryDataSource

/**
 * @author claudio
 */
class DevicePersistenceTest extends BaseTest {

  val deviceDao = new DeviceDao(InMemoryDataSource)

  it should "persist a device in a storage" in {

    val device = new Device("testName", "testMail")
    val deviceId = deviceDao.create(device)

    val resultDevice = deviceDao.read(deviceId)

    assert(resultDevice == Some(device))

  }

  it should "update the device" in {
    val idToUpdate = deviceDao.create(new Device("n1", "m1"))
    val updatedDevice = new Device("updatedName", "updatedMail")

    deviceDao.update(idToUpdate, updatedDevice)

    assert(deviceDao.read(idToUpdate) == Some(updatedDevice))
  }

  it should "delete a device" in {
    val device = new Device("n1", "m1")
    val idToDelete = deviceDao.create(device)
    deviceDao.delete(idToDelete)

    assert(deviceDao.read(idToDelete) == None)
  }

  it should "return None when reading a non existing device" in {
    assert(deviceDao.read(1L) == None)
  }

}