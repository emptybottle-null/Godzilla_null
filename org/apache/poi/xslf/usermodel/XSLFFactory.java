/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xslf.usermodel;

import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.xslf.usermodel.XSLFRelation;

public final class XSLFFactory
extends POIXMLFactory {
    private static final XSLFFactory inst = new XSLFFactory();

    public static XSLFFactory getInstance() {
        return inst;
    }

    private XSLFFactory() {
    }

    @Override
    protected POIXMLRelation getDescriptor(String relationshipType) {
        return XSLFRelation.getInstance(relationshipType);
    }
}

