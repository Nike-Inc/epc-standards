/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.itip;

import static com.nike.epc.util.Validation.notNullOrEmpty;

/**
 * From the standard: The Individual Trade Item Piece EPC scheme is used to assign a unique identity
 * to a subordinate element of a trade item (e.g., left and right shoes, suit trousers and jacket,
 * DIY trade item consisting of several physical units), the latter of which comprises multiple
 * pieces.
 *
 * <p>In the epc uris, the companyPrefix and itemReference will both be zero padded such that when
 * the companyPrefix, itemReference, and indicator are concatenated, the resultant string is 13
 * digits. Additionally, the piece and total will both be zero padded such that when they are
 * concatenated, the resultant string is 4 digits. The result of concatenating the companyPrefix,
 * itemReference, indicator, piece, and total, will have 17 digits.
 */
public class Itip {
  private final String companyPrefix, itemReference, indicator, piece, total, serialNumber;

  private Itip(
      String companyPrefix,
      String itemReference,
      String indicator,
      String piece,
      String total,
      String serialNumber) {

    this.companyPrefix = companyPrefix;
    this.itemReference = itemReference;
    this.indicator = indicator;
    this.piece = piece;
    this.total = total;
    this.serialNumber = serialNumber;
  }

  public String companyPrefix() {
    return companyPrefix;
  }

  public String itemReference() {
    return itemReference;
  }

  public String indicator() {
    return indicator;
  }

  public String piece() {
    return piece;
  }

  public String total() {
    return total;
  }

  public String serialNumber() {
    return serialNumber;
  }

  public static final class Builder {
    private String companyPrefix, itemReference, indicator, piece, total, serialNumber;

    private Builder() {}

    public Builder withCompanyPrefix(String companyPrefix) {
      this.companyPrefix = companyPrefix;
      return this;
    }

    public Builder withItemReference(String itemReference) {
      this.itemReference = itemReference;
      return this;
    }

    public Builder withIndicator(String indicator) {
      this.indicator = indicator;
      return this;
    }

    public Builder withPiece(String piece) {
      this.piece = piece;
      return this;
    }

    public Builder withTotal(String total) {
      this.total = total;
      return this;
    }

    public Builder withSerialNumber(String serialNumber) {
      this.serialNumber = serialNumber;
      return this;
    }

    public Itip build() {
      return new Itip(
          notNullOrEmpty(companyPrefix),
          notNullOrEmpty(itemReference),
          notNullOrEmpty(indicator),
          notNullOrEmpty(piece),
          notNullOrEmpty(total),
          notNullOrEmpty(serialNumber));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
