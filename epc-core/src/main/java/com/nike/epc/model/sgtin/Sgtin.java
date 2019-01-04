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

import static com.nike.epc.util.Validation.notNullOrEmpty;

public final class Sgtin {
  private final String companyPrefix, indicator, gs1ItemReference, serialNumber;
  private final int size;
  private final byte filter;

  private Sgtin(
      String companyPrefix,
      String indicator,
      String gs1ItemReference,
      String serialNumber,
      int size,
      byte filter) {
    this.companyPrefix = companyPrefix;
    this.indicator = indicator;
    this.gs1ItemReference = gs1ItemReference;
    this.serialNumber = serialNumber;
    this.size = size;
    this.filter = filter;
  }

  public String companyPrefix() {
    return companyPrefix;
  }

  public String indicator() {
    return indicator;
  }

  public String gs1ItemReference() {
    return gs1ItemReference;
  }

  public String itemReference() {
    return String.format("%s%s", indicator, gs1ItemReference);
  }

  public String serialNumber() {
    return serialNumber;
  }

  public int size() {
    return size;
  }

  public byte filter() {
    return filter;
  }

  /*
   * Where the checkDigit is calculated by numbering the the digits in the indicator, companyPrefix, and gs1ItemReference
   *  as d1-d13, and evaluating the following:
   *
   *  (10 – ((3(d1 + d3 + d5 + d7 + d9 + d11 + d13) + (d2 + d4 + d6 + d8 + d10 + d12)) mod 10)) mod 10
   */
  public int checkDigit() {
    final String value = String.format("%s%s%s", indicator, companyPrefix, gs1ItemReference);
    return (10
            - ((3
                        * (Character.getNumericValue(value.charAt(0))
                            + Character.getNumericValue(value.charAt(2))
                            + Character.getNumericValue(value.charAt(4))
                            + Character.getNumericValue(value.charAt(6))
                            + Character.getNumericValue(value.charAt(8))
                            + Character.getNumericValue(value.charAt(10))
                            + Character.getNumericValue(value.charAt(12)))
                    + (Character.getNumericValue(value.charAt(1))
                        + Character.getNumericValue(value.charAt(3))
                        + Character.getNumericValue(value.charAt(5))
                        + Character.getNumericValue(value.charAt(7))
                        + Character.getNumericValue(value.charAt(9))
                        + Character.getNumericValue(value.charAt(11))))
                % 10))
        % 10;
  }

  /*
   * In order to find the GTIN from an SGTIN, concatenate the following:
   *  indicator + companyPrefix + gs1ItemReference + checkDigit
   */
  public String gs1Gtin() {
    return String.format("%s%s%s%s", indicator, companyPrefix, gs1ItemReference, checkDigit());
  }

  public static Sgtin fromBits(RawBits bits, int partition, int size, byte filter) {
    TableItem tableItem = SgtinPartitionTableList.getPartitionByValue(partition);
    String companyPrefix = bits.getDecimalString(14, tableItem.getM(), tableItem.getL());
    int itemReferenceStart = 14 + tableItem.getM();
    String itemReference =
        bits.getDecimalString(itemReferenceStart, tableItem.getN(), tableItem.getDigits());
    String serialNumber = bits.getDecimalString(itemReferenceStart + tableItem.getN());

    String indicator = itemReference.substring(0, 1);
    itemReference = itemReference.substring(1);
    return new Sgtin(
        notNullOrEmpty(companyPrefix),
        notNullOrEmpty(indicator),
        notNullOrEmpty(itemReference),
        notNullOrEmpty(serialNumber),
        size,
        filter);
  }

  // public static final class Builder {
  //   private String companyPrefix, indicator, gs1ItemReference, serialNumber;

  //   private Builder() {}

  //   public Builder withCompanyPrefix(String companyPrefix) {
  //     this.companyPrefix = companyPrefix;
  //     return this;
  //   }

  //   public Builder withIndicator(String indicator) {
  //     this.indicator = indicator;
  //     return this;
  //   }

  //   public Builder withItemReference(String indicatorPlusGs1ItemReference) {
  //     this.indicator = indicatorPlusGs1ItemReference.substring(0, 1);
  //     this.gs1ItemReference = indicatorPlusGs1ItemReference.substring(1);
  //     return this;
  //   }

  //   public Builder withGs1ItemReference(String itemReference) {
  //     this.gs1ItemReference = itemReference;
  //     return this;
  //   }

  //   public Builder withSerialNumber(String serialNumber) {
  //     this.serialNumber = serialNumber;
  //     return this;
  //   }

  //   public Sgtin build() {
  //     return new Sgtin(
  //         notNullOrEmpty(companyPrefix),
  //         notNullOrEmpty(indicator),
  //         notNullOrEmpty(gs1ItemReference),
  //         notNullOrEmpty(serialNumber));
  //   }
  // }

  // public static Builder builder() {
  //   return new Builder();
  // }
}
