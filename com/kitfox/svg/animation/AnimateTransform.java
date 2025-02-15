/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.animation.AnimateXform;
import com.kitfox.svg.animation.parser.AnimTimeParser;
import com.kitfox.svg.xml.StyleAttribute;
import com.kitfox.svg.xml.XMLParseUtil;
import java.awt.geom.AffineTransform;
import java.util.regex.Pattern;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class AnimateTransform
extends AnimateXform {
    public static final String TAG_NAME = "animateTransform";
    private double[][] values;
    private double[] keyTimes;
    public static final int AT_REPLACE = 0;
    public static final int AT_SUM = 1;
    private int additive = 0;
    public static final int TR_TRANSLATE = 0;
    public static final int TR_ROTATE = 1;
    public static final int TR_SCALE = 2;
    public static final int TR_SKEWY = 3;
    public static final int TR_SKEWX = 4;
    public static final int TR_INVALID = 5;
    private int xformType = 5;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    @Override
    public void loaderStartElement(SVGLoaderHelper helper, Attributes attrs, SVGElement parent) throws SAXException {
        String additive;
        super.loaderStartElement(helper, attrs, parent);
        String type = attrs.getValue("type").toLowerCase();
        if (type.equals("translate")) {
            this.xformType = 0;
        }
        if (type.equals("rotate")) {
            this.xformType = 1;
        }
        if (type.equals("scale")) {
            this.xformType = 2;
        }
        if (type.equals("skewx")) {
            this.xformType = 4;
        }
        if (type.equals("skewy")) {
            this.xformType = 3;
        }
        String fromStrn = attrs.getValue("from");
        String toStrn = attrs.getValue("to");
        if (fromStrn != null && toStrn != null) {
            double[] fromValue = XMLParseUtil.parseDoubleList(fromStrn);
            fromValue = this.validate(fromValue);
            double[] toValue = XMLParseUtil.parseDoubleList(toStrn);
            toValue = this.validate(toValue);
            this.values = new double[][]{fromValue, toValue};
            this.keyTimes = new double[]{0.0, 1.0};
        }
        String keyTimeStrn = attrs.getValue("keyTimes");
        String valuesStrn = attrs.getValue("values");
        if (keyTimeStrn != null && valuesStrn != null) {
            this.keyTimes = XMLParseUtil.parseDoubleList(keyTimeStrn);
            String[] valueList = Pattern.compile(";").split(valuesStrn);
            this.values = new double[valueList.length][];
            for (int i = 0; i < valueList.length; ++i) {
                double[] list = XMLParseUtil.parseDoubleList(valueList[i]);
                this.values[i] = this.validate(list);
            }
        }
        if ((additive = attrs.getValue("additive")) != null && additive.equals("sum")) {
            this.additive = 1;
        }
    }

    private double[] validate(double[] paramList) {
        switch (this.xformType) {
            case 2: {
                if (paramList == null) {
                    paramList = new double[]{1.0, 1.0};
                    break;
                }
                if (paramList.length != 1) break;
                paramList = new double[]{paramList[0], paramList[0]};
            }
        }
        return paramList;
    }

    @Override
    public AffineTransform eval(AffineTransform xform, double interp) {
        int idx;
        for (idx = 0; idx < this.keyTimes.length - 1; ++idx) {
            if (!(interp >= this.keyTimes[idx])) continue;
            if (--idx >= 0) break;
            idx = 0;
            break;
        }
        double spanStartTime = this.keyTimes[idx];
        double spanEndTime = this.keyTimes[idx + 1];
        interp = (interp - spanStartTime) / (spanEndTime - spanStartTime);
        double[] fromValue = this.values[idx];
        double[] toValue = this.values[idx + 1];
        switch (this.xformType) {
            case 0: {
                double x0 = fromValue.length >= 1 ? fromValue[0] : 0.0;
                double x1 = toValue.length >= 1 ? toValue[0] : 0.0;
                double y0 = fromValue.length >= 2 ? fromValue[1] : 0.0;
                double y1 = toValue.length >= 2 ? toValue[1] : 0.0;
                double x = this.lerp(x0, x1, interp);
                double y = this.lerp(y0, y1, interp);
                xform.setToTranslation(x, y);
                break;
            }
            case 1: {
                double x1 = fromValue.length == 3 ? fromValue[1] : 0.0;
                double y1 = fromValue.length == 3 ? fromValue[2] : 0.0;
                double x2 = toValue.length == 3 ? toValue[1] : 0.0;
                double y2 = toValue.length == 3 ? toValue[2] : 0.0;
                double theta = this.lerp(fromValue[0], toValue[0], interp);
                double x = this.lerp(x1, x2, interp);
                double y = this.lerp(y1, y2, interp);
                xform.setToRotation(Math.toRadians(theta), x, y);
                break;
            }
            case 2: {
                double x0 = fromValue.length >= 1 ? fromValue[0] : 1.0;
                double x1 = toValue.length >= 1 ? toValue[0] : 1.0;
                double y0 = fromValue.length >= 2 ? fromValue[1] : 1.0;
                double y1 = toValue.length >= 2 ? toValue[1] : 1.0;
                double x = this.lerp(x0, x1, interp);
                double y = this.lerp(y0, y1, interp);
                xform.setToScale(x, y);
                break;
            }
            case 4: {
                double x = this.lerp(fromValue[0], toValue[0], interp);
                xform.setToShear(Math.toRadians(x), 0.0);
                break;
            }
            case 3: {
                double y = this.lerp(fromValue[0], toValue[0], interp);
                xform.setToShear(0.0, Math.toRadians(y));
                break;
            }
            default: {
                xform.setToIdentity();
            }
        }
        return xform;
    }

    @Override
    protected void rebuild(AnimTimeParser animTimeParser) throws SVGException {
        String strn;
        super.rebuild(animTimeParser);
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("type"))) {
            String strn2 = sty.getStringValue().toLowerCase();
            if (strn2.equals("translate")) {
                this.xformType = 0;
            }
            if (strn2.equals("rotate")) {
                this.xformType = 1;
            }
            if (strn2.equals("scale")) {
                this.xformType = 2;
            }
            if (strn2.equals("skewx")) {
                this.xformType = 4;
            }
            if (strn2.equals("skewy")) {
                this.xformType = 3;
            }
        }
        String fromStrn = null;
        if (this.getPres(sty.setName("from"))) {
            fromStrn = sty.getStringValue();
        }
        String toStrn = null;
        if (this.getPres(sty.setName("to"))) {
            toStrn = sty.getStringValue();
        }
        if (fromStrn != null && toStrn != null) {
            double[] fromValue = XMLParseUtil.parseDoubleList(fromStrn);
            fromValue = this.validate(fromValue);
            double[] toValue = XMLParseUtil.parseDoubleList(toStrn);
            toValue = this.validate(toValue);
            this.values = new double[][]{fromValue, toValue};
        }
        String keyTimeStrn = null;
        if (this.getPres(sty.setName("keyTimes"))) {
            keyTimeStrn = sty.getStringValue();
        }
        String valuesStrn = null;
        if (this.getPres(sty.setName("values"))) {
            valuesStrn = sty.getStringValue();
        }
        if (keyTimeStrn != null && valuesStrn != null) {
            this.keyTimes = XMLParseUtil.parseDoubleList(keyTimeStrn);
            String[] valueList = Pattern.compile(";").split(valuesStrn);
            this.values = new double[valueList.length][];
            for (int i = 0; i < valueList.length; ++i) {
                double[] list = XMLParseUtil.parseDoubleList(valueList[i]);
                this.values[i] = this.validate(list);
            }
        }
        if (this.getPres(sty.setName("additive")) && (strn = sty.getStringValue().toLowerCase()).equals("sum")) {
            this.additive = 1;
        }
    }

    public double[][] getValues() {
        return this.values;
    }

    public void setValues(double[][] values) {
        this.values = values;
    }

    public double[] getKeyTimes() {
        return this.keyTimes;
    }

    public void setKeyTimes(double[] keyTimes) {
        this.keyTimes = keyTimes;
    }

    public int getAdditive() {
        return this.additive;
    }

    public void setAdditive(int additive) {
        this.additive = additive;
    }

    public int getXformType() {
        return this.xformType;
    }

    public void setXformType(int xformType) {
        this.xformType = xformType;
    }
}

