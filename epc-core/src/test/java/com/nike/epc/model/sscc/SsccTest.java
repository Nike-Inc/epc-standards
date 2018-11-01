/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.sscc;

import com.nike.epc.model.*;
import com.nike.epc.TestValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SsccTest {
  private final String CP = TestValues.random();
  private final String E = TestValues.random();
  private final String SR = TestValues.random();

  @Test
  void epcSchema() {
    final EpcScheme s =
        EpcScheme.sscc(
            Sscc.builder().withCompanyPrefix(CP).withExtension(E).withSerialReference(SR).build());
    assertTrue(s.isSscc());

    final Sscc ss = s.sscc().get();
    assertEquals(CP, ss.companyPrefix());
    assertEquals(E, ss.extension());
    assertEquals(SR, ss.serialReference());

    // TODO: Make these values meaningful.
    assertEquals(Integer.valueOf(0), ss.checkDigit());
    assertEquals("", ss.gs1Sscc());
  }
}
