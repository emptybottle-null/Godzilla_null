/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLatentStyles;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLsdException;

public class XWPFLatentStyles {
    protected XWPFStyles styles;
    private CTLatentStyles latentStyles;

    protected XWPFLatentStyles() {
    }

    protected XWPFLatentStyles(CTLatentStyles latentStyles) {
        this(latentStyles, null);
    }

    protected XWPFLatentStyles(CTLatentStyles latentStyles, XWPFStyles styles) {
        this.latentStyles = latentStyles;
        this.styles = styles;
    }

    public int getNumberOfStyles() {
        return this.latentStyles.sizeOfLsdExceptionArray();
    }

    protected boolean isLatentStyle(String latentStyleID) {
        for (CTLsdException lsd : this.latentStyles.getLsdExceptionArray()) {
            if (!lsd.getName().equals(latentStyleID)) continue;
            return true;
        }
        return false;
    }
}

