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
import javassist.bytecode.ConstPool;
import javassist.bytecode.ExceptionTableEntry;

public class ExceptionTable
implements Cloneable {
    private ConstPool constPool;
    private List<ExceptionTableEntry> entries;

    public ExceptionTable(ConstPool cp) {
        this.constPool = cp;
        this.entries = new ArrayList<ExceptionTableEntry>();
    }

    ExceptionTable(ConstPool cp, DataInputStream in) throws IOException {
        this.constPool = cp;
        int length = in.readUnsignedShort();
        ArrayList<ExceptionTableEntry> list = new ArrayList<ExceptionTableEntry>(length);
        for (int i = 0; i < length; ++i) {
            int start = in.readUnsignedShort();
            int end = in.readUnsignedShort();
            int handle = in.readUnsignedShort();
            int type = in.readUnsignedShort();
            list.add(new ExceptionTableEntry(start, end, handle, type));
        }
        this.entries = list;
    }

    public Object clone() throws CloneNotSupportedException {
        ExceptionTable r = (ExceptionTable)super.clone();
        r.entries = new ArrayList<ExceptionTableEntry>(this.entries);
        return r;
    }

    public int size() {
        return this.entries.size();
    }

    public int startPc(int nth) {
        return this.entries.get((int)nth).startPc;
    }

    public void setStartPc(int nth, int value) {
        this.entries.get((int)nth).startPc = value;
    }

    public int endPc(int nth) {
        return this.entries.get((int)nth).endPc;
    }

    public void setEndPc(int nth, int value) {
        this.entries.get((int)nth).endPc = value;
    }

    public int handlerPc(int nth) {
        return this.entries.get((int)nth).handlerPc;
    }

    public void setHandlerPc(int nth, int value) {
        this.entries.get((int)nth).handlerPc = value;
    }

    public int catchType(int nth) {
        return this.entries.get((int)nth).catchType;
    }

    public void setCatchType(int nth, int value) {
        this.entries.get((int)nth).catchType = value;
    }

    public void add(int index, ExceptionTable table, int offset) {
        int len = table.size();
        while (--len >= 0) {
            ExceptionTableEntry e = table.entries.get(len);
            this.add(index, e.startPc + offset, e.endPc + offset, e.handlerPc + offset, e.catchType);
        }
    }

    public void add(int index, int start, int end, int handler, int type) {
        if (start < end) {
            this.entries.add(index, new ExceptionTableEntry(start, end, handler, type));
        }
    }

    public void add(int start, int end, int handler, int type) {
        if (start < end) {
            this.entries.add(new ExceptionTableEntry(start, end, handler, type));
        }
    }

    public void remove(int index) {
        this.entries.remove(index);
    }

    public ExceptionTable copy(ConstPool newCp, Map<String, String> classnames) {
        ExceptionTable et = new ExceptionTable(newCp);
        ConstPool srcCp = this.constPool;
        for (ExceptionTableEntry e : this.entries) {
            int type = srcCp.copy(e.catchType, newCp, classnames);
            et.add(e.startPc, e.endPc, e.handlerPc, type);
        }
        return et;
    }

    void shiftPc(int where, int gapLength, boolean exclusive) {
        for (ExceptionTableEntry e : this.entries) {
            e.startPc = ExceptionTable.shiftPc(e.startPc, where, gapLength, exclusive);
            e.endPc = ExceptionTable.shiftPc(e.endPc, where, gapLength, exclusive);
            e.handlerPc = ExceptionTable.shiftPc(e.handlerPc, where, gapLength, exclusive);
        }
    }

    private static int shiftPc(int pc, int where, int gapLength, boolean exclusive) {
        if (pc > where || exclusive && pc == where) {
            pc += gapLength;
        }
        return pc;
    }

    void write(DataOutputStream out) throws IOException {
        out.writeShort(this.size());
        for (ExceptionTableEntry e : this.entries) {
            out.writeShort(e.startPc);
            out.writeShort(e.endPc);
            out.writeShort(e.handlerPc);
            out.writeShort(e.catchType);
        }
    }
}

