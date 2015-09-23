package com.distancetracker.salat

import org.bson.types.ObjectId

/**
 * Created by claudio.david on 22/09/2015.
 */
case class DeviceQueryParams(_id: Option[ObjectId] = None,
                        name: Option[String] = None,
                        email: Option[String] = None)
