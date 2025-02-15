/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.ExceptionContextProvider;
import org.apache.commons.math3.exception.util.LocalizedFormats;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class MathParseException
extends MathIllegalStateException
implements ExceptionContextProvider {
    private static final long serialVersionUID = -6024911025449780478L;

    public MathParseException(String wrong, int position, Class<?> type) {
        this.getContext().addMessage(LocalizedFormats.CANNOT_PARSE_AS_TYPE, wrong, position, type.getName());
    }

    public MathParseException(String wrong, int position) {
        this.getContext().addMessage(LocalizedFormats.CANNOT_PARSE, wrong, position);
    }
}

