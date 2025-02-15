/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.XDDFAutoFit;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNoAutofit;

public class XDDFNoAutoFit
implements XDDFAutoFit {
    private CTTextNoAutofit autofit;

    public XDDFNoAutoFit() {
        this(CTTextNoAutofit.Factory.newInstance());
    }

    @Internal
    protected XDDFNoAutoFit(CTTextNoAutofit autofit) {
        this.autofit = autofit;
    }

    @Internal
    protected CTTextNoAutofit getXmlObject() {
        return this.autofit;
    }

    @Override
    public int getFontScale() {
        return 100000;
    }

    @Override
    public int getLineSpaceReduction() {
        return 0;
    }
}

