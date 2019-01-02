/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.bic;

import com.nike.epc.model.DecodedUri;
import com.nike.epc.util.Validation;

/**
 * From the standard: ISO 6346 is an international standard covering the coding, identification and
 * marking of intermodal (shipping) containers used within containerized intermodal freight
 * transport. The standard establishes a visual identification system for every container that
 * includes a unique serial number (with check digit), the owner, a country code, a size, type and
 * equipment category as well as any operational marks. The standard is managed by the International
 * Container Bureau (BIC).
 *
 * <p>In the epc uris, the individual elements of the BIC are not separated by dots.
 */
public class Bic extends DecodedUri.Unimplemented {
  private final String ownerCode, equipmentCategoryIdentifier, serialNumber, checkDigit;

  private Bic(
      String ownerCode,
      String equipmentCategoryIdentifier,
      String serialNumber,
      String checkDigit) {
    this.ownerCode = ownerCode;
    this.equipmentCategoryIdentifier = equipmentCategoryIdentifier;
    this.serialNumber = serialNumber;
    this.checkDigit = checkDigit;
  }

  public String ownerCode() {
    return ownerCode;
  }

  public String equipmentCategoryIdentifier() {
    return equipmentCategoryIdentifier;
  }

  public String serialNumber() {
    return serialNumber;
  }

  public String checkDigit() {
    return checkDigit;
  }

  public static final class Builder {
    private String ownerCode, equipmentCategoryIdentifier, serialNumber, checkDigit;

    private Builder() {}

    public Builder withOwnerCode(String ownerCode) {
      this.ownerCode = ownerCode;
      return this;
    }

    public Builder withEquipmentCategoryIdentifier(String equipmentCategoryIdentifier) {
      this.equipmentCategoryIdentifier = equipmentCategoryIdentifier;
      return this;
    }

    public Builder withSerialNumber(String serialNumber) {
      this.serialNumber = serialNumber;
      return this;
    }

    public Builder withCheckDigit(String checkDigit) {
      this.checkDigit = checkDigit;
      return this;
    }

    public Bic build() {
      return new Bic(
          Validation.notNullOrEmpty(ownerCode),
          Validation.notNullOrEmpty(equipmentCategoryIdentifier),
          Validation.notNullOrEmpty(serialNumber),
          Validation.notNullOrEmpty(checkDigit));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
