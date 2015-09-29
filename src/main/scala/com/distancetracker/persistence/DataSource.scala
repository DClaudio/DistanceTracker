package com.distancetracker.persistence

import com.distancetracker.model.Entity


trait DataSource[T <: Entity] {

  def create(device: T): Option[T]

  def getById(id: String): Option[T]

  def update(entity: T): Option[T]

  def delete(id: String): Option[T]

  def getAll(): Set[T]

}