/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.support;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

public final class SpringFactoriesLoader {
    public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";
    private static final Log logger = LogFactory.getLog(SpringFactoriesLoader.class);
    static final Map<ClassLoader, Map<String, List<String>>> cache = new ConcurrentReferenceHashMap<ClassLoader, Map<String, List<String>>>();

    private SpringFactoriesLoader() {
    }

    public static <T> List<T> loadFactories(Class<T> factoryType, @Nullable ClassLoader classLoader) {
        Assert.notNull(factoryType, "'factoryType' must not be null");
        ClassLoader classLoaderToUse = classLoader;
        if (classLoaderToUse == null) {
            classLoaderToUse = SpringFactoriesLoader.class.getClassLoader();
        }
        List<String> factoryImplementationNames = SpringFactoriesLoader.loadFactoryNames(factoryType, classLoaderToUse);
        if (logger.isTraceEnabled()) {
            logger.trace("Loaded [" + factoryType.getName() + "] names: " + factoryImplementationNames);
        }
        ArrayList<T> result = new ArrayList<T>(factoryImplementationNames.size());
        for (String factoryImplementationName : factoryImplementationNames) {
            result.add(SpringFactoriesLoader.instantiateFactory(factoryImplementationName, factoryType, classLoaderToUse));
        }
        AnnotationAwareOrderComparator.sort(result);
        return result;
    }

    public static List<String> loadFactoryNames(Class<?> factoryType, @Nullable ClassLoader classLoader) {
        ClassLoader classLoaderToUse = classLoader;
        if (classLoaderToUse == null) {
            classLoaderToUse = SpringFactoriesLoader.class.getClassLoader();
        }
        String factoryTypeName = factoryType.getName();
        return SpringFactoriesLoader.loadSpringFactories(classLoaderToUse).getOrDefault(factoryTypeName, Collections.emptyList());
    }

    private static Map<String, List<String>> loadSpringFactories(ClassLoader classLoader) {
        Map<String, List<String>> result = cache.get(classLoader);
        if (result != null) {
            return result;
        }
        result = new HashMap<String, List<String>>();
        try {
            Enumeration<URL> urls = classLoader.getResources(FACTORIES_RESOURCE_LOCATION);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                UrlResource resource = new UrlResource(url);
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                for (Map.Entry entry : properties.entrySet()) {
                    String[] factoryImplementationNames;
                    String factoryTypeName = ((String)entry.getKey()).trim();
                    for (String factoryImplementationName : factoryImplementationNames = StringUtils.commaDelimitedListToStringArray((String)entry.getValue())) {
                        result.computeIfAbsent(factoryTypeName, key -> new ArrayList()).add(factoryImplementationName.trim());
                    }
                }
            }
            result.replaceAll((factoryType, implementations) -> implementations.stream().distinct().collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList)));
            cache.put(classLoader, result);
        } catch (IOException ex) {
            throw new IllegalArgumentException("Unable to load factories from location [META-INF/spring.factories]", ex);
        }
        return result;
    }

    private static <T> T instantiateFactory(String factoryImplementationName, Class<T> factoryType, ClassLoader classLoader) {
        try {
            Class<?> factoryImplementationClass = ClassUtils.forName(factoryImplementationName, classLoader);
            if (!factoryType.isAssignableFrom(factoryImplementationClass)) {
                throw new IllegalArgumentException("Class [" + factoryImplementationName + "] is not assignable to factory type [" + factoryType.getName() + "]");
            }
            return (T)ReflectionUtils.accessibleConstructor(factoryImplementationClass, new Class[0]).newInstance(new Object[0]);
        } catch (Throwable ex) {
            throw new IllegalArgumentException("Unable to instantiate factory class [" + factoryImplementationName + "] for factory type [" + factoryType.getName() + "]", ex);
        }
    }
}

