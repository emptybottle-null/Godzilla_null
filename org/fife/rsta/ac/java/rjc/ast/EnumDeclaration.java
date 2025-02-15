/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.ast;

import org.fife.rsta.ac.java.rjc.ast.AbstractTypeDeclarationNode;
import org.fife.rsta.ac.java.rjc.lexer.Scanner;

public class EnumDeclaration
extends AbstractTypeDeclarationNode {
    public EnumDeclaration(Scanner s, int offs, String name) {
        super(name, s.createOffset(offs), s.createOffset(offs + name.length()));
    }

    @Override
    public String getTypeString() {
        return "enum";
    }
}

