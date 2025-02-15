/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.xmlbeans.ResourceLoader;
import org.apache.xmlbeans.impl.schema.FileResourceLoader;

public class PathResourceLoader
implements ResourceLoader {
    private ResourceLoader[] _path;

    public PathResourceLoader(ResourceLoader[] loaderpath) throws IOException {
        this._path = new ResourceLoader[loaderpath.length];
        System.arraycopy(loaderpath, 0, this._path, 0, this._path.length);
    }

    public PathResourceLoader(File[] filepath) {
        ArrayList<FileResourceLoader> pathlist = new ArrayList<FileResourceLoader>();
        for (int i = 0; i < filepath.length; ++i) {
            try {
                FileResourceLoader path = new FileResourceLoader(filepath[i]);
                pathlist.add(path);
                continue;
            } catch (IOException e) {
                // empty catch block
            }
        }
        this._path = pathlist.toArray(new ResourceLoader[pathlist.size()]);
    }

    @Override
    public InputStream getResourceAsStream(String resourceName) {
        for (int i = 0; i < this._path.length; ++i) {
            InputStream result = this._path[i].getResourceAsStream(resourceName);
            if (result == null) continue;
            return result;
        }
        return null;
    }

    @Override
    public void close() {
        for (int i = 0; i < this._path.length; ++i) {
            try {
                this._path[i].close();
                continue;
            } catch (Exception exception) {
                // empty catch block
            }
        }
    }
}

