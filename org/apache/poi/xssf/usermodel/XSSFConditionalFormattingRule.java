/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.ConditionFilterData;
import org.apache.poi.ss.usermodel.ConditionFilterType;
import org.apache.poi.ss.usermodel.ConditionType;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.ConditionalFormattingThreshold;
import org.apache.poi.ss.usermodel.ExcelNumberFormat;
import org.apache.poi.ss.usermodel.IconMultiStateFormatting;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFBorderFormatting;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFColorScaleFormatting;
import org.apache.poi.xssf.usermodel.XSSFConditionFilterData;
import org.apache.poi.xssf.usermodel.XSSFDataBarFormatting;
import org.apache.poi.xssf.usermodel.XSSFFontFormatting;
import org.apache.poi.xssf.usermodel.XSSFIconMultiStateFormatting;
import org.apache.poi.xssf.usermodel.XSSFPatternFormatting;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTBorder;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfRule;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCfvo;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTColorScale;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDataBar;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTDxf;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFill;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFont;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTIconSet;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTNumFmt;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCfType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STCfvoType;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STConditionalFormattingOperator;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType;

public class XSSFConditionalFormattingRule
implements ConditionalFormattingRule {
    private final CTCfRule _cfRule;
    private XSSFSheet _sh;
    private static Map<STCfType.Enum, ConditionType> typeLookup = new HashMap<STCfType.Enum, ConditionType>();
    private static Map<STCfType.Enum, ConditionFilterType> filterTypeLookup = new HashMap<STCfType.Enum, ConditionFilterType>();

    XSSFConditionalFormattingRule(XSSFSheet sh) {
        this._cfRule = CTCfRule.Factory.newInstance();
        this._sh = sh;
    }

    XSSFConditionalFormattingRule(XSSFSheet sh, CTCfRule cfRule) {
        this._cfRule = cfRule;
        this._sh = sh;
    }

    CTCfRule getCTCfRule() {
        return this._cfRule;
    }

    CTDxf getDxf(boolean create) {
        int dxfId;
        StylesTable styles = this._sh.getWorkbook().getStylesSource();
        CTDxf dxf = null;
        if (styles._getDXfsSize() > 0 && this._cfRule.isSetDxfId()) {
            dxfId = (int)this._cfRule.getDxfId();
            dxf = styles.getDxfAt(dxfId);
        }
        if (create && dxf == null) {
            dxf = CTDxf.Factory.newInstance();
            dxfId = styles.putDxf(dxf);
            this._cfRule.setDxfId(dxfId - 1);
        }
        return dxf;
    }

    @Override
    public int getPriority() {
        int priority = this._cfRule.getPriority();
        return priority >= 1 ? priority : 0;
    }

    @Override
    public boolean getStopIfTrue() {
        return this._cfRule.getStopIfTrue();
    }

    @Override
    public XSSFBorderFormatting createBorderFormatting() {
        CTDxf dxf = this.getDxf(true);
        CTBorder border = !dxf.isSetBorder() ? dxf.addNewBorder() : dxf.getBorder();
        return new XSSFBorderFormatting(border, this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    @Override
    public XSSFBorderFormatting getBorderFormatting() {
        CTDxf dxf = this.getDxf(false);
        if (dxf == null || !dxf.isSetBorder()) {
            return null;
        }
        return new XSSFBorderFormatting(dxf.getBorder(), this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    @Override
    public XSSFFontFormatting createFontFormatting() {
        CTDxf dxf = this.getDxf(true);
        CTFont font = !dxf.isSetFont() ? dxf.addNewFont() : dxf.getFont();
        return new XSSFFontFormatting(font, this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    @Override
    public XSSFFontFormatting getFontFormatting() {
        CTDxf dxf = this.getDxf(false);
        if (dxf == null || !dxf.isSetFont()) {
            return null;
        }
        return new XSSFFontFormatting(dxf.getFont(), this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    @Override
    public XSSFPatternFormatting createPatternFormatting() {
        CTDxf dxf = this.getDxf(true);
        CTFill fill = !dxf.isSetFill() ? dxf.addNewFill() : dxf.getFill();
        return new XSSFPatternFormatting(fill, this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    @Override
    public XSSFPatternFormatting getPatternFormatting() {
        CTDxf dxf = this.getDxf(false);
        if (dxf == null || !dxf.isSetFill()) {
            return null;
        }
        return new XSSFPatternFormatting(dxf.getFill(), this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    public XSSFDataBarFormatting createDataBarFormatting(XSSFColor color) {
        if (this._cfRule.isSetDataBar() && this._cfRule.getType() == STCfType.DATA_BAR) {
            return this.getDataBarFormatting();
        }
        this._cfRule.setType(STCfType.DATA_BAR);
        CTDataBar bar = null;
        bar = this._cfRule.isSetDataBar() ? this._cfRule.getDataBar() : this._cfRule.addNewDataBar();
        bar.setColor(color.getCTColor());
        CTCfvo min = bar.addNewCfvo();
        min.setType(STCfvoType.Enum.forString(ConditionalFormattingThreshold.RangeType.MIN.name));
        CTCfvo max = bar.addNewCfvo();
        max.setType(STCfvoType.Enum.forString(ConditionalFormattingThreshold.RangeType.MAX.name));
        return new XSSFDataBarFormatting(bar, this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    @Override
    public XSSFDataBarFormatting getDataBarFormatting() {
        if (this._cfRule.isSetDataBar()) {
            CTDataBar bar = this._cfRule.getDataBar();
            return new XSSFDataBarFormatting(bar, this._sh.getWorkbook().getStylesSource().getIndexedColors());
        }
        return null;
    }

    public XSSFIconMultiStateFormatting createMultiStateFormatting(IconMultiStateFormatting.IconSet iconSet) {
        if (this._cfRule.isSetIconSet() && this._cfRule.getType() == STCfType.ICON_SET) {
            return this.getMultiStateFormatting();
        }
        this._cfRule.setType(STCfType.ICON_SET);
        CTIconSet icons = null;
        icons = this._cfRule.isSetIconSet() ? this._cfRule.getIconSet() : this._cfRule.addNewIconSet();
        if (iconSet.name != null) {
            STIconSetType.Enum xIconSet = STIconSetType.Enum.forString(iconSet.name);
            icons.setIconSet(xIconSet);
        }
        int jump = 100 / iconSet.num;
        STCfvoType.Enum type = STCfvoType.Enum.forString(ConditionalFormattingThreshold.RangeType.PERCENT.name);
        for (int i = 0; i < iconSet.num; ++i) {
            CTCfvo cfvo = icons.addNewCfvo();
            cfvo.setType(type);
            cfvo.setVal(Integer.toString(i * jump));
        }
        return new XSSFIconMultiStateFormatting(icons);
    }

    @Override
    public XSSFIconMultiStateFormatting getMultiStateFormatting() {
        if (this._cfRule.isSetIconSet()) {
            CTIconSet icons = this._cfRule.getIconSet();
            return new XSSFIconMultiStateFormatting(icons);
        }
        return null;
    }

    public XSSFColorScaleFormatting createColorScaleFormatting() {
        if (this._cfRule.isSetColorScale() && this._cfRule.getType() == STCfType.COLOR_SCALE) {
            return this.getColorScaleFormatting();
        }
        this._cfRule.setType(STCfType.COLOR_SCALE);
        CTColorScale scale = null;
        scale = this._cfRule.isSetColorScale() ? this._cfRule.getColorScale() : this._cfRule.addNewColorScale();
        if (scale.sizeOfCfvoArray() == 0) {
            CTCfvo cfvo = scale.addNewCfvo();
            cfvo.setType(STCfvoType.Enum.forString(ConditionalFormattingThreshold.RangeType.MIN.name));
            cfvo = scale.addNewCfvo();
            cfvo.setType(STCfvoType.Enum.forString(ConditionalFormattingThreshold.RangeType.PERCENTILE.name));
            cfvo.setVal("50");
            cfvo = scale.addNewCfvo();
            cfvo.setType(STCfvoType.Enum.forString(ConditionalFormattingThreshold.RangeType.MAX.name));
            for (int i = 0; i < 3; ++i) {
                scale.addNewColor();
            }
        }
        return new XSSFColorScaleFormatting(scale, this._sh.getWorkbook().getStylesSource().getIndexedColors());
    }

    @Override
    public XSSFColorScaleFormatting getColorScaleFormatting() {
        if (this._cfRule.isSetColorScale()) {
            CTColorScale scale = this._cfRule.getColorScale();
            return new XSSFColorScaleFormatting(scale, this._sh.getWorkbook().getStylesSource().getIndexedColors());
        }
        return null;
    }

    @Override
    public ExcelNumberFormat getNumberFormat() {
        CTDxf dxf = this.getDxf(false);
        if (dxf == null || !dxf.isSetNumFmt()) {
            return null;
        }
        CTNumFmt numFmt = dxf.getNumFmt();
        return new ExcelNumberFormat((int)numFmt.getNumFmtId(), numFmt.getFormatCode());
    }

    @Override
    public ConditionType getConditionType() {
        return typeLookup.get(this._cfRule.getType());
    }

    @Override
    public ConditionFilterType getConditionFilterType() {
        return filterTypeLookup.get(this._cfRule.getType());
    }

    @Override
    public ConditionFilterData getFilterConfiguration() {
        return new XSSFConditionFilterData(this._cfRule);
    }

    @Override
    public byte getComparisonOperation() {
        STConditionalFormattingOperator.Enum op = this._cfRule.getOperator();
        if (op == null) {
            return 0;
        }
        switch (op.intValue()) {
            case 1: {
                return 6;
            }
            case 2: {
                return 8;
            }
            case 6: {
                return 5;
            }
            case 5: {
                return 7;
            }
            case 3: {
                return 3;
            }
            case 4: {
                return 4;
            }
            case 7: {
                return 1;
            }
            case 8: {
                return 2;
            }
        }
        return 0;
    }

    @Override
    public String getFormula1() {
        return this._cfRule.sizeOfFormulaArray() > 0 ? this._cfRule.getFormulaArray(0) : null;
    }

    @Override
    public String getFormula2() {
        return this._cfRule.sizeOfFormulaArray() == 2 ? this._cfRule.getFormulaArray(1) : null;
    }

    @Override
    public String getText() {
        return this._cfRule.getText();
    }

    @Override
    public int getStripeSize() {
        return 0;
    }

    static {
        typeLookup.put(STCfType.CELL_IS, ConditionType.CELL_VALUE_IS);
        typeLookup.put(STCfType.EXPRESSION, ConditionType.FORMULA);
        typeLookup.put(STCfType.COLOR_SCALE, ConditionType.COLOR_SCALE);
        typeLookup.put(STCfType.DATA_BAR, ConditionType.DATA_BAR);
        typeLookup.put(STCfType.ICON_SET, ConditionType.ICON_SET);
        typeLookup.put(STCfType.TOP_10, ConditionType.FILTER);
        typeLookup.put(STCfType.UNIQUE_VALUES, ConditionType.FILTER);
        typeLookup.put(STCfType.DUPLICATE_VALUES, ConditionType.FILTER);
        typeLookup.put(STCfType.CONTAINS_TEXT, ConditionType.FILTER);
        typeLookup.put(STCfType.NOT_CONTAINS_TEXT, ConditionType.FILTER);
        typeLookup.put(STCfType.BEGINS_WITH, ConditionType.FILTER);
        typeLookup.put(STCfType.ENDS_WITH, ConditionType.FILTER);
        typeLookup.put(STCfType.CONTAINS_BLANKS, ConditionType.FILTER);
        typeLookup.put(STCfType.NOT_CONTAINS_BLANKS, ConditionType.FILTER);
        typeLookup.put(STCfType.CONTAINS_ERRORS, ConditionType.FILTER);
        typeLookup.put(STCfType.NOT_CONTAINS_ERRORS, ConditionType.FILTER);
        typeLookup.put(STCfType.TIME_PERIOD, ConditionType.FILTER);
        typeLookup.put(STCfType.ABOVE_AVERAGE, ConditionType.FILTER);
        filterTypeLookup.put(STCfType.TOP_10, ConditionFilterType.TOP_10);
        filterTypeLookup.put(STCfType.UNIQUE_VALUES, ConditionFilterType.UNIQUE_VALUES);
        filterTypeLookup.put(STCfType.DUPLICATE_VALUES, ConditionFilterType.DUPLICATE_VALUES);
        filterTypeLookup.put(STCfType.CONTAINS_TEXT, ConditionFilterType.CONTAINS_TEXT);
        filterTypeLookup.put(STCfType.NOT_CONTAINS_TEXT, ConditionFilterType.NOT_CONTAINS_TEXT);
        filterTypeLookup.put(STCfType.BEGINS_WITH, ConditionFilterType.BEGINS_WITH);
        filterTypeLookup.put(STCfType.ENDS_WITH, ConditionFilterType.ENDS_WITH);
        filterTypeLookup.put(STCfType.CONTAINS_BLANKS, ConditionFilterType.CONTAINS_BLANKS);
        filterTypeLookup.put(STCfType.NOT_CONTAINS_BLANKS, ConditionFilterType.NOT_CONTAINS_BLANKS);
        filterTypeLookup.put(STCfType.CONTAINS_ERRORS, ConditionFilterType.CONTAINS_ERRORS);
        filterTypeLookup.put(STCfType.NOT_CONTAINS_ERRORS, ConditionFilterType.NOT_CONTAINS_ERRORS);
        filterTypeLookup.put(STCfType.TIME_PERIOD, ConditionFilterType.TIME_PERIOD);
        filterTypeLookup.put(STCfType.ABOVE_AVERAGE, ConditionFilterType.ABOVE_AVERAGE);
    }
}

