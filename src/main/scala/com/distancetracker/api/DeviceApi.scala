package com.distancetracker.api

import com.distancetracker.model.Device
import com.distancetracker.swagger.DeviceApiDescription
import org.scalatra.{Ok, Created}
import org.slf4j.LoggerFactory


/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApi extends BaseController with DeviceApiDescription {

  val logger =  LoggerFactory.getLogger(getClass)

  post("/device", operation(createNewDeviceOperation)) {
    logger.info("create new device method")
    Created(1L)
  }

  get("/device/:deviceId", operation(devicesDeviceidGetOperation)) {
    logger.info("get device")
    Ok(new Device("n1", "m1"))
  }


}
