import ReleaseTransformations._
import sbt.internal.util.complete.Parser

name := "scala-bump-version-test"

lazy val globalScalaVersion = "2.11.12"
releaseIgnoreUntrackedFiles := true

lazy val global = project
  .in(file("."))
  .settings(
    releaseIgnoreUntrackedFiles := true,
    releaseProcess := Seq[ReleaseStep](
      inquireVersions,
      setNextVersion,
      commitReleaseVersion
    )
  )

commands += Command.command("patch") { state =>
  println("bumping patch!")
  /*Parser.parse(" with-defaults", ReleaseKeys.releaseCommand.parser(state)) match {
    case Right(cmd) =>
      releaseVersionBump := sbtrelease.Version.Bump.Minor
      cmd()
    case Left(msg) => throw sys.error(s"Error triggering release command:\n$msg")
  }
  state*/
  releaseVersionBump := sbtrelease.Version.Bump.Minor
  val newState = Command.process("release with-defaults", state)
  newState
}