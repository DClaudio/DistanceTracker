package com.distancetracker.dao

import com.distancetracker.persistence.{DeviceEntity, GpsDataEntity}
import com.distancetracker.salat.DeviceConversions._
import com.distancetracker.salat.DeviceQueryParams
import com.mongodb.casbah.MongoClient
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._

class MongoDeviceDao(var deviceCollectionDao: SalatDeviceDao) extends GenericDao[DeviceEntity, String] {

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


object MongoGpsDataDao extends SalatDAO[GpsDataEntity, String](collection = MongoClient()("distance_tracker")("coordinates"))


class SalatDeviceDao(val client: MongoClient, databaseName: String, collectionName: String) extends SalatDAO[DeviceEntity, String](client(databaseName)(collectionName))