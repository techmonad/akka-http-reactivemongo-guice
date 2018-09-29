name := "akka-http-reactivemongo-guice"

version := "1.0"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.5",
  "com.typesafe.akka" %% "akka-stream" % "2.5.13",
  "com.typesafe" % "config" % "1.3.3",
  "org.reactivemongo" %% "reactivemongo" % "0.16.0",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "net.codingwell" %% "scala-guice" % "4.2.1",
  "org.json4s" %% "json4s-native" % "3.5.4",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.5" % Test
)
