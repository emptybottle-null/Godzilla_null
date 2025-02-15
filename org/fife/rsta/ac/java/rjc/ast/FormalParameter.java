/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.ast;

import java.util.List;
import org.fife.rsta.ac.java.rjc.ast.LocalVariable;
import org.fife.rsta.ac.java.rjc.lang.Annotation;
import org.fife.rsta.ac.java.rjc.lang.Type;
import org.fife.rsta.ac.java.rjc.lexer.Scanner;

public class FormalParameter
extends LocalVariable {
    private List<Annotation> annotations;

    public FormalParameter(Scanner s, boolean isFinal, Type type, int offs, String name, List<Annotation> annotations) {
        super(s, isFinal, type, offs, name);
        this.annotations = annotations;
    }

    public int getAnnotationCount() {
        return this.annotations == null ? 0 : this.annotations.size();
    }

    @Override
    public String toString() {
        return this.getType() + " " + this.getName();
    }
}

