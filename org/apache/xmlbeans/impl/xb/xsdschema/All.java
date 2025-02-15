/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.xb.xsdschema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlNonNegativeInteger;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.AllNNI;
import org.apache.xmlbeans.impl.xb.xsdschema.ExplicitGroup;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface All
extends ExplicitGroup {
    public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(All.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("all3c04type");

    public static final class Factory {
        public static All newInstance() {
            return (All)XmlBeans.getContextTypeLoader().newInstance(type, null);
        }

        public static All newInstance(XmlOptions options) {
            return (All)XmlBeans.getContextTypeLoader().newInstance(type, options);
        }

        public static All parse(String xmlAsString) throws XmlException {
            return (All)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, null);
        }

        public static All parse(String xmlAsString, XmlOptions options) throws XmlException {
            return (All)XmlBeans.getContextTypeLoader().parse(xmlAsString, type, options);
        }

        public static All parse(File file) throws XmlException, IOException {
            return (All)XmlBeans.getContextTypeLoader().parse(file, type, null);
        }

        public static All parse(File file, XmlOptions options) throws XmlException, IOException {
            return (All)XmlBeans.getContextTypeLoader().parse(file, type, options);
        }

        public static All parse(URL u) throws XmlException, IOException {
            return (All)XmlBeans.getContextTypeLoader().parse(u, type, null);
        }

        public static All parse(URL u, XmlOptions options) throws XmlException, IOException {
            return (All)XmlBeans.getContextTypeLoader().parse(u, type, options);
        }

        public static All parse(InputStream is) throws XmlException, IOException {
            return (All)XmlBeans.getContextTypeLoader().parse(is, type, null);
        }

        public static All parse(InputStream is, XmlOptions options) throws XmlException, IOException {
            return (All)XmlBeans.getContextTypeLoader().parse(is, type, options);
        }

        public static All parse(Reader r) throws XmlException, IOException {
            return (All)XmlBeans.getContextTypeLoader().parse(r, type, null);
        }

        public static All parse(Reader r, XmlOptions options) throws XmlException, IOException {
            return (All)XmlBeans.getContextTypeLoader().parse(r, type, options);
        }

        public static All parse(XMLStreamReader sr) throws XmlException {
            return (All)XmlBeans.getContextTypeLoader().parse(sr, type, null);
        }

        public static All parse(XMLStreamReader sr, XmlOptions options) throws XmlException {
            return (All)XmlBeans.getContextTypeLoader().parse(sr, type, options);
        }

        public static All parse(Node node) throws XmlException {
            return (All)XmlBeans.getContextTypeLoader().parse(node, type, null);
        }

        public static All parse(Node node, XmlOptions options) throws XmlException {
            return (All)XmlBeans.getContextTypeLoader().parse(node, type, options);
        }

        public static All parse(XMLInputStream xis) throws XmlException, XMLStreamException {
            return (All)XmlBeans.getContextTypeLoader().parse(xis, type, null);
        }

        public static All parse(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return (All)XmlBeans.getContextTypeLoader().parse(xis, type, options);
        }

        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xis) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xis, type, null);
        }

        public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xis, XmlOptions options) throws XmlException, XMLStreamException {
            return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xis, type, options);
        }

        private Factory() {
        }
    }

    public static interface MaxOccurs
    extends AllNNI {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(MaxOccurs.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("maxoccurse8b1attrtype");

        @Override
        public Object getObjectValue();

        @Override
        public void setObjectValue(Object var1);

        @Override
        public Object objectValue();

        @Override
        public void objectSet(Object var1);

        @Override
        public SchemaType instanceType();

        public static final class Factory {
            public static MaxOccurs newValue(Object obj) {
                return (MaxOccurs)type.newValue(obj);
            }

            public static MaxOccurs newInstance() {
                return (MaxOccurs)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static MaxOccurs newInstance(XmlOptions options) {
                return (MaxOccurs)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }

    public static interface MinOccurs
    extends XmlNonNegativeInteger {
        public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(MinOccurs.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sXMLSCHEMA").resolveHandle("minoccurs9283attrtype");

        public static final class Factory {
            public static MinOccurs newValue(Object obj) {
                return (MinOccurs)type.newValue(obj);
            }

            public static MinOccurs newInstance() {
                return (MinOccurs)XmlBeans.getContextTypeLoader().newInstance(type, null);
            }

            public static MinOccurs newInstance(XmlOptions options) {
                return (MinOccurs)XmlBeans.getContextTypeLoader().newInstance(type, options);
            }

            private Factory() {
            }
        }
    }
}

