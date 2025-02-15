/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode.annotation;

import java.io.IOException;
import java.io.OutputStream;
import javassist.bytecode.ByteArray;
import javassist.bytecode.ConstPool;

public class AnnotationsWriter {
    protected OutputStream output;
    private ConstPool pool;

    public AnnotationsWriter(OutputStream os, ConstPool cp) {
        this.output = os;
        this.pool = cp;
    }

    public ConstPool getConstPool() {
        return this.pool;
    }

    public void close() throws IOException {
        this.output.close();
    }

    public void numParameters(int num) throws IOException {
        this.output.write(num);
    }

    public void numAnnotations(int num) throws IOException {
        this.write16bit(num);
    }

    public void annotation(String type, int numMemberValuePairs) throws IOException {
        this.annotation(this.pool.addUtf8Info(type), numMemberValuePairs);
    }

    public void annotation(int typeIndex, int numMemberValuePairs) throws IOException {
        this.write16bit(typeIndex);
        this.write16bit(numMemberValuePairs);
    }

    public void memberValuePair(String memberName) throws IOException {
        this.memberValuePair(this.pool.addUtf8Info(memberName));
    }

    public void memberValuePair(int memberNameIndex) throws IOException {
        this.write16bit(memberNameIndex);
    }

    public void constValueIndex(boolean value) throws IOException {
        this.constValueIndex(90, this.pool.addIntegerInfo(value ? 1 : 0));
    }

    public void constValueIndex(byte value) throws IOException {
        this.constValueIndex(66, this.pool.addIntegerInfo(value));
    }

    public void constValueIndex(char value) throws IOException {
        this.constValueIndex(67, this.pool.addIntegerInfo(value));
    }

    public void constValueIndex(short value) throws IOException {
        this.constValueIndex(83, this.pool.addIntegerInfo(value));
    }

    public void constValueIndex(int value) throws IOException {
        this.constValueIndex(73, this.pool.addIntegerInfo(value));
    }

    public void constValueIndex(long value) throws IOException {
        this.constValueIndex(74, this.pool.addLongInfo(value));
    }

    public void constValueIndex(float value) throws IOException {
        this.constValueIndex(70, this.pool.addFloatInfo(value));
    }

    public void constValueIndex(double value) throws IOException {
        this.constValueIndex(68, this.pool.addDoubleInfo(value));
    }

    public void constValueIndex(String value) throws IOException {
        this.constValueIndex(115, this.pool.addUtf8Info(value));
    }

    public void constValueIndex(int tag, int index) throws IOException {
        this.output.write(tag);
        this.write16bit(index);
    }

    public void enumConstValue(String typeName, String constName) throws IOException {
        this.enumConstValue(this.pool.addUtf8Info(typeName), this.pool.addUtf8Info(constName));
    }

    public void enumConstValue(int typeNameIndex, int constNameIndex) throws IOException {
        this.output.write(101);
        this.write16bit(typeNameIndex);
        this.write16bit(constNameIndex);
    }

    public void classInfoIndex(String name) throws IOException {
        this.classInfoIndex(this.pool.addUtf8Info(name));
    }

    public void classInfoIndex(int index) throws IOException {
        this.output.write(99);
        this.write16bit(index);
    }

    public void annotationValue() throws IOException {
        this.output.write(64);
    }

    public void arrayValue(int numValues) throws IOException {
        this.output.write(91);
        this.write16bit(numValues);
    }

    protected void write16bit(int value) throws IOException {
        byte[] buf = new byte[2];
        ByteArray.write16bit(value, buf, 0);
        this.output.write(buf);
    }
}

