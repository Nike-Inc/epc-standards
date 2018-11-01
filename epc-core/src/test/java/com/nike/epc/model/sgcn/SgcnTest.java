/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.sgcn;

import com.nike.epc.model.*;
import com.nike.epc.TestValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SgcnTest {
  private final String CP = TestValues.random();
  private final String CR = TestValues.random();
  private final String SC = TestValues.random();

  @Test
  void epcSchema() {
    final EpcScheme s =
        EpcScheme.sgcn(
            Sgcn.builder()
                .withCompanyPrefix(CP)
                .withCouponReference(CR)
                .withSerialComponent(SC)
                .build());
    assertTrue(s.isSgcn());

    final Sgcn sg = s.sgcn().get();
    assertEquals(CP, sg.companyPrefix());
    assertEquals(CR, sg.couponReference());
    assertEquals(SC, sg.serialComponent());
  }
}
