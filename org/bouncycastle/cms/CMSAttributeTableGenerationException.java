/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cms;

import org.bouncycastle.cms.CMSRuntimeException;

public class CMSAttributeTableGenerationException
extends CMSRuntimeException {
    Exception e;

    public CMSAttributeTableGenerationException(String string) {
        super(string);
    }

    public CMSAttributeTableGenerationException(String string, Exception exception) {
        super(string);
        this.e = exception;
    }

    public Exception getUnderlyingException() {
        return this.e;
    }

    public Throwable getCause() {
        return this.e;
    }
}

