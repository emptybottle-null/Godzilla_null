/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.xssf.usermodel.extensions.XSSFHeaderFooter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTHeaderFooter;

public class XSSFOddFooter
extends XSSFHeaderFooter
implements Footer {
    protected XSSFOddFooter(CTHeaderFooter headerFooter) {
        super(headerFooter);
    }

    @Override
    public String getText() {
        return this.getHeaderFooter().getOddFooter();
    }

    @Override
    public void setText(String text) {
        if (text == null) {
            this.getHeaderFooter().unsetOddFooter();
        } else {
            this.getHeaderFooter().setOddFooter(text);
        }
    }
}

