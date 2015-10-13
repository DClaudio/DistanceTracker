package com.distancetracker.salat

case class DeviceQueryParams(_id: Option[String] = None,
                             name: Option[String] = None,
                             email: Option[String] = None)

case class GpsDataQueryParams(_id: Option[String] = None,
                              latitude: Option[Double] = None,
                              longitude: Option[Double] = None)
