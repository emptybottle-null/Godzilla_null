/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

public interface InputStreamSource {
    public InputStream getInputStream() throws IOException;
}

