/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.compiler;

import javassist.compiler.CompileError;
import javassist.compiler.Lex;

public class SyntaxError
extends CompileError {
    private static final long serialVersionUID = 1L;

    public SyntaxError(Lex lexer) {
        super("syntax error near \"" + lexer.getTextAround() + "\"", lexer);
    }
}

