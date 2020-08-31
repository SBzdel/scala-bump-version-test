import ReleaseTransformations._

name := "scala-bump-version-test"

lazy val globalScalaVersion = "2.11.12"

lazy val global = project
  .in(file("."))

val relProcessForBump = Seq[ReleaseStep](
  inquireVersions,
  setNextVersion,
  commitReleaseVersion
)

commands += Command.command("bumpPatch") { state =>
  println("Bumping patch version!")
  val extracted = Project extract state
  val customState = extracted.appendWithoutSession(
    Seq(
      releaseProcess := relProcessForBump,
      releaseVersionBump := sbtrelease.Version.Bump.Bugfix
    ), state)
  Command.process("release with-defaults", customState)
}

commands += Command.command("bumpMinor") { state =>
  println("Bumping minor version!")
  val stateWithPartSet =
    Command.process("set releaseVersionBump := sbtrelease.Version.Bump.Minor", state)
  val newState = Command.process("release with-defaults", stateWithPartSet)
  newState
}

commands += Command.command("bumpMajor") { state =>
  println("Bumping major version!")
  val stateWithPartSet =
    Command.process("set releaseVersionBump := sbtrelease.Version.Bump.Major", state)
  val newState = Command.process("release with-defaults", stateWithPartSet)
  newState
}