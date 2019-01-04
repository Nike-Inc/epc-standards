/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.giai;

import static com.nike.epc.util.Validation.notNullOrEmpty;

/**
 * From the standard: The Global Individual Asset Identifier EPC scheme is used to assign a unique
 * identity to a specific asset, such as a forklift or a computer.
 *
 * <p>In the epc uris, the companyPrefix may be zero padded.
 */
public class Giai {
  private final String companyPrefix, individualAssetReference;

  private Giai(String companyPrefix, String individualAssetReference) {
    this.companyPrefix = companyPrefix;
    this.individualAssetReference = individualAssetReference;
  }

  public String companyPrefix() {
    return companyPrefix;
  }

  public String individualAssetReference() {
    return individualAssetReference;
  }

  public static final class Builder {
    private String companyPrefix, individualAssetReference;

    private Builder() {}

    public Builder withCompanyPrefix(String companyPrefix) {
      this.companyPrefix = companyPrefix;
      return this;
    }

    public Builder withIndividualAssetReference(String individualAssetReference) {
      this.individualAssetReference = individualAssetReference;
      return this;
    }

    public Giai build() {
      return new Giai(notNullOrEmpty(companyPrefix), notNullOrEmpty(individualAssetReference));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
