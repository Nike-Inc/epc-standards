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

bintrayOrganization in ThisBuild := Some("nike")
bintrayRepository in ThisBuild := "maven"
bintrayPackage in ThisBuild := "epc-standards"

version in ThisBuild := sys.env.get("TRAVIS_TAG").filter(_.trim.nonEmpty).getOrElse("0-SNAPSHOT")

lazy val ci = TaskKey[Unit]("ci")

lazy val `epc-standards` = (project in file("."))
  .settings(
    ci := Def.taskDyn {
      if (version.value.endsWith("-SNAPSHOT")) {
        Def.task {
          (`epc-core` / (jacoco in Test)).value
        }
      } else {
        Def.task {
          publish.dependsOn((`epc-core` / (jacoco in Test))).value
        }
      }
    }.value
  )
  .aggregate(`epc-core`)

lazy val Benchmark = config("bench") extend Test

lazy val `epc-core` = (project in file("./epc-core"))
  .settings(
    autoScalaLibrary := false,
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
        branch = 50,
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
