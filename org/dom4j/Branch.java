/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j;

import java.util.Iterator;
import java.util.List;
import org.dom4j.Comment;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;

public interface Branch
extends Node {
    public Node node(int var1) throws IndexOutOfBoundsException;

    public int indexOf(Node var1);

    public int nodeCount();

    public Element elementByID(String var1);

    public List<Node> content();

    public Iterator<Node> nodeIterator();

    public void setContent(List<Node> var1);

    public void appendContent(Branch var1);

    public void clearContent();

    public List<ProcessingInstruction> processingInstructions();

    public List<ProcessingInstruction> processingInstructions(String var1);

    public ProcessingInstruction processingInstruction(String var1);

    public void setProcessingInstructions(List<ProcessingInstruction> var1);

    public Element addElement(String var1);

    public Element addElement(QName var1);

    public Element addElement(String var1, String var2);

    public boolean removeProcessingInstruction(String var1);

    public void add(Node var1);

    public void add(Comment var1);

    public void add(Element var1);

    public void add(ProcessingInstruction var1);

    public boolean remove(Node var1);

    public boolean remove(Comment var1);

    public boolean remove(Element var1);

    public boolean remove(ProcessingInstruction var1);

    public void normalize();
}

