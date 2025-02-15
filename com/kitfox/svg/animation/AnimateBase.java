/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.animation.AnimationElement;
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

public abstract class AnimateBase
extends AnimationElement {
    private double repeatCount = Double.NaN;
    private TimeBase repeatDur;

    @Override
    public void evalParametric(AnimationTimeEval state, double curTime) {
        this.evalParametric(state, curTime, this.repeatCount, this.repeatDur == null ? Double.NaN : this.repeatDur.evalTime());
    }

    @Override
    public void loaderStartElement(SVGLoaderHelper helper, Attributes attrs, SVGElement parent) throws SAXException {
        super.loaderStartElement(helper, attrs, parent);
        String repeatDurTime = attrs.getValue("repeatDur");
        try {
            if (repeatDurTime != null) {
                helper.animTimeParser.ReInit(new StringReader(repeatDurTime));
                this.repeatDur = helper.animTimeParser.Expr();
                this.repeatDur.setParentElement(this);
            }
        } catch (Exception e) {
            throw new SAXException(e);
        }
        String strn = attrs.getValue("repeatCount");
        if (strn == null) {
            this.repeatCount = 1.0;
        } else if ("indefinite".equals(strn)) {
            this.repeatCount = Double.POSITIVE_INFINITY;
        } else {
            try {
                this.repeatCount = Double.parseDouble(strn);
            } catch (Exception e) {
                this.repeatCount = Double.NaN;
            }
        }
    }

    @Override
    protected void rebuild(AnimTimeParser animTimeParser) throws SVGException {
        String strn;
        super.rebuild(animTimeParser);
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("repeatDur")) && (strn = sty.getStringValue()) != null) {
            animTimeParser.ReInit(new StringReader(strn));
            try {
                this.repeatDur = animTimeParser.Expr();
            } catch (ParseException ex) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not parse '" + strn + "'", ex);
            }
        }
        if (this.getPres(sty.setName("repeatCount"))) {
            strn = sty.getStringValue();
            if (strn == null) {
                this.repeatCount = 1.0;
            } else if ("indefinite".equals(strn)) {
                this.repeatCount = Double.POSITIVE_INFINITY;
            } else {
                try {
                    this.repeatCount = Double.parseDouble(strn);
                } catch (Exception e) {
                    this.repeatCount = Double.NaN;
                }
            }
        }
    }

    public double getRepeatCount() {
        return this.repeatCount;
    }

    public void setRepeatCount(double repeatCount) {
        this.repeatCount = repeatCount;
    }

    public TimeBase getRepeatDur() {
        return this.repeatDur;
    }

    public void setRepeatDur(TimeBase repeatDur) {
        this.repeatDur = repeatDur;
    }
}

