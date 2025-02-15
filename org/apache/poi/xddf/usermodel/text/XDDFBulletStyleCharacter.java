/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.XDDFBulletStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharBullet;

public class XDDFBulletStyleCharacter
implements XDDFBulletStyle {
    private CTTextCharBullet style;

    @Internal
    protected XDDFBulletStyleCharacter(CTTextCharBullet style) {
        this.style = style;
    }

    @Internal
    protected CTTextCharBullet getXmlObject() {
        return this.style;
    }

    public String getCharacter() {
        return this.style.getChar();
    }

    public void setCharacter(String value) {
        this.style.setChar(value);
    }
}

