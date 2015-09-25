package com.distancetracker.salat

import com.distancetracker.model.DeviceEntity
import com.mongodb.casbah.Imports._
import com.novus.salat._
import com.novus.salat.global._
/**
 * Created by claudio.david on 22/09/2015.
 */
object DeviceConversions{

  implicit def paramsToDBObject(params: DeviceQueryParams): DBObject =
    grater[DeviceQueryParams].asDBObject(params)

  implicit def deviceToDBObject(device: DeviceEntity): DBObject =
    grater[DeviceEntity].asDBObject(device)

}
