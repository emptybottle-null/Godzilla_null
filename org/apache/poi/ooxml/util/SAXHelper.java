/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml.util;

import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.util.Removal;
import org.apache.poi.util.XMLHelper;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

@Deprecated
@Removal(version="5.0.0")
public final class SAXHelper {
    public static XMLReader newXMLReader() throws SAXException, ParserConfigurationException {
        return XMLHelper.newXMLReader();
    }
}

