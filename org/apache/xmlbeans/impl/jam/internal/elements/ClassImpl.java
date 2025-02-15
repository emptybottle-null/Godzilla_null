/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.jam.internal.elements;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import org.apache.xmlbeans.impl.jam.JAnnotation;
import org.apache.xmlbeans.impl.jam.JAnnotationValue;
import org.apache.xmlbeans.impl.jam.JClass;
import org.apache.xmlbeans.impl.jam.JComment;
import org.apache.xmlbeans.impl.jam.JConstructor;
import org.apache.xmlbeans.impl.jam.JField;
import org.apache.xmlbeans.impl.jam.JMethod;
import org.apache.xmlbeans.impl.jam.JPackage;
import org.apache.xmlbeans.impl.jam.JProperty;
import org.apache.xmlbeans.impl.jam.JSourcePosition;
import org.apache.xmlbeans.impl.jam.internal.JamClassLoaderImpl;
import org.apache.xmlbeans.impl.jam.internal.classrefs.JClassRef;
import org.apache.xmlbeans.impl.jam.internal.classrefs.JClassRefContext;
import org.apache.xmlbeans.impl.jam.internal.classrefs.QualifiedJClassRef;
import org.apache.xmlbeans.impl.jam.internal.classrefs.UnqualifiedJClassRef;
import org.apache.xmlbeans.impl.jam.internal.elements.ConstructorImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.ElementContext;
import org.apache.xmlbeans.impl.jam.internal.elements.FieldImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.MemberImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.MethodImpl;
import org.apache.xmlbeans.impl.jam.internal.elements.PropertyImpl;
import org.apache.xmlbeans.impl.jam.mutable.MClass;
import org.apache.xmlbeans.impl.jam.mutable.MConstructor;
import org.apache.xmlbeans.impl.jam.mutable.MField;
import org.apache.xmlbeans.impl.jam.mutable.MMethod;
import org.apache.xmlbeans.impl.jam.provider.JamClassPopulator;
import org.apache.xmlbeans.impl.jam.visitor.JVisitor;
import org.apache.xmlbeans.impl.jam.visitor.MVisitor;

public class ClassImpl
extends MemberImpl
implements MClass,
JClassRef,
JClassRefContext {
    public static final int NEW = 1;
    public static final int UNPOPULATED = 2;
    public static final int POPULATING = 3;
    public static final int UNINITIALIZED = 4;
    public static final int INITIALIZING = 5;
    public static final int LOADED = 6;
    private int mState = 1;
    private boolean mIsAnnotationType = false;
    private boolean mIsInterface = false;
    private boolean mIsEnum = false;
    private String mPackageName = null;
    private JClassRef mSuperClassRef = null;
    private ArrayList mInterfaceRefs = null;
    private ArrayList mFields = null;
    private ArrayList mMethods = null;
    private ArrayList mConstructors = null;
    private ArrayList mProperties = null;
    private ArrayList mDeclaredProperties = null;
    private ArrayList mInnerClasses = null;
    private String[] mImports = null;
    private JamClassPopulator mPopulator;

    public ClassImpl(String packageName, String simpleName, ElementContext ctx, String[] importSpecs, JamClassPopulator populator) {
        super(ctx);
        super.setSimpleName(simpleName);
        this.mPackageName = packageName.trim();
        this.mImports = importSpecs;
        this.mPopulator = populator;
        this.setState(2);
    }

    public ClassImpl(String packageName, String simpleName, ElementContext ctx, String[] importSpecs) {
        super(ctx);
        super.setSimpleName(simpleName);
        this.mPackageName = packageName.trim();
        this.mImports = importSpecs;
        this.mPopulator = null;
        this.setState(4);
    }

    private ClassImpl(String packageName, String simpleName, String[] importSpecs, ClassImpl parent) {
        super(parent);
        super.setSimpleName(simpleName);
        this.mPackageName = packageName.trim();
        this.mImports = importSpecs;
        this.mPopulator = null;
        this.setState(4);
    }

    @Override
    public JPackage getContainingPackage() {
        return this.getClassLoader().getPackage(this.mPackageName);
    }

    @Override
    public JClass getSuperclass() {
        this.ensureLoaded();
        if (this.mSuperClassRef == null) {
            return null;
        }
        return this.mSuperClassRef.getRefClass();
    }

    @Override
    public JClass[] getInterfaces() {
        this.ensureLoaded();
        if (this.mInterfaceRefs == null || this.mInterfaceRefs.size() == 0) {
            return new JClass[0];
        }
        JClass[] out = new JClass[this.mInterfaceRefs.size()];
        for (int i = 0; i < out.length; ++i) {
            out[i] = ((JClassRef)this.mInterfaceRefs.get(i)).getRefClass();
        }
        return out;
    }

    @Override
    public JField[] getFields() {
        this.ensureLoaded();
        ArrayList list = new ArrayList();
        this.addFieldsRecursively(this, list);
        JField[] out = new JField[list.size()];
        list.toArray(out);
        return out;
    }

    @Override
    public JField[] getDeclaredFields() {
        this.ensureLoaded();
        return this.getMutableFields();
    }

    @Override
    public JMethod[] getMethods() {
        this.ensureLoaded();
        ArrayList list = new ArrayList();
        this.addMethodsRecursively(this, list);
        JMethod[] out = new JMethod[list.size()];
        list.toArray(out);
        return out;
    }

    @Override
    public JProperty[] getProperties() {
        this.ensureLoaded();
        if (this.mProperties == null) {
            return new JProperty[0];
        }
        JProperty[] out = new JProperty[this.mProperties.size()];
        this.mProperties.toArray(out);
        return out;
    }

    @Override
    public JProperty[] getDeclaredProperties() {
        this.ensureLoaded();
        if (this.mDeclaredProperties == null) {
            return new JProperty[0];
        }
        JProperty[] out = new JProperty[this.mDeclaredProperties.size()];
        this.mDeclaredProperties.toArray(out);
        return out;
    }

    @Override
    public JMethod[] getDeclaredMethods() {
        this.ensureLoaded();
        return this.getMutableMethods();
    }

    @Override
    public JConstructor[] getConstructors() {
        this.ensureLoaded();
        return this.getMutableConstructors();
    }

    @Override
    public boolean isInterface() {
        this.ensureLoaded();
        return this.mIsInterface;
    }

    @Override
    public boolean isAnnotationType() {
        this.ensureLoaded();
        return this.mIsAnnotationType;
    }

    @Override
    public boolean isEnumType() {
        this.ensureLoaded();
        return this.mIsEnum;
    }

    @Override
    public int getModifiers() {
        this.ensureLoaded();
        return super.getModifiers();
    }

    @Override
    public boolean isFinal() {
        return Modifier.isFinal(this.getModifiers());
    }

    @Override
    public boolean isStatic() {
        return Modifier.isStatic(this.getModifiers());
    }

    @Override
    public boolean isAbstract() {
        return Modifier.isAbstract(this.getModifiers());
    }

    @Override
    public boolean isAssignableFrom(JClass arg) {
        this.ensureLoaded();
        if (this.isPrimitiveType() || arg.isPrimitiveType()) {
            return this.getQualifiedName().equals(arg.getQualifiedName());
        }
        return this.isAssignableFromRecursively(arg);
    }

    @Override
    public JClass[] getClasses() {
        this.ensureLoaded();
        if (this.mInnerClasses == null) {
            return new JClass[0];
        }
        JClass[] out = new JClass[this.mInnerClasses.size()];
        this.mInnerClasses.toArray(out);
        return out;
    }

    @Override
    public String getFieldDescriptor() {
        return this.getQualifiedName();
    }

    @Override
    public JClass forName(String name) {
        return this.getClassLoader().loadClass(name);
    }

    @Override
    public JPackage[] getImportedPackages() {
        this.ensureLoaded();
        TreeSet<JPackage> set = new TreeSet<JPackage>();
        JClass[] importedClasses = this.getImportedClasses();
        for (int i = 0; i < importedClasses.length; ++i) {
            JPackage c = importedClasses[i].getContainingPackage();
            if (c == null) continue;
            set.add(c);
        }
        String[] imports = this.getImportSpecs();
        if (imports != null) {
            for (int i = 0; i < imports.length; ++i) {
                if (!imports[i].endsWith(".*")) continue;
                set.add(this.getClassLoader().getPackage(imports[i].substring(0, imports[i].length() - 2)));
            }
        }
        JPackage[] array = new JPackage[set.size()];
        set.toArray(array);
        return array;
    }

    @Override
    public JClass[] getImportedClasses() {
        this.ensureLoaded();
        String[] imports = this.getImportSpecs();
        if (imports == null) {
            return new JClass[0];
        }
        ArrayList<JClass> list = new ArrayList<JClass>();
        for (int i = 0; i < imports.length; ++i) {
            if (imports[i].endsWith("*")) continue;
            list.add(this.getClassLoader().loadClass(imports[i]));
        }
        JClass[] out = new JClass[list.size()];
        list.toArray(out);
        return out;
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
    public void setSimpleName(String name) {
        throw new UnsupportedOperationException("Class names cannot be changed");
    }

    @Override
    public Class getPrimitiveClass() {
        return null;
    }

    @Override
    public boolean isPrimitiveType() {
        return false;
    }

    @Override
    public boolean isBuiltinType() {
        return false;
    }

    @Override
    public boolean isVoidType() {
        return false;
    }

    @Override
    public boolean isUnresolvedType() {
        return false;
    }

    @Override
    public boolean isObjectType() {
        return this.getQualifiedName().equals("java.lang.Object");
    }

    @Override
    public boolean isArrayType() {
        return false;
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
    public JAnnotation[] getAnnotations() {
        this.ensureLoaded();
        return super.getAnnotations();
    }

    @Override
    public JAnnotation getAnnotation(Class proxyClass) {
        this.ensureLoaded();
        return super.getAnnotation(proxyClass);
    }

    @Override
    public JAnnotation getAnnotation(String named) {
        this.ensureLoaded();
        return super.getAnnotation(named);
    }

    @Override
    public JAnnotationValue getAnnotationValue(String valueId) {
        this.ensureLoaded();
        return super.getAnnotationValue(valueId);
    }

    @Override
    public Object getAnnotationProxy(Class proxyClass) {
        this.ensureLoaded();
        return super.getAnnotationProxy(proxyClass);
    }

    @Override
    public JComment getComment() {
        this.ensureLoaded();
        return super.getComment();
    }

    @Override
    public JAnnotation[] getAllJavadocTags() {
        this.ensureLoaded();
        return super.getAllJavadocTags();
    }

    @Override
    public JSourcePosition getSourcePosition() {
        this.ensureLoaded();
        return super.getSourcePosition();
    }

    @Override
    public void setSuperclass(String qualifiedClassName) {
        if (qualifiedClassName == null) {
            this.mSuperClassRef = null;
        } else {
            if (qualifiedClassName.equals(this.getQualifiedName())) {
                throw new IllegalArgumentException("A class cannot be it's own superclass: '" + qualifiedClassName + "'");
            }
            this.mSuperClassRef = QualifiedJClassRef.create(qualifiedClassName, this);
        }
    }

    @Override
    public void setSuperclassUnqualified(String unqualifiedClassName) {
        this.mSuperClassRef = UnqualifiedJClassRef.create(unqualifiedClassName, this);
    }

    @Override
    public void setSuperclass(JClass clazz) {
        if (clazz == null) {
            this.mSuperClassRef = null;
        } else {
            this.setSuperclass(clazz.getQualifiedName());
        }
    }

    @Override
    public void addInterface(JClass interf) {
        if (interf == null) {
            throw new IllegalArgumentException("null interf");
        }
        this.addInterface(interf.getQualifiedName());
    }

    @Override
    public void addInterface(String qcName) {
        if (this.mInterfaceRefs == null) {
            this.mInterfaceRefs = new ArrayList();
        }
        if (qcName.equals(this.getQualifiedName())) {
            throw new IllegalArgumentException("A class cannot implement itself: '" + qcName + "'");
        }
        this.mInterfaceRefs.add(QualifiedJClassRef.create(qcName, this));
    }

    @Override
    public void addInterfaceUnqualified(String ucname) {
        if (this.mInterfaceRefs == null) {
            this.mInterfaceRefs = new ArrayList();
        }
        this.mInterfaceRefs.add(UnqualifiedJClassRef.create(ucname, this));
    }

    @Override
    public void removeInterface(JClass interf) {
        if (interf == null) {
            throw new IllegalArgumentException("null interf");
        }
        this.removeInterface(interf.getQualifiedName());
    }

    @Override
    public void removeInterface(String qcname) {
        if (qcname == null) {
            throw new IllegalArgumentException("null classname");
        }
        if (this.mInterfaceRefs == null) {
            return;
        }
        for (int i = 0; i < this.mInterfaceRefs.size(); ++i) {
            if (!qcname.equals(((JClassRef)this.mInterfaceRefs.get(i)).getQualifiedName())) continue;
            this.mInterfaceRefs.remove(i);
        }
    }

    @Override
    public MConstructor addNewConstructor() {
        if (this.mConstructors == null) {
            this.mConstructors = new ArrayList();
        }
        ConstructorImpl out = new ConstructorImpl(this);
        this.mConstructors.add(out);
        return out;
    }

    @Override
    public void removeConstructor(MConstructor constr) {
        if (this.mConstructors == null) {
            return;
        }
        this.mConstructors.remove(constr);
    }

    @Override
    public MConstructor[] getMutableConstructors() {
        if (this.mConstructors == null || this.mConstructors.size() == 0) {
            return new MConstructor[0];
        }
        MConstructor[] out = new MConstructor[this.mConstructors.size()];
        this.mConstructors.toArray(out);
        return out;
    }

    @Override
    public MField addNewField() {
        if (this.mFields == null) {
            this.mFields = new ArrayList();
        }
        FieldImpl out = new FieldImpl(ClassImpl.defaultName(this.mFields.size()), this, "java.lang.Object");
        this.mFields.add(out);
        return out;
    }

    @Override
    public void removeField(MField field) {
        if (this.mFields == null) {
            return;
        }
        this.mFields.remove(field);
    }

    @Override
    public MField[] getMutableFields() {
        if (this.mFields == null || this.mFields.size() == 0) {
            return new MField[0];
        }
        MField[] out = new MField[this.mFields.size()];
        this.mFields.toArray(out);
        return out;
    }

    @Override
    public MMethod addNewMethod() {
        if (this.mMethods == null) {
            this.mMethods = new ArrayList();
        }
        MethodImpl out = new MethodImpl(ClassImpl.defaultName(this.mMethods.size()), this);
        this.mMethods.add(out);
        return out;
    }

    @Override
    public void removeMethod(MMethod method) {
        if (this.mMethods == null) {
            return;
        }
        this.mMethods.remove(method);
    }

    @Override
    public MMethod[] getMutableMethods() {
        if (this.mMethods == null || this.mMethods.size() == 0) {
            return new MMethod[0];
        }
        MMethod[] out = new MMethod[this.mMethods.size()];
        this.mMethods.toArray(out);
        return out;
    }

    @Override
    public JProperty addNewProperty(String name, JMethod getter, JMethod setter) {
        if (this.mProperties == null) {
            this.mProperties = new ArrayList();
        }
        String typeName = getter != null ? getter.getReturnType().getFieldDescriptor() : setter.getParameters()[0].getType().getFieldDescriptor();
        PropertyImpl out = new PropertyImpl(name, getter, setter, typeName);
        this.mProperties.add(out);
        return out;
    }

    @Override
    public void removeProperty(JProperty p) {
        if (this.mProperties != null) {
            this.mProperties.remove(p);
        }
    }

    @Override
    public JProperty addNewDeclaredProperty(String name, JMethod getter, JMethod setter) {
        if (this.mDeclaredProperties == null) {
            this.mDeclaredProperties = new ArrayList();
        }
        String typeName = getter != null ? getter.getReturnType().getFieldDescriptor() : setter.getParameters()[0].getType().getFieldDescriptor();
        PropertyImpl out = new PropertyImpl(name, getter, setter, typeName);
        this.mDeclaredProperties.add(out);
        return out;
    }

    @Override
    public void removeDeclaredProperty(JProperty p) {
        if (this.mDeclaredProperties != null) {
            this.mDeclaredProperties.remove(p);
        }
    }

    @Override
    public MClass addNewInnerClass(String name) {
        int lastDot = name.lastIndexOf(46);
        if (lastDot == -1) {
            lastDot = name.lastIndexOf(36);
        }
        if (lastDot != -1) {
            name = name.substring(lastDot + 1);
        }
        ClassImpl inner = new ClassImpl(this.mPackageName, this.getSimpleName() + "$" + name, this.getImportSpecs(), this);
        if (this.mInnerClasses == null) {
            this.mInnerClasses = new ArrayList();
        }
        this.mInnerClasses.add(inner);
        inner.setState(6);
        ((JamClassLoaderImpl)this.getClassLoader()).addToCache(inner);
        return inner;
    }

    @Override
    public void removeInnerClass(MClass clazz) {
        if (this.mInnerClasses == null) {
            return;
        }
        this.mInnerClasses.remove(clazz);
    }

    @Override
    public void setIsInterface(boolean b) {
        this.mIsInterface = b;
    }

    @Override
    public void setIsAnnotationType(boolean b) {
        this.mIsAnnotationType = b;
    }

    @Override
    public void setIsEnumType(boolean b) {
        this.mIsEnum = b;
    }

    @Override
    public String getQualifiedName() {
        return (this.mPackageName.length() > 0 ? this.mPackageName + '.' : "") + this.mSimpleName;
    }

    @Override
    public JClass getRefClass() {
        return this;
    }

    @Override
    public String getPackageName() {
        return this.mPackageName;
    }

    @Override
    public String[] getImportSpecs() {
        this.ensureLoaded();
        if (this.mImports == null) {
            return new String[0];
        }
        return this.mImports;
    }

    public void setState(int state) {
        this.mState = state;
    }

    public static void validateClassName(String className) throws IllegalArgumentException {
        if (className == null) {
            throw new IllegalArgumentException("null class name specified");
        }
        if (!Character.isJavaIdentifierStart(className.charAt(0))) {
            throw new IllegalArgumentException("Invalid first character in class name: " + className);
        }
        for (int i = 1; i < className.length(); ++i) {
            char c = className.charAt(i);
            if (c == '.') {
                if (className.charAt(i - 1) == '.') {
                    throw new IllegalArgumentException("'..' not allowed in class name: " + className);
                }
                if (i != className.length() - 1) continue;
                throw new IllegalArgumentException("'.' not allowed at end of class name: " + className);
            }
            if (Character.isJavaIdentifierPart(c)) continue;
            throw new IllegalArgumentException("Illegal character '" + c + "' in class name: " + className);
        }
    }

    private boolean isAssignableFromRecursively(JClass arg) {
        if (this.getQualifiedName().equals(arg.getQualifiedName())) {
            return true;
        }
        JClass[] interfaces = arg.getInterfaces();
        if (interfaces != null) {
            for (int i = 0; i < interfaces.length; ++i) {
                if (!this.isAssignableFromRecursively(interfaces[i])) continue;
                return true;
            }
        }
        return (arg = arg.getSuperclass()) != null && this.isAssignableFromRecursively(arg);
    }

    private void addFieldsRecursively(JClass clazz, Collection out) {
        JField[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            out.add(fields[i]);
        }
        JClass[] ints = clazz.getInterfaces();
        for (int i = 0; i < ints.length; ++i) {
            this.addFieldsRecursively(ints[i], out);
        }
        if ((clazz = clazz.getSuperclass()) != null) {
            this.addFieldsRecursively(clazz, out);
        }
    }

    private void addMethodsRecursively(JClass clazz, Collection out) {
        JMethod[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; ++i) {
            out.add(methods[i]);
        }
        JClass[] ints = clazz.getInterfaces();
        for (int i = 0; i < ints.length; ++i) {
            this.addMethodsRecursively(ints[i], out);
        }
        if ((clazz = clazz.getSuperclass()) != null) {
            this.addMethodsRecursively(clazz, out);
        }
    }

    public void ensureLoaded() {
        if (this.mState == 6) {
            return;
        }
        if (this.mState == 2) {
            if (this.mPopulator == null) {
                throw new IllegalStateException("null populator");
            }
            this.setState(3);
            this.mPopulator.populate(this);
            this.setState(4);
        }
        if (this.mState == 4) {
            this.setState(5);
            ((JamClassLoaderImpl)this.getClassLoader()).initialize(this);
        }
        this.setState(6);
    }
}

