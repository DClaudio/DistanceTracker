package com.distancetracker.api

import com.distancetracker.model.Device
import org.scalatra.{Created, Ok, ScalatraServlet}

/**
 * Created by claudio.david on 14/09/2015.
 */
class DeviceApi extends ScalatraServlet{


  post("/device"){
    Created
  }

  get("/device/:deviceId"){
    Ok(new Device("n1", "m1"))
  }



}
