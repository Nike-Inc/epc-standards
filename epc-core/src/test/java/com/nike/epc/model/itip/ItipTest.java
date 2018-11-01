/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.itip;

import com.nike.epc.model.*;
import com.nike.epc.TestValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItipTest {
  private final String CP = TestValues.random();
  private final String I = TestValues.random();
  private final String IR = TestValues.random();
  private final String P = TestValues.random();
  private final String SN = TestValues.random();
  private final String T = TestValues.random();

  @Test
  void epcSchema() {
    final EpcScheme s =
        EpcScheme.itip(
            Itip.builder()
                .withCompanyPrefix(CP)
                .withIndicator(I)
                .withItemReference(IR)
                .withPiece(P)
                .withSerialNumber(SN)
                .withTotal(T)
                .build());
    assertTrue(s.isItip());

    final Itip i = s.itip().get();
    assertEquals(CP, i.companyPrefix());
    assertEquals(I, i.indicator());
    assertEquals(IR, i.itemReference());
    assertEquals(P, i.piece());
    assertEquals(SN, i.serialNumber());
    assertEquals(T, i.total());
  }
}
