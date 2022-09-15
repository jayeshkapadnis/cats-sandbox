name := "cats-sandbox"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.6.1",
  "com.github.julien-truffaut" %% "monocle-core"  % "3.1.0",
  "com.github.julien-truffaut" %% "monocle-macro" % "3.1.0"
)

// scalac options come from the sbt-tpolecat plugin so need to set any here

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.13.0" cross CrossVersion.full)
