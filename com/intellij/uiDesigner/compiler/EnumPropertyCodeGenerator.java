/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.commons.GeneratorAdapter
 */
package com.intellij.uiDesigner.compiler;

import com.intellij.uiDesigner.compiler.PropertyCodeGenerator;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;

public class EnumPropertyCodeGenerator
extends PropertyCodeGenerator {
    public void generatePushValue(GeneratorAdapter generator, Object value) {
        Type enumType = Type.getType(value.getClass());
        generator.getStatic(enumType, value.toString(), enumType);
    }
}

