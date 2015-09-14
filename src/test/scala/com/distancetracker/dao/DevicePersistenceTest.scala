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

    assert(device == resultDevice)

  }

  it should "update the device" in {
    val idToUpdate = deviceDao.create(new Device("n1", "m1"))
    val updatedDevice = new Device("updatedName", "updatedMail")

    deviceDao.update(idToUpdate, updatedDevice)

    assert(deviceDao.read(idToUpdate) == updatedDevice)
  }

  it should "delete a device" in {
    val device = new Device("n1", "m1")
    val idToDelete = deviceDao.create(device)
    deviceDao.delete(idToDelete)

    intercept[EntityNotFoundException] {
      deviceDao.read(idToDelete)
    }
  }

  it should "throw exception when delete a non existent device" in {
    intercept[EntityNotFoundException] {
      deviceDao.read(1L)
    }
  }

}