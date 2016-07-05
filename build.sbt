import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import scalariform.formatter.preferences._

// Resolvers
resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots")
)

// Dependencies
val compilerPlugins = Seq(
  compilerPlugin("org.spire-math" %% "kind-projector" % "0.7.1")
)

val rootDependencies = Seq(
  "io.getquill"   %% "quill-cassandra" % "0.7.1-SNAPSHOT" changing(),
  "org.typelevel" %% "cats"            % "0.6.0"
)

val testDependencies = Seq (
)

val dependencies =
  compilerPlugins ++
  rootDependencies ++
  testDependencies

// Settings
//
val compileSettings = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:_",
  "-unchecked",
  //"-Xfatal-warnings",
  "-Xlint",
  "-Ybackend:GenBCode",
  "-Ydelambdafy:method",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture",
  "-Ywarn-unused-import"
)

val forkedJvmOption = Seq(
  "-server",
  "-Dfile.encoding=UTF8",
  "-Duser.timezone=GMT",
  "-Xss1m",
  "-Xms2048m",
  "-Xmx2048m",
  "-XX:+CMSClassUnloadingEnabled",
  "-XX:ReservedCodeCacheSize=256m",
  "-XX:+DoEscapeAnalysis",
  "-XX:+UseConcMarkSweepGC",
  "-XX:+UseParNewGC",
  "-XX:+UseCodeCacheFlushing",
  "-XX:+UseCompressedOops"
)

val pluginsSettings =
  scalariformSettings

val settings = Seq(
  name := "quill-test",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.8",
  libraryDependencies ++= dependencies,
  fork in run := true,
  fork in Test := true,
  fork in testOnly := true,
  connectInput in run := true,
  javaOptions in run ++= forkedJvmOption,
  javaOptions in Test ++= forkedJvmOption,
  scalacOptions := compileSettings,
  //unmanagedClasspath in Compile += baseDirectory.value / "src" / "main" / "resources",
  // formatting
  //
  ScalariformKeys.preferences := PreferencesImporterExporter.loadPreferences(( file(".") / "formatter.preferences").getPath)
)

val main =
  project
    .in(file("."))
    .settings(
      pluginsSettings ++ settings:_*
    )
