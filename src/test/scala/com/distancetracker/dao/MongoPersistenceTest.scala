package com.distancetracker.dao

import com.distancetracker.model.DeviceEntity
import org.bson.types.ObjectId


//TODO: implement mongo persistence
class MongoPersistenceTest extends BaseTest{

  val deviceDao = new MongoSalatDeviceDao


  it should "persist a device" in {
    val device = new DeviceEntity(name = "n1",email="m1")
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
}


