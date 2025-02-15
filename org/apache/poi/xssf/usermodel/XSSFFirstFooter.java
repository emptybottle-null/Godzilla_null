/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

public class XSSFFirstFooter
extends XSSFHeaderFooter
implements Footer {
    protected XSSFFirstFooter(CTHeaderFooter headerFooter) {
        super(headerFooter);
        headerFooter.setDifferentFirst(true);
    }

    @Override
    public String getText() {
        return this.getHeaderFooter().getFirstFooter();
    }

    @Override
    public void setText(String text) {
        if (text == null) {
            this.getHeaderFooter().unsetFirstFooter();
            if (!this.getHeaderFooter().isSetFirstHeader()) {
                this.getHeaderFooter().unsetDifferentFirst();
            }
        } else {
            this.getHeaderFooter().setFirstFooter(text);
        }
    }
}

