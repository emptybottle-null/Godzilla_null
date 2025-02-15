/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.poi.common.Duplicatable;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.common.ExtRst;
import org.apache.poi.hssf.record.common.FormatRun;
import org.apache.poi.hssf.record.cont.ContinuableRecordInput;
import org.apache.poi.hssf.record.cont.ContinuableRecordOutput;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Removal;

public class UnicodeString
implements Comparable<UnicodeString>,
Duplicatable {
    private static final POILogger _logger = POILogFactory.getLogger(UnicodeString.class);
    private static final BitField highByte = BitFieldFactory.getInstance(1);
    private static final BitField extBit = BitFieldFactory.getInstance(4);
    private static final BitField richText = BitFieldFactory.getInstance(8);
    private short field_1_charCount;
    private byte field_2_optionflags;
    private String field_3_string;
    private List<FormatRun> field_4_format_runs;
    private ExtRst field_5_ext_rst;

    private UnicodeString(UnicodeString other) {
        this.field_1_charCount = other.field_1_charCount;
        this.field_2_optionflags = other.field_2_optionflags;
        this.field_3_string = other.field_3_string;
        this.field_4_format_runs = other.field_4_format_runs == null ? null : other.field_4_format_runs.stream().map(FormatRun::new).collect(Collectors.toList());
        this.field_5_ext_rst = other.field_5_ext_rst == null ? null : other.field_5_ext_rst.copy();
    }

    public UnicodeString(String str) {
        this.setString(str);
    }

    public UnicodeString(RecordInputStream in) {
        this.field_1_charCount = in.readShort();
        this.field_2_optionflags = in.readByte();
        int runCount = 0;
        int extensionLength = 0;
        if (this.isRichText()) {
            runCount = in.readShort();
        }
        if (this.isExtendedText()) {
            extensionLength = in.readInt();
        }
        boolean isCompressed = (this.field_2_optionflags & 1) == 0;
        int cc = this.getCharCount();
        String string = this.field_3_string = isCompressed ? in.readCompressedUnicode(cc) : in.readUnicodeLEString(cc);
        if (this.isRichText() && runCount > 0) {
            this.field_4_format_runs = new ArrayList<FormatRun>(runCount);
            for (int i = 0; i < runCount; ++i) {
                this.field_4_format_runs.add(new FormatRun(in));
            }
        }
        if (this.isExtendedText() && extensionLength > 0) {
            this.field_5_ext_rst = new ExtRst(new ContinuableRecordInput(in), extensionLength);
            if (this.field_5_ext_rst.getDataSize() + 4 != extensionLength) {
                _logger.log(5, "ExtRst was supposed to be " + extensionLength + " bytes long, but seems to actually be " + (this.field_5_ext_rst.getDataSize() + 4));
            }
        }
    }

    public int hashCode() {
        return Objects.hash(this.field_1_charCount, this.field_3_string);
    }

    public boolean equals(Object o) {
        if (!(o instanceof UnicodeString)) {
            return false;
        }
        UnicodeString other = (UnicodeString)o;
        if (this.field_1_charCount != other.field_1_charCount || this.field_2_optionflags != other.field_2_optionflags || !this.field_3_string.equals(other.field_3_string)) {
            return false;
        }
        if (this.field_4_format_runs == null) {
            return other.field_4_format_runs == null;
        }
        if (other.field_4_format_runs == null) {
            return false;
        }
        int size = this.field_4_format_runs.size();
        if (size != other.field_4_format_runs.size()) {
            return false;
        }
        for (int i = 0; i < size; ++i) {
            FormatRun run2;
            FormatRun run1 = this.field_4_format_runs.get(i);
            if (run1.equals(run2 = other.field_4_format_runs.get(i))) continue;
            return false;
        }
        if (this.field_5_ext_rst == null) {
            return other.field_5_ext_rst == null;
        }
        if (other.field_5_ext_rst == null) {
            return false;
        }
        return this.field_5_ext_rst.equals(other.field_5_ext_rst);
    }

    public int getCharCount() {
        if (this.field_1_charCount < 0) {
            return this.field_1_charCount + 65536;
        }
        return this.field_1_charCount;
    }

    public short getCharCountShort() {
        return this.field_1_charCount;
    }

    public void setCharCount(short cc) {
        this.field_1_charCount = cc;
    }

    public byte getOptionFlags() {
        return this.field_2_optionflags;
    }

    public void setOptionFlags(byte of) {
        this.field_2_optionflags = of;
    }

    public String getString() {
        return this.field_3_string;
    }

    public void setString(String string) {
        this.field_3_string = string;
        this.setCharCount((short)this.field_3_string.length());
        boolean useUTF16 = false;
        int strlen = string.length();
        for (int j = 0; j < strlen; ++j) {
            if (string.charAt(j) <= '\u00ff') continue;
            useUTF16 = true;
            break;
        }
        this.field_2_optionflags = useUTF16 ? highByte.setByte(this.field_2_optionflags) : highByte.clearByte(this.field_2_optionflags);
    }

    public int getFormatRunCount() {
        return this.field_4_format_runs == null ? 0 : this.field_4_format_runs.size();
    }

    public FormatRun getFormatRun(int index) {
        if (this.field_4_format_runs == null) {
            return null;
        }
        if (index < 0 || index >= this.field_4_format_runs.size()) {
            return null;
        }
        return this.field_4_format_runs.get(index);
    }

    private int findFormatRunAt(int characterPos) {
        int size = this.field_4_format_runs.size();
        for (int i = 0; i < size; ++i) {
            FormatRun r = this.field_4_format_runs.get(i);
            if (r._character == characterPos) {
                return i;
            }
            if (r._character <= characterPos) continue;
            return -1;
        }
        return -1;
    }

    public void addFormatRun(FormatRun r) {
        int index;
        if (this.field_4_format_runs == null) {
            this.field_4_format_runs = new ArrayList<FormatRun>();
        }
        if ((index = this.findFormatRunAt(r._character)) != -1) {
            this.field_4_format_runs.remove(index);
        }
        this.field_4_format_runs.add(r);
        Collections.sort(this.field_4_format_runs);
        this.field_2_optionflags = richText.setByte(this.field_2_optionflags);
    }

    public Iterator<FormatRun> formatIterator() {
        if (this.field_4_format_runs != null) {
            return this.field_4_format_runs.iterator();
        }
        return null;
    }

    public void removeFormatRun(FormatRun r) {
        this.field_4_format_runs.remove(r);
        if (this.field_4_format_runs.size() == 0) {
            this.field_4_format_runs = null;
            this.field_2_optionflags = richText.clearByte(this.field_2_optionflags);
        }
    }

    public void clearFormatting() {
        this.field_4_format_runs = null;
        this.field_2_optionflags = richText.clearByte(this.field_2_optionflags);
    }

    public ExtRst getExtendedRst() {
        return this.field_5_ext_rst;
    }

    void setExtendedRst(ExtRst ext_rst) {
        this.field_2_optionflags = ext_rst != null ? extBit.setByte(this.field_2_optionflags) : extBit.clearByte(this.field_2_optionflags);
        this.field_5_ext_rst = ext_rst;
    }

    public void swapFontUse(short oldFontIndex, short newFontIndex) {
        if (this.field_4_format_runs != null) {
            for (FormatRun run : this.field_4_format_runs) {
                if (run._fontIndex != oldFontIndex) continue;
                run._fontIndex = newFontIndex;
            }
        }
    }

    public String toString() {
        return this.getString();
    }

    public String getDebugInfo() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("[UNICODESTRING]\n");
        buffer.append("    .charcount       = ").append(Integer.toHexString(this.getCharCount())).append("\n");
        buffer.append("    .optionflags     = ").append(Integer.toHexString(this.getOptionFlags())).append("\n");
        buffer.append("    .string          = ").append(this.getString()).append("\n");
        if (this.field_4_format_runs != null) {
            for (int i = 0; i < this.field_4_format_runs.size(); ++i) {
                FormatRun r = this.field_4_format_runs.get(i);
                buffer.append("      .format_run").append(i).append("          = ").append(r).append("\n");
            }
        }
        if (this.field_5_ext_rst != null) {
            buffer.append("    .field_5_ext_rst          = ").append("\n");
            buffer.append(this.field_5_ext_rst).append("\n");
        }
        buffer.append("[/UNICODESTRING]\n");
        return buffer.toString();
    }

    public void serialize(ContinuableRecordOutput out) {
        int numberOfRichTextRuns = 0;
        int extendedDataSize = 0;
        if (this.isRichText() && this.field_4_format_runs != null) {
            numberOfRichTextRuns = this.field_4_format_runs.size();
        }
        if (this.isExtendedText() && this.field_5_ext_rst != null) {
            extendedDataSize = 4 + this.field_5_ext_rst.getDataSize();
        }
        out.writeString(this.field_3_string, numberOfRichTextRuns, extendedDataSize);
        if (numberOfRichTextRuns > 0) {
            for (int i = 0; i < numberOfRichTextRuns; ++i) {
                if (out.getAvailableSpace() < 4) {
                    out.writeContinue();
                }
                FormatRun r = this.field_4_format_runs.get(i);
                r.serialize(out);
            }
        }
        if (extendedDataSize > 0 && this.field_5_ext_rst != null) {
            this.field_5_ext_rst.serialize(out);
        }
    }

    @Override
    public int compareTo(UnicodeString str) {
        int result = this.getString().compareTo(str.getString());
        if (result != 0) {
            return result;
        }
        if (this.field_4_format_runs == null) {
            return str.field_4_format_runs == null ? 0 : 1;
        }
        if (str.field_4_format_runs == null) {
            return -1;
        }
        int size = this.field_4_format_runs.size();
        if (size != str.field_4_format_runs.size()) {
            return size - str.field_4_format_runs.size();
        }
        for (int i = 0; i < size; ++i) {
            FormatRun run2;
            FormatRun run1 = this.field_4_format_runs.get(i);
            result = run1.compareTo(run2 = str.field_4_format_runs.get(i));
            if (result == 0) continue;
            return result;
        }
        if (this.field_5_ext_rst == null) {
            return str.field_5_ext_rst == null ? 0 : 1;
        }
        if (str.field_5_ext_rst == null) {
            return -1;
        }
        return this.field_5_ext_rst.compareTo(str.field_5_ext_rst);
    }

    private boolean isRichText() {
        return richText.isSet(this.getOptionFlags());
    }

    private boolean isExtendedText() {
        return extBit.isSet(this.getOptionFlags());
    }

    @Deprecated
    @Removal(version="5.0.0")
    public UnicodeString clone() {
        return this.copy();
    }

    @Override
    public UnicodeString copy() {
        return new UnicodeString(this);
    }
}

