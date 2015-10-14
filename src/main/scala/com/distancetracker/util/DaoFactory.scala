package com.distancetracker.util

import com.distancetracker.dao._
import com.distancetracker.persistence.{DeviceEntity, GpsDataEntity}
import com.mongodb.casbah.MongoClient

object DaoFactory {

  val mongoClients: List[MongoClient] = null

  def getDeviceDao: GenericDao[DeviceEntity, String] = {
    if (PropertiesHelper.useInMemoryDataBase) new InMemoryGenericDao[DeviceEntity]
    else {
      val mongoClient = MongoClient(PropertiesHelper.getMongoUrl, PropertiesHelper.getMongoPort)
      val salatDeviceDao = new SalatDeviceDao(mongoClient, PropertiesHelper.getDatabaseName, PropertiesHelper.getDeviceCollectionName)
      new MongoDeviceDao(salatDeviceDao)
    }
  }

  def getGpsDao: GenericDao[GpsDataEntity, String] = {
    if (PropertiesHelper.useInMemoryDataBase) new InMemoryGenericDao[GpsDataEntity]
    else {
      val mongoClient = MongoClient(PropertiesHelper.getMongoUrl, PropertiesHelper.getMongoPort)
      val salatGpsDataDao = new SalatGpsDataColectionDao(mongoClient, PropertiesHelper.getDatabaseName, PropertiesHelper.getDeviceCollectionName)
      new MongoGpsDao(salatGpsDataDao)
    }
  }

}
