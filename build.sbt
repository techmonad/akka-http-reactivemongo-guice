name := "akka-http-reactivemongo-guice"

version := "1.0"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.2.4",
  "com.typesafe.akka" %% "akka-stream" % "2.6.14",
  "com.typesafe" % "config" % "1.4.1",
  "org.reactivemongo" %% "reactivemongo" % "1.0.3",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "net.codingwell" %% "scala-guice" % "5.0.0",
  "org.json4s" %% "json4s-native" % "3.6.11",
  "org.scalatest" %% "scalatest" % "3.2.8" % "test",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.2.4" % Test
)
