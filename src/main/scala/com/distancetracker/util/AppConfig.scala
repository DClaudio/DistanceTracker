package com.distancetracker.util

import com.typesafe.config.{Config, ConfigFactory}

object ConfigurationHelper {

  val testConfig: Config = ConfigFactory.load("test")
  val appConfig: Config = ConfigFactory.load("application")

  def getTestConfig: AppConfig = {
    new AppConfig(ConfigFactory.defaultOverrides()
      .withFallback(testConfig)
      .withFallback(appConfig)
    )
  }

  def getAppConfig: AppConfig = {
    new AppConfig(appConfig)
  }

}

class AppConfig(val config: Config) {
  val prefix = "app."

  def useInMemoryDataBase: Boolean = config.getBoolean(prefix + "useInMemDatabase")

  def getMongoPort: Int = config.getInt(prefix + "mongoDB.port")

  def getMongoUrl: String = config.getString(prefix + "mongoDB.url")

  def getDatabaseName: String = config.getString(prefix + "mongoDB.databaseName")

  def getDeviceCollectionName: String = config.getString(prefix + "mongoDB.collectionName.devices")

  def getGpsCollectionName: String = config.getString(prefix + "mongoDB.collectionName.gpsData")
}

