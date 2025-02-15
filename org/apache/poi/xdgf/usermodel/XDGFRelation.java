/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xdgf.usermodel;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLRelation;
import org.apache.poi.xdgf.usermodel.XDGFMasterContents;
import org.apache.poi.xdgf.usermodel.XDGFMasters;
import org.apache.poi.xdgf.usermodel.XDGFPageContents;
import org.apache.poi.xdgf.usermodel.XDGFPages;

public class XDGFRelation
extends POIXMLRelation {
    private static final Map<String, XDGFRelation> _table = new HashMap<String, XDGFRelation>();
    public static final XDGFRelation DOCUMENT = new XDGFRelation("application/vnd.ms-visio.drawing.main+xml", "http://schemas.microsoft.com/visio/2010/relationships/document", "/visio/document.xml", null);
    public static final XDGFRelation MASTERS = new XDGFRelation("application/vnd.ms-visio.masters+xml", "http://schemas.microsoft.com/visio/2010/relationships/masters", "/visio/masters/masters.xml", XDGFMasters::new);
    public static final XDGFRelation MASTER = new XDGFRelation("application/vnd.ms-visio.master+xml", "http://schemas.microsoft.com/visio/2010/relationships/master", "/visio/masters/master#.xml", XDGFMasterContents::new);
    public static final XDGFRelation IMAGES = new XDGFRelation(null, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/image", null, null);
    public static final XDGFRelation PAGES = new XDGFRelation("application/vnd.ms-visio.pages+xml", "http://schemas.microsoft.com/visio/2010/relationships/pages", "/visio/pages/pages.xml", XDGFPages::new);
    public static final XDGFRelation PAGE = new XDGFRelation("application/vnd.ms-visio.page+xml", "http://schemas.microsoft.com/visio/2010/relationships/page", "/visio/pages/page#.xml", XDGFPageContents::new);
    public static final XDGFRelation WINDOW = new XDGFRelation("application/vnd.ms-visio.windows+xml", "http://schemas.microsoft.com/visio/2010/relationships/windows", "/visio/windows.xml", null);

    private XDGFRelation(String type, String rel, String defaultName, POIXMLRelation.PackagePartConstructor packagePartConstructor) {
        super(type, rel, defaultName, null, packagePartConstructor, null);
        _table.put(rel, this);
    }

    public static XDGFRelation getInstance(String rel) {
        return _table.get(rel);
    }
}

