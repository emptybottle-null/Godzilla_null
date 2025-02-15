/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.provider;

import org.bouncycastle.asn1.x509.ReasonFlags;

class ReasonsMask {
    private int _reasons;
    static final ReasonsMask allReasons = new ReasonsMask(33023);

    ReasonsMask(ReasonFlags reasonFlags) {
        this._reasons = reasonFlags.intValue();
    }

    private ReasonsMask(int n) {
        this._reasons = n;
    }

    ReasonsMask() {
        this(0);
    }

    void addReasons(ReasonsMask reasonsMask) {
        this._reasons |= reasonsMask.getReasons();
    }

    boolean isAllReasons() {
        return this._reasons == ReasonsMask.allReasons._reasons;
    }

    ReasonsMask intersect(ReasonsMask reasonsMask) {
        ReasonsMask reasonsMask2 = new ReasonsMask();
        reasonsMask2.addReasons(new ReasonsMask(this._reasons & reasonsMask.getReasons()));
        return reasonsMask2;
    }

    boolean hasNewReasons(ReasonsMask reasonsMask) {
        return (this._reasons | reasonsMask.getReasons() ^ this._reasons) != 0;
    }

    int getReasons() {
        return this._reasons;
    }
}

