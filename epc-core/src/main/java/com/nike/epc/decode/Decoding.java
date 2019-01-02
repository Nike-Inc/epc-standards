/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.decode;

import com.nike.epc.model.*;
import com.nike.epc.model.sgtin.*;
import com.nike.epc.model.xndt.*;
import com.nike.epc.util.Converter;
import java.nio.ByteBuffer;
import java.util.BitSet;

public interface Decoding {

  public static Epc decode(String hex) throws Exception {
    return decode(RawBits.fromHex(hex));
  }

  public static Epc decode(byte[] bytes) throws Exception {
    return decode(new RawBits(bytes));
  }

  public static Epc decode(ByteBuffer bytes) throws Exception {
    return decode(new RawBits(bytes));
  }

  public static Epc decode(RawBits bits) throws Exception {
    byte header = bits.getByte(0);
    CodingScheme codingScheme = CodingScheme.fromByte(header);
    byte filter = bits.getByte(8, 3);
    int partition = bits.getByte(11, 3);
    return buildEpc(header, filter, partition, codingScheme.bitCount, bits);
  }

  static Epc buildEpc(byte header, byte filter, int partition, int size, RawBits bits)
      throws UnsupportedOperationException {
    switch (Byte.toUnsignedInt(header)) {
      case 0x00: // return UNPROGRAMMED;
        return new Epc("", filter, partition, size, null, bits);
      case 0x2C: // GDTI_96;
        throw new UnsupportedOperationException("GDTI_96");
      case 0x2D: // GSRN_96;
        throw new UnsupportedOperationException("GSRN_96");
      case 0x2E: // GSRNP;
        throw new UnsupportedOperationException("GSRNP");
      case 0x2F: // US_DOD_96;
        throw new UnsupportedOperationException("US_DOD_96");
      case 0x30: // SGTIN_96;
        Sgtin sgtin = Sgtin.fromBits(bits, partition, size, filter);
        return new Epc("AI 414 + AI 254", filter, partition, size, EpcScheme.sgtin(sgtin), bits);
      case 0x31: // SSCC_96;
        throw new UnsupportedOperationException("SSCC_96");
      case 0x32: // SGLN_96;
        throw new UnsupportedOperationException("SGLN_96");
      case 0x33: // GRAI_96;
        throw new UnsupportedOperationException("GRAI_96");
      case 0x34: // GIAI_96;
        throw new UnsupportedOperationException("GIAI_96");
      case 0x35: // GID_96;
        throw new UnsupportedOperationException("GID_96");
      case 0x36: // SGTIN_198;
        throw new UnsupportedOperationException("SGTIN_198");
      case 0x37: // GRAI_170;
        throw new UnsupportedOperationException("GRAI_170");
      case 0x38: // GIAI_202;
        throw new UnsupportedOperationException("GIAI_202");
      case 0x39: // SGLN_195;
        throw new UnsupportedOperationException("SGLN_195");
      case 0x3A: // GDTI_113;
        throw new UnsupportedOperationException("GDTI_113");
      case 0x3B: // ADI_VAR;
        throw new UnsupportedOperationException("ADI_VAR");
      case 0x3C: // CPI_96;
        throw new UnsupportedOperationException("CPI_96");
      case 0x3D: // CPI_VAR;
        throw new UnsupportedOperationException("CPI_VAR");
      case 0x3E: // GDTI_174;
        throw new UnsupportedOperationException("GDTI_174");
      case 0x3F: // SGCN_96;
        throw new UnsupportedOperationException("SGCN_96");
      case 0x40: // ITIP_110;
        throw new UnsupportedOperationException("ITIP_110");
      case 0x41: // ITIP_212;
        throw new UnsupportedOperationException("ITIP_212");
      case 0xE3: // XNDT_96;
        Xndt xndt = Xndt.fromBits(bits, size);
        return new Epc("UNSPECIFIED", filter, partition, size, EpcScheme.xndt(xndt), bits);
      default:
        throw new UnsupportedOperationException("default");
    }
  }

  static enum CodingScheme {
    UNPROGRAMMED(0),
    GDTI_96(96),
    GSRN_96(96),
    GSRNP(96),
    US_DOD_96(96),
    SGTIN_96(96),
    SSCC_96(96),
    SGLN_96(96),
    GRAI_96(96),
    GIAI_96(96),
    GID_96(96),
    SGTIN_198(198),
    GRAI_170(170),
    GIAI_202(202),
    SGLN_195(195),
    GDTI_113(113),
    ADI_VAR(-1), // @TODO: consider this...
    CPI_96(96),
    CPI_VAR(-1), // @TODO: consider this...
    GDTI_174(174),
    SGCN_96(96),
    ITIP_110(110),
    ITIP_212(212),
    XNDT_96(96);

    int bitCount;

    CodingScheme(int bitCount) {
      this.bitCount = bitCount;
    }

    static CodingScheme fromByte(byte code) {
      switch (Byte.toUnsignedInt(code)) {
        case 0x00:
          return UNPROGRAMMED;
        case 0x2C:
          return GDTI_96;
        case 0x2D:
          return GSRN_96;
        case 0x2E:
          return GSRNP;
        case 0x2F:
          return US_DOD_96;
        case 0x30:
          return SGTIN_96;
        case 0x31:
          return SSCC_96;
        case 0x32:
          return SGLN_96;
        case 0x33:
          return GRAI_96;
        case 0x34:
          return GIAI_96;
        case 0x35:
          return GID_96;
        case 0x36:
          return SGTIN_198;
        case 0x37:
          return GRAI_170;
        case 0x38:
          return GIAI_202;
        case 0x39:
          return SGLN_195;
        case 0x3A:
          return GDTI_113;
        case 0x3B:
          return ADI_VAR;
        case 0x3C:
          return CPI_96;
        case 0x3D:
          return CPI_VAR;
        case 0x3E:
          return GDTI_174;
        case 0x3F:
          return SGCN_96;
        case 0x40:
          return ITIP_110;
        case 0x41:
          return ITIP_212;
        case 0xE3:
          return XNDT_96;
        default:
          return UNPROGRAMMED;
      }
    }
  }
}
