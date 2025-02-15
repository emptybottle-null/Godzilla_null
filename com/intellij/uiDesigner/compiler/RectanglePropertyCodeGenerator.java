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
import java.awt.Rectangle;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class RectanglePropertyCodeGenerator
extends PropertyCodeGenerator {
    private static Type myRectangleType = Type.getType((Class)(class$java$awt$Rectangle == null ? (class$java$awt$Rectangle = RectanglePropertyCodeGenerator.class$("java.awt.Rectangle")) : class$java$awt$Rectangle));
    private static Method myInitMethod = Method.getMethod((String)"void <init>(int,int,int,int)");
    static /* synthetic */ Class class$java$awt$Rectangle;

    public void generatePushValue(GeneratorAdapter generator, Object value) {
        Rectangle rc = (Rectangle)value;
        generator.newInstance(myRectangleType);
        generator.dup();
        generator.push(rc.x);
        generator.push(rc.y);
        generator.push(rc.width);
        generator.push(rc.height);
        generator.invokeConstructor(myRectangleType, myInitMethod);
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

