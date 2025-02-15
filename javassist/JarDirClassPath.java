/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.net.URL;
import javassist.ClassPath;
import javassist.JarClassPath;
import javassist.NotFoundException;

final class JarDirClassPath
implements ClassPath {
    JarClassPath[] jars;

    JarDirClassPath(String dirName) throws NotFoundException {
        File[] files = new File(dirName).listFiles(new FilenameFilter(){

            @Override
            public boolean accept(File dir, String name) {
                return (name = name.toLowerCase()).endsWith(".jar") || name.endsWith(".zip");
            }
        });
        if (files != null) {
            this.jars = new JarClassPath[files.length];
            for (int i = 0; i < files.length; ++i) {
                this.jars[i] = new JarClassPath(files[i].getPath());
            }
        }
    }

    @Override
    public InputStream openClassfile(String classname) throws NotFoundException {
        if (this.jars != null) {
            for (int i = 0; i < this.jars.length; ++i) {
                InputStream is = this.jars[i].openClassfile(classname);
                if (is == null) continue;
                return is;
            }
        }
        return null;
    }

    @Override
    public URL find(String classname) {
        if (this.jars != null) {
            for (int i = 0; i < this.jars.length; ++i) {
                URL url = this.jars[i].find(classname);
                if (url == null) continue;
                return url;
            }
        }
        return null;
    }
}

