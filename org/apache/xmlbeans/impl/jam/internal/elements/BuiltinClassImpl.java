/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JConstructor;
import org.apache.xmlbeans.impl.jam.JField;
import org.apache.xmlbeans.impl.jam.JMethod;
import org.apache.xmlbeans.impl.jam.JPackage;
import org.apache.xmlbeans.impl.jam.JProperty;
import org.apache.xmlbeans.impl.jam.JSourcePosition;
import org.apache.xmlbeans.impl.jam.internal.elements.AnnotatedElementImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.mutable.MConstructor;
import org.apache.xmlbeans.impl.jam.mutable.MField;
import org.apache.xmlbeans.impl.jam.mutable.MMethod;
import org.apache.xmlbeans.impl.jam.visitor.JVisitor;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;

public abstract class BuiltinClassImpl
extends AnnotatedElementImpl
implements MClass {
    protected BuiltinClassImpl(ElementContext ctx) {
        super(ctx);
    }

    @Override
    public void accept(MVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(JVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getQualifiedName() {
        return this.mSimpleName;
    }

    @Override
    public String getFieldDescriptor() {
        return this.mSimpleName;
    }

    @Override
    public int getModifiers() {
        return Object.class.getModifiers();
    }

    @Override
    public boolean isPublic() {
        return true;
    }

    @Override
    public boolean isPackagePrivate() {
        return false;
    }

    @Override
    public boolean isProtected() {
        return false;
    }

    @Override
    public boolean isPrivate() {
        return false;
    }

    @Override
    public JSourcePosition getSourcePosition() {
        return null;
    }

    @Override
    public JClass getContainingClass() {
        return null;
    }

    @Override
    public JClass forName(String fd) {
        return this.getClassLoader().loadClass(fd);
    }

    @Override
    public JClass getArrayComponentType() {
        return null;
    }

    @Override
    public int getArrayDimensions() {
        return 0;
    }

    @Override
    public JClass getSuperclass() {
        return null;
    }

    @Override
    public JClass[] getInterfaces() {
        return NO_CLASS;
    }

    @Override
    public JField[] getFields() {
        return NO_FIELD;
    }

    @Override
    public JField[] getDeclaredFields() {
        return NO_FIELD;
    }

    @Override
    public JConstructor[] getConstructors() {
        return NO_CONSTRUCTOR;
    }

    @Override
    public JMethod[] getMethods() {
        return NO_METHOD;
    }

    @Override
    public JMethod[] getDeclaredMethods() {
        return NO_METHOD;
    }

    @Override
    public JPackage getContainingPackage() {
        return null;
    }

    @Override
    public boolean isInterface() {
        return false;
    }

    @Override
    public boolean isArrayType() {
        return false;
    }

    @Override
    public boolean isAnnotationType() {
        return false;
    }

    @Override
    public boolean isPrimitiveType() {
        return false;
    }

    @Override
    public boolean isBuiltinType() {
        return true;
    }

    @Override
    public boolean isUnresolvedType() {
        return false;
    }

    @Override
    public boolean isObjectType() {
        return false;
    }

    @Override
    public boolean isVoidType() {
        return false;
    }

    @Override
    public boolean isEnumType() {
        return false;
    }

    @Override
    public Class getPrimitiveClass() {
        return null;
    }

    @Override
    public boolean isAbstract() {
        return false;
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    @Override
    public boolean isStatic() {
        return false;
    }

    @Override
    public JClass[] getClasses() {
        return NO_CLASS;
    }

    @Override
    public JProperty[] getProperties() {
        return NO_PROPERTY;
    }

    @Override
    public JProperty[] getDeclaredProperties() {
        return NO_PROPERTY;
    }

    @Override
    public JPackage[] getImportedPackages() {
        return NO_PACKAGE;
    }

    @Override
    public JClass[] getImportedClasses() {
        return NO_CLASS;
    }

    @Override
    public MField[] getMutableFields() {
        return NO_FIELD;
    }

    @Override
    public MConstructor[] getMutableConstructors() {
        return NO_CONSTRUCTOR;
    }

    @Override
    public MMethod[] getMutableMethods() {
        return NO_METHOD;
    }

    @Override
    public void setSimpleName(String s) {
        this.nocando();
    }

    @Override
    public void setIsAnnotationType(boolean b) {
        this.nocando();
    }

    @Override
    public void setIsInterface(boolean b) {
        this.nocando();
    }

    public void setIsUnresolvedType(boolean b) {
        this.nocando();
    }

    @Override
    public void setIsEnumType(boolean b) {
        this.nocando();
    }

    @Override
    public void setSuperclass(String qualifiedClassName) {
        this.nocando();
    }

    @Override
    public void setSuperclassUnqualified(String unqualifiedClassName) {
        this.nocando();
    }

    @Override
    public void setSuperclass(JClass clazz) {
        this.nocando();
    }

    @Override
    public void addInterface(String className) {
        this.nocando();
    }

    @Override
    public void addInterfaceUnqualified(String unqualifiedClassName) {
        this.nocando();
    }

    @Override
    public void addInterface(JClass interf) {
        this.nocando();
    }

    @Override
    public void removeInterface(String className) {
        this.nocando();
    }

    @Override
    public void removeInterface(JClass interf) {
        this.nocando();
    }

    @Override
    public MConstructor addNewConstructor() {
        this.nocando();
        return null;
    }

    @Override
    public void removeConstructor(MConstructor constr) {
        this.nocando();
    }

    @Override
    public MField addNewField() {
        this.nocando();
        return null;
    }

    @Override
    public void removeField(MField field) {
        this.nocando();
    }

    @Override
    public MMethod addNewMethod() {
        this.nocando();
        return null;
    }

    @Override
    public void removeMethod(MMethod method) {
        this.nocando();
    }

    @Override
    public void setModifiers(int modifiers) {
        this.nocando();
    }

    @Override
    public MClass addNewInnerClass(String named) {
        this.nocando();
        return null;
    }

    @Override
    public void removeInnerClass(MClass inner) {
        this.nocando();
    }

    @Override
    public JProperty addNewProperty(String name, JMethod m, JMethod x) {
        this.nocando();
        return null;
    }

    @Override
    public void removeProperty(JProperty prop) {
        this.nocando();
    }

    @Override
    public JProperty addNewDeclaredProperty(String name, JMethod m, JMethod x) {
        this.nocando();
        return null;
    }

    @Override
    public void removeDeclaredProperty(JProperty prop) {
        this.nocando();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof JClass) {
            return ((JClass)o).getFieldDescriptor().equals(this.getFieldDescriptor());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getFieldDescriptor().hashCode();
    }

    protected void reallySetSimpleName(String name) {
        super.setSimpleName(name);
    }

    private void nocando() {
        throw new UnsupportedOperationException("Cannot alter builtin types");
    }
}

