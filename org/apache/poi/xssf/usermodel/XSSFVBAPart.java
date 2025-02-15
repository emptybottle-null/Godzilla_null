/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;

public class XSSFVBAPart
extends POIXMLDocumentPart {
    protected XSSFVBAPart() {
    }

    protected XSSFVBAPart(PackagePart part) {
        super(part);
    }

    @Override
    protected void prepareForCommit() {
    }
}

