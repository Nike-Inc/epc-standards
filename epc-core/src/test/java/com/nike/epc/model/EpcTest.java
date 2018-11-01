/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model;

import com.nike.epc.decode.*;
import com.nike.epc.model.sgtin.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpcTest {
  final String rfidHex = "3018E20A00AFFD1CBE991A14";
  final String AID = "APP_ID";
  final Integer F = 0;
  final Integer P = 6;
  final Integer S = 96;

  final String CP = "231464";
  final String I = "0";
  final String GS1 = "180212";
  final String SN = "123456789012";

  final String TU =
      String.format(
          "urn:epc:tag:sgtin-%s:%s.%s.%s%s.%s", S.toString(), F.toString(), CP, I, GS1, SN);
  final String PIU = String.format("urn:epc:id:sgtin:%s.%s%s.%s", CP, I, GS1, SN);

  @Test
  void testSgtinToBinary() throws Exception {
    final Epc e = Decoding.decode(rfidHex);
    assertEquals(TU, e.tagUri());
    assertEquals(PIU, e.pureIdentityUri());

    assertEquals(
        "001100000001100011100010000010100000000010101111111111010001110010111110100110010001101000010100",
        e.rfidBinary());
  }

  @Test
  void testSgtinFromTagUri() {
    try {
      final Epc e = Epc.fromTagUri(TU);
    } catch (IllegalArgumentException e) {
      assertEquals("fromTagUri not yet implemented", e.getMessage());
    }
  }
}
