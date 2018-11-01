/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.sgtin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SgtinPartitionTableListTest {

  @Test
  public void getPartitionByValueTest() {
    assertTableItem(SgtinPartitionTableList.getPartitionByValue(0), new TableItem(0, 40, 12, 4, 1));
    assertTableItem(SgtinPartitionTableList.getPartitionByValue(100), null);
  }

  private void assertTableItem(TableItem given, TableItem expected) {
    boolean result =
        (given == expected)
            || (given != null
                && expected != null
                && given.getPartitionValue() == expected.getPartitionValue()
                && given.getL() == expected.getL()
                && given.getM() == expected.getM()
                && given.getN() == expected.getN()
                && given.getDigits() == expected.getDigits());
    assertTrue(result);
  }
}
