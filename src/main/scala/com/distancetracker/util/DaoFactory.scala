package com.distancetracker.util

import com.distancetracker.dao._
import com.distancetracker.persistence.{DeviceEntity, GpsDataEntity}
import com.mongodb.casbah.MongoClient

object DaoFactory {

  val mongoClients: List[MongoClient] = null
  val config = ConfigurationHelper.getAppConfig

  def getMongoClients = mongoClients

  def getDeviceDao: GenericDao[DeviceEntity, String] = {
    if (config.useInMemoryDataBase) new InMemoryGenericDao[DeviceEntity]
    else {
      val mongoClient = MongoClient(config.getMongoUrl, config.getMongoPort)
      val salatDeviceDao = new SalatDeviceColletionDao(mongoClient, config.getDatabaseName, config.getDeviceCollectionName)
      new MongoDeviceDao(salatDeviceDao)
    }
  }

  def getGpsDao: GenericDao[GpsDataEntity, String] = {
    if (config.useInMemoryDataBase) new InMemoryGenericDao[GpsDataEntity]
    else {
      val mongoClient = MongoClient(config.getMongoUrl, config.getMongoPort)
      val salatGpsDataDao = new SalatGpsDataColectionDao(mongoClient, config.getDatabaseName, config.getDeviceCollectionName)
      new MongoGpsDao(salatGpsDataDao)
    }
  }

}
