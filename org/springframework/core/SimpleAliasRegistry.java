/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.AliasRegistry;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

public class SimpleAliasRegistry
implements AliasRegistry {
    protected final Log logger = LogFactory.getLog(this.getClass());
    private final Map<String, String> aliasMap = new ConcurrentHashMap<String, String>(16);

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void registerAlias(String name, String alias) {
        Assert.hasText(name, "'name' must not be empty");
        Assert.hasText(alias, "'alias' must not be empty");
        Map<String, String> map = this.aliasMap;
        synchronized (map) {
            if (alias.equals(name)) {
                this.aliasMap.remove(alias);
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Alias definition '" + alias + "' ignored since it points to same name");
                }
            } else {
                String registeredName = this.aliasMap.get(alias);
                if (registeredName != null) {
                    if (registeredName.equals(name)) {
                        return;
                    }
                    if (!this.allowAliasOverriding()) {
                        throw new IllegalStateException("Cannot define alias '" + alias + "' for name '" + name + "': It is already registered for name '" + registeredName + "'.");
                    }
                    if (this.logger.isDebugEnabled()) {
                        this.logger.debug("Overriding alias '" + alias + "' definition for registered name '" + registeredName + "' with new target name '" + name + "'");
                    }
                }
                this.checkForAliasCircle(name, alias);
                this.aliasMap.put(alias, name);
                if (this.logger.isTraceEnabled()) {
                    this.logger.trace("Alias definition '" + alias + "' registered for name '" + name + "'");
                }
            }
        }
    }

    protected boolean allowAliasOverriding() {
        return true;
    }

    public boolean hasAlias(String name, String alias) {
        String registeredName = this.aliasMap.get(alias);
        return ObjectUtils.nullSafeEquals(registeredName, name) || registeredName != null && this.hasAlias(name, registeredName);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void removeAlias(String alias) {
        Map<String, String> map = this.aliasMap;
        synchronized (map) {
            String name = this.aliasMap.remove(alias);
            if (name == null) {
                throw new IllegalStateException("No alias '" + alias + "' registered");
            }
        }
    }

    @Override
    public boolean isAlias(String name) {
        return this.aliasMap.containsKey(name);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String[] getAliases(String name) {
        ArrayList<String> result = new ArrayList<String>();
        Map<String, String> map = this.aliasMap;
        synchronized (map) {
            this.retrieveAliases(name, result);
        }
        return StringUtils.toStringArray(result);
    }

    private void retrieveAliases(String name, List<String> result) {
        this.aliasMap.forEach((alias, registeredName) -> {
            if (registeredName.equals(name)) {
                result.add((String)alias);
                this.retrieveAliases((String)alias, result);
            }
        });
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void resolveAliases(StringValueResolver valueResolver) {
        Assert.notNull((Object)valueResolver, "StringValueResolver must not be null");
        Map<String, String> map = this.aliasMap;
        synchronized (map) {
            HashMap<String, String> aliasCopy = new HashMap<String, String>(this.aliasMap);
            aliasCopy.forEach((alias, registeredName) -> {
                String resolvedAlias = valueResolver.resolveStringValue((String)alias);
                String resolvedName = valueResolver.resolveStringValue((String)registeredName);
                if (resolvedAlias == null || resolvedName == null || resolvedAlias.equals(resolvedName)) {
                    this.aliasMap.remove(alias);
                } else if (!resolvedAlias.equals(alias)) {
                    String existingName = this.aliasMap.get(resolvedAlias);
                    if (existingName != null) {
                        if (existingName.equals(resolvedName)) {
                            this.aliasMap.remove(alias);
                            return;
                        }
                        throw new IllegalStateException("Cannot register resolved alias '" + resolvedAlias + "' (original: '" + alias + "') for name '" + resolvedName + "': It is already registered for name '" + registeredName + "'.");
                    }
                    this.checkForAliasCircle(resolvedName, resolvedAlias);
                    this.aliasMap.remove(alias);
                    this.aliasMap.put(resolvedAlias, resolvedName);
                } else if (!registeredName.equals(resolvedName)) {
                    this.aliasMap.put((String)alias, resolvedName);
                }
            });
        }
    }

    protected void checkForAliasCircle(String name, String alias) {
        if (this.hasAlias(alias, name)) {
            throw new IllegalStateException("Cannot register alias '" + alias + "' for name '" + name + "': Circular reference - '" + name + "' is a direct or indirect alias for '" + alias + "' already");
        }
    }

    public String canonicalName(String name) {
        String resolvedName;
        String canonicalName = name;
        do {
            if ((resolvedName = this.aliasMap.get(canonicalName)) == null) continue;
            canonicalName = resolvedName;
        } while (resolvedName != null);
        return canonicalName;
    }
}

