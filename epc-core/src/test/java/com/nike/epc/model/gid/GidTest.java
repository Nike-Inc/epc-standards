/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.gid;

import com.nike.epc.model.*;
import com.nike.epc.TestValues;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GidTest {
  private final String GMN = TestValues.random();
  private final String OC = TestValues.random();
  private final String SN = TestValues.random();

  @Test
  void epcSchema() {
    final EpcScheme s =
        EpcScheme.gid(
            Gid.builder()
                .withGeneralManagerNumber(GMN)
                .withObjectClass(OC)
                .withSerialNumber(SN)
                .build());
    assertTrue(s.isGid());

    final Gid g = s.gid().get();
    assertEquals(GMN, g.generalManagerNumber());
    assertEquals(OC, g.objectClass());
    assertEquals(SN, g.serialNumber());
  }
}
