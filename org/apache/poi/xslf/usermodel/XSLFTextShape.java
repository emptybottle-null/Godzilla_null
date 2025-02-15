/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.sl.draw.DrawFactory;
import org.apache.poi.sl.draw.DrawTextShape;
import org.apache.poi.sl.usermodel.Insets2D;
import org.apache.poi.sl.usermodel.Placeholder;
import org.apache.poi.sl.usermodel.TextShape;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
import org.apache.poi.xslf.model.TextBodyPropertyFetcher;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFSimpleShape;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBodyProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextAnchoringType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextVerticalType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextWrappingType;

public abstract class XSLFTextShape
extends XSLFSimpleShape
implements TextContainer,
TextShape<XSLFShape, XSLFTextParagraph> {
    private final List<XSLFTextParagraph> _paragraphs = new ArrayList<XSLFTextParagraph>();

    XSLFTextShape(XmlObject shape, XSLFSheet sheet) {
        super(shape, sheet);
        CTTextBody txBody = this.getTextBody(false);
        if (txBody != null) {
            for (CTTextParagraph p : txBody.getPArray()) {
                this._paragraphs.add(this.newTextParagraph(p));
            }
        }
    }

    public XDDFTextBody getTextBody() {
        CTTextBody txBody = this.getTextBody(false);
        if (txBody == null) {
            return null;
        }
        return new XDDFTextBody(this, txBody);
    }

    @Override
    public Iterator<XSLFTextParagraph> iterator() {
        return this.getTextParagraphs().iterator();
    }

    @Override
    public String getText() {
        StringBuilder out = new StringBuilder();
        for (XSLFTextParagraph p : this._paragraphs) {
            if (out.length() > 0) {
                out.append('\n');
            }
            out.append(p.getText());
        }
        return out.toString();
    }

    public void clearText() {
        this._paragraphs.clear();
        CTTextBody txBody = this.getTextBody(true);
        txBody.setPArray(null);
    }

    @Override
    public XSLFTextRun setText(String text) {
        if (!this._paragraphs.isEmpty()) {
            int cntPs;
            CTTextBody txBody = this.getTextBody(false);
            for (int i = cntPs = txBody.sizeOfPArray(); i > 1; --i) {
                txBody.removeP(i - 1);
                this._paragraphs.remove(i - 1);
            }
            this._paragraphs.get(0).clearButKeepProperties();
        }
        return this.appendText(text, false);
    }

    @Override
    public XSLFTextRun appendText(String text, boolean newParagraph) {
        XSLFTextParagraph para;
        boolean firstPara;
        if (text == null) {
            return null;
        }
        CTTextParagraphProperties otherPPr = null;
        CTTextCharacterProperties otherRPr = null;
        if (this._paragraphs.isEmpty()) {
            firstPara = false;
            para = null;
        } else {
            XSLFTextRun r0;
            firstPara = !newParagraph;
            para = this._paragraphs.get(this._paragraphs.size() - 1);
            CTTextParagraph ctp = para.getXmlObject();
            otherPPr = ctp.getPPr();
            List<XSLFTextRun> runs = para.getTextRuns();
            if (!runs.isEmpty() && (otherRPr = (r0 = runs.get(runs.size() - 1)).getRPr(false)) == null) {
                otherRPr = ctp.getEndParaRPr();
            }
        }
        XSLFTextRun run = null;
        for (String lineTxt : text.split("\\r\\n?|\\n")) {
            if (!firstPara) {
                CTTextParagraph ctp;
                CTTextCharacterProperties unexpectedRPr;
                if (para != null && (unexpectedRPr = (ctp = para.getXmlObject()).getEndParaRPr()) != null && unexpectedRPr != otherRPr) {
                    ctp.unsetEndParaRPr();
                }
                para = this.addNewTextParagraph();
                if (otherPPr != null) {
                    para.getXmlObject().setPPr(otherPPr);
                }
            }
            boolean firstRun = true;
            for (String runText : lineTxt.split("[\u000b]")) {
                if (!firstRun) {
                    para.addLineBreak();
                }
                run = para.addNewTextRun();
                run.setText(runText);
                if (otherRPr != null) {
                    run.getRPr(true).set(otherRPr);
                }
                firstRun = false;
            }
            firstPara = false;
        }
        assert (run != null);
        return run;
    }

    @Override
    public List<XSLFTextParagraph> getTextParagraphs() {
        return this._paragraphs;
    }

    public XSLFTextParagraph addNewTextParagraph() {
        CTTextParagraph p;
        CTTextBody txBody = this.getTextBody(false);
        if (txBody == null) {
            txBody = this.getTextBody(true);
            new XDDFTextBody(this, txBody).initialize();
            p = txBody.getPArray(0);
            p.removeR(0);
        } else {
            p = txBody.addNewP();
        }
        XSLFTextParagraph paragraph = this.newTextParagraph(p);
        this._paragraphs.add(paragraph);
        return paragraph;
    }

    @Override
    public void setVerticalAlignment(VerticalAlignment anchor) {
        CTTextBodyProperties bodyPr = this.getTextBodyPr(true);
        if (bodyPr != null) {
            if (anchor == null) {
                if (bodyPr.isSetAnchor()) {
                    bodyPr.unsetAnchor();
                }
            } else {
                bodyPr.setAnchor(STTextAnchoringType.Enum.forInt(anchor.ordinal() + 1));
            }
        }
    }

    @Override
    public VerticalAlignment getVerticalAlignment() {
        TextBodyPropertyFetcher<VerticalAlignment> fetcher = new TextBodyPropertyFetcher<VerticalAlignment>(){

            @Override
            public boolean fetch(CTTextBodyProperties props) {
                if (props.isSetAnchor()) {
                    int val = props.getAnchor().intValue();
                    this.setValue(VerticalAlignment.values()[val - 1]);
                    return true;
                }
                return false;
            }
        };
        this.fetchShapeProperty(fetcher);
        return fetcher.getValue() == null ? VerticalAlignment.TOP : (VerticalAlignment)((Object)fetcher.getValue());
    }

    @Override
    public void setHorizontalCentered(Boolean isCentered) {
        CTTextBodyProperties bodyPr = this.getTextBodyPr(true);
        if (bodyPr != null) {
            if (isCentered == null) {
                if (bodyPr.isSetAnchorCtr()) {
                    bodyPr.unsetAnchorCtr();
                }
            } else {
                bodyPr.setAnchorCtr(isCentered);
            }
        }
    }

    @Override
    public boolean isHorizontalCentered() {
        TextBodyPropertyFetcher<Boolean> fetcher = new TextBodyPropertyFetcher<Boolean>(){

            @Override
            public boolean fetch(CTTextBodyProperties props) {
                if (props.isSetAnchorCtr()) {
                    this.setValue(props.getAnchorCtr());
                    return true;
                }
                return false;
            }
        };
        this.fetchShapeProperty(fetcher);
        return fetcher.getValue() == null ? false : (Boolean)fetcher.getValue();
    }

    @Override
    public void setTextDirection(TextShape.TextDirection orientation) {
        CTTextBodyProperties bodyPr = this.getTextBodyPr(true);
        if (bodyPr != null) {
            if (orientation == null) {
                if (bodyPr.isSetVert()) {
                    bodyPr.unsetVert();
                }
            } else {
                bodyPr.setVert(STTextVerticalType.Enum.forInt(orientation.ordinal() + 1));
            }
        }
    }

    @Override
    public TextShape.TextDirection getTextDirection() {
        STTextVerticalType.Enum val;
        CTTextBodyProperties bodyPr = this.getTextBodyPr();
        if (bodyPr != null && (val = bodyPr.getVert()) != null) {
            switch (val.intValue()) {
                default: {
                    return TextShape.TextDirection.HORIZONTAL;
                }
                case 2: 
                case 5: 
                case 6: {
                    return TextShape.TextDirection.VERTICAL;
                }
                case 3: {
                    return TextShape.TextDirection.VERTICAL_270;
                }
                case 4: 
                case 7: 
            }
            return TextShape.TextDirection.STACKED;
        }
        return TextShape.TextDirection.HORIZONTAL;
    }

    @Override
    public Double getTextRotation() {
        CTTextBodyProperties bodyPr = this.getTextBodyPr();
        if (bodyPr != null && bodyPr.isSetRot()) {
            return (double)bodyPr.getRot() / 60000.0;
        }
        return null;
    }

    @Override
    public void setTextRotation(Double rotation) {
        CTTextBodyProperties bodyPr = this.getTextBodyPr(true);
        if (bodyPr != null) {
            bodyPr.setRot((int)(rotation * 60000.0));
        }
    }

    public double getBottomInset() {
        TextBodyPropertyFetcher<Double> fetcher = new TextBodyPropertyFetcher<Double>(){

            @Override
            public boolean fetch(CTTextBodyProperties props) {
                if (props.isSetBIns()) {
                    double val = Units.toPoints(props.getBIns());
                    this.setValue(val);
                    return true;
                }
                return false;
            }
        };
        this.fetchShapeProperty(fetcher);
        return fetcher.getValue() == null ? 3.6 : (Double)fetcher.getValue();
    }

    public double getLeftInset() {
        TextBodyPropertyFetcher<Double> fetcher = new TextBodyPropertyFetcher<Double>(){

            @Override
            public boolean fetch(CTTextBodyProperties props) {
                if (props.isSetLIns()) {
                    double val = Units.toPoints(props.getLIns());
                    this.setValue(val);
                    return true;
                }
                return false;
            }
        };
        this.fetchShapeProperty(fetcher);
        return fetcher.getValue() == null ? 7.2 : (Double)fetcher.getValue();
    }

    public double getRightInset() {
        TextBodyPropertyFetcher<Double> fetcher = new TextBodyPropertyFetcher<Double>(){

            @Override
            public boolean fetch(CTTextBodyProperties props) {
                if (props.isSetRIns()) {
                    double val = Units.toPoints(props.getRIns());
                    this.setValue(val);
                    return true;
                }
                return false;
            }
        };
        this.fetchShapeProperty(fetcher);
        return fetcher.getValue() == null ? 7.2 : (Double)fetcher.getValue();
    }

    public double getTopInset() {
        TextBodyPropertyFetcher<Double> fetcher = new TextBodyPropertyFetcher<Double>(){

            @Override
            public boolean fetch(CTTextBodyProperties props) {
                if (props.isSetTIns()) {
                    double val = Units.toPoints(props.getTIns());
                    this.setValue(val);
                    return true;
                }
                return false;
            }
        };
        this.fetchShapeProperty(fetcher);
        return fetcher.getValue() == null ? 3.6 : (Double)fetcher.getValue();
    }

    public void setBottomInset(double margin) {
        CTTextBodyProperties bodyPr = this.getTextBodyPr(true);
        if (bodyPr != null) {
            if (margin == -1.0) {
                bodyPr.unsetBIns();
            } else {
                bodyPr.setBIns(Units.toEMU(margin));
            }
        }
    }

    public void setLeftInset(double margin) {
        CTTextBodyProperties bodyPr = this.getTextBodyPr(true);
        if (bodyPr != null) {
            if (margin == -1.0) {
                bodyPr.unsetLIns();
            } else {
                bodyPr.setLIns(Units.toEMU(margin));
            }
        }
    }

    public void setRightInset(double margin) {
        CTTextBodyProperties bodyPr = this.getTextBodyPr(true);
        if (bodyPr != null) {
            if (margin == -1.0) {
                bodyPr.unsetRIns();
            } else {
                bodyPr.setRIns(Units.toEMU(margin));
            }
        }
    }

    public void setTopInset(double margin) {
        CTTextBodyProperties bodyPr = this.getTextBodyPr(true);
        if (bodyPr != null) {
            if (margin == -1.0) {
                bodyPr.unsetTIns();
            } else {
                bodyPr.setTIns(Units.toEMU(margin));
            }
        }
    }

    @Override
    public Insets2D getInsets() {
        return new Insets2D(this.getTopInset(), this.getLeftInset(), this.getBottomInset(), this.getRightInset());
    }

    @Override
    public void setInsets(Insets2D insets) {
        this.setTopInset(insets.top);
        this.setLeftInset(insets.left);
        this.setBottomInset(insets.bottom);
        this.setRightInset(insets.right);
    }

    @Override
    public boolean getWordWrap() {
        TextBodyPropertyFetcher<Boolean> fetcher = new TextBodyPropertyFetcher<Boolean>(){

            @Override
            public boolean fetch(CTTextBodyProperties props) {
                if (props.isSetWrap()) {
                    this.setValue(props.getWrap() == STTextWrappingType.SQUARE);
                    return true;
                }
                return false;
            }
        };
        this.fetchShapeProperty(fetcher);
        return fetcher.getValue() == null ? true : (Boolean)fetcher.getValue();
    }

    @Override
    public void setWordWrap(boolean wrap) {
        CTTextBodyProperties bodyPr = this.getTextBodyPr(true);
        if (bodyPr != null) {
            bodyPr.setWrap(wrap ? STTextWrappingType.SQUARE : STTextWrappingType.NONE);
        }
    }

    public void setTextAutofit(TextShape.TextAutofit value) {
        CTTextBodyProperties bodyPr = this.getTextBodyPr(true);
        if (bodyPr != null) {
            if (bodyPr.isSetSpAutoFit()) {
                bodyPr.unsetSpAutoFit();
            }
            if (bodyPr.isSetNoAutofit()) {
                bodyPr.unsetNoAutofit();
            }
            if (bodyPr.isSetNormAutofit()) {
                bodyPr.unsetNormAutofit();
            }
            switch (value) {
                case NONE: {
                    bodyPr.addNewNoAutofit();
                    break;
                }
                case NORMAL: {
                    bodyPr.addNewNormAutofit();
                    break;
                }
                case SHAPE: {
                    bodyPr.addNewSpAutoFit();
                }
            }
        }
    }

    public TextShape.TextAutofit getTextAutofit() {
        CTTextBodyProperties bodyPr = this.getTextBodyPr();
        if (bodyPr != null) {
            if (bodyPr.isSetNoAutofit()) {
                return TextShape.TextAutofit.NONE;
            }
            if (bodyPr.isSetNormAutofit()) {
                return TextShape.TextAutofit.NORMAL;
            }
            if (bodyPr.isSetSpAutoFit()) {
                return TextShape.TextAutofit.SHAPE;
            }
        }
        return TextShape.TextAutofit.NORMAL;
    }

    protected CTTextBodyProperties getTextBodyPr() {
        return this.getTextBodyPr(false);
    }

    protected CTTextBodyProperties getTextBodyPr(boolean create) {
        CTTextBody textBody = this.getTextBody(create);
        if (textBody == null) {
            return null;
        }
        CTTextBodyProperties textBodyPr = textBody.getBodyPr();
        if (textBodyPr == null && create) {
            textBodyPr = textBody.addNewBodyPr();
        }
        return textBodyPr;
    }

    protected abstract CTTextBody getTextBody(boolean var1);

    @Override
    public void setPlaceholder(Placeholder placeholder) {
        super.setPlaceholder(placeholder);
    }

    public Placeholder getTextType() {
        return this.getPlaceholder();
    }

    @Override
    public double getTextHeight() {
        return this.getTextHeight(null);
    }

    @Override
    public double getTextHeight(Graphics2D graphics) {
        DrawFactory drawFact = DrawFactory.getInstance(graphics);
        DrawTextShape dts = drawFact.getDrawable(this);
        return dts.getTextHeight(graphics);
    }

    @Override
    public Rectangle2D resizeToFitText() {
        return this.resizeToFitText(null);
    }

    @Override
    public Rectangle2D resizeToFitText(Graphics2D graphics) {
        Rectangle2D anchor = this.getAnchor();
        if (anchor.getWidth() == 0.0) {
            throw new POIXMLException("Anchor of the shape was not set.");
        }
        double height = this.getTextHeight(graphics);
        Insets2D insets = this.getInsets();
        anchor.setRect(anchor.getX(), anchor.getY(), anchor.getWidth(), (height += 1.0) + insets.top + insets.bottom);
        this.setAnchor(anchor);
        return anchor;
    }

    @Override
    void copy(XSLFShape other) {
        VerticalAlignment vAlign;
        double bottomInset;
        double topInset;
        double rightInset;
        double leftInset;
        boolean srcWordWrap;
        super.copy(other);
        XSLFTextShape otherTS = (XSLFTextShape)other;
        CTTextBody otherTB = otherTS.getTextBody(false);
        if (otherTB == null) {
            return;
        }
        CTTextBody thisTB = this.getTextBody(true);
        thisTB.setBodyPr((CTTextBodyProperties)otherTB.getBodyPr().copy());
        if (thisTB.isSetLstStyle()) {
            thisTB.unsetLstStyle();
        }
        if (otherTB.isSetLstStyle()) {
            thisTB.setLstStyle((CTTextListStyle)otherTB.getLstStyle().copy());
        }
        if ((srcWordWrap = otherTS.getWordWrap()) != this.getWordWrap()) {
            this.setWordWrap(srcWordWrap);
        }
        if ((leftInset = otherTS.getLeftInset()) != this.getLeftInset()) {
            this.setLeftInset(leftInset);
        }
        if ((rightInset = otherTS.getRightInset()) != this.getRightInset()) {
            this.setRightInset(rightInset);
        }
        if ((topInset = otherTS.getTopInset()) != this.getTopInset()) {
            this.setTopInset(topInset);
        }
        if ((bottomInset = otherTS.getBottomInset()) != this.getBottomInset()) {
            this.setBottomInset(bottomInset);
        }
        if ((vAlign = otherTS.getVerticalAlignment()) != this.getVerticalAlignment()) {
            this.setVerticalAlignment(vAlign);
        }
        this.clearText();
        for (XSLFTextParagraph srcP : otherTS.getTextParagraphs()) {
            XSLFTextParagraph tgtP = this.addNewTextParagraph();
            tgtP.copy(srcP);
        }
    }

    @Override
    public void setTextPlaceholder(TextShape.TextPlaceholder placeholder) {
        switch (placeholder) {
            default: {
                this.setPlaceholder(Placeholder.BODY);
                break;
            }
            case TITLE: {
                this.setPlaceholder(Placeholder.TITLE);
                break;
            }
            case CENTER_BODY: {
                this.setPlaceholder(Placeholder.BODY);
                this.setHorizontalCentered(true);
                break;
            }
            case CENTER_TITLE: {
                this.setPlaceholder(Placeholder.CENTERED_TITLE);
                break;
            }
            case OTHER: {
                this.setPlaceholder(Placeholder.CONTENT);
            }
        }
    }

    @Override
    public TextShape.TextPlaceholder getTextPlaceholder() {
        Placeholder ph = this.getTextType();
        if (ph == null) {
            return TextShape.TextPlaceholder.BODY;
        }
        switch (ph) {
            case BODY: {
                return TextShape.TextPlaceholder.BODY;
            }
            case TITLE: {
                return TextShape.TextPlaceholder.TITLE;
            }
            case CENTERED_TITLE: {
                return TextShape.TextPlaceholder.CENTER_TITLE;
            }
        }
        return TextShape.TextPlaceholder.OTHER;
    }

    protected XSLFTextParagraph newTextParagraph(CTTextParagraph p) {
        return new XSLFTextParagraph(p, this);
    }

    @Override
    public <R> Optional<R> findDefinedParagraphProperty(Function<CTTextParagraphProperties, Boolean> isSet, Function<CTTextParagraphProperties, R> getter) {
        return Optional.empty();
    }

    @Override
    public <R> Optional<R> findDefinedRunProperty(Function<CTTextCharacterProperties, Boolean> isSet, Function<CTTextCharacterProperties, R> getter) {
        return Optional.empty();
    }
}

