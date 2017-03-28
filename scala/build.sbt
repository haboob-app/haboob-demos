import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "co.haboob",
      scalaVersion := "2.11.8",
      version      := "0.1.0"
    )),
    name := "HaboobDemo",
    resolvers ++= Seq(
      Resolver.defaultLocal,
      Resolver.mavenLocal,
      Resolver.typesafeRepo("releases")
    ),
    libraryDependencies ++= Seq(
      scalaTest % Test, yaml, ws, json, akka
    )
  )
