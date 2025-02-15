/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.opc.internal.marshallers;

import java.io.OutputStream;
import java.util.Optional;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart;
import org.apache.poi.openxml4j.opc.internal.PartMarshaller;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class PackagePropertiesMarshaller
implements PartMarshaller {
    private static final NamespaceImpl namespaceDC = new NamespaceImpl("dc", "http://purl.org/dc/elements/1.1/");
    private static final NamespaceImpl namespaceCoreProperties = new NamespaceImpl("cp", "http://schemas.openxmlformats.org/package/2006/metadata/core-properties");
    private static final NamespaceImpl namespaceDcTerms = new NamespaceImpl("dcterms", "http://purl.org/dc/terms/");
    private static final NamespaceImpl namespaceXSI = new NamespaceImpl("xsi", "http://www.w3.org/2001/XMLSchema-instance");
    protected static final String KEYWORD_CATEGORY = "category";
    protected static final String KEYWORD_CONTENT_STATUS = "contentStatus";
    protected static final String KEYWORD_CONTENT_TYPE = "contentType";
    protected static final String KEYWORD_CREATED = "created";
    protected static final String KEYWORD_CREATOR = "creator";
    protected static final String KEYWORD_DESCRIPTION = "description";
    protected static final String KEYWORD_IDENTIFIER = "identifier";
    protected static final String KEYWORD_KEYWORDS = "keywords";
    protected static final String KEYWORD_LANGUAGE = "language";
    protected static final String KEYWORD_LAST_MODIFIED_BY = "lastModifiedBy";
    protected static final String KEYWORD_LAST_PRINTED = "lastPrinted";
    protected static final String KEYWORD_MODIFIED = "modified";
    protected static final String KEYWORD_REVISION = "revision";
    protected static final String KEYWORD_SUBJECT = "subject";
    protected static final String KEYWORD_TITLE = "title";
    protected static final String KEYWORD_VERSION = "version";
    PackagePropertiesPart propsPart;
    Document xmlDoc;

    @Override
    public boolean marshall(PackagePart part, OutputStream out) throws OpenXML4JException {
        if (!(part instanceof PackagePropertiesPart)) {
            throw new IllegalArgumentException("'part' must be a PackagePropertiesPart instance.");
        }
        this.propsPart = (PackagePropertiesPart)part;
        this.xmlDoc = DocumentHelper.createDocument();
        Element rootElem = this.xmlDoc.createElementNS(namespaceCoreProperties.getNamespaceURI(), this.getQName("coreProperties", namespaceCoreProperties));
        DocumentHelper.addNamespaceDeclaration(rootElem, namespaceCoreProperties.getPrefix(), namespaceCoreProperties.getNamespaceURI());
        DocumentHelper.addNamespaceDeclaration(rootElem, namespaceDC.getPrefix(), namespaceDC.getNamespaceURI());
        DocumentHelper.addNamespaceDeclaration(rootElem, namespaceDcTerms.getPrefix(), namespaceDcTerms.getNamespaceURI());
        DocumentHelper.addNamespaceDeclaration(rootElem, namespaceXSI.getPrefix(), namespaceXSI.getNamespaceURI());
        this.xmlDoc.appendChild(rootElem);
        this.addCategory();
        this.addContentStatus();
        this.addContentType();
        this.addCreated();
        this.addCreator();
        this.addDescription();
        this.addIdentifier();
        this.addKeywords();
        this.addLanguage();
        this.addLastModifiedBy();
        this.addLastPrinted();
        this.addModified();
        this.addRevision();
        this.addSubject();
        this.addTitle();
        this.addVersion();
        return true;
    }

    private Element setElementTextContent(String localName, NamespaceImpl namespace, Optional<String> property) {
        return this.setElementTextContent(localName, namespace, property, property.orElse(null));
    }

    private String getQName(String localName, NamespaceImpl namespace) {
        return namespace.getPrefix().isEmpty() ? localName : namespace.getPrefix() + ':' + localName;
    }

    private Element setElementTextContent(String localName, NamespaceImpl namespace, Optional<?> property, String propertyValue) {
        if (!property.isPresent()) {
            return null;
        }
        Element root = this.xmlDoc.getDocumentElement();
        Element elem = (Element)root.getElementsByTagNameNS(namespace.getNamespaceURI(), localName).item(0);
        if (elem == null) {
            elem = this.xmlDoc.createElementNS(namespace.getNamespaceURI(), this.getQName(localName, namespace));
            root.appendChild(elem);
        }
        elem.setTextContent(propertyValue);
        return elem;
    }

    private Element setElementTextContent(String localName, NamespaceImpl namespace, Optional<?> property, String propertyValue, String xsiType) {
        Element element = this.setElementTextContent(localName, namespace, property, propertyValue);
        if (element != null) {
            element.setAttributeNS(namespaceXSI.getNamespaceURI(), this.getQName("type", namespaceXSI), xsiType);
        }
        return element;
    }

    private void addCategory() {
        this.setElementTextContent(KEYWORD_CATEGORY, namespaceCoreProperties, this.propsPart.getCategoryProperty());
    }

    private void addContentStatus() {
        this.setElementTextContent(KEYWORD_CONTENT_STATUS, namespaceCoreProperties, this.propsPart.getContentStatusProperty());
    }

    private void addContentType() {
        this.setElementTextContent(KEYWORD_CONTENT_TYPE, namespaceCoreProperties, this.propsPart.getContentTypeProperty());
    }

    private void addCreated() {
        this.setElementTextContent(KEYWORD_CREATED, namespaceDcTerms, this.propsPart.getCreatedProperty(), this.propsPart.getCreatedPropertyString(), "dcterms:W3CDTF");
    }

    private void addCreator() {
        this.setElementTextContent(KEYWORD_CREATOR, namespaceDC, this.propsPart.getCreatorProperty());
    }

    private void addDescription() {
        this.setElementTextContent(KEYWORD_DESCRIPTION, namespaceDC, this.propsPart.getDescriptionProperty());
    }

    private void addIdentifier() {
        this.setElementTextContent(KEYWORD_IDENTIFIER, namespaceDC, this.propsPart.getIdentifierProperty());
    }

    private void addKeywords() {
        this.setElementTextContent(KEYWORD_KEYWORDS, namespaceCoreProperties, this.propsPart.getKeywordsProperty());
    }

    private void addLanguage() {
        this.setElementTextContent(KEYWORD_LANGUAGE, namespaceDC, this.propsPart.getLanguageProperty());
    }

    private void addLastModifiedBy() {
        this.setElementTextContent(KEYWORD_LAST_MODIFIED_BY, namespaceCoreProperties, this.propsPart.getLastModifiedByProperty());
    }

    private void addLastPrinted() {
        this.setElementTextContent(KEYWORD_LAST_PRINTED, namespaceCoreProperties, this.propsPart.getLastPrintedProperty(), this.propsPart.getLastPrintedPropertyString());
    }

    private void addModified() {
        this.setElementTextContent(KEYWORD_MODIFIED, namespaceDcTerms, this.propsPart.getModifiedProperty(), this.propsPart.getModifiedPropertyString(), "dcterms:W3CDTF");
    }

    private void addRevision() {
        this.setElementTextContent(KEYWORD_REVISION, namespaceCoreProperties, this.propsPart.getRevisionProperty());
    }

    private void addSubject() {
        this.setElementTextContent(KEYWORD_SUBJECT, namespaceDC, this.propsPart.getSubjectProperty());
    }

    private void addTitle() {
        this.setElementTextContent(KEYWORD_TITLE, namespaceDC, this.propsPart.getTitleProperty());
    }

    private void addVersion() {
        this.setElementTextContent(KEYWORD_VERSION, namespaceCoreProperties, this.propsPart.getVersionProperty());
    }

    private static class NamespaceImpl {
        private final String prefix;
        private final String namespaceURI;

        NamespaceImpl(String prefix, String namespaceURI) {
            this.prefix = prefix;
            this.namespaceURI = namespaceURI;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public String getNamespaceURI() {
            return this.namespaceURI;
        }
    }
}

