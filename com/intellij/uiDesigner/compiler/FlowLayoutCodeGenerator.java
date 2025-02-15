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
import com.intellij.uiDesigner.lw.LwContainer;
import java.awt.FlowLayout;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class FlowLayoutCodeGenerator
extends LayoutCodeGenerator {
    private static Type ourFlowLayoutType = Type.getType((Class)(class$java$awt$FlowLayout == null ? (class$java$awt$FlowLayout = FlowLayoutCodeGenerator.class$("java.awt.FlowLayout")) : class$java$awt$FlowLayout));
    private static Method ourConstructor = Method.getMethod((String)"void <init>(int,int,int)");
    static /* synthetic */ Class class$java$awt$FlowLayout;

    public void generateContainerLayout(LwContainer lwContainer, GeneratorAdapter generator, int componentLocal) {
        generator.loadLocal(componentLocal);
        FlowLayout flowLayout = (FlowLayout)lwContainer.getLayout();
        generator.newInstance(ourFlowLayoutType);
        generator.dup();
        generator.push(flowLayout.getAlignment());
        generator.push(flowLayout.getHgap());
        generator.push(flowLayout.getVgap());
        generator.invokeConstructor(ourFlowLayoutType, ourConstructor);
        generator.invokeVirtual(ourContainerType, ourSetLayoutMethod);
    }

    public void generateComponentLayout(LwComponent lwComponent, GeneratorAdapter generator, int componentLocal, int parentLocal) {
        generator.loadLocal(parentLocal);
        generator.loadLocal(componentLocal);
        generator.invokeVirtual(ourContainerType, ourAddNoConstraintMethod);
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

