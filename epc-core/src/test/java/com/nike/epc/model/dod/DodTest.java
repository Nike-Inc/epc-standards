/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.dod;

import com.nike.epc.model.*;
import com.nike.epc.TestValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DodTest {
  private final String CC = TestValues.random();
  private final String SN = TestValues.random();

  @Test
  void epcSchema() {
    final EpcScheme s = EpcScheme.dod(Dod.builder().withCageCode(CC).withSerialNumber(SN).build());
    assertTrue(s.isDod());

    final Dod d = s.dod().get();
    assertEquals(CC, d.cageCode());
    assertEquals(SN, d.serialNumber());
  }
}
