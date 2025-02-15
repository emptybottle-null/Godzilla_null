/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.ExtSSTRecord;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.util.IntMapper;

final class SSTSerializer {
    private final int _numStrings;
    private final int _numUniqueStrings;
    private final IntMapper<UnicodeString> strings;
    private final int[] bucketAbsoluteOffsets;
    private final int[] bucketRelativeOffsets;

    public SSTSerializer(IntMapper<UnicodeString> strings, int numStrings, int numUniqueStrings) {
        this.strings = strings;
        this._numStrings = numStrings;
        this._numUniqueStrings = numUniqueStrings;
        int infoRecs = ExtSSTRecord.getNumberOfInfoRecsForStrings(strings.size());
        this.bucketAbsoluteOffsets = new int[infoRecs];
        this.bucketRelativeOffsets = new int[infoRecs];
    }

    public void serialize(ContinuableRecordOutput out) {
        out.writeInt(this._numStrings);
        out.writeInt(this._numUniqueStrings);
        for (int k = 0; k < this.strings.size(); ++k) {
            if (k % 8 == 0) {
                int rOff = out.getTotalSize();
                int index = k / 8;
                if (index < 128) {
                    this.bucketAbsoluteOffsets[index] = rOff;
                    this.bucketRelativeOffsets[index] = rOff;
                }
            }
            UnicodeString s = this.getUnicodeString(k);
            s.serialize(out);
        }
    }

    private UnicodeString getUnicodeString(int index) {
        return SSTSerializer.getUnicodeString(this.strings, index);
    }

    private static UnicodeString getUnicodeString(IntMapper<UnicodeString> strings, int index) {
        return strings.get(index);
    }

    public int[] getBucketAbsoluteOffsets() {
        return this.bucketAbsoluteOffsets;
    }

    public int[] getBucketRelativeOffsets() {
        return this.bucketRelativeOffsets;
    }
}

