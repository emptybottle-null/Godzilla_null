/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.commons.GeneratorAdapter
 *  org.objectweb.asm.commons.Method
 */
package com.intellij.uiDesigner.compiler;

import com.intellij.uiDesigner.compiler.PropertyCodeGenerator;
import com.intellij.uiDesigner.lw.ColorDescriptor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class ColorPropertyCodeGenerator
extends PropertyCodeGenerator {
    private static final Type ourColorType = Type.getType((Class)(class$java$awt$Color == null ? (class$java$awt$Color = ColorPropertyCodeGenerator.class$("java.awt.Color")) : class$java$awt$Color));
    private static final Type ourObjectType = Type.getType((Class)(class$java$lang$Object == null ? (class$java$lang$Object = ColorPropertyCodeGenerator.class$("java.lang.Object")) : class$java$lang$Object));
    private static final Type ourUIManagerType = Type.getType((String)"Ljavax/swing/UIManager;");
    private static final Type ourSystemColorType = Type.getType((Class)(class$java$awt$SystemColor == null ? (class$java$awt$SystemColor = ColorPropertyCodeGenerator.class$("java.awt.SystemColor")) : class$java$awt$SystemColor));
    private static final Method ourInitMethod = Method.getMethod((String)"void <init>(int)");
    private static final Method ourGetColorMethod = new Method("getColor", ourColorType, new Type[]{ourObjectType});
    static /* synthetic */ Class class$java$awt$Color;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$awt$SystemColor;

    public void generatePushValue(GeneratorAdapter generator, Object value) {
        ColorDescriptor descriptor = (ColorDescriptor)value;
        if (descriptor.getColor() != null) {
            generator.newInstance(ourColorType);
            generator.dup();
            generator.push(descriptor.getColor().getRGB());
            generator.invokeConstructor(ourColorType, ourInitMethod);
        } else if (descriptor.getSwingColor() != null) {
            generator.push(descriptor.getSwingColor());
            generator.invokeStatic(ourUIManagerType, ourGetColorMethod);
        } else if (descriptor.getSystemColor() != null) {
            generator.getStatic(ourSystemColorType, descriptor.getSystemColor(), ourSystemColorType);
        } else if (descriptor.getAWTColor() != null) {
            generator.getStatic(ourColorType, descriptor.getAWTColor(), ourColorType);
        } else if (descriptor.isColorSet()) {
            throw new IllegalStateException("Unknown color type");
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

