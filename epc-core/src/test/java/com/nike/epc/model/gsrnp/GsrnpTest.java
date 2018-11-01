/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.gsrnp;

import com.nike.epc.model.*;
import com.nike.epc.TestValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GsrnpTest {
  private final String CP = TestValues.random();
  private final String SR = TestValues.random();

  @Test
  void epcSchema() {
    final EpcScheme s =
        EpcScheme.gsrnp(Gsrnp.builder().withCompanyPrefix(CP).withServiceReference(SR).build());
    assertTrue(s.isGsrnp());

    final Gsrnp g = s.gsrnp().get();
    assertEquals(CP, g.companyPrefix());
    assertEquals(SR, g.serviceReference());
  }
}
