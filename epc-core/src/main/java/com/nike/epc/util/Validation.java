/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.util;

import java.util.Collection;

public interface Validation {
  public static <T> T nonNull(T t) {
    if (t == null) {
      throw new IllegalArgumentException("Required field was null");
    }

    return t;
  }

  public static String notNullOrEmpty(String s) {
    if (s == null) {
      throw new IllegalArgumentException("Required string was null");

    } else if (s.isEmpty()) {
      throw new IllegalArgumentException("Required string was empty");
    }

    return s;
  }

  public static <T, C extends Collection<T>> C notNullOrEmpty(C c) {
    if (c == null) {
      throw new IllegalArgumentException("Required collection was null");

    } else if (c.isEmpty()) {
      throw new IllegalArgumentException("Required collection was empty");
    }

    return c;
  }

  public static <A, B> A oneOf(A a, B b) {
    if (a == null) {
      if (b == null) {
        throw new IllegalArgumentException("Required oneOf was empty");
      }

    } else if (b != null) {
      throw new IllegalArgumentException("Required oneOf had too many members");
    }

    return a;
  }
}
