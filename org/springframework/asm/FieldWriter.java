/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.asm;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.AnnotationWriter;
import org.springframework.asm.Attribute;
import org.springframework.asm.ByteVector;
import org.springframework.asm.FieldVisitor;
import org.springframework.asm.SymbolTable;
import org.springframework.asm.TypePath;

final class FieldWriter
extends FieldVisitor {
    private final SymbolTable symbolTable;
    private final int accessFlags;
    private final int nameIndex;
    private final int descriptorIndex;
    private int signatureIndex;
    private int constantValueIndex;
    private AnnotationWriter lastRuntimeVisibleAnnotation;
    private AnnotationWriter lastRuntimeInvisibleAnnotation;
    private AnnotationWriter lastRuntimeVisibleTypeAnnotation;
    private AnnotationWriter lastRuntimeInvisibleTypeAnnotation;
    private Attribute firstAttribute;

    FieldWriter(SymbolTable symbolTable, int access, String name, String descriptor, String signature, Object constantValue) {
        super(589824);
        this.symbolTable = symbolTable;
        this.accessFlags = access;
        this.nameIndex = symbolTable.addConstantUtf8(name);
        this.descriptorIndex = symbolTable.addConstantUtf8(descriptor);
        if (signature != null) {
            this.signatureIndex = symbolTable.addConstantUtf8(signature);
        }
        if (constantValue != null) {
            this.constantValueIndex = symbolTable.addConstant((Object)constantValue).index;
        }
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if (visible) {
            this.lastRuntimeVisibleAnnotation = AnnotationWriter.create(this.symbolTable, descriptor, this.lastRuntimeVisibleAnnotation);
            return this.lastRuntimeVisibleAnnotation;
        }
        this.lastRuntimeInvisibleAnnotation = AnnotationWriter.create(this.symbolTable, descriptor, this.lastRuntimeInvisibleAnnotation);
        return this.lastRuntimeInvisibleAnnotation;
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        if (visible) {
            this.lastRuntimeVisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, typeRef, typePath, descriptor, this.lastRuntimeVisibleTypeAnnotation);
            return this.lastRuntimeVisibleTypeAnnotation;
        }
        this.lastRuntimeInvisibleTypeAnnotation = AnnotationWriter.create(this.symbolTable, typeRef, typePath, descriptor, this.lastRuntimeInvisibleTypeAnnotation);
        return this.lastRuntimeInvisibleTypeAnnotation;
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        attribute.nextAttribute = this.firstAttribute;
        this.firstAttribute = attribute;
    }

    @Override
    public void visitEnd() {
    }

    int computeFieldInfoSize() {
        int size = 8;
        if (this.constantValueIndex != 0) {
            this.symbolTable.addConstantUtf8("ConstantValue");
            size += 8;
        }
        size += Attribute.computeAttributesSize(this.symbolTable, this.accessFlags, this.signatureIndex);
        size += AnnotationWriter.computeAnnotationsSize(this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation);
        if (this.firstAttribute != null) {
            size += this.firstAttribute.computeAttributesSize(this.symbolTable);
        }
        return size;
    }

    void putFieldInfo(ByteVector output) {
        boolean useSyntheticAttribute = this.symbolTable.getMajorVersion() < 49;
        int mask = useSyntheticAttribute ? 4096 : 0;
        output.putShort(this.accessFlags & ~mask).putShort(this.nameIndex).putShort(this.descriptorIndex);
        int attributesCount = 0;
        if (this.constantValueIndex != 0) {
            ++attributesCount;
        }
        if ((this.accessFlags & 0x1000) != 0 && useSyntheticAttribute) {
            ++attributesCount;
        }
        if (this.signatureIndex != 0) {
            ++attributesCount;
        }
        if ((this.accessFlags & 0x20000) != 0) {
            ++attributesCount;
        }
        if (this.lastRuntimeVisibleAnnotation != null) {
            ++attributesCount;
        }
        if (this.lastRuntimeInvisibleAnnotation != null) {
            ++attributesCount;
        }
        if (this.lastRuntimeVisibleTypeAnnotation != null) {
            ++attributesCount;
        }
        if (this.lastRuntimeInvisibleTypeAnnotation != null) {
            ++attributesCount;
        }
        if (this.firstAttribute != null) {
            attributesCount += this.firstAttribute.getAttributeCount();
        }
        output.putShort(attributesCount);
        if (this.constantValueIndex != 0) {
            output.putShort(this.symbolTable.addConstantUtf8("ConstantValue")).putInt(2).putShort(this.constantValueIndex);
        }
        Attribute.putAttributes(this.symbolTable, this.accessFlags, this.signatureIndex, output);
        AnnotationWriter.putAnnotations(this.symbolTable, this.lastRuntimeVisibleAnnotation, this.lastRuntimeInvisibleAnnotation, this.lastRuntimeVisibleTypeAnnotation, this.lastRuntimeInvisibleTypeAnnotation, output);
        if (this.firstAttribute != null) {
            this.firstAttribute.putAttributes(this.symbolTable, output);
        }
    }

    final void collectAttributePrototypes(Attribute.Set attributePrototypes) {
        attributePrototypes.addAttributes(this.firstAttribute);
    }
}

