/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.ginc;

import com.nike.epc.model.*;
import com.nike.epc.TestValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GincTest {
  private final String CP = TestValues.random();
  private final String CR = TestValues.random();

  @Test
  void epcSchema() {
    final EpcScheme s =
        EpcScheme.ginc(Ginc.builder().withCompanyPrefix(CP).withConsignmentReference(CR).build());
    assertTrue(s.isGinc());

    final Ginc g = s.ginc().get();
    assertEquals(CP, g.companyPrefix());
    assertEquals(CR, g.consignmentReference());
  }
}
