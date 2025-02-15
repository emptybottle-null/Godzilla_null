/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.usermodel;

import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.xssf.usermodel.XSSFRelation;

public class XSSFFactory
extends POIXMLFactory {
    private static final XSSFFactory inst = new XSSFFactory();

    public static XSSFFactory getInstance() {
        return inst;
    }

    protected XSSFFactory() {
    }

    @Override
    protected POIXMLRelation getDescriptor(String relationshipType) {
        return XSSFRelation.getInstance(relationshipType);
    }
}

