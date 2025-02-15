/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.text.AnchorType;
import org.apache.poi.xddf.usermodel.text.TextAlignment;
import org.apache.poi.xddf.usermodel.text.TextContainer;
import org.apache.poi.xddf.usermodel.text.XDDFBodyProperties;
import org.apache.poi.xddf.usermodel.text.XDDFParagraphProperties;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.apache.poi.xddf.usermodel.text.XDDFTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextBody;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextListStyle;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextParagraphProperties;

public class XDDFTextBody {
    private CTTextBody _body;
    private TextContainer _parent;

    public XDDFTextBody(TextContainer parent) {
        this(parent, CTTextBody.Factory.newInstance());
    }

    @Internal
    public XDDFTextBody(TextContainer parent, CTTextBody body) {
        this._parent = parent;
        this._body = body;
    }

    @Internal
    public CTTextBody getXmlObject() {
        return this._body;
    }

    public TextContainer getParentShape() {
        return this._parent;
    }

    public XDDFTextParagraph initialize() {
        this._body.addNewLstStyle();
        this._body.addNewBodyPr();
        XDDFBodyProperties bp = this.getBodyProperties();
        bp.setAnchoring(AnchorType.TOP);
        bp.setRightToLeft(false);
        XDDFTextParagraph p = this.addNewParagraph();
        p.setTextAlignment(TextAlignment.LEFT);
        p.appendRegularRun("");
        XDDFRunProperties end = p.addAfterLastRunProperties();
        end.setLanguage(Locale.US);
        end.setFontSize(11.0);
        return p;
    }

    public void setText(String text) {
        if (this._body.sizeOfPArray() > 0) {
            for (int i = this._body.sizeOfPArray() - 1; i > 0; --i) {
                this._body.removeP(i);
            }
            this.getParagraph(0).setText(text);
        } else {
            this.initialize().setText(text);
        }
    }

    public XDDFTextParagraph addNewParagraph() {
        return new XDDFTextParagraph(this._body.addNewP(), this);
    }

    public XDDFTextParagraph insertNewParagraph(int index) {
        return new XDDFTextParagraph(this._body.insertNewP(index), this);
    }

    public void removeParagraph(int index) {
        this._body.removeP(index);
    }

    public XDDFTextParagraph getParagraph(int index) {
        return new XDDFTextParagraph(this._body.getPArray(index), this);
    }

    public List<XDDFTextParagraph> getParagraphs() {
        return Collections.unmodifiableList(this._body.getPList().stream().map(ds -> new XDDFTextParagraph((CTTextParagraph)ds, this)).collect(Collectors.toList()));
    }

    public XDDFBodyProperties getBodyProperties() {
        return new XDDFBodyProperties(this._body.getBodyPr());
    }

    public void setBodyProperties(XDDFBodyProperties properties) {
        if (properties == null) {
            this._body.addNewBodyPr();
        } else {
            this._body.setBodyPr(properties.getXmlObject());
        }
    }

    public XDDFParagraphProperties getDefaultProperties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetDefPPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getDefPPr());
        }
        return null;
    }

    public void setDefaultProperties(XDDFParagraphProperties properties) {
        if (properties == null) {
            CTTextListStyle style;
            if (this._body.isSetLstStyle() && (style = this._body.getLstStyle()).isSetDefPPr()) {
                style.unsetDefPPr();
            }
        } else {
            CTTextListStyle style = this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle();
            style.setDefPPr(properties.getXmlObject());
        }
    }

    public XDDFParagraphProperties getLevel1Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl1PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl1PPr());
        }
        return null;
    }

    public void setLevel1Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            CTTextListStyle style;
            if (this._body.isSetLstStyle() && (style = this._body.getLstStyle()).isSetLvl1PPr()) {
                style.unsetLvl1PPr();
            }
        } else {
            CTTextListStyle style = this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle();
            style.setLvl1PPr(properties.getXmlObject());
        }
    }

    public XDDFParagraphProperties getLevel2Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl2PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl2PPr());
        }
        return null;
    }

    public void setLevel2Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            CTTextListStyle style;
            if (this._body.isSetLstStyle() && (style = this._body.getLstStyle()).isSetLvl2PPr()) {
                style.unsetLvl2PPr();
            }
        } else {
            CTTextListStyle style = this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle();
            style.setLvl2PPr(properties.getXmlObject());
        }
    }

    public XDDFParagraphProperties getLevel3Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl3PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl3PPr());
        }
        return null;
    }

    public void setLevel3Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            CTTextListStyle style;
            if (this._body.isSetLstStyle() && (style = this._body.getLstStyle()).isSetLvl3PPr()) {
                style.unsetLvl3PPr();
            }
        } else {
            CTTextListStyle style = this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle();
            style.setLvl3PPr(properties.getXmlObject());
        }
    }

    public XDDFParagraphProperties getLevel4Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl4PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl4PPr());
        }
        return null;
    }

    public void setLevel4Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            CTTextListStyle style;
            if (this._body.isSetLstStyle() && (style = this._body.getLstStyle()).isSetLvl4PPr()) {
                style.unsetLvl4PPr();
            }
        } else {
            CTTextListStyle style = this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle();
            style.setLvl4PPr(properties.getXmlObject());
        }
    }

    public XDDFParagraphProperties getLevel5Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl5PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl5PPr());
        }
        return null;
    }

    public void setLevel5Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            CTTextListStyle style;
            if (this._body.isSetLstStyle() && (style = this._body.getLstStyle()).isSetLvl5PPr()) {
                style.unsetLvl5PPr();
            }
        } else {
            CTTextListStyle style = this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle();
            style.setLvl5PPr(properties.getXmlObject());
        }
    }

    public XDDFParagraphProperties getLevel6Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl6PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl6PPr());
        }
        return null;
    }

    public void setLevel6Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            CTTextListStyle style;
            if (this._body.isSetLstStyle() && (style = this._body.getLstStyle()).isSetLvl6PPr()) {
                style.unsetLvl6PPr();
            }
        } else {
            CTTextListStyle style = this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle();
            style.setLvl6PPr(properties.getXmlObject());
        }
    }

    public XDDFParagraphProperties getLevel7Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl7PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl7PPr());
        }
        return null;
    }

    public void setLevel7Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            CTTextListStyle style;
            if (this._body.isSetLstStyle() && (style = this._body.getLstStyle()).isSetLvl7PPr()) {
                style.unsetLvl7PPr();
            }
        } else {
            CTTextListStyle style = this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle();
            style.setLvl7PPr(properties.getXmlObject());
        }
    }

    public XDDFParagraphProperties getLevel8Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl8PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl8PPr());
        }
        return null;
    }

    public void setLevel8Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            CTTextListStyle style;
            if (this._body.isSetLstStyle() && (style = this._body.getLstStyle()).isSetLvl8PPr()) {
                style.unsetLvl8PPr();
            }
        } else {
            CTTextListStyle style = this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle();
            style.setLvl8PPr(properties.getXmlObject());
        }
    }

    public XDDFParagraphProperties getLevel9Properties() {
        if (this._body.isSetLstStyle() && this._body.getLstStyle().isSetLvl9PPr()) {
            return new XDDFParagraphProperties(this._body.getLstStyle().getLvl9PPr());
        }
        return null;
    }

    public void setLevel9Properties(XDDFParagraphProperties properties) {
        if (properties == null) {
            CTTextListStyle style;
            if (this._body.isSetLstStyle() && (style = this._body.getLstStyle()).isSetLvl9PPr()) {
                style.unsetLvl9PPr();
            }
        } else {
            CTTextListStyle style = this._body.isSetLstStyle() ? this._body.getLstStyle() : this._body.addNewLstStyle();
            style.setLvl9PPr(properties.getXmlObject());
        }
    }

    @Internal
    protected <R> Optional<R> findDefinedParagraphProperty(Function<CTTextParagraphProperties, Boolean> isSet, Function<CTTextParagraphProperties, R> getter, int level) {
        if (this._body.isSetLstStyle() && level >= 0) {
            CTTextParagraphProperties props;
            CTTextListStyle list = this._body.getLstStyle();
            CTTextParagraphProperties cTTextParagraphProperties = props = level == 0 ? list.getDefPPr() : this.retrieveProperties(list, level);
            if (props != null && isSet.apply(props).booleanValue()) {
                return Optional.of(getter.apply(props));
            }
            return this.findDefinedParagraphProperty(isSet, getter, level - 1);
        }
        if (this._parent != null) {
            return this._parent.findDefinedParagraphProperty(isSet, getter);
        }
        return Optional.empty();
    }

    @Internal
    protected <R> Optional<R> findDefinedRunProperty(Function<CTTextCharacterProperties, Boolean> isSet, Function<CTTextCharacterProperties, R> getter, int level) {
        if (this._body.isSetLstStyle() && level >= 0) {
            CTTextParagraphProperties props;
            CTTextListStyle list = this._body.getLstStyle();
            CTTextParagraphProperties cTTextParagraphProperties = props = level == 0 ? list.getDefPPr() : this.retrieveProperties(list, level);
            if (props != null && props.isSetDefRPr() && isSet.apply(props.getDefRPr()).booleanValue()) {
                return Optional.of(getter.apply(props.getDefRPr()));
            }
            return this.findDefinedRunProperty(isSet, getter, level - 1);
        }
        if (this._parent != null) {
            return this._parent.findDefinedRunProperty(isSet, getter);
        }
        return Optional.empty();
    }

    private CTTextParagraphProperties retrieveProperties(CTTextListStyle list, int level) {
        switch (level) {
            case 1: {
                if (list.isSetLvl1PPr()) {
                    return list.getLvl1PPr();
                }
                return null;
            }
            case 2: {
                if (list.isSetLvl2PPr()) {
                    return list.getLvl2PPr();
                }
                return null;
            }
            case 3: {
                if (list.isSetLvl3PPr()) {
                    return list.getLvl3PPr();
                }
                return null;
            }
            case 4: {
                if (list.isSetLvl4PPr()) {
                    return list.getLvl4PPr();
                }
                return null;
            }
            case 5: {
                if (list.isSetLvl5PPr()) {
                    return list.getLvl5PPr();
                }
                return null;
            }
            case 6: {
                if (list.isSetLvl6PPr()) {
                    return list.getLvl6PPr();
                }
                return null;
            }
            case 7: {
                if (list.isSetLvl7PPr()) {
                    return list.getLvl7PPr();
                }
                return null;
            }
            case 8: {
                if (list.isSetLvl8PPr()) {
                    return list.getLvl8PPr();
                }
                return null;
            }
            case 9: {
                if (list.isSetLvl9PPr()) {
                    return list.getLvl9PPr();
                }
                return null;
            }
        }
        return null;
    }
}

