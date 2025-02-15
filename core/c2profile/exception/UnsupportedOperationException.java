/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package core.c2profile.exception;

import core.EasyI18N;

public class UnsupportedOperationException
extends java.lang.UnsupportedOperationException {
    public UnsupportedOperationException(String message) {
        super(EasyI18N.getI18nString(message));
    }

    public UnsupportedOperationException(String format, Object ... args) {
        this(String.format(EasyI18N.getI18nString(format), args));
    }
}

