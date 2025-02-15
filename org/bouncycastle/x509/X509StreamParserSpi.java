/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.io.InputStream;
import java.util.Collection;
import org.bouncycastle.x509.util.StreamParsingException;

public abstract class X509StreamParserSpi {
    public abstract void engineInit(InputStream var1);

    public abstract Object engineRead() throws StreamParsingException;

    public abstract Collection engineReadAll() throws StreamParsingException;
}

