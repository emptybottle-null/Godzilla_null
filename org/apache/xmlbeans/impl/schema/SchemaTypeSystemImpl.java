/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.ResourceLoader;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaLocalElement;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaParticle;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaStringEnumEntry;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SchemaTypeLoaderException;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.SystemProperties;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.DefaultClassLoaderResourceLoader;
import org.apache.xmlbeans.impl.common.NameUtil;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.XBeanDebug;
import org.apache.xmlbeans.impl.regex.RegularExpression;
import org.apache.xmlbeans.impl.repackage.Repackager;
import org.apache.xmlbeans.impl.schema.BuiltinSchemaTypeSystem;
import org.apache.xmlbeans.impl.schema.ClassLoaderResourceLoader;
import org.apache.xmlbeans.impl.schema.SchemaAnnotationImpl;
import org.apache.xmlbeans.impl.schema.SchemaAttributeGroupImpl;
import org.apache.xmlbeans.impl.schema.SchemaAttributeModelImpl;
import org.apache.xmlbeans.impl.schema.SchemaContainer;
import org.apache.xmlbeans.impl.schema.SchemaDependencies;
import org.apache.xmlbeans.impl.schema.SchemaGlobalAttributeImpl;
import org.apache.xmlbeans.impl.schema.SchemaGlobalElementImpl;
import org.apache.xmlbeans.impl.schema.SchemaIdentityConstraintImpl;
import org.apache.xmlbeans.impl.schema.SchemaLocalAttributeImpl;
import org.apache.xmlbeans.impl.schema.SchemaLocalElementImpl;
import org.apache.xmlbeans.impl.schema.SchemaModelGroupImpl;
import org.apache.xmlbeans.impl.schema.SchemaParticleImpl;
import org.apache.xmlbeans.impl.schema.SchemaPropertyImpl;
import org.apache.xmlbeans.impl.schema.SchemaStringEnumEntryImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeCodePrinter;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeLoaderBase;
import org.apache.xmlbeans.impl.schema.SchemaTypeLoaderImpl;
import org.apache.xmlbeans.impl.schema.StscComplexTypeResolver;
import org.apache.xmlbeans.impl.schema.StscState;
import org.apache.xmlbeans.impl.schema.XQuerySchemaTypeSystem;
import org.apache.xmlbeans.impl.schema.XmlValueRef;
import org.apache.xmlbeans.impl.util.FilerImpl;
import org.apache.xmlbeans.impl.util.HexBin;
import org.apache.xmlbeans.impl.values.XmlObjectBase;
import org.apache.xmlbeans.impl.xb.xsdschema.AttributeGroupDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.GroupDocument;
import org.apache.xmlbeans.soap.SOAPArrayType;
import org.apache.xmlbeans.soap.SchemaWSDLArrayType;

public class SchemaTypeSystemImpl
extends SchemaTypeLoaderBase
implements SchemaTypeSystem {
    public static final int DATA_BABE = -629491010;
    public static final int MAJOR_VERSION = 2;
    public static final int MINOR_VERSION = 24;
    public static final int RELEASE_NUMBER = 0;
    public static final int FILETYPE_SCHEMAINDEX = 1;
    public static final int FILETYPE_SCHEMATYPE = 2;
    public static final int FILETYPE_SCHEMAELEMENT = 3;
    public static final int FILETYPE_SCHEMAATTRIBUTE = 4;
    public static final int FILETYPE_SCHEMAPOINTER = 5;
    public static final int FILETYPE_SCHEMAMODELGROUP = 6;
    public static final int FILETYPE_SCHEMAATTRIBUTEGROUP = 7;
    public static final int FILETYPE_SCHEMAIDENTITYCONSTRAINT = 8;
    public static final int FLAG_PART_SKIPPABLE = 1;
    public static final int FLAG_PART_FIXED = 4;
    public static final int FLAG_PART_NILLABLE = 8;
    public static final int FLAG_PART_BLOCKEXT = 16;
    public static final int FLAG_PART_BLOCKREST = 32;
    public static final int FLAG_PART_BLOCKSUBST = 64;
    public static final int FLAG_PART_ABSTRACT = 128;
    public static final int FLAG_PART_FINALEXT = 256;
    public static final int FLAG_PART_FINALREST = 512;
    public static final int FLAG_PROP_ISATTR = 1;
    public static final int FLAG_PROP_JAVASINGLETON = 2;
    public static final int FLAG_PROP_JAVAOPTIONAL = 4;
    public static final int FLAG_PROP_JAVAARRAY = 8;
    public static final int FIELD_NONE = 0;
    public static final int FIELD_GLOBAL = 1;
    public static final int FIELD_LOCALATTR = 2;
    public static final int FIELD_LOCALELT = 3;
    static final int FLAG_SIMPLE_TYPE = 1;
    static final int FLAG_DOCUMENT_TYPE = 2;
    static final int FLAG_ORDERED = 4;
    static final int FLAG_BOUNDED = 8;
    static final int FLAG_FINITE = 16;
    static final int FLAG_NUMERIC = 32;
    static final int FLAG_STRINGENUM = 64;
    static final int FLAG_UNION_OF_LISTS = 128;
    static final int FLAG_HAS_PATTERN = 256;
    static final int FLAG_ORDER_SENSITIVE = 512;
    static final int FLAG_TOTAL_ORDER = 1024;
    static final int FLAG_COMPILED = 2048;
    static final int FLAG_BLOCK_EXT = 4096;
    static final int FLAG_BLOCK_REST = 8192;
    static final int FLAG_FINAL_EXT = 16384;
    static final int FLAG_FINAL_REST = 32768;
    static final int FLAG_FINAL_UNION = 65536;
    static final int FLAG_FINAL_LIST = 131072;
    static final int FLAG_ABSTRACT = 262144;
    static final int FLAG_ATTRIBUTE_TYPE = 524288;
    public static String METADATA_PACKAGE_GEN;
    private static final String HOLDER_TEMPLATE_CLASS = "org.apache.xmlbeans.impl.schema.TypeSystemHolder";
    private static final String HOLDER_TEMPLATE_CLASSFILE = "TypeSystemHolder.template";
    private static final String[] HOLDER_TEMPLATE_NAMES;
    private static final int CONSTANT_UTF8 = 1;
    private static final int CONSTANT_UNICODE = 2;
    private static final int CONSTANT_INTEGER = 3;
    private static final int CONSTANT_FLOAT = 4;
    private static final int CONSTANT_LONG = 5;
    private static final int CONSTANT_DOUBLE = 6;
    private static final int CONSTANT_CLASS = 7;
    private static final int CONSTANT_STRING = 8;
    private static final int CONSTANT_FIELD = 9;
    private static final int CONSTANT_METHOD = 10;
    private static final int CONSTANT_INTERFACEMETHOD = 11;
    private static final int CONSTANT_NAMEANDTYPE = 12;
    private static final int MAX_UNSIGNED_SHORT = 65535;
    private static Random _random;
    private static byte[] _mask;
    private String _name;
    private String _basePackage;
    private boolean _incomplete = false;
    private ClassLoader _classloader;
    private ResourceLoader _resourceLoader;
    SchemaTypeLoader _linker;
    private HandlePool _localHandles;
    private Filer _filer;
    private List _annotations;
    private Map _containers = new HashMap();
    private SchemaDependencies _deps;
    private List _redefinedModelGroups;
    private List _redefinedAttributeGroups;
    private List _redefinedGlobalTypes;
    private Map _globalElements;
    private Map _globalAttributes;
    private Map _modelGroups;
    private Map _attributeGroups;
    private Map _globalTypes;
    private Map _documentTypes;
    private Map _attributeTypes;
    private Map _identityConstraints = Collections.EMPTY_MAP;
    private Map _typeRefsByClassname = new HashMap();
    private Set _namespaces;
    private static final SchemaType[] EMPTY_ST_ARRAY;
    private static final SchemaGlobalElement[] EMPTY_GE_ARRAY;
    private static final SchemaGlobalAttribute[] EMPTY_GA_ARRAY;
    private static final SchemaModelGroup[] EMPTY_MG_ARRAY;
    private static final SchemaAttributeGroup[] EMPTY_AG_ARRAY;
    private static final SchemaIdentityConstraint[] EMPTY_IC_ARRAY;
    private static final SchemaAnnotation[] EMPTY_ANN_ARRAY;
    static final byte[] SINGLE_ZERO_BYTE;
    private final Map _resolvedHandles = new HashMap();
    private boolean _allNonGroupHandlesResolved = false;

    private static String nameToPathString(String nameForSystem) {
        if (!(nameForSystem = nameForSystem.replace('.', '/')).endsWith("/") && nameForSystem.length() > 0) {
            nameForSystem = nameForSystem + "/";
        }
        return nameForSystem;
    }

    public SchemaTypeSystemImpl(Class indexclass) {
        String fullname = indexclass.getName();
        this._name = fullname.substring(0, fullname.lastIndexOf(46));
        XBeanDebug.trace(1, "Loading type system " + this._name, 1);
        this._basePackage = SchemaTypeSystemImpl.nameToPathString(this._name);
        this._classloader = indexclass.getClassLoader();
        this._linker = SchemaTypeLoaderImpl.build(null, null, this._classloader, this.getMetadataPath());
        this._resourceLoader = new ClassLoaderResourceLoader(this._classloader);
        try {
            this.initFromHeader();
        } catch (RuntimeException e) {
            XBeanDebug.logException(e);
            throw e;
        } catch (Error e) {
            XBeanDebug.logException(e);
            throw e;
        }
        XBeanDebug.trace(1, "Finished loading type system " + this._name, -1);
    }

    public static boolean fileContainsTypeSystem(File file, String name) {
        String indexname = SchemaTypeSystemImpl.nameToPathString(name) + "index.xsb";
        if (file.isDirectory()) {
            return new File(file, indexname).isFile();
        }
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(file);
            ZipEntry entry = zipfile.getEntry(indexname);
            boolean bl = entry != null && !entry.isDirectory();
            return bl;
        } catch (IOException e) {
            XBeanDebug.log("Problem loading SchemaTypeSystem, zipfilename " + file);
            XBeanDebug.logException(e);
            throw new SchemaTypeLoaderException(e.getMessage(), name, "index", 9);
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {}
            }
        }
    }

    public static SchemaTypeSystemImpl forName(String name, ClassLoader loader) {
        try {
            Class<?> c = Class.forName(name + "." + "TypeSystemHolder", true, loader);
            return (SchemaTypeSystemImpl)c.getField("typeSystem").get(null);
        } catch (Exception e) {
            return null;
        }
    }

    public SchemaTypeSystemImpl(ResourceLoader resourceLoader, String name, SchemaTypeLoader linker) {
        this._name = name;
        this._basePackage = SchemaTypeSystemImpl.nameToPathString(this._name);
        this._linker = linker;
        this._resourceLoader = resourceLoader;
        try {
            this.initFromHeader();
        } catch (RuntimeException e) {
            XBeanDebug.logException(e);
            throw e;
        } catch (Error e) {
            XBeanDebug.logException(e);
            throw e;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void initFromHeader() {
        XBeanDebug.trace(1, "Reading unresolved handles for type system " + this._name, 0);
        XsbReader reader = null;
        try {
            reader = new XsbReader("index", 1);
            this._localHandles = new HandlePool();
            reader.readHandlePool(this._localHandles);
            this._globalElements = reader.readQNameRefMap();
            this._globalAttributes = reader.readQNameRefMap();
            this._modelGroups = reader.readQNameRefMap();
            this._attributeGroups = reader.readQNameRefMap();
            this._identityConstraints = reader.readQNameRefMap();
            this._globalTypes = reader.readQNameRefMap();
            this._documentTypes = reader.readQNameRefMap();
            this._attributeTypes = reader.readQNameRefMap();
            this._typeRefsByClassname = reader.readClassnameRefMap();
            this._namespaces = reader.readNamespaces();
            ArrayList typeNames = new ArrayList();
            ArrayList modelGroupNames = new ArrayList();
            ArrayList attributeGroupNames = new ArrayList();
            if (reader.atLeast(2, 15, 0)) {
                this._redefinedGlobalTypes = reader.readQNameRefMapAsList(typeNames);
                this._redefinedModelGroups = reader.readQNameRefMapAsList(modelGroupNames);
                this._redefinedAttributeGroups = reader.readQNameRefMapAsList(attributeGroupNames);
            }
            if (reader.atLeast(2, 19, 0)) {
                this._annotations = reader.readAnnotations();
            }
            this.buildContainers(typeNames, modelGroupNames, attributeGroupNames);
        } finally {
            if (reader != null) {
                reader.readEnd();
            }
        }
    }

    void saveIndex() {
        String handle = "index";
        XsbReader saver = new XsbReader(handle);
        saver.writeIndexData();
        saver.writeRealHeader(handle, 1);
        saver.writeIndexData();
        saver.writeEnd();
    }

    void savePointers() {
        this.savePointersForComponents(this.globalElements(), this.getMetadataPath() + "/element/");
        this.savePointersForComponents(this.globalAttributes(), this.getMetadataPath() + "/attribute/");
        this.savePointersForComponents(this.modelGroups(), this.getMetadataPath() + "/modelgroup/");
        this.savePointersForComponents(this.attributeGroups(), this.getMetadataPath() + "/attributegroup/");
        this.savePointersForComponents(this.globalTypes(), this.getMetadataPath() + "/type/");
        this.savePointersForComponents(this.identityConstraints(), this.getMetadataPath() + "/identityconstraint/");
        this.savePointersForNamespaces(this._namespaces, this.getMetadataPath() + "/namespace/");
        this.savePointersForClassnames(this._typeRefsByClassname.keySet(), this.getMetadataPath() + "/javaname/");
        this.savePointersForComponents(this.redefinedModelGroups(), this.getMetadataPath() + "/redefinedmodelgroup/");
        this.savePointersForComponents(this.redefinedAttributeGroups(), this.getMetadataPath() + "/redefinedattributegroup/");
        this.savePointersForComponents(this.redefinedGlobalTypes(), this.getMetadataPath() + "/redefinedtype/");
    }

    void savePointersForComponents(SchemaComponent[] components, String dir) {
        for (int i = 0; i < components.length; ++i) {
            this.savePointerFile(dir + QNameHelper.hexsafedir(components[i].getName()), this._name);
        }
    }

    void savePointersForClassnames(Set classnames, String dir) {
        for (String classname : classnames) {
            this.savePointerFile(dir + classname.replace('.', '/'), this._name);
        }
    }

    void savePointersForNamespaces(Set namespaces, String dir) {
        for (String ns : namespaces) {
            this.savePointerFile(dir + QNameHelper.hexsafedir(new QName(ns, "xmlns")), this._name);
        }
    }

    void savePointerFile(String filename, String name) {
        XsbReader saver = new XsbReader(filename);
        saver.writeString(name);
        saver.writeRealHeader(filename, 5);
        saver.writeString(name);
        saver.writeEnd();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    void saveLoader() {
        String indexClassName = SchemaTypeCodePrinter.indexClassForSystem(this);
        String[] replace = SchemaTypeSystemImpl.makeClassStrings(indexClassName);
        assert (replace.length == HOLDER_TEMPLATE_NAMES.length);
        InputStream is = null;
        OutputStream os = null;
        DataInputStream in = null;
        DataOutputStream out = null;
        Repackager repackager = null;
        if (this._filer instanceof FilerImpl) {
            repackager = ((FilerImpl)this._filer).getRepackager();
        }
        try {
            is = SchemaTypeSystemImpl.class.getResourceAsStream(HOLDER_TEMPLATE_CLASSFILE);
            if (is == null) {
                DefaultClassLoaderResourceLoader clLoader = new DefaultClassLoaderResourceLoader();
                is = clLoader.getResourceAsStream(HOLDER_TEMPLATE_CLASSFILE);
            }
            if (is == null) {
                throw new SchemaTypeLoaderException("couldn't find resource: TypeSystemHolder.template", this._name, null, 9);
            }
            in = new DataInputStream(is);
            os = this._filer.createBinaryFile(indexClassName.replace('.', '/') + ".class");
            out = new DataOutputStream(os);
            out.writeInt(in.readInt());
            out.writeShort(in.readUnsignedShort());
            out.writeShort(in.readUnsignedShort());
            int poolsize = in.readUnsignedShort();
            out.writeShort(poolsize);
            block26: for (int i = 1; i < poolsize; ++i) {
                int tag = in.readUnsignedByte();
                out.writeByte(tag);
                switch (tag) {
                    case 1: {
                        String value = in.readUTF();
                        out.writeUTF(SchemaTypeSystemImpl.repackageConstant(value, replace, repackager));
                        continue block26;
                    }
                    case 7: 
                    case 8: {
                        out.writeShort(in.readUnsignedShort());
                        continue block26;
                    }
                    case 9: 
                    case 10: 
                    case 11: 
                    case 12: {
                        out.writeShort(in.readUnsignedShort());
                        out.writeShort(in.readUnsignedShort());
                        continue block26;
                    }
                    case 3: 
                    case 4: {
                        out.writeInt(in.readInt());
                        continue block26;
                    }
                    case 5: 
                    case 6: {
                        out.writeInt(in.readInt());
                        out.writeInt(in.readInt());
                        continue block26;
                    }
                    default: {
                        throw new RuntimeException("Unexpected constant type: " + tag);
                    }
                }
            }
            try {
                while (true) {
                    out.writeByte(in.readByte());
                }
            } catch (EOFException e) {
                if (is != null) {
                    try {
                        is.close();
                    } catch (Exception e2) {
                        // empty catch block
                    }
                }
                if (os != null) {
                    try {
                        os.close();
                    } catch (Exception e3) {}
                }
            }
        } catch (IOException e) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {}
            }
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {}
            }
        }
    }

    private static String repackageConstant(String value, String[] replace, Repackager repackager) {
        for (int i = 0; i < HOLDER_TEMPLATE_NAMES.length; ++i) {
            if (!HOLDER_TEMPLATE_NAMES[i].equals(value)) continue;
            return replace[i];
        }
        if (repackager != null) {
            return repackager.repackage(new StringBuffer(value)).toString();
        }
        return value;
    }

    private static String[] makeClassStrings(String classname) {
        String[] result;
        result = new String[]{classname, classname.replace('.', '/'), "L" + result[1] + ";", "class$" + classname.replace('.', '$')};
        return result;
    }

    private Map buildTypeRefsByClassname() {
        ArrayList<SchemaType> allSeenTypes = new ArrayList<SchemaType>();
        LinkedHashMap<String, SchemaType.Ref> result = new LinkedHashMap<String, SchemaType.Ref>();
        allSeenTypes.addAll(Arrays.asList(this.documentTypes()));
        allSeenTypes.addAll(Arrays.asList(this.attributeTypes()));
        allSeenTypes.addAll(Arrays.asList(this.globalTypes()));
        for (int i = 0; i < allSeenTypes.size(); ++i) {
            SchemaType gType = (SchemaType)allSeenTypes.get(i);
            String className = gType.getFullJavaName();
            if (className != null) {
                result.put(className.replace('$', '.'), gType.getRef());
            }
            allSeenTypes.addAll(Arrays.asList(gType.getAnonymousTypes()));
        }
        return result;
    }

    private Map buildTypeRefsByClassname(Map typesByClassname) {
        LinkedHashMap<String, SchemaType.Ref> result = new LinkedHashMap<String, SchemaType.Ref>();
        for (String className : typesByClassname.keySet()) {
            result.put(className, ((SchemaType)typesByClassname.get(className)).getRef());
        }
        return result;
    }

    private static Map buildComponentRefMap(SchemaComponent[] components) {
        LinkedHashMap<QName, SchemaComponent.Ref> result = new LinkedHashMap<QName, SchemaComponent.Ref>();
        for (int i = 0; i < components.length; ++i) {
            result.put(components[i].getName(), components[i].getComponentRef());
        }
        return result;
    }

    private static List buildComponentRefList(SchemaComponent[] components) {
        ArrayList<SchemaComponent.Ref> result = new ArrayList<SchemaComponent.Ref>();
        for (int i = 0; i < components.length; ++i) {
            result.add(components[i].getComponentRef());
        }
        return result;
    }

    private static Map buildDocumentMap(SchemaType[] types) {
        LinkedHashMap<QName, SchemaType.Ref> result = new LinkedHashMap<QName, SchemaType.Ref>();
        for (int i = 0; i < types.length; ++i) {
            result.put(types[i].getDocumentElementName(), types[i].getRef());
        }
        return result;
    }

    private static Map buildAttributeTypeMap(SchemaType[] types) {
        LinkedHashMap<QName, SchemaType.Ref> result = new LinkedHashMap<QName, SchemaType.Ref>();
        for (int i = 0; i < types.length; ++i) {
            result.put(types[i].getAttributeTypeAttributeName(), types[i].getRef());
        }
        return result;
    }

    private SchemaContainer getContainer(String namespace) {
        return (SchemaContainer)this._containers.get(namespace);
    }

    private void addContainer(String namespace) {
        SchemaContainer c = new SchemaContainer(namespace);
        c.setTypeSystem(this);
        this._containers.put(namespace, c);
    }

    private SchemaContainer getContainerNonNull(String namespace) {
        SchemaContainer result = this.getContainer(namespace);
        if (result == null) {
            this.addContainer(namespace);
            result = this.getContainer(namespace);
        }
        return result;
    }

    private void buildContainers(List redefTypeNames, List redefModelGroupNames, List redefAttributeGroupNames) {
        Iterator it;
        String ns;
        for (Map.Entry entry : this._globalElements.entrySet()) {
            ns = ((QName)entry.getKey()).getNamespaceURI();
            this.getContainerNonNull(ns).addGlobalElement((SchemaGlobalElement.Ref)entry.getValue());
        }
        for (Map.Entry entry : this._globalAttributes.entrySet()) {
            ns = ((QName)entry.getKey()).getNamespaceURI();
            this.getContainerNonNull(ns).addGlobalAttribute((SchemaGlobalAttribute.Ref)entry.getValue());
        }
        for (Map.Entry entry : this._modelGroups.entrySet()) {
            ns = ((QName)entry.getKey()).getNamespaceURI();
            this.getContainerNonNull(ns).addModelGroup((SchemaModelGroup.Ref)entry.getValue());
        }
        for (Map.Entry entry : this._attributeGroups.entrySet()) {
            ns = ((QName)entry.getKey()).getNamespaceURI();
            this.getContainerNonNull(ns).addAttributeGroup((SchemaAttributeGroup.Ref)entry.getValue());
        }
        for (Map.Entry entry : this._identityConstraints.entrySet()) {
            ns = ((QName)entry.getKey()).getNamespaceURI();
            this.getContainerNonNull(ns).addIdentityConstraint((SchemaIdentityConstraint.Ref)entry.getValue());
        }
        for (Map.Entry entry : this._globalTypes.entrySet()) {
            ns = ((QName)entry.getKey()).getNamespaceURI();
            this.getContainerNonNull(ns).addGlobalType((SchemaType.Ref)entry.getValue());
        }
        for (Map.Entry entry : this._documentTypes.entrySet()) {
            ns = ((QName)entry.getKey()).getNamespaceURI();
            this.getContainerNonNull(ns).addDocumentType((SchemaType.Ref)entry.getValue());
        }
        for (Map.Entry entry : this._attributeTypes.entrySet()) {
            ns = ((QName)entry.getKey()).getNamespaceURI();
            this.getContainerNonNull(ns).addAttributeType((SchemaType.Ref)entry.getValue());
        }
        if (this._redefinedGlobalTypes != null && this._redefinedModelGroups != null && this._redefinedAttributeGroups != null) {
            assert (this._redefinedGlobalTypes.size() == redefTypeNames.size());
            it = this._redefinedGlobalTypes.iterator();
            Iterator itname = redefTypeNames.iterator();
            while (it.hasNext()) {
                ns = ((QName)itname.next()).getNamespaceURI();
                this.getContainerNonNull(ns).addRedefinedType((SchemaType.Ref)((Object)it.next()));
            }
            it = this._redefinedModelGroups.iterator();
            itname = redefModelGroupNames.iterator();
            while (it.hasNext()) {
                ns = ((QName)itname.next()).getNamespaceURI();
                this.getContainerNonNull(ns).addRedefinedModelGroup((SchemaModelGroup.Ref)((Object)it.next()));
            }
            it = this._redefinedAttributeGroups.iterator();
            itname = redefAttributeGroupNames.iterator();
            while (it.hasNext()) {
                ns = ((QName)itname.next()).getNamespaceURI();
                this.getContainerNonNull(ns).addRedefinedAttributeGroup((SchemaAttributeGroup.Ref)((Object)it.next()));
            }
        }
        if (this._annotations != null) {
            for (SchemaAnnotation ann : this._annotations) {
                this.getContainerNonNull("").addAnnotation(ann);
            }
        }
        it = this._containers.values().iterator();
        while (it.hasNext()) {
            ((SchemaContainer)((Object)it.next())).setImmutable();
        }
    }

    private void fixupContainers() {
        for (SchemaContainer container : this._containers.values()) {
            container.setTypeSystem(this);
            container.setImmutable();
        }
    }

    private void assertContainersSynchronized() {
        boolean assertEnabled = false;
        if (!$assertionsDisabled) {
            assertEnabled = true;
            if (!true) {
                throw new AssertionError();
            }
        }
        if (!assertEnabled) {
            return;
        }
        HashMap temp = new HashMap();
        Iterator it = this._containers.values().iterator();
        while (it.hasNext()) {
            temp.putAll(SchemaTypeSystemImpl.buildComponentRefMap(((SchemaContainer)it.next()).globalElements().toArray(new SchemaComponent[0])));
        }
        assert (((Object)this._globalElements).equals(temp));
        temp = new HashMap();
        it = this._containers.values().iterator();
        while (it.hasNext()) {
            temp.putAll(SchemaTypeSystemImpl.buildComponentRefMap(((SchemaContainer)it.next()).globalAttributes().toArray(new SchemaComponent[0])));
        }
        assert (((Object)this._globalAttributes).equals(temp));
        temp = new HashMap();
        it = this._containers.values().iterator();
        while (it.hasNext()) {
            temp.putAll(SchemaTypeSystemImpl.buildComponentRefMap(((SchemaContainer)it.next()).modelGroups().toArray(new SchemaComponent[0])));
        }
        assert (((Object)this._modelGroups).equals(temp));
        HashSet<String> temp2 = new HashSet<String>();
        Iterator it2 = this._containers.values().iterator();
        while (it2.hasNext()) {
            temp2.addAll(SchemaTypeSystemImpl.buildComponentRefList(((SchemaContainer)it2.next()).redefinedModelGroups().toArray(new SchemaComponent[0])));
        }
        assert (new HashSet(this._redefinedModelGroups).equals(temp2));
        temp = new HashMap();
        it2 = this._containers.values().iterator();
        while (it2.hasNext()) {
            temp.putAll(SchemaTypeSystemImpl.buildComponentRefMap(((SchemaContainer)it2.next()).attributeGroups().toArray(new SchemaComponent[0])));
        }
        assert (((Object)this._attributeGroups).equals(temp));
        temp2 = new HashSet();
        it2 = this._containers.values().iterator();
        while (it2.hasNext()) {
            temp2.addAll(SchemaTypeSystemImpl.buildComponentRefList(((SchemaContainer)it2.next()).redefinedAttributeGroups().toArray(new SchemaComponent[0])));
        }
        assert (new HashSet(this._redefinedAttributeGroups).equals(temp2));
        temp = new HashMap();
        it2 = this._containers.values().iterator();
        while (it2.hasNext()) {
            temp.putAll(SchemaTypeSystemImpl.buildComponentRefMap(((SchemaContainer)it2.next()).globalTypes().toArray(new SchemaComponent[0])));
        }
        assert (((Object)this._globalTypes).equals(temp));
        temp2 = new HashSet();
        it2 = this._containers.values().iterator();
        while (it2.hasNext()) {
            temp2.addAll(SchemaTypeSystemImpl.buildComponentRefList(((SchemaContainer)it2.next()).redefinedGlobalTypes().toArray(new SchemaComponent[0])));
        }
        assert (new HashSet(this._redefinedGlobalTypes).equals(temp2));
        temp = new HashMap();
        it2 = this._containers.values().iterator();
        while (it2.hasNext()) {
            temp.putAll(SchemaTypeSystemImpl.buildDocumentMap(((SchemaContainer)it2.next()).documentTypes().toArray(new SchemaType[0])));
        }
        assert (((Object)this._documentTypes).equals(temp));
        temp = new HashMap();
        it2 = this._containers.values().iterator();
        while (it2.hasNext()) {
            temp.putAll(SchemaTypeSystemImpl.buildAttributeTypeMap(((SchemaContainer)it2.next()).attributeTypes().toArray(new SchemaType[0])));
        }
        assert (((Object)this._attributeTypes).equals(temp));
        temp = new HashMap();
        it2 = this._containers.values().iterator();
        while (it2.hasNext()) {
            temp.putAll(SchemaTypeSystemImpl.buildComponentRefMap(((SchemaContainer)it2.next()).identityConstraints().toArray(new SchemaComponent[0])));
        }
        assert (((Object)this._identityConstraints).equals(temp));
        temp2 = new HashSet();
        it2 = this._containers.values().iterator();
        while (it2.hasNext()) {
            temp2.addAll(((SchemaContainer)it2.next()).annotations());
        }
        assert (new HashSet(this._annotations).equals(temp2));
        temp2 = new HashSet();
        it2 = this._containers.values().iterator();
        while (it2.hasNext()) {
            temp2.add(((SchemaContainer)it2.next()).getNamespace());
        }
        assert (((Object)this._namespaces).equals(temp2));
    }

    private static synchronized void nextBytes(byte[] result) {
        if (_random == null) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                DataOutputStream daos = new DataOutputStream(baos);
                daos.writeInt(System.identityHashCode(SchemaTypeSystemImpl.class));
                String[] props = new String[]{"user.name", "user.dir", "user.timezone", "user.country", "java.class.path", "java.home", "java.vendor", "java.version", "os.version"};
                for (int i = 0; i < props.length; ++i) {
                    String prop = SystemProperties.getProperty(props[i]);
                    if (prop == null) continue;
                    daos.writeUTF(prop);
                    daos.writeInt(System.identityHashCode(prop));
                }
                daos.writeLong(Runtime.getRuntime().freeMemory());
                daos.close();
                byte[] bytes = baos.toByteArray();
                for (int i = 0; i < bytes.length; ++i) {
                    int j;
                    int n = j = i % _mask.length;
                    _mask[n] = (byte)(_mask[n] * 21);
                    int n2 = j;
                    _mask[n2] = (byte)(_mask[n2] + i);
                }
            } catch (IOException e) {
                XBeanDebug.logException(e);
            }
            _random = new Random(System.currentTimeMillis());
        }
        _random.nextBytes(result);
        int i = 0;
        while (i < result.length) {
            int j = i & _mask.length;
            int n = i++;
            result[n] = (byte)(result[n] ^ _mask[j]);
        }
    }

    public SchemaTypeSystemImpl(String nameForSystem) {
        if (nameForSystem == null) {
            byte[] bytes = new byte[16];
            SchemaTypeSystemImpl.nextBytes(bytes);
            nameForSystem = "s" + new String(HexBin.encode(bytes));
        }
        this._name = this.getMetadataPath().replace('/', '.') + ".system." + nameForSystem;
        this._basePackage = SchemaTypeSystemImpl.nameToPathString(this._name);
        this._classloader = null;
    }

    public void loadFromBuilder(SchemaGlobalElement[] globalElements, SchemaGlobalAttribute[] globalAttributes, SchemaType[] globalTypes, SchemaType[] documentTypes, SchemaType[] attributeTypes) {
        assert (this._classloader == null);
        this._localHandles = new HandlePool();
        this._globalElements = SchemaTypeSystemImpl.buildComponentRefMap(globalElements);
        this._globalAttributes = SchemaTypeSystemImpl.buildComponentRefMap(globalAttributes);
        this._globalTypes = SchemaTypeSystemImpl.buildComponentRefMap(globalTypes);
        this._documentTypes = SchemaTypeSystemImpl.buildDocumentMap(documentTypes);
        this._attributeTypes = SchemaTypeSystemImpl.buildAttributeTypeMap(attributeTypes);
        this._typeRefsByClassname = this.buildTypeRefsByClassname();
        this.buildContainers(Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
        this._namespaces = new HashSet();
    }

    public void loadFromStscState(StscState state) {
        assert (this._classloader == null);
        this._localHandles = new HandlePool();
        this._globalElements = SchemaTypeSystemImpl.buildComponentRefMap(state.globalElements());
        this._globalAttributes = SchemaTypeSystemImpl.buildComponentRefMap(state.globalAttributes());
        this._modelGroups = SchemaTypeSystemImpl.buildComponentRefMap(state.modelGroups());
        this._redefinedModelGroups = SchemaTypeSystemImpl.buildComponentRefList(state.redefinedModelGroups());
        this._attributeGroups = SchemaTypeSystemImpl.buildComponentRefMap(state.attributeGroups());
        this._redefinedAttributeGroups = SchemaTypeSystemImpl.buildComponentRefList(state.redefinedAttributeGroups());
        this._globalTypes = SchemaTypeSystemImpl.buildComponentRefMap(state.globalTypes());
        this._redefinedGlobalTypes = SchemaTypeSystemImpl.buildComponentRefList(state.redefinedGlobalTypes());
        this._documentTypes = SchemaTypeSystemImpl.buildDocumentMap(state.documentTypes());
        this._attributeTypes = SchemaTypeSystemImpl.buildAttributeTypeMap(state.attributeTypes());
        this._typeRefsByClassname = this.buildTypeRefsByClassname(state.typesByClassname());
        this._identityConstraints = SchemaTypeSystemImpl.buildComponentRefMap(state.idConstraints());
        this._annotations = state.annotations();
        this._namespaces = new HashSet<String>(Arrays.asList(state.getNamespaces()));
        this._containers = state.getContainerMap();
        this.fixupContainers();
        this.assertContainersSynchronized();
        this.setDependencies(state.getDependencies());
    }

    final SchemaTypeSystemImpl getTypeSystem() {
        return this;
    }

    void setDependencies(SchemaDependencies deps) {
        this._deps = deps;
    }

    SchemaDependencies getDependencies() {
        return this._deps;
    }

    public boolean isIncomplete() {
        return this._incomplete;
    }

    void setIncomplete(boolean incomplete) {
        this._incomplete = incomplete;
    }

    @Override
    public void saveToDirectory(File classDir) {
        this.save(new FilerImpl(classDir, null, null, false, false));
    }

    @Override
    public void save(Filer filer) {
        if (this._incomplete) {
            throw new IllegalStateException("Incomplete SchemaTypeSystems cannot be saved.");
        }
        if (filer == null) {
            throw new IllegalArgumentException("filer must not be null");
        }
        this._filer = filer;
        this._localHandles.startWriteMode();
        this.saveTypesRecursively(this.globalTypes());
        this.saveTypesRecursively(this.documentTypes());
        this.saveTypesRecursively(this.attributeTypes());
        this.saveGlobalElements(this.globalElements());
        this.saveGlobalAttributes(this.globalAttributes());
        this.saveModelGroups(this.modelGroups());
        this.saveAttributeGroups(this.attributeGroups());
        this.saveIdentityConstraints(this.identityConstraints());
        this.saveTypesRecursively(this.redefinedGlobalTypes());
        this.saveModelGroups(this.redefinedModelGroups());
        this.saveAttributeGroups(this.redefinedAttributeGroups());
        this.saveIndex();
        this.savePointers();
        this.saveLoader();
    }

    void saveTypesRecursively(SchemaType[] types) {
        for (int i = 0; i < types.length; ++i) {
            if (types[i].getTypeSystem() != this.getTypeSystem()) continue;
            this.saveType(types[i]);
            this.saveTypesRecursively(types[i].getAnonymousTypes());
        }
    }

    public void saveGlobalElements(SchemaGlobalElement[] elts) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        for (int i = 0; i < elts.length; ++i) {
            this.saveGlobalElement(elts[i]);
        }
    }

    public void saveGlobalAttributes(SchemaGlobalAttribute[] attrs) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        for (int i = 0; i < attrs.length; ++i) {
            this.saveGlobalAttribute(attrs[i]);
        }
    }

    public void saveModelGroups(SchemaModelGroup[] groups) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        for (int i = 0; i < groups.length; ++i) {
            this.saveModelGroup(groups[i]);
        }
    }

    public void saveAttributeGroups(SchemaAttributeGroup[] groups) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        for (int i = 0; i < groups.length; ++i) {
            this.saveAttributeGroup(groups[i]);
        }
    }

    public void saveIdentityConstraints(SchemaIdentityConstraint[] idcs) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        for (int i = 0; i < idcs.length; ++i) {
            this.saveIdentityConstraint(idcs[i]);
        }
    }

    public void saveGlobalElement(SchemaGlobalElement elt) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        String handle = this._localHandles.handleForElement(elt);
        XsbReader saver = new XsbReader(handle);
        saver.writeParticleData((SchemaParticle)((Object)elt));
        saver.writeString(elt.getSourceName());
        saver.writeRealHeader(handle, 3);
        saver.writeParticleData((SchemaParticle)((Object)elt));
        saver.writeString(elt.getSourceName());
        saver.writeEnd();
    }

    public void saveGlobalAttribute(SchemaGlobalAttribute attr) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        String handle = this._localHandles.handleForAttribute(attr);
        XsbReader saver = new XsbReader(handle);
        saver.writeAttributeData(attr);
        saver.writeString(attr.getSourceName());
        saver.writeRealHeader(handle, 4);
        saver.writeAttributeData(attr);
        saver.writeString(attr.getSourceName());
        saver.writeEnd();
    }

    public void saveModelGroup(SchemaModelGroup grp) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        String handle = this._localHandles.handleForModelGroup(grp);
        XsbReader saver = new XsbReader(handle);
        saver.writeModelGroupData(grp);
        saver.writeRealHeader(handle, 6);
        saver.writeModelGroupData(grp);
        saver.writeEnd();
    }

    public void saveAttributeGroup(SchemaAttributeGroup grp) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        String handle = this._localHandles.handleForAttributeGroup(grp);
        XsbReader saver = new XsbReader(handle);
        saver.writeAttributeGroupData(grp);
        saver.writeRealHeader(handle, 7);
        saver.writeAttributeGroupData(grp);
        saver.writeEnd();
    }

    public void saveIdentityConstraint(SchemaIdentityConstraint idc) {
        if (this._incomplete) {
            throw new IllegalStateException("This SchemaTypeSystem cannot be saved.");
        }
        String handle = this._localHandles.handleForIdentityConstraint(idc);
        XsbReader saver = new XsbReader(handle);
        saver.writeIdConstraintData(idc);
        saver.writeRealHeader(handle, 8);
        saver.writeIdConstraintData(idc);
        saver.writeEnd();
    }

    void saveType(SchemaType type) {
        String handle = this._localHandles.handleForType(type);
        XsbReader saver = new XsbReader(handle);
        saver.writeTypeData(type);
        saver.writeRealHeader(handle, 2);
        saver.writeTypeData(type);
        saver.writeEnd();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String crackPointer(InputStream stream) {
        DataInputStream input = null;
        try {
            short actualfiletype;
            input = new DataInputStream(stream);
            int magic = input.readInt();
            if (magic != -629491010) {
                String string = null;
                return string;
            }
            short majorver = input.readShort();
            short minorver = input.readShort();
            if (majorver != 2) {
                String string = null;
                return string;
            }
            if (minorver > 24) {
                String string = null;
                return string;
            }
            if (majorver > 2 || majorver == 2 && minorver >= 18) {
                input.readShort();
            }
            if ((actualfiletype = input.readShort()) != 5) {
                String e = null;
                return e;
            }
            StringPool stringPool = new StringPool("pointer", "unk");
            stringPool.readFrom(input);
            String string = stringPool.stringForCode(input.readShort());
            return string;
        } catch (IOException e) {
            String string = null;
            return string;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {}
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SchemaType typeForHandle(String handle) {
        Map map = this._resolvedHandles;
        synchronized (map) {
            return (SchemaType)this._resolvedHandles.get(handle);
        }
    }

    @Override
    public SchemaType typeForClassname(String classname) {
        SchemaType.Ref ref = (SchemaType.Ref)this._typeRefsByClassname.get(classname);
        return ref != null ? ref.get() : null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SchemaComponent resolveHandle(String handle) {
        SchemaComponent result;
        Map map = this._resolvedHandles;
        synchronized (map) {
            result = (SchemaComponent)this._resolvedHandles.get(handle);
        }
        if (result == null) {
            XsbReader reader = new XsbReader(handle, 65535);
            int filetype = reader.getActualFiletype();
            switch (filetype) {
                case 2: {
                    XBeanDebug.trace(1, "Resolving type for handle " + handle, 0);
                    result = reader.finishLoadingType();
                    break;
                }
                case 3: {
                    XBeanDebug.trace(1, "Resolving element for handle " + handle, 0);
                    result = reader.finishLoadingElement();
                    break;
                }
                case 4: {
                    XBeanDebug.trace(1, "Resolving attribute for handle " + handle, 0);
                    result = reader.finishLoadingAttribute();
                    break;
                }
                case 6: {
                    XBeanDebug.trace(1, "Resolving model group for handle " + handle, 0);
                    result = reader.finishLoadingModelGroup();
                    break;
                }
                case 7: {
                    XBeanDebug.trace(1, "Resolving attribute group for handle " + handle, 0);
                    result = reader.finishLoadingAttributeGroup();
                    break;
                }
                case 8: {
                    XBeanDebug.trace(1, "Resolving id constraint for handle " + handle, 0);
                    result = reader.finishLoadingIdentityConstraint();
                    break;
                }
                default: {
                    throw new IllegalStateException("Illegal handle type");
                }
            }
            Map map2 = this._resolvedHandles;
            synchronized (map2) {
                if (!this._resolvedHandles.containsKey(handle)) {
                    this._resolvedHandles.put(handle, result);
                } else {
                    result = (SchemaComponent)this._resolvedHandles.get(handle);
                }
            }
        }
        return result;
    }

    @Override
    public void resolve() {
        XBeanDebug.trace(1, "Resolve called type system " + this._name, 0);
        if (this._allNonGroupHandlesResolved) {
            return;
        }
        XBeanDebug.trace(1, "Resolving all handles for type system " + this._name, 1);
        ArrayList refs = new ArrayList();
        refs.addAll(this._globalElements.values());
        refs.addAll(this._globalAttributes.values());
        refs.addAll(this._globalTypes.values());
        refs.addAll(this._documentTypes.values());
        refs.addAll(this._attributeTypes.values());
        refs.addAll(this._identityConstraints.values());
        for (SchemaComponent.Ref ref : refs) {
            ref.getComponent();
        }
        XBeanDebug.trace(1, "Finished resolving type system " + this._name, -1);
        this._allNonGroupHandlesResolved = true;
    }

    @Override
    public boolean isNamespaceDefined(String namespace) {
        return this._namespaces.contains(namespace);
    }

    @Override
    public SchemaType.Ref findTypeRef(QName name) {
        return (SchemaType.Ref)this._globalTypes.get(name);
    }

    @Override
    public SchemaType.Ref findDocumentTypeRef(QName name) {
        return (SchemaType.Ref)this._documentTypes.get(name);
    }

    @Override
    public SchemaType.Ref findAttributeTypeRef(QName name) {
        return (SchemaType.Ref)this._attributeTypes.get(name);
    }

    @Override
    public SchemaGlobalElement.Ref findElementRef(QName name) {
        return (SchemaGlobalElement.Ref)this._globalElements.get(name);
    }

    @Override
    public SchemaGlobalAttribute.Ref findAttributeRef(QName name) {
        return (SchemaGlobalAttribute.Ref)this._globalAttributes.get(name);
    }

    @Override
    public SchemaModelGroup.Ref findModelGroupRef(QName name) {
        return (SchemaModelGroup.Ref)this._modelGroups.get(name);
    }

    @Override
    public SchemaAttributeGroup.Ref findAttributeGroupRef(QName name) {
        return (SchemaAttributeGroup.Ref)this._attributeGroups.get(name);
    }

    @Override
    public SchemaIdentityConstraint.Ref findIdentityConstraintRef(QName name) {
        return (SchemaIdentityConstraint.Ref)this._identityConstraints.get(name);
    }

    @Override
    public SchemaType[] globalTypes() {
        if (this._globalTypes.isEmpty()) {
            return EMPTY_ST_ARRAY;
        }
        SchemaType[] result = new SchemaType[this._globalTypes.size()];
        int j = 0;
        Iterator i = this._globalTypes.values().iterator();
        while (i.hasNext()) {
            result[j] = ((SchemaType.Ref)i.next()).get();
            ++j;
        }
        return result;
    }

    public SchemaType[] redefinedGlobalTypes() {
        if (this._redefinedGlobalTypes == null || this._redefinedGlobalTypes.isEmpty()) {
            return EMPTY_ST_ARRAY;
        }
        SchemaType[] result = new SchemaType[this._redefinedGlobalTypes.size()];
        int j = 0;
        Iterator i = this._redefinedGlobalTypes.iterator();
        while (i.hasNext()) {
            result[j] = ((SchemaType.Ref)i.next()).get();
            ++j;
        }
        return result;
    }

    @Override
    public InputStream getSourceAsStream(String sourceName) {
        if (!sourceName.startsWith("/")) {
            sourceName = "/" + sourceName;
        }
        return this._resourceLoader.getResourceAsStream(this.getMetadataPath() + "/src" + sourceName);
    }

    SchemaContainer[] containers() {
        SchemaContainer[] result = new SchemaContainer[this._containers.size()];
        int j = 0;
        Iterator i = this._containers.values().iterator();
        while (i.hasNext()) {
            result[j] = (SchemaContainer)i.next();
            ++j;
        }
        return result;
    }

    @Override
    public SchemaType[] documentTypes() {
        if (this._documentTypes.isEmpty()) {
            return EMPTY_ST_ARRAY;
        }
        SchemaType[] result = new SchemaType[this._documentTypes.size()];
        int j = 0;
        Iterator i = this._documentTypes.values().iterator();
        while (i.hasNext()) {
            result[j] = ((SchemaType.Ref)i.next()).get();
            ++j;
        }
        return result;
    }

    @Override
    public SchemaType[] attributeTypes() {
        if (this._attributeTypes.isEmpty()) {
            return EMPTY_ST_ARRAY;
        }
        SchemaType[] result = new SchemaType[this._attributeTypes.size()];
        int j = 0;
        Iterator i = this._attributeTypes.values().iterator();
        while (i.hasNext()) {
            result[j] = ((SchemaType.Ref)i.next()).get();
            ++j;
        }
        return result;
    }

    @Override
    public SchemaGlobalElement[] globalElements() {
        if (this._globalElements.isEmpty()) {
            return EMPTY_GE_ARRAY;
        }
        SchemaGlobalElement[] result = new SchemaGlobalElement[this._globalElements.size()];
        int j = 0;
        Iterator i = this._globalElements.values().iterator();
        while (i.hasNext()) {
            result[j] = ((SchemaGlobalElement.Ref)i.next()).get();
            ++j;
        }
        return result;
    }

    @Override
    public SchemaGlobalAttribute[] globalAttributes() {
        if (this._globalAttributes.isEmpty()) {
            return EMPTY_GA_ARRAY;
        }
        SchemaGlobalAttribute[] result = new SchemaGlobalAttribute[this._globalAttributes.size()];
        int j = 0;
        Iterator i = this._globalAttributes.values().iterator();
        while (i.hasNext()) {
            result[j] = ((SchemaGlobalAttribute.Ref)i.next()).get();
            ++j;
        }
        return result;
    }

    @Override
    public SchemaModelGroup[] modelGroups() {
        if (this._modelGroups.isEmpty()) {
            return EMPTY_MG_ARRAY;
        }
        SchemaModelGroup[] result = new SchemaModelGroup[this._modelGroups.size()];
        int j = 0;
        Iterator i = this._modelGroups.values().iterator();
        while (i.hasNext()) {
            result[j] = ((SchemaModelGroup.Ref)i.next()).get();
            ++j;
        }
        return result;
    }

    public SchemaModelGroup[] redefinedModelGroups() {
        if (this._redefinedModelGroups == null || this._redefinedModelGroups.isEmpty()) {
            return EMPTY_MG_ARRAY;
        }
        SchemaModelGroup[] result = new SchemaModelGroup[this._redefinedModelGroups.size()];
        int j = 0;
        Iterator i = this._redefinedModelGroups.iterator();
        while (i.hasNext()) {
            result[j] = ((SchemaModelGroup.Ref)i.next()).get();
            ++j;
        }
        return result;
    }

    @Override
    public SchemaAttributeGroup[] attributeGroups() {
        if (this._attributeGroups.isEmpty()) {
            return EMPTY_AG_ARRAY;
        }
        SchemaAttributeGroup[] result = new SchemaAttributeGroup[this._attributeGroups.size()];
        int j = 0;
        Iterator i = this._attributeGroups.values().iterator();
        while (i.hasNext()) {
            result[j] = ((SchemaAttributeGroup.Ref)i.next()).get();
            ++j;
        }
        return result;
    }

    public SchemaAttributeGroup[] redefinedAttributeGroups() {
        if (this._redefinedAttributeGroups == null || this._redefinedAttributeGroups.isEmpty()) {
            return EMPTY_AG_ARRAY;
        }
        SchemaAttributeGroup[] result = new SchemaAttributeGroup[this._redefinedAttributeGroups.size()];
        int j = 0;
        Iterator i = this._redefinedAttributeGroups.iterator();
        while (i.hasNext()) {
            result[j] = ((SchemaAttributeGroup.Ref)i.next()).get();
            ++j;
        }
        return result;
    }

    @Override
    public SchemaAnnotation[] annotations() {
        if (this._annotations == null || this._annotations.isEmpty()) {
            return EMPTY_ANN_ARRAY;
        }
        SchemaAnnotation[] result = new SchemaAnnotation[this._annotations.size()];
        result = this._annotations.toArray(result);
        return result;
    }

    public SchemaIdentityConstraint[] identityConstraints() {
        if (this._identityConstraints.isEmpty()) {
            return EMPTY_IC_ARRAY;
        }
        SchemaIdentityConstraint[] result = new SchemaIdentityConstraint[this._identityConstraints.size()];
        int j = 0;
        Iterator i = this._identityConstraints.values().iterator();
        while (i.hasNext()) {
            result[j] = ((SchemaIdentityConstraint.Ref)i.next()).get();
            ++j;
        }
        return result;
    }

    @Override
    public ClassLoader getClassLoader() {
        return this._classloader;
    }

    public String handleForType(SchemaType type) {
        return this._localHandles.handleForType(type);
    }

    @Override
    public String getName() {
        return this._name;
    }

    public SchemaTypeSystem typeSystemForName(String name) {
        if (this._name != null && name.equals(this._name)) {
            return this;
        }
        return null;
    }

    protected String getMetadataPath() {
        return "schema" + METADATA_PACKAGE_GEN;
    }

    static {
        Package stsPackage = SchemaTypeSystem.class.getPackage();
        String stsPackageName = stsPackage == null ? SchemaTypeSystem.class.getName().substring(0, SchemaTypeSystem.class.getName().lastIndexOf(".")) : stsPackage.getName();
        METADATA_PACKAGE_GEN = stsPackageName.replaceAll("\\.", "_");
        HOLDER_TEMPLATE_NAMES = SchemaTypeSystemImpl.makeClassStrings(HOLDER_TEMPLATE_CLASS);
        _mask = new byte[16];
        EMPTY_ST_ARRAY = new SchemaType[0];
        EMPTY_GE_ARRAY = new SchemaGlobalElement[0];
        EMPTY_GA_ARRAY = new SchemaGlobalAttribute[0];
        EMPTY_MG_ARRAY = new SchemaModelGroup[0];
        EMPTY_AG_ARRAY = new SchemaAttributeGroup[0];
        EMPTY_IC_ARRAY = new SchemaIdentityConstraint[0];
        EMPTY_ANN_ARRAY = new SchemaAnnotation[0];
        SINGLE_ZERO_BYTE = new byte[]{0};
    }

    private class XsbReader {
        DataInputStream _input;
        DataOutputStream _output;
        StringPool _stringPool;
        String _handle;
        private int _majorver;
        private int _minorver;
        private int _releaseno;
        int _actualfiletype;

        public XsbReader(String handle, int filetype) {
            int actualfiletype;
            String resourcename = SchemaTypeSystemImpl.this._basePackage + handle + ".xsb";
            InputStream rawinput = this.getLoaderStream(resourcename);
            if (rawinput == null) {
                throw new SchemaTypeLoaderException("XML-BEANS compiled schema: Could not locate compiled schema resource " + resourcename, SchemaTypeSystemImpl.this._name, handle, 0);
            }
            this._input = new DataInputStream(rawinput);
            this._handle = handle;
            int magic = this.readInt();
            if (magic != -629491010) {
                throw new SchemaTypeLoaderException("XML-BEANS compiled schema: Wrong magic cookie", SchemaTypeSystemImpl.this._name, handle, 1);
            }
            this._majorver = this.readShort();
            this._minorver = this.readShort();
            if (this._majorver != 2) {
                throw new SchemaTypeLoaderException("XML-BEANS compiled schema: Wrong major version - expecting 2, got " + this._majorver, SchemaTypeSystemImpl.this._name, handle, 2);
            }
            if (this._minorver > 24) {
                throw new SchemaTypeLoaderException("XML-BEANS compiled schema: Incompatible minor version - expecting up to 24, got " + this._minorver, SchemaTypeSystemImpl.this._name, handle, 3);
            }
            if (this._minorver < 14) {
                throw new SchemaTypeLoaderException("XML-BEANS compiled schema: Incompatible minor version - expecting at least 14, got " + this._minorver, SchemaTypeSystemImpl.this._name, handle, 3);
            }
            if (this.atLeast(2, 18, 0)) {
                this._releaseno = this.readShort();
            }
            if ((actualfiletype = this.readShort()) != filetype && filetype != 65535) {
                throw new SchemaTypeLoaderException("XML-BEANS compiled schema: File has the wrong type - expecting type " + filetype + ", got type " + actualfiletype, SchemaTypeSystemImpl.this._name, handle, 4);
            }
            this._stringPool = new StringPool(this._handle, SchemaTypeSystemImpl.this._name);
            this._stringPool.readFrom(this._input);
            this._actualfiletype = actualfiletype;
        }

        protected boolean atLeast(int majorver, int minorver, int releaseno) {
            if (this._majorver > majorver) {
                return true;
            }
            if (this._majorver < majorver) {
                return false;
            }
            if (this._minorver > minorver) {
                return true;
            }
            if (this._minorver < minorver) {
                return false;
            }
            return this._releaseno >= releaseno;
        }

        protected boolean atMost(int majorver, int minorver, int releaseno) {
            if (this._majorver > majorver) {
                return false;
            }
            if (this._majorver < majorver) {
                return true;
            }
            if (this._minorver > minorver) {
                return false;
            }
            if (this._minorver < minorver) {
                return true;
            }
            return this._releaseno <= releaseno;
        }

        int getActualFiletype() {
            return this._actualfiletype;
        }

        XsbReader(String handle) {
            this._handle = handle;
            this._stringPool = new StringPool(this._handle, SchemaTypeSystemImpl.this._name);
        }

        void writeRealHeader(String handle, int filetype) {
            String resourcename = handle.indexOf(47) >= 0 ? handle + ".xsb" : SchemaTypeSystemImpl.this._basePackage + handle + ".xsb";
            OutputStream rawoutput = this.getSaverStream(resourcename);
            if (rawoutput == null) {
                throw new SchemaTypeLoaderException("Could not write compiled schema resource " + resourcename, SchemaTypeSystemImpl.this._name, handle, 12);
            }
            this._output = new DataOutputStream(rawoutput);
            this._handle = handle;
            this.writeInt(-629491010);
            this.writeShort(2);
            this.writeShort(24);
            this.writeShort(0);
            this.writeShort(filetype);
            this._stringPool.writeTo(this._output);
        }

        void readEnd() {
            try {
                if (this._input != null) {
                    this._input.close();
                }
            } catch (IOException iOException) {
                // empty catch block
            }
            this._input = null;
            this._stringPool = null;
            this._handle = null;
        }

        void writeEnd() {
            try {
                if (this._output != null) {
                    this._output.flush();
                    this._output.close();
                }
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage(), SchemaTypeSystemImpl.this._name, this._handle, 9);
            }
            this._output = null;
            this._stringPool = null;
            this._handle = null;
        }

        int fileTypeFromComponentType(int componentType) {
            switch (componentType) {
                case 0: {
                    return 2;
                }
                case 1: {
                    return 3;
                }
                case 3: {
                    return 4;
                }
                case 6: {
                    return 6;
                }
                case 4: {
                    return 7;
                }
                case 5: {
                    return 8;
                }
            }
            throw new IllegalStateException("Unexpected component type");
        }

        void writeIndexData() {
            this.writeHandlePool(SchemaTypeSystemImpl.this._localHandles);
            this.writeQNameMap(SchemaTypeSystemImpl.this.globalElements());
            this.writeQNameMap(SchemaTypeSystemImpl.this.globalAttributes());
            this.writeQNameMap(SchemaTypeSystemImpl.this.modelGroups());
            this.writeQNameMap(SchemaTypeSystemImpl.this.attributeGroups());
            this.writeQNameMap(SchemaTypeSystemImpl.this.identityConstraints());
            this.writeQNameMap(SchemaTypeSystemImpl.this.globalTypes());
            this.writeDocumentTypeMap(SchemaTypeSystemImpl.this.documentTypes());
            this.writeAttributeTypeMap(SchemaTypeSystemImpl.this.attributeTypes());
            this.writeClassnameMap(SchemaTypeSystemImpl.this._typeRefsByClassname);
            this.writeNamespaces(SchemaTypeSystemImpl.this._namespaces);
            this.writeQNameMap(SchemaTypeSystemImpl.this.redefinedGlobalTypes());
            this.writeQNameMap(SchemaTypeSystemImpl.this.redefinedModelGroups());
            this.writeQNameMap(SchemaTypeSystemImpl.this.redefinedAttributeGroups());
            this.writeAnnotations(SchemaTypeSystemImpl.this.annotations());
        }

        void writeHandlePool(HandlePool pool) {
            this.writeShort(pool._componentsToHandles.size());
            for (SchemaComponent comp : pool._componentsToHandles.keySet()) {
                String handle = (String)pool._componentsToHandles.get(comp);
                int code = this.fileTypeFromComponentType(comp.getComponentType());
                this.writeString(handle);
                this.writeShort(code);
            }
        }

        void readHandlePool(HandlePool pool) {
            if (pool._handlesToRefs.size() != 0 || pool._started) {
                throw new IllegalStateException("Nonempty handle set before read");
            }
            int size = this.readShort();
            for (int i = 0; i < size; ++i) {
                SchemaComponent.Ref result;
                String handle = this.readString();
                int code = this.readShort();
                switch (code) {
                    case 2: {
                        result = new SchemaType.Ref(SchemaTypeSystemImpl.this.getTypeSystem(), handle);
                        break;
                    }
                    case 3: {
                        result = new SchemaGlobalElement.Ref(SchemaTypeSystemImpl.this.getTypeSystem(), handle);
                        break;
                    }
                    case 4: {
                        result = new SchemaGlobalAttribute.Ref(SchemaTypeSystemImpl.this.getTypeSystem(), handle);
                        break;
                    }
                    case 6: {
                        result = new SchemaModelGroup.Ref(SchemaTypeSystemImpl.this.getTypeSystem(), handle);
                        break;
                    }
                    case 7: {
                        result = new SchemaAttributeGroup.Ref(SchemaTypeSystemImpl.this.getTypeSystem(), handle);
                        break;
                    }
                    case 8: {
                        result = new SchemaIdentityConstraint.Ref(SchemaTypeSystemImpl.this.getTypeSystem(), handle);
                        break;
                    }
                    default: {
                        throw new SchemaTypeLoaderException("Schema index has an unrecognized entry of type " + code, SchemaTypeSystemImpl.this._name, handle, 5);
                    }
                }
                pool._handlesToRefs.put(handle, result);
            }
        }

        int readShort() {
            try {
                return this._input.readUnsignedShort();
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage(), SchemaTypeSystemImpl.this._name, this._handle, 9);
            }
        }

        void writeShort(int s) {
            if (s >= 65535 || s < -1) {
                throw new SchemaTypeLoaderException("Value " + s + " out of range: must fit in a 16-bit unsigned short.", SchemaTypeSystemImpl.this._name, this._handle, 10);
            }
            if (this._output != null) {
                try {
                    this._output.writeShort(s);
                } catch (IOException e) {
                    throw new SchemaTypeLoaderException(e.getMessage(), SchemaTypeSystemImpl.this._name, this._handle, 9);
                }
            }
        }

        int readInt() {
            try {
                return this._input.readInt();
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage(), SchemaTypeSystemImpl.this._name, this._handle, 9);
            }
        }

        void writeInt(int i) {
            if (this._output != null) {
                try {
                    this._output.writeInt(i);
                } catch (IOException e) {
                    throw new SchemaTypeLoaderException(e.getMessage(), SchemaTypeSystemImpl.this._name, this._handle, 9);
                }
            }
        }

        String readString() {
            return this._stringPool.stringForCode(this.readShort());
        }

        void writeString(String str) {
            int code = this._stringPool.codeForString(str);
            this.writeShort(code);
        }

        QName readQName() {
            String namespace = this.readString();
            String localname = this.readString();
            if (localname == null) {
                return null;
            }
            return new QName(namespace, localname);
        }

        void writeQName(QName qname) {
            if (qname == null) {
                this.writeString(null);
                this.writeString(null);
                return;
            }
            this.writeString(qname.getNamespaceURI());
            this.writeString(qname.getLocalPart());
        }

        SOAPArrayType readSOAPArrayType() {
            QName qName = this.readQName();
            String dimensions = this.readString();
            if (qName == null) {
                return null;
            }
            return new SOAPArrayType(qName, dimensions);
        }

        void writeSOAPArrayType(SOAPArrayType arrayType) {
            if (arrayType == null) {
                this.writeQName(null);
                this.writeString(null);
            } else {
                this.writeQName(arrayType.getQName());
                this.writeString(arrayType.soap11DimensionString());
            }
        }

        void writeAnnotation(SchemaAnnotation a) {
            if (a == null) {
                this.writeInt(-1);
                return;
            }
            SchemaAnnotation.Attribute[] attributes = a.getAttributes();
            this.writeInt(attributes.length);
            for (int i = 0; i < attributes.length; ++i) {
                QName name = attributes[i].getName();
                String value = attributes[i].getValue();
                String valueURI = attributes[i].getValueUri();
                this.writeQName(name);
                this.writeString(value);
                this.writeString(valueURI);
            }
            XmlObject[] documentationItems = a.getUserInformation();
            this.writeInt(documentationItems.length);
            XmlOptions opt = new XmlOptions().setSaveOuter().setSaveAggressiveNamespaces();
            for (int i = 0; i < documentationItems.length; ++i) {
                XmlObject doc = documentationItems[i];
                this.writeString(doc.xmlText(opt));
            }
            XmlObject[] appInfoItems = a.getApplicationInformation();
            this.writeInt(appInfoItems.length);
            for (int i = 0; i < appInfoItems.length; ++i) {
                XmlObject doc = appInfoItems[i];
                this.writeString(doc.xmlText(opt));
            }
        }

        SchemaAnnotation readAnnotation(SchemaContainer c) {
            if (!this.atLeast(2, 19, 0)) {
                return null;
            }
            int n = this.readInt();
            if (n == -1) {
                return null;
            }
            SchemaAnnotation.Attribute[] attributes = new SchemaAnnotation.Attribute[n];
            for (int i = 0; i < n; ++i) {
                QName name = this.readQName();
                String value = this.readString();
                String valueUri = null;
                if (this.atLeast(2, 24, 0)) {
                    valueUri = this.readString();
                }
                attributes[i] = new SchemaAnnotationImpl.AttributeImpl(name, value, valueUri);
            }
            n = this.readInt();
            String[] docStrings = new String[n];
            for (int i = 0; i < n; ++i) {
                docStrings[i] = this.readString();
            }
            n = this.readInt();
            String[] appInfoStrings = new String[n];
            for (int i = 0; i < n; ++i) {
                appInfoStrings[i] = this.readString();
            }
            return new SchemaAnnotationImpl(c, appInfoStrings, docStrings, attributes);
        }

        void writeAnnotations(SchemaAnnotation[] anns) {
            this.writeInt(anns.length);
            for (int i = 0; i < anns.length; ++i) {
                this.writeAnnotation(anns[i]);
            }
        }

        List readAnnotations() {
            int n = this.readInt();
            ArrayList<SchemaAnnotation> result = new ArrayList<SchemaAnnotation>(n);
            SchemaContainer container = SchemaTypeSystemImpl.this.getContainerNonNull("");
            for (int i = 0; i < n; ++i) {
                result.add(this.readAnnotation(container));
            }
            return result;
        }

        SchemaComponent.Ref readHandle() {
            String handle = this.readString();
            if (handle == null) {
                return null;
            }
            if (handle.charAt(0) != '_') {
                return SchemaTypeSystemImpl.this._localHandles.refForHandle(handle);
            }
            switch (handle.charAt(2)) {
                case 'I': {
                    SchemaType st = (SchemaType)BuiltinSchemaTypeSystem.get().resolveHandle(handle);
                    if (st != null) {
                        return st.getRef();
                    }
                    st = (SchemaType)XQuerySchemaTypeSystem.get().resolveHandle(handle);
                    return st.getRef();
                }
                case 'T': {
                    return SchemaTypeSystemImpl.this._linker.findTypeRef(QNameHelper.forPretty(handle, 4));
                }
                case 'E': {
                    return SchemaTypeSystemImpl.this._linker.findElementRef(QNameHelper.forPretty(handle, 4));
                }
                case 'A': {
                    return SchemaTypeSystemImpl.this._linker.findAttributeRef(QNameHelper.forPretty(handle, 4));
                }
                case 'M': {
                    return SchemaTypeSystemImpl.this._linker.findModelGroupRef(QNameHelper.forPretty(handle, 4));
                }
                case 'N': {
                    return SchemaTypeSystemImpl.this._linker.findAttributeGroupRef(QNameHelper.forPretty(handle, 4));
                }
                case 'D': {
                    return SchemaTypeSystemImpl.this._linker.findIdentityConstraintRef(QNameHelper.forPretty(handle, 4));
                }
                case 'R': {
                    SchemaGlobalAttribute attr = SchemaTypeSystemImpl.this._linker.findAttribute(QNameHelper.forPretty(handle, 4));
                    if (attr == null) {
                        throw new SchemaTypeLoaderException("Cannot resolve attribute for handle " + handle, SchemaTypeSystemImpl.this._name, this._handle, 13);
                    }
                    return attr.getType().getRef();
                }
                case 'S': {
                    SchemaGlobalElement elem = SchemaTypeSystemImpl.this._linker.findElement(QNameHelper.forPretty(handle, 4));
                    if (elem == null) {
                        throw new SchemaTypeLoaderException("Cannot resolve element for handle " + handle, SchemaTypeSystemImpl.this._name, this._handle, 13);
                    }
                    return elem.getType().getRef();
                }
                case 'O': {
                    return SchemaTypeSystemImpl.this._linker.findDocumentTypeRef(QNameHelper.forPretty(handle, 4));
                }
                case 'Y': {
                    SchemaType type = SchemaTypeSystemImpl.this._linker.typeForSignature(handle.substring(4));
                    if (type == null) {
                        throw new SchemaTypeLoaderException("Cannot resolve type for handle " + handle, SchemaTypeSystemImpl.this._name, this._handle, 13);
                    }
                    return type.getRef();
                }
            }
            throw new SchemaTypeLoaderException("Cannot resolve handle " + handle, SchemaTypeSystemImpl.this._name, this._handle, 13);
        }

        void writeHandle(SchemaComponent comp) {
            if (comp == null || comp.getTypeSystem() == SchemaTypeSystemImpl.this.getTypeSystem()) {
                this.writeString(SchemaTypeSystemImpl.this._localHandles.handleForComponent(comp));
                return;
            }
            switch (comp.getComponentType()) {
                case 3: {
                    this.writeString("_XA_" + QNameHelper.pretty(comp.getName()));
                    return;
                }
                case 6: {
                    this.writeString("_XM_" + QNameHelper.pretty(comp.getName()));
                    return;
                }
                case 4: {
                    this.writeString("_XN_" + QNameHelper.pretty(comp.getName()));
                    return;
                }
                case 1: {
                    this.writeString("_XE_" + QNameHelper.pretty(comp.getName()));
                    return;
                }
                case 5: {
                    this.writeString("_XD_" + QNameHelper.pretty(comp.getName()));
                    return;
                }
                case 0: {
                    SchemaType type = (SchemaType)comp;
                    if (type.isBuiltinType()) {
                        this.writeString("_BI_" + type.getName().getLocalPart());
                        return;
                    }
                    if (type.getName() != null) {
                        this.writeString("_XT_" + QNameHelper.pretty(type.getName()));
                    } else if (type.isDocumentType()) {
                        this.writeString("_XO_" + QNameHelper.pretty(type.getDocumentElementName()));
                    } else {
                        this.writeString("_XY_" + type.toString());
                    }
                    return;
                }
            }
            assert (false);
            throw new SchemaTypeLoaderException("Cannot write handle for component " + comp, SchemaTypeSystemImpl.this._name, this._handle, 13);
        }

        SchemaType.Ref readTypeRef() {
            return (SchemaType.Ref)this.readHandle();
        }

        void writeType(SchemaType type) {
            this.writeHandle(type);
        }

        Map readQNameRefMap() {
            HashMap<QName, SchemaComponent.Ref> result = new HashMap<QName, SchemaComponent.Ref>();
            int size = this.readShort();
            for (int i = 0; i < size; ++i) {
                QName name = this.readQName();
                SchemaComponent.Ref obj = this.readHandle();
                result.put(name, obj);
            }
            return result;
        }

        List readQNameRefMapAsList(List names) {
            int size = this.readShort();
            ArrayList<SchemaComponent.Ref> result = new ArrayList<SchemaComponent.Ref>(size);
            for (int i = 0; i < size; ++i) {
                QName name = this.readQName();
                SchemaComponent.Ref obj = this.readHandle();
                result.add(obj);
                names.add(name);
            }
            return result;
        }

        void writeQNameMap(SchemaComponent[] components) {
            this.writeShort(components.length);
            for (int i = 0; i < components.length; ++i) {
                this.writeQName(components[i].getName());
                this.writeHandle(components[i]);
            }
        }

        void writeDocumentTypeMap(SchemaType[] doctypes) {
            this.writeShort(doctypes.length);
            for (int i = 0; i < doctypes.length; ++i) {
                this.writeQName(doctypes[i].getDocumentElementName());
                this.writeHandle(doctypes[i]);
            }
        }

        void writeAttributeTypeMap(SchemaType[] attrtypes) {
            this.writeShort(attrtypes.length);
            for (int i = 0; i < attrtypes.length; ++i) {
                this.writeQName(attrtypes[i].getAttributeTypeAttributeName());
                this.writeHandle(attrtypes[i]);
            }
        }

        SchemaType.Ref[] readTypeRefArray() {
            int size = this.readShort();
            SchemaType.Ref[] result = new SchemaType.Ref[size];
            for (int i = 0; i < size; ++i) {
                result[i] = this.readTypeRef();
            }
            return result;
        }

        void writeTypeArray(SchemaType[] array) {
            this.writeShort(array.length);
            for (int i = 0; i < array.length; ++i) {
                this.writeHandle(array[i]);
            }
        }

        Map readClassnameRefMap() {
            HashMap<String, SchemaComponent.Ref> result = new HashMap<String, SchemaComponent.Ref>();
            int size = this.readShort();
            for (int i = 0; i < size; ++i) {
                String name = this.readString();
                SchemaComponent.Ref obj = this.readHandle();
                result.put(name, obj);
            }
            return result;
        }

        void writeClassnameMap(Map typesByClass) {
            this.writeShort(typesByClass.size());
            for (String className : typesByClass.keySet()) {
                this.writeString(className);
                this.writeHandle(((SchemaType.Ref)typesByClass.get(className)).get());
            }
        }

        Set readNamespaces() {
            HashSet<String> result = new HashSet<String>();
            int size = this.readShort();
            for (int i = 0; i < size; ++i) {
                String ns = this.readString();
                result.add(ns);
            }
            return result;
        }

        void writeNamespaces(Set namespaces) {
            this.writeShort(namespaces.size());
            for (String ns : namespaces) {
                this.writeString(ns);
            }
        }

        OutputStream getSaverStream(String name) {
            try {
                return SchemaTypeSystemImpl.this._filer.createBinaryFile(name);
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage(), SchemaTypeSystemImpl.this._name, this._handle, 9);
            }
        }

        InputStream getLoaderStream(String resourcename) {
            return SchemaTypeSystemImpl.this._resourceLoader.getResourceAsStream(resourcename);
        }

        void checkContainerNotNull(SchemaContainer container, QName name) {
            if (container == null) {
                throw new LinkageError("Loading of resource " + SchemaTypeSystemImpl.this._name + '.' + this._handle + "failed, information from " + SchemaTypeSystemImpl.this._name + ".index.xsb is " + " out of sync (or conflicting index files found)");
            }
        }

        public SchemaGlobalElement finishLoadingElement() {
            String handle = null;
            try {
                int particleType = this.readShort();
                if (particleType != 4) {
                    throw new SchemaTypeLoaderException("Wrong particle type ", SchemaTypeSystemImpl.this._name, this._handle, 11);
                }
                int particleFlags = this.readShort();
                BigInteger minOccurs = this.readBigInteger();
                BigInteger maxOccurs = this.readBigInteger();
                QNameSet transitionRules = this.readQNameSet();
                QName name = this.readQName();
                SchemaContainer container = SchemaTypeSystemImpl.this.getContainer(name.getNamespaceURI());
                this.checkContainerNotNull(container, name);
                SchemaGlobalElementImpl impl = new SchemaGlobalElementImpl(container);
                impl.setParticleType(particleType);
                impl.setMinOccurs(minOccurs);
                impl.setMaxOccurs(maxOccurs);
                impl.setTransitionRules(transitionRules, (particleFlags & 1) != 0);
                impl.setNameAndTypeRef(name, this.readTypeRef());
                impl.setDefault(this.readString(), (particleFlags & 4) != 0, null);
                if (this.atLeast(2, 16, 0)) {
                    impl.setDefaultValue(this.readXmlValueObject());
                }
                impl.setNillable((particleFlags & 8) != 0);
                impl.setBlock((particleFlags & 0x10) != 0, (particleFlags & 0x20) != 0, (particleFlags & 0x40) != 0);
                impl.setWsdlArrayType(this.readSOAPArrayType());
                impl.setAbstract((particleFlags & 0x80) != 0);
                impl.setAnnotation(this.readAnnotation(container));
                impl.setFinal((particleFlags & 0x100) != 0, (particleFlags & 0x200) != 0);
                if (this.atLeast(2, 17, 0)) {
                    impl.setSubstitutionGroup((SchemaGlobalElement.Ref)this.readHandle());
                }
                int substGroupCount = this.readShort();
                for (int i = 0; i < substGroupCount; ++i) {
                    impl.addSubstitutionGroupMember(this.readQName());
                }
                SchemaIdentityConstraint.Ref[] idcs = new SchemaIdentityConstraint.Ref[this.readShort()];
                for (int i = 0; i < idcs.length; ++i) {
                    idcs[i] = (SchemaIdentityConstraint.Ref)this.readHandle();
                }
                impl.setIdentityConstraints(idcs);
                impl.setFilename(this.readString());
                SchemaGlobalElementImpl schemaGlobalElementImpl = impl;
                return schemaGlobalElementImpl;
            } catch (SchemaTypeLoaderException e) {
                throw e;
            } catch (Exception e) {
                throw new SchemaTypeLoaderException("Cannot load type from typesystem", SchemaTypeSystemImpl.this._name, handle, 14, e);
            } finally {
                this.readEnd();
            }
        }

        public SchemaGlobalAttribute finishLoadingAttribute() {
            try {
                QName name = this.readQName();
                SchemaContainer container = SchemaTypeSystemImpl.this.getContainer(name.getNamespaceURI());
                this.checkContainerNotNull(container, name);
                SchemaGlobalAttributeImpl impl = new SchemaGlobalAttributeImpl(container);
                this.loadAttribute(impl, name, container);
                impl.setFilename(this.readString());
                SchemaGlobalAttributeImpl schemaGlobalAttributeImpl = impl;
                return schemaGlobalAttributeImpl;
            } catch (SchemaTypeLoaderException e) {
                throw e;
            } catch (Exception e) {
                throw new SchemaTypeLoaderException("Cannot load type from typesystem", SchemaTypeSystemImpl.this._name, this._handle, 14, e);
            } finally {
                this.readEnd();
            }
        }

        SchemaModelGroup finishLoadingModelGroup() {
            QName name = this.readQName();
            SchemaContainer container = SchemaTypeSystemImpl.this.getContainer(name.getNamespaceURI());
            this.checkContainerNotNull(container, name);
            SchemaModelGroupImpl impl = new SchemaModelGroupImpl(container);
            try {
                impl.init(name, this.readString(), this.readShort() == 1, this.atLeast(2, 22, 0) ? this.readString() : null, this.atLeast(2, 22, 0) ? this.readString() : null, this.atLeast(2, 15, 0) ? this.readShort() == 1 : false, GroupDocument.Factory.parse(this.readString()).getGroup(), this.readAnnotation(container), null);
                if (this.atLeast(2, 21, 0)) {
                    impl.setFilename(this.readString());
                }
                SchemaModelGroupImpl schemaModelGroupImpl = impl;
                return schemaModelGroupImpl;
            } catch (SchemaTypeLoaderException e) {
                throw e;
            } catch (Exception e) {
                throw new SchemaTypeLoaderException("Cannot load type from typesystem", SchemaTypeSystemImpl.this._name, this._handle, 14, e);
            } finally {
                this.readEnd();
            }
        }

        SchemaIdentityConstraint finishLoadingIdentityConstraint() {
            try {
                QName name = this.readQName();
                SchemaContainer container = SchemaTypeSystemImpl.this.getContainer(name.getNamespaceURI());
                this.checkContainerNotNull(container, name);
                SchemaIdentityConstraintImpl impl = new SchemaIdentityConstraintImpl(container);
                impl.setName(name);
                impl.setConstraintCategory(this.readShort());
                impl.setSelector(this.readString());
                impl.setAnnotation(this.readAnnotation(container));
                String[] fields = new String[this.readShort()];
                for (int i = 0; i < fields.length; ++i) {
                    fields[i] = this.readString();
                }
                impl.setFields(fields);
                if (impl.getConstraintCategory() == 2) {
                    impl.setReferencedKey((SchemaIdentityConstraint.Ref)this.readHandle());
                }
                int mapCount = this.readShort();
                HashMap<String, String> nsMappings = new HashMap<String, String>();
                for (int i = 0; i < mapCount; ++i) {
                    String prefix = this.readString();
                    String uri = this.readString();
                    nsMappings.put(prefix, uri);
                }
                impl.setNSMap(nsMappings);
                if (this.atLeast(2, 21, 0)) {
                    impl.setFilename(this.readString());
                }
                SchemaIdentityConstraintImpl schemaIdentityConstraintImpl = impl;
                return schemaIdentityConstraintImpl;
            } catch (SchemaTypeLoaderException e) {
                throw e;
            } catch (Exception e) {
                throw new SchemaTypeLoaderException("Cannot load type from typesystem", SchemaTypeSystemImpl.this._name, this._handle, 14, e);
            } finally {
                this.readEnd();
            }
        }

        SchemaAttributeGroup finishLoadingAttributeGroup() {
            QName name = this.readQName();
            SchemaContainer container = SchemaTypeSystemImpl.this.getContainer(name.getNamespaceURI());
            this.checkContainerNotNull(container, name);
            SchemaAttributeGroupImpl impl = new SchemaAttributeGroupImpl(container);
            try {
                impl.init(name, this.readString(), this.readShort() == 1, this.atLeast(2, 22, 0) ? this.readString() : null, this.atLeast(2, 15, 0) ? this.readShort() == 1 : false, AttributeGroupDocument.Factory.parse(this.readString()).getAttributeGroup(), this.readAnnotation(container), null);
                if (this.atLeast(2, 21, 0)) {
                    impl.setFilename(this.readString());
                }
                SchemaAttributeGroupImpl schemaAttributeGroupImpl = impl;
                return schemaAttributeGroupImpl;
            } catch (SchemaTypeLoaderException e) {
                throw e;
            } catch (Exception e) {
                throw new SchemaTypeLoaderException("Cannot load type from typesystem", SchemaTypeSystemImpl.this._name, this._handle, 14, e);
            } finally {
                this.readEnd();
            }
        }

        public SchemaType finishLoadingType() {
            try {
                QName name;
                int i;
                SchemaContainer cNonNull = SchemaTypeSystemImpl.this.getContainerNonNull("");
                SchemaTypeImpl impl = new SchemaTypeImpl(cNonNull, true);
                impl.setName(this.readQName());
                impl.setOuterSchemaTypeRef(this.readTypeRef());
                impl.setBaseDepth(this.readShort());
                impl.setBaseTypeRef(this.readTypeRef());
                impl.setDerivationType(this.readShort());
                impl.setAnnotation(this.readAnnotation(null));
                switch (this.readShort()) {
                    case 1: {
                        impl.setContainerFieldRef(this.readHandle());
                        break;
                    }
                    case 2: {
                        impl.setContainerFieldIndex((short)1, this.readShort());
                        break;
                    }
                    case 3: {
                        impl.setContainerFieldIndex((short)2, this.readShort());
                    }
                }
                String jn = this.readString();
                impl.setFullJavaName(jn == null ? "" : jn);
                jn = this.readString();
                impl.setFullJavaImplName(jn == null ? "" : jn);
                impl.setAnonymousTypeRefs(this.readTypeRefArray());
                impl.setAnonymousUnionMemberOrdinal(this.readShort());
                int flags = this.readInt();
                boolean isComplexType = (flags & 1) == 0;
                impl.setCompiled((flags & 0x800) != 0);
                impl.setDocumentType((flags & 2) != 0);
                impl.setAttributeType((flags & 0x80000) != 0);
                impl.setSimpleType(!isComplexType);
                int complexVariety = 0;
                if (isComplexType) {
                    impl.setAbstractFinal((flags & 0x40000) != 0, (flags & 0x4000) != 0, (flags & 0x8000) != 0, (flags & 0x20000) != 0, (flags & 0x10000) != 0);
                    impl.setBlock((flags & 0x1000) != 0, (flags & 0x2000) != 0);
                    impl.setOrderSensitive((flags & 0x200) != 0);
                    complexVariety = this.readShort();
                    impl.setComplexTypeVariety(complexVariety);
                    if (this.atLeast(2, 23, 0)) {
                        impl.setContentBasedOnTypeRef(this.readTypeRef());
                    }
                    SchemaAttributeModelImpl attrModel = new SchemaAttributeModelImpl();
                    int attrCount = this.readShort();
                    for (int i2 = 0; i2 < attrCount; ++i2) {
                        attrModel.addAttribute(this.readAttributeData());
                    }
                    attrModel.setWildcardSet(this.readQNameSet());
                    attrModel.setWildcardProcess(this.readShort());
                    LinkedHashMap<QName, SchemaProperty> attrProperties = new LinkedHashMap<QName, SchemaProperty>();
                    int attrPropCount = this.readShort();
                    for (int i3 = 0; i3 < attrPropCount; ++i3) {
                        SchemaProperty prop = this.readPropertyData();
                        if (!prop.isAttribute()) {
                            throw new SchemaTypeLoaderException("Attribute property " + i3 + " is not an attribute", SchemaTypeSystemImpl.this._name, this._handle, 6);
                        }
                        attrProperties.put(prop.getName(), prop);
                    }
                    SchemaParticle contentModel = null;
                    LinkedHashMap<QName, SchemaProperty> elemProperties = null;
                    int isAll = 0;
                    if (complexVariety == 3 || complexVariety == 4) {
                        isAll = this.readShort();
                        SchemaParticle[] parts = this.readParticleArray();
                        if (parts.length == 1) {
                            contentModel = parts[0];
                        } else if (parts.length == 0) {
                            contentModel = null;
                        } else {
                            throw new SchemaTypeLoaderException("Content model not well-formed", SchemaTypeSystemImpl.this._name, this._handle, 7);
                        }
                        elemProperties = new LinkedHashMap<QName, SchemaProperty>();
                        int elemPropCount = this.readShort();
                        for (i = 0; i < elemPropCount; ++i) {
                            SchemaProperty prop = this.readPropertyData();
                            if (prop.isAttribute()) {
                                throw new SchemaTypeLoaderException("Element property " + i + " is not an element", SchemaTypeSystemImpl.this._name, this._handle, 6);
                            }
                            elemProperties.put(prop.getName(), prop);
                        }
                    }
                    impl.setContentModel(contentModel, attrModel, elemProperties, attrProperties, isAll == 1);
                    StscComplexTypeResolver.WildcardResult wcElt = StscComplexTypeResolver.summarizeEltWildcards(contentModel);
                    StscComplexTypeResolver.WildcardResult wcAttr = StscComplexTypeResolver.summarizeAttrWildcards(attrModel);
                    impl.setWildcardSummary(wcElt.typedWildcards, wcElt.hasWildcards, wcAttr.typedWildcards, wcAttr.hasWildcards);
                }
                if (!isComplexType || complexVariety == 2) {
                    boolean isStringEnum;
                    int simpleVariety = this.readShort();
                    impl.setSimpleTypeVariety(simpleVariety);
                    boolean bl = isStringEnum = (flags & 0x40) != 0;
                    impl.setOrdered((flags & 4) != 0 ? 0 : ((flags & 0x400) != 0 ? 2 : 1));
                    impl.setBounded((flags & 8) != 0);
                    impl.setFinite((flags & 0x10) != 0);
                    impl.setNumeric((flags & 0x20) != 0);
                    impl.setUnionOfLists((flags & 0x80) != 0);
                    impl.setSimpleFinal((flags & 0x8000) != 0, (flags & 0x20000) != 0, (flags & 0x10000) != 0);
                    XmlValueRef[] facets = new XmlValueRef[12];
                    boolean[] fixedFacets = new boolean[12];
                    int facetCount = this.readShort();
                    for (int i4 = 0; i4 < facetCount; ++i4) {
                        int facetCode = this.readShort();
                        facets[facetCode] = this.readXmlValueObject();
                        fixedFacets[facetCode] = this.readShort() == 1;
                    }
                    impl.setBasicFacets(facets, fixedFacets);
                    impl.setWhiteSpaceRule(this.readShort());
                    impl.setPatternFacet((flags & 0x100) != 0);
                    int patternCount = this.readShort();
                    RegularExpression[] patterns = new RegularExpression[patternCount];
                    for (int i5 = 0; i5 < patternCount; ++i5) {
                        patterns[i5] = new RegularExpression(this.readString(), "X");
                    }
                    impl.setPatterns(patterns);
                    int enumCount = this.readShort();
                    XmlValueRef[] enumValues = new XmlValueRef[enumCount];
                    for (i = 0; i < enumCount; ++i) {
                        enumValues[i] = this.readXmlValueObject();
                    }
                    impl.setEnumerationValues(enumCount == 0 ? null : enumValues);
                    impl.setBaseEnumTypeRef(this.readTypeRef());
                    if (isStringEnum) {
                        int seCount = this.readShort();
                        SchemaStringEnumEntry[] entries = new SchemaStringEnumEntry[seCount];
                        for (int i6 = 0; i6 < seCount; ++i6) {
                            entries[i6] = new SchemaStringEnumEntryImpl(this.readString(), this.readShort(), this.readString());
                        }
                        impl.setStringEnumEntries(entries);
                    }
                    switch (simpleVariety) {
                        case 1: {
                            impl.setPrimitiveTypeRef(this.readTypeRef());
                            impl.setDecimalSize(this.readInt());
                            break;
                        }
                        case 3: {
                            impl.setPrimitiveTypeRef(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getRef());
                            impl.setListItemTypeRef(this.readTypeRef());
                            break;
                        }
                        case 2: {
                            impl.setPrimitiveTypeRef(BuiltinSchemaTypeSystem.ST_ANY_SIMPLE.getRef());
                            impl.setUnionMemberTypeRefs(this.readTypeRefArray());
                            break;
                        }
                        default: {
                            throw new SchemaTypeLoaderException("Simple type does not have a recognized variety", SchemaTypeSystemImpl.this._name, this._handle, 8);
                        }
                    }
                }
                impl.setFilename(this.readString());
                if (impl.getName() != null) {
                    SchemaContainer container = SchemaTypeSystemImpl.this.getContainer(impl.getName().getNamespaceURI());
                    this.checkContainerNotNull(container, impl.getName());
                    impl.setContainer(container);
                } else if (impl.isDocumentType()) {
                    name = impl.getDocumentElementName();
                    if (name != null) {
                        SchemaContainer container = SchemaTypeSystemImpl.this.getContainer(name.getNamespaceURI());
                        this.checkContainerNotNull(container, name);
                        impl.setContainer(container);
                    }
                } else if (impl.isAttributeType() && (name = impl.getAttributeTypeAttributeName()) != null) {
                    SchemaContainer container = SchemaTypeSystemImpl.this.getContainer(name.getNamespaceURI());
                    this.checkContainerNotNull(container, name);
                    impl.setContainer(container);
                }
                SchemaTypeImpl schemaTypeImpl = impl;
                return schemaTypeImpl;
            } catch (SchemaTypeLoaderException e) {
                throw e;
            } catch (Exception e) {
                throw new SchemaTypeLoaderException("Cannot load type from typesystem", SchemaTypeSystemImpl.this._name, this._handle, 14, e);
            } finally {
                this.readEnd();
            }
        }

        void writeTypeData(SchemaType type) {
            int i;
            this.writeQName(type.getName());
            this.writeType(type.getOuterType());
            this.writeShort(((SchemaTypeImpl)type).getBaseDepth());
            this.writeType(type.getBaseType());
            this.writeShort(type.getDerivationType());
            this.writeAnnotation(type.getAnnotation());
            if (type.getContainerField() == null) {
                this.writeShort(0);
            } else if (type.getOuterType().isAttributeType() || type.getOuterType().isDocumentType()) {
                this.writeShort(1);
                this.writeHandle((SchemaComponent)((Object)type.getContainerField()));
            } else if (type.getContainerField().isAttribute()) {
                this.writeShort(2);
                this.writeShort(((SchemaTypeImpl)type.getOuterType()).getIndexForLocalAttribute((SchemaLocalAttribute)type.getContainerField()));
            } else {
                this.writeShort(3);
                this.writeShort(((SchemaTypeImpl)type.getOuterType()).getIndexForLocalElement((SchemaLocalElement)type.getContainerField()));
            }
            this.writeString(type.getFullJavaName());
            this.writeString(type.getFullJavaImplName());
            this.writeTypeArray(type.getAnonymousTypes());
            this.writeShort(type.getAnonymousUnionMemberOrdinal());
            int flags = 0;
            if (type.isSimpleType()) {
                flags |= 1;
            }
            if (type.isDocumentType()) {
                flags |= 2;
            }
            if (type.isAttributeType()) {
                flags |= 0x80000;
            }
            if (type.ordered() != 0) {
                flags |= 4;
            }
            if (type.ordered() == 2) {
                flags |= 0x400;
            }
            if (type.isBounded()) {
                flags |= 8;
            }
            if (type.isFinite()) {
                flags |= 0x10;
            }
            if (type.isNumeric()) {
                flags |= 0x20;
            }
            if (type.hasStringEnumValues()) {
                flags |= 0x40;
            }
            if (((SchemaTypeImpl)type).isUnionOfLists()) {
                flags |= 0x80;
            }
            if (type.hasPatternFacet()) {
                flags |= 0x100;
            }
            if (type.isOrderSensitive()) {
                flags |= 0x200;
            }
            if (type.blockExtension()) {
                flags |= 0x1000;
            }
            if (type.blockRestriction()) {
                flags |= 0x2000;
            }
            if (type.finalExtension()) {
                flags |= 0x4000;
            }
            if (type.finalRestriction()) {
                flags |= 0x4000;
            }
            if (type.finalList()) {
                flags |= 0x20000;
            }
            if (type.finalUnion()) {
                flags |= 0x10000;
            }
            if (type.isAbstract()) {
                flags |= 0x40000;
            }
            this.writeInt(flags);
            if (!type.isSimpleType()) {
                this.writeShort(type.getContentType());
                this.writeType(type.getContentBasedOnType());
                SchemaAttributeModel attrModel = type.getAttributeModel();
                SchemaLocalAttribute[] attrs = attrModel.getAttributes();
                this.writeShort(attrs.length);
                for (int i2 = 0; i2 < attrs.length; ++i2) {
                    this.writeAttributeData(attrs[i2]);
                }
                this.writeQNameSet(attrModel.getWildcardSet());
                this.writeShort(attrModel.getWildcardProcess());
                SchemaProperty[] attrProperties = type.getAttributeProperties();
                this.writeShort(attrProperties.length);
                for (i = 0; i < attrProperties.length; ++i) {
                    this.writePropertyData(attrProperties[i]);
                }
                if (type.getContentType() == 3 || type.getContentType() == 4) {
                    this.writeShort(type.hasAllContent() ? 1 : 0);
                    SchemaParticle[] parts = type.getContentModel() != null ? new SchemaParticle[]{type.getContentModel()} : new SchemaParticle[]{};
                    this.writeParticleArray(parts);
                    SchemaProperty[] eltProperties = type.getElementProperties();
                    this.writeShort(eltProperties.length);
                    for (int i3 = 0; i3 < eltProperties.length; ++i3) {
                        this.writePropertyData(eltProperties[i3]);
                    }
                }
            }
            if (type.isSimpleType() || type.getContentType() == 2) {
                int i4;
                this.writeShort(type.getSimpleVariety());
                int facetCount = 0;
                for (i4 = 0; i4 <= 11; ++i4) {
                    if (type.getFacet(i4) == null) continue;
                    ++facetCount;
                }
                this.writeShort(facetCount);
                for (i4 = 0; i4 <= 11; ++i4) {
                    XmlAnySimpleType facet = type.getFacet(i4);
                    if (facet == null) continue;
                    this.writeShort(i4);
                    this.writeXmlValueObject(facet);
                    this.writeShort(type.isFacetFixed(i4) ? 1 : 0);
                }
                this.writeShort(type.getWhiteSpaceRule());
                RegularExpression[] patterns = ((SchemaTypeImpl)type).getPatternExpressions();
                this.writeShort(patterns.length);
                for (int i5 = 0; i5 < patterns.length; ++i5) {
                    this.writeString(patterns[i5].getPattern());
                }
                XmlAnySimpleType[] enumValues = type.getEnumerationValues();
                if (enumValues == null) {
                    this.writeShort(0);
                } else {
                    this.writeShort(enumValues.length);
                    for (i = 0; i < enumValues.length; ++i) {
                        this.writeXmlValueObject(enumValues[i]);
                    }
                }
                this.writeType(type.getBaseEnumType());
                if (type.hasStringEnumValues()) {
                    SchemaStringEnumEntry[] entries = type.getStringEnumEntries();
                    this.writeShort(entries.length);
                    for (int i6 = 0; i6 < entries.length; ++i6) {
                        this.writeString(entries[i6].getString());
                        this.writeShort(entries[i6].getIntValue());
                        this.writeString(entries[i6].getEnumName());
                    }
                }
                switch (type.getSimpleVariety()) {
                    case 1: {
                        this.writeType(type.getPrimitiveType());
                        this.writeInt(type.getDecimalSize());
                        break;
                    }
                    case 3: {
                        this.writeType(type.getListItemType());
                        break;
                    }
                    case 2: {
                        this.writeTypeArray(type.getUnionMemberTypes());
                    }
                }
            }
            this.writeString(type.getSourceName());
        }

        void readExtensionsList() {
            int count = this.readShort();
            assert (count == 0);
            for (int i = 0; i < count; ++i) {
                this.readString();
                this.readString();
                this.readString();
            }
        }

        SchemaLocalAttribute readAttributeData() {
            SchemaLocalAttributeImpl result = new SchemaLocalAttributeImpl();
            this.loadAttribute(result, this.readQName(), null);
            return result;
        }

        void loadAttribute(SchemaLocalAttributeImpl result, QName name, SchemaContainer container) {
            result.init(name, this.readTypeRef(), this.readShort(), this.readString(), null, this.atLeast(2, 16, 0) ? this.readXmlValueObject() : null, this.readShort() == 1, this.readSOAPArrayType(), this.readAnnotation(container), null);
        }

        void writeAttributeData(SchemaLocalAttribute attr) {
            this.writeQName(attr.getName());
            this.writeType(attr.getType());
            this.writeShort(attr.getUse());
            this.writeString(attr.getDefaultText());
            this.writeXmlValueObject(attr.getDefaultValue());
            this.writeShort(attr.isFixed() ? 1 : 0);
            this.writeSOAPArrayType(((SchemaWSDLArrayType)((Object)attr)).getWSDLArrayType());
            this.writeAnnotation(attr.getAnnotation());
        }

        void writeIdConstraintData(SchemaIdentityConstraint idc) {
            this.writeQName(idc.getName());
            this.writeShort(idc.getConstraintCategory());
            this.writeString(idc.getSelector());
            this.writeAnnotation(idc.getAnnotation());
            String[] fields = idc.getFields();
            this.writeShort(fields.length);
            for (int i = 0; i < fields.length; ++i) {
                this.writeString(fields[i]);
            }
            if (idc.getConstraintCategory() == 2) {
                this.writeHandle(idc.getReferencedKey());
            }
            Set mappings = idc.getNSMap().entrySet();
            this.writeShort(mappings.size());
            for (Map.Entry e : mappings) {
                String prefix = (String)e.getKey();
                String uri = (String)e.getValue();
                this.writeString(prefix);
                this.writeString(uri);
            }
            this.writeString(idc.getSourceName());
        }

        SchemaParticle[] readParticleArray() {
            SchemaParticle[] result = new SchemaParticle[this.readShort()];
            for (int i = 0; i < result.length; ++i) {
                result[i] = this.readParticleData();
            }
            return result;
        }

        void writeParticleArray(SchemaParticle[] spa) {
            this.writeShort(spa.length);
            for (int i = 0; i < spa.length; ++i) {
                this.writeParticleData(spa[i]);
            }
        }

        SchemaParticle readParticleData() {
            int particleType = this.readShort();
            SchemaParticleImpl result = particleType != 4 ? new SchemaParticleImpl() : new SchemaLocalElementImpl();
            this.loadParticle(result, particleType);
            return result;
        }

        void loadParticle(SchemaParticleImpl result, int particleType) {
            int particleFlags = this.readShort();
            result.setParticleType(particleType);
            result.setMinOccurs(this.readBigInteger());
            result.setMaxOccurs(this.readBigInteger());
            result.setTransitionRules(this.readQNameSet(), (particleFlags & 1) != 0);
            switch (particleType) {
                case 5: {
                    result.setWildcardSet(this.readQNameSet());
                    result.setWildcardProcess(this.readShort());
                    break;
                }
                case 4: {
                    SchemaLocalElementImpl lresult = (SchemaLocalElementImpl)result;
                    lresult.setNameAndTypeRef(this.readQName(), this.readTypeRef());
                    lresult.setDefault(this.readString(), (particleFlags & 4) != 0, null);
                    if (this.atLeast(2, 16, 0)) {
                        lresult.setDefaultValue(this.readXmlValueObject());
                    }
                    lresult.setNillable((particleFlags & 8) != 0);
                    lresult.setBlock((particleFlags & 0x10) != 0, (particleFlags & 0x20) != 0, (particleFlags & 0x40) != 0);
                    lresult.setWsdlArrayType(this.readSOAPArrayType());
                    lresult.setAbstract((particleFlags & 0x80) != 0);
                    lresult.setAnnotation(this.readAnnotation(null));
                    SchemaIdentityConstraint.Ref[] idcs = new SchemaIdentityConstraint.Ref[this.readShort()];
                    for (int i = 0; i < idcs.length; ++i) {
                        idcs[i] = (SchemaIdentityConstraint.Ref)this.readHandle();
                    }
                    lresult.setIdentityConstraints(idcs);
                    break;
                }
                case 1: 
                case 2: 
                case 3: {
                    result.setParticleChildren(this.readParticleArray());
                    break;
                }
                default: {
                    throw new SchemaTypeLoaderException("Unrecognized particle type ", SchemaTypeSystemImpl.this._name, this._handle, 11);
                }
            }
        }

        void writeParticleData(SchemaParticle part) {
            SchemaGlobalElement gpart;
            SchemaLocalElement lpart;
            this.writeShort(part.getParticleType());
            short flags = 0;
            if (part.isSkippable()) {
                flags = (short)(flags | 1);
            }
            if (part.getParticleType() == 4) {
                lpart = (SchemaLocalElement)((Object)part);
                if (lpart.isFixed()) {
                    flags = (short)(flags | 4);
                }
                if (lpart.isNillable()) {
                    flags = (short)(flags | 8);
                }
                if (lpart.blockExtension()) {
                    flags = (short)(flags | 0x10);
                }
                if (lpart.blockRestriction()) {
                    flags = (short)(flags | 0x20);
                }
                if (lpart.blockSubstitution()) {
                    flags = (short)(flags | 0x40);
                }
                if (lpart.isAbstract()) {
                    flags = (short)(flags | 0x80);
                }
                if (lpart instanceof SchemaGlobalElement) {
                    gpart = (SchemaGlobalElement)lpart;
                    if (gpart.finalExtension()) {
                        flags = (short)(flags | 0x100);
                    }
                    if (gpart.finalRestriction()) {
                        flags = (short)(flags | 0x200);
                    }
                }
            }
            this.writeShort(flags);
            this.writeBigInteger(part.getMinOccurs());
            this.writeBigInteger(part.getMaxOccurs());
            this.writeQNameSet(part.acceptedStartNames());
            switch (part.getParticleType()) {
                case 5: {
                    this.writeQNameSet(part.getWildcardSet());
                    this.writeShort(part.getWildcardProcess());
                    break;
                }
                case 4: {
                    lpart = (SchemaLocalElement)((Object)part);
                    this.writeQName(lpart.getName());
                    this.writeType(lpart.getType());
                    this.writeString(lpart.getDefaultText());
                    this.writeXmlValueObject(lpart.getDefaultValue());
                    this.writeSOAPArrayType(((SchemaWSDLArrayType)((Object)lpart)).getWSDLArrayType());
                    this.writeAnnotation(lpart.getAnnotation());
                    if (lpart instanceof SchemaGlobalElement) {
                        gpart = (SchemaGlobalElement)lpart;
                        this.writeHandle(gpart.substitutionGroup());
                        QName[] substGroupMembers = gpart.substitutionGroupMembers();
                        this.writeShort(substGroupMembers.length);
                        for (int i = 0; i < substGroupMembers.length; ++i) {
                            this.writeQName(substGroupMembers[i]);
                        }
                    }
                    SchemaIdentityConstraint[] idcs = lpart.getIdentityConstraints();
                    this.writeShort(idcs.length);
                    for (int i = 0; i < idcs.length; ++i) {
                        this.writeHandle(idcs[i]);
                    }
                    break;
                }
                case 1: 
                case 2: 
                case 3: {
                    this.writeParticleArray(part.getParticleChildren());
                    break;
                }
                default: {
                    throw new SchemaTypeLoaderException("Unrecognized particle type ", SchemaTypeSystemImpl.this._name, this._handle, 11);
                }
            }
        }

        SchemaProperty readPropertyData() {
            SchemaPropertyImpl prop = new SchemaPropertyImpl();
            prop.setName(this.readQName());
            prop.setTypeRef(this.readTypeRef());
            int propflags = this.readShort();
            prop.setAttribute((propflags & 1) != 0);
            prop.setContainerTypeRef(this.readTypeRef());
            prop.setMinOccurs(this.readBigInteger());
            prop.setMaxOccurs(this.readBigInteger());
            prop.setNillable(this.readShort());
            prop.setDefault(this.readShort());
            prop.setFixed(this.readShort());
            prop.setDefaultText(this.readString());
            prop.setJavaPropertyName(this.readString());
            prop.setJavaTypeCode(this.readShort());
            prop.setExtendsJava(this.readTypeRef(), (propflags & 2) != 0, (propflags & 4) != 0, (propflags & 8) != 0);
            if (this.atMost(2, 19, 0)) {
                prop.setJavaSetterDelimiter(this.readQNameSet());
            }
            if (this.atLeast(2, 16, 0)) {
                prop.setDefaultValue(this.readXmlValueObject());
            }
            if (!prop.isAttribute() && this.atLeast(2, 17, 0)) {
                int size = this.readShort();
                LinkedHashSet<QName> qnames = new LinkedHashSet<QName>(size);
                for (int i = 0; i < size; ++i) {
                    qnames.add(this.readQName());
                }
                prop.setAcceptedNames(qnames);
            }
            prop.setImmutable();
            return prop;
        }

        void writePropertyData(SchemaProperty prop) {
            this.writeQName(prop.getName());
            this.writeType(prop.getType());
            this.writeShort((prop.isAttribute() ? 1 : 0) | (prop.extendsJavaSingleton() ? 2 : 0) | (prop.extendsJavaOption() ? 4 : 0) | (prop.extendsJavaArray() ? 8 : 0));
            this.writeType(prop.getContainerType());
            this.writeBigInteger(prop.getMinOccurs());
            this.writeBigInteger(prop.getMaxOccurs());
            this.writeShort(prop.hasNillable());
            this.writeShort(prop.hasDefault());
            this.writeShort(prop.hasFixed());
            this.writeString(prop.getDefaultText());
            this.writeString(prop.getJavaPropertyName());
            this.writeShort(prop.getJavaTypeCode());
            this.writeType(prop.javaBasedOnType());
            this.writeXmlValueObject(prop.getDefaultValue());
            if (!prop.isAttribute()) {
                QName[] names = prop.acceptedNames();
                this.writeShort(names.length);
                for (int i = 0; i < names.length; ++i) {
                    this.writeQName(names[i]);
                }
            }
        }

        void writeModelGroupData(SchemaModelGroup grp) {
            SchemaModelGroupImpl impl = (SchemaModelGroupImpl)grp;
            this.writeQName(impl.getName());
            this.writeString(impl.getTargetNamespace());
            this.writeShort(impl.getChameleonNamespace() != null ? 1 : 0);
            this.writeString(impl.getElemFormDefault());
            this.writeString(impl.getAttFormDefault());
            this.writeShort(impl.isRedefinition() ? 1 : 0);
            this.writeString(impl.getParseObject().xmlText(new XmlOptions().setSaveOuter()));
            this.writeAnnotation(impl.getAnnotation());
            this.writeString(impl.getSourceName());
        }

        void writeAttributeGroupData(SchemaAttributeGroup grp) {
            SchemaAttributeGroupImpl impl = (SchemaAttributeGroupImpl)grp;
            this.writeQName(impl.getName());
            this.writeString(impl.getTargetNamespace());
            this.writeShort(impl.getChameleonNamespace() != null ? 1 : 0);
            this.writeString(impl.getFormDefault());
            this.writeShort(impl.isRedefinition() ? 1 : 0);
            this.writeString(impl.getParseObject().xmlText(new XmlOptions().setSaveOuter()));
            this.writeAnnotation(impl.getAnnotation());
            this.writeString(impl.getSourceName());
        }

        XmlValueRef readXmlValueObject() {
            SchemaType.Ref typeref = this.readTypeRef();
            if (typeref == null) {
                return null;
            }
            int btc = this.readShort();
            switch (btc) {
                default: {
                    assert (false);
                }
                case 0: {
                    return new XmlValueRef(typeref, null);
                }
                case 65535: {
                    int size = this.readShort();
                    ArrayList<XmlValueRef> values = new ArrayList<XmlValueRef>();
                    this.writeShort(values.size());
                    for (int i = 0; i < size; ++i) {
                        values.add(this.readXmlValueObject());
                    }
                    return new XmlValueRef(typeref, values);
                }
                case 2: 
                case 3: 
                case 6: 
                case 11: 
                case 12: 
                case 13: 
                case 14: 
                case 15: 
                case 16: 
                case 17: 
                case 18: 
                case 19: 
                case 20: 
                case 21: {
                    return new XmlValueRef(typeref, this.readString());
                }
                case 4: 
                case 5: {
                    return new XmlValueRef(typeref, this.readByteArray());
                }
                case 7: 
                case 8: {
                    return new XmlValueRef(typeref, this.readQName());
                }
                case 9: 
                case 10: 
            }
            return new XmlValueRef(typeref, new Double(this.readDouble()));
        }

        void writeXmlValueObject(XmlAnySimpleType value) {
            SchemaType type = value == null ? null : value.schemaType();
            this.writeType(type);
            if (type == null) {
                return;
            }
            SchemaType iType = ((SimpleValue)((Object)value)).instanceType();
            if (iType == null) {
                this.writeShort(0);
            } else if (iType.getSimpleVariety() == 3) {
                this.writeShort(-1);
                List values = ((XmlObjectBase)((Object)value)).xgetListValue();
                this.writeShort(values.size());
                Iterator i = values.iterator();
                while (i.hasNext()) {
                    this.writeXmlValueObject((XmlAnySimpleType)i.next());
                }
            } else {
                int btc = iType.getPrimitiveType().getBuiltinTypeCode();
                this.writeShort(btc);
                switch (btc) {
                    case 2: 
                    case 3: 
                    case 6: 
                    case 11: 
                    case 12: 
                    case 13: 
                    case 14: 
                    case 15: 
                    case 16: 
                    case 17: 
                    case 18: 
                    case 19: 
                    case 20: 
                    case 21: {
                        this.writeString(value.getStringValue());
                        break;
                    }
                    case 4: 
                    case 5: {
                        this.writeByteArray(((SimpleValue)((Object)value)).getByteArrayValue());
                        break;
                    }
                    case 7: 
                    case 8: {
                        this.writeQName(((SimpleValue)((Object)value)).getQNameValue());
                        break;
                    }
                    case 9: {
                        this.writeDouble(((SimpleValue)((Object)value)).getFloatValue());
                        break;
                    }
                    case 10: {
                        this.writeDouble(((SimpleValue)((Object)value)).getDoubleValue());
                    }
                }
            }
        }

        double readDouble() {
            try {
                return this._input.readDouble();
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage(), SchemaTypeSystemImpl.this._name, this._handle, 9);
            }
        }

        void writeDouble(double d) {
            if (this._output != null) {
                try {
                    this._output.writeDouble(d);
                } catch (IOException e) {
                    throw new SchemaTypeLoaderException(e.getMessage(), SchemaTypeSystemImpl.this._name, this._handle, 9);
                }
            }
        }

        QNameSet readQNameSet() {
            int flag = this.readShort();
            HashSet<String> uriSet = new HashSet<String>();
            int uriCount = this.readShort();
            for (int i = 0; i < uriCount; ++i) {
                uriSet.add(this.readString());
            }
            HashSet<QName> qnameSet1 = new HashSet<QName>();
            int qncount1 = this.readShort();
            for (int i = 0; i < qncount1; ++i) {
                qnameSet1.add(this.readQName());
            }
            HashSet<QName> qnameSet2 = new HashSet<QName>();
            int qncount2 = this.readShort();
            for (int i = 0; i < qncount2; ++i) {
                qnameSet2.add(this.readQName());
            }
            if (flag == 1) {
                return QNameSet.forSets(uriSet, null, qnameSet1, qnameSet2);
            }
            return QNameSet.forSets(null, uriSet, qnameSet2, qnameSet1);
        }

        void writeQNameSet(QNameSet set) {
            boolean invert = set.excludedURIs() != null;
            this.writeShort(invert ? 1 : 0);
            Set uriSet = invert ? set.excludedURIs() : set.includedURIs();
            this.writeShort(uriSet.size());
            Iterator i = uriSet.iterator();
            while (i.hasNext()) {
                this.writeString((String)i.next());
            }
            Set qnameSet1 = invert ? set.excludedQNamesInIncludedURIs() : set.includedQNamesInExcludedURIs();
            this.writeShort(qnameSet1.size());
            Iterator i2 = qnameSet1.iterator();
            while (i2.hasNext()) {
                this.writeQName((QName)i2.next());
            }
            Set qnameSet2 = invert ? set.includedQNamesInExcludedURIs() : set.excludedQNamesInIncludedURIs();
            this.writeShort(qnameSet2.size());
            Iterator i3 = qnameSet2.iterator();
            while (i3.hasNext()) {
                this.writeQName((QName)i3.next());
            }
        }

        byte[] readByteArray() {
            try {
                short len = this._input.readShort();
                byte[] result = new byte[len];
                this._input.readFully(result);
                return result;
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage(), SchemaTypeSystemImpl.this._name, this._handle, 9);
            }
        }

        void writeByteArray(byte[] ba) {
            try {
                this.writeShort(ba.length);
                if (this._output != null) {
                    this._output.write(ba);
                }
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage(), SchemaTypeSystemImpl.this._name, this._handle, 9);
            }
        }

        BigInteger readBigInteger() {
            byte[] result = this.readByteArray();
            if (result.length == 0) {
                return null;
            }
            if (result.length == 1 && result[0] == 0) {
                return BigInteger.ZERO;
            }
            if (result.length == 1 && result[0] == 1) {
                return BigInteger.ONE;
            }
            return new BigInteger(result);
        }

        void writeBigInteger(BigInteger bi) {
            if (bi == null) {
                this.writeShort(0);
            } else if (bi.signum() == 0) {
                this.writeByteArray(SINGLE_ZERO_BYTE);
            } else {
                this.writeByteArray(bi.toByteArray());
            }
        }
    }

    class HandlePool {
        private Map _handlesToRefs = new LinkedHashMap();
        private Map _componentsToHandles = new LinkedHashMap();
        private boolean _started;

        HandlePool() {
        }

        private String addUniqueHandle(SchemaComponent obj, String base) {
            String handle = base = base.toLowerCase();
            int index = 2;
            while (this._handlesToRefs.containsKey(handle)) {
                handle = base + index;
                ++index;
            }
            this._handlesToRefs.put(handle, obj.getComponentRef());
            this._componentsToHandles.put(obj, handle);
            return handle;
        }

        String handleForComponent(SchemaComponent comp) {
            if (comp == null) {
                return null;
            }
            if (comp.getTypeSystem() != SchemaTypeSystemImpl.this.getTypeSystem()) {
                throw new IllegalArgumentException("Cannot supply handles for types from another type system");
            }
            if (comp instanceof SchemaType) {
                return this.handleForType((SchemaType)comp);
            }
            if (comp instanceof SchemaGlobalElement) {
                return this.handleForElement((SchemaGlobalElement)comp);
            }
            if (comp instanceof SchemaGlobalAttribute) {
                return this.handleForAttribute((SchemaGlobalAttribute)comp);
            }
            if (comp instanceof SchemaModelGroup) {
                return this.handleForModelGroup((SchemaModelGroup)comp);
            }
            if (comp instanceof SchemaAttributeGroup) {
                return this.handleForAttributeGroup((SchemaAttributeGroup)comp);
            }
            if (comp instanceof SchemaIdentityConstraint) {
                return this.handleForIdentityConstraint((SchemaIdentityConstraint)comp);
            }
            throw new IllegalStateException("Component type cannot have a handle");
        }

        String handleForElement(SchemaGlobalElement element) {
            if (element == null) {
                return null;
            }
            if (element.getTypeSystem() != SchemaTypeSystemImpl.this.getTypeSystem()) {
                throw new IllegalArgumentException("Cannot supply handles for types from another type system");
            }
            String handle = (String)this._componentsToHandles.get(element);
            if (handle == null) {
                handle = this.addUniqueHandle(element, NameUtil.upperCamelCase(element.getName().getLocalPart()) + "Element");
            }
            return handle;
        }

        String handleForAttribute(SchemaGlobalAttribute attribute) {
            if (attribute == null) {
                return null;
            }
            if (attribute.getTypeSystem() != SchemaTypeSystemImpl.this.getTypeSystem()) {
                throw new IllegalArgumentException("Cannot supply handles for types from another type system");
            }
            String handle = (String)this._componentsToHandles.get(attribute);
            if (handle == null) {
                handle = this.addUniqueHandle(attribute, NameUtil.upperCamelCase(attribute.getName().getLocalPart()) + "Attribute");
            }
            return handle;
        }

        String handleForModelGroup(SchemaModelGroup group) {
            if (group == null) {
                return null;
            }
            if (group.getTypeSystem() != SchemaTypeSystemImpl.this.getTypeSystem()) {
                throw new IllegalArgumentException("Cannot supply handles for types from another type system");
            }
            String handle = (String)this._componentsToHandles.get(group);
            if (handle == null) {
                handle = this.addUniqueHandle(group, NameUtil.upperCamelCase(group.getName().getLocalPart()) + "ModelGroup");
            }
            return handle;
        }

        String handleForAttributeGroup(SchemaAttributeGroup group) {
            if (group == null) {
                return null;
            }
            if (group.getTypeSystem() != SchemaTypeSystemImpl.this.getTypeSystem()) {
                throw new IllegalArgumentException("Cannot supply handles for types from another type system");
            }
            String handle = (String)this._componentsToHandles.get(group);
            if (handle == null) {
                handle = this.addUniqueHandle(group, NameUtil.upperCamelCase(group.getName().getLocalPart()) + "AttributeGroup");
            }
            return handle;
        }

        String handleForIdentityConstraint(SchemaIdentityConstraint idc) {
            if (idc == null) {
                return null;
            }
            if (idc.getTypeSystem() != SchemaTypeSystemImpl.this.getTypeSystem()) {
                throw new IllegalArgumentException("Cannot supply handles for types from another type system");
            }
            String handle = (String)this._componentsToHandles.get(idc);
            if (handle == null) {
                handle = this.addUniqueHandle(idc, NameUtil.upperCamelCase(idc.getName().getLocalPart()) + "IdentityConstraint");
            }
            return handle;
        }

        String handleForType(SchemaType type) {
            if (type == null) {
                return null;
            }
            if (type.getTypeSystem() != SchemaTypeSystemImpl.this.getTypeSystem()) {
                throw new IllegalArgumentException("Cannot supply handles for types from another type system");
            }
            String handle = (String)this._componentsToHandles.get(type);
            if (handle == null) {
                QName name = type.getName();
                String suffix = "";
                if (name == null) {
                    if (type.isDocumentType()) {
                        name = type.getDocumentElementName();
                        suffix = "Doc";
                    } else if (type.isAttributeType()) {
                        name = type.getAttributeTypeAttributeName();
                        suffix = "AttrType";
                    } else if (type.getContainerField() != null) {
                        name = type.getContainerField().getName();
                        suffix = type.getContainerField().isAttribute() ? "Attr" : "Elem";
                    }
                }
                String uniq = Integer.toHexString(type.toString().hashCode() | Integer.MIN_VALUE).substring(4).toUpperCase();
                String baseName = name == null ? "Anon" + uniq + "Type" : NameUtil.upperCamelCase(name.getLocalPart()) + uniq + suffix + "Type";
                handle = this.addUniqueHandle(type, baseName);
            }
            return handle;
        }

        SchemaComponent.Ref refForHandle(String handle) {
            if (handle == null) {
                return null;
            }
            return (SchemaComponent.Ref)this._handlesToRefs.get(handle);
        }

        Set getAllHandles() {
            return this._handlesToRefs.keySet();
        }

        void startWriteMode() {
            this._started = true;
            this._componentsToHandles = new LinkedHashMap();
            for (String handle : this._handlesToRefs.keySet()) {
                SchemaComponent comp = ((SchemaComponent.Ref)this._handlesToRefs.get(handle)).getComponent();
                this._componentsToHandles.put(comp, handle);
            }
        }
    }

    static class StringPool {
        private List intsToStrings = new ArrayList();
        private Map stringsToInts = new HashMap();
        private String _handle;
        private String _name;

        StringPool(String handle, String name) {
            this._handle = handle;
            this._name = name;
            this.intsToStrings.add(null);
        }

        int codeForString(String str) {
            if (str == null) {
                return 0;
            }
            Integer result = (Integer)this.stringsToInts.get(str);
            if (result == null) {
                result = new Integer(this.intsToStrings.size());
                this.intsToStrings.add(str);
                this.stringsToInts.put(str, result);
            }
            return result;
        }

        String stringForCode(int code) {
            if (code == 0) {
                return null;
            }
            return (String)this.intsToStrings.get(code);
        }

        void writeTo(DataOutputStream output) {
            if (this.intsToStrings.size() >= 65535) {
                throw new SchemaTypeLoaderException("Too many strings (" + this.intsToStrings.size() + ")", this._name, this._handle, 10);
            }
            try {
                output.writeShort(this.intsToStrings.size());
                Iterator i = this.intsToStrings.iterator();
                i.next();
                while (i.hasNext()) {
                    String str = (String)i.next();
                    output.writeUTF(str);
                }
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage(), this._name, this._handle, 9);
            }
        }

        void readFrom(DataInputStream input) {
            if (this.intsToStrings.size() != 1 || this.stringsToInts.size() != 0) {
                throw new IllegalStateException();
            }
            try {
                int size = input.readUnsignedShort();
                for (int i = 1; i < size; ++i) {
                    String str = input.readUTF().intern();
                    int code = this.codeForString(str);
                    if (code == i) continue;
                    throw new IllegalStateException();
                }
            } catch (IOException e) {
                throw new SchemaTypeLoaderException(e.getMessage() == null ? e.getMessage() : "IO Exception", this._name, this._handle, 9, e);
            }
        }
    }
}

