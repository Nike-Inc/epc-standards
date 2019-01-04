/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.sgtin;

import com.nike.epc.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SgtinTest {

  @Test
  public void parseSgtin() {
    RawBits bits = RawBits.fromHex("30185E78003ECB006E2DA00E");
    Sgtin sgtin = Sgtin.fromBits(bits, 6, 0, (byte) 0);

    assertEquals("096736", sgtin.companyPrefix());
    assertEquals("0064300", sgtin.itemReference());
    assertEquals("064300", sgtin.gs1ItemReference());
    assertEquals("0", sgtin.indicator());
    assertEquals(4, sgtin.checkDigit());
    assertEquals("1848483854", sgtin.serialNumber());
    assertEquals("00967360643004", sgtin.gs1Gtin());
    assertEquals(4, sgtin.checkDigit());
  }
}
