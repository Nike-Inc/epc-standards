/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model;

import com.nike.epc.model.EpcScheme;
import com.nike.epc.model.sgtin.Sgtin;
import com.nike.epc.model.xndt.Xndt;
import java.util.function.Function;
import java.util.Optional;

import java.util.Date;

abstract class UriEncoder {
  static String encodeTagUri(EpcScheme scheme) throws UnsupportedOperationException {
    return scheme
        .sgtin()
        .map(sgtinEncoder::encodeTagUri)
        .map(Optional::of)
        .orElseGet(() -> scheme.xndt().map(xndtEncoder::encodeTagUri))
        .orElseThrow(
            () -> new UnsupportedOperationException("tagUri not yet implemented for this scheme"));
  }

  static String encodePureIdentityUri(EpcScheme scheme) throws UnsupportedOperationException {
    return scheme
        .sgtin()
        .map(sgtinEncoder::encodePureIdentityUri)
        .map(Optional::of)
        .orElseGet(() -> scheme.xndt().map(xndtEncoder::encodePureIdentityUri))
        .orElseThrow(
            () ->
                new UnsupportedOperationException(
                    "pureIdentityUri not yet implemented for this scheme"));
  }

  private interface Encoder<A> {
    String encodeTagUri(A a);

    String encodePureIdentityUri(A a);
  }

  private static Encoder<Sgtin> sgtinEncoder =
      new Encoder<Sgtin>() {

        public String encodeTagUri(Sgtin sgtin) {
          return String.format(
              "urn:epc:tag:sgtin-%d:%s.%s.%s.%s",
              sgtin.size(),
              sgtin.filter(),
              sgtin.companyPrefix(),
              sgtin.itemReference(),
              sgtin.serialNumber());
        }

        public String encodePureIdentityUri(Sgtin sgtin) {
          return String.format(
              "urn:epc:id:sgtin:%s.%s.%s",
              sgtin.companyPrefix(), sgtin.itemReference(), sgtin.serialNumber());
        }
      };

  private static Encoder<Xndt> xndtEncoder =
      new Encoder<Xndt>() {
        public String encodeTagUri(Xndt xndt) {
          return String.format(
              "urn:epc:tag:xndt-%d:%d.%s.%s.%s",
              xndt.size(), xndt.displayCode(), xndt.style(), xndt.color(), xndt.serialNumber());
        }

        public String encodePureIdentityUri(Xndt xndt) {
          return String.format(
              "urn:epc:id:xndt:%s.%s.%s.%s",
              xndt.displayCode(), xndt.style(), xndt.color(), xndt.serialNumber());
        }
      };
}
