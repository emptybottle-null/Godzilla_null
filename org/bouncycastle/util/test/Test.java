/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.test;

import org.bouncycastle.util.test.TestResult;

public interface Test {
    public String getName();

    public TestResult perform();
}

