/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.gid;

import static com.nike.epc.util.Validation.notNullOrEmpty;

/**
 * From the standard: The General Identifier EPC scheme is independent of any specifications or
 * identity scheme outsdie the EPCglobal Tag Data Standard.
 *
 * <p>The generalManagerNumber dentifies an organisational entity responsible for maintaining the
 * numbers in the other fields as unique.
 */
public class Gid {
  private final String generalManagerNumber, objectClass, serialNumber;

  private Gid(String generalManagerNumber, String objectClass, String serialNumber) {
    this.generalManagerNumber = generalManagerNumber;
    this.objectClass = objectClass;
    this.serialNumber = serialNumber;
  }

  public String generalManagerNumber() {
    return generalManagerNumber;
  }

  public String objectClass() {
    return objectClass;
  }

  public String serialNumber() {
    return serialNumber;
  }

  public static final class Builder {
    private String generalManagerNumber, objectClass, serialNumber;

    private Builder() {}

    public Builder withGeneralManagerNumber(String generalManagerNumber) {
      this.generalManagerNumber = generalManagerNumber;
      return this;
    }

    public Builder withObjectClass(String objectClass) {
      this.objectClass = objectClass;
      return this;
    }

    public Builder withSerialNumber(String serialNumber) {
      this.serialNumber = serialNumber;
      return this;
    }

    public Gid build() {
      return new Gid(
          notNullOrEmpty(generalManagerNumber),
          notNullOrEmpty(objectClass),
          notNullOrEmpty(serialNumber));
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
