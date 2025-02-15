/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast;

import org.fife.rsta.ac.js.ast.CodeBlock;
import org.fife.rsta.ac.js.ast.JavaScriptDeclaration;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;

public class JavaScriptFunctionDeclaration
extends JavaScriptDeclaration {
    private TypeDeclaration typeDec;
    private String functionName;

    public JavaScriptFunctionDeclaration(String name, int offset, CodeBlock block, TypeDeclaration typeDec) {
        super(name, offset, block);
        this.typeDec = typeDec;
    }

    public TypeDeclaration getTypeDeclaration() {
        return this.typeDec;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionName() {
        return this.functionName;
    }
}

