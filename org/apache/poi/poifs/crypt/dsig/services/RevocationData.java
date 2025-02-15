/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.dsig.services;

import java.security.cert.CRLException;
import java.security.cert.X509CRL;
import java.util.ArrayList;
import java.util.List;

public class RevocationData {
    private final List<byte[]> crls = new ArrayList<byte[]>();
    private final List<byte[]> ocsps = new ArrayList<byte[]>();

    public void addCRL(byte[] encodedCrl) {
        this.crls.add(encodedCrl);
    }

    public void addCRL(X509CRL crl) {
        byte[] encodedCrl;
        try {
            encodedCrl = crl.getEncoded();
        } catch (CRLException e) {
            throw new IllegalArgumentException("CRL coding error: " + e.getMessage(), e);
        }
        this.addCRL(encodedCrl);
    }

    public void addOCSP(byte[] encodedOcsp) {
        this.ocsps.add(encodedOcsp);
    }

    public List<byte[]> getCRLs() {
        return this.crls;
    }

    public List<byte[]> getOCSPs() {
        return this.ocsps;
    }

    public boolean hasOCSPs() {
        return !this.ocsps.isEmpty();
    }

    public boolean hasCRLs() {
        return !this.crls.isEmpty();
    }

    public boolean hasRevocationDataEntries() {
        return this.hasOCSPs() || this.hasCRLs();
    }
}

