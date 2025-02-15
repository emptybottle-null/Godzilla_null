/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.crypt.dsig;

import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ooxml.POIXMLRelation;

public class DSigRelation
extends POIXMLRelation {
    private static final Map<String, DSigRelation> _table = new HashMap<String, DSigRelation>();
    public static final DSigRelation ORIGIN_SIGS = new DSigRelation("application/vnd.openxmlformats-package.digital-signature-origin", "http://schemas.openxmlformats.org/package/2006/relationships/digital-signature/origin", "/_xmlsignatures/origin.sigs");
    public static final DSigRelation SIG = new DSigRelation("application/vnd.openxmlformats-package.digital-signature-xmlsignature+xml", "http://schemas.openxmlformats.org/package/2006/relationships/digital-signature/signature", "/_xmlsignatures/sig#.xml");

    private DSigRelation(String type, String rel, String defaultName) {
        super(type, rel, defaultName);
        _table.put(rel, this);
    }

    public static DSigRelation getInstance(String rel) {
        return _table.get(rel);
    }
}

