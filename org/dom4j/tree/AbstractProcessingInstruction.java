/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.dom4j.Element;
import org.dom4j.ProcessingInstruction;
import org.dom4j.Visitor;
import org.dom4j.tree.AbstractNode;

public abstract class AbstractProcessingInstruction
extends AbstractNode
implements ProcessingInstruction {
    @Override
    public short getNodeType() {
        return 7;
    }

    @Override
    public String getPath(Element context) {
        Element parent = this.getParent();
        return parent != null && parent != context ? parent.getPath(context) + "/processing-instruction()" : "processing-instruction()";
    }

    @Override
    public String getUniquePath(Element context) {
        Element parent = this.getParent();
        return parent != null && parent != context ? parent.getUniquePath(context) + "/processing-instruction()" : "processing-instruction()";
    }

    public String toString() {
        return super.toString() + " [ProcessingInstruction: &" + this.getName() + ";]";
    }

    @Override
    public String asXML() {
        return "<?" + this.getName() + " " + this.getText() + "?>";
    }

    @Override
    public void write(Writer writer) throws IOException {
        writer.write("<?");
        writer.write(this.getName());
        writer.write(" ");
        writer.write(this.getText());
        writer.write("?>");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void setValue(String name, String value) {
        throw new UnsupportedOperationException("This PI is read-only and cannot be modified");
    }

    @Override
    public void setValues(Map<String, String> data) {
        throw new UnsupportedOperationException("This PI is read-only and cannot be modified");
    }

    @Override
    public String getName() {
        return this.getTarget();
    }

    @Override
    public void setName(String name) {
        this.setTarget(name);
    }

    @Override
    public boolean removeValue(String name) {
        return false;
    }

    protected String toString(Map<String, String> values) {
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<String, String> entry : values.entrySet()) {
            String name = entry.getKey();
            String value = entry.getValue();
            buffer.append(name);
            buffer.append("=\"");
            buffer.append(value);
            buffer.append("\" ");
        }
        buffer.setLength(buffer.length() - 1);
        return buffer.toString();
    }

    protected Map<String, String> parseValues(String text) {
        HashMap<String, String> data = new HashMap<String, String>();
        StringTokenizer s = new StringTokenizer(text, " ='\"", true);
        while (s.hasMoreTokens()) {
            String name = this.getName(s);
            if (!s.hasMoreTokens()) continue;
            String value = this.getValue(s);
            data.put(name, value);
        }
        return data;
    }

    private String getName(StringTokenizer tokenizer) {
        String token = tokenizer.nextToken();
        StringBuilder name = new StringBuilder(token);
        while (tokenizer.hasMoreTokens() && !(token = tokenizer.nextToken()).equals("=")) {
            name.append(token);
        }
        return name.toString().trim();
    }

    private String getValue(StringTokenizer tokenizer) {
        String token = tokenizer.nextToken();
        StringBuilder value = new StringBuilder();
        while (tokenizer.hasMoreTokens() && !token.equals("'") && !token.equals("\"")) {
            token = tokenizer.nextToken();
        }
        String quote = token;
        while (tokenizer.hasMoreTokens() && !quote.equals(token = tokenizer.nextToken())) {
            value.append(token);
        }
        return value.toString();
    }
}

