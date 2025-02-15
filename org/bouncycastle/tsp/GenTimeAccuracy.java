/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.tsp;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.tsp.Accuracy;

public class GenTimeAccuracy {
    private Accuracy accuracy;

    public GenTimeAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
    }

    public int getSeconds() {
        return this.getTimeComponent(this.accuracy.getSeconds());
    }

    public int getMillis() {
        return this.getTimeComponent(this.accuracy.getMillis());
    }

    public int getMicros() {
        return this.getTimeComponent(this.accuracy.getMicros());
    }

    private int getTimeComponent(ASN1Integer aSN1Integer) {
        if (aSN1Integer != null) {
            return aSN1Integer.getValue().intValue();
        }
        return 0;
    }

    public String toString() {
        return this.getSeconds() + "." + this.format(this.getMillis()) + this.format(this.getMicros());
    }

    private String format(int n) {
        if (n < 10) {
            return "00" + n;
        }
        if (n < 100) {
            return "0" + n;
        }
        return Integer.toString(n);
    }
}

