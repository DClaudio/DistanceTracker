package com.distancetracker.persistence


import com.distancetracker.dao.EntityNotFoundException
import com.distancetracker.model.Device

import scala.collection.concurrent.TrieMap

/**
 * @author claudio
 */
object InMemoryDataSource extends DataSource {

  val database = new TrieMap[Long, Device]
  val rng = scala.util.Random

  def createNewDevice(device: Device): Long = {
    val deviceId = rng.nextLong()
    database.put(deviceId, device)
    deviceId
  }

  def getDevice(deviceId: Long): Device = {
    database.get(deviceId) match {
      case Some(device) => device
      case None => throw new EntityNotFoundException("device doesn't exist in database")
    }
  }

  def update(deviceId: Long, device: Device): Unit = {
    database.put(deviceId, device) match {
      case Some(_) =>
      case None => throw new EntityNotFoundException("Can't update device, ID not found")
    }
  }

  def delete(deviceId: Long): Unit = {
    database.remove(deviceId)
  }

}