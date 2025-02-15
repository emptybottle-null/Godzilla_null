/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.xml;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.NamespaceContext;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class SimpleNamespaceContext
implements NamespaceContext {
    private final Map<String, String> prefixToNamespaceUri = new HashMap<String, String>();
    private final Map<String, Set<String>> namespaceUriToPrefixes = new HashMap<String, Set<String>>();
    private String defaultNamespaceUri = "";

    @Override
    public String getNamespaceURI(String prefix) {
        Assert.notNull((Object)prefix, "No prefix given");
        if ("xml".equals(prefix)) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        if ("xmlns".equals(prefix)) {
            return "http://www.w3.org/2000/xmlns/";
        }
        if ("".equals(prefix)) {
            return this.defaultNamespaceUri;
        }
        if (this.prefixToNamespaceUri.containsKey(prefix)) {
            return this.prefixToNamespaceUri.get(prefix);
        }
        return "";
    }

    @Override
    @Nullable
    public String getPrefix(String namespaceUri) {
        Set<String> prefixes = this.getPrefixesSet(namespaceUri);
        return !prefixes.isEmpty() ? prefixes.iterator().next() : null;
    }

    @Override
    public Iterator<String> getPrefixes(String namespaceUri) {
        return this.getPrefixesSet(namespaceUri).iterator();
    }

    private Set<String> getPrefixesSet(String namespaceUri) {
        Assert.notNull((Object)namespaceUri, "No namespaceUri given");
        if (this.defaultNamespaceUri.equals(namespaceUri)) {
            return Collections.singleton("");
        }
        if ("http://www.w3.org/XML/1998/namespace".equals(namespaceUri)) {
            return Collections.singleton("xml");
        }
        if ("http://www.w3.org/2000/xmlns/".equals(namespaceUri)) {
            return Collections.singleton("xmlns");
        }
        Set<String> prefixes = this.namespaceUriToPrefixes.get(namespaceUri);
        return prefixes != null ? Collections.unmodifiableSet(prefixes) : Collections.emptySet();
    }

    public void setBindings(Map<String, String> bindings) {
        bindings.forEach(this::bindNamespaceUri);
    }

    public void bindDefaultNamespaceUri(String namespaceUri) {
        this.bindNamespaceUri("", namespaceUri);
    }

    public void bindNamespaceUri(String prefix, String namespaceUri) {
        Assert.notNull((Object)prefix, "No prefix given");
        Assert.notNull((Object)namespaceUri, "No namespaceUri given");
        if ("".equals(prefix)) {
            this.defaultNamespaceUri = namespaceUri;
        } else {
            this.prefixToNamespaceUri.put(prefix, namespaceUri);
            Set prefixes = this.namespaceUriToPrefixes.computeIfAbsent(namespaceUri, k -> new LinkedHashSet());
            prefixes.add(prefix);
        }
    }

    public void removeBinding(@Nullable String prefix) {
        Set<String> prefixes;
        String namespaceUri;
        if ("".equals(prefix)) {
            this.defaultNamespaceUri = "";
        } else if (prefix != null && (namespaceUri = this.prefixToNamespaceUri.remove(prefix)) != null && (prefixes = this.namespaceUriToPrefixes.get(namespaceUri)) != null) {
            prefixes.remove(prefix);
            if (prefixes.isEmpty()) {
                this.namespaceUriToPrefixes.remove(namespaceUri);
            }
        }
    }

    public void clear() {
        this.prefixToNamespaceUri.clear();
        this.namespaceUriToPrefixes.clear();
    }

    public Iterator<String> getBoundPrefixes() {
        return this.prefixToNamespaceUri.keySet().iterator();
    }
}

