/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javassist.ClassPath;
import javassist.ClassPoolTail;

public class URLClassPath
implements ClassPath {
    protected String hostname;
    protected int port;
    protected String directory;
    protected String packageName;

    public URLClassPath(String host, int port, String directory, String packageName) {
        this.hostname = host;
        this.port = port;
        this.directory = directory;
        this.packageName = packageName;
    }

    public String toString() {
        return this.hostname + ":" + this.port + this.directory;
    }

    @Override
    public InputStream openClassfile(String classname) {
        try {
            URLConnection con = this.openClassfile0(classname);
            if (con != null) {
                return con.getInputStream();
            }
        } catch (IOException iOException) {
            // empty catch block
        }
        return null;
    }

    private URLConnection openClassfile0(String classname) throws IOException {
        if (this.packageName == null || classname.startsWith(this.packageName)) {
            String jarname = this.directory + classname.replace('.', '/') + ".class";
            return URLClassPath.fetchClass0(this.hostname, this.port, jarname);
        }
        return null;
    }

    @Override
    public URL find(String classname) {
        try {
            URLConnection con = this.openClassfile0(classname);
            InputStream is = con.getInputStream();
            if (is != null) {
                is.close();
                return con.getURL();
            }
        } catch (IOException iOException) {
            // empty catch block
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static byte[] fetchClass(String host, int port, String directory, String classname) throws IOException {
        byte[] b;
        URLConnection con = URLClassPath.fetchClass0(host, port, directory + classname.replace('.', '/') + ".class");
        int size = con.getContentLength();
        try (InputStream s = con.getInputStream();){
            if (size <= 0) {
                b = ClassPoolTail.readStream(s);
            } else {
                int n;
                b = new byte[size];
                int len = 0;
                do {
                    if ((n = s.read(b, len, size - len)) >= 0) continue;
                    throw new IOException("the stream was closed: " + classname);
                } while ((len += n) < size);
            }
        }
        return b;
    }

    private static URLConnection fetchClass0(String host, int port, String filename) throws IOException {
        URL url;
        try {
            url = new URL("http", host, port, filename);
        } catch (MalformedURLException e) {
            throw new IOException("invalid URL?");
        }
        URLConnection con = url.openConnection();
        con.connect();
        return con;
    }
}

