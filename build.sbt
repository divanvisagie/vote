import com.typesafe.sbt.packager.docker._
import org.flywaydb.sbt.FlywayPlugin.autoImport._
import sbt.Keys._

lazy val versions = new {
  val akka       = "2.4.16"
  val akkaHttp   = "10.0.1"
  val scalatest  = "3.0.1"
  val mockito    = "1.9.5"
  val slick      = "3.2.0"
}


name := "vote"
organization := "com.dvisagie"
version := "1.0"
scalaVersion := "2.11.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

flywayUrl := "jdbc:postgresql://localhost:5432/vote"
flywayUser := "postgres"
flywayPassword := "postgres"
//flywayLocations += "filesystem:database/flyway/sql"

enablePlugins(JavaServerAppPackaging)
packageName in Docker := "divanvisagie/vote"
version in Docker := "1.1.2"
dockerCommands += Cmd("RUN","apk add --no-cache bash")
dockerCommands := Seq(
  Cmd("FROM", "openjdk:alpine"),
  Cmd("WORKDIR", "/opt/docker"),
  Cmd("ADD", "opt /opt"),
  ExecCmd("RUN" , "chown", "-R", "daemon:daemon", "." ),
  ExecCmd("RUN", "apk", "add", "--no-cache", "bash"),
  Cmd("EXPOSE", "5000"),
  ExecCmd("ENTRYPOINT", "bin/vote")
)

libraryDependencies ++=
  Seq(
    "com.typesafe.akka" %% "akka-actor" % versions.akka,
    "com.typesafe.akka" %% "akka-stream" % versions.akka,
    "com.typesafe.akka" %% "akka-testkit" % versions.akka,
    "com.typesafe.akka" %% "akka-http" % versions.akkaHttp,
    "com.typesafe.akka" %% "akka-http-spray-json" % versions.akkaHttp,
    "com.typesafe.akka" %% "akka-http-testkit" % versions.akkaHttp,
    "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
    "com.typesafe.slick" %% "slick" % versions.slick,
    "org.slf4j" % "slf4j-nop" % "1.7.7",

    "org.scalatest"     %% "scalatest" % versions.scalatest % "test"
  )


