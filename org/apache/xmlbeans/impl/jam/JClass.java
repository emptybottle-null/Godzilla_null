/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam;

import org.apache.xmlbeans.impl.jam.JConstructor;
import org.apache.xmlbeans.impl.jam.JField;
import org.apache.xmlbeans.impl.jam.JMember;
import org.apache.xmlbeans.impl.jam.JMethod;
import org.apache.xmlbeans.impl.jam.JPackage;
import org.apache.xmlbeans.impl.jam.JProperty;
import org.apache.xmlbeans.impl.jam.JamClassLoader;

public interface JClass
extends JMember {
    public JPackage getContainingPackage();

    public JClass getSuperclass();

    public JClass[] getInterfaces();

    public JField[] getFields();

    public JField[] getDeclaredFields();

    public JMethod[] getMethods();

    public JMethod[] getDeclaredMethods();

    public JConstructor[] getConstructors();

    public JProperty[] getProperties();

    public JProperty[] getDeclaredProperties();

    public boolean isInterface();

    public boolean isAnnotationType();

    public boolean isPrimitiveType();

    public boolean isBuiltinType();

    public Class getPrimitiveClass();

    public boolean isFinal();

    public boolean isStatic();

    public boolean isAbstract();

    public boolean isVoidType();

    public boolean isObjectType();

    public boolean isArrayType();

    public JClass getArrayComponentType();

    public int getArrayDimensions();

    public boolean isAssignableFrom(JClass var1);

    public boolean equals(Object var1);

    public JClass[] getClasses();

    @Override
    public JClass getContainingClass();

    public String getFieldDescriptor();

    public boolean isEnumType();

    public JamClassLoader getClassLoader();

    public JClass forName(String var1);

    public JClass[] getImportedClasses();

    public JPackage[] getImportedPackages();

    public boolean isUnresolvedType();
}

