/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.sgln;

import com.nike.epc.model.*;
import com.nike.epc.TestValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SglnTest {
  private final String CP = TestValues.random();
  private final String GE = TestValues.random();
  private final String LR = TestValues.random();

  @Test
  void epcSchema() {
    final EpcScheme s =
        EpcScheme.sgln(
            Sgln.builder()
                .withCompanyPrefix(CP)
                .withGlnExtension(GE)
                .withLocationReference(LR)
                .build());
    assertTrue(s.isSgln());

    final Sgln sg = s.sgln().get();
    assertEquals(CP, sg.companyPrefix());
    assertEquals(GE, sg.glnExtension());
    assertEquals(LR, sg.locationReference());
  }
}
