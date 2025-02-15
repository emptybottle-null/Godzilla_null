/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.io.support;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class ResourceArrayPropertyEditor
extends PropertyEditorSupport {
    private static final Log logger = LogFactory.getLog(ResourceArrayPropertyEditor.class);
    private final ResourcePatternResolver resourcePatternResolver;
    @Nullable
    private PropertyResolver propertyResolver;
    private final boolean ignoreUnresolvablePlaceholders;

    public ResourceArrayPropertyEditor() {
        this(new PathMatchingResourcePatternResolver(), null, true);
    }

    public ResourceArrayPropertyEditor(ResourcePatternResolver resourcePatternResolver, @Nullable PropertyResolver propertyResolver) {
        this(resourcePatternResolver, propertyResolver, true);
    }

    public ResourceArrayPropertyEditor(ResourcePatternResolver resourcePatternResolver, @Nullable PropertyResolver propertyResolver, boolean ignoreUnresolvablePlaceholders) {
        Assert.notNull((Object)resourcePatternResolver, "ResourcePatternResolver must not be null");
        this.resourcePatternResolver = resourcePatternResolver;
        this.propertyResolver = propertyResolver;
        this.ignoreUnresolvablePlaceholders = ignoreUnresolvablePlaceholders;
    }

    @Override
    public void setAsText(String text) {
        String pattern = this.resolvePath(text).trim();
        try {
            this.setValue(this.resourcePatternResolver.getResources(pattern));
        } catch (IOException ex) {
            throw new IllegalArgumentException("Could not resolve resource location pattern [" + pattern + "]: " + ex.getMessage());
        }
    }

    @Override
    public void setValue(Object value) throws IllegalArgumentException {
        if (value instanceof Collection || value instanceof Object[] && !(value instanceof Resource[])) {
            List<Object> input = value instanceof Collection ? (List<Object>)value : Arrays.asList((Object[])value);
            LinkedHashSet<Resource> merged = new LinkedHashSet<Resource>();
            for (Object e : input) {
                if (e instanceof String) {
                    String pattern = this.resolvePath((String)e).trim();
                    try {
                        Resource[] resources = this.resourcePatternResolver.getResources(pattern);
                        Collections.addAll(merged, resources);
                    } catch (IOException ex) {
                        if (!logger.isDebugEnabled()) continue;
                        logger.debug("Could not retrieve resources for pattern '" + pattern + "'", ex);
                    }
                    continue;
                }
                if (e instanceof Resource) {
                    merged.add((Resource)e);
                    continue;
                }
                throw new IllegalArgumentException("Cannot convert element [" + e + "] to [" + Resource.class.getName() + "]: only location String and Resource object supported");
            }
            super.setValue(merged.toArray(new Resource[0]));
        } else {
            super.setValue(value);
        }
    }

    protected String resolvePath(String path) {
        if (this.propertyResolver == null) {
            this.propertyResolver = new StandardEnvironment();
        }
        return this.ignoreUnresolvablePlaceholders ? this.propertyResolver.resolvePlaceholders(path) : this.propertyResolver.resolveRequiredPlaceholders(path);
    }
}

