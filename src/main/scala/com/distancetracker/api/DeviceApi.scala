package com.distancetracker.api

import com.distancetracker.dao.DeviceDao
import com.distancetracker.model.{Device, DeviceEntity}
import com.distancetracker.swagger.DeviceApiDescription
import org.scalatra.{Created, NotFound, Ok}
import org.slf4j.LoggerFactory


/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApi(implicit var deviceDao: DeviceDao) extends BaseController with DeviceApiDescription {

  val logger = LoggerFactory.getLogger(getClass)

  post("/device", operation(createNewDeviceOperation)) {
    logger.info("create new device method")
    val device = parsedBody.extract[Device]
    val device2 = deviceDao.create(new DeviceEntity(device))
    Created(device2)
  }

  get("/device/:deviceId", operation(getDeviceByIdOperation)) {
    logger.info("get device")
    val deviceId = params.getOrElse("deviceId", halt(400))
    deviceDao.read(deviceId) match {
      case None => NotFound("device not found")
      case Some(device) => Ok(device)
    }
  }

  delete("/device/:deviceId", operation(deleteDeviceOperation)){
    val deviceId = params.getOrElse("deviceId", halt(400))
    deviceDao.delete(deviceId) match {
      case None => NotFound("device not found")
      case Some(device) => Ok(device)
    }
  }

  put("/device/:deviceId", operation(updateDeviceOperation)){
    val deviceId = params.getOrElse("deviceId", halt(400))
    val dev = new DeviceEntity(deviceId, parsedBody.extract[Device])
    deviceDao.update(dev) match {
      case None => NotFound("device not found")
      case Some(device) => Ok(device)
    }
  }


}
