/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.geom;

import java.awt.geom.Rectangle2D;
import org.apache.poi.sl.draw.geom.Context;
import org.apache.poi.sl.draw.geom.Formula;

enum BuiltInGuide implements Formula
{
    _3cd4,
    _3cd8,
    _5cd8,
    _7cd8,
    _b,
    _cd2,
    _cd4,
    _cd8,
    _hc,
    _h,
    _hd2,
    _hd3,
    _hd4,
    _hd5,
    _hd6,
    _hd8,
    _l,
    _ls,
    _r,
    _ss,
    _ssd2,
    _ssd4,
    _ssd6,
    _ssd8,
    _ssd16,
    _ssd32,
    _t,
    _vc,
    _w,
    _wd2,
    _wd3,
    _wd4,
    _wd5,
    _wd6,
    _wd8,
    _wd10,
    _wd32;


    public String getName() {
        return this.name().substring(1);
    }

    @Override
    public double evaluate(Context ctx) {
        Rectangle2D anchor = ctx.getShapeAnchor();
        double height = anchor.getHeight();
        double width = anchor.getWidth();
        double ss = Math.min(width, height);
        switch (this) {
            case _3cd4: {
                return 1.62E7;
            }
            case _3cd8: {
                return 8100000.0;
            }
            case _5cd8: {
                return 1.35E7;
            }
            case _7cd8: {
                return 1.89E7;
            }
            case _t: {
                return anchor.getY();
            }
            case _b: {
                return anchor.getMaxY();
            }
            case _l: {
                return anchor.getX();
            }
            case _r: {
                return anchor.getMaxX();
            }
            case _cd2: {
                return 1.08E7;
            }
            case _cd4: {
                return 5400000.0;
            }
            case _cd8: {
                return 2700000.0;
            }
            case _hc: {
                return anchor.getCenterX();
            }
            case _h: {
                return height;
            }
            case _hd2: {
                return height / 2.0;
            }
            case _hd3: {
                return height / 3.0;
            }
            case _hd4: {
                return height / 4.0;
            }
            case _hd5: {
                return height / 5.0;
            }
            case _hd6: {
                return height / 6.0;
            }
            case _hd8: {
                return height / 8.0;
            }
            case _ls: {
                return Math.max(width, height);
            }
            case _ss: {
                return ss;
            }
            case _ssd2: {
                return ss / 2.0;
            }
            case _ssd4: {
                return ss / 4.0;
            }
            case _ssd6: {
                return ss / 6.0;
            }
            case _ssd8: {
                return ss / 8.0;
            }
            case _ssd16: {
                return ss / 16.0;
            }
            case _ssd32: {
                return ss / 32.0;
            }
            case _vc: {
                return anchor.getCenterY();
            }
            case _w: {
                return width;
            }
            case _wd2: {
                return width / 2.0;
            }
            case _wd3: {
                return width / 3.0;
            }
            case _wd4: {
                return width / 4.0;
            }
            case _wd5: {
                return width / 5.0;
            }
            case _wd6: {
                return width / 6.0;
            }
            case _wd8: {
                return width / 8.0;
            }
            case _wd10: {
                return width / 10.0;
            }
            case _wd32: {
                return width / 32.0;
            }
        }
        return 0.0;
    }
}

