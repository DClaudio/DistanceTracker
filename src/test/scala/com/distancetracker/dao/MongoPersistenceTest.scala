package com.distancetracker.dao

import com.distancetracker.model.Device
import com.distancetracker.persistence.MongoDataSource

/**
 * Created by claudio.david on 22/09/2015.
 */
class MongoPersistenceTest extends BaseTest{

  val deviceDao = new DeviceDao(new MongoDataSource)

  it should "persist a device" in{

    val device = new Device("testName", "testMail")
    val deviceId = deviceDao.create(device)

    val resultDevice = deviceDao.read(deviceId)

    assert(resultDevice == Some(device))
  }

}
