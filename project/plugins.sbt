addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.3.5")
addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.0-M15")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.2.0-M8")


addSbtPlugin("org.flywaydb" % "flyway-sbt" % "4.1.2")

resolvers += "Flyway" at "https://flywaydb.org/repo"