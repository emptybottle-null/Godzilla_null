/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.test;

public interface TestResult {
    public boolean isSuccessful();

    public Throwable getException();

    public String toString();
}

