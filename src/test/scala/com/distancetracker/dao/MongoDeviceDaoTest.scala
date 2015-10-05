package com.distancetracker.dao

import com.distancetracker.persistence.DeviceEntity
import com.mongodb.casbah.MongoClient
import org.bson.types.ObjectId


class MongoDeviceDaoTest extends BaseTest {


  var deviceCollectionDao = new SalatDeviceDao(MongoClient(), "distance_tracker", "device")
  var deviceDao: MongoDeviceDao = new MongoDeviceDao(deviceCollectionDao)


  override def afterAll() {
    // close mongo connection
    deviceCollectionDao.client.close()
  }


  it should "persist a device" in {
    val device = new DeviceEntity(name = "n1", email = "m1")
    val updatedDevice = new DeviceEntity(device.id, "new", "new")
    //insert a device
    deviceDao.create(device) should be(Some(device))
    //test if it's persisted
    deviceDao.getById(device.id) should be(Some(device))
    //test update
    deviceDao.update(updatedDevice) should be(Some(updatedDevice))
    // test delete
    deviceDao.delete(device.id) should be(true)
    deviceDao.delete(ObjectId.get.toString) should be(false)
  }

  it should "return all devices persisted" in {
    val expectedDeviceList = Set(new DeviceEntity(name = "testName", email = "testMail"),
      new DeviceEntity(name = "testName", email = "testMail"))
    expectedDeviceList.foreach(deviceDao.create)

    deviceDao.getAll should equal(expectedDeviceList)
    // clean up db
    expectedDeviceList.foreach(device => deviceDao.delete(device.id))
  }
}


