/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.sgtin;

public class TableItem {
  private int partitionValue;
  private int l;
  private int m;
  private int n;
  private int digits;

  public TableItem(int partitionValue, int m, int l, int n, int digits) {
    this.partitionValue = partitionValue;
    this.m = m;
    this.l = l;
    this.n = n;
    this.digits = digits;
  }

  public int getPartitionValue() {
    return partitionValue;
  }

  public int getL() {
    return l;
  }

  public int getM() {
    return m;
  }

  public int getN() {
    return n;
  }

  public int getDigits() {
    return digits;
  }
}
