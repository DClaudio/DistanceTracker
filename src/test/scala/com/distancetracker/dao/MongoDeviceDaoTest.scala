package com.distancetracker.dao

import com.distancetracker.{EmbeddedMongoTest, BaseTest}
import com.distancetracker.persistence.DeviceEntity
import com.distancetracker.util.PropertiesHelper
import com.mongodb.casbah.MongoClient
import de.flapdoodle.embed.mongo.MongodProcess
import org.bson.types.ObjectId
import org.slf4j.LoggerFactory


class MongoDeviceDaoTest extends BaseTest with EmbeddedMongoTest{

  val mongoDbPort: Int = PropertiesHelper.getMongoPort
  val mongoDbUrl: String = PropertiesHelper.getMongoUrl
  val databaseName: String = PropertiesHelper.getDatabaseName
  val deviceCollectionName: String = PropertiesHelper.getDeviceCollectionName

  val deviceCollectionDao = new SalatDeviceDao(MongoClient(mongoDbUrl, mongoDbPort), databaseName, deviceCollectionName)
  val deviceDao: MongoDeviceDao = new MongoDeviceDao(deviceCollectionDao)
  val logger = LoggerFactory.getLogger(getClass)
  val mongoDaemon: MongodProcess = getMongoDaemon(mongoDbPort, logger).start

  override def beforeAll(): Unit = {
    // create the collection
    val db = MongoClient(mongoDbUrl, mongoDbPort).getDB(databaseName)
    db.getCollection(deviceCollectionName)

  }

  override def afterAll(): Unit = {
    // close mongo connection
    deviceDao.deviceCollectionDao.client.close()
    // stop the mongo process
    mongoDaemon.stop
  }


  it should "persist a device" in {
    val device = new DeviceEntity(name = "n1", email = "m1")
    val updatedDevice = new DeviceEntity(device.id, "new", "new")
    //insert a device
    deviceDao.create(device) should be(Some(device))
    //test if it's persisted
    deviceDao.getById(device.id) should be(Some(device))
    //test update
    deviceDao.update(updatedDevice) should be(Some(updatedDevice))
    // test delete
    deviceDao.delete(device.id) should be(true)
    deviceDao.delete(ObjectId.get.toString) should be(false)
  }

  it should "return all devices persisted" in {
    val expectedDeviceList = Set(new DeviceEntity(name = "testName", email = "testMail"),
      new DeviceEntity(name = "testName", email = "testMail"))
    expectedDeviceList.foreach(deviceDao.create)

    deviceDao.getAll should equal(expectedDeviceList)
    // clean up db
    expectedDeviceList.foreach(device => deviceDao.delete(device.id))
  }
}


