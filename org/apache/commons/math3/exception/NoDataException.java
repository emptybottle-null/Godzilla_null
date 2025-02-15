/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class NoDataException
extends MathIllegalArgumentException {
    private static final long serialVersionUID = -3629324471511904459L;

    public NoDataException() {
        this(LocalizedFormats.NO_DATA);
    }

    public NoDataException(Localizable specific) {
        super(specific, new Object[0]);
    }
}

