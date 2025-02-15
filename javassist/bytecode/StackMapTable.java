/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package javassist.bytecode;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;
import javassist.CannotCompileException;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.ByteArray;
import javassist.bytecode.ConstPool;

public class StackMapTable
extends AttributeInfo {
    public static final String tag = "StackMapTable";
    public static final int TOP = 0;
    public static final int INTEGER = 1;
    public static final int FLOAT = 2;
    public static final int DOUBLE = 3;
    public static final int LONG = 4;
    public static final int NULL = 5;
    public static final int THIS = 6;
    public static final int OBJECT = 7;
    public static final int UNINIT = 8;

    StackMapTable(ConstPool cp, byte[] newInfo) {
        super(cp, tag, newInfo);
    }

    StackMapTable(ConstPool cp, int name_id, DataInputStream in) throws IOException {
        super(cp, name_id, in);
    }

    @Override
    public AttributeInfo copy(ConstPool newCp, Map<String, String> classnames) throws RuntimeCopyException {
        try {
            return new StackMapTable(newCp, new Copier(this.constPool, this.info, newCp, classnames).doit());
        } catch (BadBytecode e) {
            throw new RuntimeCopyException("bad bytecode. fatal?");
        }
    }

    @Override
    void write(DataOutputStream out) throws IOException {
        super.write(out);
    }

    public void insertLocal(int index, int tag, int classInfo) throws BadBytecode {
        byte[] data = new InsertLocal(this.get(), index, tag, classInfo).doit();
        this.set(data);
    }

    public static int typeTagOf(char descriptor) {
        switch (descriptor) {
            case 'D': {
                return 3;
            }
            case 'F': {
                return 2;
            }
            case 'J': {
                return 4;
            }
            case 'L': 
            case '[': {
                return 7;
            }
        }
        return 1;
    }

    public void println(PrintWriter w) {
        Printer.print(this, w);
    }

    public void println(PrintStream ps) {
        Printer.print(this, new PrintWriter(ps, true));
    }

    void shiftPc(int where, int gapSize, boolean exclusive) throws BadBytecode {
        new OffsetShifter(this, where, gapSize).parse();
        new Shifter(this, where, gapSize, exclusive).doit();
    }

    void shiftForSwitch(int where, int gapSize) throws BadBytecode {
        new SwitchShifter(this, where, gapSize).doit();
    }

    public void removeNew(int where) throws CannotCompileException {
        try {
            byte[] data = new NewRemover(this.get(), where).doit();
            this.set(data);
        } catch (BadBytecode e) {
            throw new CannotCompileException("bad stack map table", e);
        }
    }

    static class NewRemover
    extends SimpleCopy {
        int posOfNew;

        public NewRemover(byte[] data, int pos) {
            super(data);
            this.posOfNew = pos;
        }

        @Override
        public void sameLocals(int pos, int offsetDelta, int stackTag, int stackData) {
            if (stackTag == 8 && stackData == this.posOfNew) {
                super.sameFrame(pos, offsetDelta);
            } else {
                super.sameLocals(pos, offsetDelta, stackTag, stackData);
            }
        }

        @Override
        public void fullFrame(int pos, int offsetDelta, int[] localTags, int[] localData, int[] stackTags, int[] stackData) {
            int n = stackTags.length - 1;
            for (int i = 0; i < n; ++i) {
                if (stackTags[i] != 8 || stackData[i] != this.posOfNew || stackTags[i + 1] != 8 || stackData[i + 1] != this.posOfNew) continue;
                int[] stackTags2 = new int[++n - 2];
                int[] stackData2 = new int[n - 2];
                int k = 0;
                for (int j = 0; j < n; ++j) {
                    if (j == i) {
                        ++j;
                        continue;
                    }
                    stackTags2[k] = stackTags[j];
                    stackData2[k++] = stackData[j];
                }
                stackTags = stackTags2;
                stackData = stackData2;
                break;
            }
            super.fullFrame(pos, offsetDelta, localTags, localData, stackTags, stackData);
        }
    }

    static class SwitchShifter
    extends Shifter {
        SwitchShifter(StackMapTable smt, int where, int gap) {
            super(smt, where, gap, false);
        }

        @Override
        void update(int pos, int offsetDelta, int base, int entry) {
            int oldPos;
            this.position = oldPos + offsetDelta + ((oldPos = this.position) == 0 ? 0 : 1);
            int newDelta = offsetDelta;
            if (this.where == this.position) {
                newDelta = offsetDelta - this.gap;
            } else if (this.where == oldPos) {
                newDelta = offsetDelta + this.gap;
            } else {
                return;
            }
            if (offsetDelta < 64) {
                if (newDelta < 64) {
                    this.info[pos] = (byte)(newDelta + base);
                } else {
                    byte[] newinfo = SwitchShifter.insertGap(this.info, pos, 2);
                    newinfo[pos] = (byte)entry;
                    ByteArray.write16bit(newDelta, newinfo, pos + 1);
                    this.updatedInfo = newinfo;
                }
            } else if (newDelta < 64) {
                byte[] newinfo = SwitchShifter.deleteGap(this.info, pos, 2);
                newinfo[pos] = (byte)(newDelta + base);
                this.updatedInfo = newinfo;
            } else {
                ByteArray.write16bit(newDelta, this.info, pos + 1);
            }
        }

        static byte[] deleteGap(byte[] info, int where, int gap) {
            where += gap;
            int len = info.length;
            byte[] newinfo = new byte[len - gap];
            for (int i = 0; i < len; ++i) {
                newinfo[i - (i < where ? 0 : gap)] = info[i];
            }
            return newinfo;
        }

        @Override
        void update(int pos, int offsetDelta) {
            int oldPos;
            this.position = oldPos + offsetDelta + ((oldPos = this.position) == 0 ? 0 : 1);
            int newDelta = offsetDelta;
            if (this.where == this.position) {
                newDelta = offsetDelta - this.gap;
            } else if (this.where == oldPos) {
                newDelta = offsetDelta + this.gap;
            } else {
                return;
            }
            ByteArray.write16bit(newDelta, this.info, pos + 1);
        }
    }

    static class Shifter
    extends Walker {
        private StackMapTable stackMap;
        int where;
        int gap;
        int position;
        byte[] updatedInfo;
        boolean exclusive;

        public Shifter(StackMapTable smt, int where, int gap, boolean exclusive) {
            super(smt);
            this.stackMap = smt;
            this.where = where;
            this.gap = gap;
            this.position = 0;
            this.updatedInfo = null;
            this.exclusive = exclusive;
        }

        public void doit() throws BadBytecode {
            this.parse();
            if (this.updatedInfo != null) {
                this.stackMap.set(this.updatedInfo);
            }
        }

        @Override
        public void sameFrame(int pos, int offsetDelta) {
            this.update(pos, offsetDelta, 0, 251);
        }

        @Override
        public void sameLocals(int pos, int offsetDelta, int stackTag, int stackData) {
            this.update(pos, offsetDelta, 64, 247);
        }

        void update(int pos, int offsetDelta, int base, int entry) {
            boolean match;
            int oldPos;
            this.position = oldPos + offsetDelta + ((oldPos = this.position) == 0 ? 0 : 1);
            if (this.exclusive) {
                match = oldPos == 0 && this.where == 0 || oldPos < this.where && this.where <= this.position;
            } else {
                boolean bl = match = oldPos <= this.where && this.where < this.position;
            }
            if (match) {
                int current = this.info[pos] & 0xFF;
                int newDelta = offsetDelta + this.gap;
                this.position += this.gap;
                if (newDelta < 64) {
                    this.info[pos] = (byte)(newDelta + base);
                } else if (offsetDelta < 64 && current != entry) {
                    byte[] newinfo = Shifter.insertGap(this.info, pos, 2);
                    newinfo[pos] = (byte)entry;
                    ByteArray.write16bit(newDelta, newinfo, pos + 1);
                    this.updatedInfo = newinfo;
                } else {
                    ByteArray.write16bit(newDelta, this.info, pos + 1);
                }
            }
        }

        static byte[] insertGap(byte[] info, int where, int gap) {
            int len = info.length;
            byte[] newinfo = new byte[len + gap];
            for (int i = 0; i < len; ++i) {
                newinfo[i + (i < where ? 0 : gap)] = info[i];
            }
            return newinfo;
        }

        @Override
        public void chopFrame(int pos, int offsetDelta, int k) {
            this.update(pos, offsetDelta);
        }

        @Override
        public void appendFrame(int pos, int offsetDelta, int[] tags, int[] data) {
            this.update(pos, offsetDelta);
        }

        @Override
        public void fullFrame(int pos, int offsetDelta, int[] localTags, int[] localData, int[] stackTags, int[] stackData) {
            this.update(pos, offsetDelta);
        }

        void update(int pos, int offsetDelta) {
            boolean match;
            int oldPos;
            this.position = oldPos + offsetDelta + ((oldPos = this.position) == 0 ? 0 : 1);
            if (this.exclusive) {
                match = oldPos == 0 && this.where == 0 || oldPos < this.where && this.where <= this.position;
            } else {
                boolean bl = match = oldPos <= this.where && this.where < this.position;
            }
            if (match) {
                int newDelta = offsetDelta + this.gap;
                ByteArray.write16bit(newDelta, this.info, pos + 1);
                this.position += this.gap;
            }
        }
    }

    static class OffsetShifter
    extends Walker {
        int where;
        int gap;

        public OffsetShifter(StackMapTable smt, int where, int gap) {
            super(smt);
            this.where = where;
            this.gap = gap;
        }

        @Override
        public void objectOrUninitialized(int tag, int data, int pos) {
            if (tag == 8 && this.where <= data) {
                ByteArray.write16bit(data + this.gap, this.info, pos);
            }
        }
    }

    static class Printer
    extends Walker {
        private PrintWriter writer;
        private int offset;

        public static void print(StackMapTable smt, PrintWriter writer) {
            try {
                new Printer(smt.get(), writer).parse();
            } catch (BadBytecode e) {
                writer.println(e.getMessage());
            }
        }

        Printer(byte[] data, PrintWriter pw) {
            super(data);
            this.writer = pw;
            this.offset = -1;
        }

        @Override
        public void sameFrame(int pos, int offsetDelta) {
            this.offset += offsetDelta + 1;
            this.writer.println(this.offset + " same frame: " + offsetDelta);
        }

        @Override
        public void sameLocals(int pos, int offsetDelta, int stackTag, int stackData) {
            this.offset += offsetDelta + 1;
            this.writer.println(this.offset + " same locals: " + offsetDelta);
            this.printTypeInfo(stackTag, stackData);
        }

        @Override
        public void chopFrame(int pos, int offsetDelta, int k) {
            this.offset += offsetDelta + 1;
            this.writer.println(this.offset + " chop frame: " + offsetDelta + ",    " + k + " last locals");
        }

        @Override
        public void appendFrame(int pos, int offsetDelta, int[] tags, int[] data) {
            this.offset += offsetDelta + 1;
            this.writer.println(this.offset + " append frame: " + offsetDelta);
            for (int i = 0; i < tags.length; ++i) {
                this.printTypeInfo(tags[i], data[i]);
            }
        }

        @Override
        public void fullFrame(int pos, int offsetDelta, int[] localTags, int[] localData, int[] stackTags, int[] stackData) {
            int i;
            this.offset += offsetDelta + 1;
            this.writer.println(this.offset + " full frame: " + offsetDelta);
            this.writer.println("[locals]");
            for (i = 0; i < localTags.length; ++i) {
                this.printTypeInfo(localTags[i], localData[i]);
            }
            this.writer.println("[stack]");
            for (i = 0; i < stackTags.length; ++i) {
                this.printTypeInfo(stackTags[i], stackData[i]);
            }
        }

        private void printTypeInfo(int tag, int data) {
            String msg = null;
            switch (tag) {
                case 0: {
                    msg = "top";
                    break;
                }
                case 1: {
                    msg = "integer";
                    break;
                }
                case 2: {
                    msg = "float";
                    break;
                }
                case 3: {
                    msg = "double";
                    break;
                }
                case 4: {
                    msg = "long";
                    break;
                }
                case 5: {
                    msg = "null";
                    break;
                }
                case 6: {
                    msg = "this";
                    break;
                }
                case 7: {
                    msg = "object (cpool_index " + data + ")";
                    break;
                }
                case 8: {
                    msg = "uninitialized (offset " + data + ")";
                }
            }
            this.writer.print("    ");
            this.writer.println(msg);
        }
    }

    public static class Writer {
        ByteArrayOutputStream output;
        int numOfEntries;

        public Writer(int size) {
            this.output = new ByteArrayOutputStream(size);
            this.numOfEntries = 0;
            this.output.write(0);
            this.output.write(0);
        }

        public byte[] toByteArray() {
            byte[] b = this.output.toByteArray();
            ByteArray.write16bit(this.numOfEntries, b, 0);
            return b;
        }

        public StackMapTable toStackMapTable(ConstPool cp) {
            return new StackMapTable(cp, this.toByteArray());
        }

        public void sameFrame(int offsetDelta) {
            ++this.numOfEntries;
            if (offsetDelta < 64) {
                this.output.write(offsetDelta);
            } else {
                this.output.write(251);
                this.write16(offsetDelta);
            }
        }

        public void sameLocals(int offsetDelta, int tag, int data) {
            ++this.numOfEntries;
            if (offsetDelta < 64) {
                this.output.write(offsetDelta + 64);
            } else {
                this.output.write(247);
                this.write16(offsetDelta);
            }
            this.writeTypeInfo(tag, data);
        }

        public void chopFrame(int offsetDelta, int k) {
            ++this.numOfEntries;
            this.output.write(251 - k);
            this.write16(offsetDelta);
        }

        public void appendFrame(int offsetDelta, int[] tags, int[] data) {
            ++this.numOfEntries;
            int k = tags.length;
            this.output.write(k + 251);
            this.write16(offsetDelta);
            for (int i = 0; i < k; ++i) {
                this.writeTypeInfo(tags[i], data[i]);
            }
        }

        public void fullFrame(int offsetDelta, int[] localTags, int[] localData, int[] stackTags, int[] stackData) {
            int i;
            ++this.numOfEntries;
            this.output.write(255);
            this.write16(offsetDelta);
            int n = localTags.length;
            this.write16(n);
            for (i = 0; i < n; ++i) {
                this.writeTypeInfo(localTags[i], localData[i]);
            }
            n = stackTags.length;
            this.write16(n);
            for (i = 0; i < n; ++i) {
                this.writeTypeInfo(stackTags[i], stackData[i]);
            }
        }

        private void writeTypeInfo(int tag, int data) {
            this.output.write(tag);
            if (tag == 7 || tag == 8) {
                this.write16(data);
            }
        }

        private void write16(int value) {
            this.output.write(value >>> 8 & 0xFF);
            this.output.write(value & 0xFF);
        }
    }

    static class InsertLocal
    extends SimpleCopy {
        private int varIndex;
        private int varTag;
        private int varData;

        public InsertLocal(byte[] data, int varIndex, int varTag, int varData) {
            super(data);
            this.varIndex = varIndex;
            this.varTag = varTag;
            this.varData = varData;
        }

        @Override
        public void fullFrame(int pos, int offsetDelta, int[] localTags, int[] localData, int[] stackTags, int[] stackData) {
            int len = localTags.length;
            if (len < this.varIndex) {
                super.fullFrame(pos, offsetDelta, localTags, localData, stackTags, stackData);
                return;
            }
            int typeSize = this.varTag == 4 || this.varTag == 3 ? 2 : 1;
            int[] localTags2 = new int[len + typeSize];
            int[] localData2 = new int[len + typeSize];
            int index = this.varIndex;
            int j = 0;
            for (int i = 0; i < len; ++i) {
                if (j == index) {
                    j += typeSize;
                }
                localTags2[j] = localTags[i];
                localData2[j++] = localData[i];
            }
            localTags2[index] = this.varTag;
            localData2[index] = this.varData;
            if (typeSize > 1) {
                localTags2[index + 1] = 0;
                localData2[index + 1] = 0;
            }
            super.fullFrame(pos, offsetDelta, localTags2, localData2, stackTags, stackData);
        }
    }

    static class Copier
    extends SimpleCopy {
        private ConstPool srcPool;
        private ConstPool destPool;
        private Map<String, String> classnames;

        public Copier(ConstPool src, byte[] data, ConstPool dest, Map<String, String> names) {
            super(data);
            this.srcPool = src;
            this.destPool = dest;
            this.classnames = names;
        }

        @Override
        protected int copyData(int tag, int data) {
            if (tag == 7) {
                return this.srcPool.copy(data, this.destPool, this.classnames);
            }
            return data;
        }

        @Override
        protected int[] copyData(int[] tags, int[] data) {
            int[] newData = new int[data.length];
            for (int i = 0; i < data.length; ++i) {
                newData[i] = tags[i] == 7 ? this.srcPool.copy(data[i], this.destPool, this.classnames) : data[i];
            }
            return newData;
        }
    }

    static class SimpleCopy
    extends Walker {
        private Writer writer;

        public SimpleCopy(byte[] data) {
            super(data);
            this.writer = new Writer(data.length);
        }

        public byte[] doit() throws BadBytecode {
            this.parse();
            return this.writer.toByteArray();
        }

        @Override
        public void sameFrame(int pos, int offsetDelta) {
            this.writer.sameFrame(offsetDelta);
        }

        @Override
        public void sameLocals(int pos, int offsetDelta, int stackTag, int stackData) {
            this.writer.sameLocals(offsetDelta, stackTag, this.copyData(stackTag, stackData));
        }

        @Override
        public void chopFrame(int pos, int offsetDelta, int k) {
            this.writer.chopFrame(offsetDelta, k);
        }

        @Override
        public void appendFrame(int pos, int offsetDelta, int[] tags, int[] data) {
            this.writer.appendFrame(offsetDelta, tags, this.copyData(tags, data));
        }

        @Override
        public void fullFrame(int pos, int offsetDelta, int[] localTags, int[] localData, int[] stackTags, int[] stackData) {
            this.writer.fullFrame(offsetDelta, localTags, this.copyData(localTags, localData), stackTags, this.copyData(stackTags, stackData));
        }

        protected int copyData(int tag, int data) {
            return data;
        }

        protected int[] copyData(int[] tags, int[] data) {
            return data;
        }
    }

    public static class Walker {
        byte[] info;
        int numOfEntries;

        public Walker(StackMapTable smt) {
            this(smt.get());
        }

        public Walker(byte[] data) {
            this.info = data;
            this.numOfEntries = ByteArray.readU16bit(data, 0);
        }

        public final int size() {
            return this.numOfEntries;
        }

        public void parse() throws BadBytecode {
            int n = this.numOfEntries;
            int pos = 2;
            for (int i = 0; i < n; ++i) {
                pos = this.stackMapFrames(pos, i);
            }
        }

        int stackMapFrames(int pos, int nth) throws BadBytecode {
            int type = this.info[pos] & 0xFF;
            if (type < 64) {
                this.sameFrame(pos, type);
                ++pos;
            } else if (type < 128) {
                pos = this.sameLocals(pos, type);
            } else {
                if (type < 247) {
                    throw new BadBytecode("bad frame_type " + type + " in StackMapTable (pos: " + pos + ", frame no.:" + nth + ")");
                }
                if (type == 247) {
                    pos = this.sameLocals(pos, type);
                } else if (type < 251) {
                    int offset = ByteArray.readU16bit(this.info, pos + 1);
                    this.chopFrame(pos, offset, 251 - type);
                    pos += 3;
                } else if (type == 251) {
                    int offset = ByteArray.readU16bit(this.info, pos + 1);
                    this.sameFrame(pos, offset);
                    pos += 3;
                } else {
                    pos = type < 255 ? this.appendFrame(pos, type) : this.fullFrame(pos);
                }
            }
            return pos;
        }

        public void sameFrame(int pos, int offsetDelta) throws BadBytecode {
        }

        private int sameLocals(int pos, int type) throws BadBytecode {
            int offset;
            int top = pos;
            if (type < 128) {
                offset = type - 64;
            } else {
                offset = ByteArray.readU16bit(this.info, pos + 1);
                pos += 2;
            }
            int tag = this.info[pos + 1] & 0xFF;
            int data = 0;
            if (tag == 7 || tag == 8) {
                data = ByteArray.readU16bit(this.info, pos + 2);
                this.objectOrUninitialized(tag, data, pos + 2);
                pos += 2;
            }
            this.sameLocals(top, offset, tag, data);
            return pos + 2;
        }

        public void sameLocals(int pos, int offsetDelta, int stackTag, int stackData) throws BadBytecode {
        }

        public void chopFrame(int pos, int offsetDelta, int k) throws BadBytecode {
        }

        private int appendFrame(int pos, int type) throws BadBytecode {
            int k = type - 251;
            int offset = ByteArray.readU16bit(this.info, pos + 1);
            int[] tags = new int[k];
            int[] data = new int[k];
            int p = pos + 3;
            for (int i = 0; i < k; ++i) {
                int tag;
                tags[i] = tag = this.info[p] & 0xFF;
                if (tag == 7 || tag == 8) {
                    data[i] = ByteArray.readU16bit(this.info, p + 1);
                    this.objectOrUninitialized(tag, data[i], p + 1);
                    p += 3;
                    continue;
                }
                data[i] = 0;
                ++p;
            }
            this.appendFrame(pos, offset, tags, data);
            return p;
        }

        public void appendFrame(int pos, int offsetDelta, int[] tags, int[] data) throws BadBytecode {
        }

        private int fullFrame(int pos) throws BadBytecode {
            int offset = ByteArray.readU16bit(this.info, pos + 1);
            int numOfLocals = ByteArray.readU16bit(this.info, pos + 3);
            int[] localsTags = new int[numOfLocals];
            int[] localsData = new int[numOfLocals];
            int p = this.verifyTypeInfo(pos + 5, numOfLocals, localsTags, localsData);
            int numOfItems = ByteArray.readU16bit(this.info, p);
            int[] itemsTags = new int[numOfItems];
            int[] itemsData = new int[numOfItems];
            p = this.verifyTypeInfo(p + 2, numOfItems, itemsTags, itemsData);
            this.fullFrame(pos, offset, localsTags, localsData, itemsTags, itemsData);
            return p;
        }

        public void fullFrame(int pos, int offsetDelta, int[] localTags, int[] localData, int[] stackTags, int[] stackData) throws BadBytecode {
        }

        private int verifyTypeInfo(int pos, int n, int[] tags, int[] data) {
            for (int i = 0; i < n; ++i) {
                int tag;
                tags[i] = tag = this.info[pos++] & 0xFF;
                if (tag != 7 && tag != 8) continue;
                data[i] = ByteArray.readU16bit(this.info, pos);
                this.objectOrUninitialized(tag, data[i], pos);
                pos += 2;
            }
            return pos;
        }

        public void objectOrUninitialized(int tag, int data, int pos) {
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

