/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.AbstractMap.SimpleEntry;

public interface Converter {
  static final Map<String, String> hexToBinMap =
      Collections.unmodifiableMap(
          Stream.of(
                  new SimpleEntry<>("0", "0000"),
                  new SimpleEntry<>("1", "0001"),
                  new SimpleEntry<>("2", "0010"),
                  new SimpleEntry<>("3", "0011"),
                  new SimpleEntry<>("4", "0100"),
                  new SimpleEntry<>("5", "0101"),
                  new SimpleEntry<>("6", "0110"),
                  new SimpleEntry<>("7", "0111"),
                  new SimpleEntry<>("8", "1000"),
                  new SimpleEntry<>("9", "1001"),
                  new SimpleEntry<>("A", "1010"),
                  new SimpleEntry<>("B", "1011"),
                  new SimpleEntry<>("C", "1100"),
                  new SimpleEntry<>("D", "1101"),
                  new SimpleEntry<>("E", "1110"),
                  new SimpleEntry<>("F", "1111"))
              .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

  static final Map<String, String> binToHexMap =
      Collections.unmodifiableMap(
          Stream.of(
                  new SimpleEntry<>("0000", "0"),
                  new SimpleEntry<>("0001", "1"),
                  new SimpleEntry<>("0010", "2"),
                  new SimpleEntry<>("0011", "3"),
                  new SimpleEntry<>("0100", "4"),
                  new SimpleEntry<>("0101", "5"),
                  new SimpleEntry<>("0110", "6"),
                  new SimpleEntry<>("0111", "7"),
                  new SimpleEntry<>("1000", "8"),
                  new SimpleEntry<>("1001", "9"),
                  new SimpleEntry<>("1010", "A"),
                  new SimpleEntry<>("1011", "B"),
                  new SimpleEntry<>("1100", "C"),
                  new SimpleEntry<>("1101", "D"),
                  new SimpleEntry<>("1110", "E"),
                  new SimpleEntry<>("1111", "F"))
              .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

  public static String hexToBin(String hex) {
    int len = hex.length();
    StringBuilder bin = new StringBuilder();

    hex = hex.toUpperCase();

    for (int i = 0; i < len; i++) {
      bin.append(hexToBinMap.get(hex.substring(i, i + 1)));
    }

    return bin.toString();
  }

  public static String binToHex(String bin) {
    int endIndex = 4;
    int len = bin.length() / endIndex;
    int startIndex = 0;

    StringBuilder hex = new StringBuilder();
    for (int i = 0; i < len; i++) {
      hex.append(binToHexMap.get(bin.substring(startIndex, endIndex)));
      startIndex = endIndex;
      endIndex = endIndex + 4;
    }

    return hex.toString();
  }

  public static String binToDec(String bin) {
    return new BigInteger(bin, 2).toString();
  }

  public static String strZero(int number, int len) {
    return strZero(Integer.toString(number), len);
  }

  public static String strZero(String str, int len) {
    StringBuilder sb = new StringBuilder();

    for (int toPrepend = len - str.length(); toPrepend > 0; toPrepend--) {
      sb.append('0');
    }

    sb.append(str);
    return sb.toString();
  }

  public static List<String> splitEqually(String text, int size) {
    List<String> ret = new ArrayList<String>((text.length() + size - 1) / size);

    for (int start = 0; start < text.length(); start += size) {
      ret.add(text.substring(start, Math.min(text.length(), start + size)));
    }
    return ret;
  }
}
