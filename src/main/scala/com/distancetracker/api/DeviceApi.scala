package com.distancetracker.api

import com.distancetracker.dao.{DeviceDao, MongoSalatDeviceDao}
import com.distancetracker.model.{Device, DeviceEntity}
import com.distancetracker.swagger.DeviceApiDescription
import org.scalatra.{Ok, Created}
import org.slf4j.LoggerFactory
import com.mongodb.casbah.Imports.ObjectId


/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApi(implicit val deviceDao: DeviceDao) extends BaseController with DeviceApiDescription {

  val logger =  LoggerFactory.getLogger(getClass)

  post("/device", operation(createNewDeviceOperation)) {
    logger.info("create new device method")
    val device = parsedBody.extract[Device]
    val id = deviceDao.create(new DeviceEntity(new ObjectId, device.name, device.email))
    Created(id)
  }

  get("/device/:deviceId", operation(devicesDeviceidGetOperation)) {
    logger.info("get device")
    val deviceId = params.get("deviceId")
    Ok(new DeviceEntity(new ObjectId,"n1", "m1"))
  }


}
