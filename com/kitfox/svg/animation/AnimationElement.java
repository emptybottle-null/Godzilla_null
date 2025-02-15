/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.animation.AnimationTimeEval;
import com.kitfox.svg.animation.TimeBase;
import com.kitfox.svg.animation.parser.AnimTimeParser;
import com.kitfox.svg.animation.parser.ParseException;
import com.kitfox.svg.xml.StyleAttribute;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public abstract class AnimationElement
extends SVGElement {
    protected String attribName;
    protected int attribType = 2;
    public static final int AT_CSS = 0;
    public static final int AT_XML = 1;
    public static final int AT_AUTO = 2;
    private TimeBase beginTime;
    private TimeBase durTime;
    private TimeBase endTime;
    private int fillType = 4;
    public static final int FT_REMOVE = 0;
    public static final int FT_FREEZE = 1;
    public static final int FT_HOLD = 2;
    public static final int FT_TRANSITION = 3;
    public static final int FT_AUTO = 4;
    public static final int FT_DEFAULT = 5;
    public static final int AD_REPLACE = 0;
    public static final int AD_SUM = 1;
    private int additiveType = 0;
    public static final int AC_REPLACE = 0;
    public static final int AC_SUM = 1;
    private int accumulateType = 0;

    public static String animationElementToString(int attrValue) {
        switch (attrValue) {
            case 0: {
                return "CSS";
            }
            case 1: {
                return "XML";
            }
            case 2: {
                return "AUTO";
            }
        }
        throw new RuntimeException("Unknown element type");
    }

    @Override
    public void loaderStartElement(SVGLoaderHelper helper, Attributes attrs, SVGElement parent) throws SAXException {
        String accumulateStrn;
        String additiveStrn;
        super.loaderStartElement(helper, attrs, parent);
        this.attribName = attrs.getValue("attributeName");
        String attribType = attrs.getValue("attributeType");
        if (attribType != null) {
            if ((attribType = attribType.toLowerCase()).equals("css")) {
                this.attribType = 0;
            } else if (attribType.equals("xml")) {
                this.attribType = 1;
            }
        }
        String beginTime = attrs.getValue("begin");
        String durTime = attrs.getValue("dur");
        String endTime = attrs.getValue("end");
        try {
            if (beginTime != null) {
                helper.animTimeParser.ReInit(new StringReader(beginTime));
                this.beginTime = helper.animTimeParser.Expr();
                this.beginTime.setParentElement(this);
            }
            if (durTime != null) {
                helper.animTimeParser.ReInit(new StringReader(durTime));
                this.durTime = helper.animTimeParser.Expr();
                this.durTime.setParentElement(this);
            }
            if (endTime != null) {
                helper.animTimeParser.ReInit(new StringReader(endTime));
                this.endTime = helper.animTimeParser.Expr();
                this.endTime.setParentElement(this);
            }
        } catch (Exception e) {
            throw new SAXException(e);
        }
        String fill = attrs.getValue("fill");
        if (fill != null) {
            if (fill.equals("remove")) {
                this.fillType = 0;
            }
            if (fill.equals("freeze")) {
                this.fillType = 1;
            }
            if (fill.equals("hold")) {
                this.fillType = 2;
            }
            if (fill.equals("transiton")) {
                this.fillType = 3;
            }
            if (fill.equals("auto")) {
                this.fillType = 4;
            }
            if (fill.equals("default")) {
                this.fillType = 5;
            }
        }
        if ((additiveStrn = attrs.getValue("additive")) != null) {
            if (additiveStrn.equals("replace")) {
                this.additiveType = 0;
            }
            if (additiveStrn.equals("sum")) {
                this.additiveType = 1;
            }
        }
        if ((accumulateStrn = attrs.getValue("accumulate")) != null) {
            if (accumulateStrn.equals("replace")) {
                this.accumulateType = 0;
            }
            if (accumulateStrn.equals("sum")) {
                this.accumulateType = 1;
            }
        }
    }

    public String getAttribName() {
        return this.attribName;
    }

    public int getAttribType() {
        return this.attribType;
    }

    public int getAdditiveType() {
        return this.additiveType;
    }

    public int getAccumulateType() {
        return this.accumulateType;
    }

    public void evalParametric(AnimationTimeEval state, double curTime) {
        this.evalParametric(state, curTime, Double.NaN, Double.NaN);
    }

    protected void evalParametric(AnimationTimeEval state, double curTime, double repeatCount, double repeatDur) {
        int rep;
        double finishTime;
        double evalTime;
        double ratio;
        double interp;
        double end;
        double dur;
        double begin;
        double d = begin = this.beginTime == null ? 0.0 : this.beginTime.evalTime();
        if (Double.isNaN(begin) || begin > curTime) {
            state.set(Double.NaN, 0);
            return;
        }
        double d2 = dur = this.durTime == null ? Double.NaN : this.durTime.evalTime();
        if (Double.isNaN(dur)) {
            state.set(Double.NaN, 0);
            return;
        }
        double d3 = end = this.endTime == null ? Double.NaN : this.endTime.evalTime();
        double repeat = Double.isNaN(repeatCount) && Double.isNaN(repeatDur) ? Double.NaN : Math.min(Double.isNaN(repeatCount) ? Double.POSITIVE_INFINITY : dur * repeatCount, Double.isNaN(repeatDur) ? Double.POSITIVE_INFINITY : repeatDur);
        if (Double.isNaN(repeat) && Double.isNaN(end)) {
            end = begin + dur;
        }
        if ((interp = (ratio = ((evalTime = Math.min(curTime, finishTime = Double.isNaN(end) ? begin + repeat : (Double.isNaN(repeat) ? end : Math.min(end, repeat)))) - begin) / dur) - (double)(rep = (int)ratio)) < 1.0E-5) {
            interp = 0.0;
        }
        if (curTime == evalTime) {
            state.set(interp, rep);
            return;
        }
        switch (this.fillType) {
            default: {
                state.set(Double.NaN, rep);
                return;
            }
            case 1: 
            case 2: 
            case 3: 
        }
        state.set(interp == 0.0 ? 1.0 : interp, rep);
    }

    double evalStartTime() {
        return this.beginTime == null ? Double.NaN : this.beginTime.evalTime();
    }

    double evalDurTime() {
        return this.durTime == null ? Double.NaN : this.durTime.evalTime();
    }

    double evalEndTime() {
        return this.endTime == null ? Double.NaN : this.endTime.evalTime();
    }

    boolean hasEndTime() {
        return this.endTime != null;
    }

    @Override
    public boolean updateTime(double curTime) {
        return false;
    }

    public void rebuild() throws SVGException {
        AnimTimeParser animTimeParser = new AnimTimeParser(new StringReader(""));
        this.rebuild(animTimeParser);
    }

    protected void rebuild(AnimTimeParser animTimeParser) throws SVGException {
        String newVal;
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("begin"))) {
            newVal = sty.getStringValue();
            animTimeParser.ReInit(new StringReader(newVal));
            try {
                this.beginTime = animTimeParser.Expr();
            } catch (ParseException ex) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse '" + newVal + "'", ex);
            }
        }
        if (this.getPres(sty.setName("dur"))) {
            newVal = sty.getStringValue();
            animTimeParser.ReInit(new StringReader(newVal));
            try {
                this.durTime = animTimeParser.Expr();
            } catch (ParseException ex) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse '" + newVal + "'", ex);
            }
        }
        if (this.getPres(sty.setName("end"))) {
            newVal = sty.getStringValue();
            animTimeParser.ReInit(new StringReader(newVal));
            try {
                this.endTime = animTimeParser.Expr();
            } catch (ParseException ex) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse '" + newVal + "'", ex);
            }
        }
        if (this.getPres(sty.setName("fill"))) {
            newVal = sty.getStringValue();
            if (newVal.equals("remove")) {
                this.fillType = 0;
            }
            if (newVal.equals("freeze")) {
                this.fillType = 1;
            }
            if (newVal.equals("hold")) {
                this.fillType = 2;
            }
            if (newVal.equals("transiton")) {
                this.fillType = 3;
            }
            if (newVal.equals("auto")) {
                this.fillType = 4;
            }
            if (newVal.equals("default")) {
                this.fillType = 5;
            }
        }
        if (this.getPres(sty.setName("additive"))) {
            newVal = sty.getStringValue();
            if (newVal.equals("replace")) {
                this.additiveType = 0;
            }
            if (newVal.equals("sum")) {
                this.additiveType = 1;
            }
        }
        if (this.getPres(sty.setName("accumulate"))) {
            newVal = sty.getStringValue();
            if (newVal.equals("replace")) {
                this.accumulateType = 0;
            }
            if (newVal.equals("sum")) {
                this.accumulateType = 1;
            }
        }
    }

    public TimeBase getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(TimeBase beginTime) {
        this.beginTime = beginTime;
    }

    public TimeBase getDurTime() {
        return this.durTime;
    }

    public void setDurTime(TimeBase durTime) {
        this.durTime = durTime;
    }

    public TimeBase getEndTime() {
        return this.endTime;
    }

    public void setEndTime(TimeBase endTime) {
        this.endTime = endTime;
    }

    public int getFillType() {
        return this.fillType;
    }

    public void setFillType(int fillType) {
        this.fillType = fillType;
    }

    public void setAdditiveType(int additiveType) {
        this.additiveType = additiveType;
    }

    public void setAccumulateType(int accumulateType) {
        this.accumulateType = accumulateType;
    }
}

