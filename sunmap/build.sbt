enablePlugins(ScalaJSPlugin)

name := "Sun Map"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.1",
  "io.surfkit" %%% "scalajs-google-maps" % "0.0.2-SNAPSHOT"
)

jsDependencies ++= Seq(
  ProvidedJS / "CanvasLayer.js",
  ProvidedJS / "CanvasLayerOptions.js"
)
