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
    val createdDevice = deviceDao.create(new DeviceEntity(getDeviceFromBody))
    Created(createdDevice)
  }

  get("/device/:deviceId", operation(getDeviceByIdOperation)) {
    logger.info("get device")
    deviceDao.read(getDeviceIdFromUrl) match {
      case None => NotFound("device not found")
      case Some(device) => Ok(device)
    }
  }

  delete("/device/:deviceId", operation(deleteDeviceOperation)){
    deviceDao.delete(getDeviceIdFromUrl) match {
      case None => NotFound("device not found")
      case Some(device) => Ok(device)
    }
  }

  put("/device/:deviceId", operation(updateDeviceOperation)){
    val device = new DeviceEntity(getDeviceIdFromUrl, getDeviceFromBody)
    deviceDao.update(device) match {
      case None => NotFound("device not found")
      case Some(device) => Ok(device)
    }
  }

  def getDeviceFromBody = parsedBody.extractOrElse[Device](halt(400))
  def getDeviceIdFromUrl = params.getOrElse("deviceId", halt(400))


}
