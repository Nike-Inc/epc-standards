/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.gdti;

import com.nike.epc.model.DecodedUri;

import static com.nike.epc.util.Validation.notNullOrEmpty;

/**
 * From the standard: The Global Document Type Identifier EPC scheme is used to assign a unique
 * identity to a specific document, such as land registration papers, an insurance policy, and
 * others.
 *
 * <p>In the epc uris, the companyPrefix and documentType will both be zero padded such that when
 * they are concatenated, the resultant string is 12 digits.
 */
public class Gdti extends DecodedUri.Unimplemented {
  private final String companyPrefix, documentType, serialNumber;

  private Gdti(String companyPrefix, String documentType, String serialNumber) {
    this.companyPrefix = companyPrefix;
    this.documentType = documentType;
    this.serialNumber = serialNumber;
  }

  public String companyPrefix() {
    return companyPrefix;
  }

  public String documentType() {
    return documentType;
  }

  public String serialNumber() {
    return serialNumber;
  }

  public static final class Builder {
    private String companyPrefix, documentType, serialNumber;

    private Builder() {}

    public Builder withCompanyPrefix(String companyPrefix) {
      this.companyPrefix = companyPrefix;
      return this;
    }

    public Builder withDocumentType(String documentType) {
      this.documentType = documentType;
      return this;
    }

    public Builder withSerialNumber(String serialNumber) {
      this.serialNumber = serialNumber;
      return this;
    }

    public Gdti build() {
      return new Gdti(
          notNullOrEmpty(companyPrefix),
          notNullOrEmpty(documentType),
          notNullOrEmpty(serialNumber));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
