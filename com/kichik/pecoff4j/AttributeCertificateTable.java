/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j;

import com.kichik.pecoff4j.util.DataObject;

public class AttributeCertificateTable
extends DataObject {
    private int length;
    private int revision;
    private int certificateType;
    private byte[] certificate;

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getRevision() {
        return this.revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public int getCertificateType() {
        return this.certificateType;
    }

    public void setCertificateType(int certificateType) {
        this.certificateType = certificateType;
    }

    public byte[] getCertificate() {
        return this.certificate;
    }

    public void setCertificate(byte[] certificate) {
        this.certificate = certificate;
    }
}

