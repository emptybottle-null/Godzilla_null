/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.AutonumberScheme;
import org.apache.poi.xddf.usermodel.text.XDDFBulletStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextAutonumberBullet;

public class XDDFBulletStyleAutoNumbered
implements XDDFBulletStyle {
    private CTTextAutonumberBullet style;

    @Internal
    protected XDDFBulletStyleAutoNumbered(CTTextAutonumberBullet style) {
        this.style = style;
    }

    @Internal
    protected CTTextAutonumberBullet getXmlObject() {
        return this.style;
    }

    public AutonumberScheme getType() {
        return AutonumberScheme.valueOf(this.style.getType());
    }

    public void setType(AutonumberScheme scheme) {
        this.style.setType(scheme.underlying);
    }

    public int getStartAt() {
        if (this.style.isSetStartAt()) {
            return this.style.getStartAt();
        }
        return 1;
    }

    public void setStartAt(Integer value) {
        if (value == null) {
            if (this.style.isSetStartAt()) {
                this.style.unsetStartAt();
            }
        } else {
            this.style.setStartAt(value);
        }
    }
}

