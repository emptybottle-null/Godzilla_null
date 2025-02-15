/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ddf.EscherDgRecord;
import org.apache.poi.ddf.EscherDggRecord;
import org.apache.poi.util.Removal;

public class DrawingManager2 {
    private final EscherDggRecord dgg;
    private final List<EscherDgRecord> drawingGroups = new ArrayList<EscherDgRecord>();

    public DrawingManager2(EscherDggRecord dgg) {
        this.dgg = dgg;
    }

    public void clearDrawingGroups() {
        this.drawingGroups.clear();
    }

    public EscherDgRecord createDgRecord() {
        EscherDgRecord dg = new EscherDgRecord();
        dg.setRecordId(EscherDgRecord.RECORD_ID);
        short dgId = this.findNewDrawingGroupId();
        dg.setOptions((short)(dgId << 4));
        dg.setNumShapes(0);
        dg.setLastMSOSPID(-1);
        this.drawingGroups.add(dg);
        this.dgg.addCluster(dgId, 0);
        this.dgg.setDrawingsSaved(this.dgg.getDrawingsSaved() + 1);
        return dg;
    }

    @Deprecated
    @Removal(version="4.0")
    public int allocateShapeId(short drawingGroupId) {
        for (EscherDgRecord dg : this.drawingGroups) {
            if (dg.getDrawingGroupId() != drawingGroupId) continue;
            return this.allocateShapeId(dg);
        }
        throw new IllegalStateException("Drawing group id " + drawingGroupId + " doesn't exist.");
    }

    @Deprecated
    @Removal(version="4.0")
    public int allocateShapeId(short drawingGroupId, EscherDgRecord dg) {
        return this.allocateShapeId(dg);
    }

    public int allocateShapeId(EscherDgRecord dg) {
        return this.dgg.allocateShapeId(dg, true);
    }

    public short findNewDrawingGroupId() {
        return this.dgg.findNewDrawingGroupId();
    }

    public EscherDggRecord getDgg() {
        return this.dgg;
    }

    public void incrementDrawingsSaved() {
        this.dgg.setDrawingsSaved(this.dgg.getDrawingsSaved() + 1);
    }
}

