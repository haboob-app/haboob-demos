import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1"
  lazy val yaml = "net.jcazevedo" %% "moultingyaml" % "0.4.0"
  lazy val ws = "com.typesafe.play" % "play-ws_2.11" % "2.5.13"
  lazy val json = "com.typesafe.play" %% "play-json" % "2.5.13"
  lazy val akka = "com.typesafe.akka" %% "akka-actor" % "2.4.17"
}
