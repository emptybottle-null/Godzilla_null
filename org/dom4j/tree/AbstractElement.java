/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.CharacterData;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.IllegalAddException;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.Visitor;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.AbstractBranch;
import org.dom4j.tree.BackedList;
import org.dom4j.tree.ContentListFacade;
import org.dom4j.tree.NamespaceStack;
import org.dom4j.tree.SingleIterator;
import org.xml.sax.Attributes;

public abstract class AbstractElement
extends AbstractBranch
implements Element {
    private static final DocumentFactory DOCUMENT_FACTORY = DocumentFactory.getInstance();
    protected static final boolean VERBOSE_TOSTRING = false;
    protected static final boolean USE_STRINGVALUE_SEPARATOR = false;

    @Override
    public short getNodeType() {
        return 1;
    }

    @Override
    public boolean isRootElement() {
        Element root;
        Document document = this.getDocument();
        return document != null && (root = document.getRootElement()) == this;
    }

    @Override
    public void setName(String name) {
        this.setQName(this.getDocumentFactory().createQName(name));
    }

    public void setNamespace(Namespace namespace) {
        this.setQName(this.getDocumentFactory().createQName(this.getName(), namespace));
    }

    public String getXPathNameStep() {
        String uri = this.getNamespaceURI();
        if (uri == null || uri.length() == 0) {
            return this.getName();
        }
        String prefix = this.getNamespacePrefix();
        if (prefix == null || prefix.length() == 0) {
            return "*[name()='" + this.getName() + "']";
        }
        return this.getQualifiedName();
    }

    @Override
    public String getPath(Element context) {
        if (this == context) {
            return ".";
        }
        Element parent = this.getParent();
        if (parent == null) {
            return "/" + this.getXPathNameStep();
        }
        if (parent == context) {
            return this.getXPathNameStep();
        }
        return parent.getPath(context) + "/" + this.getXPathNameStep();
    }

    @Override
    public String getUniquePath(Element context) {
        int idx;
        Element parent = this.getParent();
        if (parent == null) {
            return "/" + this.getXPathNameStep();
        }
        StringBuilder buffer = new StringBuilder();
        if (parent != context) {
            buffer.append(parent.getUniquePath(context));
            buffer.append("/");
        }
        buffer.append(this.getXPathNameStep());
        List<Element> mySiblings = parent.elements(this.getQName());
        if (mySiblings.size() > 1 && (idx = mySiblings.indexOf(this)) >= 0) {
            buffer.append("[");
            buffer.append(Integer.toString(++idx));
            buffer.append("]");
        }
        return buffer.toString();
    }

    @Override
    public String asXML() {
        try {
            StringWriter out = new StringWriter();
            XMLWriter writer = new XMLWriter(out, new OutputFormat());
            writer.write(this);
            writer.flush();
            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException("IOException while generating textual representation: " + e.getMessage());
        }
    }

    @Override
    public void write(Writer out) throws IOException {
        XMLWriter writer = new XMLWriter(out, new OutputFormat());
        writer.write(this);
    }

    @Override
    public void accept(Visitor visitor) {
        int i;
        visitor.visit(this);
        int size = this.attributeCount();
        for (i = 0; i < size; ++i) {
            Attribute attribute = this.attribute(i);
            visitor.visit(attribute);
        }
        size = this.nodeCount();
        for (i = 0; i < size; ++i) {
            Node node = this.node(i);
            node.accept(visitor);
        }
    }

    public String toString() {
        String uri = this.getNamespaceURI();
        if (uri != null && uri.length() > 0) {
            return super.toString() + " [Element: <" + this.getQualifiedName() + " uri: " + uri + " attributes: " + this.attributeList() + "/>]";
        }
        return super.toString() + " [Element: <" + this.getQualifiedName() + " attributes: " + this.attributeList() + "/>]";
    }

    @Override
    public Namespace getNamespace() {
        return this.getQName().getNamespace();
    }

    @Override
    public String getName() {
        return this.getQName().getName();
    }

    @Override
    public String getNamespacePrefix() {
        return this.getQName().getNamespacePrefix();
    }

    @Override
    public String getNamespaceURI() {
        return this.getQName().getNamespaceURI();
    }

    @Override
    public String getQualifiedName() {
        return this.getQName().getQualifiedName();
    }

    @Override
    public Object getData() {
        return this.getText();
    }

    @Override
    public void setData(Object data) {
    }

    @Override
    public Node node(int index) {
        if (index >= 0) {
            List<Node> list = this.contentList();
            if (index >= list.size()) {
                return null;
            }
            Node node = list.get(index);
            if (node != null) {
                return node;
            }
        }
        return null;
    }

    @Override
    public int indexOf(Node node) {
        return this.contentList().indexOf(node);
    }

    @Override
    public int nodeCount() {
        return this.contentList().size();
    }

    @Override
    public Iterator<Node> nodeIterator() {
        return this.contentList().iterator();
    }

    @Override
    public Element element(String name) {
        for (Node node : this.contentList()) {
            Element element;
            if (!(node instanceof Element) || !name.equals((element = (Element)node).getName())) continue;
            return element;
        }
        return null;
    }

    @Override
    public Element element(QName qName) {
        for (Node node : this.contentList()) {
            Element element;
            if (!(node instanceof Element) || !qName.equals((element = (Element)node).getQName())) continue;
            return element;
        }
        return null;
    }

    public Element element(String name, Namespace namespace) {
        return this.element(this.getDocumentFactory().createQName(name, namespace));
    }

    @Override
    public List<Element> elements() {
        BackedList<Element> answer = this.createResultList();
        for (Node node : this.contentList()) {
            if (!(node instanceof Element)) continue;
            answer.addLocal((Element)node);
        }
        return answer;
    }

    @Override
    public List<Element> elements(String name) {
        BackedList<Element> answer = this.createResultList();
        for (Node node : this.contentList()) {
            Element element;
            if (!(node instanceof Element) || !name.equals((element = (Element)node).getName())) continue;
            answer.addLocal(element);
        }
        return answer;
    }

    @Override
    public List<Element> elements(QName qName) {
        BackedList<Element> answer = this.createResultList();
        for (Node node : this.contentList()) {
            Element element;
            if (!(node instanceof Element) || !qName.equals((element = (Element)node).getQName())) continue;
            answer.addLocal(element);
        }
        return answer;
    }

    public List<Element> elements(String name, Namespace namespace) {
        return this.elements(this.getDocumentFactory().createQName(name, namespace));
    }

    @Override
    public Iterator<Element> elementIterator() {
        List<Element> list = this.elements();
        return list.iterator();
    }

    @Override
    public Iterator<Element> elementIterator(String name) {
        List<Element> list = this.elements(name);
        return list.iterator();
    }

    @Override
    public Iterator<Element> elementIterator(QName qName) {
        List<Element> list = this.elements(qName);
        return list.iterator();
    }

    public Iterator<Element> elementIterator(String name, Namespace ns) {
        return this.elementIterator(this.getDocumentFactory().createQName(name, ns));
    }

    @Override
    public List<Attribute> attributes() {
        return new ContentListFacade<Attribute>(this, this.attributeList());
    }

    @Override
    public Iterator<Attribute> attributeIterator() {
        return this.attributeList().iterator();
    }

    @Override
    public Attribute attribute(int index) {
        return this.attributeList().get(index);
    }

    @Override
    public int attributeCount() {
        return this.attributeList().size();
    }

    @Override
    public Attribute attribute(String name) {
        for (Attribute attribute : this.attributeList()) {
            if (!name.equals(attribute.getName())) continue;
            return attribute;
        }
        return null;
    }

    @Override
    public Attribute attribute(QName qName) {
        for (Attribute attribute : this.attributeList()) {
            if (!qName.equals(attribute.getQName())) continue;
            return attribute;
        }
        return null;
    }

    public Attribute attribute(String name, Namespace namespace) {
        return this.attribute(this.getDocumentFactory().createQName(name, namespace));
    }

    public void setAttributes(Attributes attributes, NamespaceStack namespaceStack, boolean noNamespaceAttributes) {
        int size = attributes.getLength();
        if (size > 0) {
            DocumentFactory factory = this.getDocumentFactory();
            if (size == 1) {
                String name = attributes.getQName(0);
                if (noNamespaceAttributes || !name.startsWith("xmlns")) {
                    String attributeURI = attributes.getURI(0);
                    String attributeLocalName = attributes.getLocalName(0);
                    String attributeValue = attributes.getValue(0);
                    QName attributeQName = namespaceStack.getAttributeQName(attributeURI, attributeLocalName, name);
                    this.add(factory.createAttribute((Element)this, attributeQName, attributeValue));
                }
            } else {
                List<Attribute> list = this.attributeList(size);
                list.clear();
                for (int i = 0; i < size; ++i) {
                    String attributeName = attributes.getQName(i);
                    if (!noNamespaceAttributes && attributeName.startsWith("xmlns")) continue;
                    String attributeURI = attributes.getURI(i);
                    String attributeLocalName = attributes.getLocalName(i);
                    String attributeValue = attributes.getValue(i);
                    QName attributeQName = namespaceStack.getAttributeQName(attributeURI, attributeLocalName, attributeName);
                    Attribute attribute = factory.createAttribute((Element)this, attributeQName, attributeValue);
                    list.add(attribute);
                    this.childAdded(attribute);
                }
            }
        }
    }

    @Override
    public String attributeValue(String name) {
        Attribute attrib = this.attribute(name);
        if (attrib == null) {
            return null;
        }
        return attrib.getValue();
    }

    @Override
    public String attributeValue(QName qName) {
        Attribute attrib = this.attribute(qName);
        if (attrib == null) {
            return null;
        }
        return attrib.getValue();
    }

    @Override
    public String attributeValue(String name, String defaultValue) {
        String answer = this.attributeValue(name);
        return answer != null ? answer : defaultValue;
    }

    @Override
    public String attributeValue(QName qName, String defaultValue) {
        String answer = this.attributeValue(qName);
        return answer != null ? answer : defaultValue;
    }

    @Override
    public void setAttributeValue(String name, String value) {
        this.addAttribute(name, value);
    }

    @Override
    public void setAttributeValue(QName qName, String value) {
        this.addAttribute(qName, value);
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
            this.attributeList().add(attribute);
            this.childAdded(attribute);
        }
    }

    @Override
    public boolean remove(Attribute attribute) {
        List<Attribute> list = this.attributeList();
        boolean answer = list.remove(attribute);
        if (answer) {
            this.childRemoved(attribute);
        } else {
            Attribute copy = this.attribute(attribute.getQName());
            if (copy != null) {
                list.remove(copy);
                answer = true;
            }
        }
        return answer;
    }

    @Override
    public List<ProcessingInstruction> processingInstructions() {
        BackedList<ProcessingInstruction> answer = this.createResultList();
        for (Node node : this.contentList()) {
            if (!(node instanceof ProcessingInstruction)) continue;
            answer.addLocal((ProcessingInstruction)node);
        }
        return answer;
    }

    @Override
    public List<ProcessingInstruction> processingInstructions(String target) {
        BackedList<ProcessingInstruction> answer = this.createResultList();
        for (Node node : this.contentList()) {
            ProcessingInstruction pi;
            if (!(node instanceof ProcessingInstruction) || !target.equals((pi = (ProcessingInstruction)node).getName())) continue;
            answer.addLocal(pi);
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
    public Node getXPathResult(int index) {
        Node answer = this.node(index);
        if (answer != null && !answer.supportsParent()) {
            return answer.asXPathResult(this);
        }
        return answer;
    }

    @Override
    public Element addAttribute(String name, String value) {
        Attribute attribute = this.attribute(name);
        if (value != null) {
            if (attribute == null) {
                this.add(this.getDocumentFactory().createAttribute((Element)this, name, value));
            } else if (attribute.isReadOnly()) {
                this.remove(attribute);
                this.add(this.getDocumentFactory().createAttribute((Element)this, name, value));
            } else {
                attribute.setValue(value);
            }
        } else if (attribute != null) {
            this.remove(attribute);
        }
        return this;
    }

    @Override
    public Element addAttribute(QName qName, String value) {
        Attribute attribute = this.attribute(qName);
        if (value != null) {
            if (attribute == null) {
                this.add(this.getDocumentFactory().createAttribute((Element)this, qName, value));
            } else if (attribute.isReadOnly()) {
                this.remove(attribute);
                this.add(this.getDocumentFactory().createAttribute((Element)this, qName, value));
            } else {
                attribute.setValue(value);
            }
        } else if (attribute != null) {
            this.remove(attribute);
        }
        return this;
    }

    @Override
    public Element addCDATA(String cdata) {
        CDATA node = this.getDocumentFactory().createCDATA(cdata);
        this.addNewNode(node);
        return this;
    }

    @Override
    public Element addComment(String comment) {
        Comment node = this.getDocumentFactory().createComment(comment);
        this.addNewNode(node);
        return this;
    }

    @Override
    public Element addElement(String name) {
        Element node;
        Namespace namespace;
        DocumentFactory factory = this.getDocumentFactory();
        int index = name.indexOf(":");
        String localName = name;
        if (index > 0) {
            String prefix = name.substring(0, index);
            localName = name.substring(index + 1);
            namespace = this.getNamespaceForPrefix(prefix);
            if (namespace == null) {
                throw new IllegalAddException("No such namespace prefix: " + prefix + " is in scope on: " + this + " so cannot add element: " + name);
            }
        } else {
            namespace = this.getNamespaceForPrefix("");
        }
        if (namespace != null) {
            QName qname = factory.createQName(localName, namespace);
            node = factory.createElement(qname);
        } else {
            node = factory.createElement(name);
        }
        this.addNewNode(node);
        return node;
    }

    @Override
    public Element addEntity(String name, String text) {
        Entity node = this.getDocumentFactory().createEntity(name, text);
        this.addNewNode(node);
        return this;
    }

    @Override
    public Element addNamespace(String prefix, String uri) {
        Namespace node = this.getDocumentFactory().createNamespace(prefix, uri);
        this.addNewNode(node);
        return this;
    }

    @Override
    public Element addProcessingInstruction(String target, String data) {
        ProcessingInstruction node = this.getDocumentFactory().createProcessingInstruction(target, data);
        this.addNewNode(node);
        return this;
    }

    @Override
    public Element addProcessingInstruction(String target, Map<String, String> data) {
        ProcessingInstruction node = this.getDocumentFactory().createProcessingInstruction(target, data);
        this.addNewNode(node);
        return this;
    }

    @Override
    public Element addText(String text) {
        Text node = this.getDocumentFactory().createText(text);
        this.addNewNode(node);
        return this;
    }

    @Override
    public void add(Node node) {
        switch (node.getNodeType()) {
            case 1: {
                this.add((Element)node);
                break;
            }
            case 2: {
                this.add((Attribute)node);
                break;
            }
            case 3: {
                this.add((Text)node);
                break;
            }
            case 4: {
                this.add((CDATA)node);
                break;
            }
            case 5: {
                this.add((Entity)node);
                break;
            }
            case 7: {
                this.add((ProcessingInstruction)node);
                break;
            }
            case 8: {
                this.add((Comment)node);
                break;
            }
            case 13: {
                this.add((Namespace)node);
                break;
            }
            default: {
                this.invalidNodeTypeAddException(node);
            }
        }
    }

    @Override
    public boolean remove(Node node) {
        switch (node.getNodeType()) {
            case 1: {
                return this.remove((Element)node);
            }
            case 2: {
                return this.remove((Attribute)node);
            }
            case 3: {
                return this.remove((Text)node);
            }
            case 4: {
                return this.remove((CDATA)node);
            }
            case 5: {
                return this.remove((Entity)node);
            }
            case 7: {
                return this.remove((ProcessingInstruction)node);
            }
            case 8: {
                return this.remove((Comment)node);
            }
            case 13: {
                return this.remove((Namespace)node);
            }
        }
        return false;
    }

    @Override
    public void add(CDATA cdata) {
        this.addNode(cdata);
    }

    @Override
    public void add(Comment comment) {
        this.addNode(comment);
    }

    @Override
    public void add(Element element) {
        this.addNode(element);
    }

    @Override
    public void add(Entity entity) {
        this.addNode(entity);
    }

    @Override
    public void add(Namespace namespace) {
        this.addNode(namespace);
    }

    @Override
    public void add(ProcessingInstruction pi) {
        this.addNode(pi);
    }

    @Override
    public void add(Text text) {
        this.addNode(text);
    }

    @Override
    public boolean remove(CDATA cdata) {
        return this.removeNode(cdata);
    }

    @Override
    public boolean remove(Comment comment) {
        return this.removeNode(comment);
    }

    @Override
    public boolean remove(Element element) {
        return this.removeNode(element);
    }

    @Override
    public boolean remove(Entity entity) {
        return this.removeNode(entity);
    }

    @Override
    public boolean remove(Namespace namespace) {
        return this.removeNode(namespace);
    }

    @Override
    public boolean remove(ProcessingInstruction pi) {
        return this.removeNode(pi);
    }

    @Override
    public boolean remove(Text text) {
        return this.removeNode(text);
    }

    @Override
    public boolean hasMixedContent() {
        List<Node> content = this.contentList();
        if (content == null || content.isEmpty() || content.size() < 2) {
            return false;
        }
        Class<?> prevClass = null;
        for (Node node : content) {
            Class<?> newClass = node.getClass();
            if (newClass == prevClass) continue;
            if (prevClass != null) {
                return true;
            }
            prevClass = newClass;
        }
        return false;
    }

    @Override
    public boolean isTextOnly() {
        List<Node> content = this.contentList();
        if (content == null || content.isEmpty()) {
            return true;
        }
        for (Node object : content) {
            if (object instanceof CharacterData) continue;
            return false;
        }
        return true;
    }

    @Override
    public void setText(String text) {
        List<Node> allContent = this.contentList();
        if (allContent != null) {
            Iterator<Node> it = allContent.iterator();
            while (it.hasNext()) {
                Node node = it.next();
                switch (node.getNodeType()) {
                    case 3: 
                    case 4: 
                    case 5: {
                        it.remove();
                    }
                }
            }
        }
        this.addText(text);
    }

    @Override
    public String getStringValue() {
        List<Node> list = this.contentList();
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
        return "";
    }

    @Override
    public void normalize() {
        List<Node> content = this.contentList();
        CharacterData previousText = null;
        int i = 0;
        while (i < content.size()) {
            Node node = content.get(i);
            if (node instanceof Text) {
                Text text = (Text)node;
                if (previousText != null) {
                    previousText.appendText(text.getText());
                    this.remove(text);
                    continue;
                }
                String value = text.getText();
                if (value == null || value.length() <= 0) {
                    this.remove(text);
                    continue;
                }
                previousText = text;
                ++i;
                continue;
            }
            if (node instanceof Element) {
                Element element = (Element)node;
                element.normalize();
            }
            previousText = null;
            ++i;
        }
    }

    @Override
    public String elementText(String name) {
        Element element = this.element(name);
        return element != null ? element.getText() : null;
    }

    @Override
    public String elementText(QName qName) {
        Element element = this.element(qName);
        return element != null ? element.getText() : null;
    }

    @Override
    public String elementTextTrim(String name) {
        Element element = this.element(name);
        return element != null ? element.getTextTrim() : null;
    }

    @Override
    public String elementTextTrim(QName qName) {
        Element element = this.element(qName);
        return element != null ? element.getTextTrim() : null;
    }

    @Override
    public void appendAttributes(Element element) {
        int size = element.attributeCount();
        for (int i = 0; i < size; ++i) {
            Attribute attribute = element.attribute(i);
            if (attribute.supportsParent()) {
                this.addAttribute(attribute.getQName(), attribute.getValue());
                continue;
            }
            this.add(attribute);
        }
    }

    @Override
    public Element createCopy() {
        Element clone = this.createElement(this.getQName());
        clone.appendAttributes(this);
        clone.appendContent(this);
        return clone;
    }

    @Override
    public Element createCopy(String name) {
        Element clone = this.createElement(name);
        clone.appendAttributes(this);
        clone.appendContent(this);
        return clone;
    }

    @Override
    public Element createCopy(QName qName) {
        Element clone = this.createElement(qName);
        clone.appendAttributes(this);
        clone.appendContent(this);
        return clone;
    }

    @Override
    public QName getQName(String qualifiedName) {
        Namespace namespace;
        String prefix = "";
        String localName = qualifiedName;
        int index = qualifiedName.indexOf(":");
        if (index > 0) {
            prefix = qualifiedName.substring(0, index);
            localName = qualifiedName.substring(index + 1);
        }
        if ((namespace = this.getNamespaceForPrefix(prefix)) != null) {
            return this.getDocumentFactory().createQName(localName, namespace);
        }
        return this.getDocumentFactory().createQName(localName);
    }

    @Override
    public Namespace getNamespaceForPrefix(String prefix) {
        Namespace answer;
        if (prefix == null) {
            prefix = "";
        }
        if (prefix.equals(this.getNamespacePrefix())) {
            return this.getNamespace();
        }
        if (prefix.equals("xml")) {
            return Namespace.XML_NAMESPACE;
        }
        for (Node node : this.contentList()) {
            Namespace namespace;
            if (!(node instanceof Namespace) || !prefix.equals((namespace = (Namespace)node).getPrefix())) continue;
            return namespace;
        }
        Element parent = this.getParent();
        if (parent != null && (answer = parent.getNamespaceForPrefix(prefix)) != null) {
            return answer;
        }
        if (prefix.length() == 0) {
            return Namespace.NO_NAMESPACE;
        }
        return null;
    }

    @Override
    public Namespace getNamespaceForURI(String uri) {
        if (uri == null || uri.length() <= 0) {
            return Namespace.NO_NAMESPACE;
        }
        if (uri.equals(this.getNamespaceURI())) {
            return this.getNamespace();
        }
        for (Node node : this.contentList()) {
            Namespace namespace;
            if (!(node instanceof Namespace) || !uri.equals((namespace = (Namespace)node).getURI())) continue;
            return namespace;
        }
        return null;
    }

    @Override
    public List<Namespace> getNamespacesForURI(String uri) {
        BackedList<Namespace> answer = this.createResultList();
        for (Node node : this.contentList()) {
            if (!(node instanceof Namespace) || !((Namespace)node).getURI().equals(uri)) continue;
            answer.addLocal((Namespace)node);
        }
        return answer;
    }

    @Override
    public List<Namespace> declaredNamespaces() {
        BackedList<Namespace> answer = this.createResultList();
        for (Node node : this.contentList()) {
            if (!(node instanceof Namespace)) continue;
            answer.addLocal((Namespace)node);
        }
        return answer;
    }

    @Override
    public List<Namespace> additionalNamespaces() {
        BackedList<Namespace> answer = this.createResultList();
        for (Node node : this.contentList()) {
            Namespace namespace;
            if (!(node instanceof Namespace) || (namespace = (Namespace)node).equals(this.getNamespace())) continue;
            answer.addLocal(namespace);
        }
        return answer;
    }

    public List<Namespace> additionalNamespaces(String defaultNamespaceURI) {
        BackedList<Namespace> answer = this.createResultList();
        for (Node node : this.contentList()) {
            Namespace namespace;
            if (!(node instanceof Namespace) || defaultNamespaceURI.equals((namespace = (Namespace)node).getURI())) continue;
            answer.addLocal(namespace);
        }
        return answer;
    }

    public void ensureAttributesCapacity(int minCapacity) {
        List<Attribute> list;
        if (minCapacity > 1 && (list = this.attributeList()) instanceof ArrayList) {
            ArrayList arrayList = (ArrayList)list;
            arrayList.ensureCapacity(minCapacity);
        }
    }

    protected Element createElement(String name) {
        return this.getDocumentFactory().createElement(name);
    }

    protected Element createElement(QName qName) {
        return this.getDocumentFactory().createElement(qName);
    }

    @Override
    protected void addNode(Node node) {
        if (node.getParent() != null) {
            String message = "The Node already has an existing parent of \"" + node.getParent().getQualifiedName() + "\"";
            throw new IllegalAddException(this, node, message);
        }
        this.addNewNode(node);
    }

    @Override
    protected void addNode(int index, Node node) {
        if (node.getParent() != null) {
            String message = "The Node already has an existing parent of \"" + node.getParent().getQualifiedName() + "\"";
            throw new IllegalAddException(this, node, message);
        }
        this.addNewNode(index, node);
    }

    protected void addNewNode(Node node) {
        this.contentList().add(node);
        this.childAdded(node);
    }

    protected void addNewNode(int index, Node node) {
        this.contentList().add(index, node);
        this.childAdded(node);
    }

    @Override
    protected boolean removeNode(Node node) {
        boolean answer = this.contentList().remove(node);
        if (answer) {
            this.childRemoved(node);
        }
        return answer;
    }

    @Override
    protected void childAdded(Node node) {
        if (node != null) {
            node.setParent(this);
        }
    }

    @Override
    protected void childRemoved(Node node) {
        if (node != null) {
            node.setParent(null);
            node.setDocument(null);
        }
    }

    protected abstract List<Attribute> attributeList();

    protected abstract List<Attribute> attributeList(int var1);

    @Override
    protected DocumentFactory getDocumentFactory() {
        DocumentFactory factory;
        QName qName = this.getQName();
        if (qName != null && (factory = qName.getDocumentFactory()) != null) {
            return factory;
        }
        return DOCUMENT_FACTORY;
    }

    protected List<Attribute> createAttributeList() {
        return this.createAttributeList(5);
    }

    protected List<Attribute> createAttributeList(int size) {
        return new ArrayList<Attribute>(size);
    }

    protected <T> Iterator<T> createSingleIterator(T result) {
        return new SingleIterator<T>(result);
    }
}

