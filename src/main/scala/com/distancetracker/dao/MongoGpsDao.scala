package com.distancetracker.dao

import com.distancetracker.persistence.GpsDataEntity
import com.distancetracker.salat.GpsDataConversions._
import com.distancetracker.salat.GpsDataQueryParams
import com.mongodb.casbah.MongoClient
import com.novus.salat.dao.SalatDAO
import com.novus.salat.global._

class MongoGpsDao(var gpsDataCollection: SalatGpsDataColectionDao) extends GenericDao[GpsDataEntity, String] {

  override def create(gpsData: GpsDataEntity): Option[GpsDataEntity] = {
    gpsDataCollection.insert(gpsData) match {
      case None => None
      case Some(_) => Some(gpsData)
    }
  }

  override def update(gpsData: GpsDataEntity): Option[GpsDataEntity] = {
    // if writeResult is 1 => update was successful
    gpsDataCollection.update(GpsDataQueryParams(_id = Some(gpsData.id)), gpsData).getN match {
      case 1 => Some(gpsData)
      case default => None
    }
  }

  override def delete(id: String): Boolean = {
    // if writeResult is 1 => remove was successful
    gpsDataCollection.removeById(id).getN match {
      case 1 => true
      case default => false
    }
  }

  override def getById(id: String): Option[GpsDataEntity] = {
    gpsDataCollection.findOneById(id)
  }

  override def getAll(): Set[GpsDataEntity] = {
    val mongoCursor = gpsDataCollection.find(new GpsDataQueryParams)
    mongoCursor.toSet
  }
}

class SalatGpsDataColectionDao(val client: MongoClient, databaseName: String, collectionName: String) extends SalatDAO[GpsDataEntity, String](client(databaseName)(collectionName))

