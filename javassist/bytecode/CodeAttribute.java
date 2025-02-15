/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.CodeAnalyzer;
import javassist.bytecode.CodeIterator;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ExceptionTable;
import javassist.bytecode.Opcode;
import javassist.bytecode.StackMap;
import javassist.bytecode.StackMapTable;

public class CodeAttribute
extends AttributeInfo
implements Opcode {
    public static final String tag = "Code";
    private int maxStack;
    private int maxLocals;
    private ExceptionTable exceptions;
    private List<AttributeInfo> attributes;

    public CodeAttribute(ConstPool cp, int stack, int locals, byte[] code, ExceptionTable etable) {
        super(cp, tag);
        this.maxStack = stack;
        this.maxLocals = locals;
        this.info = code;
        this.exceptions = etable;
        this.attributes = new ArrayList<AttributeInfo>();
    }

    private CodeAttribute(ConstPool cp, CodeAttribute src, Map<String, String> classnames) throws BadBytecode {
        super(cp, tag);
        this.maxStack = src.getMaxStack();
        this.maxLocals = src.getMaxLocals();
        this.exceptions = src.getExceptionTable().copy(cp, classnames);
        this.attributes = new ArrayList<AttributeInfo>();
        List<AttributeInfo> src_attr = src.getAttributes();
        int num = src_attr.size();
        for (int i = 0; i < num; ++i) {
            AttributeInfo ai = src_attr.get(i);
            this.attributes.add(ai.copy(cp, classnames));
        }
        this.info = src.copyCode(cp, classnames, this.exceptions, this);
    }

    CodeAttribute(ConstPool cp, int name_id, DataInputStream in) throws IOException {
        super(cp, name_id, (byte[])null);
        int attr_len = in.readInt();
        this.maxStack = in.readUnsignedShort();
        this.maxLocals = in.readUnsignedShort();
        int code_len = in.readInt();
        this.info = new byte[code_len];
        in.readFully(this.info);
        this.exceptions = new ExceptionTable(cp, in);
        this.attributes = new ArrayList<AttributeInfo>();
        int num = in.readUnsignedShort();
        for (int i = 0; i < num; ++i) {
            this.attributes.add(AttributeInfo.read(cp, in));
        }
    }

    @Override
    public AttributeInfo copy(ConstPool newCp, Map<String, String> classnames) throws RuntimeCopyException {
        try {
            return new CodeAttribute(newCp, this, classnames);
        } catch (BadBytecode e) {
            throw new RuntimeCopyException("bad bytecode. fatal?");
        }
    }

    @Override
    public int length() {
        return 18 + this.info.length + this.exceptions.size() * 8 + AttributeInfo.getLength(this.attributes);
    }

    @Override
    void write(DataOutputStream out) throws IOException {
        out.writeShort(this.name);
        out.writeInt(this.length() - 6);
        out.writeShort(this.maxStack);
        out.writeShort(this.maxLocals);
        out.writeInt(this.info.length);
        out.write(this.info);
        this.exceptions.write(out);
        out.writeShort(this.attributes.size());
        AttributeInfo.writeAll(this.attributes, out);
    }

    @Override
    public byte[] get() {
        throw new UnsupportedOperationException("CodeAttribute.get()");
    }

    @Override
    public void set(byte[] newinfo) {
        throw new UnsupportedOperationException("CodeAttribute.set()");
    }

    @Override
    void renameClass(String oldname, String newname) {
        AttributeInfo.renameClass(this.attributes, oldname, newname);
    }

    @Override
    void renameClass(Map<String, String> classnames) {
        AttributeInfo.renameClass(this.attributes, classnames);
    }

    @Override
    void getRefClasses(Map<String, String> classnames) {
        AttributeInfo.getRefClasses(this.attributes, classnames);
    }

    public String getDeclaringClass() {
        ConstPool cp = this.getConstPool();
        return cp.getClassName();
    }

    public int getMaxStack() {
        return this.maxStack;
    }

    public void setMaxStack(int value) {
        this.maxStack = value;
    }

    public int computeMaxStack() throws BadBytecode {
        this.maxStack = new CodeAnalyzer(this).computeMaxStack();
        return this.maxStack;
    }

    public int getMaxLocals() {
        return this.maxLocals;
    }

    public void setMaxLocals(int value) {
        this.maxLocals = value;
    }

    public int getCodeLength() {
        return this.info.length;
    }

    public byte[] getCode() {
        return this.info;
    }

    void setCode(byte[] newinfo) {
        super.set(newinfo);
    }

    public CodeIterator iterator() {
        return new CodeIterator(this);
    }

    public ExceptionTable getExceptionTable() {
        return this.exceptions;
    }

    public List<AttributeInfo> getAttributes() {
        return this.attributes;
    }

    public AttributeInfo getAttribute(String name) {
        return AttributeInfo.lookup(this.attributes, name);
    }

    public void setAttribute(StackMapTable smt) {
        AttributeInfo.remove(this.attributes, "StackMapTable");
        if (smt != null) {
            this.attributes.add(smt);
        }
    }

    public void setAttribute(StackMap sm) {
        AttributeInfo.remove(this.attributes, "StackMap");
        if (sm != null) {
            this.attributes.add(sm);
        }
    }

    private byte[] copyCode(ConstPool destCp, Map<String, String> classnames, ExceptionTable etable, CodeAttribute destCa) throws BadBytecode {
        int len = this.getCodeLength();
        byte[] newCode = new byte[len];
        destCa.info = newCode;
        LdcEntry ldc = CodeAttribute.copyCode(this.info, 0, len, this.getConstPool(), newCode, destCp, classnames);
        return LdcEntry.doit(newCode, ldc, etable, destCa);
    }

    private static LdcEntry copyCode(byte[] code, int beginPos, int endPos, ConstPool srcCp, byte[] newcode, ConstPool destCp, Map<String, String> classnameMap) throws BadBytecode {
        LdcEntry ldcEntry = null;
        int i = beginPos;
        while (i < endPos) {
            byte c;
            int i2 = CodeIterator.nextOpcode(code, i);
            newcode[i] = c = code[i];
            switch (c & 0xFF) {
                case 19: 
                case 20: 
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
                    CodeAttribute.copyConstPoolInfo(i + 1, code, srcCp, newcode, destCp, classnameMap);
                    break;
                }
                case 18: {
                    int index = code[i + 1] & 0xFF;
                    index = srcCp.copy(index, destCp, classnameMap);
                    if (index < 256) {
                        newcode[i + 1] = (byte)index;
                        break;
                    }
                    newcode[i] = 0;
                    newcode[i + 1] = 0;
                    LdcEntry ldc = new LdcEntry();
                    ldc.where = i;
                    ldc.index = index;
                    ldc.next = ldcEntry;
                    ldcEntry = ldc;
                    break;
                }
                case 185: {
                    CodeAttribute.copyConstPoolInfo(i + 1, code, srcCp, newcode, destCp, classnameMap);
                    newcode[i + 3] = code[i + 3];
                    newcode[i + 4] = code[i + 4];
                    break;
                }
                case 186: {
                    CodeAttribute.copyConstPoolInfo(i + 1, code, srcCp, newcode, destCp, classnameMap);
                    newcode[i + 3] = 0;
                    newcode[i + 4] = 0;
                    break;
                }
                case 197: {
                    CodeAttribute.copyConstPoolInfo(i + 1, code, srcCp, newcode, destCp, classnameMap);
                    newcode[i + 3] = code[i + 3];
                    break;
                }
                default: {
                    while (++i < i2) {
                        newcode[i] = code[i];
                    }
                    break block0;
                }
            }
            i = i2;
        }
        return ldcEntry;
    }

    private static void copyConstPoolInfo(int i, byte[] code, ConstPool srcCp, byte[] newcode, ConstPool destCp, Map<String, String> classnameMap) {
        int index = (code[i] & 0xFF) << 8 | code[i + 1] & 0xFF;
        index = srcCp.copy(index, destCp, classnameMap);
        newcode[i] = (byte)(index >> 8);
        newcode[i + 1] = (byte)index;
    }

    public void insertLocalVar(int where, int size) throws BadBytecode {
        CodeIterator ci = this.iterator();
        while (ci.hasNext()) {
            CodeAttribute.shiftIndex(ci, where, size);
        }
        this.setMaxLocals(this.getMaxLocals() + size);
    }

    private static void shiftIndex(CodeIterator ci, int lessThan, int delta) throws BadBytecode {
        int index = ci.next();
        int opcode = ci.byteAt(index);
        if (opcode < 21) {
            return;
        }
        if (opcode < 79) {
            if (opcode < 26) {
                CodeAttribute.shiftIndex8(ci, index, opcode, lessThan, delta);
            } else if (opcode < 46) {
                CodeAttribute.shiftIndex0(ci, index, opcode, lessThan, delta, 26, 21);
            } else {
                if (opcode < 54) {
                    return;
                }
                if (opcode < 59) {
                    CodeAttribute.shiftIndex8(ci, index, opcode, lessThan, delta);
                } else {
                    CodeAttribute.shiftIndex0(ci, index, opcode, lessThan, delta, 59, 54);
                }
            }
        } else if (opcode == 132) {
            int var = ci.byteAt(index + 1);
            if (var < lessThan) {
                return;
            }
            if ((var += delta) < 256) {
                ci.writeByte(var, index + 1);
            } else {
                byte plus = (byte)ci.byteAt(index + 2);
                int pos = ci.insertExGap(3);
                ci.writeByte(196, pos - 3);
                ci.writeByte(132, pos - 2);
                ci.write16bit(var, pos - 1);
                ci.write16bit(plus, pos + 1);
            }
        } else if (opcode == 169) {
            CodeAttribute.shiftIndex8(ci, index, opcode, lessThan, delta);
        } else if (opcode == 196) {
            int var = ci.u16bitAt(index + 2);
            if (var < lessThan) {
                return;
            }
            ci.write16bit(var += delta, index + 2);
        }
    }

    private static void shiftIndex8(CodeIterator ci, int index, int opcode, int lessThan, int delta) throws BadBytecode {
        int var = ci.byteAt(index + 1);
        if (var < lessThan) {
            return;
        }
        if ((var += delta) < 256) {
            ci.writeByte(var, index + 1);
        } else {
            int pos = ci.insertExGap(2);
            ci.writeByte(196, pos - 2);
            ci.writeByte(opcode, pos - 1);
            ci.write16bit(var, pos);
        }
    }

    private static void shiftIndex0(CodeIterator ci, int index, int opcode, int lessThan, int delta, int opcode_i_0, int opcode_i) throws BadBytecode {
        int var = (opcode - opcode_i_0) % 4;
        if (var < lessThan) {
            return;
        }
        if ((var += delta) < 4) {
            ci.writeByte(opcode + delta, index);
        } else {
            opcode = (opcode - opcode_i_0) / 4 + opcode_i;
            if (var < 256) {
                int pos = ci.insertExGap(1);
                ci.writeByte(opcode, pos - 1);
                ci.writeByte(var, pos);
            } else {
                int pos = ci.insertExGap(3);
                ci.writeByte(196, pos - 1);
                ci.writeByte(opcode, pos);
                ci.write16bit(var, pos + 1);
            }
        }
    }

    static class LdcEntry {
        LdcEntry next;
        int where;
        int index;

        LdcEntry() {
        }

        static byte[] doit(byte[] code, LdcEntry ldc, ExceptionTable etable, CodeAttribute ca) throws BadBytecode {
            if (ldc != null) {
                code = CodeIterator.changeLdcToLdcW(code, etable, ca, ldc);
            }
            return code;
        }
    }

    public static class RuntimeCopyException
    extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public RuntimeCopyException(String s) {
            super(s);
        }
    }
}

