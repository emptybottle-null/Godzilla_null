/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.dom4j.DocumentFactory;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class QNameCache {
    protected Map<String, QName> noNamespaceCache = Collections.synchronizedMap(new WeakHashMap());
    protected Map<Namespace, Map<String, QName>> namespaceCache = Collections.synchronizedMap(new WeakHashMap());
    private DocumentFactory documentFactory;

    public QNameCache() {
    }

    public QNameCache(DocumentFactory documentFactory) {
        this.documentFactory = documentFactory;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public List<QName> getQNames() {
        ArrayList<QName> answer = new ArrayList<QName>();
        Map<Object, Object> map = this.noNamespaceCache;
        synchronized (map) {
            answer.addAll(this.noNamespaceCache.values());
        }
        map = this.namespaceCache;
        synchronized (map) {
            for (Map<String, QName> map2 : this.namespaceCache.values()) {
                answer.addAll(map2.values());
            }
        }
        return answer;
    }

    public QName get(String name) {
        QName answer = null;
        if (name != null) {
            answer = this.noNamespaceCache.get(name);
        } else {
            name = "";
        }
        if (answer == null) {
            answer = this.createQName(name);
            answer.setDocumentFactory(this.documentFactory);
            this.noNamespaceCache.put(name, answer);
        }
        return answer;
    }

    public QName get(String name, Namespace namespace) {
        Map<String, QName> cache = this.getNamespaceCache(namespace);
        QName answer = null;
        if (name != null) {
            answer = cache.get(name);
        } else {
            name = "";
        }
        if (answer == null) {
            answer = this.createQName(name, namespace);
            answer.setDocumentFactory(this.documentFactory);
            cache.put(name, answer);
        }
        return answer;
    }

    public QName get(String localName, Namespace namespace, String qName) {
        Map<String, QName> cache = this.getNamespaceCache(namespace);
        QName answer = null;
        if (localName != null) {
            answer = cache.get(localName);
        } else {
            localName = "";
        }
        if (answer == null) {
            answer = this.createQName(localName, namespace, qName);
            answer.setDocumentFactory(this.documentFactory);
            cache.put(localName, answer);
        }
        return answer;
    }

    public QName get(String qualifiedName, String uri) {
        int index = qualifiedName.indexOf(58);
        if (index < 0) {
            return this.get(qualifiedName, Namespace.get(uri));
        }
        if (index == 0) {
            throw new IllegalArgumentException("Qualified name cannot start with ':'.");
        }
        String name = qualifiedName.substring(index + 1);
        String prefix = qualifiedName.substring(0, index);
        return this.get(name, Namespace.get(prefix, uri));
    }

    public QName intern(QName qname) {
        return this.get(qname.getName(), qname.getNamespace(), qname.getQualifiedName());
    }

    protected Map<String, QName> getNamespaceCache(Namespace namespace) {
        if (namespace == Namespace.NO_NAMESPACE) {
            return this.noNamespaceCache;
        }
        Map<String, QName> answer = null;
        if (namespace != null) {
            answer = this.namespaceCache.get(namespace);
        }
        if (answer == null) {
            answer = this.createMap();
            this.namespaceCache.put(namespace, answer);
        }
        return answer;
    }

    protected Map<String, QName> createMap() {
        return Collections.synchronizedMap(new HashMap());
    }

    protected QName createQName(String name) {
        return new QName(name);
    }

    protected QName createQName(String name, Namespace namespace) {
        return new QName(name, namespace);
    }

    protected QName createQName(String name, Namespace namespace, String qualifiedName) {
        return new QName(name, namespace, qualifiedName);
    }
}

