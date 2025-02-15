/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.ast;

import java.util.Iterator;
import java.util.List;
import org.fife.rsta.ac.java.rjc.ast.AbstractMember;
import org.fife.rsta.ac.java.rjc.ast.CodeBlock;
import org.fife.rsta.ac.java.rjc.ast.FormalParameter;
import org.fife.rsta.ac.java.rjc.lang.Modifiers;
import org.fife.rsta.ac.java.rjc.lang.Type;
import org.fife.rsta.ac.java.rjc.lexer.Scanner;
import org.fife.rsta.ac.java.rjc.lexer.Token;

public class Method
extends AbstractMember {
    private Modifiers modifiers;
    private Type type;
    private List<FormalParameter> parameters;
    private List<String> thrownTypeNames;
    private CodeBlock body;
    private boolean deprecated;
    private String docComment;

    public Method(Scanner s, Modifiers modifiers, Type type, Token nameToken, List<FormalParameter> params, List<String> thrownTypeNames) {
        super(nameToken.getLexeme(), s.createOffset(nameToken.getOffset()), s.createOffset(nameToken.getOffset() + nameToken.getLength()));
        if (modifiers == null) {
            modifiers = new Modifiers();
        }
        this.modifiers = modifiers;
        this.type = type;
        this.parameters = params;
        this.thrownTypeNames = thrownTypeNames;
    }

    public CodeBlock getBody() {
        return this.body;
    }

    public boolean getBodyContainsOffset(int offs) {
        return offs >= this.getBodyStartOffset() && offs < this.getBodyEndOffset();
    }

    public int getBodyEndOffset() {
        return this.body == null ? Integer.MAX_VALUE : this.body.getNameEndOffset();
    }

    public int getBodyStartOffset() {
        return this.getNameStartOffset();
    }

    @Override
    public String getDocComment() {
        return this.docComment;
    }

    @Override
    public Modifiers getModifiers() {
        return this.modifiers;
    }

    public String getNameAndParameters() {
        StringBuilder sb = new StringBuilder(this.getName());
        sb.append('(');
        int count = this.getParameterCount();
        for (int i = 0; i < count; ++i) {
            FormalParameter fp = this.getParameter(i);
            sb.append(fp.getType().getName(false));
            sb.append(' ');
            sb.append(fp.getName());
            if (i >= count - 1) continue;
            sb.append(", ");
        }
        sb.append(')');
        return sb.toString();
    }

    public FormalParameter getParameter(int index) {
        return this.parameters.get(index);
    }

    public int getParameterCount() {
        return this.parameters.size();
    }

    public Iterator<FormalParameter> getParameterIterator() {
        return this.parameters.iterator();
    }

    public int getThrownTypeNameCount() {
        return this.thrownTypeNames == null ? 0 : this.thrownTypeNames.size();
    }

    @Override
    public Type getType() {
        return this.type;
    }

    public boolean isConstructor() {
        return this.type == null;
    }

    @Override
    public boolean isDeprecated() {
        return this.deprecated;
    }

    public void setBody(CodeBlock body) {
        this.body = body;
    }

    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public void setDocComment(String comment) {
        this.docComment = comment;
    }
}

