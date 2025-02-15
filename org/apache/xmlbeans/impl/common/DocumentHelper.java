/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.common;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xmlbeans.XmlOptionsBean;
import org.apache.xmlbeans.impl.common.SAXHelper;
import org.apache.xmlbeans.impl.common.XBLogFactory;
import org.apache.xmlbeans.impl.common.XBLogger;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public final class DocumentHelper {
    private static XBLogger logger = XBLogFactory.getLogger(DocumentHelper.class);
    private static long lastLog;
    private static final DocumentBuilder documentBuilderSingleton;

    private DocumentHelper() {
    }

    public static DocumentBuilder newDocumentBuilder(XmlOptionsBean xmlOptions) {
        try {
            DocumentBuilder documentBuilder = DocumentHelper.documentBuilderFactory(xmlOptions).newDocumentBuilder();
            documentBuilder.setEntityResolver(SAXHelper.IGNORING_ENTITY_RESOLVER);
            documentBuilder.setErrorHandler(new DocHelperErrorHandler());
            return documentBuilder;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException("cannot create a DocumentBuilder", e);
        }
    }

    private static final DocumentBuilderFactory documentBuilderFactory(XmlOptionsBean options) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setValidating(false);
        DocumentHelper.trySetFeature(documentBuilderFactory, "http://javax.xml.XMLConstants/feature/secure-processing", true);
        DocumentHelper.trySetFeature(documentBuilderFactory, "http://apache.org/xml/features/nonvalidating/load-dtd-grammar", options.isLoadDTDGrammar());
        DocumentHelper.trySetFeature(documentBuilderFactory, "http://apache.org/xml/features/nonvalidating/load-external-dtd", options.isLoadExternalDTD());
        DocumentHelper.trySetXercesSecurityManager(documentBuilderFactory, options);
        return documentBuilderFactory;
    }

    private static void trySetFeature(DocumentBuilderFactory dbf, String feature, boolean enabled) {
        try {
            dbf.setFeature(feature, enabled);
        } catch (Exception e) {
            logger.log(5, "SAX Feature unsupported", feature, e);
        } catch (AbstractMethodError ame) {
            logger.log(5, "Cannot set SAX feature because outdated XML parser in classpath", feature, ame);
        }
    }

    private static void trySetXercesSecurityManager(DocumentBuilderFactory dbf, XmlOptionsBean options) {
        block6: {
            for (String securityManagerClassName : new String[]{"org.apache.xerces.util.SecurityManager"}) {
                try {
                    Object mgr = Class.forName(securityManagerClassName).newInstance();
                    Method setLimit = mgr.getClass().getMethod("setEntityExpansionLimit", Integer.TYPE);
                    setLimit.invoke(mgr, options.getEntityExpansionLimit());
                    dbf.setAttribute("http://apache.org/xml/properties/security-manager", mgr);
                    return;
                } catch (ClassNotFoundException e) {
                } catch (Throwable e) {
                    if (System.currentTimeMillis() <= lastLog + TimeUnit.MINUTES.toMillis(5L)) continue;
                    logger.log(5, "DocumentBuilderFactory Security Manager could not be setup [log suppressed for 5 minutes]", e);
                    lastLog = System.currentTimeMillis();
                }
            }
            try {
                dbf.setAttribute("http://www.oracle.com/xml/jaxp/properties/entityExpansionLimit", options.getEntityExpansionLimit());
            } catch (Throwable e) {
                if (System.currentTimeMillis() <= lastLog + TimeUnit.MINUTES.toMillis(5L)) break block6;
                logger.log(5, "DocumentBuilderFactory Entity Expansion Limit could not be setup [log suppressed for 5 minutes]", e);
                lastLog = System.currentTimeMillis();
            }
        }
    }

    public static Document readDocument(XmlOptionsBean xmlOptions, InputStream inp) throws IOException, SAXException {
        return DocumentHelper.newDocumentBuilder(xmlOptions).parse(inp);
    }

    public static Document readDocument(XmlOptionsBean xmlOptions, InputSource inp) throws IOException, SAXException {
        return DocumentHelper.newDocumentBuilder(xmlOptions).parse(inp);
    }

    public static Document createDocument() {
        return documentBuilderSingleton.newDocument();
    }

    static {
        documentBuilderSingleton = DocumentHelper.newDocumentBuilder(new XmlOptionsBean());
    }

    private static class DocHelperErrorHandler
    implements ErrorHandler {
        private DocHelperErrorHandler() {
        }

        @Override
        public void warning(SAXParseException exception) throws SAXException {
            this.printError(5, exception);
        }

        @Override
        public void error(SAXParseException exception) throws SAXException {
            this.printError(7, exception);
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
            this.printError(9, exception);
            throw exception;
        }

        private void printError(int type, SAXParseException ex) {
            StringBuilder sb = new StringBuilder();
            String systemId = ex.getSystemId();
            if (systemId != null) {
                int index = systemId.lastIndexOf(47);
                if (index != -1) {
                    systemId = systemId.substring(index + 1);
                }
                sb.append(systemId);
            }
            sb.append(':');
            sb.append(ex.getLineNumber());
            sb.append(':');
            sb.append(ex.getColumnNumber());
            sb.append(": ");
            sb.append(ex.getMessage());
            logger.log(type, sb.toString(), ex);
        }
    }
}

