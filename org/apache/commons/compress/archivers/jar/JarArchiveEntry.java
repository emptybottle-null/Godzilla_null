/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.jar;

import java.security.cert.Certificate;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

public class JarArchiveEntry
extends ZipArchiveEntry {
    private final Attributes manifestAttributes = null;
    private final Certificate[] certificates = null;

    public JarArchiveEntry(ZipEntry entry) throws ZipException {
        super(entry);
    }

    public JarArchiveEntry(String name) {
        super(name);
    }

    public JarArchiveEntry(ZipArchiveEntry entry) throws ZipException {
        super(entry);
    }

    public JarArchiveEntry(JarEntry entry) throws ZipException {
        super(entry);
    }

    @Deprecated
    public Attributes getManifestAttributes() {
        return this.manifestAttributes;
    }

    @Deprecated
    public Certificate[] getCertificates() {
        if (this.certificates != null) {
            Certificate[] certs = new Certificate[this.certificates.length];
            System.arraycopy(this.certificates, 0, certs, 0, certs.length);
            return certs;
        }
        return null;
    }
}

