/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml.extractor;

import java.io.IOException;
import org.apache.poi.extractor.POITextExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ooxml.extractor.POIXMLPropertiesTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;

public abstract class POIXMLTextExtractor
extends POITextExtractor {
    private final POIXMLDocument _document;

    public POIXMLTextExtractor(POIXMLDocument document) {
        this._document = document;
    }

    public POIXMLProperties.CoreProperties getCoreProperties() {
        return this._document.getProperties().getCoreProperties();
    }

    public POIXMLProperties.ExtendedProperties getExtendedProperties() {
        return this._document.getProperties().getExtendedProperties();
    }

    public POIXMLProperties.CustomProperties getCustomProperties() {
        return this._document.getProperties().getCustomProperties();
    }

    @Override
    public final POIXMLDocument getDocument() {
        return this._document;
    }

    public OPCPackage getPackage() {
        return this._document.getPackage();
    }

    @Override
    public POIXMLPropertiesTextExtractor getMetadataTextExtractor() {
        return new POIXMLPropertiesTextExtractor(this._document);
    }

    @Override
    public void close() throws IOException {
        OPCPackage pkg;
        if (this._document != null && (pkg = this._document.getPackage()) != null) {
            pkg.revert();
        }
        super.close();
    }

    protected void checkMaxTextSize(CharSequence text, String string) {
        if (string == null) {
            return;
        }
        int size = text.length() + string.length();
        if ((long)size > ZipSecureFile.getMaxTextSize()) {
            throw new IllegalStateException("The text would exceed the max allowed overall size of extracted text. By default this is prevented as some documents may exhaust available memory and it may indicate that the file is used to inflate memory usage and thus could pose a security risk. You can adjust this limit via ZipSecureFile.setMaxTextSize() if you need to work with files which have a lot of text. Size: " + size + ", limit: MAX_TEXT_SIZE: " + ZipSecureFile.getMaxTextSize());
        }
    }
}

