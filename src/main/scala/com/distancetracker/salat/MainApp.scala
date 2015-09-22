package com.distancetracker.salat

import com.distancetracker.dao.MongoSalatDeviceDao
import com.distancetracker.persistence.DeviceEntity

/**
 * Created by claudio.david on 22/09/2015.
 */
object MainApp extends App{

  val deviceDao = new MongoSalatDeviceDao

  val device = new DeviceEntity(name = "Gigel", email = "gigel@yahoo.com")

  val id = deviceDao.insert(device)
  assert(id.isDefined)

  val getResult = deviceDao.findOneById(id.get)
  assert(getResult.isDefined)

}
