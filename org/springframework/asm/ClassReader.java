/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Attribute;
import org.springframework.asm.ClassVisitor;
import org.springframework.asm.ConstantDynamic;
import org.springframework.asm.Context;
import org.springframework.asm.FieldVisitor;
import org.springframework.asm.Handle;
import org.springframework.asm.Label;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.MethodWriter;
import org.springframework.asm.ModuleVisitor;
import org.springframework.asm.Opcodes;
import org.springframework.asm.RecordComponentVisitor;
import org.springframework.asm.Type;
import org.springframework.asm.TypePath;

public class ClassReader {
    public static final int SKIP_CODE = 1;
    public static final int SKIP_DEBUG = 2;
    public static final int SKIP_FRAMES = 4;
    public static final int EXPAND_FRAMES = 8;
    static final int EXPAND_ASM_INSNS = 256;
    private static final int MAX_BUFFER_SIZE = 0x100000;
    private static final int INPUT_STREAM_DATA_CHUNK_SIZE = 4096;
    @Deprecated
    public final byte[] b;
    public final int header;
    final byte[] classFileBuffer;
    private final int[] cpInfoOffsets;
    private final String[] constantUtf8Values;
    private final ConstantDynamic[] constantDynamicValues;
    private final int[] bootstrapMethodOffsets;
    private final int maxStringLength;

    public ClassReader(byte[] classFile) {
        this(classFile, 0, classFile.length);
    }

    public ClassReader(byte[] classFileBuffer, int classFileOffset, int classFileLength) {
        this(classFileBuffer, classFileOffset, true);
    }

    ClassReader(byte[] classFileBuffer, int classFileOffset, boolean checkClassVersion) {
        this.classFileBuffer = classFileBuffer;
        this.b = classFileBuffer;
        if (checkClassVersion && this.readShort(classFileOffset + 6) > 62) {
            throw new IllegalArgumentException("Unsupported class file major version " + this.readShort(classFileOffset + 6));
        }
        int constantPoolCount = this.readUnsignedShort(classFileOffset + 8);
        this.cpInfoOffsets = new int[constantPoolCount];
        this.constantUtf8Values = new String[constantPoolCount];
        int currentCpInfoIndex = 1;
        int currentCpInfoOffset = classFileOffset + 10;
        int currentMaxStringLength = 0;
        boolean hasBootstrapMethods = false;
        boolean hasConstantDynamic = false;
        while (currentCpInfoIndex < constantPoolCount) {
            int cpInfoSize;
            this.cpInfoOffsets[currentCpInfoIndex++] = currentCpInfoOffset + 1;
            switch (classFileBuffer[currentCpInfoOffset]) {
                case 3: 
                case 4: 
                case 9: 
                case 10: 
                case 11: 
                case 12: {
                    cpInfoSize = 5;
                    break;
                }
                case 17: {
                    cpInfoSize = 5;
                    hasBootstrapMethods = true;
                    hasConstantDynamic = true;
                    break;
                }
                case 18: {
                    cpInfoSize = 5;
                    hasBootstrapMethods = true;
                    break;
                }
                case 5: 
                case 6: {
                    cpInfoSize = 9;
                    ++currentCpInfoIndex;
                    break;
                }
                case 1: {
                    cpInfoSize = 3 + this.readUnsignedShort(currentCpInfoOffset + 1);
                    if (cpInfoSize <= currentMaxStringLength) break;
                    currentMaxStringLength = cpInfoSize;
                    break;
                }
                case 15: {
                    cpInfoSize = 4;
                    break;
                }
                case 7: 
                case 8: 
                case 16: 
                case 19: 
                case 20: {
                    cpInfoSize = 3;
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
            currentCpInfoOffset += cpInfoSize;
        }
        this.maxStringLength = currentMaxStringLength;
        this.header = currentCpInfoOffset;
        this.constantDynamicValues = hasConstantDynamic ? new ConstantDynamic[constantPoolCount] : null;
        this.bootstrapMethodOffsets = hasBootstrapMethods ? this.readBootstrapMethodsAttribute(currentMaxStringLength) : null;
    }

    public ClassReader(InputStream inputStream) throws IOException {
        this(ClassReader.readStream(inputStream, false));
    }

    public ClassReader(String className) throws IOException {
        this(ClassReader.readStream(ClassLoader.getSystemResourceAsStream(className.replace('.', '/') + ".class"), true));
    }

    /*
     * Loose catch block
     */
    private static byte[] readStream(InputStream inputStream, boolean close) throws IOException {
        if (inputStream == null) {
            throw new IOException("Class not found");
        }
        int bufferSize = ClassReader.calculateBufferSize(inputStream);
        try {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();){
                int bytesRead;
                byte[] data = new byte[bufferSize];
                int readCount = 0;
                while ((bytesRead = inputStream.read(data, 0, bufferSize)) != -1) {
                    outputStream.write(data, 0, bytesRead);
                    ++readCount;
                }
                outputStream.flush();
                if (readCount == 1) {
                    // empty if block
                }
                byte[] byArray = outputStream.toByteArray();
                return byArray;
            }
            {
                catch (Throwable throwable) {
                    throw throwable;
                }
            }
        } finally {
            if (close) {
                inputStream.close();
            }
        }
    }

    private static int calculateBufferSize(InputStream inputStream) throws IOException {
        int expectedLength = inputStream.available();
        if (expectedLength < 256) {
            return 4096;
        }
        return Math.min(expectedLength, 0x100000);
    }

    public int getAccess() {
        return this.readUnsignedShort(this.header);
    }

    public String getClassName() {
        return this.readClass(this.header + 2, new char[this.maxStringLength]);
    }

    public String getSuperName() {
        return this.readClass(this.header + 4, new char[this.maxStringLength]);
    }

    public String[] getInterfaces() {
        int currentOffset = this.header + 6;
        int interfacesCount = this.readUnsignedShort(currentOffset);
        String[] interfaces = new String[interfacesCount];
        if (interfacesCount > 0) {
            char[] charBuffer = new char[this.maxStringLength];
            for (int i = 0; i < interfacesCount; ++i) {
                interfaces[i] = this.readClass(currentOffset += 2, charBuffer);
            }
        }
        return interfaces;
    }

    public void accept(ClassVisitor classVisitor, int parsingOptions) {
        this.accept(classVisitor, new Attribute[0], parsingOptions);
    }

    public void accept(ClassVisitor classVisitor, Attribute[] attributePrototypes, int parsingOptions) {
        int numAnnotations;
        Context context = new Context();
        context.attributePrototypes = attributePrototypes;
        context.parsingOptions = parsingOptions;
        char[] charBuffer = context.charBuffer = new char[this.maxStringLength];
        int currentOffset = this.header;
        int accessFlags = this.readUnsignedShort(currentOffset);
        String thisClass = this.readClass(currentOffset + 2, charBuffer);
        String superClass = this.readClass(currentOffset + 4, charBuffer);
        String[] interfaces = new String[this.readUnsignedShort(currentOffset + 6)];
        currentOffset += 8;
        for (int i = 0; i < interfaces.length; ++i) {
            interfaces[i] = this.readClass(currentOffset, charBuffer);
            currentOffset += 2;
        }
        int innerClassesOffset = 0;
        int enclosingMethodOffset = 0;
        String signature = null;
        String sourceFile = null;
        String sourceDebugExtension = null;
        int runtimeVisibleAnnotationsOffset = 0;
        int runtimeInvisibleAnnotationsOffset = 0;
        int runtimeVisibleTypeAnnotationsOffset = 0;
        int runtimeInvisibleTypeAnnotationsOffset = 0;
        int moduleOffset = 0;
        int modulePackagesOffset = 0;
        String moduleMainClass = null;
        String nestHostClass = null;
        int nestMembersOffset = 0;
        int permittedSubclassesOffset = 0;
        int recordOffset = 0;
        Attribute attributes = null;
        int currentAttributeOffset = this.getFirstAttributeOffset();
        for (int i = this.readUnsignedShort(currentAttributeOffset - 2); i > 0; --i) {
            String attributeName = this.readUTF8(currentAttributeOffset, charBuffer);
            int attributeLength = this.readInt(currentAttributeOffset + 2);
            currentAttributeOffset += 6;
            if ("SourceFile".equals(attributeName)) {
                sourceFile = this.readUTF8(currentAttributeOffset, charBuffer);
            } else if ("InnerClasses".equals(attributeName)) {
                innerClassesOffset = currentAttributeOffset;
            } else if ("EnclosingMethod".equals(attributeName)) {
                enclosingMethodOffset = currentAttributeOffset;
            } else if ("NestHost".equals(attributeName)) {
                nestHostClass = this.readClass(currentAttributeOffset, charBuffer);
            } else if ("NestMembers".equals(attributeName)) {
                nestMembersOffset = currentAttributeOffset;
            } else if ("PermittedSubclasses".equals(attributeName)) {
                permittedSubclassesOffset = currentAttributeOffset;
            } else if ("Signature".equals(attributeName)) {
                signature = this.readUTF8(currentAttributeOffset, charBuffer);
            } else if ("RuntimeVisibleAnnotations".equals(attributeName)) {
                runtimeVisibleAnnotationsOffset = currentAttributeOffset;
            } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
                runtimeVisibleTypeAnnotationsOffset = currentAttributeOffset;
            } else if ("Deprecated".equals(attributeName)) {
                accessFlags |= 0x20000;
            } else if ("Synthetic".equals(attributeName)) {
                accessFlags |= 0x1000;
            } else if ("SourceDebugExtension".equals(attributeName)) {
                if (attributeLength > this.classFileBuffer.length - currentAttributeOffset) {
                    throw new IllegalArgumentException();
                }
                sourceDebugExtension = this.readUtf(currentAttributeOffset, attributeLength, new char[attributeLength]);
            } else if ("RuntimeInvisibleAnnotations".equals(attributeName)) {
                runtimeInvisibleAnnotationsOffset = currentAttributeOffset;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
                runtimeInvisibleTypeAnnotationsOffset = currentAttributeOffset;
            } else if ("Record".equals(attributeName)) {
                recordOffset = currentAttributeOffset;
                accessFlags |= 0x10000;
            } else if ("Module".equals(attributeName)) {
                moduleOffset = currentAttributeOffset;
            } else if ("ModuleMainClass".equals(attributeName)) {
                moduleMainClass = this.readClass(currentAttributeOffset, charBuffer);
            } else if ("ModulePackages".equals(attributeName)) {
                modulePackagesOffset = currentAttributeOffset;
            } else if (!"BootstrapMethods".equals(attributeName)) {
                Attribute attribute = this.readAttribute(attributePrototypes, attributeName, currentAttributeOffset, attributeLength, charBuffer, -1, null);
                attribute.nextAttribute = attributes;
                attributes = attribute;
            }
            currentAttributeOffset += attributeLength;
        }
        classVisitor.visit(this.readInt(this.cpInfoOffsets[1] - 7), accessFlags, thisClass, signature, superClass, interfaces);
        if ((parsingOptions & 2) == 0 && (sourceFile != null || sourceDebugExtension != null)) {
            classVisitor.visitSource(sourceFile, sourceDebugExtension);
        }
        if (moduleOffset != 0) {
            this.readModuleAttributes(classVisitor, context, moduleOffset, modulePackagesOffset, moduleMainClass);
        }
        if (nestHostClass != null) {
            classVisitor.visitNestHost(nestHostClass);
        }
        if (enclosingMethodOffset != 0) {
            String className = this.readClass(enclosingMethodOffset, charBuffer);
            int methodIndex = this.readUnsignedShort(enclosingMethodOffset + 2);
            String name = methodIndex == 0 ? null : this.readUTF8(this.cpInfoOffsets[methodIndex], charBuffer);
            String type = methodIndex == 0 ? null : this.readUTF8(this.cpInfoOffsets[methodIndex] + 2, charBuffer);
            classVisitor.visitOuterClass(className, name, type);
        }
        if (runtimeVisibleAnnotationsOffset != 0) {
            numAnnotations = this.readUnsignedShort(runtimeVisibleAnnotationsOffset);
            int currentAnnotationOffset = runtimeVisibleAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                String annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(classVisitor.visitAnnotation(annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
            }
        }
        if (runtimeInvisibleAnnotationsOffset != 0) {
            numAnnotations = this.readUnsignedShort(runtimeInvisibleAnnotationsOffset);
            int currentAnnotationOffset = runtimeInvisibleAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                String annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(classVisitor.visitAnnotation(annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
            }
        }
        if (runtimeVisibleTypeAnnotationsOffset != 0) {
            numAnnotations = this.readUnsignedShort(runtimeVisibleTypeAnnotationsOffset);
            int currentAnnotationOffset = runtimeVisibleTypeAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                currentAnnotationOffset = this.readTypeAnnotationTarget(context, currentAnnotationOffset);
                String annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(classVisitor.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
            }
        }
        if (runtimeInvisibleTypeAnnotationsOffset != 0) {
            numAnnotations = this.readUnsignedShort(runtimeInvisibleTypeAnnotationsOffset);
            int currentAnnotationOffset = runtimeInvisibleTypeAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                currentAnnotationOffset = this.readTypeAnnotationTarget(context, currentAnnotationOffset);
                String annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(classVisitor.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
            }
        }
        while (attributes != null) {
            Attribute nextAttribute = attributes.nextAttribute;
            attributes.nextAttribute = null;
            classVisitor.visitAttribute(attributes);
            attributes = nextAttribute;
        }
        if (nestMembersOffset != 0) {
            int numberOfNestMembers = this.readUnsignedShort(nestMembersOffset);
            int currentNestMemberOffset = nestMembersOffset + 2;
            while (numberOfNestMembers-- > 0) {
                classVisitor.visitNestMember(this.readClass(currentNestMemberOffset, charBuffer));
                currentNestMemberOffset += 2;
            }
        }
        if (permittedSubclassesOffset != 0) {
            int numberOfPermittedSubclasses = this.readUnsignedShort(permittedSubclassesOffset);
            int currentPermittedSubclassesOffset = permittedSubclassesOffset + 2;
            while (numberOfPermittedSubclasses-- > 0) {
                classVisitor.visitPermittedSubclass(this.readClass(currentPermittedSubclassesOffset, charBuffer));
                currentPermittedSubclassesOffset += 2;
            }
        }
        if (innerClassesOffset != 0) {
            int numberOfClasses = this.readUnsignedShort(innerClassesOffset);
            int currentClassesOffset = innerClassesOffset + 2;
            while (numberOfClasses-- > 0) {
                classVisitor.visitInnerClass(this.readClass(currentClassesOffset, charBuffer), this.readClass(currentClassesOffset + 2, charBuffer), this.readUTF8(currentClassesOffset + 4, charBuffer), this.readUnsignedShort(currentClassesOffset + 6));
                currentClassesOffset += 8;
            }
        }
        if (recordOffset != 0) {
            int recordComponentsCount = this.readUnsignedShort(recordOffset);
            recordOffset += 2;
            while (recordComponentsCount-- > 0) {
                recordOffset = this.readRecordComponent(classVisitor, context, recordOffset);
            }
        }
        int fieldsCount = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        while (fieldsCount-- > 0) {
            currentOffset = this.readField(classVisitor, context, currentOffset);
        }
        int methodsCount = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        while (methodsCount-- > 0) {
            currentOffset = this.readMethod(classVisitor, context, currentOffset);
        }
        classVisitor.visitEnd();
    }

    private void readModuleAttributes(ClassVisitor classVisitor, Context context, int moduleOffset, int modulePackagesOffset, String moduleMainClass) {
        char[] buffer = context.charBuffer;
        int currentOffset = moduleOffset;
        String moduleName = this.readModule(currentOffset, buffer);
        int moduleFlags = this.readUnsignedShort(currentOffset + 2);
        String moduleVersion = this.readUTF8(currentOffset + 4, buffer);
        currentOffset += 6;
        ModuleVisitor moduleVisitor = classVisitor.visitModule(moduleName, moduleFlags, moduleVersion);
        if (moduleVisitor == null) {
            return;
        }
        if (moduleMainClass != null) {
            moduleVisitor.visitMainClass(moduleMainClass);
        }
        if (modulePackagesOffset != 0) {
            int packageCount = this.readUnsignedShort(modulePackagesOffset);
            int currentPackageOffset = modulePackagesOffset + 2;
            while (packageCount-- > 0) {
                moduleVisitor.visitPackage(this.readPackage(currentPackageOffset, buffer));
                currentPackageOffset += 2;
            }
        }
        int requiresCount = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        while (requiresCount-- > 0) {
            String requires = this.readModule(currentOffset, buffer);
            int requiresFlags = this.readUnsignedShort(currentOffset + 2);
            String requiresVersion = this.readUTF8(currentOffset + 4, buffer);
            currentOffset += 6;
            moduleVisitor.visitRequire(requires, requiresFlags, requiresVersion);
        }
        int exportsCount = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        while (exportsCount-- > 0) {
            String exports = this.readPackage(currentOffset, buffer);
            int exportsFlags = this.readUnsignedShort(currentOffset + 2);
            int exportsToCount = this.readUnsignedShort(currentOffset + 4);
            currentOffset += 6;
            String[] exportsTo = null;
            if (exportsToCount != 0) {
                exportsTo = new String[exportsToCount];
                for (int i = 0; i < exportsToCount; ++i) {
                    exportsTo[i] = this.readModule(currentOffset, buffer);
                    currentOffset += 2;
                }
            }
            moduleVisitor.visitExport(exports, exportsFlags, exportsTo);
        }
        int opensCount = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        while (opensCount-- > 0) {
            String opens = this.readPackage(currentOffset, buffer);
            int opensFlags = this.readUnsignedShort(currentOffset + 2);
            int opensToCount = this.readUnsignedShort(currentOffset + 4);
            currentOffset += 6;
            String[] opensTo = null;
            if (opensToCount != 0) {
                opensTo = new String[opensToCount];
                for (int i = 0; i < opensToCount; ++i) {
                    opensTo[i] = this.readModule(currentOffset, buffer);
                    currentOffset += 2;
                }
            }
            moduleVisitor.visitOpen(opens, opensFlags, opensTo);
        }
        int usesCount = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        while (usesCount-- > 0) {
            moduleVisitor.visitUse(this.readClass(currentOffset, buffer));
            currentOffset += 2;
        }
        int providesCount = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        while (providesCount-- > 0) {
            String provides = this.readClass(currentOffset, buffer);
            int providesWithCount = this.readUnsignedShort(currentOffset + 2);
            currentOffset += 4;
            String[] providesWith = new String[providesWithCount];
            for (int i = 0; i < providesWithCount; ++i) {
                providesWith[i] = this.readClass(currentOffset, buffer);
                currentOffset += 2;
            }
            moduleVisitor.visitProvide(provides, providesWith);
        }
        moduleVisitor.visitEnd();
    }

    private int readRecordComponent(ClassVisitor classVisitor, Context context, int recordComponentOffset) {
        String annotationDescriptor;
        int numAnnotations;
        char[] charBuffer = context.charBuffer;
        int currentOffset = recordComponentOffset;
        String name = this.readUTF8(currentOffset, charBuffer);
        String descriptor = this.readUTF8(currentOffset + 2, charBuffer);
        currentOffset += 4;
        String signature = null;
        int runtimeVisibleAnnotationsOffset = 0;
        int runtimeInvisibleAnnotationsOffset = 0;
        int runtimeVisibleTypeAnnotationsOffset = 0;
        int runtimeInvisibleTypeAnnotationsOffset = 0;
        Attribute attributes = null;
        int attributesCount = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        while (attributesCount-- > 0) {
            String attributeName = this.readUTF8(currentOffset, charBuffer);
            int attributeLength = this.readInt(currentOffset + 2);
            currentOffset += 6;
            if ("Signature".equals(attributeName)) {
                signature = this.readUTF8(currentOffset, charBuffer);
            } else if ("RuntimeVisibleAnnotations".equals(attributeName)) {
                runtimeVisibleAnnotationsOffset = currentOffset;
            } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
                runtimeVisibleTypeAnnotationsOffset = currentOffset;
            } else if ("RuntimeInvisibleAnnotations".equals(attributeName)) {
                runtimeInvisibleAnnotationsOffset = currentOffset;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
                runtimeInvisibleTypeAnnotationsOffset = currentOffset;
            } else {
                Attribute attribute = this.readAttribute(context.attributePrototypes, attributeName, currentOffset, attributeLength, charBuffer, -1, null);
                attribute.nextAttribute = attributes;
                attributes = attribute;
            }
            currentOffset += attributeLength;
        }
        RecordComponentVisitor recordComponentVisitor = classVisitor.visitRecordComponent(name, descriptor, signature);
        if (recordComponentVisitor == null) {
            return currentOffset;
        }
        if (runtimeVisibleAnnotationsOffset != 0) {
            numAnnotations = this.readUnsignedShort(runtimeVisibleAnnotationsOffset);
            int currentAnnotationOffset = runtimeVisibleAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(recordComponentVisitor.visitAnnotation(annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
            }
        }
        if (runtimeInvisibleAnnotationsOffset != 0) {
            numAnnotations = this.readUnsignedShort(runtimeInvisibleAnnotationsOffset);
            int currentAnnotationOffset = runtimeInvisibleAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(recordComponentVisitor.visitAnnotation(annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
            }
        }
        if (runtimeVisibleTypeAnnotationsOffset != 0) {
            numAnnotations = this.readUnsignedShort(runtimeVisibleTypeAnnotationsOffset);
            int currentAnnotationOffset = runtimeVisibleTypeAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                currentAnnotationOffset = this.readTypeAnnotationTarget(context, currentAnnotationOffset);
                annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(recordComponentVisitor.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
            }
        }
        if (runtimeInvisibleTypeAnnotationsOffset != 0) {
            numAnnotations = this.readUnsignedShort(runtimeInvisibleTypeAnnotationsOffset);
            int currentAnnotationOffset = runtimeInvisibleTypeAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                currentAnnotationOffset = this.readTypeAnnotationTarget(context, currentAnnotationOffset);
                annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(recordComponentVisitor.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
            }
        }
        while (attributes != null) {
            Attribute nextAttribute = attributes.nextAttribute;
            attributes.nextAttribute = null;
            recordComponentVisitor.visitAttribute(attributes);
            attributes = nextAttribute;
        }
        recordComponentVisitor.visitEnd();
        return currentOffset;
    }

    private int readField(ClassVisitor classVisitor, Context context, int fieldInfoOffset) {
        String annotationDescriptor;
        int currentAnnotationOffset;
        int numAnnotations;
        char[] charBuffer = context.charBuffer;
        int currentOffset = fieldInfoOffset;
        int accessFlags = this.readUnsignedShort(currentOffset);
        String name = this.readUTF8(currentOffset + 2, charBuffer);
        String descriptor = this.readUTF8(currentOffset + 4, charBuffer);
        currentOffset += 6;
        Object constantValue = null;
        String signature = null;
        int runtimeVisibleAnnotationsOffset = 0;
        int runtimeInvisibleAnnotationsOffset = 0;
        int runtimeVisibleTypeAnnotationsOffset = 0;
        int runtimeInvisibleTypeAnnotationsOffset = 0;
        Attribute attributes = null;
        int attributesCount = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        while (attributesCount-- > 0) {
            String attributeName = this.readUTF8(currentOffset, charBuffer);
            int attributeLength = this.readInt(currentOffset + 2);
            currentOffset += 6;
            if ("ConstantValue".equals(attributeName)) {
                int constantvalueIndex = this.readUnsignedShort(currentOffset);
                constantValue = constantvalueIndex == 0 ? null : this.readConst(constantvalueIndex, charBuffer);
            } else if ("Signature".equals(attributeName)) {
                signature = this.readUTF8(currentOffset, charBuffer);
            } else if ("Deprecated".equals(attributeName)) {
                accessFlags |= 0x20000;
            } else if ("Synthetic".equals(attributeName)) {
                accessFlags |= 0x1000;
            } else if ("RuntimeVisibleAnnotations".equals(attributeName)) {
                runtimeVisibleAnnotationsOffset = currentOffset;
            } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
                runtimeVisibleTypeAnnotationsOffset = currentOffset;
            } else if ("RuntimeInvisibleAnnotations".equals(attributeName)) {
                runtimeInvisibleAnnotationsOffset = currentOffset;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
                runtimeInvisibleTypeAnnotationsOffset = currentOffset;
            } else {
                Attribute attribute = this.readAttribute(context.attributePrototypes, attributeName, currentOffset, attributeLength, charBuffer, -1, null);
                attribute.nextAttribute = attributes;
                attributes = attribute;
            }
            currentOffset += attributeLength;
        }
        FieldVisitor fieldVisitor = classVisitor.visitField(accessFlags, name, descriptor, signature, constantValue);
        if (fieldVisitor == null) {
            return currentOffset;
        }
        if (runtimeVisibleAnnotationsOffset != 0) {
            numAnnotations = this.readUnsignedShort(runtimeVisibleAnnotationsOffset);
            currentAnnotationOffset = runtimeVisibleAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(fieldVisitor.visitAnnotation(annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
            }
        }
        if (runtimeInvisibleAnnotationsOffset != 0) {
            numAnnotations = this.readUnsignedShort(runtimeInvisibleAnnotationsOffset);
            currentAnnotationOffset = runtimeInvisibleAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(fieldVisitor.visitAnnotation(annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
            }
        }
        if (runtimeVisibleTypeAnnotationsOffset != 0) {
            numAnnotations = this.readUnsignedShort(runtimeVisibleTypeAnnotationsOffset);
            currentAnnotationOffset = runtimeVisibleTypeAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                currentAnnotationOffset = this.readTypeAnnotationTarget(context, currentAnnotationOffset);
                annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(fieldVisitor.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
            }
        }
        if (runtimeInvisibleTypeAnnotationsOffset != 0) {
            numAnnotations = this.readUnsignedShort(runtimeInvisibleTypeAnnotationsOffset);
            currentAnnotationOffset = runtimeInvisibleTypeAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                currentAnnotationOffset = this.readTypeAnnotationTarget(context, currentAnnotationOffset);
                annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(fieldVisitor.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
            }
        }
        while (attributes != null) {
            Attribute nextAttribute = attributes.nextAttribute;
            attributes.nextAttribute = null;
            fieldVisitor.visitAttribute(attributes);
            attributes = nextAttribute;
        }
        fieldVisitor.visitEnd();
        return currentOffset;
    }

    private int readMethod(ClassVisitor classVisitor, Context context, int methodInfoOffset) {
        int currentAnnotationOffset;
        MethodWriter methodWriter;
        char[] charBuffer = context.charBuffer;
        int currentOffset = methodInfoOffset;
        context.currentMethodAccessFlags = this.readUnsignedShort(currentOffset);
        context.currentMethodName = this.readUTF8(currentOffset + 2, charBuffer);
        context.currentMethodDescriptor = this.readUTF8(currentOffset + 4, charBuffer);
        currentOffset += 6;
        int codeOffset = 0;
        int exceptionsOffset = 0;
        String[] exceptions = null;
        boolean synthetic = false;
        int signatureIndex = 0;
        int runtimeVisibleAnnotationsOffset = 0;
        int runtimeInvisibleAnnotationsOffset = 0;
        int runtimeVisibleParameterAnnotationsOffset = 0;
        int runtimeInvisibleParameterAnnotationsOffset = 0;
        int runtimeVisibleTypeAnnotationsOffset = 0;
        int runtimeInvisibleTypeAnnotationsOffset = 0;
        int annotationDefaultOffset = 0;
        int methodParametersOffset = 0;
        Attribute attributes = null;
        int attributesCount = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        while (attributesCount-- > 0) {
            String attributeName = this.readUTF8(currentOffset, charBuffer);
            int attributeLength = this.readInt(currentOffset + 2);
            currentOffset += 6;
            if ("Code".equals(attributeName)) {
                if ((context.parsingOptions & 1) == 0) {
                    codeOffset = currentOffset;
                }
            } else if ("Exceptions".equals(attributeName)) {
                exceptionsOffset = currentOffset;
                exceptions = new String[this.readUnsignedShort(exceptionsOffset)];
                int currentExceptionOffset = exceptionsOffset + 2;
                for (int i = 0; i < exceptions.length; ++i) {
                    exceptions[i] = this.readClass(currentExceptionOffset, charBuffer);
                    currentExceptionOffset += 2;
                }
            } else if ("Signature".equals(attributeName)) {
                signatureIndex = this.readUnsignedShort(currentOffset);
            } else if ("Deprecated".equals(attributeName)) {
                context.currentMethodAccessFlags |= 0x20000;
            } else if ("RuntimeVisibleAnnotations".equals(attributeName)) {
                runtimeVisibleAnnotationsOffset = currentOffset;
            } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
                runtimeVisibleTypeAnnotationsOffset = currentOffset;
            } else if ("AnnotationDefault".equals(attributeName)) {
                annotationDefaultOffset = currentOffset;
            } else if ("Synthetic".equals(attributeName)) {
                synthetic = true;
                context.currentMethodAccessFlags |= 0x1000;
            } else if ("RuntimeInvisibleAnnotations".equals(attributeName)) {
                runtimeInvisibleAnnotationsOffset = currentOffset;
            } else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
                runtimeInvisibleTypeAnnotationsOffset = currentOffset;
            } else if ("RuntimeVisibleParameterAnnotations".equals(attributeName)) {
                runtimeVisibleParameterAnnotationsOffset = currentOffset;
            } else if ("RuntimeInvisibleParameterAnnotations".equals(attributeName)) {
                runtimeInvisibleParameterAnnotationsOffset = currentOffset;
            } else if ("MethodParameters".equals(attributeName)) {
                methodParametersOffset = currentOffset;
            } else {
                Attribute attribute = this.readAttribute(context.attributePrototypes, attributeName, currentOffset, attributeLength, charBuffer, -1, null);
                attribute.nextAttribute = attributes;
                attributes = attribute;
            }
            currentOffset += attributeLength;
        }
        MethodVisitor methodVisitor = classVisitor.visitMethod(context.currentMethodAccessFlags, context.currentMethodName, context.currentMethodDescriptor, signatureIndex == 0 ? null : this.readUtf(signatureIndex, charBuffer), exceptions);
        if (methodVisitor == null) {
            return currentOffset;
        }
        if (methodVisitor instanceof MethodWriter && (methodWriter = (MethodWriter)methodVisitor).canCopyMethodAttributes(this, synthetic, (context.currentMethodAccessFlags & 0x20000) != 0, this.readUnsignedShort(methodInfoOffset + 4), signatureIndex, exceptionsOffset)) {
            methodWriter.setMethodAttributesSource(methodInfoOffset, currentOffset - methodInfoOffset);
            return currentOffset;
        }
        if (methodParametersOffset != 0 && (context.parsingOptions & 2) == 0) {
            int parametersCount = this.readByte(methodParametersOffset);
            int currentParameterOffset = methodParametersOffset + 1;
            while (parametersCount-- > 0) {
                methodVisitor.visitParameter(this.readUTF8(currentParameterOffset, charBuffer), this.readUnsignedShort(currentParameterOffset + 2));
                currentParameterOffset += 4;
            }
        }
        if (annotationDefaultOffset != 0) {
            AnnotationVisitor annotationVisitor = methodVisitor.visitAnnotationDefault();
            this.readElementValue(annotationVisitor, annotationDefaultOffset, null, charBuffer);
            if (annotationVisitor != null) {
                annotationVisitor.visitEnd();
            }
        }
        if (runtimeVisibleAnnotationsOffset != 0) {
            int numAnnotations = this.readUnsignedShort(runtimeVisibleAnnotationsOffset);
            currentAnnotationOffset = runtimeVisibleAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                String annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(methodVisitor.visitAnnotation(annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
            }
        }
        if (runtimeInvisibleAnnotationsOffset != 0) {
            int numAnnotations = this.readUnsignedShort(runtimeInvisibleAnnotationsOffset);
            currentAnnotationOffset = runtimeInvisibleAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                String annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(methodVisitor.visitAnnotation(annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
            }
        }
        if (runtimeVisibleTypeAnnotationsOffset != 0) {
            int numAnnotations = this.readUnsignedShort(runtimeVisibleTypeAnnotationsOffset);
            currentAnnotationOffset = runtimeVisibleTypeAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                currentAnnotationOffset = this.readTypeAnnotationTarget(context, currentAnnotationOffset);
                String annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(methodVisitor.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset, true, charBuffer);
            }
        }
        if (runtimeInvisibleTypeAnnotationsOffset != 0) {
            int numAnnotations = this.readUnsignedShort(runtimeInvisibleTypeAnnotationsOffset);
            currentAnnotationOffset = runtimeInvisibleTypeAnnotationsOffset + 2;
            while (numAnnotations-- > 0) {
                currentAnnotationOffset = this.readTypeAnnotationTarget(context, currentAnnotationOffset);
                String annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                currentAnnotationOffset += 2;
                currentAnnotationOffset = this.readElementValues(methodVisitor.visitTypeAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset, true, charBuffer);
            }
        }
        if (runtimeVisibleParameterAnnotationsOffset != 0) {
            this.readParameterAnnotations(methodVisitor, context, runtimeVisibleParameterAnnotationsOffset, true);
        }
        if (runtimeInvisibleParameterAnnotationsOffset != 0) {
            this.readParameterAnnotations(methodVisitor, context, runtimeInvisibleParameterAnnotationsOffset, false);
        }
        while (attributes != null) {
            Attribute nextAttribute = attributes.nextAttribute;
            attributes.nextAttribute = null;
            methodVisitor.visitAttribute(attributes);
            attributes = nextAttribute;
        }
        if (codeOffset != 0) {
            methodVisitor.visitCode();
            this.readCode(methodVisitor, context, codeOffset);
        }
        methodVisitor.visitEnd();
        return currentOffset;
    }

    private void readCode(MethodVisitor methodVisitor, Context context, int codeOffset) {
        boolean expandFrames;
        int currentOffset = codeOffset;
        byte[] classBuffer = this.classFileBuffer;
        char[] charBuffer = context.charBuffer;
        int maxStack = this.readUnsignedShort(currentOffset);
        int maxLocals = this.readUnsignedShort(currentOffset + 2);
        int codeLength = this.readInt(currentOffset + 4);
        if (codeLength > this.classFileBuffer.length - (currentOffset += 8)) {
            throw new IllegalArgumentException();
        }
        int bytecodeStartOffset = currentOffset;
        int bytecodeEndOffset = currentOffset + codeLength;
        context.currentMethodLabels = new Label[codeLength + 1];
        Label[] labels = context.currentMethodLabels;
        block39: while (currentOffset < bytecodeEndOffset) {
            int bytecodeOffset = currentOffset - bytecodeStartOffset;
            int opcode = classBuffer[currentOffset] & 0xFF;
            switch (opcode) {
                case 0: 
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 7: 
                case 8: 
                case 9: 
                case 10: 
                case 11: 
                case 12: 
                case 13: 
                case 14: 
                case 15: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 31: 
                case 32: 
                case 33: 
                case 34: 
                case 35: 
                case 36: 
                case 37: 
                case 38: 
                case 39: 
                case 40: 
                case 41: 
                case 42: 
                case 43: 
                case 44: 
                case 45: 
                case 46: 
                case 47: 
                case 48: 
                case 49: 
                case 50: 
                case 51: 
                case 52: 
                case 53: 
                case 59: 
                case 60: 
                case 61: 
                case 62: 
                case 63: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: 
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 75: 
                case 76: 
                case 77: 
                case 78: 
                case 79: 
                case 80: 
                case 81: 
                case 82: 
                case 83: 
                case 84: 
                case 85: 
                case 86: 
                case 87: 
                case 88: 
                case 89: 
                case 90: 
                case 91: 
                case 92: 
                case 93: 
                case 94: 
                case 95: 
                case 96: 
                case 97: 
                case 98: 
                case 99: 
                case 100: 
                case 101: 
                case 102: 
                case 103: 
                case 104: 
                case 105: 
                case 106: 
                case 107: 
                case 108: 
                case 109: 
                case 110: 
                case 111: 
                case 112: 
                case 113: 
                case 114: 
                case 115: 
                case 116: 
                case 117: 
                case 118: 
                case 119: 
                case 120: 
                case 121: 
                case 122: 
                case 123: 
                case 124: 
                case 125: 
                case 126: 
                case 127: 
                case 128: 
                case 129: 
                case 130: 
                case 131: 
                case 133: 
                case 134: 
                case 135: 
                case 136: 
                case 137: 
                case 138: 
                case 139: 
                case 140: 
                case 141: 
                case 142: 
                case 143: 
                case 144: 
                case 145: 
                case 146: 
                case 147: 
                case 148: 
                case 149: 
                case 150: 
                case 151: 
                case 152: 
                case 172: 
                case 173: 
                case 174: 
                case 175: 
                case 176: 
                case 177: 
                case 190: 
                case 191: 
                case 194: 
                case 195: {
                    ++currentOffset;
                    continue block39;
                }
                case 153: 
                case 154: 
                case 155: 
                case 156: 
                case 157: 
                case 158: 
                case 159: 
                case 160: 
                case 161: 
                case 162: 
                case 163: 
                case 164: 
                case 165: 
                case 166: 
                case 167: 
                case 168: 
                case 198: 
                case 199: {
                    this.createLabel(bytecodeOffset + this.readShort(currentOffset + 1), labels);
                    currentOffset += 3;
                    continue block39;
                }
                case 202: 
                case 203: 
                case 204: 
                case 205: 
                case 206: 
                case 207: 
                case 208: 
                case 209: 
                case 210: 
                case 211: 
                case 212: 
                case 213: 
                case 214: 
                case 215: 
                case 216: 
                case 217: 
                case 218: 
                case 219: {
                    this.createLabel(bytecodeOffset + this.readUnsignedShort(currentOffset + 1), labels);
                    currentOffset += 3;
                    continue block39;
                }
                case 200: 
                case 201: 
                case 220: {
                    this.createLabel(bytecodeOffset + this.readInt(currentOffset + 1), labels);
                    currentOffset += 5;
                    continue block39;
                }
                case 196: {
                    switch (classBuffer[currentOffset + 1] & 0xFF) {
                        case 21: 
                        case 22: 
                        case 23: 
                        case 24: 
                        case 25: 
                        case 54: 
                        case 55: 
                        case 56: 
                        case 57: 
                        case 58: 
                        case 169: {
                            currentOffset += 4;
                            continue block39;
                        }
                        case 132: {
                            currentOffset += 6;
                            continue block39;
                        }
                    }
                    throw new IllegalArgumentException();
                }
                case 170: {
                    this.createLabel(bytecodeOffset + this.readInt(currentOffset += 4 - (bytecodeOffset & 3)), labels);
                    int numTableEntries = this.readInt(currentOffset + 8) - this.readInt(currentOffset + 4) + 1;
                    currentOffset += 12;
                    while (numTableEntries-- > 0) {
                        this.createLabel(bytecodeOffset + this.readInt(currentOffset), labels);
                        currentOffset += 4;
                    }
                    continue block39;
                }
                case 171: {
                    this.createLabel(bytecodeOffset + this.readInt(currentOffset += 4 - (bytecodeOffset & 3)), labels);
                    int numSwitchCases = this.readInt(currentOffset + 4);
                    currentOffset += 8;
                    while (numSwitchCases-- > 0) {
                        this.createLabel(bytecodeOffset + this.readInt(currentOffset + 4), labels);
                        currentOffset += 8;
                    }
                    continue block39;
                }
                case 16: 
                case 18: 
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 54: 
                case 55: 
                case 56: 
                case 57: 
                case 58: 
                case 169: 
                case 188: {
                    currentOffset += 2;
                    continue block39;
                }
                case 17: 
                case 19: 
                case 20: 
                case 132: 
                case 178: 
                case 179: 
                case 180: 
                case 181: 
                case 182: 
                case 183: 
                case 184: 
                case 187: 
                case 189: 
                case 192: 
                case 193: {
                    currentOffset += 3;
                    continue block39;
                }
                case 185: 
                case 186: {
                    currentOffset += 5;
                    continue block39;
                }
                case 197: {
                    currentOffset += 4;
                    continue block39;
                }
            }
            throw new IllegalArgumentException();
        }
        int exceptionTableLength = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        while (exceptionTableLength-- > 0) {
            Label start = this.createLabel(this.readUnsignedShort(currentOffset), labels);
            Label end = this.createLabel(this.readUnsignedShort(currentOffset + 2), labels);
            Label handler = this.createLabel(this.readUnsignedShort(currentOffset + 4), labels);
            String catchType = this.readUTF8(this.cpInfoOffsets[this.readUnsignedShort(currentOffset + 6)], charBuffer);
            currentOffset += 8;
            methodVisitor.visitTryCatchBlock(start, end, handler, catchType);
        }
        int stackMapFrameOffset = 0;
        int stackMapTableEndOffset = 0;
        boolean compressedFrames = true;
        int localVariableTableOffset = 0;
        int localVariableTypeTableOffset = 0;
        int[] visibleTypeAnnotationOffsets = null;
        int[] invisibleTypeAnnotationOffsets = null;
        Attribute attributes = null;
        int attributesCount = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        while (attributesCount-- > 0) {
            int startPc;
            String attributeName = this.readUTF8(currentOffset, charBuffer);
            int attributeLength = this.readInt(currentOffset + 2);
            currentOffset += 6;
            if ("LocalVariableTable".equals(attributeName)) {
                if ((context.parsingOptions & 2) == 0) {
                    localVariableTableOffset = currentOffset;
                    int currentLocalVariableTableOffset = currentOffset;
                    int localVariableTableLength = this.readUnsignedShort(currentLocalVariableTableOffset);
                    currentLocalVariableTableOffset += 2;
                    while (localVariableTableLength-- > 0) {
                        startPc = this.readUnsignedShort(currentLocalVariableTableOffset);
                        this.createDebugLabel(startPc, labels);
                        int length = this.readUnsignedShort(currentLocalVariableTableOffset + 2);
                        this.createDebugLabel(startPc + length, labels);
                        currentLocalVariableTableOffset += 10;
                    }
                }
            } else if ("LocalVariableTypeTable".equals(attributeName)) {
                localVariableTypeTableOffset = currentOffset;
            } else if ("LineNumberTable".equals(attributeName)) {
                if ((context.parsingOptions & 2) == 0) {
                    int currentLineNumberTableOffset = currentOffset;
                    int lineNumberTableLength = this.readUnsignedShort(currentLineNumberTableOffset);
                    currentLineNumberTableOffset += 2;
                    while (lineNumberTableLength-- > 0) {
                        startPc = this.readUnsignedShort(currentLineNumberTableOffset);
                        int lineNumber = this.readUnsignedShort(currentLineNumberTableOffset + 2);
                        currentLineNumberTableOffset += 4;
                        this.createDebugLabel(startPc, labels);
                        labels[startPc].addLineNumber(lineNumber);
                    }
                }
            } else if ("RuntimeVisibleTypeAnnotations".equals(attributeName)) {
                visibleTypeAnnotationOffsets = this.readTypeAnnotations(methodVisitor, context, currentOffset, true);
            } else if ("RuntimeInvisibleTypeAnnotations".equals(attributeName)) {
                invisibleTypeAnnotationOffsets = this.readTypeAnnotations(methodVisitor, context, currentOffset, false);
            } else if ("StackMapTable".equals(attributeName)) {
                if ((context.parsingOptions & 4) == 0) {
                    stackMapFrameOffset = currentOffset + 2;
                    stackMapTableEndOffset = currentOffset + attributeLength;
                }
            } else if ("StackMap".equals(attributeName)) {
                if ((context.parsingOptions & 4) == 0) {
                    stackMapFrameOffset = currentOffset + 2;
                    stackMapTableEndOffset = currentOffset + attributeLength;
                    compressedFrames = false;
                }
            } else {
                Attribute attribute = this.readAttribute(context.attributePrototypes, attributeName, currentOffset, attributeLength, charBuffer, codeOffset, labels);
                attribute.nextAttribute = attributes;
                attributes = attribute;
            }
            currentOffset += attributeLength;
        }
        boolean bl = expandFrames = (context.parsingOptions & 8) != 0;
        if (stackMapFrameOffset != 0) {
            context.currentFrameOffset = -1;
            context.currentFrameType = 0;
            context.currentFrameLocalCount = 0;
            context.currentFrameLocalCountDelta = 0;
            context.currentFrameLocalTypes = new Object[maxLocals];
            context.currentFrameStackCount = 0;
            context.currentFrameStackTypes = new Object[maxStack];
            if (expandFrames) {
                this.computeImplicitFrame(context);
            }
            for (int offset = stackMapFrameOffset; offset < stackMapTableEndOffset - 2; ++offset) {
                int potentialBytecodeOffset;
                if (classBuffer[offset] != 8 || (potentialBytecodeOffset = this.readUnsignedShort(offset + 1)) < 0 || potentialBytecodeOffset >= codeLength || (classBuffer[bytecodeStartOffset + potentialBytecodeOffset] & 0xFF) != 187) continue;
                this.createLabel(potentialBytecodeOffset, labels);
            }
        }
        if (expandFrames && (context.parsingOptions & 0x100) != 0) {
            methodVisitor.visitFrame(-1, maxLocals, null, 0, null);
        }
        int currentVisibleTypeAnnotationIndex = 0;
        int currentVisibleTypeAnnotationBytecodeOffset = this.getTypeAnnotationBytecodeOffset(visibleTypeAnnotationOffsets, 0);
        int currentInvisibleTypeAnnotationIndex = 0;
        int currentInvisibleTypeAnnotationBytecodeOffset = this.getTypeAnnotationBytecodeOffset(invisibleTypeAnnotationOffsets, 0);
        boolean insertFrame = false;
        int wideJumpOpcodeDelta = (context.parsingOptions & 0x100) == 0 ? 33 : 0;
        currentOffset = bytecodeStartOffset;
        while (currentOffset < bytecodeEndOffset) {
            String annotationDescriptor;
            int currentBytecodeOffset = currentOffset - bytecodeStartOffset;
            Label currentLabel = labels[currentBytecodeOffset];
            if (currentLabel != null) {
                currentLabel.accept(methodVisitor, (context.parsingOptions & 2) == 0);
            }
            while (stackMapFrameOffset != 0 && (context.currentFrameOffset == currentBytecodeOffset || context.currentFrameOffset == -1)) {
                if (context.currentFrameOffset != -1) {
                    if (!compressedFrames || expandFrames) {
                        methodVisitor.visitFrame(-1, context.currentFrameLocalCount, context.currentFrameLocalTypes, context.currentFrameStackCount, context.currentFrameStackTypes);
                    } else {
                        methodVisitor.visitFrame(context.currentFrameType, context.currentFrameLocalCountDelta, context.currentFrameLocalTypes, context.currentFrameStackCount, context.currentFrameStackTypes);
                    }
                    insertFrame = false;
                }
                if (stackMapFrameOffset < stackMapTableEndOffset) {
                    stackMapFrameOffset = this.readStackMapFrame(stackMapFrameOffset, compressedFrames, expandFrames, context);
                    continue;
                }
                stackMapFrameOffset = 0;
            }
            if (insertFrame) {
                if ((context.parsingOptions & 8) != 0) {
                    methodVisitor.visitFrame(256, 0, null, 0, null);
                }
                insertFrame = false;
            }
            int opcode = classBuffer[currentOffset] & 0xFF;
            switch (opcode) {
                case 0: 
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 7: 
                case 8: 
                case 9: 
                case 10: 
                case 11: 
                case 12: 
                case 13: 
                case 14: 
                case 15: 
                case 46: 
                case 47: 
                case 48: 
                case 49: 
                case 50: 
                case 51: 
                case 52: 
                case 53: 
                case 79: 
                case 80: 
                case 81: 
                case 82: 
                case 83: 
                case 84: 
                case 85: 
                case 86: 
                case 87: 
                case 88: 
                case 89: 
                case 90: 
                case 91: 
                case 92: 
                case 93: 
                case 94: 
                case 95: 
                case 96: 
                case 97: 
                case 98: 
                case 99: 
                case 100: 
                case 101: 
                case 102: 
                case 103: 
                case 104: 
                case 105: 
                case 106: 
                case 107: 
                case 108: 
                case 109: 
                case 110: 
                case 111: 
                case 112: 
                case 113: 
                case 114: 
                case 115: 
                case 116: 
                case 117: 
                case 118: 
                case 119: 
                case 120: 
                case 121: 
                case 122: 
                case 123: 
                case 124: 
                case 125: 
                case 126: 
                case 127: 
                case 128: 
                case 129: 
                case 130: 
                case 131: 
                case 133: 
                case 134: 
                case 135: 
                case 136: 
                case 137: 
                case 138: 
                case 139: 
                case 140: 
                case 141: 
                case 142: 
                case 143: 
                case 144: 
                case 145: 
                case 146: 
                case 147: 
                case 148: 
                case 149: 
                case 150: 
                case 151: 
                case 152: 
                case 172: 
                case 173: 
                case 174: 
                case 175: 
                case 176: 
                case 177: 
                case 190: 
                case 191: 
                case 194: 
                case 195: {
                    methodVisitor.visitInsn(opcode);
                    ++currentOffset;
                    break;
                }
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 31: 
                case 32: 
                case 33: 
                case 34: 
                case 35: 
                case 36: 
                case 37: 
                case 38: 
                case 39: 
                case 40: 
                case 41: 
                case 42: 
                case 43: 
                case 44: 
                case 45: {
                    methodVisitor.visitVarInsn(21 + ((opcode -= 26) >> 2), opcode & 3);
                    ++currentOffset;
                    break;
                }
                case 59: 
                case 60: 
                case 61: 
                case 62: 
                case 63: 
                case 64: 
                case 65: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: 
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 75: 
                case 76: 
                case 77: 
                case 78: {
                    methodVisitor.visitVarInsn(54 + ((opcode -= 59) >> 2), opcode & 3);
                    ++currentOffset;
                    break;
                }
                case 153: 
                case 154: 
                case 155: 
                case 156: 
                case 157: 
                case 158: 
                case 159: 
                case 160: 
                case 161: 
                case 162: 
                case 163: 
                case 164: 
                case 165: 
                case 166: 
                case 167: 
                case 168: 
                case 198: 
                case 199: {
                    methodVisitor.visitJumpInsn(opcode, labels[currentBytecodeOffset + this.readShort(currentOffset + 1)]);
                    currentOffset += 3;
                    break;
                }
                case 200: 
                case 201: {
                    methodVisitor.visitJumpInsn(opcode - wideJumpOpcodeDelta, labels[currentBytecodeOffset + this.readInt(currentOffset + 1)]);
                    currentOffset += 5;
                    break;
                }
                case 202: 
                case 203: 
                case 204: 
                case 205: 
                case 206: 
                case 207: 
                case 208: 
                case 209: 
                case 210: 
                case 211: 
                case 212: 
                case 213: 
                case 214: 
                case 215: 
                case 216: 
                case 217: 
                case 218: 
                case 219: {
                    opcode = opcode < 218 ? opcode - 49 : opcode - 20;
                    Label target = labels[currentBytecodeOffset + this.readUnsignedShort(currentOffset + 1)];
                    if (opcode == 167 || opcode == 168) {
                        methodVisitor.visitJumpInsn(opcode + 33, target);
                    } else {
                        opcode = opcode < 167 ? (opcode + 1 ^ 1) - 1 : opcode ^ 1;
                        Label endif = this.createLabel(currentBytecodeOffset + 3, labels);
                        methodVisitor.visitJumpInsn(opcode, endif);
                        methodVisitor.visitJumpInsn(200, target);
                        insertFrame = true;
                    }
                    currentOffset += 3;
                    break;
                }
                case 220: {
                    methodVisitor.visitJumpInsn(200, labels[currentBytecodeOffset + this.readInt(currentOffset + 1)]);
                    insertFrame = true;
                    currentOffset += 5;
                    break;
                }
                case 196: {
                    opcode = classBuffer[currentOffset + 1] & 0xFF;
                    if (opcode == 132) {
                        methodVisitor.visitIincInsn(this.readUnsignedShort(currentOffset + 2), this.readShort(currentOffset + 4));
                        currentOffset += 6;
                        break;
                    }
                    methodVisitor.visitVarInsn(opcode, this.readUnsignedShort(currentOffset + 2));
                    currentOffset += 4;
                    break;
                }
                case 170: {
                    Label defaultLabel = labels[currentBytecodeOffset + this.readInt(currentOffset += 4 - (currentBytecodeOffset & 3))];
                    int low = this.readInt(currentOffset + 4);
                    int high = this.readInt(currentOffset + 8);
                    currentOffset += 12;
                    Label[] table = new Label[high - low + 1];
                    for (int i = 0; i < table.length; ++i) {
                        table[i] = labels[currentBytecodeOffset + this.readInt(currentOffset)];
                        currentOffset += 4;
                    }
                    methodVisitor.visitTableSwitchInsn(low, high, defaultLabel, table);
                    break;
                }
                case 171: {
                    Label defaultLabel = labels[currentBytecodeOffset + this.readInt(currentOffset += 4 - (currentBytecodeOffset & 3))];
                    int numPairs = this.readInt(currentOffset + 4);
                    currentOffset += 8;
                    int[] keys = new int[numPairs];
                    Label[] values = new Label[numPairs];
                    for (int i = 0; i < numPairs; ++i) {
                        keys[i] = this.readInt(currentOffset);
                        values[i] = labels[currentBytecodeOffset + this.readInt(currentOffset + 4)];
                        currentOffset += 8;
                    }
                    methodVisitor.visitLookupSwitchInsn(defaultLabel, keys, values);
                    break;
                }
                case 21: 
                case 22: 
                case 23: 
                case 24: 
                case 25: 
                case 54: 
                case 55: 
                case 56: 
                case 57: 
                case 58: 
                case 169: {
                    methodVisitor.visitVarInsn(opcode, classBuffer[currentOffset + 1] & 0xFF);
                    currentOffset += 2;
                    break;
                }
                case 16: 
                case 188: {
                    methodVisitor.visitIntInsn(opcode, classBuffer[currentOffset + 1]);
                    currentOffset += 2;
                    break;
                }
                case 17: {
                    methodVisitor.visitIntInsn(opcode, this.readShort(currentOffset + 1));
                    currentOffset += 3;
                    break;
                }
                case 18: {
                    methodVisitor.visitLdcInsn(this.readConst(classBuffer[currentOffset + 1] & 0xFF, charBuffer));
                    currentOffset += 2;
                    break;
                }
                case 19: 
                case 20: {
                    methodVisitor.visitLdcInsn(this.readConst(this.readUnsignedShort(currentOffset + 1), charBuffer));
                    currentOffset += 3;
                    break;
                }
                case 178: 
                case 179: 
                case 180: 
                case 181: 
                case 182: 
                case 183: 
                case 184: 
                case 185: {
                    int cpInfoOffset = this.cpInfoOffsets[this.readUnsignedShort(currentOffset + 1)];
                    int nameAndTypeCpInfoOffset = this.cpInfoOffsets[this.readUnsignedShort(cpInfoOffset + 2)];
                    String owner = this.readClass(cpInfoOffset, charBuffer);
                    String name = this.readUTF8(nameAndTypeCpInfoOffset, charBuffer);
                    String descriptor = this.readUTF8(nameAndTypeCpInfoOffset + 2, charBuffer);
                    if (opcode < 182) {
                        methodVisitor.visitFieldInsn(opcode, owner, name, descriptor);
                    } else {
                        boolean isInterface = classBuffer[cpInfoOffset - 1] == 11;
                        methodVisitor.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
                    }
                    if (opcode == 185) {
                        currentOffset += 5;
                        break;
                    }
                    currentOffset += 3;
                    break;
                }
                case 186: {
                    int cpInfoOffset = this.cpInfoOffsets[this.readUnsignedShort(currentOffset + 1)];
                    int nameAndTypeCpInfoOffset = this.cpInfoOffsets[this.readUnsignedShort(cpInfoOffset + 2)];
                    String name = this.readUTF8(nameAndTypeCpInfoOffset, charBuffer);
                    String descriptor = this.readUTF8(nameAndTypeCpInfoOffset + 2, charBuffer);
                    int bootstrapMethodOffset = this.bootstrapMethodOffsets[this.readUnsignedShort(cpInfoOffset)];
                    Handle handle = (Handle)this.readConst(this.readUnsignedShort(bootstrapMethodOffset), charBuffer);
                    Object[] bootstrapMethodArguments = new Object[this.readUnsignedShort(bootstrapMethodOffset + 2)];
                    bootstrapMethodOffset += 4;
                    for (int i = 0; i < bootstrapMethodArguments.length; ++i) {
                        bootstrapMethodArguments[i] = this.readConst(this.readUnsignedShort(bootstrapMethodOffset), charBuffer);
                        bootstrapMethodOffset += 2;
                    }
                    methodVisitor.visitInvokeDynamicInsn(name, descriptor, handle, bootstrapMethodArguments);
                    currentOffset += 5;
                    break;
                }
                case 187: 
                case 189: 
                case 192: 
                case 193: {
                    methodVisitor.visitTypeInsn(opcode, this.readClass(currentOffset + 1, charBuffer));
                    currentOffset += 3;
                    break;
                }
                case 132: {
                    methodVisitor.visitIincInsn(classBuffer[currentOffset + 1] & 0xFF, classBuffer[currentOffset + 2]);
                    currentOffset += 3;
                    break;
                }
                case 197: {
                    methodVisitor.visitMultiANewArrayInsn(this.readClass(currentOffset + 1, charBuffer), classBuffer[currentOffset + 3] & 0xFF);
                    currentOffset += 4;
                    break;
                }
                default: {
                    throw new AssertionError();
                }
            }
            while (visibleTypeAnnotationOffsets != null && currentVisibleTypeAnnotationIndex < visibleTypeAnnotationOffsets.length && currentVisibleTypeAnnotationBytecodeOffset <= currentBytecodeOffset) {
                if (currentVisibleTypeAnnotationBytecodeOffset == currentBytecodeOffset) {
                    int currentAnnotationOffset = this.readTypeAnnotationTarget(context, visibleTypeAnnotationOffsets[currentVisibleTypeAnnotationIndex]);
                    annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                    this.readElementValues(methodVisitor.visitInsnAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, true), currentAnnotationOffset += 2, true, charBuffer);
                }
                currentVisibleTypeAnnotationBytecodeOffset = this.getTypeAnnotationBytecodeOffset(visibleTypeAnnotationOffsets, ++currentVisibleTypeAnnotationIndex);
            }
            while (invisibleTypeAnnotationOffsets != null && currentInvisibleTypeAnnotationIndex < invisibleTypeAnnotationOffsets.length && currentInvisibleTypeAnnotationBytecodeOffset <= currentBytecodeOffset) {
                if (currentInvisibleTypeAnnotationBytecodeOffset == currentBytecodeOffset) {
                    int currentAnnotationOffset = this.readTypeAnnotationTarget(context, invisibleTypeAnnotationOffsets[currentInvisibleTypeAnnotationIndex]);
                    annotationDescriptor = this.readUTF8(currentAnnotationOffset, charBuffer);
                    this.readElementValues(methodVisitor.visitInsnAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, annotationDescriptor, false), currentAnnotationOffset += 2, true, charBuffer);
                }
                currentInvisibleTypeAnnotationBytecodeOffset = this.getTypeAnnotationBytecodeOffset(invisibleTypeAnnotationOffsets, ++currentInvisibleTypeAnnotationIndex);
            }
        }
        if (labels[codeLength] != null) {
            methodVisitor.visitLabel(labels[codeLength]);
        }
        if (localVariableTableOffset != 0 && (context.parsingOptions & 2) == 0) {
            int[] typeTable = null;
            if (localVariableTypeTableOffset != 0) {
                typeTable = new int[this.readUnsignedShort(localVariableTypeTableOffset) * 3];
                currentOffset = localVariableTypeTableOffset + 2;
                int typeTableIndex = typeTable.length;
                while (typeTableIndex > 0) {
                    typeTable[--typeTableIndex] = currentOffset + 6;
                    typeTable[--typeTableIndex] = this.readUnsignedShort(currentOffset + 8);
                    typeTable[--typeTableIndex] = this.readUnsignedShort(currentOffset);
                    currentOffset += 10;
                }
            }
            int localVariableTableLength = this.readUnsignedShort(localVariableTableOffset);
            currentOffset = localVariableTableOffset + 2;
            while (localVariableTableLength-- > 0) {
                int startPc = this.readUnsignedShort(currentOffset);
                int length = this.readUnsignedShort(currentOffset + 2);
                String name = this.readUTF8(currentOffset + 4, charBuffer);
                String descriptor = this.readUTF8(currentOffset + 6, charBuffer);
                int index = this.readUnsignedShort(currentOffset + 8);
                currentOffset += 10;
                String signature = null;
                if (typeTable != null) {
                    for (int i = 0; i < typeTable.length; i += 3) {
                        if (typeTable[i] != startPc || typeTable[i + 1] != index) continue;
                        signature = this.readUTF8(typeTable[i + 2], charBuffer);
                        break;
                    }
                }
                methodVisitor.visitLocalVariable(name, descriptor, signature, labels[startPc], labels[startPc + length], index);
            }
        }
        if (visibleTypeAnnotationOffsets != null) {
            int[] typeTable = visibleTypeAnnotationOffsets;
            int n = typeTable.length;
            for (int i = 0; i < n; ++i) {
                int typeAnnotationOffset = typeTable[i];
                int targetType = this.readByte(typeAnnotationOffset);
                if (targetType != 64 && targetType != 65) continue;
                currentOffset = this.readTypeAnnotationTarget(context, typeAnnotationOffset);
                String annotationDescriptor = this.readUTF8(currentOffset, charBuffer);
                this.readElementValues(methodVisitor.visitLocalVariableAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, context.currentLocalVariableAnnotationRangeStarts, context.currentLocalVariableAnnotationRangeEnds, context.currentLocalVariableAnnotationRangeIndices, annotationDescriptor, true), currentOffset += 2, true, charBuffer);
            }
        }
        if (invisibleTypeAnnotationOffsets != null) {
            for (int typeAnnotationOffset : invisibleTypeAnnotationOffsets) {
                int targetType = this.readByte(typeAnnotationOffset);
                if (targetType != 64 && targetType != 65) continue;
                currentOffset = this.readTypeAnnotationTarget(context, typeAnnotationOffset);
                String annotationDescriptor = this.readUTF8(currentOffset, charBuffer);
                this.readElementValues(methodVisitor.visitLocalVariableAnnotation(context.currentTypeAnnotationTarget, context.currentTypeAnnotationTargetPath, context.currentLocalVariableAnnotationRangeStarts, context.currentLocalVariableAnnotationRangeEnds, context.currentLocalVariableAnnotationRangeIndices, annotationDescriptor, false), currentOffset += 2, true, charBuffer);
            }
        }
        while (attributes != null) {
            Attribute nextAttribute = attributes.nextAttribute;
            attributes.nextAttribute = null;
            methodVisitor.visitAttribute(attributes);
            attributes = nextAttribute;
        }
        methodVisitor.visitMaxs(maxStack, maxLocals);
    }

    protected Label readLabel(int bytecodeOffset, Label[] labels) {
        if (bytecodeOffset >= labels.length) {
            return new Label();
        }
        if (labels[bytecodeOffset] == null) {
            labels[bytecodeOffset] = new Label();
        }
        return labels[bytecodeOffset];
    }

    private Label createLabel(int bytecodeOffset, Label[] labels) {
        Label label = this.readLabel(bytecodeOffset, labels);
        label.flags = (short)(label.flags & 0xFFFFFFFE);
        return label;
    }

    private void createDebugLabel(int bytecodeOffset, Label[] labels) {
        if (labels[bytecodeOffset] == null) {
            this.readLabel((int)bytecodeOffset, (Label[])labels).flags = (short)(this.readLabel((int)bytecodeOffset, (Label[])labels).flags | 1);
        }
    }

    private int[] readTypeAnnotations(MethodVisitor methodVisitor, Context context, int runtimeTypeAnnotationsOffset, boolean visible) {
        char[] charBuffer = context.charBuffer;
        int currentOffset = runtimeTypeAnnotationsOffset;
        int[] typeAnnotationsOffsets = new int[this.readUnsignedShort(currentOffset)];
        currentOffset += 2;
        for (int i = 0; i < typeAnnotationsOffsets.length; ++i) {
            typeAnnotationsOffsets[i] = currentOffset;
            int targetType = this.readInt(currentOffset);
            switch (targetType >>> 24) {
                case 64: 
                case 65: {
                    int tableLength = this.readUnsignedShort(currentOffset + 1);
                    currentOffset += 3;
                    while (tableLength-- > 0) {
                        int startPc = this.readUnsignedShort(currentOffset);
                        int length = this.readUnsignedShort(currentOffset + 2);
                        currentOffset += 6;
                        this.createLabel(startPc, context.currentMethodLabels);
                        this.createLabel(startPc + length, context.currentMethodLabels);
                    }
                    break;
                }
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 75: {
                    currentOffset += 4;
                    break;
                }
                case 16: 
                case 17: 
                case 18: 
                case 23: 
                case 66: 
                case 67: 
                case 68: 
                case 69: 
                case 70: {
                    currentOffset += 3;
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
            int pathLength = this.readByte(currentOffset);
            if (targetType >>> 24 == 66) {
                TypePath path = pathLength == 0 ? null : new TypePath(this.classFileBuffer, currentOffset);
                String annotationDescriptor = this.readUTF8(currentOffset += 1 + 2 * pathLength, charBuffer);
                currentOffset += 2;
                currentOffset = this.readElementValues(methodVisitor.visitTryCatchAnnotation(targetType & 0xFFFFFF00, path, annotationDescriptor, visible), currentOffset, true, charBuffer);
                continue;
            }
            currentOffset += 3 + 2 * pathLength;
            currentOffset = this.readElementValues(null, currentOffset, true, charBuffer);
        }
        return typeAnnotationsOffsets;
    }

    private int getTypeAnnotationBytecodeOffset(int[] typeAnnotationOffsets, int typeAnnotationIndex) {
        if (typeAnnotationOffsets == null || typeAnnotationIndex >= typeAnnotationOffsets.length || this.readByte(typeAnnotationOffsets[typeAnnotationIndex]) < 67) {
            return -1;
        }
        return this.readUnsignedShort(typeAnnotationOffsets[typeAnnotationIndex] + 1);
    }

    private int readTypeAnnotationTarget(Context context, int typeAnnotationOffset) {
        int currentOffset = typeAnnotationOffset;
        int targetType = this.readInt(typeAnnotationOffset);
        switch (targetType >>> 24) {
            case 0: 
            case 1: 
            case 22: {
                targetType &= 0xFFFF0000;
                currentOffset += 2;
                break;
            }
            case 19: 
            case 20: 
            case 21: {
                targetType &= 0xFF000000;
                ++currentOffset;
                break;
            }
            case 64: 
            case 65: {
                targetType &= 0xFF000000;
                int tableLength = this.readUnsignedShort(currentOffset + 1);
                currentOffset += 3;
                context.currentLocalVariableAnnotationRangeStarts = new Label[tableLength];
                context.currentLocalVariableAnnotationRangeEnds = new Label[tableLength];
                context.currentLocalVariableAnnotationRangeIndices = new int[tableLength];
                for (int i = 0; i < tableLength; ++i) {
                    int startPc = this.readUnsignedShort(currentOffset);
                    int length = this.readUnsignedShort(currentOffset + 2);
                    int index = this.readUnsignedShort(currentOffset + 4);
                    currentOffset += 6;
                    context.currentLocalVariableAnnotationRangeStarts[i] = this.createLabel(startPc, context.currentMethodLabels);
                    context.currentLocalVariableAnnotationRangeEnds[i] = this.createLabel(startPc + length, context.currentMethodLabels);
                    context.currentLocalVariableAnnotationRangeIndices[i] = index;
                }
                break;
            }
            case 71: 
            case 72: 
            case 73: 
            case 74: 
            case 75: {
                targetType &= 0xFF0000FF;
                currentOffset += 4;
                break;
            }
            case 16: 
            case 17: 
            case 18: 
            case 23: 
            case 66: {
                targetType &= 0xFFFFFF00;
                currentOffset += 3;
                break;
            }
            case 67: 
            case 68: 
            case 69: 
            case 70: {
                targetType &= 0xFF000000;
                currentOffset += 3;
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
        context.currentTypeAnnotationTarget = targetType;
        int pathLength = this.readByte(currentOffset);
        context.currentTypeAnnotationTargetPath = pathLength == 0 ? null : new TypePath(this.classFileBuffer, currentOffset);
        return currentOffset + 1 + 2 * pathLength;
    }

    private void readParameterAnnotations(MethodVisitor methodVisitor, Context context, int runtimeParameterAnnotationsOffset, boolean visible) {
        int currentOffset = runtimeParameterAnnotationsOffset;
        int numParameters = this.classFileBuffer[currentOffset++] & 0xFF;
        methodVisitor.visitAnnotableParameterCount(numParameters, visible);
        char[] charBuffer = context.charBuffer;
        for (int i = 0; i < numParameters; ++i) {
            int numAnnotations = this.readUnsignedShort(currentOffset);
            currentOffset += 2;
            while (numAnnotations-- > 0) {
                String annotationDescriptor = this.readUTF8(currentOffset, charBuffer);
                currentOffset += 2;
                currentOffset = this.readElementValues(methodVisitor.visitParameterAnnotation(i, annotationDescriptor, visible), currentOffset, true, charBuffer);
            }
        }
    }

    private int readElementValues(AnnotationVisitor annotationVisitor, int annotationOffset, boolean named, char[] charBuffer) {
        int currentOffset = annotationOffset;
        int numElementValuePairs = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        if (named) {
            while (numElementValuePairs-- > 0) {
                String elementName = this.readUTF8(currentOffset, charBuffer);
                currentOffset = this.readElementValue(annotationVisitor, currentOffset + 2, elementName, charBuffer);
            }
        } else {
            while (numElementValuePairs-- > 0) {
                currentOffset = this.readElementValue(annotationVisitor, currentOffset, null, charBuffer);
            }
        }
        if (annotationVisitor != null) {
            annotationVisitor.visitEnd();
        }
        return currentOffset;
    }

    private int readElementValue(AnnotationVisitor annotationVisitor, int elementValueOffset, String elementName, char[] charBuffer) {
        int currentOffset = elementValueOffset;
        if (annotationVisitor == null) {
            switch (this.classFileBuffer[currentOffset] & 0xFF) {
                case 101: {
                    return currentOffset + 5;
                }
                case 64: {
                    return this.readElementValues(null, currentOffset + 3, true, charBuffer);
                }
                case 91: {
                    return this.readElementValues(null, currentOffset + 1, false, charBuffer);
                }
            }
            return currentOffset + 3;
        }
        block5 : switch (this.classFileBuffer[currentOffset++] & 0xFF) {
            case 66: {
                annotationVisitor.visit(elementName, (byte)this.readInt(this.cpInfoOffsets[this.readUnsignedShort(currentOffset)]));
                currentOffset += 2;
                break;
            }
            case 67: {
                annotationVisitor.visit(elementName, Character.valueOf((char)this.readInt(this.cpInfoOffsets[this.readUnsignedShort(currentOffset)])));
                currentOffset += 2;
                break;
            }
            case 68: 
            case 70: 
            case 73: 
            case 74: {
                annotationVisitor.visit(elementName, this.readConst(this.readUnsignedShort(currentOffset), charBuffer));
                currentOffset += 2;
                break;
            }
            case 83: {
                annotationVisitor.visit(elementName, (short)this.readInt(this.cpInfoOffsets[this.readUnsignedShort(currentOffset)]));
                currentOffset += 2;
                break;
            }
            case 90: {
                annotationVisitor.visit(elementName, this.readInt(this.cpInfoOffsets[this.readUnsignedShort(currentOffset)]) == 0 ? Boolean.FALSE : Boolean.TRUE);
                currentOffset += 2;
                break;
            }
            case 115: {
                annotationVisitor.visit(elementName, this.readUTF8(currentOffset, charBuffer));
                currentOffset += 2;
                break;
            }
            case 101: {
                annotationVisitor.visitEnum(elementName, this.readUTF8(currentOffset, charBuffer), this.readUTF8(currentOffset + 2, charBuffer));
                currentOffset += 4;
                break;
            }
            case 99: {
                annotationVisitor.visit(elementName, Type.getType(this.readUTF8(currentOffset, charBuffer)));
                currentOffset += 2;
                break;
            }
            case 64: {
                currentOffset = this.readElementValues(annotationVisitor.visitAnnotation(elementName, this.readUTF8(currentOffset, charBuffer)), currentOffset + 2, true, charBuffer);
                break;
            }
            case 91: {
                int numValues = this.readUnsignedShort(currentOffset);
                currentOffset += 2;
                if (numValues == 0) {
                    return this.readElementValues(annotationVisitor.visitArray(elementName), currentOffset - 2, false, charBuffer);
                }
                switch (this.classFileBuffer[currentOffset] & 0xFF) {
                    case 66: {
                        byte[] byteValues = new byte[numValues];
                        for (int i = 0; i < numValues; ++i) {
                            byteValues[i] = (byte)this.readInt(this.cpInfoOffsets[this.readUnsignedShort(currentOffset + 1)]);
                            currentOffset += 3;
                        }
                        annotationVisitor.visit(elementName, byteValues);
                        break block5;
                    }
                    case 90: {
                        boolean[] booleanValues = new boolean[numValues];
                        for (int i = 0; i < numValues; ++i) {
                            booleanValues[i] = this.readInt(this.cpInfoOffsets[this.readUnsignedShort(currentOffset + 1)]) != 0;
                            currentOffset += 3;
                        }
                        annotationVisitor.visit(elementName, booleanValues);
                        break block5;
                    }
                    case 83: {
                        short[] shortValues = new short[numValues];
                        for (int i = 0; i < numValues; ++i) {
                            shortValues[i] = (short)this.readInt(this.cpInfoOffsets[this.readUnsignedShort(currentOffset + 1)]);
                            currentOffset += 3;
                        }
                        annotationVisitor.visit(elementName, shortValues);
                        break block5;
                    }
                    case 67: {
                        char[] charValues = new char[numValues];
                        for (int i = 0; i < numValues; ++i) {
                            charValues[i] = (char)this.readInt(this.cpInfoOffsets[this.readUnsignedShort(currentOffset + 1)]);
                            currentOffset += 3;
                        }
                        annotationVisitor.visit(elementName, charValues);
                        break block5;
                    }
                    case 73: {
                        int[] intValues = new int[numValues];
                        for (int i = 0; i < numValues; ++i) {
                            intValues[i] = this.readInt(this.cpInfoOffsets[this.readUnsignedShort(currentOffset + 1)]);
                            currentOffset += 3;
                        }
                        annotationVisitor.visit(elementName, intValues);
                        break block5;
                    }
                    case 74: {
                        long[] longValues = new long[numValues];
                        for (int i = 0; i < numValues; ++i) {
                            longValues[i] = this.readLong(this.cpInfoOffsets[this.readUnsignedShort(currentOffset + 1)]);
                            currentOffset += 3;
                        }
                        annotationVisitor.visit(elementName, longValues);
                        break block5;
                    }
                    case 70: {
                        float[] floatValues = new float[numValues];
                        for (int i = 0; i < numValues; ++i) {
                            floatValues[i] = Float.intBitsToFloat(this.readInt(this.cpInfoOffsets[this.readUnsignedShort(currentOffset + 1)]));
                            currentOffset += 3;
                        }
                        annotationVisitor.visit(elementName, floatValues);
                        break block5;
                    }
                    case 68: {
                        double[] doubleValues = new double[numValues];
                        for (int i = 0; i < numValues; ++i) {
                            doubleValues[i] = Double.longBitsToDouble(this.readLong(this.cpInfoOffsets[this.readUnsignedShort(currentOffset + 1)]));
                            currentOffset += 3;
                        }
                        annotationVisitor.visit(elementName, doubleValues);
                        break block5;
                    }
                }
                currentOffset = this.readElementValues(annotationVisitor.visitArray(elementName), currentOffset - 2, false, charBuffer);
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
        return currentOffset;
    }

    private void computeImplicitFrame(Context context) {
        String methodDescriptor = context.currentMethodDescriptor;
        Object[] locals = context.currentFrameLocalTypes;
        int numLocal = 0;
        if ((context.currentMethodAccessFlags & 8) == 0) {
            locals[numLocal++] = "<init>".equals(context.currentMethodName) ? Opcodes.UNINITIALIZED_THIS : this.readClass(this.header + 2, context.charBuffer);
        }
        int currentMethodDescritorOffset = 1;
        block8: while (true) {
            int currentArgumentDescriptorStartOffset = currentMethodDescritorOffset;
            switch (methodDescriptor.charAt(currentMethodDescritorOffset++)) {
                case 'B': 
                case 'C': 
                case 'I': 
                case 'S': 
                case 'Z': {
                    locals[numLocal++] = Opcodes.INTEGER;
                    continue block8;
                }
                case 'F': {
                    locals[numLocal++] = Opcodes.FLOAT;
                    continue block8;
                }
                case 'J': {
                    locals[numLocal++] = Opcodes.LONG;
                    continue block8;
                }
                case 'D': {
                    locals[numLocal++] = Opcodes.DOUBLE;
                    continue block8;
                }
                case '[': {
                    while (methodDescriptor.charAt(currentMethodDescritorOffset) == '[') {
                        ++currentMethodDescritorOffset;
                    }
                    if (methodDescriptor.charAt(currentMethodDescritorOffset) == 'L') {
                        ++currentMethodDescritorOffset;
                        while (methodDescriptor.charAt(currentMethodDescritorOffset) != ';') {
                            ++currentMethodDescritorOffset;
                        }
                    }
                    locals[numLocal++] = methodDescriptor.substring(currentArgumentDescriptorStartOffset, ++currentMethodDescritorOffset);
                    continue block8;
                }
                case 'L': {
                    while (methodDescriptor.charAt(currentMethodDescritorOffset) != ';') {
                        ++currentMethodDescritorOffset;
                    }
                    locals[numLocal++] = methodDescriptor.substring(currentArgumentDescriptorStartOffset + 1, currentMethodDescritorOffset++);
                    continue block8;
                }
            }
            break;
        }
        context.currentFrameLocalCount = numLocal;
    }

    private int readStackMapFrame(int stackMapFrameOffset, boolean compressed, boolean expand, Context context) {
        int offsetDelta;
        int frameType;
        int currentOffset = stackMapFrameOffset;
        char[] charBuffer = context.charBuffer;
        Label[] labels = context.currentMethodLabels;
        if (compressed) {
            frameType = this.classFileBuffer[currentOffset++] & 0xFF;
        } else {
            frameType = 255;
            context.currentFrameOffset = -1;
        }
        context.currentFrameLocalCountDelta = 0;
        if (frameType < 64) {
            offsetDelta = frameType;
            context.currentFrameType = 3;
            context.currentFrameStackCount = 0;
        } else if (frameType < 128) {
            offsetDelta = frameType - 64;
            currentOffset = this.readVerificationTypeInfo(currentOffset, context.currentFrameStackTypes, 0, charBuffer, labels);
            context.currentFrameType = 4;
            context.currentFrameStackCount = 1;
        } else if (frameType >= 247) {
            offsetDelta = this.readUnsignedShort(currentOffset);
            currentOffset += 2;
            if (frameType == 247) {
                currentOffset = this.readVerificationTypeInfo(currentOffset, context.currentFrameStackTypes, 0, charBuffer, labels);
                context.currentFrameType = 4;
                context.currentFrameStackCount = 1;
            } else if (frameType >= 248 && frameType < 251) {
                context.currentFrameType = 2;
                context.currentFrameLocalCountDelta = 251 - frameType;
                context.currentFrameLocalCount -= context.currentFrameLocalCountDelta;
                context.currentFrameStackCount = 0;
            } else if (frameType == 251) {
                context.currentFrameType = 3;
                context.currentFrameStackCount = 0;
            } else if (frameType < 255) {
                int local = expand ? context.currentFrameLocalCount : 0;
                for (int k = frameType - 251; k > 0; --k) {
                    currentOffset = this.readVerificationTypeInfo(currentOffset, context.currentFrameLocalTypes, local++, charBuffer, labels);
                }
                context.currentFrameType = 1;
                context.currentFrameLocalCountDelta = frameType - 251;
                context.currentFrameLocalCount += context.currentFrameLocalCountDelta;
                context.currentFrameStackCount = 0;
            } else {
                int numberOfLocals = this.readUnsignedShort(currentOffset);
                currentOffset += 2;
                context.currentFrameType = 0;
                context.currentFrameLocalCountDelta = numberOfLocals;
                context.currentFrameLocalCount = numberOfLocals;
                for (int local = 0; local < numberOfLocals; ++local) {
                    currentOffset = this.readVerificationTypeInfo(currentOffset, context.currentFrameLocalTypes, local, charBuffer, labels);
                }
                int numberOfStackItems = this.readUnsignedShort(currentOffset);
                currentOffset += 2;
                context.currentFrameStackCount = numberOfStackItems;
                for (int stack = 0; stack < numberOfStackItems; ++stack) {
                    currentOffset = this.readVerificationTypeInfo(currentOffset, context.currentFrameStackTypes, stack, charBuffer, labels);
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
        context.currentFrameOffset += offsetDelta + 1;
        this.createLabel(context.currentFrameOffset, labels);
        return currentOffset;
    }

    private int readVerificationTypeInfo(int verificationTypeInfoOffset, Object[] frame, int index, char[] charBuffer, Label[] labels) {
        int currentOffset = verificationTypeInfoOffset;
        int tag = this.classFileBuffer[currentOffset++] & 0xFF;
        switch (tag) {
            case 0: {
                frame[index] = Opcodes.TOP;
                break;
            }
            case 1: {
                frame[index] = Opcodes.INTEGER;
                break;
            }
            case 2: {
                frame[index] = Opcodes.FLOAT;
                break;
            }
            case 3: {
                frame[index] = Opcodes.DOUBLE;
                break;
            }
            case 4: {
                frame[index] = Opcodes.LONG;
                break;
            }
            case 5: {
                frame[index] = Opcodes.NULL;
                break;
            }
            case 6: {
                frame[index] = Opcodes.UNINITIALIZED_THIS;
                break;
            }
            case 7: {
                frame[index] = this.readClass(currentOffset, charBuffer);
                currentOffset += 2;
                break;
            }
            case 8: {
                frame[index] = this.createLabel(this.readUnsignedShort(currentOffset), labels);
                currentOffset += 2;
                break;
            }
            default: {
                throw new IllegalArgumentException();
            }
        }
        return currentOffset;
    }

    final int getFirstAttributeOffset() {
        int currentOffset = this.header + 8 + this.readUnsignedShort(this.header + 6) * 2;
        int fieldsCount = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        while (fieldsCount-- > 0) {
            int attributesCount = this.readUnsignedShort(currentOffset + 6);
            currentOffset += 8;
            while (attributesCount-- > 0) {
                currentOffset += 6 + this.readInt(currentOffset + 2);
            }
        }
        int methodsCount = this.readUnsignedShort(currentOffset);
        currentOffset += 2;
        while (methodsCount-- > 0) {
            int attributesCount = this.readUnsignedShort(currentOffset + 6);
            currentOffset += 8;
            while (attributesCount-- > 0) {
                currentOffset += 6 + this.readInt(currentOffset + 2);
            }
        }
        return currentOffset + 2;
    }

    private int[] readBootstrapMethodsAttribute(int maxStringLength) {
        char[] charBuffer = new char[maxStringLength];
        int currentAttributeOffset = this.getFirstAttributeOffset();
        for (int i = this.readUnsignedShort(currentAttributeOffset - 2); i > 0; --i) {
            String attributeName = this.readUTF8(currentAttributeOffset, charBuffer);
            int attributeLength = this.readInt(currentAttributeOffset + 2);
            currentAttributeOffset += 6;
            if ("BootstrapMethods".equals(attributeName)) {
                int[] result = new int[this.readUnsignedShort(currentAttributeOffset)];
                int currentBootstrapMethodOffset = currentAttributeOffset + 2;
                for (int j = 0; j < result.length; ++j) {
                    result[j] = currentBootstrapMethodOffset;
                    currentBootstrapMethodOffset += 4 + this.readUnsignedShort(currentBootstrapMethodOffset + 2) * 2;
                }
                return result;
            }
            currentAttributeOffset += attributeLength;
        }
        throw new IllegalArgumentException();
    }

    private Attribute readAttribute(Attribute[] attributePrototypes, String type, int offset, int length, char[] charBuffer, int codeAttributeOffset, Label[] labels) {
        for (Attribute attributePrototype : attributePrototypes) {
            if (!attributePrototype.type.equals(type)) continue;
            return attributePrototype.read(this, offset, length, charBuffer, codeAttributeOffset, labels);
        }
        return new Attribute(type).read(this, offset, length, null, -1, null);
    }

    public int getItemCount() {
        return this.cpInfoOffsets.length;
    }

    public int getItem(int constantPoolEntryIndex) {
        return this.cpInfoOffsets[constantPoolEntryIndex];
    }

    public int getMaxStringLength() {
        return this.maxStringLength;
    }

    public int readByte(int offset) {
        return this.classFileBuffer[offset] & 0xFF;
    }

    public int readUnsignedShort(int offset) {
        byte[] classBuffer = this.classFileBuffer;
        return (classBuffer[offset] & 0xFF) << 8 | classBuffer[offset + 1] & 0xFF;
    }

    public short readShort(int offset) {
        byte[] classBuffer = this.classFileBuffer;
        return (short)((classBuffer[offset] & 0xFF) << 8 | classBuffer[offset + 1] & 0xFF);
    }

    public int readInt(int offset) {
        byte[] classBuffer = this.classFileBuffer;
        return (classBuffer[offset] & 0xFF) << 24 | (classBuffer[offset + 1] & 0xFF) << 16 | (classBuffer[offset + 2] & 0xFF) << 8 | classBuffer[offset + 3] & 0xFF;
    }

    public long readLong(int offset) {
        long l1 = this.readInt(offset);
        long l0 = (long)this.readInt(offset + 4) & 0xFFFFFFFFL;
        return l1 << 32 | l0;
    }

    public String readUTF8(int offset, char[] charBuffer) {
        int constantPoolEntryIndex = this.readUnsignedShort(offset);
        if (offset == 0 || constantPoolEntryIndex == 0) {
            return null;
        }
        return this.readUtf(constantPoolEntryIndex, charBuffer);
    }

    final String readUtf(int constantPoolEntryIndex, char[] charBuffer) {
        String value = this.constantUtf8Values[constantPoolEntryIndex];
        if (value != null) {
            return value;
        }
        int cpInfoOffset = this.cpInfoOffsets[constantPoolEntryIndex];
        this.constantUtf8Values[constantPoolEntryIndex] = this.readUtf(cpInfoOffset + 2, this.readUnsignedShort(cpInfoOffset), charBuffer);
        return this.constantUtf8Values[constantPoolEntryIndex];
    }

    private String readUtf(int utfOffset, int utfLength, char[] charBuffer) {
        int currentOffset = utfOffset;
        int endOffset = currentOffset + utfLength;
        int strLength = 0;
        byte[] classBuffer = this.classFileBuffer;
        while (currentOffset < endOffset) {
            byte currentByte;
            if (((currentByte = classBuffer[currentOffset++]) & 0x80) == 0) {
                charBuffer[strLength++] = (char)(currentByte & 0x7F);
                continue;
            }
            if ((currentByte & 0xE0) == 192) {
                charBuffer[strLength++] = (char)(((currentByte & 0x1F) << 6) + (classBuffer[currentOffset++] & 0x3F));
                continue;
            }
            charBuffer[strLength++] = (char)(((currentByte & 0xF) << 12) + ((classBuffer[currentOffset++] & 0x3F) << 6) + (classBuffer[currentOffset++] & 0x3F));
        }
        return new String(charBuffer, 0, strLength);
    }

    private String readStringish(int offset, char[] charBuffer) {
        return this.readUTF8(this.cpInfoOffsets[this.readUnsignedShort(offset)], charBuffer);
    }

    public String readClass(int offset, char[] charBuffer) {
        return this.readStringish(offset, charBuffer);
    }

    public String readModule(int offset, char[] charBuffer) {
        return this.readStringish(offset, charBuffer);
    }

    public String readPackage(int offset, char[] charBuffer) {
        return this.readStringish(offset, charBuffer);
    }

    private ConstantDynamic readConstantDynamic(int constantPoolEntryIndex, char[] charBuffer) {
        ConstantDynamic constantDynamic = this.constantDynamicValues[constantPoolEntryIndex];
        if (constantDynamic != null) {
            return constantDynamic;
        }
        int cpInfoOffset = this.cpInfoOffsets[constantPoolEntryIndex];
        int nameAndTypeCpInfoOffset = this.cpInfoOffsets[this.readUnsignedShort(cpInfoOffset + 2)];
        String name = this.readUTF8(nameAndTypeCpInfoOffset, charBuffer);
        String descriptor = this.readUTF8(nameAndTypeCpInfoOffset + 2, charBuffer);
        int bootstrapMethodOffset = this.bootstrapMethodOffsets[this.readUnsignedShort(cpInfoOffset)];
        Handle handle = (Handle)this.readConst(this.readUnsignedShort(bootstrapMethodOffset), charBuffer);
        Object[] bootstrapMethodArguments = new Object[this.readUnsignedShort(bootstrapMethodOffset + 2)];
        bootstrapMethodOffset += 4;
        for (int i = 0; i < bootstrapMethodArguments.length; ++i) {
            bootstrapMethodArguments[i] = this.readConst(this.readUnsignedShort(bootstrapMethodOffset), charBuffer);
            bootstrapMethodOffset += 2;
        }
        this.constantDynamicValues[constantPoolEntryIndex] = new ConstantDynamic(name, descriptor, handle, bootstrapMethodArguments);
        return this.constantDynamicValues[constantPoolEntryIndex];
    }

    public Object readConst(int constantPoolEntryIndex, char[] charBuffer) {
        int cpInfoOffset = this.cpInfoOffsets[constantPoolEntryIndex];
        switch (this.classFileBuffer[cpInfoOffset - 1]) {
            case 3: {
                return this.readInt(cpInfoOffset);
            }
            case 4: {
                return Float.valueOf(Float.intBitsToFloat(this.readInt(cpInfoOffset)));
            }
            case 5: {
                return this.readLong(cpInfoOffset);
            }
            case 6: {
                return Double.longBitsToDouble(this.readLong(cpInfoOffset));
            }
            case 7: {
                return Type.getObjectType(this.readUTF8(cpInfoOffset, charBuffer));
            }
            case 8: {
                return this.readUTF8(cpInfoOffset, charBuffer);
            }
            case 16: {
                return Type.getMethodType(this.readUTF8(cpInfoOffset, charBuffer));
            }
            case 15: {
                int referenceKind = this.readByte(cpInfoOffset);
                int referenceCpInfoOffset = this.cpInfoOffsets[this.readUnsignedShort(cpInfoOffset + 1)];
                int nameAndTypeCpInfoOffset = this.cpInfoOffsets[this.readUnsignedShort(referenceCpInfoOffset + 2)];
                String owner = this.readClass(referenceCpInfoOffset, charBuffer);
                String name = this.readUTF8(nameAndTypeCpInfoOffset, charBuffer);
                String descriptor = this.readUTF8(nameAndTypeCpInfoOffset + 2, charBuffer);
                boolean isInterface = this.classFileBuffer[referenceCpInfoOffset - 1] == 11;
                return new Handle(referenceKind, owner, name, descriptor, isInterface);
            }
            case 17: {
                return this.readConstantDynamic(constantPoolEntryIndex, charBuffer);
            }
        }
        throw new IllegalArgumentException();
    }
}

