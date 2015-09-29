package com.distancetracker.api

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.ScalatraServlet
import org.scalatra.json.NativeJsonSupport

abstract class BaseController extends ScalatraServlet with NativeJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    response.headers += ("Access-Control-Allow-Origin" -> "*")
    contentType += formats("json")
  }

}
