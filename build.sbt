import ReleaseTransformations._

name := "scala-bump-version-test"

lazy val globalScalaVersion = "2.11.12"

lazy val commonSettings = Seq(
  scalaVersion := globalScalaVersion
)

val bumpVersionPatch = taskKey[Unit]("bump patch version")

lazy val global = project
  .in(file("."))
  .settings(
    releaseProcess := Seq[ReleaseStep](
      inquireVersions,
      setNextVersion,
      commitReleaseVersion
    ),
    bumpVersionPatch := {
      println("bumping patch...")
      releaseVersionBump := sbtrelease.Version.Bump.Bugfix
    })
  .settings(commonSettings)