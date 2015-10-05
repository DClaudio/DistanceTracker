package com.distancetracker.api

import com.distancetracker.dao.GenericDao
import com.distancetracker.model.Device
import com.distancetracker.persistence.{DeviceEntity, GpsDataEntity}
import com.distancetracker.swagger.DeviceApiDescription
import org.scalatra.{Created, NoContent, NotFound, Ok}
import org.slf4j.LoggerFactory


class DeviceApi(implicit var deviceDS: GenericDao[DeviceEntity, String],
                implicit var coordDS: GenericDao[GpsDataEntity, String]) extends BaseController with DeviceApiDescription {

  val logger = LoggerFactory.getLogger(getClass)

  post("/device", operation(createNewDeviceOperation)) {
    logger.info("create new device method")
    val dev = getDeviceFromBody
    val createdDevice = deviceDS.create(new DeviceEntity(name = dev.name, email = dev.email))
    coordDS.create(new GpsDataEntity(createdDevice.get.id, 0, 0))
    Created(createdDevice)
  }

  get("/device/:deviceId", operation(getDeviceByIdOperation)) {
    logger.info("get device")
    deviceDS.getById(getDeviceIdFromUrl) match {
      case None => NotFound("device not found")
      case Some(device) => Ok(device)
    }
  }

  delete("/device/:deviceId", operation(deleteDeviceOperation)) {
    deviceDS.delete(getDeviceIdFromUrl) match {
      case false => NotFound("device not found")
      case true => {
        coordDS.delete(getDeviceIdFromUrl)
        NoContent(reason = "delete succesful")
      }
    }
  }

  put("/device/:deviceId", operation(updateDeviceOperation)) {
    val dev = getDeviceFromBody
    val device = new DeviceEntity(getDeviceIdFromUrl, dev.name, dev.email)
    deviceDS.update(device) match {
      case None => NotFound("device not found")
      case Some(device) => Ok(device)
    }
  }

  get("/", operation(getDeviceListOperation)) {
    Ok(deviceDS.getAll)
  }

  def getDeviceFromBody = parsedBody.extractOrElse[Device](halt(400))

  def getDeviceIdFromUrl = params.getOrElse("deviceId", halt(400))


}
