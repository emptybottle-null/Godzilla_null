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
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class ListModelPropertyCodeGenerator
extends PropertyCodeGenerator {
    private final Type myListModelType;
    private static final Method ourInitMethod = Method.getMethod((String)"void <init>()");
    private static final Method ourAddElementMethod = Method.getMethod((String)"void addElement(java.lang.Object)");

    public ListModelPropertyCodeGenerator(Class aClass) {
        this.myListModelType = Type.getType((Class)aClass);
    }

    public void generatePushValue(GeneratorAdapter generator, Object value) {
        String[] items = (String[])value;
        int listModelLocal = generator.newLocal(this.myListModelType);
        generator.newInstance(this.myListModelType);
        generator.dup();
        generator.invokeConstructor(this.myListModelType, ourInitMethod);
        generator.storeLocal(listModelLocal);
        for (int i = 0; i < items.length; ++i) {
            generator.loadLocal(listModelLocal);
            generator.push(items[i]);
            generator.invokeVirtual(this.myListModelType, ourAddElementMethod);
        }
        generator.loadLocal(listModelLocal);
    }
}

