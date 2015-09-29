package com.distancetracker.model

case class DeviceEntity(name: String, email: String) extends Entity {

  def this(id: String, name: String, email: String) {
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