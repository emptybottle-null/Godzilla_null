/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

public class UrlResource
extends AbstractFileResolvingResource {
    @Nullable
    private final URI uri;
    private final URL url;
    @Nullable
    private volatile URL cleanedUrl;

    public UrlResource(URI uri) throws MalformedURLException {
        Assert.notNull((Object)uri, "URI must not be null");
        this.uri = uri;
        this.url = uri.toURL();
    }

    public UrlResource(URL url) {
        Assert.notNull((Object)url, "URL must not be null");
        this.uri = null;
        this.url = url;
    }

    public UrlResource(String path) throws MalformedURLException {
        Assert.notNull((Object)path, "Path must not be null");
        this.uri = null;
        this.url = new URL(path);
        this.cleanedUrl = UrlResource.getCleanedUrl(this.url, path);
    }

    public UrlResource(String protocol, String location) throws MalformedURLException {
        this(protocol, location, null);
    }

    public UrlResource(String protocol, String location, @Nullable String fragment) throws MalformedURLException {
        try {
            this.uri = new URI(protocol, location, fragment);
            this.url = this.uri.toURL();
        } catch (URISyntaxException ex) {
            MalformedURLException exToThrow = new MalformedURLException(ex.getMessage());
            exToThrow.initCause(ex);
            throw exToThrow;
        }
    }

    private static URL getCleanedUrl(URL originalUrl, String originalPath) {
        String cleanedPath = StringUtils.cleanPath(originalPath);
        if (!cleanedPath.equals(originalPath)) {
            try {
                return new URL(cleanedPath);
            } catch (MalformedURLException malformedURLException) {
                // empty catch block
            }
        }
        return originalUrl;
    }

    private URL getCleanedUrl() {
        URL cleanedUrl = this.cleanedUrl;
        if (cleanedUrl != null) {
            return cleanedUrl;
        }
        this.cleanedUrl = cleanedUrl = UrlResource.getCleanedUrl(this.url, (this.uri != null ? this.uri : this.url).toString());
        return cleanedUrl;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        ResourceUtils.useCachesIfNecessary(con);
        try {
            return con.getInputStream();
        } catch (IOException ex) {
            if (con instanceof HttpURLConnection) {
                ((HttpURLConnection)con).disconnect();
            }
            throw ex;
        }
    }

    @Override
    public URL getURL() {
        return this.url;
    }

    @Override
    public URI getURI() throws IOException {
        if (this.uri != null) {
            return this.uri;
        }
        return super.getURI();
    }

    @Override
    public boolean isFile() {
        if (this.uri != null) {
            return super.isFile(this.uri);
        }
        return super.isFile();
    }

    @Override
    public File getFile() throws IOException {
        if (this.uri != null) {
            return super.getFile(this.uri);
        }
        return super.getFile();
    }

    @Override
    public Resource createRelative(String relativePath) throws MalformedURLException {
        return new UrlResource(this.createRelativeURL(relativePath));
    }

    protected URL createRelativeURL(String relativePath) throws MalformedURLException {
        if (relativePath.startsWith("/")) {
            relativePath = relativePath.substring(1);
        }
        relativePath = StringUtils.replace(relativePath, "#", "%23");
        return new URL(this.url, relativePath);
    }

    @Override
    public String getFilename() {
        return StringUtils.getFilename(this.getCleanedUrl().getPath());
    }

    @Override
    public String getDescription() {
        return "URL [" + this.url + "]";
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return this == other || other instanceof UrlResource && this.getCleanedUrl().equals(((UrlResource)other).getCleanedUrl());
    }

    @Override
    public int hashCode() {
        return this.getCleanedUrl().hashCode();
    }
}

