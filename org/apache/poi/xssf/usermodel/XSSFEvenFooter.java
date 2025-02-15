/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

public class XSSFEvenFooter
extends XSSFHeaderFooter
implements Footer {
    protected XSSFEvenFooter(CTHeaderFooter headerFooter) {
        super(headerFooter);
        headerFooter.setDifferentOddEven(true);
    }

    @Override
    public String getText() {
        return this.getHeaderFooter().getEvenFooter();
    }

    @Override
    public void setText(String text) {
        if (text == null) {
            this.getHeaderFooter().unsetEvenFooter();
            if (!this.getHeaderFooter().isSetEvenHeader()) {
                this.getHeaderFooter().unsetDifferentOddEven();
            }
        } else {
            this.getHeaderFooter().setEvenFooter(text);
        }
    }
}

