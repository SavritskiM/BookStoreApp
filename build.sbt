name := """BookStoreApp"""
organization := "ca.ycdsb"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean, JavaAppPackaging)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies += jdbc
//libraryDependencies += "com.h2database" % "h2" % "1.4.196"
// libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.2"


herokuAppName in Compile := "salty-river-61698"