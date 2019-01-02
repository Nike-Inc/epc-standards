/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.cpi;

import com.nike.epc.model.DecodedUri;

import com.nike.epc.util.Validation;

/**
 * From the standard: The Component / Part EPC identifier is designed for use by the technical
 * industries (including the automotive sector) for the unique identification of parts or
 * components.
 *
 * <p>In the epc uris, the companyPrefix and componentPartReference may both be zero padded such
 * that when they are concatenated, the resultant string is at most 15 digits.
 */
public class Cpi extends DecodedUri.Unimplemented {
  private final String companyPrefix, componentPartReference, serialNumber;

  private Cpi(String companyPrefix, String componentPartReference, String serialNumber) {
    this.companyPrefix = companyPrefix;
    this.componentPartReference = componentPartReference;
    this.serialNumber = serialNumber;
  }

  public String companyPrefix() {
    return companyPrefix;
  }

  public String componentPartReference() {
    return componentPartReference;
  }

  public String serialNumber() {
    return serialNumber;
  }

  public static final class Builder {
    private String companyPrefix, componentPartReference, serialNumber;

    private Builder() {}

    public Builder withCompanyPrefix(String companyPrefix) {
      this.companyPrefix = companyPrefix;
      return this;
    }

    public Builder withComponentPartReference(String componentPartReference) {
      this.componentPartReference = componentPartReference;
      return this;
    }

    public Builder withSerialNumber(String serialNumber) {
      this.serialNumber = serialNumber;
      return this;
    }

    public Cpi build() {
      return new Cpi(
          Validation.notNullOrEmpty(companyPrefix),
          Validation.notNullOrEmpty(componentPartReference),
          Validation.notNullOrEmpty(serialNumber));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
