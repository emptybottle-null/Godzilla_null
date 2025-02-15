/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.hpsf.extractor;

import java.io.File;
import java.io.IOException;
import org.apache.poi.POIDocument;
import org.apache.poi.extractor.POIOLE2TextExtractor;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.hpsf.CustomProperties;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.HPSFPropertiesOnlyDocument;
import org.apache.poi.hpsf.Property;
import org.apache.poi.hpsf.PropertySet;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class HPSFPropertiesExtractor
extends POIOLE2TextExtractor {
    public HPSFPropertiesExtractor(POIOLE2TextExtractor mainExtractor) {
        super(mainExtractor);
    }

    public HPSFPropertiesExtractor(POIDocument doc) {
        super(doc);
    }

    public HPSFPropertiesExtractor(POIFSFileSystem fs) {
        super(new HPSFPropertiesOnlyDocument(fs));
    }

    public String getDocumentSummaryInformationText() {
        CustomProperties cps;
        if (this.document == null) {
            return "";
        }
        DocumentSummaryInformation dsi = this.document.getDocumentSummaryInformation();
        StringBuilder text = new StringBuilder();
        text.append(HPSFPropertiesExtractor.getPropertiesText(dsi));
        CustomProperties customProperties = cps = dsi == null ? null : dsi.getCustomProperties();
        if (cps != null) {
            for (String key : cps.nameSet()) {
                String val = HPSFPropertiesExtractor.getPropertyValueText(cps.get(key));
                text.append(key).append(" = ").append(val).append("\n");
            }
        }
        return text.toString();
    }

    public String getSummaryInformationText() {
        if (this.document == null) {
            return "";
        }
        SummaryInformation si = this.document.getSummaryInformation();
        return HPSFPropertiesExtractor.getPropertiesText(si);
    }

    private static String getPropertiesText(PropertySet ps) {
        Property[] props;
        if (ps == null) {
            return "";
        }
        StringBuilder text = new StringBuilder();
        PropertyIDMap idMap = ps.getPropertySetIDMap();
        for (Property prop : props = ps.getProperties()) {
            String typeObj;
            String type = Long.toString(prop.getID());
            String string = typeObj = idMap == null ? null : idMap.get(prop.getID());
            if (typeObj != null) {
                type = typeObj.toString();
            }
            String val = HPSFPropertiesExtractor.getPropertyValueText(prop.getValue());
            text.append(type).append(" = ").append(val).append("\n");
        }
        return text.toString();
    }

    @Override
    public String getText() {
        return this.getSummaryInformationText() + this.getDocumentSummaryInformationText();
    }

    @Override
    public POITextExtractor getMetadataTextExtractor() {
        throw new IllegalStateException("You already have the Metadata Text Extractor, not recursing!");
    }

    private static String getPropertyValueText(Object val) {
        return val == null ? "(not set)" : PropertySet.getPropertyStringValue(val);
    }

    public boolean equals(Object o) {
        return super.equals(o);
    }

    public int hashCode() {
        return super.hashCode();
    }

    public static void main(String[] args) throws IOException {
        for (String file : args) {
            try (HPSFPropertiesExtractor ext = new HPSFPropertiesExtractor(new POIFSFileSystem(new File(file)));){
                System.out.println(ext.getText());
            }
        }
    }
}

