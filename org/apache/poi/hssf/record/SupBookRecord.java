/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.StringUtil;

public final class SupBookRecord
extends StandardRecord {
    private static final POILogger logger = POILogFactory.getLogger(SupBookRecord.class);
    public static final short sid = 430;
    private static final short SMALL_RECORD_SIZE = 4;
    private static final short TAG_INTERNAL_REFERENCES = 1025;
    private static final short TAG_ADD_IN_FUNCTIONS = 14849;
    static final char CH_VOLUME = '\u0001';
    static final char CH_SAME_VOLUME = '\u0002';
    static final char CH_DOWN_DIR = '\u0003';
    static final char CH_UP_DIR = '\u0004';
    static final char CH_LONG_VOLUME = '\u0005';
    static final char CH_STARTUP_DIR = '\u0006';
    static final char CH_ALT_STARTUP_DIR = '\u0007';
    static final char CH_LIB_DIR = '\b';
    static final String PATH_SEPERATOR = System.getProperty("file.separator");
    private short field_1_number_of_sheets;
    private String field_2_encoded_url;
    private String[] field_3_sheet_names;
    private boolean _isAddInFunctions;

    public SupBookRecord(SupBookRecord other) {
        super(other);
        this.field_1_number_of_sheets = other.field_1_number_of_sheets;
        this.field_2_encoded_url = other.field_2_encoded_url;
        this.field_3_sheet_names = other.field_3_sheet_names;
        this._isAddInFunctions = other._isAddInFunctions;
    }

    private SupBookRecord(boolean isAddInFuncs, short numberOfSheets) {
        this.field_1_number_of_sheets = numberOfSheets;
        this.field_2_encoded_url = null;
        this.field_3_sheet_names = null;
        this._isAddInFunctions = isAddInFuncs;
    }

    public SupBookRecord(String url, String[] sheetNames) {
        this.field_1_number_of_sheets = (short)sheetNames.length;
        this.field_2_encoded_url = url;
        this.field_3_sheet_names = sheetNames;
        this._isAddInFunctions = false;
    }

    public static SupBookRecord createInternalReferences(short numberOfSheets) {
        return new SupBookRecord(false, numberOfSheets);
    }

    public static SupBookRecord createAddInFunctions() {
        return new SupBookRecord(true, 1);
    }

    public static SupBookRecord createExternalReferences(String url, String[] sheetNames) {
        return new SupBookRecord(url, sheetNames);
    }

    public boolean isExternalReferences() {
        return this.field_3_sheet_names != null;
    }

    public boolean isInternalReferences() {
        return this.field_3_sheet_names == null && !this._isAddInFunctions;
    }

    public boolean isAddInFunctions() {
        return this.field_3_sheet_names == null && this._isAddInFunctions;
    }

    public SupBookRecord(RecordInputStream in) {
        int recLen = in.remaining();
        this.field_1_number_of_sheets = in.readShort();
        if (recLen > 4) {
            this._isAddInFunctions = false;
            this.field_2_encoded_url = in.readString();
            String[] sheetNames = new String[this.field_1_number_of_sheets];
            for (int i = 0; i < sheetNames.length; ++i) {
                sheetNames[i] = in.readString();
            }
            this.field_3_sheet_names = sheetNames;
            return;
        }
        this.field_2_encoded_url = null;
        this.field_3_sheet_names = null;
        short nextShort = in.readShort();
        if (nextShort == 1025) {
            this._isAddInFunctions = false;
        } else if (nextShort == 14849) {
            this._isAddInFunctions = true;
            if (this.field_1_number_of_sheets != 1) {
                throw new RuntimeException("Expected 0x0001 for number of sheets field in 'Add-In Functions' but got (" + this.field_1_number_of_sheets + ")");
            }
        } else {
            throw new RuntimeException("invalid EXTERNALBOOK code (" + Integer.toHexString(nextShort) + ")");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[SUPBOOK ");
        if (this.isExternalReferences()) {
            sb.append("External References]\n");
            sb.append(" .url     = ").append(this.getURL()).append("\n");
            sb.append(" .nSheets = ").append(this.field_1_number_of_sheets).append("\n");
            for (String sheetname : this.field_3_sheet_names) {
                sb.append("    .name = ").append(sheetname).append("\n");
            }
            sb.append("[/SUPBOOK");
        } else if (this._isAddInFunctions) {
            sb.append("Add-In Functions");
        } else {
            sb.append("Internal References");
            sb.append(" nSheets=").append(this.field_1_number_of_sheets);
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    protected int getDataSize() {
        if (!this.isExternalReferences()) {
            return 4;
        }
        int sum = 2;
        sum += StringUtil.getEncodedSize(this.field_2_encoded_url);
        for (String field_3_sheet_name : this.field_3_sheet_names) {
            sum += StringUtil.getEncodedSize(field_3_sheet_name);
        }
        return sum;
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeShort(this.field_1_number_of_sheets);
        if (this.isExternalReferences()) {
            StringUtil.writeUnicodeString(out, this.field_2_encoded_url);
            for (String field_3_sheet_name : this.field_3_sheet_names) {
                StringUtil.writeUnicodeString(out, field_3_sheet_name);
            }
        } else {
            int field2val = this._isAddInFunctions ? 14849 : 1025;
            out.writeShort(field2val);
        }
    }

    public void setNumberOfSheets(short number) {
        this.field_1_number_of_sheets = number;
    }

    public short getNumberOfSheets() {
        return this.field_1_number_of_sheets;
    }

    @Override
    public short getSid() {
        return 430;
    }

    public String getURL() {
        String encodedUrl = this.field_2_encoded_url;
        switch (encodedUrl.charAt(0)) {
            case '\u0000': {
                return encodedUrl.substring(1);
            }
            case '\u0001': {
                return SupBookRecord.decodeFileName(encodedUrl);
            }
            case '\u0002': {
                return encodedUrl.substring(1);
            }
        }
        return encodedUrl;
    }

    private static String decodeFileName(String encodedUrl) {
        StringBuilder sb = new StringBuilder();
        block8: for (int i = 1; i < encodedUrl.length(); ++i) {
            char c = encodedUrl.charAt(i);
            switch (c) {
                case '\u0001': {
                    char driveLetter = encodedUrl.charAt(++i);
                    if (driveLetter == '@') {
                        sb.append("\\\\");
                        continue block8;
                    }
                    sb.append(driveLetter).append(":");
                    continue block8;
                }
                case '\u0002': {
                    sb.append(PATH_SEPERATOR);
                    continue block8;
                }
                case '\u0003': {
                    sb.append(PATH_SEPERATOR);
                    continue block8;
                }
                case '\u0004': {
                    sb.append("..").append(PATH_SEPERATOR);
                    continue block8;
                }
                case '\u0005': {
                    logger.log(5, "Found unexpected key: ChLongVolume - IGNORING");
                    continue block8;
                }
                case '\u0006': 
                case '\u0007': 
                case '\b': {
                    logger.log(5, "EXCEL.EXE path unkown - using this directoy instead: .");
                    sb.append(".").append(PATH_SEPERATOR);
                    continue block8;
                }
                default: {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public String[] getSheetNames() {
        return (String[])this.field_3_sheet_names.clone();
    }

    public void setURL(String pUrl) {
        this.field_2_encoded_url = this.field_2_encoded_url.substring(0, 1) + pUrl;
    }

    @Override
    public SupBookRecord copy() {
        return new SupBookRecord(this);
    }
}

