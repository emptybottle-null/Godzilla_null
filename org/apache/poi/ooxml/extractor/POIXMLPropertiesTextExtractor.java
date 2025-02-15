/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml.extractor;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.internal.PackagePropertiesPart;
import org.apache.poi.util.LocaleUtil;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperties;
import org.openxmlformats.schemas.officeDocument.x2006.customProperties.CTProperty;

public class POIXMLPropertiesTextExtractor
extends POIXMLTextExtractor {
    private final DateFormat dateFormat;

    public POIXMLPropertiesTextExtractor(POIXMLDocument doc) {
        super(doc);
        DateFormatSymbols dfs = DateFormatSymbols.getInstance(Locale.ROOT);
        this.dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", dfs);
        this.dateFormat.setTimeZone(LocaleUtil.TIMEZONE_UTC);
    }

    public POIXMLPropertiesTextExtractor(POIXMLTextExtractor otherExtractor) {
        this(otherExtractor.getDocument());
    }

    private void appendIfPresent(StringBuilder text, String thing, boolean value) {
        this.appendIfPresent(text, thing, Boolean.toString(value));
    }

    private void appendIfPresent(StringBuilder text, String thing, int value) {
        this.appendIfPresent(text, thing, Integer.toString(value));
    }

    private void appendDateIfPresent(StringBuilder text, String thing, Optional<Date> value) {
        if (!value.isPresent()) {
            return;
        }
        this.appendIfPresent(text, thing, this.dateFormat.format(value.get()));
    }

    private void appendIfPresent(StringBuilder text, String thing, Optional<String> value) {
        if (!value.isPresent()) {
            return;
        }
        this.appendIfPresent(text, thing, value.get());
    }

    private void appendIfPresent(StringBuilder text, String thing, String value) {
        if (value == null) {
            return;
        }
        text.append(thing);
        text.append(" = ");
        text.append(value);
        text.append('\n');
    }

    public String getCorePropertiesText() {
        POIXMLDocument document = this.getDocument();
        if (document == null) {
            return "";
        }
        StringBuilder text = new StringBuilder(64);
        PackagePropertiesPart props = document.getProperties().getCoreProperties().getUnderlyingProperties();
        this.appendIfPresent(text, "Category", props.getCategoryProperty());
        this.appendIfPresent(text, "Category", props.getCategoryProperty());
        this.appendIfPresent(text, "ContentStatus", props.getContentStatusProperty());
        this.appendIfPresent(text, "ContentType", props.getContentTypeProperty());
        this.appendDateIfPresent(text, "Created", props.getCreatedProperty());
        this.appendIfPresent(text, "CreatedString", props.getCreatedPropertyString());
        this.appendIfPresent(text, "Creator", props.getCreatorProperty());
        this.appendIfPresent(text, "Description", props.getDescriptionProperty());
        this.appendIfPresent(text, "Identifier", props.getIdentifierProperty());
        this.appendIfPresent(text, "Keywords", props.getKeywordsProperty());
        this.appendIfPresent(text, "Language", props.getLanguageProperty());
        this.appendIfPresent(text, "LastModifiedBy", props.getLastModifiedByProperty());
        this.appendDateIfPresent(text, "LastPrinted", props.getLastPrintedProperty());
        this.appendIfPresent(text, "LastPrintedString", props.getLastPrintedPropertyString());
        this.appendDateIfPresent(text, "Modified", props.getModifiedProperty());
        this.appendIfPresent(text, "ModifiedString", props.getModifiedPropertyString());
        this.appendIfPresent(text, "Revision", props.getRevisionProperty());
        this.appendIfPresent(text, "Subject", props.getSubjectProperty());
        this.appendIfPresent(text, "Title", props.getTitleProperty());
        this.appendIfPresent(text, "Version", props.getVersionProperty());
        return text.toString();
    }

    public String getExtendedPropertiesText() {
        POIXMLDocument document = this.getDocument();
        if (document == null) {
            return "";
        }
        StringBuilder text = new StringBuilder(64);
        org.openxmlformats.schemas.officeDocument.x2006.extendedProperties.CTProperties props = document.getProperties().getExtendedProperties().getUnderlyingProperties();
        this.appendIfPresent(text, "Application", props.getApplication());
        this.appendIfPresent(text, "AppVersion", props.getAppVersion());
        this.appendIfPresent(text, "Characters", props.getCharacters());
        this.appendIfPresent(text, "CharactersWithSpaces", props.getCharactersWithSpaces());
        this.appendIfPresent(text, "Company", props.getCompany());
        this.appendIfPresent(text, "HyperlinkBase", props.getHyperlinkBase());
        this.appendIfPresent(text, "HyperlinksChanged", props.getHyperlinksChanged());
        this.appendIfPresent(text, "Lines", props.getLines());
        this.appendIfPresent(text, "LinksUpToDate", props.getLinksUpToDate());
        this.appendIfPresent(text, "Manager", props.getManager());
        this.appendIfPresent(text, "Pages", props.getPages());
        this.appendIfPresent(text, "Paragraphs", props.getParagraphs());
        this.appendIfPresent(text, "PresentationFormat", props.getPresentationFormat());
        this.appendIfPresent(text, "Template", props.getTemplate());
        this.appendIfPresent(text, "TotalTime", props.getTotalTime());
        return text.toString();
    }

    public String getCustomPropertiesText() {
        POIXMLDocument document = this.getDocument();
        if (document == null) {
            return "";
        }
        StringBuilder text = new StringBuilder();
        CTProperties props = document.getProperties().getCustomProperties().getUnderlyingProperties();
        for (CTProperty property : props.getPropertyList()) {
            String val = "(not implemented!)";
            if (property.isSetLpwstr()) {
                val = property.getLpwstr();
            } else if (property.isSetLpstr()) {
                val = property.getLpstr();
            } else if (property.isSetDate()) {
                val = property.getDate().toString();
            } else if (property.isSetFiletime()) {
                val = property.getFiletime().toString();
            } else if (property.isSetBool()) {
                val = Boolean.toString(property.getBool());
            } else if (property.isSetI1()) {
                val = Integer.toString(property.getI1());
            } else if (property.isSetI2()) {
                val = Integer.toString(property.getI2());
            } else if (property.isSetI4()) {
                val = Integer.toString(property.getI4());
            } else if (property.isSetI8()) {
                val = Long.toString(property.getI8());
            } else if (property.isSetInt()) {
                val = Integer.toString(property.getInt());
            } else if (property.isSetUi1()) {
                val = Integer.toString(property.getUi1());
            } else if (property.isSetUi2()) {
                val = Integer.toString(property.getUi2());
            } else if (property.isSetUi4()) {
                val = Long.toString(property.getUi4());
            } else if (property.isSetUi8()) {
                val = property.getUi8().toString();
            } else if (property.isSetUint()) {
                val = Long.toString(property.getUint());
            } else if (property.isSetR4()) {
                val = Float.toString(property.getR4());
            } else if (property.isSetR8()) {
                val = Double.toString(property.getR8());
            } else if (property.isSetDecimal()) {
                BigDecimal d = property.getDecimal();
                val = d == null ? null : d.toPlainString();
            }
            text.append(property.getName()).append(" = ").append(val).append("\n");
        }
        return text.toString();
    }

    @Override
    public String getText() {
        try {
            return this.getCorePropertiesText() + this.getExtendedPropertiesText() + this.getCustomPropertiesText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public POIXMLPropertiesTextExtractor getMetadataTextExtractor() {
        throw new IllegalStateException("You already have the Metadata Text Extractor, not recursing!");
    }
}

