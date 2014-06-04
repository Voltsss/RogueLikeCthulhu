import AssemblyKeys._

import java.util.Date

name := "roguelike"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "org.scalafx" % "scalafx_2.10" % "8.0.0-R4"
)

jarName in assembly := "roguelike-" + version.value + "_%tY%<tm%<td%<tH%<tM%<tS".format(new Date) + ".jar"

mainClass in assembly := Some("controller.World")

unmanagedResourceDirectories in Compile += { baseDirectory.value / "src/main/resources" }

assemblySettings
