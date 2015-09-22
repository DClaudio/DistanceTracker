package com.distancetracker.persistence

import com.mongodb.casbah.Imports.ObjectId

/**
 * Created by claudio.david on 15/09/2015.
 */
case class DeviceEntity(val _id: ObjectId = new ObjectId, val name: String, val email: String)