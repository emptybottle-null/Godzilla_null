/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xwpf.usermodel;

import org.apache.poi.ooxml.POIXMLFactory;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.xwpf.usermodel.XWPFRelation;

public final class XWPFFactory
extends POIXMLFactory {
    private static final XWPFFactory inst = new XWPFFactory();

    public static XWPFFactory getInstance() {
        return inst;
    }

    private XWPFFactory() {
    }

    @Override
    protected POIXMLRelation getDescriptor(String relationshipType) {
        return XWPFRelation.getInstance(relationshipType);
    }
}

