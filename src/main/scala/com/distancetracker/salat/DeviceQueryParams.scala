package com.distancetracker.salat

import org.bson.types.ObjectId

/**
 * Created by claudio.david on 22/09/2015.
 */
class DeviceQueryParams(val _id: Option[ObjectId] = None,
                        val name: Option[String] = None,
                        val email: Option[String] = None)
