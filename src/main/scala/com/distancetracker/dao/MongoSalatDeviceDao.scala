package com.distancetracker.dao

import com.distancetracker.model.DeviceEntity
import com.mongodb.casbah.Imports.ObjectId
import com.mongodb.casbah.MongoConnection
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._

class MongoSalatDeviceDao
  extends SalatDAO[DeviceEntity, ObjectId](collection = MongoConnection()("distance_tracker")("device"))
