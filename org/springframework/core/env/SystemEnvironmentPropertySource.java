/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core.env;

import java.util.Map;
import org.springframework.core.env.MapPropertySource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class SystemEnvironmentPropertySource
extends MapPropertySource {
    public SystemEnvironmentPropertySource(String name, Map<String, Object> source) {
        super(name, source);
    }

    @Override
    public boolean containsProperty(String name) {
        return this.getProperty(name) != null;
    }

    @Override
    @Nullable
    public Object getProperty(String name) {
        String actualName = this.resolvePropertyName(name);
        if (this.logger.isDebugEnabled() && !name.equals(actualName)) {
            this.logger.debug("PropertySource '" + this.getName() + "' does not contain property '" + name + "', but found equivalent '" + actualName + "'");
        }
        return super.getProperty(actualName);
    }

    protected final String resolvePropertyName(String name) {
        Assert.notNull((Object)name, "Property name must not be null");
        String resolvedName = this.checkPropertyName(name);
        if (resolvedName != null) {
            return resolvedName;
        }
        String uppercasedName = name.toUpperCase();
        if (!name.equals(uppercasedName) && (resolvedName = this.checkPropertyName(uppercasedName)) != null) {
            return resolvedName;
        }
        return name;
    }

    @Nullable
    private String checkPropertyName(String name) {
        if (this.containsKey(name)) {
            return name;
        }
        String noDotName = name.replace('.', '_');
        if (!name.equals(noDotName) && this.containsKey(noDotName)) {
            return noDotName;
        }
        String noHyphenName = name.replace('-', '_');
        if (!name.equals(noHyphenName) && this.containsKey(noHyphenName)) {
            return noHyphenName;
        }
        String noDotNoHyphenName = noDotName.replace('-', '_');
        if (!noDotName.equals(noDotNoHyphenName) && this.containsKey(noDotNoHyphenName)) {
            return noDotNoHyphenName;
        }
        return null;
    }

    private boolean containsKey(String name) {
        return this.isSecurityManagerPresent() ? ((Map)this.source).keySet().contains(name) : ((Map)this.source).containsKey(name);
    }

    protected boolean isSecurityManagerPresent() {
        return System.getSecurityManager() != null;
    }
}

