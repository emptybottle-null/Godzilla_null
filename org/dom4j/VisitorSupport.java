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
import org.dom4j.Visitor;

public abstract class VisitorSupport
implements Visitor {
    @Override
    public void visit(Document document) {
    }

    @Override
    public void visit(DocumentType documentType) {
    }

    @Override
    public void visit(Element node) {
    }

    @Override
    public void visit(Attribute node) {
    }

    @Override
    public void visit(CDATA node) {
    }

    @Override
    public void visit(Comment node) {
    }

    @Override
    public void visit(Entity node) {
    }

    @Override
    public void visit(Namespace namespace) {
    }

    @Override
    public void visit(ProcessingInstruction node) {
    }

    @Override
    public void visit(Text node) {
    }
}

