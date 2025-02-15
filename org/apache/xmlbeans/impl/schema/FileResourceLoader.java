/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.xmlbeans.ResourceLoader;

public class FileResourceLoader
implements ResourceLoader {
    private File _directory;
    private ZipFile _zipfile;

    public FileResourceLoader(File file) throws IOException {
        if (file.isDirectory()) {
            this._directory = file;
        } else {
            this._zipfile = new ZipFile(file);
        }
    }

    @Override
    public InputStream getResourceAsStream(String resourceName) {
        try {
            if (this._zipfile != null) {
                ZipEntry entry = this._zipfile.getEntry(resourceName);
                if (entry == null) {
                    return null;
                }
                return this._zipfile.getInputStream(entry);
            }
            return new FileInputStream(new File(this._directory, resourceName));
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void close() {
        if (this._zipfile != null) {
            try {
                this._zipfile.close();
            } catch (IOException iOException) {
                // empty catch block
            }
            this._zipfile = null;
        }
    }
}

