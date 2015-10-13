package com.distancetracker.util

import com.distancetracker.dao._
import com.distancetracker.persistence.{GpsDataEntity, DeviceEntity}
import com.mongodb.casbah.MongoClient

object DaoFactory {

  def getDeviceDao:GenericDao[DeviceEntity, String] = {
    if(PropertiesHelper.useInMemoryDataBase) new InMemoryGenericDao[DeviceEntity]
    else {
      val salatDeviceDao = new SalatDeviceDao(MongoClient(PropertiesHelper.getMongoUrl, PropertiesHelper.getMongoPort), PropertiesHelper.getDatabaseName, PropertiesHelper.getDeviceCollectionName)
      new MongoDeviceDao(salatDeviceDao)
    }
  }

  def getGpsDao:GenericDao[GpsDataEntity, String] = {
    if(PropertiesHelper.useInMemoryDataBase) new InMemoryGenericDao[GpsDataEntity]
    else {
      val salatGpsDataDao = new SalatGpsDataColectionDao(MongoClient(PropertiesHelper.getMongoUrl, PropertiesHelper.getMongoPort), PropertiesHelper.getDatabaseName, PropertiesHelper.getDeviceCollectionName)
      new MongoGpsDao(salatGpsDataDao)
    }
  }

}
