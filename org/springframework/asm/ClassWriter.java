/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.AnnotationWriter;
import org.springframework.asm.Attribute;
import org.springframework.asm.ByteVector;
import org.springframework.asm.ClassReader;
import org.springframework.asm.ClassTooLargeException;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.FieldVisitor;
import org.springframework.asm.FieldWriter;
import org.springframework.asm.Handle;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.MethodWriter;
import org.springframework.asm.ModuleVisitor;
import org.springframework.asm.ModuleWriter;
import org.springframework.asm.RecordComponentVisitor;
import org.springframework.asm.RecordComponentWriter;
import org.springframework.asm.Symbol;
import org.springframework.asm.SymbolTable;
import org.springframework.asm.TypePath;

public class ClassWriter
extends ClassVisitor {
    public static final int COMPUTE_MAXS = 1;
    public static final int COMPUTE_FRAMES = 2;
    private int version;
    private final SymbolTable symbolTable;
    private int accessFlags;
    private int thisClass;
    private int superClass;
    private int interfaceCount;
    private int[] interfaces;
    private FieldWriter firstField;
    private FieldWriter lastField;
    private MethodWriter firstMethod;
    private MethodWriter lastMethod;
    private int numberOfInnerClasses;
    private ByteVector innerClasses;
    private int enclosingClassIndex;
    private int enclosingMethodIndex;
    private int signatureIndex;
    private int sourceFileIndex;
    private ByteVector debugExtension;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private ModuleWriter moduleWriter;
    private int nestHostClassIndex;
    private int numberOfNestMemberClasses;
    private ByteVector nestMemberClasses;
    private int numberOfPermittedSubclasses;
    private ByteVector permittedSubclasses;
    private RecordComponentWriter firstRecordComponent;
    private RecordComponentWriter lastRecordComponent;
    private Attribute firstAttribute;
    private int compute;

    public ClassWriter(int flags) {
        this(null, flags);
    }

    public ClassWriter(ClassReader classReader, int flags) {
        super(589824);
        SymbolTable symbolTable = this.symbolTable = classReader == null ? new SymbolTable(this) : new SymbolTable(this, classReader);
        this.compute = (flags & 2) != 0 ? 4 : ((flags & 1) != 0 ? 1 : 0);
    }

    @Override
    public final void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.version = version;
        this.accessFlags = access;
        this.thisClass = this.symbolTable.setMajorVersionAndClassName(version & 0xFFFF, name);
        if (signature != null) {
            this.signatureIndex = this.symbolTable.addConstantUtf8(signature);
        }
        int n = this.superClass = superName == null ? 0 : this.symbolTable.addConstantClass((String)superName).index;
        if (interfaces != null && interfaces.length > 0) {
            this.interfaceCount = interfaces.length;
            this.interfaces = new int[this.interfaceCount];
            for (int i = 0; i < this.interfaceCount; ++i) {
                this.interfaces[i] = this.symbolTable.addConstantClass((String)interfaces[i]).index;
            }
        }
        if (this.compute == 1 && (version & 0xFFFF) >= 51) {
            this.compute = 2;
        }
    }

    @Override
    public final void visitSource(String file, String debug) {
        if (file != null) {
            this.sourceFileIndex = this.symbolTable.addConstantUtf8(file);
        }
        if (debug != null) {
            this.debugExtension = new ByteVector().encodeUtf8(debug, 0, Integer.MAX_VALUE);
        }
    }

    @Override
    public final ModuleVisitor visitModule(String name, int access, String version) {
        this.moduleWriter = new ModuleWriter(this.symbolTable, this.symbolTable.addConstantModule((String)name).index, access, version == null ? 0 : this.symbolTable.addConstantUtf8(version));
        return this.moduleWriter;
    }

    @Override
    public final void visitNestHost(String nestHost) {
        this.nestHostClassIndex = this.symbolTable.addConstantClass((String)nestHost).index;
    }

    @Override
    public final void visitOuterClass(String owner, String name, String descriptor) {
        this.enclosingClassIndex = this.symbolTable.addConstantClass((String)owner).index;
        if (name != null && descriptor != null) {
            this.enclosingMethodIndex = this.symbolTable.addConstantNameAndType(name, descriptor);
        }
    }

    @Override
    public final AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (visible) {
            this.lastRuntimeVisibleAnnotation = AnnotationWriter.create(this.symbolTable, descriptor, this.lastRuntimeVisibleAnnotation);
            return this.lastRuntimeVisibleAnnotation;
        }
        this.lastRuntimeInvisibleAnnotation = AnnotationWriter.create(this.symbolTable, descriptor, this.lastRuntimeInvisibleAnnotation);
        return this.lastRuntimeInvisibleAnnotation;
    }

    @Override
    public final AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        if (visible) {
            this.lastRuntimeVisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, typeRef, typePath, descriptor, this.lastRuntimeVisibleTypeAnnotation);
            return this.lastRuntimeVisibleTypeAnnotation;
        }
        this.lastRuntimeInvisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, typeRef, typePath, descriptor, this.lastRuntimeInvisibleTypeAnnotation);
        return this.lastRuntimeInvisibleTypeAnnotation;
    }

    @Override
    public final void visitAttribute(Attribute attribute) {
        attribute.nextAttribute = this.firstAttribute;
        this.firstAttribute = attribute;
    }

    @Override
    public final void visitNestMember(String nestMember) {
        if (this.nestMemberClasses == null) {
            this.nestMemberClasses = new ByteVector();
        }
        ++this.numberOfNestMemberClasses;
        this.nestMemberClasses.putShort(this.symbolTable.addConstantClass((String)nestMember).index);
    }

    @Override
    public final void visitPermittedSubclass(String permittedSubclass) {
        if (this.permittedSubclasses == null) {
            this.permittedSubclasses = new ByteVector();
        }
        ++this.numberOfPermittedSubclasses;
        this.permittedSubclasses.putShort(this.symbolTable.addConstantClass((String)permittedSubclass).index);
    }

    @Override
    public final void visitInnerClass(String name, String outerName, String innerName, int access) {
        if (this.innerClasses == null) {
            this.innerClasses = new ByteVector();
        }
        Symbol nameSymbol = this.symbolTable.addConstantClass(name);
        if (nameSymbol.info == 0) {
            ++this.numberOfInnerClasses;
            this.innerClasses.putShort(nameSymbol.index);
            this.innerClasses.putShort(outerName == null ? 0 : this.symbolTable.addConstantClass((String)outerName).index);
            this.innerClasses.putShort(innerName == null ? 0 : this.symbolTable.addConstantUtf8(innerName));
            this.innerClasses.putShort(access);
            nameSymbol.info = this.numberOfInnerClasses;
        }
    }

    @Override
    public final RecordComponentVisitor visitRecordComponent(String name, String descriptor, String signature) {
        RecordComponentWriter recordComponentWriter = new RecordComponentWriter(this.symbolTable, name, descriptor, signature);
        if (this.firstRecordComponent == null) {
            this.firstRecordComponent = recordComponentWriter;
        } else {
            this.lastRecordComponent.delegate = recordComponentWriter;
        }
        this.lastRecordComponent = recordComponentWriter;
        return this.lastRecordComponent;
    }

    @Override
    public final FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        FieldWriter fieldWriter = new FieldWriter(this.symbolTable, access, name, descriptor, signature, value);
        if (this.firstField == null) {
            this.firstField = fieldWriter;
        } else {
            this.lastField.fv = fieldWriter;
        }
        this.lastField = fieldWriter;
        return this.lastField;
    }

    @Override
    public final MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodWriter methodWriter = new MethodWriter(this.symbolTable, access, name, descriptor, signature, exceptions, this.compute);
        if (this.firstMethod == null) {
            this.firstMethod = methodWriter;
        } else {
            this.lastMethod.mv = methodWriter;
        }
        this.lastMethod = methodWriter;
        return this.lastMethod;
    }

    @Override
    public final void visitEnd() {
    }

    public byte[] toByteArray() {
        int size = 24 + 2 * this.interfaceCount;
        int fieldsCount = 0;
        FieldWriter fieldWriter = this.firstField;
        while (fieldWriter != null) {
            ++fieldsCount;
            size += fieldWriter.computeFieldInfoSize();
            fieldWriter = (FieldWriter)fieldWriter.fv;
        }
        int methodsCount = 0;
        MethodWriter methodWriter = this.firstMethod;
        while (methodWriter != null) {
            ++methodsCount;
            size += methodWriter.computeMethodInfoSize();
            methodWriter = (MethodWriter)methodWriter.mv;
        }
        int attributesCount = 0;
        if (this.innerClasses != null) {
            ++attributesCount;
            size += 8 + this.innerClasses.length;
            this.symbolTable.addConstantUtf8("InnerClasses");
        }
        if (this.enclosingClassIndex != 0) {
            ++attributesCount;
            size += 10;
            this.symbolTable.addConstantUtf8("EnclosingMethod");
        }
        if ((this.accessFlags & 0x1000) != 0 && (this.version & 0xFFFF) < 49) {
            ++attributesCount;
            size += 6;
            this.symbolTable.addConstantUtf8("Synthetic");
        }
        if (this.signatureIndex != 0) {
            ++attributesCount;
            size += 8;
            this.symbolTable.addConstantUtf8("Signature");
        }
        if (this.sourceFileIndex != 0) {
            ++attributesCount;
            size += 8;
            this.symbolTable.addConstantUtf8("SourceFile");
        }
        if (this.debugExtension != null) {
            ++attributesCount;
            size += 6 + this.debugExtension.length;
            this.symbolTable.addConstantUtf8("SourceDebugExtension");
        }
        if ((this.accessFlags & 0x20000) != 0) {
            ++attributesCount;
            size += 6;
            this.symbolTable.addConstantUtf8("Deprecated");
        }
        if (this.lastRuntimeVisibleAnnotation != null) {
            ++attributesCount;
            size += this.lastRuntimeVisibleAnnotation.computeAnnotationsSize("RuntimeVisibleAnnotations");
        }
        if (this.lastRuntimeInvisibleAnnotation != null) {
            ++attributesCount;
            size += this.lastRuntimeInvisibleAnnotation.computeAnnotationsSize("RuntimeInvisibleAnnotations");
        }
        if (this.lastRuntimeVisibleTypeAnnotation != null) {
            ++attributesCount;
            size += this.lastRuntimeVisibleTypeAnnotation.computeAnnotationsSize("RuntimeVisibleTypeAnnotations");
        }
        if (this.lastRuntimeInvisibleTypeAnnotation != null) {
            ++attributesCount;
            size += this.lastRuntimeInvisibleTypeAnnotation.computeAnnotationsSize("RuntimeInvisibleTypeAnnotations");
        }
        if (this.symbolTable.computeBootstrapMethodsSize() > 0) {
            ++attributesCount;
            size += this.symbolTable.computeBootstrapMethodsSize();
        }
        if (this.moduleWriter != null) {
            attributesCount += this.moduleWriter.getAttributeCount();
            size += this.moduleWriter.computeAttributesSize();
        }
        if (this.nestHostClassIndex != 0) {
            ++attributesCount;
            size += 8;
            this.symbolTable.addConstantUtf8("NestHost");
        }
        if (this.nestMemberClasses != null) {
            ++attributesCount;
            size += 8 + this.nestMemberClasses.length;
            this.symbolTable.addConstantUtf8("NestMembers");
        }
        if (this.permittedSubclasses != null) {
            ++attributesCount;
            size += 8 + this.permittedSubclasses.length;
            this.symbolTable.addConstantUtf8("PermittedSubclasses");
        }
        int recordComponentCount = 0;
        int recordSize = 0;
        if ((this.accessFlags & 0x10000) != 0 || this.firstRecordComponent != null) {
            RecordComponentWriter recordComponentWriter = this.firstRecordComponent;
            while (recordComponentWriter != null) {
                ++recordComponentCount;
                recordSize += recordComponentWriter.computeRecordComponentInfoSize();
                recordComponentWriter = (RecordComponentWriter)recordComponentWriter.delegate;
            }
            ++attributesCount;
            size += 8 + recordSize;
            this.symbolTable.addConstantUtf8("Record");
        }
        if (this.firstAttribute != null) {
            attributesCount += this.firstAttribute.getAttributeCount();
            size += this.firstAttribute.computeAttributesSize(this.symbolTable);
        }
        size += this.symbolTable.getConstantPoolLength();
        int constantPoolCount = this.symbolTable.getConstantPoolCount();
        if (constantPoolCount > 65535) {
            throw new ClassTooLargeException(this.symbolTable.getClassName(), constantPoolCount);
        }
        ByteVector result = new ByteVector(size);
        result.putInt(-889275714).putInt(this.version);
        this.symbolTable.putConstantPool(result);
        int mask = (this.version & 0xFFFF) < 49 ? 4096 : 0;
        result.putShort(this.accessFlags & ~mask).putShort(this.thisClass).putShort(this.superClass);
        result.putShort(this.interfaceCount);
        for (int i = 0; i < this.interfaceCount; ++i) {
            result.putShort(this.interfaces[i]);
        }
        result.putShort(fieldsCount);
        fieldWriter = this.firstField;
        while (fieldWriter != null) {
            fieldWriter.putFieldInfo(result);
            fieldWriter = (FieldWriter)fieldWriter.fv;
        }
        result.putShort(methodsCount);
        boolean hasFrames = false;
        boolean hasAsmInstructions = false;
        methodWriter = this.firstMethod;
        while (methodWriter != null) {
            hasFrames |= methodWriter.hasFrames();
            hasAsmInstructions |= methodWriter.hasAsmInstructions();
            methodWriter.putMethodInfo(result);
            methodWriter = (MethodWriter)methodWriter.mv;
        }
        result.putShort(attributesCount);
        if (this.innerClasses != null) {
            result.putShort(this.symbolTable.addConstantUtf8("InnerClasses")).putInt(this.innerClasses.length + 2).putShort(this.numberOfInnerClasses).putByteArray(this.innerClasses.data, 0, this.innerClasses.length);
        }
        if (this.enclosingClassIndex != 0) {
            result.putShort(this.symbolTable.addConstantUtf8("EnclosingMethod")).putInt(4).putShort(this.enclosingClassIndex).putShort(this.enclosingMethodIndex);
        }
        if ((this.accessFlags & 0x1000) != 0 && (this.version & 0xFFFF) < 49) {
            result.putShort(this.symbolTable.addConstantUtf8("Synthetic")).putInt(0);
        }
        if (this.signatureIndex != 0) {
            result.putShort(this.symbolTable.addConstantUtf8("Signature")).putInt(2).putShort(this.signatureIndex);
        }
        if (this.sourceFileIndex != 0) {
            result.putShort(this.symbolTable.addConstantUtf8("SourceFile")).putInt(2).putShort(this.sourceFileIndex);
        }
        if (this.debugExtension != null) {
            int length = this.debugExtension.length;
            result.putShort(this.symbolTable.addConstantUtf8("SourceDebugExtension")).putInt(length).putByteArray(this.debugExtension.data, 0, length);
        }
        if ((this.accessFlags & 0x20000) != 0) {
            result.putShort(this.symbolTable.addConstantUtf8("Deprecated")).putInt(0);
        }
        AnnotationWriter.putAnnotations(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, result);
        this.symbolTable.putBootstrapMethods(result);
        if (this.moduleWriter != null) {
            this.moduleWriter.putAttributes(result);
        }
        if (this.nestHostClassIndex != 0) {
            result.putShort(this.symbolTable.addConstantUtf8("NestHost")).putInt(2).putShort(this.nestHostClassIndex);
        }
        if (this.nestMemberClasses != null) {
            result.putShort(this.symbolTable.addConstantUtf8("NestMembers")).putInt(this.nestMemberClasses.length + 2).putShort(this.numberOfNestMemberClasses).putByteArray(this.nestMemberClasses.data, 0, this.nestMemberClasses.length);
        }
        if (this.permittedSubclasses != null) {
            result.putShort(this.symbolTable.addConstantUtf8("PermittedSubclasses")).putInt(this.permittedSubclasses.length + 2).putShort(this.numberOfPermittedSubclasses).putByteArray(this.permittedSubclasses.data, 0, this.permittedSubclasses.length);
        }
        if ((this.accessFlags & 0x10000) != 0 || this.firstRecordComponent != null) {
            result.putShort(this.symbolTable.addConstantUtf8("Record")).putInt(recordSize + 2).putShort(recordComponentCount);
            RecordComponentWriter recordComponentWriter = this.firstRecordComponent;
            while (recordComponentWriter != null) {
                recordComponentWriter.putRecordComponentInfo(result);
                recordComponentWriter = (RecordComponentWriter)recordComponentWriter.delegate;
            }
        }
        if (this.firstAttribute != null) {
            this.firstAttribute.putAttributes(this.symbolTable, result);
        }
        if (hasAsmInstructions) {
            return this.replaceAsmInstructions(result.data, hasFrames);
        }
        return result.data;
    }

    private byte[] replaceAsmInstructions(byte[] classFile, boolean hasFrames) {
        Attribute[] attributes = this.getAttributePrototypes();
        this.firstField = null;
        this.lastField = null;
        this.firstMethod = null;
        this.lastMethod = null;
        this.lastRuntimeVisibleAnnotation = null;
        this.lastRuntimeInvisibleAnnotation = null;
        this.lastRuntimeVisibleTypeAnnotation = null;
        this.lastRuntimeInvisibleTypeAnnotation = null;
        this.moduleWriter = null;
        this.nestHostClassIndex = 0;
        this.numberOfNestMemberClasses = 0;
        this.nestMemberClasses = null;
        this.numberOfPermittedSubclasses = 0;
        this.permittedSubclasses = null;
        this.firstRecordComponent = null;
        this.lastRecordComponent = null;
        this.firstAttribute = null;
        this.compute = hasFrames ? 3 : 0;
        new ClassReader(classFile, 0, false).accept(this, attributes, (hasFrames ? 8 : 0) | 0x100);
        return this.toByteArray();
    }

    private Attribute[] getAttributePrototypes() {
        Attribute.Set attributePrototypes = new Attribute.Set();
        attributePrototypes.addAttributes(this.firstAttribute);
        FieldWriter fieldWriter = this.firstField;
        while (fieldWriter != null) {
            fieldWriter.collectAttributePrototypes(attributePrototypes);
            fieldWriter = (FieldWriter)fieldWriter.fv;
        }
        MethodWriter methodWriter = this.firstMethod;
        while (methodWriter != null) {
            methodWriter.collectAttributePrototypes(attributePrototypes);
            methodWriter = (MethodWriter)methodWriter.mv;
        }
        RecordComponentWriter recordComponentWriter = this.firstRecordComponent;
        while (recordComponentWriter != null) {
            recordComponentWriter.collectAttributePrototypes(attributePrototypes);
            recordComponentWriter = (RecordComponentWriter)recordComponentWriter.delegate;
        }
        return attributePrototypes.toArray();
    }

    public int newConst(Object value) {
        return this.symbolTable.addConstant((Object)value).index;
    }

    public int newUTF8(String value) {
        return this.symbolTable.addConstantUtf8(value);
    }

    public int newClass(String value) {
        return this.symbolTable.addConstantClass((String)value).index;
    }

    public int newMethodType(String methodDescriptor) {
        return this.symbolTable.addConstantMethodType((String)methodDescriptor).index;
    }

    public int newModule(String moduleName) {
        return this.symbolTable.addConstantModule((String)moduleName).index;
    }

    public int newPackage(String packageName) {
        return this.symbolTable.addConstantPackage((String)packageName).index;
    }

    @Deprecated
    public int newHandle(int tag, String owner, String name, String descriptor) {
        return this.newHandle(tag, owner, name, descriptor, tag == 9);
    }

    public int newHandle(int tag, String owner, String name, String descriptor, boolean isInterface) {
        return this.symbolTable.addConstantMethodHandle((int)tag, (String)owner, (String)name, (String)descriptor, (boolean)isInterface).index;
    }

    public int newConstantDynamic(String name, String descriptor, Handle bootstrapMethodHandle, Object ... bootstrapMethodArguments) {
        return this.symbolTable.addConstantDynamic((String)name, (String)descriptor, (Handle)bootstrapMethodHandle, (Object[])bootstrapMethodArguments).index;
    }

    public int newInvokeDynamic(String name, String descriptor, Handle bootstrapMethodHandle, Object ... bootstrapMethodArguments) {
        return this.symbolTable.addConstantInvokeDynamic((String)name, (String)descriptor, (Handle)bootstrapMethodHandle, (Object[])bootstrapMethodArguments).index;
    }

    public int newField(String owner, String name, String descriptor) {
        return this.symbolTable.addConstantFieldref((String)owner, (String)name, (String)descriptor).index;
    }

    public int newMethod(String owner, String name, String descriptor, boolean isInterface) {
        return this.symbolTable.addConstantMethodref((String)owner, (String)name, (String)descriptor, (boolean)isInterface).index;
    }

    public int newNameType(String name, String descriptor) {
        return this.symbolTable.addConstantNameAndType(name, descriptor);
    }

    protected String getCommonSuperClass(String type1, String type2) {
        Class<?> class2;
        Class<?> class1;
        ClassLoader classLoader = this.getClassLoader();
        try {
            class1 = Class.forName(type1.replace('/', '.'), false, classLoader);
        } catch (ClassNotFoundException e) {
            throw new TypeNotPresentException(type1, e);
        }
        try {
            class2 = Class.forName(type2.replace('/', '.'), false, classLoader);
        } catch (ClassNotFoundException e) {
            throw new TypeNotPresentException(type2, e);
        }
        if (class1.isAssignableFrom(class2)) {
            return type1;
        }
        if (class2.isAssignableFrom(class1)) {
            return type2;
        }
        if (class1.isInterface() || class2.isInterface()) {
            return "java/lang/Object";
        }
        while (!(class1 = class1.getSuperclass()).isAssignableFrom(class2)) {
        }
        return class1.getName().replace('.', '/');
    }

    protected ClassLoader getClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable throwable) {
            // empty catch block
        }
        return classLoader != null ? classLoader : this.getClass().getClassLoader();
    }
}

