/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.store;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.GlobalLock;
import org.apache.xmlbeans.impl.common.XMLChar;
import org.apache.xmlbeans.impl.store.CharUtil;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.Jsr173;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.store.Path;
import org.apache.xmlbeans.impl.store.Query;
import org.apache.xmlbeans.impl.store.Saver;
import org.apache.xmlbeans.impl.store.Xobj;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public final class Cursor
implements XmlCursor,
Locale.ChangeListener {
    static final int ROOT = 1;
    static final int ELEM = 2;
    static final int ATTR = 3;
    static final int COMMENT = 4;
    static final int PROCINST = 5;
    static final int TEXT = 0;
    private static final int MOVE_XML = 0;
    private static final int COPY_XML = 1;
    private static final int MOVE_XML_CONTENTS = 2;
    private static final int COPY_XML_CONTENTS = 3;
    private static final int MOVE_CHARS = 4;
    private static final int COPY_CHARS = 5;
    private Cur _cur;
    private Path.PathEngine _pathEngine;
    private int _currentSelection;
    private Locale.ChangeListener _nextChangeListener;

    Cursor(Xobj x, int p) {
        this._cur = x._locale.weakCur(this);
        this._cur.moveTo(x, p);
        this._currentSelection = -1;
    }

    Cursor(Cur c) {
        this(c._xobj, c._pos);
    }

    private static boolean isValid(Cur c) {
        if (c.kind() <= 0) {
            int pk;
            c.push();
            if (c.toParentRaw() && ((pk = c.kind()) == 4 || pk == 5 || pk == 3)) {
                return false;
            }
            c.pop();
        }
        return true;
    }

    private boolean isValid() {
        return Cursor.isValid(this._cur);
    }

    Locale locale() {
        return this._cur._locale;
    }

    Cur tempCur() {
        return this._cur.tempCur();
    }

    public void dump(PrintStream o) {
        this._cur.dump(o);
    }

    static void validateLocalName(QName name) {
        if (name == null) {
            throw new IllegalArgumentException("QName is null");
        }
        Cursor.validateLocalName(name.getLocalPart());
    }

    static void validateLocalName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name is null");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("Name is empty");
        }
        if (!XMLChar.isValidNCName(name)) {
            throw new IllegalArgumentException("Name is not valid");
        }
    }

    static void validatePrefix(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Prefix is null");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("Prefix is empty");
        }
        if (Locale.beginsWithXml(name)) {
            throw new IllegalArgumentException("Prefix begins with 'xml'");
        }
        if (!XMLChar.isValidNCName(name)) {
            throw new IllegalArgumentException("Prefix is not valid");
        }
    }

    private static void complain(String msg) {
        throw new IllegalArgumentException(msg);
    }

    private void checkInsertionValidity(Cur that) {
        int thisKind;
        int thatKind = that.kind();
        if (thatKind < 0) {
            Cursor.complain("Can't move/copy/insert an end token.");
        }
        if (thatKind == 1) {
            Cursor.complain("Can't move/copy/insert a whole document.");
        }
        if ((thisKind = this._cur.kind()) == 1) {
            Cursor.complain("Can't insert before the start of the document.");
        }
        if (thatKind == 3) {
            this._cur.push();
            this._cur.prevWithAttrs();
            int pk = this._cur.kind();
            this._cur.pop();
            if (pk != 2 && pk != 1 && pk != -3) {
                Cursor.complain("Can only insert attributes before other attributes or after containers.");
            }
        }
        if (thisKind == 3 && thatKind != 3) {
            Cursor.complain("Can only insert attributes before other attributes or after containers.");
        }
    }

    private void insertNode(Cur that, String text) {
        assert (!that.isRoot());
        assert (that.isNode());
        assert (Cursor.isValid(that));
        assert (this.isValid());
        if (text != null && text.length() > 0) {
            that.next();
            that.insertString(text);
            that.toParent();
        }
        this.checkInsertionValidity(that);
        that.moveNode(this._cur);
        this._cur.toEnd();
        this._cur.nextWithAttrs();
    }

    public void _dispose() {
        this._cur.release();
        this._cur = null;
    }

    public XmlCursor _newCursor() {
        return new Cursor(this._cur);
    }

    public QName _getName() {
        switch (this._cur.kind()) {
            case 3: {
                if (this._cur.isXmlns()) {
                    return this._cur._locale.makeQNameNoCheck(this._cur.getXmlnsUri(), this._cur.getXmlnsPrefix());
                }
            }
            case 2: 
            case 5: {
                return this._cur.getName();
            }
        }
        return null;
    }

    public void _setName(QName name) {
        if (name == null) {
            throw new IllegalArgumentException("Name is null");
        }
        switch (this._cur.kind()) {
            case 2: 
            case 3: {
                Cursor.validateLocalName(name.getLocalPart());
                break;
            }
            case 5: {
                Cursor.validatePrefix(name.getLocalPart());
                if (name.getNamespaceURI().length() > 0) {
                    throw new IllegalArgumentException("Procinst name must have no URI");
                }
                if (name.getPrefix().length() <= 0) break;
                throw new IllegalArgumentException("Procinst name must have no prefix");
            }
            default: {
                throw new IllegalStateException("Can set name on element, atrtribute and procinst only");
            }
        }
        this._cur.setName(name);
    }

    public XmlCursor.TokenType _currentTokenType() {
        assert (this.isValid());
        switch (this._cur.kind()) {
            case 1: {
                return XmlCursor.TokenType.STARTDOC;
            }
            case -1: {
                return XmlCursor.TokenType.ENDDOC;
            }
            case 2: {
                return XmlCursor.TokenType.START;
            }
            case -2: {
                return XmlCursor.TokenType.END;
            }
            case 0: {
                return XmlCursor.TokenType.TEXT;
            }
            case 3: {
                return this._cur.isXmlns() ? XmlCursor.TokenType.NAMESPACE : XmlCursor.TokenType.ATTR;
            }
            case 4: {
                return XmlCursor.TokenType.COMMENT;
            }
            case 5: {
                return XmlCursor.TokenType.PROCINST;
            }
        }
        throw new IllegalStateException();
    }

    public boolean _isStartdoc() {
        assert (this.isValid());
        return this._cur.isRoot();
    }

    public boolean _isEnddoc() {
        assert (this.isValid());
        return this._cur.isEndRoot();
    }

    public boolean _isStart() {
        assert (this.isValid());
        return this._cur.isElem();
    }

    public boolean _isEnd() {
        assert (this.isValid());
        return this._cur.isEnd();
    }

    public boolean _isText() {
        assert (this.isValid());
        return this._cur.isText();
    }

    public boolean _isAttr() {
        assert (this.isValid());
        return this._cur.isNormalAttr();
    }

    public boolean _isNamespace() {
        assert (this.isValid());
        return this._cur.isXmlns();
    }

    public boolean _isComment() {
        assert (this.isValid());
        return this._cur.isComment();
    }

    public boolean _isProcinst() {
        assert (this.isValid());
        return this._cur.isProcinst();
    }

    public boolean _isContainer() {
        assert (this.isValid());
        return this._cur.isContainer();
    }

    public boolean _isFinish() {
        assert (this.isValid());
        return this._cur.isFinish();
    }

    public boolean _isAnyAttr() {
        assert (this.isValid());
        return this._cur.isAttr();
    }

    public XmlCursor.TokenType _toNextToken() {
        assert (this.isValid());
        switch (this._cur.kind()) {
            case 1: 
            case 2: {
                if (this._cur.toFirstAttr()) break;
                this._cur.next();
                break;
            }
            case 3: {
                if (this._cur.toNextSibling()) break;
                this._cur.toParent();
                this._cur.next();
                break;
            }
            case 4: 
            case 5: {
                this._cur.skip();
                break;
            }
            default: {
                if (this._cur.next()) break;
                return XmlCursor.TokenType.NONE;
            }
        }
        return this._currentTokenType();
    }

    public XmlCursor.TokenType _toPrevToken() {
        assert (this.isValid());
        boolean wasText = this._cur.isText();
        if (!this._cur.prev()) {
            assert (this._cur.isRoot() || this._cur.isAttr());
            if (this._cur.isRoot()) {
                return XmlCursor.TokenType.NONE;
            }
            this._cur.toParent();
        } else {
            int k = this._cur.kind();
            if (k < 0 && (k == -4 || k == -5 || k == -3)) {
                this._cur.toParent();
            } else if (this._cur.isContainer()) {
                this._cur.toLastAttr();
            } else if (wasText && this._cur.isText()) {
                return this._toPrevToken();
            }
        }
        return this._currentTokenType();
    }

    public Object _monitor() {
        return this._cur._locale;
    }

    public boolean _toParent() {
        Cur c = this._cur.tempCur();
        if (!c.toParent()) {
            return false;
        }
        this._cur.moveToCur(c);
        c.release();
        return true;
    }

    public XmlCursor.ChangeStamp _getDocChangeStamp() {
        return new ChangeStampImpl(this._cur._locale);
    }

    public XMLInputStream _newXMLInputStream() {
        return this._newXMLInputStream(null);
    }

    public XMLStreamReader _newXMLStreamReader() {
        return this._newXMLStreamReader(null);
    }

    public Node _newDomNode() {
        return this._newDomNode(null);
    }

    public InputStream _newInputStream() {
        return this._newInputStream(null);
    }

    public String _xmlText() {
        return this._xmlText(null);
    }

    public Reader _newReader() {
        return this._newReader(null);
    }

    public void _save(File file) throws IOException {
        this._save(file, null);
    }

    public void _save(OutputStream os) throws IOException {
        this._save(os, null);
    }

    public void _save(Writer w) throws IOException {
        this._save(w, null);
    }

    public void _save(ContentHandler ch, LexicalHandler lh) throws SAXException {
        this._save(ch, lh, null);
    }

    public XmlDocumentProperties _documentProperties() {
        return Locale.getDocProps(this._cur, true);
    }

    public XMLStreamReader _newXMLStreamReader(XmlOptions options) {
        return Jsr173.newXmlStreamReader(this._cur, options);
    }

    public XMLInputStream _newXMLInputStream(XmlOptions options) {
        return new Saver.XmlInputStreamImpl(this._cur, options);
    }

    public String _xmlText(XmlOptions options) {
        assert (this.isValid());
        return new Saver.TextSaver(this._cur, options, null).saveToString();
    }

    public InputStream _newInputStream(XmlOptions options) {
        return new Saver.InputStreamSaver(this._cur, options);
    }

    public Reader _newReader(XmlOptions options) {
        return new Saver.TextReader(this._cur, options);
    }

    public void _save(ContentHandler ch, LexicalHandler lh, XmlOptions options) throws SAXException {
        new Saver.SaxSaver(this._cur, options, ch, lh);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void _save(File file, XmlOptions options) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("Null file specified");
        }
        FileOutputStream os = new FileOutputStream(file);
        try {
            this._save(os, options);
        } finally {
            ((OutputStream)os).close();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void _save(OutputStream os, XmlOptions options) throws IOException {
        if (os == null) {
            throw new IllegalArgumentException("Null OutputStream specified");
        }
        InputStream is = this._newInputStream(options);
        try {
            int n;
            byte[] bytes = new byte[8192];
            while ((n = is.read(bytes)) >= 0) {
                os.write(bytes, 0, n);
            }
        } finally {
            is.close();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void _save(Writer w, XmlOptions options) throws IOException {
        if (w == null) {
            throw new IllegalArgumentException("Null Writer specified");
        }
        if (options != null && options.hasOption("SAVE_OPTIMIZE_FOR_SPEED")) {
            Saver.OptimizedForSpeedSaver.save(this._cur, w);
            return;
        }
        Reader r = this._newReader(options);
        try {
            int n;
            char[] chars = new char[8192];
            while ((n = r.read(chars)) >= 0) {
                w.write(chars, 0, n);
            }
        } finally {
            r.close();
        }
    }

    public Node _getDomNode() {
        return (Node)((Object)this._cur.getDom());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Unable to fully structure code
     */
    private boolean isDomFragment() {
        block19: {
            if (!this.isStartdoc()) {
                return true;
            }
            seenElement = false;
            c = this.newCursor();
            token = c.toNextToken().intValue();
            try {
                block14: while (true) {
                    switch (token) {
                        case 3: {
                            if (seenElement) {
                                var4_4 = true;
                                return var4_4;
                            }
                            seenElement = true;
                            token = c.toEndToken().intValue();
                            continue block14;
                        }
                        case 5: {
                            if (!Locale.isWhiteSpace(c.getChars())) {
                                var4_5 = true;
                                return var4_5;
                            }
                            token = c.toNextToken().intValue();
                            continue block14;
                        }
                        case 0: 
                        case 2: {
                            ** break;
lbl24:
                            // 1 sources

                            break block19;
                        }
                        case 6: 
                        case 7: {
                            var4_6 = true;
                            return var4_6;
                        }
                        case 4: 
                        case 8: 
                        case 9: {
                            token = c.toNextToken().intValue();
                            continue block14;
                        }
                        case 1: {
                            if (!Cursor.$assertionsDisabled) {
                                throw new AssertionError();
                            }
                            break block19;
                        }
                        default: {
                            continue block14;
                        }
                    }
                    break;
                }
            } finally {
                c.dispose();
            }
        }
        return seenElement == false;
    }

    public Node _newDomNode(XmlOptions options) {
        if (XmlOptions.hasOption(options, "SAVE_INNER")) {
            options = new XmlOptions(options);
            options.remove("SAVE_INNER");
        }
        return new DomSaver(this._cur, this.isDomFragment(), options).saveDom();
    }

    public boolean _toCursor(Cursor other) {
        assert (this._cur._locale == other._cur._locale);
        this._cur.moveToCur(other._cur);
        return true;
    }

    public void _push() {
        this._cur.push();
    }

    public boolean _pop() {
        return this._cur.pop();
    }

    @Override
    public void notifyChange() {
        if (this._cur != null) {
            this._getSelectionCount();
        }
    }

    @Override
    public void setNextChangeListener(Locale.ChangeListener listener) {
        this._nextChangeListener = listener;
    }

    @Override
    public Locale.ChangeListener getNextChangeListener() {
        return this._nextChangeListener;
    }

    public void _selectPath(String path) {
        this._selectPath(path, null);
    }

    public void _selectPath(String pathExpr, XmlOptions options) {
        this._clearSelections();
        assert (this._pathEngine == null);
        this._pathEngine = Path.getCompiledPath(pathExpr, options).execute(this._cur, options);
        this._cur._locale.registerForChange(this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean _hasNextSelection() {
        int curr = this._currentSelection;
        this.push();
        try {
            boolean bl = this._toNextSelection();
            return bl;
        } finally {
            this._currentSelection = curr;
            this.pop();
        }
    }

    public boolean _toNextSelection() {
        return this._toSelection(this._currentSelection + 1);
    }

    public boolean _toSelection(int i) {
        if (i < 0) {
            return false;
        }
        while (i >= this._cur.selectionCount()) {
            if (this._pathEngine == null) {
                return false;
            }
            if (this._pathEngine.next(this._cur)) continue;
            this._pathEngine.release();
            this._pathEngine = null;
            return false;
        }
        this._currentSelection = i;
        this._cur.moveToSelection(this._currentSelection);
        return true;
    }

    public int _getSelectionCount() {
        this._toSelection(Integer.MAX_VALUE);
        return this._cur.selectionCount();
    }

    public void _addToSelection() {
        this._toSelection(Integer.MAX_VALUE);
        this._cur.addToSelection();
    }

    public void _clearSelections() {
        if (this._pathEngine != null) {
            this._pathEngine.release();
            this._pathEngine = null;
        }
        this._cur.clearSelection();
        this._currentSelection = -1;
    }

    public String _namespaceForPrefix(String prefix) {
        if (!this._cur.isContainer()) {
            throw new IllegalStateException("Not on a container");
        }
        return this._cur.namespaceForPrefix(prefix, true);
    }

    public String _prefixForNamespace(String ns) {
        if (ns == null || ns.length() == 0) {
            throw new IllegalArgumentException("Must specify a namespace");
        }
        return this._cur.prefixForNamespace(ns, null, true);
    }

    public void _getAllNamespaces(Map addToThis) {
        if (!this._cur.isContainer()) {
            throw new IllegalStateException("Not on a container");
        }
        if (addToThis != null) {
            Locale.getAllNamespaces(this._cur, addToThis);
        }
    }

    public XmlObject _getObject() {
        return this._cur.getObject();
    }

    public XmlCursor.TokenType _prevTokenType() {
        this._cur.push();
        XmlCursor.TokenType tt = this._toPrevToken();
        this._cur.pop();
        return tt;
    }

    public boolean _hasNextToken() {
        return this._cur._pos != -1 || this._cur._xobj.kind() != 1;
    }

    public boolean _hasPrevToken() {
        return this._cur.kind() != 1;
    }

    public XmlCursor.TokenType _toFirstContentToken() {
        if (!this._cur.isContainer()) {
            return XmlCursor.TokenType.NONE;
        }
        this._cur.next();
        return this.currentTokenType();
    }

    public XmlCursor.TokenType _toEndToken() {
        if (!this._cur.isContainer()) {
            return XmlCursor.TokenType.NONE;
        }
        this._cur.toEnd();
        return this.currentTokenType();
    }

    public boolean _toChild(String local) {
        return this._toChild(null, local);
    }

    public boolean _toChild(QName name) {
        return this._toChild(name, 0);
    }

    public boolean _toChild(int index) {
        return this._toChild(null, index);
    }

    public boolean _toChild(String uri, String local) {
        Cursor.validateLocalName(local);
        return this._toChild(this._cur._locale.makeQName(uri, local), 0);
    }

    public boolean _toChild(QName name, int index) {
        return Locale.toChild(this._cur, name, index);
    }

    public int _toNextChar(int maxCharacterCount) {
        return this._cur.nextChars(maxCharacterCount);
    }

    public int _toPrevChar(int maxCharacterCount) {
        return this._cur.prevChars(maxCharacterCount);
    }

    public boolean _toPrevSibling() {
        return Locale.toPrevSiblingElement(this._cur);
    }

    public boolean _toLastChild() {
        return Locale.toLastChildElement(this._cur);
    }

    public boolean _toFirstChild() {
        return Locale.toFirstChildElement(this._cur);
    }

    public boolean _toNextSibling(String name) {
        return this._toNextSibling(new QName(name));
    }

    public boolean _toNextSibling(String uri, String local) {
        Cursor.validateLocalName(local);
        return this._toNextSibling(this._cur._locale._qnameFactory.getQName(uri, local));
    }

    public boolean _toNextSibling(QName name) {
        this._cur.push();
        while (this.___toNextSibling()) {
            if (!this._cur.getName().equals(name)) continue;
            this._cur.popButStay();
            return true;
        }
        this._cur.pop();
        return false;
    }

    public boolean _toFirstAttribute() {
        return this._cur.isContainer() && Locale.toFirstNormalAttr(this._cur);
    }

    public boolean _toLastAttribute() {
        if (this._cur.isContainer()) {
            this._cur.push();
            this._cur.push();
            boolean foundAttr = false;
            while (this._cur.toNextAttr()) {
                if (!this._cur.isNormalAttr()) continue;
                this._cur.popButStay();
                this._cur.push();
                foundAttr = true;
            }
            this._cur.pop();
            if (foundAttr) {
                this._cur.popButStay();
                return true;
            }
            this._cur.pop();
        }
        return false;
    }

    public boolean _toNextAttribute() {
        return this._cur.isAttr() && Locale.toNextNormalAttr(this._cur);
    }

    public boolean _toPrevAttribute() {
        return this._cur.isAttr() && Locale.toPrevNormalAttr(this._cur);
    }

    public String _getAttributeText(QName attrName) {
        if (attrName == null) {
            throw new IllegalArgumentException("Attr name is null");
        }
        if (!this._cur.isContainer()) {
            return null;
        }
        return this._cur.getAttrValue(attrName);
    }

    public boolean _setAttributeText(QName attrName, String value) {
        if (attrName == null) {
            throw new IllegalArgumentException("Attr name is null");
        }
        Cursor.validateLocalName(attrName.getLocalPart());
        if (!this._cur.isContainer()) {
            return false;
        }
        this._cur.setAttrValue(attrName, value);
        return true;
    }

    public boolean _removeAttribute(QName attrName) {
        if (attrName == null) {
            throw new IllegalArgumentException("Attr name is null");
        }
        if (!this._cur.isContainer()) {
            return false;
        }
        return this._cur.removeAttr(attrName);
    }

    public String _getTextValue() {
        if (this._cur.isText()) {
            return this._getChars();
        }
        if (!this._cur.isNode()) {
            throw new IllegalStateException("Can't get text value, current token can have no text value");
        }
        return this._cur.hasChildren() ? Locale.getTextValue(this._cur) : this._cur.getValueAsString();
    }

    public int _getTextValue(char[] chars, int offset, int max) {
        if (this._cur.isText()) {
            return this._getChars(chars, offset, max);
        }
        if (chars == null) {
            throw new IllegalArgumentException("char buffer is null");
        }
        if (offset < 0) {
            throw new IllegalArgumentException("offset < 0");
        }
        if (offset >= chars.length) {
            throw new IllegalArgumentException("offset off end");
        }
        if (max < 0) {
            max = Integer.MAX_VALUE;
        }
        if (offset + max > chars.length) {
            max = chars.length - offset;
        }
        if (!this._cur.isNode()) {
            throw new IllegalStateException("Can't get text value, current token can have no text value");
        }
        if (this._cur.hasChildren()) {
            return Locale.getTextValue(this._cur, 1, chars, offset, max);
        }
        Object src = this._cur.getFirstChars();
        if (this._cur._cchSrc > max) {
            this._cur._cchSrc = max;
        }
        if (this._cur._cchSrc <= 0) {
            return 0;
        }
        CharUtil.getChars(chars, offset, src, this._cur._offSrc, this._cur._cchSrc);
        return this._cur._cchSrc;
    }

    private void setTextValue(Object src, int off, int cch) {
        if (!this._cur.isNode()) {
            throw new IllegalStateException("Can't set text value, current token can have no text value");
        }
        this._cur.moveNodeContents(null, false);
        this._cur.next();
        this._cur.insertChars(src, off, cch);
        this._cur.toParent();
    }

    public void _setTextValue(String text) {
        if (text == null) {
            text = "";
        }
        this.setTextValue(text, 0, text.length());
    }

    public void _setTextValue(char[] sourceChars, int offset, int length) {
        if (length < 0) {
            throw new IndexOutOfBoundsException("setTextValue: length < 0");
        }
        if (sourceChars == null) {
            if (length > 0) {
                throw new IllegalArgumentException("setTextValue: sourceChars == null");
            }
            this.setTextValue(null, 0, 0);
            return;
        }
        if (offset < 0 || offset >= sourceChars.length) {
            throw new IndexOutOfBoundsException("setTextValue: offset out of bounds");
        }
        if (offset + length > sourceChars.length) {
            length = sourceChars.length - offset;
        }
        CharUtil cu = this._cur._locale.getCharUtil();
        this.setTextValue(cu.saveChars(sourceChars, offset, length), cu._offSrc, cu._cchSrc);
    }

    public String _getChars() {
        return this._cur.getCharsAsString(-1);
    }

    public int _getChars(char[] buf, int off, int cch) {
        int cchRight = this._cur.cchRight();
        if (cch < 0 || cch > cchRight) {
            cch = cchRight;
        }
        if (buf == null || off >= buf.length) {
            return 0;
        }
        if (buf.length - off < cch) {
            cch = buf.length - off;
        }
        Object src = this._cur.getChars(cch);
        CharUtil.getChars(buf, off, src, this._cur._offSrc, this._cur._cchSrc);
        return this._cur._cchSrc;
    }

    public void _toStartDoc() {
        this._cur.toRoot();
    }

    public void _toEndDoc() {
        this._toStartDoc();
        this._cur.toEnd();
    }

    public int _comparePosition(Cursor other) {
        int s = this._cur.comparePosition(other._cur);
        if (s == 2) {
            throw new IllegalArgumentException("Cursors not in same document");
        }
        assert (s >= -1 && s <= 1);
        return s;
    }

    public boolean _isLeftOf(Cursor other) {
        return this._comparePosition(other) < 0;
    }

    public boolean _isAtSamePositionAs(Cursor other) {
        return this._cur.isSamePos(other._cur);
    }

    public boolean _isRightOf(Cursor other) {
        return this._comparePosition(other) > 0;
    }

    public XmlCursor _execQuery(String query) {
        return this._execQuery(query, null);
    }

    public XmlCursor _execQuery(String query, XmlOptions options) {
        this.checkThisCursor();
        return Query.cursorExecQuery(this._cur, query, options);
    }

    public boolean _toBookmark(XmlCursor.XmlBookmark bookmark) {
        if (bookmark == null || !(bookmark._currentMark instanceof Xobj.Bookmark)) {
            return false;
        }
        Xobj.Bookmark m = (Xobj.Bookmark)bookmark._currentMark;
        if (m._xobj == null || m._xobj._locale != this._cur._locale) {
            return false;
        }
        this._cur.moveTo(m._xobj, m._pos);
        return true;
    }

    public XmlCursor.XmlBookmark _toNextBookmark(Object key) {
        if (key == null) {
            return null;
        }
        this._cur.push();
        do {
            int cch;
            if ((cch = this._cur.cchRight()) > 1) {
                this._cur.nextChars(1);
                cch = this._cur.firstBookmarkInChars(key, cch - 1);
                this._cur.nextChars(cch >= 0 ? cch : -1);
            } else if (this._toNextToken().isNone()) {
                this._cur.pop();
                return null;
            }
            XmlCursor.XmlBookmark bm = Cursor.getBookmark(key, this._cur);
            if (bm == null) continue;
            this._cur.popButStay();
            return bm;
        } while (this._cur.kind() != -1);
        this._cur.pop();
        return null;
    }

    public XmlCursor.XmlBookmark _toPrevBookmark(Object key) {
        if (key == null) {
            return null;
        }
        this._cur.push();
        do {
            int cch;
            if ((cch = this._cur.cchLeft()) > 1) {
                this._cur.prevChars(1);
                cch = this._cur.firstBookmarkInCharsLeft(key, cch - 1);
                this._cur.prevChars(cch >= 0 ? cch : -1);
            } else if (cch == 1) {
                this._cur.prevChars(1);
            } else if (this._toPrevToken().isNone()) {
                this._cur.pop();
                return null;
            }
            XmlCursor.XmlBookmark bm = Cursor.getBookmark(key, this._cur);
            if (bm == null) continue;
            this._cur.popButStay();
            return bm;
        } while (this._cur.kind() != 1);
        this._cur.pop();
        return null;
    }

    public void _setBookmark(XmlCursor.XmlBookmark bookmark) {
        if (bookmark != null) {
            if (bookmark.getKey() == null) {
                throw new IllegalArgumentException("Annotation key is null");
            }
            bookmark._currentMark = this._cur.setBookmark(bookmark.getKey(), bookmark);
        }
    }

    static XmlCursor.XmlBookmark getBookmark(Object key, Cur c) {
        if (key == null) {
            return null;
        }
        Object bm = c.getBookmark(key);
        return bm != null && bm instanceof XmlCursor.XmlBookmark ? (XmlCursor.XmlBookmark)bm : null;
    }

    public XmlCursor.XmlBookmark _getBookmark(Object key) {
        return key == null ? null : Cursor.getBookmark(key, this._cur);
    }

    public void _clearBookmark(Object key) {
        if (key != null) {
            this._cur.setBookmark(key, null);
        }
    }

    public void _getAllBookmarkRefs(Collection listToFill) {
        if (listToFill != null) {
            Xobj.Bookmark b = this._cur._xobj._bookmarks;
            while (b != null) {
                if (b._value instanceof XmlCursor.XmlBookmark) {
                    listToFill.add(b._value);
                }
                b = b._next;
            }
        }
    }

    public boolean _removeXml() {
        if (this._cur.isRoot()) {
            throw new IllegalStateException("Can't remove a whole document.");
        }
        if (this._cur.isFinish()) {
            return false;
        }
        assert (this._cur.isText() || this._cur.isNode());
        if (this._cur.isText()) {
            this._cur.moveChars(null, -1);
        } else {
            this._cur.moveNode(null);
        }
        return true;
    }

    public boolean _moveXml(Cursor to) {
        to.checkInsertionValidity(this._cur);
        if (this._cur.isText()) {
            int cchRight = this._cur.cchRight();
            assert (cchRight > 0);
            if (this._cur.inChars(to._cur, cchRight, true)) {
                return false;
            }
            this._cur.moveChars(to._cur, cchRight);
            to._cur.nextChars(cchRight);
            return true;
        }
        if (this._cur.contains(to._cur)) {
            return false;
        }
        Cur c = to.tempCur();
        this._cur.moveNode(to._cur);
        to._cur.moveToCur(c);
        c.release();
        return true;
    }

    public boolean _copyXml(Cursor to) {
        to.checkInsertionValidity(this._cur);
        assert (this._cur.isText() || this._cur.isNode());
        Cur c = to.tempCur();
        if (this._cur.isText()) {
            to._cur.insertChars(this._cur.getChars(-1), this._cur._offSrc, this._cur._cchSrc);
        } else {
            this._cur.copyNode(to._cur);
        }
        to._cur.moveToCur(c);
        c.release();
        return true;
    }

    public boolean _removeXmlContents() {
        if (!this._cur.isContainer()) {
            return false;
        }
        this._cur.moveNodeContents(null, false);
        return true;
    }

    private boolean checkContentInsertionValidity(Cursor to) {
        this._cur.push();
        this._cur.next();
        if (this._cur.isFinish()) {
            this._cur.pop();
            return false;
        }
        try {
            to.checkInsertionValidity(this._cur);
        } catch (IllegalArgumentException e) {
            this._cur.pop();
            throw e;
        }
        this._cur.pop();
        return true;
    }

    public boolean _moveXmlContents(Cursor to) {
        if (!this._cur.isContainer() || this._cur.contains(to._cur)) {
            return false;
        }
        if (!this.checkContentInsertionValidity(to)) {
            return false;
        }
        Cur c = to.tempCur();
        this._cur.moveNodeContents(to._cur, false);
        to._cur.moveToCur(c);
        c.release();
        return true;
    }

    public boolean _copyXmlContents(Cursor to) {
        if (!this._cur.isContainer() || this._cur.contains(to._cur)) {
            return false;
        }
        if (!this.checkContentInsertionValidity(to)) {
            return false;
        }
        Cur c = this._cur._locale.tempCur();
        this._cur.copyNode(c);
        Cur c2 = to._cur.tempCur();
        c.moveNodeContents(to._cur, false);
        c.release();
        to._cur.moveToCur(c2);
        c2.release();
        return true;
    }

    public int _removeChars(int cch) {
        int cchRight = this._cur.cchRight();
        if (cchRight == 0 || cch == 0) {
            return 0;
        }
        if (cch < 0 || cch > cchRight) {
            cch = cchRight;
        }
        this._cur.moveChars(null, cch);
        return this._cur._cchSrc;
    }

    public int _moveChars(int cch, Cursor to) {
        int cchRight = this._cur.cchRight();
        if (cchRight <= 0 || cch == 0) {
            return 0;
        }
        if (cch < 0 || cch > cchRight) {
            cch = cchRight;
        }
        to.checkInsertionValidity(this._cur);
        this._cur.moveChars(to._cur, cch);
        to._cur.nextChars(this._cur._cchSrc);
        return this._cur._cchSrc;
    }

    public int _copyChars(int cch, Cursor to) {
        int cchRight = this._cur.cchRight();
        if (cchRight <= 0 || cch == 0) {
            return 0;
        }
        if (cch < 0 || cch > cchRight) {
            cch = cchRight;
        }
        to.checkInsertionValidity(this._cur);
        to._cur.insertChars(this._cur.getChars(cch), this._cur._offSrc, this._cur._cchSrc);
        to._cur.nextChars(this._cur._cchSrc);
        return this._cur._cchSrc;
    }

    public void _insertChars(String text) {
        int l;
        int n = l = text == null ? 0 : text.length();
        if (l > 0) {
            if (this._cur.isRoot() || this._cur.isAttr()) {
                throw new IllegalStateException("Can't insert before the document or an attribute.");
            }
            this._cur.insertChars(text, 0, l);
            this._cur.nextChars(l);
        }
    }

    public void _beginElement(String localName) {
        this._insertElementWithText(localName, null, null);
        this._toPrevToken();
    }

    public void _beginElement(String localName, String uri) {
        this._insertElementWithText(localName, uri, null);
        this._toPrevToken();
    }

    public void _beginElement(QName name) {
        this._insertElementWithText(name, null);
        this._toPrevToken();
    }

    public void _insertElement(String localName) {
        this._insertElementWithText(localName, null, null);
    }

    public void _insertElement(String localName, String uri) {
        this._insertElementWithText(localName, uri, null);
    }

    public void _insertElement(QName name) {
        this._insertElementWithText(name, null);
    }

    public void _insertElementWithText(String localName, String text) {
        this._insertElementWithText(localName, null, text);
    }

    public void _insertElementWithText(String localName, String uri, String text) {
        Cursor.validateLocalName(localName);
        this._insertElementWithText(this._cur._locale.makeQName(uri, localName), text);
    }

    public void _insertElementWithText(QName name, String text) {
        Cursor.validateLocalName(name.getLocalPart());
        Cur c = this._cur._locale.tempCur();
        c.createElement(name);
        this.insertNode(c, text);
        c.release();
    }

    public void _insertAttribute(String localName) {
        this._insertAttributeWithValue(localName, null);
    }

    public void _insertAttribute(String localName, String uri) {
        this._insertAttributeWithValue(localName, uri, null);
    }

    public void _insertAttribute(QName name) {
        this._insertAttributeWithValue(name, null);
    }

    public void _insertAttributeWithValue(String localName, String value) {
        this._insertAttributeWithValue(localName, null, value);
    }

    public void _insertAttributeWithValue(String localName, String uri, String value) {
        Cursor.validateLocalName(localName);
        this._insertAttributeWithValue(this._cur._locale.makeQName(uri, localName), value);
    }

    public void _insertAttributeWithValue(QName name, String text) {
        Cursor.validateLocalName(name.getLocalPart());
        Cur c = this._cur._locale.tempCur();
        c.createAttr(name);
        this.insertNode(c, text);
        c.release();
    }

    public void _insertNamespace(String prefix, String namespace) {
        this._insertAttributeWithValue(this._cur._locale.createXmlns(prefix), namespace);
    }

    public void _insertComment(String text) {
        Cur c = this._cur._locale.tempCur();
        c.createComment();
        this.insertNode(c, text);
        c.release();
    }

    public void _insertProcInst(String target, String text) {
        Cursor.validateLocalName(target);
        if (Locale.beginsWithXml(target) && target.length() == 3) {
            throw new IllegalArgumentException("Target is 'xml'");
        }
        Cur c = this._cur._locale.tempCur();
        c.createProcinst(target);
        this.insertNode(c, text);
        c.release();
    }

    public void _dump() {
        this._cur.dump();
    }

    private void checkThisCursor() {
        if (this._cur == null) {
            throw new IllegalStateException("This cursor has been disposed");
        }
    }

    private Cursor checkCursors(XmlCursor xOther) {
        this.checkThisCursor();
        if (xOther == null) {
            throw new IllegalArgumentException("Other cursor is <null>");
        }
        if (!(xOther instanceof Cursor)) {
            throw new IllegalArgumentException("Incompatible cursors: " + xOther);
        }
        Cursor other = (Cursor)xOther;
        if (other._cur == null) {
            throw new IllegalStateException("Other cursor has been disposed");
        }
        return other;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private int twoLocaleOp(XmlCursor xOther, int op, int arg) {
        Cursor other = this.checkCursors(xOther);
        Locale locale = this._cur._locale;
        Locale otherLocale = other._cur._locale;
        if (locale == otherLocale) {
            if (locale.noSync()) {
                return this.twoLocaleOp(other, op, arg);
            }
            Locale locale2 = locale;
            synchronized (locale2) {
                return this.twoLocaleOp(other, op, arg);
            }
        }
        if (locale.noSync()) {
            if (otherLocale.noSync()) {
                return this.twoLocaleOp(other, op, arg);
            }
            Locale locale3 = otherLocale;
            synchronized (locale3) {
                return this.twoLocaleOp(other, op, arg);
            }
        }
        if (otherLocale.noSync()) {
            Locale locale4 = locale;
            synchronized (locale4) {
                return this.twoLocaleOp(other, op, arg);
            }
        }
        boolean acquired = false;
        try {
            GlobalLock.acquire();
            acquired = true;
            Locale locale5 = locale;
            synchronized (locale5) {
                Locale locale6 = otherLocale;
                synchronized (locale6) {
                    try {
                        GlobalLock.release();
                        acquired = false;
                        int n = this.twoLocaleOp(other, op, arg);
                        return n;
                    } catch (Throwable throwable) {
                        try {
                            throw throwable;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e.getMessage(), e);
                        }
                    }
                }
            }
        } finally {
            if (acquired) {
                GlobalLock.release();
            }
        }
    }

    private int twoLocaleOp(Cursor other, int op, int arg) {
        Locale locale = this._cur._locale;
        Locale otherLocale = other._cur._locale;
        locale.enter(otherLocale);
        try {
            switch (op) {
                case 0: {
                    int n = this._moveXml(other) ? 1 : 0;
                    return n;
                }
                case 1: {
                    int n = this._copyXml(other) ? 1 : 0;
                    return n;
                }
                case 2: {
                    int n = this._moveXmlContents(other) ? 1 : 0;
                    return n;
                }
                case 3: {
                    int n = this._copyXmlContents(other) ? 1 : 0;
                    return n;
                }
                case 4: {
                    int n = this._moveChars(arg, other);
                    return n;
                }
                case 5: {
                    int n = this._copyChars(arg, other);
                    return n;
                }
            }
            throw new RuntimeException("Unknown operation: " + op);
        } finally {
            locale.exit(otherLocale);
        }
    }

    @Override
    public boolean moveXml(XmlCursor xTo) {
        return this.twoLocaleOp(xTo, 0, 0) == 1;
    }

    @Override
    public boolean copyXml(XmlCursor xTo) {
        return this.twoLocaleOp(xTo, 1, 0) == 1;
    }

    @Override
    public boolean moveXmlContents(XmlCursor xTo) {
        return this.twoLocaleOp(xTo, 2, 0) == 1;
    }

    @Override
    public boolean copyXmlContents(XmlCursor xTo) {
        return this.twoLocaleOp(xTo, 3, 0) == 1;
    }

    @Override
    public int moveChars(int cch, XmlCursor xTo) {
        return this.twoLocaleOp(xTo, 4, cch);
    }

    @Override
    public int copyChars(int cch, XmlCursor xTo) {
        return this.twoLocaleOp(xTo, 5, cch);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toCursor(XmlCursor xOther) {
        Cursor other = this.checkCursors(xOther);
        if (this._cur._locale != other._cur._locale) {
            return false;
        }
        if (this._cur._locale.noSync()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toCursor(other);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toCursor(other);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    @Override
    public boolean isInSameDocument(XmlCursor xOther) {
        return xOther == null ? false : this._cur.isInSameTree(this.checkCursors((XmlCursor)xOther)._cur);
    }

    private Cursor preCheck(XmlCursor xOther) {
        Cursor other = this.checkCursors(xOther);
        if (this._cur._locale != other._cur._locale) {
            throw new IllegalArgumentException("Cursors not in same document");
        }
        return other;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int comparePosition(XmlCursor xOther) {
        Cursor other = this.preCheck(xOther);
        if (this._cur._locale.noSync()) {
            this._cur._locale.enter();
            try {
                int n = this._comparePosition(other);
                return n;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            int n;
            this._cur._locale.enter();
            try {
                n = this._comparePosition(other);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return n;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isLeftOf(XmlCursor xOther) {
        Cursor other = this.preCheck(xOther);
        if (this._cur._locale.noSync()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._isLeftOf(other);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._isLeftOf(other);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isAtSamePositionAs(XmlCursor xOther) {
        Cursor other = this.preCheck(xOther);
        if (this._cur._locale.noSync()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._isAtSamePositionAs(other);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._isAtSamePositionAs(other);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isRightOf(XmlCursor xOther) {
        Cursor other = this.preCheck(xOther);
        if (this._cur._locale.noSync()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._isRightOf(other);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._isRightOf(other);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static XmlCursor newCursor(Xobj x, int p) {
        Locale l = x._locale;
        if (l.noSync()) {
            l.enter();
            try {
                Cursor cursor = new Cursor(x, p);
                return cursor;
            } finally {
                l.exit();
            }
        }
        Locale locale = l;
        synchronized (locale) {
            Cursor cursor;
            l.enter();
            try {
                cursor = new Cursor(x, p);
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return cursor;
        }
    }

    private boolean preCheck() {
        this.checkThisCursor();
        return this._cur._locale.noSync();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void dispose() {
        if (this._cur != null) {
            Locale l = this._cur._locale;
            if (this.preCheck()) {
                l.enter();
                try {
                    this._dispose();
                } finally {
                    l.exit();
                }
            }
            Locale locale = l;
            synchronized (locale) {
                l.enter();
                try {
                    this._dispose();
                } finally {
                    l.exit();
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object monitor() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                Object object = this._monitor();
                return object;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            Object object;
            this._cur._locale.enter();
            try {
                object = this._monitor();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return object;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDocumentProperties documentProperties() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XmlDocumentProperties xmlDocumentProperties = this._documentProperties();
                return xmlDocumentProperties;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XmlDocumentProperties xmlDocumentProperties;
            this._cur._locale.enter();
            try {
                xmlDocumentProperties = this._documentProperties();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return xmlDocumentProperties;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor newCursor() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XmlCursor xmlCursor = this._newCursor();
                return xmlCursor;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XmlCursor xmlCursor;
            this._cur._locale.enter();
            try {
                xmlCursor = this._newCursor();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return xmlCursor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XMLStreamReader newXMLStreamReader() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XMLStreamReader xMLStreamReader = this._newXMLStreamReader();
                return xMLStreamReader;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XMLStreamReader xMLStreamReader;
            this._cur._locale.enter();
            try {
                xMLStreamReader = this._newXMLStreamReader();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return xMLStreamReader;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XMLStreamReader newXMLStreamReader(XmlOptions options) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XMLStreamReader xMLStreamReader = this._newXMLStreamReader(options);
                return xMLStreamReader;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XMLStreamReader xMLStreamReader;
            this._cur._locale.enter();
            try {
                xMLStreamReader = this._newXMLStreamReader(options);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return xMLStreamReader;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XMLInputStream newXMLInputStream() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XMLInputStream xMLInputStream = this._newXMLInputStream();
                return xMLInputStream;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XMLInputStream xMLInputStream;
            this._cur._locale.enter();
            try {
                xMLInputStream = this._newXMLInputStream();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return xMLInputStream;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String xmlText() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                String string = this._xmlText();
                return string;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            String string;
            this._cur._locale.enter();
            try {
                string = this._xmlText();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public InputStream newInputStream() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                InputStream inputStream = this._newInputStream();
                return inputStream;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            InputStream inputStream;
            this._cur._locale.enter();
            try {
                inputStream = this._newInputStream();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return inputStream;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Reader newReader() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                Reader reader = this._newReader();
                return reader;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            Reader reader;
            this._cur._locale.enter();
            try {
                reader = this._newReader();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return reader;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Node newDomNode() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                Node node = this._newDomNode();
                return node;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            Node node;
            this._cur._locale.enter();
            try {
                node = this._newDomNode();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return node;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Node getDomNode() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                Node node = this._getDomNode();
                return node;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            Node node;
            this._cur._locale.enter();
            try {
                node = this._getDomNode();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return node;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void save(ContentHandler ch, LexicalHandler lh) throws SAXException {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._save(ch, lh);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._save(ch, lh);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void save(File file) throws IOException {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._save(file);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._save(file);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void save(OutputStream os) throws IOException {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._save(os);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._save(os);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void save(Writer w) throws IOException {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._save(w);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._save(w);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XMLInputStream newXMLInputStream(XmlOptions options) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XMLInputStream xMLInputStream = this._newXMLInputStream(options);
                return xMLInputStream;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XMLInputStream xMLInputStream;
            this._cur._locale.enter();
            try {
                xMLInputStream = this._newXMLInputStream(options);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return xMLInputStream;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String xmlText(XmlOptions options) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                String string = this._xmlText(options);
                return string;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            String string;
            this._cur._locale.enter();
            try {
                string = this._xmlText(options);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public InputStream newInputStream(XmlOptions options) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                InputStream inputStream = this._newInputStream(options);
                return inputStream;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            InputStream inputStream;
            this._cur._locale.enter();
            try {
                inputStream = this._newInputStream(options);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return inputStream;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Reader newReader(XmlOptions options) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                Reader reader = this._newReader(options);
                return reader;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            Reader reader;
            this._cur._locale.enter();
            try {
                reader = this._newReader(options);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return reader;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Node newDomNode(XmlOptions options) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                Node node = this._newDomNode(options);
                return node;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            Node node;
            this._cur._locale.enter();
            try {
                node = this._newDomNode(options);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return node;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void save(ContentHandler ch, LexicalHandler lh, XmlOptions options) throws SAXException {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._save(ch, lh, options);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._save(ch, lh, options);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void save(File file, XmlOptions options) throws IOException {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._save(file, options);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._save(file, options);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void save(OutputStream os, XmlOptions options) throws IOException {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._save(os, options);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._save(os, options);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void save(Writer w, XmlOptions options) throws IOException {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._save(w, options);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._save(w, options);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void push() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._push();
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._push();
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean pop() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._pop();
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._pop();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void selectPath(String path) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._selectPath(path);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._selectPath(path);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void selectPath(String path, XmlOptions options) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._selectPath(path, options);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._selectPath(path, options);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean hasNextSelection() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._hasNextSelection();
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._hasNextSelection();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toNextSelection() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toNextSelection();
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toNextSelection();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toSelection(int i) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toSelection(i);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toSelection(i);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getSelectionCount() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                int n = this._getSelectionCount();
                return n;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            int n;
            this._cur._locale.enter();
            try {
                n = this._getSelectionCount();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return n;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void addToSelection() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._addToSelection();
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._addToSelection();
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void clearSelections() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._clearSelections();
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._clearSelections();
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toBookmark(XmlCursor.XmlBookmark bookmark) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toBookmark(bookmark);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toBookmark(bookmark);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor.XmlBookmark toNextBookmark(Object key) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XmlCursor.XmlBookmark xmlBookmark = this._toNextBookmark(key);
                return xmlBookmark;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XmlCursor.XmlBookmark xmlBookmark;
            this._cur._locale.enter();
            try {
                xmlBookmark = this._toNextBookmark(key);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return xmlBookmark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor.XmlBookmark toPrevBookmark(Object key) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XmlCursor.XmlBookmark xmlBookmark = this._toPrevBookmark(key);
                return xmlBookmark;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XmlCursor.XmlBookmark xmlBookmark;
            this._cur._locale.enter();
            try {
                xmlBookmark = this._toPrevBookmark(key);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return xmlBookmark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public QName getName() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                QName qName = this._getName();
                return qName;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            QName qName;
            this._cur._locale.enter();
            try {
                qName = this._getName();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return qName;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setName(QName name) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._setName(name);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._setName(name);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String namespaceForPrefix(String prefix) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                String string = this._namespaceForPrefix(prefix);
                return string;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            String string;
            this._cur._locale.enter();
            try {
                string = this._namespaceForPrefix(prefix);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String prefixForNamespace(String namespaceURI) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                String string = this._prefixForNamespace(namespaceURI);
                return string;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            String string;
            this._cur._locale.enter();
            try {
                string = this._prefixForNamespace(namespaceURI);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void getAllNamespaces(Map addToThis) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._getAllNamespaces(addToThis);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._getAllNamespaces(addToThis);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlObject getObject() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XmlObject xmlObject = this._getObject();
                return xmlObject;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XmlObject xmlObject;
            this._cur._locale.enter();
            try {
                xmlObject = this._getObject();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return xmlObject;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor.TokenType currentTokenType() {
        if (this.preCheck()) {
            return this._currentTokenType();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._currentTokenType();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isStartdoc() {
        if (this.preCheck()) {
            return this._isStartdoc();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._isStartdoc();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isEnddoc() {
        if (this.preCheck()) {
            return this._isEnddoc();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._isEnddoc();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isStart() {
        if (this.preCheck()) {
            return this._isStart();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._isStart();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isEnd() {
        if (this.preCheck()) {
            return this._isEnd();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._isEnd();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isText() {
        if (this.preCheck()) {
            return this._isText();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._isText();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isAttr() {
        if (this.preCheck()) {
            return this._isAttr();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._isAttr();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isNamespace() {
        if (this.preCheck()) {
            return this._isNamespace();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._isNamespace();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isComment() {
        if (this.preCheck()) {
            return this._isComment();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._isComment();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isProcinst() {
        if (this.preCheck()) {
            return this._isProcinst();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._isProcinst();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isContainer() {
        if (this.preCheck()) {
            return this._isContainer();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._isContainer();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isFinish() {
        if (this.preCheck()) {
            return this._isFinish();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._isFinish();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isAnyAttr() {
        if (this.preCheck()) {
            return this._isAnyAttr();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._isAnyAttr();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor.TokenType prevTokenType() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XmlCursor.TokenType tokenType = this._prevTokenType();
                return tokenType;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XmlCursor.TokenType tokenType;
            this._cur._locale.enter();
            try {
                tokenType = this._prevTokenType();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return tokenType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean hasNextToken() {
        if (this.preCheck()) {
            return this._hasNextToken();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._hasNextToken();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean hasPrevToken() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._hasPrevToken();
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._hasPrevToken();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor.TokenType toNextToken() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XmlCursor.TokenType tokenType = this._toNextToken();
                return tokenType;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XmlCursor.TokenType tokenType;
            this._cur._locale.enter();
            try {
                tokenType = this._toNextToken();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return tokenType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor.TokenType toPrevToken() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XmlCursor.TokenType tokenType = this._toPrevToken();
                return tokenType;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XmlCursor.TokenType tokenType;
            this._cur._locale.enter();
            try {
                tokenType = this._toPrevToken();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return tokenType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor.TokenType toFirstContentToken() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XmlCursor.TokenType tokenType = this._toFirstContentToken();
                return tokenType;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XmlCursor.TokenType tokenType;
            this._cur._locale.enter();
            try {
                tokenType = this._toFirstContentToken();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return tokenType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor.TokenType toEndToken() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XmlCursor.TokenType tokenType = this._toEndToken();
                return tokenType;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XmlCursor.TokenType tokenType;
            this._cur._locale.enter();
            try {
                tokenType = this._toEndToken();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return tokenType;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int toNextChar(int cch) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                int n = this._toNextChar(cch);
                return n;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            int n;
            this._cur._locale.enter();
            try {
                n = this._toNextChar(cch);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return n;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int toPrevChar(int cch) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                int n = this._toPrevChar(cch);
                return n;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            int n;
            this._cur._locale.enter();
            try {
                n = this._toPrevChar(cch);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return n;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean ___toNextSibling() {
        if (!this._cur.hasParent()) {
            return false;
        }
        Xobj parent = this._cur.getParentNoRoot();
        if (parent == null) {
            this._cur._locale.enter();
            try {
                parent = this._cur.getParent();
            } finally {
                this._cur._locale.exit();
            }
        }
        return Locale.toNextSiblingElement(this._cur, parent);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toNextSibling() {
        if (this.preCheck()) {
            return this.___toNextSibling();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this.___toNextSibling();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toPrevSibling() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toPrevSibling();
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toPrevSibling();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toParent() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toParent();
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toParent();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toFirstChild() {
        if (this.preCheck()) {
            return this._toFirstChild();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._toFirstChild();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toLastChild() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toLastChild();
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toLastChild();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toChild(String name) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toChild(name);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toChild(name);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toChild(String namespace, String name) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toChild(namespace, name);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toChild(namespace, name);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toChild(QName name) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toChild(name);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toChild(name);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toChild(int index) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toChild(index);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toChild(index);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toChild(QName name, int index) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toChild(name, index);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toChild(name, index);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toNextSibling(String name) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toNextSibling(name);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toNextSibling(name);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toNextSibling(String namespace, String name) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toNextSibling(namespace, name);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toNextSibling(namespace, name);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toNextSibling(QName name) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toNextSibling(name);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toNextSibling(name);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toFirstAttribute() {
        if (this.preCheck()) {
            return this._toFirstAttribute();
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            return this._toFirstAttribute();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toLastAttribute() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toLastAttribute();
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toLastAttribute();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toNextAttribute() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toNextAttribute();
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toNextAttribute();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean toPrevAttribute() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._toPrevAttribute();
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._toPrevAttribute();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getAttributeText(QName attrName) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                String string = this._getAttributeText(attrName);
                return string;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            String string;
            this._cur._locale.enter();
            try {
                string = this._getAttributeText(attrName);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean setAttributeText(QName attrName, String value) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._setAttributeText(attrName, value);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._setAttributeText(attrName, value);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean removeAttribute(QName attrName) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._removeAttribute(attrName);
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._removeAttribute(attrName);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getTextValue() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                String string = this._getTextValue();
                return string;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            String string;
            this._cur._locale.enter();
            try {
                string = this._getTextValue();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getTextValue(char[] chars, int offset, int cch) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                int n = this._getTextValue(chars, offset, cch);
                return n;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            int n;
            this._cur._locale.enter();
            try {
                n = this._getTextValue(chars, offset, cch);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return n;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTextValue(String text) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._setTextValue(text);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._setTextValue(text);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setTextValue(char[] sourceChars, int offset, int length) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._setTextValue(sourceChars, offset, length);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._setTextValue(sourceChars, offset, length);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getChars() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                String string = this._getChars();
                return string;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            String string;
            this._cur._locale.enter();
            try {
                string = this._getChars();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return string;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int getChars(char[] chars, int offset, int cch) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                int n = this._getChars(chars, offset, cch);
                return n;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            int n;
            this._cur._locale.enter();
            try {
                n = this._getChars(chars, offset, cch);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return n;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void toStartDoc() {
        if (this.preCheck()) {
            this._toStartDoc();
        } else {
            Locale locale = this._cur._locale;
            synchronized (locale) {
                this._toStartDoc();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void toEndDoc() {
        if (this.preCheck()) {
            this._toEndDoc();
        } else {
            Locale locale = this._cur._locale;
            synchronized (locale) {
                this._toEndDoc();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor execQuery(String query) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XmlCursor xmlCursor = this._execQuery(query);
                return xmlCursor;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XmlCursor xmlCursor;
            this._cur._locale.enter();
            try {
                xmlCursor = this._execQuery(query);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return xmlCursor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor execQuery(String query, XmlOptions options) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XmlCursor xmlCursor = this._execQuery(query, options);
                return xmlCursor;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XmlCursor xmlCursor;
            this._cur._locale.enter();
            try {
                xmlCursor = this._execQuery(query, options);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return xmlCursor;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor.ChangeStamp getDocChangeStamp() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XmlCursor.ChangeStamp changeStamp = this._getDocChangeStamp();
                return changeStamp;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XmlCursor.ChangeStamp changeStamp;
            this._cur._locale.enter();
            try {
                changeStamp = this._getDocChangeStamp();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return changeStamp;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void setBookmark(XmlCursor.XmlBookmark bookmark) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._setBookmark(bookmark);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._setBookmark(bookmark);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor.XmlBookmark getBookmark(Object key) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                XmlCursor.XmlBookmark xmlBookmark = this._getBookmark(key);
                return xmlBookmark;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            XmlCursor.XmlBookmark xmlBookmark;
            this._cur._locale.enter();
            try {
                xmlBookmark = this._getBookmark(key);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return xmlBookmark;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void clearBookmark(Object key) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._clearBookmark(key);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._clearBookmark(key);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void getAllBookmarkRefs(Collection listToFill) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._getAllBookmarkRefs(listToFill);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._getAllBookmarkRefs(listToFill);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean removeXml() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._removeXml();
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._removeXml();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean removeXmlContents() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                boolean bl = this._removeXmlContents();
                return bl;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            boolean bl;
            this._cur._locale.enter();
            try {
                bl = this._removeXmlContents();
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return bl;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int removeChars(int cch) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                int n = this._removeChars(cch);
                return n;
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            int n;
            this._cur._locale.enter();
            try {
                n = this._removeChars(cch);
                this._cur._locale.exit();
            } catch (Throwable throwable) {
                this._cur._locale.exit();
                throw throwable;
            }
            return n;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertChars(String text) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertChars(text);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertChars(text);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertElement(QName name) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertElement(name);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertElement(name);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertElement(String localName) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertElement(localName);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertElement(localName);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertElement(String localName, String uri) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertElement(localName, uri);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertElement(localName, uri);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void beginElement(QName name) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._beginElement(name);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._beginElement(name);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void beginElement(String localName) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._beginElement(localName);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._beginElement(localName);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void beginElement(String localName, String uri) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._beginElement(localName, uri);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._beginElement(localName, uri);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertElementWithText(QName name, String text) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertElementWithText(name, text);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertElementWithText(name, text);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertElementWithText(String localName, String text) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertElementWithText(localName, text);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertElementWithText(localName, text);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertElementWithText(String localName, String uri, String text) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertElementWithText(localName, uri, text);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertElementWithText(localName, uri, text);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertAttribute(String localName) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertAttribute(localName);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertAttribute(localName);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertAttribute(String localName, String uri) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertAttribute(localName, uri);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertAttribute(localName, uri);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertAttribute(QName name) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertAttribute(name);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertAttribute(name);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertAttributeWithValue(String Name2, String value) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertAttributeWithValue(Name2, value);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertAttributeWithValue(Name2, value);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertAttributeWithValue(String name, String uri, String value) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertAttributeWithValue(name, uri, value);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertAttributeWithValue(name, uri, value);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertAttributeWithValue(QName name, String value) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertAttributeWithValue(name, value);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertAttributeWithValue(name, value);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertNamespace(String prefix, String namespace) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertNamespace(prefix, namespace);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertNamespace(prefix, namespace);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertComment(String text) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertComment(text);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertComment(text);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void insertProcInst(String target, String text) {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._insertProcInst(target, text);
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._insertProcInst(target, text);
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void dump() {
        if (this.preCheck()) {
            this._cur._locale.enter();
            try {
                this._dump();
            } finally {
                this._cur._locale.exit();
            }
        }
        Locale locale = this._cur._locale;
        synchronized (locale) {
            this._cur._locale.enter();
            try {
                this._dump();
            } finally {
                this._cur._locale.exit();
            }
        }
    }

    private static final class DomSaver
    extends Saver {
        private Cur _nodeCur;
        private SchemaType _type;
        private SchemaTypeLoader _stl;
        private XmlOptions _options;
        private boolean _isFrag;

        DomSaver(Cur c, boolean isFrag, XmlOptions options) {
            super(c, options);
            if (c.isUserNode()) {
                this._type = c.getUser().get_schema_type();
            }
            this._stl = c._locale._schemaTypeLoader;
            this._options = options;
            this._isFrag = isFrag;
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        Node saveDom() {
            Locale l = Locale.getLocale(this._stl, this._options);
            l.enter();
            try {
                this._nodeCur = l.getCur();
                while (this.process()) {
                }
                while (!this._nodeCur.isRoot()) {
                    this._nodeCur.toParent();
                }
                if (this._type != null) {
                    this._nodeCur.setType(this._type);
                }
                Node node = (Node)((Object)this._nodeCur.getDom());
                this._nodeCur.release();
                this._nodeCur = null;
                Node node2 = node;
                return node2;
            } finally {
                l.exit();
            }
        }

        @Override
        protected boolean emitElement(Saver.SaveCur c, ArrayList attrNames, ArrayList attrValues) {
            if (Locale.isFragmentQName(c.getName())) {
                this._nodeCur.moveTo(null, -2);
            }
            this.ensureDoc();
            this._nodeCur.createElement(this.getQualifiedName(c, c.getName()));
            this._nodeCur.next();
            this.iterateMappings();
            while (this.hasMapping()) {
                this._nodeCur.createAttr(this._nodeCur._locale.createXmlns(this.mappingPrefix()));
                this._nodeCur.next();
                this._nodeCur.insertString(this.mappingUri());
                this._nodeCur.toParent();
                this._nodeCur.skipWithAttrs();
                this.nextMapping();
            }
            for (int i = 0; i < attrNames.size(); ++i) {
                this._nodeCur.createAttr(this.getQualifiedName(c, (QName)attrNames.get(i)));
                this._nodeCur.next();
                this._nodeCur.insertString((String)attrValues.get(i));
                this._nodeCur.toParent();
                this._nodeCur.skipWithAttrs();
            }
            return false;
        }

        @Override
        protected void emitFinish(Saver.SaveCur c) {
            if (!Locale.isFragmentQName(c.getName())) {
                assert (this._nodeCur.isEnd());
                this._nodeCur.next();
            }
        }

        @Override
        protected void emitText(Saver.SaveCur c) {
            this.ensureDoc();
            Object src = c.getChars();
            if (c._cchSrc > 0) {
                this._nodeCur.insertChars(src, c._offSrc, c._cchSrc);
                this._nodeCur.next();
            }
        }

        @Override
        protected void emitComment(Saver.SaveCur c) {
            this.ensureDoc();
            this._nodeCur.createComment();
            this.emitTextValue(c);
            this._nodeCur.skip();
        }

        @Override
        protected void emitProcinst(Saver.SaveCur c) {
            this.ensureDoc();
            this._nodeCur.createProcinst(c.getName().getLocalPart());
            this.emitTextValue(c);
            this._nodeCur.skip();
        }

        @Override
        protected void emitDocType(String docTypeName, String publicId, String systemId) {
            this.ensureDoc();
            XmlDocumentProperties props = Locale.getDocProps(this._nodeCur, true);
            props.setDoctypeName(docTypeName);
            props.setDoctypePublicId(publicId);
            props.setDoctypeSystemId(systemId);
        }

        @Override
        protected void emitStartDoc(Saver.SaveCur c) {
            this.ensureDoc();
        }

        @Override
        protected void emitEndDoc(Saver.SaveCur c) {
        }

        private QName getQualifiedName(Saver.SaveCur c, QName name) {
            String prefix;
            String uri = name.getNamespaceURI();
            String string = prefix = uri.length() > 0 ? this.getUriMapping(uri) : "";
            if (prefix.equals(name.getPrefix())) {
                return name;
            }
            return this._nodeCur._locale.makeQName(uri, name.getLocalPart(), prefix);
        }

        private void emitTextValue(Saver.SaveCur c) {
            c.push();
            c.next();
            if (c.isText()) {
                this._nodeCur.next();
                this._nodeCur.insertChars(c.getChars(), c._offSrc, c._cchSrc);
                this._nodeCur.toParent();
            }
            c.pop();
        }

        private void ensureDoc() {
            if (!this._nodeCur.isPositioned()) {
                if (this._isFrag) {
                    this._nodeCur.createDomDocFragRoot();
                } else {
                    this._nodeCur.createDomDocumentRoot();
                }
                this._nodeCur.next();
            }
        }
    }

    private static final class ChangeStampImpl
    implements XmlCursor.ChangeStamp {
        private final Locale _locale;
        private final long _versionStamp;

        ChangeStampImpl(Locale l) {
            this._locale = l;
            this._versionStamp = this._locale.version();
        }

        @Override
        public boolean hasChanged() {
            return this._versionStamp != this._locale.version();
        }
    }
}

