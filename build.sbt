name := "ScalaGraphAlgorithm"

organization := "com.github.wpm.ScalaGraphAlgorithm"

version := "1.0.0"

scalaVersion := "2.10.1"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-language:implicitConversions", "-language:higherKinds")

initialCommands in console := "import com.github.wpm.ScalaGraphAlgorithm._"

libraryDependencies ++= Seq(
    "com.assembla.scala-incubator" % "graph-core_2.10" % "1.6.1",
    "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"
)

