/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.eac;

import java.io.UnsupportedEncodingException;

public class CertificateHolderReference {
    private static final String ReferenceEncoding = "ISO-8859-1";
    private String countryCode;
    private String holderMnemonic;
    private String sequenceNumber;

    public CertificateHolderReference(String string, String string2, String string3) {
        this.countryCode = string;
        this.holderMnemonic = string2;
        this.sequenceNumber = string3;
    }

    CertificateHolderReference(byte[] byArray) {
        try {
            String string = new String(byArray, ReferenceEncoding);
            this.countryCode = string.substring(0, 2);
            this.holderMnemonic = string.substring(2, string.length() - 5);
            this.sequenceNumber = string.substring(string.length() - 5);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new IllegalStateException(unsupportedEncodingException.toString());
        }
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getHolderMnemonic() {
        return this.holderMnemonic;
    }

    public String getSequenceNumber() {
        return this.sequenceNumber;
    }

    public byte[] getEncoded() {
        String string = this.countryCode + this.holderMnemonic + this.sequenceNumber;
        try {
            return string.getBytes(ReferenceEncoding);
        } catch (UnsupportedEncodingException unsupportedEncodingException) {
            throw new IllegalStateException(unsupportedEncodingException.toString());
        }
    }
}

