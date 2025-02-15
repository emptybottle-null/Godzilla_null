/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import org.apache.poi.hssf.model.InternalWorkbook;
import org.apache.poi.hssf.record.FormatRecord;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormat;

public final class HSSFDataFormat
implements DataFormat {
    private static final String[] _builtinFormats = BuiltinFormats.getAll();
    private final Vector<String> _formats = new Vector();
    private final InternalWorkbook _workbook;
    private boolean _movedBuiltins;

    HSSFDataFormat(InternalWorkbook workbook) {
        this._workbook = workbook;
        for (FormatRecord r : workbook.getFormats()) {
            this.ensureFormatsSize(r.getIndexCode());
            this._formats.set(r.getIndexCode(), r.getFormatString());
        }
    }

    public static List<String> getBuiltinFormats() {
        return Arrays.asList(_builtinFormats);
    }

    public static short getBuiltinFormat(String format) {
        return (short)BuiltinFormats.getBuiltinFormat(format);
    }

    @Override
    public short getFormat(String pFormat) {
        int i;
        String format = pFormat.equalsIgnoreCase("TEXT") ? "@" : pFormat;
        if (!this._movedBuiltins) {
            for (i = 0; i < _builtinFormats.length; ++i) {
                this.ensureFormatsSize(i);
                if (this._formats.get(i) != null) continue;
                this._formats.set(i, _builtinFormats[i]);
            }
            this._movedBuiltins = true;
        }
        for (i = 0; i < this._formats.size(); ++i) {
            if (!format.equals(this._formats.get(i))) continue;
            return (short)i;
        }
        short index = this._workbook.getFormat(format, true);
        this.ensureFormatsSize(index);
        this._formats.set(index, format);
        return index;
    }

    @Override
    public String getFormat(short index) {
        String fmt;
        if (this._movedBuiltins) {
            return this._formats.get(index);
        }
        if (index == -1) {
            return null;
        }
        String string = fmt = this._formats.size() > index ? this._formats.get(index) : null;
        if (_builtinFormats.length > index && _builtinFormats[index] != null) {
            if (fmt != null) {
                return fmt;
            }
            return _builtinFormats[index];
        }
        return fmt;
    }

    public static String getBuiltinFormat(short index) {
        return BuiltinFormats.getBuiltinFormat(index);
    }

    public static int getNumberOfBuiltinBuiltinFormats() {
        return _builtinFormats.length;
    }

    private void ensureFormatsSize(int index) {
        if (this._formats.size() <= index) {
            this._formats.setSize(index + 1);
        }
    }
}

