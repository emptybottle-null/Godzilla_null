/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.InputStream;

public interface ResourceLoader {
    public InputStream getResourceAsStream(String var1);

    public void close();
}

