/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.Removal;

public final class WSBoolRecord
extends StandardRecord {
    public static final short sid = 129;
    private static final BitField autobreaks = BitFieldFactory.getInstance(1);
    private static final BitField dialog = BitFieldFactory.getInstance(16);
    private static final BitField applystyles = BitFieldFactory.getInstance(32);
    private static final BitField rowsumsbelow = BitFieldFactory.getInstance(64);
    private static final BitField rowsumsright = BitFieldFactory.getInstance(128);
    private static final BitField fittopage = BitFieldFactory.getInstance(1);
    private static final BitField displayguts = BitFieldFactory.getInstance(6);
    private static final BitField alternateexpression = BitFieldFactory.getInstance(64);
    private static final BitField alternateformula = BitFieldFactory.getInstance(128);
    private byte field_1_wsbool;
    private byte field_2_wsbool;

    public WSBoolRecord() {
    }

    public WSBoolRecord(WSBoolRecord other) {
        super(other);
        this.field_1_wsbool = other.field_1_wsbool;
        this.field_2_wsbool = other.field_2_wsbool;
    }

    public WSBoolRecord(RecordInputStream in) {
        byte[] data = in.readRemainder();
        this.field_1_wsbool = data[1];
        this.field_2_wsbool = data[0];
    }

    public void setWSBool1(byte bool1) {
        this.field_1_wsbool = bool1;
    }

    public void setAutobreaks(boolean ab) {
        this.field_1_wsbool = autobreaks.setByteBoolean(this.field_1_wsbool, ab);
    }

    public void setDialog(boolean isDialog) {
        this.field_1_wsbool = dialog.setByteBoolean(this.field_1_wsbool, isDialog);
    }

    public void setRowSumsBelow(boolean below) {
        this.field_1_wsbool = rowsumsbelow.setByteBoolean(this.field_1_wsbool, below);
    }

    public void setRowSumsRight(boolean right) {
        this.field_1_wsbool = rowsumsright.setByteBoolean(this.field_1_wsbool, right);
    }

    public void setWSBool2(byte bool2) {
        this.field_2_wsbool = bool2;
    }

    public void setFitToPage(boolean fit2page) {
        this.field_2_wsbool = fittopage.setByteBoolean(this.field_2_wsbool, fit2page);
    }

    public void setDisplayGuts(boolean guts) {
        this.field_2_wsbool = displayguts.setByteBoolean(this.field_2_wsbool, guts);
    }

    public void setAlternateExpression(boolean altexp) {
        this.field_2_wsbool = alternateexpression.setByteBoolean(this.field_2_wsbool, altexp);
    }

    public void setAlternateFormula(boolean formula) {
        this.field_2_wsbool = alternateformula.setByteBoolean(this.field_2_wsbool, formula);
    }

    public byte getWSBool1() {
        return this.field_1_wsbool;
    }

    public boolean getAutobreaks() {
        return autobreaks.isSet(this.field_1_wsbool);
    }

    public boolean getDialog() {
        return dialog.isSet(this.field_1_wsbool);
    }

    public boolean getRowSumsBelow() {
        return rowsumsbelow.isSet(this.field_1_wsbool);
    }

    public boolean getRowSumsRight() {
        return rowsumsright.isSet(this.field_1_wsbool);
    }

    public byte getWSBool2() {
        return this.field_2_wsbool;
    }

    public boolean getFitToPage() {
        return fittopage.isSet(this.field_2_wsbool);
    }

    public boolean getDisplayGuts() {
        return displayguts.isSet(this.field_2_wsbool);
    }

    public boolean getAlternateExpression() {
        return alternateexpression.isSet(this.field_2_wsbool);
    }

    public boolean getAlternateFormula() {
        return alternateformula.isSet(this.field_2_wsbool);
    }

    @Override
    public String toString() {
        return "[WSBOOL]\n    .wsbool1        = " + Integer.toHexString(this.getWSBool1()) + "\n        .autobreaks = " + this.getAutobreaks() + "\n        .dialog     = " + this.getDialog() + "\n        .rowsumsbelw= " + this.getRowSumsBelow() + "\n        .rowsumsrigt= " + this.getRowSumsRight() + "\n    .wsbool2        = " + Integer.toHexString(this.getWSBool2()) + "\n        .fittopage  = " + this.getFitToPage() + "\n        .displayguts= " + this.getDisplayGuts() + "\n        .alternateex= " + this.getAlternateExpression() + "\n        .alternatefo= " + this.getAlternateFormula() + "\n[/WSBOOL]\n";
    }

    @Override
    public void serialize(LittleEndianOutput out) {
        out.writeByte(this.getWSBool2());
        out.writeByte(this.getWSBool1());
    }

    @Override
    protected int getDataSize() {
        return 2;
    }

    @Override
    public short getSid() {
        return 129;
    }

    @Deprecated
    @Removal(version="5.0.0")
    public WSBoolRecord clone() {
        return this.copy();
    }

    @Override
    public WSBoolRecord copy() {
        return new WSBoolRecord(this);
    }
}

