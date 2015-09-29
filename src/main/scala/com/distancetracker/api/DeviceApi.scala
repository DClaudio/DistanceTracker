package com.distancetracker.api

import com.distancetracker.model.{Device, DeviceEntity}
import com.distancetracker.persistence.DataSource
import com.distancetracker.swagger.DeviceApiDescription
import org.scalatra.{Created, NotFound, Ok}
import org.slf4j.LoggerFactory


class DeviceApi(implicit var dataSource: DataSource[DeviceEntity]) extends BaseController with DeviceApiDescription {

  val logger = LoggerFactory.getLogger(getClass)

  post("/device", operation(createNewDeviceOperation)) {
    logger.info("create new device method")
    val createdDevice = dataSource.create(new DeviceEntity(getDeviceFromBody))
    Created(createdDevice)
  }

  get("/device/:deviceId", operation(getDeviceByIdOperation)) {
    logger.info("get device")
    dataSource.getById(getDeviceIdFromUrl) match {
      case None => NotFound("device not found")
      case Some(device) => Ok(device)
    }
  }

  delete("/device/:deviceId", operation(deleteDeviceOperation)) {
    dataSource.delete(getDeviceIdFromUrl) match {
      case None => NotFound("device not found")
      case Some(device) => Ok(device)
    }
  }

  put("/device/:deviceId", operation(updateDeviceOperation)) {
    val device = new DeviceEntity(getDeviceIdFromUrl, getDeviceFromBody)
    dataSource.update(device) match {
      case None => NotFound("device not found")
      case Some(device) => Ok(device)
    }
  }

  get("/", operation(getDeviceListOperation)) {
    dataSource.getAll
  }

  def getDeviceFromBody = parsedBody.extractOrElse[Device](halt(400))

  def getDeviceIdFromUrl = params.getOrElse("deviceId", halt(400))


}
