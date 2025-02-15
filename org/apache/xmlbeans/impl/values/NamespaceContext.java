/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Map;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.TypeStoreUser;
import org.apache.xmlbeans.xml.stream.StartElement;

public class NamespaceContext
implements PrefixResolver {
    private static final int TYPE_STORE = 1;
    private static final int XML_OBJECT = 2;
    private static final int MAP = 3;
    private static final int START_ELEMENT = 4;
    private static final int RESOLVER = 5;
    private Object _obj;
    private int _code;
    private static ThreadLocal tl_namespaceContextStack = new ThreadLocal();

    public NamespaceContext(Map prefixToUriMap) {
        this._code = 3;
        this._obj = prefixToUriMap;
    }

    public NamespaceContext(TypeStore typeStore) {
        this._code = 1;
        this._obj = typeStore;
    }

    public NamespaceContext(XmlObject xmlObject) {
        this._code = 2;
        this._obj = xmlObject;
    }

    public NamespaceContext(StartElement start) {
        this._code = 4;
        this._obj = start;
    }

    public NamespaceContext(PrefixResolver resolver) {
        this._code = 5;
        this._obj = resolver;
    }

    public static void clearThreadLocals() {
        tl_namespaceContextStack.remove();
    }

    private static NamespaceContextStack getNamespaceContextStack() {
        NamespaceContextStack namespaceContextStack = (NamespaceContextStack)tl_namespaceContextStack.get();
        if (namespaceContextStack == null) {
            namespaceContextStack = new NamespaceContextStack();
            tl_namespaceContextStack.set(namespaceContextStack);
        }
        return namespaceContextStack;
    }

    public static void push(NamespaceContext next) {
        NamespaceContext.getNamespaceContextStack().push(next);
    }

    public static void pop() {
        NamespaceContextStack nsContextStack = NamespaceContext.getNamespaceContextStack();
        nsContextStack.pop();
        if (nsContextStack.stack.size() == 0) {
            tl_namespaceContextStack.set(null);
        }
    }

    public static PrefixResolver getCurrent() {
        return NamespaceContext.getNamespaceContextStack().current;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getNamespaceForPrefix(String prefix) {
        if (prefix != null && prefix.equals("xml")) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        switch (this._code) {
            case 2: {
                Object obj = this._obj;
                if (Proxy.isProxyClass(obj.getClass())) {
                    obj = Proxy.getInvocationHandler(obj);
                }
                if (obj instanceof TypeStoreUser) {
                    return ((TypeStoreUser)obj).get_store().getNamespaceForPrefix(prefix);
                }
                XmlCursor cur = ((XmlObject)this._obj).newCursor();
                if (cur != null) {
                    if (cur.currentTokenType() == XmlCursor.TokenType.ATTR) {
                        cur.toParent();
                    }
                    try {
                        String string = cur.namespaceForPrefix(prefix);
                        return string;
                    } finally {
                        cur.dispose();
                    }
                }
            }
            case 3: {
                return (String)((Map)this._obj).get(prefix);
            }
            case 1: {
                return ((TypeStore)this._obj).getNamespaceForPrefix(prefix);
            }
            case 4: {
                return ((StartElement)this._obj).getNamespaceUri(prefix);
            }
            case 5: {
                return ((PrefixResolver)this._obj).getNamespaceForPrefix(prefix);
            }
        }
        assert (false) : "Improperly initialized NamespaceContext.";
        return null;
    }

    private static final class NamespaceContextStack {
        NamespaceContext current;
        ArrayList stack = new ArrayList();

        private NamespaceContextStack() {
        }

        final void push(NamespaceContext next) {
            this.stack.add(this.current);
            this.current = next;
        }

        final void pop() {
            this.current = (NamespaceContext)this.stack.get(this.stack.size() - 1);
            this.stack.remove(this.stack.size() - 1);
        }
    }
}

