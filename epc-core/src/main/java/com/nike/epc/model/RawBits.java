/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model;

import com.nike.epc.util.Converter;
import java.nio.ByteBuffer;
import java.util.BitSet;

public class RawBits {
  private final BitSet bits;
  private final int length;

  public RawBits(byte[] bytes) {
    this.bits = BitSet.valueOf(bytes);
    this.length = bytes.length * 8;
  }

  public RawBits(ByteBuffer bytes) {
    this.bits = BitSet.valueOf(bytes);
    this.length = bytes.capacity() * 8;
  }

  private RawBits(BitSet bits, int length) {
    this.bits = bits;
    this.length = length;
  }

  public static RawBits fromHex(String hex) {
    BitSet bits = new BitSet();

    int bitSetIdx = 0;
    for (int i = 0; i < hex.length(); i++, bitSetIdx += 4) {

      switch (Character.toLowerCase(hex.charAt(i))) {
        case '0': // 0000
          break;
        case '1': // 0001
          bits.set(bitSetIdx + 3);
          break;
        case '2': // 0010
          bits.set(bitSetIdx + 2);
          break;
        case '3': // 0011
          bits.set(bitSetIdx + 2);
          bits.set(bitSetIdx + 3);
          break;
        case '4': // 0100
          bits.set(bitSetIdx + 1);
          break;
        case '5': // 0101
          bits.set(bitSetIdx + 1);
          bits.set(bitSetIdx + 3);
          break;
        case '6': // 0110
          bits.set(bitSetIdx + 1);
          bits.set(bitSetIdx + 2);
          break;
        case '7': // 0111
          bits.set(bitSetIdx + 1);
          bits.set(bitSetIdx + 2);
          bits.set(bitSetIdx + 3);
          break;
        case '8': // 1000
          bits.set(bitSetIdx);
          break;
        case '9': // 1001
          bits.set(bitSetIdx);
          bits.set(bitSetIdx + 3);
          break;
        case 'a': // 1010
          bits.set(bitSetIdx);
          bits.set(bitSetIdx + 2);
          break;
        case 'b': // 1011
          bits.set(bitSetIdx);
          bits.set(bitSetIdx + 2);
          bits.set(bitSetIdx + 3);
          break;
        case 'c': // 1100
          bits.set(bitSetIdx);
          bits.set(bitSetIdx + 1);
          break;
        case 'd': // 1101
          bits.set(bitSetIdx);
          bits.set(bitSetIdx + 1);
          bits.set(bitSetIdx + 3);
          break;
        case 'e': // 1110
          bits.set(bitSetIdx);
          bits.set(bitSetIdx + 1);
          bits.set(bitSetIdx + 2);
          break;
        case 'f': // 1111
          bits.set(bitSetIdx);
          bits.set(bitSetIdx + 1);
          bits.set(bitSetIdx + 2);
          bits.set(bitSetIdx + 3);
          break;
        default:
          break;
      }
    }

    return new RawBits(bits, bitSetIdx);
  }

  @Override
  public String toString() {
    return bits.toString();
  }

  public String binary() {
    return binary(0, this.length);
  }

  public String binary(int offset, int count) {
    StringBuilder sb = new StringBuilder();
    for (int i = offset; i < offset + count; i++) {
      sb.append(bits.get(i) ? '1' : '0');
    }
    return sb.toString();
  }

  /**
   * This array is a lookup table that translates 6-bit positive integer index values into their
   * "Base64 Alphabet" equivalents as specified in "Table 1: The Base64 Alphabet" of RFC 2045 (and
   * RFC 4648).
   */
  private static final char[] toBase64 = {
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
  };

  public String getBase64Decoded(int offset, int count) {
    StringBuilder sb = new StringBuilder();

    boolean nonLeadingZeroFlag = false;

    int end = count + offset;
    for (int i = offset; i < end; i += 6) {
      int nextValue = getInt(i, 6);
      if (nextValue != 0 || nonLeadingZeroFlag) {
        nonLeadingZeroFlag = true;
        char character = toBase64[nextValue];
        sb.append(character);
      }
    }

    return sb.toString();
  }

  public byte getByte(int offset) {
    return getByte(offset, 8);
  }

  public byte getByte(int offset, int count) {
    return (byte) bitsToDecimal(offset, count);
  }

  public String getDecimalString(int offset) {
    return getDecimalString(offset, this.length - offset, 0);
  }

  public String getDecimalString(int offset, int count) {
    return getDecimalString(offset, count, 0);
  }

  public String getDecimalString(int offset, int count, int length) {
    String decimal = Long.toString(bitsToDecimal(offset, count));
    if (length > 0) {
      return Converter.strZero(decimal, length);
    } else {
      return decimal;
    }
  }

  public int getInt(int offset, int count) {
    return (int) bitsToDecimal(offset, count);
  }

  public long getLong(int offset, int count) {
    return bitsToDecimal(offset, count);
  }

  private long bitsToDecimal(int offset, int count) {
    long value = 0L;
    int end = offset + count;
    int shifter = count - 1;
    for (int i = offset; i < end; i++) {
      value += bits.get(i) ? (1L << (shifter - (i - offset))) : 0L;
    }
    return value;
  }
}
