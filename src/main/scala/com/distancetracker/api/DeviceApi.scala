package com.distancetracker.api

import com.distancetracker.dao.MongoSalatDeviceDao
import com.distancetracker.model.Device
import com.distancetracker.persistence.DeviceEntity
import com.distancetracker.swagger.DeviceApiDescription
import org.scalatra.{Ok, Created}
import org.slf4j.LoggerFactory
import com.mongodb.casbah.Imports.ObjectId


/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApi() extends BaseController with DeviceApiDescription {

  val logger =  LoggerFactory.getLogger(getClass)
  val deviceDao = new MongoSalatDeviceDao

  post("/device", operation(createNewDeviceOperation)) {
    logger.info("create new device method")
    val device = parsedBody.extract[DeviceEntity]
    deviceDao.insert(device)
    Created(device)
  }

  get("/device/:deviceId", operation(devicesDeviceidGetOperation)) {
    logger.info("get device")
    val deviceId = params.get("deviceId")
    val device = deviceDao.findOneById(new ObjectId)
    Ok(new DeviceEntity(new ObjectId,"n1", "m1"))
  }


}
