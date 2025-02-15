/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.inst2xsd.util;

import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlQName;
import org.apache.xmlbeans.XmlString;
import org.apache.xmlbeans.impl.inst2xsd.util.Attribute;
import org.apache.xmlbeans.impl.inst2xsd.util.Element;
import org.apache.xmlbeans.impl.inst2xsd.util.Type;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.apache.xmlbeans.impl.xb.xsdschema.ComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.DocumentationDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.impl.xb.xsdschema.FormChoice;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.LocalElement;
import org.apache.xmlbeans.impl.xb.xsdschema.NoFixedFacet;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleContentDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.SimpleExtensionType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelAttribute;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelComplexType;
import org.apache.xmlbeans.impl.xb.xsdschema.TopLevelElement;

public class TypeSystemHolder {
    Map _globalElements = new LinkedHashMap();
    Map _globalAttributes = new LinkedHashMap();
    Map _globalTypes = new LinkedHashMap();

    public void addGlobalElement(Element element) {
        assert (element.isGlobal() && !element.isRef());
        this._globalElements.put(element.getName(), element);
    }

    public Element getGlobalElement(QName name) {
        return (Element)this._globalElements.get(name);
    }

    public Element[] getGlobalElements() {
        Collection col = this._globalElements.values();
        return col.toArray(new Element[col.size()]);
    }

    public void addGlobalAttribute(Attribute attribute) {
        assert (attribute.isGlobal() && !attribute.isRef());
        this._globalAttributes.put(attribute.getName(), attribute);
    }

    public Attribute getGlobalAttribute(QName name) {
        return (Attribute)this._globalAttributes.get(name);
    }

    public Attribute[] getGlobalAttributes() {
        Collection col = this._globalAttributes.values();
        return col.toArray(new Attribute[col.size()]);
    }

    public void addGlobalType(Type type) {
        assert (type.isGlobal() && type.getName() != null) : "type must be a global type before being added.";
        this._globalTypes.put(type.getName(), type);
    }

    public Type getGlobalType(QName name) {
        return (Type)this._globalTypes.get(name);
    }

    public Type[] getGlobalTypes() {
        Collection col = this._globalTypes.values();
        return col.toArray(new Type[col.size()]);
    }

    public SchemaDocument[] getSchemaDocuments() {
        SchemaDocument schDoc;
        String tns;
        LinkedHashMap nsToSchemaDocs = new LinkedHashMap();
        for (QName globalElemName : this._globalElements.keySet()) {
            tns = globalElemName.getNamespaceURI();
            schDoc = TypeSystemHolder.getSchemaDocumentForTNS(nsToSchemaDocs, tns);
            this.fillUpGlobalElement((Element)this._globalElements.get(globalElemName), schDoc, tns);
        }
        for (QName globalAttName : this._globalAttributes.keySet()) {
            tns = globalAttName.getNamespaceURI();
            schDoc = TypeSystemHolder.getSchemaDocumentForTNS(nsToSchemaDocs, tns);
            this.fillUpGlobalAttribute((Attribute)this._globalAttributes.get(globalAttName), schDoc, tns);
        }
        for (QName globalTypeName : this._globalTypes.keySet()) {
            tns = globalTypeName.getNamespaceURI();
            schDoc = TypeSystemHolder.getSchemaDocumentForTNS(nsToSchemaDocs, tns);
            this.fillUpGlobalType((Type)this._globalTypes.get(globalTypeName), schDoc, tns);
        }
        Collection schDocColl = nsToSchemaDocs.values();
        return schDocColl.toArray(new SchemaDocument[schDocColl.size()]);
    }

    private static SchemaDocument getSchemaDocumentForTNS(Map nsToSchemaDocs, String tns) {
        SchemaDocument schDoc = (SchemaDocument)nsToSchemaDocs.get(tns);
        if (schDoc == null) {
            schDoc = SchemaDocument.Factory.newInstance();
            nsToSchemaDocs.put(tns, schDoc);
        }
        return schDoc;
    }

    private static SchemaDocument.Schema getTopLevelSchemaElement(SchemaDocument schDoc, String tns) {
        SchemaDocument.Schema sch = schDoc.getSchema();
        if (sch == null) {
            sch = schDoc.addNewSchema();
            sch.setAttributeFormDefault(FormChoice.Enum.forString("unqualified"));
            sch.setElementFormDefault(FormChoice.Enum.forString("qualified"));
            if (!tns.equals("")) {
                sch.setTargetNamespace(tns);
            }
        }
        return sch;
    }

    private void fillUpGlobalElement(Element globalElement, SchemaDocument schDoc, String tns) {
        assert (tns.equals(globalElement.getName().getNamespaceURI()));
        SchemaDocument.Schema sch = TypeSystemHolder.getTopLevelSchemaElement(schDoc, tns);
        TopLevelElement topLevelElem = sch.addNewElement();
        topLevelElem.setName(globalElement.getName().getLocalPart());
        if (globalElement.isNillable()) {
            topLevelElem.setNillable(globalElement.isNillable());
        }
        TypeSystemHolder.fillUpElementDocumentation(topLevelElem, globalElement.getComment());
        Type elemType = globalElement.getType();
        this.fillUpTypeOnElement(elemType, topLevelElem, tns);
    }

    protected void fillUpLocalElement(Element element, LocalElement localSElement, String tns) {
        TypeSystemHolder.fillUpElementDocumentation(localSElement, element.getComment());
        if (!element.isRef()) {
            assert (element.getName().getNamespaceURI().equals(tns) || element.getName().getNamespaceURI().length() == 0);
            this.fillUpTypeOnElement(element.getType(), localSElement, tns);
            localSElement.setName(element.getName().getLocalPart());
        } else {
            localSElement.setRef(element.getName());
            assert (!element.isNillable());
        }
        if (element.getMaxOccurs() == -1) {
            localSElement.setMaxOccurs("unbounded");
        }
        if (element.getMinOccurs() != 1) {
            localSElement.setMinOccurs(new BigInteger("" + element.getMinOccurs()));
        }
        if (element.isNillable()) {
            localSElement.setNillable(element.isNillable());
        }
    }

    private void fillUpTypeOnElement(Type elemType, org.apache.xmlbeans.impl.xb.xsdschema.Element parentSElement, String tns) {
        if (elemType.isGlobal()) {
            assert (elemType.getName() != null) : "Global type must have a name.";
            parentSElement.setType(elemType.getName());
        } else if (elemType.getContentType() == 1) {
            if (elemType.isEnumeration()) {
                this.fillUpEnumeration(elemType, parentSElement);
            } else {
                parentSElement.setType(elemType.getName());
            }
        } else {
            LocalComplexType localComplexType = parentSElement.addNewComplexType();
            this.fillUpContentForComplexType(elemType, localComplexType, tns);
        }
    }

    private void fillUpEnumeration(Type type, org.apache.xmlbeans.impl.xb.xsdschema.Element parentSElement) {
        assert (type.isEnumeration() && !type.isComplexType()) : "Enumerations must be on simple types only.";
        RestrictionDocument.Restriction restriction = parentSElement.addNewSimpleType().addNewRestriction();
        restriction.setBase(type.getName());
        if (type.isQNameEnumeration()) {
            for (int i = 0; i < type.getEnumerationQNames().size(); ++i) {
                QName value = (QName)type.getEnumerationQNames().get(i);
                XmlQName xqname = XmlQName.Factory.newValue(value);
                NoFixedFacet enumSElem = restriction.addNewEnumeration();
                XmlCursor xc = enumSElem.newCursor();
                String newPrefix = xc.prefixForNamespace(value.getNamespaceURI());
                xc.dispose();
                enumSElem.setValue(XmlQName.Factory.newValue(new QName(value.getNamespaceURI(), value.getLocalPart(), newPrefix)));
            }
        } else {
            for (int i = 0; i < type.getEnumerationValues().size(); ++i) {
                String value = (String)type.getEnumerationValues().get(i);
                restriction.addNewEnumeration().setValue(XmlString.Factory.newValue(value));
            }
        }
    }

    private void fillUpAttributesInComplexTypesSimpleContent(Type elemType, SimpleExtensionType sExtension, String tns) {
        for (int i = 0; i < elemType.getAttributes().size(); ++i) {
            Attribute att = (Attribute)elemType.getAttributes().get(i);
            org.apache.xmlbeans.impl.xb.xsdschema.Attribute sAttribute = sExtension.addNewAttribute();
            this.fillUpLocalAttribute(att, sAttribute, tns);
        }
    }

    private void fillUpAttributesInComplexTypesComplexContent(Type elemType, ComplexType localSComplexType, String tns) {
        for (int i = 0; i < elemType.getAttributes().size(); ++i) {
            Attribute att = (Attribute)elemType.getAttributes().get(i);
            org.apache.xmlbeans.impl.xb.xsdschema.Attribute sAttribute = localSComplexType.addNewAttribute();
            this.fillUpLocalAttribute(att, sAttribute, tns);
        }
    }

    protected void fillUpLocalAttribute(Attribute att, org.apache.xmlbeans.impl.xb.xsdschema.Attribute sAttribute, String tns) {
        if (att.isRef()) {
            sAttribute.setRef(att.getRef().getName());
        } else {
            assert (att.getName().getNamespaceURI() == tns || att.getName().getNamespaceURI().equals(""));
            sAttribute.setType(att.getType().getName());
            sAttribute.setName(att.getName().getLocalPart());
            if (att.isOptional()) {
                sAttribute.setUse(Attribute.Use.OPTIONAL);
            }
        }
    }

    protected void fillUpContentForComplexType(Type type, ComplexType sComplexType, String tns) {
        if (type.getContentType() == 2) {
            SimpleContentDocument.SimpleContent simpleContent = sComplexType.addNewSimpleContent();
            assert (type.getExtensionType() != null && type.getExtensionType().getName() != null) : "Extension type must exist and be named for a COMPLEX_TYPE_SIMPLE_CONTENT";
            SimpleExtensionType ext = simpleContent.addNewExtension();
            ext.setBase(type.getExtensionType().getName());
            this.fillUpAttributesInComplexTypesSimpleContent(type, ext, tns);
        } else {
            ExplicitGroup explicitGroup;
            if (type.getContentType() == 4) {
                sComplexType.setMixed(true);
            }
            if (type.getContentType() == 5) {
                explicitGroup = null;
            } else if (type.getTopParticleForComplexOrMixedContent() == 1) {
                explicitGroup = sComplexType.addNewSequence();
            } else if (type.getTopParticleForComplexOrMixedContent() == 2) {
                explicitGroup = sComplexType.addNewChoice();
                explicitGroup.setMaxOccurs("unbounded");
                explicitGroup.setMinOccurs(new BigInteger("0"));
            } else {
                throw new IllegalStateException("Unknown particle type in complex and mixed content");
            }
            for (int i = 0; i < type.getElements().size(); ++i) {
                Element child = (Element)type.getElements().get(i);
                assert (!child.isGlobal());
                LocalElement childLocalElement = explicitGroup.addNewElement();
                this.fillUpLocalElement(child, childLocalElement, tns);
            }
            this.fillUpAttributesInComplexTypesComplexContent(type, sComplexType, tns);
        }
    }

    private void fillUpGlobalAttribute(Attribute globalAttribute, SchemaDocument schDoc, String tns) {
        assert (tns.equals(globalAttribute.getName().getNamespaceURI()));
        SchemaDocument.Schema sch = TypeSystemHolder.getTopLevelSchemaElement(schDoc, tns);
        TopLevelAttribute topLevelAtt = sch.addNewAttribute();
        topLevelAtt.setName(globalAttribute.getName().getLocalPart());
        Type elemType = globalAttribute.getType();
        if (elemType.getContentType() != 1) {
            throw new IllegalStateException();
        }
        topLevelAtt.setType(elemType.getName());
    }

    private static void fillUpElementDocumentation(org.apache.xmlbeans.impl.xb.xsdschema.Element element, String comment) {
        if (comment != null && comment.length() > 0) {
            DocumentationDocument.Documentation documentation = element.addNewAnnotation().addNewDocumentation();
            documentation.set(XmlString.Factory.newValue(comment));
        }
    }

    private void fillUpGlobalType(Type globalType, SchemaDocument schDoc, String tns) {
        assert (tns.equals(globalType.getName().getNamespaceURI()));
        SchemaDocument.Schema sch = TypeSystemHolder.getTopLevelSchemaElement(schDoc, tns);
        TopLevelComplexType topLevelComplexType = sch.addNewComplexType();
        topLevelComplexType.setName(globalType.getName().getLocalPart());
        this.fillUpContentForComplexType(globalType, topLevelComplexType, tns);
    }

    public String toString() {
        return "TypeSystemHolder{\n\n_globalElements=" + this._globalElements + "\n\n_globalAttributes=" + this._globalAttributes + "\n\n_globalTypes=" + this._globalTypes + "\n}";
    }
}

