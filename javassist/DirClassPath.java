/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javassist.ClassPath;

final class DirClassPath
implements ClassPath {
    String directory;

    DirClassPath(String dirName) {
        this.directory = dirName;
    }

    @Override
    public InputStream openClassfile(String classname) {
        try {
            char sep = File.separatorChar;
            String filename = this.directory + sep + classname.replace('.', sep) + ".class";
            return new FileInputStream(filename.toString());
        } catch (FileNotFoundException fileNotFoundException) {
        } catch (SecurityException securityException) {
            // empty catch block
        }
        return null;
    }

    @Override
    public URL find(String classname) {
        char sep = File.separatorChar;
        String filename = this.directory + sep + classname.replace('.', sep) + ".class";
        File f = new File(filename);
        if (f.exists()) {
            try {
                return f.getCanonicalFile().toURI().toURL();
            } catch (MalformedURLException malformedURLException) {
            } catch (IOException iOException) {
                // empty catch block
            }
        }
        return null;
    }

    public String toString() {
        return this.directory;
    }
}

