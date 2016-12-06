
name := "Akka-streams"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.3.11",
  "com.typesafe.akka" % "akka-stream-experimental_2.11" % "1.0",
  "com.typesafe.akka" % "akka-stream-testkit-experimental_2.11" % "1.0",
  "org.twitter4j" % "twitter4j-stream" % "4.0.3",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.0" % "test"
)