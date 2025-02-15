/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

@Internal
public class XSSFBRelation
extends POIXMLRelation {
    private static final POILogger log = POILogFactory.getLogger(XSSFBRelation.class);
    static final XSSFBRelation SHARED_STRINGS_BINARY = new XSSFBRelation("application/vnd.ms-excel.sharedStrings", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/sharedStrings", "/xl/sharedStrings.bin");
    public static final XSSFBRelation STYLES_BINARY = new XSSFBRelation("application/vnd.ms-excel.styles", "http://schemas.openxmlformats.org/officeDocument/2006/relationships/styles", "/xl/styles.bin");

    private XSSFBRelation(String type, String rel, String defaultName) {
        super(type, rel, defaultName);
    }
}

