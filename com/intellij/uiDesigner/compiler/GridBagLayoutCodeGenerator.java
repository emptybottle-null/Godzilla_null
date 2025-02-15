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
import com.intellij.uiDesigner.compiler.GridBagConverter;
import com.intellij.uiDesigner.compiler.LayoutCodeGenerator;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwContainer;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class GridBagLayoutCodeGenerator
extends LayoutCodeGenerator {
    private static Type ourGridBagLayoutType = Type.getType((Class)(class$java$awt$GridBagLayout == null ? (class$java$awt$GridBagLayout = GridBagLayoutCodeGenerator.class$("java.awt.GridBagLayout")) : class$java$awt$GridBagLayout));
    private static Type ourGridBagConstraintsType = Type.getType((Class)(class$java$awt$GridBagConstraints == null ? (class$java$awt$GridBagConstraints = GridBagLayoutCodeGenerator.class$("java.awt.GridBagConstraints")) : class$java$awt$GridBagConstraints));
    private static Method ourDefaultConstructor = Method.getMethod((String)"void <init> ()");
    private static Type myPanelType = Type.getType((Class)(class$javax$swing$JPanel == null ? (class$javax$swing$JPanel = GridBagLayoutCodeGenerator.class$("javax.swing.JPanel")) : class$javax$swing$JPanel));
    static /* synthetic */ Class class$java$awt$GridBagLayout;
    static /* synthetic */ Class class$java$awt$GridBagConstraints;
    static /* synthetic */ Class class$javax$swing$JPanel;
    static /* synthetic */ Class class$com$intellij$uiDesigner$core$Spacer;
    static /* synthetic */ Class class$java$awt$Insets;
    static /* synthetic */ Class class$java$awt$Component;
    static /* synthetic */ Class class$java$awt$Dimension;

    public String mapComponentClass(String componentClassName) {
        if (componentClassName.equals((class$com$intellij$uiDesigner$core$Spacer == null ? (class$com$intellij$uiDesigner$core$Spacer = GridBagLayoutCodeGenerator.class$("com.intellij.uiDesigner.core.Spacer")) : class$com$intellij$uiDesigner$core$Spacer).getName())) {
            return (class$javax$swing$JPanel == null ? (class$javax$swing$JPanel = GridBagLayoutCodeGenerator.class$("javax.swing.JPanel")) : class$javax$swing$JPanel).getName();
        }
        return super.mapComponentClass(componentClassName);
    }

    public void generateContainerLayout(LwContainer lwContainer, GeneratorAdapter generator, int componentLocal) {
        generator.loadLocal(componentLocal);
        generator.newInstance(ourGridBagLayoutType);
        generator.dup();
        generator.invokeConstructor(ourGridBagLayoutType, ourDefaultConstructor);
        generator.invokeVirtual(ourContainerType, ourSetLayoutMethod);
    }

    private void generateFillerPanel(GeneratorAdapter generator, int parentLocal, GridBagConverter.Result result) {
        int panelLocal = generator.newLocal(myPanelType);
        generator.newInstance(myPanelType);
        generator.dup();
        generator.invokeConstructor(myPanelType, ourDefaultConstructor);
        generator.storeLocal(panelLocal);
        GridBagLayoutCodeGenerator.generateConversionResult(generator, result, panelLocal, parentLocal);
    }

    public void generateComponentLayout(LwComponent component, GeneratorAdapter generator, int componentLocal, int parentLocal) {
        GridBagConstraints gbc = component.getCustomLayoutConstraints() instanceof GridBagConstraints ? (GridBagConstraints)component.getCustomLayoutConstraints() : new GridBagConstraints();
        GridBagConverter.constraintsToGridBag(component.getConstraints(), gbc);
        GridBagLayoutCodeGenerator.generateGridBagConstraints(generator, gbc, componentLocal, parentLocal);
    }

    private static void generateConversionResult(GeneratorAdapter generator, GridBagConverter.Result result, int componentLocal, int parentLocal) {
        GridBagLayoutCodeGenerator.checkSetSize(generator, componentLocal, "setMinimumSize", result.minimumSize);
        GridBagLayoutCodeGenerator.checkSetSize(generator, componentLocal, "setPreferredSize", result.preferredSize);
        GridBagLayoutCodeGenerator.checkSetSize(generator, componentLocal, "setMaximumSize", result.maximumSize);
        GridBagLayoutCodeGenerator.generateGridBagConstraints(generator, result.constraints, componentLocal, parentLocal);
    }

    private static void generateGridBagConstraints(GeneratorAdapter generator, GridBagConstraints constraints, int componentLocal, int parentLocal) {
        int gbcLocal = generator.newLocal(ourGridBagConstraintsType);
        generator.newInstance(ourGridBagConstraintsType);
        generator.dup();
        generator.invokeConstructor(ourGridBagConstraintsType, ourDefaultConstructor);
        generator.storeLocal(gbcLocal);
        GridBagConstraints defaults = new GridBagConstraints();
        if (defaults.gridx != constraints.gridx) {
            GridBagLayoutCodeGenerator.setIntField(generator, gbcLocal, "gridx", constraints.gridx);
        }
        if (defaults.gridy != constraints.gridy) {
            GridBagLayoutCodeGenerator.setIntField(generator, gbcLocal, "gridy", constraints.gridy);
        }
        if (defaults.gridwidth != constraints.gridwidth) {
            GridBagLayoutCodeGenerator.setIntField(generator, gbcLocal, "gridwidth", constraints.gridwidth);
        }
        if (defaults.gridheight != constraints.gridheight) {
            GridBagLayoutCodeGenerator.setIntField(generator, gbcLocal, "gridheight", constraints.gridheight);
        }
        if (defaults.weightx != constraints.weightx) {
            GridBagLayoutCodeGenerator.setDoubleField(generator, gbcLocal, "weightx", constraints.weightx);
        }
        if (defaults.weighty != constraints.weighty) {
            GridBagLayoutCodeGenerator.setDoubleField(generator, gbcLocal, "weighty", constraints.weighty);
        }
        if (defaults.anchor != constraints.anchor) {
            GridBagLayoutCodeGenerator.setIntField(generator, gbcLocal, "anchor", constraints.anchor);
        }
        if (defaults.fill != constraints.fill) {
            GridBagLayoutCodeGenerator.setIntField(generator, gbcLocal, "fill", constraints.fill);
        }
        if (defaults.ipadx != constraints.ipadx) {
            GridBagLayoutCodeGenerator.setIntField(generator, gbcLocal, "ipadx", constraints.ipadx);
        }
        if (defaults.ipady != constraints.ipady) {
            GridBagLayoutCodeGenerator.setIntField(generator, gbcLocal, "ipady", constraints.ipady);
        }
        if (!defaults.insets.equals(constraints.insets)) {
            generator.loadLocal(gbcLocal);
            AsmCodeGenerator.pushPropValue(generator, (class$java$awt$Insets == null ? (class$java$awt$Insets = GridBagLayoutCodeGenerator.class$("java.awt.Insets")) : class$java$awt$Insets).getName(), constraints.insets);
            generator.putField(ourGridBagConstraintsType, "insets", Type.getType((Class)(class$java$awt$Insets == null ? (class$java$awt$Insets = GridBagLayoutCodeGenerator.class$("java.awt.Insets")) : class$java$awt$Insets)));
        }
        generator.loadLocal(parentLocal);
        generator.loadLocal(componentLocal);
        generator.loadLocal(gbcLocal);
        generator.invokeVirtual(ourContainerType, ourAddMethod);
    }

    private static void checkSetSize(GeneratorAdapter generator, int componentLocal, String methodName, Dimension dimension) {
        if (dimension != null) {
            generator.loadLocal(componentLocal);
            AsmCodeGenerator.pushPropValue(generator, "java.awt.Dimension", dimension);
            generator.invokeVirtual(Type.getType((Class)(class$java$awt$Component == null ? (class$java$awt$Component = GridBagLayoutCodeGenerator.class$("java.awt.Component")) : class$java$awt$Component)), new Method(methodName, Type.VOID_TYPE, new Type[]{Type.getType((Class)(class$java$awt$Dimension == null ? (class$java$awt$Dimension = GridBagLayoutCodeGenerator.class$("java.awt.Dimension")) : class$java$awt$Dimension))}));
        }
    }

    private static void setIntField(GeneratorAdapter generator, int local, String fieldName, int value) {
        generator.loadLocal(local);
        generator.push(value);
        generator.putField(ourGridBagConstraintsType, fieldName, Type.INT_TYPE);
    }

    private static void setDoubleField(GeneratorAdapter generator, int local, String fieldName, double value) {
        generator.loadLocal(local);
        generator.push(value);
        generator.putField(ourGridBagConstraintsType, fieldName, Type.DOUBLE_TYPE);
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

