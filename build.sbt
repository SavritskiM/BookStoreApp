name := """BookStoreApp"""
organization := "ca.ycdsb"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies += "com.h2database" % "h2" % "1.4.192"
libraryDependencies += javaJdbc


herokuAppName in Compile := "aqueous-plateau-24952"