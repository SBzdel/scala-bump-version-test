import ReleaseTransformations._

name := "scala-bump-version-test"

lazy val globalScalaVersion = "2.11.12"

lazy val global = project
  .in(file("."))
  .settings()

commands += Command.command("bumpPatch") { state =>
  println("Bumping patch version!")
  releaseProcess := Seq[ReleaseStep](
    inquireVersions,
    setNextVersion,
    commitReleaseVersion
  )
  val stateWithPartSet = 
    Command.process("set releaseVersionBump := sbtrelease.Version.Bump.Bugfix", state)
  val newState = Command.process("release with-defaults", stateWithPartSet)
  newState
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