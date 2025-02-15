/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.commons.GeneratorAdapter
 *  org.objectweb.asm.commons.Method
 */
package com.intellij.uiDesigner.compiler;

import com.intellij.uiDesigner.compiler.LayoutCodeGenerator;
import com.intellij.uiDesigner.lw.LwComponent;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class ScrollPaneLayoutCodeGenerator
extends LayoutCodeGenerator {
    private final Type myScrollPaneType = Type.getType((Class)(class$javax$swing$JScrollPane == null ? (class$javax$swing$JScrollPane = ScrollPaneLayoutCodeGenerator.class$("javax.swing.JScrollPane")) : class$javax$swing$JScrollPane));
    private final Method mySetViewportViewMethod = Method.getMethod((String)"void setViewportView(java.awt.Component)");
    static /* synthetic */ Class class$javax$swing$JScrollPane;

    public void generateComponentLayout(LwComponent lwComponent, GeneratorAdapter generator, int componentLocal, int parentLocal) {
        generator.loadLocal(parentLocal);
        generator.loadLocal(componentLocal);
        generator.invokeVirtual(this.myScrollPaneType, this.mySetViewportViewMethod);
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

