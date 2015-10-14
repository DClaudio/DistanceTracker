package com.distancetracker

import de.flapdoodle.embed.mongo.config.{MongodConfigBuilder, Net, RuntimeConfigBuilder}
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.mongo.{Command, MongodExecutable, MongodStarter}
import de.flapdoodle.embed.process.config.IRuntimeConfig
import de.flapdoodle.embed.process.runtime.Network
import org.json4s.{DefaultFormats, Formats}
import org.scalatest._
import org.scalatest.mock.MockitoSugar
import org.scalatra.test.scalatest.ScalatraSuite
import org.slf4j.Logger


abstract class BaseTest extends FlatSpec with BeforeAndAfter with Matchers with BeforeAndAfterAll

abstract class BaseServletTest extends ScalatraSuite with FunSuiteLike with MockitoSugar {

  val jsonContentTypeHeader = Map("Content-Type" -> "application/json")
  implicit val jsonFormats: Formats = DefaultFormats

}

trait EmbeddedMongoTest {
  def getMongoDaemon(mongoDbPort: Int, logger: Logger): MongodExecutable = {
    val runtimeConfig: IRuntimeConfig = new RuntimeConfigBuilder()
      .defaultsWithLogger(Command.MongoD, logger)
      .build()
    val mongodConfig = new MongodConfigBuilder()
      .version(Version.Main.PRODUCTION)
      .net(new Net(mongoDbPort, Network.localhostIsIPv6()))
      .build()
    MongodStarter.getInstance(runtimeConfig).prepare(mongodConfig)
  }
}