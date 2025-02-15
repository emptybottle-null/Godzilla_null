/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.commons.GeneratorAdapter
 */
package com.intellij.uiDesigner.compiler;

import com.intellij.uiDesigner.compiler.AsmCodeGenerator;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwIntrospectedProperty;
import org.objectweb.asm.commons.GeneratorAdapter;

public abstract class PropertyCodeGenerator {
    public abstract void generatePushValue(GeneratorAdapter var1, Object var2);

    public boolean generateCustomSetValue(LwComponent lwComponent, Class componentClass, LwIntrospectedProperty property, GeneratorAdapter generator, int componentLocal, String formClassName) {
        return false;
    }

    public void generateClassStart(AsmCodeGenerator.FormClassVisitor visitor, String name, ClassLoader loader) {
    }

    public void generateClassEnd(AsmCodeGenerator.FormClassVisitor visitor) {
    }
}

