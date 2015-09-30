package com.distancetracker.persistence

import com.distancetracker.model.Entity

import scala.collection.concurrent.TrieMap


class InMemoryDataSource[T <: Entity] extends DataSource[T,String] {

  val database = new TrieMap[String, T]

  override def create(entity: T): Option[T] = {
    database.putIfAbsent(entity.id, entity)
    Some(entity)
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

  override def delete(id: String): Option[T] = {
    database.remove(id)
  }

  override def getAll(): Set[T] = {
    database.values.toSet
  }
}