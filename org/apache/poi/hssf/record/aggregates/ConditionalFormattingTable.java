/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.record.aggregates;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.model.RecordStream;
import org.apache.poi.hssf.record.CFHeaderBase;
import org.apache.poi.hssf.record.aggregates.CFRecordsAggregate;
import org.apache.poi.hssf.record.aggregates.RecordAggregate;
import org.apache.poi.ss.formula.FormulaShifter;

public final class ConditionalFormattingTable
extends RecordAggregate {
    private final List<CFRecordsAggregate> _cfHeaders = new ArrayList<CFRecordsAggregate>();

    public ConditionalFormattingTable() {
    }

    public ConditionalFormattingTable(RecordStream rs) {
        while (rs.peekNextRecord() instanceof CFHeaderBase) {
            this._cfHeaders.add(CFRecordsAggregate.createCFAggregate(rs));
        }
    }

    @Override
    public void visitContainedRecords(RecordAggregate.RecordVisitor rv) {
        for (CFRecordsAggregate subAgg : this._cfHeaders) {
            subAgg.visitContainedRecords(rv);
        }
    }

    public int add(CFRecordsAggregate cfAggregate) {
        cfAggregate.getHeader().setID(this._cfHeaders.size());
        this._cfHeaders.add(cfAggregate);
        return this._cfHeaders.size() - 1;
    }

    public int size() {
        return this._cfHeaders.size();
    }

    public CFRecordsAggregate get(int index) {
        this.checkIndex(index);
        return this._cfHeaders.get(index);
    }

    public void remove(int index) {
        this.checkIndex(index);
        this._cfHeaders.remove(index);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this._cfHeaders.size()) {
            throw new IllegalArgumentException("Specified CF index " + index + " is outside the allowable range (0.." + (this._cfHeaders.size() - 1) + ")");
        }
    }

    public void updateFormulasAfterCellShift(FormulaShifter shifter, int externSheetIndex) {
        for (int i = 0; i < this._cfHeaders.size(); ++i) {
            CFRecordsAggregate subAgg = this._cfHeaders.get(i);
            boolean shouldKeep = subAgg.updateFormulasAfterCellShift(shifter, externSheetIndex);
            if (shouldKeep) continue;
            this._cfHeaders.remove(i);
            --i;
        }
    }
}

