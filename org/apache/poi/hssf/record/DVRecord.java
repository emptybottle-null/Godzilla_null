/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.hssf.record.common.UnicodeString;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.BitField;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;
import org.apache.poi.util.StringUtil;

public final class DVRecord
extends StandardRecord {
    public static final short sid = 446;
    private static final UnicodeString NULL_TEXT_STRING = new UnicodeString("\u0000");
    private static final BitField opt_data_type = new BitField(15);
    private static final BitField opt_error_style = new BitField(112);
    private static final BitField opt_string_list_formula = new BitField(128);
    private static final BitField opt_empty_cell_allowed = new BitField(256);
    private static final BitField opt_suppress_dropdown_arrow = new BitField(512);
    private static final BitField opt_show_prompt_on_cell_selected = new BitField(262144);
    private static final BitField opt_show_error_on_invalid_value = new BitField(524288);
    private static final BitField opt_condition_operator = new BitField(0x700000);
    private int _option_flags;
    private final UnicodeString _promptTitle;
    private final UnicodeString _errorTitle;
    private final UnicodeString _promptText;
    private final UnicodeString _errorText;
    private short _not_used_1 = (short)16352;
    private final Formula _formula1;
    private short _not_used_2 = 0;
    private final Formula _formula2;
    private final CellRangeAddressList _regions;

    public DVRecord(DVRecord other) {
        super(other);
        this._option_flags = other._option_flags;
        this._promptTitle = other._promptTitle.copy();
        this._errorTitle = other._errorTitle.copy();
        this._promptText = other._promptText.copy();
        this._errorText = other._errorText.copy();
        this._not_used_1 = other._not_used_1;
        this._formula1 = other._formula1 == null ? null : other._formula1.copy();
        this._not_used_2 = other._not_used_2;
        this._formula2 = other._formula2 == null ? null : other._formula2.copy();
        this._regions = other._regions == null ? null : other._regions.copy();
    }

    public DVRecord(int validationType, int operator2, int errorStyle, boolean emptyCellAllowed, boolean suppressDropDownArrow, boolean isExplicitList, boolean showPromptBox, String promptTitle, String promptText, boolean showErrorBox, String errorTitle, String errorText, Ptg[] formula1, Ptg[] formula2, CellRangeAddressList regions) {
        if (promptTitle != null && promptTitle.length() > 32) {
            throw new IllegalStateException("Prompt-title cannot be longer than 32 characters, but had: " + promptTitle);
        }
        if (promptText != null && promptText.length() > 255) {
            throw new IllegalStateException("Prompt-text cannot be longer than 255 characters, but had: " + promptText);
        }
        if (errorTitle != null && errorTitle.length() > 32) {
            throw new IllegalStateException("Error-title cannot be longer than 32 characters, but had: " + errorTitle);
        }
        if (errorText != null && errorText.length() > 255) {
            throw new IllegalStateException("Error-text cannot be longer than 255 characters, but had: " + errorText);
        }
        int flags = 0;
        flags = opt_data_type.setValue(flags, validationType);
        flags = opt_condition_operator.setValue(flags, operator2);
        flags = opt_error_style.setValue(flags, errorStyle);
        flags = opt_empty_cell_allowed.setBoolean(flags, emptyCellAllowed);
        flags = opt_suppress_dropdown_arrow.setBoolean(flags, suppressDropDownArrow);
        flags = opt_string_list_formula.setBoolean(flags, isExplicitList);
        flags = opt_show_prompt_on_cell_selected.setBoolean(flags, showPromptBox);
        this._option_flags = flags = opt_show_error_on_invalid_value.setBoolean(flags, showErrorBox);
        this._promptTitle = DVRecord.resolveTitleText(promptTitle);
        this._promptText = DVRecord.resolveTitleText(promptText);
        this._errorTitle = DVRecord.resolveTitleText(errorTitle);
        this._errorText = DVRecord.resolveTitleText(errorText);
        this._formula1 = Formula.create(formula1);
        this._formula2 = Formula.create(formula2);
        this._regions = regions;
    }

    public DVRecord(RecordInputStream in) {
        this._option_flags = in.readInt();
        this._promptTitle = DVRecord.readUnicodeString(in);
        this._errorTitle = DVRecord.readUnicodeString(in);
        this._promptText = DVRecord.readUnicodeString(in);
        this._errorText = DVRecord.readUnicodeString(in);
        int field_size_first_formula = in.readUShort();
        this._not_used_1 = in.readShort();
        this._formula1 = Formula.read(field_size_first_formula, in);
        int field_size_sec_formula = in.readUShort();
        this._not_used_2 = in.readShort();
        this._formula2 = Formula.read(field_size_sec_formula, in);
        this._regions = new CellRangeAddressList(in);
    }

    public int getDataType() {
        return opt_data_type.getValue(this._option_flags);
    }

    public int getErrorStyle() {
        return opt_error_style.getValue(this._option_flags);
    }

    public boolean getListExplicitFormula() {
        return opt_string_list_formula.isSet(this._option_flags);
    }

    public boolean getEmptyCellAllowed() {
        return opt_empty_cell_allowed.isSet(this._option_flags);
    }

    public boolean getSuppressDropdownArrow() {
        return opt_suppress_dropdown_arrow.isSet(this._option_flags);
    }

    public boolean getShowPromptOnCellSelected() {
        return opt_show_prompt_on_cell_selected.isSet(this._option_flags);
    }

    public boolean getShowErrorOnInvalidValue() {
        return opt_show_error_on_invalid_value.isSet(this._option_flags);
    }

    public int getConditionOperator() {
        return opt_condition_operator.getValue(this._option_flags);
    }

    public String getPromptTitle() {
        return DVRecord.resolveTitleString(this._promptTitle);
    }

    public String getErrorTitle() {
        return DVRecord.resolveTitleString(this._errorTitle);
    }

    public String getPromptText() {
        return DVRecord.resolveTitleString(this._promptText);
    }

    public String getErrorText() {
        return DVRecord.resolveTitleString(this._errorText);
    }

    public Ptg[] getFormula1() {
        return Formula.getTokens(this._formula1);
    }

    public Ptg[] getFormula2() {
        return Formula.getTokens(this._formula2);
    }

    public CellRangeAddressList getCellRangeAddress() {
        return this._regions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[DV]\n");
        sb.append(" options=").append(Integer.toHexString(this._option_flags));
        sb.append(" title-prompt=").append(DVRecord.formatTextTitle(this._promptTitle));
        sb.append(" title-error=").append(DVRecord.formatTextTitle(this._errorTitle));
        sb.append(" text-prompt=").append(DVRecord.formatTextTitle(this._promptText));
        sb.append(" text-error=").append(DVRecord.formatTextTitle(this._errorText));
        sb.append("\n");
        DVRecord.appendFormula(sb, "Formula 1:", this._formula1);
        DVRecord.appendFormula(sb, "Formula 2:", this._formula2);
        sb.append("Regions: ");
        int nRegions = this._regions.countRanges();
        for (int i = 0; i < nRegions; ++i) {
            if (i > 0) {
                sb.append(", ");
            }
            CellRangeAddress addr = this._regions.getCellRangeAddress(i);
            sb.append('(').append(addr.getFirstRow()).append(',').append(addr.getLastRow());
            sb.append(',').append(addr.getFirstColumn()).append(',').append(addr.getLastColumn()).append(')');
        }
        sb.append("\n");
        sb.append("[/DV]");
        return sb.toString();
    }

    private static String formatTextTitle(UnicodeString us) {
        String str = us.getString();
        if (str.length() == 1 && str.charAt(0) == '\u0000') {
            return "'\\0'";
        }
        return str;
    }

    private static void appendFormula(StringBuilder sb, String label, Formula f) {
        sb.append(label);
        if (f == null) {
            sb.append("<empty>\n");
            return;
        }
        Ptg[] ptgs = f.getTokens();
        sb.append('\n');
        for (Ptg ptg : ptgs) {
            sb.append('\t').append(ptg).append('\n');
        }
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeInt(this._option_flags);
        DVRecord.serializeUnicodeString(this._promptTitle, out);
        DVRecord.serializeUnicodeString(this._errorTitle, out);
        DVRecord.serializeUnicodeString(this._promptText, out);
        DVRecord.serializeUnicodeString(this._errorText, out);
        out.writeShort(this._formula1.getEncodedTokenSize());
        out.writeShort(this._not_used_1);
        this._formula1.serializeTokens(out);
        out.writeShort(this._formula2.getEncodedTokenSize());
        out.writeShort(this._not_used_2);
        this._formula2.serializeTokens(out);
        this._regions.serialize(out);
    }

    private static UnicodeString resolveTitleText(String str) {
        if (str == null || str.length() < 1) {
            return NULL_TEXT_STRING;
        }
        return new UnicodeString(str);
    }

    private static String resolveTitleString(UnicodeString us) {
        if (us == null || us.equals(NULL_TEXT_STRING)) {
            return null;
        }
        return us.getString();
    }

    private static UnicodeString readUnicodeString(RecordInputStream in) {
        return new UnicodeString(in);
    }

    private static void serializeUnicodeString(UnicodeString us, LittleEndianOutput out) {
        StringUtil.writeUnicodeString(out, us.getString());
    }

    private static int getUnicodeStringSize(UnicodeString us) {
        String str = us.getString();
        return 3 + str.length() * (StringUtil.hasMultibyte(str) ? 2 : 1);
    }

    @Override
    protected int getDataSize() {
        int size = 12;
        size += DVRecord.getUnicodeStringSize(this._promptTitle);
        size += DVRecord.getUnicodeStringSize(this._errorTitle);
        size += DVRecord.getUnicodeStringSize(this._promptText);
        size += DVRecord.getUnicodeStringSize(this._errorText);
        size += this._formula1.getEncodedTokenSize();
        size += this._formula2.getEncodedTokenSize();
        return size += this._regions.getSize();
    }

    @Override
    public short getSid() {
        return 446;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public DVRecord clone() {
        return this.copy();
    }

    @Override
    public DVRecord copy() {
        return new DVRecord(this);
    }
}

