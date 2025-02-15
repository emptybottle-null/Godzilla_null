/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ByteArray;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.TypeAnnotationsWriter;

public class TypeAnnotationsAttribute
extends AttributeInfo {
    public static final String visibleTag = "RuntimeVisibleTypeAnnotations";
    public static final String invisibleTag = "RuntimeInvisibleTypeAnnotations";

    public TypeAnnotationsAttribute(ConstPool cp, String attrname, byte[] info) {
        super(cp, attrname, info);
    }

    TypeAnnotationsAttribute(ConstPool cp, int n, DataInputStream in) throws IOException {
        super(cp, n, in);
    }

    public int numAnnotations() {
        return ByteArray.readU16bit(this.info, 0);
    }

    @Override
    public AttributeInfo copy(ConstPool newCp, Map<String, String> classnames) {
        Copier copier = new Copier(this.info, this.constPool, newCp, classnames);
        try {
            copier.annotationArray();
            return new TypeAnnotationsAttribute(newCp, this.getName(), copier.close());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    void renameClass(String oldname, String newname) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(oldname, newname);
        this.renameClass(map);
    }

    @Override
    void renameClass(Map<String, String> classnames) {
        Renamer renamer = new Renamer(this.info, this.getConstPool(), classnames);
        try {
            renamer.annotationArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    void getRefClasses(Map<String, String> classnames) {
        this.renameClass(classnames);
    }

    static class SubCopier
    extends SubWalker {
        ConstPool srcPool;
        ConstPool destPool;
        Map<String, String> classnames;
        TypeAnnotationsWriter writer;

        SubCopier(byte[] attrInfo, ConstPool src, ConstPool dest, Map<String, String> map, TypeAnnotationsWriter w) {
            super(attrInfo);
            this.srcPool = src;
            this.destPool = dest;
            this.classnames = map;
            this.writer = w;
        }

        @Override
        void typeParameterTarget(int pos, int targetType, int typeParameterIndex) throws Exception {
            this.writer.typeParameterTarget(targetType, typeParameterIndex);
        }

        @Override
        void supertypeTarget(int pos, int superTypeIndex) throws Exception {
            this.writer.supertypeTarget(superTypeIndex);
        }

        @Override
        void typeParameterBoundTarget(int pos, int targetType, int typeParameterIndex, int boundIndex) throws Exception {
            this.writer.typeParameterBoundTarget(targetType, typeParameterIndex, boundIndex);
        }

        @Override
        void emptyTarget(int pos, int targetType) throws Exception {
            this.writer.emptyTarget(targetType);
        }

        @Override
        void formalParameterTarget(int pos, int formalParameterIndex) throws Exception {
            this.writer.formalParameterTarget(formalParameterIndex);
        }

        @Override
        void throwsTarget(int pos, int throwsTypeIndex) throws Exception {
            this.writer.throwsTarget(throwsTypeIndex);
        }

        @Override
        int localvarTarget(int pos, int targetType, int tableLength) throws Exception {
            this.writer.localVarTarget(targetType, tableLength);
            return super.localvarTarget(pos, targetType, tableLength);
        }

        @Override
        void localvarTarget(int pos, int targetType, int startPc, int length, int index) throws Exception {
            this.writer.localVarTargetTable(startPc, length, index);
        }

        @Override
        void catchTarget(int pos, int exceptionTableIndex) throws Exception {
            this.writer.catchTarget(exceptionTableIndex);
        }

        @Override
        void offsetTarget(int pos, int targetType, int offset) throws Exception {
            this.writer.offsetTarget(targetType, offset);
        }

        @Override
        void typeArgumentTarget(int pos, int targetType, int offset, int typeArgumentIndex) throws Exception {
            this.writer.typeArgumentTarget(targetType, offset, typeArgumentIndex);
        }

        @Override
        int typePath(int pos, int pathLength) throws Exception {
            this.writer.typePath(pathLength);
            return super.typePath(pos, pathLength);
        }

        @Override
        void typePath(int pos, int typePathKind, int typeArgumentIndex) throws Exception {
            this.writer.typePathPath(typePathKind, typeArgumentIndex);
        }
    }

    static class Copier
    extends AnnotationsAttribute.Copier {
        SubCopier sub;

        Copier(byte[] attrInfo, ConstPool src, ConstPool dest, Map<String, String> map) {
            super(attrInfo, src, dest, map, false);
            TypeAnnotationsWriter w = new TypeAnnotationsWriter(this.output, dest);
            this.writer = w;
            this.sub = new SubCopier(attrInfo, src, dest, map, w);
        }

        @Override
        int annotationArray(int pos, int num) throws Exception {
            this.writer.numAnnotations(num);
            for (int i = 0; i < num; ++i) {
                int targetType = this.info[pos] & 0xFF;
                pos = this.sub.targetInfo(pos + 1, targetType);
                pos = this.sub.typePath(pos);
                pos = this.annotation(pos);
            }
            return pos;
        }
    }

    static class Renamer
    extends AnnotationsAttribute.Renamer {
        SubWalker sub;

        Renamer(byte[] attrInfo, ConstPool cp, Map<String, String> map) {
            super(attrInfo, cp, map);
            this.sub = new SubWalker(attrInfo);
        }

        @Override
        int annotationArray(int pos, int num) throws Exception {
            for (int i = 0; i < num; ++i) {
                int targetType = this.info[pos] & 0xFF;
                pos = this.sub.targetInfo(pos + 1, targetType);
                pos = this.sub.typePath(pos);
                pos = this.annotation(pos);
            }
            return pos;
        }
    }

    static class SubWalker {
        byte[] info;

        SubWalker(byte[] attrInfo) {
            this.info = attrInfo;
        }

        final int targetInfo(int pos, int type) throws Exception {
            switch (type) {
                case 0: 
                case 1: {
                    int index = this.info[pos] & 0xFF;
                    this.typeParameterTarget(pos, type, index);
                    return pos + 1;
                }
                case 16: {
                    int index = ByteArray.readU16bit(this.info, pos);
                    this.supertypeTarget(pos, index);
                    return pos + 2;
                }
                case 17: 
                case 18: {
                    int param = this.info[pos] & 0xFF;
                    int bound = this.info[pos + 1] & 0xFF;
                    this.typeParameterBoundTarget(pos, type, param, bound);
                    return pos + 2;
                }
                case 19: 
                case 20: 
                case 21: {
                    this.emptyTarget(pos, type);
                    return pos;
                }
                case 22: {
                    int index = this.info[pos] & 0xFF;
                    this.formalParameterTarget(pos, index);
                    return pos + 1;
                }
                case 23: {
                    int index = ByteArray.readU16bit(this.info, pos);
                    this.throwsTarget(pos, index);
                    return pos + 2;
                }
                case 64: 
                case 65: {
                    int len = ByteArray.readU16bit(this.info, pos);
                    return this.localvarTarget(pos + 2, type, len);
                }
                case 66: {
                    int index = ByteArray.readU16bit(this.info, pos);
                    this.catchTarget(pos, index);
                    return pos + 2;
                }
                case 67: 
                case 68: 
                case 69: 
                case 70: {
                    int offset = ByteArray.readU16bit(this.info, pos);
                    this.offsetTarget(pos, type, offset);
                    return pos + 2;
                }
                case 71: 
                case 72: 
                case 73: 
                case 74: 
                case 75: {
                    int offset = ByteArray.readU16bit(this.info, pos);
                    int index = this.info[pos + 2] & 0xFF;
                    this.typeArgumentTarget(pos, type, offset, index);
                    return pos + 3;
                }
            }
            throw new RuntimeException("invalid target type: " + type);
        }

        void typeParameterTarget(int pos, int targetType, int typeParameterIndex) throws Exception {
        }

        void supertypeTarget(int pos, int superTypeIndex) throws Exception {
        }

        void typeParameterBoundTarget(int pos, int targetType, int typeParameterIndex, int boundIndex) throws Exception {
        }

        void emptyTarget(int pos, int targetType) throws Exception {
        }

        void formalParameterTarget(int pos, int formalParameterIndex) throws Exception {
        }

        void throwsTarget(int pos, int throwsTypeIndex) throws Exception {
        }

        int localvarTarget(int pos, int targetType, int tableLength) throws Exception {
            for (int i = 0; i < tableLength; ++i) {
                int start = ByteArray.readU16bit(this.info, pos);
                int length = ByteArray.readU16bit(this.info, pos + 2);
                int index = ByteArray.readU16bit(this.info, pos + 4);
                this.localvarTarget(pos, targetType, start, length, index);
                pos += 6;
            }
            return pos;
        }

        void localvarTarget(int pos, int targetType, int startPc, int length, int index) throws Exception {
        }

        void catchTarget(int pos, int exceptionTableIndex) throws Exception {
        }

        void offsetTarget(int pos, int targetType, int offset) throws Exception {
        }

        void typeArgumentTarget(int pos, int targetType, int offset, int typeArgumentIndex) throws Exception {
        }

        final int typePath(int pos) throws Exception {
            int len = this.info[pos++] & 0xFF;
            return this.typePath(pos, len);
        }

        int typePath(int pos, int pathLength) throws Exception {
            for (int i = 0; i < pathLength; ++i) {
                int kind = this.info[pos] & 0xFF;
                int index = this.info[pos + 1] & 0xFF;
                this.typePath(pos, kind, index);
                pos += 2;
            }
            return pos;
        }

        void typePath(int pos, int typePathKind, int typeArgumentIndex) throws Exception {
        }
    }

    static class TAWalker
    extends AnnotationsAttribute.Walker {
        SubWalker subWalker;

        TAWalker(byte[] attrInfo) {
            super(attrInfo);
            this.subWalker = new SubWalker(attrInfo);
        }

        @Override
        int annotationArray(int pos, int num) throws Exception {
            for (int i = 0; i < num; ++i) {
                int targetType = this.info[pos] & 0xFF;
                pos = this.subWalker.targetInfo(pos + 1, targetType);
                pos = this.subWalker.typePath(pos);
                pos = this.annotation(pos);
            }
            return pos;
        }
    }
}

