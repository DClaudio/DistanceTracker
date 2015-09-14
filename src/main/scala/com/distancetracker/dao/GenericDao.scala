package com.distancetracker.dao


/**
 * Created by claudio.david on 11/09/2015.
 */
trait GenericDao[T, PK] {

  /** Persist the newInstance object into database */
  def create(newInstance: T): PK

  /** Retrieve an object that was previously persisted to the database using
    * the indicated id as primary key
    */
  def read(id: PK): T

  /** Save changes made to a persistent object.  */
  def update(deviceId: PK, transientObject: T): Unit

  /** Remove an object from persistent storage in the database */
  def delete(id: PK): Unit

}
