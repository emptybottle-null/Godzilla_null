/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.intellij.uiDesigner.lw;

import com.intellij.uiDesigner.compiler.Utils;
import com.intellij.uiDesigner.lw.LwIntroBooleanProperty;
import com.intellij.uiDesigner.lw.LwIntroCharProperty;
import com.intellij.uiDesigner.lw.LwIntroColorProperty;
import com.intellij.uiDesigner.lw.LwIntroComponentProperty;
import com.intellij.uiDesigner.lw.LwIntroDimensionProperty;
import com.intellij.uiDesigner.lw.LwIntroEnumProperty;
import com.intellij.uiDesigner.lw.LwIntroFontProperty;
import com.intellij.uiDesigner.lw.LwIntroIconProperty;
import com.intellij.uiDesigner.lw.LwIntroInsetsProperty;
import com.intellij.uiDesigner.lw.LwIntroIntProperty;
import com.intellij.uiDesigner.lw.LwIntroListModelProperty;
import com.intellij.uiDesigner.lw.LwIntroPrimitiveTypeProperty;
import com.intellij.uiDesigner.lw.LwIntroRectangleProperty;
import com.intellij.uiDesigner.lw.LwIntrospectedProperty;
import com.intellij.uiDesigner.lw.LwRbIntroStringProperty;
import com.intellij.uiDesigner.lw.PropertiesProvider;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;

public final class CompiledClassPropertiesProvider
implements PropertiesProvider {
    private final ClassLoader myLoader;
    private final HashMap myCache;
    static /* synthetic */ Class class$java$awt$Component;
    static /* synthetic */ Class class$javax$swing$ListModel;
    static /* synthetic */ Class class$java$lang$Double;
    static /* synthetic */ Class class$java$lang$Float;
    static /* synthetic */ Class class$java$lang$Long;
    static /* synthetic */ Class class$java$lang$Byte;
    static /* synthetic */ Class class$java$lang$Short;
    static /* synthetic */ Class class$java$lang$String;

    public CompiledClassPropertiesProvider(ClassLoader loader) {
        if (loader == null) {
            throw new IllegalArgumentException("loader cannot be null");
        }
        this.myLoader = loader;
        this.myCache = new HashMap();
    }

    public HashMap getLwProperties(String className) {
        BeanInfo beanInfo;
        Class<?> aClass;
        if (this.myCache.containsKey(className)) {
            return (HashMap)this.myCache.get(className);
        }
        if (Utils.validateJComponentClass(this.myLoader, className, false) != null) {
            return null;
        }
        try {
            aClass = Class.forName(className, false, this.myLoader);
        } catch (ClassNotFoundException exc) {
            throw new RuntimeException(exc.toString());
        }
        try {
            beanInfo = Introspector.getBeanInfo(aClass);
        } catch (Throwable e) {
            return null;
        }
        HashMap<String, LwIntrospectedProperty> result = new HashMap<String, LwIntrospectedProperty>();
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < descriptors.length; ++i) {
            PropertyDescriptor descriptor = descriptors[i];
            Method readMethod = descriptor.getReadMethod();
            Method writeMethod = descriptor.getWriteMethod();
            if (writeMethod == null || readMethod == null) continue;
            String name = descriptor.getName();
            LwIntrospectedProperty property = CompiledClassPropertiesProvider.propertyFromClass(descriptor.getPropertyType(), name);
            if (property == null) continue;
            property.setDeclaringClassName(descriptor.getReadMethod().getDeclaringClass().getName());
            result.put(name, property);
        }
        this.myCache.put(className, result);
        return result;
    }

    public static LwIntrospectedProperty propertyFromClass(Class propertyType, String name) {
        LwIntrospectedProperty property = CompiledClassPropertiesProvider.propertyFromClassName(propertyType.getName(), name);
        if (property == null) {
            if ((class$java$awt$Component == null ? (class$java$awt$Component = CompiledClassPropertiesProvider.class$("java.awt.Component")) : class$java$awt$Component).isAssignableFrom(propertyType)) {
                property = new LwIntroComponentProperty(name, propertyType.getName());
            } else if ((class$javax$swing$ListModel == null ? (class$javax$swing$ListModel = CompiledClassPropertiesProvider.class$("javax.swing.ListModel")) : class$javax$swing$ListModel).isAssignableFrom(propertyType)) {
                property = new LwIntroListModelProperty(name, propertyType.getName());
            } else if (propertyType.getSuperclass() != null && "java.lang.Enum".equals(propertyType.getSuperclass().getName())) {
                property = new LwIntroEnumProperty(name, propertyType);
            }
        }
        return property;
    }

    public static LwIntrospectedProperty propertyFromClassName(String propertyClassName, String name) {
        LwIntrospectedProperty property = Integer.TYPE.getName().equals(propertyClassName) ? new LwIntroIntProperty(name) : (Boolean.TYPE.getName().equals(propertyClassName) ? new LwIntroBooleanProperty(name) : (Double.TYPE.getName().equals(propertyClassName) ? new LwIntroPrimitiveTypeProperty(name, class$java$lang$Double == null ? (class$java$lang$Double = CompiledClassPropertiesProvider.class$("java.lang.Double")) : class$java$lang$Double) : (Float.TYPE.getName().equals(propertyClassName) ? new LwIntroPrimitiveTypeProperty(name, class$java$lang$Float == null ? (class$java$lang$Float = CompiledClassPropertiesProvider.class$("java.lang.Float")) : class$java$lang$Float) : (Long.TYPE.getName().equals(propertyClassName) ? new LwIntroPrimitiveTypeProperty(name, class$java$lang$Long == null ? (class$java$lang$Long = CompiledClassPropertiesProvider.class$("java.lang.Long")) : class$java$lang$Long) : (Byte.TYPE.getName().equals(propertyClassName) ? new LwIntroPrimitiveTypeProperty(name, class$java$lang$Byte == null ? (class$java$lang$Byte = CompiledClassPropertiesProvider.class$("java.lang.Byte")) : class$java$lang$Byte) : (Short.TYPE.getName().equals(propertyClassName) ? new LwIntroPrimitiveTypeProperty(name, class$java$lang$Short == null ? (class$java$lang$Short = CompiledClassPropertiesProvider.class$("java.lang.Short")) : class$java$lang$Short) : (Character.TYPE.getName().equals(propertyClassName) ? new LwIntroCharProperty(name) : ((class$java$lang$String == null ? (class$java$lang$String = CompiledClassPropertiesProvider.class$("java.lang.String")) : class$java$lang$String).getName().equals(propertyClassName) ? new LwRbIntroStringProperty(name) : ("java.awt.Insets".equals(propertyClassName) ? new LwIntroInsetsProperty(name) : ("java.awt.Dimension".equals(propertyClassName) ? new LwIntroDimensionProperty(name) : ("java.awt.Rectangle".equals(propertyClassName) ? new LwIntroRectangleProperty(name) : ("java.awt.Color".equals(propertyClassName) ? new LwIntroColorProperty(name) : ("java.awt.Font".equals(propertyClassName) ? new LwIntroFontProperty(name) : ("javax.swing.Icon".equals(propertyClassName) ? new LwIntroIconProperty(name) : null))))))))))))));
        return property;
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }
}

