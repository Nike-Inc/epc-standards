/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model.sgtin;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface SgtinPartitionTableList {
  static final Map<Integer, TableItem> tableItems =
      Collections.unmodifiableMap(
          Stream.of(
                  new SimpleEntry<>(0, new TableItem(0, 40, 12, 4, 1)),
                  new SimpleEntry<>(1, new TableItem(1, 37, 11, 7, 2)),
                  new SimpleEntry<>(2, new TableItem(2, 34, 10, 10, 3)),
                  new SimpleEntry<>(3, new TableItem(3, 30, 9, 14, 4)),
                  new SimpleEntry<>(4, new TableItem(4, 27, 8, 17, 5)),
                  new SimpleEntry<>(5, new TableItem(5, 24, 7, 20, 6)),
                  new SimpleEntry<>(6, new TableItem(6, 20, 6, 24, 7)))
              .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())));

  public static TableItem getPartitionByValue(int index) {
    return tableItems.get(index);
  }
}
