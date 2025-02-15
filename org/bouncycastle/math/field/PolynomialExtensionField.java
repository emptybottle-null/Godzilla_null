/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.field;

import org.bouncycastle.math.field.ExtensionField;
import org.bouncycastle.math.field.Polynomial;

public interface PolynomialExtensionField
extends ExtensionField {
    public Polynomial getMinimalPolynomial();
}

