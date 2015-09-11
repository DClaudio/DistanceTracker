package com.test.api

import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json.NativeJsonSupport
import org.scalatra.{Created, NotFound, ScalatraServlet}

/**
 * Created by claudio.daclsvid on 11/09/2015.
 */
class TestApi extends ScalatraServlet with NativeJsonSupport{

  protected implicit val jsonFormats: Formats = DefaultFormats

  before(){
    contentType = formats("json")
    response.headers += ("Access-Control-Allow-Origin" -> "*")
  }

  get("/tst"){
    "success"
  }

  get("/t1"){
    NotFound("resource not found")
  }

  get("/t2"){
    val pers = Person(13L, "Gigel", 13L)
    Created(pers)
  }

}
