/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import org.apache.xmlbeans.XmlOptionsBean;
import org.apache.xmlbeans.impl.common.XBLogFactory;
import org.apache.xmlbeans.impl.common.XBLogger;

public final class StaxHelper {
    private static final XBLogger logger = XBLogFactory.getLogger(StaxHelper.class);

    private StaxHelper() {
    }

    public static XMLInputFactory newXMLInputFactory(XmlOptionsBean options) {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        StaxHelper.trySetProperty(factory, "javax.xml.stream.isNamespaceAware", true);
        StaxHelper.trySetProperty(factory, "javax.xml.stream.isValidating", false);
        StaxHelper.trySetProperty(factory, "javax.xml.stream.supportDTD", options.isLoadDTDGrammar());
        StaxHelper.trySetProperty(factory, "javax.xml.stream.isSupportingExternalEntities", options.isLoadExternalDTD());
        return factory;
    }

    public static XMLOutputFactory newXMLOutputFactory(XmlOptionsBean options) {
        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        StaxHelper.trySetProperty(factory, "javax.xml.stream.isRepairingNamespaces", true);
        return factory;
    }

    public static XMLEventFactory newXMLEventFactory(XmlOptionsBean options) {
        return XMLEventFactory.newFactory();
    }

    private static void trySetProperty(XMLInputFactory factory, String feature, boolean flag) {
        try {
            factory.setProperty(feature, flag);
        } catch (Exception e) {
            logger.log(5, "StAX Property unsupported", feature, e);
        } catch (AbstractMethodError ame) {
            logger.log(5, "Cannot set StAX property because outdated StAX parser in classpath", feature, ame);
        }
    }

    private static void trySetProperty(XMLOutputFactory factory, String feature, boolean flag) {
        try {
            factory.setProperty(feature, flag);
        } catch (Exception e) {
            logger.log(5, "StAX Property unsupported", feature, e);
        } catch (AbstractMethodError ame) {
            logger.log(5, "Cannot set StAX property because outdated StAX parser in classpath", feature, ame);
        }
    }
}

