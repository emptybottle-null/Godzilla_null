/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface Source<T> {
    public InputStream getInputStream() throws IOException;

    public OutputStream getOutputStream() throws IOException;

    public T getSession();

    public void close() throws IOException;
}

