import ReleaseTransformations._
import sbt.internal.util.complete.Parser
import sbtrelease.Version.Bump._

name := "scala-bump-version-test"

lazy val globalScalaVersion = "2.11.12"

lazy val commonSettings = Seq(
  scalaVersion := globalScalaVersion
)

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
    ),
    commands += Command.command("patch") { state =>
      println("bumping patch!")

      Parser.parse("", ReleaseKeys.releaseCommand.parser(state)) match {
        case Right(cmd) =>
          releaseVersionBump := sbtrelease.Version.Bump.Minor
          cmd()
        case Left(msg) => throw sys.error(s"Error triggering release command:\n$msg")
      }
      state
    }
  )
  .settings(commonSettings)