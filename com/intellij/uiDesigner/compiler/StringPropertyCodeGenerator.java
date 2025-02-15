/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.objectweb.asm.Label
 *  org.objectweb.asm.MethodVisitor
 *  org.objectweb.asm.Opcodes
 *  org.objectweb.asm.Type
 *  org.objectweb.asm.commons.GeneratorAdapter
 *  org.objectweb.asm.commons.Method
 */
package com.intellij.uiDesigner.compiler;

import com.intellij.uiDesigner.compiler.AsmCodeGenerator;
import com.intellij.uiDesigner.compiler.PropertyCodeGenerator;
import com.intellij.uiDesigner.core.SupportCode;
import com.intellij.uiDesigner.lw.LwComponent;
import com.intellij.uiDesigner.lw.LwIntrospectedProperty;
import com.intellij.uiDesigner.lw.StringDescriptor;
import java.util.HashSet;
import java.util.Set;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class StringPropertyCodeGenerator
extends PropertyCodeGenerator
implements Opcodes {
    private static final Type myResourceBundleType = Type.getType((Class)(class$java$util$ResourceBundle == null ? (class$java$util$ResourceBundle = StringPropertyCodeGenerator.class$("java.util.ResourceBundle")) : class$java$util$ResourceBundle));
    private final Method myGetBundleMethod = Method.getMethod((String)"java.util.ResourceBundle getBundle(java.lang.String)");
    private final Method myGetStringMethod = Method.getMethod((String)"java.lang.String getString(java.lang.String)");
    private static final Method myLoadLabelTextMethod = new Method("$$$loadLabelText$$$", Type.VOID_TYPE, new Type[]{Type.getType((Class)(class$javax$swing$JLabel == null ? (class$javax$swing$JLabel = StringPropertyCodeGenerator.class$("javax.swing.JLabel")) : class$javax$swing$JLabel)), Type.getType((Class)(class$java$lang$String == null ? (class$java$lang$String = StringPropertyCodeGenerator.class$("java.lang.String")) : class$java$lang$String))});
    private static final Method myLoadButtonTextMethod = new Method("$$$loadButtonText$$$", Type.VOID_TYPE, new Type[]{Type.getType((Class)(class$javax$swing$AbstractButton == null ? (class$javax$swing$AbstractButton = StringPropertyCodeGenerator.class$("javax.swing.AbstractButton")) : class$javax$swing$AbstractButton)), Type.getType((Class)(class$java$lang$String == null ? (class$java$lang$String = StringPropertyCodeGenerator.class$("java.lang.String")) : class$java$lang$String))});
    private Set myClassesRequiringLoadLabelText = new HashSet();
    private Set myClassesRequiringLoadButtonText = new HashSet();
    private boolean myHaveSetDisplayedMnemonicIndex = false;
    static /* synthetic */ Class class$java$util$ResourceBundle;
    static /* synthetic */ Class class$javax$swing$JLabel;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$javax$swing$AbstractButton;

    public void generateClassStart(AsmCodeGenerator.FormClassVisitor visitor, String name, ClassLoader loader) {
        this.myClassesRequiringLoadLabelText.remove(name);
        this.myClassesRequiringLoadButtonText.remove(name);
        try {
            Class<?> c = loader.loadClass((class$javax$swing$AbstractButton == null ? (class$javax$swing$AbstractButton = StringPropertyCodeGenerator.class$("javax.swing.AbstractButton")) : class$javax$swing$AbstractButton).getName());
            if (c.getMethod("getDisplayedMnemonicIndex", new Class[0]) != null) {
                this.myHaveSetDisplayedMnemonicIndex = true;
            }
        } catch (Exception e) {
            // empty catch block
        }
    }

    public boolean generateCustomSetValue(LwComponent lwComponent, Class componentClass, LwIntrospectedProperty property, GeneratorAdapter generator, int componentLocal, String formClassName) {
        if ("text".equals(property.getName()) && ((class$javax$swing$AbstractButton == null ? (class$javax$swing$AbstractButton = StringPropertyCodeGenerator.class$("javax.swing.AbstractButton")) : class$javax$swing$AbstractButton).isAssignableFrom(componentClass) || (class$javax$swing$JLabel == null ? (class$javax$swing$JLabel = StringPropertyCodeGenerator.class$("javax.swing.JLabel")) : class$javax$swing$JLabel).isAssignableFrom(componentClass))) {
            StringDescriptor propertyValue = (StringDescriptor)lwComponent.getPropertyValue(property);
            if (propertyValue.getValue() != null) {
                SupportCode.TextWithMnemonic textWithMnemonic = SupportCode.parseText(propertyValue.getValue());
                if (textWithMnemonic.myMnemonicIndex >= 0) {
                    generator.loadLocal(componentLocal);
                    generator.push(textWithMnemonic.myText);
                    generator.invokeVirtual(Type.getType((Class)componentClass), new Method(property.getWriteMethodName(), Type.VOID_TYPE, new Type[]{Type.getType((Class)(class$java$lang$String == null ? (class$java$lang$String = StringPropertyCodeGenerator.class$("java.lang.String")) : class$java$lang$String))}));
                    String setMnemonicMethodName = (class$javax$swing$AbstractButton == null ? (class$javax$swing$AbstractButton = StringPropertyCodeGenerator.class$("javax.swing.AbstractButton")) : class$javax$swing$AbstractButton).isAssignableFrom(componentClass) ? "setMnemonic" : "setDisplayedMnemonic";
                    generator.loadLocal(componentLocal);
                    generator.push((int)textWithMnemonic.getMnemonicChar());
                    generator.invokeVirtual(Type.getType((Class)componentClass), new Method(setMnemonicMethodName, Type.VOID_TYPE, new Type[]{Type.CHAR_TYPE}));
                    if (this.myHaveSetDisplayedMnemonicIndex) {
                        generator.loadLocal(componentLocal);
                        generator.push(textWithMnemonic.myMnemonicIndex);
                        generator.invokeVirtual(Type.getType((Class)componentClass), new Method("setDisplayedMnemonicIndex", Type.VOID_TYPE, new Type[]{Type.INT_TYPE}));
                    }
                    return true;
                }
            } else {
                Method method;
                if ((class$javax$swing$AbstractButton == null ? (class$javax$swing$AbstractButton = StringPropertyCodeGenerator.class$("javax.swing.AbstractButton")) : class$javax$swing$AbstractButton).isAssignableFrom(componentClass)) {
                    this.myClassesRequiringLoadButtonText.add(formClassName);
                    method = myLoadButtonTextMethod;
                } else {
                    this.myClassesRequiringLoadLabelText.add(formClassName);
                    method = myLoadLabelTextMethod;
                }
                generator.loadThis();
                generator.loadLocal(componentLocal);
                generator.push(propertyValue.getBundleName());
                generator.invokeStatic(myResourceBundleType, this.myGetBundleMethod);
                generator.push(propertyValue.getKey());
                generator.invokeVirtual(myResourceBundleType, this.myGetStringMethod);
                generator.invokeVirtual(Type.getType((String)("L" + formClassName + ";")), method);
                return true;
            }
        }
        return false;
    }

    public void generatePushValue(GeneratorAdapter generator, Object value) {
        StringDescriptor descriptor = (StringDescriptor)value;
        if (descriptor == null) {
            generator.push((String)null);
        } else if (descriptor.getValue() != null) {
            generator.push(descriptor.getValue());
        } else {
            generator.push(descriptor.getBundleName());
            generator.invokeStatic(myResourceBundleType, this.myGetBundleMethod);
            generator.push(descriptor.getKey());
            generator.invokeVirtual(myResourceBundleType, this.myGetStringMethod);
        }
    }

    public void generateClassEnd(AsmCodeGenerator.FormClassVisitor visitor) {
        if (this.myClassesRequiringLoadLabelText.contains(visitor.getClassName())) {
            this.generateLoadTextMethod(visitor, "$$$loadLabelText$$$", "javax/swing/JLabel", "setDisplayedMnemonic");
            this.myClassesRequiringLoadLabelText.remove(visitor.getClassName());
        }
        if (this.myClassesRequiringLoadButtonText.contains(visitor.getClassName())) {
            this.generateLoadTextMethod(visitor, "$$$loadButtonText$$$", "javax/swing/AbstractButton", "setMnemonic");
            this.myClassesRequiringLoadButtonText.remove(visitor.getClassName());
        }
    }

    private void generateLoadTextMethod(AsmCodeGenerator.FormClassVisitor visitor, String methodName, String componentClass, String setMnemonicMethodName) {
        MethodVisitor mv = visitor.visitNewMethod(4098, methodName, "(L" + componentClass + ";Ljava/lang/String;)V", null, null);
        mv.visitCode();
        mv.visitTypeInsn(187, "java/lang/StringBuffer");
        mv.visitInsn(89);
        mv.visitMethodInsn(183, "java/lang/StringBuffer", "<init>", "()V");
        mv.visitVarInsn(58, 3);
        mv.visitInsn(3);
        mv.visitVarInsn(54, 4);
        mv.visitInsn(3);
        mv.visitVarInsn(54, 5);
        mv.visitInsn(2);
        mv.visitVarInsn(54, 6);
        mv.visitInsn(3);
        mv.visitVarInsn(54, 7);
        Label l0 = new Label();
        mv.visitLabel(l0);
        mv.visitVarInsn(21, 7);
        mv.visitVarInsn(25, 2);
        mv.visitMethodInsn(182, "java/lang/String", "length", "()I");
        Label l1 = new Label();
        mv.visitJumpInsn(162, l1);
        mv.visitVarInsn(25, 2);
        mv.visitVarInsn(21, 7);
        mv.visitMethodInsn(182, "java/lang/String", "charAt", "(I)C");
        mv.visitIntInsn(16, 38);
        Label l2 = new Label();
        mv.visitJumpInsn(160, l2);
        mv.visitIincInsn(7, 1);
        mv.visitVarInsn(21, 7);
        mv.visitVarInsn(25, 2);
        mv.visitMethodInsn(182, "java/lang/String", "length", "()I");
        Label l3 = new Label();
        mv.visitJumpInsn(160, l3);
        mv.visitJumpInsn(167, l1);
        mv.visitLabel(l3);
        mv.visitVarInsn(21, 4);
        mv.visitJumpInsn(154, l2);
        mv.visitVarInsn(25, 2);
        mv.visitVarInsn(21, 7);
        mv.visitMethodInsn(182, "java/lang/String", "charAt", "(I)C");
        mv.visitIntInsn(16, 38);
        mv.visitJumpInsn(159, l2);
        mv.visitInsn(4);
        mv.visitVarInsn(54, 4);
        mv.visitVarInsn(25, 2);
        mv.visitVarInsn(21, 7);
        mv.visitMethodInsn(182, "java/lang/String", "charAt", "(I)C");
        mv.visitVarInsn(54, 5);
        mv.visitVarInsn(25, 3);
        mv.visitMethodInsn(182, "java/lang/StringBuffer", "length", "()I");
        mv.visitVarInsn(54, 6);
        mv.visitLabel(l2);
        mv.visitVarInsn(25, 3);
        mv.visitVarInsn(25, 2);
        mv.visitVarInsn(21, 7);
        mv.visitMethodInsn(182, "java/lang/String", "charAt", "(I)C");
        mv.visitMethodInsn(182, "java/lang/StringBuffer", "append", "(C)Ljava/lang/StringBuffer;");
        mv.visitInsn(87);
        mv.visitIincInsn(7, 1);
        mv.visitJumpInsn(167, l0);
        mv.visitLabel(l1);
        mv.visitVarInsn(25, 1);
        mv.visitVarInsn(25, 3);
        mv.visitMethodInsn(182, "java/lang/StringBuffer", "toString", "()Ljava/lang/String;");
        mv.visitMethodInsn(182, componentClass, "setText", "(Ljava/lang/String;)V");
        mv.visitVarInsn(21, 4);
        Label l4 = new Label();
        mv.visitJumpInsn(153, l4);
        mv.visitVarInsn(25, 1);
        mv.visitVarInsn(21, 5);
        mv.visitMethodInsn(182, componentClass, setMnemonicMethodName, "(C)V");
        if (this.myHaveSetDisplayedMnemonicIndex) {
            mv.visitVarInsn(25, 1);
            mv.visitVarInsn(21, 6);
            mv.visitMethodInsn(182, componentClass, "setDisplayedMnemonicIndex", "(I)V");
        }
        mv.visitLabel(l4);
        mv.visitInsn(177);
        mv.visitMaxs(3, 8);
        mv.visitEnd();
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

