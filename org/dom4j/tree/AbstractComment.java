/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import org.dom4j.Comment;
import org.dom4j.Element;
import org.dom4j.Visitor;
import org.dom4j.tree.AbstractCharacterData;

public abstract class AbstractComment
extends AbstractCharacterData
implements Comment {
    @Override
    public short getNodeType() {
        return 8;
    }

    @Override
    public String getPath(Element context) {
        Element parent = this.getParent();
        return parent != null && parent != context ? parent.getPath(context) + "/comment()" : "comment()";
    }

    @Override
    public String getUniquePath(Element context) {
        Element parent = this.getParent();
        return parent != null && parent != context ? parent.getUniquePath(context) + "/comment()" : "comment()";
    }

    public String toString() {
        return super.toString() + " [Comment: \"" + this.getText() + "\"]";
    }

    @Override
    public String asXML() {
        return "<!--" + this.getText() + "-->";
    }

    @Override
    public void write(Writer writer) throws IOException {
        writer.write("<!--");
        writer.write(this.getText());
        writer.write("-->");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

