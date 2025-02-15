/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.ast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.fife.rsta.ac.java.rjc.ast.AbstractTypeDeclarationNode;
import org.fife.rsta.ac.java.rjc.lang.Type;
import org.fife.rsta.ac.java.rjc.lexer.Scanner;

public class NormalInterfaceDeclaration
extends AbstractTypeDeclarationNode {
    private List<Type> extendedList = new ArrayList<Type>(1);

    public NormalInterfaceDeclaration(Scanner s, int offs, String name) {
        super(name, s.createOffset(offs), s.createOffset(offs + name.length()));
    }

    public void addExtended(Type extended) {
        this.extendedList.add(extended);
    }

    public int getExtendedCount() {
        return this.extendedList.size();
    }

    public Iterator<Type> getExtendedIterator() {
        return this.extendedList.iterator();
    }

    @Override
    public String getTypeString() {
        return "interface";
    }
}

