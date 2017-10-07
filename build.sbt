name := "calculator"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.11"

resolvers += "Calculator Repo" at "https://github.com/plokhotnyuk/calculator/raw/master/repo"

libraryDependencies ++= Seq(
  "com.googlecode.windowlicker" % "windowlicker-core" % "268" % "test",
  "com.googlecode.windowlicker" % "windowlicker-swing" % "268" % "test",
  "org.objenesis" % "objenesis" % "1.0" % "test",
  "org.specs2" %% "specs2" % "2.3.13" % "test",
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
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Xfuture"
)

parallelExecution in Test := false

assemblyJarName in assembly := "calculator.jar"
