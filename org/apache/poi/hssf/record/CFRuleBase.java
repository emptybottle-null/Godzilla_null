/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record;

import org.apache.poi.hssf.model.HSSFFormulaParser;
import org.apache.poi.hssf.record.CFRuleRecord;
import org.apache.poi.hssf.record.RecordInputStream;
import org.apache.poi.hssf.record.StandardRecord;
import org.apache.poi.hssf.record.cf.BorderFormatting;
import org.apache.poi.hssf.record.cf.FontFormatting;
import org.apache.poi.hssf.record.cf.PatternFormatting;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.util.BitField;
import org.apache.poi.util.BitFieldFactory;
import org.apache.poi.util.LittleEndianOutput;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.Removal;

public abstract class CFRuleBase
extends StandardRecord {
    public static final byte CONDITION_TYPE_CELL_VALUE_IS = 1;
    public static final byte CONDITION_TYPE_FORMULA = 2;
    public static final byte CONDITION_TYPE_COLOR_SCALE = 3;
    public static final byte CONDITION_TYPE_DATA_BAR = 4;
    public static final byte CONDITION_TYPE_FILTER = 5;
    public static final byte CONDITION_TYPE_ICON_SET = 6;
    public static final int TEMPLATE_CELL_VALUE = 0;
    public static final int TEMPLATE_FORMULA = 1;
    public static final int TEMPLATE_COLOR_SCALE_FORMATTING = 2;
    public static final int TEMPLATE_DATA_BAR_FORMATTING = 3;
    public static final int TEMPLATE_ICON_SET_FORMATTING = 4;
    public static final int TEMPLATE_FILTER = 5;
    public static final int TEMPLATE_UNIQUE_VALUES = 7;
    public static final int TEMPLATE_CONTAINS_TEXT = 8;
    public static final int TEMPLATE_CONTAINS_BLANKS = 9;
    public static final int TEMPLATE_CONTAINS_NO_BLANKS = 10;
    public static final int TEMPLATE_CONTAINS_ERRORS = 11;
    public static final int TEMPLATE_CONTAINS_NO_ERRORS = 12;
    public static final int TEMPLATE_TODAY = 15;
    public static final int TEMPLATE_TOMORROW = 16;
    public static final int TEMPLATE_YESTERDAY = 17;
    public static final int TEMPLATE_LAST_7_DAYS = 18;
    public static final int TEMPLATE_LAST_MONTH = 19;
    public static final int TEMPLATE_NEXT_MONTH = 20;
    public static final int TEMPLATE_THIS_WEEK = 21;
    public static final int TEMPLATE_NEXT_WEEK = 22;
    public static final int TEMPLATE_LAST_WEEK = 23;
    public static final int TEMPLATE_THIS_MONTH = 24;
    public static final int TEMPLATE_ABOVE_AVERAGE = 25;
    public static final int TEMPLATE_BELOW_AVERAGE = 26;
    public static final int TEMPLATE_DUPLICATE_VALUES = 27;
    public static final int TEMPLATE_ABOVE_OR_EQUAL_TO_AVERAGE = 29;
    public static final int TEMPLATE_BELOW_OR_EQUAL_TO_AVERAGE = 30;
    protected static final POILogger logger = POILogFactory.getLogger(CFRuleBase.class);
    static final BitField modificationBits = CFRuleBase.bf(0x3FFFFF);
    static final BitField alignHor = CFRuleBase.bf(1);
    static final BitField alignVer = CFRuleBase.bf(2);
    static final BitField alignWrap = CFRuleBase.bf(4);
    static final BitField alignRot = CFRuleBase.bf(8);
    static final BitField alignJustLast = CFRuleBase.bf(16);
    static final BitField alignIndent = CFRuleBase.bf(32);
    static final BitField alignShrin = CFRuleBase.bf(64);
    static final BitField mergeCell = CFRuleBase.bf(128);
    static final BitField protLocked = CFRuleBase.bf(256);
    static final BitField protHidden = CFRuleBase.bf(512);
    static final BitField bordLeft = CFRuleBase.bf(1024);
    static final BitField bordRight = CFRuleBase.bf(2048);
    static final BitField bordTop = CFRuleBase.bf(4096);
    static final BitField bordBot = CFRuleBase.bf(8192);
    static final BitField bordTlBr = CFRuleBase.bf(16384);
    static final BitField bordBlTr = CFRuleBase.bf(32768);
    static final BitField pattStyle = CFRuleBase.bf(65536);
    static final BitField pattCol = CFRuleBase.bf(131072);
    static final BitField pattBgCol = CFRuleBase.bf(262144);
    static final BitField notUsed2 = CFRuleBase.bf(0x380000);
    static final BitField undocumented = CFRuleBase.bf(0x3C00000);
    static final BitField fmtBlockBits = CFRuleBase.bf(0x7C000000);
    static final BitField font = CFRuleBase.bf(0x4000000);
    static final BitField align = CFRuleBase.bf(0x8000000);
    static final BitField bord = CFRuleBase.bf(0x10000000);
    static final BitField patt = CFRuleBase.bf(0x20000000);
    static final BitField prot = CFRuleBase.bf(0x40000000);
    static final BitField alignTextDir = CFRuleBase.bf(Integer.MIN_VALUE);
    private byte condition_type;
    private byte comparison_operator;
    protected int formatting_options;
    protected short formatting_not_used;
    protected FontFormatting _fontFormatting;
    protected BorderFormatting _borderFormatting;
    protected PatternFormatting _patternFormatting;
    private Formula formula1;
    private Formula formula2;

    private static BitField bf(int i) {
        return BitFieldFactory.getInstance(i);
    }

    protected CFRuleBase(byte conditionType, byte comparisonOperation) {
        this.setConditionType(conditionType);
        this.setComparisonOperation(comparisonOperation);
        this.formula1 = Formula.create(Ptg.EMPTY_PTG_ARRAY);
        this.formula2 = Formula.create(Ptg.EMPTY_PTG_ARRAY);
    }

    protected CFRuleBase(byte conditionType, byte comparisonOperation, Ptg[] formula1, Ptg[] formula2) {
        this(conditionType, comparisonOperation);
        this.formula1 = Formula.create(formula1);
        this.formula2 = Formula.create(formula2);
    }

    protected CFRuleBase() {
    }

    protected CFRuleBase(CFRuleBase other) {
        super(other);
        this.setConditionType(other.getConditionType());
        this.setComparisonOperation(other.getComparisonOperation());
        this.formatting_options = other.formatting_options;
        this.formatting_not_used = other.formatting_not_used;
        this._fontFormatting = !other.containsFontFormattingBlock() ? null : other.getFontFormatting().copy();
        this._borderFormatting = !other.containsBorderFormattingBlock() ? null : other.getBorderFormatting().copy();
        this._patternFormatting = !other.containsPatternFormattingBlock() ? null : other.getPatternFormatting().copy();
        this.formula1 = other.getFormula1().copy();
        this.formula2 = other.getFormula2().copy();
    }

    protected int readFormatOptions(RecordInputStream in) {
        this.formatting_options = in.readInt();
        this.formatting_not_used = in.readShort();
        int len = 6;
        if (this.containsFontFormattingBlock()) {
            this._fontFormatting = new FontFormatting(in);
            len += this._fontFormatting.getDataLength();
        }
        if (this.containsBorderFormattingBlock()) {
            this._borderFormatting = new BorderFormatting(in);
            len += this._borderFormatting.getDataLength();
        }
        if (this.containsPatternFormattingBlock()) {
            this._patternFormatting = new PatternFormatting(in);
            len += this._patternFormatting.getDataLength();
        }
        return len;
    }

    public byte getConditionType() {
        return this.condition_type;
    }

    protected void setConditionType(byte condition_type) {
        if (this instanceof CFRuleRecord && condition_type != 1 && condition_type != 2) {
            throw new IllegalArgumentException("CFRuleRecord only accepts Value-Is and Formula types");
        }
        this.condition_type = condition_type;
    }

    public void setComparisonOperation(byte operation) {
        if (operation < 0 || operation > 8) {
            throw new IllegalArgumentException("Valid operators are only in the range 0 to 8");
        }
        this.comparison_operator = operation;
    }

    public byte getComparisonOperation() {
        return this.comparison_operator;
    }

    public boolean containsFontFormattingBlock() {
        return this.getOptionFlag(font);
    }

    public void setFontFormatting(FontFormatting fontFormatting) {
        this._fontFormatting = fontFormatting;
        this.setOptionFlag(fontFormatting != null, font);
    }

    public FontFormatting getFontFormatting() {
        if (this.containsFontFormattingBlock()) {
            return this._fontFormatting;
        }
        return null;
    }

    public boolean containsAlignFormattingBlock() {
        return this.getOptionFlag(align);
    }

    public void setAlignFormattingUnchanged() {
        this.setOptionFlag(false, align);
    }

    public boolean containsBorderFormattingBlock() {
        return this.getOptionFlag(bord);
    }

    public void setBorderFormatting(BorderFormatting borderFormatting) {
        this._borderFormatting = borderFormatting;
        this.setOptionFlag(borderFormatting != null, bord);
    }

    public BorderFormatting getBorderFormatting() {
        if (this.containsBorderFormattingBlock()) {
            return this._borderFormatting;
        }
        return null;
    }

    public boolean containsPatternFormattingBlock() {
        return this.getOptionFlag(patt);
    }

    public void setPatternFormatting(PatternFormatting patternFormatting) {
        this._patternFormatting = patternFormatting;
        this.setOptionFlag(patternFormatting != null, patt);
    }

    public PatternFormatting getPatternFormatting() {
        if (this.containsPatternFormattingBlock()) {
            return this._patternFormatting;
        }
        return null;
    }

    public boolean containsProtectionFormattingBlock() {
        return this.getOptionFlag(prot);
    }

    public void setProtectionFormattingUnchanged() {
        this.setOptionFlag(false, prot);
    }

    public int getOptions() {
        return this.formatting_options;
    }

    private boolean isModified(BitField field) {
        return !field.isSet(this.formatting_options);
    }

    private void setModified(boolean modified, BitField field) {
        this.formatting_options = field.setBoolean(this.formatting_options, !modified);
    }

    public boolean isLeftBorderModified() {
        return this.isModified(bordLeft);
    }

    public void setLeftBorderModified(boolean modified) {
        this.setModified(modified, bordLeft);
    }

    public boolean isRightBorderModified() {
        return this.isModified(bordRight);
    }

    public void setRightBorderModified(boolean modified) {
        this.setModified(modified, bordRight);
    }

    public boolean isTopBorderModified() {
        return this.isModified(bordTop);
    }

    public void setTopBorderModified(boolean modified) {
        this.setModified(modified, bordTop);
    }

    public boolean isBottomBorderModified() {
        return this.isModified(bordBot);
    }

    public void setBottomBorderModified(boolean modified) {
        this.setModified(modified, bordBot);
    }

    public boolean isTopLeftBottomRightBorderModified() {
        return this.isModified(bordTlBr);
    }

    public void setTopLeftBottomRightBorderModified(boolean modified) {
        this.setModified(modified, bordTlBr);
    }

    public boolean isBottomLeftTopRightBorderModified() {
        return this.isModified(bordBlTr);
    }

    public void setBottomLeftTopRightBorderModified(boolean modified) {
        this.setModified(modified, bordBlTr);
    }

    public boolean isPatternStyleModified() {
        return this.isModified(pattStyle);
    }

    public void setPatternStyleModified(boolean modified) {
        this.setModified(modified, pattStyle);
    }

    public boolean isPatternColorModified() {
        return this.isModified(pattCol);
    }

    public void setPatternColorModified(boolean modified) {
        this.setModified(modified, pattCol);
    }

    public boolean isPatternBackgroundColorModified() {
        return this.isModified(pattBgCol);
    }

    public void setPatternBackgroundColorModified(boolean modified) {
        this.setModified(modified, pattBgCol);
    }

    private boolean getOptionFlag(BitField field) {
        return field.isSet(this.formatting_options);
    }

    private void setOptionFlag(boolean flag, BitField field) {
        this.formatting_options = field.setBoolean(this.formatting_options, flag);
    }

    protected int getFormattingBlockSize() {
        return 6 + (this.containsFontFormattingBlock() ? this._fontFormatting.getRawRecord().length : 0) + (this.containsBorderFormattingBlock() ? 8 : 0) + (this.containsPatternFormattingBlock() ? 4 : 0);
    }

    protected void serializeFormattingBlock(LittleEndianOutput out) {
        out.writeInt(this.formatting_options);
        out.writeShort(this.formatting_not_used);
        if (this.containsFontFormattingBlock()) {
            byte[] fontFormattingRawRecord = this._fontFormatting.getRawRecord();
            out.write(fontFormattingRawRecord);
        }
        if (this.containsBorderFormattingBlock()) {
            this._borderFormatting.serialize(out);
        }
        if (this.containsPatternFormattingBlock()) {
            this._patternFormatting.serialize(out);
        }
    }

    public Ptg[] getParsedExpression1() {
        return this.formula1.getTokens();
    }

    public void setParsedExpression1(Ptg[] ptgs) {
        this.formula1 = Formula.create(ptgs);
    }

    protected Formula getFormula1() {
        return this.formula1;
    }

    protected void setFormula1(Formula formula1) {
        this.formula1 = formula1;
    }

    public Ptg[] getParsedExpression2() {
        return Formula.getTokens(this.formula2);
    }

    public void setParsedExpression2(Ptg[] ptgs) {
        this.formula2 = Formula.create(ptgs);
    }

    protected Formula getFormula2() {
        return this.formula2;
    }

    protected void setFormula2(Formula formula2) {
        this.formula2 = formula2;
    }

    protected static int getFormulaSize(Formula formula) {
        return formula.getEncodedTokenSize();
    }

    public static Ptg[] parseFormula(String formula, HSSFSheet sheet) {
        if (formula == null) {
            return null;
        }
        int sheetIndex = sheet.getWorkbook().getSheetIndex(sheet);
        return HSSFFormulaParser.parse(formula, sheet.getWorkbook(), FormulaType.CELL, sheetIndex);
    }

    @Deprecated
    @Removal(version="5.0.0")
    public abstract CFRuleBase clone();

    @Override
    public abstract CFRuleBase copy();

    public static interface ComparisonOperator {
        public static final byte NO_COMPARISON = 0;
        public static final byte BETWEEN = 1;
        public static final byte NOT_BETWEEN = 2;
        public static final byte EQUAL = 3;
        public static final byte NOT_EQUAL = 4;
        public static final byte GT = 5;
        public static final byte LT = 6;
        public static final byte GE = 7;
        public static final byte LE = 8;
        public static final byte max_operator = 8;
    }
}

