package com.distancetracker.util

import com.typesafe.config.{Config, ConfigFactory}

object PropertiesHelper {
  // load application config:
  val config: Config = ConfigFactory.load

  val prefix = "app."

  def useInMemoryDataBase: Boolean = config.getBoolean(prefix + "useInMemDatabase")

  def getMongoPort: Int = config.getInt(prefix + "mongoDB.port")

  def getMongoUrl: String = config.getString(prefix + "mongoDB.url")

  def getDatabaseName: String = config.getString(prefix + "mongoDB.databaseName")

  def getDeviceCollectionName: String = config.getString(prefix + "mongoDB.collectionName.devices")

  def getGpsCollectionName: String = config.getString(prefix + "mongoDB.collectionName.gpsData")
}
