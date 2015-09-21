package com.distancetracker.persistence

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

  def getDevice(deviceId: Long): Option[Device] = {
    database.get(deviceId)
  }

  def update(deviceId: Long, device: Device): Option[Device] = {
    database.put(deviceId, device)
  }

  def delete(deviceId: Long): Unit = {
    database.remove(deviceId)
  }

}