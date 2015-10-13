package com.distancetracker.dao

import com.typesafe.config.{Config, ConfigFactory}
import de.flapdoodle.embed.mongo.config.{MongodConfigBuilder, Net, RuntimeConfigBuilder}
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.mongo.{Command, MongodExecutable, MongodStarter}
import de.flapdoodle.embed.process.config.IRuntimeConfig
import de.flapdoodle.embed.process.runtime.Network
import org.scalatest.{BeforeAndAfter, BeforeAndAfterAll, FlatSpec, Matchers}
import org.slf4j.Logger


abstract class BaseTest extends FlatSpec with BeforeAndAfter with Matchers with BeforeAndAfterAll {
  
  val config: Config = ConfigFactory.load

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