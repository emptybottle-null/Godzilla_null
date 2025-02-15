/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardOpenOption;
import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.VfsResource;
import org.springframework.core.io.VfsUtils;
import org.springframework.util.ResourceUtils;

public abstract class AbstractFileResolvingResource
extends AbstractResource {
    @Override
    public boolean exists() {
        try {
            HttpURLConnection httpCon;
            URL url = this.getURL();
            if (ResourceUtils.isFileURL(url)) {
                return this.getFile().exists();
            }
            URLConnection con = url.openConnection();
            this.customizeConnection(con);
            HttpURLConnection httpURLConnection = httpCon = con instanceof HttpURLConnection ? (HttpURLConnection)con : null;
            if (httpCon != null) {
                int code = httpCon.getResponseCode();
                if (code == 200) {
                    return true;
                }
                if (code == 404) {
                    return false;
                }
            }
            if (con.getContentLengthLong() > 0L) {
                return true;
            }
            if (httpCon != null) {
                httpCon.disconnect();
                return false;
            }
            this.getInputStream().close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean isReadable() {
        try {
            return this.checkReadable(this.getURL());
        } catch (IOException ex) {
            return false;
        }
    }

    boolean checkReadable(URL url) {
        try {
            HttpURLConnection httpCon;
            int code;
            if (ResourceUtils.isFileURL(url)) {
                File file = this.getFile();
                return file.canRead() && !file.isDirectory();
            }
            URLConnection con = url.openConnection();
            this.customizeConnection(con);
            if (con instanceof HttpURLConnection && (code = (httpCon = (HttpURLConnection)con).getResponseCode()) != 200) {
                httpCon.disconnect();
                return false;
            }
            long contentLength = con.getContentLengthLong();
            if (contentLength > 0L) {
                return true;
            }
            if (contentLength == 0L) {
                return false;
            }
            this.getInputStream().close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean isFile() {
        try {
            URL url = this.getURL();
            if (url.getProtocol().startsWith("vfs")) {
                return VfsResourceDelegate.getResource(url).isFile();
            }
            return "file".equals(url.getProtocol());
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public File getFile() throws IOException {
        URL url = this.getURL();
        if (url.getProtocol().startsWith("vfs")) {
            return VfsResourceDelegate.getResource(url).getFile();
        }
        return ResourceUtils.getFile(url, this.getDescription());
    }

    @Override
    protected File getFileForLastModifiedCheck() throws IOException {
        URL url = this.getURL();
        if (ResourceUtils.isJarURL(url)) {
            URL actualUrl = ResourceUtils.extractArchiveURL(url);
            if (actualUrl.getProtocol().startsWith("vfs")) {
                return VfsResourceDelegate.getResource(actualUrl).getFile();
            }
            return ResourceUtils.getFile(actualUrl, "Jar URL");
        }
        return this.getFile();
    }

    protected boolean isFile(URI uri) {
        try {
            if (uri.getScheme().startsWith("vfs")) {
                return VfsResourceDelegate.getResource(uri).isFile();
            }
            return "file".equals(uri.getScheme());
        } catch (IOException ex) {
            return false;
        }
    }

    protected File getFile(URI uri) throws IOException {
        if (uri.getScheme().startsWith("vfs")) {
            return VfsResourceDelegate.getResource(uri).getFile();
        }
        return ResourceUtils.getFile(uri, this.getDescription());
    }

    @Override
    public ReadableByteChannel readableChannel() throws IOException {
        try {
            return FileChannel.open(this.getFile().toPath(), StandardOpenOption.READ);
        } catch (FileNotFoundException | NoSuchFileException ex) {
            return super.readableChannel();
        }
    }

    @Override
    public long contentLength() throws IOException {
        URL url = this.getURL();
        if (ResourceUtils.isFileURL(url)) {
            File file = this.getFile();
            long length = file.length();
            if (length == 0L && !file.exists()) {
                throw new FileNotFoundException(this.getDescription() + " cannot be resolved in the file system for checking its content length");
            }
            return length;
        }
        URLConnection con = url.openConnection();
        this.customizeConnection(con);
        return con.getContentLengthLong();
    }

    @Override
    public long lastModified() throws IOException {
        long lastModified;
        URL url = this.getURL();
        boolean fileCheck = false;
        if (ResourceUtils.isFileURL(url) || ResourceUtils.isJarURL(url)) {
            fileCheck = true;
            try {
                File fileToCheck = this.getFileForLastModifiedCheck();
                lastModified = fileToCheck.lastModified();
                if (lastModified > 0L || fileToCheck.exists()) {
                    return lastModified;
                }
            } catch (FileNotFoundException fileToCheck) {
                // empty catch block
            }
        }
        URLConnection con = url.openConnection();
        this.customizeConnection(con);
        lastModified = con.getLastModified();
        if (fileCheck && lastModified == 0L && con.getContentLengthLong() <= 0L) {
            throw new FileNotFoundException(this.getDescription() + " cannot be resolved in the file system for checking its last-modified timestamp");
        }
        return lastModified;
    }

    protected void customizeConnection(URLConnection con) throws IOException {
        ResourceUtils.useCachesIfNecessary(con);
        if (con instanceof HttpURLConnection) {
            this.customizeConnection((HttpURLConnection)con);
        }
    }

    protected void customizeConnection(HttpURLConnection con) throws IOException {
        con.setRequestMethod("HEAD");
    }

    private static class VfsResourceDelegate {
        private VfsResourceDelegate() {
        }

        public static Resource getResource(URL url) throws IOException {
            return new VfsResource(VfsUtils.getRoot(url));
        }

        public static Resource getResource(URI uri) throws IOException {
            return new VfsResource(VfsUtils.getRoot(uri));
        }
    }
}

