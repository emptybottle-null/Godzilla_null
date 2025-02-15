/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.ast;

import org.fife.rsta.ac.java.rjc.ast.AbstractASTNode;
import org.fife.rsta.ac.java.rjc.lexer.Scanner;

public class Package
extends AbstractASTNode {
    public Package(Scanner s, int offs, String pkg) {
        super(pkg, s.createOffset(offs), s.createOffset(offs + pkg.length()));
    }
}

