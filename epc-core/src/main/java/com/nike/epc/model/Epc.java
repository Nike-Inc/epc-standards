/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model;

import com.nike.epc.util.Converter;

/**
 * The Electronic Product Code (EPC) standard is an extension of the GS1 standard, and specifies the
 * memory contents of all Gen2 RFID tags. More information about the standard can be found at
 * https://www.gs1.org/sites/default/files/docs/epc/GS1_EPC_TDS_i1_11.pdf
 *
 * <p>From the specifications: The Electronic Product Code is a universal identifier for any
 * physical object. It is used in information systems that need to track or otherwise refer to
 * physical objects. Within computer systems, including electronic documents, databases, and
 * electronic messages, the EPC takes the form of an Internet Uniform Resource Identifier (URI).
 * This is true regardless of whether the EPC was originally read from an RFID tag or some other
 * kind of data carrier. This URI is called the “Pure Identity EPC URI.” The following is an example
 * of a Pure Identity EPC URI: urn:epc:id:sgtin:0614141.112345.400
 *
 * <p>A very large subset of applications that use the Electronic Product Code also rely upon RFID
 * Tags as a data carrier. RFID is often a very appropriate data carrier technology to use for
 * applications involving visibility of physical objects, because RFID permits data to be physically
 * attached to an object such that reading the data is minimally invasive to material handling
 * processes. For this reason, a large part of the Tag Data Standard is concerned with the encoding
 * of Electronic Product Codes onto RFID tags, along with defining the standards for other data
 * apart from the EPC that may be stored on a Gen 2 RFID tag. Owing to memory limitations of RFID
 * tags, the EPC is not stored in URI form on the tag, but is instead encoded into a compact binary
 * representation. This is called the “EPC Binary Encoding.”
 *
 * <p>Therefore, the two broad areas covered by the Tag Data Standard (the EPC and RFID) overlap in
 * the parts where the encoding of the EPC onto RFID tags is discussed. Nevertheless, it should
 * always be remembered that the EPC and RFID are not at all synonymous: EPC is an identifier, and
 * RFID is a data carrier. RFID tags contain other data besides EPC identifiers (and in some
 * applications may not carry an EPC identifier at all), and the EPC identifier exists in non-RFID
 * contexts (those non-RFID contexts currently including the URI form used within information
 * systems, printed human-readable EPC URIs, and EPC identifiers derived from barcode data following
 * the procedures in this standard).
 *
 * <p>The term “Electronic Product Code” (or “EPC”) is used when referring to the EPC regardless of
 * the concrete form used to represent it. The term “Pure Identity EPC URI” is used to refer
 * specifically to the text form the EPC takes within computer systems, including electronic
 * documents, databases, and electronic messages. The term “EPC Binary Encoding” is used
 * specifically to refer to the form the EPC takes within the memory of RFID tags.
 */
public class Epc {
  private final String applicationIdentifier;
  private final int filter;
  private final int partition;
  private final int size;
  private final EpcScheme scheme;
  private final RawBits bits;

  public Epc(
      String applicationIdentifier,
      int filter,
      int partition,
      int size,
      EpcScheme scheme,
      RawBits bits) {
    this.applicationIdentifier = applicationIdentifier;
    this.filter = filter;
    this.partition = partition;
    this.size = size;
    this.scheme = scheme;
    this.bits = bits;
  }

  public static Epc fromTagUri(String tagUri) {
    throw new IllegalArgumentException("fromTagUri not yet implemented");
  }

  /**
   * The generally-prefered way to denote a specific physical object. Contains no RFID control
   * metadata.
   *
   * <p>These identity uris should be formatted as follows for the corresponding EpcScheme:
   *
   * <p>SGTIN: urn:epc:id:sgtin:PaddedCompanyPrefix.PaddedItemRef.SerialNumber
   */
  public String pureIdentityUri() {
    if (scheme.isSgtin()) {
      return scheme
          .sgtin()
          .map(
              sgtin ->
                  String.format(
                      "urn:epc:id:sgtin:%s.%s.%s",
                      sgtin.companyPrefix(), sgtin.itemReference(), sgtin.serialNumber()))
          .orElseThrow(() -> new IllegalStateException("tag appeared to be an sgtin, but wasn't"));
    } else {
      throw new IllegalArgumentException("pureIdentityUri not yet implemented for this scheme");
    }
  }

  /*
   * Identifier for a specific physical object, along with settings for control metadata. This notation method is
   *  preferable when reading an rfid tag when control information is of interest to the capturing application.
   *  This is the necessary notation method when attempting to write an RFID tag, because the control metadata is
   *  required at write time.
   */
  public String tagUri() {
    if (scheme.isSgtin()) {
      return scheme
          .sgtin()
          .map(
              sgtin ->
                  String.format(
                      "urn:epc:tag:sgtin-%d:%s.%s.%s.%s",
                      size,
                      filter,
                      sgtin.companyPrefix(),
                      sgtin.itemReference(),
                      sgtin.serialNumber()))
          .orElseThrow(() -> new IllegalStateException("tag appeared to be an sgtin, but wasn't"));

    } else {
      throw new IllegalArgumentException("tagUri not yet implemented for this scheme");
    }
  }

  private static final Integer SGTIN_96_SERIAL_BIT_SIZE = 38;
  private static final Integer SGTIN_198_SERIAL_BIT_SIZE = 140;

  /*
   * A compressed encoding of the EPC and all control metadata in compact binary form. There is a 1-to-1 relationship
   *  between this field and the tagUri, so they should be symmetrically encodable/decodable.
   */
  public String rfidBinary() {
    return this.bits.binary();
  }

  public String applicationIdentifier() {
    return applicationIdentifier;
  }

  public int filter() {
    return filter;
  }

  public int partition() {
    return partition;
  }

  public int size() {
    return size;
  }

  public EpcScheme scheme() {
    return scheme;
  }
}
