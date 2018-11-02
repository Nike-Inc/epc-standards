/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.decode;

import org.junit.jupiter.api.Test;
import com.nike.epc.model.*;
import com.nike.epc.model.sgtin.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RawBitsTest {

  @Test
  public void getByteEmpty() {

    RawBits bits = RawBits.fromHex("");

    assertEquals((byte) 0, bits.getByte(0));
    assertEquals((byte) 0, bits.getByte(10));
    assertEquals((byte) 0, bits.getByte(100));
    assertEquals((byte) 0, bits.getByte(1000));
  }

  @Test
  void getBytePastEnd() {
    RawBits bits = RawBits.fromHex("FF");

    assertEquals((byte) -1, bits.getByte(0));
    assertEquals((byte) 0, bits.getByte(8));
  }

  @Test
  void getByte() {
    RawBits bits = RawBits.fromHex("CF0F"); // 1100 1111 0000 1111

    assertEquals((byte) 207, bits.getByte(0)); // 1100 1111
    assertEquals((byte) 158, bits.getByte(1)); // 1001 1110
    assertEquals((byte) 60, bits.getByte(2)); // 0011 1100
    assertEquals((byte) 120, bits.getByte(3)); // 0111 1000
    assertEquals((byte) 240, bits.getByte(4)); // 1111 0000
    assertEquals((byte) 225, bits.getByte(5)); // 1110 0001
    assertEquals((byte) 195, bits.getByte(6)); // 1100 0011
    assertEquals((byte) 135, bits.getByte(7)); // 1000 0111
    assertEquals((byte) 15, bits.getByte(8)); // 0000 1111
    assertEquals((byte) 30, bits.getByte(9)); // 0001 1110
    assertEquals((byte) 60, bits.getByte(10)); // 0011 1100
    assertEquals((byte) 120, bits.getByte(11)); // 0111 1000
    assertEquals((byte) 240, bits.getByte(12)); // 1111 0000
    assertEquals((byte) 224, bits.getByte(13)); // 1110 0000
    assertEquals((byte) 192, bits.getByte(14)); // 1100 0000
    assertEquals((byte) 128, bits.getByte(15)); // 1000 0000
    assertEquals((byte) 0, bits.getByte(16)); // 0000 0000
  }

  @Test
  void getByteWithPadding() {
    RawBits bits = RawBits.fromHex("CF0F"); // 1100 1111 0000 1111

    assertEquals((byte) 103, bits.getByte(0, 7)); // 0110 0111
    assertEquals((byte) 39, bits.getByte(1, 6)); // 0010 0111
    assertEquals((byte) 7, bits.getByte(2, 5)); // 0000 0111
    assertEquals((byte) 7, bits.getByte(3, 4)); // 0000 0111
    assertEquals((byte) 7, bits.getByte(4, 3)); // 0000 0111
    assertEquals((byte) 3, bits.getByte(5, 2)); // 0000 0011
    assertEquals((byte) 1, bits.getByte(6, 1)); // 0000 0001
    assertEquals((byte) 67, bits.getByte(7, 7)); // 0100 0011
    assertEquals((byte) 3, bits.getByte(8, 6)); // 0000 0011
    assertEquals((byte) 3, bits.getByte(9, 5)); // 0000 0011
    assertEquals((byte) 3, bits.getByte(10, 4)); // 0000 0011
    assertEquals((byte) 3, bits.getByte(11, 3)); // 0000 0011
    assertEquals((byte) 3, bits.getByte(12, 2)); // 0000 0011
    assertEquals((byte) 1, bits.getByte(13, 1)); // 0000 0001
    assertEquals((byte) 0, bits.getByte(14, 0)); // 0000 0000

    assertEquals((byte) 15, bits.getByte(0, 16)); // 0000 1111
    assertEquals((byte) 0, bits.getByte(0, 32)); // 0000 0000
  }

  @Test
  void getDecimalStringZero() {
    RawBits zero = RawBits.fromHex("00");
    for (int i = 1; i <= 8; i++) {
      assertEquals("0", zero.getDecimalString(0, i));
    }
  }

  @Test
  void getDecimalStringOne() {
    RawBits one = RawBits.fromHex("01");
    for (int i = 0; i < 8; i++) {
      assertEquals("1", one.getDecimalString(i, 8 - i));
    }
  }

  @Test
  void getDecimalStringOverflow() {
    RawBits one = RawBits.fromHex("01");
    assertEquals("1", one.getDecimalString(0, 8));
    assertEquals("2", one.getDecimalString(1, 8));
    assertEquals("4", one.getDecimalString(2, 8));
    assertEquals("8", one.getDecimalString(3, 8));
    assertEquals("16", one.getDecimalString(4, 8));
    assertEquals("32", one.getDecimalString(5, 8));
    assertEquals("64", one.getDecimalString(6, 8));
    assertEquals("128", one.getDecimalString(7, 8));
    assertEquals("0", one.getDecimalString(8, 8));
  }

  @Test
  void getDecimalStringLarge() {
    // 001100000001100001011110011110000000000000111110110010110000000001101110001011011010000000001110
    RawBits bits = RawBits.fromHex("30185E78003ECB006E2DA00E");

    // 00010111100111100000
    assertEquals("096736", bits.getDecimalString(14, 20, 6));

    assertEquals("0064300", bits.getDecimalString(34, 24, 7));
  }

  @Test
  void getBase64Decoded() {
    RawBits bits = RawBits.fromHex("019F59F59F4");

    assertEquals("Z9Z9Z9", bits.getBase64Decoded(0, 42));

    bits = RawBits.fromHex("00019F59F59F4");
    assertEquals("Z9Z9Z9", bits.getBase64Decoded(8, 42));
  }
}
