/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.sgln;

import static com.nike.epc.util.Validation.notNullOrEmpty;

/**
 * From the standard: The SGLN EPC scheme is used to assign a unique identity to a physical
 * location, such as a specific building or a specific unit of shelving within a warehouse.
 *
 * <p>Note that the letter “S” in the term “SGLN” does not stand for “serialised” as it does in
 * SGTIN. This is because a GLN without an extension also identifies a unique location, as opposed
 * to a class of locations, and so both GLN and GLN with extension may be considered as “serialised”
 * identifiers. The term SGLN merely distinguishes the EPC form, which can be used either for a GLN
 * by itself or GLN with extension, from the term GLN which always refers to the unextended GLN
 * identifier. The letter “S” does not stand for anything.
 *
 * <p>In the epc uris, the companyPrefix and locationReference will both be zero padded such that
 * when they are concatenated, the resultant string is 12 digits.
 */
public class Sgln {
  private final String companyPrefix, locationReference, glnExtension;

  private Sgln(String companyPrefix, String locationReference, String glnExtension) {
    this.companyPrefix = companyPrefix;
    this.locationReference = locationReference;
    this.glnExtension = glnExtension;
  }

  public String companyPrefix() {
    return companyPrefix;
  }

  public String locationReference() {
    return locationReference;
  }

  public String glnExtension() {
    return glnExtension;
  }

  public static final class Builder {
    private String companyPrefix, locationReference, glnExtension;

    private Builder() {}

    public Builder withCompanyPrefix(String companyPrefix) {
      this.companyPrefix = companyPrefix;
      return this;
    }

    public Builder withLocationReference(String locationReference) {
      this.locationReference = locationReference;
      return this;
    }

    public Builder withGlnExtension(String glnExtension) {
      this.glnExtension = glnExtension;
      return this;
    }

    public Sgln build() {
      return new Sgln(
          notNullOrEmpty(companyPrefix),
          notNullOrEmpty(locationReference),
          notNullOrEmpty(glnExtension));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
