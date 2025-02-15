/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.SchemaContainer;
import org.apache.xmlbeans.impl.schema.SchemaLocalElementImpl;

public class SchemaGlobalElementImpl
extends SchemaLocalElementImpl
implements SchemaGlobalElement {
    private Set _sgMembers = new LinkedHashSet();
    private static final QName[] _namearray = new QName[0];
    private boolean _finalExt;
    private boolean _finalRest;
    private SchemaContainer _container;
    private String _filename;
    private String _parseTNS;
    private boolean _chameleon;
    private SchemaGlobalElement.Ref _sg;
    private SchemaGlobalElement.Ref _selfref = new SchemaGlobalElement.Ref(this);

    public SchemaGlobalElementImpl(SchemaContainer container) {
        this._container = container;
    }

    @Override
    public SchemaTypeSystem getTypeSystem() {
        return this._container.getTypeSystem();
    }

    SchemaContainer getContainer() {
        return this._container;
    }

    @Override
    public String getSourceName() {
        return this._filename;
    }

    public void setFilename(String filename) {
        this._filename = filename;
    }

    void setFinal(boolean finalExt, boolean finalRest) {
        this.mutate();
        this._finalExt = finalExt;
        this._finalRest = finalRest;
    }

    @Override
    public int getComponentType() {
        return 1;
    }

    @Override
    public SchemaGlobalElement substitutionGroup() {
        return this._sg == null ? null : this._sg.get();
    }

    public void setSubstitutionGroup(SchemaGlobalElement.Ref sg) {
        this._sg = sg;
    }

    @Override
    public QName[] substitutionGroupMembers() {
        return this._sgMembers.toArray(_namearray);
    }

    public void addSubstitutionGroupMember(QName name) {
        this.mutate();
        this._sgMembers.add(name);
    }

    @Override
    public boolean finalExtension() {
        return this._finalExt;
    }

    @Override
    public boolean finalRestriction() {
        return this._finalRest;
    }

    public void setParseContext(XmlObject parseObject, String targetNamespace, boolean chameleon) {
        this._parseObject = parseObject;
        this._parseTNS = targetNamespace;
        this._chameleon = chameleon;
    }

    public XmlObject getParseObject() {
        return this._parseObject;
    }

    public String getTargetNamespace() {
        return this._parseTNS;
    }

    public String getChameleonNamespace() {
        return this._chameleon ? this._parseTNS : null;
    }

    @Override
    public SchemaGlobalElement.Ref getRef() {
        return this._selfref;
    }

    @Override
    public SchemaComponent.Ref getComponentRef() {
        return this.getRef();
    }
}

