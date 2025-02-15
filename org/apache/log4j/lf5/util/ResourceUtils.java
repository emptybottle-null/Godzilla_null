/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.util;

import java.io.InputStream;
import java.net.URL;
import org.apache.log4j.lf5.util.Resource;

public class ResourceUtils {
    public static InputStream getResourceAsStream(Object object, Resource resource) {
        ClassLoader loader = object.getClass().getClassLoader();
        InputStream in = null;
        in = loader != null ? loader.getResourceAsStream(resource.getName()) : ClassLoader.getSystemResourceAsStream(resource.getName());
        return in;
    }

    public static URL getResourceAsURL(Object object, Resource resource) {
        ClassLoader loader = object.getClass().getClassLoader();
        URL url = null;
        url = loader != null ? loader.getResource(resource.getName()) : ClassLoader.getSystemResource(resource.getName());
        return url;
    }
}

