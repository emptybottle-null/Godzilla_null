/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.animation;

import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.SVGLoaderHelper;
import com.kitfox.svg.animation.AnimateBase;
import com.kitfox.svg.animation.AnimateColorIface;
import com.kitfox.svg.animation.parser.AnimTimeParser;
import com.kitfox.svg.xml.ColorTable;
import com.kitfox.svg.xml.StyleAttribute;
import com.kitfox.svg.xml.XMLParseUtil;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class Animate
extends AnimateBase
implements AnimateColorIface {
    public static final String TAG_NAME = "animate";
    public static final int DT_REAL = 0;
    public static final int DT_COLOR = 1;
    public static final int DT_PATH = 2;
    int dataType = 0;
    private double fromValue = Double.NaN;
    private double toValue = Double.NaN;
    private double byValue = Double.NaN;
    private double[] valuesValue;
    private Color fromColor = null;
    private Color toColor = null;
    private GeneralPath fromPath = null;
    private GeneralPath toPath = null;

    @Override
    public String getTagName() {
        return TAG_NAME;
    }

    public int getDataType() {
        return this.dataType;
    }

    @Override
    public void loaderStartElement(SVGLoaderHelper helper, Attributes attrs, SVGElement parent) throws SAXException {
        super.loaderStartElement(helper, attrs, parent);
        String strn = attrs.getValue("from");
        if (strn != null) {
            if (XMLParseUtil.isDouble(strn)) {
                this.fromValue = XMLParseUtil.parseDouble(strn);
            } else {
                this.fromColor = ColorTable.parseColor(strn);
                if (this.fromColor == null) {
                    this.fromPath = Animate.buildPath(strn, 0);
                    this.dataType = 2;
                } else {
                    this.dataType = 1;
                }
            }
        }
        if ((strn = attrs.getValue("to")) != null) {
            if (XMLParseUtil.isDouble(strn)) {
                this.toValue = XMLParseUtil.parseDouble(strn);
            } else {
                this.toColor = ColorTable.parseColor(strn);
                if (this.toColor == null) {
                    this.toPath = Animate.buildPath(strn, 0);
                    this.dataType = 2;
                } else {
                    this.dataType = 1;
                }
            }
        }
        strn = attrs.getValue("by");
        try {
            if (strn != null) {
                this.byValue = XMLParseUtil.parseDouble(strn);
            }
        } catch (Exception exception) {
            // empty catch block
        }
        strn = attrs.getValue("values");
        try {
            if (strn != null) {
                this.valuesValue = XMLParseUtil.parseDoubleList(strn);
            }
        } catch (Exception exception) {
            // empty catch block
        }
    }

    public double eval(double interp) {
        boolean valuesExists;
        boolean fromExists = !Double.isNaN(this.fromValue);
        boolean toExists = !Double.isNaN(this.toValue);
        boolean byExists = !Double.isNaN(this.byValue);
        boolean bl = valuesExists = this.valuesValue != null;
        if (valuesExists) {
            double sp = interp * (double)this.valuesValue.length;
            int ip = (int)sp;
            double fp = sp - (double)ip;
            int i0 = ip;
            int i1 = ip + 1;
            if (i0 < 0) {
                return this.valuesValue[0];
            }
            if (i1 >= this.valuesValue.length) {
                return this.valuesValue[this.valuesValue.length - 1];
            }
            return this.valuesValue[i0] * (1.0 - fp) + this.valuesValue[i1] * fp;
        }
        if (fromExists && toExists) {
            return this.toValue * interp + this.fromValue * (1.0 - interp);
        }
        if (fromExists && byExists) {
            return this.fromValue + this.byValue * interp;
        }
        if (toExists && byExists) {
            return this.toValue - this.byValue * (1.0 - interp);
        }
        if (byExists) {
            return this.byValue * interp;
        }
        if (toExists) {
            StyleAttribute style = new StyleAttribute(this.getAttribName());
            try {
                this.getParent().getStyle(style, true, false);
            } catch (SVGException ex) {
                Logger.getLogger("svgSalamandeLogger").log(Level.WARNING, "Could not get from value", ex);
            }
            double from = style.getDoubleValue();
            return this.toValue * interp + from * (1.0 - interp);
        }
        throw new RuntimeException("Animate tag could not be evalutated - insufficient arguements");
    }

    @Override
    public Color evalColor(double interp) {
        if (this.fromColor == null && this.toColor != null) {
            float[] toCol = new float[3];
            this.toColor.getColorComponents(toCol);
            return new Color(toCol[0] * (float)interp, toCol[1] * (float)interp, toCol[2] * (float)interp);
        }
        if (this.fromColor != null && this.toColor != null) {
            float nInterp = 1.0f - (float)interp;
            float[] fromCol = new float[3];
            float[] toCol = new float[3];
            this.fromColor.getColorComponents(fromCol);
            this.toColor.getColorComponents(toCol);
            return new Color(fromCol[0] * nInterp + toCol[0] * (float)interp, fromCol[1] * nInterp + toCol[1] * (float)interp, fromCol[2] * nInterp + toCol[2] * (float)interp);
        }
        throw new RuntimeException("Animate tag could not be evalutated - insufficient arguements");
    }

    public GeneralPath evalPath(double interp) {
        if (this.fromPath == null && this.toPath != null) {
            PathIterator itTo = this.toPath.getPathIterator(new AffineTransform());
            GeneralPath midPath = new GeneralPath();
            float[] coordsTo = new float[6];
            while (!itTo.isDone()) {
                int segTo = itTo.currentSegment(coordsTo);
                switch (segTo) {
                    case 4: {
                        midPath.closePath();
                        break;
                    }
                    case 3: {
                        midPath.curveTo((float)((double)coordsTo[0] * interp), (float)((double)coordsTo[1] * interp), (float)((double)coordsTo[2] * interp), (float)((double)coordsTo[3] * interp), (float)((double)coordsTo[4] * interp), (float)((double)coordsTo[5] * interp));
                        break;
                    }
                    case 1: {
                        midPath.lineTo((float)((double)coordsTo[0] * interp), (float)((double)coordsTo[1] * interp));
                        break;
                    }
                    case 0: {
                        midPath.moveTo((float)((double)coordsTo[0] * interp), (float)((double)coordsTo[1] * interp));
                        break;
                    }
                    case 2: {
                        midPath.quadTo((float)((double)coordsTo[0] * interp), (float)((double)coordsTo[1] * interp), (float)((double)coordsTo[2] * interp), (float)((double)coordsTo[3] * interp));
                    }
                }
                itTo.next();
            }
            return midPath;
        }
        if (this.toPath != null) {
            PathIterator itFrom = this.fromPath.getPathIterator(new AffineTransform());
            PathIterator itTo = this.toPath.getPathIterator(new AffineTransform());
            GeneralPath midPath = new GeneralPath();
            float[] coordsFrom = new float[6];
            float[] coordsTo = new float[6];
            while (!itFrom.isDone()) {
                int segTo;
                int segFrom = itFrom.currentSegment(coordsFrom);
                if (segFrom != (segTo = itTo.currentSegment(coordsTo))) {
                    throw new RuntimeException("Path shape mismatch");
                }
                switch (segFrom) {
                    case 4: {
                        midPath.closePath();
                        break;
                    }
                    case 3: {
                        midPath.curveTo((float)((double)coordsFrom[0] * (1.0 - interp) + (double)coordsTo[0] * interp), (float)((double)coordsFrom[1] * (1.0 - interp) + (double)coordsTo[1] * interp), (float)((double)coordsFrom[2] * (1.0 - interp) + (double)coordsTo[2] * interp), (float)((double)coordsFrom[3] * (1.0 - interp) + (double)coordsTo[3] * interp), (float)((double)coordsFrom[4] * (1.0 - interp) + (double)coordsTo[4] * interp), (float)((double)coordsFrom[5] * (1.0 - interp) + (double)coordsTo[5] * interp));
                        break;
                    }
                    case 1: {
                        midPath.lineTo((float)((double)coordsFrom[0] * (1.0 - interp) + (double)coordsTo[0] * interp), (float)((double)coordsFrom[1] * (1.0 - interp) + (double)coordsTo[1] * interp));
                        break;
                    }
                    case 0: {
                        midPath.moveTo((float)((double)coordsFrom[0] * (1.0 - interp) + (double)coordsTo[0] * interp), (float)((double)coordsFrom[1] * (1.0 - interp) + (double)coordsTo[1] * interp));
                        break;
                    }
                    case 2: {
                        midPath.quadTo((float)((double)coordsFrom[0] * (1.0 - interp) + (double)coordsTo[0] * interp), (float)((double)coordsFrom[1] * (1.0 - interp) + (double)coordsTo[1] * interp), (float)((double)coordsFrom[2] * (1.0 - interp) + (double)coordsTo[2] * interp), (float)((double)coordsFrom[3] * (1.0 - interp) + (double)coordsTo[3] * interp));
                    }
                }
                itFrom.next();
                itTo.next();
            }
            return midPath;
        }
        throw new RuntimeException("Animate tag could not be evalutated - insufficient arguements");
    }

    public double repeatSkipSize(int reps) {
        boolean byExists;
        boolean fromExists = !Double.isNaN(this.fromValue);
        boolean toExists = !Double.isNaN(this.toValue);
        boolean bl = byExists = !Double.isNaN(this.byValue);
        if (fromExists && toExists) {
            return (this.toValue - this.fromValue) * (double)reps;
        }
        if (fromExists && byExists) {
            return (this.fromValue + this.byValue) * (double)reps;
        }
        if (toExists && byExists) {
            return this.toValue * (double)reps;
        }
        if (byExists) {
            return this.byValue * (double)reps;
        }
        return 0.0;
    }

    @Override
    protected void rebuild(AnimTimeParser animTimeParser) throws SVGException {
        String strn;
        super.rebuild(animTimeParser);
        StyleAttribute sty = new StyleAttribute();
        if (this.getPres(sty.setName("from"))) {
            strn = sty.getStringValue();
            if (XMLParseUtil.isDouble(strn)) {
                this.fromValue = XMLParseUtil.parseDouble(strn);
            } else {
                this.fromColor = ColorTable.parseColor(strn);
                if (this.fromColor == null) {
                    this.fromPath = Animate.buildPath(strn, 0);
                    this.dataType = 2;
                } else {
                    this.dataType = 1;
                }
            }
        }
        if (this.getPres(sty.setName("to"))) {
            strn = sty.getStringValue();
            if (XMLParseUtil.isDouble(strn)) {
                this.toValue = XMLParseUtil.parseDouble(strn);
            } else {
                this.toColor = ColorTable.parseColor(strn);
                if (this.toColor == null) {
                    this.toPath = Animate.buildPath(strn, 0);
                    this.dataType = 2;
                } else {
                    this.dataType = 1;
                }
            }
        }
        if (this.getPres(sty.setName("by")) && (strn = sty.getStringValue()) != null) {
            this.byValue = XMLParseUtil.parseDouble(strn);
        }
        if (this.getPres(sty.setName("values")) && (strn = sty.getStringValue()) != null) {
            this.valuesValue = XMLParseUtil.parseDoubleList(strn);
        }
    }

    public double getFromValue() {
        return this.fromValue;
    }

    public void setFromValue(double fromValue) {
        this.fromValue = fromValue;
    }

    public double getToValue() {
        return this.toValue;
    }

    public void setToValue(double toValue) {
        this.toValue = toValue;
    }

    public double getByValue() {
        return this.byValue;
    }

    public void setByValue(double byValue) {
        this.byValue = byValue;
    }

    public double[] getValuesValue() {
        return this.valuesValue;
    }

    public void setValuesValue(double[] valuesValue) {
        this.valuesValue = valuesValue;
    }

    public Color getFromColor() {
        return this.fromColor;
    }

    public void setFromColor(Color fromColor) {
        this.fromColor = fromColor;
    }

    public Color getToColor() {
        return this.toColor;
    }

    public void setToColor(Color toColor) {
        this.toColor = toColor;
    }

    public GeneralPath getFromPath() {
        return this.fromPath;
    }

    public void setFromPath(GeneralPath fromPath) {
        this.fromPath = fromPath;
    }

    public GeneralPath getToPath() {
        return this.toPath;
    }

    public void setToPath(GeneralPath toPath) {
        this.toPath = toPath;
    }
}

