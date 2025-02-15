/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.pathcmd;

import com.kitfox.svg.pathcmd.BuildHistory;
import com.kitfox.svg.pathcmd.PathCommand;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;

public class Arc
extends PathCommand {
    public float rx = 0.0f;
    public float ry = 0.0f;
    public float xAxisRot = 0.0f;
    public boolean largeArc = false;
    public boolean sweep = false;
    public float x = 0.0f;
    public float y = 0.0f;

    public Arc() {
    }

    public Arc(boolean isRelative, float rx, float ry, float xAxisRot, boolean largeArc, boolean sweep, float x, float y) {
        super(isRelative);
        this.rx = rx;
        this.ry = ry;
        this.xAxisRot = xAxisRot;
        this.largeArc = largeArc;
        this.sweep = sweep;
        this.x = x;
        this.y = y;
    }

    @Override
    public void appendPath(GeneralPath path, BuildHistory hist) {
        float offx = this.isRelative ? hist.lastPoint.x : 0.0f;
        float offy = this.isRelative ? hist.lastPoint.y : 0.0f;
        this.arcTo(path, this.rx, this.ry, this.xAxisRot, this.largeArc, this.sweep, this.x + offx, this.y + offy, hist.lastPoint.x, hist.lastPoint.y);
        hist.setLastPoint(this.x + offx, this.y + offy);
        hist.setLastKnot(this.x + offx, this.y + offy);
    }

    @Override
    public int getNumKnotsAdded() {
        return 6;
    }

    public void arcTo(GeneralPath path, float rx, float ry, float angle, boolean largeArcFlag, boolean sweepFlag, float x, float y, float x0, float y0) {
        if (rx == 0.0f || ry == 0.0f) {
            path.lineTo(x, y);
            return;
        }
        if (x0 == x && y0 == y) {
            return;
        }
        Arc2D arc = Arc.computeArc(x0, y0, rx, ry, angle, largeArcFlag, sweepFlag, x, y);
        if (arc == null) {
            return;
        }
        AffineTransform t = AffineTransform.getRotateInstance(Math.toRadians(angle), arc.getCenterX(), arc.getCenterY());
        Shape s = t.createTransformedShape(arc);
        path.append(s, true);
    }

    public static Arc2D computeArc(double x0, double y0, double rx, double ry, double angle, boolean largeArcFlag, boolean sweepFlag, double x, double y) {
        double Pry;
        double Py1;
        double dx2 = (x0 - x) / 2.0;
        double dy2 = (y0 - y) / 2.0;
        angle = Math.toRadians(angle % 360.0);
        double cosAngle = Math.cos(angle);
        double sinAngle = Math.sin(angle);
        double x1 = cosAngle * dx2 + sinAngle * dy2;
        double y1 = -sinAngle * dx2 + cosAngle * dy2;
        double Px1 = x1 * x1;
        double Prx = (rx = Math.abs(rx)) * rx;
        double radiiCheck = Px1 / Prx + (Py1 = y1 * y1) / (Pry = (ry = Math.abs(ry)) * ry);
        if (radiiCheck > 1.0) {
            rx = Math.sqrt(radiiCheck) * rx;
            ry = Math.sqrt(radiiCheck) * ry;
            Prx = rx * rx;
            Pry = ry * ry;
        }
        double sign = largeArcFlag == sweepFlag ? -1.0 : 1.0;
        double sq = (Prx * Pry - Prx * Py1 - Pry * Px1) / (Prx * Py1 + Pry * Px1);
        sq = sq < 0.0 ? 0.0 : sq;
        double coef = sign * Math.sqrt(sq);
        double cx1 = coef * (rx * y1 / ry);
        double cy1 = coef * -(ry * x1 / rx);
        double sx2 = (x0 + x) / 2.0;
        double sy2 = (y0 + y) / 2.0;
        double cx = sx2 + (cosAngle * cx1 - sinAngle * cy1);
        double cy = sy2 + (sinAngle * cx1 + cosAngle * cy1);
        double ux = (x1 - cx1) / rx;
        double uy = (y1 - cy1) / ry;
        double vx = (-x1 - cx1) / rx;
        double vy = (-y1 - cy1) / ry;
        double n = Math.sqrt(ux * ux + uy * uy);
        double p = ux;
        sign = uy < 0.0 ? -1.0 : 1.0;
        double angleStart = Math.toDegrees(sign * Math.acos(p / n));
        n = Math.sqrt((ux * ux + uy * uy) * (vx * vx + vy * vy));
        p = ux * vx + uy * vy;
        sign = ux * vy - uy * vx < 0.0 ? -1.0 : 1.0;
        double angleExtent = Math.toDegrees(sign * Math.acos(p / n));
        if (!sweepFlag && angleExtent > 0.0) {
            angleExtent -= 360.0;
        } else if (sweepFlag && angleExtent < 0.0) {
            angleExtent += 360.0;
        }
        angleExtent %= 360.0;
        angleStart %= 360.0;
        Arc2D.Double arc = new Arc2D.Double();
        arc.x = cx - rx;
        arc.y = cy - ry;
        arc.width = rx * 2.0;
        arc.height = ry * 2.0;
        arc.start = -angleStart;
        arc.extent = -angleExtent;
        return arc;
    }

    public String toString() {
        return "A " + this.rx + " " + this.ry + " " + this.xAxisRot + " " + this.largeArc + " " + this.sweep + " " + this.x + " " + this.y;
    }
}

