/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.store;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBoolean;
import org.apache.xmlbeans.XmlDate;
import org.apache.xmlbeans.XmlDecimal;
import org.apache.xmlbeans.XmlDouble;
import org.apache.xmlbeans.XmlFloat;
import org.apache.xmlbeans.XmlInteger;
import org.apache.xmlbeans.XmlLong;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.common.DefaultClassLoaderResourceLoader;
import org.apache.xmlbeans.impl.common.XPath;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.store.PathDelegate;
import org.w3c.dom.Node;

public abstract class Path {
    public static final String PATH_DELEGATE_INTERFACE = "PATH_DELEGATE_INTERFACE";
    public static String _useDelegateForXpath = "use delegate for xpath";
    public static String _useXdkForXpath = "use xdk for xpath";
    public static String _useXqrlForXpath = "use xqrl for xpath";
    public static String _useXbeanForXpath = "use xbean for xpath";
    public static String _forceXqrl2002ForXpathXQuery = "use xqrl-2002 for xpath";
    private static final int USE_XBEAN = 1;
    private static final int USE_XQRL = 2;
    private static final int USE_DELEGATE = 4;
    private static final int USE_XQRL2002 = 8;
    private static final int USE_XDK = 16;
    private static Map _xbeanPathCache = new WeakHashMap();
    private static Map _xdkPathCache = new WeakHashMap();
    private static Map _xqrlPathCache = new WeakHashMap();
    private static Map _xqrl2002PathCache = new WeakHashMap();
    private static Method _xdkCompilePath;
    private static Method _xqrlCompilePath;
    private static Method _xqrl2002CompilePath;
    private static boolean _xdkAvailable;
    private static boolean _xqrlAvailable;
    private static boolean _xqrl2002Available;
    private static final String _delIntfName;
    private static final ReentrantReadWriteLock lock;
    protected final String _pathKey;

    Path(String key) {
        this._pathKey = key;
    }

    abstract PathEngine execute(Cur var1, XmlOptions var2);

    static String getCurrentNodeVar(XmlOptions options) {
        String currentNodeVar = "this";
        if ((options = XmlOptions.maskNull(options)).hasOption("XQUERY_CURRENT_NODE_VAR") && (currentNodeVar = (String)options.get("XQUERY_CURRENT_NODE_VAR")).startsWith("$")) {
            throw new IllegalArgumentException("Omit the '$' prefix for the current node variable");
        }
        return currentNodeVar;
    }

    public static Path getCompiledPath(String pathExpr, XmlOptions options) {
        int force = (options = XmlOptions.maskNull(options)).hasOption(_useDelegateForXpath) ? 4 : (options.hasOption(_useXqrlForXpath) ? 2 : (options.hasOption(_useXdkForXpath) ? 16 : (options.hasOption(_useXbeanForXpath) ? 1 : (options.hasOption(_forceXqrl2002ForXpathXQuery) ? 8 : 23))));
        String delIntfName = options.hasOption(PATH_DELEGATE_INTERFACE) ? (String)options.get(PATH_DELEGATE_INTERFACE) : _delIntfName;
        return Path.getCompiledPath(pathExpr, force, Path.getCurrentNodeVar(options), delIntfName);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static Path getCompiledPath(String pathExpr, int force, String currentVar, String delIntfName) {
        Path path = null;
        WeakReference pathWeakRef = null;
        HashMap namespaces = (force & 4) != 0 ? new HashMap() : null;
        lock.readLock().lock();
        try {
            if ((force & 1) != 0) {
                pathWeakRef = (WeakReference)_xbeanPathCache.get(pathExpr);
            }
            if (pathWeakRef == null && (force & 2) != 0) {
                pathWeakRef = (WeakReference)_xqrlPathCache.get(pathExpr);
            }
            if (pathWeakRef == null && (force & 0x10) != 0) {
                pathWeakRef = (WeakReference)_xdkPathCache.get(pathExpr);
            }
            if (pathWeakRef == null && (force & 8) != 0) {
                pathWeakRef = (WeakReference)_xqrl2002PathCache.get(pathExpr);
            }
            if (pathWeakRef != null) {
                path = (Path)pathWeakRef.get();
            }
            if (path != null) {
                Path path2 = path;
                return path2;
            }
        } finally {
            lock.readLock().unlock();
        }
        lock.writeLock().lock();
        try {
            if ((force & 1) != 0) {
                pathWeakRef = (WeakReference)_xbeanPathCache.get(pathExpr);
                if (pathWeakRef != null) {
                    path = (Path)pathWeakRef.get();
                }
                if (path == null) {
                    path = Path.getCompiledPathXbean(pathExpr, currentVar, namespaces);
                }
            }
            if (path == null && (force & 2) != 0) {
                pathWeakRef = (WeakReference)_xqrlPathCache.get(pathExpr);
                if (pathWeakRef != null) {
                    path = (Path)pathWeakRef.get();
                }
                if (path == null) {
                    path = Path.getCompiledPathXqrl(pathExpr, currentVar);
                }
            }
            if (path == null && (force & 0x10) != 0) {
                pathWeakRef = (WeakReference)_xdkPathCache.get(pathExpr);
                if (pathWeakRef != null) {
                    path = (Path)pathWeakRef.get();
                }
                if (path == null) {
                    path = Path.getCompiledPathXdk(pathExpr, currentVar);
                }
            }
            if (path == null && (force & 4) != 0) {
                path = Path.getCompiledPathDelegate(pathExpr, currentVar, namespaces, delIntfName);
            }
            if (path == null && (force & 8) != 0) {
                pathWeakRef = (WeakReference)_xqrl2002PathCache.get(pathExpr);
                if (pathWeakRef != null) {
                    path = (Path)pathWeakRef.get();
                }
                if (path == null) {
                    path = Path.getCompiledPathXqrl2002(pathExpr, currentVar);
                }
            }
            if (path == null) {
                StringBuffer errMessage = new StringBuffer();
                if ((force & 1) != 0) {
                    errMessage.append(" Trying XBeans path engine...");
                }
                if ((force & 2) != 0) {
                    errMessage.append(" Trying XQRL...");
                }
                if ((force & 0x10) != 0) {
                    errMessage.append(" Trying XDK...");
                }
                if ((force & 4) != 0) {
                    errMessage.append(" Trying delegated path engine...");
                }
                if ((force & 8) != 0) {
                    errMessage.append(" Trying XQRL2002...");
                }
                throw new RuntimeException(errMessage.toString() + " FAILED on " + pathExpr);
            }
        } finally {
            lock.writeLock().unlock();
        }
        return path;
    }

    private static Path getCompiledPathXdk(String pathExpr, String currentVar) {
        Path path = Path.createXdkCompiledPath(pathExpr, currentVar);
        if (path != null) {
            _xdkPathCache.put(path._pathKey, new WeakReference<Path>(path));
        }
        return path;
    }

    private static Path getCompiledPathXqrl(String pathExpr, String currentVar) {
        Path path = Path.createXqrlCompiledPath(pathExpr, currentVar);
        if (path != null) {
            _xqrlPathCache.put(path._pathKey, new WeakReference<Path>(path));
        }
        return path;
    }

    private static Path getCompiledPathXqrl2002(String pathExpr, String currentVar) {
        Path path = Path.createXqrl2002CompiledPath(pathExpr, currentVar);
        if (path != null) {
            _xqrl2002PathCache.put(path._pathKey, new WeakReference<Path>(path));
        }
        return path;
    }

    private static Path getCompiledPathXbean(String pathExpr, String currentVar, Map namespaces) {
        Path path = XbeanPath.create(pathExpr, currentVar, namespaces);
        if (path != null) {
            _xbeanPathCache.put(path._pathKey, new WeakReference<Path>(path));
        }
        return path;
    }

    private static Path getCompiledPathDelegate(String pathExpr, String currentVar, Map namespaces, String delIntfName) {
        Path path = null;
        if (namespaces == null) {
            namespaces = new HashMap();
        }
        try {
            XPath.compileXPath(pathExpr, currentVar, namespaces);
        } catch (XPath.XPathCompileException e) {
            // empty catch block
        }
        int offset = namespaces.get("$xmlbeans!ns_boundary") == null ? 0 : (Integer)namespaces.get("$xmlbeans!ns_boundary");
        namespaces.remove("$xmlbeans!ns_boundary");
        path = DelegatePathImpl.create(delIntfName, pathExpr.substring(offset), currentVar, namespaces);
        return path;
    }

    public static String compilePath(String pathExpr, XmlOptions options) {
        return Path.getCompiledPath((String)pathExpr, (XmlOptions)options)._pathKey;
    }

    private static Path createXdkCompiledPath(String pathExpr, String currentVar) {
        if (!_xdkAvailable) {
            return null;
        }
        if (_xdkCompilePath == null) {
            try {
                Class<?> xdkImpl = Class.forName("org.apache.xmlbeans.impl.store.OXQXBXqrlImpl");
                _xdkCompilePath = xdkImpl.getDeclaredMethod("compilePath", String.class, String.class, Boolean.class);
            } catch (ClassNotFoundException e) {
                _xdkAvailable = false;
                return null;
            } catch (Exception e) {
                _xdkAvailable = false;
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        Object[] args = new Object[]{pathExpr, currentVar, new Boolean(true)};
        try {
            return (Path)_xdkCompilePath.invoke(null, args);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            throw new RuntimeException(t.getMessage(), t);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static Path createXqrlCompiledPath(String pathExpr, String currentVar) {
        if (!_xqrlAvailable) {
            return null;
        }
        if (_xqrlCompilePath == null) {
            try {
                Class<?> xqrlImpl = Class.forName("org.apache.xmlbeans.impl.store.XqrlImpl");
                _xqrlCompilePath = xqrlImpl.getDeclaredMethod("compilePath", String.class, String.class, Boolean.class);
            } catch (ClassNotFoundException e) {
                _xqrlAvailable = false;
                return null;
            } catch (Exception e) {
                _xqrlAvailable = false;
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        Object[] args = new Object[]{pathExpr, currentVar, new Boolean(true)};
        try {
            return (Path)_xqrlCompilePath.invoke(null, args);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            throw new RuntimeException(t.getMessage(), t);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static Path createXqrl2002CompiledPath(String pathExpr, String currentVar) {
        if (!_xqrl2002Available) {
            return null;
        }
        if (_xqrl2002CompilePath == null) {
            try {
                Class<?> xqrlImpl = Class.forName("org.apache.xmlbeans.impl.store.Xqrl2002Impl");
                _xqrl2002CompilePath = xqrlImpl.getDeclaredMethod("compilePath", String.class, String.class, Boolean.class);
            } catch (ClassNotFoundException e) {
                _xqrl2002Available = false;
                return null;
            } catch (Exception e) {
                _xqrl2002Available = false;
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        Object[] args = new Object[]{pathExpr, currentVar, new Boolean(true)};
        try {
            return (Path)_xqrl2002CompilePath.invoke(null, args);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            throw new RuntimeException(t.getMessage(), t);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    static {
        _xdkAvailable = true;
        _xqrlAvailable = true;
        _xqrl2002Available = true;
        lock = new ReentrantReadWriteLock();
        String id = "META-INF/services/org.apache.xmlbeans.impl.store.PathDelegate.SelectPathInterface";
        InputStream in = new DefaultClassLoaderResourceLoader().getResourceAsStream(id);
        String name = null;
        if (in != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                name = br.readLine().trim();
                br.close();
            } catch (Exception exception) {
                // empty catch block
            }
        }
        _delIntfName = name;
    }

    private static final class DelegatePathImpl
    extends Path {
        private PathDelegate.SelectPathInterface _xpathImpl;

        static Path create(String implClassName, String pathExpr, String currentNodeVar, Map namespaceMap) {
            assert (!currentNodeVar.startsWith("$"));
            PathDelegate.SelectPathInterface impl = PathDelegate.createInstance(implClassName, pathExpr, currentNodeVar, namespaceMap);
            if (impl == null) {
                return null;
            }
            return new DelegatePathImpl(impl, pathExpr);
        }

        private DelegatePathImpl(PathDelegate.SelectPathInterface xpathImpl, String pathExpr) {
            super(pathExpr);
            this._xpathImpl = xpathImpl;
        }

        @Override
        protected PathEngine execute(Cur c, XmlOptions options) {
            return new DelegatePathEngine(this._xpathImpl, c);
        }

        private static class DelegatePathEngine
        extends XPath.ExecutionContext
        implements PathEngine {
            private final DateFormat xmlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            private Cur _cur;
            private PathDelegate.SelectPathInterface _engine;
            private boolean _firstCall = true;
            private long _version;

            DelegatePathEngine(PathDelegate.SelectPathInterface xpathImpl, Cur c) {
                this._engine = xpathImpl;
                this._version = c._locale.version();
                this._cur = c.weakCur(this);
            }

            @Override
            public boolean next(Cur c) {
                if (!this._firstCall) {
                    return false;
                }
                this._firstCall = false;
                if (this._cur != null && this._version != this._cur._locale.version()) {
                    throw new ConcurrentModificationException("Document changed during select");
                }
                DomImpl.Dom context_node = this._cur.getDom();
                List resultsList = this._engine.selectPath(context_node);
                for (int i = 0; i < resultsList.size(); ++i) {
                    Object node = resultsList.get(i);
                    Cur pos = null;
                    if (!(node instanceof Node)) {
                        Object obj = resultsList.get(i);
                        String value = obj instanceof Date ? this.xmlDateFormat.format((Date)obj) : (obj instanceof BigDecimal ? ((BigDecimal)obj).toPlainString() : obj.toString());
                        Locale l = c._locale;
                        try {
                            pos = l.load("<xml-fragment/>").tempCur();
                            pos.setValue(value);
                            SchemaType type = this.getType(node);
                            Locale.autoTypeDocument(pos, type, null);
                            pos.next();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        assert (node instanceof DomImpl.Dom) : "New object created in XPATH!";
                        pos = ((DomImpl.Dom)node).tempCur();
                    }
                    c.addToSelection(pos);
                    pos.release();
                }
                this.release();
                this._engine = null;
                return true;
            }

            private SchemaType getType(Object node) {
                SchemaType type = node instanceof Integer ? XmlInteger.type : (node instanceof Double ? XmlDouble.type : (node instanceof Long ? XmlLong.type : (node instanceof Float ? XmlFloat.type : (node instanceof BigDecimal ? XmlDecimal.type : (node instanceof Boolean ? XmlBoolean.type : (node instanceof String ? XmlString.type : (node instanceof Date ? XmlDate.type : XmlAnySimpleType.type)))))));
                return type;
            }

            @Override
            public void release() {
                if (this._cur != null) {
                    this._cur.release();
                    this._cur = null;
                }
            }
        }
    }

    private static final class XbeanPathEngine
    extends XPath.ExecutionContext
    implements PathEngine {
        private final long _version;
        private Cur _cur;

        XbeanPathEngine(XPath xpath, Cur c) {
            assert (c.isContainer());
            this._version = c._locale.version();
            this._cur = c.weakCur(this);
            this._cur.push();
            this.init(xpath);
            int ret = this.start();
            if ((ret & 1) != 0) {
                c.addToSelection();
            }
            this.doAttrs(ret, c);
            if ((ret & 2) == 0 || !Locale.toFirstChildElement(this._cur)) {
                this.release();
            }
        }

        private void advance(Cur c) {
            assert (this._cur != null);
            if (this._cur.isFinish()) {
                if (this._cur.isAtEndOfLastPush()) {
                    this.release();
                } else {
                    this.end();
                    this._cur.next();
                }
            } else if (this._cur.isElem()) {
                int ret = this.element(this._cur.getName());
                if ((ret & 1) != 0) {
                    c.addToSelection(this._cur);
                }
                this.doAttrs(ret, c);
                if ((ret & 2) == 0 || !Locale.toFirstChildElement(this._cur)) {
                    this.end();
                    this._cur.skip();
                }
            } else {
                do {
                    this._cur.next();
                } while (!this._cur.isContainerOrFinish());
            }
        }

        private void doAttrs(int ret, Cur c) {
            assert (this._cur.isContainer());
            if ((ret & 4) != 0 && this._cur.toFirstAttr()) {
                do {
                    if (!this.attr(this._cur.getName())) continue;
                    c.addToSelection(this._cur);
                } while (this._cur.toNextAttr());
                this._cur.toParent();
            }
        }

        @Override
        public boolean next(Cur c) {
            if (this._cur != null && this._version != this._cur._locale.version()) {
                throw new ConcurrentModificationException("Document changed during select");
            }
            int startCount = c.selectionCount();
            while (this._cur != null) {
                this.advance(c);
                if (startCount == c.selectionCount()) continue;
                return true;
            }
            return false;
        }

        @Override
        public void release() {
            if (this._cur != null) {
                this._cur.release();
                this._cur = null;
            }
        }
    }

    private static final class XbeanPath
    extends Path {
        private final String _currentVar;
        private final XPath _compiledPath;
        public Map namespaces;

        static Path create(String pathExpr, String currentVar, Map namespaces) {
            try {
                return new XbeanPath(pathExpr, currentVar, XPath.compileXPath(pathExpr, currentVar, namespaces));
            } catch (XPath.XPathCompileException e) {
                return null;
            }
        }

        private XbeanPath(String pathExpr, String currentVar, XPath xpath) {
            super(pathExpr);
            this._currentVar = currentVar;
            this._compiledPath = xpath;
        }

        @Override
        PathEngine execute(Cur c, XmlOptions options) {
            String delIntfName;
            String string = delIntfName = (options = XmlOptions.maskNull(options)).hasOption(Path.PATH_DELEGATE_INTERFACE) ? (String)options.get(Path.PATH_DELEGATE_INTERFACE) : _delIntfName;
            if (!c.isContainer() || this._compiledPath.sawDeepDot()) {
                int force = 22;
                return XbeanPath.getCompiledPath(this._pathKey, force, this._currentVar, delIntfName).execute(c, options);
            }
            return new XbeanPathEngine(this._compiledPath, c);
        }
    }

    static interface PathEngine {
        public void release();

        public boolean next(Cur var1);
    }
}

