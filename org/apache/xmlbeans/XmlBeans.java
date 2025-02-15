/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.File;
import java.lang.ref.SoftReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.BindingConfig;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.QNameCache;
import org.apache.xmlbeans.ResourceLoader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.w3c.dom.Node;

public final class XmlBeans {
    private static String XMLBEANS_TITLE = "org.apache.xmlbeans";
    private static String XMLBEANS_VERSION = "3.1.0";
    private static String XMLBEANS_VENDOR = "Apache Software Foundation";
    private static final ThreadLocal _threadLocalLoaderQNameCache;
    private static final Method _getContextTypeLoaderMethod;
    private static final Method _getBuiltinSchemaTypeSystemMethod;
    private static final Method _getNoTypeMethod;
    private static final Method _typeLoaderBuilderMethod;
    private static final Method _compilationMethod;
    private static final Method _nodeToCursorMethod;
    private static final Method _nodeToXmlObjectMethod;
    private static final Method _nodeToXmlStreamMethod;
    private static final Method _streamToNodeMethod;
    private static final Constructor _pathResourceLoaderConstructor;
    private static final String HOLDER_CLASS_NAME = "TypeSystemHolder";
    private static final String TYPE_SYSTEM_FIELD = "typeSystem";
    public static SchemaType NO_TYPE;

    public static final String getTitle() {
        return XMLBEANS_TITLE;
    }

    public static final String getVendor() {
        return XMLBEANS_VENDOR;
    }

    public static final String getVersion() {
        return XMLBEANS_VERSION;
    }

    public static void clearThreadLocals() {
        _threadLocalLoaderQNameCache.remove();
    }

    public static QNameCache getQNameCache() {
        SoftReference softRef = (SoftReference)_threadLocalLoaderQNameCache.get();
        QNameCache qnameCache = (QNameCache)softRef.get();
        if (qnameCache == null) {
            qnameCache = new QNameCache(32);
            _threadLocalLoaderQNameCache.set(new SoftReference<QNameCache>(qnameCache));
        }
        return qnameCache;
    }

    public static QName getQName(String localPart) {
        return XmlBeans.getQNameCache().getName("", localPart);
    }

    public static QName getQName(String namespaceUri, String localPart) {
        return XmlBeans.getQNameCache().getName(namespaceUri, localPart);
    }

    private static RuntimeException causedException(RuntimeException e, Throwable cause) {
        e.initCause(cause);
        return e;
    }

    private static XmlException wrappedException(Throwable e) {
        if (e instanceof XmlException) {
            return (XmlException)e;
        }
        return new XmlException(e.getMessage(), e);
    }

    private static final Constructor buildConstructor(String className, Class[] args) {
        try {
            return Class.forName(className, false, XmlBeans.class.getClassLoader()).getConstructor(args);
        } catch (Exception e) {
            throw XmlBeans.causedException(new IllegalStateException("Cannot load constructor for " + className + ": verify that xbean.jar is on the classpath"), e);
        }
    }

    private static final Method buildMethod(String className, String methodName, Class[] args) {
        try {
            return Class.forName(className, false, XmlBeans.class.getClassLoader()).getMethod(methodName, args);
        } catch (Exception e) {
            throw XmlBeans.causedException(new IllegalStateException("Cannot load " + methodName + ": verify that xbean.jar is on the classpath"), e);
        }
    }

    private static final Method buildNoArgMethod(String className, String methodName) {
        return XmlBeans.buildMethod(className, methodName, new Class[0]);
    }

    private static final Method buildNodeMethod(String className, String methodName) {
        return XmlBeans.buildMethod(className, methodName, new Class[]{Node.class});
    }

    private static Method buildGetContextTypeLoaderMethod() {
        return XmlBeans.buildNoArgMethod("org.apache.xmlbeans.impl.schema.SchemaTypeLoaderImpl", "getContextTypeLoader");
    }

    private static final Method buildGetBuiltinSchemaTypeSystemMethod() {
        return XmlBeans.buildNoArgMethod("org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem", "get");
    }

    private static final Method buildGetNoTypeMethod() {
        return XmlBeans.buildNoArgMethod("org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem", "getNoType");
    }

    private static final Method buildTypeLoaderBuilderMethod() {
        return XmlBeans.buildMethod("org.apache.xmlbeans.impl.schema.SchemaTypeLoaderImpl", "build", new Class[]{SchemaTypeLoader[].class, ResourceLoader.class, ClassLoader.class});
    }

    private static final Method buildCompilationMethod() {
        return XmlBeans.buildMethod("org.apache.xmlbeans.impl.schema.SchemaTypeSystemCompiler", "compile", new Class[]{String.class, SchemaTypeSystem.class, XmlObject[].class, BindingConfig.class, SchemaTypeLoader.class, Filer.class, XmlOptions.class});
    }

    private static final Method buildNodeToCursorMethod() {
        return XmlBeans.buildNodeMethod("org.apache.xmlbeans.impl.store.Locale", "nodeToCursor");
    }

    private static final Method buildNodeToXmlObjectMethod() {
        return XmlBeans.buildNodeMethod("org.apache.xmlbeans.impl.store.Locale", "nodeToXmlObject");
    }

    private static final Method buildNodeToXmlStreamMethod() {
        return XmlBeans.buildNodeMethod("org.apache.xmlbeans.impl.store.Locale", "nodeToXmlStream");
    }

    private static final Method buildStreamToNodeMethod() {
        return XmlBeans.buildMethod("org.apache.xmlbeans.impl.store.Locale", "streamToNode", new Class[]{XMLStreamReader.class});
    }

    private static final Constructor buildPathResourceLoaderConstructor() {
        return XmlBeans.buildConstructor("org.apache.xmlbeans.impl.schema.PathResourceLoader", new Class[]{File[].class});
    }

    public static String compilePath(String pathExpr) throws XmlException {
        return XmlBeans.compilePath(pathExpr, null);
    }

    public static String compilePath(String pathExpr, XmlOptions options) throws XmlException {
        return XmlBeans.getContextTypeLoader().compilePath(pathExpr, options);
    }

    public static String compileQuery(String queryExpr) throws XmlException {
        return XmlBeans.compileQuery(queryExpr, null);
    }

    public static String compileQuery(String queryExpr, XmlOptions options) throws XmlException {
        return XmlBeans.getContextTypeLoader().compileQuery(queryExpr, options);
    }

    public static SchemaTypeLoader getContextTypeLoader() {
        try {
            return (SchemaTypeLoader)_getContextTypeLoaderMethod.invoke(null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw XmlBeans.causedException(new IllegalStateException("No access to SchemaTypeLoaderImpl.getContextTypeLoader(): verify that version of xbean.jar is correct"), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            IllegalStateException ise = new IllegalStateException(t.getMessage());
            ise.initCause(t);
            throw ise;
        }
    }

    public static SchemaTypeSystem getBuiltinTypeSystem() {
        try {
            return (SchemaTypeSystem)_getBuiltinSchemaTypeSystemMethod.invoke(null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw XmlBeans.causedException(new IllegalStateException("No access to BuiltinSchemaTypeSystem.get(): verify that version of xbean.jar is correct"), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            IllegalStateException ise = new IllegalStateException(t.getMessage());
            ise.initCause(t);
            throw ise;
        }
    }

    public static XmlCursor nodeToCursor(Node n) {
        try {
            return (XmlCursor)_nodeToCursorMethod.invoke(null, n);
        } catch (IllegalAccessException e) {
            throw XmlBeans.causedException(new IllegalStateException("No access to nodeToCursor verify that version of xbean.jar is correct"), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            IllegalStateException ise = new IllegalStateException(t.getMessage());
            ise.initCause(t);
            throw ise;
        }
    }

    public static XmlObject nodeToXmlObject(Node n) {
        try {
            return (XmlObject)_nodeToXmlObjectMethod.invoke(null, n);
        } catch (IllegalAccessException e) {
            throw XmlBeans.causedException(new IllegalStateException("No access to nodeToXmlObject verify that version of xbean.jar is correct"), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            IllegalStateException ise = new IllegalStateException(t.getMessage());
            ise.initCause(t);
            throw ise;
        }
    }

    public static XMLStreamReader nodeToXmlStreamReader(Node n) {
        try {
            return (XMLStreamReader)_nodeToXmlStreamMethod.invoke(null, n);
        } catch (IllegalAccessException e) {
            throw XmlBeans.causedException(new IllegalStateException("No access to nodeToXmlStreamReader verify that version of xbean.jar is correct"), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            IllegalStateException ise = new IllegalStateException(t.getMessage());
            ise.initCause(t);
            throw ise;
        }
    }

    public static Node streamToNode(XMLStreamReader xs) {
        try {
            return (Node)_streamToNodeMethod.invoke(null, xs);
        } catch (IllegalAccessException e) {
            throw XmlBeans.causedException(new IllegalStateException("No access to streamToNode verify that version of xbean.jar is correct"), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            IllegalStateException ise = new IllegalStateException(t.getMessage());
            ise.initCause(t);
            throw ise;
        }
    }

    public static SchemaTypeLoader loadXsd(XmlObject[] schemas) throws XmlException {
        return XmlBeans.loadXsd(schemas, null);
    }

    public static SchemaTypeLoader loadXsd(XmlObject[] schemas, XmlOptions options) throws XmlException {
        try {
            SchemaTypeSystem sts = (SchemaTypeSystem)_compilationMethod.invoke(null, null, null, schemas, null, XmlBeans.getContextTypeLoader(), null, options);
            if (sts == null) {
                return null;
            }
            return XmlBeans.typeLoaderUnion(new SchemaTypeLoader[]{sts, XmlBeans.getContextTypeLoader()});
        } catch (IllegalAccessException e) {
            throw XmlBeans.causedException(new IllegalStateException("No access to SchemaTypeLoaderImpl.forSchemaXml(): verify that version of xbean.jar is correct"), e);
        } catch (InvocationTargetException e) {
            throw XmlBeans.wrappedException(e.getCause());
        }
    }

    public static SchemaTypeSystem compileXsd(XmlObject[] schemas, SchemaTypeLoader typepath, XmlOptions options) throws XmlException {
        return XmlBeans.compileXmlBeans(null, null, schemas, null, typepath, null, options);
    }

    public static SchemaTypeSystem compileXsd(SchemaTypeSystem system, XmlObject[] schemas, SchemaTypeLoader typepath, XmlOptions options) throws XmlException {
        return XmlBeans.compileXmlBeans(null, system, schemas, null, typepath, null, options);
    }

    public static SchemaTypeSystem compileXmlBeans(String name, SchemaTypeSystem system, XmlObject[] schemas, BindingConfig config, SchemaTypeLoader typepath, Filer filer, XmlOptions options) throws XmlException {
        try {
            return (SchemaTypeSystem)_compilationMethod.invoke(null, name, system, schemas, config, typepath != null ? typepath : XmlBeans.getContextTypeLoader(), filer, options);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("No access to SchemaTypeLoaderImpl.forSchemaXml(): verify that version of xbean.jar is correct");
        } catch (InvocationTargetException e) {
            throw XmlBeans.wrappedException(e.getCause());
        }
    }

    public static SchemaTypeLoader typeLoaderUnion(SchemaTypeLoader[] typeLoaders) {
        try {
            if (typeLoaders.length == 1) {
                return typeLoaders[0];
            }
            return (SchemaTypeLoader)_typeLoaderBuilderMethod.invoke(null, typeLoaders, null, null);
        } catch (IllegalAccessException e) {
            throw XmlBeans.causedException(new IllegalStateException("No access to SchemaTypeLoaderImpl: verify that version of xbean.jar is correct"), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            IllegalStateException ise = new IllegalStateException(t.getMessage());
            ise.initCause(t);
            throw ise;
        }
    }

    public static SchemaTypeLoader typeLoaderForClassLoader(ClassLoader loader) {
        try {
            return (SchemaTypeLoader)_typeLoaderBuilderMethod.invoke(null, null, null, loader);
        } catch (IllegalAccessException e) {
            throw XmlBeans.causedException(new IllegalStateException("No access to SchemaTypeLoaderImpl: verify that version of xbean.jar is correct"), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            IllegalStateException ise = new IllegalStateException(t.getMessage());
            ise.initCause(t);
            throw ise;
        }
    }

    public static SchemaTypeLoader typeLoaderForResource(ResourceLoader resourceLoader) {
        try {
            return (SchemaTypeLoader)_typeLoaderBuilderMethod.invoke(null, null, resourceLoader, null);
        } catch (IllegalAccessException e) {
            throw XmlBeans.causedException(new IllegalStateException("No access to SchemaTypeLoaderImpl: verify that version of xbean.jar is correct"), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            IllegalStateException ise = new IllegalStateException(t.getMessage());
            ise.initCause(t);
            throw ise;
        }
    }

    public static SchemaTypeSystem typeSystemForClassLoader(ClassLoader loader, String stsName) {
        try {
            ClassLoader cl = loader == null ? Thread.currentThread().getContextClassLoader() : loader;
            Class<?> clazz = cl.loadClass(stsName + "." + HOLDER_CLASS_NAME);
            SchemaTypeSystem sts = (SchemaTypeSystem)clazz.getDeclaredField(TYPE_SYSTEM_FIELD).get(null);
            if (sts == null) {
                throw new RuntimeException("SchemaTypeSystem is null for field typeSystem on class with name " + stsName + "." + HOLDER_CLASS_NAME + ". Please verify the version of xbean.jar is correct.");
            }
            return sts;
        } catch (ClassNotFoundException e) {
            throw XmlBeans.causedException(new RuntimeException("Cannot load SchemaTypeSystem. Unable to load class with name " + stsName + "." + HOLDER_CLASS_NAME + ". Make sure the generated binary files are on the classpath."), e);
        } catch (NoSuchFieldException e) {
            throw XmlBeans.causedException(new RuntimeException("Cannot find field typeSystem on class " + stsName + "." + HOLDER_CLASS_NAME + ". Please verify the version of xbean.jar is correct."), e);
        } catch (IllegalAccessException e) {
            throw XmlBeans.causedException(new RuntimeException("Field typeSystem on class " + stsName + "." + HOLDER_CLASS_NAME + "is not accessible. Please verify the version of xbean.jar is correct."), e);
        }
    }

    public static ResourceLoader resourceLoaderForPath(File[] path) {
        try {
            return (ResourceLoader)_pathResourceLoaderConstructor.newInstance(new Object[]{path});
        } catch (IllegalAccessException e) {
            throw XmlBeans.causedException(new IllegalStateException("No access to SchemaTypeLoaderImpl: verify that version of xbean.jar is correct"), e);
        } catch (InstantiationException e) {
            throw XmlBeans.causedException(new IllegalStateException(e.getMessage()), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            IllegalStateException ise = new IllegalStateException(t.getMessage());
            ise.initCause(t);
            throw ise;
        }
    }

    public static SchemaType typeForClass(Class c) {
        if (c == null || !XmlObject.class.isAssignableFrom(c)) {
            return null;
        }
        try {
            Field typeField = c.getField("type");
            if (typeField == null) {
                return null;
            }
            return (SchemaType)typeField.get(null);
        } catch (Exception e) {
            return null;
        }
    }

    private static SchemaType getNoType() {
        try {
            return (SchemaType)_getNoTypeMethod.invoke(null, new Object[0]);
        } catch (IllegalAccessException e) {
            throw XmlBeans.causedException(new IllegalStateException("No access to SchemaTypeLoaderImpl.getContextTypeLoader(): verify that version of xbean.jar is correct"), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            IllegalStateException ise = new IllegalStateException(t.getMessage());
            ise.initCause(t);
            throw ise;
        }
    }

    private XmlBeans() {
    }

    static {
        Package pkg = XmlBeans.class.getPackage();
        if (pkg != null && pkg.getImplementationVersion() != null) {
            XMLBEANS_TITLE = pkg.getImplementationTitle();
            XMLBEANS_VERSION = pkg.getImplementationVersion();
            XMLBEANS_VENDOR = pkg.getImplementationVendor();
        }
        _threadLocalLoaderQNameCache = new ThreadLocal(){

            protected Object initialValue() {
                return new SoftReference<QNameCache>(new QNameCache(32));
            }
        };
        _getContextTypeLoaderMethod = XmlBeans.buildGetContextTypeLoaderMethod();
        _getBuiltinSchemaTypeSystemMethod = XmlBeans.buildGetBuiltinSchemaTypeSystemMethod();
        _getNoTypeMethod = XmlBeans.buildGetNoTypeMethod();
        _typeLoaderBuilderMethod = XmlBeans.buildTypeLoaderBuilderMethod();
        _compilationMethod = XmlBeans.buildCompilationMethod();
        _nodeToCursorMethod = XmlBeans.buildNodeToCursorMethod();
        _nodeToXmlObjectMethod = XmlBeans.buildNodeToXmlObjectMethod();
        _nodeToXmlStreamMethod = XmlBeans.buildNodeToXmlStreamMethod();
        _streamToNodeMethod = XmlBeans.buildStreamToNodeMethod();
        _pathResourceLoaderConstructor = XmlBeans.buildPathResourceLoaderConstructor();
        NO_TYPE = XmlBeans.getNoType();
    }
}

