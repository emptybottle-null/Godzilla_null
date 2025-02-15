/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.values;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.DelegateXmlObject;
import org.apache.xmlbeans.GDate;
import org.apache.xmlbeans.GDateSpecification;
import org.apache.xmlbeans.GDuration;
import org.apache.xmlbeans.GDurationSpecification;
import org.apache.xmlbeans.QNameSet;
import org.apache.xmlbeans.SchemaAttributeModel;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaLocalAttribute;
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.StringEnumAbstractBase;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlDocumentProperties;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlRuntimeException;
import org.apache.xmlbeans.impl.common.GlobalLock;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.common.XmlErrorWatcher;
import org.apache.xmlbeans.impl.common.XmlLocale;
import org.apache.xmlbeans.impl.common.XmlWhitespace;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.apache.xmlbeans.impl.schema.SchemaTypeVisitorImpl;
import org.apache.xmlbeans.impl.validator.Validator;
import org.apache.xmlbeans.impl.values.NamespaceContext;
import org.apache.xmlbeans.impl.values.NamespaceManager;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.TypeStoreUser;
import org.apache.xmlbeans.impl.values.TypeStoreVisitor;
import org.apache.xmlbeans.impl.values.XmlValueDisconnectedException;
import org.apache.xmlbeans.impl.values.XmlValueNotNillableException;
import org.apache.xmlbeans.impl.values.XmlValueNotSupportedException;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.ext.LexicalHandler;

public abstract class XmlObjectBase
implements TypeStoreUser,
Serializable,
XmlObject,
SimpleValue {
    public static final short MAJOR_VERSION_NUMBER = 1;
    public static final short MINOR_VERSION_NUMBER = 1;
    public static final short KIND_SETTERHELPER_SINGLETON = 1;
    public static final short KIND_SETTERHELPER_ARRAYITEM = 2;
    public static final ValidationContext _voorVc = new ValueOutOfRangeValidationContext();
    private int _flags = 65;
    private Object _textsource;
    private static final int FLAG_NILLABLE = 1;
    private static final int FLAG_HASDEFAULT = 2;
    private static final int FLAG_FIXED = 4;
    private static final int FLAG_ATTRIBUTE = 8;
    private static final int FLAG_STORE = 16;
    private static final int FLAG_VALUE_DATED = 32;
    private static final int FLAG_NIL = 64;
    private static final int FLAG_NIL_DATED = 128;
    private static final int FLAG_ISDEFAULT = 256;
    private static final int FLAG_ELEMENT_DATED = 512;
    private static final int FLAG_SETTINGDEFAULT = 1024;
    private static final int FLAG_ORPHANED = 2048;
    private static final int FLAG_IMMUTABLE = 4096;
    private static final int FLAG_COMPLEXTYPE = 8192;
    private static final int FLAG_COMPLEXCONTENT = 16384;
    private static final int FLAG_NOT_VARIABLE = 32768;
    private static final int FLAG_VALIDATE_ON_SET = 65536;
    private static final int FLAGS_DATED = 672;
    private static final int FLAGS_ELEMENT = 7;
    private static final BigInteger _max = BigInteger.valueOf(Long.MAX_VALUE);
    private static final BigInteger _min = BigInteger.valueOf(Long.MIN_VALUE);
    private static final XmlOptions _toStringOptions = XmlObjectBase.buildInnerPrettyOptions();
    private static final XmlObject[] EMPTY_RESULT = new XmlObject[0];

    @Override
    public final Object monitor() {
        if (this.has_store()) {
            return this.get_store().get_locale();
        }
        return this;
    }

    private static XmlObjectBase underlying(XmlObject obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof XmlObjectBase) {
            return (XmlObjectBase)obj;
        }
        while (obj instanceof DelegateXmlObject) {
            obj = ((DelegateXmlObject)((Object)obj)).underlyingXmlObject();
        }
        if (obj instanceof XmlObjectBase) {
            return (XmlObjectBase)obj;
        }
        throw new IllegalStateException("Non-native implementations of XmlObject should extend FilterXmlObject or implement DelegateXmlObject");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final XmlObject copy() {
        if (this.preCheck()) {
            return this._copy();
        }
        Object object = this.monitor();
        synchronized (object) {
            return this._copy();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final XmlObject copy(XmlOptions options) {
        if (this.preCheck()) {
            return this._copy(options);
        }
        Object object = this.monitor();
        synchronized (object) {
            return this._copy(options);
        }
    }

    private boolean preCheck() {
        if (this.has_store()) {
            return this.get_store().get_locale().noSync();
        }
        return false;
    }

    public final XmlObject _copy() {
        return this._copy(null);
    }

    public final XmlObject _copy(XmlOptions xmlOptions) {
        if (this.isImmutable()) {
            return this;
        }
        this.check_orphaned();
        SchemaTypeLoader stl = this.get_store().get_schematypeloader();
        XmlObject result = (XmlObject)((Object)this.get_store().copy(stl, this.schemaType(), xmlOptions));
        return result;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlDocumentProperties documentProperties() {
        XmlCursor cur = this.newCursorForce();
        try {
            XmlDocumentProperties xmlDocumentProperties = cur.documentProperties();
            return xmlDocumentProperties;
        } finally {
            cur.dispose();
        }
    }

    @Override
    public XMLInputStream newXMLInputStream() {
        return this.newXMLInputStream(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XMLInputStream newXMLInputStream(XmlOptions options) {
        XmlCursor cur = this.newCursorForce();
        try {
            XMLInputStream xMLInputStream = cur.newXMLInputStream(XmlObjectBase.makeInnerOptions(options));
            return xMLInputStream;
        } finally {
            cur.dispose();
        }
    }

    @Override
    public XMLStreamReader newXMLStreamReader() {
        return this.newXMLStreamReader(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XMLStreamReader newXMLStreamReader(XmlOptions options) {
        XmlCursor cur = this.newCursorForce();
        try {
            XMLStreamReader xMLStreamReader = cur.newXMLStreamReader(XmlObjectBase.makeInnerOptions(options));
            return xMLStreamReader;
        } finally {
            cur.dispose();
        }
    }

    @Override
    public InputStream newInputStream() {
        return this.newInputStream(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public InputStream newInputStream(XmlOptions options) {
        XmlCursor cur = this.newCursorForce();
        try {
            InputStream inputStream = cur.newInputStream(XmlObjectBase.makeInnerOptions(options));
            return inputStream;
        } finally {
            cur.dispose();
        }
    }

    @Override
    public Reader newReader() {
        return this.newReader(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Reader newReader(XmlOptions options) {
        XmlCursor cur = this.newCursorForce();
        try {
            Reader reader = cur.newReader(XmlObjectBase.makeInnerOptions(options));
            return reader;
        } finally {
            cur.dispose();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Node getDomNode() {
        XmlCursor cur = this.newCursorForce();
        try {
            Node node = cur.getDomNode();
            return node;
        } finally {
            cur.dispose();
        }
    }

    @Override
    public Node newDomNode() {
        return this.newDomNode(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Node newDomNode(XmlOptions options) {
        XmlCursor cur = this.newCursorForce();
        try {
            Node node = cur.newDomNode(XmlObjectBase.makeInnerOptions(options));
            return node;
        } finally {
            cur.dispose();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void save(ContentHandler ch, LexicalHandler lh, XmlOptions options) throws SAXException {
        XmlCursor cur = this.newCursorForce();
        try {
            cur.save(ch, lh, XmlObjectBase.makeInnerOptions(options));
        } finally {
            cur.dispose();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void save(File file, XmlOptions options) throws IOException {
        XmlCursor cur = this.newCursorForce();
        try {
            cur.save(file, XmlObjectBase.makeInnerOptions(options));
        } finally {
            cur.dispose();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void save(OutputStream os, XmlOptions options) throws IOException {
        XmlCursor cur = this.newCursorForce();
        try {
            cur.save(os, XmlObjectBase.makeInnerOptions(options));
        } finally {
            cur.dispose();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void save(Writer w, XmlOptions options) throws IOException {
        XmlCursor cur = this.newCursorForce();
        try {
            cur.save(w, XmlObjectBase.makeInnerOptions(options));
        } finally {
            cur.dispose();
        }
    }

    @Override
    public void save(ContentHandler ch, LexicalHandler lh) throws SAXException {
        this.save(ch, lh, null);
    }

    @Override
    public void save(File file) throws IOException {
        this.save(file, null);
    }

    @Override
    public void save(OutputStream os) throws IOException {
        this.save(os, null);
    }

    @Override
    public void save(Writer w) throws IOException {
        this.save(w, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void dump() {
        XmlCursor cur = this.newCursorForce();
        try {
            cur.dump();
        } finally {
            cur.dispose();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public XmlCursor newCursorForce() {
        Object object = this.monitor();
        synchronized (object) {
            return this.ensureStore().newCursor();
        }
    }

    private XmlObject ensureStore() {
        if ((this._flags & 0x10) != 0) {
            return this;
        }
        this.check_dated();
        String value = (this._flags & 0x40) != 0 ? "" : this.compute_text(this.has_store() ? this.get_store() : null);
        XmlOptions options = new XmlOptions().setDocumentType(this.schemaType());
        XmlObject x = XmlObject.Factory.newInstance(options);
        XmlCursor c = x.newCursor();
        c.toNextToken();
        c.insertChars(value);
        return x;
    }

    private static XmlOptions makeInnerOptions(XmlOptions options) {
        XmlOptions innerOptions = new XmlOptions(options);
        innerOptions.put("SAVE_INNER");
        return innerOptions;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlCursor newCursor() {
        if ((this._flags & 0x10) == 0) {
            throw new IllegalStateException("XML Value Objects cannot create cursors");
        }
        this.check_orphaned();
        XmlLocale l = this.getXmlLocale();
        if (l.noSync()) {
            l.enter();
            try {
                XmlCursor xmlCursor = this.get_store().new_cursor();
                return xmlCursor;
            } finally {
                l.exit();
            }
        }
        XmlLocale xmlLocale = l;
        synchronized (xmlLocale) {
            XmlCursor xmlCursor;
            l.enter();
            try {
                xmlCursor = this.get_store().new_cursor();
                l.exit();
            } catch (Throwable throwable) {
                l.exit();
                throw throwable;
            }
            return xmlCursor;
        }
    }

    @Override
    public abstract SchemaType schemaType();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public SchemaType instanceType() {
        Object object = this.monitor();
        synchronized (object) {
            return this.isNil() ? null : this.schemaType();
        }
    }

    private SchemaField schemaField() {
        SchemaType st = this.schemaType();
        SchemaField field = st.getContainerField();
        if (field == null) {
            field = this.get_store().get_schema_field();
        }
        return field;
    }

    @Override
    public boolean validate() {
        return this.validate(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean validate(XmlOptions options) {
        if ((this._flags & 0x10) == 0) {
            if ((this._flags & 0x1000) != 0) {
                return this.validate_immutable(options);
            }
            throw new IllegalStateException("XML objects with no underlying store cannot be validated");
        }
        Object object = this.monitor();
        synchronized (object) {
            if ((this._flags & 0x800) != 0) {
                throw new XmlValueDisconnectedException();
            }
            SchemaField field = this.schemaField();
            SchemaType type = this.schemaType();
            TypeStore typeStore = this.get_store();
            Validator validator = new Validator(type, field, typeStore.get_schematypeloader(), options, null);
            typeStore.validate(validator);
            return validator.isValid();
        }
    }

    private boolean validate_immutable(XmlOptions options) {
        String text;
        Collection errorListener = options == null ? null : (Collection)options.get("ERROR_LISTENER");
        XmlErrorWatcher watcher = new XmlErrorWatcher(errorListener);
        if (!(this.schemaType().isSimpleType() || options != null && options.hasOption("VALIDATE_TEXT_ONLY"))) {
            SchemaProperty[] properties = this.schemaType().getProperties();
            for (int i = 0; i < properties.length; ++i) {
                if (properties[i].getMinOccurs().signum() <= 0) continue;
                if (properties[i].isAttribute()) {
                    watcher.add(XmlError.forObject("cvc-complex-type.4", new Object[]{QNameHelper.pretty(properties[i].getName())}, (XmlObject)this));
                    continue;
                }
                watcher.add(XmlError.forObject("cvc-complex-type.2.4c", new Object[]{properties[i].getMinOccurs(), QNameHelper.pretty(properties[i].getName())}, (XmlObject)this));
            }
            if (this.schemaType().getContentType() != 2) {
                return !watcher.hasError();
            }
        }
        if ((text = (String)this._textsource) == null) {
            text = "";
        }
        this.validate_simpleval(text, new ImmutableValueValidationContext(watcher, this));
        return !watcher.hasError();
    }

    protected void validate_simpleval(String lexical, ValidationContext ctx) {
    }

    private static XmlObject[] _typedArray(XmlObject[] input) {
        if (input.length == 0) {
            return input;
        }
        SchemaType commonType = input[0].schemaType();
        if (commonType.equals(XmlObject.type) || commonType.isNoType()) {
            return input;
        }
        for (int i = 1; i < input.length; ++i) {
            if (input[i].schemaType().isNoType()) {
                return input;
            }
            if (!(commonType = commonType.getCommonBaseType(input[i].schemaType())).equals(XmlObject.type)) continue;
            return input;
        }
        Class desiredClass = commonType.getJavaClass();
        while (desiredClass == null) {
            if (XmlObject.type.equals(commonType = commonType.getBaseType())) {
                return input;
            }
            desiredClass = commonType.getJavaClass();
        }
        XmlObject[] result = (XmlObject[])Array.newInstance(desiredClass, input.length);
        System.arraycopy(input, 0, result, 0, input.length);
        return result;
    }

    @Override
    public XmlObject[] selectPath(String path) {
        return this.selectPath(path, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlObject[] selectPath(String path, XmlOptions options) {
        XmlObject[] selections;
        XmlCursor c = this.newCursor();
        if (c == null) {
            throw new XmlValueDisconnectedException();
        }
        try {
            c.selectPath(path, options);
            if (!c.hasNextSelection()) {
                selections = EMPTY_RESULT;
            } else {
                selections = new XmlObject[c.getSelectionCount()];
                int i = 0;
                while (c.toNextSelection()) {
                    selections[i] = c.getObject();
                    if (!(selections[i] != null || c.toParent() && (selections[i] = c.getObject()) != null)) {
                        throw new XmlRuntimeException("Path must select only elements and attributes");
                    }
                    ++i;
                }
            }
        } finally {
            c.dispose();
        }
        return XmlObjectBase._typedArray(selections);
    }

    @Override
    public XmlObject[] execQuery(String path) {
        return this.execQuery(path, null);
    }

    @Override
    public XmlObject[] execQuery(String queryExpr, XmlOptions options) {
        Object object = this.monitor();
        synchronized (object) {
            TypeStore typeStore = this.get_store();
            if (typeStore == null) {
                throw new XmlRuntimeException("Cannot do XQuery on XML Value Objects");
            }
            try {
                return XmlObjectBase._typedArray(typeStore.exec_query(queryExpr, options));
            } catch (XmlException e) {
                throw new XmlRuntimeException(e);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlObject changeType(SchemaType type) {
        if (type == null) {
            throw new IllegalArgumentException("Invalid type (null)");
        }
        if ((this._flags & 0x10) == 0) {
            throw new IllegalStateException("XML Value Objects cannot have thier type changed");
        }
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return (XmlObject)((Object)this.get_store().change_type(type));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlObject substitute(QName name, SchemaType type) {
        if (name == null) {
            throw new IllegalArgumentException("Invalid name (null)");
        }
        if (type == null) {
            throw new IllegalArgumentException("Invalid type (null)");
        }
        if ((this._flags & 0x10) == 0) {
            throw new IllegalStateException("XML Value Objects cannot be used with substitution");
        }
        Object object = this.monitor();
        synchronized (object) {
            this.check_orphaned();
            return (XmlObject)((Object)this.get_store().substitute(name, type));
        }
    }

    protected XmlObjectBase() {
    }

    public void init_flags(SchemaProperty prop) {
        if (prop == null) {
            return;
        }
        if (prop.hasDefault() == 1 || prop.hasFixed() == 1 || prop.hasNillable() == 1) {
            return;
        }
        this._flags &= 0xFFFFFFF8;
        this._flags |= (prop.hasDefault() == 0 ? 0 : 2) | (prop.hasFixed() == 0 ? 0 : 4) | (prop.hasNillable() == 0 ? 0 : 1) | 0x8000;
    }

    protected void initComplexType(boolean complexType, boolean complexContent) {
        this._flags |= (complexType ? 8192 : 0) | (complexContent ? 16384 : 0);
    }

    protected boolean _isComplexType() {
        return (this._flags & 0x2000) != 0;
    }

    protected boolean _isComplexContent() {
        return (this._flags & 0x4000) != 0;
    }

    public void setValidateOnSet() {
        this._flags |= 0x10000;
    }

    protected boolean _validateOnSet() {
        return (this._flags & 0x10000) != 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final boolean isNil() {
        Object object = this.monitor();
        synchronized (object) {
            this.check_dated();
            return (this._flags & 0x40) != 0;
        }
    }

    public final boolean isFixed() {
        this.check_element_dated();
        return (this._flags & 4) != 0;
    }

    public final boolean isNillable() {
        this.check_element_dated();
        return (this._flags & 1) != 0;
    }

    public final boolean isDefaultable() {
        this.check_element_dated();
        return (this._flags & 2) != 0;
    }

    public final boolean isDefault() {
        this.check_dated();
        return (this._flags & 0x100) != 0;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setNil() {
        Object object = this.monitor();
        synchronized (object) {
            this.set_prepare();
            if ((this._flags & 1) == 0 && (this._flags & 0x10000) != 0) {
                throw new XmlValueNotNillableException();
            }
            this.set_nil();
            this._flags |= 0x40;
            if ((this._flags & 0x10) != 0) {
                this.get_store().invalidate_text();
                this._flags &= 0xFFFFFD5F;
                this.get_store().invalidate_nil();
            } else {
                this._textsource = null;
            }
        }
    }

    protected int elementFlags() {
        this.check_element_dated();
        return this._flags & 7;
    }

    public void setImmutable() {
        if ((this._flags & 0x1010) != 0) {
            throw new IllegalStateException();
        }
        this._flags |= 0x1000;
    }

    @Override
    public boolean isImmutable() {
        return (this._flags & 0x1000) != 0;
    }

    @Override
    public final void attach_store(TypeStore store) {
        this._textsource = store;
        if ((this._flags & 0x1000) != 0) {
            throw new IllegalStateException();
        }
        this._flags |= 0x2B0;
        if (store.is_attribute()) {
            this._flags |= 8;
        }
        if (store.validate_on_set()) {
            this._flags |= 0x10000;
        }
    }

    @Override
    public final void invalidate_value() {
        assert ((this._flags & 0x10) != 0);
        this._flags |= 0x20;
    }

    @Override
    public final boolean uses_invalidate_value() {
        SchemaType type = this.schemaType();
        return type.isSimpleType() || type.getContentType() == 2;
    }

    @Override
    public final void invalidate_nilvalue() {
        assert ((this._flags & 0x10) != 0);
        this._flags |= 0xA0;
    }

    @Override
    public final void invalidate_element_order() {
        assert ((this._flags & 0x10) != 0);
        this._flags |= 0x2A0;
    }

    @Override
    public final TypeStore get_store() {
        assert ((this._flags & 0x10) != 0);
        return (TypeStore)this._textsource;
    }

    public final XmlLocale getXmlLocale() {
        return this.get_store().get_locale();
    }

    protected final boolean has_store() {
        return (this._flags & 0x10) != 0;
    }

    @Override
    public final String build_text(NamespaceManager nsm) {
        assert ((this._flags & 0x10) != 0);
        assert ((this._flags & 0x20) == 0);
        if ((this._flags & 0x140) != 0) {
            return "";
        }
        return this.compute_text(nsm == null ? (this.has_store() ? this.get_store() : null) : nsm);
    }

    @Override
    public boolean build_nil() {
        assert ((this._flags & 0x10) != 0);
        assert ((this._flags & 0x20) == 0);
        return (this._flags & 0x40) != 0;
    }

    @Override
    public void validate_now() {
        this.check_dated();
    }

    @Override
    public void disconnect_store() {
        assert ((this._flags & 0x10) != 0);
        this._flags |= 0xAA0;
    }

    @Override
    public TypeStoreUser create_element_user(QName eltName, QName xsiType) {
        return (TypeStoreUser)((Object)((SchemaTypeImpl)this.schemaType()).createElementType(eltName, xsiType, this.get_store().get_schematypeloader()));
    }

    @Override
    public TypeStoreUser create_attribute_user(QName attrName) {
        return (TypeStoreUser)((Object)((SchemaTypeImpl)this.schemaType()).createAttributeType(attrName, this.get_store().get_schematypeloader()));
    }

    @Override
    public SchemaType get_schema_type() {
        return this.schemaType();
    }

    @Override
    public SchemaType get_element_type(QName eltName, QName xsiType) {
        return this.schemaType().getElementType(eltName, xsiType, this.get_store().get_schematypeloader());
    }

    @Override
    public SchemaType get_attribute_type(QName attrName) {
        return this.schemaType().getAttributeType(attrName, this.get_store().get_schematypeloader());
    }

    @Override
    public String get_default_element_text(QName eltName) {
        assert (this._isComplexContent());
        if (!this._isComplexContent()) {
            throw new IllegalStateException();
        }
        SchemaProperty prop = this.schemaType().getElementProperty(eltName);
        if (prop == null) {
            return "";
        }
        return prop.getDefaultText();
    }

    @Override
    public String get_default_attribute_text(QName attrName) {
        assert (this._isComplexType());
        if (!this._isComplexType()) {
            throw new IllegalStateException();
        }
        SchemaProperty prop = this.schemaType().getAttributeProperty(attrName);
        if (prop == null) {
            return "";
        }
        return prop.getDefaultText();
    }

    @Override
    public int get_elementflags(QName eltName) {
        if (!this._isComplexContent()) {
            return 0;
        }
        SchemaProperty prop = this.schemaType().getElementProperty(eltName);
        if (prop == null) {
            return 0;
        }
        if (prop.hasDefault() == 1 || prop.hasFixed() == 1 || prop.hasNillable() == 1) {
            return -1;
        }
        return (prop.hasDefault() == 0 ? 0 : 2) | (prop.hasFixed() == 0 ? 0 : 4) | (prop.hasNillable() == 0 ? 0 : 1);
    }

    @Override
    public int get_attributeflags(QName attrName) {
        if (!this._isComplexType()) {
            return 0;
        }
        SchemaProperty prop = this.schemaType().getAttributeProperty(attrName);
        if (prop == null) {
            return 0;
        }
        return (prop.hasDefault() == 0 ? 0 : 2) | (prop.hasFixed() == 0 ? 0 : 4);
    }

    @Override
    public boolean is_child_element_order_sensitive() {
        if (!this._isComplexType()) {
            return false;
        }
        return this.schemaType().isOrderSensitive();
    }

    @Override
    public final QNameSet get_element_ending_delimiters(QName eltname) {
        SchemaProperty prop = this.schemaType().getElementProperty(eltname);
        if (prop == null) {
            return null;
        }
        return prop.getJavaSetterDelimiter();
    }

    @Override
    public TypeStoreVisitor new_visitor() {
        if (!this._isComplexContent()) {
            return null;
        }
        return new SchemaTypeVisitorImpl(this.schemaType().getContentModel());
    }

    @Override
    public SchemaField get_attribute_field(QName attrName) {
        SchemaAttributeModel model = this.schemaType().getAttributeModel();
        if (model == null) {
            return null;
        }
        return model.getAttribute(attrName);
    }

    protected void set_String(String v) {
        if ((this._flags & 0x1000) != 0) {
            throw new IllegalStateException();
        }
        boolean wasNilled = (this._flags & 0x40) != 0;
        String wscanon = this.apply_wscanon(v);
        this.update_from_wscanon_text(wscanon);
        if ((this._flags & 0x10) != 0) {
            this._flags &= 0xFFFFFFDF;
            if ((this._flags & 0x400) == 0) {
                this.get_store().store_text(v);
            }
            if (wasNilled) {
                this.get_store().invalidate_nil();
            }
        } else {
            this._textsource = v;
        }
    }

    protected void update_from_complex_content() {
        throw new XmlValueNotSupportedException("Complex content");
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private final void update_from_wscanon_text(String v) {
        if ((this._flags & 2) != 0 && (this._flags & 0x400) == 0 && (this._flags & 8) == 0 && v.equals("")) {
            String def = this.get_store().compute_default_text();
            if (def == null) {
                throw new XmlValueOutOfRangeException();
            }
            this._flags |= 0x400;
            try {
                this.setStringValue(def);
            } finally {
                this._flags &= 0xFFFFFBFF;
            }
            this._flags &= 0xFFFFFFBF;
            this._flags |= 0x100;
            return;
        }
        this.set_text(v);
        this._flags &= 0xFFFFFEBF;
    }

    protected boolean is_defaultable_ws(String v) {
        return true;
    }

    protected int get_wscanon_rule() {
        return 3;
    }

    private final String apply_wscanon(String v) {
        return XmlWhitespace.collapse(v, this.get_wscanon_rule());
    }

    private final void check_element_dated() {
        if ((this._flags & 0x200) != 0 && (this._flags & 0x8000) == 0) {
            if ((this._flags & 0x800) != 0) {
                throw new XmlValueDisconnectedException();
            }
            int eltflags = this.get_store().compute_flags();
            this._flags &= 0xFFFFFDF8;
            this._flags |= eltflags;
        }
        if ((this._flags & 0x8000) != 0) {
            this._flags &= 0xFFFFFDFF;
        }
    }

    protected final boolean is_orphaned() {
        return (this._flags & 0x800) != 0;
    }

    protected final void check_orphaned() {
        if (this.is_orphaned()) {
            throw new XmlValueDisconnectedException();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void check_dated() {
        if ((this._flags & 0x2A0) != 0) {
            if ((this._flags & 0x800) != 0) {
                throw new XmlValueDisconnectedException();
            }
            assert ((this._flags & 0x10) != 0);
            this.check_element_dated();
            if ((this._flags & 0x200) != 0) {
                int eltflags = this.get_store().compute_flags();
                this._flags &= 0xFFFFFDF8;
                this._flags |= eltflags;
            }
            boolean nilled = false;
            if ((this._flags & 0x80) != 0) {
                if (this.get_store().find_nil()) {
                    if ((this._flags & 1) == 0 && (this._flags & 0x10000) != 0) {
                        throw new XmlValueOutOfRangeException();
                    }
                    this.set_nil();
                    this._flags |= 0x40;
                    nilled = true;
                } else {
                    this._flags &= 0xFFFFFFBF;
                }
                this._flags &= 0xFFFFFF7F;
            }
            if (!nilled) {
                String text;
                if ((this._flags & 0x4000) != 0 || (text = this.get_wscanon_text()) == null) {
                    this.update_from_complex_content();
                } else {
                    NamespaceContext.push(new NamespaceContext(this.get_store()));
                    try {
                        this.update_from_wscanon_text(text);
                    } finally {
                        NamespaceContext.pop();
                    }
                }
            }
            this._flags &= 0xFFFFFFDF;
        }
    }

    private final void set_prepare() {
        this.check_element_dated();
        if ((this._flags & 0x1000) != 0) {
            throw new IllegalStateException();
        }
    }

    private final void set_commit() {
        boolean wasNilled = (this._flags & 0x40) != 0;
        this._flags &= 0xFFFFFEBF;
        if ((this._flags & 0x10) != 0) {
            this._flags &= 0xFFFFFD5F;
            this.get_store().invalidate_text();
            if (wasNilled) {
                this.get_store().invalidate_nil();
            }
        } else {
            this._textsource = null;
        }
    }

    public final String get_wscanon_text() {
        if ((this._flags & 0x10) == 0) {
            return this.apply_wscanon((String)this._textsource);
        }
        return this.get_store().fetch_text(this.get_wscanon_rule());
    }

    protected abstract void set_text(String var1);

    protected abstract void set_nil();

    protected abstract String compute_text(NamespaceManager var1);

    @Override
    public float getFloatValue() {
        BigDecimal bd = this.getBigDecimalValue();
        return bd == null ? 0.0f : bd.floatValue();
    }

    @Override
    public double getDoubleValue() {
        BigDecimal bd = this.getBigDecimalValue();
        return bd == null ? 0.0 : bd.doubleValue();
    }

    @Override
    public BigDecimal getBigDecimalValue() {
        throw new XmlValueNotSupportedException("exception.value.not.supported.s2j", new Object[]{this.getPrimitiveTypeName(), "numeric"});
    }

    @Override
    public BigInteger getBigIntegerValue() {
        BigDecimal bd = this.bigDecimalValue();
        return bd == null ? null : bd.toBigInteger();
    }

    @Override
    public byte getByteValue() {
        long l = this.getIntValue();
        if (l > 127L) {
            throw new XmlValueOutOfRangeException();
        }
        if (l < -128L) {
            throw new XmlValueOutOfRangeException();
        }
        return (byte)l;
    }

    @Override
    public short getShortValue() {
        long l = this.getIntValue();
        if (l > 32767L) {
            throw new XmlValueOutOfRangeException();
        }
        if (l < -32768L) {
            throw new XmlValueOutOfRangeException();
        }
        return (short)l;
    }

    @Override
    public int getIntValue() {
        long l = this.getLongValue();
        if (l > Integer.MAX_VALUE) {
            throw new XmlValueOutOfRangeException();
        }
        if (l < Integer.MIN_VALUE) {
            throw new XmlValueOutOfRangeException();
        }
        return (int)l;
    }

    @Override
    public long getLongValue() {
        BigInteger b = this.getBigIntegerValue();
        if (b == null) {
            return 0L;
        }
        if (b.compareTo(_max) >= 0) {
            throw new XmlValueOutOfRangeException();
        }
        if (b.compareTo(_min) <= 0) {
            throw new XmlValueOutOfRangeException();
        }
        return b.longValue();
    }

    static final XmlOptions buildInnerPrettyOptions() {
        XmlOptions options = new XmlOptions();
        options.put("SAVE_INNER");
        options.put("SAVE_PRETTY_PRINT");
        options.put("SAVE_AGGRESSIVE_NAMESPACES");
        options.put("SAVE_USE_DEFAULT_NAMESPACE");
        return options;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final String toString() {
        Object object = this.monitor();
        synchronized (object) {
            return this.ensureStore().xmlText(_toStringOptions);
        }
    }

    @Override
    public String xmlText() {
        return this.xmlText(null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String xmlText(XmlOptions options) {
        XmlCursor cur = this.newCursorForce();
        try {
            String string = cur.xmlText(XmlObjectBase.makeInnerOptions(options));
            return string;
        } finally {
            cur.dispose();
        }
    }

    @Override
    public StringEnumAbstractBase getEnumValue() {
        throw new XmlValueNotSupportedException("exception.value.not.supported.s2j", new Object[]{this.getPrimitiveTypeName(), "enum"});
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public String getStringValue() {
        if (this.isImmutable()) {
            if ((this._flags & 0x40) != 0) {
                return null;
            }
            return this.compute_text(null);
        }
        Object object = this.monitor();
        synchronized (object) {
            if (this._isComplexContent()) {
                return this.get_store().fetch_text(1);
            }
            this.check_dated();
            if ((this._flags & 0x40) != 0) {
                return null;
            }
            return this.compute_text(this.has_store() ? this.get_store() : null);
        }
    }

    @Override
    public String stringValue() {
        return this.getStringValue();
    }

    @Override
    public boolean booleanValue() {
        return this.getBooleanValue();
    }

    @Override
    public byte byteValue() {
        return this.getByteValue();
    }

    @Override
    public short shortValue() {
        return this.getShortValue();
    }

    @Override
    public int intValue() {
        return this.getIntValue();
    }

    @Override
    public long longValue() {
        return this.getLongValue();
    }

    @Override
    public BigInteger bigIntegerValue() {
        return this.getBigIntegerValue();
    }

    @Override
    public BigDecimal bigDecimalValue() {
        return this.getBigDecimalValue();
    }

    @Override
    public float floatValue() {
        return this.getFloatValue();
    }

    @Override
    public double doubleValue() {
        return this.getDoubleValue();
    }

    @Override
    public byte[] byteArrayValue() {
        return this.getByteArrayValue();
    }

    @Override
    public StringEnumAbstractBase enumValue() {
        return this.getEnumValue();
    }

    @Override
    public Calendar calendarValue() {
        return this.getCalendarValue();
    }

    @Override
    public Date dateValue() {
        return this.getDateValue();
    }

    @Override
    public GDate gDateValue() {
        return this.getGDateValue();
    }

    @Override
    public GDuration gDurationValue() {
        return this.getGDurationValue();
    }

    @Override
    public QName qNameValue() {
        return this.getQNameValue();
    }

    @Override
    public List xlistValue() {
        return this.xgetListValue();
    }

    @Override
    public List listValue() {
        return this.getListValue();
    }

    @Override
    public Object objectValue() {
        return this.getObjectValue();
    }

    @Override
    public void set(String obj) {
        this.setStringValue(obj);
    }

    @Override
    public void set(boolean v) {
        this.setBooleanValue(v);
    }

    @Override
    public void set(byte v) {
        this.setByteValue(v);
    }

    @Override
    public void set(short v) {
        this.setShortValue(v);
    }

    @Override
    public void set(int v) {
        this.setIntValue(v);
    }

    @Override
    public void set(long v) {
        this.setLongValue(v);
    }

    @Override
    public void set(BigInteger obj) {
        this.setBigIntegerValue(obj);
    }

    @Override
    public void set(BigDecimal obj) {
        this.setBigDecimalValue(obj);
    }

    @Override
    public void set(float v) {
        this.setFloatValue(v);
    }

    @Override
    public void set(double v) {
        this.setDoubleValue(v);
    }

    @Override
    public void set(byte[] obj) {
        this.setByteArrayValue(obj);
    }

    @Override
    public void set(StringEnumAbstractBase obj) {
        this.setEnumValue(obj);
    }

    @Override
    public void set(Calendar obj) {
        this.setCalendarValue(obj);
    }

    @Override
    public void set(Date obj) {
        this.setDateValue(obj);
    }

    @Override
    public void set(GDateSpecification obj) {
        this.setGDateValue(obj);
    }

    @Override
    public void set(GDurationSpecification obj) {
        this.setGDurationValue(obj);
    }

    @Override
    public void set(QName obj) {
        this.setQNameValue(obj);
    }

    @Override
    public void set(List obj) {
        this.setListValue(obj);
    }

    @Override
    public void objectSet(Object obj) {
        this.setObjectValue(obj);
    }

    @Override
    public byte[] getByteArrayValue() {
        throw new XmlValueNotSupportedException("exception.value.not.supported.s2j", new Object[]{this.getPrimitiveTypeName(), "byte[]"});
    }

    @Override
    public boolean getBooleanValue() {
        throw new XmlValueNotSupportedException("exception.value.not.supported.s2j", new Object[]{this.getPrimitiveTypeName(), "boolean"});
    }

    @Override
    public GDate getGDateValue() {
        throw new XmlValueNotSupportedException("exception.value.not.supported.s2j", new Object[]{this.getPrimitiveTypeName(), "Date"});
    }

    @Override
    public Date getDateValue() {
        throw new XmlValueNotSupportedException("exception.value.not.supported.s2j", new Object[]{this.getPrimitiveTypeName(), "Date"});
    }

    @Override
    public Calendar getCalendarValue() {
        throw new XmlValueNotSupportedException("exception.value.not.supported.s2j", new Object[]{this.getPrimitiveTypeName(), "Calendar"});
    }

    @Override
    public GDuration getGDurationValue() {
        throw new XmlValueNotSupportedException("exception.value.not.supported.s2j", new Object[]{this.getPrimitiveTypeName(), "Duration"});
    }

    @Override
    public QName getQNameValue() {
        throw new XmlValueNotSupportedException("exception.value.not.supported.s2j", new Object[]{this.getPrimitiveTypeName(), "QName"});
    }

    @Override
    public List getListValue() {
        throw new XmlValueNotSupportedException("exception.value.not.supported.s2j", new Object[]{this.getPrimitiveTypeName(), "List"});
    }

    @Override
    public List xgetListValue() {
        throw new XmlValueNotSupportedException("exception.value.not.supported.s2j", new Object[]{this.getPrimitiveTypeName(), "List"});
    }

    @Override
    public Object getObjectValue() {
        return XmlObjectBase.java_value(this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setBooleanValue(boolean v) {
        Object object = this.monitor();
        synchronized (object) {
            this.set_prepare();
            this.set_boolean(v);
            this.set_commit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setByteValue(byte v) {
        Object object = this.monitor();
        synchronized (object) {
            this.set_prepare();
            this.set_byte(v);
            this.set_commit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setShortValue(short v) {
        Object object = this.monitor();
        synchronized (object) {
            this.set_prepare();
            this.set_short(v);
            this.set_commit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setIntValue(int v) {
        Object object = this.monitor();
        synchronized (object) {
            this.set_prepare();
            this.set_int(v);
            this.set_commit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setLongValue(long v) {
        Object object = this.monitor();
        synchronized (object) {
            this.set_prepare();
            this.set_long(v);
            this.set_commit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setFloatValue(float v) {
        Object object = this.monitor();
        synchronized (object) {
            this.set_prepare();
            this.set_float(v);
            this.set_commit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setDoubleValue(double v) {
        Object object = this.monitor();
        synchronized (object) {
            this.set_prepare();
            this.set_double(v);
            this.set_commit();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setByteArrayValue(byte[] obj) {
        if (obj == null) {
            this.setNil();
        } else {
            Object object = this.monitor();
            synchronized (object) {
                this.set_prepare();
                this.set_ByteArray(obj);
                this.set_commit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setEnumValue(StringEnumAbstractBase obj) {
        if (obj == null) {
            this.setNil();
        } else {
            Object object = this.monitor();
            synchronized (object) {
                this.set_prepare();
                this.set_enum(obj);
                this.set_commit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setBigIntegerValue(BigInteger obj) {
        if (obj == null) {
            this.setNil();
        } else {
            Object object = this.monitor();
            synchronized (object) {
                this.set_prepare();
                this.set_BigInteger(obj);
                this.set_commit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setBigDecimalValue(BigDecimal obj) {
        if (obj == null) {
            this.setNil();
        } else {
            Object object = this.monitor();
            synchronized (object) {
                this.set_prepare();
                this.set_BigDecimal(obj);
                this.set_commit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setCalendarValue(Calendar obj) {
        if (obj == null) {
            this.setNil();
        } else {
            Object object = this.monitor();
            synchronized (object) {
                this.set_prepare();
                this.set_Calendar(obj);
                this.set_commit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setDateValue(Date obj) {
        if (obj == null) {
            this.setNil();
        } else {
            Object object = this.monitor();
            synchronized (object) {
                this.set_prepare();
                this.set_Date(obj);
                this.set_commit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setGDateValue(GDate obj) {
        if (obj == null) {
            this.setNil();
        } else {
            Object object = this.monitor();
            synchronized (object) {
                this.set_prepare();
                this.set_GDate(obj);
                this.set_commit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void setGDateValue(GDateSpecification obj) {
        if (obj == null) {
            this.setNil();
        } else {
            Object object = this.monitor();
            synchronized (object) {
                this.set_prepare();
                this.set_GDate(obj);
                this.set_commit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setGDurationValue(GDuration obj) {
        if (obj == null) {
            this.setNil();
        } else {
            Object object = this.monitor();
            synchronized (object) {
                this.set_prepare();
                this.set_GDuration(obj);
                this.set_commit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void setGDurationValue(GDurationSpecification obj) {
        if (obj == null) {
            this.setNil();
        } else {
            Object object = this.monitor();
            synchronized (object) {
                this.set_prepare();
                this.set_GDuration(obj);
                this.set_commit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setQNameValue(QName obj) {
        if (obj == null) {
            this.setNil();
        } else {
            Object object = this.monitor();
            synchronized (object) {
                this.set_prepare();
                this.set_QName(obj);
                this.set_commit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setListValue(List obj) {
        if (obj == null) {
            this.setNil();
        } else {
            Object object = this.monitor();
            synchronized (object) {
                this.set_prepare();
                this.set_list(obj);
                this.set_commit();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final void setStringValue(String obj) {
        if (obj == null) {
            this.setNil();
        } else {
            Object object = this.monitor();
            synchronized (object) {
                this.set_prepare();
                this.set_String(obj);
            }
        }
    }

    @Override
    public void setObjectValue(Object o) {
        if (o == null) {
            this.setNil();
            return;
        }
        if (o instanceof XmlObject) {
            this.set((XmlObject)o);
        } else if (o instanceof String) {
            this.setStringValue((String)o);
        } else if (o instanceof StringEnumAbstractBase) {
            this.setEnumValue((StringEnumAbstractBase)o);
        } else if (o instanceof BigInteger) {
            this.setBigIntegerValue((BigInteger)o);
        } else if (o instanceof BigDecimal) {
            this.setBigDecimalValue((BigDecimal)o);
        } else if (o instanceof Byte) {
            this.setByteValue((Byte)o);
        } else if (o instanceof Short) {
            this.setShortValue((Short)o);
        } else if (o instanceof Integer) {
            this.setIntValue((Integer)o);
        } else if (o instanceof Long) {
            this.setLongValue((Long)o);
        } else if (o instanceof Boolean) {
            this.setBooleanValue((Boolean)o);
        } else if (o instanceof Float) {
            this.setFloatValue(((Float)o).floatValue());
        } else if (o instanceof Double) {
            this.setDoubleValue((Double)o);
        } else if (o instanceof Calendar) {
            this.setCalendarValue((Calendar)o);
        } else if (o instanceof Date) {
            this.setDateValue((Date)o);
        } else if (o instanceof GDateSpecification) {
            this.setGDateValue((GDateSpecification)o);
        } else if (o instanceof GDurationSpecification) {
            this.setGDurationValue((GDurationSpecification)o);
        } else if (o instanceof QName) {
            this.setQNameValue((QName)o);
        } else if (o instanceof List) {
            this.setListValue((List)o);
        } else if (o instanceof byte[]) {
            this.setByteArrayValue((byte[])o);
        } else {
            throw new XmlValueNotSupportedException("Can't set union object of class : " + o.getClass().getName());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final void set_newValue(XmlObject obj) {
        block41: {
            if (obj == null || obj.isNil()) {
                this.setNil();
                return;
            }
            if (obj instanceof XmlAnySimpleType) {
                XmlAnySimpleType v = (XmlAnySimpleType)obj;
                SchemaType instanceType = ((SimpleValue)((Object)v)).instanceType();
                assert (instanceType != null) : "Nil case should have been handled already";
                if (instanceType.getSimpleVariety() == 3) {
                    Object object = this.monitor();
                    synchronized (object) {
                        this.set_prepare();
                        this.set_list(((SimpleValue)((Object)v)).xgetListValue());
                        this.set_commit();
                        return;
                    }
                }
                Object object = this.monitor();
                synchronized (object) {
                    assert (instanceType.getSimpleVariety() == 1);
                    block5 : switch (instanceType.getPrimitiveType().getBuiltinTypeCode()) {
                        default: {
                            assert (false) : "encountered nonprimitive type.";
                            break block41;
                        }
                        case 3: {
                            boolean bool = ((SimpleValue)((Object)v)).getBooleanValue();
                            this.set_prepare();
                            this.set_boolean(bool);
                            break;
                        }
                        case 4: {
                            byte[] byteArr = ((SimpleValue)((Object)v)).getByteArrayValue();
                            this.set_prepare();
                            this.set_b64(byteArr);
                            break;
                        }
                        case 5: {
                            byte[] byteArr = ((SimpleValue)((Object)v)).getByteArrayValue();
                            this.set_prepare();
                            this.set_hex(byteArr);
                            break;
                        }
                        case 7: {
                            QName name = ((SimpleValue)((Object)v)).getQNameValue();
                            this.set_prepare();
                            this.set_QName(name);
                            break;
                        }
                        case 9: {
                            float f = ((SimpleValue)((Object)v)).getFloatValue();
                            this.set_prepare();
                            this.set_float(f);
                            break;
                        }
                        case 10: {
                            double d = ((SimpleValue)((Object)v)).getDoubleValue();
                            this.set_prepare();
                            this.set_double(d);
                            break;
                        }
                        case 11: {
                            switch (instanceType.getDecimalSize()) {
                                case 8: {
                                    byte b = ((SimpleValue)((Object)v)).getByteValue();
                                    this.set_prepare();
                                    this.set_byte(b);
                                    break block5;
                                }
                                case 16: {
                                    short s = ((SimpleValue)((Object)v)).getShortValue();
                                    this.set_prepare();
                                    this.set_short(s);
                                    break block5;
                                }
                                case 32: {
                                    int i = ((SimpleValue)((Object)v)).getIntValue();
                                    this.set_prepare();
                                    this.set_int(i);
                                    break block5;
                                }
                                case 64: {
                                    long l = ((SimpleValue)((Object)v)).getLongValue();
                                    this.set_prepare();
                                    this.set_long(l);
                                    break block5;
                                }
                                case 1000000: {
                                    BigInteger bi = ((SimpleValue)((Object)v)).getBigIntegerValue();
                                    this.set_prepare();
                                    this.set_BigInteger(bi);
                                    break block5;
                                }
                                default: {
                                    assert (false) : "invalid numeric bit count";
                                    break;
                                }
                                case 1000001: 
                            }
                            BigDecimal bd = ((SimpleValue)((Object)v)).getBigDecimalValue();
                            this.set_prepare();
                            this.set_BigDecimal(bd);
                            break;
                        }
                        case 6: {
                            String uri = v.getStringValue();
                            this.set_prepare();
                            this.set_text(uri);
                            break;
                        }
                        case 8: {
                            String s = v.getStringValue();
                            this.set_prepare();
                            this.set_notation(s);
                            break;
                        }
                        case 13: {
                            GDuration gd = ((SimpleValue)((Object)v)).getGDurationValue();
                            this.set_prepare();
                            this.set_GDuration(gd);
                            break;
                        }
                        case 14: 
                        case 15: 
                        case 16: 
                        case 17: 
                        case 18: 
                        case 19: 
                        case 20: 
                        case 21: {
                            GDate gd = ((SimpleValue)((Object)v)).getGDateValue();
                            this.set_prepare();
                            this.set_GDate(gd);
                            break;
                        }
                        case 12: {
                            String s = v.getStringValue();
                            this.set_prepare();
                            this.set_String(s);
                            break;
                        }
                        case 2: {
                            boolean pushed = false;
                            if (!v.isImmutable()) {
                                pushed = true;
                                NamespaceContext.push(new NamespaceContext(v));
                            }
                            try {
                                this.set_prepare();
                                this.set_xmlanysimple(v);
                                break;
                            } finally {
                                if (pushed) {
                                    NamespaceContext.pop();
                                }
                            }
                        }
                    }
                    this.set_commit();
                    return;
                }
            }
        }
        throw new IllegalStateException("Complex type unexpected");
    }

    private TypeStoreUser setterHelper(XmlObjectBase src) {
        this.check_orphaned();
        src.check_orphaned();
        return this.get_store().copy_contents_from(src.get_store()).get_store().change_type(src.schemaType());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final XmlObject set(XmlObject src) {
        if (this.isImmutable()) {
            throw new IllegalStateException("Cannot set the value of an immutable XmlObject");
        }
        XmlObjectBase obj = XmlObjectBase.underlying(src);
        TypeStoreUser newObj = this;
        if (obj == null) {
            this.setNil();
            return this;
        }
        if (obj.isImmutable()) {
            this.setStringValue(obj.getStringValue());
        } else {
            boolean noSyncThis = this.preCheck();
            boolean noSyncObj = obj.preCheck();
            if (this.monitor() == obj.monitor()) {
                if (noSyncThis) {
                    newObj = this.setterHelper(obj);
                } else {
                    Object object = this.monitor();
                    synchronized (object) {
                        newObj = this.setterHelper(obj);
                    }
                }
            } else if (noSyncThis) {
                if (noSyncObj) {
                    newObj = this.setterHelper(obj);
                } else {
                    Object object = obj.monitor();
                    synchronized (object) {
                        newObj = this.setterHelper(obj);
                    }
                }
            } else {
                if (noSyncObj) {
                    Object object = this.monitor();
                    synchronized (object) {
                        newObj = this.setterHelper(obj);
                    }
                }
                boolean acquired = false;
                try {
                    GlobalLock.acquire();
                    acquired = true;
                    Object object = this.monitor();
                    synchronized (object) {
                        Object object2 = obj.monitor();
                        synchronized (object2) {
                            GlobalLock.release();
                            acquired = false;
                            newObj = this.setterHelper(obj);
                        }
                    }
                } catch (InterruptedException e) {
                    throw new XmlRuntimeException(e);
                } finally {
                    if (acquired) {
                        GlobalLock.release();
                    }
                }
            }
        }
        return newObj;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final XmlObject generatedSetterHelperImpl(XmlObject src, QName propName, int index, short kindSetterHelper) {
        XmlObjectBase srcObj = XmlObjectBase.underlying(src);
        if (srcObj == null) {
            Object object = this.monitor();
            synchronized (object) {
                XmlObjectBase target = this.getTargetForSetter(propName, index, kindSetterHelper);
                target.setNil();
                return target;
            }
        }
        if (srcObj.isImmutable()) {
            Object object = this.monitor();
            synchronized (object) {
                XmlObjectBase target = this.getTargetForSetter(propName, index, kindSetterHelper);
                target.setStringValue(srcObj.getStringValue());
                return target;
            }
        }
        boolean noSyncThis = this.preCheck();
        boolean noSyncObj = srcObj.preCheck();
        if (this.monitor() == srcObj.monitor()) {
            if (noSyncThis) {
                return (XmlObject)((Object)this.objSetterHelper(srcObj, propName, index, kindSetterHelper));
            }
            Object object = this.monitor();
            synchronized (object) {
                return (XmlObject)((Object)this.objSetterHelper(srcObj, propName, index, kindSetterHelper));
            }
        }
        if (noSyncThis) {
            if (noSyncObj) {
                return (XmlObject)((Object)this.objSetterHelper(srcObj, propName, index, kindSetterHelper));
            }
            Object object = srcObj.monitor();
            synchronized (object) {
                return (XmlObject)((Object)this.objSetterHelper(srcObj, propName, index, kindSetterHelper));
            }
        }
        if (noSyncObj) {
            Object object = this.monitor();
            synchronized (object) {
                return (XmlObject)((Object)this.objSetterHelper(srcObj, propName, index, kindSetterHelper));
            }
        }
        boolean acquired = false;
        try {
            GlobalLock.acquire();
            acquired = true;
            Object object = this.monitor();
            synchronized (object) {
                Object object2 = srcObj.monitor();
                synchronized (object2) {
                    try {
                        GlobalLock.release();
                        acquired = false;
                        XmlObject xmlObject = (XmlObject)((Object)this.objSetterHelper(srcObj, propName, index, kindSetterHelper));
                        return xmlObject;
                    } catch (Throwable throwable) {
                        try {
                            throw throwable;
                        } catch (InterruptedException e) {
                            throw new XmlRuntimeException(e);
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

    private TypeStoreUser objSetterHelper(XmlObjectBase srcObj, QName propName, int index, short kindSetterHelper) {
        XmlObjectBase target = this.getTargetForSetter(propName, index, kindSetterHelper);
        target.check_orphaned();
        srcObj.check_orphaned();
        return target.get_store().copy_contents_from(srcObj.get_store()).get_store().change_type(srcObj.schemaType());
    }

    private XmlObjectBase getTargetForSetter(QName propName, int index, short kindSetterHelper) {
        switch (kindSetterHelper) {
            case 1: {
                this.check_orphaned();
                XmlObjectBase target = null;
                target = (XmlObjectBase)this.get_store().find_element_user(propName, index);
                if (target == null) {
                    target = (XmlObjectBase)this.get_store().add_element_user(propName);
                }
                if (target.isImmutable()) {
                    throw new IllegalStateException("Cannot set the value of an immutable XmlObject");
                }
                return target;
            }
            case 2: {
                this.check_orphaned();
                XmlObjectBase target = null;
                target = (XmlObjectBase)this.get_store().find_element_user(propName, index);
                if (target == null) {
                    throw new IndexOutOfBoundsException();
                }
                if (target.isImmutable()) {
                    throw new IllegalStateException("Cannot set the value of an immutable XmlObject");
                }
                return target;
            }
        }
        throw new IllegalArgumentException("Unknown kindSetterHelper: " + kindSetterHelper);
    }

    public final XmlObject _set(XmlObject src) {
        if (this.isImmutable()) {
            throw new IllegalStateException("Cannot set the value of an immutable XmlObject");
        }
        XmlObjectBase obj = XmlObjectBase.underlying(src);
        TypeStoreUser newObj = this;
        if (obj == null) {
            this.setNil();
            return this;
        }
        if (obj.isImmutable()) {
            this.set(obj.stringValue());
        } else {
            this.check_orphaned();
            obj.check_orphaned();
            newObj = this.get_store().copy_contents_from(obj.get_store()).get_store().change_type(obj.schemaType());
        }
        return newObj;
    }

    protected void set_list(List list) {
        throw new XmlValueNotSupportedException("exception.value.not.supported.j2s", new Object[]{"List", this.getPrimitiveTypeName()});
    }

    protected void set_boolean(boolean v) {
        throw new XmlValueNotSupportedException("exception.value.not.supported.j2s", new Object[]{"boolean", this.getPrimitiveTypeName()});
    }

    protected void set_byte(byte v) {
        this.set_int(v);
    }

    protected void set_short(short v) {
        this.set_int(v);
    }

    protected void set_int(int v) {
        this.set_long(v);
    }

    protected void set_long(long v) {
        this.set_BigInteger(BigInteger.valueOf(v));
    }

    protected void set_char(char v) {
        this.set_String(Character.toString(v));
    }

    protected void set_float(float v) {
        this.set_BigDecimal(new BigDecimal(v));
    }

    protected void set_double(double v) {
        this.set_BigDecimal(new BigDecimal(v));
    }

    protected void set_enum(StringEnumAbstractBase e) {
        throw new XmlValueNotSupportedException("exception.value.not.supported.j2s", new Object[]{"enum", this.getPrimitiveTypeName()});
    }

    protected void set_ByteArray(byte[] b) {
        throw new XmlValueNotSupportedException("exception.value.not.supported.j2s", new Object[]{"byte[]", this.getPrimitiveTypeName()});
    }

    protected void set_b64(byte[] b) {
        this.set_ByteArray(b);
    }

    protected void set_hex(byte[] b) {
        this.set_ByteArray(b);
    }

    protected void set_BigInteger(BigInteger v) {
        this.set_BigDecimal(new BigDecimal(v));
    }

    protected void set_BigDecimal(BigDecimal v) {
        throw new XmlValueNotSupportedException("exception.value.not.supported.j2s", new Object[]{"numeric", this.getPrimitiveTypeName()});
    }

    protected void set_Date(Date v) {
        throw new XmlValueNotSupportedException("exception.value.not.supported.j2s", new Object[]{"Date", this.getPrimitiveTypeName()});
    }

    protected void set_Calendar(Calendar v) {
        throw new XmlValueNotSupportedException("exception.value.not.supported.j2s", new Object[]{"Calendar", this.getPrimitiveTypeName()});
    }

    protected void set_GDate(GDateSpecification v) {
        throw new XmlValueNotSupportedException("exception.value.not.supported.j2s", new Object[]{"Date", this.getPrimitiveTypeName()});
    }

    protected void set_GDuration(GDurationSpecification v) {
        throw new XmlValueNotSupportedException("exception.value.not.supported.j2s", new Object[]{"Duration", this.getPrimitiveTypeName()});
    }

    protected void set_ComplexXml(XmlObject v) {
        throw new XmlValueNotSupportedException("exception.value.not.supported.j2s", new Object[]{"complex content", this.getPrimitiveTypeName()});
    }

    protected void set_QName(QName v) {
        throw new XmlValueNotSupportedException("exception.value.not.supported.j2s", new Object[]{"QName", this.getPrimitiveTypeName()});
    }

    protected void set_notation(String v) {
        throw new XmlValueNotSupportedException();
    }

    protected void set_xmlanysimple(XmlAnySimpleType v) {
        this.set_String(v.getStringValue());
    }

    private final String getPrimitiveTypeName() {
        SchemaType type = this.schemaType();
        if (type.isNoType()) {
            return "unknown";
        }
        SchemaType t = type.getPrimitiveType();
        if (t == null) {
            return "complex";
        }
        return t.getName().getLocalPart();
    }

    private final boolean comparable_value_spaces(SchemaType t1, SchemaType t2) {
        assert (t1.getSimpleVariety() != 2 && t2.getSimpleVariety() != 2);
        if (!t1.isSimpleType() && !t2.isSimpleType()) {
            return t1.getContentType() == t2.getContentType();
        }
        if (!t1.isSimpleType() || !t2.isSimpleType()) {
            return false;
        }
        if (t1.getSimpleVariety() == 3 && t2.getSimpleVariety() == 3) {
            return true;
        }
        if (t1.getSimpleVariety() == 3 || t2.getSimpleVariety() == 3) {
            return false;
        }
        return t1.getPrimitiveType().equals(t2.getPrimitiveType());
    }

    private final boolean valueEqualsImpl(XmlObject xmlobj) {
        this.check_dated();
        SchemaType typethis = this.instanceType();
        SchemaType typeother = ((SimpleValue)xmlobj).instanceType();
        if (typethis == null && typeother == null) {
            return true;
        }
        if (typethis == null || typeother == null) {
            return false;
        }
        if (!this.comparable_value_spaces(typethis, typeother)) {
            return false;
        }
        if (xmlobj.schemaType().getSimpleVariety() == 2) {
            return XmlObjectBase.underlying(xmlobj).equal_to(this);
        }
        return this.equal_to(xmlobj);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final boolean valueEquals(XmlObject xmlobj) {
        Object object;
        boolean acquired;
        block25: {
            acquired = false;
            if (!this.isImmutable()) break block25;
            if (xmlobj.isImmutable()) {
                boolean bl = this.valueEqualsImpl(xmlobj);
                return bl;
            }
            object = xmlobj.monitor();
            synchronized (object) {
                boolean bl = this.valueEqualsImpl(xmlobj);
                return bl;
            }
        }
        if (xmlobj.isImmutable() || this.monitor() == xmlobj.monitor()) {
            object = this.monitor();
            synchronized (object) {
                boolean bl = this.valueEqualsImpl(xmlobj);
                return bl;
            }
        }
        GlobalLock.acquire();
        acquired = true;
        object = this.monitor();
        synchronized (object) {
            Object object2 = xmlobj.monitor();
            synchronized (object2) {
                try {
                    GlobalLock.release();
                    acquired = false;
                    boolean bl = this.valueEqualsImpl(xmlobj);
                    return bl;
                } catch (Throwable throwable) {
                    try {
                        throw throwable;
                    } catch (InterruptedException e) {
                        throw new XmlRuntimeException(e);
                    }
                }
            }
        }
        finally {
            if (acquired) {
                GlobalLock.release();
            }
        }
    }

    @Override
    public final int compareTo(Object obj) {
        int result = this.compareValue((XmlObject)obj);
        if (result == 2) {
            throw new ClassCastException();
        }
        return result;
    }

    private final int compareValueImpl(XmlObject xmlobj) {
        SchemaType type2;
        SchemaType type1;
        try {
            type1 = this.instanceType();
            type2 = ((SimpleValue)xmlobj).instanceType();
        } catch (XmlValueOutOfRangeException e) {
            return 2;
        }
        if (type1 == null && type2 == null) {
            return 0;
        }
        if (type1 == null || type2 == null) {
            return 2;
        }
        if (!type1.isSimpleType() || type1.isURType()) {
            return 2;
        }
        if (!type2.isSimpleType() || type2.isURType()) {
            return 2;
        }
        type1 = type1.getPrimitiveType();
        type2 = type2.getPrimitiveType();
        if (type1.getBuiltinTypeCode() != type2.getBuiltinTypeCode()) {
            return 2;
        }
        return this.compare_to(xmlobj);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public final int compareValue(XmlObject xmlobj) {
        Object object;
        boolean acquired;
        block26: {
            if (xmlobj == null) {
                return 2;
            }
            acquired = false;
            if (!this.isImmutable()) break block26;
            if (xmlobj.isImmutable()) {
                int n = this.compareValueImpl(xmlobj);
                return n;
            }
            object = xmlobj.monitor();
            synchronized (object) {
                int n = this.compareValueImpl(xmlobj);
                return n;
            }
        }
        if (xmlobj.isImmutable() || this.monitor() == xmlobj.monitor()) {
            object = this.monitor();
            synchronized (object) {
                int n = this.compareValueImpl(xmlobj);
                return n;
            }
        }
        GlobalLock.acquire();
        acquired = true;
        object = this.monitor();
        synchronized (object) {
            Object object2 = xmlobj.monitor();
            synchronized (object2) {
                try {
                    GlobalLock.release();
                    acquired = false;
                    int n = this.compareValueImpl(xmlobj);
                    return n;
                } catch (Throwable throwable) {
                    try {
                        throw throwable;
                    } catch (InterruptedException e) {
                        throw new XmlRuntimeException(e);
                    }
                }
            }
        }
        finally {
            if (acquired) {
                GlobalLock.release();
            }
        }
    }

    protected int compare_to(XmlObject xmlobj) {
        if (this.equal_to(xmlobj)) {
            return 0;
        }
        return 2;
    }

    protected abstract boolean equal_to(XmlObject var1);

    protected abstract int value_hash_code();

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int valueHashCode() {
        Object object = this.monitor();
        synchronized (object) {
            return this.value_hash_code();
        }
    }

    public boolean isInstanceOf(SchemaType type) {
        if (type.getSimpleVariety() != 2) {
            for (SchemaType myType = this.instanceType(); myType != null; myType = myType.getBaseType()) {
                if (type != myType) continue;
                return true;
            }
            return false;
        }
        HashSet<SchemaType> ctypes = new HashSet<SchemaType>(Arrays.asList(type.getUnionConstituentTypes()));
        for (SchemaType myType = this.instanceType(); myType != null; myType = myType.getBaseType()) {
            if (!ctypes.contains(myType)) continue;
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (!this.isImmutable()) {
            return super.equals(obj);
        }
        if (!(obj instanceof XmlObject)) {
            return false;
        }
        XmlObject xmlobj = (XmlObject)obj;
        if (!xmlobj.isImmutable()) {
            return false;
        }
        return this.valueEquals(xmlobj);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public final int hashCode() {
        if (!this.isImmutable()) {
            return super.hashCode();
        }
        Object object = this.monitor();
        synchronized (object) {
            if (this.isNil()) {
                return 0;
            }
            return this.value_hash_code();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlObject[] selectChildren(QName elementName) {
        XmlCursor xc = this.newCursor();
        try {
            if (!xc.isContainer()) {
                XmlObject[] xmlObjectArray = EMPTY_RESULT;
                return xmlObjectArray;
            }
            ArrayList<XmlObject> result = new ArrayList<XmlObject>();
            if (xc.toChild(elementName)) {
                do {
                    result.add(xc.getObject());
                } while (xc.toNextSibling(elementName));
            }
            if (result.size() == 0) {
                XmlObject[] xmlObjectArray = EMPTY_RESULT;
                return xmlObjectArray;
            }
            XmlObject[] xmlObjectArray = result.toArray(EMPTY_RESULT);
            return xmlObjectArray;
        } finally {
            xc.dispose();
        }
    }

    @Override
    public XmlObject[] selectChildren(String elementUri, String elementLocalName) {
        return this.selectChildren(new QName(elementUri, elementLocalName));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlObject[] selectChildren(QNameSet elementNameSet) {
        if (elementNameSet == null) {
            throw new IllegalArgumentException();
        }
        XmlCursor xc = this.newCursor();
        try {
            if (!xc.isContainer()) {
                XmlObject[] xmlObjectArray = EMPTY_RESULT;
                return xmlObjectArray;
            }
            ArrayList<XmlObject> result = new ArrayList<XmlObject>();
            if (xc.toFirstChild()) {
                do {
                    assert (xc.isContainer());
                    if (!elementNameSet.contains(xc.getName())) continue;
                    result.add(xc.getObject());
                } while (xc.toNextSibling());
            }
            if (result.size() == 0) {
                XmlObject[] xmlObjectArray = EMPTY_RESULT;
                return xmlObjectArray;
            }
            XmlObject[] xmlObjectArray = result.toArray(EMPTY_RESULT);
            return xmlObjectArray;
        } finally {
            xc.dispose();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlObject selectAttribute(QName attributeName) {
        XmlCursor xc = this.newCursor();
        try {
            if (!xc.isContainer()) {
                XmlObject xmlObject = null;
                return xmlObject;
            }
            if (xc.toFirstAttribute()) {
                do {
                    if (!xc.getName().equals(attributeName)) continue;
                    XmlObject xmlObject = xc.getObject();
                    return xmlObject;
                } while (xc.toNextAttribute());
            }
            XmlObject xmlObject = null;
            return xmlObject;
        } finally {
            xc.dispose();
        }
    }

    @Override
    public XmlObject selectAttribute(String attributeUri, String attributeLocalName) {
        return this.selectAttribute(new QName(attributeUri, attributeLocalName));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public XmlObject[] selectAttributes(QNameSet attributeNameSet) {
        if (attributeNameSet == null) {
            throw new IllegalArgumentException();
        }
        XmlCursor xc = this.newCursor();
        try {
            if (!xc.isContainer()) {
                XmlObject[] xmlObjectArray = EMPTY_RESULT;
                return xmlObjectArray;
            }
            ArrayList<XmlObject> result = new ArrayList<XmlObject>();
            if (xc.toFirstAttribute()) {
                do {
                    if (!attributeNameSet.contains(xc.getName())) continue;
                    result.add(xc.getObject());
                } while (xc.toNextAttribute());
            }
            if (result.size() == 0) {
                XmlObject[] xmlObjectArray = EMPTY_RESULT;
                return xmlObjectArray;
            }
            XmlObject[] xmlObjectArray = result.toArray(EMPTY_RESULT);
            return xmlObjectArray;
        } finally {
            xc.dispose();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Object writeReplace() {
        Object object = this.monitor();
        synchronized (object) {
            if (this.isRootXmlObject()) {
                return new SerializedRootObject(this);
            }
            return new SerializedInteriorObject(this, this.getRootXmlObject());
        }
    }

    private boolean isRootXmlObject() {
        XmlCursor cur = this.newCursor();
        if (cur == null) {
            return false;
        }
        boolean result = !cur.toParent();
        cur.dispose();
        return result;
    }

    private XmlObject getRootXmlObject() {
        XmlCursor cur = this.newCursor();
        if (cur == null) {
            return this;
        }
        cur.toStartDoc();
        XmlObject result = cur.getObject();
        cur.dispose();
        return result;
    }

    protected static Object java_value(XmlObject obj) {
        if (obj.isNil()) {
            return null;
        }
        if (!(obj instanceof XmlAnySimpleType)) {
            return obj;
        }
        SchemaType instanceType = ((SimpleValue)obj).instanceType();
        assert (instanceType != null) : "Nil case should have been handled above";
        if (instanceType.getSimpleVariety() == 3) {
            return ((SimpleValue)obj).getListValue();
        }
        SimpleValue base = (SimpleValue)obj;
        switch (instanceType.getPrimitiveType().getBuiltinTypeCode()) {
            case 3: {
                return base.getBooleanValue() ? Boolean.TRUE : Boolean.FALSE;
            }
            case 4: 
            case 5: {
                return base.getByteArrayValue();
            }
            case 7: {
                return base.getQNameValue();
            }
            case 9: {
                return new Float(base.getFloatValue());
            }
            case 10: {
                return new Double(base.getDoubleValue());
            }
            case 11: {
                switch (instanceType.getDecimalSize()) {
                    case 8: {
                        return new Byte(base.getByteValue());
                    }
                    case 16: {
                        return new Short(base.getShortValue());
                    }
                    case 32: {
                        return new Integer(base.getIntValue());
                    }
                    case 64: {
                        return new Long(base.getLongValue());
                    }
                    case 1000000: {
                        return base.getBigIntegerValue();
                    }
                    default: {
                        assert (false) : "invalid numeric bit count";
                        break;
                    }
                    case 1000001: 
                }
                return base.getBigDecimalValue();
            }
            case 6: {
                return base.getStringValue();
            }
            case 13: {
                return base.getGDurationValue();
            }
            case 14: 
            case 15: 
            case 16: 
            case 17: 
            case 18: 
            case 19: 
            case 20: 
            case 21: {
                return base.getCalendarValue();
            }
            default: {
                assert (false) : "encountered nonprimitive type.";
                break;
            }
            case 2: 
            case 8: 
            case 12: 
        }
        return base.getStringValue();
    }

    protected XmlAnySimpleType get_default_attribute_value(QName name) {
        SchemaType sType = this.schemaType();
        SchemaAttributeModel aModel = sType.getAttributeModel();
        if (aModel == null) {
            return null;
        }
        SchemaLocalAttribute sAttr = aModel.getAttribute(name);
        if (sAttr == null) {
            return null;
        }
        return sAttr.getDefaultValue();
    }

    private static class SerializedInteriorObject
    implements Serializable {
        private static final long serialVersionUID = 1L;
        transient XmlObject _impl;
        transient XmlObject _root;

        private SerializedInteriorObject() {
        }

        private SerializedInteriorObject(XmlObject impl, XmlObject root) {
            this._impl = impl;
            this._root = root;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.writeObject(this._root);
            out.writeBoolean(false);
            out.writeInt(this.distanceToRoot());
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            this._root = (XmlObject)in.readObject();
            in.readBoolean();
            this._impl = this.objectAtDistance(in.readInt());
        }

        private Object readResolve() throws ObjectStreamException {
            return this._impl;
        }

        private int distanceToRoot() {
            XmlCursor cur = this._impl.newCursor();
            int count = 0;
            while (!cur.toPrevToken().isNone()) {
                if (cur.currentTokenType().isNamespace()) continue;
                ++count;
            }
            cur.dispose();
            return count;
        }

        private XmlObject objectAtDistance(int count) {
            XmlCursor cur = this._root.newCursor();
            while (count > 0) {
                cur.toNextToken();
                if (cur.currentTokenType().isNamespace()) continue;
                --count;
            }
            XmlObject result = cur.getObject();
            cur.dispose();
            return result;
        }
    }

    private static class SerializedRootObject
    implements Serializable {
        private static final long serialVersionUID = 1L;
        transient Class _xbeanClass;
        transient XmlObject _impl;

        private SerializedRootObject() {
        }

        private SerializedRootObject(XmlObject impl) {
            this._xbeanClass = impl.schemaType().getJavaClass();
            this._impl = impl;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.writeObject(this._xbeanClass);
            out.writeShort(0);
            out.writeShort(1);
            out.writeShort(1);
            String xmlText = this._impl.xmlText();
            out.writeObject(xmlText);
            out.writeBoolean(false);
        }

        private void readObject(ObjectInputStream in) throws IOException {
            try {
                this._xbeanClass = (Class)in.readObject();
                int utfBytes = in.readUnsignedShort();
                int majorVersionNum = 0;
                int minorVersionNum = 0;
                if (utfBytes == 0) {
                    majorVersionNum = in.readUnsignedShort();
                    minorVersionNum = in.readUnsignedShort();
                }
                String xmlText = null;
                block1 : switch (majorVersionNum) {
                    case 0: {
                        xmlText = this.readObjectV0(in, utfBytes);
                        in.readBoolean();
                        break;
                    }
                    case 1: {
                        switch (minorVersionNum) {
                            case 1: {
                                xmlText = (String)in.readObject();
                                in.readBoolean();
                                break block1;
                            }
                        }
                        throw new IOException("Deserialization error: version number " + majorVersionNum + "." + minorVersionNum + " not supported.");
                    }
                    default: {
                        throw new IOException("Deserialization error: version number " + majorVersionNum + "." + minorVersionNum + " not supported.");
                    }
                }
                XmlOptions opts = new XmlOptions().setDocumentType(XmlBeans.typeForClass(this._xbeanClass));
                this._impl = XmlBeans.getContextTypeLoader().parse(xmlText, null, opts);
            } catch (Exception e) {
                throw (IOException)new IOException(e.getMessage()).initCause(e);
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private String readObjectV0(ObjectInputStream in, int utfBytes) throws IOException {
            int totalBytesRead;
            int numRead;
            byte[] bArray = new byte[utfBytes + 2];
            bArray[0] = (byte)(0xFF & utfBytes >> 8);
            bArray[1] = (byte)(0xFF & utfBytes);
            for (totalBytesRead = 0; totalBytesRead < utfBytes && (numRead = in.read(bArray, 2 + totalBytesRead, utfBytes - totalBytesRead)) != -1; totalBytesRead += numRead) {
            }
            if (totalBytesRead != utfBytes) {
                throw new IOException("Error reading backwards compatible XmlObject: number of bytes read (" + totalBytesRead + ") != number expected (" + utfBytes + ")");
            }
            FilterInputStream dis = null;
            String str = null;
            try {
                dis = new DataInputStream(new ByteArrayInputStream(bArray));
                str = ((DataInputStream)dis).readUTF();
            } finally {
                if (dis != null) {
                    dis.close();
                }
            }
            return str;
        }

        private Object readResolve() throws ObjectStreamException {
            return this._impl;
        }
    }

    private static final class ImmutableValueValidationContext
    implements ValidationContext {
        private XmlObject _loc;
        private Collection _coll;

        ImmutableValueValidationContext(Collection coll, XmlObject loc) {
            this._coll = coll;
            this._loc = loc;
        }

        @Override
        public void invalid(String message) {
            this._coll.add(XmlError.forObject(message, this._loc));
        }

        @Override
        public void invalid(String code, Object[] args) {
            this._coll.add(XmlError.forObject(code, args, this._loc));
        }
    }

    private static final class ValueOutOfRangeValidationContext
    implements ValidationContext {
        private ValueOutOfRangeValidationContext() {
        }

        @Override
        public void invalid(String message) {
            throw new XmlValueOutOfRangeException(message);
        }

        @Override
        public void invalid(String code, Object[] args) {
            throw new XmlValueOutOfRangeException(code, args);
        }
    }
}

