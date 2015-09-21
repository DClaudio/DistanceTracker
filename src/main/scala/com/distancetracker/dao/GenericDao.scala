package com.distancetracker.dao


/**
 * Created by claudio.david on 11/09/2015.
 */
trait GenericDao[T, PK] {

  /** Persist the newInstance object into database */
  def create(item: T): PK

  /** Retrieve an object that was previously persisted to the database using
    * the indicated id as primary key
    */
  def read(id: PK): Option[T]

  /** Save changes made to a persistent object.  */
  def update(id: PK, item: T): Option[T]

  /** Remove an object from persistent storage in the database */
  def delete(id: PK): Unit

}
