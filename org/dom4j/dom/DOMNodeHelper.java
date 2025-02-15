/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.dom;

import java.util.List;
import org.dom4j.Branch;
import org.dom4j.CharacterData;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class DOMNodeHelper {
    public static final NodeList EMPTY_NODE_LIST = new EmptyNodeList();

    protected DOMNodeHelper() {
    }

    public static boolean supports(Node node, String feature, String version) {
        return false;
    }

    public static String getNamespaceURI(Node node) {
        return null;
    }

    public static String getPrefix(Node node) {
        return null;
    }

    public static String getLocalName(Node node) {
        return null;
    }

    public static void setPrefix(Node node, String prefix) throws DOMException {
        DOMNodeHelper.notSupported();
    }

    public static String getNodeValue(Node node) throws DOMException {
        return node.getText();
    }

    public static void setNodeValue(Node node, String nodeValue) throws DOMException {
        node.setText(nodeValue);
    }

    public static org.w3c.dom.Node getParentNode(Node node) {
        return DOMNodeHelper.asDOMNode(node.getParent());
    }

    public static NodeList getChildNodes(Node node) {
        return EMPTY_NODE_LIST;
    }

    public static org.w3c.dom.Node getFirstChild(Node node) {
        return null;
    }

    public static org.w3c.dom.Node getLastChild(Node node) {
        return null;
    }

    public static org.w3c.dom.Node getPreviousSibling(Node node) {
        int index;
        Element parent = node.getParent();
        if (parent != null && (index = parent.indexOf(node)) > 0) {
            Node previous = parent.node(index - 1);
            return DOMNodeHelper.asDOMNode(previous);
        }
        return null;
    }

    public static org.w3c.dom.Node getNextSibling(Node node) {
        int index;
        Element parent = node.getParent();
        if (parent != null && (index = parent.indexOf(node)) >= 0 && ++index < parent.nodeCount()) {
            Node next = parent.node(index);
            return DOMNodeHelper.asDOMNode(next);
        }
        return null;
    }

    public static NamedNodeMap getAttributes(Node node) {
        return null;
    }

    public static org.w3c.dom.Document getOwnerDocument(Node node) {
        return DOMNodeHelper.asDOMDocument(node.getDocument());
    }

    public static org.w3c.dom.Node insertBefore(Node node, org.w3c.dom.Node newChild, org.w3c.dom.Node refChild) throws DOMException {
        if (node instanceof Branch) {
            assert (newChild instanceof Node);
            Branch branch = (Branch)node;
            List<Node> list = branch.content();
            int index = list.indexOf(refChild);
            if (index < 0) {
                branch.add((Node)((Object)newChild));
            } else {
                list.add(index, (Node)((Object)newChild));
            }
            return newChild;
        }
        throw new DOMException(3, "Children not allowed for this node: " + node);
    }

    public static org.w3c.dom.Node replaceChild(Node node, org.w3c.dom.Node newChild, org.w3c.dom.Node oldChild) throws DOMException {
        if (node instanceof Branch) {
            Branch branch = (Branch)node;
            List<Node> list = branch.content();
            assert (newChild instanceof Node);
            int index = list.indexOf(oldChild);
            if (index < 0) {
                throw new DOMException(8, "Tried to replace a non existing child for node: " + node);
            }
            list.set(index, (Node)((Object)newChild));
            return oldChild;
        }
        throw new DOMException(3, "Children not allowed for this node: " + node);
    }

    public static org.w3c.dom.Node removeChild(Node node, org.w3c.dom.Node oldChild) throws DOMException {
        if (node instanceof Branch) {
            Branch branch = (Branch)node;
            branch.remove((Node)((Object)oldChild));
            return oldChild;
        }
        throw new DOMException(3, "Children not allowed for this node: " + node);
    }

    public static org.w3c.dom.Node appendChild(Node node, org.w3c.dom.Node newChild) throws DOMException {
        if (node instanceof Branch) {
            Branch branch = (Branch)node;
            org.w3c.dom.Node previousParent = newChild.getParentNode();
            if (previousParent != null) {
                previousParent.removeChild(newChild);
            }
            branch.add((Node)((Object)newChild));
            return newChild;
        }
        throw new DOMException(3, "Children not allowed for this node: " + node);
    }

    public static boolean hasChildNodes(Node node) {
        return false;
    }

    public static org.w3c.dom.Node cloneNode(Node node, boolean deep) {
        return DOMNodeHelper.asDOMNode((Node)node.clone());
    }

    public static void normalize(Node node) {
        DOMNodeHelper.notSupported();
    }

    public static boolean isSupported(Node n, String feature, String version) {
        return false;
    }

    public static boolean hasAttributes(Node node) {
        if (node != null && node instanceof Element) {
            return ((Element)node).attributeCount() > 0;
        }
        return false;
    }

    public static String getData(CharacterData charData) throws DOMException {
        return charData.getText();
    }

    public static void setData(CharacterData charData, String data) throws DOMException {
        charData.setText(data);
    }

    public static int getLength(CharacterData charData) {
        String text = charData.getText();
        return text != null ? text.length() : 0;
    }

    public static String substringData(CharacterData charData, int offset, int count) throws DOMException {
        int length;
        if (count < 0) {
            throw new DOMException(1, "Illegal value for count: " + count);
        }
        String text = charData.getText();
        int n = length = text != null ? text.length() : 0;
        if (offset < 0 || offset >= length) {
            throw new DOMException(1, "No text at offset: " + offset);
        }
        if (offset + count > length) {
            return text.substring(offset);
        }
        return text.substring(offset, offset + count);
    }

    public static void appendData(CharacterData charData, String arg) throws DOMException {
        if (charData.isReadOnly()) {
            throw new DOMException(7, "CharacterData node is read only: " + charData);
        }
        String text = charData.getText();
        if (text == null) {
            charData.setText(arg);
        } else {
            charData.setText(text + arg);
        }
    }

    public static void insertData(CharacterData data, int offset, String arg) throws DOMException {
        if (data.isReadOnly()) {
            throw new DOMException(7, "CharacterData node is read only: " + data);
        }
        String text = data.getText();
        if (text == null) {
            data.setText(arg);
        } else {
            int length = text.length();
            if (offset < 0 || offset > length) {
                throw new DOMException(1, "No text at offset: " + offset);
            }
            StringBuilder buffer = new StringBuilder(text);
            buffer.insert(offset, arg);
            data.setText(buffer.toString());
        }
    }

    public static void deleteData(CharacterData charData, int offset, int count) throws DOMException {
        if (charData.isReadOnly()) {
            throw new DOMException(7, "CharacterData node is read only: " + charData);
        }
        if (count < 0) {
            throw new DOMException(1, "Illegal value for count: " + count);
        }
        String text = charData.getText();
        if (text != null) {
            int length = text.length();
            if (offset < 0 || offset >= length) {
                throw new DOMException(1, "No text at offset: " + offset);
            }
            StringBuilder buffer = new StringBuilder(text);
            buffer.delete(offset, offset + count);
            charData.setText(buffer.toString());
        }
    }

    public static void replaceData(CharacterData charData, int offset, int count, String arg) throws DOMException {
        if (charData.isReadOnly()) {
            throw new DOMException(7, "CharacterData node is read only: " + charData);
        }
        if (count < 0) {
            throw new DOMException(1, "Illegal value for count: " + count);
        }
        String text = charData.getText();
        if (text != null) {
            int length = text.length();
            if (offset < 0 || offset >= length) {
                throw new DOMException(1, "No text at offset: " + offset);
            }
            StringBuilder buffer = new StringBuilder(text);
            buffer.replace(offset, offset + count, arg);
            charData.setText(buffer.toString());
        }
    }

    public static void appendElementsByTagName(List<? super Element> list, Branch parent, String name) {
        boolean isStar = "*".equals(name);
        int size = parent.nodeCount();
        for (int i = 0; i < size; ++i) {
            Node node = parent.node(i);
            if (!(node instanceof Element)) continue;
            Element element = (Element)node;
            if (isStar || name.equals(element.getName())) {
                list.add(element);
            }
            DOMNodeHelper.appendElementsByTagName(list, element, name);
        }
    }

    public static void appendElementsByTagNameNS(List<? super Element> list, Branch parent, String namespace, String localName) {
        boolean isStarNS = "*".equals(namespace);
        boolean isStar = "*".equals(localName);
        int size = parent.nodeCount();
        for (int i = 0; i < size; ++i) {
            Node node = parent.node(i);
            if (!(node instanceof Element)) continue;
            Element element = (Element)node;
            if ((isStarNS || (namespace == null || namespace.length() == 0) && (element.getNamespaceURI() == null || element.getNamespaceURI().length() == 0) || namespace != null && namespace.equals(element.getNamespaceURI())) && (isStar || localName.equals(element.getName()))) {
                list.add(element);
            }
            DOMNodeHelper.appendElementsByTagNameNS(list, element, namespace, localName);
        }
    }

    public static NodeList createNodeList(final List<Node> list) {
        return new NodeList(){

            @Override
            public org.w3c.dom.Node item(int index) {
                if (index >= this.getLength()) {
                    return null;
                }
                return DOMNodeHelper.asDOMNode((Node)list.get(index));
            }

            @Override
            public int getLength() {
                return list.size();
            }
        };
    }

    public static org.w3c.dom.Node asDOMNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node instanceof org.w3c.dom.Node) {
            return (org.w3c.dom.Node)((Object)node);
        }
        System.out.println("Cannot convert: " + node + " into a W3C DOM Node");
        DOMNodeHelper.notSupported();
        return null;
    }

    public static org.w3c.dom.Document asDOMDocument(Document document) {
        if (document == null) {
            return null;
        }
        if (document instanceof org.w3c.dom.Document) {
            return (org.w3c.dom.Document)((Object)document);
        }
        DOMNodeHelper.notSupported();
        return null;
    }

    public static org.w3c.dom.DocumentType asDOMDocumentType(DocumentType dt) {
        if (dt == null) {
            return null;
        }
        if (dt instanceof org.w3c.dom.DocumentType) {
            return (org.w3c.dom.DocumentType)((Object)dt);
        }
        DOMNodeHelper.notSupported();
        return null;
    }

    public static Text asDOMText(CharacterData text) {
        if (text == null) {
            return null;
        }
        if (text instanceof Text) {
            return (Text)((Object)text);
        }
        DOMNodeHelper.notSupported();
        return null;
    }

    public static org.w3c.dom.Element asDOMElement(Node element) {
        if (element == null) {
            return null;
        }
        if (element instanceof org.w3c.dom.Element) {
            return (org.w3c.dom.Element)((Object)element);
        }
        DOMNodeHelper.notSupported();
        return null;
    }

    public static Attr asDOMAttr(Node attribute) {
        if (attribute == null) {
            return null;
        }
        if (attribute instanceof Attr) {
            return (Attr)((Object)attribute);
        }
        DOMNodeHelper.notSupported();
        return null;
    }

    public static void notSupported() {
        throw new DOMException(9, "Not supported yet");
    }

    public static boolean isStringEquals(String string1, String string2) {
        if (string1 == null && string2 == null) {
            return true;
        }
        if (string1 == null || string2 == null) {
            return false;
        }
        return string1.equals(string2);
    }

    public static boolean isNodeEquals(org.w3c.dom.Node node1, org.w3c.dom.Node node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.getNodeType() != node2.getNodeType()) {
            return false;
        }
        if (!DOMNodeHelper.isStringEquals(node1.getNodeName(), node2.getNodeName())) {
            return false;
        }
        if (!DOMNodeHelper.isStringEquals(node1.getLocalName(), node2.getLocalName())) {
            return false;
        }
        if (!DOMNodeHelper.isStringEquals(node1.getNamespaceURI(), node2.getNamespaceURI())) {
            return false;
        }
        if (!DOMNodeHelper.isStringEquals(node1.getPrefix(), node2.getPrefix())) {
            return false;
        }
        return DOMNodeHelper.isStringEquals(node1.getNodeValue(), node2.getNodeValue());
    }

    public static boolean isNodeSame(org.w3c.dom.Node node1, org.w3c.dom.Node node2) {
        return node1 == node2;
    }

    public static class EmptyNodeList
    implements NodeList {
        @Override
        public org.w3c.dom.Node item(int index) {
            return null;
        }

        @Override
        public int getLength() {
            return 0;
        }
    }
}

