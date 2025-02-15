/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.js.resolver;

import java.io.IOException;
import org.fife.rsta.ac.js.SourceCompletionProvider;
import org.fife.rsta.ac.js.ast.jsType.JavaScriptType;
import org.fife.rsta.ac.js.ast.type.TypeDeclaration;
import org.fife.rsta.ac.js.completion.JSMethodData;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.FunctionCall;

public abstract class JavaScriptResolver {
    protected SourceCompletionProvider provider;

    public JavaScriptResolver(SourceCompletionProvider provider) {
        this.provider = provider;
    }

    public abstract TypeDeclaration resolveNode(AstNode var1);

    public abstract TypeDeclaration resolveParamNode(String var1) throws IOException;

    public abstract JavaScriptType compileText(String var1) throws IOException;

    protected abstract TypeDeclaration resolveNativeType(AstNode var1);

    public abstract String getLookupText(JSMethodData var1, String var2);

    public abstract String getFunctionNameLookup(FunctionCall var1, SourceCompletionProvider var2);
}

