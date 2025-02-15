/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.geom;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.poi.sl.draw.geom.BuiltInGuide;
import org.apache.poi.sl.draw.geom.CustomGeometry;
import org.apache.poi.sl.draw.geom.Formula;
import org.apache.poi.sl.draw.geom.Guide;
import org.apache.poi.sl.draw.geom.IAdjustableShape;

public class Context {
    private static final Pattern DOUBLE_PATTERN = Pattern.compile("[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?(\\p{Digit}+))?)|(\\.(\\p{Digit}+)([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");
    private final Map<String, Double> _ctx = new HashMap<String, Double>();
    private final IAdjustableShape _props;
    private final Rectangle2D _anchor;

    public Context(CustomGeometry geom, Rectangle2D anchor, IAdjustableShape props) {
        this._props = props;
        this._anchor = anchor;
        for (Guide gd : geom.adjusts) {
            this.evaluate(gd);
        }
        for (Guide gd : geom.guides) {
            this.evaluate(gd);
        }
    }

    Rectangle2D getShapeAnchor() {
        return this._anchor;
    }

    Guide getAdjustValue(String name) {
        return this._props.getAdjustValue(name);
    }

    public double getValue(String key) {
        if (DOUBLE_PATTERN.matcher(key).matches()) {
            return Double.parseDouble(key);
        }
        return this._ctx.containsKey(key) ? this._ctx.get(key).doubleValue() : this.evaluate(BuiltInGuide.valueOf("_" + key));
    }

    public double evaluate(Formula fmla) {
        String key;
        double result = fmla.evaluate(this);
        if (fmla instanceof Guide && (key = ((Guide)fmla).getName()) != null) {
            this._ctx.put(key, result);
        }
        return result;
    }
}

