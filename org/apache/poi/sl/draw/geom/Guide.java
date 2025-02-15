/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.sl.draw.geom;

import java.util.regex.Pattern;
import org.apache.poi.sl.draw.binding.CTGeomGuide;
import org.apache.poi.sl.draw.geom.Context;
import org.apache.poi.sl.draw.geom.Formula;

public class Guide
implements Formula {
    private static final Pattern WHITESPACE = Pattern.compile("\\s+");
    private final String name;
    private final String fmla;
    private final Op op;
    private final String[] operands;

    public Guide(CTGeomGuide gd) {
        this(gd.getName(), gd.getFmla());
    }

    public Guide(String nm, String fm) {
        this.name = nm;
        this.fmla = fm;
        this.operands = WHITESPACE.split(fm);
        switch (this.operands[0]) {
            case "*/": {
                this.op = Op.muldiv;
                break;
            }
            case "+-": {
                this.op = Op.addsub;
                break;
            }
            case "+/": {
                this.op = Op.adddiv;
                break;
            }
            case "?:": {
                this.op = Op.ifelse;
                break;
            }
            default: {
                this.op = Op.valueOf(this.operands[0]);
            }
        }
    }

    public String getName() {
        return this.name;
    }

    @Override
    public double evaluate(Context ctx) {
        double x = this.operands.length > 1 ? ctx.getValue(this.operands[1]) : 0.0;
        double y = this.operands.length > 2 ? ctx.getValue(this.operands[2]) : 0.0;
        double z = this.operands.length > 3 ? ctx.getValue(this.operands[3]) : 0.0;
        switch (this.op) {
            case abs: {
                return Math.abs(x);
            }
            case adddiv: {
                return z == 0.0 ? 0.0 : (x + y) / z;
            }
            case addsub: {
                return x + y - z;
            }
            case at2: {
                return Math.toDegrees(Math.atan2(y, x)) * 60000.0;
            }
            case cos: {
                return x * Math.cos(Math.toRadians(y / 60000.0));
            }
            case cat2: {
                return x * Math.cos(Math.atan2(z, y));
            }
            case ifelse: {
                return x > 0.0 ? y : z;
            }
            case val: {
                return x;
            }
            case max: {
                return Math.max(x, y);
            }
            case min: {
                return Math.min(x, y);
            }
            case mod: {
                return Math.sqrt(x * x + y * y + z * z);
            }
            case muldiv: {
                return z == 0.0 ? 0.0 : x * y / z;
            }
            case pin: {
                return Math.max(x, Math.min(y, z));
            }
            case sat2: {
                return x * Math.sin(Math.atan2(z, y));
            }
            case sin: {
                return x * Math.sin(Math.toRadians(y / 60000.0));
            }
            case sqrt: {
                return Math.sqrt(x);
            }
            case tan: {
                return x * Math.tan(Math.toRadians(y / 60000.0));
            }
        }
        return 0.0;
    }

    static enum Op {
        muldiv,
        addsub,
        adddiv,
        ifelse,
        val,
        abs,
        sqrt,
        max,
        min,
        at2,
        sin,
        cos,
        tan,
        cat2,
        sat2,
        pin,
        mod;

    }
}

