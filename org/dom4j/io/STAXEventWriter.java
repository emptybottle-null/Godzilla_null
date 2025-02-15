/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Iterator;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.DTD;
import javax.xml.stream.events.EndDocument;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.EntityReference;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.util.XMLEventConsumer;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;

public class STAXEventWriter {
    private XMLEventConsumer consumer;
    private XMLEventFactory factory = XMLEventFactory.newInstance();
    private XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

    public STAXEventWriter() {
    }

    public STAXEventWriter(File file) throws XMLStreamException, IOException {
        this.consumer = this.outputFactory.createXMLEventWriter(new FileWriter(file));
    }

    public STAXEventWriter(Writer writer) throws XMLStreamException {
        this.consumer = this.outputFactory.createXMLEventWriter(writer);
    }

    public STAXEventWriter(OutputStream stream) throws XMLStreamException {
        this.consumer = this.outputFactory.createXMLEventWriter(stream);
    }

    public STAXEventWriter(XMLEventConsumer consumer) {
        this.consumer = consumer;
    }

    public XMLEventConsumer getConsumer() {
        return this.consumer;
    }

    public void setConsumer(XMLEventConsumer consumer) {
        this.consumer = consumer;
    }

    public XMLEventFactory getEventFactory() {
        return this.factory;
    }

    public void setEventFactory(XMLEventFactory eventFactory) {
        this.factory = eventFactory;
    }

    public void writeNode(Node n) throws XMLStreamException {
        switch (n.getNodeType()) {
            case 1: {
                this.writeElement((Element)n);
                break;
            }
            case 3: {
                this.writeText((Text)n);
                break;
            }
            case 2: {
                this.writeAttribute((Attribute)n);
                break;
            }
            case 13: {
                this.writeNamespace((Namespace)n);
                break;
            }
            case 8: {
                this.writeComment((Comment)n);
                break;
            }
            case 4: {
                this.writeCDATA((CDATA)n);
                break;
            }
            case 7: {
                this.writeProcessingInstruction((ProcessingInstruction)n);
                break;
            }
            case 5: {
                this.writeEntity((Entity)n);
                break;
            }
            case 9: {
                this.writeDocument((Document)n);
                break;
            }
            case 10: {
                this.writeDocumentType((DocumentType)n);
                break;
            }
            default: {
                throw new XMLStreamException("Unsupported DOM4J Node: " + n);
            }
        }
    }

    public void writeChildNodes(Branch branch) throws XMLStreamException {
        int s = branch.nodeCount();
        for (int i = 0; i < s; ++i) {
            Node n = branch.node(i);
            this.writeNode(n);
        }
    }

    public void writeElement(Element elem) throws XMLStreamException {
        this.consumer.add(this.createStartElement(elem));
        this.writeChildNodes(elem);
        this.consumer.add(this.createEndElement(elem));
    }

    public StartElement createStartElement(Element elem) {
        javax.xml.namespace.QName tagName = this.createQName(elem.getQName());
        AttributeIterator attrIter = new AttributeIterator(elem.attributeIterator());
        NamespaceIterator nsIter = new NamespaceIterator(elem.declaredNamespaces().iterator());
        return this.factory.createStartElement(tagName, attrIter, nsIter);
    }

    public EndElement createEndElement(Element elem) {
        javax.xml.namespace.QName tagName = this.createQName(elem.getQName());
        NamespaceIterator nsIter = new NamespaceIterator(elem.declaredNamespaces().iterator());
        return this.factory.createEndElement(tagName, nsIter);
    }

    public void writeAttribute(Attribute attr) throws XMLStreamException {
        this.consumer.add(this.createAttribute(attr));
    }

    public javax.xml.stream.events.Attribute createAttribute(Attribute attr) {
        javax.xml.namespace.QName attrName = this.createQName(attr.getQName());
        String value = attr.getValue();
        return this.factory.createAttribute(attrName, value);
    }

    public void writeNamespace(Namespace ns) throws XMLStreamException {
        this.consumer.add(this.createNamespace(ns));
    }

    public javax.xml.stream.events.Namespace createNamespace(Namespace ns) {
        String prefix = ns.getPrefix();
        String uri = ns.getURI();
        return this.factory.createNamespace(prefix, uri);
    }

    public void writeText(Text text) throws XMLStreamException {
        this.consumer.add(this.createCharacters(text));
    }

    public Characters createCharacters(Text text) {
        return this.factory.createCharacters(text.getText());
    }

    public void writeCDATA(CDATA cdata) throws XMLStreamException {
        this.consumer.add(this.createCharacters(cdata));
    }

    public Characters createCharacters(CDATA cdata) {
        return this.factory.createCData(cdata.getText());
    }

    public void writeComment(Comment comment) throws XMLStreamException {
        this.consumer.add(this.createComment(comment));
    }

    public javax.xml.stream.events.Comment createComment(Comment comment) {
        return this.factory.createComment(comment.getText());
    }

    public void writeProcessingInstruction(ProcessingInstruction pi) throws XMLStreamException {
        this.consumer.add(this.createProcessingInstruction(pi));
    }

    public javax.xml.stream.events.ProcessingInstruction createProcessingInstruction(ProcessingInstruction pi) {
        String target = pi.getTarget();
        String data = pi.getText();
        return this.factory.createProcessingInstruction(target, data);
    }

    public void writeEntity(Entity entity) throws XMLStreamException {
        this.consumer.add(this.createEntityReference(entity));
    }

    private EntityReference createEntityReference(Entity entity) {
        return this.factory.createEntityReference(entity.getName(), null);
    }

    public void writeDocumentType(DocumentType docType) throws XMLStreamException {
        this.consumer.add(this.createDTD(docType));
    }

    public DTD createDTD(DocumentType docType) {
        StringWriter decl = new StringWriter();
        try {
            docType.write(decl);
        } catch (IOException e) {
            throw new RuntimeException("Error writing DTD", e);
        }
        return this.factory.createDTD(decl.toString());
    }

    public void writeDocument(Document doc) throws XMLStreamException {
        this.consumer.add(this.createStartDocument(doc));
        this.writeChildNodes(doc);
        this.consumer.add(this.createEndDocument(doc));
    }

    public StartDocument createStartDocument(Document doc) {
        String encoding = doc.getXMLEncoding();
        if (encoding != null) {
            return this.factory.createStartDocument(encoding);
        }
        return this.factory.createStartDocument();
    }

    public EndDocument createEndDocument(Document doc) {
        return this.factory.createEndDocument();
    }

    public javax.xml.namespace.QName createQName(QName qname) {
        return new javax.xml.namespace.QName(qname.getNamespaceURI(), qname.getName(), qname.getNamespacePrefix());
    }

    private class NamespaceIterator
    implements Iterator<javax.xml.stream.events.Namespace> {
        private Iterator<Namespace> iter;

        public NamespaceIterator(Iterator<Namespace> iter) {
            this.iter = iter;
        }

        @Override
        public boolean hasNext() {
            return this.iter.hasNext();
        }

        @Override
        public javax.xml.stream.events.Namespace next() {
            Namespace ns = this.iter.next();
            String prefix = ns.getPrefix();
            String nsURI = ns.getURI();
            return STAXEventWriter.this.factory.createNamespace(prefix, nsURI);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class AttributeIterator
    implements Iterator<javax.xml.stream.events.Attribute> {
        private Iterator<Attribute> iter;

        public AttributeIterator(Iterator<Attribute> iter) {
            this.iter = iter;
        }

        @Override
        public boolean hasNext() {
            return this.iter.hasNext();
        }

        @Override
        public javax.xml.stream.events.Attribute next() {
            Attribute attr = this.iter.next();
            javax.xml.namespace.QName attrName = STAXEventWriter.this.createQName(attr.getQName());
            String value = attr.getValue();
            return STAXEventWriter.this.factory.createAttribute(attrName, value);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

