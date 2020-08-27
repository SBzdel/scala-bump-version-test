import ReleaseTransformations._

name := "scala-bump-version-test"

lazy val globalScalaVersion = "2.11.12"

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

commands += Command.command("bumpPatch") { state =>
  println("Bumping patch version!")
  releaseVersionBump := sbtrelease.Version.Bump.Minor
  val newState = Command.process("set releaseVersionBump := sbtrelease.Version.Bump.Bugfix", state)
  val newState2 = Command.process("release with-defaults;", newState)
  newState2
}

commands += Command.command("bumpMinor") { state =>
  println("Bumping minor version!")
  releaseVersionBump := sbtrelease.Version.Bump.Minor
  val newState = Command.process("set releaseVersionBump := sbtrelease.Version.Bump.Minor", state)
  val newState2 = Command.process("release with-defaults;", newState)
  newState2
}

commands += Command.command("bumpMajor") { state =>
  println("Bumping major version!")
  releaseVersionBump := sbtrelease.Version.Bump.Minor
  val newState = Command.process("set releaseVersionBump := sbtrelease.Version.Bump.Major", state)
  val newState2 = Command.process("release with-defaults;", newState)
  newState2
}