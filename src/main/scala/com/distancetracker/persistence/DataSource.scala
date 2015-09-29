package com.distancetracker.persistence

import com.distancetracker.model.EntityBase


/**
 * @author claudio
 */
trait DataSource[T <: EntityBase] {

  def create(device: T): Option[T]

  def get(id: String): Option[T]

  def update(entity: T): Option[T]

  def delete(id: String): Option[T]

  def getAll(): Set[T]

}