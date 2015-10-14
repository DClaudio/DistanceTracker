package com.distancetracker.dao

import com.distancetracker.dao.DeviceConversions._
import com.distancetracker.persistence.DeviceEntity

class MongoDeviceDao(var deviceCollectionDao: SalatDeviceColletionDao) extends GenericDao[DeviceEntity, String] {

  override def create(device: DeviceEntity): Option[DeviceEntity] = {
    deviceCollectionDao.insert(device) match {
      case None => None
      case Some(_) => Some(device)
    }
  }

  override def update(device: DeviceEntity): Option[DeviceEntity] = {
    // if writeResult is 1 => update was successful
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

  override def getAll(): Set[DeviceEntity] = {
    val mongoCursor = deviceCollectionDao.find(new DeviceQueryParams)
    mongoCursor.toSet
  }
}