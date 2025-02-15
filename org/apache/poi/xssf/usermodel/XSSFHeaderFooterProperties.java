/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

public class XSSFHeaderFooterProperties {
    private CTHeaderFooter headerFooter;

    public XSSFHeaderFooterProperties(CTHeaderFooter headerFooter) {
        this.headerFooter = headerFooter;
    }

    @Internal
    public CTHeaderFooter getHeaderFooter() {
        return this.headerFooter;
    }

    public boolean getAlignWithMargins() {
        return this.getHeaderFooter().isSetAlignWithMargins() ? this.getHeaderFooter().getAlignWithMargins() : false;
    }

    public boolean getDifferentFirst() {
        return this.getHeaderFooter().isSetDifferentFirst() ? this.getHeaderFooter().getDifferentFirst() : false;
    }

    public boolean getDifferentOddEven() {
        return this.getHeaderFooter().isSetDifferentOddEven() ? this.getHeaderFooter().getDifferentOddEven() : false;
    }

    public boolean getScaleWithDoc() {
        return this.getHeaderFooter().isSetScaleWithDoc() ? this.getHeaderFooter().getScaleWithDoc() : false;
    }

    public void setAlignWithMargins(boolean flag) {
        this.getHeaderFooter().setAlignWithMargins(flag);
    }

    public void setDifferentFirst(boolean flag) {
        this.getHeaderFooter().setDifferentFirst(flag);
    }

    public void setDifferentOddEven(boolean flag) {
        this.getHeaderFooter().setDifferentOddEven(flag);
    }

    public void setScaleWithDoc(boolean flag) {
        this.getHeaderFooter().setScaleWithDoc(flag);
    }

    public void removeAlignWithMargins() {
        if (this.getHeaderFooter().isSetAlignWithMargins()) {
            this.getHeaderFooter().unsetAlignWithMargins();
        }
    }

    public void removeDifferentFirst() {
        if (this.getHeaderFooter().isSetDifferentFirst()) {
            this.getHeaderFooter().unsetDifferentFirst();
        }
    }

    public void removeDifferentOddEven() {
        if (this.getHeaderFooter().isSetDifferentOddEven()) {
            this.getHeaderFooter().unsetDifferentOddEven();
        }
    }

    public void removeScaleWithDoc() {
        if (this.getHeaderFooter().isSetScaleWithDoc()) {
            this.getHeaderFooter().unsetScaleWithDoc();
        }
    }
}

