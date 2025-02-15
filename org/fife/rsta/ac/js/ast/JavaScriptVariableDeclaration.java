/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast;

import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.ast.CodeBlock;
import org.fife.rsta.ac.js.ast.JavaScriptDeclaration;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.mozilla.javascript.ast.AstNode;

public class JavaScriptVariableDeclaration
extends JavaScriptDeclaration {
    protected TypeDeclaration typeDec;
    protected SourceCompletionProvider provider;
    private boolean reassigned;
    private TypeDeclaration originalTypeDec;

    public JavaScriptVariableDeclaration(String name, int offset, SourceCompletionProvider provider, CodeBlock block) {
        super(name, offset, block);
        this.provider = provider;
    }

    public void setTypeDeclaration(AstNode typeNode) {
        this.typeDec = this.provider.getJavaScriptEngine().getJavaScriptResolver(this.provider).resolveNode(typeNode);
    }

    public void setTypeDeclaration(AstNode typeNode, boolean overrideOriginal) {
        if (!this.reassigned) {
            this.originalTypeDec = this.typeDec;
        }
        this.setTypeDeclaration(typeNode);
        if (overrideOriginal) {
            this.originalTypeDec = this.typeDec;
        }
        this.reassigned = true;
    }

    public void resetVariableToOriginalType() {
        if (this.reassigned) {
            this.reassigned = false;
            this.typeDec = this.originalTypeDec;
        }
        this.originalTypeDec = null;
    }

    public void setTypeDeclaration(TypeDeclaration typeDec) {
        this.typeDec = typeDec;
    }

    public TypeDeclaration getTypeDeclaration() {
        return this.typeDec;
    }

    public String getJavaScriptTypeName() {
        TypeDeclaration dec = this.getTypeDeclaration();
        return dec != null ? dec.getJSName() : this.provider.getTypesFactory().getDefaultTypeDeclaration().getJSName();
    }
}

