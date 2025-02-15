/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.support;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Properties;
import org.springframework.core.SpringProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.ResourcePropertiesPersister;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.PropertiesPersister;
import org.springframework.util.ResourceUtils;

public abstract class PropertiesLoaderUtils {
    private static final String XML_FILE_EXTENSION = ".xml";
    private static final boolean shouldIgnoreXml = SpringProperties.getFlag("spring.xml.ignore");

    public static Properties loadProperties(EncodedResource resource) throws IOException {
        Properties props = new Properties();
        PropertiesLoaderUtils.fillProperties(props, resource);
        return props;
    }

    public static void fillProperties(Properties props, EncodedResource resource) throws IOException {
        PropertiesLoaderUtils.fillProperties(props, resource, ResourcePropertiesPersister.INSTANCE);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static void fillProperties(Properties props, EncodedResource resource, PropertiesPersister persister) throws IOException {
        InputStream stream = null;
        Reader reader = null;
        try {
            String filename = resource.getResource().getFilename();
            if (filename != null && filename.endsWith(XML_FILE_EXTENSION)) {
                if (shouldIgnoreXml) {
                    throw new UnsupportedOperationException("XML support disabled");
                }
                stream = resource.getInputStream();
                persister.loadFromXml(props, stream);
            } else if (resource.requiresReader()) {
                reader = resource.getReader();
                persister.load(props, reader);
            } else {
                stream = resource.getInputStream();
                persister.load(props, stream);
            }
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (reader != null) {
                reader.close();
            }
        }
    }

    public static Properties loadProperties(Resource resource) throws IOException {
        Properties props = new Properties();
        PropertiesLoaderUtils.fillProperties(props, resource);
        return props;
    }

    public static void fillProperties(Properties props, Resource resource) throws IOException {
        try (InputStream is = resource.getInputStream();){
            String filename = resource.getFilename();
            if (filename != null && filename.endsWith(XML_FILE_EXTENSION)) {
                if (shouldIgnoreXml) {
                    throw new UnsupportedOperationException("XML support disabled");
                }
                props.loadFromXML(is);
            } else {
                props.load(is);
            }
        }
    }

    public static Properties loadAllProperties(String resourceName) throws IOException {
        return PropertiesLoaderUtils.loadAllProperties(resourceName, null);
    }

    public static Properties loadAllProperties(String resourceName, @Nullable ClassLoader classLoader) throws IOException {
        Assert.notNull((Object)resourceName, "Resource name must not be null");
        ClassLoader classLoaderToUse = classLoader;
        if (classLoaderToUse == null) {
            classLoaderToUse = ClassUtils.getDefaultClassLoader();
        }
        Enumeration<URL> urls = classLoaderToUse != null ? classLoaderToUse.getResources(resourceName) : ClassLoader.getSystemResources(resourceName);
        Properties props = new Properties();
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            URLConnection con = url.openConnection();
            ResourceUtils.useCachesIfNecessary(con);
            InputStream is = con.getInputStream();
            Throwable throwable = null;
            try {
                if (resourceName.endsWith(XML_FILE_EXTENSION)) {
                    if (shouldIgnoreXml) {
                        throw new UnsupportedOperationException("XML support disabled");
                    }
                    props.loadFromXML(is);
                    continue;
                }
                props.load(is);
            } catch (Throwable throwable2) {
                throwable = throwable2;
                throw throwable2;
            } finally {
                if (is == null) continue;
                if (throwable != null) {
                    try {
                        is.close();
                    } catch (Throwable throwable3) {
                        throwable.addSuppressed(throwable3);
                    }
                    continue;
                }
                is.close();
            }
        }
        return props;
    }
}

