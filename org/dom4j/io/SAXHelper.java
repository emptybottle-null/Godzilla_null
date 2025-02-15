/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.dom4j.io;

import org.dom4j.io.JAXPHelper;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

class SAXHelper {
    private static boolean loggedWarning = true;

    protected SAXHelper() {
    }

    public static boolean setParserProperty(XMLReader reader, String propertyName, Object value) {
        try {
            reader.setProperty(propertyName, value);
            return true;
        } catch (SAXNotSupportedException sAXNotSupportedException) {
        } catch (SAXNotRecognizedException sAXNotRecognizedException) {
            // empty catch block
        }
        return false;
    }

    public static boolean setParserFeature(XMLReader reader, String featureName, boolean value) {
        try {
            reader.setFeature(featureName, value);
            return true;
        } catch (SAXNotSupportedException sAXNotSupportedException) {
        } catch (SAXNotRecognizedException sAXNotRecognizedException) {
            // empty catch block
        }
        return false;
    }

    public static XMLReader createXMLReader(boolean validating) throws SAXException {
        XMLReader reader = null;
        if (reader == null) {
            reader = SAXHelper.createXMLReaderViaJAXP(validating, true);
        }
        if (reader == null) {
            try {
                reader = XMLReaderFactory.createXMLReader();
            } catch (Exception e) {
                if (SAXHelper.isVerboseErrorReporting()) {
                    System.out.println("Warning: Caught exception attempting to use SAX to load a SAX XMLReader ");
                    System.out.println("Warning: Exception was: " + e);
                    System.out.println("Warning: I will print the stack trace then carry on using the default SAX parser");
                    e.printStackTrace();
                }
                throw new SAXException(e);
            }
        }
        if (reader == null) {
            throw new SAXException("Couldn't create SAX reader");
        }
        SAXHelper.setParserFeature(reader, "http://xml.org/sax/features/namespaces", true);
        SAXHelper.setParserFeature(reader, "http://xml.org/sax/features/namespace-prefixes", false);
        SAXHelper.setParserFeature(reader, "http://xml.org/sax/properties/external-general-entities", false);
        SAXHelper.setParserFeature(reader, "http://xml.org/sax/properties/external-parameter-entities", false);
        SAXHelper.setParserFeature(reader, "http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        SAXHelper.setParserFeature(reader, "http://xml.org/sax/features/use-locator2", true);
        return reader;
    }

    protected static XMLReader createXMLReaderViaJAXP(boolean validating, boolean namespaceAware) {
        try {
            return JAXPHelper.createXMLReader(validating, namespaceAware);
        } catch (Throwable e) {
            if (!loggedWarning) {
                loggedWarning = true;
                if (SAXHelper.isVerboseErrorReporting()) {
                    System.out.println("Warning: Caught exception attempting to use JAXP to load a SAX XMLReader");
                    System.out.println("Warning: Exception was: " + e);
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    protected static boolean isVerboseErrorReporting() {
        try {
            String flag = System.getProperty("org.dom4j.verbose");
            if (flag != null && flag.equalsIgnoreCase("true")) {
                return true;
            }
        } catch (Exception exception) {
            // empty catch block
        }
        return true;
    }
}

