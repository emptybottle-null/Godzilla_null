/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.namespace.QName;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColor;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRPrElt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRst;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STXstring;

public class XSSFRichTextString
implements RichTextString {
    private static final Pattern utfPtrn = Pattern.compile("_x([0-9A-Fa-f]{4})_");
    private CTRst st;
    private StylesTable styles;

    public XSSFRichTextString(String str) {
        this.st = CTRst.Factory.newInstance();
        this.st.setT(str);
        XSSFRichTextString.preserveSpaces(this.st.xgetT());
    }

    public XSSFRichTextString() {
        this.st = CTRst.Factory.newInstance();
    }

    @Internal
    public XSSFRichTextString(CTRst st) {
        this.st = st;
    }

    @Override
    public void applyFont(int startIndex, int endIndex, short fontIndex) {
        XSSFFont font;
        if (this.styles == null) {
            font = new XSSFFont();
            font.setFontName("#" + fontIndex);
        } else {
            font = this.styles.getFontAt(fontIndex);
        }
        this.applyFont(startIndex, endIndex, font);
    }

    @Override
    public void applyFont(int startIndex, int endIndex, Font font) {
        if (startIndex > endIndex) {
            throw new IllegalArgumentException("Start index must be less than end index, but had " + startIndex + " and " + endIndex);
        }
        if (startIndex < 0 || endIndex > this.length()) {
            throw new IllegalArgumentException("Start and end index not in range, but had " + startIndex + " and " + endIndex);
        }
        if (startIndex == endIndex) {
            return;
        }
        if (this.st.sizeOfRArray() == 0 && this.st.isSetT()) {
            this.st.addNewR().setT(this.st.getT());
            this.st.unsetT();
        }
        String text = this.getString();
        XSSFFont xssfFont = (XSSFFont)font;
        TreeMap<Integer, CTRPrElt> formats = this.getFormatMap(this.st);
        CTRPrElt fmt = CTRPrElt.Factory.newInstance();
        this.setRunAttributes(xssfFont.getCTFont(), fmt);
        this.applyFont(formats, startIndex, endIndex, fmt);
        CTRst newSt = this.buildCTRst(text, formats);
        this.st.set(newSt);
    }

    @Override
    public void applyFont(Font font) {
        String text = this.getString();
        this.applyFont(0, text.length(), font);
    }

    @Override
    public void applyFont(short fontIndex) {
        XSSFFont font;
        if (this.styles == null) {
            font = new XSSFFont();
            font.setFontName("#" + fontIndex);
        } else {
            font = this.styles.getFontAt(fontIndex);
        }
        String text = this.getString();
        this.applyFont(0, text.length(), font);
    }

    public void append(String text, XSSFFont font) {
        CTRElt lt;
        if (this.st.sizeOfRArray() == 0 && this.st.isSetT()) {
            lt = this.st.addNewR();
            lt.setT(this.st.getT());
            XSSFRichTextString.preserveSpaces(lt.xgetT());
            this.st.unsetT();
        }
        lt = this.st.addNewR();
        lt.setT(text);
        XSSFRichTextString.preserveSpaces(lt.xgetT());
        if (font != null) {
            CTRPrElt pr = lt.addNewRPr();
            this.setRunAttributes(font.getCTFont(), pr);
        }
    }

    public void append(String text) {
        this.append(text, null);
    }

    private void setRunAttributes(CTFont ctFont, CTRPrElt pr) {
        if (ctFont.sizeOfBArray() > 0) {
            pr.addNewB().setVal(ctFont.getBArray(0).getVal());
        }
        if (ctFont.sizeOfUArray() > 0) {
            pr.addNewU().setVal(ctFont.getUArray(0).getVal());
        }
        if (ctFont.sizeOfIArray() > 0) {
            pr.addNewI().setVal(ctFont.getIArray(0).getVal());
        }
        if (ctFont.sizeOfColorArray() > 0) {
            CTColor c1 = ctFont.getColorArray(0);
            CTColor c2 = pr.addNewColor();
            if (c1.isSetAuto()) {
                c2.setAuto(c1.getAuto());
            }
            if (c1.isSetIndexed()) {
                c2.setIndexed(c1.getIndexed());
            }
            if (c1.isSetRgb()) {
                c2.setRgb(c1.getRgb());
            }
            if (c1.isSetTheme()) {
                c2.setTheme(c1.getTheme());
            }
            if (c1.isSetTint()) {
                c2.setTint(c1.getTint());
            }
        }
        if (ctFont.sizeOfSzArray() > 0) {
            pr.addNewSz().setVal(ctFont.getSzArray(0).getVal());
        }
        if (ctFont.sizeOfNameArray() > 0) {
            pr.addNewRFont().setVal(ctFont.getNameArray(0).getVal());
        }
        if (ctFont.sizeOfFamilyArray() > 0) {
            pr.addNewFamily().setVal(ctFont.getFamilyArray(0).getVal());
        }
        if (ctFont.sizeOfSchemeArray() > 0) {
            pr.addNewScheme().setVal(ctFont.getSchemeArray(0).getVal());
        }
        if (ctFont.sizeOfCharsetArray() > 0) {
            pr.addNewCharset().setVal(ctFont.getCharsetArray(0).getVal());
        }
        if (ctFont.sizeOfCondenseArray() > 0) {
            pr.addNewCondense().setVal(ctFont.getCondenseArray(0).getVal());
        }
        if (ctFont.sizeOfExtendArray() > 0) {
            pr.addNewExtend().setVal(ctFont.getExtendArray(0).getVal());
        }
        if (ctFont.sizeOfVertAlignArray() > 0) {
            pr.addNewVertAlign().setVal(ctFont.getVertAlignArray(0).getVal());
        }
        if (ctFont.sizeOfOutlineArray() > 0) {
            pr.addNewOutline().setVal(ctFont.getOutlineArray(0).getVal());
        }
        if (ctFont.sizeOfShadowArray() > 0) {
            pr.addNewShadow().setVal(ctFont.getShadowArray(0).getVal());
        }
        if (ctFont.sizeOfStrikeArray() > 0) {
            pr.addNewStrike().setVal(ctFont.getStrikeArray(0).getVal());
        }
    }

    public boolean hasFormatting() {
        CTRElt[] rs = this.st.getRArray();
        if (rs == null || rs.length == 0) {
            return false;
        }
        for (CTRElt r : rs) {
            if (!r.isSetRPr()) continue;
            return true;
        }
        return false;
    }

    @Override
    public void clearFormatting() {
        String text = this.getString();
        this.st.setRArray(null);
        this.st.setT(text);
    }

    @Override
    public int getIndexOfFormattingRun(int index) {
        if (this.st.sizeOfRArray() == 0) {
            return 0;
        }
        int pos = 0;
        for (int i = 0; i < this.st.sizeOfRArray(); ++i) {
            CTRElt r = this.st.getRArray(i);
            if (i == index) {
                return pos;
            }
            pos += r.getT().length();
        }
        return -1;
    }

    public int getLengthOfFormattingRun(int index) {
        if (this.st.sizeOfRArray() == 0 || index >= this.st.sizeOfRArray()) {
            return -1;
        }
        CTRElt r = this.st.getRArray(index);
        return r.getT().length();
    }

    @Override
    public String getString() {
        if (this.st.sizeOfRArray() == 0) {
            return XSSFRichTextString.utfDecode(this.st.getT());
        }
        StringBuilder buf = new StringBuilder();
        for (CTRElt r : this.st.getRArray()) {
            buf.append(r.getT());
        }
        return XSSFRichTextString.utfDecode(buf.toString());
    }

    public void setString(String s) {
        this.clearFormatting();
        this.st.setT(s);
        XSSFRichTextString.preserveSpaces(this.st.xgetT());
    }

    public String toString() {
        String str = this.getString();
        if (str == null) {
            return "";
        }
        return str;
    }

    @Override
    public int length() {
        return this.getString().length();
    }

    @Override
    public int numFormattingRuns() {
        return this.st.sizeOfRArray();
    }

    public XSSFFont getFontOfFormattingRun(int index) {
        if (this.st.sizeOfRArray() == 0 || index >= this.st.sizeOfRArray()) {
            return null;
        }
        CTRElt r = this.st.getRArray(index);
        if (r.getRPr() != null) {
            XSSFFont fnt = new XSSFFont(XSSFRichTextString.toCTFont(r.getRPr()));
            fnt.setThemesTable(this.getThemesTable());
            return fnt;
        }
        return null;
    }

    public XSSFFont getFontAtIndex(int index) {
        ThemesTable themes = this.getThemesTable();
        int pos = 0;
        for (CTRElt r : this.st.getRArray()) {
            int length = r.getT().length();
            if (index >= pos && index < pos + length) {
                XSSFFont fnt = new XSSFFont(XSSFRichTextString.toCTFont(r.getRPr()));
                fnt.setThemesTable(themes);
                return fnt;
            }
            pos += length;
        }
        return null;
    }

    @Internal
    public CTRst getCTRst() {
        return this.st;
    }

    protected void setStylesTableReference(StylesTable tbl) {
        this.styles = tbl;
        if (this.st.sizeOfRArray() > 0) {
            for (CTRElt r : this.st.getRArray()) {
                String fontName;
                CTRPrElt pr = r.getRPr();
                if (pr == null || pr.sizeOfRFontArray() <= 0 || !(fontName = pr.getRFontArray(0).getVal()).startsWith("#")) continue;
                int idx = Integer.parseInt(fontName.substring(1));
                XSSFFont font = this.styles.getFontAt(idx);
                pr.removeRFont(0);
                this.setRunAttributes(font.getCTFont(), pr);
            }
        }
    }

    protected static CTFont toCTFont(CTRPrElt pr) {
        CTFont ctFont = CTFont.Factory.newInstance();
        if (pr == null) {
            return ctFont;
        }
        if (pr.sizeOfBArray() > 0) {
            ctFont.addNewB().setVal(pr.getBArray(0).getVal());
        }
        if (pr.sizeOfUArray() > 0) {
            ctFont.addNewU().setVal(pr.getUArray(0).getVal());
        }
        if (pr.sizeOfIArray() > 0) {
            ctFont.addNewI().setVal(pr.getIArray(0).getVal());
        }
        if (pr.sizeOfColorArray() > 0) {
            CTColor c1 = pr.getColorArray(0);
            CTColor c2 = ctFont.addNewColor();
            if (c1.isSetAuto()) {
                c2.setAuto(c1.getAuto());
            }
            if (c1.isSetIndexed()) {
                c2.setIndexed(c1.getIndexed());
            }
            if (c1.isSetRgb()) {
                c2.setRgb(c1.getRgb());
            }
            if (c1.isSetTheme()) {
                c2.setTheme(c1.getTheme());
            }
            if (c1.isSetTint()) {
                c2.setTint(c1.getTint());
            }
        }
        if (pr.sizeOfSzArray() > 0) {
            ctFont.addNewSz().setVal(pr.getSzArray(0).getVal());
        }
        if (pr.sizeOfRFontArray() > 0) {
            ctFont.addNewName().setVal(pr.getRFontArray(0).getVal());
        }
        if (pr.sizeOfFamilyArray() > 0) {
            ctFont.addNewFamily().setVal(pr.getFamilyArray(0).getVal());
        }
        if (pr.sizeOfSchemeArray() > 0) {
            ctFont.addNewScheme().setVal(pr.getSchemeArray(0).getVal());
        }
        if (pr.sizeOfCharsetArray() > 0) {
            ctFont.addNewCharset().setVal(pr.getCharsetArray(0).getVal());
        }
        if (pr.sizeOfCondenseArray() > 0) {
            ctFont.addNewCondense().setVal(pr.getCondenseArray(0).getVal());
        }
        if (pr.sizeOfExtendArray() > 0) {
            ctFont.addNewExtend().setVal(pr.getExtendArray(0).getVal());
        }
        if (pr.sizeOfVertAlignArray() > 0) {
            ctFont.addNewVertAlign().setVal(pr.getVertAlignArray(0).getVal());
        }
        if (pr.sizeOfOutlineArray() > 0) {
            ctFont.addNewOutline().setVal(pr.getOutlineArray(0).getVal());
        }
        if (pr.sizeOfShadowArray() > 0) {
            ctFont.addNewShadow().setVal(pr.getShadowArray(0).getVal());
        }
        if (pr.sizeOfStrikeArray() > 0) {
            ctFont.addNewStrike().setVal(pr.getStrikeArray(0).getVal());
        }
        return ctFont;
    }

    protected static void preserveSpaces(STXstring xs) {
        String text = xs.getStringValue();
        if (text != null && text.length() > 0) {
            char firstChar = text.charAt(0);
            char lastChar = text.charAt(text.length() - 1);
            if (Character.isWhitespace(firstChar) || Character.isWhitespace(lastChar)) {
                XmlCursor c = xs.newCursor();
                c.toNextToken();
                c.insertAttributeWithValue(new QName("http://www.w3.org/XML/1998/namespace", "space"), "preserve");
                c.dispose();
            }
        }
    }

    static String utfDecode(String value) {
        if (value == null || !value.contains("_x")) {
            return value;
        }
        StringBuilder buf = new StringBuilder();
        Matcher m = utfPtrn.matcher(value);
        int idx = 0;
        while (m.find()) {
            int pos = m.start();
            if (pos > idx) {
                buf.append(value, idx, pos);
            }
            String code = m.group(1);
            int icode = Integer.decode("0x" + code);
            buf.append((char)icode);
            idx = m.end();
        }
        if (idx == 0) {
            return value;
        }
        buf.append(value.substring(idx));
        return buf.toString();
    }

    void applyFont(TreeMap<Integer, CTRPrElt> formats, int startIndex, int endIndex, CTRPrElt fmt) {
        int runStartIdx = 0;
        Iterator<Object> it = formats.keySet().iterator();
        while (it.hasNext()) {
            int n = it.next();
            if (runStartIdx >= startIndex && n < endIndex) {
                it.remove();
            }
            runStartIdx = n;
        }
        if (startIndex > 0 && !formats.containsKey(startIndex)) {
            for (Map.Entry entry : formats.entrySet()) {
                if ((Integer)entry.getKey() <= startIndex) continue;
                formats.put(startIndex, (CTRPrElt)entry.getValue());
                break;
            }
        }
        formats.put(endIndex, fmt);
        SortedMap<Integer, CTRPrElt> sub = formats.subMap(startIndex, endIndex);
        while (sub.size() > 1) {
            sub.remove(sub.lastKey());
        }
    }

    TreeMap<Integer, CTRPrElt> getFormatMap(CTRst entry) {
        int length = 0;
        TreeMap<Integer, CTRPrElt> formats = new TreeMap<Integer, CTRPrElt>();
        for (CTRElt r : entry.getRArray()) {
            String txt = r.getT();
            CTRPrElt fmt = r.getRPr();
            formats.put(length += txt.length(), fmt);
        }
        return formats;
    }

    CTRst buildCTRst(String text, TreeMap<Integer, CTRPrElt> formats) {
        if (text.length() != formats.lastKey().intValue()) {
            throw new IllegalArgumentException("Text length was " + text.length() + " but the last format index was " + formats.lastKey());
        }
        CTRst stf = CTRst.Factory.newInstance();
        int runStartIdx = 0;
        for (Map.Entry<Integer, CTRPrElt> me : formats.entrySet()) {
            int runEndIdx = me.getKey();
            CTRElt run = stf.addNewR();
            String fragment = text.substring(runStartIdx, runEndIdx);
            run.setT(fragment);
            XSSFRichTextString.preserveSpaces(run.xgetT());
            CTRPrElt fmt = me.getValue();
            if (fmt != null) {
                run.setRPr(fmt);
            }
            runStartIdx = runEndIdx;
        }
        return stf;
    }

    private ThemesTable getThemesTable() {
        if (this.styles == null) {
            return null;
        }
        return this.styles.getTheme();
    }
}

