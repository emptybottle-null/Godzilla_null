/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.commons.GeneratorAdapter
 *  org.objectweb.asm.commons.Method
 */
package com.intellij.uiDesigner.compiler;

import com.intellij.uiDesigner.compiler.AsmCodeGenerator;
import com.intellij.uiDesigner.compiler.LayoutCodeGenerator;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwContainer;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class GridLayoutCodeGenerator
extends LayoutCodeGenerator {
    private static final Method myInitConstraintsMethod = Method.getMethod((String)"void <init> (int,int,int,int,int,int,int,int,java.awt.Dimension,java.awt.Dimension,java.awt.Dimension)");
    private static final Method myInitConstraintsIndentMethod = Method.getMethod((String)"void <init> (int,int,int,int,int,int,int,int,java.awt.Dimension,java.awt.Dimension,java.awt.Dimension,int)");
    private static final Method myInitConstraintsIndentParentMethod = Method.getMethod((String)"void <init> (int,int,int,int,int,int,int,int,java.awt.Dimension,java.awt.Dimension,java.awt.Dimension,int,boolean)");
    private static final Method ourGridLayoutManagerConstructor = Method.getMethod((String)"void <init> (int,int,java.awt.Insets,int,int,boolean,boolean)");
    private static final Type myGridLayoutManagerType = Type.getType((Class)(class$com$intellij$uiDesigner$core$GridLayoutManager == null ? (class$com$intellij$uiDesigner$core$GridLayoutManager = GridLayoutCodeGenerator.class$("com.intellij.uiDesigner.core.GridLayoutManager")) : class$com$intellij$uiDesigner$core$GridLayoutManager));
    private static final Type myGridConstraintsType = Type.getType((Class)(class$com$intellij$uiDesigner$core$GridConstraints == null ? (class$com$intellij$uiDesigner$core$GridConstraints = GridLayoutCodeGenerator.class$("com.intellij.uiDesigner.core.GridConstraints")) : class$com$intellij$uiDesigner$core$GridConstraints));
    public static GridLayoutCodeGenerator INSTANCE = new GridLayoutCodeGenerator();
    static /* synthetic */ Class class$com$intellij$uiDesigner$core$GridLayoutManager;
    static /* synthetic */ Class class$com$intellij$uiDesigner$core$GridConstraints;

    public void generateContainerLayout(LwContainer lwContainer, GeneratorAdapter generator, int componentLocal) {
        if (lwContainer.isGrid()) {
            generator.loadLocal(componentLocal);
            GridLayoutManager layout = (GridLayoutManager)lwContainer.getLayout();
            generator.newInstance(myGridLayoutManagerType);
            generator.dup();
            generator.push(layout.getRowCount());
            generator.push(layout.getColumnCount());
            AsmCodeGenerator.pushPropValue(generator, "java.awt.Insets", layout.getMargin());
            generator.push(layout.getHGap());
            generator.push(layout.getVGap());
            generator.push(layout.isSameSizeHorizontally());
            generator.push(layout.isSameSizeVertically());
            generator.invokeConstructor(myGridLayoutManagerType, ourGridLayoutManagerConstructor);
            generator.invokeVirtual(ourContainerType, ourSetLayoutMethod);
        }
    }

    public void generateComponentLayout(LwComponent lwComponent, GeneratorAdapter generator, int componentLocal, int parentLocal) {
        generator.loadLocal(parentLocal);
        generator.loadLocal(componentLocal);
        GridLayoutCodeGenerator.addNewGridConstraints(generator, lwComponent);
        generator.invokeVirtual(ourContainerType, ourAddMethod);
    }

    private static void addNewGridConstraints(GeneratorAdapter generator, LwComponent lwComponent) {
        GridConstraints constraints = lwComponent.getConstraints();
        generator.newInstance(myGridConstraintsType);
        generator.dup();
        generator.push(constraints.getRow());
        generator.push(constraints.getColumn());
        generator.push(constraints.getRowSpan());
        generator.push(constraints.getColSpan());
        generator.push(constraints.getAnchor());
        generator.push(constraints.getFill());
        generator.push(constraints.getHSizePolicy());
        generator.push(constraints.getVSizePolicy());
        GridLayoutCodeGenerator.newDimensionOrNull(generator, constraints.myMinimumSize);
        GridLayoutCodeGenerator.newDimensionOrNull(generator, constraints.myPreferredSize);
        GridLayoutCodeGenerator.newDimensionOrNull(generator, constraints.myMaximumSize);
        if (constraints.isUseParentLayout()) {
            generator.push(constraints.getIndent());
            generator.push(constraints.isUseParentLayout());
            generator.invokeConstructor(myGridConstraintsType, myInitConstraintsIndentParentMethod);
        } else if (constraints.getIndent() != 0) {
            generator.push(constraints.getIndent());
            generator.invokeConstructor(myGridConstraintsType, myInitConstraintsIndentMethod);
        } else {
            generator.invokeConstructor(myGridConstraintsType, myInitConstraintsMethod);
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

