/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.PresetGeometry;
import org.apache.poi.xddf.usermodel.XDDFGeometryGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPresetGeometry2D;

public class XDDFPresetGeometry2D {
    private CTPresetGeometry2D geometry;

    protected XDDFPresetGeometry2D(CTPresetGeometry2D geometry) {
        this.geometry = geometry;
    }

    @Internal
    protected CTPresetGeometry2D getXmlObject() {
        return this.geometry;
    }

    public PresetGeometry getGeometry() {
        return PresetGeometry.valueOf(this.geometry.getPrst());
    }

    public void setGeometry(PresetGeometry preset) {
        this.geometry.setPrst(preset.underlying);
    }

    public XDDFGeometryGuide addAdjustValue() {
        if (!this.geometry.isSetAvLst()) {
            this.geometry.addNewAvLst();
        }
        return new XDDFGeometryGuide(this.geometry.getAvLst().addNewGd());
    }

    public XDDFGeometryGuide insertAdjustValue(int index) {
        if (!this.geometry.isSetAvLst()) {
            this.geometry.addNewAvLst();
        }
        return new XDDFGeometryGuide(this.geometry.getAvLst().insertNewGd(index));
    }

    public void removeAdjustValue(int index) {
        if (this.geometry.isSetAvLst()) {
            this.geometry.getAvLst().removeGd(index);
        }
    }

    public XDDFGeometryGuide getAdjustValue(int index) {
        if (this.geometry.isSetAvLst()) {
            return new XDDFGeometryGuide(this.geometry.getAvLst().getGdArray(index));
        }
        return null;
    }

    public List<XDDFGeometryGuide> getAdjustValues() {
        if (this.geometry.isSetAvLst()) {
            return Collections.unmodifiableList(this.geometry.getAvLst().getGdList().stream().map(guide -> new XDDFGeometryGuide((CTGeomGuide)guide)).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }
}

