package com.distancetracker.dao


trait GenericDao[T, PK] {

  /** Persist an item into storage */
  def create(item: T): Option[T]

  /** Retrieve an item from storage */
  def getById(id: PK): Option[T]

  /** Save changes made to an item  */
  def update(item: T): Option[T]

  /** Remove an item from persistent storage */
  def delete(id: PK): Option[T]

}
