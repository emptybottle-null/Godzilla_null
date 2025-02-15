/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j;

import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.ProcessingInstruction;
import org.dom4j.Text;

public interface Visitor {
    public void visit(Document var1);

    public void visit(DocumentType var1);

    public void visit(Element var1);

    public void visit(Attribute var1);

    public void visit(CDATA var1);

    public void visit(Comment var1);

    public void visit(Entity var1);

    public void visit(Namespace var1);

    public void visit(ProcessingInstruction var1);

    public void visit(Text var1);
}

