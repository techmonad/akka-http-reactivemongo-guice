name := "akka-http-reactivemongo-guice"

version := "1.0"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.14",
  "com.typesafe.akka" %% "akka-stream" % "2.5.32",
  "com.typesafe" % "config" % "1.3.4",
  "org.reactivemongo" %% "reactivemongo" % "0.16.6",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "net.codingwell" %% "scala-guice" % "4.2.11",
  "org.json4s" %% "json4s-native" % "3.5.5",
  "org.scalatest" %% "scalatest" % "3.0.9" % "test",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.14" % Test
)
