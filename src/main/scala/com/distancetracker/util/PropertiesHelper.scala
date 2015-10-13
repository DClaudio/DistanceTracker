package com.distancetracker.util

import com.typesafe.config.{Config, ConfigFactory}

object PropertiesHelper {
  // load application config:
  val config: Config = ConfigFactory.load

  def useInMemoryDataBase: Boolean = config.getBoolean("app.useInMemDatabase")

  def getMongoPort: Int = config.getInt("mongoDB.port")

  def getMongoUrl: String = config.getString("mongoDB.url")

  def getDatabaseName: String = config.getString("mongoDB.databaseName")

  def getDeviceCollectionName: String = config.getString("mongoDB.collectionName.devices")

  def getGpsCollectionName: String = config.getString("mongoDB.collectionName.gpsData")
}
