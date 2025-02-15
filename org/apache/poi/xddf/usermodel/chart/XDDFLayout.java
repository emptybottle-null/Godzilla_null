/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.chart.XDDFChartExtensionList;
import org.apache.poi.xddf.usermodel.chart.XDDFManualLayout;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLayout;

public class XDDFLayout {
    private CTLayout layout;

    public XDDFLayout() {
        this(CTLayout.Factory.newInstance());
    }

    @Internal
    protected XDDFLayout(CTLayout layout) {
        this.layout = layout;
    }

    @Internal
    protected CTLayout getXmlObject() {
        return this.layout;
    }

    public void setExtensionList(XDDFChartExtensionList list) {
        if (list == null) {
            if (this.layout.isSetExtLst()) {
                this.layout.unsetExtLst();
            }
        } else {
            this.layout.setExtLst(list.getXmlObject());
        }
    }

    public XDDFChartExtensionList getExtensionList() {
        if (this.layout.isSetExtLst()) {
            return new XDDFChartExtensionList(this.layout.getExtLst());
        }
        return null;
    }

    public void setManualLayout(XDDFManualLayout manual) {
        if (manual == null) {
            if (this.layout.isSetManualLayout()) {
                this.layout.unsetManualLayout();
            }
        } else {
            this.layout.setManualLayout(manual.getXmlObject());
        }
    }

    public XDDFManualLayout getManualLayout() {
        if (this.layout.isSetManualLayout()) {
            return new XDDFManualLayout(this.layout);
        }
        return null;
    }
}

