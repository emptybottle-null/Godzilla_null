/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.common.usermodel.fonts;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.common.usermodel.fonts.FontCharset;
import org.apache.poi.common.usermodel.fonts.FontFamily;
import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.common.usermodel.fonts.FontPitch;
import org.apache.poi.util.GenericRecordUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianInputStream;

public class FontHeader
implements FontInfo,
GenericRecord {
    private static final int[] FLAGS_MASKS = new int[]{1, 4, 16, 32, 64, 128, 0x10000000};
    private static final String[] FLAGS_NAMES = new String[]{"SUBSET", "TTCOMPRESSED", "FAILIFVARIATIONSIMULATED", "EMBEDEUDC", "VALIDATIONTESTS", "WEBOBJECT", "XORENCRYPTDATA"};
    private static final int[] FSTYPE_MASKS = new int[]{0, 2, 4, 8, 256, 512};
    private static final String[] FSTYPE_NAMES = new String[]{"INSTALLABLE_EMBEDDING", "RESTRICTED_LICENSE_EMBEDDING", "PREVIEW_PRINT_EMBEDDING", "EDITABLE_EMBEDDING", "NO_SUBSETTING", "BITMAP_EMBEDDING_ONLY"};
    public static final int REGULAR_WEIGHT = 400;
    private int eotSize;
    private int fontDataSize;
    private int version;
    private int flags;
    private final byte[] panose = new byte[10];
    private byte charset;
    private byte italic;
    private int weight;
    private int fsType;
    private int magic;
    private int unicodeRange1;
    private int unicodeRange2;
    private int unicodeRange3;
    private int unicodeRange4;
    private int codePageRange1;
    private int codePageRange2;
    private int checkSumAdjustment;
    private String familyName;
    private String styleName;
    private String versionName;
    private String fullName;

    public void init(byte[] source, int offset, int length) {
        this.init(new LittleEndianByteArrayInputStream(source, offset, length));
    }

    public void init(LittleEndianInput leis) {
        this.eotSize = leis.readInt();
        this.fontDataSize = leis.readInt();
        this.version = leis.readInt();
        if (this.version != 65536 && this.version != 131073 && this.version != 131074) {
            throw new RuntimeException("not a EOT font data stream");
        }
        this.flags = leis.readInt();
        leis.readFully(this.panose);
        this.charset = leis.readByte();
        this.italic = leis.readByte();
        this.weight = leis.readInt();
        this.fsType = leis.readUShort();
        this.magic = leis.readUShort();
        if (this.magic != 20556) {
            throw new RuntimeException("not a EOT font data stream");
        }
        this.unicodeRange1 = leis.readInt();
        this.unicodeRange2 = leis.readInt();
        this.unicodeRange3 = leis.readInt();
        this.unicodeRange4 = leis.readInt();
        this.codePageRange1 = leis.readInt();
        this.codePageRange2 = leis.readInt();
        this.checkSumAdjustment = leis.readInt();
        int reserved1 = leis.readInt();
        int reserved2 = leis.readInt();
        int reserved3 = leis.readInt();
        int reserved4 = leis.readInt();
        this.familyName = this.readName(leis);
        this.styleName = this.readName(leis);
        this.versionName = this.readName(leis);
        this.fullName = this.readName(leis);
    }

    public InputStream bufferInit(InputStream fontStream) throws IOException {
        LittleEndianInputStream is = new LittleEndianInputStream(fontStream);
        is.mark(1000);
        this.init(is);
        is.reset();
        return is;
    }

    private String readName(LittleEndianInput leis) {
        leis.readShort();
        int nameSize = leis.readUShort();
        byte[] nameBuf = IOUtils.safelyAllocate(nameSize, 1000);
        leis.readFully(nameBuf);
        return new String(nameBuf, 0, nameSize, StandardCharsets.UTF_16LE).trim();
    }

    public boolean isItalic() {
        return this.italic != 0;
    }

    public int getWeight() {
        return this.weight;
    }

    public boolean isBold() {
        return this.getWeight() > 400;
    }

    public byte getCharsetByte() {
        return this.charset;
    }

    @Override
    public FontCharset getCharset() {
        return FontCharset.valueOf(this.getCharsetByte());
    }

    @Override
    public FontPitch getPitch() {
        switch (this.getPanoseFamily()) {
            default: {
                return FontPitch.VARIABLE;
            }
            case TEXT_DISPLAY: 
            case DECORATIVE: {
                return this.getPanoseProportion() == PanoseProportion.MONOSPACED ? FontPitch.FIXED : FontPitch.VARIABLE;
            }
            case SCRIPT: 
            case PICTORIAL: 
        }
        return this.getPanoseProportion() == PanoseProportion.MODERN ? FontPitch.FIXED : FontPitch.VARIABLE;
    }

    @Override
    public FontFamily getFamily() {
        switch (this.getPanoseFamily()) {
            case ANY: 
            case NO_FIT: {
                return FontFamily.FF_DONTCARE;
            }
            case TEXT_DISPLAY: {
                switch (this.getPanoseSerif()) {
                    case TRIANGLE: 
                    case NORMAL_SANS: 
                    case OBTUSE_SANS: 
                    case PERP_SANS: 
                    case FLARED: 
                    case ROUNDED: {
                        return FontFamily.FF_SWISS;
                    }
                }
                return FontFamily.FF_ROMAN;
            }
            case SCRIPT: {
                return FontFamily.FF_SCRIPT;
            }
            default: {
                return FontFamily.FF_DECORATIVE;
            }
            case PICTORIAL: 
        }
        return FontFamily.FF_MODERN;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public String getStyleName() {
        return this.styleName;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public String getFullName() {
        return this.fullName;
    }

    @Override
    public byte[] getPanose() {
        return this.panose;
    }

    @Override
    public String getTypeface() {
        return this.getFamilyName();
    }

    public int getFlags() {
        return this.flags;
    }

    @Override
    public Map<String, Supplier<?>> getGenericProperties() {
        LinkedHashMap<String, Supplier<Object>> m = new LinkedHashMap<String, Supplier<Object>>();
        m.put("eotSize", () -> this.eotSize);
        m.put("fontDataSize", () -> this.fontDataSize);
        m.put("version", () -> this.version);
        m.put("flags", GenericRecordUtil.getBitsAsString(this::getFlags, FLAGS_MASKS, FLAGS_NAMES));
        m.put("panose.familyType", this::getPanoseFamily);
        m.put("panose.serifType", this::getPanoseSerif);
        m.put("panose.weight", this::getPanoseWeight);
        m.put("panose.proportion", this::getPanoseProportion);
        m.put("panose.contrast", this::getPanoseContrast);
        m.put("panose.stroke", this::getPanoseStroke);
        m.put("panose.armStyle", this::getPanoseArmStyle);
        m.put("panose.letterForm", this::getPanoseLetterForm);
        m.put("panose.midLine", this::getPanoseMidLine);
        m.put("panose.xHeight", this::getPanoseXHeight);
        m.put("charset", this::getCharset);
        m.put("italic", this::isItalic);
        m.put("weight", this::getWeight);
        m.put("fsType", GenericRecordUtil.getBitsAsString(() -> this.fsType, FSTYPE_MASKS, FSTYPE_NAMES));
        m.put("unicodeRange1", () -> this.unicodeRange1);
        m.put("unicodeRange2", () -> this.unicodeRange2);
        m.put("unicodeRange3", () -> this.unicodeRange3);
        m.put("unicodeRange4", () -> this.unicodeRange4);
        m.put("codePageRange1", () -> this.codePageRange1);
        m.put("codePageRange2", () -> this.codePageRange2);
        m.put("checkSumAdjustment", () -> this.checkSumAdjustment);
        m.put("familyName", this::getFamilyName);
        m.put("styleName", this::getStyleName);
        m.put("versionName", this::getVersionName);
        m.put("fullName", this::getFullName);
        return Collections.unmodifiableMap(m);
    }

    public PanoseFamily getPanoseFamily() {
        return (PanoseFamily)((Object)GenericRecordUtil.safeEnum((Enum[])PanoseFamily.values(), () -> this.panose[0]).get());
    }

    public PanoseSerif getPanoseSerif() {
        return (PanoseSerif)((Object)GenericRecordUtil.safeEnum((Enum[])PanoseSerif.values(), () -> this.panose[1]).get());
    }

    public PanoseWeight getPanoseWeight() {
        return (PanoseWeight)((Object)GenericRecordUtil.safeEnum((Enum[])PanoseWeight.values(), () -> this.panose[2]).get());
    }

    public PanoseProportion getPanoseProportion() {
        return (PanoseProportion)((Object)GenericRecordUtil.safeEnum((Enum[])PanoseProportion.values(), () -> this.panose[3]).get());
    }

    public PanoseContrast getPanoseContrast() {
        return (PanoseContrast)((Object)GenericRecordUtil.safeEnum((Enum[])PanoseContrast.values(), () -> this.panose[4]).get());
    }

    public PanoseStroke getPanoseStroke() {
        return (PanoseStroke)((Object)GenericRecordUtil.safeEnum((Enum[])PanoseStroke.values(), () -> this.panose[5]).get());
    }

    public PanoseArmStyle getPanoseArmStyle() {
        return (PanoseArmStyle)((Object)GenericRecordUtil.safeEnum((Enum[])PanoseArmStyle.values(), () -> this.panose[6]).get());
    }

    public PanoseLetterForm getPanoseLetterForm() {
        return (PanoseLetterForm)((Object)GenericRecordUtil.safeEnum((Enum[])PanoseLetterForm.values(), () -> this.panose[7]).get());
    }

    public PanoseMidLine getPanoseMidLine() {
        return (PanoseMidLine)((Object)GenericRecordUtil.safeEnum((Enum[])PanoseMidLine.values(), () -> this.panose[8]).get());
    }

    public PanoseXHeight getPanoseXHeight() {
        return (PanoseXHeight)((Object)GenericRecordUtil.safeEnum((Enum[])PanoseXHeight.values(), () -> this.panose[9]).get());
    }

    public static enum PanoseXHeight {
        ANY,
        NO_FIT,
        CONSTANT_SMALL,
        CONSTANT_STD,
        CONSTANT_LARGE,
        DUCKING_SMALL,
        DUCKING_STD,
        DUCKING_LARGE;

    }

    public static enum PanoseMidLine {
        ANY,
        NO_FIT,
        STANDARD_TRIMMED,
        STANDARD_POINTED,
        STANDARD_SERIFED,
        HIGH_TRIMMED,
        HIGH_POINTED,
        HIGH_SERIFED,
        CONSTANT_TRIMMED,
        CONSTANT_POINTED,
        CONSTANT_SERIFED,
        LOW_TRIMMED,
        LOW_POINTED,
        LOW_SERIFED;

    }

    public static enum PanoseLetterForm {
        ANY,
        NO_FIT,
        NORMAL_CONTACT,
        NORMAL_WEIGHTED,
        NORMAL_BOXED,
        NORMAL_FLATTENED,
        NORMAL_ROUNDED,
        NORMAL_OFF_CENTER,
        NORMAL_SQUARE,
        OBLIQUE_CONTACT,
        OBLIQUE_WEIGHTED,
        OBLIQUE_BOXED,
        OBLIQUE_FLATTENED,
        OBLIQUE_ROUNDED,
        OBLIQUE_OFF_CENTER,
        OBLIQUE_SQUARE;

    }

    public static enum PanoseArmStyle {
        ANY,
        NO_FIT,
        STRAIGHT_ARMS_HORZ,
        STRAIGHT_ARMS_WEDGE,
        STRAIGHT_ARMS_VERT,
        STRAIGHT_ARMS_SINGLE_SERIF,
        STRAIGHT_ARMS_DOUBLE_SERIF,
        BENT_ARMS_HORZ,
        BENT_ARMS_WEDGE,
        BENT_ARMS_VERT,
        BENT_ARMS_SINGLE_SERIF,
        BENT_ARMS_DOUBLE_SERIF;

    }

    public static enum PanoseStroke {
        ANY,
        NO_FIT,
        GRADUAL_DIAG,
        GRADUAL_TRAN,
        GRADUAL_VERT,
        GRADUAL_HORZ,
        RAPID_VERT,
        RAPID_HORZ,
        INSTANT_VERT;

    }

    public static enum PanoseContrast {
        ANY,
        NO_FIT,
        NONE,
        VERY_LOW,
        LOW,
        MEDIUM_LOW,
        MEDIUM,
        MEDIUM_HIGH,
        HIGH,
        VERY_HIGH;

    }

    public static enum PanoseProportion {
        ANY,
        NO_FIT,
        OLD_STYLE,
        MODERN,
        EVEN_WIDTH,
        EXPANDED,
        CONDENSED,
        VERY_EXPANDED,
        VERY_CONDENSED,
        MONOSPACED;

    }

    public static enum PanoseWeight {
        ANY,
        NO_FIT,
        VERY_LIGHT,
        LIGHT,
        THIN,
        BOOK,
        MEDIUM,
        DEMI,
        BOLD,
        HEAVY,
        BLACK,
        NORD;

    }

    public static enum PanoseSerif {
        ANY,
        NO_FIT,
        COVE,
        OBTUSE_COVE,
        SQUARE_COVE,
        OBTUSE_SQUARE_COVE,
        SQUARE,
        THIN,
        BONE,
        EXAGGERATED,
        TRIANGLE,
        NORMAL_SANS,
        OBTUSE_SANS,
        PERP_SANS,
        FLARED,
        ROUNDED;

    }

    public static enum PanoseFamily {
        ANY,
        NO_FIT,
        TEXT_DISPLAY,
        SCRIPT,
        DECORATIVE,
        PICTORIAL;

    }
}

