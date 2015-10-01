package com.distancetracker.dao

import com.distancetracker.model.Entity


trait GenericDao[T <: Entity,K] {

  def create(entity: T): Option[T]

  def getById(id: K): Option[T]

  def update(entity: T): Option[T]

  def delete(id: K): Boolean

  def getAll(): Set[T]

}