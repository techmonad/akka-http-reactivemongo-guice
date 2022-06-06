name := "akka-http-reactivemongo-guice"

version := "1.0"

scalaVersion := "2.13.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.2.8",
  "com.typesafe.akka" %% "akka-stream" % "2.6.19",
  "com.typesafe" % "config" % "1.4.2",
  "org.reactivemongo" %% "reactivemongo" % "1.0.10",
  "ch.qos.logback" % "logback-classic" % "1.2.11",
  "net.codingwell" %% "scala-guice" % "5.0.2",
  "org.json4s" %% "json4s-native" % "4.0.5",
  "org.scalatest" %% "scalatest" % "3.2.12" % "test",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.2.8" % Test
)
