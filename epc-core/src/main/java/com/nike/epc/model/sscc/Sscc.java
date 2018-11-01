/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.sscc;

import static com.nike.epc.util.Validation.notNullOrEmpty;

public final class Sscc {
  private final String companyPrefix, extension, serialReference;

  private Sscc(String companyPrefix, String extension, String serialReference) {
    this.companyPrefix = companyPrefix;
    this.extension = extension;
    this.serialReference = serialReference;
  }

  public String companyPrefix() {
    return companyPrefix;
  }

  public String extension() {
    return extension;
  }

  public String serialReference() {
    return serialReference;
  }

  /*
   * Where the checkDigit is calculated by numbering the the digits in the extension, companyPrefix, and serialReference
   *  as d1-d13, and evaluating the following:
   *
   *  (10 – ((3(d1 + d3 + d5 + d7 + d9 + d11 + d13) + (d2 + d4 + d6 + d8 + d10 + d12)) mod 10)) mod 10
   */
  public Integer checkDigit() {
    return 0;
  }

  /*
   * In order to find the GTIN from an SGTIN, concatenate the following:
   *  extension + companyPrefix + serialReference + checkDigit
   */
  public String gs1Sscc() {
    return "";
  }

  public static final class Builder {
    private String companyPrefix, extension, serialReference;

    private Builder() {}

    public Builder withCompanyPrefix(String companyPrefix) {
      this.companyPrefix = companyPrefix;
      return this;
    }

    public Builder withExtension(String extension) {
      this.extension = extension;
      return this;
    }

    public Builder withSerialReference(String itemReference) {
      this.serialReference = itemReference;
      return this;
    }

    public Sscc build() {
      return new Sscc(
          notNullOrEmpty(companyPrefix),
          notNullOrEmpty(extension),
          notNullOrEmpty(serialReference));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
