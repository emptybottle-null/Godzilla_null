/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.util.IntMapper;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

class SSTDeserializer {
    private static POILogger logger = POILogFactory.getLogger(SSTDeserializer.class);
    private IntMapper<UnicodeString> strings;

    public SSTDeserializer(IntMapper<UnicodeString> strings) {
        this.strings = strings;
    }

    public void manufactureStrings(int stringCount, RecordInputStream in) {
        for (int i = 0; i < stringCount; ++i) {
            UnicodeString str;
            if (in.available() == 0 && !in.hasNextRecord()) {
                logger.log(7, "Ran out of data before creating all the strings! String at index " + i + "");
                str = new UnicodeString("");
            } else {
                str = new UnicodeString(in);
            }
            SSTDeserializer.addToStringTable(this.strings, str);
        }
    }

    public static void addToStringTable(IntMapper<UnicodeString> strings, UnicodeString string) {
        strings.add(string);
    }
}

