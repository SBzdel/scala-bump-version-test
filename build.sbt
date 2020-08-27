import ReleaseTransformations._
import sbtrelease.Version.Bump._

name := "scala-bump-version-test"

lazy val globalScalaVersion = "2.11.12"

lazy val commonSettings = Seq(
  scalaVersion := globalScalaVersion
)
releaseVersionBump := Major

val bumpVersionPatch = taskKey[Unit]("bump patch version")

lazy val global = project
  .in(file("."))
  .settings(
    bumpVersionPatch := {
      println("bumping patch...")
      releaseVersionBump := Minor
    },
    releaseProcess := Seq[ReleaseStep](
      inquireVersions,
      setNextVersion,
      commitReleaseVersion
    )
  )
  .settings(commonSettings)