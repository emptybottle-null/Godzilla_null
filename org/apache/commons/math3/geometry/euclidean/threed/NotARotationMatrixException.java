/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.geometry.euclidean.threed;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.Localizable;

public class NotARotationMatrixException
extends MathIllegalArgumentException {
    private static final long serialVersionUID = 5647178478658937642L;

    public NotARotationMatrixException(Localizable specifier, Object ... parts) {
        super(specifier, parts);
    }
}

