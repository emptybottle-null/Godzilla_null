/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import javassist.ClassPath;

public class ByteArrayClassPath
implements ClassPath {
    protected String classname;
    protected byte[] classfile;

    public ByteArrayClassPath(String name, byte[] classfile) {
        this.classname = name;
        this.classfile = classfile;
    }

    public String toString() {
        return "byte[]:" + this.classname;
    }

    @Override
    public InputStream openClassfile(String classname) {
        if (this.classname.equals(classname)) {
            return new ByteArrayInputStream(this.classfile);
        }
        return null;
    }

    @Override
    public URL find(String classname) {
        if (this.classname.equals(classname)) {
            String cname = classname.replace('.', '/') + ".class";
            try {
                return new URL(null, "file:/ByteArrayClassPath/" + cname, new BytecodeURLStreamHandler());
            } catch (MalformedURLException malformedURLException) {
                // empty catch block
            }
        }
        return null;
    }

    private class BytecodeURLConnection
    extends URLConnection {
        protected BytecodeURLConnection(URL url) {
            super(url);
        }

        @Override
        public void connect() throws IOException {
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(ByteArrayClassPath.this.classfile);
        }

        @Override
        public int getContentLength() {
            return ByteArrayClassPath.this.classfile.length;
        }
    }

    private class BytecodeURLStreamHandler
    extends URLStreamHandler {
        private BytecodeURLStreamHandler() {
        }

        @Override
        protected URLConnection openConnection(URL u) {
            return new BytecodeURLConnection(u);
        }
    }
}

