/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.common.usermodel.fonts.FontCharset;
import org.apache.poi.common.usermodel.fonts.FontFacet;
import org.apache.poi.common.usermodel.fonts.FontFamily;
import org.apache.poi.common.usermodel.fonts.FontHeader;
import org.apache.poi.common.usermodel.fonts.FontInfo;
import org.apache.poi.common.usermodel.fonts.FontPitch;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFFactory;
import org.apache.poi.xslf.usermodel.XSLFFontData;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import org.openxmlformats.schemas.drawingml.x2006.main.CTTextFont;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontDataId;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontList;
import org.openxmlformats.schemas.presentationml.x2006.main.CTEmbeddedFontListEntry;
import org.openxmlformats.schemas.presentationml.x2006.main.CTPresentation;

public class XSLFFontInfo
implements FontInfo {
    final XMLSlideShow ppt;
    final String typeface;
    final CTEmbeddedFontListEntry fontListEntry;

    public XSLFFontInfo(XMLSlideShow ppt, String typeface) {
        this.ppt = ppt;
        this.typeface = typeface;
        CTPresentation pres = ppt.getCTPresentation();
        CTEmbeddedFontList fontList = pres.isSetEmbeddedFontLst() ? pres.getEmbeddedFontLst() : pres.addNewEmbeddedFontLst();
        for (CTEmbeddedFontListEntry fe : fontList.getEmbeddedFontArray()) {
            if (!typeface.equalsIgnoreCase(fe.getFont().getTypeface())) continue;
            this.fontListEntry = fe;
            return;
        }
        this.fontListEntry = fontList.addNewEmbeddedFont();
        this.fontListEntry.addNewFont().setTypeface(typeface);
    }

    public XSLFFontInfo(XMLSlideShow ppt, CTEmbeddedFontListEntry fontListEntry) {
        this.ppt = ppt;
        this.typeface = fontListEntry.getFont().getTypeface();
        this.fontListEntry = fontListEntry;
    }

    @Override
    public String getTypeface() {
        return this.getFont().getTypeface();
    }

    @Override
    public void setTypeface(String typeface) {
        this.getFont().setTypeface(typeface);
    }

    @Override
    public FontCharset getCharset() {
        return FontCharset.valueOf(this.getFont().getCharset());
    }

    @Override
    public void setCharset(FontCharset charset) {
        this.getFont().setCharset((byte)charset.getNativeId());
    }

    @Override
    public FontFamily getFamily() {
        return FontFamily.valueOfPitchFamily(this.getFont().getPitchFamily());
    }

    @Override
    public void setFamily(FontFamily family) {
        byte pitchAndFamily = this.getFont().getPitchFamily();
        FontPitch pitch = FontPitch.valueOfPitchFamily(pitchAndFamily);
        this.getFont().setPitchFamily(FontPitch.getNativeId(pitch, family));
    }

    @Override
    public FontPitch getPitch() {
        return FontPitch.valueOfPitchFamily(this.getFont().getPitchFamily());
    }

    @Override
    public void setPitch(FontPitch pitch) {
        byte pitchAndFamily = this.getFont().getPitchFamily();
        FontFamily family = FontFamily.valueOfPitchFamily(pitchAndFamily);
        this.getFont().setPitchFamily(FontPitch.getNativeId(pitch, family));
    }

    @Override
    public byte[] getPanose() {
        return this.getFont().getPanose();
    }

    public List<FontFacet> getFacets() {
        ArrayList<FontFacet> facetList = new ArrayList<FontFacet>();
        if (this.fontListEntry.isSetRegular()) {
            facetList.add(new XSLFFontFacet(this.fontListEntry.getRegular()));
        }
        if (this.fontListEntry.isSetItalic()) {
            facetList.add(new XSLFFontFacet(this.fontListEntry.getItalic()));
        }
        if (this.fontListEntry.isSetBold()) {
            facetList.add(new XSLFFontFacet(this.fontListEntry.getBold()));
        }
        if (this.fontListEntry.isSetBoldItalic()) {
            facetList.add(new XSLFFontFacet(this.fontListEntry.getBoldItalic()));
        }
        return facetList;
    }

    public FontFacet addFacet(InputStream fontData) throws IOException {
        CTEmbeddedFontDataId dataId;
        FontHeader header = new FontHeader();
        InputStream is = header.bufferInit(fontData);
        CTPresentation pres = this.ppt.getCTPresentation();
        pres.setEmbedTrueTypeFonts(true);
        pres.setSaveSubsetFonts(true);
        int style = (header.getWeight() > 400 ? 1 : 0) | (header.isItalic() ? 2 : 0);
        switch (style) {
            case 0: {
                dataId = this.fontListEntry.isSetRegular() ? this.fontListEntry.getRegular() : this.fontListEntry.addNewRegular();
                break;
            }
            case 1: {
                dataId = this.fontListEntry.isSetBold() ? this.fontListEntry.getBold() : this.fontListEntry.addNewBold();
                break;
            }
            case 2: {
                dataId = this.fontListEntry.isSetItalic() ? this.fontListEntry.getItalic() : this.fontListEntry.addNewItalic();
                break;
            }
            default: {
                dataId = this.fontListEntry.isSetBoldItalic() ? this.fontListEntry.getBoldItalic() : this.fontListEntry.addNewBoldItalic();
            }
        }
        XSLFFontFacet facet = new XSLFFontFacet(dataId);
        facet.setFontData(is);
        return facet;
    }

    private CTTextFont getFont() {
        return this.fontListEntry.getFont();
    }

    public static XSLFFontInfo addFontToSlideShow(XMLSlideShow ppt, InputStream fontStream) throws IOException {
        FontHeader header = new FontHeader();
        InputStream is = header.bufferInit(fontStream);
        XSLFFontInfo fontInfo = new XSLFFontInfo(ppt, header.getFamilyName());
        fontInfo.addFacet(is);
        return fontInfo;
    }

    public static List<XSLFFontInfo> getFonts(XMLSlideShow ppt) {
        CTPresentation pres = ppt.getCTPresentation();
        return pres.isSetEmbeddedFontLst() ? Stream.of(pres.getEmbeddedFontLst().getEmbeddedFontArray()).map(fe -> new XSLFFontInfo(ppt, (CTEmbeddedFontListEntry)fe)).collect(Collectors.toList()) : Collections.emptyList();
    }

    private final class XSLFFontFacet
    implements FontFacet {
        private final CTEmbeddedFontDataId fontEntry;
        private final FontHeader header = new FontHeader();

        private XSLFFontFacet(CTEmbeddedFontDataId fontEntry) {
            this.fontEntry = fontEntry;
        }

        @Override
        public int getWeight() {
            this.init();
            return this.header.getWeight();
        }

        @Override
        public boolean isItalic() {
            this.init();
            return this.header.isItalic();
        }

        @Override
        public XSLFFontData getFontData() {
            return (XSLFFontData)XSLFFontInfo.this.ppt.getRelationPartById(this.fontEntry.getId()).getDocumentPart();
        }

        void setFontData(InputStream is) throws IOException {
            XSLFFontData fntData;
            XSLFRelation fntRel = XSLFRelation.FONT;
            String relId = this.fontEntry.getId();
            if (relId == null || relId.isEmpty()) {
                int fntDataIdx;
                try {
                    fntDataIdx = XSLFFontInfo.this.ppt.getPackage().getUnusedPartIndex(fntRel.getDefaultFileName());
                } catch (InvalidFormatException e) {
                    throw new RuntimeException(e);
                }
                POIXMLDocumentPart.RelationPart rp = XSLFFontInfo.this.ppt.createRelationship(fntRel, XSLFFactory.getInstance(), fntDataIdx, false);
                fntData = (XSLFFontData)rp.getDocumentPart();
                this.fontEntry.setId(rp.getRelationship().getId());
            } else {
                fntData = (XSLFFontData)XSLFFontInfo.this.ppt.getRelationById(relId);
            }
            assert (fntData != null);
            try (OutputStream os = fntData.getOutputStream();){
                IOUtils.copy(is, os);
            }
        }

        private void init() {
            if (this.header.getFamilyName() == null) {
                try (InputStream is = this.getFontData().getInputStream();){
                    byte[] buf = IOUtils.toByteArray(is, 1000);
                    this.header.init(buf, 0, buf.length);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

