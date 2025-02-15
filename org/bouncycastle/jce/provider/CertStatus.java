/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import java.util.Date;

class CertStatus {
    public static final int UNREVOKED = 11;
    public static final int UNDETERMINED = 12;
    int certStatus = 11;
    Date revocationDate = null;

    CertStatus() {
    }

    public Date getRevocationDate() {
        return this.revocationDate;
    }

    public void setRevocationDate(Date date) {
        this.revocationDate = date;
    }

    public int getCertStatus() {
        return this.certStatus;
    }

    public void setCertStatus(int n) {
        this.certStatus = n;
    }
}

