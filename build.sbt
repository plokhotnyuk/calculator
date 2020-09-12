name := "calculator"

version := "1.0-SNAPSHOT"

scalaVersion := "2.13.3"

crossScalaVersions := Seq("2.13.3", "2.12.12", "2.11.12")

resolvers += "Calculator Repo" at "https://github.com/plokhotnyuk/calculator/raw/master/repo"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.0" % "test",
  "com.googlecode.windowlicker" % "windowlicker-core" % "268" % "test",
  "com.googlecode.windowlicker" % "windowlicker-swing" % "268" % "test",
  "org.objenesis" % "objenesis" % "1.0" % "test",
  "org.hamcrest" % "hamcrest-core" % "1.3" % "test",
  "org.hamcrest" % "hamcrest-library" % "1.3" % "test",
  "junit" % "junit-dep" % "4.10" % "test"
)

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code"
)

parallelExecution in Test := false

concurrentRestrictions in Test += Tags.limit(Tags.Test, 1)

assemblyJarName in assembly := "calculator.jar"
