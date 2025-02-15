/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hssf.usermodel;

import org.apache.poi.ddf.EscherChildAnchorRecord;
import org.apache.poi.ddf.EscherClientAnchorRecord;
import org.apache.poi.ddf.EscherContainerRecord;
import org.apache.poi.ddf.EscherRecord;
import org.apache.poi.hssf.usermodel.HSSFChildAnchor;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.ss.usermodel.ChildAnchor;

public abstract class HSSFAnchor
implements ChildAnchor {
    protected boolean _isHorizontallyFlipped;
    protected boolean _isVerticallyFlipped;

    public HSSFAnchor() {
        this.createEscherAnchor();
    }

    public HSSFAnchor(int dx1, int dy1, int dx2, int dy2) {
        this.createEscherAnchor();
        this.setDx1(dx1);
        this.setDy1(dy1);
        this.setDx2(dx2);
        this.setDy2(dy2);
    }

    public static HSSFAnchor createAnchorFromEscher(EscherContainerRecord container) {
        if (null != container.getChildById(EscherChildAnchorRecord.RECORD_ID)) {
            return new HSSFChildAnchor((EscherChildAnchorRecord)container.getChildById(EscherChildAnchorRecord.RECORD_ID));
        }
        if (null != container.getChildById(EscherClientAnchorRecord.RECORD_ID)) {
            return new HSSFClientAnchor((EscherClientAnchorRecord)container.getChildById(EscherClientAnchorRecord.RECORD_ID));
        }
        return null;
    }

    public abstract boolean isHorizontallyFlipped();

    public abstract boolean isVerticallyFlipped();

    protected abstract EscherRecord getEscherAnchor();

    protected abstract void createEscherAnchor();
}

