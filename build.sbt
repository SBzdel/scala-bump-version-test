import ReleaseTransformations._

name := "scala-bump-version-test"

lazy val globalScalaVersion = "2.11.12"

lazy val commonSettings = Seq(
  scalaVersion := globalScalaVersion
)

lazy val global = project
  .in(file("."))
  .settings(releaseProcess := Seq[ReleaseStep](
    setNextVersion,
    commitReleaseVersion
  ))
  .settings(commonSettings)