/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.chart;

import java.util.Optional;
import java.util.function.Function;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.chart.XDDFChartExtensionList;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLegendEntry;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public class XDDFLegendEntry
implements TextContainer {
    private CTLegendEntry entry;

    @Internal
    protected XDDFLegendEntry(CTLegendEntry entry) {
        this.entry = entry;
        if (entry.getIdx() == null) {
            entry.addNewIdx().setVal(0L);
        }
    }

    @Internal
    protected CTLegendEntry getXmlObject() {
        return this.entry;
    }

    public XDDFTextBody getTextBody() {
        if (this.entry.isSetTxPr()) {
            return new XDDFTextBody(this, this.entry.getTxPr());
        }
        return null;
    }

    public void setTextBody(XDDFTextBody body) {
        if (body == null) {
            if (this.entry.isSetTxPr()) {
                this.entry.unsetTxPr();
            }
        } else {
            this.entry.setTxPr(body.getXmlObject());
        }
    }

    public boolean getDelete() {
        if (this.entry.isSetDelete()) {
            return this.entry.getDelete().getVal();
        }
        return false;
    }

    public void setDelete(Boolean delete) {
        if (delete == null) {
            if (this.entry.isSetDelete()) {
                this.entry.unsetDelete();
            }
        } else if (this.entry.isSetDelete()) {
            this.entry.getDelete().setVal(delete);
        } else {
            this.entry.addNewDelete().setVal(delete);
        }
    }

    public long getIndex() {
        return this.entry.getIdx().getVal();
    }

    public void setIndex(long index) {
        this.entry.getIdx().setVal(index);
    }

    public void setExtensionList(XDDFChartExtensionList list) {
        if (list == null) {
            if (this.entry.isSetExtLst()) {
                this.entry.unsetExtLst();
            }
        } else {
            this.entry.setExtLst(list.getXmlObject());
        }
    }

    public XDDFChartExtensionList getExtensionList() {
        if (this.entry.isSetExtLst()) {
            return new XDDFChartExtensionList(this.entry.getExtLst());
        }
        return null;
    }

    @Override
    public <R> Optional<R> findDefinedParagraphProperty(Function<CTTextParagraphProperties, Boolean> isSet, Function<CTTextParagraphProperties, R> getter) {
        return Optional.empty();
    }

    @Override
    public <R> Optional<R> findDefinedRunProperty(Function<CTTextCharacterProperties, Boolean> isSet, Function<CTTextCharacterProperties, R> getter) {
        return Optional.empty();
    }
}

