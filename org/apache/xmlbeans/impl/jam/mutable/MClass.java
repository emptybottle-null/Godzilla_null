/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.mutable;

import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JMethod;
import org.apache.xmlbeans.impl.jam.JProperty;
import org.apache.xmlbeans.impl.jam.mutable.MConstructor;
import org.apache.xmlbeans.impl.jam.mutable.MField;
import org.apache.xmlbeans.impl.jam.mutable.MMember;
import org.apache.xmlbeans.impl.jam.mutable.MMethod;

public interface MClass
extends MMember,
JClass {
    public void setIsInterface(boolean var1);

    public void setIsAnnotationType(boolean var1);

    public void setIsEnumType(boolean var1);

    public void setSuperclass(String var1);

    public void setSuperclassUnqualified(String var1);

    public void setSuperclass(JClass var1);

    public void addInterface(String var1);

    public void addInterfaceUnqualified(String var1);

    public void addInterface(JClass var1);

    public void removeInterface(String var1);

    public void removeInterface(JClass var1);

    public MConstructor addNewConstructor();

    public void removeConstructor(MConstructor var1);

    public MConstructor[] getMutableConstructors();

    public MField addNewField();

    public void removeField(MField var1);

    public MField[] getMutableFields();

    public MMethod addNewMethod();

    public void removeMethod(MMethod var1);

    public MMethod[] getMutableMethods();

    public JProperty addNewProperty(String var1, JMethod var2, JMethod var3);

    public void removeProperty(JProperty var1);

    public JProperty addNewDeclaredProperty(String var1, JMethod var2, JMethod var3);

    public void removeDeclaredProperty(JProperty var1);

    public MClass addNewInnerClass(String var1);

    public void removeInnerClass(MClass var1);
}

