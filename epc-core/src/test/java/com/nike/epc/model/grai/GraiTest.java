/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.grai;

import com.nike.epc.model.*;
import com.nike.epc.TestValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraiTest {
  private final String AT = TestValues.random();
  private final String CP = TestValues.random();
  private final String SN = TestValues.random();

  @Test
  void epcSchema() {
    final EpcScheme s =
        EpcScheme.grai(
            Grai.builder().withAssetType(AT).withCompanyPrefix(CP).withSerialNumber(SN).build());
    assertTrue(s.isGrai());

    final Grai g = s.grai().get();
    assertEquals(AT, g.assetType());
    assertEquals(CP, g.companyPrefix());
    assertEquals(SN, g.serialNumber());
  }
}
