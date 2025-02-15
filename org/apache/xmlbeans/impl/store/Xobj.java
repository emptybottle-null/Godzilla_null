/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.store;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import org.apache.xmlbeans.CDataBookmark;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidatorListener;
import org.apache.xmlbeans.impl.common.XmlLocale;
import org.apache.xmlbeans.impl.soap.Detail;
import org.apache.xmlbeans.impl.soap.DetailEntry;
import org.apache.xmlbeans.impl.soap.Name;
import org.apache.xmlbeans.impl.soap.Node;
import org.apache.xmlbeans.impl.soap.SOAPBody;
import org.apache.xmlbeans.impl.soap.SOAPBodyElement;
import org.apache.xmlbeans.impl.soap.SOAPElement;
import org.apache.xmlbeans.impl.soap.SOAPEnvelope;
import org.apache.xmlbeans.impl.soap.SOAPException;
import org.apache.xmlbeans.impl.soap.SOAPFault;
import org.apache.xmlbeans.impl.soap.SOAPFaultElement;
import org.apache.xmlbeans.impl.soap.SOAPHeader;
import org.apache.xmlbeans.impl.soap.SOAPHeaderElement;
import org.apache.xmlbeans.impl.soap.SOAPPart;
import org.apache.xmlbeans.impl.store.CharUtil;
import org.apache.xmlbeans.impl.store.Cur;
import org.apache.xmlbeans.impl.store.Cursor;
import org.apache.xmlbeans.impl.store.DomImpl;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.store.Query;
import org.apache.xmlbeans.impl.store.Validate;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.TypeStoreUser;
import org.apache.xmlbeans.impl.values.TypeStoreUserFactory;
import org.apache.xmlbeans.impl.values.TypeStoreVisitor;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;

abstract class Xobj
implements TypeStore {
    static final int TEXT = 0;
    static final int ROOT = 1;
    static final int ELEM = 2;
    static final int ATTR = 3;
    static final int COMMENT = 4;
    static final int PROCINST = 5;
    static final int END_POS = -1;
    static final int NO_POS = -2;
    static final int VACANT = 256;
    static final int STABLE_USER = 512;
    static final int INHIBIT_DISCONNECT = 1024;
    Locale _locale;
    QName _name;
    Cur _embedded;
    Bookmark _bookmarks;
    int _bits;
    Xobj _parent;
    Xobj _nextSibling;
    Xobj _prevSibling;
    Xobj _firstChild;
    Xobj _lastChild;
    Object _srcValue;
    Object _srcAfter;
    int _offValue;
    int _offAfter;
    int _cchValue;
    int _cchAfter;
    DomImpl.CharNode _charNodesValue;
    DomImpl.CharNode _charNodesAfter;
    TypeStoreUser _user;

    Xobj(Locale l, int kind, int domType) {
        assert (kind == 1 || kind == 2 || kind == 3 || kind == 4 || kind == 5);
        this._locale = l;
        this._bits = (domType << 4) + kind;
    }

    final boolean entered() {
        return this._locale.entered();
    }

    final int kind() {
        return this._bits & 0xF;
    }

    final int domType() {
        return (this._bits & 0xF0) >> 4;
    }

    final boolean isRoot() {
        return this.kind() == 1;
    }

    final boolean isAttr() {
        return this.kind() == 3;
    }

    final boolean isElem() {
        return this.kind() == 2;
    }

    final boolean isProcinst() {
        return this.kind() == 5;
    }

    final boolean isComment() {
        return this.kind() == 4;
    }

    final boolean isContainer() {
        return Cur.kindIsContainer(this.kind());
    }

    final boolean isUserNode() {
        int k = this.kind();
        return k == 2 || k == 1 || k == 3 && !this.isXmlns();
    }

    final boolean isNormalAttr() {
        return this.isAttr() && !Locale.isXmlns(this._name);
    }

    final boolean isXmlns() {
        return this.isAttr() && Locale.isXmlns(this._name);
    }

    final int cchValue() {
        return this._cchValue;
    }

    final int cchAfter() {
        return this._cchAfter;
    }

    final int posAfter() {
        return 2 + this._cchValue;
    }

    final int posMax() {
        return 2 + this._cchValue + this._cchAfter;
    }

    final String getXmlnsPrefix() {
        return Locale.xmlnsPrefix(this._name);
    }

    final String getXmlnsUri() {
        return this.getValueAsString();
    }

    final boolean hasTextEnsureOccupancy() {
        this.ensureOccupancy();
        return this.hasTextNoEnsureOccupancy();
    }

    final boolean hasTextNoEnsureOccupancy() {
        if (this._cchValue > 0) {
            return true;
        }
        Xobj lastAttr = this.lastAttr();
        return lastAttr != null && lastAttr._cchAfter > 0;
    }

    final boolean hasAttrs() {
        return this._firstChild != null && this._firstChild.isAttr();
    }

    final boolean hasChildren() {
        return this._lastChild != null && !this._lastChild.isAttr();
    }

    protected final int getDomZeroOneChildren() {
        Xobj node;
        if (this._firstChild == null && this._srcValue == null && this._charNodesValue == null) {
            return 0;
        }
        if (this._lastChild != null && this._lastChild.isAttr() && this._lastChild._charNodesAfter == null && this._lastChild._srcAfter == null && this._srcValue == null && this._charNodesValue == null) {
            return 0;
        }
        if (this._firstChild == this._lastChild && this._firstChild != null && !this._firstChild.isAttr() && this._srcValue == null && this._charNodesValue == null && this._firstChild._srcAfter == null) {
            return 1;
        }
        if (this._firstChild == null && this._srcValue != null && (this._charNodesValue == null || this._charNodesValue._next == null && this._charNodesValue._cch == this._cchValue)) {
            return 1;
        }
        Xobj lastAttr = this.lastAttr();
        Xobj xobj = node = lastAttr == null ? null : lastAttr._nextSibling;
        if (lastAttr != null && lastAttr._srcAfter == null && node != null && node._srcAfter == null && node._nextSibling == null) {
            return 1;
        }
        return 2;
    }

    protected final boolean isFirstChildPtrDomUsable() {
        if (this._firstChild == null && this._srcValue == null && this._charNodesValue == null) {
            return true;
        }
        if (this._firstChild != null && !this._firstChild.isAttr() && this._srcValue == null && this._charNodesValue == null) {
            assert (this._firstChild instanceof NodeXobj) : "wrong node type";
            return true;
        }
        return false;
    }

    protected final boolean isNextSiblingPtrDomUsable() {
        if (this._charNodesAfter == null && this._srcAfter == null) {
            assert (this._nextSibling == null || this._nextSibling instanceof NodeXobj) : "wrong node type";
            return true;
        }
        return false;
    }

    protected final boolean isExistingCharNodesValueUsable() {
        if (this._srcValue == null) {
            return false;
        }
        return this._charNodesValue != null && this._charNodesValue._next == null && this._charNodesValue._cch == this._cchValue;
    }

    protected final boolean isCharNodesValueUsable() {
        return this.isExistingCharNodesValueUsable() || (this._charNodesValue = Cur.updateCharNodes(this._locale, this, this._charNodesValue, this._cchValue)) != null;
    }

    protected final boolean isCharNodesAfterUsable() {
        if (this._srcAfter == null) {
            return false;
        }
        if (this._charNodesAfter != null && this._charNodesAfter._next == null && this._charNodesAfter._cch == this._cchAfter) {
            return true;
        }
        this._charNodesAfter = Cur.updateCharNodes(this._locale, this, this._charNodesAfter, this._cchAfter);
        return this._charNodesAfter != null;
    }

    final Xobj lastAttr() {
        if (this._firstChild == null || !this._firstChild.isAttr()) {
            return null;
        }
        Xobj lastAttr = this._firstChild;
        while (lastAttr._nextSibling != null && lastAttr._nextSibling.isAttr()) {
            lastAttr = lastAttr._nextSibling;
        }
        return lastAttr;
    }

    abstract DomImpl.Dom getDom();

    abstract Xobj newNode(Locale var1);

    final int cchLeft(int p) {
        int pa;
        if (this.isRoot() && p == 0) {
            return 0;
        }
        Xobj x = this.getDenormal(p);
        return p - ((p = this.posTemp()) < (pa = x.posAfter()) ? 1 : pa);
    }

    final int cchRight(int p) {
        assert (p < this.posMax());
        if (p <= 0) {
            return 0;
        }
        int pa = this.posAfter();
        return p < pa ? pa - p - 1 : this.posMax() - p;
    }

    public final Locale locale() {
        return this._locale;
    }

    public final int nodeType() {
        return this.domType();
    }

    public final QName getQName() {
        return this._name;
    }

    public final Cur tempCur() {
        Cur c = this._locale.tempCur();
        c.moveTo(this);
        return c;
    }

    public void dump(PrintStream o, Object ref) {
        Cur.dump(o, this, ref);
    }

    public void dump(PrintStream o) {
        Cur.dump(o, this, (Object)this);
    }

    public void dump() {
        this.dump(System.out);
    }

    final Cur getEmbedded() {
        this._locale.embedCurs();
        return this._embedded;
    }

    final boolean inChars(int p, Xobj xIn, int pIn, int cch, boolean includeEnd) {
        int offset;
        assert (p > 0 && p < this.posMax() && p != this.posAfter() - 1 && cch > 0);
        assert (xIn.isNormal(pIn));
        if (includeEnd) {
            if (xIn.isRoot() && pIn == 0) {
                return false;
            }
            xIn = xIn.getDenormal(pIn);
            pIn = xIn.posTemp();
            offset = 1;
        } else {
            offset = 0;
        }
        return xIn == this && pIn >= p && pIn < p + (cch < 0 ? this.cchRight(p) : cch) + offset;
    }

    final boolean isJustAfterEnd(Xobj x, int p) {
        assert (x.isNormal(p));
        if (x.isRoot() && p == 0) {
            return false;
        }
        return x == this ? p == this.posAfter() : x.getDenormal(p) == this && x.posTemp() == this.posAfter();
    }

    final boolean isInSameTree(Xobj x) {
        if (this._locale != x._locale) {
            return false;
        }
        Xobj y = this;
        while (y != x) {
            if (y._parent == null) {
                while (true) {
                    if (x == this) {
                        return true;
                    }
                    if (x._parent == null) {
                        return x == y;
                    }
                    x = x._parent;
                }
            }
            y = y._parent;
        }
        return true;
    }

    final boolean contains(Cur c) {
        assert (c.isNormal());
        return this.contains(c._xobj, c._pos);
    }

    final boolean contains(Xobj x, int p) {
        assert (x.isNormal(p));
        if (this == x) {
            return p == -1 || p > 0 && p < this.posAfter();
        }
        if (this._firstChild == null) {
            return false;
        }
        while (x != null) {
            if (x == this) {
                return true;
            }
            x = x._parent;
        }
        return false;
    }

    final Bookmark setBookmark(int p, Object key, Object value) {
        assert (this.isNormal(p));
        Bookmark b = this._bookmarks;
        while (b != null) {
            if (p == b._pos && key == b._key) {
                if (value == null) {
                    this._bookmarks = b.listRemove(this._bookmarks);
                    return null;
                }
                b._value = value;
                return b;
            }
            b = b._next;
        }
        if (value == null) {
            return null;
        }
        b = new Bookmark();
        b._xobj = this;
        b._pos = p;
        b._key = key;
        b._value = value;
        this._bookmarks = b.listInsert(this._bookmarks);
        return b;
    }

    final boolean hasBookmark(Object key, int pos) {
        Bookmark b = this._bookmarks;
        while (b != null) {
            if (b._pos == pos && key == b._key) {
                return true;
            }
            b = b._next;
        }
        return false;
    }

    final Xobj findXmlnsForPrefix(String prefix) {
        assert (this.isContainer() && prefix != null);
        Xobj c = this;
        while (c != null) {
            for (Xobj a = c.firstAttr(); a != null; a = a.nextAttr()) {
                if (!a.isXmlns() || !a.getXmlnsPrefix().equals(prefix)) continue;
                return a;
            }
            c = c._parent;
        }
        return null;
    }

    final boolean removeAttr(QName name) {
        assert (this.isContainer());
        Xobj a = this.getAttr(name);
        if (a == null) {
            return false;
        }
        Cur c = a.tempCur();
        while (true) {
            c.moveNode(null);
            a = this.getAttr(name);
            if (a == null) break;
            c.moveTo(a);
        }
        c.release();
        return true;
    }

    final Xobj setAttr(QName name, String value) {
        assert (this.isContainer());
        Cur c = this.tempCur();
        if (c.toAttr(name)) {
            c.removeFollowingAttrs();
        } else {
            c.next();
            c.createAttr(name);
        }
        c.setValue(value);
        Xobj a = c._xobj;
        c.release();
        return a;
    }

    final void setName(QName newName) {
        assert (this.isAttr() || this.isElem() || this.isProcinst());
        assert (newName != null);
        if (!this._name.equals(newName) || !this._name.getPrefix().equals(newName.getPrefix())) {
            this._locale.notifyChange();
            QName oldName = this._name;
            this._name = newName;
            if (this instanceof NamedNodeXobj) {
                NamedNodeXobj me = (NamedNodeXobj)this;
                me._canHavePrefixUri = true;
            }
            if (!this.isProcinst()) {
                Xobj disconnectFromHere = this;
                if (this.isAttr() && this._parent != null) {
                    if (oldName.equals(Locale._xsiType) || newName.equals(Locale._xsiType)) {
                        disconnectFromHere = this._parent;
                    }
                    if (oldName.equals(Locale._xsiNil) || newName.equals(Locale._xsiNil)) {
                        this._parent.invalidateNil();
                    }
                }
                disconnectFromHere.disconnectNonRootUsers();
            }
            ++this._locale._versionAll;
            ++this._locale._versionSansText;
        }
    }

    final Xobj ensureParent() {
        assert (this._parent != null || !this.isRoot() && this.cchAfter() == 0);
        return this._parent == null ? new DocumentFragXobj(this._locale).appendXobj(this) : this._parent;
    }

    final Xobj firstAttr() {
        return this._firstChild == null || !this._firstChild.isAttr() ? null : this._firstChild;
    }

    final Xobj nextAttr() {
        if (this._firstChild != null && this._firstChild.isAttr()) {
            return this._firstChild;
        }
        if (this._nextSibling != null && this._nextSibling.isAttr()) {
            return this._nextSibling;
        }
        return null;
    }

    final boolean isValid() {
        return !this.isVacant() || this._cchValue == 0 && this._user != null;
    }

    final int posTemp() {
        return this._locale._posTemp;
    }

    final Xobj getNormal(int p) {
        assert (p == -1 || p >= 0 && p <= this.posMax());
        Xobj x = this;
        if (p == x.posMax()) {
            if (x._nextSibling != null) {
                x = x._nextSibling;
                p = 0;
            } else {
                x = x.ensureParent();
                p = -1;
            }
        } else if (p == x.posAfter() - 1) {
            p = -1;
        }
        this._locale._posTemp = p;
        return x;
    }

    final Xobj getDenormal(int p) {
        assert (!this.isRoot() || p == -1 || p > 0);
        Xobj x = this;
        if (p == 0) {
            if (x._prevSibling == null) {
                x = x.ensureParent();
                p = x.posAfter() - 1;
            } else {
                x = x._prevSibling;
                p = x.posMax();
            }
        } else if (p == -1) {
            if (x._lastChild == null) {
                p = x.posAfter() - 1;
            } else {
                x = x._lastChild;
                p = x.posMax();
            }
        }
        this._locale._posTemp = p;
        return x;
    }

    final boolean isNormal(int p) {
        if (!this.isValid()) {
            return false;
        }
        if (p == -1 || p == 0) {
            return true;
        }
        if (p < 0 || p >= this.posMax()) {
            return false;
        }
        if (p >= this.posAfter()) {
            if (this.isRoot()) {
                return false;
            }
            if (this._nextSibling != null && this._nextSibling.isAttr()) {
                return false;
            }
            if (this._parent == null || !this._parent.isContainer()) {
                return false;
            }
        }
        return p != this.posAfter() - 1;
    }

    final Xobj walk(Xobj root, boolean walkChildren) {
        if (this._firstChild != null && walkChildren) {
            return this._firstChild;
        }
        Xobj x = this;
        while (x != root) {
            if (x._nextSibling != null) {
                return x._nextSibling;
            }
            x = x._parent;
        }
        return null;
    }

    final Xobj removeXobj() {
        if (this._parent != null) {
            if (this._parent._firstChild == this) {
                this._parent._firstChild = this._nextSibling;
            }
            if (this._parent._lastChild == this) {
                this._parent._lastChild = this._prevSibling;
            }
            if (this._prevSibling != null) {
                this._prevSibling._nextSibling = this._nextSibling;
            }
            if (this._nextSibling != null) {
                this._nextSibling._prevSibling = this._prevSibling;
            }
            this._parent = null;
            this._prevSibling = null;
            this._nextSibling = null;
        }
        return this;
    }

    final Xobj insertXobj(Xobj s) {
        assert (this._locale == s._locale);
        assert (!s.isRoot() && !this.isRoot());
        assert (s._parent == null);
        assert (s._prevSibling == null);
        assert (s._nextSibling == null);
        this.ensureParent();
        s._parent = this._parent;
        s._prevSibling = this._prevSibling;
        s._nextSibling = this;
        if (this._prevSibling != null) {
            this._prevSibling._nextSibling = s;
        } else {
            this._parent._firstChild = s;
        }
        this._prevSibling = s;
        return this;
    }

    final Xobj appendXobj(Xobj c) {
        assert (this._locale == c._locale);
        assert (!c.isRoot());
        assert (c._parent == null);
        assert (c._prevSibling == null);
        assert (c._nextSibling == null);
        assert (this._lastChild == null || this._firstChild != null);
        c._parent = this;
        c._prevSibling = this._lastChild;
        if (this._lastChild == null) {
            this._firstChild = c;
        } else {
            this._lastChild._nextSibling = c;
        }
        this._lastChild = c;
        return this;
    }

    final void removeXobjs(Xobj first, Xobj last) {
        assert (last._locale == first._locale);
        assert (first._parent == this);
        assert (last._parent == this);
        if (this._firstChild == first) {
            this._firstChild = last._nextSibling;
        }
        if (this._lastChild == last) {
            this._lastChild = first._prevSibling;
        }
        if (first._prevSibling != null) {
            first._prevSibling._nextSibling = last._nextSibling;
        }
        if (last._nextSibling != null) {
            last._nextSibling._prevSibling = first._prevSibling;
        }
        first._prevSibling = null;
        last._nextSibling = null;
        while (first != null) {
            first._parent = null;
            first = first._nextSibling;
        }
    }

    final void insertXobjs(Xobj first, Xobj last) {
        assert (this._locale == first._locale);
        assert (last._locale == first._locale);
        assert (first._parent == null && last._parent == null);
        assert (first._prevSibling == null);
        assert (last._nextSibling == null);
        first._prevSibling = this._prevSibling;
        last._nextSibling = this;
        if (this._prevSibling != null) {
            this._prevSibling._nextSibling = first;
        } else {
            this._parent._firstChild = first;
        }
        this._prevSibling = last;
        while (first != this) {
            first._parent = this._parent;
            first = first._nextSibling;
        }
    }

    final void appendXobjs(Xobj first, Xobj last) {
        assert (this._locale == first._locale);
        assert (last._locale == first._locale);
        assert (first._parent == null && last._parent == null);
        assert (first._prevSibling == null);
        assert (last._nextSibling == null);
        assert (!first.isRoot());
        first._prevSibling = this._lastChild;
        if (this._lastChild == null) {
            this._firstChild = first;
        } else {
            this._lastChild._nextSibling = first;
        }
        this._lastChild = last;
        while (first != null) {
            first._parent = this;
            first = first._nextSibling;
        }
    }

    static final void disbandXobjs(Xobj first, Xobj last) {
        assert (last._locale == first._locale);
        assert (first._parent == null && last._parent == null);
        assert (first._prevSibling == null);
        assert (last._nextSibling == null);
        assert (!first.isRoot());
        while (first != null) {
            Xobj next = first._nextSibling;
            first._prevSibling = null;
            first._nextSibling = null;
            first = next;
        }
    }

    final void invalidateSpecialAttr(Xobj newParent) {
        if (this.isAttr()) {
            if (this._name.equals(Locale._xsiType)) {
                if (this._parent != null) {
                    this._parent.disconnectNonRootUsers();
                }
                if (newParent != null) {
                    newParent.disconnectNonRootUsers();
                }
            }
            if (this._name.equals(Locale._xsiNil)) {
                if (this._parent != null) {
                    this._parent.invalidateNil();
                }
                if (newParent != null) {
                    newParent.invalidateNil();
                }
            }
        }
    }

    final void removeCharsHelper(int p, int cchRemove, Xobj xTo, int pTo, boolean moveCurs, boolean invalidate) {
        Object next;
        assert (p > 0 && p < this.posMax() && p != this.posAfter() - 1);
        assert (cchRemove > 0);
        assert (this.cchRight(p) >= cchRemove);
        assert (!moveCurs || xTo != null);
        Object c = this.getEmbedded();
        while (c != null) {
            next = ((Cur)c)._next;
            assert (((Cur)c)._xobj == this);
            if (((Cur)c)._pos >= p && ((Cur)c)._pos < p + cchRemove) {
                if (moveCurs) {
                    ((Cur)c).moveToNoCheck(xTo, pTo + ((Cur)c)._pos - p);
                } else {
                    ((Cur)c).nextChars(cchRemove - ((Cur)c)._pos + p);
                }
            }
            if (((Cur)c)._xobj == this && ((Cur)c)._pos >= p + cchRemove) {
                ((Cur)c)._pos -= cchRemove;
            }
            c = next;
        }
        Bookmark b = this._bookmarks;
        while (b != null) {
            next = b._next;
            assert (b._xobj == this);
            if (b._pos >= p && b._pos < p + cchRemove) {
                assert (xTo != null);
                b.moveTo(xTo, pTo + b._pos - p);
            }
            if (b._xobj == this && b._pos >= p + cchRemove) {
                b._pos -= cchRemove;
            }
            b = b._next;
        }
        int pa = this.posAfter();
        CharUtil cu = this._locale.getCharUtil();
        if (p < pa) {
            this._srcValue = cu.removeChars(p - 1, cchRemove, this._srcValue, this._offValue, this._cchValue);
            this._offValue = cu._offSrc;
            this._cchValue = cu._cchSrc;
            if (invalidate) {
                this.invalidateUser();
                this.invalidateSpecialAttr(null);
            }
        } else {
            this._srcAfter = cu.removeChars(p - pa, cchRemove, this._srcAfter, this._offAfter, this._cchAfter);
            this._offAfter = cu._offSrc;
            this._cchAfter = cu._cchSrc;
            if (invalidate && this._parent != null) {
                this._parent.invalidateUser();
            }
        }
    }

    final void insertCharsHelper(int p, Object src, int off, int cch, boolean invalidate) {
        assert (p > 0);
        assert (p >= this.posAfter() || this.isOccupied());
        int pa = this.posAfter();
        if (p - (p < pa ? 1 : 2) < this._cchValue + this._cchAfter) {
            Cur c = this.getEmbedded();
            while (c != null) {
                if (c._pos >= p) {
                    c._pos += cch;
                }
                c = c._next;
            }
            Bookmark b = this._bookmarks;
            while (b != null) {
                if (b._pos >= p) {
                    b._pos += cch;
                }
                b = b._next;
            }
        }
        CharUtil cu = this._locale.getCharUtil();
        if (p < pa) {
            this._srcValue = cu.insertChars(p - 1, this._srcValue, this._offValue, this._cchValue, src, off, cch);
            this._offValue = cu._offSrc;
            this._cchValue = cu._cchSrc;
            if (invalidate) {
                this.invalidateUser();
                this.invalidateSpecialAttr(null);
            }
        } else {
            this._srcAfter = cu.insertChars(p - pa, this._srcAfter, this._offAfter, this._cchAfter, src, off, cch);
            this._offAfter = cu._offSrc;
            this._cchAfter = cu._cchSrc;
            if (invalidate && this._parent != null) {
                this._parent.invalidateUser();
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    Xobj copyNode(Locale toLocale) {
        newParent = null;
        copy = null;
        x = this;
        block0: while (true) {
            x.ensureOccupancy();
            newX = x.newNode(toLocale);
            newX._srcValue = x._srcValue;
            newX._offValue = x._offValue;
            newX._cchValue = x._cchValue;
            newX._srcAfter = x._srcAfter;
            newX._offAfter = x._offAfter;
            newX._cchAfter = x._cchAfter;
            b = x._bookmarks;
            while (b != null) {
                if (x.hasBookmark(CDataBookmark.CDATA_BOOKMARK.getKey(), b._pos)) {
                    newX.setBookmark(b._pos, CDataBookmark.CDATA_BOOKMARK.getKey(), CDataBookmark.CDATA_BOOKMARK);
                }
                b = b._next;
            }
            if (newParent == null) {
                copy = newX;
            } else {
                newParent.appendXobj(newX);
            }
            y = x;
            x = x.walk(this, true);
            if (x == null) break;
            if (y == x._parent) {
                newParent = newX;
                continue;
            }
            while (true) {
                if (y._parent != x._parent) ** break;
                continue block0;
                newParent = newParent._parent;
                y = y._parent;
            }
            break;
        }
        copy._srcAfter = null;
        copy._offAfter = 0;
        copy._cchAfter = 0;
        return copy;
    }

    String getCharsAsString(int p, int cch, int wsr) {
        if (this.cchRight(p) == 0) {
            return "";
        }
        Object src = this.getChars(p, cch);
        if (wsr == 1) {
            return CharUtil.getString(src, this._locale._offSrc, this._locale._cchSrc);
        }
        Locale.ScrubBuffer scrub = Locale.getScrubBuffer(wsr);
        scrub.scrub(src, this._locale._offSrc, this._locale._cchSrc);
        return scrub.getResultAsString();
    }

    String getCharsAfterAsString(int off, int cch) {
        int offset = off + this._cchValue + 2;
        if (offset == this.posMax()) {
            offset = -1;
        }
        return this.getCharsAsString(offset, cch, 1);
    }

    String getCharsValueAsString(int off, int cch) {
        return this.getCharsAsString(off + 1, cch, 1);
    }

    String getValueAsString(int wsr) {
        if (!this.hasChildren()) {
            Object src = this.getFirstChars();
            if (wsr == 1) {
                String s = CharUtil.getString(src, this._locale._offSrc, this._locale._cchSrc);
                int cch = s.length();
                if (cch > 0) {
                    Xobj lastAttr = this.lastAttr();
                    assert ((lastAttr == null ? this._cchValue : lastAttr._cchAfter) == cch);
                    if (lastAttr != null) {
                        lastAttr._srcAfter = s;
                        lastAttr._offAfter = 0;
                    } else {
                        this._srcValue = s;
                        this._offValue = 0;
                    }
                }
                return s;
            }
            Locale.ScrubBuffer scrub = Locale.getScrubBuffer(wsr);
            scrub.scrub(src, this._locale._offSrc, this._locale._cchSrc);
            return scrub.getResultAsString();
        }
        Locale.ScrubBuffer scrub = Locale.getScrubBuffer(wsr);
        Cur c = this.tempCur();
        c.push();
        c.next();
        while (!c.isAtEndOfLastPush()) {
            if (c.isText()) {
                scrub.scrub(c.getChars(-1), c._offSrc, c._cchSrc);
            }
            if (c.isComment() || c.isProcinst()) {
                c.skip();
                continue;
            }
            c.next();
        }
        String s = scrub.getResultAsString();
        c.release();
        return s;
    }

    String getValueAsString() {
        return this.getValueAsString(1);
    }

    String getString(int p, int cch) {
        String s;
        int cchRight = this.cchRight(p);
        if (cchRight == 0) {
            return "";
        }
        if (cch < 0 || cch > cchRight) {
            cch = cchRight;
        }
        int pa = this.posAfter();
        assert (p > 0);
        if (p >= pa) {
            s = CharUtil.getString(this._srcAfter, this._offAfter + p - pa, cch);
            if (p == pa && cch == this._cchAfter) {
                this._srcAfter = s;
                this._offAfter = 0;
            }
        } else {
            s = CharUtil.getString(this._srcValue, this._offValue + p - 1, cch);
            if (p == 1 && cch == this._cchValue) {
                this._srcValue = s;
                this._offValue = 0;
            }
        }
        return s;
    }

    Object getFirstChars() {
        this.ensureOccupancy();
        if (this._cchValue > 0) {
            return this.getChars(1, -1);
        }
        Xobj lastAttr = this.lastAttr();
        if (lastAttr == null || lastAttr._cchAfter <= 0) {
            this._locale._offSrc = 0;
            this._locale._cchSrc = 0;
            return null;
        }
        return lastAttr.getChars(lastAttr.posAfter(), -1);
    }

    Object getChars(int pos, int cch, Cur c) {
        Object src = this.getChars(pos, cch);
        c._offSrc = this._locale._offSrc;
        c._cchSrc = this._locale._cchSrc;
        return src;
    }

    Object getChars(int pos, int cch) {
        assert (this.isNormal(pos));
        int cchRight = this.cchRight(pos);
        if (cch < 0 || cch > cchRight) {
            cch = cchRight;
        }
        if (cch == 0) {
            this._locale._offSrc = 0;
            this._locale._cchSrc = 0;
            return null;
        }
        return this.getCharsHelper(pos, cch);
    }

    Object getCharsHelper(int pos, int cch) {
        Object src;
        assert (cch > 0 && this.cchRight(pos) >= cch);
        int pa = this.posAfter();
        if (pos >= pa) {
            src = this._srcAfter;
            this._locale._offSrc = this._offAfter + pos - pa;
        } else {
            src = this._srcValue;
            this._locale._offSrc = this._offValue + pos - 1;
        }
        this._locale._cchSrc = cch;
        return src;
    }

    final void setBit(int mask) {
        this._bits |= mask;
    }

    final void clearBit(int mask) {
        this._bits &= ~mask;
    }

    final boolean bitIsSet(int mask) {
        return (this._bits & mask) != 0;
    }

    final boolean bitIsClear(int mask) {
        return (this._bits & mask) == 0;
    }

    final boolean isVacant() {
        return this.bitIsSet(256);
    }

    final boolean isOccupied() {
        return this.bitIsClear(256);
    }

    final boolean inhibitDisconnect() {
        return this.bitIsSet(1024);
    }

    final boolean isStableUser() {
        return this.bitIsSet(512);
    }

    void invalidateNil() {
        if (this._user != null) {
            this._user.invalidate_nilvalue();
        }
    }

    void setStableType(SchemaType type) {
        this.setStableUser(((TypeStoreUserFactory)((Object)type)).createTypeStoreUser());
    }

    void setStableUser(TypeStoreUser user) {
        this.disconnectNonRootUsers();
        this.disconnectUser();
        assert (this._user == null);
        this._user = user;
        this._user.attach_store(this);
        this.setBit(512);
    }

    void disconnectUser() {
        if (this._user != null && !this.inhibitDisconnect()) {
            this.ensureOccupancy();
            this._user.disconnect_store();
            this._user = null;
        }
    }

    void disconnectNonRootUsers() {
        Xobj x = this;
        while (x != null) {
            Xobj next = x.walk(this, x._user != null);
            if (!x.isRoot()) {
                x.disconnectUser();
            }
            x = next;
        }
    }

    void disconnectChildrenUsers() {
        Xobj x = this.walk(this, this._user == null);
        while (x != null) {
            Xobj next = x.walk(this, x._user != null);
            x.disconnectUser();
            x = next;
        }
    }

    final String namespaceForPrefix(String prefix, boolean defaultAlwaysMapped) {
        if (prefix == null) {
            prefix = "";
        }
        if (prefix.equals("xml")) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        if (prefix.equals("xmlns")) {
            return "http://www.w3.org/2000/xmlns/";
        }
        Xobj x = this;
        while (x != null) {
            Xobj a = x._firstChild;
            while (a != null && a.isAttr()) {
                if (a.isXmlns() && a.getXmlnsPrefix().equals(prefix)) {
                    return a.getXmlnsUri();
                }
                a = a._nextSibling;
            }
            x = x._parent;
        }
        return defaultAlwaysMapped && prefix.length() == 0 ? "" : null;
    }

    final String prefixForNamespace(String ns, String suggestion, boolean createIfMissing) {
        if (ns == null) {
            ns = "";
        }
        if (ns.equals("http://www.w3.org/XML/1998/namespace")) {
            return "xml";
        }
        if (ns.equals("http://www.w3.org/2000/xmlns/")) {
            return "xmlns";
        }
        Xobj base = this;
        while (!base.isContainer()) {
            base = base.ensureParent();
        }
        if (ns.length() == 0) {
            Xobj a = base.findXmlnsForPrefix("");
            if (a == null || a.getXmlnsUri().length() == 0) {
                return "";
            }
            if (!createIfMissing) {
                return null;
            }
            base.setAttr(this._locale.createXmlns(null), "");
            return "";
        }
        Xobj c = base;
        while (c != null) {
            for (Xobj a = c.firstAttr(); a != null; a = a.nextAttr()) {
                if (!a.isXmlns() || !a.getXmlnsUri().equals(ns) || base.findXmlnsForPrefix(a.getXmlnsPrefix()) != a) continue;
                return a.getXmlnsPrefix();
            }
            c = c._parent;
        }
        if (!createIfMissing) {
            return null;
        }
        if (suggestion != null && (suggestion.length() == 0 || suggestion.toLowerCase().startsWith("xml") || base.findXmlnsForPrefix(suggestion) != null)) {
            suggestion = null;
        }
        if (suggestion == null) {
            String prefixBase;
            suggestion = prefixBase = QNameHelper.suggestPrefix(ns);
            int i = 1;
            while (base.findXmlnsForPrefix(suggestion) != null) {
                suggestion = prefixBase + i++;
            }
        }
        c = base;
        while (!c.isRoot() && !c.ensureParent().isRoot()) {
            c = c._parent;
        }
        base.setAttr(this._locale.createXmlns(suggestion), ns);
        return suggestion;
    }

    final QName getValueAsQName() {
        String localname;
        String prefix;
        assert (!this.hasChildren());
        String value = this.getValueAsString(3);
        int firstcolon = value.indexOf(58);
        if (firstcolon >= 0) {
            prefix = value.substring(0, firstcolon);
            localname = value.substring(firstcolon + 1);
        } else {
            prefix = "";
            localname = value;
        }
        String uri = this.namespaceForPrefix(prefix, true);
        if (uri == null) {
            return null;
        }
        return new QName(uri, localname);
    }

    final Xobj getAttr(QName name) {
        Xobj x = this._firstChild;
        while (x != null && x.isAttr()) {
            if (x._name.equals(name)) {
                return x;
            }
            x = x._nextSibling;
        }
        return null;
    }

    final QName getXsiTypeName() {
        assert (this.isContainer());
        Xobj a = this.getAttr(Locale._xsiType);
        return a == null ? null : a.getValueAsQName();
    }

    final XmlObject getObject() {
        return this.isUserNode() ? (XmlObject)((Object)this.getUser()) : null;
    }

    final TypeStoreUser getUser() {
        assert (this.isUserNode());
        assert (this._user != null || !this.isRoot() && !this.isStableUser());
        if (this._user == null) {
            TypeStoreUser parentUser = this._parent == null ? ((TypeStoreUserFactory)((Object)XmlBeans.NO_TYPE)).createTypeStoreUser() : this._parent.getUser();
            this._user = this.isElem() ? parentUser.create_element_user(this._name, this.getXsiTypeName()) : parentUser.create_attribute_user(this._name);
            this._user.attach_store(this);
        }
        return this._user;
    }

    final void invalidateUser() {
        assert (this.isValid());
        assert (this._user == null || this.isUserNode());
        if (this._user != null) {
            this._user.invalidate_value();
        }
    }

    final void ensureOccupancy() {
        assert (this.isValid());
        if (this.isVacant()) {
            assert (this.isUserNode());
            this.clearBit(256);
            TypeStoreUser user = this._user;
            this._user = null;
            String value = user.build_text(this);
            long saveVersion = this._locale._versionAll;
            long saveVersionSansText = this._locale._versionSansText;
            this.setValue(value);
            assert (saveVersionSansText == this._locale._versionSansText);
            this._locale._versionAll = saveVersion;
            assert (this._user == null);
            this._user = user;
        }
    }

    private void setValue(String val) {
        assert (CharUtil.isValid(val, 0, val.length()));
        if (val.length() <= 0) {
            return;
        }
        this._locale.notifyChange();
        Xobj lastAttr = this.lastAttr();
        int startPos = 1;
        Xobj charOwner = this;
        if (lastAttr != null) {
            charOwner = lastAttr;
            startPos = charOwner.posAfter();
        }
        charOwner.insertCharsHelper(startPos, val, 0, val.length(), true);
    }

    @Override
    public SchemaTypeLoader get_schematypeloader() {
        return this._locale._schemaTypeLoader;
    }

    @Override
    public XmlLocale get_locale() {
        return this._locale;
    }

    @Override
    public Object get_root_object() {
        return this._locale;
    }

    @Override
    public boolean is_attribute() {
        assert (this.isValid());
        return this.isAttr();
    }

    @Override
    public boolean validate_on_set() {
        assert (this.isValid());
        return this._locale._validateOnSet;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void invalidate_text() {
        this._locale.enter();
        try {
            assert (this.isValid());
            if (this.isOccupied()) {
                if (this.hasTextNoEnsureOccupancy() || this.hasChildren()) {
                    TypeStoreUser user = this._user;
                    this._user = null;
                    Cur c = this.tempCur();
                    c.moveNodeContents(null, false);
                    c.release();
                    assert (this._user == null);
                    this._user = user;
                }
                this.setBit(256);
            }
            assert (this.isValid());
        } finally {
            this._locale.exit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String fetch_text(int wsr) {
        this._locale.enter();
        try {
            assert (this.isValid() && this.isOccupied());
            String string = this.getValueAsString(wsr);
            return string;
        } finally {
            this._locale.exit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor new_cursor() {
        this._locale.enter();
        try {
            Cur c = this.tempCur();
            Cursor xc = new Cursor(c);
            c.release();
            Cursor cursor = xc;
            return cursor;
        } finally {
            this._locale.exit();
        }
    }

    @Override
    public SchemaField get_schema_field() {
        assert (this.isValid());
        if (this.isRoot()) {
            return null;
        }
        TypeStoreUser parentUser = this.ensureParent().getUser();
        if (this.isAttr()) {
            return parentUser.get_attribute_field(this._name);
        }
        assert (this.isElem());
        TypeStoreVisitor visitor = parentUser.new_visitor();
        if (visitor == null) {
            return null;
        }
        Xobj x = this._parent._firstChild;
        while (true) {
            if (x.isElem()) {
                visitor.visit(x._name);
                if (x == this) {
                    return visitor.get_schema_field();
                }
            }
            x = x._nextSibling;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void validate(ValidatorListener eventSink) {
        this._locale.enter();
        try {
            Cur c = this.tempCur();
            Validate validate = new Validate(c, eventSink);
            c.release();
        } finally {
            this._locale.exit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TypeStoreUser change_type(SchemaType type) {
        this._locale.enter();
        try {
            Cur c = this.tempCur();
            c.setType(type, false);
            c.release();
        } finally {
            this._locale.exit();
        }
        return this.getUser();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TypeStoreUser substitute(QName name, SchemaType type) {
        this._locale.enter();
        try {
            Cur c = this.tempCur();
            c.setSubstitution(name, type, false);
            c.release();
        } finally {
            this._locale.exit();
        }
        return this.getUser();
    }

    @Override
    public QName get_xsi_type() {
        return this.getXsiTypeName();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void store_text(String text) {
        this._locale.enter();
        TypeStoreUser user = this._user;
        this._user = null;
        try {
            Cur c = this.tempCur();
            c.moveNodeContents(null, false);
            if (text != null && text.length() > 0) {
                c.next();
                c.insertString(text);
            }
            c.release();
        } finally {
            assert (this._user == null);
            this._user = user;
            this._locale.exit();
        }
    }

    @Override
    public int compute_flags() {
        if (this.isRoot()) {
            return 0;
        }
        TypeStoreUser parentUser = this.ensureParent().getUser();
        if (this.isAttr()) {
            return parentUser.get_attributeflags(this._name);
        }
        int f = parentUser.get_elementflags(this._name);
        if (f != -1) {
            return f;
        }
        TypeStoreVisitor visitor = parentUser.new_visitor();
        if (visitor == null) {
            return 0;
        }
        Xobj x = this._parent._firstChild;
        while (true) {
            if (x.isElem()) {
                visitor.visit(x._name);
                if (x == this) {
                    return visitor.get_elementflags();
                }
            }
            x = x._nextSibling;
        }
    }

    @Override
    public String compute_default_text() {
        if (this.isRoot()) {
            return null;
        }
        TypeStoreUser parentUser = this.ensureParent().getUser();
        if (this.isAttr()) {
            return parentUser.get_default_attribute_text(this._name);
        }
        String result = parentUser.get_default_element_text(this._name);
        if (result != null) {
            return result;
        }
        TypeStoreVisitor visitor = parentUser.new_visitor();
        if (visitor == null) {
            return null;
        }
        Xobj x = this._parent._firstChild;
        while (true) {
            if (x.isElem()) {
                visitor.visit(x._name);
                if (x == this) {
                    return visitor.get_default_text();
                }
            }
            x = x._nextSibling;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean find_nil() {
        if (this.isAttr()) {
            return false;
        }
        this._locale.enter();
        try {
            Xobj a = this.getAttr(Locale._xsiNil);
            if (a == null) {
                boolean bl = false;
                return bl;
            }
            String value = a.getValueAsString(3);
            boolean bl = value.equals("true") || value.equals("1");
            return bl;
        } finally {
            this._locale.exit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void invalidate_nil() {
        if (this.isAttr()) {
            return;
        }
        this._locale.enter();
        try {
            if (!this._user.build_nil()) {
                this.removeAttr(Locale._xsiNil);
            } else {
                this.setAttr(Locale._xsiNil, "true");
            }
        } finally {
            this._locale.exit();
        }
    }

    @Override
    public int count_elements(QName name) {
        return this._locale.count(this, name, null);
    }

    @Override
    public int count_elements(QNameSet names) {
        return this._locale.count(this, null, names);
    }

    @Override
    public TypeStoreUser find_element_user(QName name, int i) {
        Xobj x = this._firstChild;
        while (x != null) {
            if (x.isElem() && x._name.equals(name) && --i < 0) {
                return x.getUser();
            }
            x = x._nextSibling;
        }
        return null;
    }

    @Override
    public TypeStoreUser find_element_user(QNameSet names, int i) {
        Xobj x = this._firstChild;
        while (x != null) {
            if (x.isElem() && names.contains(x._name) && --i < 0) {
                return x.getUser();
            }
            x = x._nextSibling;
        }
        return null;
    }

    @Override
    public void find_all_element_users(QName name, List fillMeUp) {
        Xobj x = this._firstChild;
        while (x != null) {
            if (x.isElem() && x._name.equals(name)) {
                fillMeUp.add(x.getUser());
            }
            x = x._nextSibling;
        }
    }

    @Override
    public void find_all_element_users(QNameSet names, List fillMeUp) {
        Xobj x = this._firstChild;
        while (x != null) {
            if (x.isElem() && names.contains(x._name)) {
                fillMeUp.add(x.getUser());
            }
            x = x._nextSibling;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static TypeStoreUser insertElement(QName name, Xobj x, int pos) {
        x._locale.enter();
        try {
            Cur c = x._locale.tempCur();
            c.moveTo(x, pos);
            c.createElement(name);
            TypeStoreUser user = c.getUser();
            c.release();
            TypeStoreUser typeStoreUser = user;
            return typeStoreUser;
        } finally {
            x._locale.exit();
        }
    }

    @Override
    public TypeStoreUser insert_element_user(QName name, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!this.isContainer()) {
            throw new IllegalStateException();
        }
        Xobj x = this._locale.findNthChildElem(this, name, null, i);
        if (x == null) {
            if (i > this._locale.count(this, name, null) + 1) {
                throw new IndexOutOfBoundsException();
            }
            return this.add_element_user(name);
        }
        return Xobj.insertElement(name, x, 0);
    }

    @Override
    public TypeStoreUser insert_element_user(QNameSet names, QName name, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!this.isContainer()) {
            throw new IllegalStateException();
        }
        Xobj x = this._locale.findNthChildElem(this, null, names, i);
        if (x == null) {
            if (i > this._locale.count(this, null, names) + 1) {
                throw new IndexOutOfBoundsException();
            }
            return this.add_element_user(name);
        }
        return Xobj.insertElement(name, x, 0);
    }

    @Override
    public TypeStoreUser add_element_user(QName name) {
        if (!this.isContainer()) {
            throw new IllegalStateException();
        }
        QNameSet endSet = null;
        boolean gotEndSet = false;
        Xobj candidate = null;
        Xobj x = this._lastChild;
        while (x != null) {
            if (x.isContainer()) {
                if (x._name.equals(name)) break;
                if (!gotEndSet) {
                    endSet = this._user.get_element_ending_delimiters(name);
                    gotEndSet = true;
                }
                if (endSet == null || endSet.contains(x._name)) {
                    candidate = x;
                }
            }
            x = x._prevSibling;
        }
        return candidate == null ? Xobj.insertElement(name, this, -1) : Xobj.insertElement(name, candidate, 0);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void removeElement(Xobj x) {
        if (x == null) {
            throw new IndexOutOfBoundsException();
        }
        x._locale.enter();
        try {
            Cur c = x.tempCur();
            c.moveNode(null);
            c.release();
        } finally {
            x._locale.exit();
        }
    }

    @Override
    public void remove_element(QName name, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!this.isContainer()) {
            throw new IllegalStateException();
        }
        Xobj x = this._firstChild;
        while (!(x == null || x.isElem() && x._name.equals(name) && --i < 0)) {
            x = x._nextSibling;
        }
        Xobj.removeElement(x);
    }

    @Override
    public void remove_element(QNameSet names, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!this.isContainer()) {
            throw new IllegalStateException();
        }
        Xobj x = this._firstChild;
        while (!(x == null || x.isElem() && names.contains(x._name) && --i < 0)) {
            x = x._nextSibling;
        }
        Xobj.removeElement(x);
    }

    @Override
    public TypeStoreUser find_attribute_user(QName name) {
        Xobj a = this.getAttr(name);
        return a == null ? null : a.getUser();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TypeStoreUser add_attribute_user(QName name) {
        if (this.getAttr(name) != null) {
            throw new IndexOutOfBoundsException();
        }
        this._locale.enter();
        try {
            TypeStoreUser typeStoreUser = this.setAttr(name, "").getUser();
            return typeStoreUser;
        } finally {
            this._locale.exit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void remove_attribute(QName name) {
        this._locale.enter();
        try {
            if (!this.removeAttr(name)) {
                throw new IndexOutOfBoundsException();
            }
        } finally {
            this._locale.exit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TypeStoreUser copy_contents_from(TypeStore source) {
        Xobj xSrc = (Xobj)source;
        if (xSrc == this) {
            return this.getUser();
        }
        this._locale.enter();
        try {
            xSrc._locale.enter();
            Cur c = this.tempCur();
            try {
                Cur cSrc1 = xSrc.tempCur();
                Map sourceNamespaces = Locale.getAllNamespaces(cSrc1, null);
                cSrc1.release();
                if (this.isAttr()) {
                    Cur cSrc = xSrc.tempCur();
                    String value = Locale.getTextValue(cSrc);
                    cSrc.release();
                    c.setValue(value);
                } else {
                    this.disconnectChildrenUsers();
                    assert (!this.inhibitDisconnect());
                    this.setBit(1024);
                    QName xsiType = this.isContainer() ? this.getXsiTypeName() : null;
                    Xobj copy = xSrc.copyNode(this._locale);
                    Cur.moveNodeContents(this, null, true);
                    c.next();
                    Cur.moveNodeContents(copy, c, true);
                    c.moveTo(this);
                    if (xsiType != null) {
                        c.setXsiType(xsiType);
                    }
                    assert (this.inhibitDisconnect());
                    this.clearBit(1024);
                }
                if (sourceNamespaces != null) {
                    if (!c.isContainer()) {
                        c.toParent();
                    }
                    Locale.applyNamespaces(c, sourceNamespaces);
                }
            } finally {
                c.release();
                xSrc._locale.exit();
            }
        } finally {
            this._locale.exit();
        }
        return this.getUser();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TypeStoreUser copy(SchemaTypeLoader stl, SchemaType type, XmlOptions options) {
        Xobj destination = null;
        SchemaType sType = (SchemaType)(options = XmlOptions.maskNull(options)).get("DOCUMENT_TYPE");
        if (sType == null) {
            sType = type == null ? XmlObject.type : type;
        }
        Locale locale = this.locale();
        if (Boolean.TRUE.equals(options.get("COPY_USE_NEW_LOCALE"))) {
            locale = Locale.getLocale(stl, options);
        }
        destination = sType.isDocumentType() || sType.isNoType() && this instanceof DocumentXobj ? Cur.createDomDocumentRootXobj(locale, false) : Cur.createDomDocumentRootXobj(locale, true);
        locale.enter();
        try {
            Cur c = destination.tempCur();
            c.setType(type);
            c.release();
        } finally {
            locale.exit();
        }
        TypeStoreUser tsu = destination.copy_contents_from(this);
        return tsu;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void array_setter(XmlObject[] sources, QName elementName) {
        this._locale.enter();
        try {
            int n;
            int m = sources.length;
            ArrayList<Xobj> copies = new ArrayList<Xobj>();
            ArrayList<SchemaType> types = new ArrayList<SchemaType>();
            for (int i = 0; i < m; ++i) {
                if (sources[i] == null) {
                    throw new IllegalArgumentException("Array element null");
                }
                if (sources[i].isImmutable()) {
                    copies.add(null);
                    types.add(null);
                    continue;
                }
                Xobj x = (Xobj)((TypeStoreUser)((Object)sources[i])).get_store();
                if (x._locale == this._locale) {
                    copies.add(x.copyNode(this._locale));
                } else {
                    x._locale.enter();
                    try {
                        copies.add(x.copyNode(this._locale));
                    } finally {
                        x._locale.exit();
                    }
                }
                types.add(sources[i].schemaType());
            }
            for (n = this.count_elements(elementName); n > m; --n) {
                this.remove_element(elementName, m);
            }
            while (m > n) {
                this.add_element_user(elementName);
                ++n;
            }
            assert (m == n);
            ArrayList<Xobj> elements = new ArrayList<Xobj>();
            this.find_all_element_users(elementName, elements);
            for (int i = 0; i < elements.size(); ++i) {
                elements.set(i, (Xobj)((TypeStoreUser)elements.get(i)).get_store());
            }
            assert (elements.size() == n);
            Cur c = this.tempCur();
            for (int i = 0; i < n; ++i) {
                Xobj x = (Xobj)elements.get(i);
                if (sources[i].isImmutable()) {
                    x.getObject().set(sources[i]);
                    continue;
                }
                Cur.moveNodeContents(x, null, true);
                c.moveTo(x);
                c.next();
                Cur.moveNodeContents((Xobj)copies.get(i), c, true);
                x.change_type((SchemaType)types.get(i));
            }
            c.release();
        } finally {
            this._locale.exit();
        }
    }

    @Override
    public void visit_elements(TypeStoreVisitor visitor) {
        throw new RuntimeException("Not implemeneted");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlObject[] exec_query(String queryExpr, XmlOptions options) throws XmlException {
        this._locale.enter();
        try {
            Cur c = this.tempCur();
            XmlObject[] result = Query.objectExecQuery(c, queryExpr, options);
            c.release();
            XmlObject[] xmlObjectArray = result;
            return xmlObjectArray;
        } finally {
            this._locale.exit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String find_prefix_for_nsuri(String nsuri, String suggested_prefix) {
        this._locale.enter();
        try {
            String string = this.prefixForNamespace(nsuri, suggested_prefix, true);
            return string;
        } finally {
            this._locale.exit();
        }
    }

    @Override
    public String getNamespaceForPrefix(String prefix) {
        return this.namespaceForPrefix(prefix, true);
    }

    static class Bookmark
    implements XmlCursor.XmlMark {
        Xobj _xobj;
        int _pos;
        Bookmark _next;
        Bookmark _prev;
        Object _key;
        Object _value;

        Bookmark() {
        }

        boolean isOnList(Bookmark head) {
            while (head != null) {
                if (head == this) {
                    return true;
                }
                head = head._next;
            }
            return false;
        }

        Bookmark listInsert(Bookmark head) {
            assert (this._next == null && this._prev == null);
            if (head == null) {
                head = this._prev = this;
            } else {
                this._prev = head._prev;
                head._prev = head._prev._next = this;
            }
            return head;
        }

        Bookmark listRemove(Bookmark head) {
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

        void moveTo(Xobj x, int p) {
            assert (this.isOnList(this._xobj._bookmarks));
            if (this._xobj != x) {
                this._xobj._bookmarks = this.listRemove(this._xobj._bookmarks);
                x._bookmarks = this.listInsert(x._bookmarks);
                this._xobj = x;
            }
            this._pos = p;
        }

        @Override
        public XmlCursor createCursor() {
            if (this._xobj == null) {
                throw new IllegalStateException("Attempting to create a cursor on a bookmark that has been cleared or replaced.");
            }
            return Cursor.newCursor(this._xobj, this._pos);
        }
    }

    static class DetailEntryXobj
    extends SoapElementXobj
    implements DetailEntry {
        @Override
        Xobj newNode(Locale l) {
            return new DetailEntryXobj(l, this._name);
        }

        DetailEntryXobj(Locale l, QName name) {
            super(l, name);
        }
    }

    static class DetailXobj
    extends SoapFaultElementXobj
    implements Detail {
        DetailXobj(Locale l, QName name) {
            super(l, name);
        }

        @Override
        Xobj newNode(Locale l) {
            return new DetailXobj(l, this._name);
        }

        @Override
        public DetailEntry addDetailEntry(Name name) {
            return DomImpl.detail_addDetailEntry(this, name);
        }

        @Override
        public Iterator getDetailEntries() {
            return DomImpl.detail_getDetailEntries(this);
        }
    }

    static class SoapFaultElementXobj
    extends SoapElementXobj
    implements SOAPFaultElement {
        SoapFaultElementXobj(Locale l, QName name) {
            super(l, name);
        }

        @Override
        Xobj newNode(Locale l) {
            return new SoapFaultElementXobj(l, this._name);
        }
    }

    static class SoapFaultXobj
    extends SoapBodyElementXobj
    implements SOAPFault {
        SoapFaultXobj(Locale l, QName name) {
            super(l, name);
        }

        @Override
        Xobj newNode(Locale l) {
            return new SoapFaultXobj(l, this._name);
        }

        @Override
        public void setFaultString(String faultString) {
            DomImpl.soapFault_setFaultString(this, faultString);
        }

        @Override
        public void setFaultString(String faultString, java.util.Locale locale) {
            DomImpl.soapFault_setFaultString(this, faultString, locale);
        }

        @Override
        public void setFaultCode(Name faultCodeName) throws SOAPException {
            DomImpl.soapFault_setFaultCode((DomImpl.Dom)this, faultCodeName);
        }

        @Override
        public void setFaultActor(String faultActorString) {
            DomImpl.soapFault_setFaultActor(this, faultActorString);
        }

        @Override
        public String getFaultActor() {
            return DomImpl.soapFault_getFaultActor(this);
        }

        @Override
        public String getFaultCode() {
            return DomImpl.soapFault_getFaultCode(this);
        }

        @Override
        public void setFaultCode(String faultCode) throws SOAPException {
            DomImpl.soapFault_setFaultCode((DomImpl.Dom)this, faultCode);
        }

        @Override
        public java.util.Locale getFaultStringLocale() {
            return DomImpl.soapFault_getFaultStringLocale(this);
        }

        @Override
        public Name getFaultCodeAsName() {
            return DomImpl.soapFault_getFaultCodeAsName(this);
        }

        @Override
        public String getFaultString() {
            return DomImpl.soapFault_getFaultString(this);
        }

        @Override
        public Detail addDetail() throws SOAPException {
            return DomImpl.soapFault_addDetail(this);
        }

        @Override
        public Detail getDetail() {
            return DomImpl.soapFault_getDetail(this);
        }
    }

    static class SoapHeaderElementXobj
    extends SoapElementXobj
    implements SOAPHeaderElement {
        SoapHeaderElementXobj(Locale l, QName name) {
            super(l, name);
        }

        @Override
        Xobj newNode(Locale l) {
            return new SoapHeaderElementXobj(l, this._name);
        }

        @Override
        public void setMustUnderstand(boolean mustUnderstand) {
            DomImpl.soapHeaderElement_setMustUnderstand(this, mustUnderstand);
        }

        @Override
        public boolean getMustUnderstand() {
            return DomImpl.soapHeaderElement_getMustUnderstand(this);
        }

        @Override
        public void setActor(String actor) {
            DomImpl.soapHeaderElement_setActor(this, actor);
        }

        @Override
        public String getActor() {
            return DomImpl.soapHeaderElement_getActor(this);
        }
    }

    static class SoapHeaderXobj
    extends SoapElementXobj
    implements SOAPHeader {
        SoapHeaderXobj(Locale l, QName name) {
            super(l, name);
        }

        @Override
        Xobj newNode(Locale l) {
            return new SoapHeaderXobj(l, this._name);
        }

        @Override
        public Iterator examineAllHeaderElements() {
            return DomImpl.soapHeader_examineAllHeaderElements(this);
        }

        @Override
        public Iterator extractAllHeaderElements() {
            return DomImpl.soapHeader_extractAllHeaderElements(this);
        }

        @Override
        public Iterator examineHeaderElements(String actor) {
            return DomImpl.soapHeader_examineHeaderElements(this, actor);
        }

        @Override
        public Iterator examineMustUnderstandHeaderElements(String mustUnderstandString) {
            return DomImpl.soapHeader_examineMustUnderstandHeaderElements(this, mustUnderstandString);
        }

        @Override
        public Iterator extractHeaderElements(String actor) {
            return DomImpl.soapHeader_extractHeaderElements(this, actor);
        }

        @Override
        public SOAPHeaderElement addHeaderElement(Name name) {
            return DomImpl.soapHeader_addHeaderElement(this, name);
        }
    }

    static class SoapEnvelopeXobj
    extends SoapElementXobj
    implements SOAPEnvelope {
        SoapEnvelopeXobj(Locale l, QName name) {
            super(l, name);
        }

        @Override
        Xobj newNode(Locale l) {
            return new SoapEnvelopeXobj(l, this._name);
        }

        @Override
        public SOAPBody addBody() throws SOAPException {
            return DomImpl._soapEnvelope_addBody(this);
        }

        @Override
        public SOAPBody getBody() throws SOAPException {
            return DomImpl._soapEnvelope_getBody(this);
        }

        @Override
        public SOAPHeader getHeader() throws SOAPException {
            return DomImpl._soapEnvelope_getHeader(this);
        }

        @Override
        public SOAPHeader addHeader() throws SOAPException {
            return DomImpl._soapEnvelope_addHeader(this);
        }

        @Override
        public Name createName(String localName) {
            return DomImpl._soapEnvelope_createName(this, localName);
        }

        @Override
        public Name createName(String localName, String prefix, String namespaceURI) {
            return DomImpl._soapEnvelope_createName(this, localName, prefix, namespaceURI);
        }
    }

    static class SoapBodyElementXobj
    extends SoapElementXobj
    implements SOAPBodyElement {
        SoapBodyElementXobj(Locale l, QName name) {
            super(l, name);
        }

        @Override
        Xobj newNode(Locale l) {
            return new SoapBodyElementXobj(l, this._name);
        }
    }

    static class SoapBodyXobj
    extends SoapElementXobj
    implements SOAPBody {
        SoapBodyXobj(Locale l, QName name) {
            super(l, name);
        }

        @Override
        Xobj newNode(Locale l) {
            return new SoapBodyXobj(l, this._name);
        }

        @Override
        public boolean hasFault() {
            return DomImpl.soapBody_hasFault(this);
        }

        @Override
        public SOAPFault addFault() throws SOAPException {
            return DomImpl.soapBody_addFault(this);
        }

        @Override
        public SOAPFault getFault() {
            return DomImpl.soapBody_getFault(this);
        }

        @Override
        public SOAPBodyElement addBodyElement(Name name) {
            return DomImpl.soapBody_addBodyElement(this, name);
        }

        @Override
        public SOAPBodyElement addDocument(Document document) {
            return DomImpl.soapBody_addDocument(this, document);
        }

        @Override
        public SOAPFault addFault(Name name, String s) throws SOAPException {
            return DomImpl.soapBody_addFault(this, name, s);
        }

        @Override
        public SOAPFault addFault(Name faultCode, String faultString, java.util.Locale locale) throws SOAPException {
            return DomImpl.soapBody_addFault(this, faultCode, faultString, locale);
        }
    }

    static class SoapElementXobj
    extends ElementXobj
    implements SOAPElement,
    Node {
        SoapElementXobj(Locale l, QName name) {
            super(l, name);
        }

        @Override
        Xobj newNode(Locale l) {
            return new SoapElementXobj(l, this._name);
        }

        @Override
        public void detachNode() {
            DomImpl._soapNode_detachNode(this);
        }

        @Override
        public void recycleNode() {
            DomImpl._soapNode_recycleNode(this);
        }

        @Override
        public String getValue() {
            return DomImpl._soapNode_getValue(this);
        }

        @Override
        public void setValue(String value) {
            DomImpl._soapNode_setValue(this, value);
        }

        @Override
        public SOAPElement getParentElement() {
            return DomImpl._soapNode_getParentElement(this);
        }

        @Override
        public void setParentElement(SOAPElement p) {
            DomImpl._soapNode_setParentElement(this, p);
        }

        @Override
        public void removeContents() {
            DomImpl._soapElement_removeContents(this);
        }

        @Override
        public String getEncodingStyle() {
            return DomImpl._soapElement_getEncodingStyle(this);
        }

        @Override
        public void setEncodingStyle(String encodingStyle) {
            DomImpl._soapElement_setEncodingStyle(this, encodingStyle);
        }

        @Override
        public boolean removeNamespaceDeclaration(String prefix) {
            return DomImpl._soapElement_removeNamespaceDeclaration(this, prefix);
        }

        @Override
        public Iterator getAllAttributes() {
            return DomImpl._soapElement_getAllAttributes(this);
        }

        @Override
        public Iterator getChildElements() {
            return DomImpl._soapElement_getChildElements(this);
        }

        @Override
        public Iterator getNamespacePrefixes() {
            return DomImpl._soapElement_getNamespacePrefixes(this);
        }

        @Override
        public SOAPElement addAttribute(Name name, String value) throws SOAPException {
            return DomImpl._soapElement_addAttribute(this, name, value);
        }

        @Override
        public SOAPElement addChildElement(SOAPElement oldChild) throws SOAPException {
            return DomImpl._soapElement_addChildElement((DomImpl.Dom)this, oldChild);
        }

        @Override
        public SOAPElement addChildElement(Name name) throws SOAPException {
            return DomImpl._soapElement_addChildElement((DomImpl.Dom)this, name);
        }

        @Override
        public SOAPElement addChildElement(String localName) throws SOAPException {
            return DomImpl._soapElement_addChildElement((DomImpl.Dom)this, localName);
        }

        @Override
        public SOAPElement addChildElement(String localName, String prefix) throws SOAPException {
            return DomImpl._soapElement_addChildElement(this, localName, prefix);
        }

        @Override
        public SOAPElement addChildElement(String localName, String prefix, String uri) throws SOAPException {
            return DomImpl._soapElement_addChildElement(this, localName, prefix, uri);
        }

        @Override
        public SOAPElement addNamespaceDeclaration(String prefix, String uri) {
            return DomImpl._soapElement_addNamespaceDeclaration(this, prefix, uri);
        }

        @Override
        public SOAPElement addTextNode(String data) {
            return DomImpl._soapElement_addTextNode(this, data);
        }

        @Override
        public String getAttributeValue(Name name) {
            return DomImpl._soapElement_getAttributeValue(this, name);
        }

        @Override
        public Iterator getChildElements(Name name) {
            return DomImpl._soapElement_getChildElements(this, name);
        }

        @Override
        public Name getElementName() {
            return DomImpl._soapElement_getElementName(this);
        }

        @Override
        public String getNamespaceURI(String prefix) {
            return DomImpl._soapElement_getNamespaceURI(this, prefix);
        }

        @Override
        public Iterator getVisibleNamespacePrefixes() {
            return DomImpl._soapElement_getVisibleNamespacePrefixes(this);
        }

        @Override
        public boolean removeAttribute(Name name) {
            return DomImpl._soapElement_removeAttribute(this, name);
        }
    }

    static class SoapPartDom
    extends SOAPPart
    implements DomImpl.Dom,
    Document,
    NodeList {
        SoapPartDocXobj _docXobj;

        SoapPartDom(SoapPartDocXobj docXobj) {
            this._docXobj = docXobj;
        }

        @Override
        public int nodeType() {
            return 9;
        }

        @Override
        public Locale locale() {
            return this._docXobj._locale;
        }

        @Override
        public Cur tempCur() {
            return this._docXobj.tempCur();
        }

        @Override
        public QName getQName() {
            return this._docXobj._name;
        }

        @Override
        public void dump() {
            this.dump(System.out);
        }

        @Override
        public void dump(PrintStream o) {
            this._docXobj.dump(o);
        }

        @Override
        public void dump(PrintStream o, Object ref) {
            this._docXobj.dump(o, ref);
        }

        public String name() {
            return "#document";
        }

        @Override
        public org.w3c.dom.Node appendChild(org.w3c.dom.Node newChild) {
            return DomImpl._node_appendChild(this, newChild);
        }

        @Override
        public org.w3c.dom.Node cloneNode(boolean deep) {
            return DomImpl._node_cloneNode(this, deep);
        }

        @Override
        public NamedNodeMap getAttributes() {
            return null;
        }

        @Override
        public NodeList getChildNodes() {
            return this;
        }

        @Override
        public org.w3c.dom.Node getParentNode() {
            return DomImpl._node_getParentNode(this);
        }

        @Override
        public org.w3c.dom.Node removeChild(org.w3c.dom.Node oldChild) {
            return DomImpl._node_removeChild(this, oldChild);
        }

        @Override
        public org.w3c.dom.Node getFirstChild() {
            return DomImpl._node_getFirstChild(this);
        }

        @Override
        public org.w3c.dom.Node getLastChild() {
            return DomImpl._node_getLastChild(this);
        }

        @Override
        public String getLocalName() {
            return DomImpl._node_getLocalName(this);
        }

        @Override
        public String getNamespaceURI() {
            return DomImpl._node_getNamespaceURI(this);
        }

        @Override
        public org.w3c.dom.Node getNextSibling() {
            return DomImpl._node_getNextSibling(this);
        }

        @Override
        public String getNodeName() {
            return DomImpl._node_getNodeName(this);
        }

        @Override
        public short getNodeType() {
            return DomImpl._node_getNodeType(this);
        }

        @Override
        public String getNodeValue() {
            return DomImpl._node_getNodeValue(this);
        }

        @Override
        public Document getOwnerDocument() {
            return DomImpl._node_getOwnerDocument(this);
        }

        @Override
        public String getPrefix() {
            return DomImpl._node_getPrefix(this);
        }

        @Override
        public org.w3c.dom.Node getPreviousSibling() {
            return DomImpl._node_getPreviousSibling(this);
        }

        @Override
        public boolean hasAttributes() {
            return DomImpl._node_hasAttributes(this);
        }

        @Override
        public boolean hasChildNodes() {
            return DomImpl._node_hasChildNodes(this);
        }

        @Override
        public org.w3c.dom.Node insertBefore(org.w3c.dom.Node newChild, org.w3c.dom.Node refChild) {
            return DomImpl._node_insertBefore(this, newChild, refChild);
        }

        @Override
        public boolean isSupported(String feature, String version) {
            return DomImpl._node_isSupported(this, feature, version);
        }

        @Override
        public void normalize() {
            DomImpl._node_normalize(this);
        }

        @Override
        public org.w3c.dom.Node replaceChild(org.w3c.dom.Node newChild, org.w3c.dom.Node oldChild) {
            return DomImpl._node_replaceChild(this, newChild, oldChild);
        }

        @Override
        public void setNodeValue(String nodeValue) {
            DomImpl._node_setNodeValue(this, nodeValue);
        }

        @Override
        public void setPrefix(String prefix) {
            DomImpl._node_setPrefix(this, prefix);
        }

        @Override
        public Object getUserData(String key) {
            return DomImpl._node_getUserData(this, key);
        }

        @Override
        public Object setUserData(String key, Object data, UserDataHandler handler) {
            return DomImpl._node_setUserData(this, key, data, handler);
        }

        @Override
        public Object getFeature(String feature, String version) {
            return DomImpl._node_getFeature(this, feature, version);
        }

        @Override
        public boolean isEqualNode(org.w3c.dom.Node arg) {
            return DomImpl._node_isEqualNode(this, arg);
        }

        @Override
        public boolean isSameNode(org.w3c.dom.Node arg) {
            return DomImpl._node_isSameNode(this, arg);
        }

        @Override
        public String lookupNamespaceURI(String prefix) {
            return DomImpl._node_lookupNamespaceURI(this, prefix);
        }

        @Override
        public String lookupPrefix(String namespaceURI) {
            return DomImpl._node_lookupPrefix(this, namespaceURI);
        }

        @Override
        public boolean isDefaultNamespace(String namespaceURI) {
            return DomImpl._node_isDefaultNamespace(this, namespaceURI);
        }

        @Override
        public void setTextContent(String textContent) {
            DomImpl._node_setTextContent(this, textContent);
        }

        @Override
        public String getTextContent() {
            return DomImpl._node_getTextContent(this);
        }

        @Override
        public short compareDocumentPosition(org.w3c.dom.Node other) {
            return DomImpl._node_compareDocumentPosition(this, other);
        }

        @Override
        public String getBaseURI() {
            return DomImpl._node_getBaseURI(this);
        }

        @Override
        public org.w3c.dom.Node adoptNode(org.w3c.dom.Node source) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public String getDocumentURI() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public DOMConfiguration getDomConfig() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public String getInputEncoding() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public boolean getStrictErrorChecking() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public String getXmlEncoding() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public boolean getXmlStandalone() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public String getXmlVersion() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public void normalizeDocument() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public org.w3c.dom.Node renameNode(org.w3c.dom.Node n, String namespaceURI, String qualifiedName) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public void setDocumentURI(String documentURI) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public void setStrictErrorChecking(boolean strictErrorChecking) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public void setXmlStandalone(boolean xmlStandalone) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public void setXmlVersion(String xmlVersion) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public Attr createAttribute(String name) {
            return DomImpl._document_createAttribute(this, name);
        }

        @Override
        public Attr createAttributeNS(String namespaceURI, String qualifiedName) {
            return DomImpl._document_createAttributeNS(this, namespaceURI, qualifiedName);
        }

        @Override
        public CDATASection createCDATASection(String data) {
            return DomImpl._document_createCDATASection(this, data);
        }

        @Override
        public Comment createComment(String data) {
            return DomImpl._document_createComment(this, data);
        }

        @Override
        public DocumentFragment createDocumentFragment() {
            return DomImpl._document_createDocumentFragment(this);
        }

        @Override
        public Element createElement(String tagName) {
            return DomImpl._document_createElement(this, tagName);
        }

        @Override
        public Element createElementNS(String namespaceURI, String qualifiedName) {
            return DomImpl._document_createElementNS(this, namespaceURI, qualifiedName);
        }

        @Override
        public EntityReference createEntityReference(String name) {
            return DomImpl._document_createEntityReference(this, name);
        }

        @Override
        public ProcessingInstruction createProcessingInstruction(String target, String data) {
            return DomImpl._document_createProcessingInstruction(this, target, data);
        }

        @Override
        public Text createTextNode(String data) {
            return DomImpl._document_createTextNode(this, data);
        }

        @Override
        public DocumentType getDoctype() {
            return DomImpl._document_getDoctype(this);
        }

        @Override
        public Element getDocumentElement() {
            return DomImpl._document_getDocumentElement(this);
        }

        @Override
        public Element getElementById(String elementId) {
            return DomImpl._document_getElementById(this, elementId);
        }

        @Override
        public NodeList getElementsByTagName(String tagname) {
            return DomImpl._document_getElementsByTagName(this, tagname);
        }

        @Override
        public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
            return DomImpl._document_getElementsByTagNameNS(this, namespaceURI, localName);
        }

        @Override
        public DOMImplementation getImplementation() {
            return DomImpl._document_getImplementation(this);
        }

        @Override
        public org.w3c.dom.Node importNode(org.w3c.dom.Node importedNode, boolean deep) {
            return DomImpl._document_importNode(this, importedNode, deep);
        }

        @Override
        public int getLength() {
            return DomImpl._childNodes_getLength(this);
        }

        @Override
        public org.w3c.dom.Node item(int i) {
            return DomImpl._childNodes_item(this, i);
        }

        @Override
        public void removeAllMimeHeaders() {
            DomImpl._soapPart_removeAllMimeHeaders(this);
        }

        @Override
        public void removeMimeHeader(String name) {
            DomImpl._soapPart_removeMimeHeader(this, name);
        }

        @Override
        public Iterator getAllMimeHeaders() {
            return DomImpl._soapPart_getAllMimeHeaders(this);
        }

        @Override
        public SOAPEnvelope getEnvelope() {
            return DomImpl._soapPart_getEnvelope(this);
        }

        @Override
        public Source getContent() {
            return DomImpl._soapPart_getContent(this);
        }

        @Override
        public void setContent(Source source) {
            DomImpl._soapPart_setContent(this, source);
        }

        @Override
        public String[] getMimeHeader(String name) {
            return DomImpl._soapPart_getMimeHeader(this, name);
        }

        @Override
        public void addMimeHeader(String name, String value) {
            DomImpl._soapPart_addMimeHeader(this, name, value);
        }

        @Override
        public void setMimeHeader(String name, String value) {
            DomImpl._soapPart_setMimeHeader(this, name, value);
        }

        @Override
        public Iterator getMatchingMimeHeaders(String[] names) {
            return DomImpl._soapPart_getMatchingMimeHeaders(this, names);
        }

        @Override
        public Iterator getNonMatchingMimeHeaders(String[] names) {
            return DomImpl._soapPart_getNonMatchingMimeHeaders(this, names);
        }

        @Override
        public boolean nodeCanHavePrefixUri() {
            return true;
        }
    }

    static class SoapPartDocXobj
    extends DocumentXobj {
        SoapPartDom _soapPartDom = new SoapPartDom(this);

        SoapPartDocXobj(Locale l) {
            super(l);
        }

        @Override
        DomImpl.Dom getDom() {
            return this._soapPartDom;
        }

        @Override
        Xobj newNode(Locale l) {
            return new SoapPartDocXobj(l);
        }
    }

    static class ProcInstXobj
    extends NodeXobj
    implements ProcessingInstruction {
        ProcInstXobj(Locale l, String target) {
            super(l, 5, 7);
            this._name = this._locale.makeQName(null, target);
        }

        @Override
        Xobj newNode(Locale l) {
            return new ProcInstXobj(l, this._name.getLocalPart());
        }

        @Override
        public int getLength() {
            return 0;
        }

        @Override
        public org.w3c.dom.Node getFirstChild() {
            return null;
        }

        @Override
        public String getData() {
            return DomImpl._processingInstruction_getData(this);
        }

        @Override
        public String getTarget() {
            return DomImpl._processingInstruction_getTarget(this);
        }

        @Override
        public void setData(String data) {
            DomImpl._processingInstruction_setData(this, data);
        }
    }

    static class CommentXobj
    extends NodeXobj
    implements Comment {
        CommentXobj(Locale l) {
            super(l, 4, 8);
        }

        @Override
        Xobj newNode(Locale l) {
            return new CommentXobj(l);
        }

        @Override
        public NodeList getChildNodes() {
            return DomImpl._emptyNodeList;
        }

        @Override
        public void appendData(String arg) {
            DomImpl._characterData_appendData(this, arg);
        }

        @Override
        public void deleteData(int offset, int count) {
            DomImpl._characterData_deleteData(this, offset, count);
        }

        @Override
        public String getData() {
            return DomImpl._characterData_getData(this);
        }

        @Override
        public int getLength() {
            return DomImpl._characterData_getLength(this);
        }

        @Override
        public org.w3c.dom.Node getFirstChild() {
            return null;
        }

        @Override
        public void insertData(int offset, String arg) {
            DomImpl._characterData_insertData(this, offset, arg);
        }

        @Override
        public void replaceData(int offset, int count, String arg) {
            DomImpl._characterData_replaceData(this, offset, count, arg);
        }

        @Override
        public void setData(String data) {
            DomImpl._characterData_setData(this, data);
        }

        @Override
        public String substringData(int offset, int count) {
            return DomImpl._characterData_substringData(this, offset, count);
        }
    }

    static class AttrIdXobj
    extends AttrXobj {
        AttrIdXobj(Locale l, QName name) {
            super(l, name);
        }

        @Override
        public boolean isId() {
            return true;
        }
    }

    static class AttrXobj
    extends NamedNodeXobj
    implements Attr {
        AttrXobj(Locale l, QName name) {
            super(l, 3, 2);
            this._name = name;
        }

        @Override
        Xobj newNode(Locale l) {
            return new AttrXobj(l, this._name);
        }

        @Override
        public org.w3c.dom.Node getNextSibling() {
            return null;
        }

        @Override
        public String getName() {
            return DomImpl._node_getNodeName(this);
        }

        @Override
        public Element getOwnerElement() {
            return DomImpl._attr_getOwnerElement(this);
        }

        @Override
        public boolean getSpecified() {
            return DomImpl._attr_getSpecified(this);
        }

        @Override
        public String getValue() {
            return DomImpl._node_getNodeValue(this);
        }

        @Override
        public void setValue(String value) {
            DomImpl._node_setNodeValue(this, value);
        }

        @Override
        public TypeInfo getSchemaTypeInfo() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public boolean isId() {
            return false;
        }
    }

    static class ElementXobj
    extends NamedNodeXobj
    implements Element {
        private ElementAttributes _attributes;

        ElementXobj(Locale l, QName name) {
            super(l, 2, 1);
            this._name = name;
        }

        @Override
        Xobj newNode(Locale l) {
            return new ElementXobj(l, this._name);
        }

        @Override
        public NamedNodeMap getAttributes() {
            if (this._attributes == null) {
                this._attributes = new ElementAttributes(this);
            }
            return this._attributes;
        }

        @Override
        public String getAttribute(String name) {
            return DomImpl._element_getAttribute(this, name);
        }

        @Override
        public Attr getAttributeNode(String name) {
            return DomImpl._element_getAttributeNode(this, name);
        }

        @Override
        public Attr getAttributeNodeNS(String namespaceURI, String localName) {
            return DomImpl._element_getAttributeNodeNS(this, namespaceURI, localName);
        }

        @Override
        public String getAttributeNS(String namespaceURI, String localName) {
            return DomImpl._element_getAttributeNS(this, namespaceURI, localName);
        }

        @Override
        public NodeList getElementsByTagName(String name) {
            return DomImpl._element_getElementsByTagName(this, name);
        }

        @Override
        public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
            return DomImpl._element_getElementsByTagNameNS(this, namespaceURI, localName);
        }

        @Override
        public String getTagName() {
            return DomImpl._element_getTagName(this);
        }

        @Override
        public boolean hasAttribute(String name) {
            return DomImpl._element_hasAttribute(this, name);
        }

        @Override
        public boolean hasAttributeNS(String namespaceURI, String localName) {
            return DomImpl._element_hasAttributeNS(this, namespaceURI, localName);
        }

        @Override
        public void removeAttribute(String name) {
            DomImpl._element_removeAttribute(this, name);
        }

        @Override
        public Attr removeAttributeNode(Attr oldAttr) {
            return DomImpl._element_removeAttributeNode(this, oldAttr);
        }

        @Override
        public void removeAttributeNS(String namespaceURI, String localName) {
            DomImpl._element_removeAttributeNS(this, namespaceURI, localName);
        }

        @Override
        public void setAttribute(String name, String value) {
            DomImpl._element_setAttribute(this, name, value);
        }

        @Override
        public Attr setAttributeNode(Attr newAttr) {
            return DomImpl._element_setAttributeNode(this, newAttr);
        }

        @Override
        public Attr setAttributeNodeNS(Attr newAttr) {
            return DomImpl._element_setAttributeNodeNS(this, newAttr);
        }

        @Override
        public void setAttributeNS(String namespaceURI, String qualifiedName, String value) {
            DomImpl._element_setAttributeNS(this, namespaceURI, qualifiedName, value);
        }

        @Override
        public TypeInfo getSchemaTypeInfo() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public void setIdAttribute(String name, boolean isId) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public void setIdAttributeNS(String namespaceURI, String localName, boolean isId) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public void setIdAttributeNode(Attr idAttr, boolean isId) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }
    }

    static abstract class NamedNodeXobj
    extends NodeXobj {
        boolean _canHavePrefixUri = true;

        NamedNodeXobj(Locale l, int kind, int domType) {
            super(l, kind, domType);
        }

        @Override
        public boolean nodeCanHavePrefixUri() {
            return this._canHavePrefixUri;
        }
    }

    static final class ElementAttributes
    implements NamedNodeMap {
        private ElementXobj _elementXobj;

        ElementAttributes(ElementXobj elementXobj) {
            this._elementXobj = elementXobj;
        }

        @Override
        public int getLength() {
            return DomImpl._attributes_getLength(this._elementXobj);
        }

        @Override
        public org.w3c.dom.Node getNamedItem(String name) {
            return DomImpl._attributes_getNamedItem(this._elementXobj, name);
        }

        @Override
        public org.w3c.dom.Node getNamedItemNS(String namespaceURI, String localName) {
            return DomImpl._attributes_getNamedItemNS(this._elementXobj, namespaceURI, localName);
        }

        @Override
        public org.w3c.dom.Node item(int index) {
            return DomImpl._attributes_item(this._elementXobj, index);
        }

        @Override
        public org.w3c.dom.Node removeNamedItem(String name) {
            return DomImpl._attributes_removeNamedItem(this._elementXobj, name);
        }

        @Override
        public org.w3c.dom.Node removeNamedItemNS(String namespaceURI, String localName) {
            return DomImpl._attributes_removeNamedItemNS(this._elementXobj, namespaceURI, localName);
        }

        @Override
        public org.w3c.dom.Node setNamedItem(org.w3c.dom.Node arg) {
            return DomImpl._attributes_setNamedItem(this._elementXobj, arg);
        }

        @Override
        public org.w3c.dom.Node setNamedItemNS(org.w3c.dom.Node arg) {
            return DomImpl._attributes_setNamedItemNS(this._elementXobj, arg);
        }
    }

    static class DocumentFragXobj
    extends NodeXobj
    implements DocumentFragment {
        DocumentFragXobj(Locale l) {
            super(l, 1, 11);
        }

        @Override
        Xobj newNode(Locale l) {
            return new DocumentFragXobj(l);
        }
    }

    static class DocumentXobj
    extends NodeXobj
    implements Document {
        private Hashtable _idToElement;

        DocumentXobj(Locale l) {
            super(l, 1, 9);
        }

        @Override
        Xobj newNode(Locale l) {
            return new DocumentXobj(l);
        }

        @Override
        public Attr createAttribute(String name) {
            return DomImpl._document_createAttribute(this, name);
        }

        @Override
        public Attr createAttributeNS(String namespaceURI, String qualifiedName) {
            return DomImpl._document_createAttributeNS(this, namespaceURI, qualifiedName);
        }

        @Override
        public CDATASection createCDATASection(String data) {
            return DomImpl._document_createCDATASection(this, data);
        }

        @Override
        public Comment createComment(String data) {
            return DomImpl._document_createComment(this, data);
        }

        @Override
        public DocumentFragment createDocumentFragment() {
            return DomImpl._document_createDocumentFragment(this);
        }

        @Override
        public Element createElement(String tagName) {
            return DomImpl._document_createElement(this, tagName);
        }

        @Override
        public Element createElementNS(String namespaceURI, String qualifiedName) {
            return DomImpl._document_createElementNS(this, namespaceURI, qualifiedName);
        }

        @Override
        public EntityReference createEntityReference(String name) {
            return DomImpl._document_createEntityReference(this, name);
        }

        @Override
        public ProcessingInstruction createProcessingInstruction(String target, String data) {
            return DomImpl._document_createProcessingInstruction(this, target, data);
        }

        @Override
        public Text createTextNode(String data) {
            return DomImpl._document_createTextNode(this, data);
        }

        @Override
        public DocumentType getDoctype() {
            return DomImpl._document_getDoctype(this);
        }

        @Override
        public Element getDocumentElement() {
            return DomImpl._document_getDocumentElement(this);
        }

        @Override
        public Element getElementById(String elementId) {
            if (this._idToElement == null) {
                return null;
            }
            Xobj o = (Xobj)this._idToElement.get(elementId);
            if (o == null) {
                return null;
            }
            if (!this.isInSameTree(o)) {
                this._idToElement.remove(elementId);
            }
            return (Element)((Object)o);
        }

        @Override
        public NodeList getElementsByTagName(String tagname) {
            return DomImpl._document_getElementsByTagName(this, tagname);
        }

        @Override
        public NodeList getElementsByTagNameNS(String namespaceURI, String localName) {
            return DomImpl._document_getElementsByTagNameNS(this, namespaceURI, localName);
        }

        @Override
        public DOMImplementation getImplementation() {
            return DomImpl._document_getImplementation(this);
        }

        @Override
        public org.w3c.dom.Node importNode(org.w3c.dom.Node importedNode, boolean deep) {
            return DomImpl._document_importNode(this, importedNode, deep);
        }

        @Override
        public org.w3c.dom.Node adoptNode(org.w3c.dom.Node source) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public String getDocumentURI() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public DOMConfiguration getDomConfig() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public String getInputEncoding() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public boolean getStrictErrorChecking() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public String getXmlEncoding() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public boolean getXmlStandalone() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public String getXmlVersion() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public void normalizeDocument() {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public org.w3c.dom.Node renameNode(org.w3c.dom.Node n, String namespaceURI, String qualifiedName) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public void setDocumentURI(String documentURI) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public void setStrictErrorChecking(boolean strictErrorChecking) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public void setXmlStandalone(boolean xmlStandalone) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        @Override
        public void setXmlVersion(String xmlVersion) {
            throw new RuntimeException("DOM Level 3 Not implemented");
        }

        protected void addIdElement(String idVal, DomImpl.Dom e) {
            if (this._idToElement == null) {
                this._idToElement = new Hashtable();
            }
            this._idToElement.put(idVal, e);
        }

        void removeIdElement(String idVal) {
            if (this._idToElement != null) {
                this._idToElement.remove(idVal);
            }
        }
    }

    static abstract class NodeXobj
    extends Xobj
    implements DomImpl.Dom,
    org.w3c.dom.Node,
    NodeList {
        NodeXobj(Locale l, int kind, int domType) {
            super(l, kind, domType);
        }

        @Override
        DomImpl.Dom getDom() {
            return this;
        }

        @Override
        public int getLength() {
            return DomImpl._childNodes_getLength(this);
        }

        @Override
        public org.w3c.dom.Node item(int i) {
            return DomImpl._childNodes_item(this, i);
        }

        @Override
        public org.w3c.dom.Node appendChild(org.w3c.dom.Node newChild) {
            return DomImpl._node_appendChild(this, newChild);
        }

        @Override
        public org.w3c.dom.Node cloneNode(boolean deep) {
            return DomImpl._node_cloneNode(this, deep);
        }

        @Override
        public NamedNodeMap getAttributes() {
            return null;
        }

        @Override
        public NodeList getChildNodes() {
            return this;
        }

        @Override
        public org.w3c.dom.Node getParentNode() {
            return DomImpl._node_getParentNode(this);
        }

        @Override
        public org.w3c.dom.Node removeChild(org.w3c.dom.Node oldChild) {
            return DomImpl._node_removeChild(this, oldChild);
        }

        @Override
        public org.w3c.dom.Node getFirstChild() {
            return DomImpl._node_getFirstChild(this);
        }

        @Override
        public org.w3c.dom.Node getLastChild() {
            return DomImpl._node_getLastChild(this);
        }

        @Override
        public String getLocalName() {
            return DomImpl._node_getLocalName(this);
        }

        @Override
        public String getNamespaceURI() {
            return DomImpl._node_getNamespaceURI(this);
        }

        @Override
        public org.w3c.dom.Node getNextSibling() {
            return DomImpl._node_getNextSibling(this);
        }

        @Override
        public String getNodeName() {
            return DomImpl._node_getNodeName(this);
        }

        @Override
        public short getNodeType() {
            return DomImpl._node_getNodeType(this);
        }

        @Override
        public String getNodeValue() {
            return DomImpl._node_getNodeValue(this);
        }

        @Override
        public Document getOwnerDocument() {
            return DomImpl._node_getOwnerDocument(this);
        }

        @Override
        public String getPrefix() {
            return DomImpl._node_getPrefix(this);
        }

        @Override
        public org.w3c.dom.Node getPreviousSibling() {
            return DomImpl._node_getPreviousSibling(this);
        }

        @Override
        public boolean hasAttributes() {
            return DomImpl._node_hasAttributes(this);
        }

        @Override
        public boolean hasChildNodes() {
            return DomImpl._node_hasChildNodes(this);
        }

        @Override
        public org.w3c.dom.Node insertBefore(org.w3c.dom.Node newChild, org.w3c.dom.Node refChild) {
            return DomImpl._node_insertBefore(this, newChild, refChild);
        }

        @Override
        public boolean isSupported(String feature, String version) {
            return DomImpl._node_isSupported(this, feature, version);
        }

        @Override
        public void normalize() {
            DomImpl._node_normalize(this);
        }

        @Override
        public org.w3c.dom.Node replaceChild(org.w3c.dom.Node newChild, org.w3c.dom.Node oldChild) {
            return DomImpl._node_replaceChild(this, newChild, oldChild);
        }

        @Override
        public void setNodeValue(String nodeValue) {
            DomImpl._node_setNodeValue(this, nodeValue);
        }

        @Override
        public void setPrefix(String prefix) {
            DomImpl._node_setPrefix(this, prefix);
        }

        @Override
        public boolean nodeCanHavePrefixUri() {
            return false;
        }

        @Override
        public Object getUserData(String key) {
            return DomImpl._node_getUserData(this, key);
        }

        @Override
        public Object setUserData(String key, Object data, UserDataHandler handler) {
            return DomImpl._node_setUserData(this, key, data, handler);
        }

        @Override
        public Object getFeature(String feature, String version) {
            return DomImpl._node_getFeature(this, feature, version);
        }

        @Override
        public boolean isEqualNode(org.w3c.dom.Node arg) {
            return DomImpl._node_isEqualNode(this, arg);
        }

        @Override
        public boolean isSameNode(org.w3c.dom.Node arg) {
            return DomImpl._node_isSameNode(this, arg);
        }

        @Override
        public String lookupNamespaceURI(String prefix) {
            return DomImpl._node_lookupNamespaceURI(this, prefix);
        }

        @Override
        public String lookupPrefix(String namespaceURI) {
            return DomImpl._node_lookupPrefix(this, namespaceURI);
        }

        @Override
        public boolean isDefaultNamespace(String namespaceURI) {
            return DomImpl._node_isDefaultNamespace(this, namespaceURI);
        }

        @Override
        public void setTextContent(String textContent) {
            DomImpl._node_setTextContent(this, textContent);
        }

        @Override
        public String getTextContent() {
            return DomImpl._node_getTextContent(this);
        }

        @Override
        public short compareDocumentPosition(org.w3c.dom.Node other) {
            return DomImpl._node_compareDocumentPosition(this, other);
        }

        @Override
        public String getBaseURI() {
            return DomImpl._node_getBaseURI(this);
        }
    }
}

