import com.typesafe.sbt.packager.docker._

name := "vote"
organization := "com.dvisagie"
version := "1.0"
scalaVersion := "2.11.8"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
enablePlugins(JavaAppPackaging)

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

libraryDependencies ++= {
  val akkaV       = "2.4.16"
  val akkaHttpV   = "10.0.1"
  val scalaTestV  = "3.0.1"
  val slickV      = "3.2.0"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV,
    "com.typesafe.akka" %% "akka-http" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpV,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpV,
    "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
    "com.typesafe.slick" %% "slick" % slickV,
    "org.slf4j" % "slf4j-nop" % "1.7.7",

    "org.scalatest"     %% "scalatest" % scalaTestV % "test"
  )
}

