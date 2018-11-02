/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.bench

import org.scalameter.api._
import org.scalameter.picklers.Implicits._
import org.epctagcoder.parse.SGTIN.ParseSGTIN
import com.nike.epc.decode._
import com.nike.epc.util._

object DecodeBench extends Bench.OfflineRegressionReport {

  val sizes: Gen[Int] = Gen.range("size")(5000, 100000, 5000)
  val sgtinHexes: Gen[Seq[String]] = sizes.map { size =>
    (0 until size).map(_ => "30185E78003ECB006E2DA00E")
  }

  performance of "SGTIN" in {
    measure method "Decoding.decode" in {
      using(sgtinHexes) in { hexes =>
        hexes.foreach { hex =>
          Decoding.decode(hex)
        }
      }
    }

    measure method "ParseSGTIN.Builder().withRFIDTag" in {
      using(sgtinHexes) in { hexes =>
        hexes.foreach { hex =>
          val epc = ParseSGTIN.Builder().withRFIDTag(hex).build()
          epc.getSGTIN()
        }
      }
    }
  }

  val xndtHexes: Gen[Seq[String]] = sizes.map { size =>
    (0 until size).map(_ => "E31019F59F59F5965900000A")
  }

  performance of "XNDT" in {
    measure method "Decoding.decode" in {
      using(xndtHexes) in { hexes =>
        hexes.foreach { hex =>
          Decoding.decode(hex)
        }
      }
    }
  }
}
