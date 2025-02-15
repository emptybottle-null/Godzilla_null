/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.IllegalAddException;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.tree.AbstractDocument;
import org.dom4j.tree.BackedList;
import org.dom4j.tree.ContentListFacade;
import org.xml.sax.EntityResolver;

public class DefaultDocument
extends AbstractDocument {
    private String name;
    private Element rootElement;
    private List<Node> content;
    private DocumentType docType;
    private DocumentFactory documentFactory = DocumentFactory.getInstance();
    private transient EntityResolver entityResolver;

    public DefaultDocument() {
    }

    public DefaultDocument(String name) {
        this.name = name;
    }

    public DefaultDocument(Element rootElement) {
        this.rootElement = rootElement;
    }

    public DefaultDocument(DocumentType docType) {
        this.docType = docType;
    }

    public DefaultDocument(Element rootElement, DocumentType docType) {
        this.rootElement = rootElement;
        this.docType = docType;
    }

    public DefaultDocument(String name, Element rootElement, DocumentType docType) {
        this.name = name;
        this.rootElement = rootElement;
        this.docType = docType;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Element getRootElement() {
        return this.rootElement;
    }

    @Override
    public DocumentType getDocType() {
        return this.docType;
    }

    @Override
    public void setDocType(DocumentType docType) {
        this.docType = docType;
    }

    @Override
    public Document addDocType(String docTypeName, String publicId, String systemId) {
        this.setDocType(this.getDocumentFactory().createDocType(docTypeName, publicId, systemId));
        return this;
    }

    @Override
    public String getXMLEncoding() {
        return this.encoding;
    }

    @Override
    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }

    @Override
    public void setEntityResolver(EntityResolver entityResolver) {
        this.entityResolver = entityResolver;
    }

    @Override
    public Object clone() {
        DefaultDocument document = (DefaultDocument)super.clone();
        document.rootElement = null;
        document.content = null;
        document.appendContent(this);
        return document;
    }

    @Override
    public List<ProcessingInstruction> processingInstructions() {
        BackedList<ProcessingInstruction> answer = this.createResultList();
        for (Node node : this.contentList()) {
            if (!(node instanceof ProcessingInstruction)) continue;
            answer.add((ProcessingInstruction)node);
        }
        return answer;
    }

    @Override
    public List<ProcessingInstruction> processingInstructions(String target) {
        BackedList<ProcessingInstruction> answer = this.createResultList();
        for (Node node : this.contentList()) {
            ProcessingInstruction pi;
            if (!(node instanceof ProcessingInstruction) || !target.equals((pi = (ProcessingInstruction)node).getName())) continue;
            answer.add(pi);
        }
        return answer;
    }

    @Override
    public ProcessingInstruction processingInstruction(String target) {
        for (Node node : this.contentList()) {
            ProcessingInstruction pi;
            if (!(node instanceof ProcessingInstruction) || !target.equals((pi = (ProcessingInstruction)node).getName())) continue;
            return pi;
        }
        return null;
    }

    @Override
    public boolean removeProcessingInstruction(String target) {
        Iterator<Node> iter = this.contentList().iterator();
        while (iter.hasNext()) {
            ProcessingInstruction pi;
            Node node = iter.next();
            if (!(node instanceof ProcessingInstruction) || !target.equals((pi = (ProcessingInstruction)node).getName())) continue;
            iter.remove();
            return true;
        }
        return false;
    }

    @Override
    public void setContent(List<Node> content) {
        this.rootElement = null;
        this.contentRemoved();
        if (content instanceof ContentListFacade) {
            content = ((ContentListFacade)content).getBackingList();
        }
        if (content == null) {
            this.content = null;
        } else {
            int size = content.size();
            List<Node> newContent = this.createContentList(size);
            for (Node node : content) {
                Document doc = node.getDocument();
                if (doc != null && doc != this) {
                    node = (Node)node.clone();
                }
                if (node instanceof Element) {
                    if (this.rootElement == null) {
                        this.rootElement = (Element)node;
                    } else {
                        throw new IllegalAddException("A document may only contain one root element: " + content);
                    }
                }
                newContent.add(node);
                this.childAdded(node);
            }
            this.content = newContent;
        }
    }

    @Override
    public void clearContent() {
        this.contentRemoved();
        this.content = null;
        this.rootElement = null;
    }

    public void setDocumentFactory(DocumentFactory documentFactory) {
        this.documentFactory = documentFactory;
    }

    @Override
    protected List<Node> contentList() {
        if (this.content == null) {
            this.content = this.createContentList();
            if (this.rootElement != null) {
                this.content.add(this.rootElement);
            }
        }
        return this.content;
    }

    @Override
    protected void addNode(Node node) {
        if (node != null) {
            Document document = node.getDocument();
            if (document != null && document != this) {
                String message = "The Node already has an existing document: " + document;
                throw new IllegalAddException(this, node, message);
            }
            this.contentList().add(node);
            this.childAdded(node);
        }
    }

    @Override
    protected void addNode(int index, Node node) {
        if (node != null) {
            Document document = node.getDocument();
            if (document != null && document != this) {
                String message = "The Node already has an existing document: " + document;
                throw new IllegalAddException(this, node, message);
            }
            this.contentList().add(index, node);
            this.childAdded(node);
        }
    }

    @Override
    protected boolean removeNode(Node node) {
        if (node == this.rootElement) {
            this.rootElement = null;
        }
        if (this.contentList().remove(node)) {
            this.childRemoved(node);
            return true;
        }
        return false;
    }

    @Override
    protected void rootElementAdded(Element element) {
        this.rootElement = element;
        element.setDocument(this);
    }

    @Override
    protected DocumentFactory getDocumentFactory() {
        return this.documentFactory;
    }
}

