// Comment to get more information during initialization
//
logLevel := Level.Warn

// Resolvers
//

resolvers += "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
      
resolvers += "Softprops Maven" at "http://dl.bintray.com/content/softprops/maven"

// Assembly
//
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")

// Build Info
//
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.3.2")

// Releases
//
addSbtPlugin("com.github.gseitz" % "sbt-release" % "0.8.3")

// Scalariform
//
addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.3.0")
