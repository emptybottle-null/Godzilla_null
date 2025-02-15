/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.schema;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.xmlbeans.SchemaAnnotation;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaComponent;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaIdentityConstraint;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeSystem;

class SchemaContainer {
    private String _namespace;
    private SchemaTypeSystem _typeSystem;
    boolean _immutable;
    private List _globalElements = new ArrayList();
    private List _globalAttributes = new ArrayList();
    private List _modelGroups = new ArrayList();
    private List _redefinedModelGroups = new ArrayList();
    private List _attributeGroups = new ArrayList();
    private List _redefinedAttributeGroups = new ArrayList();
    private List _globalTypes = new ArrayList();
    private List _redefinedGlobalTypes = new ArrayList();
    private List _documentTypes = new ArrayList();
    private List _attributeTypes = new ArrayList();
    private List _identityConstraints = new ArrayList();
    private List _annotations = new ArrayList();

    SchemaContainer(String namespace) {
        this._namespace = namespace;
    }

    String getNamespace() {
        return this._namespace;
    }

    synchronized SchemaTypeSystem getTypeSystem() {
        return this._typeSystem;
    }

    synchronized void setTypeSystem(SchemaTypeSystem typeSystem) {
        this._typeSystem = typeSystem;
    }

    synchronized void setImmutable() {
        this._immutable = true;
    }

    synchronized void unsetImmutable() {
        this._immutable = false;
    }

    private void check_immutable() {
        if (this._immutable) {
            throw new IllegalStateException("Cannot add components to immutable SchemaContainer");
        }
    }

    void addGlobalElement(SchemaGlobalElement.Ref e) {
        this.check_immutable();
        this._globalElements.add(e);
    }

    List globalElements() {
        return this.getComponentList(this._globalElements);
    }

    void addGlobalAttribute(SchemaGlobalAttribute.Ref a) {
        this.check_immutable();
        this._globalAttributes.add(a);
    }

    List globalAttributes() {
        return this.getComponentList(this._globalAttributes);
    }

    void addModelGroup(SchemaModelGroup.Ref g) {
        this.check_immutable();
        this._modelGroups.add(g);
    }

    List modelGroups() {
        return this.getComponentList(this._modelGroups);
    }

    void addRedefinedModelGroup(SchemaModelGroup.Ref g) {
        this.check_immutable();
        this._redefinedModelGroups.add(g);
    }

    List redefinedModelGroups() {
        return this.getComponentList(this._redefinedModelGroups);
    }

    void addAttributeGroup(SchemaAttributeGroup.Ref g) {
        this.check_immutable();
        this._attributeGroups.add(g);
    }

    List attributeGroups() {
        return this.getComponentList(this._attributeGroups);
    }

    void addRedefinedAttributeGroup(SchemaAttributeGroup.Ref g) {
        this.check_immutable();
        this._redefinedAttributeGroups.add(g);
    }

    List redefinedAttributeGroups() {
        return this.getComponentList(this._redefinedAttributeGroups);
    }

    void addGlobalType(SchemaType.Ref t) {
        this.check_immutable();
        this._globalTypes.add(t);
    }

    List globalTypes() {
        return this.getComponentList(this._globalTypes);
    }

    void addRedefinedType(SchemaType.Ref t) {
        this.check_immutable();
        this._redefinedGlobalTypes.add(t);
    }

    List redefinedGlobalTypes() {
        return this.getComponentList(this._redefinedGlobalTypes);
    }

    void addDocumentType(SchemaType.Ref t) {
        this.check_immutable();
        this._documentTypes.add(t);
    }

    List documentTypes() {
        return this.getComponentList(this._documentTypes);
    }

    void addAttributeType(SchemaType.Ref t) {
        this.check_immutable();
        this._attributeTypes.add(t);
    }

    List attributeTypes() {
        return this.getComponentList(this._attributeTypes);
    }

    void addIdentityConstraint(SchemaIdentityConstraint.Ref c) {
        this.check_immutable();
        this._identityConstraints.add(c);
    }

    List identityConstraints() {
        return this.getComponentList(this._identityConstraints);
    }

    void addAnnotation(SchemaAnnotation a) {
        this.check_immutable();
        this._annotations.add(a);
    }

    List annotations() {
        return Collections.unmodifiableList(this._annotations);
    }

    private List getComponentList(List referenceList) {
        ArrayList<SchemaComponent> result = new ArrayList<SchemaComponent>();
        for (int i = 0; i < referenceList.size(); ++i) {
            result.add(((SchemaComponent.Ref)referenceList.get(i)).getComponent());
        }
        return Collections.unmodifiableList(result);
    }
}

