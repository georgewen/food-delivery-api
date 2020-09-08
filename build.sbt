name := """drop-bear-eats"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, SwaggerPlugin)

swaggerDomainNameSpaces := Seq("models")
cancelable in Global := true
swaggerPrettyJson := true
swaggerPlayJava := true
scalaVersion := "2.13.3"

//libraryDependencies += guice

libraryDependencies ++= Seq(
  guice,
  "org.mongodb" % "mongodb-driver-sync" % "4.1.0",
  "dev.morphia.morphia" % "core" % "1.6.0",
 // "io.swagger" %% "swagger-play2" % "1.7.1",
  "org.webjars" % "swagger-ui" % "2.2.0"
)
