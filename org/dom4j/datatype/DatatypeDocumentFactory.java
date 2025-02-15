/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.datatype;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.datatype.DatatypeElementFactory;
import org.dom4j.datatype.InvalidSchemaException;
import org.dom4j.datatype.SchemaParser;
import org.dom4j.io.SAXReader;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class DatatypeDocumentFactory
extends DocumentFactory {
    private static final boolean DO_INTERN_QNAME = false;
    protected static transient DatatypeDocumentFactory singleton = new DatatypeDocumentFactory();
    private static final Namespace XSI_NAMESPACE = Namespace.get("xsi", "http://www.w3.org/2001/XMLSchema-instance");
    private static final QName XSI_SCHEMA_LOCATION = QName.get("schemaLocation", XSI_NAMESPACE);
    private static final QName XSI_NO_SCHEMA_LOCATION = QName.get("noNamespaceSchemaLocation", XSI_NAMESPACE);
    private SchemaParser schemaBuilder;
    private SAXReader xmlSchemaReader = new SAXReader();
    private boolean autoLoadSchema = true;

    public DatatypeDocumentFactory() {
        this.schemaBuilder = new SchemaParser(this);
    }

    public static DocumentFactory getInstance() {
        return singleton;
    }

    public void loadSchema(Document schemaDocument) {
        this.schemaBuilder.build(schemaDocument);
    }

    public void loadSchema(Document schemaDocument, Namespace targetNamespace) {
        this.schemaBuilder.build(schemaDocument, targetNamespace);
    }

    public DatatypeElementFactory getElementFactory(QName elementQName) {
        DatatypeElementFactory result = null;
        DocumentFactory factory = elementQName.getDocumentFactory();
        if (factory instanceof DatatypeElementFactory) {
            result = (DatatypeElementFactory)factory;
        }
        return result;
    }

    @Override
    public Attribute createAttribute(Element owner, QName qname, String value) {
        if (this.autoLoadSchema && qname.equals(XSI_NO_SCHEMA_LOCATION)) {
            Document document = owner != null ? owner.getDocument() : null;
            this.loadSchema(document, value);
        } else if (this.autoLoadSchema && qname.equals(XSI_SCHEMA_LOCATION)) {
            Document document = owner != null ? owner.getDocument() : null;
            String uri = value.substring(0, value.indexOf(32));
            Namespace namespace = owner.getNamespaceForURI(uri);
            this.loadSchema(document, value.substring(value.indexOf(32) + 1), namespace);
        }
        return super.createAttribute(owner, qname, value);
    }

    protected void loadSchema(Document document, String schemaInstanceURI) {
        try {
            EntityResolver resolver = document.getEntityResolver();
            if (resolver == null) {
                String msg = "No EntityResolver available for resolving URI: ";
                throw new InvalidSchemaException(msg + schemaInstanceURI);
            }
            InputSource inputSource = resolver.resolveEntity(null, schemaInstanceURI);
            if (resolver == null) {
                throw new InvalidSchemaException("Could not resolve the URI: " + schemaInstanceURI);
            }
            Document schemaDocument = this.xmlSchemaReader.read(inputSource);
            this.loadSchema(schemaDocument);
        } catch (Exception e) {
            System.out.println("Failed to load schema: " + schemaInstanceURI);
            System.out.println("Caught: " + e);
            e.printStackTrace();
            throw new InvalidSchemaException("Failed to load schema: " + schemaInstanceURI);
        }
    }

    protected void loadSchema(Document document, String schemaInstanceURI, Namespace namespace) {
        try {
            EntityResolver resolver = document.getEntityResolver();
            if (resolver == null) {
                String msg = "No EntityResolver available for resolving URI: ";
                throw new InvalidSchemaException(msg + schemaInstanceURI);
            }
            InputSource inputSource = resolver.resolveEntity(null, schemaInstanceURI);
            if (resolver == null) {
                throw new InvalidSchemaException("Could not resolve the URI: " + schemaInstanceURI);
            }
            Document schemaDocument = this.xmlSchemaReader.read(inputSource);
            this.loadSchema(schemaDocument, namespace);
        } catch (Exception e) {
            System.out.println("Failed to load schema: " + schemaInstanceURI);
            System.out.println("Caught: " + e);
            e.printStackTrace();
            throw new InvalidSchemaException("Failed to load schema: " + schemaInstanceURI);
        }
    }
}

