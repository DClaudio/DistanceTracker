package com.distancetracker.model

/**
 * Created by claudio.david on 15/09/2015.
 */
case class DeviceEntity(name: String, email: String) extends EntityBase {


  def this(id: String, name: String, email: String){
    this(name, email)
    this.id = id
  }

  def this(device: Device) {
    this(device.name, device.email)
  }

  def this(id: String, device: Device) {
    this(device.name, device.email)
    this.id = id
  }

}