/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFLineJoinProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineJoinMiterProperties;

public class XDDFLineJoinMiterProperties
implements XDDFLineJoinProperties {
    private CTLineJoinMiterProperties join;

    public XDDFLineJoinMiterProperties() {
        this(CTLineJoinMiterProperties.Factory.newInstance());
    }

    protected XDDFLineJoinMiterProperties(CTLineJoinMiterProperties join) {
        this.join = join;
    }

    @Internal
    protected CTLineJoinMiterProperties getXmlObject() {
        return this.join;
    }

    public Integer getLimit() {
        if (this.join.isSetLim()) {
            return this.join.getLim();
        }
        return null;
    }

    public void setLimit(Integer limit) {
        if (limit == null) {
            if (this.join.isSetLim()) {
                this.join.unsetLim();
            }
        } else {
            this.join.setLim(limit);
        }
    }
}

