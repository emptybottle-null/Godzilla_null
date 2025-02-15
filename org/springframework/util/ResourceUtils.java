/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

public abstract class ResourceUtils {
    public static final String CLASSPATH_URL_PREFIX = "classpath:";
    public static final String FILE_URL_PREFIX = "file:";
    public static final String JAR_URL_PREFIX = "jar:";
    public static final String WAR_URL_PREFIX = "war:";
    public static final String URL_PROTOCOL_FILE = "file";
    public static final String URL_PROTOCOL_JAR = "jar";
    public static final String URL_PROTOCOL_WAR = "war";
    public static final String URL_PROTOCOL_ZIP = "zip";
    public static final String URL_PROTOCOL_WSJAR = "wsjar";
    public static final String URL_PROTOCOL_VFSZIP = "vfszip";
    public static final String URL_PROTOCOL_VFSFILE = "vfsfile";
    public static final String URL_PROTOCOL_VFS = "vfs";
    public static final String JAR_FILE_EXTENSION = ".jar";
    public static final String JAR_URL_SEPARATOR = "!/";
    public static final String WAR_URL_SEPARATOR = "*/";

    public static boolean isUrl(@Nullable String resourceLocation) {
        if (resourceLocation == null) {
            return false;
        }
        if (resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
            return true;
        }
        try {
            new URL(resourceLocation);
            return true;
        } catch (MalformedURLException ex) {
            return false;
        }
    }

    public static URL getURL(String resourceLocation) throws FileNotFoundException {
        Assert.notNull((Object)resourceLocation, "Resource location must not be null");
        if (resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
            URL url;
            String path = resourceLocation.substring(CLASSPATH_URL_PREFIX.length());
            ClassLoader cl = ClassUtils.getDefaultClassLoader();
            URL uRL = url = cl != null ? cl.getResource(path) : ClassLoader.getSystemResource(path);
            if (url == null) {
                String description = "class path resource [" + path + "]";
                throw new FileNotFoundException(description + " cannot be resolved to URL because it does not exist");
            }
            return url;
        }
        try {
            return new URL(resourceLocation);
        } catch (MalformedURLException ex) {
            try {
                return new File(resourceLocation).toURI().toURL();
            } catch (MalformedURLException ex2) {
                throw new FileNotFoundException("Resource location [" + resourceLocation + "] is neither a URL not a well-formed file path");
            }
        }
    }

    public static File getFile(String resourceLocation) throws FileNotFoundException {
        Assert.notNull((Object)resourceLocation, "Resource location must not be null");
        if (resourceLocation.startsWith(CLASSPATH_URL_PREFIX)) {
            URL url;
            String path = resourceLocation.substring(CLASSPATH_URL_PREFIX.length());
            String description = "class path resource [" + path + "]";
            ClassLoader cl = ClassUtils.getDefaultClassLoader();
            URL uRL = url = cl != null ? cl.getResource(path) : ClassLoader.getSystemResource(path);
            if (url == null) {
                throw new FileNotFoundException(description + " cannot be resolved to absolute file path because it does not exist");
            }
            return ResourceUtils.getFile(url, description);
        }
        try {
            return ResourceUtils.getFile(new URL(resourceLocation));
        } catch (MalformedURLException ex) {
            return new File(resourceLocation);
        }
    }

    public static File getFile(URL resourceUrl) throws FileNotFoundException {
        return ResourceUtils.getFile(resourceUrl, "URL");
    }

    public static File getFile(URL resourceUrl, String description) throws FileNotFoundException {
        Assert.notNull((Object)resourceUrl, "Resource URL must not be null");
        if (!URL_PROTOCOL_FILE.equals(resourceUrl.getProtocol())) {
            throw new FileNotFoundException(description + " cannot be resolved to absolute file path because it does not reside in the file system: " + resourceUrl);
        }
        try {
            return new File(ResourceUtils.toURI(resourceUrl).getSchemeSpecificPart());
        } catch (URISyntaxException ex) {
            return new File(resourceUrl.getFile());
        }
    }

    public static File getFile(URI resourceUri) throws FileNotFoundException {
        return ResourceUtils.getFile(resourceUri, "URI");
    }

    public static File getFile(URI resourceUri, String description) throws FileNotFoundException {
        Assert.notNull((Object)resourceUri, "Resource URI must not be null");
        if (!URL_PROTOCOL_FILE.equals(resourceUri.getScheme())) {
            throw new FileNotFoundException(description + " cannot be resolved to absolute file path because it does not reside in the file system: " + resourceUri);
        }
        return new File(resourceUri.getSchemeSpecificPart());
    }

    public static boolean isFileURL(URL url) {
        String protocol = url.getProtocol();
        return URL_PROTOCOL_FILE.equals(protocol) || URL_PROTOCOL_VFSFILE.equals(protocol) || URL_PROTOCOL_VFS.equals(protocol);
    }

    public static boolean isJarURL(URL url) {
        String protocol = url.getProtocol();
        return URL_PROTOCOL_JAR.equals(protocol) || URL_PROTOCOL_WAR.equals(protocol) || URL_PROTOCOL_ZIP.equals(protocol) || URL_PROTOCOL_VFSZIP.equals(protocol) || URL_PROTOCOL_WSJAR.equals(protocol);
    }

    public static boolean isJarFileURL(URL url) {
        return URL_PROTOCOL_FILE.equals(url.getProtocol()) && url.getPath().toLowerCase().endsWith(JAR_FILE_EXTENSION);
    }

    public static URL extractJarFileURL(URL jarUrl) throws MalformedURLException {
        String urlFile = jarUrl.getFile();
        int separatorIndex = urlFile.indexOf(JAR_URL_SEPARATOR);
        if (separatorIndex != -1) {
            String jarFile = urlFile.substring(0, separatorIndex);
            try {
                return new URL(jarFile);
            } catch (MalformedURLException ex) {
                if (!jarFile.startsWith("/")) {
                    jarFile = "/" + jarFile;
                }
                return new URL(FILE_URL_PREFIX + jarFile);
            }
        }
        return jarUrl;
    }

    public static URL extractArchiveURL(URL jarUrl) throws MalformedURLException {
        String urlFile = jarUrl.getFile();
        int endIndex = urlFile.indexOf(WAR_URL_SEPARATOR);
        if (endIndex != -1) {
            String warFile = urlFile.substring(0, endIndex);
            if (URL_PROTOCOL_WAR.equals(jarUrl.getProtocol())) {
                return new URL(warFile);
            }
            int startIndex = warFile.indexOf(WAR_URL_PREFIX);
            if (startIndex != -1) {
                return new URL(warFile.substring(startIndex + WAR_URL_PREFIX.length()));
            }
        }
        return ResourceUtils.extractJarFileURL(jarUrl);
    }

    public static URI toURI(URL url) throws URISyntaxException {
        return ResourceUtils.toURI(url.toString());
    }

    public static URI toURI(String location) throws URISyntaxException {
        return new URI(StringUtils.replace(location, " ", "%20"));
    }

    public static void useCachesIfNecessary(URLConnection con) {
        con.setUseCaches(con.getClass().getSimpleName().startsWith("JNLP"));
    }
}

