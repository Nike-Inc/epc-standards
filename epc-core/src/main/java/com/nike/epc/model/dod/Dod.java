/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.dod;

import com.nike.epc.model.DecodedUri;

import static com.nike.epc.util.Validation.notNullOrEmpty;
import static com.nike.epc.util.Validation.oneOf;

/**
 * From the standard: The US Department of Defense identifier is defined by the United States
 * Department of Defense. This tag data construct may be used to encode 96-bit Class 1 tags for
 * shipping goods to the United States Department of Defense by a supplier who has already been
 * assigned a CAGE (Commercial and Government Entity) code.
 *
 * <p>At the time of this writing, the details of what information to encode into these fields is
 * explained in a document titled "United States Department of Defense Supplier's Passive RFID
 * Information Guide" that can be obtained at the United States Department of Defense's web site
 * (http://www.dodrfid.org/supplierguide.htm).
 *
 * <p>Note that the DoD Guide explicitly recognises the value of cross-branch, globally applicable
 * standards, advising that “suppliers that are EPCglobal subscribers and possess a unique [GS1]
 * Company Prefix may use any of the identity types and encoding instructions described in the EPC™
 * Tag Data Standards document to encode tags.”
 */
public class Dod extends DecodedUri.Unimplemented {
  private final String cageCode, dodAac, serialNumber;

  private Dod(String cageCode, String dodAac, String serialNumber) {
    this.cageCode = cageCode;
    this.dodAac = dodAac;
    this.serialNumber = serialNumber;
  }

  public String cageCode() {
    return cageCode;
  }

  public String dodAac() {
    return dodAac;
  }

  public String serialNumber() {
    return serialNumber;
  }

  public static final class Builder {
    private String cageCode, dodAac, serialNumber;

    private Builder() {}

    public Builder withCageCode(String cageCode) {
      this.cageCode = cageCode;
      return this;
    }

    public Builder withDodAac(String dodAac) {
      this.dodAac = dodAac;
      return this;
    }

    public Builder withSerialNumber(String serialNumber) {
      this.serialNumber = serialNumber;
      return this;
    }

    public Dod build() {
      return new Dod(
          oneOf(cageCode, dodAac), oneOf(dodAac, cageCode), notNullOrEmpty(serialNumber));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
