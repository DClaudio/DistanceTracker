package com.distancetracker.dao

import com.distancetracker.model.DeviceEntity
import com.distancetracker.persistence.{DataSource, InMemoryDataSource}
import org.bson.types.ObjectId


class InMemoryPersistenceTest extends BaseTest {

  var dataSource: DataSource[DeviceEntity,String]= null

  before {
    dataSource = new InMemoryDataSource[DeviceEntity]
  }

  it should "persist a device in a storage" in {
    val expectedDevice = new DeviceEntity("testName", "testMail")
    dataSource.create(expectedDevice) should be(Some(expectedDevice))
  }

  it should "update the device" in {
    val deviceToUpdate = dataSource.create(new DeviceEntity("n1", "m1")).get
    val expectedDevice = new DeviceEntity(deviceToUpdate.id, "updatedName", "updatedMail")

    dataSource.update(expectedDevice) should be(Some(expectedDevice))
  }

  it should "delete a device" in {
    val expectedDevice = dataSource.create(new DeviceEntity("n1", "m1")).get

    dataSource.delete(expectedDevice.id) should be(Some(expectedDevice))
    dataSource.getById(expectedDevice.id) should be(None)
  }

  it should "return None when reading a non existing device" in {
    dataSource.getById(ObjectId.get.toString) should be(None)
  }

  it should "return all devices registered" in {
    val expectedDeviceList = Set(new DeviceEntity("n1", "m1"), new DeviceEntity("n2", "m2"))
    expectedDeviceList.foreach(dataSource.create)

    dataSource.getAll should equal(expectedDeviceList)
  }

}