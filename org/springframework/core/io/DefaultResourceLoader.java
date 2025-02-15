/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ContextResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

public class DefaultResourceLoader
implements ResourceLoader {
    @Nullable
    private ClassLoader classLoader;
    private final Set<ProtocolResolver> protocolResolvers = new LinkedHashSet<ProtocolResolver>(4);
    private final Map<Class<?>, Map<Resource, ?>> resourceCaches = new ConcurrentHashMap(4);

    public DefaultResourceLoader() {
    }

    public DefaultResourceLoader(@Nullable ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public void setClassLoader(@Nullable ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    @Nullable
    public ClassLoader getClassLoader() {
        return this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader();
    }

    public void addProtocolResolver(ProtocolResolver resolver) {
        Assert.notNull((Object)resolver, "ProtocolResolver must not be null");
        this.protocolResolvers.add(resolver);
    }

    public Collection<ProtocolResolver> getProtocolResolvers() {
        return this.protocolResolvers;
    }

    public <T> Map<Resource, T> getResourceCache(Class<T> valueType) {
        return this.resourceCaches.computeIfAbsent(valueType, key -> new ConcurrentHashMap());
    }

    public void clearResourceCaches() {
        this.resourceCaches.clear();
    }

    @Override
    public Resource getResource(String location) {
        Assert.notNull((Object)location, "Location must not be null");
        for (ProtocolResolver protocolResolver : this.getProtocolResolvers()) {
            Resource resource = protocolResolver.resolve(location, this);
            if (resource == null) continue;
            return resource;
        }
        if (location.startsWith("/")) {
            return this.getResourceByPath(location);
        }
        if (location.startsWith("classpath:")) {
            return new ClassPathResource(location.substring("classpath:".length()), this.getClassLoader());
        }
        try {
            URL url = new URL(location);
            return ResourceUtils.isFileURL(url) ? new FileUrlResource(url) : new UrlResource(url);
        } catch (MalformedURLException ex) {
            return this.getResourceByPath(location);
        }
    }

    protected Resource getResourceByPath(String path) {
        return new ClassPathContextResource(path, this.getClassLoader());
    }

    protected static class ClassPathContextResource
    extends ClassPathResource
    implements ContextResource {
        public ClassPathContextResource(String path, @Nullable ClassLoader classLoader) {
            super(path, classLoader);
        }

        @Override
        public String getPathWithinContext() {
            return this.getPath();
        }

        @Override
        public Resource createRelative(String relativePath) {
            String pathToUse = StringUtils.applyRelativePath(this.getPath(), relativePath);
            return new ClassPathContextResource(pathToUse, this.getClassLoader());
        }
    }
}

