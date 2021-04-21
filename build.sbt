/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */

// This allows coursier to work as an alternative resolver. See:
// https://github.com/maichler/sbt-jupiter-interface/tree/0.7.0#usage
resolvers in ThisBuild += Resolver.jcenterRepo

organizationName in ThisBuild := "Nike"
organization in ThisBuild := "com.nike.epc"
organizationHomepage in ThisBuild := Some(url("http://engineering.nike.com"))
javacOptions in ThisBuild ++= Seq("-source", "1.8")
licenses in ThisBuild += "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt")
startYear in ThisBuild := Some(2018)
description in ThisBuild := "A library for working with standards from the EPC extension to the GS1 standard"
crossPaths in ThisBuild := false

publishTo in ThisBuild := {
  val repo = "https://artifactory.nike.com/artifactory/maven"
  if (isSnapshot.value) {
    Some("snapshots" at s"$repo-snapshots")
  } else {
    Some("releases" at repo)
  }
}
credentials in ThisBuild += Credentials(Path.userHome / ".ivy2" / ".credentials")

lazy val ci = TaskKey[Unit]("ci")

lazy val `epc-standards` = (project in file("."))
  .settings(
    publishArtifact := false,
    ci := Def.taskDyn {
      Def.task {
        (`epc-core` / publish).dependsOn((`epc-core` / (jacoco in Test))).value
      }
    }.value
  )
  .aggregate(`epc-core`)

lazy val `epc-core` = (project in file("./epc-core"))
  .settings(
    autoScalaLibrary := false,
    jacocoReportSettings := JacocoReportSettings(
      title = "Epc-Standards Project Coverage",
      formats = Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML)
    ).withThresholds(
      // These should be adjusted as test coverage improves
      JacocoThresholds(
        instruction = 90,
        method = 88,
        branch = 50,
        complexity = 72,
        line = 89,
        clazz = 93,
      )
    ),
    libraryDependencies ++= Seq(
      "org.junit.jupiter" % "junit-jupiter-api" % "5.3.1" % Test,
      "commons-lang" % "commons-lang" % "2.6" % Test,
    )
  )

lazy val benchmark = (project in file("./benchmark"))
  .dependsOn(`epc-core`)
  .settings(
    testFrameworks += new TestFramework("org.scalameter.ScalaMeterFramework"),
    parallelExecution in Test := false,
    logBuffered := false,
    libraryDependencies ++= Seq(
      "com.storm-enroute" %% "scalameter" % "0.8.2" % Test,
      "epctagcoder" % "epctagcoder" % "0.0.5" % Test from "https://github.com/jlcout/epctagcoder/releases/download/v0.0.5/epctagcoder-0.0.5-SNAPSHOT.jar",
    )
  )
