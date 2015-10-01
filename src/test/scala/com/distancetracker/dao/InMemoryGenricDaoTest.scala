package com.distancetracker.dao

import com.distancetracker.persistence.DeviceEntity
import org.bson.types.ObjectId


class InMemoryGenricDaoTest extends BaseTest {

  var dataSource: GenericDao[DeviceEntity, String] = null

  before {
    dataSource = new InMemoryGenericDao[DeviceEntity]
  }

  it should "persist a device in a storage" in {
    val expectedDevice = new DeviceEntity(name = "testName", email = "testMail")
    dataSource.create(expectedDevice) should be(Some(expectedDevice))
  }

  it should "update the device" in {
    val deviceToUpdate = dataSource.create(new DeviceEntity(name = "testName", email = "testMail")).get
    val expectedDevice = new DeviceEntity(deviceToUpdate.id, "updatedName", "updatedMail")

    dataSource.update(expectedDevice) should be(Some(expectedDevice))
  }

  it should "delete a device" in {
    val expectedDevice = dataSource.create(new DeviceEntity(name = "testName", email = "testMail")).get

    dataSource.delete(expectedDevice.id) should be(true)
    dataSource.getById(expectedDevice.id) should be(None)
  }

  it should "return None when reading a non existing device" in {
    dataSource.getById(ObjectId.get.toString) should be(None)
  }

  it should "return all devices registered" in {
    val expectedDeviceList = Set(new DeviceEntity(name = "testName", email = "testMail"),
      new DeviceEntity(name = "testName", email = "testMail"))
    expectedDeviceList.foreach(dataSource.create)

    dataSource.getAll should equal(expectedDeviceList)
  }

  it should "fail to create two entities with the same key" in {
    val d1 = new DeviceEntity(name = "testName", email = "testMail")
    val d2 = new DeviceEntity(d1.id, "n2", "m2")

    dataSource.create(d1) should be(Some(d1))
    dataSource.create(d2) should be(None)

    dataSource.getById(d1.id) should be(Some(d1))
  }

}