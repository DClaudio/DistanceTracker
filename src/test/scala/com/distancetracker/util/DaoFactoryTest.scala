package com.distancetracker.util

import com.distancetracker.dao.{BaseTest, InMemoryGenericDao}
import com.distancetracker.persistence.{GpsDataEntity, DeviceEntity}


class DaoFactoryTest extends BaseTest {

  it should "return an instance of InMemoryDao" in {
    DaoFactory.getDeviceDao.isInstanceOf[InMemoryGenericDao[DeviceEntity]] should be(true)
    DaoFactory.getGpsDao.isInstanceOf[InMemoryGenericDao[GpsDataEntity]] should be(true)
  }
}
