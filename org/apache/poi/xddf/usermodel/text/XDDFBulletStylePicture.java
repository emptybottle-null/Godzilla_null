/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFPicture;
import org.apache.poi.xddf.usermodel.text.XDDFBulletStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBlipBullet;

public class XDDFBulletStylePicture
implements XDDFBulletStyle {
    private CTTextBlipBullet style;

    @Internal
    protected XDDFBulletStylePicture(CTTextBlipBullet style) {
        this.style = style;
    }

    @Internal
    protected CTTextBlipBullet getXmlObject() {
        return this.style;
    }

    public XDDFPicture getPicture() {
        return new XDDFPicture(this.style.getBlip());
    }

    public void setPicture(XDDFPicture picture) {
        if (picture != null) {
            this.style.setBlip(picture.getXmlObject());
        }
    }
}

