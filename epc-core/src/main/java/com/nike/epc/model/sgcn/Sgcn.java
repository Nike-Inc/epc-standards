/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.sgcn;

import com.nike.epc.model.DecodedUri;

import static com.nike.epc.util.Validation.notNullOrEmpty;

/**
 * From the standard: The Global Coupon Number EPC scheme is used to assign a unique identity to a
 * coupon.
 *
 * <p>As with SGTIN, the 'S' in SGCN stands for Serialised.
 *
 * <p>In the epc uris, the companyPrefix and couponReference will both be zero padded such that when
 * they are concatenated, the resultant string is 12 digits.
 */
public class Sgcn extends DecodedUri.Unimplemented {
  private final String companyPrefix, couponReference, serialComponent;

  private Sgcn(String companyPrefix, String couponReference, String serialComponent) {
    this.companyPrefix = companyPrefix;
    this.couponReference = couponReference;
    this.serialComponent = serialComponent;
  }

  public String companyPrefix() {
    return companyPrefix;
  }

  public String couponReference() {
    return couponReference;
  }

  public String serialComponent() {
    return serialComponent;
  }

  public static final class Builder {
    private String companyPrefix, couponReference, serialComponent;

    private Builder() {}

    public Builder withCompanyPrefix(String companyPrefix) {
      this.companyPrefix = companyPrefix;
      return this;
    }

    public Builder withCouponReference(String couponReference) {
      this.couponReference = couponReference;
      return this;
    }

    public Builder withSerialComponent(String serialComponent) {
      this.serialComponent = serialComponent;
      return this;
    }

    public Sgcn build() {
      return new Sgcn(
          notNullOrEmpty(companyPrefix),
          notNullOrEmpty(couponReference),
          notNullOrEmpty(serialComponent));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
