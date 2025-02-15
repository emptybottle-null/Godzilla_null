/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.aggregates;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.record.CFHeader12Record;
import org.apache.poi.hssf.record.CFHeaderBase;
import org.apache.poi.hssf.record.CFHeaderRecord;
import org.apache.poi.hssf.record.CFRule12Record;
import org.apache.poi.hssf.record.CFRuleBase;
import org.apache.poi.hssf.record.CFRuleRecord;
import org.apache.poi.hssf.record.Record;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.ss.formula.FormulaShifter;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.helpers.BaseRowColShifter;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.apache.poi.util.RecordFormatException;

public final class CFRecordsAggregate
extends RecordAggregate {
    private static final int MAX_97_2003_CONDTIONAL_FORMAT_RULES = 3;
    private static final POILogger logger = POILogFactory.getLogger(CFRecordsAggregate.class);
    private final CFHeaderBase header;
    private final List<CFRuleBase> rules = new ArrayList<CFRuleBase>();

    public CFRecordsAggregate(CFRecordsAggregate other) {
        this.header = other.header.copy();
        other.rules.stream().map(t -> t.copy()).forEach(this.rules::add);
    }

    private CFRecordsAggregate(CFHeaderBase pHeader, CFRuleBase[] pRules) {
        if (pHeader == null) {
            throw new IllegalArgumentException("header must not be null");
        }
        if (pRules == null) {
            throw new IllegalArgumentException("rules must not be null");
        }
        if (pRules.length > 3) {
            logger.log(5, "Excel versions before 2007 require that No more than 3 rules may be specified, " + pRules.length + " were found, this file will cause problems with old Excel versions");
        }
        if (pRules.length != pHeader.getNumberOfConditionalFormats()) {
            throw new RecordFormatException("Mismatch number of rules");
        }
        this.header = pHeader;
        for (CFRuleBase pRule : pRules) {
            this.checkRuleType(pRule);
            this.rules.add(pRule);
        }
    }

    public CFRecordsAggregate(CellRangeAddress[] regions, CFRuleBase[] rules) {
        this(CFRecordsAggregate.createHeader(regions, rules), rules);
    }

    private static CFHeaderBase createHeader(CellRangeAddress[] regions, CFRuleBase[] rules) {
        CFHeaderBase header = rules.length == 0 || rules[0] instanceof CFRuleRecord ? new CFHeaderRecord(regions, rules.length) : new CFHeader12Record(regions, rules.length);
        header.setNeedRecalculation(true);
        return header;
    }

    public static CFRecordsAggregate createCFAggregate(RecordStream rs) {
        Record rec = rs.getNext();
        if (rec.getSid() != 432 && rec.getSid() != 2169) {
            throw new IllegalStateException("next record sid was " + rec.getSid() + " instead of " + 432 + " or " + 2169 + " as expected");
        }
        CFHeaderBase header = (CFHeaderBase)rec;
        int nRules = header.getNumberOfConditionalFormats();
        CFRuleBase[] rules = new CFRuleBase[nRules];
        for (int i = 0; i < rules.length; ++i) {
            rules[i] = (CFRuleBase)rs.getNext();
        }
        return new CFRecordsAggregate(header, rules);
    }

    public CFRecordsAggregate cloneCFAggregate() {
        return new CFRecordsAggregate(this);
    }

    public CFHeaderBase getHeader() {
        return this.header;
    }

    private void checkRuleIndex(int idx) {
        if (idx < 0 || idx >= this.rules.size()) {
            throw new IllegalArgumentException("Bad rule record index (" + idx + ") nRules=" + this.rules.size());
        }
    }

    private void checkRuleType(CFRuleBase r) {
        if (this.header instanceof CFHeaderRecord && r instanceof CFRuleRecord) {
            return;
        }
        if (this.header instanceof CFHeader12Record && r instanceof CFRule12Record) {
            return;
        }
        throw new IllegalArgumentException("Header and Rule must both be CF or both be CF12, can't mix");
    }

    public CFRuleBase getRule(int idx) {
        this.checkRuleIndex(idx);
        return this.rules.get(idx);
    }

    public void setRule(int idx, CFRuleBase r) {
        if (r == null) {
            throw new IllegalArgumentException("r must not be null");
        }
        this.checkRuleIndex(idx);
        this.checkRuleType(r);
        this.rules.set(idx, r);
    }

    public void addRule(CFRuleBase r) {
        if (r == null) {
            throw new IllegalArgumentException("r must not be null");
        }
        if (this.rules.size() >= 3) {
            logger.log(5, "Excel versions before 2007 cannot cope with any more than 3 - this file will cause problems with old Excel versions");
        }
        this.checkRuleType(r);
        this.rules.add(r);
        this.header.setNumberOfConditionalFormats(this.rules.size());
    }

    public int getNumberOfRules() {
        return this.rules.size();
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        String type = "CF";
        if (this.header instanceof CFHeader12Record) {
            type = "CF12";
        }
        buffer.append("[").append(type).append("]\n");
        if (this.header != null) {
            buffer.append(this.header);
        }
        for (CFRuleBase cfRule : this.rules) {
            buffer.append(cfRule);
        }
        buffer.append("[/").append(type).append("]\n");
        return buffer.toString();
    }

    @Override
    public void visitContainedRecords(RecordAggregate.RecordVisitor rv) {
        rv.visitRecord(this.header);
        for (CFRuleBase rule : this.rules) {
            rv.visitRecord(rule);
        }
    }

    public boolean updateFormulasAfterCellShift(FormulaShifter shifter, int currentExternSheetIx) {
        CellRangeAddress[] cellRanges = this.header.getCellRanges();
        boolean changed = false;
        ArrayList<CellRangeAddress> temp = new ArrayList<CellRangeAddress>();
        for (CellRangeAddress craOld : cellRanges) {
            CellRangeAddress craNew = BaseRowColShifter.shiftRange(shifter, craOld, currentExternSheetIx);
            if (craNew == null) {
                changed = true;
                continue;
            }
            temp.add(craNew);
            if (craNew == craOld) continue;
            changed = true;
        }
        if (changed) {
            int nRanges = temp.size();
            if (nRanges == 0) {
                return false;
            }
            CellRangeAddress[] newRanges = new CellRangeAddress[nRanges];
            temp.toArray(newRanges);
            this.header.setCellRanges(newRanges);
        }
        for (CFRuleBase rule : this.rules) {
            CFRule12Record rule12;
            Ptg[] ptgs = rule.getParsedExpression1();
            if (ptgs != null && shifter.adjustFormula(ptgs, currentExternSheetIx)) {
                rule.setParsedExpression1(ptgs);
            }
            if ((ptgs = rule.getParsedExpression2()) != null && shifter.adjustFormula(ptgs, currentExternSheetIx)) {
                rule.setParsedExpression2(ptgs);
            }
            if (!(rule instanceof CFRule12Record) || (ptgs = (rule12 = (CFRule12Record)rule).getParsedExpressionScale()) == null || !shifter.adjustFormula(ptgs, currentExternSheetIx)) continue;
            rule12.setParsedExpressionScale(ptgs);
        }
        return true;
    }
}

