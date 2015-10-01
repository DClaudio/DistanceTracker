package com.distancetracker.dao

import com.distancetracker.model.DeviceEntity
import com.distancetracker.persistence.DataSource
import com.distancetracker.salat.DeviceConversions._
import com.distancetracker.salat.DeviceQueryParams
import com.mongodb.casbah.MongoConnection
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._

class MongoSalatDeviceDao extends DataSource[DeviceEntity, String] {

  var deviceCollectionDao = MongoDeviceDao

  override def create(device: DeviceEntity): Option[DeviceEntity] = {
    deviceCollectionDao.insert(device) match {
      case None => None
      case Some(_) => Some(device)
    }
  }

  override def update(device: DeviceEntity): Option[DeviceEntity] = {
    deviceCollectionDao.update(DeviceQueryParams(_id = Some(device.id)), device).getN match {
      case 1 => Some(device)
      case default => None
    }
  }

  override def delete(id: String): Boolean = {
    // if writeResult is 1 => remove was successful
    deviceCollectionDao.removeById(id).getN match {
      case 1 => true
      case default => false
    }
  }

  override def getById(id: String): Option[DeviceEntity] = {
    deviceCollectionDao.findOneById(id)
  }

  override def getAll(): Set[DeviceEntity] = ???
}


object MongoDeviceDao extends SalatDAO[DeviceEntity, String](collection = MongoConnection()("distance_tracker")("device"))