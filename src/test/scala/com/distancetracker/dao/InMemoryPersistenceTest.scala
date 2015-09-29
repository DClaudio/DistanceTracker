package com.distancetracker.dao

import com.distancetracker.model.DeviceEntity
import com.distancetracker.persistence.{DataSource, InMemoryDataSource}
import org.bson.types.ObjectId

/**
 * @author claudio
 */
class InMemoryPersistenceTest extends BaseTest {

  var dataSource: DataSource = null

  before {
    dataSource = new InMemoryDataSource
  }

  it should "persist a device in a storage" in {
    val expectedDevice = new DeviceEntity("testName", "testMail")
    dataSource.createNewDevice(expectedDevice) should be(Some(expectedDevice))
  }

  it should "update the device" in {
    val deviceToUpdate = dataSource.createNewDevice(new DeviceEntity("n1", "m1")).get
    val expectedDevice = new DeviceEntity(deviceToUpdate.id, "updatedName", "updatedMail")

    dataSource.updateDevice(expectedDevice) should be(Some(expectedDevice))
  }

  it should "delete a device" in {
    val expectedDevice = dataSource.createNewDevice(new DeviceEntity("n1", "m1")).get

    dataSource.deleteDevice(expectedDevice.id) should be(Some(expectedDevice))
    dataSource.getDevice(expectedDevice.id) should be(None)
  }

  it should "return None when reading a non existing device" in {
    dataSource.getDevice(ObjectId.get.toString) should be(None)
  }

  it should "return all devices registered" in {
    val expectedDeviceList = Set(new DeviceEntity("n1", "m1"), new DeviceEntity("n2", "m2"))
    expectedDeviceList.foreach(dataSource.createNewDevice)

    dataSource.getAllDevices should equal(expectedDeviceList)
  }

}