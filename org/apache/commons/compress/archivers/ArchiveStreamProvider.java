/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;

public interface ArchiveStreamProvider {
    public ArchiveInputStream createArchiveInputStream(String var1, InputStream var2, String var3) throws ArchiveException;

    public ArchiveOutputStream createArchiveOutputStream(String var1, OutputStream var2, String var3) throws ArchiveException;

    public Set<String> getInputStreamArchiveNames();

    public Set<String> getOutputStreamArchiveNames();
}

