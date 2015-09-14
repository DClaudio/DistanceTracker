name := "intellij-sbt-tst"

version := "1.0"

scalaVersion := "2.11.6"

//enable debug
javaOptions in container ++= Seq(
  "-Xdebug",
  "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8888"
)