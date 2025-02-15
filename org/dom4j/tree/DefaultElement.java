/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.IllegalAddException;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.tree.AbstractElement;
import org.dom4j.tree.BackedList;
import org.dom4j.tree.ContentListFacade;

public class DefaultElement
extends AbstractElement {
    private static final transient DocumentFactory DOCUMENT_FACTORY = DocumentFactory.getInstance();
    private QName qname;
    private Branch parentBranch;
    private Object content;
    private Object attributes;

    public DefaultElement(String name) {
        this.qname = DOCUMENT_FACTORY.createQName(name);
    }

    public DefaultElement(QName qname) {
        this.qname = qname;
    }

    public DefaultElement(QName qname, int attributeCount) {
        this.qname = qname;
        if (attributeCount > 1) {
            this.attributes = new ArrayList(attributeCount);
        }
    }

    public DefaultElement(String name, Namespace namespace) {
        this.qname = DOCUMENT_FACTORY.createQName(name, namespace);
    }

    @Override
    public Element getParent() {
        Element result = null;
        if (this.parentBranch instanceof Element) {
            result = (Element)this.parentBranch;
        }
        return result;
    }

    @Override
    public void setParent(Element parent) {
        if (this.parentBranch instanceof Element || parent != null) {
            this.parentBranch = parent;
        }
    }

    @Override
    public Document getDocument() {
        if (this.parentBranch instanceof Document) {
            return (Document)this.parentBranch;
        }
        if (this.parentBranch instanceof Element) {
            Element parent = (Element)this.parentBranch;
            return parent.getDocument();
        }
        return null;
    }

    @Override
    public void setDocument(Document document) {
        if (this.parentBranch instanceof Document || document != null) {
            this.parentBranch = document;
        }
    }

    @Override
    public boolean supportsParent() {
        return true;
    }

    @Override
    public QName getQName() {
        return this.qname;
    }

    @Override
    public void setQName(QName name) {
        this.qname = name;
    }

    @Override
    public String getText() {
        Object contentShadow = this.content;
        if (contentShadow instanceof List) {
            return super.getText();
        }
        if (contentShadow != null) {
            return this.getContentAsText(contentShadow);
        }
        return "";
    }

    @Override
    public String getStringValue() {
        Object contentShadow = this.content;
        if (contentShadow instanceof List) {
            List list = (List)contentShadow;
            int size = list.size();
            if (size > 0) {
                if (size == 1) {
                    return this.getContentAsStringValue(list.get(0));
                }
                StringBuilder buffer = new StringBuilder();
                for (Node node : list) {
                    String string = this.getContentAsStringValue(node);
                    if (string.length() <= 0) continue;
                    buffer.append(string);
                }
                return buffer.toString();
            }
        } else if (contentShadow != null) {
            return this.getContentAsStringValue(contentShadow);
        }
        return "";
    }

    @Override
    public Object clone() {
        DefaultElement answer = (DefaultElement)super.clone();
        if (answer != this) {
            answer.content = null;
            answer.attributes = null;
            answer.appendAttributes(this);
            answer.appendContent(this);
        }
        return answer;
    }

    @Override
    public Namespace getNamespaceForPrefix(String prefix) {
        Namespace answer;
        Element parent;
        Namespace namespace;
        if (prefix == null) {
            prefix = "";
        }
        if (prefix.equals(this.getNamespacePrefix())) {
            return this.getNamespace();
        }
        if (prefix.equals("xml")) {
            return Namespace.XML_NAMESPACE;
        }
        Object contentShadow = this.content;
        if (contentShadow instanceof List) {
            List list = (List)contentShadow;
            for (Node node : list) {
                Namespace namespace2;
                if (!(node instanceof Namespace) || !prefix.equals((namespace2 = (Namespace)node).getPrefix())) continue;
                return namespace2;
            }
        } else if (contentShadow instanceof Namespace && prefix.equals((namespace = (Namespace)contentShadow).getPrefix())) {
            return namespace;
        }
        if ((parent = this.getParent()) != null && (answer = parent.getNamespaceForPrefix(prefix)) != null) {
            return answer;
        }
        if (prefix.length() <= 0) {
            return Namespace.NO_NAMESPACE;
        }
        return null;
    }

    @Override
    public Namespace getNamespaceForURI(String uri) {
        Element parent;
        Namespace namespace;
        if (uri == null || uri.length() <= 0) {
            return Namespace.NO_NAMESPACE;
        }
        if (uri.equals(this.getNamespaceURI())) {
            return this.getNamespace();
        }
        Object contentShadow = this.content;
        if (contentShadow instanceof List) {
            List list = (List)contentShadow;
            for (Node node : list) {
                Namespace namespace2;
                if (!(node instanceof Namespace) || !uri.equals((namespace2 = (Namespace)node).getURI())) continue;
                return namespace2;
            }
        } else if (contentShadow instanceof Namespace && uri.equals((namespace = (Namespace)contentShadow).getURI())) {
            return namespace;
        }
        if ((parent = this.getParent()) != null) {
            return parent.getNamespaceForURI(uri);
        }
        return null;
    }

    @Override
    public List<Namespace> declaredNamespaces() {
        BackedList<Namespace> answer = this.createResultList();
        Object contentShadow = this.content;
        if (contentShadow instanceof List) {
            List list = (List)contentShadow;
            for (Node node : list) {
                if (!(node instanceof Namespace)) continue;
                answer.addLocal((Namespace)node);
            }
        } else if (contentShadow instanceof Namespace) {
            answer.addLocal((Namespace)contentShadow);
        }
        return answer;
    }

    @Override
    public List<Namespace> additionalNamespaces() {
        Object contentShadow = this.content;
        if (contentShadow instanceof List) {
            List list = (List)contentShadow;
            BackedList<Namespace> answer = this.createResultList();
            for (Node node : list) {
                Namespace namespace;
                if (!(node instanceof Namespace) || (namespace = (Namespace)node).equals(this.getNamespace())) continue;
                answer.addLocal(namespace);
            }
            return answer;
        }
        if (contentShadow instanceof Namespace) {
            Namespace namespace = (Namespace)contentShadow;
            if (namespace.equals(this.getNamespace())) {
                return this.createEmptyList();
            }
            return this.createSingleResultList(namespace);
        }
        return this.createEmptyList();
    }

    @Override
    public List<Namespace> additionalNamespaces(String defaultNamespaceURI) {
        Namespace namespace;
        Object contentShadow = this.content;
        if (contentShadow instanceof List) {
            List list = (List)contentShadow;
            BackedList<Namespace> answer = this.createResultList();
            for (Node node : list) {
                Namespace namespace2;
                if (!(node instanceof Namespace) || defaultNamespaceURI.equals((namespace2 = (Namespace)node).getURI())) continue;
                answer.addLocal(namespace2);
            }
            return answer;
        }
        if (contentShadow instanceof Namespace && !defaultNamespaceURI.equals((namespace = (Namespace)contentShadow).getURI())) {
            return this.createSingleResultList(namespace);
        }
        return this.createEmptyList();
    }

    @Override
    public List<ProcessingInstruction> processingInstructions() {
        Object contentShadow = this.content;
        if (contentShadow instanceof List) {
            List list = (List)contentShadow;
            BackedList<ProcessingInstruction> answer = this.createResultList();
            for (Node node : list) {
                if (!(node instanceof ProcessingInstruction)) continue;
                answer.addLocal((ProcessingInstruction)node);
            }
            return answer;
        }
        if (contentShadow instanceof ProcessingInstruction) {
            return this.createSingleResultList((ProcessingInstruction)contentShadow);
        }
        return this.createEmptyList();
    }

    @Override
    public List<ProcessingInstruction> processingInstructions(String target) {
        ProcessingInstruction pi;
        Object shadow = this.content;
        if (shadow instanceof List) {
            List list = (List)shadow;
            BackedList<ProcessingInstruction> answer = this.createResultList();
            for (Node node : list) {
                ProcessingInstruction pi2;
                if (!(node instanceof ProcessingInstruction) || !target.equals((pi2 = (ProcessingInstruction)node).getName())) continue;
                answer.addLocal(pi2);
            }
            return answer;
        }
        if (shadow instanceof ProcessingInstruction && target.equals((pi = (ProcessingInstruction)shadow).getName())) {
            return this.createSingleResultList(pi);
        }
        return this.createEmptyList();
    }

    @Override
    public ProcessingInstruction processingInstruction(String target) {
        ProcessingInstruction pi;
        Object shadow = this.content;
        if (shadow instanceof List) {
            List list = (List)shadow;
            for (Node node : list) {
                ProcessingInstruction pi2;
                if (!(node instanceof ProcessingInstruction) || !target.equals((pi2 = (ProcessingInstruction)node).getName())) continue;
                return pi2;
            }
        } else if (shadow instanceof ProcessingInstruction && target.equals((pi = (ProcessingInstruction)shadow).getName())) {
            return pi;
        }
        return null;
    }

    @Override
    public boolean removeProcessingInstruction(String target) {
        ProcessingInstruction pi;
        Object shadow = this.content;
        if (shadow instanceof List) {
            List list = (List)shadow;
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                ProcessingInstruction pi2;
                Node node = (Node)iter.next();
                if (!(node instanceof ProcessingInstruction) || !target.equals((pi2 = (ProcessingInstruction)node).getName())) continue;
                iter.remove();
                return true;
            }
        } else if (shadow instanceof ProcessingInstruction && target.equals((pi = (ProcessingInstruction)shadow).getName())) {
            this.content = null;
            return true;
        }
        return false;
    }

    @Override
    public Element element(String name) {
        Element element;
        Object contentShadow = this.content;
        if (contentShadow instanceof List) {
            List list = (List)contentShadow;
            for (Node node : list) {
                Element element2;
                if (!(node instanceof Element) || !name.equals((element2 = (Element)node).getName())) continue;
                return element2;
            }
        } else if (contentShadow instanceof Element && name.equals((element = (Element)contentShadow).getName())) {
            return element;
        }
        return null;
    }

    @Override
    public Element element(QName qName) {
        Element element;
        Object contentShadow = this.content;
        if (contentShadow instanceof List) {
            List list = (List)contentShadow;
            for (Node node : list) {
                Element element2;
                if (!(node instanceof Element) || !qName.equals((element2 = (Element)node).getQName())) continue;
                return element2;
            }
        } else if (contentShadow instanceof Element && qName.equals((element = (Element)contentShadow).getQName())) {
            return element;
        }
        return null;
    }

    @Override
    public Element element(String name, Namespace namespace) {
        return this.element(this.getDocumentFactory().createQName(name, namespace));
    }

    @Override
    public void setContent(List<Node> content) {
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
                Element parent = node.getParent();
                if (parent != null && parent != this) {
                    node = (Node)node.clone();
                }
                newContent.add(node);
                this.childAdded(node);
            }
            this.content = newContent;
        }
    }

    @Override
    public void clearContent() {
        if (this.content != null) {
            this.contentRemoved();
            this.content = null;
        }
    }

    @Override
    public Node node(int index) {
        if (index >= 0) {
            Node node;
            Object contentShadow = this.content;
            if (contentShadow instanceof List) {
                List list = (List)contentShadow;
                if (index >= list.size()) {
                    return null;
                }
                node = (Node)list.get(index);
            } else {
                node = index == 0 ? (Node)contentShadow : null;
            }
            return node;
        }
        return null;
    }

    @Override
    public int indexOf(Node node) {
        Object contentShadow = this.content;
        if (contentShadow instanceof List) {
            List list = (List)contentShadow;
            return list.indexOf(node);
        }
        if (contentShadow != null && contentShadow.equals(node)) {
            return 0;
        }
        return -1;
    }

    @Override
    public int nodeCount() {
        Object contentShadow = this.content;
        if (contentShadow instanceof List) {
            List list = (List)contentShadow;
            return list.size();
        }
        return contentShadow != null ? 1 : 0;
    }

    @Override
    public Iterator<Node> nodeIterator() {
        Object contentShadow = this.content;
        if (contentShadow instanceof List) {
            List list = (List)contentShadow;
            return list.iterator();
        }
        if (contentShadow != null) {
            return this.createSingleIterator((Node)contentShadow);
        }
        return Collections.emptyList().iterator();
    }

    @Override
    public List<Attribute> attributes() {
        return new ContentListFacade<Attribute>(this, this.attributeList());
    }

    @Override
    public void setAttributes(List<Attribute> attributes) {
        if (attributes instanceof ContentListFacade) {
            attributes = ((ContentListFacade)attributes).getBackingList();
        }
        this.attributes = attributes;
    }

    @Override
    public Iterator<Attribute> attributeIterator() {
        Object attributesShadow = this.attributes;
        if (attributesShadow instanceof List) {
            List list = (List)attributesShadow;
            return list.iterator();
        }
        if (attributesShadow != null) {
            return this.createSingleIterator((Attribute)attributesShadow);
        }
        return Collections.emptyList().iterator();
    }

    @Override
    public Attribute attribute(int index) {
        Object attributesShadow = this.attributes;
        if (attributesShadow instanceof List) {
            List list = (List)attributesShadow;
            return (Attribute)list.get(index);
        }
        if (attributesShadow != null && index == 0) {
            return (Attribute)attributesShadow;
        }
        return null;
    }

    @Override
    public int attributeCount() {
        Object attributesShadow = this.attributes;
        if (attributesShadow instanceof List) {
            List list = (List)attributesShadow;
            return list.size();
        }
        return attributesShadow != null ? 1 : 0;
    }

    @Override
    public Attribute attribute(String name) {
        Attribute attribute;
        Object attributesShadow = this.attributes;
        if (attributesShadow instanceof List) {
            List list = (List)attributesShadow;
            for (Attribute attribute2 : list) {
                if (!name.equals(attribute2.getName())) continue;
                return attribute2;
            }
        } else if (attributesShadow != null && name.equals((attribute = (Attribute)attributesShadow).getName())) {
            return attribute;
        }
        return null;
    }

    @Override
    public Attribute attribute(QName qName) {
        Attribute attribute;
        Object attributesShadow = this.attributes;
        if (attributesShadow instanceof List) {
            List list = (List)attributesShadow;
            for (Attribute attribute2 : list) {
                if (!qName.equals(attribute2.getQName())) continue;
                return attribute2;
            }
        } else if (attributesShadow != null && qName.equals((attribute = (Attribute)attributesShadow).getQName())) {
            return attribute;
        }
        return null;
    }

    @Override
    public Attribute attribute(String name, Namespace namespace) {
        return this.attribute(this.getDocumentFactory().createQName(name, namespace));
    }

    @Override
    public void add(Attribute attribute) {
        if (attribute.getParent() != null) {
            String message = "The Attribute already has an existing parent \"" + attribute.getParent().getQualifiedName() + "\"";
            throw new IllegalAddException(this, (Node)attribute, message);
        }
        if (attribute.getValue() == null) {
            Attribute oldAttribute = this.attribute(attribute.getQName());
            if (oldAttribute != null) {
                this.remove(oldAttribute);
            }
        } else {
            if (this.attributes == null) {
                this.attributes = attribute;
            } else {
                this.attributeList().add(attribute);
            }
            this.childAdded(attribute);
        }
    }

    @Override
    public boolean remove(Attribute attribute) {
        boolean answer = false;
        Object attributesShadow = this.attributes;
        if (attributesShadow instanceof List) {
            Attribute copy;
            List list = (List)attributesShadow;
            answer = list.remove(attribute);
            if (!answer && (copy = this.attribute(attribute.getQName())) != null) {
                list.remove(copy);
                answer = true;
            }
        } else if (attributesShadow != null) {
            if (attribute.equals(attributesShadow)) {
                this.attributes = null;
                answer = true;
            } else {
                Attribute other = (Attribute)attributesShadow;
                if (attribute.getQName().equals(other.getQName())) {
                    this.attributes = null;
                    answer = true;
                }
            }
        }
        if (answer) {
            this.childRemoved(attribute);
        }
        return answer;
    }

    @Override
    protected void addNewNode(Node node) {
        Object contentShadow = this.content;
        if (contentShadow == null) {
            this.content = node;
        } else if (contentShadow instanceof List) {
            List list = (List)contentShadow;
            list.add(node);
        } else {
            List<Node> list = this.createContentList();
            list.add((Node)contentShadow);
            list.add(node);
            this.content = list;
        }
        this.childAdded(node);
    }

    @Override
    protected boolean removeNode(Node node) {
        boolean answer = false;
        Object contentShadow = this.content;
        if (contentShadow != null) {
            if (contentShadow == node) {
                this.content = null;
                answer = true;
            } else if (contentShadow instanceof List) {
                List list = (List)contentShadow;
                answer = list.remove(node);
            }
        }
        if (answer) {
            this.childRemoved(node);
        }
        return answer;
    }

    @Override
    protected List<Node> contentList() {
        Object contentShadow = this.content;
        if (contentShadow instanceof List) {
            return (List)contentShadow;
        }
        List<Node> list = this.createContentList();
        if (contentShadow != null) {
            list.add((Node)contentShadow);
        }
        this.content = list;
        return list;
    }

    @Override
    protected List<Attribute> attributeList() {
        List<Attribute> list;
        Object attributesShadow = this.attributes;
        if (attributesShadow instanceof List) {
            return (List)attributesShadow;
        }
        if (attributesShadow != null) {
            List<Attribute> list2 = this.createAttributeList();
            list2.add((Attribute)attributesShadow);
            this.attributes = list2;
            return list2;
        }
        this.attributes = list = this.createAttributeList();
        return list;
    }

    @Override
    protected List<Attribute> attributeList(int size) {
        List<Attribute> list;
        Object attributesShadow = this.attributes;
        if (attributesShadow instanceof List) {
            return (List)attributesShadow;
        }
        if (attributesShadow != null) {
            List<Attribute> list2 = this.createAttributeList(size);
            list2.add((Attribute)attributesShadow);
            this.attributes = list2;
            return list2;
        }
        this.attributes = list = this.createAttributeList(size);
        return list;
    }

    protected void setAttributeList(List<Attribute> attributeList) {
        this.attributes = attributeList;
    }

    @Override
    protected DocumentFactory getDocumentFactory() {
        DocumentFactory factory = this.qname.getDocumentFactory();
        return factory != null ? factory : DOCUMENT_FACTORY;
    }
}

