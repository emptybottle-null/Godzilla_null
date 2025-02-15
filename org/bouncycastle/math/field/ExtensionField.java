/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.math.field;

import org.bouncycastle.math.field.FiniteField;

public interface ExtensionField
extends FiniteField {
    public FiniteField getSubfield();

    public int getDegree();
}

