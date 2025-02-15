/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class MathInternalError
extends MathIllegalStateException {
    private static final long serialVersionUID = -6276776513966934846L;
    private static final String REPORT_URL = "https://issues.apache.org/jira/browse/MATH";

    public MathInternalError() {
        this.getContext().addMessage(LocalizedFormats.INTERNAL_ERROR, REPORT_URL);
    }

    public MathInternalError(Throwable cause) {
        super(cause, LocalizedFormats.INTERNAL_ERROR, REPORT_URL);
    }

    public MathInternalError(Localizable pattern, Object ... args) {
        super(pattern, args);
    }
}

