/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode.annotation;

import java.io.IOException;
import java.io.OutputStream;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.AnnotationsWriter;

public class TypeAnnotationsWriter
extends AnnotationsWriter {
    public TypeAnnotationsWriter(OutputStream os, ConstPool cp) {
        super(os, cp);
    }

    @Override
    public void numAnnotations(int num) throws IOException {
        super.numAnnotations(num);
    }

    public void typeParameterTarget(int targetType, int typeParameterIndex) throws IOException {
        this.output.write(targetType);
        this.output.write(typeParameterIndex);
    }

    public void supertypeTarget(int supertypeIndex) throws IOException {
        this.output.write(16);
        this.write16bit(supertypeIndex);
    }

    public void typeParameterBoundTarget(int targetType, int typeParameterIndex, int boundIndex) throws IOException {
        this.output.write(targetType);
        this.output.write(typeParameterIndex);
        this.output.write(boundIndex);
    }

    public void emptyTarget(int targetType) throws IOException {
        this.output.write(targetType);
    }

    public void formalParameterTarget(int formalParameterIndex) throws IOException {
        this.output.write(22);
        this.output.write(formalParameterIndex);
    }

    public void throwsTarget(int throwsTypeIndex) throws IOException {
        this.output.write(23);
        this.write16bit(throwsTypeIndex);
    }

    public void localVarTarget(int targetType, int tableLength) throws IOException {
        this.output.write(targetType);
        this.write16bit(tableLength);
    }

    public void localVarTargetTable(int startPc, int length, int index) throws IOException {
        this.write16bit(startPc);
        this.write16bit(length);
        this.write16bit(index);
    }

    public void catchTarget(int exceptionTableIndex) throws IOException {
        this.output.write(66);
        this.write16bit(exceptionTableIndex);
    }

    public void offsetTarget(int targetType, int offset) throws IOException {
        this.output.write(targetType);
        this.write16bit(offset);
    }

    public void typeArgumentTarget(int targetType, int offset, int type_argument_index) throws IOException {
        this.output.write(targetType);
        this.write16bit(offset);
        this.output.write(type_argument_index);
    }

    public void typePath(int pathLength) throws IOException {
        this.output.write(pathLength);
    }

    public void typePathPath(int typePathKind, int typeArgumentIndex) throws IOException {
        this.output.write(typePathKind);
        this.output.write(typeArgumentIndex);
    }
}

