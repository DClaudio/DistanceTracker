package com.distancetracker.dao

import com.distancetracker.persistence.DeviceEntity
import com.mongodb.casbah.MongoClient
import de.flapdoodle.embed.mongo.config.{MongodConfigBuilder, Net}
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.mongo.{MongodExecutable, MongodProcess, MongodStarter}
import de.flapdoodle.embed.process.runtime.Network
import org.bson.types.ObjectId


class MongoDeviceDaoTest extends BaseTest {

  val mongoDbPort: Int = 12345;
  val mongoDbUrl: String = "localhost";
  val databaseName: String = "distance_tracker"
  val collectionName: String = "device"
  val deviceCollectionDao = new SalatDeviceDao(MongoClient(mongoDbUrl, mongoDbPort), databaseName, collectionName)
  val deviceDao: MongoDeviceDao = new MongoDeviceDao(deviceCollectionDao)
  var mongoD: MongodProcess = null
  var mongodExe: MongodExecutable = null

  override def beforeAll(): Unit = {
    val mongodConfig = new MongodConfigBuilder()
      .version(Version.Main.PRODUCTION)
      .net(new Net(mongoDbPort, Network.localhostIsIPv6()))
      .build()
    mongodExe = MongodStarter.getDefaultInstance.prepare(mongodConfig)
    mongoD = mongodExe.start

    val mongo = MongoClient(mongoDbUrl, mongoDbPort)
    val db = mongo.getDB(databaseName)
    db.getCollection(collectionName)

  }

  override def afterAll(): Unit = {
    // close mongo connection
    deviceDao.deviceCollectionDao.client.close()
    mongoD.stop
    mongodExe.stop
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


