/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.ginc;

import static com.nike.epc.util.Validation.notNullOrEmpty;

/**
 * From the standard: The Global Identification Number for Consignment EPC scheme is used to assign
 * a unique identity to a logical grouping of goods (one or more physical entities) that has been
 * consigned to a freight forwarder and is intended to be transported as a whole.
 *
 * <p>In the epc uris, the companyPrefix may be zero padded.
 */
public class Ginc {
  private final String companyPrefix, consignmentReference;

  private Ginc(String companyPrefix, String consignmentReference) {
    this.companyPrefix = companyPrefix;
    this.consignmentReference = consignmentReference;
  }

  public String companyPrefix() {
    return companyPrefix;
  }

  public String consignmentReference() {
    return consignmentReference;
  }

  public static final class Builder {
    private String companyPrefix, consignmentReference;

    private Builder() {}

    public Builder withCompanyPrefix(String companyPrefix) {
      this.companyPrefix = companyPrefix;
      return this;
    }

    public Builder withConsignmentReference(String consignmentReference) {
      this.consignmentReference = consignmentReference;
      return this;
    }

    public Ginc build() {
      return new Ginc(notNullOrEmpty(companyPrefix), notNullOrEmpty(consignmentReference));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
