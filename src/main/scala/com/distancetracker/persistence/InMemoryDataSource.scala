package com.distancetracker.persistence

import com.distancetracker.model.Device

import scala.collection.concurrent.TrieMap

/**
 * @author claudio
 *  This simulates a persistent storage
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

  def updateDevice(deviceId: Long, device: Device): Option[Device] = {
    database.put(deviceId, device)
  }

  def deleteDevice(deviceId: Long): Option[Device] = {
    database.remove(deviceId)
  }

}