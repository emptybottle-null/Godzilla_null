/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Visitor;
import org.dom4j.tree.AbstractNode;

public abstract class AbstractEntity
extends AbstractNode
implements Entity {
    @Override
    public short getNodeType() {
        return 5;
    }

    @Override
    public String getPath(Element context) {
        Element parent = this.getParent();
        return parent != null && parent != context ? parent.getPath(context) + "/text()" : "text()";
    }

    @Override
    public String getUniquePath(Element context) {
        Element parent = this.getParent();
        return parent != null && parent != context ? parent.getUniquePath(context) + "/text()" : "text()";
    }

    public String toString() {
        return super.toString() + " [Entity: &" + this.getName() + ";]";
    }

    @Override
    public String getStringValue() {
        return "&" + this.getName() + ";";
    }

    @Override
    public String asXML() {
        return "&" + this.getName() + ";";
    }

    @Override
    public void write(Writer writer) throws IOException {
        writer.write("&");
        writer.write(this.getName());
        writer.write(";");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

