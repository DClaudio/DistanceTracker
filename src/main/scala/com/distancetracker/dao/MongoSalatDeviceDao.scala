package com.distancetracker.dao

import com.distancetracker.persistence.DeviceEntity
import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.Imports.ObjectId
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._

/**
 * Created by claudio.david on 22/09/2015.
 */
class MongoSalatDeviceDao
  extends SalatDAO[DeviceEntity, ObjectId](collection = MongoConnection()("distance_tracker")("device"))
