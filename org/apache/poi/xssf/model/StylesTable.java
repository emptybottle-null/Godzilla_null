/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.ooxml.POIXMLTypeLoader;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.FontScheme;
import org.apache.poi.ss.usermodel.TableStyle;
import org.apache.poi.util.Internal;
import org.apache.poi.xssf.model.Styles;
import org.apache.poi.xssf.model.ThemesTable;
import org.apache.poi.xssf.usermodel.CustomIndexedColorMap;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFBuiltinTableStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFactory;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRelation;
import org.apache.poi.xssf.usermodel.XSSFTableStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellFill;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorders;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellStyleXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCellXfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxfs;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFills;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFonts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmts;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTStylesheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyle;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTableStyles;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTXf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STPatternType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.StyleSheetDocument;

public class StylesTable
extends POIXMLDocumentPart
implements Styles {
    private final SortedMap<Short, String> numberFormats = new TreeMap<Short, String>();
    private final List<XSSFFont> fonts = new ArrayList<XSSFFont>();
    private final List<XSSFCellFill> fills = new ArrayList<XSSFCellFill>();
    private final List<XSSFCellBorder> borders = new ArrayList<XSSFCellBorder>();
    private final List<CTXf> styleXfs = new ArrayList<CTXf>();
    private final List<CTXf> xfs = new ArrayList<CTXf>();
    private final List<CTDxf> dxfs = new ArrayList<CTDxf>();
    private final Map<String, TableStyle> tableStyles = new HashMap<String, TableStyle>();
    private IndexedColorMap indexedColors = new DefaultIndexedColorMap();
    public static final int FIRST_CUSTOM_STYLE_ID = 165;
    private static final int MAXIMUM_STYLE_ID = SpreadsheetVersion.EXCEL2007.getMaxCellStyles();
    private static final short FIRST_USER_DEFINED_NUMBER_FORMAT_ID = 164;
    private int MAXIMUM_NUMBER_OF_DATA_FORMATS = 250;
    private StyleSheetDocument doc;
    private XSSFWorkbook workbook;
    private ThemesTable theme;

    public void setMaxNumberOfDataFormats(int num) {
        if (num < this.getNumDataFormats()) {
            if (num < 0) {
                throw new IllegalArgumentException("Maximum Number of Data Formats must be greater than or equal to 0");
            }
            throw new IllegalStateException("Cannot set the maximum number of data formats less than the current quantity. Data formats must be explicitly removed (via StylesTable.removeNumberFormat) before the limit can be decreased.");
        }
        this.MAXIMUM_NUMBER_OF_DATA_FORMATS = num;
    }

    public int getMaxNumberOfDataFormats() {
        return this.MAXIMUM_NUMBER_OF_DATA_FORMATS;
    }

    public StylesTable() {
        this.doc = StyleSheetDocument.Factory.newInstance();
        this.doc.addNewStyleSheet();
        this.initialize();
    }

    public StylesTable(PackagePart part) throws IOException {
        super(part);
        this.readFrom(part.getInputStream());
    }

    public void setWorkbook(XSSFWorkbook wb) {
        this.workbook = wb;
    }

    public ThemesTable getTheme() {
        return this.theme;
    }

    public void setTheme(ThemesTable theme) {
        this.theme = theme;
        if (theme != null) {
            theme.setColorMap(this.getIndexedColors());
        }
        for (XSSFFont font : this.fonts) {
            font.setThemesTable(theme);
        }
        for (XSSFCellBorder border : this.borders) {
            border.setThemesTable(theme);
        }
    }

    public void ensureThemesTable() {
        if (this.theme != null) {
            return;
        }
        this.setTheme((ThemesTable)this.workbook.createRelationship(XSSFRelation.THEME, XSSFFactory.getInstance()));
    }

    public void readFrom(InputStream is) throws IOException {
        try {
            CTTableStyles ctTableStyles;
            CTDxfs styleDxfs;
            CTCellStyleXfs cellStyleXfs;
            CTCellXfs cellXfs;
            CTBorders ctborders;
            CTFills ctfills;
            CTFonts ctfonts;
            int formatId;
            CTNumFmts ctfmts;
            this.doc = StyleSheetDocument.Factory.parse(is, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
            CTStylesheet styleSheet = this.doc.getStyleSheet();
            CustomIndexedColorMap customColors = CustomIndexedColorMap.fromColors(styleSheet.getColors());
            if (customColors != null) {
                this.indexedColors = customColors;
            }
            if ((ctfmts = styleSheet.getNumFmts()) != null) {
                for (CTNumFmt nfmt : ctfmts.getNumFmtArray()) {
                    formatId = (short)nfmt.getNumFmtId();
                    this.numberFormats.put((short)formatId, nfmt.getFormatCode());
                }
            }
            if ((ctfonts = styleSheet.getFonts()) != null) {
                int idx = 0;
                CTFont[] cTFontArray = ctfonts.getFontArray();
                int nfmt = cTFontArray.length;
                for (formatId = 0; formatId < nfmt; ++formatId) {
                    CTFont font = cTFontArray[formatId];
                    XSSFFont f = new XSSFFont(font, idx, this.indexedColors);
                    this.fonts.add(f);
                    ++idx;
                }
            }
            if ((ctfills = styleSheet.getFills()) != null) {
                for (CTFill fill : ctfills.getFillArray()) {
                    this.fills.add(new XSSFCellFill(fill, this.indexedColors));
                }
            }
            if ((ctborders = styleSheet.getBorders()) != null) {
                for (CTBorder border : ctborders.getBorderArray()) {
                    this.borders.add(new XSSFCellBorder(border, this.indexedColors));
                }
            }
            if ((cellXfs = styleSheet.getCellXfs()) != null) {
                this.xfs.addAll(Arrays.asList(cellXfs.getXfArray()));
            }
            if ((cellStyleXfs = styleSheet.getCellStyleXfs()) != null) {
                this.styleXfs.addAll(Arrays.asList(cellStyleXfs.getXfArray()));
            }
            if ((styleDxfs = styleSheet.getDxfs()) != null) {
                this.dxfs.addAll(Arrays.asList(styleDxfs.getDxfArray()));
            }
            if ((ctTableStyles = styleSheet.getTableStyles()) != null) {
                int idx = 0;
                for (CTTableStyle style : ctTableStyles.getTableStyleArray()) {
                    this.tableStyles.put(style.getName(), new XSSFTableStyle(idx, styleDxfs, style, this.indexedColors));
                    ++idx;
                }
            }
        } catch (XmlException e) {
            throw new IOException(e.getLocalizedMessage());
        }
    }

    @Override
    public String getNumberFormatAt(short fmtId) {
        return (String)this.numberFormats.get(fmtId);
    }

    private short getNumberFormatId(String fmt) {
        for (Map.Entry<Short, String> numFmt : this.numberFormats.entrySet()) {
            if (!numFmt.getValue().equals(fmt)) continue;
            return numFmt.getKey();
        }
        throw new IllegalStateException("Number format not in style table: " + fmt);
    }

    @Override
    public int putNumberFormat(String fmt) {
        short formatIndex;
        if (this.numberFormats.containsValue(fmt)) {
            try {
                return this.getNumberFormatId(fmt);
            } catch (IllegalStateException e) {
                throw new IllegalStateException("Found the format, but couldn't figure out where - should never happen!");
            }
        }
        if (this.numberFormats.size() >= this.MAXIMUM_NUMBER_OF_DATA_FORMATS) {
            throw new IllegalStateException("The maximum number of Data Formats was exceeded. You can define up to " + this.MAXIMUM_NUMBER_OF_DATA_FORMATS + " formats in a .xlsx Workbook.");
        }
        if (this.numberFormats.isEmpty()) {
            formatIndex = 164;
        } else {
            short nextKey = (short)(this.numberFormats.lastKey() + 1);
            if (nextKey < 0) {
                throw new IllegalStateException("Cowardly avoiding creating a number format with a negative id. This is probably due to arithmetic overflow.");
            }
            formatIndex = (short)Math.max(nextKey, 164);
        }
        this.numberFormats.put(formatIndex, fmt);
        return formatIndex;
    }

    @Override
    public void putNumberFormat(short index, String fmt) {
        this.numberFormats.put(index, fmt);
    }

    @Override
    public boolean removeNumberFormat(short index) {
        boolean removed;
        String fmt = (String)this.numberFormats.remove(index);
        boolean bl = removed = fmt != null;
        if (removed) {
            for (CTXf style : this.xfs) {
                if (!style.isSetNumFmtId() || style.getNumFmtId() != (long)index) continue;
                style.unsetApplyNumberFormat();
                style.unsetNumFmtId();
            }
        }
        return removed;
    }

    @Override
    public boolean removeNumberFormat(String fmt) {
        short id = this.getNumberFormatId(fmt);
        return this.removeNumberFormat(id);
    }

    @Override
    public XSSFFont getFontAt(int idx) {
        return this.fonts.get(idx);
    }

    @Override
    public int putFont(XSSFFont font, boolean forceRegistration) {
        int idx = -1;
        if (!forceRegistration) {
            idx = this.fonts.indexOf(font);
        }
        if (idx != -1) {
            return idx;
        }
        idx = this.fonts.size();
        this.fonts.add(font);
        return idx;
    }

    @Override
    public int putFont(XSSFFont font) {
        return this.putFont(font, false);
    }

    @Override
    public XSSFCellStyle getStyleAt(int idx) {
        int styleXfId = 0;
        if (idx < 0 || idx >= this.xfs.size()) {
            return null;
        }
        if (this.xfs.get(idx).getXfId() > 0L) {
            styleXfId = (int)this.xfs.get(idx).getXfId();
        }
        return new XSSFCellStyle(idx, styleXfId, this, this.theme);
    }

    @Override
    public int putStyle(XSSFCellStyle style) {
        CTXf mainXF = style.getCoreXf();
        if (!this.xfs.contains(mainXF)) {
            this.xfs.add(mainXF);
        }
        return this.xfs.indexOf(mainXF);
    }

    @Override
    public XSSFCellBorder getBorderAt(int idx) {
        return this.borders.get(idx);
    }

    @Override
    public int putBorder(XSSFCellBorder border) {
        int idx = this.borders.indexOf(border);
        if (idx != -1) {
            return idx;
        }
        this.borders.add(border);
        border.setThemesTable(this.theme);
        return this.borders.size() - 1;
    }

    @Override
    public XSSFCellFill getFillAt(int idx) {
        return this.fills.get(idx);
    }

    public List<XSSFCellBorder> getBorders() {
        return Collections.unmodifiableList(this.borders);
    }

    public List<XSSFCellFill> getFills() {
        return Collections.unmodifiableList(this.fills);
    }

    public List<XSSFFont> getFonts() {
        return Collections.unmodifiableList(this.fonts);
    }

    public Map<Short, String> getNumberFormats() {
        return Collections.unmodifiableMap(this.numberFormats);
    }

    @Override
    public int putFill(XSSFCellFill fill) {
        int idx = this.fills.indexOf(fill);
        if (idx != -1) {
            return idx;
        }
        this.fills.add(fill);
        return this.fills.size() - 1;
    }

    @Internal
    public CTXf getCellXfAt(int idx) {
        return this.xfs.get(idx);
    }

    @Internal
    public int putCellXf(CTXf cellXf) {
        this.xfs.add(cellXf);
        return this.xfs.size();
    }

    @Internal
    public void replaceCellXfAt(int idx, CTXf cellXf) {
        this.xfs.set(idx, cellXf);
    }

    @Internal
    public CTXf getCellStyleXfAt(int idx) {
        try {
            return this.styleXfs.get(idx);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Internal
    public int putCellStyleXf(CTXf cellStyleXf) {
        this.styleXfs.add(cellStyleXf);
        return this.styleXfs.size();
    }

    @Internal
    protected void replaceCellStyleXfAt(int idx, CTXf cellStyleXf) {
        this.styleXfs.set(idx, cellStyleXf);
    }

    @Override
    public int getNumCellStyles() {
        return this.xfs.size();
    }

    @Override
    public int getNumDataFormats() {
        return this.numberFormats.size();
    }

    @Internal
    int _getXfsSize() {
        return this.xfs.size();
    }

    @Internal
    public int _getStyleXfsSize() {
        return this.styleXfs.size();
    }

    @Internal
    public CTStylesheet getCTStylesheet() {
        return this.doc.getStyleSheet();
    }

    @Internal
    public int _getDXfsSize() {
        return this.dxfs.size();
    }

    public void writeTo(OutputStream out) throws IOException {
        CTStylesheet styleSheet = this.doc.getStyleSheet();
        CTNumFmts formats = CTNumFmts.Factory.newInstance();
        formats.setCount(this.numberFormats.size());
        for (Map.Entry<Short, String> entry : this.numberFormats.entrySet()) {
            CTNumFmt ctFmt = formats.addNewNumFmt();
            ctFmt.setNumFmtId(entry.getKey().shortValue());
            ctFmt.setFormatCode(entry.getValue());
        }
        styleSheet.setNumFmts(formats);
        CTFonts ctFonts = styleSheet.getFonts();
        if (ctFonts == null) {
            ctFonts = CTFonts.Factory.newInstance();
        }
        ctFonts.setCount(this.fonts.size());
        CTFont[] ctfnt = new CTFont[this.fonts.size()];
        int idx = 0;
        for (XSSFFont f : this.fonts) {
            ctfnt[idx++] = f.getCTFont();
        }
        ctFonts.setFontArray(ctfnt);
        styleSheet.setFonts(ctFonts);
        CTFills ctFills = styleSheet.getFills();
        if (ctFills == null) {
            ctFills = CTFills.Factory.newInstance();
        }
        ctFills.setCount(this.fills.size());
        CTFill[] ctf = new CTFill[this.fills.size()];
        idx = 0;
        for (XSSFCellFill f : this.fills) {
            ctf[idx++] = f.getCTFill();
        }
        ctFills.setFillArray(ctf);
        styleSheet.setFills(ctFills);
        CTBorders ctBorders = styleSheet.getBorders();
        if (ctBorders == null) {
            ctBorders = CTBorders.Factory.newInstance();
        }
        ctBorders.setCount(this.borders.size());
        CTBorder[] ctb = new CTBorder[this.borders.size()];
        idx = 0;
        for (XSSFCellBorder b : this.borders) {
            ctb[idx++] = b.getCTBorder();
        }
        ctBorders.setBorderArray(ctb);
        styleSheet.setBorders(ctBorders);
        if (this.xfs.size() > 0) {
            CTCellXfs ctXfs = styleSheet.getCellXfs();
            if (ctXfs == null) {
                ctXfs = CTCellXfs.Factory.newInstance();
            }
            ctXfs.setCount(this.xfs.size());
            ctXfs.setXfArray(this.xfs.toArray(new CTXf[0]));
            styleSheet.setCellXfs(ctXfs);
        }
        if (this.styleXfs.size() > 0) {
            CTCellStyleXfs ctSXfs = styleSheet.getCellStyleXfs();
            if (ctSXfs == null) {
                ctSXfs = CTCellStyleXfs.Factory.newInstance();
            }
            ctSXfs.setCount(this.styleXfs.size());
            ctSXfs.setXfArray(this.styleXfs.toArray(new CTXf[0]));
            styleSheet.setCellStyleXfs(ctSXfs);
        }
        if (this.dxfs.size() > 0) {
            CTDxfs ctDxfs = styleSheet.getDxfs();
            if (ctDxfs == null) {
                ctDxfs = CTDxfs.Factory.newInstance();
            }
            ctDxfs.setCount(this.dxfs.size());
            ctDxfs.setDxfArray(this.dxfs.toArray(new CTDxf[0]));
            styleSheet.setDxfs(ctDxfs);
        }
        this.doc.save(out, POIXMLTypeLoader.DEFAULT_XML_OPTIONS);
    }

    @Override
    protected void commit() throws IOException {
        PackagePart part = this.getPackagePart();
        OutputStream out = part.getOutputStream();
        this.writeTo(out);
        out.close();
    }

    private void initialize() {
        XSSFFont xssfFont = StylesTable.createDefaultFont();
        this.fonts.add(xssfFont);
        CTFill[] ctFill = StylesTable.createDefaultFills();
        this.fills.add(new XSSFCellFill(ctFill[0], this.indexedColors));
        this.fills.add(new XSSFCellFill(ctFill[1], this.indexedColors));
        CTBorder ctBorder = StylesTable.createDefaultBorder();
        this.borders.add(new XSSFCellBorder(ctBorder));
        CTXf styleXf = StylesTable.createDefaultXf();
        this.styleXfs.add(styleXf);
        CTXf xf = StylesTable.createDefaultXf();
        xf.setXfId(0L);
        this.xfs.add(xf);
    }

    private static CTXf createDefaultXf() {
        CTXf ctXf = CTXf.Factory.newInstance();
        ctXf.setNumFmtId(0L);
        ctXf.setFontId(0L);
        ctXf.setFillId(0L);
        ctXf.setBorderId(0L);
        return ctXf;
    }

    private static CTBorder createDefaultBorder() {
        CTBorder ctBorder = CTBorder.Factory.newInstance();
        ctBorder.addNewBottom();
        ctBorder.addNewTop();
        ctBorder.addNewLeft();
        ctBorder.addNewRight();
        ctBorder.addNewDiagonal();
        return ctBorder;
    }

    private static CTFill[] createDefaultFills() {
        CTFill[] ctFill = new CTFill[]{CTFill.Factory.newInstance(), CTFill.Factory.newInstance()};
        ctFill[0].addNewPatternFill().setPatternType(STPatternType.NONE);
        ctFill[1].addNewPatternFill().setPatternType(STPatternType.DARK_GRAY);
        return ctFill;
    }

    private static XSSFFont createDefaultFont() {
        CTFont ctFont = CTFont.Factory.newInstance();
        XSSFFont xssfFont = new XSSFFont(ctFont, 0, null);
        xssfFont.setFontHeightInPoints((short)11);
        xssfFont.setColor(XSSFFont.DEFAULT_FONT_COLOR);
        xssfFont.setFontName("Calibri");
        xssfFont.setFamily(FontFamily.SWISS);
        xssfFont.setScheme(FontScheme.MINOR);
        return xssfFont;
    }

    @Internal
    public CTDxf getDxfAt(int idx) {
        return this.dxfs.get(idx);
    }

    @Internal
    public int putDxf(CTDxf dxf) {
        this.dxfs.add(dxf);
        return this.dxfs.size();
    }

    public TableStyle getExplicitTableStyle(String name) {
        return this.tableStyles.get(name);
    }

    public Set<String> getExplicitTableStyleNames() {
        return this.tableStyles.keySet();
    }

    public TableStyle getTableStyle(String name) {
        if (name == null) {
            return null;
        }
        try {
            return XSSFBuiltinTableStyle.valueOf(name).getStyle();
        } catch (IllegalArgumentException e) {
            return this.getExplicitTableStyle(name);
        }
    }

    public XSSFCellStyle createCellStyle() {
        if (this.getNumCellStyles() > MAXIMUM_STYLE_ID) {
            throw new IllegalStateException("The maximum number of Cell Styles was exceeded. You can define up to " + MAXIMUM_STYLE_ID + " style in a .xlsx Workbook");
        }
        int xfSize = this.styleXfs.size();
        CTXf xf = CTXf.Factory.newInstance();
        xf.setNumFmtId(0L);
        xf.setFontId(0L);
        xf.setFillId(0L);
        xf.setBorderId(0L);
        xf.setXfId(0L);
        int indexXf = this.putCellXf(xf);
        return new XSSFCellStyle(indexXf - 1, xfSize - 1, this, this.theme);
    }

    public XSSFFont findFont(boolean bold, short color, short fontHeight, String name, boolean italic, boolean strikeout, short typeOffset, byte underline) {
        for (XSSFFont font : this.fonts) {
            if (font.getBold() != bold || font.getColor() != color || font.getFontHeight() != fontHeight || !font.getFontName().equals(name) || font.getItalic() != italic || font.getStrikeout() != strikeout || font.getTypeOffset() != typeOffset || font.getUnderline() != underline) continue;
            return font;
        }
        return null;
    }

    public XSSFFont findFont(boolean bold, Color color, short fontHeight, String name, boolean italic, boolean strikeout, short typeOffset, byte underline) {
        for (XSSFFont font : this.fonts) {
            if (font.getBold() != bold || !font.getXSSFColor().equals(color) || font.getFontHeight() != fontHeight || !font.getFontName().equals(name) || font.getItalic() != italic || font.getStrikeout() != strikeout || font.getTypeOffset() != typeOffset || font.getUnderline() != underline) continue;
            return font;
        }
        return null;
    }

    public IndexedColorMap getIndexedColors() {
        return this.indexedColors;
    }
}

