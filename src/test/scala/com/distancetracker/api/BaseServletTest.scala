package com.distancetracker.api

import org.json4s.{DefaultFormats, Formats}
import org.scalatest.FunSuiteLike
import org.scalatest.mock.MockitoSugar
import org.scalatra.test.scalatest.ScalatraSuite

/**
 * Created by claudio.david on 28/09/2015.
 */
abstract class BaseServletTest extends ScalatraSuite with FunSuiteLike with MockitoSugar {

  val jsonContentTypeHeader = Map("Content-Type" -> "application/json")
  implicit val jsonFormats: Formats = DefaultFormats

}
