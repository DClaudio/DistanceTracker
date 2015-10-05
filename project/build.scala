import org.scalatra.sbt._
import sbt.Keys._
import sbt._

object MyScalatraWebAppBuild extends Build {
  val Organization = "com.example"
  val Name = "Distance Tracker API"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.6"
  val ScalatraVersion = "2.3.0"

  lazy val project = Project(
    "distancetracker-api",
    file("."),
    settings = ScalatraPlugin.scalatraWithJRebel ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-json" % ScalatraVersion,
        "org.json4s" %% "json4s-native" % "3.2.10",
        "org.json4s"   %% "json4s-jackson" % "3.2.10",
        "org.mongodb" %% "casbah" % "2.7.2",
        "com.novus" %% "salat" % "1.9.9",
        "org.eclipse.jetty" % "jetty-webapp" % "9.1.5.v20140505" % "container",
        "org.eclipse.jetty" % "jetty-plus" % "9.1.5.v20140505" % "container",
        "javax.servlet" % "javax.servlet-api" % "3.1.0",
        "org.scalatra" %% "scalatra-swagger" % ScalatraVersion,
        "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
        "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
        "org.mockito" % "mockito-core" % "1.9.5" % "test",
        "de.flapdoodle.embed" % "de.flapdoodle.embed.mongo" % "1.50.0" % "test",
        "ch.qos.logback" % "logback-classic" % "1.1.1" % "runtime"
      )
    )
  )
}
