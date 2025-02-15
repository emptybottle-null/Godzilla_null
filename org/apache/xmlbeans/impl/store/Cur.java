/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.store;

import java.io.PrintStream;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.CDataBookmark;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlLineNumber;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.soap.Detail;
import org.apache.xmlbeans.impl.soap.DetailEntry;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.soap.SOAPBodyElement;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPEnvelope;
import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.apache.xmlbeans.impl.soap.SOAPFaultElement;
import org.apache.xmlbeans.impl.soap.SOAPHeader;
import org.apache.xmlbeans.impl.soap.SOAPHeaderElement;
import org.apache.xmlbeans.impl.store.CharUtil;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.store.Xobj;
import org.apache.xmlbeans.impl.values.TypeStoreUser;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Node;

final class Cur {
    static final int TEXT = 0;
    static final int ROOT = 1;
    static final int ELEM = 2;
    static final int ATTR = 3;
    static final int COMMENT = 4;
    static final int PROCINST = 5;
    static final int POOLED = 0;
    static final int REGISTERED = 1;
    static final int EMBEDDED = 2;
    static final int DISPOSED = 3;
    static final int END_POS = -1;
    static final int NO_POS = -2;
    static final String LOAD_USE_LOCALE_CHAR_UTIL = "LOAD_USE_LOCALE_CHAR_UTIL";
    Locale _locale;
    Xobj _xobj;
    int _pos;
    int _state;
    String _id;
    Cur _nextTemp;
    Cur _prevTemp;
    int _tempFrame;
    Cur _next;
    Cur _prev;
    Locale.Ref _ref;
    int _stackTop;
    int _selectionFirst;
    int _selectionN;
    int _selectionLoc;
    int _selectionCount;
    private int _posTemp;
    int _offSrc;
    int _cchSrc;

    Cur(Locale l) {
        this._locale = l;
        this._pos = -2;
        this._tempFrame = -1;
        this._state = 0;
        this._stackTop = -1;
        this._selectionFirst = -1;
        this._selectionN = -1;
        this._selectionLoc = -1;
        this._selectionCount = 0;
    }

    boolean isPositioned() {
        assert (this.isNormal());
        return this._xobj != null;
    }

    static boolean kindIsContainer(int k) {
        return k == 2 || k == 1;
    }

    static boolean kindIsFinish(int k) {
        return k == -2 || k == -1;
    }

    int kind() {
        assert (this.isPositioned());
        int kind = this._xobj.kind();
        return this._pos == 0 ? kind : (this._pos == -1 ? -kind : 0);
    }

    boolean isRoot() {
        assert (this.isPositioned());
        return this._pos == 0 && this._xobj.kind() == 1;
    }

    boolean isElem() {
        assert (this.isPositioned());
        return this._pos == 0 && this._xobj.kind() == 2;
    }

    boolean isAttr() {
        assert (this.isPositioned());
        return this._pos == 0 && this._xobj.kind() == 3;
    }

    boolean isComment() {
        assert (this.isPositioned());
        return this._pos == 0 && this._xobj.kind() == 4;
    }

    boolean isProcinst() {
        assert (this.isPositioned());
        return this._pos == 0 && this._xobj.kind() == 5;
    }

    boolean isText() {
        assert (this.isPositioned());
        return this._pos > 0;
    }

    boolean isEnd() {
        assert (this.isPositioned());
        return this._pos == -1 && this._xobj.kind() == 2;
    }

    boolean isEndRoot() {
        assert (this.isPositioned());
        return this._pos == -1 && this._xobj.kind() == 1;
    }

    boolean isNode() {
        assert (this.isPositioned());
        return this._pos == 0;
    }

    boolean isContainer() {
        assert (this.isPositioned());
        return this._pos == 0 && Cur.kindIsContainer(this._xobj.kind());
    }

    boolean isFinish() {
        assert (this.isPositioned());
        return this._pos == -1 && Cur.kindIsContainer(this._xobj.kind());
    }

    boolean isUserNode() {
        assert (this.isPositioned());
        int k = this.kind();
        return k == 2 || k == 1 || k == 3 && !this.isXmlns();
    }

    boolean isContainerOrFinish() {
        assert (this.isPositioned());
        if (this._pos != 0 && this._pos != -1) {
            return false;
        }
        int kind = this._xobj.kind();
        return kind == 2 || kind == -2 || kind == 1 || kind == -1;
    }

    boolean isNormalAttr() {
        return this.isNode() && this._xobj.isNormalAttr();
    }

    boolean isXmlns() {
        return this.isNode() && this._xobj.isXmlns();
    }

    boolean isTextCData() {
        return this._xobj.hasBookmark(CDataBookmark.class, this._pos);
    }

    QName getName() {
        assert (this.isNode() || this.isEnd());
        return this._xobj._name;
    }

    String getLocal() {
        return this.getName().getLocalPart();
    }

    String getUri() {
        return this.getName().getNamespaceURI();
    }

    String getXmlnsPrefix() {
        assert (this.isXmlns());
        return this._xobj.getXmlnsPrefix();
    }

    String getXmlnsUri() {
        assert (this.isXmlns());
        return this._xobj.getXmlnsUri();
    }

    boolean isDomDocRoot() {
        return this.isRoot() && this._xobj.getDom() instanceof Document;
    }

    boolean isDomFragRoot() {
        return this.isRoot() && this._xobj.getDom() instanceof DocumentFragment;
    }

    int cchRight() {
        assert (this.isPositioned());
        return this._xobj.cchRight(this._pos);
    }

    int cchLeft() {
        assert (this.isPositioned());
        return this._xobj.cchLeft(this._pos);
    }

    void createRoot() {
        this.createDomDocFragRoot();
    }

    void createDomDocFragRoot() {
        this.moveTo(new Xobj.DocumentFragXobj(this._locale));
    }

    void createDomDocumentRoot() {
        this.moveTo(Cur.createDomDocumentRootXobj(this._locale));
    }

    void createAttr(QName name) {
        this.createHelper(new Xobj.AttrXobj(this._locale, name));
    }

    void createComment() {
        this.createHelper(new Xobj.CommentXobj(this._locale));
    }

    void createProcinst(String target) {
        this.createHelper(new Xobj.ProcInstXobj(this._locale, target));
    }

    void createElement(QName name) {
        this.createElement(name, null);
    }

    void createElement(QName name, QName parentName) {
        this.createHelper(Cur.createElementXobj(this._locale, name, parentName));
    }

    static Xobj createDomDocumentRootXobj(Locale l) {
        return Cur.createDomDocumentRootXobj(l, false);
    }

    static Xobj createDomDocumentRootXobj(Locale l, boolean fragment) {
        Xobj.NodeXobj xo = l._saaj == null ? (fragment ? new Xobj.DocumentFragXobj(l) : new Xobj.DocumentXobj(l)) : new Xobj.SoapPartDocXobj(l);
        if (l._ownerDoc == null) {
            l._ownerDoc = ((Xobj)xo).getDom();
        }
        return xo;
    }

    static Xobj createElementXobj(Locale l, QName name, QName parentName) {
        if (l._saaj == null) {
            return new Xobj.ElementXobj(l, name);
        }
        Class c = l._saaj.identifyElement(name, parentName);
        if (c == SOAPElement.class) {
            return new Xobj.SoapElementXobj(l, name);
        }
        if (c == SOAPBody.class) {
            return new Xobj.SoapBodyXobj(l, name);
        }
        if (c == SOAPBodyElement.class) {
            return new Xobj.SoapBodyElementXobj(l, name);
        }
        if (c == SOAPEnvelope.class) {
            return new Xobj.SoapEnvelopeXobj(l, name);
        }
        if (c == SOAPHeader.class) {
            return new Xobj.SoapHeaderXobj(l, name);
        }
        if (c == SOAPHeaderElement.class) {
            return new Xobj.SoapHeaderElementXobj(l, name);
        }
        if (c == SOAPFaultElement.class) {
            return new Xobj.SoapFaultElementXobj(l, name);
        }
        if (c == Detail.class) {
            return new Xobj.DetailXobj(l, name);
        }
        if (c == DetailEntry.class) {
            return new Xobj.DetailEntryXobj(l, name);
        }
        if (c == SOAPFault.class) {
            return new Xobj.SoapFaultXobj(l, name);
        }
        throw new IllegalStateException("Unknown SAAJ element class: " + c);
    }

    private void createHelper(Xobj x) {
        assert (x._locale == this._locale);
        if (this.isPositioned()) {
            Cur from = this.tempCur(x, 0);
            from.moveNode(this);
            from.release();
        }
        this.moveTo(x);
    }

    boolean isSamePos(Cur that) {
        assert (this.isNormal() && (that == null || that.isNormal()));
        return this._xobj == that._xobj && this._pos == that._pos;
    }

    boolean isJustAfterEnd(Cur that) {
        assert (this.isNormal() && that != null && that.isNormal() && that.isNode());
        return that._xobj.isJustAfterEnd(this._xobj, this._pos);
    }

    boolean isJustAfterEnd(Xobj x) {
        return x.isJustAfterEnd(this._xobj, this._pos);
    }

    boolean isAtEndOf(Cur that) {
        assert (that != null && that.isNormal() && that.isNode());
        return this._xobj == that._xobj && this._pos == -1;
    }

    boolean isInSameTree(Cur that) {
        assert (this.isPositioned() && that.isPositioned());
        return this._xobj.isInSameTree(that._xobj);
    }

    int comparePosition(Cur that) {
        int pThat;
        assert (this.isPositioned() && that.isPositioned());
        if (this._locale != that._locale) {
            return 2;
        }
        Xobj xThis = this._xobj;
        int pThis = this._pos == -1 ? xThis.posAfter() - 1 : this._pos;
        Xobj xThat = that._xobj;
        int n = pThat = that._pos == -1 ? xThat.posAfter() - 1 : that._pos;
        if (xThis == xThat) {
            return pThis < pThat ? -1 : (pThis == pThat ? 0 : 1);
        }
        int dThis = 0;
        Xobj x = xThis._parent;
        while (x != null) {
            ++dThis;
            if (x == xThat) {
                return pThat < xThat.posAfter() - 1 ? 1 : -1;
            }
            x = x._parent;
        }
        int dThat = 0;
        Xobj x2 = xThat._parent;
        while (x2 != null) {
            ++dThat;
            if (x2 == xThis) {
                return pThis < xThis.posAfter() - 1 ? -1 : 1;
            }
            x2 = x2._parent;
        }
        while (dThis > dThat) {
            --dThis;
            xThis = xThis._parent;
        }
        while (dThat > dThis) {
            --dThat;
            xThat = xThat._parent;
        }
        assert (dThat == dThis);
        if (dThat == 0) {
            return 2;
        }
        assert (xThis._parent != null && xThat._parent != null);
        while (xThis._parent != xThat._parent) {
            xThis = xThis._parent;
            if (xThis == null) {
                return 2;
            }
            xThat = xThat._parent;
        }
        if (xThis._prevSibling == null || xThat._nextSibling == null) {
            return -1;
        }
        if (xThis._nextSibling == null || xThat._prevSibling == null) {
            return 1;
        }
        while (xThis != null) {
            xThis = xThis._prevSibling;
            if (xThis != xThat) continue;
            return 1;
        }
        return -1;
    }

    void setName(QName newName) {
        assert (this.isNode() && newName != null);
        this._xobj.setName(newName);
    }

    void moveTo(Xobj x) {
        this.moveTo(x, 0);
    }

    void moveTo(Xobj x, int p) {
        assert (x == null || this._locale == x._locale);
        assert (x != null || p == -2);
        assert (x == null || x.isNormal(p) || x.isVacant() && x._cchValue == 0 && x._user == null);
        assert (this._state == 1 || this._state == 2);
        assert (this._state == 2 || this._xobj == null || !this.isOnList(this._xobj._embedded));
        assert (this._state == 1 || this._xobj != null && this.isOnList(this._xobj._embedded));
        this.moveToNoCheck(x, p);
        assert (this.isNormal() || this._xobj.isVacant() && this._xobj._cchValue == 0 && this._xobj._user == null);
    }

    void moveToNoCheck(Xobj x, int p) {
        if (this._state == 2 && x != this._xobj) {
            this._xobj._embedded = this.listRemove(this._xobj._embedded);
            this._locale._registered = this.listInsert(this._locale._registered);
            this._state = 1;
        }
        this._xobj = x;
        this._pos = p;
    }

    void moveToCur(Cur to) {
        assert (this.isNormal() && (to == null || to.isNormal()));
        if (to == null) {
            this.moveTo(null, -2);
        } else {
            this.moveTo(to._xobj, to._pos);
        }
    }

    void moveToDom(DomImpl.Dom d) {
        assert (this._locale == d.locale());
        assert (d instanceof Xobj || d instanceof Xobj.SoapPartDom);
        this.moveTo(d instanceof Xobj ? (Xobj)((Object)d) : ((Xobj.SoapPartDom)d)._docXobj);
    }

    void push() {
        assert (this.isPositioned());
        int i = this._locale._locations.allocate(this);
        this._stackTop = this._locale._locations.insert(this._stackTop, this._stackTop, i);
    }

    void pop(boolean stay) {
        if (stay) {
            this.popButStay();
        } else {
            this.pop();
        }
    }

    void popButStay() {
        if (this._stackTop != -1) {
            this._stackTop = this._locale._locations.remove(this._stackTop, this._stackTop);
        }
    }

    boolean pop() {
        if (this._stackTop == -1) {
            return false;
        }
        this._locale._locations.moveTo(this._stackTop, this);
        this._stackTop = this._locale._locations.remove(this._stackTop, this._stackTop);
        return true;
    }

    boolean isAtLastPush() {
        assert (this._stackTop != -1);
        return this._locale._locations.isSamePos(this._stackTop, this);
    }

    boolean isAtEndOfLastPush() {
        assert (this._stackTop != -1);
        return this._locale._locations.isAtEndOf(this._stackTop, this);
    }

    void addToSelection(Cur that) {
        assert (that != null && that.isNormal());
        assert (this.isPositioned() && that.isPositioned());
        int i = this._locale._locations.allocate(that);
        this._selectionFirst = this._locale._locations.insert(this._selectionFirst, -1, i);
        ++this._selectionCount;
    }

    void addToSelection() {
        assert (this.isPositioned());
        int i = this._locale._locations.allocate(this);
        this._selectionFirst = this._locale._locations.insert(this._selectionFirst, -1, i);
        ++this._selectionCount;
    }

    private int selectionIndex(int i) {
        assert (this._selectionN >= -1 && i >= 0 && i < this._selectionCount);
        if (this._selectionN == -1) {
            this._selectionN = 0;
            this._selectionLoc = this._selectionFirst;
        }
        while (this._selectionN < i) {
            this._selectionLoc = this._locale._locations.next(this._selectionLoc);
            ++this._selectionN;
        }
        while (this._selectionN > i) {
            this._selectionLoc = this._locale._locations.prev(this._selectionLoc);
            --this._selectionN;
        }
        return this._selectionLoc;
    }

    void removeSelection(int i) {
        assert (i >= 0 && i < this._selectionCount);
        int j = this.selectionIndex(i);
        if (i < this._selectionN) {
            --this._selectionN;
        } else if (i == this._selectionN) {
            --this._selectionN;
            this._selectionLoc = i == 0 ? -1 : this._locale._locations.prev(this._selectionLoc);
        }
        this._selectionFirst = this._locale._locations.remove(this._selectionFirst, j);
        --this._selectionCount;
    }

    int selectionCount() {
        return this._selectionCount;
    }

    void moveToSelection(int i) {
        assert (i >= 0 && i < this._selectionCount);
        this._locale._locations.moveTo(this.selectionIndex(i), this);
    }

    void clearSelection() {
        assert (this._selectionCount >= 0);
        while (this._selectionCount > 0) {
            this.removeSelection(0);
        }
    }

    boolean toParent() {
        return this.toParent(false);
    }

    boolean toParentRaw() {
        return this.toParent(true);
    }

    Xobj getParent() {
        return this.getParent(false);
    }

    Xobj getParentRaw() {
        return this.getParent(true);
    }

    boolean hasParent() {
        assert (this.isPositioned());
        if (this._pos == -1 || this._pos >= 1 && this._pos < this._xobj.posAfter()) {
            return true;
        }
        assert (this._pos == 0 || this._xobj._parent != null);
        return this._xobj._parent != null;
    }

    Xobj getParentNoRoot() {
        assert (this.isPositioned());
        if (this._pos == -1 || this._pos >= 1 && this._pos < this._xobj.posAfter()) {
            return this._xobj;
        }
        assert (this._pos == 0 || this._xobj._parent != null);
        if (this._xobj._parent != null) {
            return this._xobj._parent;
        }
        return null;
    }

    Xobj getParent(boolean raw) {
        assert (this.isPositioned());
        if (this._pos == -1 || this._pos >= 1 && this._pos < this._xobj.posAfter()) {
            return this._xobj;
        }
        assert (this._pos == 0 || this._xobj._parent != null);
        if (this._xobj._parent != null) {
            return this._xobj._parent;
        }
        if (raw || this._xobj.isRoot()) {
            return null;
        }
        Cur r = this._locale.tempCur();
        r.createRoot();
        Xobj root = r._xobj;
        r.next();
        this.moveNode(r);
        r.release();
        return root;
    }

    boolean toParent(boolean raw) {
        Xobj parent = this.getParent(raw);
        if (parent == null) {
            return false;
        }
        this.moveTo(parent);
        return true;
    }

    void toRoot() {
        Xobj xobj = this._xobj;
        while (!xobj.isRoot()) {
            if (xobj._parent == null) {
                Cur r = this._locale.tempCur();
                r.createRoot();
                Xobj root = r._xobj;
                r.next();
                this.moveNode(r);
                r.release();
                xobj = root;
                break;
            }
            xobj = xobj._parent;
        }
        this.moveTo(xobj);
    }

    boolean hasText() {
        assert (this.isNode());
        return this._xobj.hasTextEnsureOccupancy();
    }

    boolean hasAttrs() {
        assert (this.isNode());
        return this._xobj.hasAttrs();
    }

    boolean hasChildren() {
        assert (this.isNode());
        return this._xobj.hasChildren();
    }

    boolean toFirstChild() {
        assert (this.isNode());
        if (!this._xobj.hasChildren()) {
            return false;
        }
        Xobj x = this._xobj._firstChild;
        while (true) {
            if (!x.isAttr()) {
                this.moveTo(x);
                return true;
            }
            x = x._nextSibling;
        }
    }

    protected boolean toLastChild() {
        assert (this.isNode());
        if (!this._xobj.hasChildren()) {
            return false;
        }
        this.moveTo(this._xobj._lastChild);
        return true;
    }

    boolean toNextSibling() {
        assert (this.isNode());
        if (this._xobj.isAttr()) {
            if (this._xobj._nextSibling != null && this._xobj._nextSibling.isAttr()) {
                this.moveTo(this._xobj._nextSibling);
                return true;
            }
        } else if (this._xobj._nextSibling != null) {
            this.moveTo(this._xobj._nextSibling);
            return true;
        }
        return false;
    }

    void setValueAsQName(QName qname) {
        assert (this.isNode());
        String value = qname.getLocalPart();
        String ns = qname.getNamespaceURI();
        String prefix = this.prefixForNamespace(ns, qname.getPrefix().length() > 0 ? qname.getPrefix() : null, true);
        if (prefix.length() > 0) {
            value = prefix + ":" + value;
        }
        this.setValue(value);
    }

    void setValue(String value) {
        assert (this.isNode());
        this.moveNodeContents(null, false);
        this.next();
        this.insertString(value);
        this.toParent();
    }

    void removeFollowingAttrs() {
        assert (this.isAttr());
        QName attrName = this.getName();
        this.push();
        if (this.toNextAttr()) {
            while (this.isAttr()) {
                if (this.getName().equals(attrName)) {
                    this.moveNode(null);
                    continue;
                }
                if (this.toNextAttr()) continue;
            }
        }
        this.pop();
    }

    String getAttrValue(QName name) {
        String s = null;
        this.push();
        if (this.toAttr(name)) {
            s = this.getValueAsString();
        }
        this.pop();
        return s;
    }

    void setAttrValueAsQName(QName name, QName value) {
        assert (this.isContainer());
        if (value == null) {
            this._xobj.removeAttr(name);
        } else {
            if (this.toAttr(name)) {
                this.removeFollowingAttrs();
            } else {
                this.next();
                this.createAttr(name);
            }
            this.setValueAsQName(value);
            this.toParent();
        }
    }

    boolean removeAttr(QName name) {
        assert (this.isContainer());
        return this._xobj.removeAttr(name);
    }

    void setAttrValue(QName name, String value) {
        assert (this.isContainer());
        this._xobj.setAttr(name, value);
    }

    boolean toAttr(QName name) {
        assert (this.isNode());
        Xobj a = this._xobj.getAttr(name);
        if (a == null) {
            return false;
        }
        this.moveTo(a);
        return true;
    }

    boolean toFirstAttr() {
        assert (this.isNode());
        Xobj firstAttr = this._xobj.firstAttr();
        if (firstAttr == null) {
            return false;
        }
        this.moveTo(firstAttr);
        return true;
    }

    boolean toLastAttr() {
        assert (this.isNode());
        if (!this.toFirstAttr()) {
            return false;
        }
        while (this.toNextAttr()) {
        }
        return true;
    }

    boolean toNextAttr() {
        assert (this.isAttr() || this.isContainer());
        Xobj nextAttr = this._xobj.nextAttr();
        if (nextAttr == null) {
            return false;
        }
        this.moveTo(nextAttr);
        return true;
    }

    boolean toPrevAttr() {
        if (this.isAttr()) {
            if (this._xobj._prevSibling == null) {
                this.moveTo(this._xobj.ensureParent());
            } else {
                this.moveTo(this._xobj._prevSibling);
            }
            return true;
        }
        this.prev();
        if (!this.isContainer()) {
            this.next();
            return false;
        }
        return this.toLastAttr();
    }

    boolean skipWithAttrs() {
        assert (this.isNode());
        if (this.skip()) {
            return true;
        }
        if (this._xobj.isRoot()) {
            return false;
        }
        assert (this._xobj.isAttr());
        this.toParent();
        this.next();
        return true;
    }

    boolean skip() {
        assert (this.isNode());
        if (this._xobj.isRoot()) {
            return false;
        }
        if (this._xobj.isAttr()) {
            if (this._xobj._nextSibling == null || !this._xobj._nextSibling.isAttr()) {
                return false;
            }
            this.moveTo(this._xobj._nextSibling, 0);
        } else {
            this.moveTo(this.getNormal(this._xobj, this._xobj.posAfter()), this._posTemp);
        }
        return true;
    }

    void toEnd() {
        assert (this.isNode());
        this.moveTo(this._xobj, -1);
    }

    void moveToCharNode(DomImpl.CharNode node) {
        assert (node.getDom() != null && node.getDom().locale() == this._locale);
        this.moveToDom(node.getDom());
        this._xobj.ensureOccupancy();
        DomImpl.CharNode n = this._xobj._charNodesValue = Cur.updateCharNodes(this._locale, this._xobj, this._xobj._charNodesValue, this._xobj._cchValue);
        while (n != null) {
            if (node == n) {
                this.moveTo(this.getNormal(this._xobj, n._off + 1), this._posTemp);
                return;
            }
            n = n._next;
        }
        n = this._xobj._charNodesAfter = Cur.updateCharNodes(this._locale, this._xobj, this._xobj._charNodesAfter, this._xobj._cchAfter);
        while (n != null) {
            if (node == n) {
                this.moveTo(this.getNormal(this._xobj, n._off + this._xobj._cchValue + 2), this._posTemp);
                return;
            }
            n = n._next;
        }
        assert (false);
    }

    boolean prevWithAttrs() {
        if (this.prev()) {
            return true;
        }
        if (!this.isAttr()) {
            return false;
        }
        this.toParent();
        return true;
    }

    boolean prev() {
        assert (this.isPositioned());
        if (this._xobj.isRoot() && this._pos == 0) {
            return false;
        }
        if (this._xobj.isAttr() && this._pos == 0 && this._xobj._prevSibling == null) {
            return false;
        }
        Xobj x = this.getDenormal();
        int p = this._posTemp;
        assert (p > 0 && p != -1);
        int pa = x.posAfter();
        if (p > pa) {
            p = pa;
        } else if (p == pa) {
            if (x.isAttr() && (x._cchAfter > 0 || x._nextSibling == null || !x._nextSibling.isAttr())) {
                x = x.ensureParent();
                p = 0;
            } else {
                p = -1;
            }
        } else if (p == pa - 1) {
            x.ensureOccupancy();
            p = x._cchValue > 0 ? 1 : 0;
        } else if (p > 1) {
            p = 1;
        } else {
            assert (p == 1);
            p = 0;
        }
        this.moveTo(this.getNormal(x, p), this._posTemp);
        return true;
    }

    boolean next(boolean withAttrs) {
        return withAttrs ? this.nextWithAttrs() : this.next();
    }

    boolean nextWithAttrs() {
        int k = this.kind();
        if (Cur.kindIsContainer(k)) {
            if (this.toFirstAttr()) {
                return true;
            }
        } else if (k == -3) {
            if (this.next()) {
                return true;
            }
            this.toParent();
            if (!this.toParentRaw()) {
                return false;
            }
        }
        return this.next();
    }

    boolean next() {
        assert (this.isNormal());
        int p = this._pos;
        Xobj x = this._xobj;
        int pa = x.posAfter();
        if (p >= pa) {
            p = this._xobj.posMax();
        } else if (p == -1) {
            if (x.isRoot() || x.isAttr() && (x._nextSibling == null || !x._nextSibling.isAttr())) {
                return false;
            }
            p = pa;
        } else if (p > 0) {
            assert (x._firstChild == null || !x._firstChild.isAttr());
            if (x._firstChild != null) {
                x = x._firstChild;
                p = 0;
            } else {
                p = -1;
            }
        } else {
            assert (p == 0);
            x.ensureOccupancy();
            p = 1;
            if (x._cchValue == 0 && x._firstChild != null) {
                if (x._firstChild.isAttr()) {
                    Xobj a = x._firstChild;
                    while (a._nextSibling != null && a._nextSibling.isAttr()) {
                        a = a._nextSibling;
                    }
                    if (a._cchAfter > 0) {
                        x = a;
                        p = a.posAfter();
                    } else if (a._nextSibling != null) {
                        x = a._nextSibling;
                        p = 0;
                    }
                } else {
                    x = x._firstChild;
                    p = 0;
                }
            }
        }
        this.moveTo(this.getNormal(x, p), this._posTemp);
        return true;
    }

    int prevChars(int cch) {
        assert (this.isPositioned());
        int cchLeft = this.cchLeft();
        if (cch < 0 || cch > cchLeft) {
            cch = cchLeft;
        }
        if (cch != 0) {
            this.moveTo(this.getNormal(this.getDenormal(), this._posTemp - cch), this._posTemp);
        }
        return cch;
    }

    int nextChars(int cch) {
        assert (this.isPositioned());
        int cchRight = this.cchRight();
        if (cchRight == 0) {
            return 0;
        }
        if (cch < 0 || cch >= cchRight) {
            this.next();
            return cchRight;
        }
        this.moveTo(this.getNormal(this._xobj, this._pos + cch), this._posTemp);
        return cch;
    }

    void setCharNodes(DomImpl.CharNode nodes) {
        assert (nodes == null || this._locale == nodes.locale());
        assert (this.isPositioned());
        Xobj x = this.getDenormal();
        int p = this._posTemp;
        assert (!x.isRoot() || p > 0 && p < x.posAfter());
        if (p >= x.posAfter()) {
            x._charNodesAfter = nodes;
        } else {
            x._charNodesValue = nodes;
        }
        while (nodes != null) {
            nodes.setDom((DomImpl.Dom)((Object)x));
            nodes = nodes._next;
        }
    }

    DomImpl.CharNode getCharNodes() {
        DomImpl.CharNode nodes;
        assert (this.isPositioned());
        assert (!this.isRoot());
        Xobj x = this.getDenormal();
        if (this._posTemp >= x.posAfter()) {
            nodes = x._charNodesAfter = Cur.updateCharNodes(this._locale, x, x._charNodesAfter, x._cchAfter);
        } else {
            x.ensureOccupancy();
            nodes = x._charNodesValue = Cur.updateCharNodes(this._locale, x, x._charNodesValue, x._cchValue);
        }
        return nodes;
    }

    static DomImpl.CharNode updateCharNodes(Locale l, Xobj x, DomImpl.CharNode nodes, int cch) {
        assert (nodes == null || nodes.locale() == l);
        DomImpl.CharNode node = nodes;
        int i = 0;
        while (node != null && cch > 0) {
            assert (node.getDom() == x);
            if (node._cch > cch) {
                node._cch = cch;
            }
            node._off = i;
            i += node._cch;
            cch -= node._cch;
            node = node._next;
        }
        if (cch <= 0) {
            while (node != null) {
                assert (node.getDom() == x);
                if (node._cch != 0) {
                    node._cch = 0;
                }
                node._off = i;
                node = node._next;
            }
        } else {
            node = l.createTextNode();
            node.setDom((DomImpl.Dom)((Object)x));
            node._cch = cch;
            node._off = i;
            nodes = DomImpl.CharNode.appendNode(nodes, node);
        }
        return nodes;
    }

    final QName getXsiTypeName() {
        assert (this.isNode());
        return this._xobj.getXsiTypeName();
    }

    final void setXsiType(QName value) {
        assert (this.isContainer());
        this.setAttrValueAsQName(Locale._xsiType, value);
    }

    final QName valueAsQName() {
        throw new RuntimeException("Not implemented");
    }

    final String namespaceForPrefix(String prefix, boolean defaultAlwaysMapped) {
        return this._xobj.namespaceForPrefix(prefix, defaultAlwaysMapped);
    }

    final String prefixForNamespace(String ns, String suggestion, boolean createIfMissing) {
        return (this.isContainer() ? this._xobj : this.getParent()).prefixForNamespace(ns, suggestion, createIfMissing);
    }

    boolean contains(Cur that) {
        assert (this.isNode());
        assert (that != null && that.isPositioned());
        return this._xobj.contains(that);
    }

    void insertString(String s) {
        if (s != null) {
            this.insertChars(s, 0, s.length());
        }
    }

    void insertChars(Object src, int off, int cch) {
        assert (this.isPositioned() && !this.isRoot());
        assert (CharUtil.isValid(src, off, cch));
        if (cch <= 0) {
            return;
        }
        this._locale.notifyChange();
        if (this._pos == -1) {
            this._xobj.ensureOccupancy();
        }
        Xobj x = this.getDenormal();
        int p = this._posTemp;
        assert (p > 0);
        x.insertCharsHelper(p, src, off, cch, true);
        this.moveTo(x, p);
        ++this._locale._versionAll;
    }

    Object moveChars(Cur to, int cchMove) {
        assert (this.isPositioned());
        assert (cchMove <= 0 || cchMove <= this.cchRight());
        assert (to == null || to.isPositioned() && !to.isRoot());
        if (cchMove < 0) {
            cchMove = this.cchRight();
        }
        if (cchMove == 0) {
            this._offSrc = 0;
            this._cchSrc = 0;
            return null;
        }
        Object srcMoved = this.getChars(cchMove);
        int offMoved = this._offSrc;
        assert (this.isText() && (this._pos >= this._xobj.posAfter() ? this._xobj._parent : this._xobj).isOccupied());
        if (to == null) {
            Xobj.Bookmark b = this._xobj._bookmarks;
            while (b != null) {
                if (this.inChars(b, cchMove, false)) {
                    Cur c = this._locale.tempCur();
                    c.createRoot();
                    c.next();
                    Object chars = this.moveChars(c, cchMove);
                    c.release();
                    return chars;
                }
                b = b._next;
            }
        } else {
            if (this.inChars(to, cchMove, true)) {
                to.moveToCur(this);
                this.nextChars(cchMove);
                this._offSrc = offMoved;
                this._cchSrc = cchMove;
                return srcMoved;
            }
            to.insertChars(srcMoved, offMoved, cchMove);
        }
        this._locale.notifyChange();
        if (to == null) {
            this._xobj.removeCharsHelper(this._pos, cchMove, null, -2, false, true);
        } else {
            this._xobj.removeCharsHelper(this._pos, cchMove, to._xobj, to._pos, false, true);
        }
        ++this._locale._versionAll;
        this._offSrc = offMoved;
        this._cchSrc = cchMove;
        return srcMoved;
    }

    void moveNode(Cur to) {
        assert (this.isNode() && !this.isRoot());
        assert (to == null || to.isPositioned());
        assert (to == null || !this.contains(to));
        assert (to == null || !to.isRoot());
        Xobj x = this._xobj;
        this.skip();
        Cur.moveNode(x, to);
    }

    private static void transferChars(Xobj xFrom, int pFrom, Xobj xTo, int pTo, int cch) {
        assert (xFrom != xTo);
        assert (xFrom._locale == xTo._locale);
        assert (pFrom > 0 && pFrom < xFrom.posMax());
        assert (pTo > 0 && pTo <= xTo.posMax());
        assert (cch > 0 && cch <= xFrom.cchRight(pFrom));
        assert (pTo >= xTo.posAfter() || xTo.isOccupied());
        xTo.insertCharsHelper(pTo, xFrom.getCharsHelper(pFrom, cch), xFrom._locale._offSrc, xFrom._locale._cchSrc, false);
        xFrom.removeCharsHelper(pFrom, cch, xTo, pTo, true, false);
    }

    static void moveNode(Xobj x, Cur to) {
        assert (x != null && !x.isRoot());
        assert (to == null || to.isPositioned());
        assert (to == null || !x.contains(to));
        assert (to == null || !to.isRoot());
        if (to != null) {
            if (to._pos == -1) {
                to._xobj.ensureOccupancy();
            }
            if (to._pos == 0 && to._xobj == x || to.isJustAfterEnd(x)) {
                to.moveTo(x);
                return;
            }
        }
        x._locale.notifyChange();
        ++x._locale._versionAll;
        ++x._locale._versionSansText;
        if (to != null && to._locale != x._locale) {
            to._locale.notifyChange();
            ++to._locale._versionAll;
            ++to._locale._versionSansText;
        }
        if (x.isAttr()) {
            x.invalidateSpecialAttr(to == null ? null : to.getParentRaw());
        } else {
            if (x._parent != null) {
                x._parent.invalidateUser();
            }
            if (to != null && to.hasParent()) {
                to.getParent().invalidateUser();
            }
        }
        if (x._cchAfter > 0) {
            Cur.transferChars(x, x.posAfter(), x.getDenormal(0), x.posTemp(), x._cchAfter);
        }
        assert (x._cchAfter == 0);
        x._locale.embedCurs();
        for (Xobj y = x; y != null; y = y.walk(x, true)) {
            while (y._embedded != null) {
                y._embedded.moveTo(x.getNormal(x.posAfter()));
            }
            y.disconnectUser();
            if (to == null) continue;
            y._locale = to._locale;
        }
        x.removeXobj();
        if (to != null) {
            Xobj here = to._xobj;
            boolean append = to._pos != 0;
            int cchRight = to.cchRight();
            if (cchRight > 0) {
                to.push();
                to.next();
                here = to._xobj;
                append = to._pos != 0;
                to.pop();
            }
            if (append) {
                here.appendXobj(x);
            } else {
                here.insertXobj(x);
            }
            if (cchRight > 0) {
                Cur.transferChars(to._xobj, to._pos, x, x.posAfter(), cchRight);
            }
            to.moveTo(x);
        }
    }

    void moveNodeContents(Cur to, boolean moveAttrs) {
        assert (this._pos == 0);
        assert (to == null || !to.isRoot());
        Cur.moveNodeContents(this._xobj, to, moveAttrs);
    }

    static void moveNodeContents(Xobj x, Cur to, boolean moveAttrs) {
        boolean noSubNodesToMove;
        assert (to == null || !to.isRoot());
        boolean hasAttrs = x.hasAttrs();
        boolean bl = noSubNodesToMove = !x.hasChildren() && (!moveAttrs || !hasAttrs);
        if (noSubNodesToMove) {
            if (x.isVacant() && to == null) {
                x.clearBit(256);
                x.invalidateUser();
                x.invalidateSpecialAttr(null);
                ++x._locale._versionAll;
            } else if (x.hasTextEnsureOccupancy()) {
                Cur c = x.tempCur();
                c.next();
                c.moveChars(to, -1);
                c.release();
            }
            return;
        }
        if (to != null) {
            if (x == to._xobj && to._pos == -1) {
                to.moveTo(x);
                to.next(moveAttrs && hasAttrs);
                return;
            }
            boolean isAtLeftEdge = false;
            if (to._locale == x._locale) {
                to.push();
                to.moveTo(x);
                to.next(moveAttrs && hasAttrs);
                isAtLeftEdge = to.isAtLastPush();
                to.pop();
            }
            if (isAtLeftEdge) {
                return;
            }
            assert (!x.contains(to));
            assert (to.getParent().isOccupied());
        }
        int valueMovedCch = 0;
        if (x.hasTextNoEnsureOccupancy()) {
            Cur c = x.tempCur();
            c.next();
            c.moveChars(to, -1);
            c.release();
            if (to != null) {
                valueMovedCch = c._cchSrc;
                to.nextChars(valueMovedCch);
            }
        }
        x._locale.embedCurs();
        Xobj firstToMove = x.walk(x, true);
        boolean sawBookmark = false;
        for (Xobj y = firstToMove; y != null; y = y.walk(x, true)) {
            Cur c;
            if (y._parent == x && y.isAttr()) {
                assert (y._cchAfter == 0);
                if (!moveAttrs) {
                    firstToMove = y._nextSibling;
                    continue;
                }
                y.invalidateSpecialAttr(to == null ? null : to.getParent());
            }
            while ((c = y._embedded) != null) {
                c.moveTo(x, -1);
            }
            y.disconnectUser();
            if (to != null) {
                y._locale = to._locale;
            }
            sawBookmark = sawBookmark || y._bookmarks != null;
        }
        Xobj lastToMove = x._lastChild;
        Cur surragateTo = null;
        if (sawBookmark && to == null) {
            surragateTo = to = x._locale.tempCur();
            to.createRoot();
            to.next();
        }
        if (!lastToMove.isAttr()) {
            x.invalidateUser();
        }
        ++x._locale._versionAll;
        ++x._locale._versionSansText;
        if (to != null && valueMovedCch == 0) {
            to.getParent().invalidateUser();
            ++to._locale._versionAll;
            ++to._locale._versionSansText;
        }
        x.removeXobjs(firstToMove, lastToMove);
        if (to != null) {
            Xobj here = to._xobj;
            boolean append = to._pos != 0;
            int cchRight = to.cchRight();
            if (cchRight > 0) {
                to.push();
                to.next();
                here = to._xobj;
                append = to._pos != 0;
                to.pop();
            }
            if (firstToMove.isAttr()) {
                Xobj lastNewAttr = firstToMove;
                while (lastNewAttr._nextSibling != null && lastNewAttr._nextSibling.isAttr()) {
                    lastNewAttr = lastNewAttr._nextSibling;
                }
                Xobj y = to.getParent();
                if (cchRight > 0) {
                    Cur.transferChars(to._xobj, to._pos, lastNewAttr, lastNewAttr.posMax(), cchRight);
                }
                if (y.hasTextNoEnsureOccupancy()) {
                    int cch;
                    int p;
                    if (y._cchValue > 0) {
                        p = 1;
                        cch = y._cchValue;
                    } else {
                        y = y.lastAttr();
                        p = y.posAfter();
                        cch = y._cchAfter;
                    }
                    Cur.transferChars(y, p, lastNewAttr, lastNewAttr.posAfter(), cch);
                }
            } else if (cchRight > 0) {
                Cur.transferChars(to._xobj, to._pos, lastToMove, lastToMove.posMax(), cchRight);
            }
            if (append) {
                here.appendXobjs(firstToMove, lastToMove);
            } else {
                here.insertXobjs(firstToMove, lastToMove);
            }
            to.moveTo(firstToMove);
            to.prevChars(valueMovedCch);
        }
        if (surragateTo != null) {
            surragateTo.release();
        }
    }

    protected final Xobj.Bookmark setBookmark(Object key, Object value) {
        assert (this.isNormal());
        assert (key != null);
        return this._xobj.setBookmark(this._pos, key, value);
    }

    Object getBookmark(Object key) {
        assert (this.isNormal());
        assert (key != null);
        Xobj.Bookmark b = this._xobj._bookmarks;
        while (b != null) {
            if (b._pos == this._pos && b._key == key) {
                return b._value;
            }
            b = b._next;
        }
        return null;
    }

    int firstBookmarkInChars(Object key, int cch) {
        assert (this.isNormal());
        assert (key != null);
        assert (cch > 0);
        assert (cch <= this.cchRight());
        int d = -1;
        if (this.isText()) {
            Xobj.Bookmark b = this._xobj._bookmarks;
            while (b != null) {
                if (b._key == key && this.inChars(b, cch, false)) {
                    d = d == -1 || b._pos - this._pos < d ? b._pos - this._pos : d;
                }
                b = b._next;
            }
        }
        return d;
    }

    int firstBookmarkInCharsLeft(Object key, int cch) {
        assert (this.isNormal());
        assert (key != null);
        assert (cch > 0);
        assert (cch <= this.cchLeft());
        int d = -1;
        if (this.cchLeft() > 0) {
            Xobj x = this.getDenormal();
            int p = this._posTemp - cch;
            Xobj.Bookmark b = x._bookmarks;
            while (b != null) {
                if (b._key == key && x.inChars(p, b._xobj, b._pos, cch, false)) {
                    d = d == -1 || b._pos - p < d ? b._pos - p : d;
                }
                b = b._next;
            }
        }
        return d;
    }

    String getCharsAsString(int cch) {
        assert (this.isNormal() && this._xobj != null);
        return this.getCharsAsString(cch, 1);
    }

    String getCharsAsString(int cch, int wsr) {
        return this._xobj.getCharsAsString(this._pos, cch, wsr);
    }

    String getValueAsString(int wsr) {
        assert (this.isNode());
        return this._xobj.getValueAsString(wsr);
    }

    String getValueAsString() {
        assert (this.isNode());
        assert (!this.hasChildren());
        return this._xobj.getValueAsString();
    }

    Object getChars(int cch) {
        assert (this.isPositioned());
        return this._xobj.getChars(this._pos, cch, this);
    }

    Object getFirstChars() {
        assert (this.isNode());
        Object src = this._xobj.getFirstChars();
        this._offSrc = this._locale._offSrc;
        this._cchSrc = this._locale._cchSrc;
        return src;
    }

    void copyNode(Cur to) {
        assert (to != null);
        assert (this.isNode());
        Xobj copy = this._xobj.copyNode(to._locale);
        if (to.isPositioned()) {
            Cur.moveNode(copy, to);
        } else {
            to.moveTo(copy);
        }
    }

    Cur weakCur(Object o) {
        Cur c = this._locale.weakCur(o);
        c.moveToCur(this);
        return c;
    }

    Cur tempCur() {
        return this.tempCur(null);
    }

    Cur tempCur(String id) {
        Cur c = this._locale.tempCur(id);
        c.moveToCur(this);
        return c;
    }

    private Cur tempCur(Xobj x, int p) {
        assert (this._locale == x._locale);
        assert (x != null || p == -2);
        Cur c = this._locale.tempCur();
        if (x != null) {
            c.moveTo(this.getNormal(x, p), this._posTemp);
        }
        return c;
    }

    boolean inChars(Cur c, int cch, boolean includeEnd) {
        assert (this.isPositioned() && this.isText() && this.cchRight() >= cch);
        assert (c.isNormal());
        return this._xobj.inChars(this._pos, c._xobj, c._pos, cch, includeEnd);
    }

    boolean inChars(Xobj.Bookmark b, int cch, boolean includeEnd) {
        assert (this.isPositioned() && this.isText() && this.cchRight() >= cch);
        assert (b._xobj.isNormal(b._pos));
        return this._xobj.inChars(this._pos, b._xobj, b._pos, cch, includeEnd);
    }

    private Xobj getNormal(Xobj x, int p) {
        Xobj nx = x.getNormal(p);
        this._posTemp = x._locale._posTemp;
        return nx;
    }

    private Xobj getDenormal() {
        assert (this.isPositioned());
        return this.getDenormal(this._xobj, this._pos);
    }

    private Xobj getDenormal(Xobj x, int p) {
        Xobj dx = x.getDenormal(p);
        this._posTemp = x._locale._posTemp;
        return dx;
    }

    void setType(SchemaType type) {
        this.setType(type, true);
    }

    void setType(SchemaType type, boolean complain) {
        assert (type != null);
        assert (this.isUserNode());
        TypeStoreUser user = this.peekUser();
        if (user != null && user.get_schema_type() == type) {
            return;
        }
        if (this.isRoot()) {
            this._xobj.setStableType(type);
            return;
        }
        TypeStoreUser parentUser = this._xobj.ensureParent().getUser();
        if (this.isAttr()) {
            if (complain && parentUser.get_attribute_type(this.getName()) != type) {
                throw new IllegalArgumentException("Can't set type of attribute to " + type.toString());
            }
            return;
        }
        assert (this.isElem());
        if (parentUser.get_element_type(this.getName(), null) == type) {
            this.removeAttr(Locale._xsiType);
            return;
        }
        QName typeName = type.getName();
        if (typeName == null) {
            if (complain) {
                throw new IllegalArgumentException("Can't set type of element, type is un-named");
            }
            return;
        }
        if (parentUser.get_element_type(this.getName(), typeName) != type) {
            if (complain) {
                throw new IllegalArgumentException("Can't set type of element, invalid type");
            }
            return;
        }
        this.setAttrValueAsQName(Locale._xsiType, typeName);
    }

    void setSubstitution(QName name, SchemaType type) {
        this.setSubstitution(name, type, true);
    }

    void setSubstitution(QName name, SchemaType type, boolean complain) {
        assert (name != null);
        assert (type != null);
        assert (this.isUserNode());
        TypeStoreUser user = this.peekUser();
        if (user != null && user.get_schema_type() == type && name.equals(this.getName())) {
            return;
        }
        if (this.isRoot()) {
            return;
        }
        TypeStoreUser parentUser = this._xobj.ensureParent().getUser();
        if (this.isAttr()) {
            if (complain) {
                throw new IllegalArgumentException("Can't use substitution with attributes");
            }
            return;
        }
        assert (this.isElem());
        if (parentUser.get_element_type(name, null) == type) {
            this.setName(name);
            this.removeAttr(Locale._xsiType);
            return;
        }
        QName typeName = type.getName();
        if (typeName == null) {
            if (complain) {
                throw new IllegalArgumentException("Can't set xsi:type on element, type is un-named");
            }
            return;
        }
        if (parentUser.get_element_type(name, typeName) != type) {
            if (complain) {
                throw new IllegalArgumentException("Can't set xsi:type on element, invalid type");
            }
            return;
        }
        this.setName(name);
        this.setAttrValueAsQName(Locale._xsiType, typeName);
    }

    TypeStoreUser peekUser() {
        assert (this.isUserNode());
        return this._xobj._user;
    }

    XmlObject getObject() {
        return this.isUserNode() ? (XmlObject)((Object)this.getUser()) : null;
    }

    TypeStoreUser getUser() {
        assert (this.isUserNode());
        return this._xobj.getUser();
    }

    DomImpl.Dom getDom() {
        assert (this.isNormal());
        assert (this.isPositioned());
        if (this.isText()) {
            int cch = this.cchLeft();
            DomImpl.CharNode cn = this.getCharNodes();
            while (true) {
                if ((cch -= cn._cch) < 0) {
                    return cn;
                }
                cn = cn._next;
            }
        }
        return this._xobj.getDom();
    }

    static void release(Cur c) {
        if (c != null) {
            c.release();
        }
    }

    void release() {
        if (this._tempFrame >= 0) {
            if (this._nextTemp != null) {
                this._nextTemp._prevTemp = this._prevTemp;
            }
            if (this._prevTemp == null) {
                this._locale._tempFrames[this._tempFrame] = this._nextTemp;
            } else {
                this._prevTemp._nextTemp = this._nextTemp;
            }
            this._nextTemp = null;
            this._prevTemp = null;
            this._tempFrame = -1;
        }
        if (this._state != 0 && this._state != 3) {
            while (this._stackTop != -1) {
                this.popButStay();
            }
            this.clearSelection();
            this._id = null;
            this.moveToCur(null);
            assert (this.isNormal());
            assert (this._xobj == null);
            assert (this._pos == -2);
            if (this._ref != null) {
                this._ref.clear();
                this._ref._cur = null;
            }
            this._ref = null;
            assert (this._state == 1);
            this._locale._registered = this.listRemove(this._locale._registered);
            if (this._locale._curPoolCount < 16) {
                this._locale._curPool = this.listInsert(this._locale._curPool);
                this._state = 0;
                ++this._locale._curPoolCount;
            } else {
                this._locale = null;
                this._state = 3;
            }
        }
    }

    boolean isOnList(Cur head) {
        while (head != null) {
            if (head == this) {
                return true;
            }
            head = head._next;
        }
        return false;
    }

    Cur listInsert(Cur head) {
        assert (this._next == null && this._prev == null);
        if (head == null) {
            head = this._prev = this;
        } else {
            this._prev = head._prev;
            head._prev = head._prev._next = this;
        }
        return head;
    }

    Cur listRemove(Cur head) {
        assert (this._prev != null && this.isOnList(head));
        if (this._prev == this) {
            head = null;
        } else {
            if (head == this) {
                head = this._next;
            } else {
                this._prev._next = this._next;
            }
            if (this._next == null) {
                head._prev = this._prev;
            } else {
                this._next._prev = this._prev;
                this._next = null;
            }
        }
        this._prev = null;
        assert (this._next == null);
        return head;
    }

    boolean isNormal() {
        if (this._state == 0 || this._state == 3) {
            return false;
        }
        if (this._xobj == null) {
            return this._pos == -2;
        }
        if (!this._xobj.isNormal(this._pos)) {
            return false;
        }
        if (this._state == 2) {
            return this.isOnList(this._xobj._embedded);
        }
        assert (this._state == 1);
        return this.isOnList(this._locale._registered);
    }

    static String kindName(int kind) {
        switch (kind) {
            case 1: {
                return "ROOT";
            }
            case 2: {
                return "ELEM";
            }
            case 3: {
                return "ATTR";
            }
            case 4: {
                return "COMMENT";
            }
            case 5: {
                return "PROCINST";
            }
            case 0: {
                return "TEXT";
            }
        }
        return "<< Unknown Kind (" + kind + ") >>";
    }

    static void dump(PrintStream o, DomImpl.Dom d, Object ref) {
    }

    static void dump(PrintStream o, DomImpl.Dom d) {
        d.dump(o);
    }

    static void dump(DomImpl.Dom d) {
        Cur.dump(System.out, d);
    }

    static void dump(Node n) {
        Cur.dump(System.out, n);
    }

    static void dump(PrintStream o, Node n) {
        Cur.dump(o, (DomImpl.Dom)((Object)n));
    }

    void dump() {
        Cur.dump(System.out, this._xobj, (Object)this);
    }

    void dump(PrintStream o) {
        if (this._xobj == null) {
            o.println("Unpositioned xptr");
            return;
        }
        Cur.dump(o, this._xobj, (Object)this);
    }

    public static void dump(PrintStream o, Xobj xo, Object ref) {
        if (ref == null) {
            ref = xo;
        }
        while (xo._parent != null) {
            xo = xo._parent;
        }
        Cur.dumpXobj(o, xo, 0, ref);
        o.println();
    }

    private static void dumpCur(PrintStream o, String prefix, Cur c, Object ref) {
        o.print(" ");
        if (ref == c) {
            o.print("*:");
        }
        o.print(prefix + (c._id == null ? "<cur>" : c._id) + "[" + c._pos + "]");
    }

    private static void dumpCurs(PrintStream o, Xobj xo, Object ref) {
        Cur c = xo._embedded;
        while (c != null) {
            Cur.dumpCur(o, "E:", c, ref);
            c = c._next;
        }
        c = xo._locale._registered;
        while (c != null) {
            if (c._xobj == xo) {
                Cur.dumpCur(o, "R:", c, ref);
            }
            c = c._next;
        }
    }

    private static void dumpBookmarks(PrintStream o, Xobj xo, Object ref) {
        Xobj.Bookmark b = xo._bookmarks;
        while (b != null) {
            o.print(" ");
            if (ref == b) {
                o.print("*:");
            }
            if (b._value instanceof XmlLineNumber) {
                XmlLineNumber l = (XmlLineNumber)b._value;
                o.print("<line:" + l.getLine() + ">" + "[" + b._pos + "]");
            } else {
                o.print("<mark>[" + b._pos + "]");
            }
            b = b._next;
        }
    }

    private static void dumpCharNodes(PrintStream o, DomImpl.CharNode nodes, Object ref) {
        DomImpl.CharNode n = nodes;
        while (n != null) {
            o.print(" ");
            if (n == ref) {
                o.print("*");
            }
            o.print((n instanceof DomImpl.TextNode ? "TEXT" : "CDATA") + "[" + n._cch + "]");
            n = n._next;
        }
    }

    private static void dumpChars(PrintStream o, Object src, int off, int cch) {
        int codePoint;
        o.print("\"");
        String s = CharUtil.getString(src, off, cch);
        for (int i = 0; i < s.length(); i += Character.charCount(codePoint)) {
            if (i == 36) {
                o.print("...");
                break;
            }
            codePoint = s.codePointAt(i);
            char[] chars = Character.toChars(codePoint);
            if (chars.length == 1) {
                char ch = chars[0];
                if (ch >= ' ' && ch < '\u007f') {
                    o.print(ch);
                    continue;
                }
                if (ch == '\n') {
                    o.print("\\n");
                    continue;
                }
                if (ch == '\r') {
                    o.print("\\r");
                    continue;
                }
                if (ch == '\t') {
                    o.print("\\t");
                    continue;
                }
                if (ch == '\"') {
                    o.print("\\\"");
                    continue;
                }
                o.print("<#" + ch + ">");
                continue;
            }
            o.print("<#" + codePoint + ">");
        }
        o.print("\"");
    }

    private static void dumpXobj(PrintStream o, Xobj xo, int level, Object ref) {
        if (xo == null) {
            return;
        }
        if (xo == ref) {
            o.print("* ");
        } else {
            o.print("  ");
        }
        for (int i = 0; i < level; ++i) {
            o.print("  ");
        }
        o.print(Cur.kindName(xo.kind()));
        if (xo._name != null) {
            o.print(" ");
            if (xo._name.getPrefix().length() > 0) {
                o.print(xo._name.getPrefix() + ":");
            }
            o.print(xo._name.getLocalPart());
            if (xo._name.getNamespaceURI().length() > 0) {
                o.print("@" + xo._name.getNamespaceURI());
            }
        }
        if (xo._srcValue != null || xo._charNodesValue != null) {
            o.print(" Value( ");
            Cur.dumpChars(o, xo._srcValue, xo._offValue, xo._cchValue);
            Cur.dumpCharNodes(o, xo._charNodesValue, ref);
            o.print(" )");
        }
        if (xo._user != null) {
            o.print(" (USER)");
        }
        if (xo.isVacant()) {
            o.print(" (VACANT)");
        }
        if (xo._srcAfter != null || xo._charNodesAfter != null) {
            o.print(" After( ");
            Cur.dumpChars(o, xo._srcAfter, xo._offAfter, xo._cchAfter);
            Cur.dumpCharNodes(o, xo._charNodesAfter, ref);
            o.print(" )");
        }
        Cur.dumpCurs(o, xo, ref);
        Cur.dumpBookmarks(o, xo, ref);
        String className = xo.getClass().getName();
        int i = className.lastIndexOf(46);
        if (i > 0 && (i = (className = className.substring(i + 1)).lastIndexOf(36)) > 0) {
            className = className.substring(i + 1);
        }
        o.print(" (");
        o.print(className);
        o.print(")");
        o.println();
        xo = xo._firstChild;
        while (xo != null) {
            Cur.dumpXobj(o, xo, level + 1, ref);
            xo = xo._nextSibling;
        }
    }

    void setId(String id) {
        this._id = id;
    }

    static final class CurLoadContext
    extends Locale.LoadContext {
        private boolean _stripLeft = true;
        private Locale _locale;
        private CharUtil _charUtil;
        private Xobj _frontier;
        private boolean _after;
        private Xobj _lastXobj;
        private int _lastPos;
        private boolean _discardDocElem;
        private QName _replaceDocElem;
        private boolean _stripWhitespace;
        private boolean _stripComments;
        private boolean _stripProcinsts;
        private Map _substituteNamespaces;
        private Map _additionalNamespaces;
        private String _doctypeName;
        private String _doctypePublicId;
        private String _doctypeSystemId;

        CurLoadContext(Locale l, XmlOptions options) {
            options = XmlOptions.maskNull(options);
            this._locale = l;
            this._charUtil = options.hasOption(Cur.LOAD_USE_LOCALE_CHAR_UTIL) ? this._locale.getCharUtil() : CharUtil.getThreadLocalCharUtil();
            this._frontier = Cur.createDomDocumentRootXobj(this._locale);
            this._after = false;
            this._lastXobj = this._frontier;
            this._lastPos = 0;
            if (options.hasOption("LOAD_REPLACE_DOCUMENT_ELEMENT")) {
                this._replaceDocElem = (QName)options.get("LOAD_REPLACE_DOCUMENT_ELEMENT");
                this._discardDocElem = true;
            }
            this._stripWhitespace = options.hasOption("LOAD_STRIP_WHITESPACE");
            this._stripComments = options.hasOption("LOAD_STRIP_COMMENTS");
            this._stripProcinsts = options.hasOption("LOAD_STRIP_PROCINSTS");
            this._substituteNamespaces = (Map)options.get("LOAD_SUBSTITUTE_NAMESPACES");
            this._additionalNamespaces = (Map)options.get("LOAD_ADDITIONAL_NAMESPACES");
            ++this._locale._versionAll;
            ++this._locale._versionSansText;
        }

        private void start(Xobj xo) {
            assert (this._frontier != null);
            assert (!this._after || this._frontier._parent != null);
            this.flushText();
            if (this._after) {
                this._frontier = this._frontier._parent;
                this._after = false;
            }
            this._frontier.appendXobj(xo);
            this._frontier = xo;
            this._lastXobj = xo;
            this._lastPos = 0;
        }

        private void end() {
            assert (this._frontier != null);
            assert (!this._after || this._frontier._parent != null);
            this.flushText();
            if (this._after) {
                this._frontier = this._frontier._parent;
            } else {
                this._after = true;
            }
            this._lastXobj = this._frontier;
            this._lastPos = -1;
        }

        private void text(Object src, int off, int cch) {
            if (cch <= 0) {
                return;
            }
            this._lastXobj = this._frontier;
            this._lastPos = this._frontier._cchValue + 1;
            if (this._after) {
                this._lastPos += this._frontier._cchAfter + 1;
                this._frontier._srcAfter = this._charUtil.saveChars(src, off, cch, this._frontier._srcAfter, this._frontier._offAfter, this._frontier._cchAfter);
                this._frontier._offAfter = this._charUtil._offSrc;
                this._frontier._cchAfter = this._charUtil._cchSrc;
            } else {
                this._frontier._srcValue = this._charUtil.saveChars(src, off, cch, this._frontier._srcValue, this._frontier._offValue, this._frontier._cchValue);
                this._frontier._offValue = this._charUtil._offSrc;
                this._frontier._cchValue = this._charUtil._cchSrc;
            }
        }

        private void flushText() {
            if (this._stripWhitespace) {
                if (this._after) {
                    this._frontier._srcAfter = this._charUtil.stripRight(this._frontier._srcAfter, this._frontier._offAfter, this._frontier._cchAfter);
                    this._frontier._offAfter = this._charUtil._offSrc;
                    this._frontier._cchAfter = this._charUtil._cchSrc;
                } else {
                    this._frontier._srcValue = this._charUtil.stripRight(this._frontier._srcValue, this._frontier._offValue, this._frontier._cchValue);
                    this._frontier._offValue = this._charUtil._offSrc;
                    this._frontier._cchValue = this._charUtil._cchSrc;
                }
            }
        }

        private Xobj parent() {
            return this._after ? this._frontier._parent : this._frontier;
        }

        private QName checkName(QName name, boolean local) {
            String substituteUri;
            if (!(this._substituteNamespaces == null || local && name.getNamespaceURI().length() <= 0 || (substituteUri = (String)this._substituteNamespaces.get(name.getNamespaceURI())) == null)) {
                name = this._locale.makeQName(substituteUri, name.getLocalPart(), name.getPrefix());
            }
            return name;
        }

        @Override
        protected void startDTD(String name, String publicId, String systemId) {
            this._doctypeName = name;
            this._doctypePublicId = publicId;
            this._doctypeSystemId = systemId;
        }

        @Override
        protected void endDTD() {
        }

        @Override
        protected void startElement(QName name) {
            this.start(Cur.createElementXobj(this._locale, this.checkName(name, false), this.parent()._name));
            this._stripLeft = true;
        }

        @Override
        protected void endElement() {
            assert (this.parent().isElem());
            this.end();
            this._stripLeft = true;
        }

        @Override
        protected void xmlns(String prefix, String uri) {
            String substituteUri;
            assert (this.parent().isContainer());
            if (this._substituteNamespaces != null && (substituteUri = (String)this._substituteNamespaces.get(uri)) != null) {
                uri = substituteUri;
            }
            Xobj.AttrXobj x = new Xobj.AttrXobj(this._locale, this._locale.createXmlns(prefix));
            this.start(x);
            this.text(uri, 0, uri.length());
            this.end();
            this._lastXobj = x;
            this._lastPos = 0;
        }

        @Override
        protected void attr(QName name, String value) {
            assert (this.parent().isContainer());
            QName parentName = this._after ? this._lastXobj._parent.getQName() : this._lastXobj.getQName();
            boolean isId = this.isAttrOfTypeId(name, parentName);
            Xobj.AttrXobj x = isId ? new Xobj.AttrIdXobj(this._locale, this.checkName(name, true)) : new Xobj.AttrXobj(this._locale, this.checkName(name, true));
            this.start(x);
            this.text(value, 0, value.length());
            this.end();
            if (isId) {
                Cur c1 = x.tempCur();
                c1.toRoot();
                Xobj doc = c1._xobj;
                c1.release();
                if (doc instanceof Xobj.DocumentXobj) {
                    ((Xobj.DocumentXobj)doc).addIdElement(value, x._parent.getDom());
                }
            }
            this._lastXobj = x;
            this._lastPos = 0;
        }

        @Override
        protected void attr(String local, String uri, String prefix, String value) {
            this.attr(this._locale.makeQName(uri, local, prefix), value);
        }

        @Override
        protected void procInst(String target, String value) {
            if (!this._stripProcinsts) {
                Xobj.ProcInstXobj x = new Xobj.ProcInstXobj(this._locale, target);
                this.start(x);
                this.text(value, 0, value.length());
                this.end();
                this._lastXobj = x;
                this._lastPos = 0;
            }
            this._stripLeft = true;
        }

        @Override
        protected void comment(String comment) {
            if (!this._stripComments) {
                this.comment(comment, 0, comment.length());
            }
            this._stripLeft = true;
        }

        @Override
        protected void comment(char[] chars, int off, int cch) {
            if (!this._stripComments) {
                this.comment(this._charUtil.saveChars(chars, off, cch), this._charUtil._offSrc, this._charUtil._cchSrc);
            }
            this._stripLeft = true;
        }

        private void comment(Object src, int off, int cch) {
            Xobj.CommentXobj x = new Xobj.CommentXobj(this._locale);
            this.start(x);
            this.text(src, off, cch);
            this.end();
            this._lastXobj = x;
            this._lastPos = 0;
        }

        private void stripText(Object src, int off, int cch) {
            if (this._stripWhitespace && this._stripLeft) {
                src = this._charUtil.stripLeft(src, off, cch);
                this._stripLeft = false;
                off = this._charUtil._offSrc;
                cch = this._charUtil._cchSrc;
            }
            this.text(src, off, cch);
        }

        @Override
        protected void text(String s) {
            if (s == null) {
                return;
            }
            this.stripText(s, 0, s.length());
        }

        @Override
        protected void text(char[] src, int off, int cch) {
            this.stripText(src, off, cch);
        }

        @Override
        protected void bookmark(XmlCursor.XmlBookmark bm) {
            this._lastXobj.setBookmark(this._lastPos, bm.getKey(), bm);
        }

        @Override
        protected void bookmarkLastNonAttr(XmlCursor.XmlBookmark bm) {
            if (this._lastPos > 0 || !this._lastXobj.isAttr()) {
                this._lastXobj.setBookmark(this._lastPos, bm.getKey(), bm);
            } else {
                assert (this._lastXobj._parent != null);
                this._lastXobj._parent.setBookmark(0, bm.getKey(), bm);
            }
        }

        @Override
        protected void bookmarkLastAttr(QName attrName, XmlCursor.XmlBookmark bm) {
            if (this._lastPos == 0 && this._lastXobj.isAttr()) {
                assert (this._lastXobj._parent != null);
                Xobj a = this._lastXobj._parent.getAttr(attrName);
                if (a != null) {
                    a.setBookmark(0, bm.getKey(), bm);
                }
            }
        }

        @Override
        protected void lineNumber(int line, int column, int offset) {
            this._lastXobj.setBookmark(this._lastPos, XmlLineNumber.class, new XmlLineNumber(line, column, offset));
        }

        @Override
        protected void abort() {
            this._stripLeft = true;
            while (!this.parent().isRoot()) {
                this.end();
            }
            this.finish().release();
        }

        @Override
        protected Cur finish() {
            this.flushText();
            if (this._after) {
                this._frontier = this._frontier._parent;
            }
            assert (this._frontier != null && this._frontier._parent == null && this._frontier.isRoot());
            Cur c = this._frontier.tempCur();
            if (!Locale.toFirstChildElement(c)) {
                return c;
            }
            boolean isFrag = Locale.isFragmentQName(c.getName());
            if (this._discardDocElem || isFrag) {
                Cur c2;
                if (this._replaceDocElem != null) {
                    c.setName(this._replaceDocElem);
                } else {
                    while (c.toParent()) {
                    }
                    c.next();
                    while (!c.isElem()) {
                        if (c.isText()) {
                            c.moveChars(null, -1);
                            continue;
                        }
                        c.moveNode(null);
                    }
                    assert (c.isElem());
                    c.skip();
                    while (!c.isFinish()) {
                        if (c.isText()) {
                            c.moveChars(null, -1);
                            continue;
                        }
                        c.moveNode(null);
                    }
                    c.toParent();
                    c.next();
                    assert (c.isElem());
                    c2 = c.tempCur();
                    c.moveNodeContents(c, true);
                    c.moveToCur(c2);
                    c2.release();
                    c.moveNode(null);
                }
                if (isFrag) {
                    c.moveTo(this._frontier);
                    if (c.toFirstAttr()) {
                        while (true) {
                            if (c.isXmlns() && c.getXmlnsUri().equals("http://www.openuri.org/fragment")) {
                                c.moveNode(null);
                                if (c.isAttr()) continue;
                                break;
                            }
                            if (!c.toNextAttr()) break;
                        }
                    }
                    c.moveTo(this._frontier);
                    this._frontier = Cur.createDomDocumentRootXobj(this._locale, true);
                    c2 = this._frontier.tempCur();
                    c2.next();
                    c.moveNodeContents(c2, true);
                    c.moveTo(this._frontier);
                    c2.release();
                }
            }
            if (this._additionalNamespaces != null) {
                c.moveTo(this._frontier);
                Locale.toFirstChildElement(c);
                Locale.applyNamespaces(c, this._additionalNamespaces);
            }
            if (this._doctypeName != null && (this._doctypePublicId != null || this._doctypeSystemId != null)) {
                XmlDocumentProperties props = Locale.getDocProps(c, true);
                props.setDoctypeName(this._doctypeName);
                if (this._doctypePublicId != null) {
                    props.setDoctypePublicId(this._doctypePublicId);
                }
                if (this._doctypeSystemId != null) {
                    props.setDoctypeSystemId(this._doctypeSystemId);
                }
            }
            c.moveTo(this._frontier);
            assert (c.isRoot());
            return c;
        }

        public void dump() {
            this._frontier.dump();
        }
    }

    static final class Locations {
        private static final int NULL = -1;
        private static final int _initialSize = 32;
        private Locale _locale;
        private Xobj[] _xobjs;
        private int[] _poses;
        private Cur[] _curs;
        private int[] _next;
        private int[] _prev;
        private int[] _nextN;
        private int[] _prevN;
        private int _free;
        private int _naked;

        Locations(Locale l) {
            this._locale = l;
            this._xobjs = new Xobj[32];
            this._poses = new int[32];
            this._curs = new Cur[32];
            this._next = new int[32];
            this._prev = new int[32];
            this._nextN = new int[32];
            this._prevN = new int[32];
            for (int i = 31; i >= 0; --i) {
                assert (this._xobjs[i] == null);
                this._poses[i] = -2;
                this._next[i] = i + 1;
                this._prev[i] = -1;
                this._nextN[i] = -1;
                this._prevN[i] = -1;
            }
            this._next[31] = -1;
            this._free = 0;
            this._naked = -1;
        }

        boolean isSamePos(int i, Cur c) {
            if (this._curs[i] == null) {
                return c._xobj == this._xobjs[i] && c._pos == this._poses[i];
            }
            return c.isSamePos(this._curs[i]);
        }

        boolean isAtEndOf(int i, Cur c) {
            assert (this._curs[i] != null || this._poses[i] == 0);
            assert (this._curs[i] == null || this._curs[i].isNode());
            if (this._curs[i] == null) {
                return c._xobj == this._xobjs[i] && c._pos == -1;
            }
            return c.isAtEndOf(this._curs[i]);
        }

        void moveTo(int i, Cur c) {
            if (this._curs[i] == null) {
                c.moveTo(this._xobjs[i], this._poses[i]);
            } else {
                c.moveToCur(this._curs[i]);
            }
        }

        int insert(int head, int before, int i) {
            return Locations.insert(head, before, i, this._next, this._prev);
        }

        int remove(int head, int i) {
            Cur c = this._curs[i];
            assert (c != null || this._xobjs[i] != null);
            assert (c != null || this._xobjs[i] != null);
            if (c != null) {
                this._curs[i].release();
                this._curs[i] = null;
                assert (this._xobjs[i] == null);
                assert (this._poses[i] == -2);
            } else {
                assert (this._xobjs[i] != null && this._poses[i] != -2);
                this._xobjs[i] = null;
                this._poses[i] = -2;
                this._naked = Locations.remove(this._naked, i, this._nextN, this._prevN);
            }
            head = Locations.remove(head, i, this._next, this._prev);
            this._next[i] = this._free;
            this._free = i;
            return head;
        }

        int allocate(Cur addThis) {
            assert (addThis.isPositioned());
            if (this._free == -1) {
                this.makeRoom();
            }
            int i = this._free;
            this._free = this._next[i];
            this._next[i] = -1;
            assert (this._prev[i] == -1);
            assert (this._curs[i] == null);
            assert (this._xobjs[i] == null);
            assert (this._poses[i] == -2);
            this._xobjs[i] = addThis._xobj;
            this._poses[i] = addThis._pos;
            this._naked = Locations.insert(this._naked, -1, i, this._nextN, this._prevN);
            return i;
        }

        private static int insert(int head, int before, int i, int[] next, int[] prev) {
            if (head == -1) {
                assert (before == -1);
                prev[i] = i;
                head = i;
            } else if (before != -1) {
                prev[i] = prev[before];
                next[i] = before;
                prev[before] = i;
                if (head == before) {
                    head = i;
                }
            } else {
                prev[i] = prev[head];
                assert (next[i] == -1);
                next[prev[head]] = i;
                prev[head] = i;
            }
            return head;
        }

        private static int remove(int head, int i, int[] next, int[] prev) {
            if (prev[i] == i) {
                assert (head == i);
                head = -1;
            } else {
                if (head == i) {
                    head = next[i];
                } else {
                    next[prev[i]] = next[i];
                }
                if (next[i] == -1) {
                    prev[head] = prev[i];
                } else {
                    prev[next[i]] = prev[i];
                    next[i] = -1;
                }
            }
            prev[i] = -1;
            assert (next[i] == -1);
            return head;
        }

        void notifyChange() {
            int i;
            while ((i = this._naked) != -1) {
                assert (this._curs[i] == null && this._xobjs[i] != null && this._poses[i] != -2);
                this._naked = Locations.remove(this._naked, i, this._nextN, this._prevN);
                this._curs[i] = this._locale.getCur();
                this._curs[i].moveTo(this._xobjs[i], this._poses[i]);
                this._xobjs[i] = null;
                this._poses[i] = -2;
            }
        }

        int next(int i) {
            return this._next[i];
        }

        int prev(int i) {
            return this._prev[i];
        }

        private void makeRoom() {
            assert (this._free == -1);
            int l = this._xobjs.length;
            Xobj[] oldXobjs = this._xobjs;
            int[] oldPoses = this._poses;
            Cur[] oldCurs = this._curs;
            int[] oldNext = this._next;
            int[] oldPrev = this._prev;
            int[] oldNextN = this._nextN;
            int[] oldPrevN = this._prevN;
            this._xobjs = new Xobj[l * 2];
            this._poses = new int[l * 2];
            this._curs = new Cur[l * 2];
            this._next = new int[l * 2];
            this._prev = new int[l * 2];
            this._nextN = new int[l * 2];
            this._prevN = new int[l * 2];
            System.arraycopy(oldXobjs, 0, this._xobjs, 0, l);
            System.arraycopy(oldPoses, 0, this._poses, 0, l);
            System.arraycopy(oldCurs, 0, this._curs, 0, l);
            System.arraycopy(oldNext, 0, this._next, 0, l);
            System.arraycopy(oldPrev, 0, this._prev, 0, l);
            System.arraycopy(oldNextN, 0, this._nextN, 0, l);
            System.arraycopy(oldPrevN, 0, this._prevN, 0, l);
            for (int i = l * 2 - 1; i >= l; --i) {
                this._next[i] = i + 1;
                this._prev[i] = -1;
                this._nextN[i] = -1;
                this._prevN[i] = -1;
                this._poses[i] = -2;
            }
            this._next[l * 2 - 1] = -1;
            this._free = l;
        }
    }
}

