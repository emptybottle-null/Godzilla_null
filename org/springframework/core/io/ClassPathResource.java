/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class ClassPathResource
extends AbstractFileResolvingResource {
    private final String path;
    @Nullable
    private ClassLoader classLoader;
    @Nullable
    private Class<?> clazz;

    public ClassPathResource(String path) {
        this(path, (ClassLoader)null);
    }

    public ClassPathResource(String path, @Nullable ClassLoader classLoader) {
        Assert.notNull((Object)path, "Path must not be null");
        String pathToUse = StringUtils.cleanPath(path);
        if (pathToUse.startsWith("/")) {
            pathToUse = pathToUse.substring(1);
        }
        this.path = pathToUse;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    public ClassPathResource(String path, @Nullable Class<?> clazz) {
        Assert.notNull((Object)path, "Path must not be null");
        this.path = StringUtils.cleanPath(path);
        this.clazz = clazz;
    }

    @Deprecated
    protected ClassPathResource(String path, @Nullable ClassLoader classLoader, @Nullable Class<?> clazz) {
        this.path = StringUtils.cleanPath(path);
        this.classLoader = classLoader;
        this.clazz = clazz;
    }

    public final String getPath() {
        return this.path;
    }

    @Nullable
    public final ClassLoader getClassLoader() {
        return this.clazz != null ? this.clazz.getClassLoader() : this.classLoader;
    }

    @Override
    public boolean exists() {
        return this.resolveURL() != null;
    }

    @Override
    public boolean isReadable() {
        URL url = this.resolveURL();
        return url != null && this.checkReadable(url);
    }

    @Nullable
    protected URL resolveURL() {
        try {
            if (this.clazz != null) {
                return this.clazz.getResource(this.path);
            }
            if (this.classLoader != null) {
                return this.classLoader.getResource(this.path);
            }
            return ClassLoader.getSystemResource(this.path);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = this.clazz != null ? this.clazz.getResourceAsStream(this.path) : (this.classLoader != null ? this.classLoader.getResourceAsStream(this.path) : ClassLoader.getSystemResourceAsStream(this.path));
        if (is == null) {
            throw new FileNotFoundException(this.getDescription() + " cannot be opened because it does not exist");
        }
        return is;
    }

    @Override
    public URL getURL() throws IOException {
        URL url = this.resolveURL();
        if (url == null) {
            throw new FileNotFoundException(this.getDescription() + " cannot be resolved to URL because it does not exist");
        }
        return url;
    }

    @Override
    public Resource createRelative(String relativePath) {
        String pathToUse = StringUtils.applyRelativePath(this.path, relativePath);
        return this.clazz != null ? new ClassPathResource(pathToUse, this.clazz) : new ClassPathResource(pathToUse, this.classLoader);
    }

    @Override
    @Nullable
    public String getFilename() {
        return StringUtils.getFilename(this.path);
    }

    @Override
    public String getDescription() {
        StringBuilder builder = new StringBuilder("class path resource [");
        String pathToUse = this.path;
        if (this.clazz != null && !pathToUse.startsWith("/")) {
            builder.append(ClassUtils.classPackageAsResourcePath(this.clazz));
            builder.append('/');
        }
        if (pathToUse.startsWith("/")) {
            pathToUse = pathToUse.substring(1);
        }
        builder.append(pathToUse);
        builder.append(']');
        return builder.toString();
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ClassPathResource)) {
            return false;
        }
        ClassPathResource otherRes = (ClassPathResource)other;
        return this.path.equals(otherRes.path) && ObjectUtils.nullSafeEquals(this.classLoader, otherRes.classLoader) && ObjectUtils.nullSafeEquals(this.clazz, otherRes.clazz);
    }

    @Override
    public int hashCode() {
        return this.path.hashCode();
    }
}

