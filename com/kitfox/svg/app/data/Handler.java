/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.app.data;

import com.kitfox.svg.util.Base64InputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Handler
extends URLStreamHandler {
    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new Connection(u);
    }

    class Connection
    extends URLConnection {
        String mime;
        byte[] buf;

        public Connection(URL url) {
            super(url);
            String path = url.getPath();
            int idx = path.indexOf(59);
            this.mime = path.substring(0, idx);
            String content = path.substring(idx + 1);
            if (content.startsWith("base64,")) {
                content = content.substring(7);
                try {
                    ByteArrayInputStream bis = new ByteArrayInputStream(content.getBytes());
                    Base64InputStream b64is = new Base64InputStream(bis);
                    ByteArrayOutputStream bout = new ByteArrayOutputStream();
                    byte[] tmp = new byte[2056];
                    int size = b64is.read(tmp);
                    while (size != -1) {
                        bout.write(tmp, 0, size);
                        size = b64is.read(tmp);
                    }
                    this.buf = bout.toByteArray();
                } catch (IOException e) {
                    Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, null, e);
                }
            }
        }

        @Override
        public void connect() throws IOException {
        }

        @Override
        public String getHeaderField(String name) {
            if ("content-type".equals(name)) {
                return this.mime;
            }
            return super.getHeaderField(name);
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(this.buf);
        }
    }
}

