package com.distancetracker.salat

import com.distancetracker.persistence.{GpsDataEntity, DeviceEntity}
import com.mongodb.casbah.Imports._
import com.novus.salat._
import com.novus.salat.global._

object DeviceConversions{

  implicit def paramsToDBObject(params: DeviceQueryParams): DBObject =
    grater[DeviceQueryParams].asDBObject(params)

  implicit def deviceToDBObject(device: DeviceEntity): DBObject =
    grater[DeviceEntity].asDBObject(device)

}


object GpsDataConversions{

  implicit def paramsToDBObject(params: GpsDataQueryParams): DBObject =
    grater[GpsDataQueryParams].asDBObject(params)

  implicit def deviceToDBObject(device: GpsDataEntity): DBObject =
    grater[GpsDataEntity].asDBObject(device)

}