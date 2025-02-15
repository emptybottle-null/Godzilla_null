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
import com.intellij.uiDesigner.compiler.FormLayoutUtils;
import com.intellij.uiDesigner.compiler.LayoutCodeGenerator;
import com.intellij.uiDesigner.compiler.Utils;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwContainer;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class FormLayoutCodeGenerator
extends LayoutCodeGenerator {
    private static final Type ourFormLayoutType = Type.getType((Class)(class$com$jgoodies$forms$layout$FormLayout == null ? (class$com$jgoodies$forms$layout$FormLayout = FormLayoutCodeGenerator.class$("com.jgoodies.forms.layout.FormLayout")) : class$com$jgoodies$forms$layout$FormLayout));
    private static final Type ourCellConstraintsType = Type.getType((Class)(class$com$jgoodies$forms$layout$CellConstraints == null ? (class$com$jgoodies$forms$layout$CellConstraints = FormLayoutCodeGenerator.class$("com.jgoodies.forms.layout.CellConstraints")) : class$com$jgoodies$forms$layout$CellConstraints));
    private static final Type ourCellAlignmentType = Type.getType((Class)(class$com$jgoodies$forms$layout$CellConstraints$Alignment == null ? (class$com$jgoodies$forms$layout$CellConstraints$Alignment = FormLayoutCodeGenerator.class$("com.jgoodies.forms.layout.CellConstraints$Alignment")) : class$com$jgoodies$forms$layout$CellConstraints$Alignment));
    private static final Method ourFormLayoutConstructor = Method.getMethod((String)"void <init>(java.lang.String,java.lang.String)");
    private static final Method ourCellConstraintsConstructor = Method.getMethod((String)"void <init>(int,int,int,int,com.jgoodies.forms.layout.CellConstraints$Alignment,com.jgoodies.forms.layout.CellConstraints$Alignment,java.awt.Insets)");
    private static final Method ourSetRowGroupsMethod = Method.getMethod((String)"void setRowGroups(int[][])");
    private static final Method ourSetColumnGroupsMethod = Method.getMethod((String)"void setColumnGroups(int[][])");
    public static String[] HORZ_ALIGN_FIELDS = new String[]{"LEFT", "CENTER", "RIGHT", "FILL"};
    public static String[] VERT_ALIGN_FIELDS = new String[]{"TOP", "CENTER", "BOTTOM", "FILL"};
    static /* synthetic */ Class class$com$jgoodies$forms$layout$FormLayout;
    static /* synthetic */ Class class$com$jgoodies$forms$layout$CellConstraints;
    static /* synthetic */ Class class$com$jgoodies$forms$layout$CellConstraints$Alignment;
    static /* synthetic */ Class class$java$awt$Insets;

    public void generateContainerLayout(LwContainer lwContainer, GeneratorAdapter generator, int componentLocal) {
        FormLayout formLayout = (FormLayout)lwContainer.getLayout();
        generator.loadLocal(componentLocal);
        generator.newInstance(ourFormLayoutType);
        generator.dup();
        generator.push(FormLayoutUtils.getEncodedColumnSpecs(formLayout));
        generator.push(FormLayoutUtils.getEncodedRowSpecs(formLayout));
        generator.invokeConstructor(ourFormLayoutType, ourFormLayoutConstructor);
        FormLayoutCodeGenerator.generateGroups(generator, formLayout.getRowGroups(), ourSetRowGroupsMethod);
        FormLayoutCodeGenerator.generateGroups(generator, formLayout.getColumnGroups(), ourSetColumnGroupsMethod);
        generator.invokeVirtual(ourContainerType, ourSetLayoutMethod);
    }

    private static void generateGroups(GeneratorAdapter generator, int[][] groups, Method setGroupsMethod) {
        if (groups.length == 0) {
            return;
        }
        int groupLocal = generator.newLocal(Type.getType((String)"[I"));
        generator.dup();
        generator.push(groups.length);
        generator.newArray(Type.getType((String)"[I"));
        for (int i = 0; i < groups.length; ++i) {
            generator.dup();
            generator.push(groups[i].length);
            generator.newArray(Type.INT_TYPE);
            generator.storeLocal(groupLocal);
            for (int j = 0; j < groups[i].length; ++j) {
                generator.loadLocal(groupLocal);
                generator.push(j);
                generator.push(groups[i][j]);
                generator.visitInsn(79);
            }
            generator.push(i);
            generator.loadLocal(groupLocal);
            generator.visitInsn(83);
        }
        generator.invokeVirtual(ourFormLayoutType, setGroupsMethod);
    }

    public void generateComponentLayout(LwComponent lwComponent, GeneratorAdapter generator, int componentLocal, int parentLocal) {
        generator.loadLocal(parentLocal);
        generator.loadLocal(componentLocal);
        FormLayoutCodeGenerator.addNewCellConstraints(generator, lwComponent);
        generator.invokeVirtual(ourContainerType, ourAddMethod);
    }

    private static void addNewCellConstraints(GeneratorAdapter generator, LwComponent lwComponent) {
        GridConstraints constraints = lwComponent.getConstraints();
        CellConstraints cc = (CellConstraints)lwComponent.getCustomLayoutConstraints();
        generator.newInstance(ourCellConstraintsType);
        generator.dup();
        generator.push(constraints.getColumn() + 1);
        generator.push(constraints.getRow() + 1);
        generator.push(constraints.getColSpan());
        generator.push(constraints.getRowSpan());
        if (cc.hAlign == CellConstraints.DEFAULT) {
            generator.getStatic(ourCellConstraintsType, "DEFAULT", ourCellAlignmentType);
        } else {
            int hAlign = Utils.alignFromConstraints(constraints, true);
            generator.getStatic(ourCellConstraintsType, HORZ_ALIGN_FIELDS[hAlign], ourCellAlignmentType);
        }
        if (cc.vAlign == CellConstraints.DEFAULT) {
            generator.getStatic(ourCellConstraintsType, "DEFAULT", ourCellAlignmentType);
        } else {
            int vAlign = Utils.alignFromConstraints(constraints, false);
            generator.getStatic(ourCellConstraintsType, VERT_ALIGN_FIELDS[vAlign], ourCellAlignmentType);
        }
        AsmCodeGenerator.pushPropValue(generator, (class$java$awt$Insets == null ? (class$java$awt$Insets = FormLayoutCodeGenerator.class$("java.awt.Insets")) : class$java$awt$Insets).getName(), cc.insets);
        generator.invokeConstructor(ourCellConstraintsType, ourCellConstraintsConstructor);
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

