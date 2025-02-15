/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.ode;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class UnknownParameterException
extends MathIllegalArgumentException {
    private static final long serialVersionUID = 20120902L;
    private final String name;

    public UnknownParameterException(String name) {
        super(LocalizedFormats.UNKNOWN_PARAMETER, name);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

