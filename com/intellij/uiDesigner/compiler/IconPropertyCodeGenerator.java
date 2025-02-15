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
import com.intellij.uiDesigner.lw.IconDescriptor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

public class IconPropertyCodeGenerator
extends PropertyCodeGenerator {
    private static final Type ourImageIconType = Type.getType((Class)(class$javax$swing$ImageIcon == null ? (class$javax$swing$ImageIcon = IconPropertyCodeGenerator.class$("javax.swing.ImageIcon")) : class$javax$swing$ImageIcon));
    private static final Method ourInitMethod = Method.getMethod((String)"void <init>(java.net.URL)");
    private static final Method ourGetResourceMethod = Method.getMethod((String)"java.net.URL getResource(java.lang.String)");
    private static final Method ourGetClassMethod = new Method("getClass", "()Ljava/lang/Class;");
    private static final Type ourObjectType = Type.getType((Class)(class$java$lang$Object == null ? (class$java$lang$Object = IconPropertyCodeGenerator.class$("java.lang.Object")) : class$java$lang$Object));
    private static final Type ourClassType = Type.getType((Class)(class$java$lang$Class == null ? (class$java$lang$Class = IconPropertyCodeGenerator.class$("java.lang.Class")) : class$java$lang$Class));
    static /* synthetic */ Class class$javax$swing$ImageIcon;
    static /* synthetic */ Class class$java$lang$Object;
    static /* synthetic */ Class class$java$lang$Class;

    public void generatePushValue(GeneratorAdapter generator, Object value) {
        IconDescriptor descriptor = (IconDescriptor)value;
        generator.newInstance(ourImageIconType);
        generator.dup();
        generator.loadThis();
        generator.invokeVirtual(ourObjectType, ourGetClassMethod);
        generator.push("/" + descriptor.getIconPath());
        generator.invokeVirtual(ourClassType, ourGetResourceMethod);
        generator.invokeConstructor(ourImageIconType, ourInitMethod);
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

