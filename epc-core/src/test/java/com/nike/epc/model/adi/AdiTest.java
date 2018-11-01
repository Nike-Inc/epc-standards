/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.adi;

import com.nike.epc.model.*;
import com.nike.epc.TestValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdiTest {
  private final String CC = TestValues.random();
  private final String DA = TestValues.random();
  private final String AC = TestValues.random();
  private final String AEC = TestValues.random();

  @Test
  void testOneOfError() {
    assertThrows(
        IllegalArgumentException.class,
        () ->
            Adi.builder()
                .withCageCode(CC)
                .withDodAac(DA)
                .withAdiComponent(AC)
                .withAdiExtendedComponent(AEC)
                .build(),
        TestValues.ERROR_ONE_OF);
  }

  @Test
  void adiComponent() {
    assertThrows(
        IllegalArgumentException.class,
        () -> Adi.builder().withCageCode(CC).withAdiComponent(AC).build(),
        TestValues.ERROR_REQUIRED);
  }

  @Test
  void epcSchema() {
    final EpcScheme s =
        EpcScheme.adi(
            Adi.builder()
                .withDodAac(DA)
                .withAdiComponent(AC)
                .withAdiExtendedComponent(AEC)
                .build());
    assertTrue(s.isAdi());

    final Adi a = s.adi().get();
    assertEquals(DA, a.dodAac());
    assertEquals(AC, a.adiComponenet());
    assertEquals(AEC, a.adiExtendedComponent());
  }
}
