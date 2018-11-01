/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.util;

import org.junit.jupiter.api.Test;

import java.lang.ArithmeticException;
import java.lang.IllegalArgumentException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConverterTest {

  @Test
  public void hexToBinTest() {
    String bin = Converter.hexToBin("0123456789ABCDEF");
    assertEquals(bin, "0000000100100011010001010110011110001001101010111100110111101111");
  }

  // @TODO: a better failure mode for this method and others
  // @Test
  // public void hexToBinFailure() {
  //     String bin = Converter.hexToBin("XYZ");
  //     assertEquals(bin, "nullnullnull");
  // }

  @Test
  public void binToHexTest() {
    String hex =
        Converter.binToHex("0000000100100011010001010110011110001001101010111100110111101111");
    assertEquals(hex, "0123456789ABCDEF");
  }

  @Test
  public void binToDecTest() {
    String dec =
        Converter.binToDec("0000000100100011010001010110011110001001101010111100110111101111");
    assertEquals(dec, "81985529216486895");
  }

  @Test
  public void strZeroTest() {
    String zeroed = Converter.strZero("", 10);
    assertEquals(zeroed, "0000000000");

    zeroed = Converter.strZero("", 0);
    assertEquals(zeroed, "");

    String sample = "abc";

    zeroed = Converter.strZero(sample, sample.length());
    assertEquals(zeroed, sample);

    zeroed = Converter.strZero(sample, sample.length() + 2);
    assertEquals(zeroed, "00" + sample);

    zeroed = Converter.strZero(sample, sample.length() - 2);
    assertEquals(zeroed, sample);
  }

  @Test
  public void splitEqually() {
    String sample = "aabbcc";
    List<String> split = Converter.splitEqually(sample, 2);
    assertEquals(split.size(), 3);
    assertEquals(split.get(0), "aa");
    assertEquals(split.get(1), "bb");
    assertEquals(split.get(2), "cc");

    List<String> tooLarge = Converter.splitEqually(sample, sample.length() + 1);
    assertEquals(tooLarge.size(), 1);
    assertEquals(tooLarge.get(0), sample);

    assertThrows(ArithmeticException.class, () -> Converter.splitEqually(sample, 0));
    assertThrows(IllegalArgumentException.class, () -> Converter.splitEqually(sample, -1));
  }
}
