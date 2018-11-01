/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.grai;

import static com.nike.epc.util.Validation.notNullOrEmpty;

/**
 * From the standard: The Global Returnable Asset Identifier EPC scheme is used to assign a unique
 * identity to a specific returnable asset, such as a reusable shipping container or a pallet skid.
 *
 * <p>In the epc uris, the companyPrefix and assetType will both be zero padded such that when they
 * are concatenated, the resultant string is 12 digits.
 */
public class Grai {
  private final String companyPrefix, assetType, serialNumber;

  private Grai(String companyPrefix, String assetType, String serialNumber) {
    this.companyPrefix = companyPrefix;
    this.assetType = assetType;
    this.serialNumber = serialNumber;
  }

  public String companyPrefix() {
    return companyPrefix;
  }

  public String assetType() {
    return assetType;
  }

  public String serialNumber() {
    return serialNumber;
  }

  public static final class Builder {
    private String companyPrefix, assetType, serialNumber;

    private Builder() {}

    public Builder withCompanyPrefix(String companyPrefix) {
      this.companyPrefix = companyPrefix;
      return this;
    }

    public Builder withAssetType(String assetType) {
      this.assetType = assetType;
      return this;
    }

    public Builder withSerialNumber(String serialNumber) {
      this.serialNumber = serialNumber;
      return this;
    }

    public Grai build() {
      return new Grai(
          notNullOrEmpty(companyPrefix), notNullOrEmpty(assetType), notNullOrEmpty(serialNumber));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
