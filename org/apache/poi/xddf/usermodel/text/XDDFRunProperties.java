/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import java.util.Locale;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFEffectContainer;
import org.apache.poi.xddf.usermodel.XDDFEffectList;
import org.apache.poi.xddf.usermodel.XDDFExtensionList;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.apache.poi.xddf.usermodel.XDDFGradientFillProperties;
import org.apache.poi.xddf.usermodel.XDDFGroupFillProperties;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFNoFillProperties;
import org.apache.poi.xddf.usermodel.XDDFPatternFillProperties;
import org.apache.poi.xddf.usermodel.XDDFPictureFillProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.text.CapsType;
import org.apache.poi.xddf.usermodel.text.StrikeType;
import org.apache.poi.xddf.usermodel.text.UnderlineType;
import org.apache.poi.xddf.usermodel.text.XDDFFont;
import org.apache.poi.xddf.usermodel.text.XDDFHyperlink;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;

public class XDDFRunProperties {
    private CTTextCharacterProperties props;

    public XDDFRunProperties() {
        this(CTTextCharacterProperties.Factory.newInstance());
    }

    @Internal
    public XDDFRunProperties(CTTextCharacterProperties properties) {
        this.props = properties;
    }

    @Internal
    protected CTTextCharacterProperties getXmlObject() {
        return this.props;
    }

    public void setBaseline(Integer value) {
        if (value == null) {
            if (this.props.isSetBaseline()) {
                this.props.unsetBaseline();
            }
        } else {
            this.props.setBaseline(value);
        }
    }

    public void setDirty(Boolean dirty) {
        if (dirty == null) {
            if (this.props.isSetDirty()) {
                this.props.unsetDirty();
            }
        } else {
            this.props.setDirty(dirty);
        }
    }

    public void setSpellError(Boolean error) {
        if (error == null) {
            if (this.props.isSetErr()) {
                this.props.unsetErr();
            }
        } else {
            this.props.setErr(error);
        }
    }

    public void setNoProof(Boolean noproof) {
        if (noproof == null) {
            if (this.props.isSetNoProof()) {
                this.props.unsetNoProof();
            }
        } else {
            this.props.setNoProof(noproof);
        }
    }

    public void setNormalizeHeights(Boolean normalize) {
        if (normalize == null) {
            if (this.props.isSetNormalizeH()) {
                this.props.unsetNormalizeH();
            }
        } else {
            this.props.setNormalizeH(normalize);
        }
    }

    public void setKumimoji(Boolean kumimoji) {
        if (kumimoji == null) {
            if (this.props.isSetKumimoji()) {
                this.props.unsetKumimoji();
            }
        } else {
            this.props.setKumimoji(kumimoji);
        }
    }

    public void setBold(Boolean bold) {
        if (bold == null) {
            if (this.props.isSetB()) {
                this.props.unsetB();
            }
        } else {
            this.props.setB(bold);
        }
    }

    public void setItalic(Boolean italic) {
        if (italic == null) {
            if (this.props.isSetI()) {
                this.props.unsetI();
            }
        } else {
            this.props.setI(italic);
        }
    }

    public void setFontSize(Double size) {
        if (size == null) {
            if (this.props.isSetSz()) {
                this.props.unsetSz();
            }
        } else {
            if (size < 1.0 || 400.0 < size) {
                throw new IllegalArgumentException("Minimum inclusive = 1. Maximum inclusive = 400.");
            }
            this.props.setSz((int)(100.0 * size));
        }
    }

    public void setFillProperties(XDDFFillProperties properties) {
        if (this.props.isSetBlipFill()) {
            this.props.unsetBlipFill();
        }
        if (this.props.isSetGradFill()) {
            this.props.unsetGradFill();
        }
        if (this.props.isSetGrpFill()) {
            this.props.unsetGrpFill();
        }
        if (this.props.isSetNoFill()) {
            this.props.unsetNoFill();
        }
        if (this.props.isSetPattFill()) {
            this.props.unsetPattFill();
        }
        if (this.props.isSetSolidFill()) {
            this.props.unsetSolidFill();
        }
        if (properties == null) {
            return;
        }
        if (properties instanceof XDDFGradientFillProperties) {
            this.props.setGradFill(((XDDFGradientFillProperties)properties).getXmlObject());
        } else if (properties instanceof XDDFGroupFillProperties) {
            this.props.setGrpFill(((XDDFGroupFillProperties)properties).getXmlObject());
        } else if (properties instanceof XDDFNoFillProperties) {
            this.props.setNoFill(((XDDFNoFillProperties)properties).getXmlObject());
        } else if (properties instanceof XDDFPatternFillProperties) {
            this.props.setPattFill(((XDDFPatternFillProperties)properties).getXmlObject());
        } else if (properties instanceof XDDFPictureFillProperties) {
            this.props.setBlipFill(((XDDFPictureFillProperties)properties).getXmlObject());
        } else if (properties instanceof XDDFSolidFillProperties) {
            this.props.setSolidFill(((XDDFSolidFillProperties)properties).getXmlObject());
        }
    }

    public void setCharacterKerning(Double kerning) {
        if (kerning == null) {
            if (this.props.isSetKern()) {
                this.props.unsetKern();
            }
        } else {
            if (kerning < 0.0 || 4000.0 < kerning) {
                throw new IllegalArgumentException("Minimum inclusive = 0. Maximum inclusive = 4000.");
            }
            this.props.setKern((int)(100.0 * kerning));
        }
    }

    public void setCharacterSpacing(Double spacing) {
        if (spacing == null) {
            if (this.props.isSetSpc()) {
                this.props.unsetSpc();
            }
        } else {
            if (spacing < -4000.0 || 4000.0 < spacing) {
                throw new IllegalArgumentException("Minimum inclusive = -4000. Maximum inclusive = 4000.");
            }
            this.props.setSpc((int)(100.0 * spacing));
        }
    }

    public void setFonts(XDDFFont[] fonts) {
        block6: for (XDDFFont font : fonts) {
            CTTextFont xml = font.getXmlObject();
            switch (font.getGroup()) {
                case COMPLEX_SCRIPT: {
                    if (xml == null) {
                        if (this.props.isSetCs()) {
                            this.props.unsetCs();
                        }
                    } else {
                        this.props.setCs(xml);
                    }
                }
                case EAST_ASIAN: {
                    if (xml == null) {
                        if (this.props.isSetEa()) {
                            this.props.unsetEa();
                        }
                    } else {
                        this.props.setEa(xml);
                    }
                }
                case LATIN: {
                    if (xml == null) {
                        if (this.props.isSetLatin()) {
                            this.props.unsetLatin();
                        }
                    } else {
                        this.props.setLatin(xml);
                    }
                }
                case SYMBOL: {
                    if (xml == null) {
                        if (!this.props.isSetSym()) continue block6;
                        this.props.unsetSym();
                        continue block6;
                    }
                    this.props.setSym(xml);
                }
            }
        }
    }

    public void setUnderline(UnderlineType underline) {
        if (underline == null) {
            if (this.props.isSetU()) {
                this.props.unsetU();
            }
        } else {
            this.props.setU(underline.underlying);
        }
    }

    public void setStrikeThrough(StrikeType strike) {
        if (strike == null) {
            if (this.props.isSetStrike()) {
                this.props.unsetStrike();
            }
        } else {
            this.props.setStrike(strike.underlying);
        }
    }

    public void setCapitals(CapsType caps) {
        if (caps == null) {
            if (this.props.isSetCap()) {
                this.props.unsetCap();
            }
        } else {
            this.props.setCap(caps.underlying);
        }
    }

    public void setHyperlink(XDDFHyperlink link) {
        if (link == null) {
            if (this.props.isSetHlinkClick()) {
                this.props.unsetHlinkClick();
            }
        } else {
            this.props.setHlinkClick(link.getXmlObject());
        }
    }

    public void setMouseOver(XDDFHyperlink link) {
        if (link == null) {
            if (this.props.isSetHlinkMouseOver()) {
                this.props.unsetHlinkMouseOver();
            }
        } else {
            this.props.setHlinkMouseOver(link.getXmlObject());
        }
    }

    public void setLanguage(Locale lang) {
        if (lang == null) {
            if (this.props.isSetLang()) {
                this.props.unsetLang();
            }
        } else {
            this.props.setLang(lang.toLanguageTag());
        }
    }

    public void setAlternativeLanguage(Locale lang) {
        if (lang == null) {
            if (this.props.isSetAltLang()) {
                this.props.unsetAltLang();
            }
        } else {
            this.props.setAltLang(lang.toLanguageTag());
        }
    }

    public void setHighlight(XDDFColor color) {
        if (color == null) {
            if (this.props.isSetHighlight()) {
                this.props.unsetHighlight();
            }
        } else {
            this.props.setHighlight(color.getColorContainer());
        }
    }

    public void setLineProperties(XDDFLineProperties properties) {
        if (properties == null) {
            if (this.props.isSetLn()) {
                this.props.unsetLn();
            }
        } else {
            this.props.setLn(properties.getXmlObject());
        }
    }

    public void setBookmark(String bookmark) {
        if (bookmark == null) {
            if (this.props.isSetBmk()) {
                this.props.unsetBmk();
            }
        } else {
            this.props.setBmk(bookmark);
        }
    }

    public XDDFExtensionList getExtensionList() {
        if (this.props.isSetExtLst()) {
            return new XDDFExtensionList(this.props.getExtLst());
        }
        return null;
    }

    public void setExtensionList(XDDFExtensionList list) {
        if (list == null) {
            if (this.props.isSetExtLst()) {
                this.props.unsetExtLst();
            }
        } else {
            this.props.setExtLst(list.getXmlObject());
        }
    }

    public XDDFEffectContainer getEffectContainer() {
        if (this.props.isSetEffectDag()) {
            return new XDDFEffectContainer(this.props.getEffectDag());
        }
        return null;
    }

    public void setEffectContainer(XDDFEffectContainer container) {
        if (container == null) {
            if (this.props.isSetEffectDag()) {
                this.props.unsetEffectDag();
            }
        } else {
            this.props.setEffectDag(container.getXmlObject());
        }
    }

    public XDDFEffectList getEffectList() {
        if (this.props.isSetEffectLst()) {
            return new XDDFEffectList(this.props.getEffectLst());
        }
        return null;
    }

    public void setEffectList(XDDFEffectList list) {
        if (list == null) {
            if (this.props.isSetEffectLst()) {
                this.props.unsetEffectLst();
            }
        } else {
            this.props.setEffectLst(list.getXmlObject());
        }
    }
}

