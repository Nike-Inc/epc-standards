/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.decode;

import com.nike.epc.model.*;
import com.nike.epc.model.sgtin.*;
import com.nike.epc.model.xndt.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecodingTest {

  @Test
  public void parseUnprogrammed() throws Exception {

    Epc epc = Decoding.decode("000000000000000000000000");

    // check app id
    // assertEquals("idk", epc.applicationIdentifier());
    assertEquals(0, epc.filter());
    assertEquals(0, epc.partition());
    assertEquals(0, epc.size());

    // check scheme
    assertEquals(null, epc.scheme());
  }

  @Test
  public void parseSgtin() throws Exception {
    Epc epc = Decoding.decode("30185E78003ECB006E2DA00E");

    assertEquals("AI 414 + AI 254", epc.applicationIdentifier());
    assertEquals(0, epc.filter());
    assertEquals(6, epc.partition());
    assertEquals(96, epc.size());
    assertEquals("urn:epc:id:sgtin:096736.0064300.1848483854", epc.pureIdentityUri());
    assertEquals("urn:epc:tag:sgtin-96:0.096736.0064300.1848483854", epc.tagUri());

    // check scheme
    EpcScheme scheme = epc.scheme();
    assertEquals(true, scheme.isSgtin());
  }

  @Test
  public void parseXndt() throws Exception {
    Epc epc = Decoding.decode("E31019F59F59F5965900000A");

    assertEquals("UNSPECIFIED", epc.applicationIdentifier());
    assertEquals(0, epc.filter());
    assertEquals(4, epc.partition());
    assertEquals(96, epc.size());

    EpcScheme scheme = epc.scheme();
    assertEquals(true, scheme.isXndt());
  }
}
