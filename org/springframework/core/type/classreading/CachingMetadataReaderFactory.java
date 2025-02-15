/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.type.classreading;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.lang.Nullable;

public class CachingMetadataReaderFactory
extends SimpleMetadataReaderFactory {
    public static final int DEFAULT_CACHE_LIMIT = 256;
    @Nullable
    private Map<Resource, MetadataReader> metadataReaderCache;

    public CachingMetadataReaderFactory() {
        this.setCacheLimit(256);
    }

    public CachingMetadataReaderFactory(@Nullable ClassLoader classLoader) {
        super(classLoader);
        this.setCacheLimit(256);
    }

    public CachingMetadataReaderFactory(@Nullable ResourceLoader resourceLoader) {
        super(resourceLoader);
        if (resourceLoader instanceof DefaultResourceLoader) {
            this.metadataReaderCache = ((DefaultResourceLoader)resourceLoader).getResourceCache(MetadataReader.class);
        } else {
            this.setCacheLimit(256);
        }
    }

    public void setCacheLimit(int cacheLimit) {
        if (cacheLimit <= 0) {
            this.metadataReaderCache = null;
        } else if (this.metadataReaderCache instanceof LocalResourceCache) {
            ((LocalResourceCache)this.metadataReaderCache).setCacheLimit(cacheLimit);
        } else {
            this.metadataReaderCache = new LocalResourceCache(cacheLimit);
        }
    }

    public int getCacheLimit() {
        if (this.metadataReaderCache instanceof LocalResourceCache) {
            return ((LocalResourceCache)this.metadataReaderCache).getCacheLimit();
        }
        return this.metadataReaderCache != null ? Integer.MAX_VALUE : 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public MetadataReader getMetadataReader(Resource resource) throws IOException {
        if (this.metadataReaderCache instanceof ConcurrentMap) {
            MetadataReader metadataReader = this.metadataReaderCache.get(resource);
            if (metadataReader == null) {
                metadataReader = super.getMetadataReader(resource);
                this.metadataReaderCache.put(resource, metadataReader);
            }
            return metadataReader;
        }
        if (this.metadataReaderCache != null) {
            Map<Resource, MetadataReader> map = this.metadataReaderCache;
            synchronized (map) {
                MetadataReader metadataReader = this.metadataReaderCache.get(resource);
                if (metadataReader == null) {
                    metadataReader = super.getMetadataReader(resource);
                    this.metadataReaderCache.put(resource, metadataReader);
                }
                return metadataReader;
            }
        }
        return super.getMetadataReader(resource);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void clearCache() {
        if (this.metadataReaderCache instanceof LocalResourceCache) {
            Map<Resource, MetadataReader> map = this.metadataReaderCache;
            synchronized (map) {
                this.metadataReaderCache.clear();
            }
        } else if (this.metadataReaderCache != null) {
            this.setCacheLimit(256);
        }
    }

    private static class LocalResourceCache
    extends LinkedHashMap<Resource, MetadataReader> {
        private volatile int cacheLimit;

        public LocalResourceCache(int cacheLimit) {
            super(cacheLimit, 0.75f, true);
            this.cacheLimit = cacheLimit;
        }

        public void setCacheLimit(int cacheLimit) {
            this.cacheLimit = cacheLimit;
        }

        public int getCacheLimit() {
            return this.cacheLimit;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Resource, MetadataReader> eldest) {
            return this.size() > this.cacheLimit;
        }
    }
}

