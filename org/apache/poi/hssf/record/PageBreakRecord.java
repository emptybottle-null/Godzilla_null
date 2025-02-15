/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;

public abstract class PageBreakRecord
extends StandardRecord {
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private final ArrayList<Break> _breaks = new ArrayList();
    private final Map<Integer, Break> _breakMap = new HashMap<Integer, Break>();

    protected PageBreakRecord() {
    }

    protected PageBreakRecord(PageBreakRecord other) {
        this._breaks.addAll(other._breaks);
        this.initMap();
    }

    public PageBreakRecord(RecordInputStream in) {
        int nBreaks = in.readShort();
        this._breaks.ensureCapacity(nBreaks + 2);
        for (int k = 0; k < nBreaks; ++k) {
            this._breaks.add(new Break(in));
        }
        this.initMap();
    }

    private void initMap() {
        this._breaks.forEach(br -> this._breakMap.put(br.main, (Break)br));
    }

    public boolean isEmpty() {
        return this._breaks.isEmpty();
    }

    @Override
    protected int getDataSize() {
        return 2 + this._breaks.size() * 6;
    }

    @Override
    public final void serialize(LittleEndianOutput out) {
        int nBreaks = this._breaks.size();
        out.writeShort(nBreaks);
        for (Break aBreak : this._breaks) {
            aBreak.serialize(out);
        }
    }

    public int getNumBreaks() {
        return this._breaks.size();
    }

    public final Iterator<Break> getBreaksIterator() {
        return this._breaks.iterator();
    }

    @Override
    public String toString() {
        String subLabel;
        String mainLabel;
        String label;
        StringBuilder retval = new StringBuilder();
        if (this.getSid() == 27) {
            label = "HORIZONTALPAGEBREAK";
            mainLabel = "row";
            subLabel = "col";
        } else {
            label = "VERTICALPAGEBREAK";
            mainLabel = "column";
            subLabel = "row";
        }
        retval.append("[" + label + "]").append("\n");
        retval.append("     .sid        =").append(this.getSid()).append("\n");
        retval.append("     .numbreaks =").append(this.getNumBreaks()).append("\n");
        Iterator<Break> iterator = this.getBreaksIterator();
        for (int k = 0; k < this.getNumBreaks(); ++k) {
            Break region = iterator.next();
            retval.append("     .").append(mainLabel).append(" (zero-based) =").append(region.main).append("\n");
            retval.append("     .").append(subLabel).append("From    =").append(region.subFrom).append("\n");
            retval.append("     .").append(subLabel).append("To      =").append(region.subTo).append("\n");
        }
        retval.append("[" + label + "]").append("\n");
        return retval.toString();
    }

    public void addBreak(int main, int subFrom, int subTo) {
        Integer key = main;
        Break region = this._breakMap.get(key);
        if (region == null) {
            region = new Break(main, subFrom, subTo);
            this._breakMap.put(key, region);
            this._breaks.add(region);
        } else {
            region.main = main;
            region.subFrom = subFrom;
            region.subTo = subTo;
        }
    }

    public final void removeBreak(int main) {
        Integer rowKey = main;
        Break region = this._breakMap.get(rowKey);
        this._breaks.remove(region);
        this._breakMap.remove(rowKey);
    }

    public final Break getBreak(int main) {
        Integer rowKey = main;
        return this._breakMap.get(rowKey);
    }

    public final int[] getBreaks() {
        int count = this.getNumBreaks();
        if (count < 1) {
            return EMPTY_INT_ARRAY;
        }
        int[] result = new int[count];
        for (int i = 0; i < count; ++i) {
            Break breakItem = this._breaks.get(i);
            result[i] = breakItem.main;
        }
        return result;
    }

    @Override
    public abstract PageBreakRecord copy();

    public static final class Break {
        public static final int ENCODED_SIZE = 6;
        public int main;
        public int subFrom;
        public int subTo;

        public Break(Break other) {
            this.main = other.main;
            this.subFrom = other.subFrom;
            this.subTo = other.subTo;
        }

        public Break(int main, int subFrom, int subTo) {
            this.main = main;
            this.subFrom = subFrom;
            this.subTo = subTo;
        }

        public Break(RecordInputStream in) {
            this.main = in.readUShort() - 1;
            this.subFrom = in.readUShort();
            this.subTo = in.readUShort();
        }

        public void serialize(LittleEndianOutput out) {
            out.writeShort(this.main + 1);
            out.writeShort(this.subFrom);
            out.writeShort(this.subTo);
        }
    }
}

