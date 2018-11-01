/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.xndt;

import com.nike.epc.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XndtTest {

  @Test
  public void parseXndt() {
    // E    3    1    0    1    9    F    5    9    F    5    9    F    5    9    6    5    9    0
    //  0    0    0    0    A
    // 1110 0011 0001 0000 0001 1001 1111 0101 1001 1111 0101 1001 1111 0101 1001 0110 0101 1001
    // 0000 0000 0000 0000 0000 1010
    RawBits bits = RawBits.fromHex("E31019F59F59F5965900000A");
    Xndt xndt = Xndt.fromBits(bits);

    assertEquals(1, xndt.displayCode());
    assertEquals("Z9Z9Z9", xndt.style());
    assertEquals("ZZZ", xndt.color());
    assertEquals("Z9Z9Z9-ZZZ", xndt.styleColor());
    assertEquals(10, xndt.serialNumber());
  }
}
