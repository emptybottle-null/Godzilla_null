/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.ooxml.util;

import javax.xml.xpath.XPathFactory;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;

public final class XPathHelper {
    private static POILogger logger = POILogFactory.getLogger(XPathHelper.class);
    static final XPathFactory xpathFactory = XPathFactory.newInstance();

    private XPathHelper() {
    }

    public static XPathFactory getFactory() {
        return xpathFactory;
    }

    private static void trySetFeature(XPathFactory xpf, String feature, boolean enabled) {
        try {
            xpf.setFeature(feature, enabled);
        } catch (Exception e) {
            logger.log(5, "XPathFactory Feature unsupported", feature, e);
        } catch (AbstractMethodError ame) {
            logger.log(5, "Cannot set XPathFactory feature because outdated XML parser in classpath", feature, ame);
        }
    }

    static {
        XPathHelper.trySetFeature(xpathFactory, "http://javax.xml.XMLConstants/feature/secure-processing", true);
    }
}

