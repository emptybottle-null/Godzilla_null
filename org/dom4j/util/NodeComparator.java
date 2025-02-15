/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.util;

import java.util.Comparator;
import org.dom4j.Attribute;
import org.dom4j.Branch;
import org.dom4j.CDATA;
import org.dom4j.CharacterData;
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

public class NodeComparator
implements Comparator<Node> {
    @Override
    public int compare(Node n1, Node n2) {
        short nodeType2;
        short nodeType1 = n1.getNodeType();
        int answer = nodeType1 - (nodeType2 = n2.getNodeType());
        if (answer != 0) {
            return answer;
        }
        switch (nodeType1) {
            case 1: {
                return this.compare((Element)n1, (Element)n2);
            }
            case 9: {
                return this.compare((Document)n1, (Document)n2);
            }
            case 2: {
                return this.compare((Attribute)n1, (Attribute)n2);
            }
            case 3: {
                return this.compare((Text)n1, (Text)n2);
            }
            case 4: {
                return this.compare((CDATA)n1, (CDATA)n2);
            }
            case 5: {
                return this.compare((Entity)n1, (Entity)n2);
            }
            case 7: {
                return this.compare((ProcessingInstruction)n1, (ProcessingInstruction)n2);
            }
            case 8: {
                return this.compare((Comment)n1, (Comment)n2);
            }
            case 10: {
                return this.compare((DocumentType)n1, (DocumentType)n2);
            }
            case 13: {
                return this.compare((Namespace)n1, (Namespace)n2);
            }
        }
        throw new RuntimeException("Invalid node types. node1: " + n1 + " and node2: " + n2);
    }

    @Override
    public int compare(Document n1, Document n2) {
        int answer = this.compare(n1.getDocType(), n2.getDocType());
        if (answer == 0) {
            answer = this.compareContent(n1, n2);
        }
        return answer;
    }

    @Override
    public int compare(Element n1, Element n2) {
        int c2;
        int c1;
        int answer = this.compare(n1.getQName(), n2.getQName());
        if (answer == 0 && (answer = (c1 = n1.attributeCount()) - (c2 = n2.attributeCount())) == 0) {
            for (int i = 0; i < c1; ++i) {
                Attribute a2;
                Attribute a1 = n1.attribute(i);
                answer = this.compare(a1, a2 = n2.attribute(a1.getQName()));
                if (answer == 0) continue;
                return answer;
            }
            answer = this.compareContent(n1, n2);
        }
        return answer;
    }

    @Override
    public int compare(Attribute n1, Attribute n2) {
        int answer = this.compare(n1.getQName(), n2.getQName());
        if (answer == 0) {
            answer = this.compare(n1.getValue(), n2.getValue());
        }
        return answer;
    }

    @Override
    public int compare(QName n1, QName n2) {
        int answer = this.compare(n1.getNamespaceURI(), n2.getNamespaceURI());
        if (answer == 0) {
            answer = this.compare(n1.getQualifiedName(), n2.getQualifiedName());
        }
        return answer;
    }

    @Override
    public int compare(Namespace n1, Namespace n2) {
        int answer = this.compare(n1.getURI(), n2.getURI());
        if (answer == 0) {
            answer = this.compare(n1.getPrefix(), n2.getPrefix());
        }
        return answer;
    }

    @Override
    public int compare(CharacterData t1, CharacterData t2) {
        return this.compare(t1.getText(), t2.getText());
    }

    @Override
    public int compare(DocumentType o1, DocumentType o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }
        int answer = this.compare(o1.getPublicID(), o2.getPublicID());
        if (answer == 0 && (answer = this.compare(o1.getSystemID(), o2.getSystemID())) == 0) {
            answer = this.compare(o1.getName(), o2.getName());
        }
        return answer;
    }

    @Override
    public int compare(Entity n1, Entity n2) {
        int answer = this.compare(n1.getName(), n2.getName());
        if (answer == 0) {
            answer = this.compare(n1.getText(), n2.getText());
        }
        return answer;
    }

    @Override
    public int compare(ProcessingInstruction n1, ProcessingInstruction n2) {
        int answer = this.compare(n1.getTarget(), n2.getTarget());
        if (answer == 0) {
            answer = this.compare(n1.getText(), n2.getText());
        }
        return answer;
    }

    public int compareContent(Branch b1, Branch b2) {
        int c2;
        int c1 = b1.nodeCount();
        int answer = c1 - (c2 = b2.nodeCount());
        if (answer == 0) {
            Node n2;
            Node n1;
            for (int i = 0; i < c1 && (answer = this.compare(n1 = b1.node(i), n2 = b2.node(i))) == 0; ++i) {
            }
        }
        return answer;
    }

    @Override
    public int compare(String o1, String o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }
        return o1.compareTo(o2);
    }
}

