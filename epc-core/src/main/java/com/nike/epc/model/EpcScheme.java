/*
 * Copyright 2018-present, Nike, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE file in the root directory of this source tree.
 *
 */
package com.nike.epc.model;

import com.nike.epc.model.adi.*;
import com.nike.epc.model.bic.*;
import com.nike.epc.model.cpi.*;
import com.nike.epc.model.dod.*;
import com.nike.epc.model.gdti.*;
import com.nike.epc.model.giai.*;
import com.nike.epc.model.gid.*;
import com.nike.epc.model.ginc.*;
import com.nike.epc.model.grai.*;
import com.nike.epc.model.gsin.*;
import com.nike.epc.model.gsrn.*;
import com.nike.epc.model.gsrnp.*;
import com.nike.epc.model.itip.*;
import com.nike.epc.model.sgcn.*;
import com.nike.epc.model.sgln.*;
import com.nike.epc.model.sgtin.*;
import com.nike.epc.model.sscc.*;
import com.nike.epc.model.xndt.*;
import java.util.Optional;

/**
 * Each of the EpcUri schemes (the members of this union) corresponds to a GS1 standard. The
 * corresponding standards per the GS1 and EPC specifications are as follows:
 *
 * <p>EPC | GS1 ---------------- SGTIN | GTIN SSCC | SSCC SGLN | GLN GRAI | GRAI GIAI | GIAI GSRN |
 * GSRN GSRNP | GSRN GDTI | GDTI CPI | * SGCN | GCN GINC | GINC GSIN | GSIN ITIP | 8006 GID | * DOD
 * | * ADI | * BIC | *
 *
 * <p>* - No corresponding GS1 standard
 */
public class EpcScheme {
  private final Sgtin sgtin;
  private final Sscc sscc;
  private final Sgln sgln;
  private final Grai grai;
  private final Giai giai;
  private final Gsrn gsrn;
  private final Gsrnp gsrnp;
  private final Gdti gdti;
  private final Cpi cpi;
  private final Sgcn sgcn;
  private final Ginc ginc;
  private final Gsin gsin;
  private final Itip itip;
  private final Gid gid;
  private final Dod dod;
  private final Adi adi;
  private final Bic bic;
  private final Xndt xndt;

  private final DecodedUri decodedEpc;

  private EpcScheme(Sgtin sgtin) {
    this.decodedEpc = this.sgtin = sgtin;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme sgtin(Sgtin sgtin) {
    return new EpcScheme(sgtin);
  }

  public boolean isSgtin() {
    return sgtin != null;
  }

  public Optional<Sgtin> sgtin() {
    return Optional.ofNullable(sgtin);
  }

  private EpcScheme(Sscc sscc) {
    this.sgtin = null;
    this.decodedEpc = this.sscc = sscc;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme sscc(Sscc sscc) {
    return new EpcScheme(sscc);
  }

  public boolean isSscc() {
    return sscc != null;
  }

  public Optional<Sscc> sscc() {
    return Optional.ofNullable(sscc);
  }

  private EpcScheme(Sgln sgln) {
    this.sgtin = null;
    this.sscc = null;
    this.decodedEpc = this.sgln = sgln;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme sgln(Sgln sgln) {
    return new EpcScheme(sgln);
  }

  public boolean isSgln() {
    return sgln != null;
  }

  public Optional<Sgln> sgln() {
    return Optional.ofNullable(sgln);
  }

  private EpcScheme(Grai grai) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.decodedEpc = this.grai = grai;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme grai(Grai grai) {
    return new EpcScheme(grai);
  }

  public boolean isGrai() {
    return grai != null;
  }

  public Optional<Grai> grai() {
    return Optional.ofNullable(grai);
  }

  private EpcScheme(Giai giai) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.decodedEpc = this.giai = giai;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme giai(Giai giai) {
    return new EpcScheme(giai);
  }

  public boolean isGiai() {
    return giai != null;
  }

  public Optional<Giai> giai() {
    return Optional.ofNullable(giai);
  }

  private EpcScheme(Gsrn gsrn) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.decodedEpc = this.gsrn = gsrn;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme gsrn(Gsrn gsrn) {
    return new EpcScheme(gsrn);
  }

  public boolean isGsrn() {
    return gsrn != null;
  }

  public Optional<Gsrn> gsrn() {
    return Optional.ofNullable(gsrn);
  }

  private EpcScheme(Gsrnp gsrnp) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.decodedEpc = this.gsrnp = gsrnp;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme gsrnp(Gsrnp gsrnp) {
    return new EpcScheme(gsrnp);
  }

  public boolean isGsrnp() {
    return gsrnp != null;
  }

  public Optional<Gsrnp> gsrnp() {
    return Optional.ofNullable(gsrnp);
  }

  private EpcScheme(Gdti gdti) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.decodedEpc = this.gdti = gdti;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme gdti(Gdti gdti) {
    return new EpcScheme(gdti);
  }

  public boolean isGdti() {
    return gdti != null;
  }

  public Optional<Gdti> gdti() {
    return Optional.ofNullable(gdti);
  }

  private EpcScheme(Cpi cpi) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.decodedEpc = this.cpi = cpi;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme cpi(Cpi cpi) {
    return new EpcScheme(cpi);
  }

  public boolean isCpi() {
    return cpi != null;
  }

  public Optional<Cpi> cpi() {
    return Optional.ofNullable(cpi);
  }

  private EpcScheme(Sgcn sgcn) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.decodedEpc = this.sgcn = sgcn;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme sgcn(Sgcn sgcn) {
    return new EpcScheme(sgcn);
  }

  public boolean isSgcn() {
    return sgcn != null;
  }

  public Optional<Sgcn> sgcn() {
    return Optional.ofNullable(sgcn);
  }

  private EpcScheme(Ginc ginc) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.decodedEpc = this.ginc = ginc;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme ginc(Ginc ginc) {
    return new EpcScheme(ginc);
  }

  public boolean isGinc() {
    return ginc != null;
  }

  public Optional<Ginc> ginc() {
    return Optional.ofNullable(ginc);
  }

  private EpcScheme(Gsin gsin) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.decodedEpc = this.gsin = gsin;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme gsin(Gsin gsin) {
    return new EpcScheme(gsin);
  }

  public boolean isGsin() {
    return gsin != null;
  }

  public Optional<Gsin> gsin() {
    return Optional.ofNullable(gsin);
  }

  private EpcScheme(Itip itip) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.decodedEpc = this.itip = itip;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme itip(Itip itip) {
    return new EpcScheme(itip);
  }

  public boolean isItip() {
    return itip != null;
  }

  public Optional<Itip> itip() {
    return Optional.ofNullable(itip);
  }

  private EpcScheme(Gid gid) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.decodedEpc = this.gid = gid;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme gid(Gid gid) {
    return new EpcScheme(gid);
  }

  public boolean isGid() {
    return gid != null;
  }

  public Optional<Gid> gid() {
    return Optional.ofNullable(gid);
  }

  private EpcScheme(Dod dod) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.decodedEpc = this.dod = dod;
    this.adi = null;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme dod(Dod dod) {
    return new EpcScheme(dod);
  }

  public boolean isDod() {
    return dod != null;
  }

  public Optional<Dod> dod() {
    return Optional.ofNullable(dod);
  }

  private EpcScheme(Adi adi) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.decodedEpc = this.adi = adi;
    this.bic = null;
    this.xndt = null;
  }

  public static EpcScheme adi(Adi adi) {
    return new EpcScheme(adi);
  }

  public boolean isAdi() {
    return adi != null;
  }

  public Optional<Adi> adi() {
    return Optional.ofNullable(adi);
  }

  private EpcScheme(Bic bic) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.decodedEpc = this.bic = bic;
    this.xndt = null;
  }

  public static EpcScheme bic(Bic bic) {
    return new EpcScheme(bic);
  }

  public boolean isBic() {
    return bic != null;
  }

  public Optional<Bic> bic() {
    return Optional.ofNullable(bic);
  }

  private EpcScheme(Xndt xndt) {
    this.sgtin = null;
    this.sscc = null;
    this.sgln = null;
    this.grai = null;
    this.giai = null;
    this.gsrn = null;
    this.gsrnp = null;
    this.gdti = null;
    this.cpi = null;
    this.sgcn = null;
    this.ginc = null;
    this.gsin = null;
    this.itip = null;
    this.gid = null;
    this.dod = null;
    this.adi = null;
    this.bic = null;
    this.decodedEpc = this.xndt = xndt;
  }

  public static EpcScheme xndt(Xndt xndt) {
    return new EpcScheme(xndt);
  }

  public boolean isXndt() {
    return xndt != null;
  }

  public Optional<Xndt> xndt() {
    return Optional.ofNullable(xndt);
  }

  public String tagUri() {
    return this.decodedEpc.tagUri();
  }

  public String pureIdentityUri() {
    return this.decodedEpc.pureIdentityUri();
  }
}
