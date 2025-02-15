/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.pqc.crypto.mceliece.McElieceParameters;

public class McElieceCCA2Parameters
extends McElieceParameters {
    private final String digest;

    public McElieceCCA2Parameters() {
        this(11, 50, "SHA-256");
    }

    public McElieceCCA2Parameters(String string) {
        this(11, 50, string);
    }

    public McElieceCCA2Parameters(int n) {
        this(n, "SHA-256");
    }

    public McElieceCCA2Parameters(int n, String string) {
        super(n);
        this.digest = string;
    }

    public McElieceCCA2Parameters(int n, int n2) {
        this(n, n2, "SHA-256");
    }

    public McElieceCCA2Parameters(int n, int n2, String string) {
        super(n, n2);
        this.digest = string;
    }

    public McElieceCCA2Parameters(int n, int n2, int n3) {
        this(n, n2, n3, "SHA-256");
    }

    public McElieceCCA2Parameters(int n, int n2, int n3, String string) {
        super(n, n2, n3);
        this.digest = string;
    }

    public String getDigest() {
        return this.digest;
    }
}

