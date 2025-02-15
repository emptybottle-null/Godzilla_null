/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.store;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDate;
import org.apache.xmlbeans.XmlDecimal;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlLong;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlRuntimeException;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.common.DefaultClassLoaderResourceLoader;
import org.apache.xmlbeans.impl.common.XPath;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.Cursor;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.store.Path;
import org.apache.xmlbeans.impl.store.QueryDelegate;
import org.w3c.dom.Node;

public abstract class Query {
    public static final String QUERY_DELEGATE_INTERFACE = "QUERY_DELEGATE_INTERFACE";
    public static String _useDelegateForXQuery = "use delegate for xquery";
    public static String _useXdkForXQuery = "use xdk for xquery";
    private static String _delIntfName;
    private static HashMap _xdkQueryCache;
    private static Method _xdkCompileQuery;
    private static boolean _xdkAvailable;
    private static HashMap _xqrlQueryCache;
    private static Method _xqrlCompileQuery;
    private static boolean _xqrlAvailable;
    private static HashMap _xqrl2002QueryCache;
    private static Method _xqrl2002CompileQuery;
    private static boolean _xqrl2002Available;

    abstract XmlObject[] objectExecute(Cur var1, XmlOptions var2);

    abstract XmlCursor cursorExecute(Cur var1, XmlOptions var2);

    static XmlObject[] objectExecQuery(Cur c, String queryExpr, XmlOptions options) {
        return Query.getCompiledQuery(queryExpr, options).objectExecute(c, options);
    }

    static XmlCursor cursorExecQuery(Cur c, String queryExpr, XmlOptions options) {
        return Query.getCompiledQuery(queryExpr, options).cursorExecute(c, options);
    }

    public static synchronized Query getCompiledQuery(String queryExpr, XmlOptions options) {
        return Query.getCompiledQuery(queryExpr, Path.getCurrentNodeVar(options), options);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     */
    static synchronized Query getCompiledQuery(String queryExpr, String currentVar, XmlOptions options) {
        String delIntfName;
        Query query;
        assert (queryExpr != null);
        if ((options = XmlOptions.maskNull(options)).hasOption(Path._forceXqrl2002ForXpathXQuery)) {
            Query query2 = (Query)_xqrl2002QueryCache.get(queryExpr);
            if (query2 != null) {
                return query2;
            }
            query2 = Query.getXqrl2002CompiledQuery(queryExpr, currentVar);
            if (query2 != null) {
                _xqrl2002QueryCache.put(queryExpr, query2);
                return query2;
            }
            throw new RuntimeException("No 2002 query engine found.");
        }
        HashMap boundary = new HashMap();
        int boundaryVal = 0;
        try {
            XPath.compileXPath(queryExpr, currentVar, boundary);
            boundaryVal = boundary.get("$xmlbeans!ns_boundary") == null ? 0 : (Integer)boundary.get("$xmlbeans!ns_boundary");
        } catch (XPath.XPathCompileException e) {
            boundaryVal = boundary.get("$xmlbeans!ns_boundary") == null ? 0 : (Integer)boundary.get("$xmlbeans!ns_boundary");
            catch (Throwable throwable) {
                boundaryVal = boundary.get("$xmlbeans!ns_boundary") == null ? 0 : (Integer)boundary.get("$xmlbeans!ns_boundary");
                throw throwable;
            }
        }
        if (options.hasOption(_useXdkForXQuery)) {
            query = (Query)_xdkQueryCache.get(queryExpr);
            if (query != null) {
                return query;
            }
            query = Query.createXdkCompiledQuery(queryExpr, currentVar);
            if (query != null) {
                _xdkQueryCache.put(queryExpr, query);
                return query;
            }
        }
        if (!options.hasOption(_useDelegateForXQuery)) {
            query = (Query)_xqrlQueryCache.get(queryExpr);
            if (query != null) {
                return query;
            }
            query = Query.createXqrlCompiledQuery(queryExpr, currentVar);
            if (query != null) {
                _xqrlQueryCache.put(queryExpr, query);
                return query;
            }
        }
        if ((query = DelegateQueryImpl.createDelegateCompiledQuery(delIntfName = options.hasOption(QUERY_DELEGATE_INTERFACE) ? (String)options.get(QUERY_DELEGATE_INTERFACE) : _delIntfName, queryExpr, currentVar, boundaryVal, options)) != null) {
            return query;
        }
        throw new RuntimeException("No query engine found");
    }

    public static synchronized String compileQuery(String queryExpr, XmlOptions options) {
        Query.getCompiledQuery(queryExpr, options);
        return queryExpr;
    }

    private static Query createXdkCompiledQuery(String queryExpr, String currentVar) {
        if (!_xdkAvailable) {
            return null;
        }
        if (_xdkCompileQuery == null) {
            try {
                Class<?> xdkImpl = Class.forName("org.apache.xmlbeans.impl.store.OXQXBXqrlImpl");
                _xdkCompileQuery = xdkImpl.getDeclaredMethod("compileQuery", String.class, String.class, Boolean.class);
            } catch (ClassNotFoundException e) {
                _xdkAvailable = false;
                return null;
            } catch (Exception e) {
                _xdkAvailable = false;
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        Object[] args = new Object[]{queryExpr, currentVar, new Boolean(true)};
        try {
            return (Query)_xdkCompileQuery.invoke(null, args);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            throw new RuntimeException(t.getMessage(), t);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static Query createXqrlCompiledQuery(String queryExpr, String currentVar) {
        if (!_xqrlAvailable) {
            return null;
        }
        if (_xqrlCompileQuery == null) {
            try {
                Class<?> xqrlImpl = Class.forName("org.apache.xmlbeans.impl.store.XqrlImpl");
                _xqrlCompileQuery = xqrlImpl.getDeclaredMethod("compileQuery", String.class, String.class, Boolean.class);
            } catch (ClassNotFoundException e) {
                _xqrlAvailable = false;
                return null;
            } catch (Exception e) {
                _xqrlAvailable = false;
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        Object[] args = new Object[]{queryExpr, currentVar, new Boolean(true)};
        try {
            return (Query)_xqrlCompileQuery.invoke(null, args);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            throw new RuntimeException(t.getMessage(), t);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static Query getXqrl2002CompiledQuery(String queryExpr, String currentVar) {
        if (_xqrl2002Available && _xqrl2002CompileQuery == null) {
            try {
                Class<?> xqrlImpl = Class.forName("org.apache.xmlbeans.impl.store.Xqrl2002Impl");
                _xqrl2002CompileQuery = xqrlImpl.getDeclaredMethod("compileQuery", String.class, String.class, Boolean.class);
            } catch (ClassNotFoundException e) {
                _xqrl2002Available = false;
                return null;
            } catch (Exception e) {
                _xqrl2002Available = false;
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        Object[] args = new Object[]{queryExpr, currentVar, new Boolean(true)};
        try {
            return (Query)_xqrl2002CompileQuery.invoke(null, args);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            throw new RuntimeException(t.getMessage(), t);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    static {
        _xdkQueryCache = new HashMap();
        _xdkAvailable = true;
        _xqrlQueryCache = new HashMap();
        _xqrlAvailable = true;
        _xqrl2002QueryCache = new HashMap();
        _xqrl2002Available = true;
        String id = "META-INF/services/org.apache.xmlbeans.impl.store.QueryDelegate.QueryInterface";
        InputStream in = new DefaultClassLoaderResourceLoader().getResourceAsStream(id);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            _delIntfName = br.readLine().trim();
            br.close();
        } catch (Exception e) {
            _delIntfName = null;
        }
    }

    private static final class DelegateQueryImpl
    extends Query {
        private QueryDelegate.QueryInterface _xqueryImpl;

        private DelegateQueryImpl(QueryDelegate.QueryInterface xqueryImpl) {
            this._xqueryImpl = xqueryImpl;
        }

        static Query createDelegateCompiledQuery(String delIntfName, String queryExpr, String currentVar, int boundary, XmlOptions xmlOptions) {
            assert (!currentVar.startsWith(".") && !currentVar.startsWith(".."));
            QueryDelegate.QueryInterface impl = QueryDelegate.createInstance(delIntfName, queryExpr, currentVar, boundary, xmlOptions);
            if (impl == null) {
                return null;
            }
            return new DelegateQueryImpl(impl);
        }

        @Override
        XmlObject[] objectExecute(Cur c, XmlOptions options) {
            return new DelegateQueryEngine(this._xqueryImpl, c, options).objectExecute();
        }

        @Override
        XmlCursor cursorExecute(Cur c, XmlOptions options) {
            return new DelegateQueryEngine(this._xqueryImpl, c, options).cursorExecute();
        }

        private static class DelegateQueryEngine {
            private Cur _cur;
            private QueryDelegate.QueryInterface _engine;
            private long _version;
            private XmlOptions _options;

            public DelegateQueryEngine(QueryDelegate.QueryInterface xqImpl, Cur c, XmlOptions opt) {
                this._engine = xqImpl;
                this._version = c._locale.version();
                this._cur = c.weakCur(this);
                this._options = opt;
            }

            public XmlObject[] objectExecute() {
                if (this._cur == null || this._version != this._cur._locale.version()) {
                    // empty if block
                }
                Map bindings = (Map)XmlOptions.maskNull(this._options).get("XQUERY_VARIABLE_MAP");
                List resultsList = this._engine.execQuery(this._cur.getDom(), bindings);
                assert (resultsList.size() > -1);
                XmlObject[] result = new XmlObject[resultsList.size()];
                for (int i = 0; i < resultsList.size(); ++i) {
                    Locale l = Locale.getLocale(this._cur._locale._schemaTypeLoader, this._options);
                    l.enter();
                    Object node = resultsList.get(i);
                    Cur res = null;
                    try {
                        if (!(node instanceof Node)) {
                            res = l.load("<xml-fragment/>").tempCur();
                            res.setValue(node.toString());
                            SchemaType type = this.getType(node);
                            Locale.autoTypeDocument(res, type, null);
                            result[i] = res.getObject();
                        } else {
                            res = this.loadNode(l, (Node)node);
                        }
                        result[i] = res.getObject();
                    } catch (XmlException e) {
                        throw new RuntimeException(e);
                    } finally {
                        l.exit();
                    }
                    res.release();
                }
                this.release();
                this._engine = null;
                return result;
            }

            private SchemaType getType(Object node) {
                SchemaType type = node instanceof Integer ? XmlInteger.type : (node instanceof Double ? XmlDouble.type : (node instanceof Long ? XmlLong.type : (node instanceof Float ? XmlFloat.type : (node instanceof BigDecimal ? XmlDecimal.type : (node instanceof Boolean ? XmlBoolean.type : (node instanceof String ? XmlString.type : (node instanceof Date ? XmlDate.type : XmlAnySimpleType.type)))))));
                return type;
            }

            /*
             * WARNING - Removed try catching itself - possible behaviour change.
             */
            public XmlCursor cursorExecute() {
                if (this._cur == null || this._version != this._cur._locale.version()) {
                    // empty if block
                }
                Map bindings = (Map)XmlOptions.maskNull(this._options).get("XQUERY_VARIABLE_MAP");
                List resultsList = this._engine.execQuery(this._cur.getDom(), bindings);
                assert (resultsList.size() > -1);
                this._engine = null;
                Locale locale = Locale.getLocale(this._cur._locale._schemaTypeLoader, this._options);
                locale.enter();
                Cur.CurLoadContext _context = new Cur.CurLoadContext(locale, this._options);
                Cursor resultCur = null;
                try {
                    for (int i = 0; i < resultsList.size(); ++i) {
                        this.loadNodeHelper(locale, (Node)resultsList.get(i), _context);
                    }
                    Cur c = ((Locale.LoadContext)_context).finish();
                    Locale.associateSourceName(c, this._options);
                    Locale.autoTypeDocument(c, null, this._options);
                    resultCur = new Cursor(c);
                } catch (Exception e) {
                } finally {
                    locale.exit();
                }
                this.release();
                return resultCur;
            }

            public void release() {
                if (this._cur != null) {
                    this._cur.release();
                    this._cur = null;
                }
            }

            private Cur loadNode(Locale locale, Node node) {
                Cur.CurLoadContext context = new Cur.CurLoadContext(locale, this._options);
                try {
                    this.loadNodeHelper(locale, node, context);
                    Cur c = ((Locale.LoadContext)context).finish();
                    Locale.associateSourceName(c, this._options);
                    Locale.autoTypeDocument(c, null, this._options);
                    return c;
                } catch (Exception e) {
                    throw new XmlRuntimeException(e.getMessage(), e);
                }
            }

            private void loadNodeHelper(Locale locale, Node node, Locale.LoadContext context) {
                if (node.getNodeType() == 2) {
                    QName attName = new QName(node.getNamespaceURI(), node.getLocalName(), node.getPrefix());
                    context.attr(attName, node.getNodeValue());
                } else {
                    locale.loadNode(node, context);
                }
            }
        }
    }
}

