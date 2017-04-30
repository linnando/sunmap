enablePlugins(ScalaJSPlugin)

name := "Sun Map"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.1",
  "io.surfkit" %%% "scalajs-google-maps" % "0.0.2-SNAPSHOT",
  "org.webjars" % "google-maps-canvas-layer" % "20150622-13ce11d"
)

jsDependencies ++= Seq(
  "org.webjars" % "google-maps-canvas-layer" % "20150622-13ce11d" / "20150622-13ce11d/CanvasLayer.js",
  "org.webjars" % "google-maps-canvas-layer" % "20150622-13ce11d" / "20150622-13ce11d/CanvasLayerOptions.js"
)
