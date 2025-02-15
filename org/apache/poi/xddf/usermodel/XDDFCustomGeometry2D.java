/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFAdjustHandlePolar;
import org.apache.poi.xddf.usermodel.XDDFAdjustHandleXY;
import org.apache.poi.xddf.usermodel.XDDFConnectionSite;
import org.apache.poi.xddf.usermodel.XDDFGeometryGuide;
import org.apache.poi.xddf.usermodel.XDDFGeometryRectangle;
import org.apache.poi.xddf.usermodel.XDDFPath;
import org.openxmlformats.schemas.drawingml.x2006.main.CTConnectionSite;
import org.openxmlformats.schemas.drawingml.x2006.main.CTCustomGeometry2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGeomGuide;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPath2D;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPolarAdjustHandle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTXYAdjustHandle;

public class XDDFCustomGeometry2D {
    private CTCustomGeometry2D geometry;

    protected XDDFCustomGeometry2D(CTCustomGeometry2D geometry) {
        this.geometry = geometry;
    }

    @Internal
    protected CTCustomGeometry2D getXmlObject() {
        return this.geometry;
    }

    public XDDFGeometryRectangle getRectangle() {
        if (this.geometry.isSetRect()) {
            return new XDDFGeometryRectangle(this.geometry.getRect());
        }
        return null;
    }

    public void setRectangle(XDDFGeometryRectangle rectangle) {
        if (rectangle == null) {
            if (this.geometry.isSetRect()) {
                this.geometry.unsetRect();
            }
        } else {
            this.geometry.setRect(rectangle.getXmlObject());
        }
    }

    public XDDFAdjustHandlePolar addPolarAdjustHandle() {
        if (!this.geometry.isSetAhLst()) {
            this.geometry.addNewAhLst();
        }
        return new XDDFAdjustHandlePolar(this.geometry.getAhLst().addNewAhPolar());
    }

    public XDDFAdjustHandlePolar insertPolarAdjustHandle(int index) {
        if (!this.geometry.isSetAhLst()) {
            this.geometry.addNewAhLst();
        }
        return new XDDFAdjustHandlePolar(this.geometry.getAhLst().insertNewAhPolar(index));
    }

    public void removePolarAdjustHandle(int index) {
        if (this.geometry.isSetAhLst()) {
            this.geometry.getAhLst().removeAhPolar(index);
        }
    }

    public XDDFAdjustHandlePolar getPolarAdjustHandle(int index) {
        if (this.geometry.isSetAhLst()) {
            return new XDDFAdjustHandlePolar(this.geometry.getAhLst().getAhPolarArray(index));
        }
        return null;
    }

    public List<XDDFAdjustHandlePolar> getPolarAdjustHandles() {
        if (this.geometry.isSetAhLst()) {
            return Collections.unmodifiableList(this.geometry.getAhLst().getAhPolarList().stream().map(guide -> new XDDFAdjustHandlePolar((CTPolarAdjustHandle)guide)).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    public XDDFAdjustHandleXY addXYAdjustHandle() {
        if (!this.geometry.isSetAhLst()) {
            this.geometry.addNewAhLst();
        }
        return new XDDFAdjustHandleXY(this.geometry.getAhLst().addNewAhXY());
    }

    public XDDFAdjustHandleXY insertXYAdjustHandle(int index) {
        if (!this.geometry.isSetAhLst()) {
            this.geometry.addNewAhLst();
        }
        return new XDDFAdjustHandleXY(this.geometry.getAhLst().insertNewAhXY(index));
    }

    public void removeXYAdjustHandle(int index) {
        if (this.geometry.isSetAhLst()) {
            this.geometry.getAhLst().removeAhXY(index);
        }
    }

    public XDDFAdjustHandleXY getXYAdjustHandle(int index) {
        if (this.geometry.isSetAhLst()) {
            return new XDDFAdjustHandleXY(this.geometry.getAhLst().getAhXYArray(index));
        }
        return null;
    }

    public List<XDDFAdjustHandleXY> getXYAdjustHandles() {
        if (this.geometry.isSetAhLst()) {
            return Collections.unmodifiableList(this.geometry.getAhLst().getAhXYList().stream().map(guide -> new XDDFAdjustHandleXY((CTXYAdjustHandle)guide)).collect(Collectors.toList()));
        }
        return Collections.emptyList();
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

    public XDDFConnectionSite addConnectionSite() {
        if (!this.geometry.isSetCxnLst()) {
            this.geometry.addNewCxnLst();
        }
        return new XDDFConnectionSite(this.geometry.getCxnLst().addNewCxn());
    }

    public XDDFConnectionSite insertConnectionSite(int index) {
        if (!this.geometry.isSetCxnLst()) {
            this.geometry.addNewCxnLst();
        }
        return new XDDFConnectionSite(this.geometry.getCxnLst().insertNewCxn(index));
    }

    public void removeConnectionSite(int index) {
        if (this.geometry.isSetCxnLst()) {
            this.geometry.getCxnLst().removeCxn(index);
        }
    }

    public XDDFConnectionSite getConnectionSite(int index) {
        if (this.geometry.isSetCxnLst()) {
            return new XDDFConnectionSite(this.geometry.getCxnLst().getCxnArray(index));
        }
        return null;
    }

    public List<XDDFConnectionSite> getConnectionSites() {
        if (this.geometry.isSetCxnLst()) {
            return Collections.unmodifiableList(this.geometry.getCxnLst().getCxnList().stream().map(guide -> new XDDFConnectionSite((CTConnectionSite)guide)).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    public XDDFGeometryGuide addGuide() {
        if (!this.geometry.isSetGdLst()) {
            this.geometry.addNewGdLst();
        }
        return new XDDFGeometryGuide(this.geometry.getGdLst().addNewGd());
    }

    public XDDFGeometryGuide insertGuide(int index) {
        if (!this.geometry.isSetGdLst()) {
            this.geometry.addNewGdLst();
        }
        return new XDDFGeometryGuide(this.geometry.getGdLst().insertNewGd(index));
    }

    public void removeGuide(int index) {
        if (this.geometry.isSetGdLst()) {
            this.geometry.getGdLst().removeGd(index);
        }
    }

    public XDDFGeometryGuide getGuide(int index) {
        if (this.geometry.isSetGdLst()) {
            return new XDDFGeometryGuide(this.geometry.getGdLst().getGdArray(index));
        }
        return null;
    }

    public List<XDDFGeometryGuide> getGuides() {
        if (this.geometry.isSetGdLst()) {
            return Collections.unmodifiableList(this.geometry.getGdLst().getGdList().stream().map(guide -> new XDDFGeometryGuide((CTGeomGuide)guide)).collect(Collectors.toList()));
        }
        return Collections.emptyList();
    }

    public XDDFPath addNewPath() {
        return new XDDFPath(this.geometry.getPathLst().addNewPath());
    }

    public XDDFPath insertNewPath(int index) {
        return new XDDFPath(this.geometry.getPathLst().insertNewPath(index));
    }

    public void removePath(int index) {
        this.geometry.getPathLst().removePath(index);
    }

    public XDDFPath getPath(int index) {
        return new XDDFPath(this.geometry.getPathLst().getPathArray(index));
    }

    public List<XDDFPath> getPaths() {
        return Collections.unmodifiableList(this.geometry.getPathLst().getPathList().stream().map(ds -> new XDDFPath((CTPath2D)ds)).collect(Collectors.toList()));
    }
}

