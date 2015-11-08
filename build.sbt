import AssemblyKeys._

import java.util.Date

name := "roguelike"

scalaVersion := "2.11.7"

scalacOptions in Compile ++= Seq(
  "-deprecation",
  "-feature",
  "-Xlint",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused-import",
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:implicitConversions"
)

libraryDependencies ++= Seq(
  "org.scalafx"                 %% "scalafx"       % "8.0.0-R4",
  "com.github.julien-truffaut"  %% "monocle-core"  % "1.2.0-M1",
  "com.github.julien-truffaut"  %% "monocle-macro" % "1.2.0-M1",
  "org.scalaz"                  %% "scalaz-core"   % "7.1.4"
)

jarName in assembly := "roguelike-" + version.value + ".jar"

mainClass in assembly := Some("roguelike.controller.World")

assemblySettings

unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/ext/jfxrt.jar"))
