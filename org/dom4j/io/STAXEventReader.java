/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import java.io.InputStream;
import java.io.Reader;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.dom4j.Attribute;
import org.dom4j.CharacterData;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;

public class STAXEventReader {
    private DocumentFactory factory;
    private XMLInputFactory inputFactory = XMLInputFactory.newInstance();

    public STAXEventReader() {
        this.factory = DocumentFactory.getInstance();
    }

    public STAXEventReader(DocumentFactory factory) {
        this.factory = factory != null ? factory : DocumentFactory.getInstance();
    }

    public void setDocumentFactory(DocumentFactory documentFactory) {
        this.factory = documentFactory != null ? documentFactory : DocumentFactory.getInstance();
    }

    public Document readDocument(InputStream is) throws XMLStreamException {
        return this.readDocument(is, null);
    }

    public Document readDocument(Reader reader) throws XMLStreamException {
        return this.readDocument(reader, null);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Document readDocument(InputStream is, String systemId) throws XMLStreamException {
        try (XMLEventReader eventReader = this.inputFactory.createXMLEventReader(systemId, is);){
            Document document = this.readDocument(eventReader);
            return document;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public Document readDocument(Reader reader, String systemId) throws XMLStreamException {
        try (XMLEventReader eventReader = this.inputFactory.createXMLEventReader(systemId, reader);){
            Document document = this.readDocument(eventReader);
            return document;
        }
    }

    public Node readNode(XMLEventReader reader) throws XMLStreamException {
        XMLEvent event = reader.peek();
        if (event.isStartElement()) {
            return this.readElement(reader);
        }
        if (event.isCharacters()) {
            return this.readCharacters(reader);
        }
        if (event.isStartDocument()) {
            return this.readDocument(reader);
        }
        if (event.isProcessingInstruction()) {
            return this.readProcessingInstruction(reader);
        }
        if (event.isEntityReference()) {
            return this.readEntityReference(reader);
        }
        if (event.isAttribute()) {
            return this.readAttribute(reader);
        }
        if (event.isNamespace()) {
            return this.readNamespace(reader);
        }
        throw new XMLStreamException("Unsupported event: " + event);
    }

    public Document readDocument(XMLEventReader reader) throws XMLStreamException {
        Document doc = null;
        block4: while (reader.hasNext()) {
            XMLEvent nextEvent = reader.peek();
            int type = nextEvent.getEventType();
            switch (type) {
                case 7: {
                    StartDocument event = (StartDocument)reader.nextEvent();
                    if (doc == null) {
                        if (event.encodingSet()) {
                            String encodingScheme = event.getCharacterEncodingScheme();
                            doc = this.factory.createDocument(encodingScheme);
                            continue block4;
                        }
                        doc = this.factory.createDocument();
                        continue block4;
                    }
                    String msg = "Unexpected StartDocument event";
                    throw new XMLStreamException(msg, event.getLocation());
                }
                case 4: 
                case 6: 
                case 8: {
                    reader.nextEvent();
                    continue block4;
                }
            }
            if (doc == null) {
                doc = this.factory.createDocument();
            }
            Node n = this.readNode(reader);
            doc.add(n);
        }
        return doc;
    }

    public Element readElement(XMLEventReader eventReader) throws XMLStreamException {
        XMLEvent event = eventReader.peek();
        if (event.isStartElement()) {
            StartElement startTag = eventReader.nextEvent().asStartElement();
            Element elem = this.createElement(startTag);
            while (true) {
                if (!eventReader.hasNext()) {
                    String msg = "Unexpected end of stream while reading element content";
                    throw new XMLStreamException(msg);
                }
                XMLEvent nextEvent = eventReader.peek();
                if (nextEvent.isEndElement()) {
                    EndElement endElem = eventReader.nextEvent().asEndElement();
                    if (endElem.getName().equals(startTag.getName())) break;
                    throw new XMLStreamException("Expected " + startTag.getName() + " end-tag, but found" + endElem.getName());
                }
                Node child = this.readNode(eventReader);
                elem.add(child);
            }
            return elem;
        }
        throw new XMLStreamException("Expected Element event, found: " + event);
    }

    public Attribute readAttribute(XMLEventReader reader) throws XMLStreamException {
        XMLEvent event = reader.peek();
        if (event.isAttribute()) {
            javax.xml.stream.events.Attribute attr = (javax.xml.stream.events.Attribute)reader.nextEvent();
            return this.createAttribute(null, attr);
        }
        throw new XMLStreamException("Expected Attribute event, found: " + event);
    }

    public Namespace readNamespace(XMLEventReader reader) throws XMLStreamException {
        XMLEvent event = reader.peek();
        if (event.isNamespace()) {
            javax.xml.stream.events.Namespace ns = (javax.xml.stream.events.Namespace)reader.nextEvent();
            return this.createNamespace(ns);
        }
        throw new XMLStreamException("Expected Namespace event, found: " + event);
    }

    public CharacterData readCharacters(XMLEventReader reader) throws XMLStreamException {
        XMLEvent event = reader.peek();
        if (event.isCharacters()) {
            Characters characters = reader.nextEvent().asCharacters();
            return this.createCharacterData(characters);
        }
        throw new XMLStreamException("Expected Characters event, found: " + event);
    }

    public Comment readComment(XMLEventReader reader) throws XMLStreamException {
        XMLEvent event = reader.peek();
        if (event instanceof javax.xml.stream.events.Comment) {
            return this.createComment((javax.xml.stream.events.Comment)reader.nextEvent());
        }
        throw new XMLStreamException("Expected Comment event, found: " + event);
    }

    public Entity readEntityReference(XMLEventReader reader) throws XMLStreamException {
        XMLEvent event = reader.peek();
        if (event.isEntityReference()) {
            EntityReference entityRef = (EntityReference)reader.nextEvent();
            return this.createEntity(entityRef);
        }
        throw new XMLStreamException("Expected EntityRef event, found: " + event);
    }

    public ProcessingInstruction readProcessingInstruction(XMLEventReader reader) throws XMLStreamException {
        XMLEvent event = reader.peek();
        if (event.isProcessingInstruction()) {
            javax.xml.stream.events.ProcessingInstruction pi = (javax.xml.stream.events.ProcessingInstruction)reader.nextEvent();
            return this.createProcessingInstruction(pi);
        }
        throw new XMLStreamException("Expected PI event, found: " + event);
    }

    public Element createElement(StartElement startEvent) {
        javax.xml.namespace.QName qname = startEvent.getName();
        QName elemName = this.createQName(qname);
        Element elem = this.factory.createElement(elemName);
        Iterator i = startEvent.getAttributes();
        while (i.hasNext()) {
            javax.xml.stream.events.Attribute attr = (javax.xml.stream.events.Attribute)i.next();
            elem.addAttribute(this.createQName(attr.getName()), attr.getValue());
        }
        i = startEvent.getNamespaces();
        while (i.hasNext()) {
            javax.xml.stream.events.Namespace ns = (javax.xml.stream.events.Namespace)i.next();
            elem.addNamespace(ns.getPrefix(), ns.getNamespaceURI());
        }
        return elem;
    }

    public Attribute createAttribute(Element elem, javax.xml.stream.events.Attribute attr) {
        return this.factory.createAttribute(elem, this.createQName(attr.getName()), attr.getValue());
    }

    public Namespace createNamespace(javax.xml.stream.events.Namespace ns) {
        return this.factory.createNamespace(ns.getPrefix(), ns.getNamespaceURI());
    }

    public CharacterData createCharacterData(Characters characters) {
        String data = characters.getData();
        if (characters.isCData()) {
            return this.factory.createCDATA(data);
        }
        return this.factory.createText(data);
    }

    public Comment createComment(javax.xml.stream.events.Comment comment) {
        return this.factory.createComment(comment.getText());
    }

    public Entity createEntity(EntityReference entityRef) {
        return this.factory.createEntity(entityRef.getName(), entityRef.getDeclaration().getReplacementText());
    }

    public ProcessingInstruction createProcessingInstruction(javax.xml.stream.events.ProcessingInstruction pi) {
        return this.factory.createProcessingInstruction(pi.getTarget(), pi.getData());
    }

    public QName createQName(javax.xml.namespace.QName qname) {
        return this.factory.createQName(qname.getLocalPart(), qname.getPrefix(), qname.getNamespaceURI());
    }
}

