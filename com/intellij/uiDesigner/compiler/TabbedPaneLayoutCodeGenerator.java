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
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwTabbedPane;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class TabbedPaneLayoutCodeGenerator
extends LayoutCodeGenerator {
    private final Type myTabbedPaneType = Type.getType((Class)(class$javax$swing$JTabbedPane == null ? (class$javax$swing$JTabbedPane = TabbedPaneLayoutCodeGenerator.class$("javax.swing.JTabbedPane")) : class$javax$swing$JTabbedPane));
    private final Method myAddTabMethod = Method.getMethod((String)"void addTab(java.lang.String,javax.swing.Icon,java.awt.Component,java.lang.String)");
    private final Method mySetDisabledIconAtMethod = Method.getMethod((String)"void setDisabledIconAt(int,javax.swing.Icon)");
    private final Method mySetEnabledAtMethod = Method.getMethod((String)"void setEnabledAt(int,boolean)");
    static /* synthetic */ Class class$javax$swing$JTabbedPane;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$javax$swing$Icon;

    public void generateComponentLayout(LwComponent lwComponent, GeneratorAdapter generator, int componentLocal, int parentLocal) {
        generator.loadLocal(parentLocal);
        LwTabbedPane.Constraints tabConstraints = (LwTabbedPane.Constraints)lwComponent.getCustomLayoutConstraints();
        if (tabConstraints == null) {
            throw new IllegalArgumentException("tab constraints cannot be null: " + lwComponent.getId());
        }
        AsmCodeGenerator.pushPropValue(generator, (class$java$lang$String == null ? (class$java$lang$String = TabbedPaneLayoutCodeGenerator.class$("java.lang.String")) : class$java$lang$String).getName(), tabConstraints.myTitle);
        if (tabConstraints.myIcon == null) {
            generator.push((String)null);
        } else {
            AsmCodeGenerator.pushPropValue(generator, (class$javax$swing$Icon == null ? (class$javax$swing$Icon = TabbedPaneLayoutCodeGenerator.class$("javax.swing.Icon")) : class$javax$swing$Icon).getName(), tabConstraints.myIcon);
        }
        generator.loadLocal(componentLocal);
        if (tabConstraints.myToolTip == null) {
            generator.push((String)null);
        } else {
            AsmCodeGenerator.pushPropValue(generator, (class$java$lang$String == null ? (class$java$lang$String = TabbedPaneLayoutCodeGenerator.class$("java.lang.String")) : class$java$lang$String).getName(), tabConstraints.myToolTip);
        }
        generator.invokeVirtual(this.myTabbedPaneType, this.myAddTabMethod);
        int index = lwComponent.getParent().indexOfComponent(lwComponent);
        if (tabConstraints.myDisabledIcon != null) {
            generator.loadLocal(parentLocal);
            generator.push(index);
            AsmCodeGenerator.pushPropValue(generator, (class$javax$swing$Icon == null ? (class$javax$swing$Icon = TabbedPaneLayoutCodeGenerator.class$("javax.swing.Icon")) : class$javax$swing$Icon).getName(), tabConstraints.myDisabledIcon);
            generator.invokeVirtual(this.myTabbedPaneType, this.mySetDisabledIconAtMethod);
        }
        if (!tabConstraints.myEnabled) {
            generator.loadLocal(parentLocal);
            generator.push(index);
            generator.push(tabConstraints.myEnabled);
            generator.invokeVirtual(this.myTabbedPaneType, this.mySetEnabledAtMethod);
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

