/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.BindingConfig;
import org.apache.xmlbeans.InterfaceExtension;
import org.apache.xmlbeans.PrePostExtension;
import org.apache.xmlbeans.UserType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.config.InterfaceExtensionImpl;
import org.apache.xmlbeans.impl.config.NameSet;
import org.apache.xmlbeans.impl.config.NameSetBuilder;
import org.apache.xmlbeans.impl.config.PrePostExtensionImpl;
import org.apache.xmlbeans.impl.config.UserTypeImpl;
import org.apache.xmlbeans.impl.jam.JamClassLoader;
import org.apache.xmlbeans.impl.jam.JamService;
import org.apache.xmlbeans.impl.jam.JamServiceFactory;
import org.apache.xmlbeans.impl.jam.JamServiceParams;
import org.apache.xmlbeans.impl.schema.StscState;
import org.apache.xmlbeans.impl.xb.xmlconfig.ConfigDocument;
import org.apache.xmlbeans.impl.xb.xmlconfig.Extensionconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Qnameconfig;
import org.apache.xmlbeans.impl.xb.xmlconfig.Qnametargetenum;
import org.apache.xmlbeans.impl.xb.xmlconfig.Usertypeconfig;

public class BindingConfigImpl
extends BindingConfig {
    private Map _packageMap;
    private Map _prefixMap;
    private Map _suffixMap;
    private Map _packageMapByUriPrefix;
    private Map _prefixMapByUriPrefix;
    private Map _suffixMapByUriPrefix;
    private Map _qnameTypeMap;
    private Map _qnameDocTypeMap;
    private Map _qnameElemMap;
    private Map _qnameAttMap;
    private List _interfaceExtensions;
    private List _prePostExtensions;
    private Map _userTypes;

    private BindingConfigImpl() {
        this._packageMap = Collections.EMPTY_MAP;
        this._prefixMap = Collections.EMPTY_MAP;
        this._suffixMap = Collections.EMPTY_MAP;
        this._packageMapByUriPrefix = Collections.EMPTY_MAP;
        this._prefixMapByUriPrefix = Collections.EMPTY_MAP;
        this._suffixMapByUriPrefix = Collections.EMPTY_MAP;
        this._qnameTypeMap = Collections.EMPTY_MAP;
        this._qnameDocTypeMap = Collections.EMPTY_MAP;
        this._qnameElemMap = Collections.EMPTY_MAP;
        this._qnameAttMap = Collections.EMPTY_MAP;
        this._interfaceExtensions = new ArrayList();
        this._prePostExtensions = new ArrayList();
        this._userTypes = Collections.EMPTY_MAP;
    }

    public static BindingConfig forConfigDocuments(ConfigDocument.Config[] configs, File[] javaFiles, File[] classpath) {
        return new BindingConfigImpl(configs, javaFiles, classpath);
    }

    private BindingConfigImpl(ConfigDocument.Config[] configs, File[] javaFiles, File[] classpath) {
        this._packageMap = new LinkedHashMap();
        this._prefixMap = new LinkedHashMap();
        this._suffixMap = new LinkedHashMap();
        this._packageMapByUriPrefix = new LinkedHashMap();
        this._prefixMapByUriPrefix = new LinkedHashMap();
        this._suffixMapByUriPrefix = new LinkedHashMap();
        this._qnameTypeMap = new LinkedHashMap();
        this._qnameDocTypeMap = new LinkedHashMap();
        this._qnameElemMap = new LinkedHashMap();
        this._qnameAttMap = new LinkedHashMap();
        this._interfaceExtensions = new ArrayList();
        this._prePostExtensions = new ArrayList();
        this._userTypes = new LinkedHashMap();
        for (int i = 0; i < configs.length; ++i) {
            ConfigDocument.Config config = configs[i];
            Nsconfig[] nsa = config.getNamespaceArray();
            for (int j = 0; j < nsa.length; ++j) {
                BindingConfigImpl.recordNamespaceSetting(nsa[j].getUri(), nsa[j].getPackage(), this._packageMap);
                BindingConfigImpl.recordNamespaceSetting(nsa[j].getUri(), nsa[j].getPrefix(), this._prefixMap);
                BindingConfigImpl.recordNamespaceSetting(nsa[j].getUri(), nsa[j].getSuffix(), this._suffixMap);
                BindingConfigImpl.recordNamespacePrefixSetting(nsa[j].getUriprefix(), nsa[j].getPackage(), this._packageMapByUriPrefix);
                BindingConfigImpl.recordNamespacePrefixSetting(nsa[j].getUriprefix(), nsa[j].getPrefix(), this._prefixMapByUriPrefix);
                BindingConfigImpl.recordNamespacePrefixSetting(nsa[j].getUriprefix(), nsa[j].getSuffix(), this._suffixMapByUriPrefix);
            }
            Qnameconfig[] qnc = config.getQnameArray();
            for (int j = 0; j < qnc.length; ++j) {
                List applyto = qnc[j].xgetTarget().xgetListValue();
                QName name = qnc[j].getName();
                String javaname = qnc[j].getJavaname();
                block9: for (int k = 0; k < applyto.size(); ++k) {
                    Qnametargetenum a = (Qnametargetenum)applyto.get(k);
                    switch (a.enumValue().intValue()) {
                        case 1: {
                            this._qnameTypeMap.put(name, javaname);
                            continue block9;
                        }
                        case 2: {
                            this._qnameDocTypeMap.put(name, javaname);
                            continue block9;
                        }
                        case 3: {
                            this._qnameElemMap.put(name, javaname);
                            continue block9;
                        }
                        case 4: {
                            this._qnameAttMap.put(name, javaname);
                        }
                    }
                }
            }
            Extensionconfig[] ext = config.getExtensionArray();
            for (int j = 0; j < ext.length; ++j) {
                this.recordExtensionSetting(javaFiles, classpath, ext[j]);
            }
            Usertypeconfig[] utypes = config.getUsertypeArray();
            for (int j = 0; j < utypes.length; ++j) {
                this.recordUserTypeSetting(javaFiles, classpath, utypes[j]);
            }
        }
        this.secondPhaseValidation();
    }

    void addInterfaceExtension(InterfaceExtensionImpl ext) {
        if (ext == null) {
            return;
        }
        this._interfaceExtensions.add(ext);
    }

    void addPrePostExtension(PrePostExtensionImpl ext) {
        if (ext == null) {
            return;
        }
        this._prePostExtensions.add(ext);
    }

    void secondPhaseValidation() {
        int i;
        HashMap<InterfaceExtensionImpl.MethodSignatureImpl, InterfaceExtensionImpl.MethodSignatureImpl> methodSignatures = new HashMap<InterfaceExtensionImpl.MethodSignatureImpl, InterfaceExtensionImpl.MethodSignatureImpl>();
        for (i = 0; i < this._interfaceExtensions.size(); ++i) {
            InterfaceExtensionImpl interfaceExtension = (InterfaceExtensionImpl)this._interfaceExtensions.get(i);
            InterfaceExtensionImpl.MethodSignatureImpl[] methods = (InterfaceExtensionImpl.MethodSignatureImpl[])interfaceExtension.getMethods();
            for (int j = 0; j < methods.length; ++j) {
                InterfaceExtensionImpl.MethodSignatureImpl ms = methods[j];
                if (methodSignatures.containsKey(methods[j])) {
                    InterfaceExtensionImpl.MethodSignatureImpl ms2 = (InterfaceExtensionImpl.MethodSignatureImpl)methodSignatures.get(methods[j]);
                    if (!ms.getReturnType().equals(ms2.getReturnType())) {
                        BindingConfigImpl.error("Colliding methods '" + ms.getSignature() + "' in interfaces " + ms.getInterfaceName() + " and " + ms2.getInterfaceName() + ".", null);
                    }
                    return;
                }
                methodSignatures.put(methods[j], methods[j]);
            }
        }
        for (i = 0; i < this._prePostExtensions.size() - 1; ++i) {
            PrePostExtensionImpl a = (PrePostExtensionImpl)this._prePostExtensions.get(i);
            for (int j = 1; j < this._prePostExtensions.size(); ++j) {
                PrePostExtensionImpl b = (PrePostExtensionImpl)this._prePostExtensions.get(j);
                if (!a.hasNameSetIntersection(b)) continue;
                BindingConfigImpl.error("The applicable domain for handler '" + a.getHandlerNameForJavaSource() + "' intersects with the one for '" + b.getHandlerNameForJavaSource() + "'.", null);
            }
        }
    }

    private static void recordNamespaceSetting(Object key, String value, Map result) {
        if (value == null) {
            return;
        }
        if (key == null) {
            result.put("", value);
        } else if (key instanceof String && "##any".equals(key)) {
            result.put(key, value);
        } else if (key instanceof List) {
            for (String uri : (List)key) {
                if ("##local".equals(uri)) {
                    uri = "";
                }
                result.put(uri, value);
            }
        }
    }

    private static void recordNamespacePrefixSetting(List list, String value, Map result) {
        if (value == null) {
            return;
        }
        if (list == null) {
            return;
        }
        Iterator i = list.iterator();
        while (i.hasNext()) {
            result.put(i.next(), value);
        }
    }

    private void recordExtensionSetting(File[] javaFiles, File[] classpath, Extensionconfig ext) {
        NameSet xbeanSet = null;
        Object key = ext.getFor();
        if (key instanceof String && "*".equals(key)) {
            xbeanSet = NameSet.EVERYTHING;
        } else if (key instanceof List) {
            NameSetBuilder xbeanSetBuilder = new NameSetBuilder();
            for (String xbeanName : (List)key) {
                xbeanSetBuilder.add(xbeanName);
            }
            xbeanSet = xbeanSetBuilder.toNameSet();
        }
        if (xbeanSet == null) {
            BindingConfigImpl.error("Invalid value of attribute 'for' : '" + key + "'.", ext);
        }
        Extensionconfig.Interface[] intfXO = ext.getInterfaceArray();
        Extensionconfig.PrePostSet ppXO = ext.getPrePostSet();
        if (intfXO.length > 0 || ppXO != null) {
            JamClassLoader jamLoader = this.getJamLoader(javaFiles, classpath);
            for (int i = 0; i < intfXO.length; ++i) {
                this.addInterfaceExtension(InterfaceExtensionImpl.newInstance(jamLoader, xbeanSet, intfXO[i]));
            }
            this.addPrePostExtension(PrePostExtensionImpl.newInstance(jamLoader, xbeanSet, ppXO));
        }
    }

    private void recordUserTypeSetting(File[] javaFiles, File[] classpath, Usertypeconfig usertypeconfig) {
        JamClassLoader jamLoader = this.getJamLoader(javaFiles, classpath);
        UserTypeImpl userType = UserTypeImpl.newInstance(jamLoader, usertypeconfig);
        this._userTypes.put(userType.getName(), userType);
    }

    private String lookup(Map map, Map mapByUriPrefix, String uri) {
        String result;
        if (uri == null) {
            uri = "";
        }
        if ((result = (String)map.get(uri)) != null) {
            return result;
        }
        if (mapByUriPrefix != null && (result = this.lookupByUriPrefix(mapByUriPrefix, uri)) != null) {
            return result;
        }
        return (String)map.get("##any");
    }

    private String lookupByUriPrefix(Map mapByUriPrefix, String uri) {
        if (uri == null) {
            return null;
        }
        if (!mapByUriPrefix.isEmpty()) {
            String uriprefix = null;
            for (String nextprefix : mapByUriPrefix.keySet()) {
                if (uriprefix != null && nextprefix.length() < uriprefix.length() || !uri.startsWith(nextprefix)) continue;
                uriprefix = nextprefix;
            }
            if (uriprefix != null) {
                return (String)mapByUriPrefix.get(uriprefix);
            }
        }
        return null;
    }

    static void warning(String s, XmlObject xo) {
        StscState.get().error(s, 1, xo);
    }

    static void error(String s, XmlObject xo) {
        StscState.get().error(s, 0, xo);
    }

    @Override
    public String lookupPackageForNamespace(String uri) {
        return this.lookup(this._packageMap, this._packageMapByUriPrefix, uri);
    }

    @Override
    public String lookupPrefixForNamespace(String uri) {
        return this.lookup(this._prefixMap, this._prefixMapByUriPrefix, uri);
    }

    @Override
    public String lookupSuffixForNamespace(String uri) {
        return this.lookup(this._suffixMap, this._suffixMapByUriPrefix, uri);
    }

    @Override
    public String lookupJavanameForQName(QName qname) {
        String result = (String)this._qnameTypeMap.get(qname);
        if (result != null) {
            return result;
        }
        return (String)this._qnameDocTypeMap.get(qname);
    }

    @Override
    public String lookupJavanameForQName(QName qname, int kind) {
        switch (kind) {
            case 1: {
                return (String)this._qnameTypeMap.get(qname);
            }
            case 2: {
                return (String)this._qnameDocTypeMap.get(qname);
            }
            case 3: {
                return (String)this._qnameElemMap.get(qname);
            }
            case 4: {
                return (String)this._qnameAttMap.get(qname);
            }
        }
        return null;
    }

    @Override
    public UserType lookupUserTypeForQName(QName qname) {
        if (qname == null) {
            return null;
        }
        return (UserType)this._userTypes.get(qname);
    }

    @Override
    public InterfaceExtension[] getInterfaceExtensions() {
        return this._interfaceExtensions.toArray(new InterfaceExtension[this._interfaceExtensions.size()]);
    }

    @Override
    public InterfaceExtension[] getInterfaceExtensions(String fullJavaName) {
        ArrayList<InterfaceExtensionImpl> result = new ArrayList<InterfaceExtensionImpl>();
        for (int i = 0; i < this._interfaceExtensions.size(); ++i) {
            InterfaceExtensionImpl intfExt = (InterfaceExtensionImpl)this._interfaceExtensions.get(i);
            if (!intfExt.contains(fullJavaName)) continue;
            result.add(intfExt);
        }
        return result.toArray(new InterfaceExtension[result.size()]);
    }

    @Override
    public PrePostExtension[] getPrePostExtensions() {
        return this._prePostExtensions.toArray(new PrePostExtension[this._prePostExtensions.size()]);
    }

    @Override
    public PrePostExtension getPrePostExtension(String fullJavaName) {
        for (int i = 0; i < this._prePostExtensions.size(); ++i) {
            PrePostExtensionImpl prePostExt = (PrePostExtensionImpl)this._prePostExtensions.get(i);
            if (!prePostExt.contains(fullJavaName)) continue;
            return prePostExt;
        }
        return null;
    }

    private JamClassLoader getJamLoader(File[] javaFiles, File[] classpath) {
        JamService service;
        int i;
        JamServiceFactory jf = JamServiceFactory.getInstance();
        JamServiceParams params = jf.createServiceParams();
        params.set14WarningsEnabled(false);
        params.setShowWarnings(false);
        if (javaFiles != null) {
            for (i = 0; i < javaFiles.length; ++i) {
                params.includeSourceFile(javaFiles[i]);
            }
        }
        params.addClassLoader(this.getClass().getClassLoader());
        if (classpath != null) {
            for (i = 0; i < classpath.length; ++i) {
                params.addClasspath(classpath[i]);
            }
        }
        try {
            service = jf.createService(params);
        } catch (IOException ioe) {
            BindingConfigImpl.error("Error when accessing .java files.", null);
            return null;
        }
        return service.getClassLoader();
    }
}

