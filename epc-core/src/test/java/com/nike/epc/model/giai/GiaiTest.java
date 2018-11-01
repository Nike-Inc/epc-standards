/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.giai;

import com.nike.epc.model.*;
import com.nike.epc.TestValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GiaiTest {
  private final String CP = TestValues.random();
  private final String IAR = TestValues.random();

  @Test
  void epcSchema() {
    final EpcScheme s =
        EpcScheme.giai(
            Giai.builder().withCompanyPrefix(CP).withIndividualAssetReference(IAR).build());
    assertTrue(s.isGiai());

    final Giai g = s.giai().get();
    assertEquals(CP, g.companyPrefix());
    assertEquals(IAR, g.individualAssetReference());
  }
}
