/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.StringUtil;

public abstract class HeaderFooterBase
extends StandardRecord {
    private boolean field_2_hasMultibyte;
    private String field_3_text;

    protected HeaderFooterBase(String text) {
        this.setText(text);
    }

    protected HeaderFooterBase(HeaderFooterBase other) {
        super(other);
        this.field_2_hasMultibyte = other.field_2_hasMultibyte;
        this.field_3_text = other.field_3_text;
    }

    protected HeaderFooterBase(RecordInputStream in) {
        if (in.remaining() > 0) {
            short field_1_footer_len = in.readShort();
            if (field_1_footer_len == 0) {
                this.field_3_text = "";
                if (in.remaining() == 0) {
                    return;
                }
            }
            boolean bl = this.field_2_hasMultibyte = in.readByte() != 0;
            this.field_3_text = this.field_2_hasMultibyte ? in.readUnicodeLEString(field_1_footer_len) : in.readCompressedUnicode(field_1_footer_len);
        } else {
            this.field_3_text = "";
        }
    }

    public final void setText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("text must not be null");
        }
        this.field_2_hasMultibyte = StringUtil.hasMultibyte(text);
        this.field_3_text = text;
        if (this.getDataSize() > 8224) {
            throw new IllegalArgumentException("Header/Footer string too long (limit is 8224 bytes)");
        }
    }

    private int getTextLength() {
        return this.field_3_text.length();
    }

    public final String getText() {
        return this.field_3_text;
    }

    @Override
    public final void serialize(LittleEndianOutput out) {
        if (this.getTextLength() > 0) {
            out.writeShort(this.getTextLength());
            out.writeByte(this.field_2_hasMultibyte ? 1 : 0);
            if (this.field_2_hasMultibyte) {
                StringUtil.putUnicodeLE(this.field_3_text, out);
            } else {
                StringUtil.putCompressedUnicode(this.field_3_text, out);
            }
        }
    }

    @Override
    protected final int getDataSize() {
        if (this.getTextLength() < 1) {
            return 0;
        }
        return 3 + this.getTextLength() * (this.field_2_hasMultibyte ? 2 : 1);
    }

    @Override
    public abstract HeaderFooterBase copy();
}

