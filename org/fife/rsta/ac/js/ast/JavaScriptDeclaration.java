/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.ast;

import org.fife.rsta.ac.js.ast.CodeBlock;
import org.fife.rsta.ac.js.ast.TypeDeclarationOptions;

public abstract class JavaScriptDeclaration {
    private String name;
    private int offset;
    private int start;
    private int end;
    private CodeBlock block;
    private TypeDeclarationOptions options;

    public JavaScriptDeclaration(String name, int offset, CodeBlock block) {
        this.name = name;
        this.offset = offset;
        this.block = block;
    }

    public String getName() {
        return this.name;
    }

    public int getOffset() {
        return this.offset;
    }

    public int getEndOffset() {
        return this.end;
    }

    public void setEndOffset(int end) {
        this.end = end;
    }

    public void setStartOffset(int start) {
        this.start = start;
    }

    public int getStartOffSet() {
        return this.start;
    }

    public CodeBlock getCodeBlock() {
        return this.block;
    }

    public void setTypeDeclarationOptions(TypeDeclarationOptions options) {
        this.options = options;
    }

    public TypeDeclarationOptions getTypeDeclarationOptions() {
        return this.options;
    }
}

