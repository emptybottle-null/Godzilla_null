/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.geom;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.sl.draw.binding.CTAdjPoint2D;
import org.apache.poi.sl.draw.binding.CTPath2D;
import org.apache.poi.sl.draw.binding.CTPath2DArcTo;
import org.apache.poi.sl.draw.binding.CTPath2DClose;
import org.apache.poi.sl.draw.binding.CTPath2DCubicBezierTo;
import org.apache.poi.sl.draw.binding.CTPath2DLineTo;
import org.apache.poi.sl.draw.binding.CTPath2DMoveTo;
import org.apache.poi.sl.draw.binding.CTPath2DQuadBezierTo;
import org.apache.poi.sl.draw.geom.ArcToCommand;
import org.apache.poi.sl.draw.geom.ClosePathCommand;
import org.apache.poi.sl.draw.geom.Context;
import org.apache.poi.sl.draw.geom.CurveToCommand;
import org.apache.poi.sl.draw.geom.LineToCommand;
import org.apache.poi.sl.draw.geom.MoveToCommand;
import org.apache.poi.sl.draw.geom.PathCommand;
import org.apache.poi.sl.draw.geom.QuadToCommand;
import org.apache.poi.sl.usermodel.PaintStyle;

public class Path {
    private final List<PathCommand> commands;
    PaintStyle.PaintModifier _fill;
    boolean _stroke;
    long _w;
    long _h;

    public Path() {
        this(true, true);
    }

    public Path(boolean fill, boolean stroke) {
        this.commands = new ArrayList<PathCommand>();
        this._w = -1L;
        this._h = -1L;
        this._fill = fill ? PaintStyle.PaintModifier.NORM : PaintStyle.PaintModifier.NONE;
        this._stroke = stroke;
    }

    public Path(CTPath2D spPath) {
        switch (spPath.getFill()) {
            case NONE: {
                this._fill = PaintStyle.PaintModifier.NONE;
                break;
            }
            case DARKEN: {
                this._fill = PaintStyle.PaintModifier.DARKEN;
                break;
            }
            case DARKEN_LESS: {
                this._fill = PaintStyle.PaintModifier.DARKEN_LESS;
                break;
            }
            case LIGHTEN: {
                this._fill = PaintStyle.PaintModifier.LIGHTEN;
                break;
            }
            case LIGHTEN_LESS: {
                this._fill = PaintStyle.PaintModifier.LIGHTEN_LESS;
                break;
            }
            default: {
                this._fill = PaintStyle.PaintModifier.NORM;
            }
        }
        this._stroke = spPath.isStroke();
        this._w = spPath.isSetW() ? spPath.getW() : -1L;
        this._h = spPath.isSetH() ? spPath.getH() : -1L;
        this.commands = new ArrayList<PathCommand>();
        for (Object ch : spPath.getCloseOrMoveToOrLnTo()) {
            CTAdjPoint2D pt2;
            CTAdjPoint2D pt1;
            Object bez;
            CTAdjPoint2D pt;
            if (ch instanceof CTPath2DMoveTo) {
                pt = ((CTPath2DMoveTo)ch).getPt();
                this.commands.add(new MoveToCommand(pt));
                continue;
            }
            if (ch instanceof CTPath2DLineTo) {
                pt = ((CTPath2DLineTo)ch).getPt();
                this.commands.add(new LineToCommand(pt));
                continue;
            }
            if (ch instanceof CTPath2DArcTo) {
                CTPath2DArcTo arc = (CTPath2DArcTo)ch;
                this.commands.add(new ArcToCommand(arc));
                continue;
            }
            if (ch instanceof CTPath2DQuadBezierTo) {
                bez = (CTPath2DQuadBezierTo)ch;
                pt1 = ((CTPath2DQuadBezierTo)bez).getPt().get(0);
                pt2 = ((CTPath2DQuadBezierTo)bez).getPt().get(1);
                this.commands.add(new QuadToCommand(pt1, pt2));
                continue;
            }
            if (ch instanceof CTPath2DCubicBezierTo) {
                bez = (CTPath2DCubicBezierTo)ch;
                pt1 = ((CTPath2DCubicBezierTo)bez).getPt().get(0);
                pt2 = ((CTPath2DCubicBezierTo)bez).getPt().get(1);
                CTAdjPoint2D pt3 = ((CTPath2DCubicBezierTo)bez).getPt().get(2);
                this.commands.add(new CurveToCommand(pt1, pt2, pt3));
                continue;
            }
            if (ch instanceof CTPath2DClose) {
                this.commands.add(new ClosePathCommand());
                continue;
            }
            throw new IllegalStateException("Unsupported path segment: " + ch);
        }
    }

    public void addCommand(PathCommand cmd) {
        this.commands.add(cmd);
    }

    public Path2D.Double getPath(Context ctx) {
        Path2D.Double path = new Path2D.Double();
        for (PathCommand cmd : this.commands) {
            cmd.execute(path, ctx);
        }
        return path;
    }

    public boolean isStroked() {
        return this._stroke;
    }

    public boolean isFilled() {
        return this._fill != PaintStyle.PaintModifier.NONE;
    }

    public PaintStyle.PaintModifier getFill() {
        return this._fill;
    }

    public long getW() {
        return this._w;
    }

    public long getH() {
        return this._h;
    }
}

