/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

public class XSSFEvenHeader
extends XSSFHeaderFooter
implements Header {
    protected XSSFEvenHeader(CTHeaderFooter headerFooter) {
        super(headerFooter);
        headerFooter.setDifferentOddEven(true);
    }

    @Override
    public String getText() {
        return this.getHeaderFooter().getEvenHeader();
    }

    @Override
    public void setText(String text) {
        if (text == null) {
            this.getHeaderFooter().unsetEvenHeader();
            if (!this.getHeaderFooter().isSetEvenFooter()) {
                this.getHeaderFooter().unsetDifferentOddEven();
            }
        } else {
            this.getHeaderFooter().setEvenHeader(text);
        }
    }
}

