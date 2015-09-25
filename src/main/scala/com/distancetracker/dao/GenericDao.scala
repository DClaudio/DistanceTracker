package com.distancetracker.dao


/**
 * Created by claudio.david on 11/09/2015.
 */
trait GenericDao[T, PK] {

  /** Persist an item into storage */
  def create(item: T): Option[T]

  /** Retrieve an item from storage */
  def read(id: PK): Option[T]

  /** Save changes made to an item  */
  def update(id: PK, item: T): Option[T]

  /** Remove an item from persistent storage */
  def delete(id: PK): Option[T]

}
