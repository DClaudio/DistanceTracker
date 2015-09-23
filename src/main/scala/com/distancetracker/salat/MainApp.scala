package com.distancetracker.salat

import com.distancetracker.dao.MongoSalatDeviceDao
import com.distancetracker.persistence.DeviceEntity

import com.mongodb.casbah.Imports._
import com.novus.salat._
import com.novus.salat.global._

import com.mongodb.casbah.Imports.ObjectId

/**
 * Created by claudio.david on 22/09/2015.
 */
object MainApp extends App{

  val deviceDao = new MongoSalatDeviceDao

//  val device = new DeviceEntity(name = "Gigel", email = "gigel@yahoo.com")
//  println(device.id)
//
//  val id = deviceDao.insert(device)
//  assert(id.isDefined)
//
//  val resultedDevice = deviceDao.findOneById(device.id)
//  assert(resultedDevice.isDefined)
//  println(resultedDevice)
//  println(resultedDevice.get.id)

  val objId = new ObjectId("5602bb856844ccbe2a9fed97")
  println(deviceDao.findOneById(objId))

  //TODO: fix this with implicit conversion
  val results = deviceDao.find(ref = DeviceQueryParams(name = Some("gigel")))
  println(results.toList)

}
