package com.distancetracker.util

import com.distancetracker.BaseTest
import com.distancetracker.dao.GenericDao
import com.distancetracker.persistence.{DeviceEntity, GpsDataEntity}


class DaoFactoryTest extends BaseTest {

  it should "return an instance of InMemoryDao" in {
    DaoFactory.getDeviceDao.isInstanceOf[GenericDao[DeviceEntity, String]] should be(true)
    DaoFactory.getGpsDao.isInstanceOf[GenericDao[GpsDataEntity, String]] should be(true)
  }
}
