/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.dom4j.Namespace;

public class NamespaceCache {
    protected static Map<String, Map<String, WeakReference<Namespace>>> cache = new ConcurrentHashMap<String, Map<String, WeakReference<Namespace>>>();
    protected static Map<String, WeakReference<Namespace>> noPrefixCache = new ConcurrentHashMap<String, WeakReference<Namespace>>();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Namespace get(String prefix, String uri) {
        Map<String, WeakReference<Namespace>> uriCache = this.getURICache(uri);
        WeakReference<Namespace> ref = uriCache.get(prefix);
        Namespace answer = null;
        if (ref != null) {
            answer = (Namespace)ref.get();
        }
        if (answer == null) {
            Map<String, WeakReference<Namespace>> map = uriCache;
            synchronized (map) {
                ref = uriCache.get(prefix);
                if (ref != null) {
                    answer = (Namespace)ref.get();
                }
                if (answer == null) {
                    answer = this.createNamespace(prefix, uri);
                    uriCache.put(prefix, new WeakReference<Namespace>(answer));
                }
            }
        }
        return answer;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Namespace get(String uri) {
        WeakReference<Namespace> ref = noPrefixCache.get(uri);
        Namespace answer = null;
        if (ref != null) {
            answer = (Namespace)ref.get();
        }
        if (answer == null) {
            Map<String, WeakReference<Namespace>> map = noPrefixCache;
            synchronized (map) {
                ref = noPrefixCache.get(uri);
                if (ref != null) {
                    answer = (Namespace)ref.get();
                }
                if (answer == null) {
                    answer = this.createNamespace("", uri);
                    noPrefixCache.put(uri, new WeakReference<Namespace>(answer));
                }
            }
        }
        return answer;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    protected Map<String, WeakReference<Namespace>> getURICache(String uri) {
        Map<String, WeakReference<Namespace>> answer = cache.get(uri);
        if (answer == null) {
            Map<String, Map<String, WeakReference<Namespace>>> map = cache;
            synchronized (map) {
                answer = cache.get(uri);
                if (answer == null) {
                    answer = new ConcurrentHashMap<String, WeakReference<Namespace>>();
                    cache.put(uri, answer);
                }
            }
        }
        return answer;
    }

    protected Namespace createNamespace(String prefix, String uri) {
        return new Namespace(prefix, uri);
    }
}

