package com.distancetracker.model

import com.novus.salat.annotations.raw.Key
import org.bson.types.ObjectId

/**
 * Created by claudio.david on 29/09/2015.
 */
abstract class EntityBase(@Key("_id") key: String = ObjectId.get.toString) {
  var id = key
}
