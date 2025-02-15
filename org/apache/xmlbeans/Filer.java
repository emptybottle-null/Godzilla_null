/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public interface Filer {
    public OutputStream createBinaryFile(String var1) throws IOException;

    public Writer createSourceFile(String var1) throws IOException;
}

