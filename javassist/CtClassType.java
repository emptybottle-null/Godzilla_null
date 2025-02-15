/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javassist.CannotCompileException;
import javassist.ClassMap;
import javassist.ClassPool;
import javassist.CodeConverter;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMember;
import javassist.CtMethod;
import javassist.FieldInitLink;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.Bytecode;
import javassist.bytecode.ClassFile;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ConstantAttribute;
import javassist.bytecode.Descriptor;
import javassist.bytecode.EnclosingMethodAttribute;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.InnerClassesAttribute;
import javassist.bytecode.MethodInfo;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.SignatureAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.AnnotationImpl;
import javassist.compiler.AccessorMaker;
import javassist.compiler.CompileError;
import javassist.compiler.Javac;
import javassist.expr.ExprEditor;

class CtClassType
extends CtClass {
    ClassPool classPool;
    boolean wasChanged;
    private boolean wasFrozen;
    boolean wasPruned;
    boolean gcConstPool;
    ClassFile classfile;
    byte[] rawClassfile;
    private Reference<CtMember.Cache> memberCache;
    private AccessorMaker accessors;
    private FieldInitLink fieldInitializers;
    private Map<CtMethod, String> hiddenMethods;
    private int uniqueNumberSeed;
    private boolean doPruning = ClassPool.doPruning;
    private int getCount;
    private static final int GET_THRESHOLD = 2;

    CtClassType(String name, ClassPool cp) {
        super(name);
        this.classPool = cp;
        this.gcConstPool = false;
        this.wasPruned = false;
        this.wasFrozen = false;
        this.wasChanged = false;
        this.classfile = null;
        this.rawClassfile = null;
        this.memberCache = null;
        this.accessors = null;
        this.fieldInitializers = null;
        this.hiddenMethods = null;
        this.uniqueNumberSeed = 0;
        this.getCount = 0;
    }

    CtClassType(InputStream ins, ClassPool cp) throws IOException {
        this((String)null, cp);
        this.classfile = new ClassFile(new DataInputStream(ins));
        this.qualifiedName = this.classfile.getName();
    }

    CtClassType(ClassFile cf, ClassPool cp) {
        this((String)null, cp);
        this.classfile = cf;
        this.qualifiedName = this.classfile.getName();
    }

    @Override
    protected void extendToString(StringBuffer buffer) {
        if (this.wasChanged) {
            buffer.append("changed ");
        }
        if (this.wasFrozen) {
            buffer.append("frozen ");
        }
        if (this.wasPruned) {
            buffer.append("pruned ");
        }
        buffer.append(Modifier.toString(this.getModifiers()));
        buffer.append(" class ");
        buffer.append(this.getName());
        try {
            String name;
            CtClass ext = this.getSuperclass();
            if (ext != null && !(name = ext.getName()).equals("java.lang.Object")) {
                buffer.append(" extends " + ext.getName());
            }
        } catch (NotFoundException e) {
            buffer.append(" extends ??");
        }
        try {
            CtClass[] intf = this.getInterfaces();
            if (intf.length > 0) {
                buffer.append(" implements ");
            }
            for (int i = 0; i < intf.length; ++i) {
                buffer.append(intf[i].getName());
                buffer.append(", ");
            }
        } catch (NotFoundException e) {
            buffer.append(" extends ??");
        }
        CtMember.Cache memCache = this.getMembers();
        this.exToString(buffer, " fields=", memCache.fieldHead(), memCache.lastField());
        this.exToString(buffer, " constructors=", memCache.consHead(), memCache.lastCons());
        this.exToString(buffer, " methods=", memCache.methodHead(), memCache.lastMethod());
    }

    private void exToString(StringBuffer buffer, String msg, CtMember head, CtMember tail) {
        buffer.append(msg);
        while (head != tail) {
            head = head.next();
            buffer.append(head);
            buffer.append(", ");
        }
    }

    @Override
    public AccessorMaker getAccessorMaker() {
        if (this.accessors == null) {
            this.accessors = new AccessorMaker(this);
        }
        return this.accessors;
    }

    @Override
    public ClassFile getClassFile2() {
        return this.getClassFile3(true);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public ClassFile getClassFile3(boolean doCompress) {
        byte[] rcfile;
        ClassFile cfile = this.classfile;
        if (cfile != null) {
            return cfile;
        }
        if (doCompress) {
            this.classPool.compress();
        }
        CtClassType ctClassType = this;
        synchronized (ctClassType) {
            cfile = this.classfile;
            if (cfile != null) {
                return cfile;
            }
            rcfile = this.rawClassfile;
        }
        if (rcfile != null) {
            ClassFile cf;
            try {
                cf = new ClassFile(new DataInputStream(new ByteArrayInputStream(rcfile)));
            } catch (IOException e) {
                throw new RuntimeException(e.toString(), e);
            }
            this.getCount = 2;
            CtClassType e = this;
            synchronized (e) {
                this.rawClassfile = null;
                return this.setClassFile(cf);
            }
        }
        InputStream fin = null;
        try {
            fin = this.classPool.openClassfile(this.getName());
            if (fin == null) {
                throw new NotFoundException(this.getName());
            }
            ClassFile cf = new ClassFile(new DataInputStream(fin = new BufferedInputStream(fin)));
            if (!cf.getName().equals(this.qualifiedName)) {
                throw new RuntimeException("cannot find " + this.qualifiedName + ": " + cf.getName() + " found in " + this.qualifiedName.replace('.', '/') + ".class");
            }
            ClassFile classFile = this.setClassFile(cf);
            return classFile;
        } catch (NotFoundException e) {
            throw new RuntimeException(e.toString(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.toString(), e);
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (IOException iOException) {}
            }
        }
    }

    @Override
    final void incGetCounter() {
        ++this.getCount;
    }

    @Override
    void compress() {
        if (this.getCount < 2) {
            if (!this.isModified() && ClassPool.releaseUnmodifiedClassFile) {
                this.removeClassFile();
            } else if (this.isFrozen() && !this.wasPruned) {
                this.saveClassFile();
            }
        }
        this.getCount = 0;
    }

    private synchronized void saveClassFile() {
        if (this.classfile == null || this.hasMemberCache() != null) {
            return;
        }
        ByteArrayOutputStream barray = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(barray);
        try {
            this.classfile.write(out);
            barray.close();
            this.rawClassfile = barray.toByteArray();
            this.classfile = null;
        } catch (IOException iOException) {
            // empty catch block
        }
    }

    private synchronized void removeClassFile() {
        if (this.classfile != null && !this.isModified() && this.hasMemberCache() == null) {
            this.classfile = null;
        }
    }

    private synchronized ClassFile setClassFile(ClassFile cf) {
        if (this.classfile == null) {
            this.classfile = cf;
        }
        return this.classfile;
    }

    @Override
    public ClassPool getClassPool() {
        return this.classPool;
    }

    void setClassPool(ClassPool cp) {
        this.classPool = cp;
    }

    @Override
    public URL getURL() throws NotFoundException {
        URL url = this.classPool.find(this.getName());
        if (url == null) {
            throw new NotFoundException(this.getName());
        }
        return url;
    }

    @Override
    public boolean isModified() {
        return this.wasChanged;
    }

    @Override
    public boolean isFrozen() {
        return this.wasFrozen;
    }

    @Override
    public void freeze() {
        this.wasFrozen = true;
    }

    @Override
    void checkModify() throws RuntimeException {
        if (this.isFrozen()) {
            String msg = this.getName() + " class is frozen";
            if (this.wasPruned) {
                msg = msg + " and pruned";
            }
            throw new RuntimeException(msg);
        }
        this.wasChanged = true;
    }

    @Override
    public void defrost() {
        this.checkPruned("defrost");
        this.wasFrozen = false;
    }

    @Override
    public boolean subtypeOf(CtClass clazz) throws NotFoundException {
        int i;
        String cname = clazz.getName();
        if (this == clazz || this.getName().equals(cname)) {
            return true;
        }
        ClassFile file = this.getClassFile2();
        String supername = file.getSuperclass();
        if (supername != null && supername.equals(cname)) {
            return true;
        }
        String[] ifs = file.getInterfaces();
        int num = ifs.length;
        for (i = 0; i < num; ++i) {
            if (!ifs[i].equals(cname)) continue;
            return true;
        }
        if (supername != null && this.classPool.get(supername).subtypeOf(clazz)) {
            return true;
        }
        for (i = 0; i < num; ++i) {
            if (!this.classPool.get(ifs[i]).subtypeOf(clazz)) continue;
            return true;
        }
        return false;
    }

    @Override
    public void setName(String name) throws RuntimeException {
        String oldname = this.getName();
        if (name.equals(oldname)) {
            return;
        }
        this.classPool.checkNotFrozen(name);
        ClassFile cf = this.getClassFile2();
        super.setName(name);
        cf.setName(name);
        this.nameReplaced();
        this.classPool.classNameChanged(oldname, this);
    }

    @Override
    public String getGenericSignature() {
        SignatureAttribute sa = (SignatureAttribute)this.getClassFile2().getAttribute("Signature");
        return sa == null ? null : sa.getSignature();
    }

    @Override
    public void setGenericSignature(String sig) {
        ClassFile cf = this.getClassFile();
        SignatureAttribute sa = new SignatureAttribute(cf.getConstPool(), sig);
        cf.addAttribute(sa);
    }

    @Override
    public void replaceClassName(ClassMap classnames) throws RuntimeException {
        String oldClassName = this.getName();
        String newClassName = classnames.get(Descriptor.toJvmName(oldClassName));
        if (newClassName != null) {
            newClassName = Descriptor.toJavaName(newClassName);
            this.classPool.checkNotFrozen(newClassName);
        }
        super.replaceClassName(classnames);
        ClassFile cf = this.getClassFile2();
        cf.renameClass(classnames);
        this.nameReplaced();
        if (newClassName != null) {
            super.setName(newClassName);
            this.classPool.classNameChanged(oldClassName, this);
        }
    }

    @Override
    public void replaceClassName(String oldname, String newname) throws RuntimeException {
        String thisname = this.getName();
        if (thisname.equals(oldname)) {
            this.setName(newname);
        } else {
            super.replaceClassName(oldname, newname);
            this.getClassFile2().renameClass(oldname, newname);
            this.nameReplaced();
        }
    }

    @Override
    public boolean isInterface() {
        return Modifier.isInterface(this.getModifiers());
    }

    @Override
    public boolean isAnnotation() {
        return Modifier.isAnnotation(this.getModifiers());
    }

    @Override
    public boolean isEnum() {
        return Modifier.isEnum(this.getModifiers());
    }

    @Override
    public int getModifiers() {
        ClassFile cf = this.getClassFile2();
        int acc = cf.getAccessFlags();
        acc = AccessFlag.clear(acc, 32);
        int inner = cf.getInnerAccessFlags();
        if (inner != -1) {
            if ((inner & 8) != 0) {
                acc |= 8;
            }
            if ((inner & 1) != 0) {
                acc |= 1;
            } else {
                acc &= 0xFFFFFFFE;
                if ((inner & 4) != 0) {
                    acc |= 4;
                } else if ((inner & 2) != 0) {
                    acc |= 2;
                }
            }
        }
        return AccessFlag.toModifier(acc);
    }

    @Override
    public CtClass[] getNestedClasses() throws NotFoundException {
        ClassFile cf = this.getClassFile2();
        InnerClassesAttribute ica = (InnerClassesAttribute)cf.getAttribute("InnerClasses");
        if (ica == null) {
            return new CtClass[0];
        }
        String thisName = cf.getName() + "$";
        int n = ica.tableLength();
        ArrayList<CtClass> list = new ArrayList<CtClass>(n);
        for (int i = 0; i < n; ++i) {
            String name = ica.innerClass(i);
            if (name == null || !name.startsWith(thisName) || name.lastIndexOf(36) >= thisName.length()) continue;
            list.add(this.classPool.get(name));
        }
        return list.toArray(new CtClass[list.size()]);
    }

    @Override
    public void setModifiers(int mod) {
        this.checkModify();
        CtClassType.updateInnerEntry(mod, this.getName(), this, true);
        ClassFile cf = this.getClassFile2();
        cf.setAccessFlags(AccessFlag.of(mod & 0xFFFFFFF7));
    }

    private static void updateInnerEntry(int newMod, String name, CtClass clazz, boolean outer) {
        ClassFile cf = clazz.getClassFile2();
        InnerClassesAttribute ica = (InnerClassesAttribute)cf.getAttribute("InnerClasses");
        if (ica != null) {
            int isStatic;
            int mod = newMod & 0xFFFFFFF7;
            int i = ica.find(name);
            if (!(i < 0 || (isStatic = ica.accessFlags(i) & 8) == 0 && Modifier.isStatic(newMod))) {
                clazz.checkModify();
                ica.setAccessFlags(i, AccessFlag.of(mod) | isStatic);
                String outName = ica.outerClass(i);
                if (outName != null && outer) {
                    try {
                        CtClass parent = clazz.getClassPool().get(outName);
                        CtClassType.updateInnerEntry(mod, name, parent, false);
                    } catch (NotFoundException e) {
                        throw new RuntimeException("cannot find the declaring class: " + outName);
                    }
                }
                return;
            }
        }
        if (Modifier.isStatic(newMod)) {
            throw new RuntimeException("cannot change " + Descriptor.toJavaName(name) + " into a static class");
        }
    }

    @Override
    public boolean hasAnnotation(String annotationName) {
        ClassFile cf = this.getClassFile2();
        AnnotationsAttribute ainfo = (AnnotationsAttribute)cf.getAttribute("RuntimeInvisibleAnnotations");
        AnnotationsAttribute ainfo2 = (AnnotationsAttribute)cf.getAttribute("RuntimeVisibleAnnotations");
        return CtClassType.hasAnnotationType(annotationName, this.getClassPool(), ainfo, ainfo2);
    }

    @Deprecated
    static boolean hasAnnotationType(Class<?> clz, ClassPool cp, AnnotationsAttribute a1, AnnotationsAttribute a2) {
        return CtClassType.hasAnnotationType(clz.getName(), cp, a1, a2);
    }

    static boolean hasAnnotationType(String annotationTypeName, ClassPool cp, AnnotationsAttribute a1, AnnotationsAttribute a2) {
        int i;
        Annotation[] anno1 = a1 == null ? null : a1.getAnnotations();
        Annotation[] anno2 = a2 == null ? null : a2.getAnnotations();
        if (anno1 != null) {
            for (i = 0; i < anno1.length; ++i) {
                if (!anno1[i].getTypeName().equals(annotationTypeName)) continue;
                return true;
            }
        }
        if (anno2 != null) {
            for (i = 0; i < anno2.length; ++i) {
                if (!anno2[i].getTypeName().equals(annotationTypeName)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public Object getAnnotation(Class<?> clz) throws ClassNotFoundException {
        ClassFile cf = this.getClassFile2();
        AnnotationsAttribute ainfo = (AnnotationsAttribute)cf.getAttribute("RuntimeInvisibleAnnotations");
        AnnotationsAttribute ainfo2 = (AnnotationsAttribute)cf.getAttribute("RuntimeVisibleAnnotations");
        return CtClassType.getAnnotationType(clz, this.getClassPool(), ainfo, ainfo2);
    }

    static Object getAnnotationType(Class<?> clz, ClassPool cp, AnnotationsAttribute a1, AnnotationsAttribute a2) throws ClassNotFoundException {
        int i;
        Annotation[] anno1 = a1 == null ? null : a1.getAnnotations();
        Annotation[] anno2 = a2 == null ? null : a2.getAnnotations();
        String typeName = clz.getName();
        if (anno1 != null) {
            for (i = 0; i < anno1.length; ++i) {
                if (!anno1[i].getTypeName().equals(typeName)) continue;
                return CtClassType.toAnnoType(anno1[i], cp);
            }
        }
        if (anno2 != null) {
            for (i = 0; i < anno2.length; ++i) {
                if (!anno2[i].getTypeName().equals(typeName)) continue;
                return CtClassType.toAnnoType(anno2[i], cp);
            }
        }
        return null;
    }

    @Override
    public Object[] getAnnotations() throws ClassNotFoundException {
        return this.getAnnotations(false);
    }

    @Override
    public Object[] getAvailableAnnotations() {
        try {
            return this.getAnnotations(true);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unexpected exception ", e);
        }
    }

    private Object[] getAnnotations(boolean ignoreNotFound) throws ClassNotFoundException {
        ClassFile cf = this.getClassFile2();
        AnnotationsAttribute ainfo = (AnnotationsAttribute)cf.getAttribute("RuntimeInvisibleAnnotations");
        AnnotationsAttribute ainfo2 = (AnnotationsAttribute)cf.getAttribute("RuntimeVisibleAnnotations");
        return CtClassType.toAnnotationType(ignoreNotFound, this.getClassPool(), ainfo, ainfo2);
    }

    static Object[] toAnnotationType(boolean ignoreNotFound, ClassPool cp, AnnotationsAttribute a1, AnnotationsAttribute a2) throws ClassNotFoundException {
        int size2;
        Annotation[] anno2;
        int size1;
        Annotation[] anno1;
        if (a1 == null) {
            anno1 = null;
            size1 = 0;
        } else {
            anno1 = a1.getAnnotations();
            size1 = anno1.length;
        }
        if (a2 == null) {
            anno2 = null;
            size2 = 0;
        } else {
            anno2 = a2.getAnnotations();
            size2 = anno2.length;
        }
        if (!ignoreNotFound) {
            Object[] result = new Object[size1 + size2];
            for (int i = 0; i < size1; ++i) {
                result[i] = CtClassType.toAnnoType(anno1[i], cp);
            }
            for (int j = 0; j < size2; ++j) {
                result[j + size1] = CtClassType.toAnnoType(anno2[j], cp);
            }
            return result;
        }
        ArrayList<Object> annotations = new ArrayList<Object>();
        for (int i = 0; i < size1; ++i) {
            try {
                annotations.add(CtClassType.toAnnoType(anno1[i], cp));
                continue;
            } catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
        }
        for (int j = 0; j < size2; ++j) {
            try {
                annotations.add(CtClassType.toAnnoType(anno2[j], cp));
                continue;
            } catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
        }
        return annotations.toArray();
    }

    static Object[][] toAnnotationType(boolean ignoreNotFound, ClassPool cp, ParameterAnnotationsAttribute a1, ParameterAnnotationsAttribute a2, MethodInfo minfo) throws ClassNotFoundException {
        int numParameters = 0;
        numParameters = a1 != null ? a1.numParameters() : (a2 != null ? a2.numParameters() : Descriptor.numOfParameters(minfo.getDescriptor()));
        Object[][] result = new Object[numParameters][];
        for (int i = 0; i < numParameters; ++i) {
            int j;
            int size2;
            Annotation[] anno2;
            int size1;
            Annotation[] anno1;
            if (a1 == null) {
                anno1 = null;
                size1 = 0;
            } else {
                anno1 = a1.getAnnotations()[i];
                size1 = anno1.length;
            }
            if (a2 == null) {
                anno2 = null;
                size2 = 0;
            } else {
                anno2 = a2.getAnnotations()[i];
                size2 = anno2.length;
            }
            if (!ignoreNotFound) {
                int j2;
                result[i] = new Object[size1 + size2];
                for (j2 = 0; j2 < size1; ++j2) {
                    result[i][j2] = CtClassType.toAnnoType(anno1[j2], cp);
                }
                for (j2 = 0; j2 < size2; ++j2) {
                    result[i][j2 + size1] = CtClassType.toAnnoType(anno2[j2], cp);
                }
                continue;
            }
            ArrayList<Object> annotations = new ArrayList<Object>();
            for (j = 0; j < size1; ++j) {
                try {
                    annotations.add(CtClassType.toAnnoType(anno1[j], cp));
                    continue;
                } catch (ClassNotFoundException classNotFoundException) {
                    // empty catch block
                }
            }
            for (j = 0; j < size2; ++j) {
                try {
                    annotations.add(CtClassType.toAnnoType(anno2[j], cp));
                    continue;
                } catch (ClassNotFoundException classNotFoundException) {
                    // empty catch block
                }
            }
            result[i] = annotations.toArray();
        }
        return result;
    }

    private static Object toAnnoType(Annotation anno, ClassPool cp) throws ClassNotFoundException {
        try {
            ClassLoader cl = cp.getClassLoader();
            return anno.toAnnotationType(cl, cp);
        } catch (ClassNotFoundException e) {
            ClassLoader cl2 = cp.getClass().getClassLoader();
            try {
                return anno.toAnnotationType(cl2, cp);
            } catch (ClassNotFoundException e2) {
                try {
                    Class<?> clazz = cp.get(anno.getTypeName()).toClass();
                    return AnnotationImpl.make(clazz.getClassLoader(), clazz, cp, anno);
                } catch (Throwable e3) {
                    throw new ClassNotFoundException(anno.getTypeName());
                }
            }
        }
    }

    @Override
    public boolean subclassOf(CtClass superclass) {
        if (superclass == null) {
            return false;
        }
        String superName = superclass.getName();
        try {
            for (CtClass curr = this; curr != null; curr = ((CtClass)curr).getSuperclass()) {
                if (!curr.getName().equals(superName)) continue;
                return true;
            }
        } catch (Exception exception) {
            // empty catch block
        }
        return false;
    }

    @Override
    public CtClass getSuperclass() throws NotFoundException {
        String supername = this.getClassFile2().getSuperclass();
        if (supername == null) {
            return null;
        }
        return this.classPool.get(supername);
    }

    @Override
    public void setSuperclass(CtClass clazz) throws CannotCompileException {
        this.checkModify();
        if (this.isInterface()) {
            this.addInterface(clazz);
        } else {
            this.getClassFile2().setSuperclass(clazz.getName());
        }
    }

    @Override
    public CtClass[] getInterfaces() throws NotFoundException {
        String[] ifs = this.getClassFile2().getInterfaces();
        int num = ifs.length;
        CtClass[] ifc = new CtClass[num];
        for (int i = 0; i < num; ++i) {
            ifc[i] = this.classPool.get(ifs[i]);
        }
        return ifc;
    }

    @Override
    public void setInterfaces(CtClass[] list) {
        String[] ifs;
        this.checkModify();
        if (list == null) {
            ifs = new String[]{};
        } else {
            int num = list.length;
            ifs = new String[num];
            for (int i = 0; i < num; ++i) {
                ifs[i] = list[i].getName();
            }
        }
        this.getClassFile2().setInterfaces(ifs);
    }

    @Override
    public void addInterface(CtClass anInterface) {
        this.checkModify();
        if (anInterface != null) {
            this.getClassFile2().addInterface(anInterface.getName());
        }
    }

    @Override
    public CtClass getDeclaringClass() throws NotFoundException {
        ClassFile cf = this.getClassFile2();
        InnerClassesAttribute ica = (InnerClassesAttribute)cf.getAttribute("InnerClasses");
        if (ica == null) {
            return null;
        }
        String name = this.getName();
        int n = ica.tableLength();
        for (int i = 0; i < n; ++i) {
            if (!name.equals(ica.innerClass(i))) continue;
            String outName = ica.outerClass(i);
            if (outName != null) {
                return this.classPool.get(outName);
            }
            EnclosingMethodAttribute ema = (EnclosingMethodAttribute)cf.getAttribute("EnclosingMethod");
            if (ema == null) continue;
            return this.classPool.get(ema.className());
        }
        return null;
    }

    @Override
    public CtBehavior getEnclosingBehavior() throws NotFoundException {
        ClassFile cf = this.getClassFile2();
        EnclosingMethodAttribute ema = (EnclosingMethodAttribute)cf.getAttribute("EnclosingMethod");
        if (ema == null) {
            return null;
        }
        CtClass enc = this.classPool.get(ema.className());
        String name = ema.methodName();
        if ("<init>".equals(name)) {
            return enc.getConstructor(ema.methodDescriptor());
        }
        if ("<clinit>".equals(name)) {
            return enc.getClassInitializer();
        }
        return enc.getMethod(name, ema.methodDescriptor());
    }

    @Override
    public CtClass makeNestedClass(String name, boolean isStatic) {
        if (!isStatic) {
            throw new RuntimeException("sorry, only nested static class is supported");
        }
        this.checkModify();
        CtClass c = this.classPool.makeNestedClass(this.getName() + "$" + name);
        ClassFile cf = this.getClassFile2();
        ClassFile cf2 = c.getClassFile2();
        InnerClassesAttribute ica = (InnerClassesAttribute)cf.getAttribute("InnerClasses");
        if (ica == null) {
            ica = new InnerClassesAttribute(cf.getConstPool());
            cf.addAttribute(ica);
        }
        ica.append(c.getName(), this.getName(), name, cf2.getAccessFlags() & 0xFFFFFFDF | 8);
        cf2.addAttribute(ica.copy(cf2.getConstPool(), null));
        return c;
    }

    private void nameReplaced() {
        CtMember.Cache cache = this.hasMemberCache();
        if (cache != null) {
            CtMember tail = cache.lastMethod();
            for (CtMember mth = cache.methodHead(); mth != tail; mth = mth.next()) {
                mth.nameReplaced();
            }
        }
    }

    protected CtMember.Cache hasMemberCache() {
        if (this.memberCache != null) {
            return this.memberCache.get();
        }
        return null;
    }

    protected synchronized CtMember.Cache getMembers() {
        CtMember.Cache cache = null;
        if (this.memberCache == null || (cache = this.memberCache.get()) == null) {
            cache = new CtMember.Cache(this);
            this.makeFieldCache(cache);
            this.makeBehaviorCache(cache);
            this.memberCache = new WeakReference<CtMember.Cache>(cache);
        }
        return cache;
    }

    private void makeFieldCache(CtMember.Cache cache) {
        List<FieldInfo> fields = this.getClassFile3(false).getFields();
        for (FieldInfo finfo : fields) {
            cache.addField(new CtField(finfo, (CtClass)this));
        }
    }

    private void makeBehaviorCache(CtMember.Cache cache) {
        List<MethodInfo> methods = this.getClassFile3(false).getMethods();
        for (MethodInfo minfo : methods) {
            if (minfo.isMethod()) {
                cache.addMethod(new CtMethod(minfo, this));
                continue;
            }
            cache.addConstructor(new CtConstructor(minfo, (CtClass)this));
        }
    }

    @Override
    public CtField[] getFields() {
        ArrayList<CtMember> alist = new ArrayList<CtMember>();
        CtClassType.getFields(alist, this);
        return alist.toArray(new CtField[alist.size()]);
    }

    private static void getFields(List<CtMember> alist, CtClass cc) {
        if (cc == null) {
            return;
        }
        try {
            CtClassType.getFields(alist, cc.getSuperclass());
        } catch (NotFoundException notFoundException) {
            // empty catch block
        }
        try {
            CtClass[] ifs;
            for (CtClass ctc : ifs = cc.getInterfaces()) {
                CtClassType.getFields(alist, ctc);
            }
        } catch (NotFoundException ifs) {
            // empty catch block
        }
        CtMember.Cache memCache = ((CtClassType)cc).getMembers();
        CtMember field = memCache.fieldHead();
        CtMember tail = memCache.lastField();
        while (field != tail) {
            if (Modifier.isPrivate((field = field.next()).getModifiers())) continue;
            alist.add(field);
        }
    }

    @Override
    public CtField getField(String name, String desc) throws NotFoundException {
        CtField f = this.getField2(name, desc);
        return this.checkGetField(f, name, desc);
    }

    private CtField checkGetField(CtField f, String name, String desc) throws NotFoundException {
        if (f == null) {
            String msg = "field: " + name;
            if (desc != null) {
                msg = msg + " type " + desc;
            }
            throw new NotFoundException(msg + " in " + this.getName());
        }
        return f;
    }

    @Override
    CtField getField2(String name, String desc) {
        CtField df = this.getDeclaredField2(name, desc);
        if (df != null) {
            return df;
        }
        try {
            CtClass[] ifs;
            for (CtClass ctc : ifs = this.getInterfaces()) {
                CtField f = ctc.getField2(name, desc);
                if (f == null) continue;
                return f;
            }
            CtClass s = this.getSuperclass();
            if (s != null) {
                return s.getField2(name, desc);
            }
        } catch (NotFoundException notFoundException) {
            // empty catch block
        }
        return null;
    }

    @Override
    public CtField[] getDeclaredFields() {
        CtMember field;
        CtMember.Cache memCache = this.getMembers();
        CtMember tail = memCache.lastField();
        int num = CtMember.Cache.count(field, tail);
        CtField[] cfs = new CtField[num];
        int i = 0;
        for (field = memCache.fieldHead(); field != tail; field = field.next()) {
            cfs[i++] = (CtField)field;
        }
        return cfs;
    }

    @Override
    public CtField getDeclaredField(String name) throws NotFoundException {
        return this.getDeclaredField(name, null);
    }

    @Override
    public CtField getDeclaredField(String name, String desc) throws NotFoundException {
        CtField f = this.getDeclaredField2(name, desc);
        return this.checkGetField(f, name, desc);
    }

    private CtField getDeclaredField2(String name, String desc) {
        CtMember.Cache memCache = this.getMembers();
        CtMember field = memCache.fieldHead();
        CtMember tail = memCache.lastField();
        while (field != tail) {
            if (!(field = field.next()).getName().equals(name) || desc != null && !desc.equals(field.getSignature())) continue;
            return (CtField)field;
        }
        return null;
    }

    @Override
    public CtBehavior[] getDeclaredBehaviors() {
        CtMember cons;
        CtMember.Cache memCache = this.getMembers();
        CtMember consTail = memCache.lastCons();
        int cnum = CtMember.Cache.count(cons, consTail);
        CtMember mth = memCache.methodHead();
        CtMember mthTail = memCache.lastMethod();
        int mnum = CtMember.Cache.count(mth, mthTail);
        CtBehavior[] cb = new CtBehavior[cnum + mnum];
        int i = 0;
        for (cons = memCache.consHead(); cons != consTail; cons = cons.next()) {
            cb[i++] = (CtBehavior)cons;
        }
        while (mth != mthTail) {
            mth = mth.next();
            cb[i++] = (CtBehavior)mth;
        }
        return cb;
    }

    @Override
    public CtConstructor[] getConstructors() {
        CtMember.Cache memCache = this.getMembers();
        CtMember cons = memCache.consHead();
        CtMember consTail = memCache.lastCons();
        int n = 0;
        CtMember mem = cons;
        while (mem != consTail) {
            if (!CtClassType.isPubCons((CtConstructor)(mem = mem.next()))) continue;
            ++n;
        }
        CtConstructor[] result = new CtConstructor[n];
        int i = 0;
        mem = cons;
        while (mem != consTail) {
            CtConstructor cc = (CtConstructor)(mem = mem.next());
            if (!CtClassType.isPubCons(cc)) continue;
            result[i++] = cc;
        }
        return result;
    }

    private static boolean isPubCons(CtConstructor cons) {
        return !Modifier.isPrivate(cons.getModifiers()) && cons.isConstructor();
    }

    @Override
    public CtConstructor getConstructor(String desc) throws NotFoundException {
        CtMember.Cache memCache = this.getMembers();
        CtMember cons = memCache.consHead();
        CtMember consTail = memCache.lastCons();
        while (cons != consTail) {
            CtConstructor cc = (CtConstructor)(cons = cons.next());
            if (!cc.getMethodInfo2().getDescriptor().equals(desc) || !cc.isConstructor()) continue;
            return cc;
        }
        return super.getConstructor(desc);
    }

    @Override
    public CtConstructor[] getDeclaredConstructors() {
        CtMember.Cache memCache = this.getMembers();
        CtMember cons = memCache.consHead();
        CtMember consTail = memCache.lastCons();
        int n = 0;
        CtMember mem = cons;
        while (mem != consTail) {
            CtConstructor cc = (CtConstructor)(mem = mem.next());
            if (!cc.isConstructor()) continue;
            ++n;
        }
        CtConstructor[] result = new CtConstructor[n];
        int i = 0;
        mem = cons;
        while (mem != consTail) {
            CtConstructor cc = (CtConstructor)(mem = mem.next());
            if (!cc.isConstructor()) continue;
            result[i++] = cc;
        }
        return result;
    }

    @Override
    public CtConstructor getClassInitializer() {
        CtMember.Cache memCache = this.getMembers();
        CtMember cons = memCache.consHead();
        CtMember consTail = memCache.lastCons();
        while (cons != consTail) {
            CtConstructor cc = (CtConstructor)(cons = cons.next());
            if (!cc.isClassInitializer()) continue;
            return cc;
        }
        return null;
    }

    @Override
    public CtMethod[] getMethods() {
        HashMap<String, CtMember> h = new HashMap<String, CtMember>();
        CtClassType.getMethods0(h, this);
        return h.values().toArray(new CtMethod[h.size()]);
    }

    private static void getMethods0(Map<String, CtMember> h, CtClass cc) {
        try {
            CtClass[] ifs;
            for (CtClass ctc : ifs = cc.getInterfaces()) {
                CtClassType.getMethods0(h, ctc);
            }
        } catch (NotFoundException ifs) {
            // empty catch block
        }
        try {
            CtClass s = cc.getSuperclass();
            if (s != null) {
                CtClassType.getMethods0(h, s);
            }
        } catch (NotFoundException s) {
            // empty catch block
        }
        if (cc instanceof CtClassType) {
            CtMember.Cache memCache = ((CtClassType)cc).getMembers();
            CtMember mth = memCache.methodHead();
            CtMember mthTail = memCache.lastMethod();
            while (mth != mthTail) {
                if (Modifier.isPrivate((mth = mth.next()).getModifiers())) continue;
                h.put(((CtMethod)mth).getStringRep(), mth);
            }
        }
    }

    @Override
    public CtMethod getMethod(String name, String desc) throws NotFoundException {
        CtMethod m = CtClassType.getMethod0(this, name, desc);
        if (m != null) {
            return m;
        }
        throw new NotFoundException(name + "(..) is not found in " + this.getName());
    }

    private static CtMethod getMethod0(CtClass cc, String name, String desc) {
        if (cc instanceof CtClassType) {
            CtMember.Cache memCache = ((CtClassType)cc).getMembers();
            CtMember mth = memCache.methodHead();
            CtMember mthTail = memCache.lastMethod();
            while (mth != mthTail) {
                if (!(mth = mth.next()).getName().equals(name) || !((CtMethod)mth).getMethodInfo2().getDescriptor().equals(desc)) continue;
                return (CtMethod)mth;
            }
        }
        try {
            CtMethod m;
            CtClass s = cc.getSuperclass();
            if (s != null && (m = CtClassType.getMethod0(s, name, desc)) != null) {
                return m;
            }
        } catch (NotFoundException s) {
            // empty catch block
        }
        try {
            CtClass[] ifs;
            for (CtClass ctc : ifs = cc.getInterfaces()) {
                CtMethod m = CtClassType.getMethod0(ctc, name, desc);
                if (m == null) continue;
                return m;
            }
        } catch (NotFoundException notFoundException) {
            // empty catch block
        }
        return null;
    }

    @Override
    public CtMethod[] getDeclaredMethods() {
        CtMember.Cache memCache = this.getMembers();
        CtMember mthTail = memCache.lastMethod();
        ArrayList<CtMember> methods = new ArrayList<CtMember>();
        for (CtMember mth = memCache.methodHead(); mth != mthTail; mth = mth.next()) {
            methods.add(mth);
        }
        return methods.toArray(new CtMethod[methods.size()]);
    }

    @Override
    public CtMethod[] getDeclaredMethods(String name) throws NotFoundException {
        CtMember.Cache memCache = this.getMembers();
        CtMember mth = memCache.methodHead();
        CtMember mthTail = memCache.lastMethod();
        ArrayList<CtMember> methods = new ArrayList<CtMember>();
        while (mth != mthTail) {
            if (!(mth = mth.next()).getName().equals(name)) continue;
            methods.add(mth);
        }
        return methods.toArray(new CtMethod[methods.size()]);
    }

    @Override
    public CtMethod getDeclaredMethod(String name) throws NotFoundException {
        CtMember.Cache memCache = this.getMembers();
        CtMember mth = memCache.methodHead();
        CtMember mthTail = memCache.lastMethod();
        while (mth != mthTail) {
            if (!(mth = mth.next()).getName().equals(name)) continue;
            return (CtMethod)mth;
        }
        throw new NotFoundException(name + "(..) is not found in " + this.getName());
    }

    @Override
    public CtMethod getDeclaredMethod(String name, CtClass[] params) throws NotFoundException {
        String desc = Descriptor.ofParameters(params);
        CtMember.Cache memCache = this.getMembers();
        CtMember mth = memCache.methodHead();
        CtMember mthTail = memCache.lastMethod();
        while (mth != mthTail) {
            if (!(mth = mth.next()).getName().equals(name) || !((CtMethod)mth).getMethodInfo2().getDescriptor().startsWith(desc)) continue;
            return (CtMethod)mth;
        }
        throw new NotFoundException(name + "(..) is not found in " + this.getName());
    }

    @Override
    public void addField(CtField f, String init) throws CannotCompileException {
        this.addField(f, CtField.Initializer.byExpr(init));
    }

    @Override
    public void addField(CtField f, CtField.Initializer init) throws CannotCompileException {
        this.checkModify();
        if (f.getDeclaringClass() != this) {
            throw new CannotCompileException("cannot add");
        }
        if (init == null) {
            init = f.getInit();
        }
        if (init != null) {
            init.check(f.getSignature());
            int mod = f.getModifiers();
            if (Modifier.isStatic(mod) && Modifier.isFinal(mod)) {
                try {
                    ConstPool cp = this.getClassFile2().getConstPool();
                    int index = init.getConstantValue(cp, f.getType());
                    if (index != 0) {
                        f.getFieldInfo2().addAttribute(new ConstantAttribute(cp, index));
                        init = null;
                    }
                } catch (NotFoundException cp) {
                    // empty catch block
                }
            }
        }
        this.getMembers().addField(f);
        this.getClassFile2().addField(f.getFieldInfo2());
        if (init != null) {
            FieldInitLink fil = new FieldInitLink(f, init);
            FieldInitLink link = this.fieldInitializers;
            if (link == null) {
                this.fieldInitializers = fil;
            } else {
                while (link.next != null) {
                    link = link.next;
                }
                link.next = fil;
            }
        }
    }

    @Override
    public void removeField(CtField f) throws NotFoundException {
        this.checkModify();
        FieldInfo fi = f.getFieldInfo2();
        ClassFile cf = this.getClassFile2();
        if (!cf.getFields().remove(fi)) {
            throw new NotFoundException(f.toString());
        }
        this.getMembers().remove(f);
        this.gcConstPool = true;
    }

    @Override
    public CtConstructor makeClassInitializer() throws CannotCompileException {
        CtConstructor clinit = this.getClassInitializer();
        if (clinit != null) {
            return clinit;
        }
        this.checkModify();
        ClassFile cf = this.getClassFile2();
        Bytecode code = new Bytecode(cf.getConstPool(), 0, 0);
        this.modifyClassConstructor(cf, code, 0, 0);
        return this.getClassInitializer();
    }

    @Override
    public void addConstructor(CtConstructor c) throws CannotCompileException {
        this.checkModify();
        if (c.getDeclaringClass() != this) {
            throw new CannotCompileException("cannot add");
        }
        this.getMembers().addConstructor(c);
        this.getClassFile2().addMethod(c.getMethodInfo2());
    }

    @Override
    public void removeConstructor(CtConstructor m) throws NotFoundException {
        this.checkModify();
        MethodInfo mi = m.getMethodInfo2();
        ClassFile cf = this.getClassFile2();
        if (!cf.getMethods().remove(mi)) {
            throw new NotFoundException(m.toString());
        }
        this.getMembers().remove(m);
        this.gcConstPool = true;
    }

    @Override
    public void addMethod(CtMethod m) throws CannotCompileException {
        this.checkModify();
        if (m.getDeclaringClass() != this) {
            throw new CannotCompileException("bad declaring class");
        }
        int mod = m.getModifiers();
        if ((this.getModifiers() & 0x200) != 0) {
            if (Modifier.isProtected(mod) || Modifier.isPrivate(mod)) {
                throw new CannotCompileException("an interface method must be public: " + m.toString());
            }
            m.setModifiers(mod | 1);
        }
        this.getMembers().addMethod(m);
        this.getClassFile2().addMethod(m.getMethodInfo2());
        if ((mod & 0x400) != 0) {
            this.setModifiers(this.getModifiers() | 0x400);
        }
    }

    @Override
    public void removeMethod(CtMethod m) throws NotFoundException {
        this.checkModify();
        MethodInfo mi = m.getMethodInfo2();
        ClassFile cf = this.getClassFile2();
        if (!cf.getMethods().remove(mi)) {
            throw new NotFoundException(m.toString());
        }
        this.getMembers().remove(m);
        this.gcConstPool = true;
    }

    @Override
    public byte[] getAttribute(String name) {
        AttributeInfo ai = this.getClassFile2().getAttribute(name);
        if (ai == null) {
            return null;
        }
        return ai.get();
    }

    @Override
    public void setAttribute(String name, byte[] data) {
        this.checkModify();
        ClassFile cf = this.getClassFile2();
        cf.addAttribute(new AttributeInfo(cf.getConstPool(), name, data));
    }

    @Override
    public void instrument(CodeConverter converter) throws CannotCompileException {
        this.checkModify();
        ClassFile cf = this.getClassFile2();
        ConstPool cp = cf.getConstPool();
        List<MethodInfo> methods = cf.getMethods();
        for (MethodInfo minfo : methods.toArray(new MethodInfo[methods.size()])) {
            converter.doit(this, minfo, cp);
        }
    }

    @Override
    public void instrument(ExprEditor editor) throws CannotCompileException {
        this.checkModify();
        ClassFile cf = this.getClassFile2();
        List<MethodInfo> methods = cf.getMethods();
        for (MethodInfo minfo : methods.toArray(new MethodInfo[methods.size()])) {
            editor.doit(this, minfo);
        }
    }

    @Override
    public void prune() {
        if (this.wasPruned) {
            return;
        }
        this.wasFrozen = true;
        this.wasPruned = true;
        this.getClassFile2().prune();
    }

    @Override
    public void rebuildClassFile() {
        this.gcConstPool = true;
    }

    @Override
    public void toBytecode(DataOutputStream out) throws CannotCompileException, IOException {
        try {
            if (this.isModified()) {
                this.checkPruned("toBytecode");
                ClassFile cf = this.getClassFile2();
                if (this.gcConstPool) {
                    cf.compact();
                    this.gcConstPool = false;
                }
                this.modifyClassConstructor(cf);
                this.modifyConstructors(cf);
                if (debugDump != null) {
                    this.dumpClassFile(cf);
                }
                cf.write(out);
                out.flush();
                this.fieldInitializers = null;
                if (this.doPruning) {
                    cf.prune();
                    this.wasPruned = true;
                }
            } else {
                this.classPool.writeClassfile(this.getName(), out);
            }
            this.getCount = 0;
            this.wasFrozen = true;
        } catch (NotFoundException e) {
            throw new CannotCompileException(e);
        } catch (IOException e) {
            throw new CannotCompileException(e);
        }
    }

    private void dumpClassFile(ClassFile cf) throws IOException {
        try (DataOutputStream dump = this.makeFileOutput(debugDump);){
            cf.write(dump);
        }
    }

    private void checkPruned(String method) {
        if (this.wasPruned) {
            throw new RuntimeException(method + "(): " + this.getName() + " was pruned.");
        }
    }

    @Override
    public boolean stopPruning(boolean stop) {
        boolean prev = !this.doPruning;
        this.doPruning = !stop;
        return prev;
    }

    private void modifyClassConstructor(ClassFile cf) throws CannotCompileException, NotFoundException {
        if (this.fieldInitializers == null) {
            return;
        }
        Bytecode code = new Bytecode(cf.getConstPool(), 0, 0);
        Javac jv = new Javac(code, this);
        int stacksize = 0;
        boolean doInit = false;
        FieldInitLink fi = this.fieldInitializers;
        while (fi != null) {
            CtField f = fi.field;
            if (Modifier.isStatic(f.getModifiers())) {
                doInit = true;
                int s = fi.init.compileIfStatic(f.getType(), f.getName(), code, jv);
                if (stacksize < s) {
                    stacksize = s;
                }
            }
            fi = fi.next;
        }
        if (doInit) {
            this.modifyClassConstructor(cf, code, stacksize, 0);
        }
    }

    private void modifyClassConstructor(ClassFile cf, Bytecode code, int stacksize, int localsize) throws CannotCompileException {
        MethodInfo m = cf.getStaticInitializer();
        if (m == null) {
            code.add(177);
            code.setMaxStack(stacksize);
            code.setMaxLocals(localsize);
            m = new MethodInfo(cf.getConstPool(), "<clinit>", "()V");
            m.setAccessFlags(8);
            m.setCodeAttribute(code.toCodeAttribute());
            cf.addMethod(m);
            CtMember.Cache cache = this.hasMemberCache();
            if (cache != null) {
                cache.addConstructor(new CtConstructor(m, (CtClass)this));
            }
        } else {
            CodeAttribute codeAttr = m.getCodeAttribute();
            if (codeAttr == null) {
                throw new CannotCompileException("empty <clinit>");
            }
            try {
                int maxlocals;
                CodeIterator it = codeAttr.iterator();
                int pos = it.insertEx(code.get());
                it.insert(code.getExceptionTable(), pos);
                int maxstack = codeAttr.getMaxStack();
                if (maxstack < stacksize) {
                    codeAttr.setMaxStack(stacksize);
                }
                if ((maxlocals = codeAttr.getMaxLocals()) < localsize) {
                    codeAttr.setMaxLocals(localsize);
                }
            } catch (BadBytecode e) {
                throw new CannotCompileException(e);
            }
        }
        try {
            m.rebuildStackMapIf6(this.classPool, cf);
        } catch (BadBytecode e) {
            throw new CannotCompileException(e);
        }
    }

    private void modifyConstructors(ClassFile cf) throws CannotCompileException, NotFoundException {
        if (this.fieldInitializers == null) {
            return;
        }
        ConstPool cp = cf.getConstPool();
        List<MethodInfo> methods = cf.getMethods();
        for (MethodInfo minfo : methods) {
            CodeAttribute codeAttr;
            if (!minfo.isConstructor() || (codeAttr = minfo.getCodeAttribute()) == null) continue;
            try {
                Bytecode init = new Bytecode(cp, 0, codeAttr.getMaxLocals());
                CtClass[] params = Descriptor.getParameterTypes(minfo.getDescriptor(), this.classPool);
                int stacksize = this.makeFieldInitializer(init, params);
                CtClassType.insertAuxInitializer(codeAttr, init, stacksize);
                minfo.rebuildStackMapIf6(this.classPool, cf);
            } catch (BadBytecode e) {
                throw new CannotCompileException(e);
            }
        }
    }

    private static void insertAuxInitializer(CodeAttribute codeAttr, Bytecode initializer, int stacksize) throws BadBytecode {
        CodeIterator it = codeAttr.iterator();
        int index = it.skipSuperConstructor();
        if (index < 0 && (index = it.skipThisConstructor()) >= 0) {
            return;
        }
        int pos = it.insertEx(initializer.get());
        it.insert(initializer.getExceptionTable(), pos);
        int maxstack = codeAttr.getMaxStack();
        if (maxstack < stacksize) {
            codeAttr.setMaxStack(stacksize);
        }
    }

    private int makeFieldInitializer(Bytecode code, CtClass[] parameters) throws CannotCompileException, NotFoundException {
        int stacksize = 0;
        Javac jv = new Javac(code, this);
        try {
            jv.recordParams(parameters, false);
        } catch (CompileError e) {
            throw new CannotCompileException(e);
        }
        FieldInitLink fi = this.fieldInitializers;
        while (fi != null) {
            int s;
            CtField f = fi.field;
            if (!Modifier.isStatic(f.getModifiers()) && stacksize < (s = fi.init.compile(f.getType(), f.getName(), code, parameters, jv))) {
                stacksize = s;
            }
            fi = fi.next;
        }
        return stacksize;
    }

    Map<CtMethod, String> getHiddenMethods() {
        if (this.hiddenMethods == null) {
            this.hiddenMethods = new Hashtable<CtMethod, String>();
        }
        return this.hiddenMethods;
    }

    int getUniqueNumber() {
        return this.uniqueNumberSeed++;
    }

    @Override
    public String makeUniqueName(String prefix) {
        String name;
        HashMap<Object, CtClassType> table = new HashMap<Object, CtClassType>();
        this.makeMemberList(table);
        Set keys = table.keySet();
        String[] methods = new String[keys.size()];
        keys.toArray(methods);
        if (CtClassType.notFindInArray(prefix, methods)) {
            return prefix;
        }
        int i = 100;
        do {
            if (i <= 999) continue;
            throw new RuntimeException("too many unique name");
        } while (!CtClassType.notFindInArray(name = prefix + i++, methods));
        return name;
    }

    private static boolean notFindInArray(String prefix, String[] values) {
        int len = values.length;
        for (int i = 0; i < len; ++i) {
            if (!values[i].startsWith(prefix)) continue;
            return false;
        }
        return true;
    }

    private void makeMemberList(Map<Object, CtClassType> table) {
        int mod = this.getModifiers();
        if (Modifier.isAbstract(mod) || Modifier.isInterface(mod)) {
            try {
                CtClass[] ifs = this.getInterfaces();
                for (CtClass ic : ifs) {
                    if (ic == null || !(ic instanceof CtClassType)) continue;
                    ((CtClassType)ic).makeMemberList(table);
                }
            } catch (NotFoundException ifs) {
                // empty catch block
            }
        }
        try {
            CtClass s = this.getSuperclass();
            if (s != null && s instanceof CtClassType) {
                ((CtClassType)s).makeMemberList(table);
            }
        } catch (NotFoundException s) {
            // empty catch block
        }
        List<MethodInfo> methods = this.getClassFile2().getMethods();
        for (MethodInfo minfo : methods) {
            table.put(minfo.getName(), this);
        }
        List<FieldInfo> fields = this.getClassFile2().getFields();
        for (FieldInfo finfo : fields) {
            table.put(finfo.getName(), this);
        }
    }
}

