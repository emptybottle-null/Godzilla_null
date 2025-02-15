/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.util.Objects;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontCharset;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.FontScheme;
import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBooleanProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontName;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontScheme;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFontSize;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIntProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTUnderlineProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTVerticalAlignFontProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STFontScheme;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STUnderlineValues;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STVerticalAlignRun;

public class XSSFFont
implements Font {
    public static final String DEFAULT_FONT_NAME = "Calibri";
    public static final short DEFAULT_FONT_SIZE = 11;
    public static final short DEFAULT_FONT_COLOR = IndexedColors.BLACK.getIndex();
    private IndexedColorMap _indexedColorMap;
    private ThemesTable _themes;
    private CTFont _ctFont;
    private int _index;

    @Internal
    public XSSFFont(CTFont font) {
        this._ctFont = font;
        this._index = 0;
    }

    @Internal
    public XSSFFont(CTFont font, int index, IndexedColorMap colorMap) {
        this._ctFont = font;
        this._index = (short)index;
        this._indexedColorMap = colorMap;
    }

    public XSSFFont() {
        this._ctFont = CTFont.Factory.newInstance();
        this.setFontName(DEFAULT_FONT_NAME);
        this.setFontHeight(11.0);
    }

    @Internal
    public CTFont getCTFont() {
        return this._ctFont;
    }

    @Override
    public boolean getBold() {
        CTBooleanProperty bold = this._ctFont.sizeOfBArray() == 0 ? null : this._ctFont.getBArray(0);
        return bold != null && bold.getVal();
    }

    @Override
    public int getCharSet() {
        CTIntProperty charset = this._ctFont.sizeOfCharsetArray() == 0 ? null : this._ctFont.getCharsetArray(0);
        return charset == null ? FontCharset.ANSI.getValue() : FontCharset.valueOf(charset.getVal()).getValue();
    }

    @Override
    public short getColor() {
        CTColor color;
        CTColor cTColor = color = this._ctFont.sizeOfColorArray() == 0 ? null : this._ctFont.getColorArray(0);
        if (color == null) {
            return IndexedColors.BLACK.getIndex();
        }
        long index = color.getIndexed();
        if (index == (long)DEFAULT_FONT_COLOR) {
            return IndexedColors.BLACK.getIndex();
        }
        if (index == (long)IndexedColors.RED.getIndex()) {
            return IndexedColors.RED.getIndex();
        }
        return (short)index;
    }

    public XSSFColor getXSSFColor() {
        CTColor ctColor;
        CTColor cTColor = ctColor = this._ctFont.sizeOfColorArray() == 0 ? null : this._ctFont.getColorArray(0);
        if (ctColor != null) {
            XSSFColor color = XSSFColor.from(ctColor, this._indexedColorMap);
            if (this._themes != null) {
                this._themes.inheritFromThemeAsRequired(color);
            }
            return color;
        }
        return null;
    }

    public short getThemeColor() {
        CTColor color = this._ctFont.sizeOfColorArray() == 0 ? null : this._ctFont.getColorArray(0);
        long index = color == null ? 0L : color.getTheme();
        return (short)index;
    }

    @Override
    public short getFontHeight() {
        return (short)(this.getFontHeightRaw() * 20.0);
    }

    @Override
    public short getFontHeightInPoints() {
        return (short)this.getFontHeightRaw();
    }

    private double getFontHeightRaw() {
        CTFontSize size;
        CTFontSize cTFontSize = size = this._ctFont.sizeOfSzArray() == 0 ? null : this._ctFont.getSzArray(0);
        if (size != null) {
            return size.getVal();
        }
        return 11.0;
    }

    @Override
    public String getFontName() {
        CTFontName name = this._ctFont.sizeOfNameArray() == 0 ? null : this._ctFont.getNameArray(0);
        return name == null ? DEFAULT_FONT_NAME : name.getVal();
    }

    @Override
    public boolean getItalic() {
        CTBooleanProperty italic = this._ctFont.sizeOfIArray() == 0 ? null : this._ctFont.getIArray(0);
        return italic != null && italic.getVal();
    }

    @Override
    public boolean getStrikeout() {
        CTBooleanProperty strike = this._ctFont.sizeOfStrikeArray() == 0 ? null : this._ctFont.getStrikeArray(0);
        return strike != null && strike.getVal();
    }

    @Override
    public short getTypeOffset() {
        CTVerticalAlignFontProperty vAlign;
        CTVerticalAlignFontProperty cTVerticalAlignFontProperty = vAlign = this._ctFont.sizeOfVertAlignArray() == 0 ? null : this._ctFont.getVertAlignArray(0);
        if (vAlign == null) {
            return 0;
        }
        int val = vAlign.getVal().intValue();
        switch (val) {
            case 1: {
                return 0;
            }
            case 3: {
                return 2;
            }
            case 2: {
                return 1;
            }
        }
        throw new POIXMLException("Wrong offset value " + val);
    }

    @Override
    public byte getUnderline() {
        CTUnderlineProperty underline;
        CTUnderlineProperty cTUnderlineProperty = underline = this._ctFont.sizeOfUArray() == 0 ? null : this._ctFont.getUArray(0);
        if (underline != null) {
            FontUnderline val = FontUnderline.valueOf(underline.getVal().intValue());
            return val.getByteValue();
        }
        return 0;
    }

    @Override
    public void setBold(boolean bold) {
        if (bold) {
            CTBooleanProperty ctBold = this._ctFont.sizeOfBArray() == 0 ? this._ctFont.addNewB() : this._ctFont.getBArray(0);
            ctBold.setVal(true);
        } else {
            this._ctFont.setBArray(null);
        }
    }

    @Override
    public void setCharSet(byte charset) {
        int cs = charset & 0xFF;
        this.setCharSet(cs);
    }

    @Override
    public void setCharSet(int charset) {
        FontCharset fontCharset = FontCharset.valueOf(charset);
        if (fontCharset == null) {
            throw new POIXMLException("Attention: an attempt to set a type of unknow charset and charset");
        }
        this.setCharSet(fontCharset);
    }

    public void setCharSet(FontCharset charSet) {
        CTIntProperty charsetProperty = this._ctFont.sizeOfCharsetArray() == 0 ? this._ctFont.addNewCharset() : this._ctFont.getCharsetArray(0);
        charsetProperty.setVal(charSet.getValue());
    }

    @Override
    public void setColor(short color) {
        CTColor ctColor = this._ctFont.sizeOfColorArray() == 0 ? this._ctFont.addNewColor() : this._ctFont.getColorArray(0);
        switch (color) {
            case 32767: {
                ctColor.setIndexed(DEFAULT_FONT_COLOR);
                break;
            }
            case 10: {
                ctColor.setIndexed(IndexedColors.RED.getIndex());
                break;
            }
            default: {
                ctColor.setIndexed(color);
            }
        }
    }

    public void setColor(XSSFColor color) {
        if (color == null) {
            this._ctFont.setColorArray(null);
        } else {
            CTColor ctColor;
            CTColor cTColor = ctColor = this._ctFont.sizeOfColorArray() == 0 ? this._ctFont.addNewColor() : this._ctFont.getColorArray(0);
            if (ctColor.isSetIndexed()) {
                ctColor.unsetIndexed();
            }
            ctColor.setRgb(color.getRGB());
        }
    }

    @Override
    public void setFontHeight(short height) {
        this.setFontHeight((double)height / 20.0);
    }

    public void setFontHeight(double height) {
        CTFontSize fontSize = this._ctFont.sizeOfSzArray() == 0 ? this._ctFont.addNewSz() : this._ctFont.getSzArray(0);
        fontSize.setVal(height);
    }

    @Override
    public void setFontHeightInPoints(short height) {
        this.setFontHeight((double)height);
    }

    public void setThemeColor(short theme) {
        CTColor ctColor = this._ctFont.sizeOfColorArray() == 0 ? this._ctFont.addNewColor() : this._ctFont.getColorArray(0);
        ctColor.setTheme(theme);
    }

    @Override
    public void setFontName(String name) {
        CTFontName fontName = this._ctFont.sizeOfNameArray() == 0 ? this._ctFont.addNewName() : this._ctFont.getNameArray(0);
        fontName.setVal(name == null ? DEFAULT_FONT_NAME : name);
    }

    @Override
    public void setItalic(boolean italic) {
        if (italic) {
            CTBooleanProperty bool = this._ctFont.sizeOfIArray() == 0 ? this._ctFont.addNewI() : this._ctFont.getIArray(0);
            bool.setVal(true);
        } else {
            this._ctFont.setIArray(null);
        }
    }

    @Override
    public void setStrikeout(boolean strikeout) {
        if (strikeout) {
            CTBooleanProperty strike = this._ctFont.sizeOfStrikeArray() == 0 ? this._ctFont.addNewStrike() : this._ctFont.getStrikeArray(0);
            strike.setVal(true);
        } else {
            this._ctFont.setStrikeArray(null);
        }
    }

    @Override
    public void setTypeOffset(short offset) {
        if (offset == 0) {
            this._ctFont.setVertAlignArray(null);
        } else {
            CTVerticalAlignFontProperty offsetProperty = this._ctFont.sizeOfVertAlignArray() == 0 ? this._ctFont.addNewVertAlign() : this._ctFont.getVertAlignArray(0);
            switch (offset) {
                case 0: {
                    offsetProperty.setVal(STVerticalAlignRun.BASELINE);
                    break;
                }
                case 2: {
                    offsetProperty.setVal(STVerticalAlignRun.SUBSCRIPT);
                    break;
                }
                case 1: {
                    offsetProperty.setVal(STVerticalAlignRun.SUPERSCRIPT);
                    break;
                }
                default: {
                    throw new IllegalStateException("Invalid type offset: " + offset);
                }
            }
        }
    }

    @Override
    public void setUnderline(byte underline) {
        this.setUnderline(FontUnderline.valueOf(underline));
    }

    public void setUnderline(FontUnderline underline) {
        if (underline == FontUnderline.NONE && this._ctFont.sizeOfUArray() > 0) {
            this._ctFont.setUArray(null);
        } else {
            CTUnderlineProperty ctUnderline = this._ctFont.sizeOfUArray() == 0 ? this._ctFont.addNewU() : this._ctFont.getUArray(0);
            STUnderlineValues.Enum val = STUnderlineValues.Enum.forInt(underline.getValue());
            ctUnderline.setVal(val);
        }
    }

    public String toString() {
        return this._ctFont.toString();
    }

    public long registerTo(StylesTable styles) {
        return this.registerTo(styles, true);
    }

    public long registerTo(StylesTable styles, boolean force) {
        this._themes = styles.getTheme();
        this._index = styles.putFont(this, force);
        return this._index;
    }

    public void setThemesTable(ThemesTable themes) {
        this._themes = themes;
    }

    public FontScheme getScheme() {
        CTFontScheme scheme = this._ctFont.sizeOfSchemeArray() == 0 ? null : this._ctFont.getSchemeArray(0);
        return scheme == null ? FontScheme.NONE : FontScheme.valueOf(scheme.getVal().intValue());
    }

    public void setScheme(FontScheme scheme) {
        CTFontScheme ctFontScheme = this._ctFont.sizeOfSchemeArray() == 0 ? this._ctFont.addNewScheme() : this._ctFont.getSchemeArray(0);
        STFontScheme.Enum val = STFontScheme.Enum.forInt(scheme.getValue());
        ctFontScheme.setVal(val);
    }

    public int getFamily() {
        CTIntProperty family = this._ctFont.sizeOfFamilyArray() == 0 ? null : this._ctFont.getFamilyArray(0);
        return family == null ? FontFamily.NOT_APPLICABLE.getValue() : FontFamily.valueOf(family.getVal()).getValue();
    }

    public void setFamily(int value) {
        CTIntProperty family = this._ctFont.sizeOfFamilyArray() == 0 ? this._ctFont.addNewFamily() : this._ctFont.getFamilyArray(0);
        family.setVal(value);
    }

    public void setFamily(FontFamily family) {
        this.setFamily(family.getValue());
    }

    @Override
    @Deprecated
    public short getIndex() {
        return (short)this.getIndexAsInt();
    }

    @Override
    public int getIndexAsInt() {
        return this._index;
    }

    public int hashCode() {
        return this._ctFont.toString().hashCode();
    }

    public boolean equals(Object o) {
        if (!(o instanceof XSSFFont)) {
            return false;
        }
        XSSFFont cf = (XSSFFont)o;
        return Objects.equals(this.getItalic(), cf.getItalic()) && Objects.equals(this.getBold(), cf.getBold()) && Objects.equals(this.getStrikeout(), cf.getStrikeout()) && Objects.equals(this.getCharSet(), cf.getCharSet()) && Objects.equals(this.getItalic(), cf.getItalic()) && Objects.equals(this.getColor(), cf.getColor()) && Objects.equals(this.getFamily(), cf.getFamily()) && Objects.equals(this.getFontHeight(), cf.getFontHeight()) && Objects.equals(this.getFontName(), cf.getFontName()) && Objects.equals((Object)this.getScheme(), (Object)cf.getScheme()) && Objects.equals(this.getThemeColor(), cf.getThemeColor()) && Objects.equals(this.getTypeOffset(), cf.getTypeOffset()) && Objects.equals(this.getUnderline(), cf.getUnderline()) && Objects.equals(this.getXSSFColor(), cf.getXSSFColor());
    }
}

