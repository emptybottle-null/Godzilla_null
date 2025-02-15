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
import java.awt.Insets;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class InsetsPropertyCodeGenerator
extends PropertyCodeGenerator {
    private final Type myInsetsType = Type.getType((Class)(class$java$awt$Insets == null ? (class$java$awt$Insets = InsetsPropertyCodeGenerator.class$("java.awt.Insets")) : class$java$awt$Insets));
    static /* synthetic */ Class class$java$awt$Insets;

    public void generatePushValue(GeneratorAdapter generator, Object value) {
        Insets insets = (Insets)value;
        generator.newInstance(this.myInsetsType);
        generator.dup();
        generator.push(insets.top);
        generator.push(insets.left);
        generator.push(insets.bottom);
        generator.push(insets.right);
        generator.invokeConstructor(this.myInsetsType, Method.getMethod((String)"void <init>(int,int,int,int)"));
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

