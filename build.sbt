import ReleaseTransformations._

name := "scala-bump-version-test"

lazy val globalScalaVersion = "2.11.12"

lazy val global = project
  .in(file("."))
  .settings(
    releaseProcess := Seq[ReleaseStep](
      inquireVersions,
      setNextVersion,
      commitReleaseVersion
    )
  )

commands += Command.command("bumpPatch") { state =>
  println("Bumping patch version!")
  val newState = Command.process("'set releaseVersionBump := sbtrelease.Version.Bump.Bugfix' release with-defaults", state)
  //val newState2 = Command.process("release with-defaults", newState)
  newState
}

commands += Command.command("bumpMinor") { state =>
  println("Bumping minor version!")
  val newState = Command.process("set releaseVersionBump := sbtrelease.Version.Bump.Minor", state)
  val newState2 = Command.process("release with-defaults", newState)
  newState2
}

commands += Command.command("bumpMajor") { state =>
  println("Bumping major version!")
  val newState = Command.process("set releaseVersionBump := sbtrelease.Version.Bump.Major", state)
  val newState2 = Command.process("release with-defaults", newState)
  newState2
}