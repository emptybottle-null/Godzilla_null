/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFLineJoinProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinBevel;

public class XDDFLineJoinBevelProperties
implements XDDFLineJoinProperties {
    private CTLineJoinBevel join;

    public XDDFLineJoinBevelProperties() {
        this(CTLineJoinBevel.Factory.newInstance());
    }

    protected XDDFLineJoinBevelProperties(CTLineJoinBevel join) {
        this.join = join;
    }

    @Internal
    protected CTLineJoinBevel getXmlObject() {
        return this.join;
    }
}

