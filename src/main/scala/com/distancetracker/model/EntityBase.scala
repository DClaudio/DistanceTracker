package com.distancetracker.model

import com.novus.salat.annotations.raw.Key
import org.bson.types.ObjectId


abstract class EntityBase(@Key("_id") key: String = ObjectId.get.toString) {
  var id = key
}
