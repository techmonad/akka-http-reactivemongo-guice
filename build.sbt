name := "akka-http-reactivemongo-guice"

version := "1.0"

scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.2.9",
  "com.typesafe.akka" %% "akka-stream" % "2.6.19",
  "com.typesafe" % "config" % "1.4.2",
  "org.reactivemongo" %% "reactivemongo" % "1.0.10",
  "ch.qos.logback" % "logback-classic" % "1.4.5",
  "net.codingwell" %% "scala-guice" % "5.1.0",
  "org.json4s" %% "json4s-native" % "4.0.6",
  "org.scalatest" %% "scalatest" % "3.2.14" % "test",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.2.9" % Test
)
