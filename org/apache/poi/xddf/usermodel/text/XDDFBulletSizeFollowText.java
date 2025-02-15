/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.XDDFBulletSize;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBulletSizeFollowText;

public class XDDFBulletSizeFollowText
implements XDDFBulletSize {
    private CTTextBulletSizeFollowText follow;

    public XDDFBulletSizeFollowText() {
        this(CTTextBulletSizeFollowText.Factory.newInstance());
    }

    @Internal
    protected XDDFBulletSizeFollowText(CTTextBulletSizeFollowText follow) {
        this.follow = follow;
    }

    @Internal
    protected CTTextBulletSizeFollowText getXmlObject() {
        return this.follow;
    }
}

