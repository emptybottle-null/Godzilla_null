/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.dom4j.CDATA;
import org.dom4j.Visitor;
import org.dom4j.tree.AbstractCharacterData;

public abstract class AbstractCDATA
extends AbstractCharacterData
implements CDATA {
    @Override
    public short getNodeType() {
        return 4;
    }

    public String toString() {
        return super.toString() + " [CDATA: \"" + this.getText() + "\"]";
    }

    @Override
    public String asXML() {
        StringWriter writer = new StringWriter();
        try {
            this.write(writer);
        } catch (IOException iOException) {
            // empty catch block
        }
        return writer.toString();
    }

    @Override
    public void write(Writer writer) throws IOException {
        writer.write("<![CDATA[");
        if (this.getText() != null) {
            writer.write(this.getText());
        }
        writer.write("]]>");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

