package com.distancetracker.dao

import com.distancetracker.persistence.{DeviceEntity, GpsDataEntity}
import com.mongodb.casbah.Imports._
import com.mongodb.casbah.MongoClient
import com.novus.salat._
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._


class SalatDeviceColletionDao(val client: MongoClient, databaseName: String, collectionName: String)
  extends SalatDAO[DeviceEntity, String](client(databaseName)(collectionName))

class SalatGpsDataCollectionDao(val client: MongoClient, databaseName: String, collectionName: String)
  extends SalatDAO[GpsDataEntity, String](client(databaseName)(collectionName))


object DeviceConversions {
  implicit def paramsToDBObject(params: DeviceQueryParams): DBObject =
    grater[DeviceQueryParams].asDBObject(params)

  implicit def deviceToDBObject(device: DeviceEntity): DBObject =
    grater[DeviceEntity].asDBObject(device)
}

object GpsDataConversions {
  implicit def paramsToDBObject(params: GpsDataQueryParams): DBObject =
    grater[GpsDataQueryParams].asDBObject(params)

  implicit def deviceToDBObject(device: GpsDataEntity): DBObject =
    grater[GpsDataEntity].asDBObject(device)
}

case class DeviceQueryParams(_id: Option[String] = None, name: Option[String] = None, email: Option[String] = None)

case class GpsDataQueryParams(_id: Option[String] = None, latitude: Option[Double] = None, longitude: Option[Double] = None)
