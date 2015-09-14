package com.distancetracker.api

import com.distancetracker.model.Device
import com.distancetracker.swagger.DeviceApiDescription
import org.scalatra.{Ok, Created}


/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApi extends BaseController with DeviceApiDescription {

  post("/device", operation(createNewDeviceOperation)) {
    Created(1L)
  }

  get("/device/:deviceId", operation(devicesDeviceidGetOperation)) {
    Ok(new Device("n1", "m1"))
  }


}
