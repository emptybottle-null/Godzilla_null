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
import com.intellij.uiDesigner.compiler.PropertyCodeGenerator;
import com.intellij.uiDesigner.lw.FontDescriptor;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwIntrospectedProperty;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class FontPropertyCodeGenerator
extends PropertyCodeGenerator {
    private static final Type ourFontType = Type.getType((Class)(class$java$awt$Font == null ? (class$java$awt$Font = FontPropertyCodeGenerator.class$("java.awt.Font")) : class$java$awt$Font));
    private static final Type ourUIManagerType = Type.getType((String)"Ljavax/swing/UIManager;");
    private static final Type ourObjectType = Type.getType((Class)(class$java$lang$Object == null ? (class$java$lang$Object = FontPropertyCodeGenerator.class$("java.lang.Object")) : class$java$lang$Object));
    private static final Type ourStringType = Type.getType((Class)(class$java$lang$String == null ? (class$java$lang$String = FontPropertyCodeGenerator.class$("java.lang.String")) : class$java$lang$String));
    private static final Method ourInitMethod = Method.getMethod((String)"void <init>(java.lang.String,int,int)");
    private static final Method ourUIManagerGetFontMethod = new Method("getFont", ourFontType, new Type[]{ourObjectType});
    private static final Method ourGetNameMethod = new Method("getName", ourStringType, new Type[0]);
    private static final Method ourGetSizeMethod = new Method("getSize", Type.INT_TYPE, new Type[0]);
    private static final Method ourGetStyleMethod = new Method("getStyle", Type.INT_TYPE, new Type[0]);
    static /* synthetic */ Class class$java$awt$Font;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$String;

    public boolean generateCustomSetValue(LwComponent lwComponent, Class componentClass, LwIntrospectedProperty property, GeneratorAdapter generator, int componentLocal, String formClassName) {
        FontDescriptor descriptor = (FontDescriptor)property.getPropertyValue(lwComponent);
        if (descriptor.isFixedFont() && !descriptor.isFullyDefinedFont()) {
            generator.loadLocal(componentLocal);
            FontPropertyCodeGenerator.generatePushFont(generator, componentLocal, lwComponent, descriptor, property.getReadMethodName());
            Method setFontMethod = new Method(property.getWriteMethodName(), Type.VOID_TYPE, new Type[]{ourFontType});
            Type componentType = AsmCodeGenerator.typeFromClassName(lwComponent.getComponentClassName());
            generator.invokeVirtual(componentType, setFontMethod);
            return true;
        }
        return false;
    }

    public static void generatePushFont(GeneratorAdapter generator, int componentLocal, LwComponent lwComponent, FontDescriptor descriptor, String readMethodName) {
        int fontLocal = generator.newLocal(ourFontType);
        generator.loadLocal(componentLocal);
        Type componentType = AsmCodeGenerator.typeFromClassName(lwComponent.getComponentClassName());
        Method getFontMethod = new Method(readMethodName, ourFontType, new Type[0]);
        generator.invokeVirtual(componentType, getFontMethod);
        generator.storeLocal(fontLocal);
        generator.newInstance(ourFontType);
        generator.dup();
        if (descriptor.getFontName() != null) {
            generator.push(descriptor.getFontName());
        } else {
            generator.loadLocal(fontLocal);
            generator.invokeVirtual(ourFontType, ourGetNameMethod);
        }
        if (descriptor.getFontStyle() >= 0) {
            generator.push(descriptor.getFontStyle());
        } else {
            generator.loadLocal(fontLocal);
            generator.invokeVirtual(ourFontType, ourGetStyleMethod);
        }
        if (descriptor.getFontSize() >= 0) {
            generator.push(descriptor.getFontSize());
        } else {
            generator.loadLocal(fontLocal);
            generator.invokeVirtual(ourFontType, ourGetSizeMethod);
        }
        generator.invokeConstructor(ourFontType, ourInitMethod);
    }

    public void generatePushValue(GeneratorAdapter generator, Object value) {
        FontDescriptor descriptor = (FontDescriptor)value;
        if (descriptor.isFixedFont()) {
            if (!descriptor.isFullyDefinedFont()) {
                throw new IllegalStateException("Unexpected font state");
            }
            generator.newInstance(ourFontType);
            generator.dup();
            generator.push(descriptor.getFontName());
            generator.push(descriptor.getFontStyle());
            generator.push(descriptor.getFontSize());
            generator.invokeConstructor(ourFontType, ourInitMethod);
        } else if (descriptor.getSwingFont() != null) {
            generator.push(descriptor.getSwingFont());
            generator.invokeStatic(ourUIManagerType, ourUIManagerGetFontMethod);
        } else {
            throw new IllegalStateException("Unknown font type");
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

