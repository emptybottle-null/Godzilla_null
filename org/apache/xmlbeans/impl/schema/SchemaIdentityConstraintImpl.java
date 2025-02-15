/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.util.Collections;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.XPath;
import org.apache.xmlbeans.impl.schema.SchemaContainer;

public class SchemaIdentityConstraintImpl
implements SchemaIdentityConstraint {
    private SchemaContainer _container;
    private String _selector;
    private String[] _fields;
    private SchemaIdentityConstraint.Ref _key;
    private QName _name;
    private int _type;
    private XmlObject _parse;
    private Object _userData;
    private SchemaAnnotation _annotation;
    private Map _nsMap = Collections.EMPTY_MAP;
    private String _parseTNS;
    private boolean _chameleon;
    private String _filename;
    private volatile XPath _selectorPath;
    private volatile XPath[] _fieldPaths;
    private SchemaIdentityConstraint.Ref _selfref = new SchemaIdentityConstraint.Ref(this);

    public SchemaIdentityConstraintImpl(SchemaContainer c) {
        this._container = c;
    }

    public void setFilename(String filename) {
        this._filename = filename;
    }

    @Override
    public String getSourceName() {
        return this._filename;
    }

    @Override
    public String getSelector() {
        return this._selector;
    }

    @Override
    public Object getSelectorPath() {
        XPath p = this._selectorPath;
        if (p == null) {
            try {
                this.buildPaths();
                p = this._selectorPath;
            } catch (XPath.XPathCompileException e) {
                assert (false) : "Failed to compile xpath. Should be caught by compiler " + e;
                return null;
            }
        }
        return p;
    }

    public void setAnnotation(SchemaAnnotation ann) {
        this._annotation = ann;
    }

    @Override
    public SchemaAnnotation getAnnotation() {
        return this._annotation;
    }

    public void setNSMap(Map nsMap) {
        this._nsMap = nsMap;
    }

    @Override
    public Map getNSMap() {
        return Collections.unmodifiableMap(this._nsMap);
    }

    public void setSelector(String selector) {
        assert (selector != null);
        this._selector = selector;
    }

    public void setFields(String[] fields) {
        assert (fields != null && fields.length > 0);
        this._fields = fields;
    }

    @Override
    public String[] getFields() {
        String[] fields = new String[this._fields.length];
        System.arraycopy(this._fields, 0, fields, 0, fields.length);
        return fields;
    }

    @Override
    public Object getFieldPath(int index) {
        XPath[] p = this._fieldPaths;
        if (p == null) {
            try {
                this.buildPaths();
                p = this._fieldPaths;
            } catch (XPath.XPathCompileException e) {
                assert (false) : "Failed to compile xpath. Should be caught by compiler " + e;
                return null;
            }
        }
        return p[index];
    }

    public void buildPaths() throws XPath.XPathCompileException {
        this._selectorPath = XPath.compileXPath(this._selector, this._nsMap);
        this._fieldPaths = new XPath[this._fields.length];
        for (int i = 0; i < this._fieldPaths.length; ++i) {
            this._fieldPaths[i] = XPath.compileXPath(this._fields[i], this._nsMap);
        }
    }

    public void setReferencedKey(SchemaIdentityConstraint.Ref key) {
        this._key = key;
    }

    @Override
    public SchemaIdentityConstraint getReferencedKey() {
        return this._key.get();
    }

    public void setConstraintCategory(int type) {
        assert (type >= 1 && type <= 3);
        this._type = type;
    }

    @Override
    public int getConstraintCategory() {
        return this._type;
    }

    public void setName(QName name) {
        assert (name != null);
        this._name = name;
    }

    @Override
    public QName getName() {
        return this._name;
    }

    @Override
    public int getComponentType() {
        return 5;
    }

    @Override
    public SchemaTypeSystem getTypeSystem() {
        return this._container.getTypeSystem();
    }

    SchemaContainer getContainer() {
        return this._container;
    }

    public void setParseContext(XmlObject o, String targetNamespace, boolean chameleon) {
        this._parse = o;
        this._parseTNS = targetNamespace;
        this._chameleon = chameleon;
    }

    public XmlObject getParseObject() {
        return this._parse;
    }

    public String getTargetNamespace() {
        return this._parseTNS;
    }

    public String getChameleonNamespace() {
        return this._chameleon ? this._parseTNS : null;
    }

    public boolean isResolved() {
        return this.getConstraintCategory() != 2 || this._key != null;
    }

    public SchemaIdentityConstraint.Ref getRef() {
        return this._selfref;
    }

    @Override
    public SchemaComponent.Ref getComponentRef() {
        return this.getRef();
    }

    @Override
    public Object getUserData() {
        return this._userData;
    }

    public void setUserData(Object data) {
        this._userData = data;
    }
}

