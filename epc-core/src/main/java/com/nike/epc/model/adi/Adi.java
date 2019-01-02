/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.adi;

import com.nike.epc.model.DecodedUri;
import com.nike.epc.util.Validation;

/**
 * From the standard: The variable-length Aerospace and Defense EPC identifier is designed for use
 * by the aerospace and defense sector for the unique identification of parts or items. The existing
 * unique identifier constructs are defined in the Air Transport Association (ATA) Spec 2000
 * standard [SPEC2000], and the US Department of Defense Guide to Uniquely Identifying items [UID].
 * The ADI EPC construct provides a mechanism to directly encode such unique identifiers in RFID
 * tags and to use the URI representations at other layers of the EPCglobal architecture.
 */
public class Adi extends DecodedUri.Unimplemented {
  private final String cageCode, dodAac, adiComponent, adiExtendedComponent;

  private Adi(String cageCode, String dodAac, String adiComponent, String adiExtendedComponent) {
    this.cageCode = cageCode;
    this.dodAac = dodAac;
    this.adiComponent = adiComponent;
    this.adiExtendedComponent = adiExtendedComponent;
  }

  public String cageCode() {
    return cageCode;
  }

  public String dodAac() {
    return dodAac;
  }

  public String adiComponenet() {
    return adiComponent;
  }

  public String adiExtendedComponent() {
    return adiExtendedComponent;
  }

  public static final class Builder {
    private String cageCode, dodAac, adiComponent, adiExtendedComponent;

    private Builder() {}

    public Builder withCageCode(String cageCode) {
      this.cageCode = cageCode;
      return this;
    }

    public Builder withDodAac(String dodAac) {
      this.dodAac = dodAac;
      return this;
    }

    public Builder withAdiComponent(String adiComponent) {
      this.adiComponent = adiComponent;
      return this;
    }

    public Builder withAdiExtendedComponent(String adiExtendedComponent) {
      this.adiExtendedComponent = adiExtendedComponent;
      return this;
    }

    public Adi build() {
      return new Adi(
          Validation.oneOf(cageCode, dodAac),
          Validation.oneOf(dodAac, cageCode),
          Validation.notNullOrEmpty(adiComponent),
          Validation.notNullOrEmpty(adiExtendedComponent));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
