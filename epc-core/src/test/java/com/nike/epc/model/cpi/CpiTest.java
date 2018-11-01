/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.cpi;

import com.nike.epc.model.*;
import com.nike.epc.TestValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpiTest {
  private final String CP = TestValues.random();
  private final String CPR = TestValues.random();
  private final String SN = TestValues.random();

  @Test
  void epcSchema() {
    final EpcScheme s =
        EpcScheme.cpi(
            Cpi.builder()
                .withCompanyPrefix(CP)
                .withComponentPartReference(CPR)
                .withSerialNumber(SN)
                .build());
    assertTrue(s.isCpi());

    final Cpi c = s.cpi().get();
    assertEquals(CP, c.companyPrefix());
    assertEquals(CPR, c.componentPartReference());
    assertEquals(SN, c.serialNumber());
  }
}
