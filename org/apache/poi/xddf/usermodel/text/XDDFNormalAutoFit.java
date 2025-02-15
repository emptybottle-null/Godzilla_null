/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.XDDFAutoFit;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextNormalAutofit;

public class XDDFNormalAutoFit
implements XDDFAutoFit {
    private CTTextNormalAutofit autofit;

    public XDDFNormalAutoFit() {
        this(CTTextNormalAutofit.Factory.newInstance());
    }

    @Internal
    protected XDDFNormalAutoFit(CTTextNormalAutofit autofit) {
        this.autofit = autofit;
    }

    @Internal
    protected CTTextNormalAutofit getXmlObject() {
        return this.autofit;
    }

    @Override
    public int getFontScale() {
        if (this.autofit.isSetFontScale()) {
            return this.autofit.getFontScale();
        }
        return 100000;
    }

    public void setFontScale(Integer value) {
        if (value == null) {
            if (this.autofit.isSetFontScale()) {
                this.autofit.unsetFontScale();
            }
        } else {
            this.autofit.setFontScale(value);
        }
    }

    @Override
    public int getLineSpaceReduction() {
        if (this.autofit.isSetLnSpcReduction()) {
            return this.autofit.getLnSpcReduction();
        }
        return 0;
    }

    public void setLineSpaceReduction(Integer value) {
        if (value == null) {
            if (this.autofit.isSetLnSpcReduction()) {
                this.autofit.unsetLnSpcReduction();
            }
        } else {
            this.autofit.setLnSpcReduction(value);
        }
    }
}

