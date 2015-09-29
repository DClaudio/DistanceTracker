package com.distancetracker.dao

import com.distancetracker.model.DeviceEntity
import com.distancetracker.persistence.InMemoryDataSource
import org.bson.types.ObjectId

/**
 * @author claudio
 */
class InMemoryPersistenceTest extends BaseTest {

  val deviceDao = new DeviceDao(InMemoryDataSource)

  it should "persist a device in a storage" in {
    val expectedDevice = new DeviceEntity("testName", "testMail")

    val actualDevice = deviceDao.create(expectedDevice)
    assert(actualDevice == Some(expectedDevice))
  }

  it should "update the device" in {
    val deviceToUpdate = new DeviceEntity("n1", "m1")
    deviceDao.create(deviceToUpdate)
    val expectedDevice = new DeviceEntity(deviceToUpdate.id, "updatedName", "updatedMail")

    val actualDevice = deviceDao.update(expectedDevice)
    assert(actualDevice == Some(expectedDevice))
  }

  it should "delete a device" in {
    val expectedDevice = new DeviceEntity("n1", "m1")
    deviceDao.create(expectedDevice)

    assert(deviceDao.delete(expectedDevice.id) == Some(expectedDevice))
    assert(deviceDao.read(expectedDevice.id) == None)
  }

  it should "return None when reading a non existing device" in {
    assert(deviceDao.read(ObjectId.get.toString) == None)
  }

}