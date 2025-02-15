/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.provider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class ResourcePath {
    private File[] mFiles;

    public static ResourcePath forFiles(File[] files) {
        return new ResourcePath(files);
    }

    private ResourcePath(File[] files) {
        if (files == null) {
            throw new IllegalArgumentException("null files");
        }
        this.mFiles = files;
    }

    public URI[] toUriPath() {
        URI[] out = new URI[this.mFiles.length];
        for (int i = 0; i < this.mFiles.length; ++i) {
            out[i] = this.mFiles[i].toURI();
        }
        return out;
    }

    public URL[] toUrlPath() throws MalformedURLException {
        URL[] out = new URL[this.mFiles.length];
        for (int i = 0; i < this.mFiles.length; ++i) {
            out[i] = this.mFiles[i].toURL();
        }
        return out;
    }

    public InputStream findInPath(String resource) {
        for (int i = 0; i < this.mFiles.length; ++i) {
            File f = new File(this.mFiles[i], resource);
            try {
                if (!f.exists()) continue;
                return new FileInputStream(f);
            } catch (FileNotFoundException weird) {
                weird.printStackTrace();
            }
        }
        return null;
    }

    public String toString() {
        StringWriter out = new StringWriter();
        for (int i = 0; i < this.mFiles.length; ++i) {
            out.write(this.mFiles[i].getAbsolutePath());
            out.write(File.pathSeparatorChar);
        }
        return out.toString();
    }
}

