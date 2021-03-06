name := "FElite"

version := "0.1"

scalaVersion := "2.13.0"

coverageExcludedPackages := "<empty>;.*gui.*;.*FElite" //exclude packages from coverage

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"

libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.9"
libraryDependencies += "org.apache.commons" % "commons-io" % "1.3.2"
libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.13" % "2.1.1"

libraryDependencies += "com.google.inject" % "guice" % "4.2.2"
libraryDependencies += "net.codingwell" %% "scala-guice" % "4.2.6"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.2.0"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.8.1"


// scalacOptions += "-deprecation" //display deprecation warnings