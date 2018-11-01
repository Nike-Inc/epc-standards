/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.lgtin;

import static com.nike.epc.util.Validation.notNullOrEmpty;

/**
 * From the standard: The GTIN+ Batch/Lot scheme is used to denote a class of objects belonging to a
 * given batch or lot of a given GTIN.
 *
 * <p>In the epc uris, the company prefix and itemReference will both be zero padded such that when
 * the companyPrefix, indicator and itemReference are concatenated, the resultant string is 13
 * digits.
 */
public final class Lgtin {
  private final String companyPrefix, indicator, itemReference, batchLotNumber;

  private Lgtin(
      String companyPrefix, String indicator, String itemReference, String batchLotNumber) {
    this.companyPrefix = companyPrefix;
    this.indicator = indicator;
    this.itemReference = itemReference;
    this.batchLotNumber = batchLotNumber;
  }

  public String companyPrefix() {
    return companyPrefix;
  }

  public String indicator() {
    return indicator;
  }

  public String itemReference() {
    return itemReference;
  }

  public String batchLotNumber() {
    return batchLotNumber;
  }

  /*
   * Where the checkDigit is calculated by numbering the the digits in the indicator, companyPrefix, and itemReference
   *  as d1-d13, and evaluating the following:
   *
   *  (10 – ((3(d1 + d3 + d5 + d7 + d9 + d11 + d13) + (d2 + d4 + d6 + d8 + d10 + d12)) mod 10)) mod 10
   */
  public Integer checkDigit() {
    return 0;
  }

  /*
   * In order to find the GTIN from an SGTIN, concatenate the following:
   *  indicator + companyPrefix + itemReference + checkDigit
   */
  public String gs1Gtin() {
    return "";
  }

  public static final class Builder {
    private String companyPrefix, indicator, itemReference, batchLotNumber;

    private Builder() {}

    public Builder withCompanyPrefix(String companyPrefix) {
      this.companyPrefix = companyPrefix;
      return this;
    }

    public Builder withIndicator(String indicator) {
      this.indicator = indicator;
      return this;
    }

    public Builder withItemReference(String itemReference) {
      this.itemReference = itemReference;
      return this;
    }

    public Builder withBatchLotNumber(String batchLotNumber) {
      this.batchLotNumber = batchLotNumber;
      return this;
    }

    public Lgtin build() {
      return new Lgtin(
          notNullOrEmpty(companyPrefix),
          notNullOrEmpty(indicator),
          notNullOrEmpty(itemReference),
          notNullOrEmpty(batchLotNumber));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
