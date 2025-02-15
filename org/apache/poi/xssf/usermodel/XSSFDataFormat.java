/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.model.StylesTable;

public class XSSFDataFormat
implements DataFormat {
    private final StylesTable stylesSource;

    protected XSSFDataFormat(StylesTable stylesSource) {
        this.stylesSource = stylesSource;
    }

    @Override
    public short getFormat(String format) {
        int idx = BuiltinFormats.getBuiltinFormat(format);
        if (idx == -1) {
            idx = this.stylesSource.putNumberFormat(format);
        }
        return (short)idx;
    }

    @Override
    public String getFormat(short index) {
        String fmt = this.stylesSource.getNumberFormatAt(index);
        if (fmt == null) {
            fmt = BuiltinFormats.getBuiltinFormat(index);
        }
        return fmt;
    }

    public void putFormat(short index, String format) {
        this.stylesSource.putNumberFormat(index, format);
    }
}

