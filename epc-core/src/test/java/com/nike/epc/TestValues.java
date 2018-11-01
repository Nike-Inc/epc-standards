/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc;

import org.apache.commons.lang.RandomStringUtils;

public class TestValues {

  public static String random() {
    return RandomStringUtils.randomAlphanumeric(500);
  }

  public static final String ERROR_REQUIRED = "Required string was null";
  public static final String ERROR_ONE_OF = "Required oneOf was empty";
}
