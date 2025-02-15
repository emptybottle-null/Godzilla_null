/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.store;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SystemProperties;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlOptionCharEscapeMap;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.EncodingMap;
import org.apache.xmlbeans.impl.common.GenericXmlInputStream;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.XmlEventBase;
import org.apache.xmlbeans.impl.common.XmlNameImpl;
import org.apache.xmlbeans.impl.store.CharUtil;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.xml.stream.Attribute;
import org.apache.xmlbeans.xml.stream.AttributeIterator;
import org.apache.xmlbeans.xml.stream.ChangePrefixMapping;
import org.apache.xmlbeans.xml.stream.CharacterData;
import org.apache.xmlbeans.xml.stream.Comment;
import org.apache.xmlbeans.xml.stream.EndDocument;
import org.apache.xmlbeans.xml.stream.EndElement;
import org.apache.xmlbeans.xml.stream.EndPrefixMapping;
import org.apache.xmlbeans.xml.stream.Location;
import org.apache.xmlbeans.xml.stream.ProcessingInstruction;
import org.apache.xmlbeans.xml.stream.StartDocument;
import org.apache.xmlbeans.xml.stream.StartElement;
import org.apache.xmlbeans.xml.stream.StartPrefixMapping;
import org.apache.xmlbeans.xml.stream.XMLEvent;
import org.apache.xmlbeans.xml.stream.XMLName;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.helpers.AttributesImpl;

abstract class Saver {
    static final int ROOT = 1;
    static final int ELEM = 2;
    static final int ATTR = 3;
    static final int COMMENT = 4;
    static final int PROCINST = 5;
    static final int TEXT = 0;
    private final Locale _locale;
    private final long _version;
    private SaveCur _cur;
    private List _ancestorNamespaces;
    private Map _suggestedPrefixes;
    protected XmlOptionCharEscapeMap _replaceChar;
    private boolean _useDefaultNamespace;
    private Map _preComputedNamespaces;
    private boolean _saveNamespacesFirst;
    private ArrayList _attrNames;
    private ArrayList _attrValues;
    private ArrayList _namespaceStack;
    private int _currentMapping;
    private HashMap _uriMap;
    private HashMap _prefixMap;
    private String _initialDefaultUri;
    static final String _newLine = SystemProperties.getProperty("line.separator") == null ? "\n" : SystemProperties.getProperty("line.separator");

    protected abstract boolean emitElement(SaveCur var1, ArrayList var2, ArrayList var3);

    protected abstract void emitFinish(SaveCur var1);

    protected abstract void emitText(SaveCur var1);

    protected abstract void emitComment(SaveCur var1);

    protected abstract void emitProcinst(SaveCur var1);

    protected abstract void emitDocType(String var1, String var2, String var3);

    protected abstract void emitStartDoc(SaveCur var1);

    protected abstract void emitEndDoc(SaveCur var1);

    protected void syntheticNamespace(String prefix, String uri, boolean considerDefault) {
    }

    Saver(Cur c, XmlOptions options) {
        assert (c._locale.entered());
        options = XmlOptions.maskNull(options);
        this._cur = Saver.createSaveCur(c, options);
        this._locale = c._locale;
        this._version = this._locale.version();
        this._namespaceStack = new ArrayList();
        this._uriMap = new HashMap();
        this._prefixMap = new HashMap();
        this._attrNames = new ArrayList();
        this._attrValues = new ArrayList();
        this.addMapping("xml", "http://www.w3.org/XML/1998/namespace");
        if (options.hasOption("SAVE_IMPLICIT_NAMESPACES")) {
            Map m = (Map)options.get("SAVE_IMPLICIT_NAMESPACES");
            for (String prefix : m.keySet()) {
                this.addMapping(prefix, (String)m.get(prefix));
            }
        }
        if (options.hasOption("SAVE_SUBSTITUTE_CHARACTERS")) {
            this._replaceChar = (XmlOptionCharEscapeMap)options.get("SAVE_SUBSTITUTE_CHARACTERS");
        }
        if (this.getNamespaceForPrefix("") == null) {
            this._initialDefaultUri = new String("");
            this.addMapping("", this._initialDefaultUri);
        }
        if (options.hasOption("SAVE_AGGRESSIVE_NAMESPACES") && !(this instanceof SynthNamespaceSaver)) {
            SynthNamespaceSaver saver = new SynthNamespaceSaver(c, options);
            while (saver.process()) {
            }
            if (!saver._synthNamespaces.isEmpty()) {
                this._preComputedNamespaces = saver._synthNamespaces;
            }
        }
        this._useDefaultNamespace = options.hasOption("SAVE_USE_DEFAULT_NAMESPACE");
        this._saveNamespacesFirst = options.hasOption("SAVE_NAMESPACES_FIRST");
        if (options.hasOption("SAVE_SUGGESTED_PREFIXES")) {
            this._suggestedPrefixes = (Map)options.get("SAVE_SUGGESTED_PREFIXES");
        }
        this._ancestorNamespaces = this._cur.getAncestorNamespaces();
    }

    private static SaveCur createSaveCur(Cur c, XmlOptions options) {
        String filterPI;
        QName synthName = (QName)options.get("SAVE_SYNTHETIC_DOCUMENT_ELEMENT");
        QName fragName = synthName;
        if (fragName == null) {
            fragName = options.hasOption("SAVE_USE_OPEN_FRAGMENT") ? Locale._openuriFragment : Locale._xmlFragment;
        }
        boolean saveInner = options.hasOption("SAVE_INNER") && !options.hasOption("SAVE_OUTER");
        Cur start = c.tempCur();
        Cur end = c.tempCur();
        SaveCur cur = null;
        int k = c.kind();
        switch (k) {
            case 1: {
                Saver.positionToInner(c, start, end);
                if (Locale.isFragment(start, end)) {
                    cur = new FragSaveCur(start, end, fragName);
                    break;
                }
                if (synthName != null) {
                    cur = new FragSaveCur(start, end, synthName);
                    break;
                }
                cur = new DocSaveCur(c);
                break;
            }
            case 2: {
                if (saveInner) {
                    Saver.positionToInner(c, start, end);
                    cur = new FragSaveCur(start, end, Locale.isFragment(start, end) ? fragName : synthName);
                    break;
                }
                if (synthName != null) {
                    Saver.positionToInner(c, start, end);
                    cur = new FragSaveCur(start, end, synthName);
                    break;
                }
                start.moveToCur(c);
                end.moveToCur(c);
                end.skip();
                cur = new FragSaveCur(start, end, null);
            }
        }
        if (cur == null) {
            assert (k < 0 || k == 3 || k == 4 || k == 5 || k == 0);
            if (k < 0) {
                start.moveToCur(c);
                end.moveToCur(c);
            } else if (k == 0) {
                start.moveToCur(c);
                end.moveToCur(c);
                end.next();
            } else if (saveInner) {
                start.moveToCur(c);
                start.next();
                end.moveToCur(c);
                end.toEnd();
            } else if (k == 3) {
                start.moveToCur(c);
                end.moveToCur(c);
            } else {
                assert (k == 4 || k == 5);
                start.moveToCur(c);
                end.moveToCur(c);
                end.skip();
            }
            cur = new FragSaveCur(start, end, fragName);
        }
        if ((filterPI = (String)options.get("SAVE_FILTER_PROCINST")) != null) {
            cur = new FilterPiSaveCur(cur, filterPI);
        }
        if (options.hasOption("SAVE_PRETTY_PRINT")) {
            cur = new PrettySaveCur(cur, options);
        }
        start.release();
        end.release();
        return cur;
    }

    private static void positionToInner(Cur c, Cur start, Cur end) {
        assert (c.isContainer());
        start.moveToCur(c);
        if (!start.toFirstAttr()) {
            start.next();
        }
        end.moveToCur(c);
        end.toEnd();
    }

    static boolean isBadChar(char ch) {
        return !(Character.isHighSurrogate(ch) || Character.isLowSurrogate(ch) || ch >= ' ' && ch <= '\ud7ff' || ch >= '\ue000' && ch <= '\ufffd' || ch >= '\u10000' && ch <= '\u10ffff' || ch == '\t' || ch == '\n' || ch == '\r');
    }

    protected boolean saveNamespacesFirst() {
        return this._saveNamespacesFirst;
    }

    protected void enterLocale() {
        this._locale.enter();
    }

    protected void exitLocale() {
        this._locale.exit();
    }

    protected final boolean process() {
        assert (this._locale.entered());
        if (this._cur == null) {
            return false;
        }
        if (this._version != this._locale.version()) {
            throw new ConcurrentModificationException("Document changed during save");
        }
        switch (this._cur.kind()) {
            case 1: {
                this.processRoot();
                break;
            }
            case 2: {
                this.processElement();
                break;
            }
            case -2: {
                this.processFinish();
                break;
            }
            case 0: {
                this.emitText(this._cur);
                break;
            }
            case 4: {
                this.emitComment(this._cur);
                this._cur.toEnd();
                break;
            }
            case 5: {
                this.emitProcinst(this._cur);
                this._cur.toEnd();
                break;
            }
            case -1: {
                this.emitEndDoc(this._cur);
                this._cur.release();
                this._cur = null;
                return true;
            }
            default: {
                throw new RuntimeException("Unexpected kind");
            }
        }
        this._cur.next();
        return true;
    }

    private final void processFinish() {
        this.emitFinish(this._cur);
        this.popMappings();
    }

    private final void processRoot() {
        assert (this._cur.isRoot());
        XmlDocumentProperties props = this._cur.getDocProps();
        String systemId = null;
        String docTypeName = null;
        if (props != null) {
            systemId = props.getDoctypeSystemId();
            docTypeName = props.getDoctypeName();
        }
        if (systemId != null || docTypeName != null) {
            if (docTypeName == null) {
                this._cur.push();
                while (!this._cur.isElem() && this._cur.next()) {
                }
                if (this._cur.isElem()) {
                    docTypeName = this._cur.getName().getLocalPart();
                }
                this._cur.pop();
            }
            String publicId = props.getDoctypePublicId();
            if (docTypeName != null) {
                QName rootElemName = this._cur.getName();
                if (rootElemName == null) {
                    this._cur.push();
                    while (!this._cur.isFinish()) {
                        if (this._cur.isElem()) {
                            rootElemName = this._cur.getName();
                            break;
                        }
                        this._cur.next();
                    }
                    this._cur.pop();
                }
                if (rootElemName != null && docTypeName.equals(rootElemName.getLocalPart())) {
                    this.emitDocType(docTypeName, publicId, systemId);
                    return;
                }
            }
        }
        this.emitStartDoc(this._cur);
    }

    private final void processElement() {
        assert (this._cur.isElem() && this._cur.getName() != null);
        QName name = this._cur.getName();
        boolean ensureDefaultEmpty = name.getNamespaceURI().length() == 0;
        this.pushMappings(this._cur, ensureDefaultEmpty);
        this.ensureMapping(name.getNamespaceURI(), name.getPrefix(), !ensureDefaultEmpty, false);
        this._attrNames.clear();
        this._attrValues.clear();
        this._cur.push();
        boolean A2 = this._cur.toFirstAttr();
        while (A2) {
            block7: {
                if (this._cur.isNormalAttr()) {
                    QName attrName = this._cur.getName();
                    this._attrNames.add(attrName);
                    for (int i = this._attrNames.size() - 2; i >= 0; --i) {
                        if (!this._attrNames.get(i).equals(attrName)) continue;
                        this._attrNames.remove(this._attrNames.size() - 1);
                        break block7;
                    }
                    this._attrValues.add(this._cur.getAttrValue());
                    this.ensureMapping(attrName.getNamespaceURI(), attrName.getPrefix(), false, true);
                }
            }
            A2 = this._cur.toNextAttr();
        }
        this._cur.pop();
        if (this._preComputedNamespaces != null) {
            for (String uri : this._preComputedNamespaces.keySet()) {
                String prefix = (String)this._preComputedNamespaces.get(uri);
                boolean considerDefault = prefix.length() == 0 && !ensureDefaultEmpty;
                this.ensureMapping(uri, prefix, considerDefault, false);
            }
            this._preComputedNamespaces = null;
        }
        if (this.emitElement(this._cur, this._attrNames, this._attrValues)) {
            this.popMappings();
            this._cur.toEnd();
        }
    }

    boolean hasMappings() {
        int i = this._namespaceStack.size();
        return i > 0 && this._namespaceStack.get(i - 1) != null;
    }

    void iterateMappings() {
        this._currentMapping = this._namespaceStack.size();
        while (this._currentMapping > 0 && this._namespaceStack.get(this._currentMapping - 1) != null) {
            this._currentMapping -= 8;
        }
    }

    boolean hasMapping() {
        return this._currentMapping < this._namespaceStack.size();
    }

    void nextMapping() {
        this._currentMapping += 8;
    }

    String mappingPrefix() {
        assert (this.hasMapping());
        return (String)this._namespaceStack.get(this._currentMapping + 6);
    }

    String mappingUri() {
        assert (this.hasMapping());
        return (String)this._namespaceStack.get(this._currentMapping + 7);
    }

    private final void pushMappings(SaveCur c, boolean ensureDefaultEmpty) {
        assert (c.isContainer());
        this._namespaceStack.add(null);
        c.push();
        boolean A2 = c.toFirstAttr();
        while (A2) {
            if (c.isXmlns()) {
                this.addNewFrameMapping(c.getXmlnsPrefix(), c.getXmlnsUri(), ensureDefaultEmpty);
            }
            A2 = c.toNextAttr();
        }
        c.pop();
        if (this._ancestorNamespaces != null) {
            for (int i = 0; i < this._ancestorNamespaces.size(); i += 2) {
                String prefix = (String)this._ancestorNamespaces.get(i);
                String uri = (String)this._ancestorNamespaces.get(i + 1);
                this.addNewFrameMapping(prefix, uri, ensureDefaultEmpty);
            }
            this._ancestorNamespaces = null;
        }
        if (ensureDefaultEmpty) {
            String defaultUri = (String)this._prefixMap.get("");
            assert (defaultUri != null);
            if (defaultUri.length() > 0) {
                this.addMapping("", "");
            }
        }
    }

    private final void addNewFrameMapping(String prefix, String uri, boolean ensureDefaultEmpty) {
        if (!(prefix.length() != 0 && uri.length() <= 0 || ensureDefaultEmpty && prefix.length() <= 0 && uri.length() != 0)) {
            this.iterateMappings();
            while (this.hasMapping()) {
                if (this.mappingPrefix().equals(prefix)) {
                    return;
                }
                this.nextMapping();
            }
            if (uri.equals(this.getNamespaceForPrefix(prefix))) {
                return;
            }
            this.addMapping(prefix, uri);
        }
    }

    private final void addMapping(String prefix, String uri) {
        assert (uri != null);
        assert (prefix != null);
        String renameUri = (String)this._prefixMap.get(prefix);
        String renamePrefix = null;
        if (renameUri != null) {
            if (renameUri.equals(uri)) {
                renameUri = null;
            } else {
                int i = this._namespaceStack.size();
                while (i > 0) {
                    if (this._namespaceStack.get(i - 1) == null) {
                        --i;
                        continue;
                    }
                    if (this._namespaceStack.get(i - 7).equals(renameUri) && ((renamePrefix = (String)this._namespaceStack.get(i - 8)) == null || !renamePrefix.equals(prefix))) break;
                    i -= 8;
                }
                assert (i > 0);
            }
        }
        this._namespaceStack.add(this._uriMap.get(uri));
        this._namespaceStack.add(uri);
        if (renameUri != null) {
            this._namespaceStack.add(this._uriMap.get(renameUri));
            this._namespaceStack.add(renameUri);
        } else {
            this._namespaceStack.add(null);
            this._namespaceStack.add(null);
        }
        this._namespaceStack.add(prefix);
        this._namespaceStack.add(this._prefixMap.get(prefix));
        this._namespaceStack.add(prefix);
        this._namespaceStack.add(uri);
        this._uriMap.put(uri, prefix);
        this._prefixMap.put(prefix, uri);
        if (renameUri != null) {
            this._uriMap.put(renameUri, renamePrefix);
        }
    }

    private final void popMappings() {
        int i;
        while ((i = this._namespaceStack.size()) != 0) {
            if (this._namespaceStack.get(i - 1) == null) {
                this._namespaceStack.remove(i - 1);
                break;
            }
            Object oldUri = this._namespaceStack.get(i - 7);
            Object oldPrefix = this._namespaceStack.get(i - 8);
            if (oldPrefix == null) {
                this._uriMap.remove(oldUri);
            } else {
                this._uriMap.put(oldUri, oldPrefix);
            }
            oldPrefix = this._namespaceStack.get(i - 4);
            oldUri = this._namespaceStack.get(i - 3);
            if (oldUri == null) {
                this._prefixMap.remove(oldPrefix);
            } else {
                this._prefixMap.put(oldPrefix, oldUri);
            }
            String uri = (String)this._namespaceStack.get(i - 5);
            if (uri != null) {
                this._uriMap.put(uri, this._namespaceStack.get(i - 6));
            }
            this._namespaceStack.remove(i - 1);
            this._namespaceStack.remove(i - 2);
            this._namespaceStack.remove(i - 3);
            this._namespaceStack.remove(i - 4);
            this._namespaceStack.remove(i - 5);
            this._namespaceStack.remove(i - 6);
            this._namespaceStack.remove(i - 7);
            this._namespaceStack.remove(i - 8);
        }
    }

    private final void dumpMappings() {
        int i = this._namespaceStack.size();
        while (i > 0) {
            if (this._namespaceStack.get(i - 1) == null) {
                System.out.println("----------------");
                --i;
                continue;
            }
            System.out.print("Mapping: ");
            System.out.print(this._namespaceStack.get(i - 2));
            System.out.print(" -> ");
            System.out.print(this._namespaceStack.get(i - 1));
            System.out.println();
            System.out.print("Prefix Undo: ");
            System.out.print(this._namespaceStack.get(i - 4));
            System.out.print(" -> ");
            System.out.print(this._namespaceStack.get(i - 3));
            System.out.println();
            System.out.print("Uri Rename: ");
            System.out.print(this._namespaceStack.get(i - 5));
            System.out.print(" -> ");
            System.out.print(this._namespaceStack.get(i - 6));
            System.out.println();
            System.out.print("UriUndo: ");
            System.out.print(this._namespaceStack.get(i - 7));
            System.out.print(" -> ");
            System.out.print(this._namespaceStack.get(i - 8));
            System.out.println();
            System.out.println();
            i -= 8;
        }
    }

    private final String ensureMapping(String uri, String candidatePrefix, boolean considerCreatingDefault, boolean mustHavePrefix) {
        assert (uri != null);
        if (uri.length() == 0) {
            return null;
        }
        String prefix = (String)this._uriMap.get(uri);
        if (!(prefix == null || prefix.length() <= 0 && mustHavePrefix)) {
            return prefix;
        }
        if (candidatePrefix != null && candidatePrefix.length() == 0) {
            candidatePrefix = null;
        }
        if (candidatePrefix == null || !this.tryPrefix(candidatePrefix)) {
            if (this._suggestedPrefixes != null && this._suggestedPrefixes.containsKey(uri) && this.tryPrefix((String)this._suggestedPrefixes.get(uri))) {
                candidatePrefix = (String)this._suggestedPrefixes.get(uri);
            } else if (considerCreatingDefault && this._useDefaultNamespace && this.tryPrefix("")) {
                candidatePrefix = "";
            } else {
                String basePrefix;
                candidatePrefix = basePrefix = QNameHelper.suggestPrefix(uri);
                int i = 1;
                while (!this.tryPrefix(candidatePrefix)) {
                    candidatePrefix = basePrefix + i;
                    ++i;
                }
            }
        }
        assert (candidatePrefix != null);
        this.syntheticNamespace(candidatePrefix, uri, considerCreatingDefault);
        this.addMapping(candidatePrefix, uri);
        return candidatePrefix;
    }

    protected final String getUriMapping(String uri) {
        assert (this._uriMap.get(uri) != null);
        return (String)this._uriMap.get(uri);
    }

    String getNonDefaultUriMapping(String uri) {
        String prefix2 = (String)this._uriMap.get(uri);
        if (prefix2 != null && prefix2.length() > 0) {
            return prefix2;
        }
        for (String prefix2 : this._prefixMap.keySet()) {
            if (prefix2.length() <= 0 || !this._prefixMap.get(prefix2).equals(uri)) continue;
            return prefix2;
        }
        assert (false) : "Could not find non-default mapping";
        return null;
    }

    private final boolean tryPrefix(String prefix) {
        if (prefix == null || Locale.beginsWithXml(prefix)) {
            return false;
        }
        String existingUri = (String)this._prefixMap.get(prefix);
        return existingUri == null || prefix.length() <= 0 && existingUri == this._initialDefaultUri;
    }

    public final String getNamespaceForPrefix(String prefix) {
        assert (!prefix.equals("xml") || this._prefixMap.get(prefix).equals("http://www.w3.org/XML/1998/namespace"));
        return (String)this._prefixMap.get(prefix);
    }

    protected Map getPrefixMap() {
        return this._prefixMap;
    }

    private static final class PrettySaveCur
    extends SaveCur {
        private SaveCur _cur;
        private int _prettyIndent;
        private int _prettyOffset;
        private String _txt;
        private StringBuffer _sb = new StringBuffer();
        private int _depth;
        private ArrayList _stack = new ArrayList();
        private boolean _isTextCData = false;
        private boolean _useCDataBookmarks = false;

        PrettySaveCur(SaveCur c, XmlOptions options) {
            this._cur = c;
            assert (options != null);
            this._prettyIndent = 2;
            if (options.hasOption("SAVE_PRETTY_PRINT_INDENT")) {
                this._prettyIndent = (Integer)options.get("SAVE_PRETTY_PRINT_INDENT");
            }
            if (options.hasOption("SAVE_PRETTY_PRINT_OFFSET")) {
                this._prettyOffset = (Integer)options.get("SAVE_PRETTY_PRINT_OFFSET");
            }
            if (options.hasOption("LOAD_SAVE_CDATA_BOOKMARKS")) {
                this._useCDataBookmarks = true;
            }
        }

        @Override
        List getAncestorNamespaces() {
            return this._cur.getAncestorNamespaces();
        }

        @Override
        void release() {
            this._cur.release();
        }

        @Override
        int kind() {
            return this._txt == null ? this._cur.kind() : 0;
        }

        @Override
        QName getName() {
            assert (this._txt == null);
            return this._cur.getName();
        }

        @Override
        String getXmlnsPrefix() {
            assert (this._txt == null);
            return this._cur.getXmlnsPrefix();
        }

        @Override
        String getXmlnsUri() {
            assert (this._txt == null);
            return this._cur.getXmlnsUri();
        }

        @Override
        boolean isXmlns() {
            return this._txt == null ? this._cur.isXmlns() : false;
        }

        @Override
        boolean hasChildren() {
            return this._txt == null ? this._cur.hasChildren() : false;
        }

        @Override
        boolean hasText() {
            return this._txt == null ? this._cur.hasText() : false;
        }

        @Override
        boolean isTextCData() {
            return this._txt == null ? this._useCDataBookmarks && this._cur.isTextCData() : this._isTextCData;
        }

        @Override
        boolean toFirstAttr() {
            assert (this._txt == null);
            return this._cur.toFirstAttr();
        }

        @Override
        boolean toNextAttr() {
            assert (this._txt == null);
            return this._cur.toNextAttr();
        }

        @Override
        String getAttrValue() {
            assert (this._txt == null);
            return this._cur.getAttrValue();
        }

        @Override
        void toEnd() {
            assert (this._txt == null);
            this._cur.toEnd();
            if (this._cur.kind() == -2) {
                --this._depth;
            }
        }

        @Override
        boolean next() {
            int k;
            if (this._txt != null) {
                assert (this._txt.length() > 0);
                assert (!this._cur.isText());
                this._txt = null;
                this._isTextCData = false;
                k = this._cur.kind();
            } else {
                int prevKind = k = this._cur.kind();
                if (!this._cur.next()) {
                    return false;
                }
                this._sb.delete(0, this._sb.length());
                assert (this._txt == null);
                if (this._cur.isText()) {
                    this._isTextCData = this._useCDataBookmarks && this._cur.isTextCData();
                    CharUtil.getString(this._sb, this._cur.getChars(), this._cur._offSrc, this._cur._cchSrc);
                    this._cur.next();
                    PrettySaveCur.trim(this._sb);
                }
                k = this._cur.kind();
                if (this._prettyIndent >= 0 && prevKind != 4 && prevKind != 5 && (prevKind != 2 || k != -2)) {
                    if (this._sb.length() > 0) {
                        this._sb.insert(0, _newLine);
                        PrettySaveCur.spaces(this._sb, _newLine.length(), this._prettyOffset + this._prettyIndent * this._depth);
                    }
                    if (k != -1) {
                        if (prevKind != 1) {
                            this._sb.append(_newLine);
                        }
                        int d = k < 0 ? this._depth - 1 : this._depth;
                        PrettySaveCur.spaces(this._sb, this._sb.length(), this._prettyOffset + this._prettyIndent * d);
                    }
                }
                if (this._sb.length() > 0) {
                    this._txt = this._sb.toString();
                    k = 0;
                }
            }
            if (k == 2) {
                ++this._depth;
            } else if (k == -2) {
                --this._depth;
            }
            return true;
        }

        @Override
        void push() {
            this._cur.push();
            this._stack.add(this._txt);
            this._stack.add(new Integer(this._depth));
            this._isTextCData = false;
        }

        @Override
        void pop() {
            this._cur.pop();
            this._depth = (Integer)this._stack.remove(this._stack.size() - 1);
            this._txt = (String)this._stack.remove(this._stack.size() - 1);
            this._isTextCData = false;
        }

        @Override
        Object getChars() {
            if (this._txt != null) {
                this._offSrc = 0;
                this._cchSrc = this._txt.length();
                return this._txt;
            }
            Object o = this._cur.getChars();
            this._offSrc = this._cur._offSrc;
            this._cchSrc = this._cur._cchSrc;
            return o;
        }

        @Override
        XmlDocumentProperties getDocProps() {
            return this._cur.getDocProps();
        }

        static void spaces(StringBuffer sb, int offset, int count) {
            while (count-- > 0) {
                sb.insert(offset, ' ');
            }
        }

        static void trim(StringBuffer sb) {
            int i;
            for (i = 0; i < sb.length() && CharUtil.isWhiteSpace(sb.charAt(i)); ++i) {
            }
            sb.delete(0, i);
            for (i = sb.length(); i > 0 && CharUtil.isWhiteSpace(sb.charAt(i - 1)); --i) {
            }
            sb.delete(i, sb.length());
        }
    }

    private static final class FragSaveCur
    extends SaveCur {
        private Cur _cur;
        private Cur _end;
        private ArrayList _ancestorNamespaces;
        private QName _elem;
        private boolean _saveAttr;
        private static final int ROOT_START = 1;
        private static final int ELEM_START = 2;
        private static final int ROOT_END = 3;
        private static final int ELEM_END = 4;
        private static final int CUR = 5;
        private int _state;
        private int[] _stateStack;
        private int _stateStackSize;

        FragSaveCur(Cur start, Cur end, QName synthElem) {
            this._saveAttr = start.isAttr() && start.isSamePos(end);
            this._cur = start.weakCur(this);
            this._end = end.weakCur(this);
            this._elem = synthElem;
            this._state = 1;
            this._stateStack = new int[8];
            start.push();
            this.computeAncestorNamespaces(start);
            start.pop();
        }

        @Override
        List getAncestorNamespaces() {
            return this._ancestorNamespaces;
        }

        private void computeAncestorNamespaces(Cur c) {
            this._ancestorNamespaces = new ArrayList();
            while (c.toParentRaw()) {
                if (!c.toFirstAttr()) continue;
                do {
                    if (!c.isXmlns()) continue;
                    String prefix = c.getXmlnsPrefix();
                    String uri = c.getXmlnsUri();
                    if (uri.length() <= 0 && prefix.length() != 0) continue;
                    this._ancestorNamespaces.add(c.getXmlnsPrefix());
                    this._ancestorNamespaces.add(c.getXmlnsUri());
                } while (c.toNextAttr());
                c.toParent();
            }
        }

        @Override
        void release() {
            this._cur.release();
            this._cur = null;
            this._end.release();
            this._end = null;
        }

        @Override
        int kind() {
            switch (this._state) {
                case 1: {
                    return 1;
                }
                case 2: {
                    return 2;
                }
                case 4: {
                    return -2;
                }
                case 3: {
                    return -1;
                }
            }
            assert (this._state == 5);
            return this._cur.kind();
        }

        @Override
        QName getName() {
            switch (this._state) {
                case 1: 
                case 3: {
                    return null;
                }
                case 2: 
                case 4: {
                    return this._elem;
                }
            }
            assert (this._state == 5);
            return this._cur.getName();
        }

        @Override
        String getXmlnsPrefix() {
            assert (this._state == 5 && this._cur.isAttr());
            return this._cur.getXmlnsPrefix();
        }

        @Override
        String getXmlnsUri() {
            assert (this._state == 5 && this._cur.isAttr());
            return this._cur.getXmlnsUri();
        }

        @Override
        boolean isXmlns() {
            assert (this._state == 5 && this._cur.isAttr());
            return this._cur.isXmlns();
        }

        @Override
        boolean hasChildren() {
            boolean hasChildren = false;
            if (this.isContainer()) {
                this.push();
                this.next();
                if (!this.isText() && !this.isFinish()) {
                    hasChildren = true;
                }
                this.pop();
            }
            return hasChildren;
        }

        @Override
        boolean hasText() {
            boolean hasText = false;
            if (this.isContainer()) {
                this.push();
                this.next();
                if (this.isText()) {
                    hasText = true;
                }
                this.pop();
            }
            return hasText;
        }

        @Override
        boolean isTextCData() {
            return this._cur.isTextCData();
        }

        @Override
        Object getChars() {
            assert (this._state == 5 && this._cur.isText());
            Object src = this._cur.getChars(-1);
            this._offSrc = this._cur._offSrc;
            this._cchSrc = this._cur._cchSrc;
            return src;
        }

        @Override
        boolean next() {
            switch (this._state) {
                case 1: {
                    this._state = this._elem == null ? 5 : 2;
                    break;
                }
                case 2: {
                    if (this._saveAttr) {
                        this._state = 4;
                        break;
                    }
                    if (this._cur.isAttr()) {
                        this._cur.toParent();
                        this._cur.next();
                    }
                    if (this._cur.isSamePos(this._end)) {
                        this._state = 4;
                        break;
                    }
                    this._state = 5;
                    break;
                }
                case 5: {
                    assert (!this._cur.isAttr());
                    this._cur.next();
                    if (!this._cur.isSamePos(this._end)) break;
                    this._state = this._elem == null ? 3 : 4;
                    break;
                }
                case 4: {
                    this._state = 3;
                    break;
                }
                case 3: {
                    return false;
                }
            }
            return true;
        }

        @Override
        void toEnd() {
            switch (this._state) {
                case 1: {
                    this._state = 3;
                    return;
                }
                case 2: {
                    this._state = 4;
                    return;
                }
                case 3: 
                case 4: {
                    return;
                }
            }
            assert (this._state == 5 && !this._cur.isAttr() && !this._cur.isText());
            this._cur.toEnd();
        }

        @Override
        boolean toFirstAttr() {
            switch (this._state) {
                case 1: 
                case 3: 
                case 4: {
                    return false;
                }
                case 5: {
                    return this._cur.toFirstAttr();
                }
            }
            assert (this._state == 2);
            if (!this._cur.isAttr()) {
                return false;
            }
            this._state = 5;
            return true;
        }

        @Override
        boolean toNextAttr() {
            assert (this._state == 5);
            return !this._saveAttr && this._cur.toNextAttr();
        }

        @Override
        String getAttrValue() {
            assert (this._state == 5 && this._cur.isAttr());
            return this._cur.getValueAsString();
        }

        @Override
        void push() {
            if (this._stateStackSize == this._stateStack.length) {
                int[] newStateStack = new int[this._stateStackSize * 2];
                System.arraycopy(this._stateStack, 0, newStateStack, 0, this._stateStackSize);
                this._stateStack = newStateStack;
            }
            this._stateStack[this._stateStackSize++] = this._state;
            this._cur.push();
        }

        @Override
        void pop() {
            this._cur.pop();
            this._state = this._stateStack[--this._stateStackSize];
        }

        @Override
        XmlDocumentProperties getDocProps() {
            return Locale.getDocProps(this._cur, false);
        }
    }

    private static final class FilterPiSaveCur
    extends FilterSaveCur {
        private String _piTarget;

        FilterPiSaveCur(SaveCur c, String target) {
            super(c);
            this._piTarget = target;
        }

        @Override
        protected boolean filter() {
            return this.kind() == 5 && this.getName().getLocalPart().equals(this._piTarget);
        }
    }

    private static abstract class FilterSaveCur
    extends SaveCur {
        private SaveCur _cur;

        FilterSaveCur(SaveCur c) {
            assert (c.isRoot());
            this._cur = c;
        }

        protected abstract boolean filter();

        @Override
        void release() {
            this._cur.release();
            this._cur = null;
        }

        @Override
        int kind() {
            return this._cur.kind();
        }

        @Override
        QName getName() {
            return this._cur.getName();
        }

        @Override
        String getXmlnsPrefix() {
            return this._cur.getXmlnsPrefix();
        }

        @Override
        String getXmlnsUri() {
            return this._cur.getXmlnsUri();
        }

        @Override
        boolean isXmlns() {
            return this._cur.isXmlns();
        }

        @Override
        boolean hasChildren() {
            return this._cur.hasChildren();
        }

        @Override
        boolean hasText() {
            return this._cur.hasText();
        }

        @Override
        boolean isTextCData() {
            return this._cur.isTextCData();
        }

        @Override
        boolean toFirstAttr() {
            return this._cur.toFirstAttr();
        }

        @Override
        boolean toNextAttr() {
            return this._cur.toNextAttr();
        }

        @Override
        String getAttrValue() {
            return this._cur.getAttrValue();
        }

        @Override
        void toEnd() {
            this._cur.toEnd();
        }

        @Override
        boolean next() {
            if (!this._cur.next()) {
                return false;
            }
            if (!this.filter()) {
                return true;
            }
            assert (!(this.isRoot() || this.isText() || this.isAttr()));
            this.toEnd();
            return this.next();
        }

        @Override
        void push() {
            this._cur.push();
        }

        @Override
        void pop() {
            this._cur.pop();
        }

        @Override
        List getAncestorNamespaces() {
            return this._cur.getAncestorNamespaces();
        }

        @Override
        Object getChars() {
            Object o = this._cur.getChars();
            this._offSrc = this._cur._offSrc;
            this._cchSrc = this._cur._cchSrc;
            return o;
        }

        @Override
        XmlDocumentProperties getDocProps() {
            return this._cur.getDocProps();
        }
    }

    private static final class DocSaveCur
    extends SaveCur {
        private Cur _cur;

        DocSaveCur(Cur c) {
            assert (c.isRoot());
            this._cur = c.weakCur(this);
        }

        @Override
        void release() {
            this._cur.release();
            this._cur = null;
        }

        @Override
        int kind() {
            return this._cur.kind();
        }

        @Override
        QName getName() {
            return this._cur.getName();
        }

        @Override
        String getXmlnsPrefix() {
            return this._cur.getXmlnsPrefix();
        }

        @Override
        String getXmlnsUri() {
            return this._cur.getXmlnsUri();
        }

        @Override
        boolean isXmlns() {
            return this._cur.isXmlns();
        }

        @Override
        boolean hasChildren() {
            return this._cur.hasChildren();
        }

        @Override
        boolean hasText() {
            return this._cur.hasText();
        }

        @Override
        boolean isTextCData() {
            return this._cur.isTextCData();
        }

        @Override
        boolean toFirstAttr() {
            return this._cur.toFirstAttr();
        }

        @Override
        boolean toNextAttr() {
            return this._cur.toNextAttr();
        }

        @Override
        String getAttrValue() {
            assert (this._cur.isAttr());
            return this._cur.getValueAsString();
        }

        @Override
        void toEnd() {
            this._cur.toEnd();
        }

        @Override
        boolean next() {
            return this._cur.next();
        }

        @Override
        void push() {
            this._cur.push();
        }

        @Override
        void pop() {
            this._cur.pop();
        }

        @Override
        List getAncestorNamespaces() {
            return null;
        }

        @Override
        Object getChars() {
            Object o = this._cur.getChars(-1);
            this._offSrc = this._cur._offSrc;
            this._cchSrc = this._cur._cchSrc;
            return o;
        }

        @Override
        XmlDocumentProperties getDocProps() {
            return Locale.getDocProps(this._cur, false);
        }
    }

    static abstract class SaveCur {
        int _offSrc;
        int _cchSrc;

        SaveCur() {
        }

        final boolean isRoot() {
            return this.kind() == 1;
        }

        final boolean isElem() {
            return this.kind() == 2;
        }

        final boolean isAttr() {
            return this.kind() == 3;
        }

        final boolean isText() {
            return this.kind() == 0;
        }

        final boolean isComment() {
            return this.kind() == 4;
        }

        final boolean isProcinst() {
            return this.kind() == 5;
        }

        final boolean isFinish() {
            return Cur.kindIsFinish(this.kind());
        }

        final boolean isContainer() {
            return Cur.kindIsContainer(this.kind());
        }

        final boolean isNormalAttr() {
            return this.kind() == 3 && !this.isXmlns();
        }

        final boolean skip() {
            this.toEnd();
            return this.next();
        }

        abstract void release();

        abstract int kind();

        abstract QName getName();

        abstract String getXmlnsPrefix();

        abstract String getXmlnsUri();

        abstract boolean isXmlns();

        abstract boolean hasChildren();

        abstract boolean hasText();

        abstract boolean isTextCData();

        abstract boolean toFirstAttr();

        abstract boolean toNextAttr();

        abstract String getAttrValue();

        abstract boolean next();

        abstract void toEnd();

        abstract void push();

        abstract void pop();

        abstract Object getChars();

        abstract List getAncestorNamespaces();

        abstract XmlDocumentProperties getDocProps();
    }

    static final class SaxSaver
    extends Saver {
        private ContentHandler _contentHandler;
        private LexicalHandler _lexicalHandler;
        private AttributesImpl _attributes;
        private char[] _buf;
        private boolean _nsAsAttrs;

        SaxSaver(Cur c, XmlOptions options, ContentHandler ch, LexicalHandler lh) throws SAXException {
            super(c, options);
            this._contentHandler = ch;
            this._lexicalHandler = lh;
            this._attributes = new AttributesImpl();
            this._nsAsAttrs = !options.hasOption("SAVE_SAX_NO_NSDECLS_IN_ATTRIBUTES");
            this._contentHandler.startDocument();
            try {
                while (this.process()) {
                }
            } catch (SaverSAXException e) {
                throw e._saxException;
            }
            this._contentHandler.endDocument();
        }

        private String getPrefixedName(QName name) {
            String uri = name.getNamespaceURI();
            String local = name.getLocalPart();
            if (uri.length() == 0) {
                return local;
            }
            String prefix = this.getUriMapping(uri);
            if (prefix.length() == 0) {
                return local;
            }
            return prefix + ":" + local;
        }

        private void emitNamespacesHelper() {
            this.iterateMappings();
            while (this.hasMapping()) {
                String prefix = this.mappingPrefix();
                String uri = this.mappingUri();
                try {
                    this._contentHandler.startPrefixMapping(prefix, uri);
                } catch (SAXException e) {
                    throw new SaverSAXException(e);
                }
                if (this._nsAsAttrs) {
                    if (prefix == null || prefix.length() == 0) {
                        this._attributes.addAttribute("http://www.w3.org/2000/xmlns/", "xmlns", "xmlns", "CDATA", uri);
                    } else {
                        this._attributes.addAttribute("http://www.w3.org/2000/xmlns/", prefix, "xmlns:" + prefix, "CDATA", uri);
                    }
                }
                this.nextMapping();
            }
        }

        @Override
        protected boolean emitElement(SaveCur c, ArrayList attrNames, ArrayList attrValues) {
            this._attributes.clear();
            if (this.saveNamespacesFirst()) {
                this.emitNamespacesHelper();
            }
            for (int i = 0; i < attrNames.size(); ++i) {
                QName name = (QName)attrNames.get(i);
                this._attributes.addAttribute(name.getNamespaceURI(), name.getLocalPart(), this.getPrefixedName(name), "CDATA", (String)attrValues.get(i));
            }
            if (!this.saveNamespacesFirst()) {
                this.emitNamespacesHelper();
            }
            QName elemName = c.getName();
            try {
                this._contentHandler.startElement(elemName.getNamespaceURI(), elemName.getLocalPart(), this.getPrefixedName(elemName), this._attributes);
            } catch (SAXException e) {
                throw new SaverSAXException(e);
            }
            return false;
        }

        @Override
        protected void emitFinish(SaveCur c) {
            QName name = c.getName();
            try {
                this._contentHandler.endElement(name.getNamespaceURI(), name.getLocalPart(), this.getPrefixedName(name));
                this.iterateMappings();
                while (this.hasMapping()) {
                    this._contentHandler.endPrefixMapping(this.mappingPrefix());
                    this.nextMapping();
                }
            } catch (SAXException e) {
                throw new SaverSAXException(e);
            }
        }

        @Override
        protected void emitText(SaveCur c) {
            assert (c.isText());
            Object src = c.getChars();
            try {
                if (src instanceof char[]) {
                    this._contentHandler.characters((char[])src, c._offSrc, c._cchSrc);
                } else {
                    if (this._buf == null) {
                        this._buf = new char[1024];
                    }
                    while (c._cchSrc > 0) {
                        int cch = Math.min(this._buf.length, c._cchSrc);
                        CharUtil.getChars(this._buf, 0, src, c._offSrc, cch);
                        this._contentHandler.characters(this._buf, 0, cch);
                        c._offSrc += cch;
                        c._cchSrc -= cch;
                    }
                }
            } catch (SAXException e) {
                throw new SaverSAXException(e);
            }
        }

        @Override
        protected void emitComment(SaveCur c) {
            if (this._lexicalHandler != null) {
                c.push();
                c.next();
                try {
                    if (!c.isText()) {
                        this._lexicalHandler.comment(null, 0, 0);
                    } else {
                        Object src = c.getChars();
                        if (src instanceof char[]) {
                            this._lexicalHandler.comment((char[])src, c._offSrc, c._cchSrc);
                        } else {
                            if (this._buf == null || this._buf.length < c._cchSrc) {
                                this._buf = new char[Math.max(1024, c._cchSrc)];
                            }
                            CharUtil.getChars(this._buf, 0, src, c._offSrc, c._cchSrc);
                            this._lexicalHandler.comment(this._buf, 0, c._cchSrc);
                        }
                    }
                } catch (SAXException e) {
                    throw new SaverSAXException(e);
                }
                c.pop();
            }
        }

        @Override
        protected void emitProcinst(SaveCur c) {
            String target = c.getName().getLocalPart();
            c.push();
            c.next();
            String value = CharUtil.getString(c.getChars(), c._offSrc, c._cchSrc);
            c.pop();
            try {
                this._contentHandler.processingInstruction(c.getName().getLocalPart(), value);
            } catch (SAXException e) {
                throw new SaverSAXException(e);
            }
        }

        @Override
        protected void emitDocType(String docTypeName, String publicId, String systemId) {
            if (this._lexicalHandler != null) {
                try {
                    this._lexicalHandler.startDTD(docTypeName, publicId, systemId);
                    this._lexicalHandler.endDTD();
                } catch (SAXException e) {
                    throw new SaverSAXException(e);
                }
            }
        }

        @Override
        protected void emitStartDoc(SaveCur c) {
        }

        @Override
        protected void emitEndDoc(SaveCur c) {
        }

        private class SaverSAXException
        extends RuntimeException {
            SAXException _saxException;

            SaverSAXException(SAXException e) {
                this._saxException = e;
            }
        }
    }

    static final class XmlInputStreamImpl
    extends GenericXmlInputStream {
        private XmlInputStreamSaver _xmlInputStreamSaver;

        XmlInputStreamImpl(Cur cur, XmlOptions options) {
            this._xmlInputStreamSaver = new XmlInputStreamSaver(cur, options);
            this._xmlInputStreamSaver.process();
        }

        @Override
        protected XMLEvent nextEvent() throws XMLStreamException {
            return this._xmlInputStreamSaver.dequeue();
        }
    }

    static final class XmlInputStreamSaver
    extends Saver {
        private XmlEventImpl _in;
        private XmlEventImpl _out;

        XmlInputStreamSaver(Cur c, XmlOptions options) {
            super(c, options);
        }

        @Override
        protected boolean emitElement(SaveCur c, ArrayList attrNames, ArrayList attrValues) {
            StartElementImpl.AttributeImpl attr;
            assert (c.isElem());
            this.iterateMappings();
            while (this.hasMapping()) {
                this.enqueue(new StartPrefixMappingImpl(this.mappingPrefix(), this.mappingUri()));
                this.nextMapping();
            }
            StartElementImpl.AttributeImpl lastAttr = null;
            StartElementImpl.AttributeImpl attributes = null;
            StartElementImpl.AttributeImpl namespaces = null;
            for (int i = 0; i < attrNames.size(); ++i) {
                XMLName attXMLName = XmlInputStreamSaver.computeName((QName)attrNames.get(i), this, true);
                attr = new StartElementImpl.NormalAttributeImpl(attXMLName, (String)attrValues.get(i));
                if (attributes == null) {
                    attributes = attr;
                } else {
                    lastAttr._next = attr;
                }
                lastAttr = attr;
            }
            lastAttr = null;
            this.iterateMappings();
            while (this.hasMapping()) {
                String prefix = this.mappingPrefix();
                String uri = this.mappingUri();
                attr = new StartElementImpl.XmlnsAttributeImpl(prefix, uri);
                if (namespaces == null) {
                    namespaces = attr;
                } else {
                    lastAttr._next = attr;
                }
                lastAttr = attr;
                this.nextMapping();
            }
            QName name = c.getName();
            this.enqueue(new StartElementImpl(XmlInputStreamSaver.computeName(name, this, false), attributes, namespaces, this.getPrefixMap()));
            return false;
        }

        @Override
        protected void emitFinish(SaveCur c) {
            if (c.isRoot()) {
                this.enqueue(new EndDocumentImpl());
            } else {
                XMLName xmlName = XmlInputStreamSaver.computeName(c.getName(), this, false);
                this.enqueue(new EndElementImpl(xmlName));
            }
            this.emitEndPrefixMappings();
        }

        @Override
        protected void emitText(SaveCur c) {
            assert (c.isText());
            Object src = c.getChars();
            int cch = c._cchSrc;
            int off = c._offSrc;
            this.enqueue(new CharacterDataImpl(src, cch, off));
        }

        @Override
        protected void emitComment(SaveCur c) {
            this.enqueue(new CommentImpl(c.getChars(), c._cchSrc, c._offSrc));
        }

        @Override
        protected void emitProcinst(SaveCur c) {
            String target = null;
            QName name = c.getName();
            if (name != null) {
                target = name.getLocalPart();
            }
            this.enqueue(new ProcessingInstructionImpl(target, c.getChars(), c._cchSrc, c._offSrc));
        }

        @Override
        protected void emitDocType(String doctypeName, String publicID, String systemID) {
            this.enqueue(new StartDocumentImpl(systemID, null, true, null));
        }

        @Override
        protected void emitStartDoc(SaveCur c) {
            this.emitDocType(null, null, null);
        }

        @Override
        protected void emitEndDoc(SaveCur c) {
            this.enqueue(new EndDocumentImpl());
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        XMLEvent dequeue() {
            if (this._out == null) {
                this.enterLocale();
                try {
                    if (!this.process()) {
                        XMLEvent xMLEvent = null;
                        return xMLEvent;
                    }
                } finally {
                    this.exitLocale();
                }
            }
            if (this._out == null) {
                return null;
            }
            XmlEventImpl e = this._out;
            this._out = this._out._next;
            if (this._out == null) {
                this._in = null;
            }
            return e;
        }

        private void enqueue(XmlEventImpl e) {
            assert (e._next == null);
            if (this._in == null) {
                assert (this._out == null);
                this._out = this._in = e;
            } else {
                this._in._next = e;
                this._in = e;
            }
        }

        protected void emitEndPrefixMappings() {
            this.iterateMappings();
            while (this.hasMapping()) {
                String prevPrefixUri = null;
                String prefix = this.mappingPrefix();
                String uri = this.mappingUri();
                if (prevPrefixUri == null) {
                    this.enqueue(new EndPrefixMappingImpl(prefix));
                } else {
                    this.enqueue(new ChangePrefixMappingImpl(prefix, uri, prevPrefixUri));
                }
                this.nextMapping();
            }
        }

        private static XMLName computeName(QName name, Saver saver, boolean needsPrefix) {
            String uri = name.getNamespaceURI();
            String local = name.getLocalPart();
            assert (uri != null);
            assert (local.length() > 0);
            String prefix = null;
            if (uri != null && uri.length() != 0) {
                prefix = name.getPrefix();
                String mappedUri = saver.getNamespaceForPrefix(prefix);
                if (mappedUri == null || !mappedUri.equals(uri)) {
                    prefix = saver.getUriMapping(uri);
                }
                if (needsPrefix && prefix.length() == 0) {
                    prefix = saver.getNonDefaultUriMapping(uri);
                }
            }
            return new XmlNameImpl(uri, local, prefix);
        }

        private static class ProcessingInstructionImpl
        extends TripletEventImpl
        implements ProcessingInstruction {
            private String _target;

            ProcessingInstructionImpl(String target, Object obj, int cch, int off) {
                super(8, obj, cch, off);
                this._target = target;
            }

            @Override
            public String getTarget() {
                return this._target;
            }

            @Override
            public String getData() {
                return this.getContent();
            }
        }

        private static class CommentImpl
        extends TripletEventImpl
        implements Comment {
            CommentImpl(Object obj, int cch, int off) {
                super(32, obj, cch, off);
            }
        }

        private static class CharacterDataImpl
        extends TripletEventImpl
        implements CharacterData {
            CharacterDataImpl(Object obj, int cch, int off) {
                super(16, obj, cch, off);
            }
        }

        private static class TripletEventImpl
        extends XmlEventImpl
        implements CharacterData {
            private Object _obj;
            private int _cch;
            private int _off;

            TripletEventImpl(int eventType, Object obj, int cch, int off) {
                super(eventType);
                this._obj = obj;
                this._cch = cch;
                this._off = off;
            }

            @Override
            public String getContent() {
                return CharUtil.getString(this._obj, this._off, this._cch);
            }

            @Override
            public boolean hasContent() {
                return this._cch > 0;
            }
        }

        private static class EndDocumentImpl
        extends XmlEventImpl
        implements EndDocument {
            EndDocumentImpl() {
                super(512);
            }
        }

        private static class EndElementImpl
        extends XmlEventImpl
        implements EndElement {
            private XMLName _name;

            EndElementImpl(XMLName name) {
                super(4);
                this._name = name;
            }

            @Override
            public boolean hasName() {
                return true;
            }

            @Override
            public XMLName getName() {
                return this._name;
            }
        }

        private static class EndPrefixMappingImpl
        extends XmlEventImpl
        implements EndPrefixMapping {
            private String _prefix;

            EndPrefixMappingImpl(String prefix) {
                super(2048);
                this._prefix = prefix;
            }

            @Override
            public String getPrefix() {
                return this._prefix;
            }
        }

        private static class ChangePrefixMappingImpl
        extends XmlEventImpl
        implements ChangePrefixMapping {
            private String _oldUri;
            private String _newUri;
            private String _prefix;

            ChangePrefixMappingImpl(String prefix, String oldUri, String newUri) {
                super(4096);
                this._oldUri = oldUri;
                this._newUri = newUri;
                this._prefix = prefix;
            }

            @Override
            public String getOldNamespaceUri() {
                return this._oldUri;
            }

            @Override
            public String getNewNamespaceUri() {
                return this._newUri;
            }

            @Override
            public String getPrefix() {
                return this._prefix;
            }
        }

        private static class StartPrefixMappingImpl
        extends XmlEventImpl
        implements StartPrefixMapping {
            private String _prefix;
            private String _uri;

            StartPrefixMappingImpl(String prefix, String uri) {
                super(1024);
                this._prefix = prefix;
                this._uri = uri;
            }

            @Override
            public String getNamespaceUri() {
                return this._uri;
            }

            @Override
            public String getPrefix() {
                return this._prefix;
            }
        }

        private static class StartElementImpl
        extends XmlEventImpl
        implements StartElement {
            private XMLName _name;
            private Map _prefixMap;
            private AttributeImpl _attributes;
            private AttributeImpl _namespaces;

            StartElementImpl(XMLName name, AttributeImpl attributes, AttributeImpl namespaces, Map prefixMap) {
                super(2);
                this._name = name;
                this._attributes = attributes;
                this._namespaces = namespaces;
                this._prefixMap = prefixMap;
            }

            @Override
            public boolean hasName() {
                return true;
            }

            @Override
            public XMLName getName() {
                return this._name;
            }

            @Override
            public AttributeIterator getAttributes() {
                return new AttributeIteratorImpl(this._attributes, null);
            }

            @Override
            public AttributeIterator getNamespaces() {
                return new AttributeIteratorImpl(null, this._namespaces);
            }

            @Override
            public AttributeIterator getAttributesAndNamespaces() {
                return new AttributeIteratorImpl(this._attributes, this._namespaces);
            }

            @Override
            public Attribute getAttributeByName(XMLName xmlName) {
                AttributeImpl a = this._attributes;
                while (a != null) {
                    if (xmlName.equals(a.getName())) {
                        return a;
                    }
                    a = a._next;
                }
                return null;
            }

            @Override
            public String getNamespaceUri(String prefix) {
                return (String)this._prefixMap.get(prefix == null ? "" : prefix);
            }

            @Override
            public Map getNamespaceMap() {
                return this._prefixMap;
            }

            private static class NormalAttributeImpl
            extends AttributeImpl {
                private String _value;

                NormalAttributeImpl(XMLName name, String value) {
                    this._name = name;
                    this._value = value;
                }

                @Override
                public String getValue() {
                    return this._value;
                }
            }

            private static class XmlnsAttributeImpl
            extends AttributeImpl {
                private String _uri;

                XmlnsAttributeImpl(String prefix, String uri) {
                    String local;
                    this._uri = uri;
                    if (prefix.length() == 0) {
                        prefix = null;
                        local = "xmlns";
                    } else {
                        local = prefix;
                        prefix = "xmlns";
                    }
                    this._name = new XmlNameImpl(null, local, prefix);
                }

                @Override
                public String getValue() {
                    return this._uri;
                }
            }

            private static abstract class AttributeImpl
            implements Attribute {
                AttributeImpl _next;
                protected XMLName _name;

                AttributeImpl() {
                }

                @Override
                public XMLName getName() {
                    return this._name;
                }

                @Override
                public String getType() {
                    return "CDATA";
                }

                @Override
                public XMLName getSchemaType() {
                    return null;
                }
            }

            private static class AttributeIteratorImpl
            implements AttributeIterator {
                private AttributeImpl _attributes;
                private AttributeImpl _namespaces;

                AttributeIteratorImpl(AttributeImpl attributes, AttributeImpl namespaces) {
                    this._attributes = attributes;
                    this._namespaces = namespaces;
                }

                public Object monitor() {
                    return this;
                }

                /*
                 * WARNING - Removed try catching itself - possible behaviour change.
                 */
                @Override
                public Attribute next() {
                    Object object = this.monitor();
                    synchronized (object) {
                        this.checkVersion();
                        AttributeImpl attr = null;
                        if (this._attributes != null) {
                            attr = this._attributes;
                            this._attributes = attr._next;
                        } else if (this._namespaces != null) {
                            attr = this._namespaces;
                            this._namespaces = attr._next;
                        }
                        return attr;
                    }
                }

                /*
                 * WARNING - Removed try catching itself - possible behaviour change.
                 */
                @Override
                public boolean hasNext() {
                    Object object = this.monitor();
                    synchronized (object) {
                        this.checkVersion();
                        return this._attributes != null || this._namespaces != null;
                    }
                }

                /*
                 * WARNING - Removed try catching itself - possible behaviour change.
                 */
                @Override
                public Attribute peek() {
                    Object object = this.monitor();
                    synchronized (object) {
                        this.checkVersion();
                        if (this._attributes != null) {
                            return this._attributes;
                        }
                        if (this._namespaces != null) {
                            return this._namespaces;
                        }
                        return null;
                    }
                }

                /*
                 * WARNING - Removed try catching itself - possible behaviour change.
                 */
                @Override
                public void skip() {
                    Object object = this.monitor();
                    synchronized (object) {
                        this.checkVersion();
                        if (this._attributes != null) {
                            this._attributes = this._attributes._next;
                        } else if (this._namespaces != null) {
                            this._namespaces = this._namespaces._next;
                        }
                    }
                }

                private final void checkVersion() {
                }
            }
        }

        private static class StartDocumentImpl
        extends XmlEventImpl
        implements StartDocument {
            String _systemID;
            String _encoding;
            boolean _standAlone;
            String _version;

            StartDocumentImpl(String systemID, String encoding, boolean isStandAlone, String version) {
                super(256);
                this._systemID = systemID;
                this._encoding = encoding;
                this._standAlone = isStandAlone;
                this._version = version;
            }

            @Override
            public String getSystemId() {
                return this._systemID;
            }

            @Override
            public String getCharacterEncodingScheme() {
                return this._encoding;
            }

            @Override
            public boolean isStandalone() {
                return this._standAlone;
            }

            @Override
            public String getVersion() {
                return this._version;
            }
        }

        private static abstract class XmlEventImpl
        extends XmlEventBase {
            XmlEventImpl _next;

            XmlEventImpl(int type) {
                super(type);
            }

            @Override
            public XMLName getName() {
                return null;
            }

            @Override
            public XMLName getSchemaType() {
                throw new RuntimeException("NYI");
            }

            @Override
            public boolean hasName() {
                return false;
            }

            @Override
            public final Location getLocation() {
                return null;
            }
        }
    }

    static final class InputStreamSaver
    extends InputStream {
        private Locale _locale;
        private boolean _closed;
        private OutputStreamImpl _outStreamImpl;
        private TextSaver _textSaver;
        private OutputStreamWriter _converter;

        InputStreamSaver(Cur c, XmlOptions options) {
            String javaEncoding;
            String ianaEncoding;
            this._locale = c._locale;
            this._closed = false;
            assert (this._locale.entered());
            options = XmlOptions.maskNull(options);
            this._outStreamImpl = new OutputStreamImpl();
            String encoding = null;
            XmlDocumentProperties props = Locale.getDocProps(c, false);
            if (props != null && props.getEncoding() != null) {
                encoding = EncodingMap.getIANA2JavaMapping(props.getEncoding());
            }
            if (options.hasOption("CHARACTER_ENCODING")) {
                encoding = (String)options.get("CHARACTER_ENCODING");
            }
            if (encoding != null && (ianaEncoding = EncodingMap.getJava2IANAMapping(encoding)) != null) {
                encoding = ianaEncoding;
            }
            if (encoding == null) {
                encoding = EncodingMap.getJava2IANAMapping("UTF8");
            }
            if ((javaEncoding = EncodingMap.getIANA2JavaMapping(encoding)) == null) {
                throw new IllegalStateException("Unknown encoding: " + encoding);
            }
            try {
                this._converter = new OutputStreamWriter((OutputStream)this._outStreamImpl, javaEncoding);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            this._textSaver = new TextSaver(c, options, encoding);
        }

        @Override
        public void close() throws IOException {
            this._closed = true;
        }

        private void checkClosed() throws IOException {
            if (this._closed) {
                throw new IOException("Stream closed");
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int read() throws IOException {
            this.checkClosed();
            if (this._locale.noSync()) {
                this._locale.enter();
                try {
                    int n = this._outStreamImpl.read();
                    return n;
                } finally {
                    this._locale.exit();
                }
            }
            Locale locale = this._locale;
            synchronized (locale) {
                int n;
                this._locale.enter();
                try {
                    n = this._outStreamImpl.read();
                    this._locale.exit();
                } catch (Throwable throwable) {
                    this._locale.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int read(byte[] bbuf, int off, int len) throws IOException {
            this.checkClosed();
            if (bbuf == null) {
                throw new NullPointerException("buf to read into is null");
            }
            if (off < 0 || off > bbuf.length) {
                throw new IndexOutOfBoundsException("Offset is not within buf");
            }
            if (this._locale.noSync()) {
                this._locale.enter();
                try {
                    int n = this._outStreamImpl.read(bbuf, off, len);
                    return n;
                } finally {
                    this._locale.exit();
                }
            }
            Locale locale = this._locale;
            synchronized (locale) {
                int n;
                this._locale.enter();
                try {
                    n = this._outStreamImpl.read(bbuf, off, len);
                    this._locale.exit();
                } catch (Throwable throwable) {
                    this._locale.exit();
                    throw throwable;
                }
                return n;
            }
        }

        private int ensure(int cbyte) {
            if (cbyte <= 0) {
                cbyte = 1;
            }
            int bytesAvailable = this._outStreamImpl.getAvailable();
            while (bytesAvailable < cbyte && this._textSaver.write(this._converter, 2048) >= 2048) {
                bytesAvailable = this._outStreamImpl.getAvailable();
            }
            bytesAvailable = this._outStreamImpl.getAvailable();
            return bytesAvailable;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int available() throws IOException {
            if (this._locale.noSync()) {
                this._locale.enter();
                try {
                    int n = this.ensure(1024);
                    return n;
                } finally {
                    this._locale.exit();
                }
            }
            Locale locale = this._locale;
            synchronized (locale) {
                int n;
                this._locale.enter();
                try {
                    n = this.ensure(1024);
                    this._locale.exit();
                } catch (Throwable throwable) {
                    this._locale.exit();
                    throw throwable;
                }
                return n;
            }
        }

        private final class OutputStreamImpl
        extends OutputStream {
            private static final int _initialBufSize = 4096;
            private int _free;
            private int _in;
            private int _out;
            private byte[] _buf;

            private OutputStreamImpl() {
            }

            int read() {
                if (InputStreamSaver.this.ensure(1) == 0) {
                    return -1;
                }
                assert (this.getAvailable() > 0);
                byte bite = this._buf[this._out];
                this._out = (this._out + 1) % this._buf.length;
                ++this._free;
                return bite;
            }

            int read(byte[] bbuf, int off, int len) {
                int n = InputStreamSaver.this.ensure(len);
                if (n == 0) {
                    return -1;
                }
                if (bbuf == null || len <= 0) {
                    return 0;
                }
                if (n < len) {
                    len = n;
                }
                if (this._out < this._in) {
                    System.arraycopy(this._buf, this._out, bbuf, off, len);
                } else {
                    int chunk = this._buf.length - this._out;
                    if (chunk >= len) {
                        System.arraycopy(this._buf, this._out, bbuf, off, len);
                    } else {
                        System.arraycopy(this._buf, this._out, bbuf, off, chunk);
                        System.arraycopy(this._buf, 0, bbuf, off + chunk, len - chunk);
                    }
                }
                this._out = (this._out + len) % this._buf.length;
                this._free += len;
                return len;
            }

            int getAvailable() {
                return this._buf == null ? 0 : this._buf.length - this._free;
            }

            @Override
            public void write(int bite) {
                if (this._free == 0) {
                    this.resize(1);
                }
                assert (this._free > 0);
                this._buf[this._in] = (byte)bite;
                this._in = (this._in + 1) % this._buf.length;
                --this._free;
            }

            @Override
            public void write(byte[] buf, int off, int cbyte) {
                assert (cbyte >= 0);
                if (cbyte == 0) {
                    return;
                }
                if (this._free < cbyte) {
                    this.resize(cbyte);
                }
                if (this._in == this._out) {
                    assert (this.getAvailable() == 0);
                    assert (this._free == this._buf.length - this.getAvailable());
                    this._out = 0;
                    this._in = 0;
                }
                int chunk = this._buf.length - this._in;
                if (this._in <= this._out || cbyte < chunk) {
                    System.arraycopy(buf, off, this._buf, this._in, cbyte);
                    this._in += cbyte;
                } else {
                    System.arraycopy(buf, off, this._buf, this._in, chunk);
                    System.arraycopy(buf, off + chunk, this._buf, 0, cbyte - chunk);
                    this._in = (this._in + cbyte) % this._buf.length;
                }
                this._free -= cbyte;
            }

            void resize(int cbyte) {
                assert (cbyte > this._free) : cbyte + " !> " + this._free;
                int newLen = this._buf == null ? 4096 : this._buf.length * 2;
                int used = this.getAvailable();
                while (newLen - used < cbyte) {
                    newLen *= 2;
                }
                byte[] newBuf = new byte[newLen];
                if (used > 0) {
                    if (this._in > this._out) {
                        System.arraycopy(this._buf, this._out, newBuf, 0, used);
                    } else {
                        System.arraycopy(this._buf, this._out, newBuf, 0, used - this._in);
                        System.arraycopy(this._buf, 0, newBuf, used - this._in, this._in);
                    }
                    this._out = 0;
                    this._in = used;
                    this._free += newBuf.length - this._buf.length;
                } else {
                    this._free = newBuf.length;
                    assert (this._in == this._out);
                }
                this._buf = newBuf;
            }
        }
    }

    static final class TextReader
    extends Reader {
        private Locale _locale;
        private TextSaver _textSaver;
        private boolean _closed;

        TextReader(Cur c, XmlOptions options) {
            this._textSaver = new TextSaver(c, options, null);
            this._locale = c._locale;
            this._closed = false;
        }

        @Override
        public void close() throws IOException {
            this._closed = true;
        }

        @Override
        public boolean ready() throws IOException {
            return !this._closed;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int read() throws IOException {
            this.checkClosed();
            if (this._locale.noSync()) {
                this._locale.enter();
                try {
                    int n = this._textSaver.read();
                    return n;
                } finally {
                    this._locale.exit();
                }
            }
            Locale locale = this._locale;
            synchronized (locale) {
                int n;
                this._locale.enter();
                try {
                    n = this._textSaver.read();
                    this._locale.exit();
                } catch (Throwable throwable) {
                    this._locale.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int read(char[] cbuf) throws IOException {
            this.checkClosed();
            if (this._locale.noSync()) {
                this._locale.enter();
                try {
                    int n = this._textSaver.read(cbuf, 0, cbuf == null ? 0 : cbuf.length);
                    return n;
                } finally {
                    this._locale.exit();
                }
            }
            Locale locale = this._locale;
            synchronized (locale) {
                int n;
                this._locale.enter();
                try {
                    n = this._textSaver.read(cbuf, 0, cbuf == null ? 0 : cbuf.length);
                    this._locale.exit();
                } catch (Throwable throwable) {
                    this._locale.exit();
                    throw throwable;
                }
                return n;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        @Override
        public int read(char[] cbuf, int off, int len) throws IOException {
            this.checkClosed();
            if (this._locale.noSync()) {
                this._locale.enter();
                try {
                    int n = this._textSaver.read(cbuf, off, len);
                    return n;
                } finally {
                    this._locale.exit();
                }
            }
            Locale locale = this._locale;
            synchronized (locale) {
                int n;
                this._locale.enter();
                try {
                    n = this._textSaver.read(cbuf, off, len);
                    this._locale.exit();
                } catch (Throwable throwable) {
                    this._locale.exit();
                    throw throwable;
                }
                return n;
            }
        }

        private void checkClosed() throws IOException {
            if (this._closed) {
                throw new IOException("Reader has been closed");
            }
        }
    }

    static final class OptimizedForSpeedSaver
    extends Saver {
        Writer _w;
        private char[] _buf = new char[1024];

        OptimizedForSpeedSaver(Cur cur, Writer writer) {
            super(cur, XmlOptions.maskNull(null));
            this._w = writer;
        }

        static void save(Cur cur, Writer writer) throws IOException {
            try {
                OptimizedForSpeedSaver saver = new OptimizedForSpeedSaver(cur, writer);
                while (saver.process()) {
                }
            } catch (SaverIOException e) {
                throw (IOException)e.getCause();
            }
        }

        private void emit(String s) {
            try {
                this._w.write(s);
            } catch (IOException e) {
                throw new SaverIOException(e);
            }
        }

        private void emit(char c) {
            try {
                this._buf[0] = c;
                this._w.write(this._buf, 0, 1);
            } catch (IOException e) {
                throw new SaverIOException(e);
            }
        }

        private void emit(char c1, char c2) {
            try {
                this._buf[0] = c1;
                this._buf[1] = c2;
                this._w.write(this._buf, 0, 2);
            } catch (IOException e) {
                throw new SaverIOException(e);
            }
        }

        private void emit(char[] buf, int start, int len) {
            try {
                this._w.write(buf, start, len);
            } catch (IOException e) {
                throw new SaverIOException(e);
            }
        }

        @Override
        protected boolean emitElement(SaveCur c, ArrayList attrNames, ArrayList attrValues) {
            assert (c.isElem());
            this.emit('<');
            this.emitName(c.getName(), false);
            for (int i = 0; i < attrNames.size(); ++i) {
                this.emitAttrHelper((QName)attrNames.get(i), (String)attrValues.get(i));
            }
            if (!this.saveNamespacesFirst()) {
                this.emitNamespacesHelper();
            }
            if (!c.hasChildren() && !c.hasText()) {
                this.emit('/', '>');
                return true;
            }
            this.emit('>');
            return false;
        }

        @Override
        protected void emitFinish(SaveCur c) {
            this.emit('<', '/');
            this.emitName(c.getName(), false);
            this.emit('>');
        }

        protected void emitXmlns(String prefix, String uri) {
            assert (prefix != null);
            assert (uri != null);
            this.emit("xmlns");
            if (prefix.length() > 0) {
                this.emit(':');
                this.emit(prefix);
            }
            this.emit('=', '\"');
            this.emitAttrValue(uri);
            this.emit('\"');
        }

        private void emitNamespacesHelper() {
            this.iterateMappings();
            while (this.hasMapping()) {
                this.emit(' ');
                this.emitXmlns(this.mappingPrefix(), this.mappingUri());
                this.nextMapping();
            }
        }

        private void emitAttrHelper(QName attrName, String attrValue) {
            this.emit(' ');
            this.emitName(attrName, true);
            this.emit('=', '\"');
            this.emitAttrValue(attrValue);
            this.emit('\"');
        }

        @Override
        protected void emitComment(SaveCur c) {
            assert (c.isComment());
            this.emit("<!--");
            c.push();
            c.next();
            this.emitCommentText(c);
            c.pop();
            this.emit("-->");
        }

        @Override
        protected void emitProcinst(SaveCur c) {
            assert (c.isProcinst());
            this.emit("<?");
            this.emit(c.getName().getLocalPart());
            c.push();
            c.next();
            if (c.isText()) {
                this.emit(' ');
                this.emitPiText(c);
            }
            c.pop();
            this.emit("?>");
        }

        @Override
        protected void emitDocType(String docTypeName, String publicId, String systemId) {
            assert (docTypeName != null);
            this.emit("<!DOCTYPE ");
            this.emit(docTypeName);
            if (publicId == null && systemId != null) {
                this.emit(" SYSTEM ");
                this.emitLiteral(systemId);
            } else if (publicId != null) {
                this.emit(" PUBLIC ");
                this.emitLiteral(publicId);
                this.emit(' ');
                this.emitLiteral(systemId);
            }
            this.emit('>');
            this.emit(_newLine);
        }

        @Override
        protected void emitStartDoc(SaveCur c) {
        }

        @Override
        protected void emitEndDoc(SaveCur c) {
        }

        private void emitName(QName name, boolean needsPrefix) {
            assert (name != null);
            String uri = name.getNamespaceURI();
            assert (uri != null);
            if (uri.length() != 0) {
                String prefix = name.getPrefix();
                String mappedUri = this.getNamespaceForPrefix(prefix);
                if (mappedUri == null || !mappedUri.equals(uri)) {
                    prefix = this.getUriMapping(uri);
                }
                if (needsPrefix && prefix.length() == 0) {
                    prefix = this.getNonDefaultUriMapping(uri);
                }
                if (prefix.length() > 0) {
                    this.emit(prefix);
                    this.emit(':');
                }
            }
            assert (name.getLocalPart().length() > 0);
            this.emit(name.getLocalPart());
        }

        private void emitAttrValue(CharSequence attVal) {
            int len = attVal.length();
            for (int i = 0; i < len; ++i) {
                char ch = attVal.charAt(i);
                if (ch == '<') {
                    this.emit("&lt;");
                    continue;
                }
                if (ch == '&') {
                    this.emit("&amp;");
                    continue;
                }
                if (ch == '\"') {
                    this.emit("&quot;");
                    continue;
                }
                this.emit(ch);
            }
        }

        private void emitLiteral(String literal) {
            if (literal.indexOf("\"") < 0) {
                this.emit('\"');
                this.emit(literal);
                this.emit('\"');
            } else {
                this.emit('\'');
                this.emit(literal);
                this.emit('\'');
            }
        }

        @Override
        protected void emitText(SaveCur c) {
            assert (c.isText());
            Object src = c.getChars();
            int cch = c._cchSrc;
            int off = c._offSrc;
            int index = 0;
            int indexLimit = 0;
            while (index < cch) {
                indexLimit = index + 512 > cch ? cch : index + 512;
                CharUtil.getChars(this._buf, 0, src, off + index, indexLimit - index);
                this.entitizeAndWriteText(indexLimit - index);
                index = indexLimit;
            }
        }

        protected void emitPiText(SaveCur c) {
            assert (c.isText());
            Object src = c.getChars();
            int cch = c._cchSrc;
            int off = c._offSrc;
            int index = 0;
            int indexLimit = 0;
            while (index < cch) {
                indexLimit = index + 512 > cch ? cch : 512;
                CharUtil.getChars(this._buf, 0, src, off + index, indexLimit);
                this.entitizeAndWritePIText(indexLimit - index);
                index = indexLimit;
            }
        }

        protected void emitCommentText(SaveCur c) {
            assert (c.isText());
            Object src = c.getChars();
            int cch = c._cchSrc;
            int off = c._offSrc;
            int index = 0;
            int indexLimit = 0;
            while (index < cch) {
                indexLimit = index + 512 > cch ? cch : 512;
                CharUtil.getChars(this._buf, 0, src, off + index, indexLimit);
                this.entitizeAndWriteCommentText(indexLimit - index);
                index = indexLimit;
            }
        }

        private void entitizeAndWriteText(int bufLimit) {
            int index = 0;
            block4: for (int i = 0; i < bufLimit; ++i) {
                char c = this._buf[i];
                switch (c) {
                    case '<': {
                        this.emit(this._buf, index, i - index);
                        this.emit("&lt;");
                        index = i + 1;
                        continue block4;
                    }
                    case '&': {
                        this.emit(this._buf, index, i - index);
                        this.emit("&amp;");
                        index = i + 1;
                    }
                }
            }
            this.emit(this._buf, index, bufLimit - index);
        }

        private void entitizeAndWriteCommentText(int bufLimit) {
            boolean lastWasDash = false;
            for (int i = 0; i < bufLimit; ++i) {
                char ch = this._buf[i];
                if (OptimizedForSpeedSaver.isBadChar(ch)) {
                    this._buf[i] = 63;
                } else if (ch == '-') {
                    if (lastWasDash) {
                        this._buf[i] = 32;
                        lastWasDash = false;
                    } else {
                        lastWasDash = true;
                    }
                } else {
                    lastWasDash = false;
                }
                if (i != this._buf.length) continue;
                i = 0;
            }
            if (this._buf[bufLimit - 1] == '-') {
                this._buf[bufLimit - 1] = 32;
            }
            this.emit(this._buf, 0, bufLimit);
        }

        private void entitizeAndWritePIText(int bufLimit) {
            boolean lastWasQuestion = false;
            for (int i = 0; i < bufLimit; ++i) {
                int ch = this._buf[i];
                if (OptimizedForSpeedSaver.isBadChar((char)ch)) {
                    this._buf[i] = 63;
                    ch = 63;
                }
                if (ch == 62) {
                    if (lastWasQuestion) {
                        this._buf[i] = 32;
                    }
                    lastWasQuestion = false;
                    continue;
                }
                lastWasQuestion = ch == 63;
            }
            this.emit(this._buf, 0, bufLimit);
        }

        private static class SaverIOException
        extends RuntimeException {
            SaverIOException(IOException e) {
                super(e);
            }
        }
    }

    static final class TextSaver
    extends Saver {
        private static final int _initialBufSize = 4096;
        private int _cdataLengthThreshold = 32;
        private int _cdataEntityCountThreshold = 5;
        private boolean _useCDataBookmarks = false;
        private boolean _isPrettyPrint = false;
        private int _lastEmitIn;
        private int _lastEmitCch;
        private int _free;
        private int _in;
        private int _out;
        private char[] _buf;

        TextSaver(Cur c, XmlOptions options, String encoding) {
            super(c, options);
            boolean noSaveDecl;
            boolean bl = noSaveDecl = options != null && options.hasOption("SAVE_NO_XML_DECL");
            if (options != null && options.hasOption("SAVE_CDATA_LENGTH_THRESHOLD")) {
                this._cdataLengthThreshold = (Integer)options.get("SAVE_CDATA_LENGTH_THRESHOLD");
            }
            if (options != null && options.hasOption("SAVE_CDATA_ENTITY_COUNT_THRESHOLD")) {
                this._cdataEntityCountThreshold = (Integer)options.get("SAVE_CDATA_ENTITY_COUNT_THRESHOLD");
            }
            if (options != null && options.hasOption("LOAD_SAVE_CDATA_BOOKMARKS")) {
                this._useCDataBookmarks = true;
            }
            if (options != null && options.hasOption("SAVE_PRETTY_PRINT")) {
                this._isPrettyPrint = true;
            }
            this._out = 0;
            this._in = 0;
            this._free = 0;
            assert (this._buf == null || this._out < this._in && this._free == this._buf.length - (this._in - this._out) || this._out > this._in && this._free == this._out - this._in || this._out == this._in && this._free == this._buf.length || this._out == this._in && this._free == 0) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
            if (encoding != null && !noSaveDecl) {
                String version;
                XmlDocumentProperties props = Locale.getDocProps(c, false);
                String string = version = props == null ? null : props.getVersion();
                if (version == null) {
                    version = "1.0";
                }
                this.emit("<?xml version=\"");
                this.emit(version);
                this.emit("\" encoding=\"" + encoding + "\"?>" + _newLine);
            }
        }

        @Override
        protected boolean emitElement(SaveCur c, ArrayList attrNames, ArrayList attrValues) {
            assert (c.isElem());
            this.emit('<');
            this.emitName(c.getName(), false);
            if (this.saveNamespacesFirst()) {
                this.emitNamespacesHelper();
            }
            for (int i = 0; i < attrNames.size(); ++i) {
                this.emitAttrHelper((QName)attrNames.get(i), (String)attrValues.get(i));
            }
            if (!this.saveNamespacesFirst()) {
                this.emitNamespacesHelper();
            }
            if (!c.hasChildren() && !c.hasText()) {
                this.emit('/', '>');
                return true;
            }
            this.emit('>');
            return false;
        }

        @Override
        protected void emitFinish(SaveCur c) {
            this.emit('<', '/');
            this.emitName(c.getName(), false);
            this.emit('>');
        }

        protected void emitXmlns(String prefix, String uri) {
            assert (prefix != null);
            assert (uri != null);
            this.emit("xmlns");
            if (prefix.length() > 0) {
                this.emit(':');
                this.emit(prefix);
            }
            this.emit('=', '\"');
            this.emit(uri);
            this.entitizeAttrValue(false);
            this.emit('\"');
        }

        private void emitNamespacesHelper() {
            this.iterateMappings();
            while (this.hasMapping()) {
                this.emit(' ');
                this.emitXmlns(this.mappingPrefix(), this.mappingUri());
                this.nextMapping();
            }
        }

        private void emitAttrHelper(QName attrName, String attrValue) {
            this.emit(' ');
            this.emitName(attrName, true);
            this.emit('=', '\"');
            this.emit(attrValue);
            this.entitizeAttrValue(true);
            this.emit('\"');
        }

        @Override
        protected void emitText(SaveCur c) {
            assert (c.isText());
            boolean forceCData = this._useCDataBookmarks && c.isTextCData();
            this.emit(c);
            this.entitizeContent(forceCData);
        }

        @Override
        protected void emitComment(SaveCur c) {
            assert (c.isComment());
            this.emit("<!--");
            c.push();
            c.next();
            this.emit(c);
            c.pop();
            this.entitizeComment();
            this.emit("-->");
        }

        @Override
        protected void emitProcinst(SaveCur c) {
            assert (c.isProcinst());
            this.emit("<?");
            this.emit(c.getName().getLocalPart());
            c.push();
            c.next();
            if (c.isText()) {
                this.emit(" ");
                this.emit(c);
                this.entitizeProcinst();
            }
            c.pop();
            this.emit("?>");
        }

        private void emitLiteral(String literal) {
            if (literal.indexOf("\"") < 0) {
                this.emit('\"');
                this.emit(literal);
                this.emit('\"');
            } else {
                this.emit('\'');
                this.emit(literal);
                this.emit('\'');
            }
        }

        @Override
        protected void emitDocType(String docTypeName, String publicId, String systemId) {
            assert (docTypeName != null);
            this.emit("<!DOCTYPE ");
            this.emit(docTypeName);
            if (publicId == null && systemId != null) {
                this.emit(" SYSTEM ");
                this.emitLiteral(systemId);
            } else if (publicId != null) {
                this.emit(" PUBLIC ");
                this.emitLiteral(publicId);
                this.emit(" ");
                this.emitLiteral(systemId);
            }
            this.emit(">");
            this.emit(_newLine);
        }

        @Override
        protected void emitStartDoc(SaveCur c) {
        }

        @Override
        protected void emitEndDoc(SaveCur c) {
        }

        private void emitName(QName name, boolean needsPrefix) {
            assert (name != null);
            String uri = name.getNamespaceURI();
            assert (uri != null);
            if (uri.length() != 0) {
                String prefix = name.getPrefix();
                String mappedUri = this.getNamespaceForPrefix(prefix);
                if (mappedUri == null || !mappedUri.equals(uri)) {
                    prefix = this.getUriMapping(uri);
                }
                if (needsPrefix && prefix.length() == 0) {
                    prefix = this.getNonDefaultUriMapping(uri);
                }
                if (prefix.length() > 0) {
                    this.emit(prefix);
                    this.emit(':');
                }
            }
            assert (name.getLocalPart().length() > 0);
            this.emit(name.getLocalPart());
        }

        private void emit(char ch) {
            assert (this._buf == null || this._out < this._in && this._free == this._buf.length - (this._in - this._out) || this._out > this._in && this._free == this._out - this._in || this._out == this._in && this._free == this._buf.length || this._out == this._in && this._free == 0) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
            this.preEmit(1);
            this._buf[this._in] = ch;
            this._in = (this._in + 1) % this._buf.length;
            assert (this._buf == null || this._out < this._in && this._free == this._buf.length - (this._in - this._out) || this._out > this._in && this._free == this._out - this._in || this._out == this._in && this._free == this._buf.length || this._out == this._in && this._free == 0) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
        }

        private void emit(char ch1, char ch2) {
            if (this.preEmit(2)) {
                return;
            }
            this._buf[this._in] = ch1;
            this._in = (this._in + 1) % this._buf.length;
            this._buf[this._in] = ch2;
            this._in = (this._in + 1) % this._buf.length;
            assert (this._buf == null || this._out < this._in && this._free == this._buf.length - (this._in - this._out) || this._out > this._in && this._free == this._out - this._in || this._out == this._in && this._free == this._buf.length || this._out == this._in && this._free == 0) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
        }

        private void emit(String s) {
            int chunk;
            int cch;
            assert (this._buf == null || this._out < this._in && this._free == this._buf.length - (this._in - this._out) || this._out > this._in && this._free == this._out - this._in || this._out == this._in && this._free == this._buf.length || this._out == this._in && this._free == 0) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
            int n = cch = s == null ? 0 : s.length();
            if (this.preEmit(cch)) {
                return;
            }
            if (this._in <= this._out || cch < (chunk = this._buf.length - this._in)) {
                s.getChars(0, cch, this._buf, this._in);
                this._in += cch;
            } else {
                s.getChars(0, chunk, this._buf, this._in);
                s.getChars(chunk, cch, this._buf, 0);
                this._in = (this._in + cch) % this._buf.length;
            }
            assert (this._buf == null || this._out < this._in && this._free == this._buf.length - (this._in - this._out) || this._out > this._in && this._free == this._out - this._in || this._out == this._in && this._free == this._buf.length || this._out == this._in && this._free == 0) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
        }

        private void emit(SaveCur c) {
            if (c.isText()) {
                int chunk;
                Object src = c.getChars();
                int cch = c._cchSrc;
                if (this.preEmit(cch)) {
                    return;
                }
                if (this._in <= this._out || cch < (chunk = this._buf.length - this._in)) {
                    CharUtil.getChars(this._buf, this._in, src, c._offSrc, cch);
                    this._in += cch;
                } else {
                    CharUtil.getChars(this._buf, this._in, src, c._offSrc, chunk);
                    CharUtil.getChars(this._buf, 0, src, c._offSrc + chunk, cch - chunk);
                    this._in = (this._in + cch) % this._buf.length;
                }
            } else {
                this.preEmit(0);
            }
        }

        private boolean preEmit(int cch) {
            assert (cch >= 0);
            assert (this._buf == null || this._out < this._in && this._free == this._buf.length - (this._in - this._out) || this._out > this._in && this._free == this._out - this._in || this._out == this._in && this._free == this._buf.length || this._out == this._in && this._free == 0) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
            this._lastEmitCch = cch;
            if (cch == 0) {
                return true;
            }
            if (this._free <= cch) {
                this.resize(cch, -1);
            }
            assert (cch <= this._free);
            int used = this.getAvailable();
            if (used == 0) {
                assert (this._in == this._out);
                assert (this._free == this._buf.length);
                this._out = 0;
                this._in = 0;
            }
            this._lastEmitIn = this._in;
            this._free -= cch;
            assert (this._free >= 0);
            assert (this._buf == null || this._free == (this._in >= this._out ? this._buf.length - (this._in - this._out) : this._out - this._in) - cch) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
            assert (this._buf == null || this._out < this._in && this._free == this._buf.length - (this._in - this._out) - cch || this._out > this._in && this._free == this._out - this._in - cch || this._out == this._in && this._free == this._buf.length - cch || this._out == this._in && this._free == 0) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
            return false;
        }

        private void entitizeContent(boolean forceCData) {
            assert (this._free >= 0);
            if (this._lastEmitCch == 0) {
                return;
            }
            int i = this._lastEmitIn;
            int n = this._buf.length;
            boolean hasCharToBeReplaced = false;
            int count = 0;
            char prevChar = '\u0000';
            char prevPrevChar = '\u0000';
            for (int cch = this._lastEmitCch; cch > 0; --cch) {
                char ch = this._buf[i];
                if (ch == '<' || ch == '&') {
                    ++count;
                } else if (prevPrevChar == ']' && prevChar == ']' && ch == '>') {
                    hasCharToBeReplaced = true;
                } else if (TextSaver.isBadChar(ch) || this.isEscapedChar(ch) || !this._isPrettyPrint && ch == '\r') {
                    hasCharToBeReplaced = true;
                }
                if (++i == n) {
                    i = 0;
                }
                prevPrevChar = prevChar;
                prevChar = ch;
            }
            if (!forceCData && count == 0 && !hasCharToBeReplaced && count < this._cdataEntityCountThreshold) {
                return;
            }
            i = this._lastEmitIn;
            if (forceCData || this._lastEmitCch > this._cdataLengthThreshold && count > this._cdataEntityCountThreshold) {
                boolean lastWasBracket = this._buf[i] == ']';
                i = this.replace(i, "<![CDATA[" + this._buf[i]);
                boolean secondToLastWasBracket = lastWasBracket;
                boolean bl = lastWasBracket = this._buf[i] == ']';
                if (++i == this._buf.length) {
                    i = 0;
                }
                for (int cch = this._lastEmitCch - 2; cch > 0; --cch) {
                    char ch = this._buf[i];
                    i = ch == '>' && secondToLastWasBracket && lastWasBracket ? this.replace(i, "]]>><![CDATA[") : (TextSaver.isBadChar(ch) ? this.replace(i, "?") : ++i);
                    secondToLastWasBracket = lastWasBracket;
                    boolean bl2 = lastWasBracket = ch == ']';
                    if (i != this._buf.length) continue;
                    i = 0;
                }
                this.emit("]]>");
            } else {
                char ch = '\u0000';
                char ch_1 = '\u0000';
                for (int cch = this._lastEmitCch; cch > 0; --cch) {
                    char ch_2 = ch_1;
                    ch_1 = ch;
                    ch = this._buf[i];
                    i = ch == '<' ? this.replace(i, "&lt;") : (ch == '&' ? this.replace(i, "&amp;") : (ch == '>' && ch_1 == ']' && ch_2 == ']' ? this.replace(i, "&gt;") : (TextSaver.isBadChar(ch) ? this.replace(i, "?") : (!this._isPrettyPrint && ch == '\r' ? this.replace(i, "&#13;") : (this.isEscapedChar(ch) ? this.replace(i, this._replaceChar.getEscapedString(ch)) : ++i)))));
                    if (i != this._buf.length) continue;
                    i = 0;
                }
            }
        }

        private void entitizeAttrValue(boolean replaceEscapedChar) {
            if (this._lastEmitCch == 0) {
                return;
            }
            int i = this._lastEmitIn;
            for (int cch = this._lastEmitCch; cch > 0; --cch) {
                char ch = this._buf[i];
                if (ch == '<') {
                    i = this.replace(i, "&lt;");
                } else if (ch == '&') {
                    i = this.replace(i, "&amp;");
                } else if (ch == '\"') {
                    i = this.replace(i, "&quot;");
                } else if (this.isEscapedChar(ch)) {
                    if (replaceEscapedChar) {
                        i = this.replace(i, this._replaceChar.getEscapedString(ch));
                    }
                } else {
                    ++i;
                }
                if (i != this._buf.length) continue;
                i = 0;
            }
        }

        private void entitizeComment() {
            if (this._lastEmitCch == 0) {
                return;
            }
            int i = this._lastEmitIn;
            boolean lastWasDash = false;
            for (int cch = this._lastEmitCch; cch > 0; --cch) {
                char ch = this._buf[i];
                if (TextSaver.isBadChar(ch)) {
                    i = this.replace(i, "?");
                } else if (ch == '-') {
                    if (lastWasDash) {
                        i = this.replace(i, " ");
                        lastWasDash = false;
                    } else {
                        lastWasDash = true;
                        ++i;
                    }
                } else {
                    lastWasDash = false;
                    ++i;
                }
                if (i != this._buf.length) continue;
                i = 0;
            }
            int offset = (this._lastEmitIn + this._lastEmitCch - 1) % this._buf.length;
            if (this._buf[offset] == '-') {
                i = this.replace(offset, " ");
            }
        }

        private void entitizeProcinst() {
            if (this._lastEmitCch == 0) {
                return;
            }
            int i = this._lastEmitIn;
            boolean lastWasQuestion = false;
            for (int cch = this._lastEmitCch; cch > 0; --cch) {
                char ch = this._buf[i];
                if (TextSaver.isBadChar(ch)) {
                    i = this.replace(i, "?");
                }
                if (ch == '>') {
                    i = lastWasQuestion ? this.replace(i, " ") : ++i;
                    lastWasQuestion = false;
                } else {
                    lastWasQuestion = ch == '?';
                    ++i;
                }
                if (i != this._buf.length) continue;
                i = 0;
            }
        }

        private boolean isEscapedChar(char ch) {
            return null != this._replaceChar && this._replaceChar.containsChar(ch);
        }

        private int replace(int i, String replacement) {
            assert (replacement.length() > 0);
            int dCch = replacement.length() - 1;
            if (dCch == 0) {
                this._buf[i] = replacement.charAt(0);
                return i + 1;
            }
            assert (this._free >= 0);
            if (dCch > this._free) {
                i = this.resize(dCch, i);
            }
            assert (this._free >= 0);
            assert (this._free >= dCch);
            assert (this.getAvailable() > 0);
            int charsToCopy = dCch + 1;
            if (this._out > this._in && i >= this._out) {
                System.arraycopy(this._buf, this._out, this._buf, this._out - dCch, i - this._out);
                this._out -= dCch;
                i -= dCch;
            } else {
                assert (i < this._in);
                int availableEndChunk = this._buf.length - this._in;
                if (dCch <= availableEndChunk) {
                    System.arraycopy(this._buf, i, this._buf, i + dCch, this._in - i);
                    this._in = (this._in + dCch) % this._buf.length;
                } else if (dCch <= availableEndChunk + this._in - i - 1) {
                    int numToCopyToStart = dCch - availableEndChunk;
                    System.arraycopy(this._buf, this._in - numToCopyToStart, this._buf, 0, numToCopyToStart);
                    System.arraycopy(this._buf, i + 1, this._buf, i + 1 + dCch, this._in - i - 1 - numToCopyToStart);
                    this._in = numToCopyToStart;
                } else {
                    int numToCopyToStart = this._in - i - 1;
                    charsToCopy = availableEndChunk + this._in - i;
                    System.arraycopy(this._buf, this._in - numToCopyToStart, this._buf, dCch - charsToCopy + 1, numToCopyToStart);
                    replacement.getChars(charsToCopy, dCch + 1, this._buf, 0);
                    this._in = numToCopyToStart + dCch - charsToCopy + 1;
                }
            }
            replacement.getChars(0, charsToCopy, this._buf, i);
            this._free -= dCch;
            assert (this._free >= 0);
            assert (this._buf == null || this._out < this._in && this._free == this._buf.length - (this._in - this._out) || this._out > this._in && this._free == this._out - this._in || this._out == this._in && this._free == this._buf.length || this._out == this._in && this._free == 0) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
            return (i + dCch + 1) % this._buf.length;
        }

        private int ensure(int cch) {
            if (cch <= 0) {
                cch = 1;
            }
            int available = this.getAvailable();
            while (available < cch && this.process()) {
                available = this.getAvailable();
            }
            assert (available == this.getAvailable());
            return available;
        }

        int getAvailable() {
            return this._buf == null ? 0 : this._buf.length - this._free;
        }

        private int resize(int cch, int i) {
            assert (this._free >= 0);
            assert (cch > 0);
            assert (cch >= this._free);
            assert (this._buf == null || this._out < this._in && this._free == this._buf.length - (this._in - this._out) || this._out > this._in && this._free == this._out - this._in || this._out == this._in && this._free == this._buf.length || this._out == this._in && this._free == 0) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
            int newLen = this._buf == null ? 4096 : this._buf.length * 2;
            int used = this.getAvailable();
            while (newLen - used < cch) {
                newLen *= 2;
            }
            char[] newBuf = new char[newLen];
            if (used > 0) {
                if (this._in > this._out) {
                    assert (i == -1 || i >= this._out && i < this._in);
                    System.arraycopy(this._buf, this._out, newBuf, 0, used);
                    i -= this._out;
                } else {
                    assert (i == -1 || i >= this._out || i < this._in);
                    System.arraycopy(this._buf, this._out, newBuf, 0, used - this._in);
                    System.arraycopy(this._buf, 0, newBuf, used - this._in, this._in);
                    i = i >= this._out ? i - this._out : i + this._out;
                }
                this._out = 0;
                this._in = used;
                this._free += newBuf.length - this._buf.length;
            } else {
                this._free = newBuf.length;
                assert (this._in == 0 && this._out == 0);
                assert (i == -1);
            }
            this._buf = newBuf;
            assert (this._free >= 0);
            assert (this._buf == null || this._out < this._in && this._free == this._buf.length - (this._in - this._out) || this._out > this._in && this._free == this._out - this._in || this._out == this._in && this._free == this._buf.length || this._out == this._in && this._free == 0) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
            return i;
        }

        public int read() {
            if (this.ensure(1) == 0) {
                return -1;
            }
            assert (this.getAvailable() > 0);
            char ch = this._buf[this._out];
            this._out = (this._out + 1) % this._buf.length;
            ++this._free;
            assert (this._buf == null || this._out < this._in && this._free == this._buf.length - (this._in - this._out) || this._out > this._in && this._free == this._out - this._in || this._out == this._in && this._free == this._buf.length || this._out == this._in && this._free == 0) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
            return ch;
        }

        public int read(char[] cbuf, int off, int len) {
            int n = this.ensure(len);
            if (n == 0) {
                return -1;
            }
            if (cbuf == null || len <= 0) {
                return 0;
            }
            if (n < len) {
                len = n;
            }
            if (this._out < this._in) {
                System.arraycopy(this._buf, this._out, cbuf, off, len);
            } else {
                int chunk = this._buf.length - this._out;
                if (chunk >= len) {
                    System.arraycopy(this._buf, this._out, cbuf, off, len);
                } else {
                    System.arraycopy(this._buf, this._out, cbuf, off, chunk);
                    System.arraycopy(this._buf, 0, cbuf, off + chunk, len - chunk);
                }
            }
            this._out = (this._out + len) % this._buf.length;
            this._free += len;
            assert (this._buf == null || this._out < this._in && this._free == this._buf.length - (this._in - this._out) || this._out > this._in && this._free == this._out - this._in || this._out == this._in && this._free == this._buf.length || this._out == this._in && this._free == 0) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
            assert (this._free >= 0);
            return len;
        }

        public int write(Writer writer, int cchMin) {
            while (this.getAvailable() < cchMin && this.process()) {
            }
            int charsAvailable = this.getAvailable();
            if (charsAvailable > 0) {
                assert (this._out == 0);
                assert (this._in >= this._out) : "_in:" + this._in + " < _out:" + this._out;
                assert (this._free == this._buf.length - this._in);
                try {
                    writer.write(this._buf, 0, charsAvailable);
                    writer.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                this._free += charsAvailable;
                assert (this._free >= 0);
                this._in = 0;
            }
            assert (this._buf == null || this._out < this._in && this._free == this._buf.length - (this._in - this._out) || this._out > this._in && this._free == this._out - this._in || this._out == this._in && this._free == this._buf.length || this._out == this._in && this._free == 0) : "_buf.length:" + this._buf.length + " _in:" + this._in + " _out:" + this._out + " _free:" + this._free;
            return charsAvailable;
        }

        public String saveToString() {
            while (this.process()) {
            }
            assert (this._out == 0);
            int available = this.getAvailable();
            return available == 0 ? "" : new String(this._buf, this._out, available);
        }
    }

    static final class SynthNamespaceSaver
    extends Saver {
        LinkedHashMap _synthNamespaces = new LinkedHashMap();

        SynthNamespaceSaver(Cur c, XmlOptions options) {
            super(c, options);
        }

        @Override
        protected void syntheticNamespace(String prefix, String uri, boolean considerCreatingDefault) {
            this._synthNamespaces.put(uri, considerCreatingDefault ? "" : prefix);
        }

        @Override
        protected boolean emitElement(SaveCur c, ArrayList attrNames, ArrayList attrValues) {
            return false;
        }

        @Override
        protected void emitFinish(SaveCur c) {
        }

        @Override
        protected void emitText(SaveCur c) {
        }

        @Override
        protected void emitComment(SaveCur c) {
        }

        @Override
        protected void emitProcinst(SaveCur c) {
        }

        @Override
        protected void emitDocType(String docTypeName, String publicId, String systemId) {
        }

        @Override
        protected void emitStartDoc(SaveCur c) {
        }

        @Override
        protected void emitEndDoc(SaveCur c) {
        }
    }
}

