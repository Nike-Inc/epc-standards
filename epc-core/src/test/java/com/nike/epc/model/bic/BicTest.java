/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.bic;

import com.nike.epc.model.*;
import com.nike.epc.TestValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BicTest {
  private final String OC = TestValues.random();
  private final String ECI = TestValues.random();
  private final String SN = TestValues.random();
  private final String CD = TestValues.random();

  @Test
  void epcSchema() {
    final EpcScheme s =
        EpcScheme.bic(
            Bic.builder()
                .withOwnerCode(OC)
                .withEquipmentCategoryIdentifier(ECI)
                .withSerialNumber(SN)
                .withCheckDigit(CD)
                .build());
    assertTrue(s.isBic());

    final Bic b = s.bic().get();
    assertEquals(OC, b.ownerCode());
    assertEquals(ECI, b.equipmentCategoryIdentifier());
    assertEquals(SN, b.serialNumber());
    assertEquals(CD, b.checkDigit());
  }
}
