/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.BindingConfig;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SystemProperties;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ResolverUtil;
import org.apache.xmlbeans.impl.schema.SchemaAnnotationImpl;
import org.apache.xmlbeans.impl.schema.SchemaAttributeGroupImpl;
import org.apache.xmlbeans.impl.schema.SchemaContainer;
import org.apache.xmlbeans.impl.schema.SchemaDependencies;
import org.apache.xmlbeans.impl.schema.SchemaGlobalAttributeImpl;
import org.apache.xmlbeans.impl.schema.SchemaGlobalElementImpl;
import org.apache.xmlbeans.impl.schema.SchemaIdentityConstraintImpl;
import org.apache.xmlbeans.impl.schema.SchemaModelGroupImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl;
import org.apache.xmlbeans.impl.schema.XmlValueRef;
import org.apache.xmlbeans.impl.util.HexBin;
import org.apache.xmlbeans.impl.values.XmlStringImpl;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.xml.sax.EntityResolver;

public class StscState {
    private String _givenStsName;
    private Collection _errorListener;
    private SchemaTypeSystemImpl _target;
    private BindingConfig _config;
    private Map _compatMap;
    private boolean _doingDownloads;
    private byte[] _digest = null;
    private boolean _noDigest = false;
    private boolean _allowPartial = false;
    private int _recoveredErrors = 0;
    private SchemaTypeLoader _importingLoader;
    private Map _containers = new LinkedHashMap();
    private SchemaDependencies _dependencies;
    private Map _redefinedGlobalTypes = new LinkedHashMap();
    private Map _redefinedModelGroups = new LinkedHashMap();
    private Map _redefinedAttributeGroups = new LinkedHashMap();
    private Map _globalTypes = new LinkedHashMap();
    private Map _globalElements = new LinkedHashMap();
    private Map _globalAttributes = new LinkedHashMap();
    private Map _modelGroups = new LinkedHashMap();
    private Map _attributeGroups = new LinkedHashMap();
    private Map _documentTypes = new LinkedHashMap();
    private Map _attributeTypes = new LinkedHashMap();
    private Map _typesByClassname = new LinkedHashMap();
    private Map _misspelledNames = new HashMap();
    private Set _processingGroups = new LinkedHashSet();
    private Map _idConstraints = new LinkedHashMap();
    private Set _namespaces = new HashSet();
    private List _annotations = new ArrayList();
    private boolean _noUpa;
    private boolean _noPvr;
    private boolean _noAnn;
    private boolean _mdefAll;
    private Set _mdefNamespaces = StscState.buildDefaultMdefNamespaces();
    private EntityResolver _entityResolver;
    private File _schemasDir;
    public static final Object CHAMELEON_INCLUDE_URI = new Object();
    private static ThreadLocal tl_stscStack = new ThreadLocal();
    private static final XmlValueRef XMLSTR_PRESERVE = StscState.buildString("preserve");
    private static final XmlValueRef XMLSTR_REPLACE = StscState.buildString("preserve");
    private static final XmlValueRef XMLSTR_COLLAPSE = StscState.buildString("preserve");
    static final SchemaType[] EMPTY_ST_ARRAY = new SchemaType[0];
    static final SchemaType.Ref[] EMPTY_STREF_ARRAY = new SchemaType.Ref[0];
    private static final XmlValueRef[] FACETS_NONE = new XmlValueRef[]{null, null, null, null, null, null, null, null, null, null, null, null};
    private static final boolean[] FIXED_FACETS_NONE = new boolean[]{false, false, false, false, false, false, false, false, false, false, false, false};
    private static final XmlValueRef[] FACETS_WS_COLLAPSE = new XmlValueRef[]{null, null, null, null, null, null, null, null, null, StscState.build_wsstring(3), null, null};
    private static final boolean[] FIXED_FACETS_WS = new boolean[]{false, false, false, false, false, false, false, false, false, true, false, false};
    static final XmlValueRef[] FACETS_UNION = FACETS_NONE;
    static final boolean[] FIXED_FACETS_UNION = FIXED_FACETS_NONE;
    static final XmlValueRef[] FACETS_LIST = FACETS_WS_COLLAPSE;
    static final boolean[] FIXED_FACETS_LIST = FIXED_FACETS_WS;
    private static final String PROJECT_URL_PREFIX = "project://local";
    Map _sourceForUri = new HashMap();
    URI _baseURI = URI.create("project://local/");
    SchemaTypeLoader _s4sloader = XmlBeans.typeLoaderForClassLoader(SchemaDocument.class.getClassLoader());

    private static Set buildDefaultMdefNamespaces() {
        return new HashSet<String>(Arrays.asList("http://www.openuri.org/2002/04/soap/conversation/"));
    }

    private StscState() {
    }

    public void initFromTypeSystem(SchemaTypeSystemImpl system, Set newNamespaces) {
        SchemaContainer[] containers = system.containers();
        for (int i = 0; i < containers.length; ++i) {
            if (newNamespaces.contains(containers[i].getNamespace())) continue;
            this.addContainer(containers[i]);
        }
    }

    void addNewContainer(String namespace) {
        if (this._containers.containsKey(namespace)) {
            return;
        }
        SchemaContainer container = new SchemaContainer(namespace);
        container.setTypeSystem(this.sts());
        this.addNamespace(namespace);
        this._containers.put(namespace, container);
    }

    private void addContainer(SchemaContainer container) {
        QName name;
        this._containers.put(container.getNamespace(), container);
        List redefModelGroups = container.redefinedModelGroups();
        for (int i = 0; i < redefModelGroups.size(); ++i) {
            QName name2 = ((SchemaModelGroup)redefModelGroups.get(i)).getName();
            this._redefinedModelGroups.put(name2, redefModelGroups.get(i));
        }
        List redefAttrGroups = container.redefinedAttributeGroups();
        for (int i = 0; i < redefAttrGroups.size(); ++i) {
            QName name3 = ((SchemaAttributeGroup)redefAttrGroups.get(i)).getName();
            this._redefinedAttributeGroups.put(name3, redefAttrGroups.get(i));
        }
        List redefTypes = container.redefinedGlobalTypes();
        for (int i = 0; i < redefTypes.size(); ++i) {
            QName name4 = ((SchemaType)redefTypes.get(i)).getName();
            this._redefinedGlobalTypes.put(name4, redefTypes.get(i));
        }
        List globalElems = container.globalElements();
        for (int i = 0; i < globalElems.size(); ++i) {
            QName name5 = ((SchemaGlobalElement)globalElems.get(i)).getName();
            this._globalElements.put(name5, globalElems.get(i));
        }
        List globalAtts = container.globalAttributes();
        for (int i = 0; i < globalAtts.size(); ++i) {
            QName name6 = ((SchemaGlobalAttribute)globalAtts.get(i)).getName();
            this._globalAttributes.put(name6, globalAtts.get(i));
        }
        List modelGroups = container.modelGroups();
        for (int i = 0; i < modelGroups.size(); ++i) {
            QName name7 = ((SchemaModelGroup)modelGroups.get(i)).getName();
            this._modelGroups.put(name7, modelGroups.get(i));
        }
        List attrGroups = container.attributeGroups();
        for (int i = 0; i < attrGroups.size(); ++i) {
            QName name8 = ((SchemaAttributeGroup)attrGroups.get(i)).getName();
            this._attributeGroups.put(name8, attrGroups.get(i));
        }
        List globalTypes = container.globalTypes();
        for (int i = 0; i < globalTypes.size(); ++i) {
            SchemaType t = (SchemaType)globalTypes.get(i);
            QName name9 = t.getName();
            this._globalTypes.put(name9, t);
            if (t.getFullJavaName() == null) continue;
            this.addClassname(t.getFullJavaName(), t);
        }
        List documentTypes = container.documentTypes();
        for (int i = 0; i < documentTypes.size(); ++i) {
            SchemaType t = (SchemaType)documentTypes.get(i);
            QName name10 = t.getProperties()[0].getName();
            this._documentTypes.put(name10, t);
            if (t.getFullJavaName() == null) continue;
            this.addClassname(t.getFullJavaName(), t);
        }
        List attributeTypes = container.attributeTypes();
        for (int i = 0; i < attributeTypes.size(); ++i) {
            SchemaType t = (SchemaType)attributeTypes.get(i);
            name = t.getProperties()[0].getName();
            this._attributeTypes.put(name, t);
            if (t.getFullJavaName() == null) continue;
            this.addClassname(t.getFullJavaName(), t);
        }
        List identityConstraints = container.identityConstraints();
        for (int i = 0; i < identityConstraints.size(); ++i) {
            name = ((SchemaIdentityConstraint)identityConstraints.get(i)).getName();
            this._idConstraints.put(name, identityConstraints.get(i));
        }
        this._annotations.addAll(container.annotations());
        this._namespaces.add(container.getNamespace());
        container.unsetImmutable();
    }

    SchemaContainer getContainer(String namespace) {
        return (SchemaContainer)this._containers.get(namespace);
    }

    Map getContainerMap() {
        return Collections.unmodifiableMap(this._containers);
    }

    void registerDependency(String sourceNs, String targetNs) {
        this._dependencies.registerDependency(sourceNs, targetNs);
    }

    void registerContribution(String ns, String fileUrl) {
        this._dependencies.registerContribution(ns, fileUrl);
    }

    SchemaDependencies getDependencies() {
        return this._dependencies;
    }

    void setDependencies(SchemaDependencies deps) {
        this._dependencies = deps;
    }

    boolean isFileProcessed(String url) {
        return this._dependencies.isFileRepresented(url);
    }

    public void setImportingTypeLoader(SchemaTypeLoader loader) {
        this._importingLoader = loader;
    }

    public void setErrorListener(Collection errorListener) {
        this._errorListener = errorListener;
    }

    public void error(String message, int code, XmlObject loc) {
        StscState.addError(this._errorListener, message, code, loc);
    }

    public void error(String code, Object[] args, XmlObject loc) {
        StscState.addError(this._errorListener, code, args, loc);
    }

    public void recover(String code, Object[] args, XmlObject loc) {
        StscState.addError(this._errorListener, code, args, loc);
        ++this._recoveredErrors;
    }

    public void warning(String message, int code, XmlObject loc) {
        StscState.addWarning(this._errorListener, message, code, loc);
    }

    public void warning(String code, Object[] args, XmlObject loc) {
        if (code == "reserved-type-name" && loc.documentProperties().getSourceName() != null && loc.documentProperties().getSourceName().indexOf("XMLSchema.xsd") > 0) {
            return;
        }
        StscState.addWarning(this._errorListener, code, args, loc);
    }

    public void info(String message) {
        StscState.addInfo(this._errorListener, message);
    }

    public void info(String code, Object[] args) {
        StscState.addInfo(this._errorListener, code, args);
    }

    public static void addError(Collection errorListener, String message, int code, XmlObject location) {
        XmlError err = XmlError.forObject(message, 0, location);
        errorListener.add(err);
    }

    public static void addError(Collection errorListener, String code, Object[] args, XmlObject location) {
        XmlError err = XmlError.forObject(code, args, 0, location);
        errorListener.add(err);
    }

    public static void addError(Collection errorListener, String code, Object[] args, File location) {
        XmlError err = XmlError.forLocation(code, args, 0, location.toURI().toString(), 0, 0, 0);
        errorListener.add(err);
    }

    public static void addError(Collection errorListener, String code, Object[] args, URL location) {
        XmlError err = XmlError.forLocation(code, args, 0, location.toString(), 0, 0, 0);
        errorListener.add(err);
    }

    public static void addWarning(Collection errorListener, String message, int code, XmlObject location) {
        XmlError err = XmlError.forObject(message, 1, location);
        errorListener.add(err);
    }

    public static void addWarning(Collection errorListener, String code, Object[] args, XmlObject location) {
        XmlError err = XmlError.forObject(code, args, 1, location);
        errorListener.add(err);
    }

    public static void addInfo(Collection errorListener, String message) {
        XmlError err = XmlError.forMessage(message, 2);
        errorListener.add(err);
    }

    public static void addInfo(Collection errorListener, String code, Object[] args) {
        XmlError err = XmlError.forMessage(code, args, 2);
        errorListener.add(err);
    }

    public void setGivenTypeSystemName(String name) {
        this._givenStsName = name;
    }

    public void setTargetSchemaTypeSystem(SchemaTypeSystemImpl target) {
        this._target = target;
    }

    public void addSchemaDigest(byte[] digest) {
        int len;
        if (this._noDigest) {
            return;
        }
        if (digest == null) {
            this._noDigest = true;
            this._digest = null;
            return;
        }
        if (this._digest == null) {
            this._digest = new byte[16];
        }
        if (digest.length < (len = this._digest.length)) {
            len = digest.length;
        }
        for (int i = 0; i < len; ++i) {
            int n = i;
            this._digest[n] = (byte)(this._digest[n] ^ digest[i]);
        }
    }

    public SchemaTypeSystemImpl sts() {
        if (this._target != null) {
            return this._target;
        }
        String name = this._givenStsName;
        if (name == null && this._digest != null) {
            name = "s" + new String(HexBin.encode(this._digest));
        }
        this._target = new SchemaTypeSystemImpl(name);
        return this._target;
    }

    public boolean shouldDownloadURI(String uriString) {
        if (this._doingDownloads) {
            return true;
        }
        if (uriString == null) {
            return false;
        }
        try {
            URI uri = new URI(uriString);
            if (uri.getScheme().equalsIgnoreCase("jar") || uri.getScheme().equalsIgnoreCase("zip")) {
                String s = uri.getSchemeSpecificPart();
                int i = s.lastIndexOf(33);
                return this.shouldDownloadURI(i > 0 ? s.substring(0, i) : s);
            }
            return uri.getScheme().equalsIgnoreCase("file");
        } catch (Exception e) {
            return false;
        }
    }

    public void setOptions(XmlOptions options) {
        if (options == null) {
            return;
        }
        this._allowPartial = options.hasOption("COMPILE_PARTIAL_TYPESYSTEM");
        this._compatMap = (Map)options.get("COMPILE_SUBSTITUTE_NAMES");
        boolean bl = options.hasOption("COMPILE_NO_UPA_RULE") ? true : (this._noUpa = !"true".equals(SystemProperties.getProperty("xmlbean.uniqueparticleattribution", "true")));
        boolean bl2 = options.hasOption("COMPILE_NO_PVR_RULE") ? true : (this._noPvr = !"true".equals(SystemProperties.getProperty("xmlbean.particlerestriction", "true")));
        this._noAnn = options.hasOption("COMPILE_NO_ANNOTATIONS") ? true : !"true".equals(SystemProperties.getProperty("xmlbean.schemaannotations", "true"));
        this._doingDownloads = options.hasOption("COMPILE_DOWNLOAD_URLS") ? true : "true".equals(SystemProperties.getProperty("xmlbean.downloadurls", "false"));
        this._entityResolver = (EntityResolver)options.get("ENTITY_RESOLVER");
        if (this._entityResolver == null) {
            this._entityResolver = ResolverUtil.getGlobalEntityResolver();
        }
        if (this._entityResolver != null) {
            this._doingDownloads = true;
        }
        if (options.hasOption("COMPILE_MDEF_NAMESPACES")) {
            this._mdefNamespaces.addAll((Collection)options.get("COMPILE_MDEF_NAMESPACES"));
            String local = "##local";
            String any = "##any";
            if (this._mdefNamespaces.contains(local)) {
                this._mdefNamespaces.remove(local);
                this._mdefNamespaces.add("");
            }
            if (this._mdefNamespaces.contains(any)) {
                this._mdefNamespaces.remove(any);
                this._mdefAll = true;
            }
        }
    }

    public EntityResolver getEntityResolver() {
        return this._entityResolver;
    }

    public boolean noUpa() {
        return this._noUpa;
    }

    public boolean noPvr() {
        return this._noPvr;
    }

    public boolean noAnn() {
        return this._noAnn;
    }

    public boolean allowPartial() {
        return this._allowPartial;
    }

    public int getRecovered() {
        return this._recoveredErrors;
    }

    private QName compatName(QName name, String chameleonNamespace) {
        if (name.getNamespaceURI().length() == 0 && chameleonNamespace != null && chameleonNamespace.length() > 0) {
            name = new QName(chameleonNamespace, name.getLocalPart());
        }
        if (this._compatMap == null) {
            return name;
        }
        QName subst = (QName)this._compatMap.get(name);
        if (subst == null) {
            return name;
        }
        return subst;
    }

    public void setBindingConfig(BindingConfig config) throws IllegalArgumentException {
        this._config = config;
    }

    public BindingConfig getBindingConfig() throws IllegalArgumentException {
        return this._config;
    }

    public String getPackageOverride(String namespace) {
        if (this._config == null) {
            return null;
        }
        return this._config.lookupPackageForNamespace(namespace);
    }

    public String getJavaPrefix(String namespace) {
        if (this._config == null) {
            return null;
        }
        return this._config.lookupPrefixForNamespace(namespace);
    }

    public String getJavaSuffix(String namespace) {
        if (this._config == null) {
            return null;
        }
        return this._config.lookupSuffixForNamespace(namespace);
    }

    public String getJavaname(QName qname, int kind) {
        if (this._config == null) {
            return null;
        }
        return this._config.lookupJavanameForQName(qname, kind);
    }

    private static String crunchName(QName name) {
        return name.getLocalPart().toLowerCase();
    }

    void addSpelling(QName name, SchemaComponent comp) {
        this._misspelledNames.put(StscState.crunchName(name), comp);
    }

    SchemaComponent findSpelling(QName name) {
        return (SchemaComponent)this._misspelledNames.get(StscState.crunchName(name));
    }

    void addNamespace(String targetNamespace) {
        this._namespaces.add(targetNamespace);
    }

    String[] getNamespaces() {
        return this._namespaces.toArray(new String[this._namespaces.size()]);
    }

    boolean linkerDefinesNamespace(String namespace) {
        return this._importingLoader.isNamespaceDefined(namespace);
    }

    SchemaTypeImpl findGlobalType(QName name, String chameleonNamespace, String sourceNamespace) {
        name = this.compatName(name, chameleonNamespace);
        SchemaTypeImpl result = (SchemaTypeImpl)this._globalTypes.get(name);
        boolean foundOnLoader = false;
        if (result == null) {
            result = (SchemaTypeImpl)this._importingLoader.findType(name);
            boolean bl = foundOnLoader = result != null;
        }
        if (!foundOnLoader && sourceNamespace != null) {
            this.registerDependency(sourceNamespace, name.getNamespaceURI());
        }
        return result;
    }

    SchemaTypeImpl findRedefinedGlobalType(QName name, String chameleonNamespace, SchemaTypeImpl redefinedBy) {
        QName redefinedName = redefinedBy.getName();
        if ((name = this.compatName(name, chameleonNamespace)).equals(redefinedName)) {
            return (SchemaTypeImpl)this._redefinedGlobalTypes.get(redefinedBy);
        }
        SchemaTypeImpl result = (SchemaTypeImpl)this._globalTypes.get(name);
        if (result == null) {
            result = (SchemaTypeImpl)this._importingLoader.findType(name);
        }
        return result;
    }

    void addGlobalType(SchemaTypeImpl type, SchemaTypeImpl redefined) {
        if (type != null) {
            QName name = type.getName();
            SchemaContainer container = this.getContainer(name.getNamespaceURI());
            assert (container != null && container == type.getContainer());
            if (redefined != null) {
                if (this._redefinedGlobalTypes.containsKey(redefined)) {
                    if (!this.ignoreMdef(name)) {
                        if (this._mdefAll) {
                            this.warning("sch-props-correct.2", new Object[]{"global type", QNameHelper.pretty(name), ((SchemaType)this._redefinedGlobalTypes.get(redefined)).getSourceName()}, type.getParseObject());
                        } else {
                            this.error("sch-props-correct.2", new Object[]{"global type", QNameHelper.pretty(name), ((SchemaType)this._redefinedGlobalTypes.get(redefined)).getSourceName()}, type.getParseObject());
                        }
                    }
                } else {
                    this._redefinedGlobalTypes.put(redefined, type);
                    container.addRedefinedType(type.getRef());
                }
            } else if (this._globalTypes.containsKey(name)) {
                if (!this.ignoreMdef(name)) {
                    if (this._mdefAll) {
                        this.warning("sch-props-correct.2", new Object[]{"global type", QNameHelper.pretty(name), ((SchemaType)this._globalTypes.get(name)).getSourceName()}, type.getParseObject());
                    } else {
                        this.error("sch-props-correct.2", new Object[]{"global type", QNameHelper.pretty(name), ((SchemaType)this._globalTypes.get(name)).getSourceName()}, type.getParseObject());
                    }
                }
            } else {
                this._globalTypes.put(name, type);
                container.addGlobalType(type.getRef());
                this.addSpelling(name, type);
            }
        }
    }

    private boolean ignoreMdef(QName name) {
        return this._mdefNamespaces.contains(name.getNamespaceURI());
    }

    SchemaType[] globalTypes() {
        return this._globalTypes.values().toArray(new SchemaType[this._globalTypes.size()]);
    }

    SchemaType[] redefinedGlobalTypes() {
        return this._redefinedGlobalTypes.values().toArray(new SchemaType[this._redefinedGlobalTypes.size()]);
    }

    SchemaTypeImpl findDocumentType(QName name, String chameleonNamespace, String sourceNamespace) {
        name = this.compatName(name, chameleonNamespace);
        SchemaTypeImpl result = (SchemaTypeImpl)this._documentTypes.get(name);
        boolean foundOnLoader = false;
        if (result == null) {
            result = (SchemaTypeImpl)this._importingLoader.findDocumentType(name);
            boolean bl = foundOnLoader = result != null;
        }
        if (!foundOnLoader && sourceNamespace != null) {
            this.registerDependency(sourceNamespace, name.getNamespaceURI());
        }
        return result;
    }

    void addDocumentType(SchemaTypeImpl type, QName name) {
        if (this._documentTypes.containsKey(name)) {
            if (!this.ignoreMdef(name)) {
                if (this._mdefAll) {
                    this.warning("sch-props-correct.2", new Object[]{"global element", QNameHelper.pretty(name), ((SchemaComponent)this._documentTypes.get(name)).getSourceName()}, type.getParseObject());
                } else {
                    this.error("sch-props-correct.2", new Object[]{"global element", QNameHelper.pretty(name), ((SchemaComponent)this._documentTypes.get(name)).getSourceName()}, type.getParseObject());
                }
            }
        } else {
            this._documentTypes.put(name, type);
            SchemaContainer container = this.getContainer(name.getNamespaceURI());
            assert (container != null && container == type.getContainer());
            container.addDocumentType(type.getRef());
        }
    }

    SchemaType[] documentTypes() {
        return this._documentTypes.values().toArray(new SchemaType[this._documentTypes.size()]);
    }

    SchemaTypeImpl findAttributeType(QName name, String chameleonNamespace, String sourceNamespace) {
        name = this.compatName(name, chameleonNamespace);
        SchemaTypeImpl result = (SchemaTypeImpl)this._attributeTypes.get(name);
        boolean foundOnLoader = false;
        if (result == null) {
            result = (SchemaTypeImpl)this._importingLoader.findAttributeType(name);
            boolean bl = foundOnLoader = result != null;
        }
        if (!foundOnLoader && sourceNamespace != null) {
            this.registerDependency(sourceNamespace, name.getNamespaceURI());
        }
        return result;
    }

    void addAttributeType(SchemaTypeImpl type, QName name) {
        if (this._attributeTypes.containsKey(name)) {
            if (!this.ignoreMdef(name)) {
                if (this._mdefAll) {
                    this.warning("sch-props-correct.2", new Object[]{"global attribute", QNameHelper.pretty(name), ((SchemaComponent)this._attributeTypes.get(name)).getSourceName()}, type.getParseObject());
                } else {
                    this.error("sch-props-correct.2", new Object[]{"global attribute", QNameHelper.pretty(name), ((SchemaComponent)this._attributeTypes.get(name)).getSourceName()}, type.getParseObject());
                }
            }
        } else {
            this._attributeTypes.put(name, type);
            SchemaContainer container = this.getContainer(name.getNamespaceURI());
            assert (container != null && container == type.getContainer());
            container.addAttributeType(type.getRef());
        }
    }

    SchemaType[] attributeTypes() {
        return this._attributeTypes.values().toArray(new SchemaType[this._attributeTypes.size()]);
    }

    SchemaGlobalAttributeImpl findGlobalAttribute(QName name, String chameleonNamespace, String sourceNamespace) {
        name = this.compatName(name, chameleonNamespace);
        SchemaGlobalAttributeImpl result = (SchemaGlobalAttributeImpl)this._globalAttributes.get(name);
        boolean foundOnLoader = false;
        if (result == null) {
            result = (SchemaGlobalAttributeImpl)this._importingLoader.findAttribute(name);
            boolean bl = foundOnLoader = result != null;
        }
        if (!foundOnLoader && sourceNamespace != null) {
            this.registerDependency(sourceNamespace, name.getNamespaceURI());
        }
        return result;
    }

    void addGlobalAttribute(SchemaGlobalAttributeImpl attribute) {
        if (attribute != null) {
            QName name = attribute.getName();
            this._globalAttributes.put(name, attribute);
            this.addSpelling(name, attribute);
            SchemaContainer container = this.getContainer(name.getNamespaceURI());
            assert (container != null && container == attribute.getContainer());
            container.addGlobalAttribute(attribute.getRef());
        }
    }

    SchemaGlobalAttribute[] globalAttributes() {
        return this._globalAttributes.values().toArray(new SchemaGlobalAttribute[this._globalAttributes.size()]);
    }

    SchemaGlobalElementImpl findGlobalElement(QName name, String chameleonNamespace, String sourceNamespace) {
        name = this.compatName(name, chameleonNamespace);
        SchemaGlobalElementImpl result = (SchemaGlobalElementImpl)this._globalElements.get(name);
        boolean foundOnLoader = false;
        if (result == null) {
            result = (SchemaGlobalElementImpl)this._importingLoader.findElement(name);
            boolean bl = foundOnLoader = result != null;
        }
        if (!foundOnLoader && sourceNamespace != null) {
            this.registerDependency(sourceNamespace, name.getNamespaceURI());
        }
        return result;
    }

    void addGlobalElement(SchemaGlobalElementImpl element) {
        if (element != null) {
            QName name = element.getName();
            this._globalElements.put(name, element);
            SchemaContainer container = this.getContainer(name.getNamespaceURI());
            assert (container != null && container == element.getContainer());
            container.addGlobalElement(element.getRef());
            this.addSpelling(name, element);
        }
    }

    SchemaGlobalElement[] globalElements() {
        return this._globalElements.values().toArray(new SchemaGlobalElement[this._globalElements.size()]);
    }

    SchemaAttributeGroupImpl findAttributeGroup(QName name, String chameleonNamespace, String sourceNamespace) {
        name = this.compatName(name, chameleonNamespace);
        SchemaAttributeGroupImpl result = (SchemaAttributeGroupImpl)this._attributeGroups.get(name);
        boolean foundOnLoader = false;
        if (result == null) {
            result = (SchemaAttributeGroupImpl)this._importingLoader.findAttributeGroup(name);
            boolean bl = foundOnLoader = result != null;
        }
        if (!foundOnLoader && sourceNamespace != null) {
            this.registerDependency(sourceNamespace, name.getNamespaceURI());
        }
        return result;
    }

    SchemaAttributeGroupImpl findRedefinedAttributeGroup(QName name, String chameleonNamespace, SchemaAttributeGroupImpl redefinedBy) {
        QName redefinitionFor = redefinedBy.getName();
        if ((name = this.compatName(name, chameleonNamespace)).equals(redefinitionFor)) {
            return (SchemaAttributeGroupImpl)this._redefinedAttributeGroups.get(redefinedBy);
        }
        SchemaAttributeGroupImpl result = (SchemaAttributeGroupImpl)this._attributeGroups.get(name);
        if (result == null) {
            result = (SchemaAttributeGroupImpl)this._importingLoader.findAttributeGroup(name);
        }
        return result;
    }

    void addAttributeGroup(SchemaAttributeGroupImpl attributeGroup, SchemaAttributeGroupImpl redefined) {
        if (attributeGroup != null) {
            QName name = attributeGroup.getName();
            SchemaContainer container = this.getContainer(name.getNamespaceURI());
            assert (container != null && container == attributeGroup.getContainer());
            if (redefined != null) {
                if (this._redefinedAttributeGroups.containsKey(redefined)) {
                    if (!this.ignoreMdef(name)) {
                        if (this._mdefAll) {
                            this.warning("sch-props-correct.2", new Object[]{"attribute group", QNameHelper.pretty(name), ((SchemaComponent)this._redefinedAttributeGroups.get(redefined)).getSourceName()}, attributeGroup.getParseObject());
                        } else {
                            this.error("sch-props-correct.2", new Object[]{"attribute group", QNameHelper.pretty(name), ((SchemaComponent)this._redefinedAttributeGroups.get(redefined)).getSourceName()}, attributeGroup.getParseObject());
                        }
                    }
                } else {
                    this._redefinedAttributeGroups.put(redefined, attributeGroup);
                    container.addRedefinedAttributeGroup(attributeGroup.getRef());
                }
            } else if (this._attributeGroups.containsKey(name)) {
                if (!this.ignoreMdef(name)) {
                    if (this._mdefAll) {
                        this.warning("sch-props-correct.2", new Object[]{"attribute group", QNameHelper.pretty(name), ((SchemaComponent)this._attributeGroups.get(name)).getSourceName()}, attributeGroup.getParseObject());
                    } else {
                        this.error("sch-props-correct.2", new Object[]{"attribute group", QNameHelper.pretty(name), ((SchemaComponent)this._attributeGroups.get(name)).getSourceName()}, attributeGroup.getParseObject());
                    }
                }
            } else {
                this._attributeGroups.put(attributeGroup.getName(), attributeGroup);
                this.addSpelling(attributeGroup.getName(), attributeGroup);
                container.addAttributeGroup(attributeGroup.getRef());
            }
        }
    }

    SchemaAttributeGroup[] attributeGroups() {
        return this._attributeGroups.values().toArray(new SchemaAttributeGroup[this._attributeGroups.size()]);
    }

    SchemaAttributeGroup[] redefinedAttributeGroups() {
        return this._redefinedAttributeGroups.values().toArray(new SchemaAttributeGroup[this._redefinedAttributeGroups.size()]);
    }

    SchemaModelGroupImpl findModelGroup(QName name, String chameleonNamespace, String sourceNamespace) {
        name = this.compatName(name, chameleonNamespace);
        SchemaModelGroupImpl result = (SchemaModelGroupImpl)this._modelGroups.get(name);
        boolean foundOnLoader = false;
        if (result == null) {
            result = (SchemaModelGroupImpl)this._importingLoader.findModelGroup(name);
            boolean bl = foundOnLoader = result != null;
        }
        if (!foundOnLoader && sourceNamespace != null) {
            this.registerDependency(sourceNamespace, name.getNamespaceURI());
        }
        return result;
    }

    SchemaModelGroupImpl findRedefinedModelGroup(QName name, String chameleonNamespace, SchemaModelGroupImpl redefinedBy) {
        QName redefinitionFor = redefinedBy.getName();
        if ((name = this.compatName(name, chameleonNamespace)).equals(redefinitionFor)) {
            return (SchemaModelGroupImpl)this._redefinedModelGroups.get(redefinedBy);
        }
        SchemaModelGroupImpl result = (SchemaModelGroupImpl)this._modelGroups.get(name);
        if (result == null) {
            result = (SchemaModelGroupImpl)this._importingLoader.findModelGroup(name);
        }
        return result;
    }

    void addModelGroup(SchemaModelGroupImpl modelGroup, SchemaModelGroupImpl redefined) {
        if (modelGroup != null) {
            QName name = modelGroup.getName();
            SchemaContainer container = this.getContainer(name.getNamespaceURI());
            assert (container != null && container == modelGroup.getContainer());
            if (redefined != null) {
                if (this._redefinedModelGroups.containsKey(redefined)) {
                    if (!this.ignoreMdef(name)) {
                        if (this._mdefAll) {
                            this.warning("sch-props-correct.2", new Object[]{"model group", QNameHelper.pretty(name), ((SchemaComponent)this._redefinedModelGroups.get(redefined)).getSourceName()}, modelGroup.getParseObject());
                        } else {
                            this.error("sch-props-correct.2", new Object[]{"model group", QNameHelper.pretty(name), ((SchemaComponent)this._redefinedModelGroups.get(redefined)).getSourceName()}, modelGroup.getParseObject());
                        }
                    }
                } else {
                    this._redefinedModelGroups.put(redefined, modelGroup);
                    container.addRedefinedModelGroup(modelGroup.getRef());
                }
            } else if (this._modelGroups.containsKey(name)) {
                if (!this.ignoreMdef(name)) {
                    if (this._mdefAll) {
                        this.warning("sch-props-correct.2", new Object[]{"model group", QNameHelper.pretty(name), ((SchemaComponent)this._modelGroups.get(name)).getSourceName()}, modelGroup.getParseObject());
                    } else {
                        this.error("sch-props-correct.2", new Object[]{"model group", QNameHelper.pretty(name), ((SchemaComponent)this._modelGroups.get(name)).getSourceName()}, modelGroup.getParseObject());
                    }
                }
            } else {
                this._modelGroups.put(modelGroup.getName(), modelGroup);
                this.addSpelling(modelGroup.getName(), modelGroup);
                container.addModelGroup(modelGroup.getRef());
            }
        }
    }

    SchemaModelGroup[] modelGroups() {
        return this._modelGroups.values().toArray(new SchemaModelGroup[this._modelGroups.size()]);
    }

    SchemaModelGroup[] redefinedModelGroups() {
        return this._redefinedModelGroups.values().toArray(new SchemaModelGroup[this._redefinedModelGroups.size()]);
    }

    SchemaIdentityConstraintImpl findIdConstraint(QName name, String chameleonNamespace, String sourceNamespace) {
        name = this.compatName(name, chameleonNamespace);
        if (sourceNamespace != null) {
            this.registerDependency(sourceNamespace, name.getNamespaceURI());
        }
        return (SchemaIdentityConstraintImpl)this._idConstraints.get(name);
    }

    void addIdConstraint(SchemaIdentityConstraintImpl idc) {
        if (idc != null) {
            QName name = idc.getName();
            SchemaContainer container = this.getContainer(name.getNamespaceURI());
            assert (container != null && container == idc.getContainer());
            if (this._idConstraints.containsKey(name)) {
                if (!this.ignoreMdef(name)) {
                    this.warning("sch-props-correct.2", new Object[]{"identity constraint", QNameHelper.pretty(name), ((SchemaComponent)this._idConstraints.get(name)).getSourceName()}, idc.getParseObject());
                }
            } else {
                this._idConstraints.put(name, idc);
                this.addSpelling(idc.getName(), idc);
                container.addIdentityConstraint(idc.getRef());
            }
        }
    }

    SchemaIdentityConstraintImpl[] idConstraints() {
        return this._idConstraints.values().toArray(new SchemaIdentityConstraintImpl[this._idConstraints.size()]);
    }

    void addAnnotation(SchemaAnnotationImpl ann, String targetNamespace) {
        if (ann != null) {
            SchemaContainer container = this.getContainer(targetNamespace);
            assert (container != null && container == ann.getContainer());
            this._annotations.add(ann);
            container.addAnnotation(ann);
        }
    }

    List annotations() {
        return this._annotations;
    }

    boolean isProcessing(Object obj) {
        return this._processingGroups.contains(obj);
    }

    void startProcessing(Object obj) {
        assert (!this._processingGroups.contains(obj));
        this._processingGroups.add(obj);
    }

    void finishProcessing(Object obj) {
        assert (this._processingGroups.contains(obj));
        this._processingGroups.remove(obj);
    }

    Object[] getCurrentProcessing() {
        return this._processingGroups.toArray();
    }

    Map typesByClassname() {
        return Collections.unmodifiableMap(this._typesByClassname);
    }

    void addClassname(String classname, SchemaType type) {
        this._typesByClassname.put(classname, type);
    }

    public static void clearThreadLocals() {
        tl_stscStack.remove();
    }

    public static StscState start() {
        StscStack stscStack = (StscStack)tl_stscStack.get();
        if (stscStack == null) {
            stscStack = new StscStack();
            tl_stscStack.set(stscStack);
        }
        return stscStack.push();
    }

    public static StscState get() {
        return ((StscStack)StscState.tl_stscStack.get()).current;
    }

    public static void end() {
        StscStack stscStack = (StscStack)tl_stscStack.get();
        stscStack.pop();
        if (stscStack.stack.size() == 0) {
            tl_stscStack.set(null);
        }
    }

    static XmlValueRef build_wsstring(int wsr) {
        switch (wsr) {
            case 1: {
                return XMLSTR_PRESERVE;
            }
            case 2: {
                return XMLSTR_REPLACE;
            }
            case 3: {
                return XMLSTR_COLLAPSE;
            }
        }
        return null;
    }

    static XmlValueRef buildString(String str) {
        if (str == null) {
            return null;
        }
        try {
            XmlStringImpl i = new XmlStringImpl();
            i.set(str);
            i.setImmutable();
            return new XmlValueRef(i);
        } catch (XmlValueOutOfRangeException e) {
            return null;
        }
    }

    public void notFoundError(QName itemName, int code, XmlObject loc, boolean recovered) {
        QName name;
        String expected;
        String expectedName = QNameHelper.pretty(itemName);
        String found = null;
        String foundName = null;
        String sourceName = null;
        if (recovered) {
            ++this._recoveredErrors;
        }
        switch (code) {
            case 0: {
                expected = "type";
                break;
            }
            case 1: {
                expected = "element";
                break;
            }
            case 3: {
                expected = "attribute";
                break;
            }
            case 6: {
                expected = "model group";
                break;
            }
            case 4: {
                expected = "attribute group";
                break;
            }
            case 5: {
                expected = "identity constraint";
                break;
            }
            default: {
                assert (false);
                expected = "definition";
            }
        }
        SchemaComponent foundComponent = this.findSpelling(itemName);
        if (foundComponent != null && (name = foundComponent.getName()) != null) {
            switch (foundComponent.getComponentType()) {
                case 0: {
                    found = "type";
                    sourceName = ((SchemaType)foundComponent).getSourceName();
                    break;
                }
                case 1: {
                    found = "element";
                    sourceName = ((SchemaGlobalElement)foundComponent).getSourceName();
                    break;
                }
                case 3: {
                    found = "attribute";
                    sourceName = ((SchemaGlobalAttribute)foundComponent).getSourceName();
                    break;
                }
                case 4: {
                    found = "attribute group";
                    break;
                }
                case 6: {
                    found = "model group";
                }
            }
            if (sourceName != null) {
                sourceName = sourceName.substring(sourceName.lastIndexOf(47) + 1);
            }
            if (!name.equals(itemName)) {
                foundName = QNameHelper.pretty(name);
            }
        }
        if (found == null) {
            this.error("src-resolve", new Object[]{expected, expectedName}, loc);
        } else {
            this.error("src-resolve.a", new Object[]{expected, expectedName, found, foundName == null ? new Integer(0) : new Integer(1), foundName, sourceName == null ? new Integer(0) : new Integer(1), sourceName}, loc);
        }
    }

    public String sourceNameForUri(String uri) {
        return (String)this._sourceForUri.get(uri);
    }

    public Map sourceCopyMap() {
        return Collections.unmodifiableMap(this._sourceForUri);
    }

    public void setBaseUri(URI uri) {
        this._baseURI = uri;
    }

    public String relativize(String uri) {
        return this.relativize(uri, false);
    }

    public String computeSavedFilename(String uri) {
        return this.relativize(uri, true);
    }

    private String relativize(String uri, boolean forSavedFilename) {
        if (uri == null) {
            return null;
        }
        if (uri.startsWith("/")) {
            uri = PROJECT_URL_PREFIX + uri.replace('\\', '/');
        } else {
            int colon = uri.indexOf(58);
            if (colon <= 1 || !uri.substring(0, colon).matches("^\\w+$")) {
                uri = "project://local/" + uri.replace('\\', '/');
            }
        }
        if (this._baseURI != null) {
            try {
                URI relative = this._baseURI.relativize(new URI(uri));
                if (!relative.isAbsolute()) {
                    return relative.toString();
                }
                uri = relative.toString();
            } catch (URISyntaxException e) {
                // empty catch block
            }
        }
        if (!forSavedFilename) {
            return uri;
        }
        int lastslash = uri.lastIndexOf(47);
        String dir = QNameHelper.hexsafe(lastslash == -1 ? "" : uri.substring(0, lastslash));
        int question = uri.indexOf(63, lastslash + 1);
        if (question == -1) {
            return dir + "/" + uri.substring(lastslash + 1);
        }
        String query = QNameHelper.hexsafe(question == -1 ? "" : uri.substring(question));
        if (query.startsWith("URI_SHA_1_")) {
            return dir + "/" + uri.substring(lastslash + 1, question);
        }
        return dir + "/" + uri.substring(lastslash + 1, question) + query;
    }

    public void addSourceUri(String uri, String nameToUse) {
        if (uri == null) {
            return;
        }
        if (nameToUse == null) {
            nameToUse = this.computeSavedFilename(uri);
        }
        this._sourceForUri.put(uri, nameToUse);
    }

    public Collection getErrorListener() {
        return this._errorListener;
    }

    public SchemaTypeLoader getS4SLoader() {
        return this._s4sloader;
    }

    public File getSchemasDir() {
        return this._schemasDir;
    }

    public void setSchemasDir(File _schemasDir) {
        this._schemasDir = _schemasDir;
    }

    private static final class StscStack {
        StscState current;
        ArrayList stack = new ArrayList();

        private StscStack() {
        }

        final StscState push() {
            this.stack.add(this.current);
            this.current = new StscState();
            return this.current;
        }

        final void pop() {
            this.current = (StscState)this.stack.get(this.stack.size() - 1);
            this.stack.remove(this.stack.size() - 1);
        }
    }
}

