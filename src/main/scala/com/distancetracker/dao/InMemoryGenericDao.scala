package com.distancetracker.dao


import com.distancetracker.persistence.Entity

import scala.collection.concurrent.TrieMap


class InMemoryGenericDao[T <: Entity] extends GenericDao[T, String] {

  val database = new TrieMap[String, T]

  override def create(entity: T): Option[T] = {
    database.putIfAbsent(entity.id, entity) match {
      case None => Some(entity)
      case Some(_) => None
    }
  }

  override def getById(id: String): Option[T] = {
    database.get(id)
  }

  override def update(entity: T): Option[T] = {
    database.replace(entity.id, entity) match {
      case Some(_) => Some(entity)
      case None => None
    }
  }

  override def delete(id: String): Boolean = {
    database.remove(id) match {
      case None => false
      case Some(_) => true
    }
  }

  override def getAll(): Set[T] = {
    database.values.toSet
  }
}