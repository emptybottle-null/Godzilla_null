/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.apache.xmlbeans.XmlOptionsBean;
import org.apache.xmlbeans.impl.common.XBLogFactory;
import org.apache.xmlbeans.impl.common.XBLogger;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public final class SAXHelper {
    private static final XBLogger logger = XBLogFactory.getLogger(SAXHelper.class);
    private static long lastLog;
    public static final EntityResolver IGNORING_ENTITY_RESOLVER;

    private SAXHelper() {
    }

    public static XMLReader newXMLReader(XmlOptionsBean options) throws SAXException, ParserConfigurationException {
        XMLReader xmlReader = SAXHelper.saxFactory(options).newSAXParser().getXMLReader();
        xmlReader.setEntityResolver(IGNORING_ENTITY_RESOLVER);
        SAXHelper.trySetSAXFeature(xmlReader, "http://javax.xml.XMLConstants/feature/secure-processing");
        SAXHelper.trySetXercesSecurityManager(xmlReader, options);
        return xmlReader;
    }

    static SAXParserFactory saxFactory() {
        return SAXHelper.saxFactory(new XmlOptionsBean());
    }

    static SAXParserFactory saxFactory(XmlOptionsBean options) {
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        saxFactory.setValidating(false);
        saxFactory.setNamespaceAware(true);
        SAXHelper.trySetSAXFeature(saxFactory, "http://javax.xml.XMLConstants/feature/secure-processing", true);
        SAXHelper.trySetSAXFeature(saxFactory, "http://apache.org/xml/features/nonvalidating/load-dtd-grammar", options.isLoadDTDGrammar());
        SAXHelper.trySetSAXFeature(saxFactory, "http://apache.org/xml/features/nonvalidating/load-external-dtd", options.isLoadExternalDTD());
        return saxFactory;
    }

    private static void trySetSAXFeature(SAXParserFactory spf, String feature, boolean flag) {
        try {
            spf.setFeature(feature, flag);
        } catch (Exception e) {
            logger.log(5, "SAX Feature unsupported", feature, e);
        } catch (AbstractMethodError ame) {
            logger.log(5, "Cannot set SAX feature because outdated XML parser in classpath", feature, ame);
        }
    }

    private static void trySetSAXFeature(XMLReader xmlReader, String feature) {
        try {
            xmlReader.setFeature(feature, true);
        } catch (Exception e) {
            logger.log(5, "SAX Feature unsupported", feature, e);
        } catch (AbstractMethodError ame) {
            logger.log(5, "Cannot set SAX feature because outdated XML parser in classpath", feature, ame);
        }
    }

    private static void trySetXercesSecurityManager(XMLReader xmlReader, XmlOptionsBean options) {
        block5: {
            for (String securityManagerClassName : new String[]{"org.apache.xerces.util.SecurityManager"}) {
                try {
                    Object mgr = Class.forName(securityManagerClassName).newInstance();
                    Method setLimit = mgr.getClass().getMethod("setEntityExpansionLimit", Integer.TYPE);
                    setLimit.invoke(mgr, options.getEntityExpansionLimit());
                    xmlReader.setProperty("http://apache.org/xml/properties/security-manager", mgr);
                    return;
                } catch (Throwable e) {
                    if (System.currentTimeMillis() <= lastLog + TimeUnit.MINUTES.toMillis(5L)) continue;
                    logger.log(5, "SAX Security Manager could not be setup [log suppressed for 5 minutes]", e);
                    lastLog = System.currentTimeMillis();
                }
            }
            try {
                xmlReader.setProperty("http://www.oracle.com/xml/jaxp/properties/entityExpansionLimit", options.getEntityExpansionLimit());
            } catch (SAXException e) {
                if (System.currentTimeMillis() <= lastLog + TimeUnit.MINUTES.toMillis(5L)) break block5;
                logger.log(5, "SAX Security Manager could not be setup [log suppressed for 5 minutes]", e);
                lastLog = System.currentTimeMillis();
            }
        }
    }

    static {
        IGNORING_ENTITY_RESOLVER = new EntityResolver(){

            @Override
            public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
                return new InputSource(new StringReader(""));
            }
        };
    }
}

