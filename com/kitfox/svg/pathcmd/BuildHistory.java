/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.pathcmd;

import java.awt.geom.Point2D;

public class BuildHistory {
    Point2D.Float startPoint = new Point2D.Float();
    Point2D.Float lastPoint = new Point2D.Float();
    Point2D.Float lastKnot = new Point2D.Float();
    boolean init;

    public void setStartPoint(float x, float y) {
        this.startPoint.setLocation(x, y);
    }

    public void setLastPoint(float x, float y) {
        this.lastPoint.setLocation(x, y);
    }

    public void setLastKnot(float x, float y) {
        this.lastKnot.setLocation(x, y);
    }
}

