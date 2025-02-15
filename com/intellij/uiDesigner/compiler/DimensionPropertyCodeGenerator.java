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
import java.awt.Dimension;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class DimensionPropertyCodeGenerator
extends PropertyCodeGenerator {
    private static final Type myDimensionType = Type.getType((Class)(class$java$awt$Dimension == null ? (class$java$awt$Dimension = DimensionPropertyCodeGenerator.class$("java.awt.Dimension")) : class$java$awt$Dimension));
    private static final Method myInitMethod = Method.getMethod((String)"void <init>(int,int)");
    static /* synthetic */ Class class$java$awt$Dimension;

    public void generatePushValue(GeneratorAdapter generator, Object value) {
        Dimension dimension = (Dimension)value;
        generator.newInstance(myDimensionType);
        generator.dup();
        generator.push(dimension.width);
        generator.push(dimension.height);
        generator.invokeConstructor(myDimensionType, myInitMethod);
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

