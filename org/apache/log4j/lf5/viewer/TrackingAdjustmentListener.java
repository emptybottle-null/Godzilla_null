/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.lf5.viewer;

import java.awt.Adjustable;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class TrackingAdjustmentListener
implements AdjustmentListener {
    protected int _lastMaximum = -1;

    public void adjustmentValueChanged(AdjustmentEvent e) {
        Adjustable bar = e.getAdjustable();
        int currentMaximum = bar.getMaximum();
        if (bar.getMaximum() == this._lastMaximum) {
            return;
        }
        int bottom = bar.getValue() + bar.getVisibleAmount();
        if (bottom + bar.getUnitIncrement() >= this._lastMaximum) {
            bar.setValue(bar.getMaximum());
        }
        this._lastMaximum = currentMaximum;
    }
}

