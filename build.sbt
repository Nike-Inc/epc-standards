/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
import sbt.Keys.javacOptions

// This allows coursier to work as an alternative resolver. See:
// https://github.com/maichler/sbt-jupiter-interface/tree/0.7.0#usage
resolvers in ThisBuild += Resolver.jcenterRepo

lazy val commonSettings = Seq(
  organization := "com.nike.epc",
  organizationName := "Nike",
  organizationHomepage := Some(url("http://engineering.nike.com")),
  javacOptions ++= Seq("-source", "1.8"),
  crossPaths := false,
  autoScalaLibrary := false,
  credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),
  licenses += "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"),
  startYear := Some(2018),
  description := "A library for working with standards from the EPC extension to the GS1 standard"
)


lazy val `epc-standards` = (project in file("."))
  .settings(commonSettings)
  .settings(
    publishArtifact := false,
    jacocoAggregateReportSettings := JacocoReportSettings(
      title = "Epc-Standards Project Coverage",
      formats = Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML)
    )
  )
  .aggregate(`epc-core`)

lazy val Benchmark = config("bench") extend Test

lazy val `epc-core` = (project in file("./epc-core"))
  .settings(commonSettings)
  .settings(
    testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework"),
    parallelExecution in Benchmark := false,
    logBuffered := false,
    jacocoReportSettings := JacocoReportSettings(
      title = "Epc-Standards Project Coverage",
      formats = Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML)
    ).withThresholds(
      // These should be adjusted as test coverage improves
      JacocoThresholds(
        instruction = 90,
        method = 88,
        branch = 51,
        complexity = 72,
        line = 89,
        clazz = 93,
      )
    ),
    libraryDependencies ++= Seq(
      "org.junit.jupiter" % "junit-jupiter-api" % "5.3.1" % Test,
      "commons-lang" % "commons-lang" % "2.6" % Test,
      "com.storm-enroute" %% "scalameter" % "0.8.2" % Benchmark,
      "epctagcoder" % "epctagcoder" % "0.0.5" % Benchmark from "https://github.com/jlcout/epctagcoder/releases/download/v0.0.5/epctagcoder-0.0.5-SNAPSHOT.jar",
    )
  )
  .configs(Benchmark)
  .settings(inConfig(Benchmark)(Defaults.testSettings): _*)
