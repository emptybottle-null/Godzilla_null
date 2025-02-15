/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel;

import org.apache.poi.util.Internal;
import org.openxmlformats.schemas.drawingml.x2006.main.CTDashStop;

public class XDDFDashStop {
    private CTDashStop stop;

    @Internal
    protected XDDFDashStop(CTDashStop stop) {
        this.stop = stop;
    }

    @Internal
    protected CTDashStop getXmlObject() {
        return this.stop;
    }

    public int getDashLength() {
        return this.stop.getD();
    }

    public void setDashLength(int length) {
        this.stop.setD(length);
    }

    public int getSpaceLength() {
        return this.stop.getSp();
    }

    public void setSpaceLength(int length) {
        this.stop.setSp(length);
    }
}

