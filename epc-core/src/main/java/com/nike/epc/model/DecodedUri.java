/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model;

public interface DecodedUri {
  String tagUri();

  String pureIdentityUri();

  public abstract static class Unimplemented implements DecodedUri {
    @Override
    public String tagUri() {
      throw new UnsupportedOperationException("tagUri not yet implemented for this scheme");
    }

    @Override
    public String pureIdentityUri() {
      throw new UnsupportedOperationException(
          "pureIdentityUri not yet implemented for this scheme");
    }
  }
}
