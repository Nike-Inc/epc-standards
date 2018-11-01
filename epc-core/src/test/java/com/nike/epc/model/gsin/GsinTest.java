/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.gsin;

import com.nike.epc.model.*;
import com.nike.epc.TestValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GsinTest {
  private final String CP = TestValues.random();
  private final String SR = TestValues.random();

  @Test
  void epcSchema() {
    final EpcScheme s =
        EpcScheme.gsin(Gsin.builder().withCompanyPrefix(CP).withShipperReference(SR).build());
    assertTrue(s.isGsin());

    final Gsin g = s.gsin().get();
    assertEquals(CP, g.companyPrefix());
    assertEquals(SR, g.shipperReference());
  }
}
