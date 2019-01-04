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

abstract class UriSerializer {
  static String serializeTagUri(EpcScheme scheme) throws UnsupportedOperationException {
    return scheme
        .sgtin()
        .map(sgtinSerializer::serializeTagUri)
        .map(Optional::of)
        .orElseGet(() -> scheme.xndt().map(xndtSerializer::serializeTagUri))
        .orElseThrow(
            () -> new UnsupportedOperationException("tagUri not yet implemented for this scheme"));
  }

  static String serializePureIdentityUri(EpcScheme scheme) throws UnsupportedOperationException {
    return scheme
        .sgtin()
        .map(sgtinSerializer::serializePureIdentityUri)
        .map(Optional::of)
        .orElseGet(() -> scheme.xndt().map(xndtSerializer::serializePureIdentityUri))
        .orElseThrow(
            () ->
                new UnsupportedOperationException(
                    "pureIdentityUri not yet implemented for this scheme"));
  }

  private interface Serializer<A> {
    String serializeTagUri(A a);

    String serializePureIdentityUri(A a);
  }

  private static Serializer<Sgtin> sgtinSerializer =
      new Serializer<Sgtin>() {
        public String serializeTagUri(Sgtin sgtin) {
          return String.format(
              "urn:epc:tag:sgtin-%d:%s.%s.%s.%s",
              sgtin.size(),
              sgtin.filter(),
              sgtin.companyPrefix(),
              sgtin.itemReference(),
              sgtin.serialNumber());
        }

        public String serializePureIdentityUri(Sgtin sgtin) {
          return String.format(
              "urn:epc:id:sgtin:%s.%s.%s",
              sgtin.companyPrefix(), sgtin.itemReference(), sgtin.serialNumber());
        }
      };

  private static Serializer<Xndt> xndtSerializer =
      new Serializer<Xndt>() {
        public String serializeTagUri(Xndt xndt) {
          return String.format(
              "urn:epc:tag:xndt-%d:%d.%s.%s.%s",
              xndt.size(), xndt.displayCode(), xndt.style(), xndt.color(), xndt.serialNumber());
        }

        public String serializePureIdentityUri(Xndt xndt) {
          return String.format(
              "urn:epc:id:xndt:%s.%s.%s.%s",
              xndt.displayCode(), xndt.style(), xndt.color(), xndt.serialNumber());
        }
      };
}
