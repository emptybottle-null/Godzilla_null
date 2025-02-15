/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  com.sun.msv.datatype.xsd.DatatypeFactory
 *  com.sun.msv.datatype.xsd.TypeIncubator
 *  com.sun.msv.datatype.xsd.XSDatatype
 *  org.relaxng.datatype.DatatypeException
 *  org.relaxng.datatype.ValidationContext
 */
package org.dom4j.datatype;

import com.sun.msv.datatype.xsd.DatatypeFactory;
import com.sun.msv.datatype.xsd.TypeIncubator;
import com.sun.msv.datatype.xsd.XSDatatype;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.datatype.DatatypeDocumentFactory;
import org.dom4j.datatype.DatatypeElementFactory;
import org.dom4j.datatype.InvalidSchemaException;
import org.dom4j.datatype.NamedTypeResolver;
import org.dom4j.io.SAXReader;
import org.dom4j.util.AttributeHelper;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.ValidationContext;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class SchemaParser {
    private static final Namespace XSD_NAMESPACE = Namespace.get("xsd", "http://www.w3.org/2001/XMLSchema");
    private static final QName XSD_ELEMENT = QName.get("element", XSD_NAMESPACE);
    private static final QName XSD_ATTRIBUTE = QName.get("attribute", XSD_NAMESPACE);
    private static final QName XSD_SIMPLETYPE = QName.get("simpleType", XSD_NAMESPACE);
    private static final QName XSD_COMPLEXTYPE = QName.get("complexType", XSD_NAMESPACE);
    private static final QName XSD_RESTRICTION = QName.get("restriction", XSD_NAMESPACE);
    private static final QName XSD_SEQUENCE = QName.get("sequence", XSD_NAMESPACE);
    private static final QName XSD_CHOICE = QName.get("choice", XSD_NAMESPACE);
    private static final QName XSD_ALL = QName.get("all", XSD_NAMESPACE);
    private static final QName XSD_INCLUDE = QName.get("include", XSD_NAMESPACE);
    private DatatypeDocumentFactory documentFactory;
    private Map<String, XSDatatype> dataTypeCache = new HashMap<String, XSDatatype>();
    private NamedTypeResolver namedTypeResolver;
    private Namespace targetNamespace;

    public SchemaParser() {
        this(DatatypeDocumentFactory.singleton);
    }

    public SchemaParser(DatatypeDocumentFactory documentFactory) {
        this.documentFactory = documentFactory;
        this.namedTypeResolver = new NamedTypeResolver(documentFactory);
    }

    public void build(Document schemaDocument) {
        this.targetNamespace = null;
        this.internalBuild(schemaDocument);
    }

    public void build(Document schemaDocument, Namespace namespace) {
        this.targetNamespace = namespace;
        this.internalBuild(schemaDocument);
    }

    private synchronized void internalBuild(Document schemaDocument) {
        Element root = schemaDocument.getRootElement();
        if (root != null) {
            for (Element includeElement : root.elements(XSD_INCLUDE)) {
                String inclSchemaInstanceURI = includeElement.attributeValue("schemaLocation");
                EntityResolver resolver = schemaDocument.getEntityResolver();
                try {
                    if (resolver == null) {
                        String msg = "No EntityResolver available";
                        throw new InvalidSchemaException(msg);
                    }
                    InputSource inputSource = resolver.resolveEntity(null, inclSchemaInstanceURI);
                    if (inputSource == null) {
                        String msg = "Could not resolve the schema URI: " + inclSchemaInstanceURI;
                        throw new InvalidSchemaException(msg);
                    }
                    SAXReader reader = new SAXReader();
                    Document inclSchemaDocument = reader.read(inputSource);
                    this.build(inclSchemaDocument);
                } catch (Exception e) {
                    System.out.println("Failed to load schema: " + inclSchemaInstanceURI);
                    System.out.println("Caught: " + e);
                    e.printStackTrace();
                    throw new InvalidSchemaException("Failed to load schema: " + inclSchemaInstanceURI);
                }
            }
            for (Element element : root.elements(XSD_ELEMENT)) {
                this.onDatatypeElement(element, this.documentFactory);
            }
            for (Element element : root.elements(XSD_SIMPLETYPE)) {
                this.onNamedSchemaSimpleType(element);
            }
            for (Element element : root.elements(XSD_COMPLEXTYPE)) {
                this.onNamedSchemaComplexType(element);
            }
            this.namedTypeResolver.resolveNamedTypes();
        }
    }

    private void onDatatypeElement(Element xsdElement, DocumentFactory parentFactory) {
        Iterator<Element> iter;
        Element schemaComplexType;
        XSDatatype dataType;
        String name = xsdElement.attributeValue("name");
        String type = xsdElement.attributeValue("type");
        QName qname = null;
        DatatypeElementFactory factory = null;
        if (name != null) {
            qname = this.getQName(name);
            factory = this.getDatatypeElementFactory(qname);
        }
        if (type != null) {
            XSDatatype dataType2 = this.getTypeByName(type);
            if (dataType2 != null && factory != null) {
                factory.setChildElementXSDatatype(qname, dataType2);
            } else {
                QName typeQName = this.getQName(type);
                this.namedTypeResolver.registerTypedElement(xsdElement, typeQName, parentFactory);
            }
            return;
        }
        Element xsdSimpleType = xsdElement.element(XSD_SIMPLETYPE);
        if (xsdSimpleType != null && (dataType = this.loadXSDatatypeFromSimpleType(xsdSimpleType)) != null && factory != null) {
            factory.setChildElementXSDatatype(qname, dataType);
        }
        if ((schemaComplexType = xsdElement.element(XSD_COMPLEXTYPE)) != null && factory != null) {
            this.onSchemaComplexType(schemaComplexType, factory);
        }
        if (factory != null && (iter = xsdElement.elementIterator(XSD_ATTRIBUTE)).hasNext()) {
            do {
                this.onDatatypeAttribute(xsdElement, factory, iter.next());
            } while (iter.hasNext());
        }
    }

    private void onNamedSchemaComplexType(Element schemaComplexType) {
        Attribute nameAttr = schemaComplexType.attribute("name");
        if (nameAttr == null) {
            return;
        }
        String name = nameAttr.getText();
        QName qname = this.getQName(name);
        DatatypeElementFactory factory = this.getDatatypeElementFactory(qname);
        this.onSchemaComplexType(schemaComplexType, factory);
        this.namedTypeResolver.registerComplexType(qname, factory);
    }

    private void onSchemaComplexType(Element schemaComplexType, DatatypeElementFactory elementFactory) {
        Element schemaAll;
        Element schemaChoice;
        Iterator<Element> iter = schemaComplexType.elementIterator(XSD_ATTRIBUTE);
        while (iter.hasNext()) {
            Element xsdAttribute = iter.next();
            String name = xsdAttribute.attributeValue("name");
            QName qname = this.getQName(name);
            XSDatatype dataType = this.dataTypeForXsdAttribute(xsdAttribute);
            if (dataType == null) continue;
            elementFactory.setAttributeXSDatatype(qname, dataType);
        }
        Element schemaSequence = schemaComplexType.element(XSD_SEQUENCE);
        if (schemaSequence != null) {
            this.onChildElements(schemaSequence, elementFactory);
        }
        if ((schemaChoice = schemaComplexType.element(XSD_CHOICE)) != null) {
            this.onChildElements(schemaChoice, elementFactory);
        }
        if ((schemaAll = schemaComplexType.element(XSD_ALL)) != null) {
            this.onChildElements(schemaAll, elementFactory);
        }
    }

    private void onChildElements(Element element, DatatypeElementFactory fact) {
        Iterator<Element> iter = element.elementIterator(XSD_ELEMENT);
        while (iter.hasNext()) {
            Element xsdElement = iter.next();
            this.onDatatypeElement(xsdElement, fact);
        }
    }

    private void onDatatypeAttribute(Element xsdElement, DatatypeElementFactory elementFactory, Element xsdAttribute) {
        String name = xsdAttribute.attributeValue("name");
        QName qname = this.getQName(name);
        XSDatatype dataType = this.dataTypeForXsdAttribute(xsdAttribute);
        if (dataType != null) {
            elementFactory.setAttributeXSDatatype(qname, dataType);
        } else {
            String type = xsdAttribute.attributeValue("type");
            System.out.println("Warning: Couldn't find XSDatatype for type: " + type + " attribute: " + name);
        }
    }

    private XSDatatype dataTypeForXsdAttribute(Element xsdAttribute) {
        XSDatatype dataType;
        String type = xsdAttribute.attributeValue("type");
        if (type != null) {
            dataType = this.getTypeByName(type);
        } else {
            Element xsdSimpleType = xsdAttribute.element(XSD_SIMPLETYPE);
            if (xsdSimpleType == null) {
                String name = xsdAttribute.attributeValue("name");
                String msg = "The attribute: " + name + " has no type attribute and does not contain a <simpleType/> element";
                throw new InvalidSchemaException(msg);
            }
            dataType = this.loadXSDatatypeFromSimpleType(xsdSimpleType);
        }
        return dataType;
    }

    private void onNamedSchemaSimpleType(Element schemaSimpleType) {
        Attribute nameAttr = schemaSimpleType.attribute("name");
        if (nameAttr == null) {
            return;
        }
        String name = nameAttr.getText();
        QName qname = this.getQName(name);
        XSDatatype datatype = this.loadXSDatatypeFromSimpleType(schemaSimpleType);
        this.namedTypeResolver.registerSimpleType(qname, datatype);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private XSDatatype loadXSDatatypeFromSimpleType(Element xsdSimpleType) {
        Element xsdRestriction = xsdSimpleType.element(XSD_RESTRICTION);
        if (xsdRestriction != null) {
            String base = xsdRestriction.attributeValue("base");
            if (base != null) {
                XSDatatype baseType = this.getTypeByName(base);
                if (baseType != null) return this.deriveSimpleType(baseType, xsdRestriction);
                this.onSchemaError("Invalid base type: " + base + " when trying to build restriction: " + xsdRestriction);
                return null;
            } else {
                Element xsdSubType = xsdSimpleType.element(XSD_SIMPLETYPE);
                if (xsdSubType != null) return this.loadXSDatatypeFromSimpleType(xsdSubType);
                String msg = "The simpleType element: " + xsdSimpleType + " must contain a base attribute or simpleType element";
                this.onSchemaError(msg);
            }
            return null;
        } else {
            this.onSchemaError("No <restriction>. Could not create XSDatatype for simpleType: " + xsdSimpleType);
        }
        return null;
    }

    private XSDatatype deriveSimpleType(XSDatatype baseType, Element xsdRestriction) {
        TypeIncubator incubator = new TypeIncubator(baseType);
        ValidationContext context = null;
        try {
            Iterator<Element> iter = xsdRestriction.elementIterator();
            while (iter.hasNext()) {
                Element element = iter.next();
                String name = element.getName();
                String value = element.attributeValue("value");
                boolean fixed = AttributeHelper.booleanValue(element, "fixed");
                incubator.addFacet(name, value, fixed, context);
            }
            String newTypeName = null;
            return incubator.derive("", newTypeName);
        } catch (DatatypeException e) {
            this.onSchemaError("Invalid restriction: " + e.getMessage() + " when trying to build restriction: " + xsdRestriction);
            return null;
        }
    }

    private DatatypeElementFactory getDatatypeElementFactory(QName name) {
        DatatypeElementFactory factory = this.documentFactory.getElementFactory(name);
        if (factory == null) {
            factory = new DatatypeElementFactory(name);
            name.setDocumentFactory(factory);
        }
        return factory;
    }

    private XSDatatype getTypeByName(String type) {
        XSDatatype dataType = this.dataTypeCache.get(type);
        if (dataType == null) {
            int idx = type.indexOf(58);
            if (idx >= 0) {
                String localName = type.substring(idx + 1);
                try {
                    dataType = DatatypeFactory.getTypeByName((String)localName);
                } catch (DatatypeException datatypeException) {
                    // empty catch block
                }
            }
            if (dataType == null) {
                try {
                    dataType = DatatypeFactory.getTypeByName((String)type);
                } catch (DatatypeException localName) {
                    // empty catch block
                }
            }
            if (dataType == null) {
                QName typeQName = this.getQName(type);
                dataType = this.namedTypeResolver.simpleTypeMap.get(typeQName);
            }
            if (dataType != null) {
                this.dataTypeCache.put(type, dataType);
            }
        }
        return dataType;
    }

    private QName getQName(String name) {
        if (this.targetNamespace == null) {
            return this.documentFactory.createQName(name);
        }
        return this.documentFactory.createQName(name, this.targetNamespace);
    }

    private void onSchemaError(String message) {
        throw new InvalidSchemaException(message);
    }
}

