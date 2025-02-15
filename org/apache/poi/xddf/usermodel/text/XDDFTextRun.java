/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xddf.usermodel.text;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;
import org.apache.poi.common.usermodel.fonts.FontGroup;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.util.Internal;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFFillProperties;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.text.CapsType;
import org.apache.poi.xddf.usermodel.text.StrikeType;
import org.apache.poi.xddf.usermodel.text.UnderlineType;
import org.apache.poi.xddf.usermodel.text.XDDFFont;
import org.apache.poi.xddf.usermodel.text.XDDFHyperlink;
import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
import org.apache.poi.xddf.usermodel.text.XDDFTextParagraph;
import org.openxmlformats.schemas.drawingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.drawingml.x2006.main.CTLineProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTRegularTextRun;
import org.openxmlformats.schemas.drawingml.x2006.main.CTSolidColorFillProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextCharacterProperties;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextField;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextLineBreak;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextCapsType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextStrikeType;
import org.openxmlformats.schemas.drawingml.x2006.main.STTextUnderlineType;

public class XDDFTextRun {
    private XDDFTextParagraph _parent;
    private XDDFRunProperties _properties;
    private CTTextLineBreak _tlb;
    private CTTextField _tf;
    private CTRegularTextRun _rtr;

    @Internal
    protected XDDFTextRun(CTTextLineBreak run, XDDFTextParagraph parent) {
        this._tlb = run;
        this._parent = parent;
    }

    @Internal
    protected XDDFTextRun(CTTextField run, XDDFTextParagraph parent) {
        this._tf = run;
        this._parent = parent;
    }

    @Internal
    protected XDDFTextRun(CTRegularTextRun run, XDDFTextParagraph parent) {
        this._rtr = run;
        this._parent = parent;
    }

    public XDDFTextParagraph getParentParagraph() {
        return this._parent;
    }

    public boolean isLineBreak() {
        return this._tlb != null;
    }

    public boolean isField() {
        return this._tf != null;
    }

    public boolean isRegularRun() {
        return this._rtr != null;
    }

    public String getText() {
        if (this.isLineBreak()) {
            return "\n";
        }
        if (this.isField()) {
            return this._tf.getT();
        }
        return this._rtr.getT();
    }

    public void setText(String text) {
        if (this.isField()) {
            this._tf.setT(text);
        } else if (this.isRegularRun()) {
            this._rtr.setT(text);
        }
    }

    public void setDirty(Boolean dirty) {
        this.getOrCreateProperties().setDirty(dirty);
    }

    public Boolean getDirty() {
        return this.findDefinedProperty(props -> props.isSetDirty(), props -> props.getDirty()).orElse(null);
    }

    public void setSpellError(Boolean error) {
        this.getOrCreateProperties().setSpellError(error);
    }

    public Boolean getSpellError() {
        return this.findDefinedProperty(props -> props.isSetErr(), props -> props.getErr()).orElse(null);
    }

    public void setNoProof(Boolean noproof) {
        this.getOrCreateProperties().setNoProof(noproof);
    }

    public Boolean getNoProof() {
        return this.findDefinedProperty(props -> props.isSetNoProof(), props -> props.getNoProof()).orElse(null);
    }

    public void setNormalizeHeights(Boolean normalize) {
        this.getOrCreateProperties().setNormalizeHeights(normalize);
    }

    public Boolean getNormalizeHeights() {
        return this.findDefinedProperty(props -> props.isSetNormalizeH(), props -> props.getNormalizeH()).orElse(null);
    }

    public void setKumimoji(Boolean kumimoji) {
        this.getOrCreateProperties().setKumimoji(kumimoji);
    }

    public boolean isKumimoji() {
        return this.findDefinedProperty(props -> props.isSetKumimoji(), props -> props.getKumimoji()).orElse(false);
    }

    public void setBold(Boolean bold) {
        this.getOrCreateProperties().setBold(bold);
    }

    public boolean isBold() {
        return this.findDefinedProperty(props -> props.isSetB(), props -> props.getB()).orElse(false);
    }

    public void setItalic(Boolean italic) {
        this.getOrCreateProperties().setItalic(italic);
    }

    public boolean isItalic() {
        return this.findDefinedProperty(props -> props.isSetI(), props -> props.getI()).orElse(false);
    }

    public void setStrikeThrough(StrikeType strike) {
        this.getOrCreateProperties().setStrikeThrough(strike);
    }

    public boolean isStrikeThrough() {
        return this.findDefinedProperty(props -> props.isSetStrike(), props -> props.getStrike()).map(strike -> strike != STTextStrikeType.NO_STRIKE).orElse(false);
    }

    public StrikeType getStrikeThrough() {
        return this.findDefinedProperty(props -> props.isSetStrike(), props -> props.getStrike()).map(strike -> StrikeType.valueOf(strike)).orElse(null);
    }

    public void setUnderline(UnderlineType underline) {
        this.getOrCreateProperties().setUnderline(underline);
    }

    public boolean isUnderline() {
        return this.findDefinedProperty(props -> props.isSetU(), props -> props.getU()).map(underline -> underline != STTextUnderlineType.NONE).orElse(false);
    }

    public UnderlineType getUnderline() {
        return this.findDefinedProperty(props -> props.isSetU(), props -> props.getU()).map(underline -> UnderlineType.valueOf(underline)).orElse(null);
    }

    public void setCapitals(CapsType caps) {
        this.getOrCreateProperties().setCapitals(caps);
    }

    public boolean isCapitals() {
        return this.findDefinedProperty(props -> props.isSetCap(), props -> props.getCap()).map(caps -> caps != STTextCapsType.NONE).orElse(false);
    }

    public CapsType getCapitals() {
        return this.findDefinedProperty(props -> props.isSetCap(), props -> props.getCap()).map(caps -> CapsType.valueOf(caps)).orElse(null);
    }

    public boolean isSubscript() {
        return this.findDefinedProperty(props -> props.isSetBaseline(), props -> props.getBaseline()).map(baseline -> baseline < 0).orElse(false);
    }

    public boolean isSuperscript() {
        return this.findDefinedProperty(props -> props.isSetBaseline(), props -> props.getBaseline()).map(baseline -> baseline > 0).orElse(false);
    }

    public void setBaseline(Double offset) {
        if (offset == null) {
            this.getOrCreateProperties().setBaseline(null);
        } else {
            this.getOrCreateProperties().setBaseline((int)(offset * 1000.0));
        }
    }

    public void setSuperscript(Double offset) {
        this.setBaseline(offset == null ? null : Double.valueOf(Math.abs(offset)));
    }

    public void setSubscript(Double offset) {
        this.setBaseline(offset == null ? null : Double.valueOf(-Math.abs(offset)));
    }

    public void setFillProperties(XDDFFillProperties properties) {
        this.getOrCreateProperties().setFillProperties(properties);
    }

    public void setFontColor(XDDFColor color) {
        XDDFSolidFillProperties props = new XDDFSolidFillProperties();
        props.setColor(color);
        this.setFillProperties(props);
    }

    public XDDFColor getFontColor() {
        XDDFSolidFillProperties solid = this.findDefinedProperty(props -> props.isSetSolidFill(), props -> props.getSolidFill()).map(props -> new XDDFSolidFillProperties((CTSolidColorFillProperties)props)).orElse(new XDDFSolidFillProperties());
        return solid.getColor();
    }

    public void setFonts(XDDFFont[] fonts) {
        this.getOrCreateProperties().setFonts(fonts);
    }

    public XDDFFont[] getFonts() {
        LinkedList list = new LinkedList();
        this.findDefinedProperty(props -> props.isSetCs(), props -> props.getCs()).map(font -> new XDDFFont(FontGroup.COMPLEX_SCRIPT, (CTTextFont)font)).ifPresent(font -> list.add(font));
        this.findDefinedProperty(props -> props.isSetEa(), props -> props.getEa()).map(font -> new XDDFFont(FontGroup.EAST_ASIAN, (CTTextFont)font)).ifPresent(font -> list.add(font));
        this.findDefinedProperty(props -> props.isSetLatin(), props -> props.getLatin()).map(font -> new XDDFFont(FontGroup.LATIN, (CTTextFont)font)).ifPresent(font -> list.add(font));
        this.findDefinedProperty(props -> props.isSetSym(), props -> props.getSym()).map(font -> new XDDFFont(FontGroup.SYMBOL, (CTTextFont)font)).ifPresent(font -> list.add(font));
        return list.toArray(new XDDFFont[0]);
    }

    public void setFontSize(Double size) {
        this.getOrCreateProperties().setFontSize(size);
    }

    public Double getFontSize() {
        Integer size = this.findDefinedProperty(props -> props.isSetSz(), props -> props.getSz()).orElse(1100);
        double scale = (double)this._parent.getParentBody().getBodyProperties().getAutoFit().getFontScale() / 1.0E7;
        return (double)size.intValue() * scale;
    }

    public void setCharacterKerning(Double kerning) {
        this.getOrCreateProperties().setCharacterKerning(kerning);
    }

    public Double getCharacterKerning() {
        return this.findDefinedProperty(props -> props.isSetKern(), props -> props.getKern()).map(kerning -> 0.01 * (double)kerning.intValue()).orElse(null);
    }

    public void setCharacterSpacing(Double spacing) {
        this.getOrCreateProperties().setCharacterSpacing(spacing);
    }

    public Double getCharacterSpacing() {
        return this.findDefinedProperty(props -> props.isSetSpc(), props -> props.getSpc()).map(spacing -> 0.01 * (double)spacing.intValue()).orElse(null);
    }

    public void setBookmark(String bookmark) {
        this.getOrCreateProperties().setBookmark(bookmark);
    }

    public String getBookmark() {
        return this.findDefinedProperty(props -> props.isSetBmk(), props -> props.getBmk()).orElse(null);
    }

    public XDDFHyperlink linkToExternal(String url, PackagePart localPart, POIXMLRelation relation) {
        PackageRelationship rel = localPart.addExternalRelationship(url, relation.getRelation());
        XDDFHyperlink link = new XDDFHyperlink(rel.getId());
        this.getOrCreateProperties().setHyperlink(link);
        return link;
    }

    public XDDFHyperlink linkToAction(String action) {
        XDDFHyperlink link = new XDDFHyperlink("", action);
        this.getOrCreateProperties().setHyperlink(link);
        return link;
    }

    public XDDFHyperlink linkToInternal(String action, PackagePart localPart, POIXMLRelation relation, PackagePartName target) {
        PackageRelationship rel = localPart.addRelationship(target, TargetMode.INTERNAL, relation.getRelation());
        XDDFHyperlink link = new XDDFHyperlink(rel.getId(), action);
        this.getOrCreateProperties().setHyperlink(link);
        return link;
    }

    public XDDFHyperlink getHyperlink() {
        return this.findDefinedProperty(props -> props.isSetHlinkClick(), props -> props.getHlinkClick()).map(link -> new XDDFHyperlink((CTHyperlink)link)).orElse(null);
    }

    public XDDFHyperlink createMouseOver(String action) {
        XDDFHyperlink link = new XDDFHyperlink("", action);
        this.getOrCreateProperties().setMouseOver(link);
        return link;
    }

    public XDDFHyperlink getMouseOver() {
        return this.findDefinedProperty(props -> props.isSetHlinkMouseOver(), props -> props.getHlinkMouseOver()).map(link -> new XDDFHyperlink((CTHyperlink)link)).orElse(null);
    }

    public void setLanguage(Locale lang) {
        this.getOrCreateProperties().setLanguage(lang);
    }

    public Locale getLanguage() {
        return this.findDefinedProperty(props -> props.isSetLang(), props -> props.getLang()).map(lang -> Locale.forLanguageTag(lang)).orElse(null);
    }

    public void setAlternativeLanguage(Locale lang) {
        this.getOrCreateProperties().setAlternativeLanguage(lang);
    }

    public Locale getAlternativeLanguage() {
        return this.findDefinedProperty(props -> props.isSetAltLang(), props -> props.getAltLang()).map(lang -> Locale.forLanguageTag(lang)).orElse(null);
    }

    public void setHighlight(XDDFColor color) {
        this.getOrCreateProperties().setHighlight(color);
    }

    public XDDFColor getHighlight() {
        return this.findDefinedProperty(props -> props.isSetHighlight(), props -> props.getHighlight()).map(color -> XDDFColor.forColorContainer(color)).orElse(null);
    }

    public void setLineProperties(XDDFLineProperties properties) {
        this.getOrCreateProperties().setLineProperties(properties);
    }

    public XDDFLineProperties getLineProperties() {
        return this.findDefinedProperty(props -> props.isSetLn(), props -> props.getLn()).map(props -> new XDDFLineProperties((CTLineProperties)props)).orElse(null);
    }

    private <R> Optional<R> findDefinedProperty(Function<CTTextCharacterProperties, Boolean> isSet, Function<CTTextCharacterProperties, R> getter) {
        CTTextCharacterProperties props = this.getProperties();
        if (props != null && isSet.apply(props).booleanValue()) {
            return Optional.ofNullable(getter.apply(props));
        }
        return this._parent.findDefinedRunProperty(isSet, getter);
    }

    @Internal
    protected CTTextCharacterProperties getProperties() {
        if (this.isLineBreak() && this._tlb.isSetRPr()) {
            return this._tlb.getRPr();
        }
        if (this.isField() && this._tf.isSetRPr()) {
            return this._tf.getRPr();
        }
        if (this.isRegularRun() && this._rtr.isSetRPr()) {
            return this._rtr.getRPr();
        }
        return null;
    }

    private XDDFRunProperties getOrCreateProperties() {
        if (this._properties == null) {
            if (this.isLineBreak()) {
                this._properties = new XDDFRunProperties(this._tlb.isSetRPr() ? this._tlb.getRPr() : this._tlb.addNewRPr());
            } else if (this.isField()) {
                this._properties = new XDDFRunProperties(this._tf.isSetRPr() ? this._tf.getRPr() : this._tf.addNewRPr());
            } else if (this.isRegularRun()) {
                this._properties = new XDDFRunProperties(this._rtr.isSetRPr() ? this._rtr.getRPr() : this._rtr.addNewRPr());
            }
        }
        return this._properties;
    }
}

