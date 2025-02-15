/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans;

import java.io.Serializable;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaCodePrinter;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlOptionCharEscapeMap;
import org.xml.sax.EntityResolver;
import org.xml.sax.XMLReader;

public class XmlOptions
implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map _map = new HashMap();
    public static final String GENERATE_JAVA_14 = "1.4";
    public static final String GENERATE_JAVA_15 = "1.5";
    public static final String SAVE_NAMESPACES_FIRST = "SAVE_NAMESPACES_FIRST";
    public static final String SAVE_SYNTHETIC_DOCUMENT_ELEMENT = "SAVE_SYNTHETIC_DOCUMENT_ELEMENT";
    public static final String SAVE_PRETTY_PRINT = "SAVE_PRETTY_PRINT";
    public static final String SAVE_PRETTY_PRINT_INDENT = "SAVE_PRETTY_PRINT_INDENT";
    public static final String SAVE_PRETTY_PRINT_OFFSET = "SAVE_PRETTY_PRINT_OFFSET";
    public static final String SAVE_AGGRESSIVE_NAMESPACES = "SAVE_AGGRESSIVE_NAMESPACES";
    public static final String SAVE_USE_DEFAULT_NAMESPACE = "SAVE_USE_DEFAULT_NAMESPACE";
    public static final String SAVE_IMPLICIT_NAMESPACES = "SAVE_IMPLICIT_NAMESPACES";
    public static final String SAVE_SUGGESTED_PREFIXES = "SAVE_SUGGESTED_PREFIXES";
    public static final String SAVE_FILTER_PROCINST = "SAVE_FILTER_PROCINST";
    public static final String SAVE_USE_OPEN_FRAGMENT = "SAVE_USE_OPEN_FRAGMENT";
    public static final String SAVE_OUTER = "SAVE_OUTER";
    public static final String SAVE_INNER = "SAVE_INNER";
    public static final String SAVE_NO_XML_DECL = "SAVE_NO_XML_DECL";
    public static final String SAVE_SUBSTITUTE_CHARACTERS = "SAVE_SUBSTITUTE_CHARACTERS";
    public static final String SAVE_OPTIMIZE_FOR_SPEED = "SAVE_OPTIMIZE_FOR_SPEED";
    public static final String SAVE_CDATA_LENGTH_THRESHOLD = "SAVE_CDATA_LENGTH_THRESHOLD";
    public static final String SAVE_CDATA_ENTITY_COUNT_THRESHOLD = "SAVE_CDATA_ENTITY_COUNT_THRESHOLD";
    public static final String SAVE_SAX_NO_NSDECLS_IN_ATTRIBUTES = "SAVE_SAX_NO_NSDECLS_IN_ATTRIBUTES";
    public static final String LOAD_REPLACE_DOCUMENT_ELEMENT = "LOAD_REPLACE_DOCUMENT_ELEMENT";
    public static final String LOAD_STRIP_WHITESPACE = "LOAD_STRIP_WHITESPACE";
    public static final String LOAD_STRIP_COMMENTS = "LOAD_STRIP_COMMENTS";
    public static final String LOAD_STRIP_PROCINSTS = "LOAD_STRIP_PROCINSTS";
    public static final String LOAD_LINE_NUMBERS = "LOAD_LINE_NUMBERS";
    public static final String LOAD_LINE_NUMBERS_END_ELEMENT = "LOAD_LINE_NUMBERS_END_ELEMENT";
    public static final String LOAD_SAVE_CDATA_BOOKMARKS = "LOAD_SAVE_CDATA_BOOKMARKS";
    public static final String LOAD_SUBSTITUTE_NAMESPACES = "LOAD_SUBSTITUTE_NAMESPACES";
    public static final String LOAD_TRIM_TEXT_BUFFER = "LOAD_TRIM_TEXT_BUFFER";
    public static final String LOAD_ADDITIONAL_NAMESPACES = "LOAD_ADDITIONAL_NAMESPACES";
    public static final String LOAD_MESSAGE_DIGEST = "LOAD_MESSAGE_DIGEST";
    public static final String LOAD_USE_DEFAULT_RESOLVER = "LOAD_USE_DEFAULT_RESOLVER";
    public static final String LOAD_USE_XMLREADER = "LOAD_USE_XMLREADER";
    public static final String XQUERY_CURRENT_NODE_VAR = "XQUERY_CURRENT_NODE_VAR";
    public static final String XQUERY_VARIABLE_MAP = "XQUERY_VARIABLE_MAP";
    public static final String CHARACTER_ENCODING = "CHARACTER_ENCODING";
    public static final String ERROR_LISTENER = "ERROR_LISTENER";
    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";
    public static final String DOCUMENT_SOURCE_NAME = "DOCUMENT_SOURCE_NAME";
    public static final String COMPILE_SUBSTITUTE_NAMES = "COMPILE_SUBSTITUTE_NAMES";
    public static final String COMPILE_NO_VALIDATION = "COMPILE_NO_VALIDATION";
    public static final String COMPILE_NO_UPA_RULE = "COMPILE_NO_UPA_RULE";
    public static final String COMPILE_NO_PVR_RULE = "COMPILE_NO_PVR_RULE";
    public static final String COMPILE_NO_ANNOTATIONS = "COMPILE_NO_ANNOTATIONS";
    public static final String COMPILE_DOWNLOAD_URLS = "COMPILE_DOWNLOAD_URLS";
    public static final String COMPILE_MDEF_NAMESPACES = "COMPILE_MDEF_NAMESPACES";
    public static final String VALIDATE_ON_SET = "VALIDATE_ON_SET";
    public static final String VALIDATE_TREAT_LAX_AS_SKIP = "VALIDATE_TREAT_LAX_AS_SKIP";
    public static final String VALIDATE_STRICT = "VALIDATE_STRICT";
    public static final String VALIDATE_TEXT_ONLY = "VALIDATE_TEXT_ONLY";
    public static final String UNSYNCHRONIZED = "UNSYNCHRONIZED";
    public static final String ENTITY_RESOLVER = "ENTITY_RESOLVER";
    public static final String BASE_URI = "BASE_URI";
    public static final String SCHEMA_CODE_PRINTER = "SCHEMA_CODE_PRINTER";
    public static final String GENERATE_JAVA_VERSION = "GENERATE_JAVA_VERSION";
    public static final String COPY_USE_NEW_SYNC_DOMAIN = "COPY_USE_NEW_LOCALE";
    public static final String LOAD_ENTITY_BYTES_LIMIT = "LOAD_ENTITY_BYTES_LIMIT";
    public static final String ENTITY_EXPANSION_LIMIT = "ENTITY_EXPANSION_LIMIT";
    public static final String LOAD_DTD_GRAMMAR = "LOAD_DTD_GRAMMAR";
    public static final String LOAD_EXTERNAL_DTD = "LOAD_EXTERNAL_DTD";
    public static final int DEFAULT_ENTITY_EXPANSION_LIMIT = 2048;
    private static final XmlOptions EMPTY_OPTIONS = new XmlOptions();

    public XmlOptions() {
    }

    public XmlOptions(XmlOptions other) {
        if (other != null) {
            this._map.putAll(other._map);
        }
    }

    public XmlOptions setSaveNamespacesFirst() {
        return this.set(SAVE_NAMESPACES_FIRST);
    }

    public XmlOptions setSavePrettyPrint() {
        return this.set(SAVE_PRETTY_PRINT);
    }

    public XmlOptions setSavePrettyPrintIndent(int indent) {
        return this.set((Object)SAVE_PRETTY_PRINT_INDENT, indent);
    }

    public XmlOptions setSavePrettyPrintOffset(int offset) {
        return this.set((Object)SAVE_PRETTY_PRINT_OFFSET, offset);
    }

    public XmlOptions setCharacterEncoding(String encoding) {
        return this.set((Object)CHARACTER_ENCODING, encoding);
    }

    public XmlOptions setDocumentType(SchemaType type) {
        return this.set((Object)DOCUMENT_TYPE, type);
    }

    public XmlOptions setErrorListener(Collection c) {
        return this.set((Object)ERROR_LISTENER, c);
    }

    public XmlOptions setSaveAggressiveNamespaces() {
        return this.set(SAVE_AGGRESSIVE_NAMESPACES);
    }

    public XmlOptions setSaveAggresiveNamespaces() {
        return this.setSaveAggressiveNamespaces();
    }

    public XmlOptions setSaveSyntheticDocumentElement(QName name) {
        return this.set((Object)SAVE_SYNTHETIC_DOCUMENT_ELEMENT, name);
    }

    public XmlOptions setUseDefaultNamespace() {
        return this.set(SAVE_USE_DEFAULT_NAMESPACE);
    }

    public XmlOptions setSaveImplicitNamespaces(Map implicitNamespaces) {
        return this.set((Object)SAVE_IMPLICIT_NAMESPACES, implicitNamespaces);
    }

    public XmlOptions setSaveSuggestedPrefixes(Map suggestedPrefixes) {
        return this.set((Object)SAVE_SUGGESTED_PREFIXES, suggestedPrefixes);
    }

    public XmlOptions setSaveFilterProcinst(String filterProcinst) {
        return this.set((Object)SAVE_FILTER_PROCINST, filterProcinst);
    }

    public XmlOptions setSaveSubstituteCharacters(XmlOptionCharEscapeMap characterReplacementMap) {
        return this.set((Object)SAVE_SUBSTITUTE_CHARACTERS, characterReplacementMap);
    }

    public XmlOptions setSaveUseOpenFrag() {
        return this.set(SAVE_USE_OPEN_FRAGMENT);
    }

    public XmlOptions setSaveOuter() {
        return this.set(SAVE_OUTER);
    }

    public XmlOptions setSaveInner() {
        return this.set(SAVE_INNER);
    }

    public XmlOptions setSaveNoXmlDecl() {
        return this.set(SAVE_NO_XML_DECL);
    }

    public XmlOptions setSaveCDataLengthThreshold(int cdataLengthThreshold) {
        return this.set((Object)SAVE_CDATA_LENGTH_THRESHOLD, cdataLengthThreshold);
    }

    public XmlOptions setSaveCDataEntityCountThreshold(int cdataEntityCountThreshold) {
        return this.set((Object)SAVE_CDATA_ENTITY_COUNT_THRESHOLD, cdataEntityCountThreshold);
    }

    public XmlOptions setUseCDataBookmarks() {
        return this.set(LOAD_SAVE_CDATA_BOOKMARKS);
    }

    public XmlOptions setSaveSaxNoNSDeclsInAttributes() {
        return this.set(SAVE_SAX_NO_NSDECLS_IN_ATTRIBUTES);
    }

    public XmlOptions setLoadReplaceDocumentElement(QName replacement) {
        return this.set((Object)LOAD_REPLACE_DOCUMENT_ELEMENT, replacement);
    }

    public XmlOptions setLoadStripWhitespace() {
        return this.set(LOAD_STRIP_WHITESPACE);
    }

    public XmlOptions setLoadStripComments() {
        return this.set(LOAD_STRIP_COMMENTS);
    }

    public XmlOptions setLoadStripProcinsts() {
        return this.set(LOAD_STRIP_PROCINSTS);
    }

    public XmlOptions setLoadLineNumbers() {
        return this.set(LOAD_LINE_NUMBERS);
    }

    public XmlOptions setLoadLineNumbers(String option) {
        XmlOptions temp = this.setLoadLineNumbers();
        temp = temp.set(option);
        return temp;
    }

    public XmlOptions setLoadSubstituteNamespaces(Map substNamespaces) {
        return this.set((Object)LOAD_SUBSTITUTE_NAMESPACES, substNamespaces);
    }

    public XmlOptions setLoadTrimTextBuffer() {
        return this.set(LOAD_TRIM_TEXT_BUFFER);
    }

    public XmlOptions setLoadAdditionalNamespaces(Map nses) {
        return this.set((Object)LOAD_ADDITIONAL_NAMESPACES, nses);
    }

    public XmlOptions setLoadMessageDigest() {
        return this.set(LOAD_MESSAGE_DIGEST);
    }

    public XmlOptions setLoadUseDefaultResolver() {
        return this.set(LOAD_USE_DEFAULT_RESOLVER);
    }

    public XmlOptions setLoadUseXMLReader(XMLReader xmlReader) {
        return this.set((Object)LOAD_USE_XMLREADER, xmlReader);
    }

    public XmlOptions setXqueryCurrentNodeVar(String varName) {
        return this.set((Object)XQUERY_CURRENT_NODE_VAR, varName);
    }

    public XmlOptions setXqueryVariables(Map varMap) {
        return this.set((Object)XQUERY_VARIABLE_MAP, varMap);
    }

    public XmlOptions setDocumentSourceName(String documentSourceName) {
        return this.set((Object)DOCUMENT_SOURCE_NAME, documentSourceName);
    }

    public XmlOptions setCompileSubstituteNames(Map nameMap) {
        return this.set((Object)COMPILE_SUBSTITUTE_NAMES, nameMap);
    }

    public XmlOptions setCompileNoValidation() {
        return this.set(COMPILE_NO_VALIDATION);
    }

    public XmlOptions setCompileNoUpaRule() {
        return this.set(COMPILE_NO_UPA_RULE);
    }

    public XmlOptions setCompileNoPvrRule() {
        return this.set(COMPILE_NO_PVR_RULE);
    }

    public XmlOptions setCompileNoAnnotations() {
        return this.set(COMPILE_NO_ANNOTATIONS);
    }

    public XmlOptions setCompileDownloadUrls() {
        return this.set(COMPILE_DOWNLOAD_URLS);
    }

    public XmlOptions setCompileMdefNamespaces(Set mdefNamespaces) {
        return this.set((Object)COMPILE_MDEF_NAMESPACES, mdefNamespaces);
    }

    public XmlOptions setValidateOnSet() {
        return this.set(VALIDATE_ON_SET);
    }

    public XmlOptions setValidateTreatLaxAsSkip() {
        return this.set(VALIDATE_TREAT_LAX_AS_SKIP);
    }

    public XmlOptions setValidateStrict() {
        return this.set(VALIDATE_STRICT);
    }

    public XmlOptions setUnsynchronized() {
        return this.set(UNSYNCHRONIZED);
    }

    public XmlOptions setEntityResolver(EntityResolver resolver) {
        return this.set((Object)ENTITY_RESOLVER, resolver);
    }

    public XmlOptions setBaseURI(URI baseURI) {
        return this.set((Object)BASE_URI, baseURI);
    }

    public XmlOptions setSchemaCodePrinter(SchemaCodePrinter printer) {
        return this.set((Object)SCHEMA_CODE_PRINTER, printer);
    }

    public XmlOptions setGenerateJavaVersion(String source) {
        return this.set((Object)GENERATE_JAVA_VERSION, source);
    }

    public XmlOptions setCopyUseNewSynchronizationDomain(boolean useNewSyncDomain) {
        return this.set((Object)COPY_USE_NEW_SYNC_DOMAIN, useNewSyncDomain);
    }

    public XmlOptions setLoadEntityBytesLimit(int entityBytesLimit) {
        return this.set((Object)LOAD_ENTITY_BYTES_LIMIT, entityBytesLimit);
    }

    public XmlOptions setEntityExpansionLimit(int entityExpansionLimit) {
        return this.set((Object)ENTITY_EXPANSION_LIMIT, entityExpansionLimit);
    }

    public XmlOptions setLoadDTDGrammar(boolean loadDTDGrammar) {
        return this.set((Object)LOAD_DTD_GRAMMAR, loadDTDGrammar);
    }

    public XmlOptions setLoadExternalDTD(boolean loadExternalDTD) {
        return this.set((Object)LOAD_EXTERNAL_DTD, loadExternalDTD);
    }

    public static XmlOptions maskNull(XmlOptions o) {
        return o == null ? EMPTY_OPTIONS : o;
    }

    public void put(Object option) {
        this.put(option, null);
    }

    public void put(Object option, Object value) {
        this._map.put(option, value);
    }

    public void put(Object option, int value) {
        this.put(option, new Integer(value));
    }

    private XmlOptions set(Object option) {
        return this.set(option, null);
    }

    private XmlOptions set(Object option, Object value) {
        this._map.put(option, value);
        return this;
    }

    private XmlOptions set(Object option, int value) {
        return this.set(option, new Integer(value));
    }

    public boolean hasOption(Object option) {
        return this._map.containsKey(option);
    }

    public static boolean hasOption(XmlOptions options, Object option) {
        return options == null ? false : options.hasOption(option);
    }

    public Object get(Object option) {
        return this._map.get(option);
    }

    public void remove(Object option) {
        this._map.remove(option);
    }

    public static Object safeGet(XmlOptions o, Object option) {
        return o == null ? null : o.get(option);
    }

    static {
        XmlOptions.EMPTY_OPTIONS._map = Collections.unmodifiableMap(XmlOptions.EMPTY_OPTIONS._map);
    }
}

