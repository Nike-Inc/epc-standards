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

/**
 * ------------------------------------------------------------------------------------------------------------
 * Custom Display tag XNDT: Extended Display tag - 96 bit tag definition:
 * ------------------------------------------------------------------------------------------------------------
 * Format: urn:epc:tag:XNDT-{Bit Length}:{DisplayCode}.{Style}.{Color}.{SerialNumber} XNDT-96 header
 * value is E3 (1110 0011)
 *
 * <p>Bit Ranges:
 *
 * <p>EPC_HEADER: 95-88 bits, DISPLAY_CODE: 87-84 bits, STYLE: 83-42 bits, COLOR: 41-24 bits,
 * SEQUENCE: 39-0 bits Bit Range : EPC_HEADER : 8 bits : DISPLAY_CODE : 4 bits : STYLE : 36 bits :
 * 12 Base64 COLOR : 18 bits : 6 Base64 SEQUENCE : 30 bits : Max Sequence : 1073741823 (2^30 -1)
 */
public final class Xndt {

  private byte displayCode;
  private String style;
  private String color;
  private String styleColor;
  private int serialNumber;

  private static final int XNDT_LENGTH = 96;
  private static final int EPC_HEADER_MSB = 95;
  private static final int EPC_HEADER_LSB = 88;
  private static final int DISPLAY_CODE_MSB = 87;
  private static final int DISPLAY_CODE_LSB = 84;
  private static final int STYLE_MSB = 83;

  private static final int STYLE_BITS_FIELD_LENGTH = 42;
  private static final int COLOR_BITS_FIELD_LENGTH = 18;
  private static final int STYLE_DIGITS_FIELD_LENGTHS = 6;
  private static final int COLOR_DIGITS_FIELD_LENGTHS = 3;

  private static final int STYLE_LSB = STYLE_MSB - STYLE_BITS_FIELD_LENGTH + 1; // 42
  private static final int COLOR_MSB = STYLE_LSB - 1; // 41
  private static final int COLOR_LSB = COLOR_MSB - COLOR_BITS_FIELD_LENGTH + 1; // 24
  private static final int SEQUENCE_MSB = COLOR_LSB - 1; // 23
  private static final int SEQUENCE_LSB = 0;
  private static final int STYLE_PADDING = STYLE_DIGITS_FIELD_LENGTHS;
  private static final int COLOR_PADDING = COLOR_DIGITS_FIELD_LENGTHS;

  private Xndt(byte displayCode, String style, String color, int serialNumber) {
    this.displayCode = displayCode;
    this.style = style;
    this.color = color;
    this.serialNumber = serialNumber;
    this.styleColor = style + "-" + color;
  }

  public static Xndt fromBits(RawBits bits) {
    // [8, 12) - 4 bits
    byte displayCode = bits.getByte(8, 4);

    // [12, 54) - 42 bits
    String style = bits.getBase64Decoded(12, 42);

    // [54, 72) - 18 bits
    String color = bits.getBase64Decoded(54, 18);

    // [72, 96) - 24 bits
    int sequence = bits.getInt(72, 24);

    return new Xndt(displayCode, style, color, sequence);
  }

  public byte displayCode() {
    return this.displayCode;
  }

  public String style() {
    return this.style;
  }

  public String color() {
    return this.color;
  }

  public String styleColor() {
    return this.styleColor;
  }

  public int serialNumber() {
    return this.serialNumber;
  }
}
