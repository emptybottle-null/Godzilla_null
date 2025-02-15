/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.validation.SchemaFactory;
import org.apache.poi.util.Internal;
import org.apache.poi.util.POILogFactory;
import org.apache.poi.util.POILogger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

@Internal
public final class XMLHelper {
    static final String FEATURE_LOAD_DTD_GRAMMAR = "http://apache.org/xml/features/nonvalidating/load-dtd-grammar";
    static final String FEATURE_LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    static final String FEATURE_DISALLOW_DOCTYPE_DECL = "http://apache.org/xml/features/disallow-doctype-decl";
    static final String FEATURE_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    static final String FEATURE_EXTERNAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    static final String PROPERTY_ENTITY_EXPANSION_LIMIT = "http://www.oracle.com/xml/jaxp/properties/entityExpansionLimit";
    static final String PROPERTY_SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    static final String METHOD_ENTITY_EXPANSION_XERCES = "setEntityExpansionLimit";
    static final String[] SECURITY_MANAGERS = new String[]{"org.apache.xerces.util.SecurityManager"};
    private static POILogger logger = POILogFactory.getLogger(XMLHelper.class);
    private static long lastLog;
    private static final DocumentBuilderFactory documentBuilderFactory;
    private static final SAXParserFactory saxFactory;

    private XMLHelper() {
    }

    public static DocumentBuilderFactory getDocumentBuilderFactory() {
        DocumentBuilderFactory factory;
        block3: {
            block2: {
                factory = DocumentBuilderFactory.newInstance();
                factory.setNamespaceAware(true);
                factory.setExpandEntityReferences(false);
                factory.setValidating(false);
                XMLHelper.trySet(factory::setFeature, "http://javax.xml.XMLConstants/feature/secure-processing", true);
                XMLHelper.trySet(factory::setAttribute, "http://javax.xml.XMLConstants/property/accessExternalSchema", "");
                XMLHelper.trySet(factory::setAttribute, "http://javax.xml.XMLConstants/property/accessExternalDTD", "");
                XMLHelper.trySet(factory::setFeature, FEATURE_EXTERNAL_ENTITIES, false);
                XMLHelper.trySet(factory::setFeature, FEATURE_PARAMETER_ENTITIES, false);
                XMLHelper.trySet(factory::setFeature, FEATURE_LOAD_EXTERNAL_DTD, false);
                XMLHelper.trySet(factory::setFeature, FEATURE_LOAD_DTD_GRAMMAR, false);
                XMLHelper.trySet(factory::setFeature, FEATURE_DISALLOW_DOCTYPE_DECL, true);
                XMLHelper.trySet((String n, boolean b) -> factory.setXIncludeAware(b), "XIncludeAware", false);
                Object manager = XMLHelper.getXercesSecurityManager();
                if (manager == null) break block2;
                if (XMLHelper.trySet(factory::setAttribute, PROPERTY_SECURITY_MANAGER, manager)) break block3;
            }
            XMLHelper.trySet(factory::setAttribute, PROPERTY_ENTITY_EXPANSION_LIMIT, 1);
        }
        return factory;
    }

    public static DocumentBuilder newDocumentBuilder() {
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            documentBuilder.setEntityResolver(XMLHelper::ignoreEntity);
            documentBuilder.setErrorHandler(new DocHelperErrorHandler());
            return documentBuilder;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException("cannot create a DocumentBuilder", e);
        }
    }

    public static SAXParserFactory getSaxParserFactory() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(false);
            factory.setNamespaceAware(true);
            XMLHelper.trySet(factory::setFeature, "http://javax.xml.XMLConstants/feature/secure-processing", true);
            XMLHelper.trySet(factory::setFeature, FEATURE_LOAD_DTD_GRAMMAR, false);
            XMLHelper.trySet(factory::setFeature, FEATURE_LOAD_EXTERNAL_DTD, false);
            XMLHelper.trySet(factory::setFeature, FEATURE_EXTERNAL_ENTITIES, false);
            return factory;
        } catch (Error | RuntimeException re) {
            XMLHelper.logThrowable(re, "Failed to create SAXParserFactory", "-");
            throw re;
        } catch (Exception e) {
            XMLHelper.logThrowable(e, "Failed to create SAXParserFactory", "-");
            throw new RuntimeException("Failed to create SAXParserFactory", e);
        }
    }

    public static XMLReader newXMLReader() throws SAXException, ParserConfigurationException {
        XMLReader xmlReader;
        block3: {
            block2: {
                xmlReader = saxFactory.newSAXParser().getXMLReader();
                xmlReader.setEntityResolver(XMLHelper::ignoreEntity);
                XMLHelper.trySet(xmlReader::setFeature, "http://javax.xml.XMLConstants/feature/secure-processing", true);
                XMLHelper.trySet(xmlReader::setFeature, FEATURE_EXTERNAL_ENTITIES, false);
                Object manager = XMLHelper.getXercesSecurityManager();
                if (manager == null) break block2;
                if (XMLHelper.trySet(xmlReader::setProperty, PROPERTY_SECURITY_MANAGER, manager)) break block3;
            }
            XMLHelper.trySet(xmlReader::setProperty, PROPERTY_ENTITY_EXPANSION_LIMIT, 1);
        }
        return xmlReader;
    }

    public static XMLInputFactory newXMLInputFactory() {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLHelper.trySet(factory::setProperty, "javax.xml.stream.isNamespaceAware", true);
        XMLHelper.trySet(factory::setProperty, "javax.xml.stream.isValidating", false);
        XMLHelper.trySet(factory::setProperty, "javax.xml.stream.supportDTD", false);
        XMLHelper.trySet(factory::setProperty, "javax.xml.stream.isSupportingExternalEntities", false);
        return factory;
    }

    public static XMLOutputFactory newXMLOutputFactory() {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLHelper.trySet(factory::setProperty, "javax.xml.stream.isRepairingNamespaces", true);
        return factory;
    }

    public static XMLEventFactory newXMLEventFactory() {
        return XMLEventFactory.newInstance();
    }

    public static TransformerFactory getTransformerFactory() {
        TransformerFactory factory = TransformerFactory.newInstance();
        XMLHelper.trySet(factory::setFeature, "http://javax.xml.XMLConstants/feature/secure-processing", true);
        XMLHelper.trySet(factory::setAttribute, "http://javax.xml.XMLConstants/property/accessExternalDTD", "");
        XMLHelper.trySet(factory::setAttribute, "http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
        return factory;
    }

    public static Transformer newTransformer() throws TransformerConfigurationException {
        Transformer serializer = XMLHelper.getTransformerFactory().newTransformer();
        serializer.setOutputProperty("encoding", "UTF-8");
        serializer.setOutputProperty("indent", "no");
        serializer.setOutputProperty("method", "xml");
        return serializer;
    }

    public static SchemaFactory getSchemaFactory() {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        XMLHelper.trySet(factory::setFeature, "http://javax.xml.XMLConstants/feature/secure-processing", true);
        XMLHelper.trySet(factory::setProperty, "http://javax.xml.XMLConstants/property/accessExternalDTD", "");
        XMLHelper.trySet(factory::setProperty, "http://javax.xml.XMLConstants/property/accessExternalSchema", "");
        return factory;
    }

    private static Object getXercesSecurityManager() {
        for (String securityManagerClassName : SECURITY_MANAGERS) {
            try {
                Object mgr = Class.forName(securityManagerClassName).newInstance();
                Method setLimit = mgr.getClass().getMethod(METHOD_ENTITY_EXPANSION_XERCES, Integer.TYPE);
                setLimit.invoke(mgr, 1);
                return mgr;
            } catch (ClassNotFoundException mgr) {
            } catch (Throwable e) {
                XMLHelper.logThrowable(e, "SAX Feature unsupported", securityManagerClassName);
            }
        }
        return null;
    }

    private static boolean trySet(SecurityFeature feature, String name, boolean value) {
        try {
            feature.accept(name, value);
            return true;
        } catch (Exception e) {
            XMLHelper.logThrowable(e, "SAX Feature unsupported", name);
        } catch (AbstractMethodError ame) {
            XMLHelper.logThrowable(ame, "Cannot set SAX feature because outdated XML parser in classpath", name);
        }
        return false;
    }

    private static boolean trySet(SecurityProperty property, String name, Object value) {
        try {
            property.accept(name, value);
            return true;
        } catch (Exception e) {
            XMLHelper.logThrowable(e, "SAX Feature unsupported", name);
        } catch (AbstractMethodError ame) {
            XMLHelper.logThrowable(ame, "Cannot set SAX feature because outdated XML parser in classpath", name);
        }
        return false;
    }

    private static void logThrowable(Throwable t, String message, String name) {
        if (System.currentTimeMillis() > lastLog + TimeUnit.MINUTES.toMillis(5L)) {
            logger.log(5, message + " [log suppressed for 5 minutes]", name, t);
            lastLog = System.currentTimeMillis();
        }
    }

    private static InputSource ignoreEntity(String publicId, String systemId) {
        return new InputSource(new StringReader(""));
    }

    static {
        documentBuilderFactory = XMLHelper.getDocumentBuilderFactory();
        saxFactory = XMLHelper.getSaxParserFactory();
    }

    private static class DocHelperErrorHandler
    implements ErrorHandler {
        private DocHelperErrorHandler() {
        }

        @Override
        public void warning(SAXParseException exception) {
            this.printError(5, exception);
        }

        @Override
        public void error(SAXParseException exception) {
            this.printError(7, exception);
        }

        @Override
        public void fatalError(SAXParseException exception) throws SAXException {
            this.printError(9, exception);
            throw exception;
        }

        private void printError(int type, SAXParseException ex) {
            int index;
            String systemId = ex.getSystemId();
            if (systemId != null && (index = systemId.lastIndexOf(47)) != -1) {
                systemId = systemId.substring(index + 1);
            }
            String message = (systemId == null ? "" : systemId) + ':' + ex.getLineNumber() + ':' + ex.getColumnNumber() + ':' + ex.getMessage();
            logger.log(type, message, ex);
        }
    }

    @FunctionalInterface
    private static interface SecurityProperty {
        public void accept(String var1, Object var2) throws SAXException;
    }

    @FunctionalInterface
    private static interface SecurityFeature {
        public void accept(String var1, boolean var2) throws ParserConfigurationException, SAXException, TransformerException;
    }
}

