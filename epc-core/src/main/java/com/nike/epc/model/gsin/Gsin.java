/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.gsin;

import com.nike.epc.model.DecodedUri;

import static com.nike.epc.util.Validation.notNullOrEmpty;

/**
 * From the standard: The Global Shipment Identification Number EPC scheme is used to assign a
 * unique identity to a logical grouping of logistic units for the purpose of a transport shipment
 * from that consignor (seller) to the consignee (buyer).
 *
 * <p>In the epc uris, the companyPrefix and shipperReference will both be zero padded such that
 * when they are concatenated, the resultant string is 17 digits.
 */
public class Gsin extends DecodedUri.Unimplemented {
  private final String companyPrefix, shipperReference;

  private Gsin(String companyPrefix, String shipperReference) {
    this.companyPrefix = companyPrefix;
    this.shipperReference = shipperReference;
  }

  public String companyPrefix() {
    return companyPrefix;
  }

  public String shipperReference() {
    return shipperReference;
  }

  public static final class Builder {
    private String companyPrefix, shipperReference;

    private Builder() {}

    public Builder withCompanyPrefix(String companyPrefix) {
      this.companyPrefix = companyPrefix;
      return this;
    }

    public Builder withShipperReference(String shipperReference) {
      this.shipperReference = shipperReference;
      return this;
    }

    public Gsin build() {
      return new Gsin(notNullOrEmpty(companyPrefix), notNullOrEmpty(shipperReference));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
