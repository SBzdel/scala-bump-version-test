name := "scala-bump-version-test"

lazy val globalScalaVersion = "2.11.12"

lazy val commonSettings = Seq(
  scalaVersion := globalScalaVersion
)

lazy val global = project
  .in(file("."))
  .settings(name := "scala-bump-version-test")
  .settings(commonSettings)