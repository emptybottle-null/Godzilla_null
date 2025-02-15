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

public class SplitPaneLayoutCodeGenerator
extends LayoutCodeGenerator {
    private final Type mySplitPaneType = Type.getType((Class)(class$javax$swing$JSplitPane == null ? (class$javax$swing$JSplitPane = SplitPaneLayoutCodeGenerator.class$("javax.swing.JSplitPane")) : class$javax$swing$JSplitPane));
    private final Method mySetLeftMethod = Method.getMethod((String)"void setLeftComponent(java.awt.Component)");
    private final Method mySetRightMethod = Method.getMethod((String)"void setRightComponent(java.awt.Component)");
    static /* synthetic */ Class class$javax$swing$JSplitPane;

    public void generateComponentLayout(LwComponent lwComponent, GeneratorAdapter generator, int componentLocal, int parentLocal) {
        generator.loadLocal(parentLocal);
        generator.loadLocal(componentLocal);
        if ("left".equals(lwComponent.getCustomLayoutConstraints())) {
            generator.invokeVirtual(this.mySplitPaneType, this.mySetLeftMethod);
        } else {
            generator.invokeVirtual(this.mySplitPaneType, this.mySetRightMethod);
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

